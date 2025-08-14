package io.invertase.firebase.firestore;

import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.AggregateQuerySnapshot;
import com.google.firebase.firestore.AggregateSource;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import io.invertase.firebase.common.ReactNativeFirebaseEventEmitter;
import io.invertase.firebase.common.ReactNativeFirebaseModule;
import io.sentry.protocol.SentryStackTrace;
import java.util.concurrent.Callable;

/* loaded from: classes6.dex */
public class ReactNativeFirebaseFirestoreCollectionModule extends ReactNativeFirebaseModule {
    private static final String SERVICE_NAME = "FirestoreCollection";
    private static SparseArray<ListenerRegistration> collectionSnapshotListeners = new SparseArray<>();

    ReactNativeFirebaseFirestoreCollectionModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, SERVICE_NAME);
    }

    @Override // io.invertase.firebase.common.ReactNativeFirebaseModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        super.onCatalystInstanceDestroy();
        int size = collectionSnapshotListeners.size();
        for (int i = 0; i < size; i++) {
            collectionSnapshotListeners.get(collectionSnapshotListeners.keyAt(i)).remove();
        }
        collectionSnapshotListeners.clear();
    }

    @ReactMethod
    public void namedQueryOnSnapshot(final String str, String str2, String str3, final ReadableArray readableArray, final ReadableArray readableArray2, final ReadableMap readableMap, final int i, final ReadableMap readableMap2) {
        if (collectionSnapshotListeners.get(i) != null) {
            return;
        }
        UniversalFirebaseFirestoreCommon.getFirestoreForApp(str).getNamedQuery(str2).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda2
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.lambda$namedQueryOnSnapshot$0(str, i, readableArray, readableArray2, readableMap, readableMap2, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$namedQueryOnSnapshot$0(String str, int i, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap, ReadableMap readableMap2, Task task) {
        if (task.isSuccessful()) {
            Query query = (Query) task.getResult();
            if (query == null) {
                sendOnSnapshotError(str, i, new NullPointerException());
                return;
            } else {
                handleQueryOnSnapshot(new ReactNativeFirebaseFirestoreQuery(str, query, readableArray, readableArray2, readableMap), str, i, readableMap2);
                return;
            }
        }
        sendOnSnapshotError(str, i, task.getException());
    }

    @ReactMethod
    public void collectionOnSnapshot(String str, String str2, String str3, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap, int i, ReadableMap readableMap2) {
        if (collectionSnapshotListeners.get(i) != null) {
            return;
        }
        handleQueryOnSnapshot(new ReactNativeFirebaseFirestoreQuery(str, UniversalFirebaseFirestoreCommon.getQueryForFirestore(UniversalFirebaseFirestoreCommon.getFirestoreForApp(str), str2, str3), readableArray, readableArray2, readableMap), str, i, readableMap2);
    }

    @ReactMethod
    public void collectionOffSnapshot(String str, int i) {
        ListenerRegistration listenerRegistration = collectionSnapshotListeners.get(i);
        if (listenerRegistration != null) {
            listenerRegistration.remove();
            collectionSnapshotListeners.remove(i);
            removeEventListeningExecutor(Integer.toString(i));
        }
    }

    @ReactMethod
    public void namedQueryGet(final String str, String str2, String str3, final ReadableArray readableArray, final ReadableArray readableArray2, final ReadableMap readableMap, final ReadableMap readableMap2, final Promise promise) {
        UniversalFirebaseFirestoreCommon.getFirestoreForApp(str).getNamedQuery(str2).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.lambda$namedQueryGet$1(promise, str, readableArray, readableArray2, readableMap, readableMap2, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$namedQueryGet$1(Promise promise, String str, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap, ReadableMap readableMap2, Task task) {
        if (task.isSuccessful()) {
            Query query = (Query) task.getResult();
            if (query == null) {
                ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, new NullPointerException());
                return;
            } else {
                handleQueryGet(new ReactNativeFirebaseFirestoreQuery(str, query, readableArray, readableArray2, readableMap), getSource(readableMap2), promise);
                return;
            }
        }
        ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
    }

    @ReactMethod
    public void collectionCount(String str, String str2, String str3, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap, final Promise promise) {
        new ReactNativeFirebaseFirestoreQuery(str, UniversalFirebaseFirestoreCommon.getQueryForFirestore(UniversalFirebaseFirestoreCommon.getFirestoreForApp(str), str2, str3), readableArray, readableArray2, readableMap).query.count().get(AggregateSource.SERVER).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda6
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseFirestoreCollectionModule.lambda$collectionCount$2(promise, task);
            }
        });
    }

    static /* synthetic */ void lambda$collectionCount$2(Promise promise, Task task) {
        if (task.isSuccessful()) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putDouble("count", Long.valueOf(((AggregateQuerySnapshot) task.getResult()).getCount()).doubleValue());
            promise.resolve(writableMapCreateMap);
            return;
        }
        ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
    }

    @ReactMethod
    public void collectionGet(String str, String str2, String str3, ReadableArray readableArray, ReadableArray readableArray2, ReadableMap readableMap, ReadableMap readableMap2, Promise promise) {
        handleQueryGet(new ReactNativeFirebaseFirestoreQuery(str, UniversalFirebaseFirestoreCommon.getQueryForFirestore(UniversalFirebaseFirestoreCommon.getFirestoreForApp(str), str2, str3), readableArray, readableArray2, readableMap), getSource(readableMap2), promise);
    }

    private void handleQueryOnSnapshot(ReactNativeFirebaseFirestoreQuery reactNativeFirebaseFirestoreQuery, final String str, final int i, ReadableMap readableMap) {
        final MetadataChanges metadataChanges;
        if (readableMap != null && readableMap.hasKey("includeMetadataChanges") && readableMap.getBoolean("includeMetadataChanges")) {
            metadataChanges = MetadataChanges.INCLUDE;
        } else {
            metadataChanges = MetadataChanges.EXCLUDE;
        }
        collectionSnapshotListeners.put(i, reactNativeFirebaseFirestoreQuery.query.addSnapshotListener(metadataChanges, new EventListener() { // from class: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda5
            @Override // com.google.firebase.firestore.EventListener
            public final void onEvent(Object obj, FirebaseFirestoreException firebaseFirestoreException) {
                this.f$0.lambda$handleQueryOnSnapshot$3(i, str, metadataChanges, (QuerySnapshot) obj, firebaseFirestoreException);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$handleQueryOnSnapshot$3(int i, String str, MetadataChanges metadataChanges, QuerySnapshot querySnapshot, FirebaseFirestoreException firebaseFirestoreException) {
        if (firebaseFirestoreException != null) {
            ListenerRegistration listenerRegistration = collectionSnapshotListeners.get(i);
            if (listenerRegistration != null) {
                listenerRegistration.remove();
                collectionSnapshotListeners.remove(i);
            }
            sendOnSnapshotError(str, i, firebaseFirestoreException);
            return;
        }
        sendOnSnapshotEvent(str, i, querySnapshot, metadataChanges);
    }

    private void handleQueryGet(ReactNativeFirebaseFirestoreQuery reactNativeFirebaseFirestoreQuery, Source source, final Promise promise) {
        reactNativeFirebaseFirestoreQuery.get(getExecutor(), source).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                ReactNativeFirebaseFirestoreCollectionModule.lambda$handleQueryGet$4(promise, task);
            }
        });
    }

    static /* synthetic */ void lambda$handleQueryGet$4(Promise promise, Task task) {
        if (task.isSuccessful()) {
            promise.resolve(task.getResult());
        } else {
            ReactNativeFirebaseFirestoreCommon.rejectPromiseFirestoreException(promise, task.getException());
        }
    }

    private void sendOnSnapshotEvent(final String str, final int i, final QuerySnapshot querySnapshot, final MetadataChanges metadataChanges) {
        Tasks.call(getTransactionalExecutor(Integer.toString(i)), new Callable() { // from class: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ReactNativeFirebaseFirestoreSerialize.snapshotToWritableMap(str, "onSnapshot", querySnapshot, metadataChanges);
            }
        }).addOnCompleteListener(new OnCompleteListener() { // from class: io.invertase.firebase.firestore.ReactNativeFirebaseFirestoreCollectionModule$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.f$0.lambda$sendOnSnapshotEvent$6(str, i, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$sendOnSnapshotEvent$6(String str, int i, Task task) {
        if (task.isSuccessful()) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putMap(SentryStackTrace.JsonKeys.SNAPSHOT, (ReadableMap) task.getResult());
            ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseFirestoreEvent("firestore_collection_sync_event", writableMapCreateMap, str, i));
            return;
        }
        sendOnSnapshotError(str, i, task.getException());
    }

    private void sendOnSnapshotError(String str, int i, Exception exc) {
        WritableMap writableMapCreateMap = Arguments.createMap();
        WritableMap writableMapCreateMap2 = Arguments.createMap();
        if (exc instanceof FirebaseFirestoreException) {
            UniversalFirebaseFirestoreException universalFirebaseFirestoreException = new UniversalFirebaseFirestoreException((FirebaseFirestoreException) exc, exc.getCause());
            writableMapCreateMap2.putString("code", universalFirebaseFirestoreException.getCode());
            writableMapCreateMap2.putString("message", universalFirebaseFirestoreException.getMessage());
        } else {
            writableMapCreateMap2.putString("code", "unknown");
            writableMapCreateMap2.putString("message", "An unknown error occurred");
        }
        writableMapCreateMap.putMap("error", writableMapCreateMap2);
        ReactNativeFirebaseEventEmitter.getSharedInstance().sendEvent(new ReactNativeFirebaseFirestoreEvent("firestore_collection_sync_event", writableMapCreateMap, str, i));
    }

    private Source getSource(ReadableMap readableMap) {
        if (readableMap != null && readableMap.hasKey("source")) {
            String string = readableMap.getString("source");
            if ("server".equals(string)) {
                return Source.SERVER;
            }
            if ("cache".equals(string)) {
                return Source.CACHE;
            }
            return Source.DEFAULT;
        }
        return Source.DEFAULT;
    }
}
