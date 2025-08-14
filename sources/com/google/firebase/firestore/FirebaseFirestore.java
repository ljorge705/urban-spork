package com.google.firebase.firestore;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.interop.InteropAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.FirebaseAppCheckTokenProvider;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.ActivityScope;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.remote.FirestoreChannel;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.ByteBufferInputStream;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.inject.Deferred;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class FirebaseFirestore {
    private static final String TAG = "FirebaseFirestore";
    private final CredentialsProvider<String> appCheckProvider;
    private final AsyncQueue asyncQueue;
    private final CredentialsProvider<User> authProvider;
    private volatile FirestoreClient client;
    private final Context context;
    private final DatabaseId databaseId;
    private EmulatedServiceSettings emulatorSettings;
    private final FirebaseApp firebaseApp;
    private final InstanceRegistry instanceRegistry;
    private final GrpcMetadataProvider metadataProvider;
    private final String persistenceKey;
    private FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().build();
    private final UserDataReader userDataReader;

    public interface InstanceRegistry {
        void remove(String str);
    }

    public FirebaseApp getApp() {
        return this.firebaseApp;
    }

    AsyncQueue getAsyncQueue() {
        return this.asyncQueue;
    }

    FirestoreClient getClient() {
        return this.client;
    }

    DatabaseId getDatabaseId() {
        return this.databaseId;
    }

    public FirebaseFirestoreSettings getFirestoreSettings() {
        return this.settings;
    }

    UserDataReader getUserDataReader() {
        return this.userDataReader;
    }

    public static FirebaseFirestore getInstance() {
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        if (firebaseApp == null) {
            throw new IllegalStateException("You must call FirebaseApp.initializeApp first.");
        }
        return getInstance(firebaseApp, DatabaseId.DEFAULT_DATABASE_ID);
    }

    public static FirebaseFirestore getInstance(FirebaseApp firebaseApp) {
        return getInstance(firebaseApp, DatabaseId.DEFAULT_DATABASE_ID);
    }

    private static FirebaseFirestore getInstance(FirebaseApp firebaseApp, String str) {
        Preconditions.checkNotNull(firebaseApp, "Provided FirebaseApp must not be null.");
        FirestoreMultiDbComponent firestoreMultiDbComponent = (FirestoreMultiDbComponent) firebaseApp.get(FirestoreMultiDbComponent.class);
        Preconditions.checkNotNull(firestoreMultiDbComponent, "Firestore component is not present.");
        return firestoreMultiDbComponent.get(str);
    }

    static FirebaseFirestore newInstance(Context context, FirebaseApp firebaseApp, Deferred<InternalAuthProvider> deferred, Deferred<InteropAppCheckTokenProvider> deferred2, String str, InstanceRegistry instanceRegistry, GrpcMetadataProvider grpcMetadataProvider) {
        String projectId = firebaseApp.getOptions().getProjectId();
        if (projectId == null) {
            throw new IllegalArgumentException("FirebaseOptions.getProjectId() cannot be null");
        }
        DatabaseId databaseIdForDatabase = DatabaseId.forDatabase(projectId, str);
        AsyncQueue asyncQueue = new AsyncQueue();
        return new FirebaseFirestore(context, databaseIdForDatabase, firebaseApp.getName(), new FirebaseAuthCredentialsProvider(deferred), new FirebaseAppCheckTokenProvider(deferred2), asyncQueue, firebaseApp, instanceRegistry, grpcMetadataProvider);
    }

    FirebaseFirestore(Context context, DatabaseId databaseId, String str, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, AsyncQueue asyncQueue, FirebaseApp firebaseApp, InstanceRegistry instanceRegistry, GrpcMetadataProvider grpcMetadataProvider) {
        this.context = (Context) Preconditions.checkNotNull(context);
        this.databaseId = (DatabaseId) Preconditions.checkNotNull((DatabaseId) Preconditions.checkNotNull(databaseId));
        this.userDataReader = new UserDataReader(databaseId);
        this.persistenceKey = (String) Preconditions.checkNotNull(str);
        this.authProvider = (CredentialsProvider) Preconditions.checkNotNull(credentialsProvider);
        this.appCheckProvider = (CredentialsProvider) Preconditions.checkNotNull(credentialsProvider2);
        this.asyncQueue = (AsyncQueue) Preconditions.checkNotNull(asyncQueue);
        this.firebaseApp = firebaseApp;
        this.instanceRegistry = instanceRegistry;
        this.metadataProvider = grpcMetadataProvider;
    }

    public void setFirestoreSettings(FirebaseFirestoreSettings firebaseFirestoreSettings) {
        FirebaseFirestoreSettings firebaseFirestoreSettingsMergeEmulatorSettings = mergeEmulatorSettings(firebaseFirestoreSettings, this.emulatorSettings);
        synchronized (this.databaseId) {
            Preconditions.checkNotNull(firebaseFirestoreSettingsMergeEmulatorSettings, "Provided settings must not be null.");
            if (this.client != null && !this.settings.equals(firebaseFirestoreSettingsMergeEmulatorSettings)) {
                throw new IllegalStateException("FirebaseFirestore has already been started and its settings can no longer be changed. You can only call setFirestoreSettings() before calling any other methods on a FirebaseFirestore object.");
            }
            this.settings = firebaseFirestoreSettingsMergeEmulatorSettings;
        }
    }

    public void useEmulator(String str, int i) {
        if (this.client != null) {
            throw new IllegalStateException("Cannot call useEmulator() after instance has already been initialized.");
        }
        EmulatedServiceSettings emulatedServiceSettings = new EmulatedServiceSettings(str, i);
        this.emulatorSettings = emulatedServiceSettings;
        this.settings = mergeEmulatorSettings(this.settings, emulatedServiceSettings);
    }

    private void ensureClientConfigured() {
        if (this.client != null) {
            return;
        }
        synchronized (this.databaseId) {
            if (this.client != null) {
                return;
            }
            this.client = new FirestoreClient(this.context, new DatabaseInfo(this.databaseId, this.persistenceKey, this.settings.getHost(), this.settings.isSslEnabled()), this.settings, this.authProvider, this.appCheckProvider, this.asyncQueue, this.metadataProvider);
        }
    }

    private FirebaseFirestoreSettings mergeEmulatorSettings(FirebaseFirestoreSettings firebaseFirestoreSettings, EmulatedServiceSettings emulatedServiceSettings) {
        if (emulatedServiceSettings == null) {
            return firebaseFirestoreSettings;
        }
        if (!FirebaseFirestoreSettings.DEFAULT_HOST.equals(firebaseFirestoreSettings.getHost())) {
            Logger.warn(TAG, "Host has been set in FirebaseFirestoreSettings and useEmulator, emulator host will be used.", new Object[0]);
        }
        return new FirebaseFirestoreSettings.Builder(firebaseFirestoreSettings).setHost(emulatedServiceSettings.getHost() + ":" + emulatedServiceSettings.getPort()).setSslEnabled(false).build();
    }

    public Task<Void> setIndexConfiguration(String str) throws JSONException {
        ensureClientConfigured();
        Preconditions.checkState(this.settings.isPersistenceEnabled(), "Cannot enable indexes when persistence is disabled");
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("indexes")) {
                JSONArray jSONArray = jSONObject.getJSONArray("indexes");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String string = jSONObject2.getString("collectionGroup");
                    ArrayList arrayList2 = new ArrayList();
                    JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("fields");
                    for (int i2 = 0; jSONArrayOptJSONArray != null && i2 < jSONArrayOptJSONArray.length(); i2++) {
                        JSONObject jSONObject3 = jSONArrayOptJSONArray.getJSONObject(i2);
                        com.google.firebase.firestore.model.FieldPath fieldPathFromServerFormat = com.google.firebase.firestore.model.FieldPath.fromServerFormat(jSONObject3.getString("fieldPath"));
                        if ("CONTAINS".equals(jSONObject3.optString("arrayConfig"))) {
                            arrayList2.add(FieldIndex.Segment.create(fieldPathFromServerFormat, FieldIndex.Segment.Kind.CONTAINS));
                        } else if ("ASCENDING".equals(jSONObject3.optString("order"))) {
                            arrayList2.add(FieldIndex.Segment.create(fieldPathFromServerFormat, FieldIndex.Segment.Kind.ASCENDING));
                        } else {
                            arrayList2.add(FieldIndex.Segment.create(fieldPathFromServerFormat, FieldIndex.Segment.Kind.DESCENDING));
                        }
                    }
                    arrayList.add(FieldIndex.create(-1, string, arrayList2, FieldIndex.INITIAL_STATE));
                }
            }
            return this.client.configureFieldIndexes(arrayList);
        } catch (JSONException e) {
            throw new IllegalArgumentException("Failed to parse index configuration", e);
        }
    }

    public CollectionReference collection(String str) {
        Preconditions.checkNotNull(str, "Provided collection path must not be null.");
        ensureClientConfigured();
        return new CollectionReference(ResourcePath.fromString(str), this);
    }

    public DocumentReference document(String str) {
        Preconditions.checkNotNull(str, "Provided document path must not be null.");
        ensureClientConfigured();
        return DocumentReference.forPath(ResourcePath.fromString(str), this);
    }

    public Query collectionGroup(String str) {
        Preconditions.checkNotNull(str, "Provided collection ID must not be null.");
        if (str.contains("/")) {
            throw new IllegalArgumentException(String.format("Invalid collectionId '%s'. Collection IDs must not contain '/'.", str));
        }
        ensureClientConfigured();
        return new Query(new com.google.firebase.firestore.core.Query(ResourcePath.EMPTY, str), this);
    }

    private <ResultT> Task<ResultT> runTransaction(TransactionOptions transactionOptions, final Transaction.Function<ResultT> function, final Executor executor) {
        ensureClientConfigured();
        return this.client.transaction(transactionOptions, new Function() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda1
            @Override // com.google.firebase.firestore.util.Function
            public final Object apply(Object obj) {
                return this.f$0.m5233x911046dd(executor, function, (com.google.firebase.firestore.core.Transaction) obj);
            }
        });
    }

    /* renamed from: lambda$runTransaction$1$com-google-firebase-firestore-FirebaseFirestore, reason: not valid java name */
    /* synthetic */ Task m5233x911046dd(Executor executor, final Transaction.Function function, final com.google.firebase.firestore.core.Transaction transaction) {
        return Tasks.call(executor, new Callable() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.f$0.m5232x9186acdc(function, transaction);
            }
        });
    }

    /* renamed from: lambda$runTransaction$0$com-google-firebase-firestore-FirebaseFirestore, reason: not valid java name */
    /* synthetic */ Object m5232x9186acdc(Transaction.Function function, com.google.firebase.firestore.core.Transaction transaction) throws Exception {
        return function.apply(new Transaction(transaction, this));
    }

    public <TResult> Task<TResult> runTransaction(Transaction.Function<TResult> function) {
        return runTransaction(TransactionOptions.DEFAULT, function);
    }

    public <TResult> Task<TResult> runTransaction(TransactionOptions transactionOptions, Transaction.Function<TResult> function) {
        Preconditions.checkNotNull(function, "Provided transaction update function must not be null.");
        return runTransaction(transactionOptions, function, com.google.firebase.firestore.core.Transaction.getDefaultExecutor());
    }

    public WriteBatch batch() {
        ensureClientConfigured();
        return new WriteBatch(this);
    }

    public Task<Void> runBatch(WriteBatch.Function function) {
        WriteBatch writeBatchBatch = batch();
        function.apply(writeBatchBatch);
        return writeBatchBatch.commit();
    }

    public Task<Void> terminate() {
        this.instanceRegistry.remove(getDatabaseId().getDatabaseId());
        ensureClientConfigured();
        return this.client.terminate();
    }

    public Task<Void> waitForPendingWrites() {
        ensureClientConfigured();
        return this.client.waitForPendingWrites();
    }

    public Task<Void> enableNetwork() {
        ensureClientConfigured();
        return this.client.enableNetwork();
    }

    public Task<Void> disableNetwork() {
        ensureClientConfigured();
        return this.client.disableNetwork();
    }

    public static void setLoggingEnabled(boolean z) {
        if (z) {
            Logger.setLogLevel(Logger.Level.DEBUG);
        } else {
            Logger.setLogLevel(Logger.Level.WARN);
        }
    }

    public Task<Void> clearPersistence() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.asyncQueue.enqueueAndForgetEvenAfterShutdown(new Runnable() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() throws FirebaseFirestoreException {
                this.f$0.m5230x422b1e9d(taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    /* renamed from: lambda$clearPersistence$2$com-google-firebase-firestore-FirebaseFirestore, reason: not valid java name */
    /* synthetic */ void m5230x422b1e9d(TaskCompletionSource taskCompletionSource) throws FirebaseFirestoreException {
        try {
            if (this.client != null && !this.client.isTerminated()) {
                throw new FirebaseFirestoreException("Persistence cannot be cleared while the firestore instance is running.", FirebaseFirestoreException.Code.FAILED_PRECONDITION);
            }
            SQLitePersistence.clearPersistence(this.context, this.databaseId, this.persistenceKey);
            taskCompletionSource.setResult(null);
        } catch (FirebaseFirestoreException e) {
            taskCompletionSource.setException(e);
        }
    }

    public ListenerRegistration addSnapshotsInSyncListener(Runnable runnable) {
        return addSnapshotsInSyncListener(Executors.DEFAULT_CALLBACK_EXECUTOR, runnable);
    }

    public ListenerRegistration addSnapshotsInSyncListener(Activity activity, Runnable runnable) {
        return addSnapshotsInSyncListener(Executors.DEFAULT_CALLBACK_EXECUTOR, activity, runnable);
    }

    public ListenerRegistration addSnapshotsInSyncListener(Executor executor, Runnable runnable) {
        return addSnapshotsInSyncListener(executor, null, runnable);
    }

    public LoadBundleTask loadBundle(InputStream inputStream) {
        ensureClientConfigured();
        LoadBundleTask loadBundleTask = new LoadBundleTask();
        this.client.loadBundle(inputStream, loadBundleTask);
        return loadBundleTask;
    }

    public LoadBundleTask loadBundle(byte[] bArr) {
        return loadBundle(new ByteArrayInputStream(bArr));
    }

    public LoadBundleTask loadBundle(ByteBuffer byteBuffer) {
        return loadBundle(new ByteBufferInputStream(byteBuffer));
    }

    public Task<Query> getNamedQuery(String str) {
        ensureClientConfigured();
        return this.client.getNamedQuery(str).continueWith(new Continuation() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda5
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f$0.m5231xe4dfadf7(task);
            }
        });
    }

    /* renamed from: lambda$getNamedQuery$3$com-google-firebase-firestore-FirebaseFirestore, reason: not valid java name */
    /* synthetic */ Query m5231xe4dfadf7(Task task) throws Exception {
        com.google.firebase.firestore.core.Query query = (com.google.firebase.firestore.core.Query) task.getResult();
        if (query != null) {
            return new Query(query, this);
        }
        return null;
    }

    private ListenerRegistration addSnapshotsInSyncListener(Executor executor, Activity activity, final Runnable runnable) {
        ensureClientConfigured();
        final AsyncEventListener asyncEventListener = new AsyncEventListener(executor, new EventListener() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda2
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                FirebaseFirestore.lambda$addSnapshotsInSyncListener$4(runnable, (Void) obj, firebaseFirestoreException);
            }
        });
        this.client.addSnapshotsInSyncListener(asyncEventListener);
        return ActivityScope.bind(activity, new ListenerRegistration() { // from class: com.google.firebase.firestore.FirebaseFirestore$$ExternalSyntheticLambda3
            @Override // com.google.firebase.firestore.ListenerRegistration
            public final void remove() {
                this.f$0.m5229xb65623b0(asyncEventListener);
            }
        });
    }

    static /* synthetic */ void lambda$addSnapshotsInSyncListener$4(Runnable runnable, Void r2, FirebaseFirestoreException firebaseFirestoreException) {
        Assert.hardAssert(firebaseFirestoreException == null, "snapshots-in-sync listeners should never get errors.", new Object[0]);
        runnable.run();
    }

    /* renamed from: lambda$addSnapshotsInSyncListener$5$com-google-firebase-firestore-FirebaseFirestore, reason: not valid java name */
    /* synthetic */ void m5229xb65623b0(AsyncEventListener asyncEventListener) {
        asyncEventListener.mute();
        this.client.removeSnapshotsInSyncListener(asyncEventListener);
    }

    void validateReference(DocumentReference documentReference) {
        Preconditions.checkNotNull(documentReference, "Provided DocumentReference must not be null.");
        if (documentReference.getFirestore() != this) {
            throw new IllegalArgumentException("Provided document reference is from a different Cloud Firestore instance.");
        }
    }

    static void setClientLanguage(String str) {
        FirestoreChannel.setClientLanguage(str);
    }
}
