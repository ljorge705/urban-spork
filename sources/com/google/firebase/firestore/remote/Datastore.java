package com.google.firebase.firestore.remote;

import android.content.Context;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.firestore.AggregateField;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.remote.FirestoreChannel;
import com.google.firebase.firestore.remote.WatchStream;
import com.google.firebase.firestore.remote.WriteStream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Util;
import com.google.firestore.v1.BatchGetDocumentsRequest;
import com.google.firestore.v1.BatchGetDocumentsResponse;
import com.google.firestore.v1.CommitRequest;
import com.google.firestore.v1.CommitResponse;
import com.google.firestore.v1.FirestoreGrpc;
import com.google.firestore.v1.RunAggregationQueryRequest;
import com.google.firestore.v1.RunAggregationQueryResponse;
import com.google.firestore.v1.StructuredAggregationQuery;
import com.google.firestore.v1.Target;
import com.google.firestore.v1.Value;
import io.grpc.Status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.net.ssl.SSLHandshakeException;

/* loaded from: classes2.dex */
public class Datastore {
    static final String SSL_DEPENDENCY_ERROR_MESSAGE = "The Cloud Firestore client failed to establish a secure connection. This is likely a problem with your app, rather than with Cloud Firestore itself. See https://bit.ly/2XFpdma for instructions on how to enable TLS on Android 4.x devices.";
    static final Set<String> WHITE_LISTED_HEADERS = new HashSet(Arrays.asList("date", "x-google-backends", "x-google-netmon-label", "x-google-service", "x-google-gfe-request-trace"));
    private final FirestoreChannel channel;
    private final DatabaseInfo databaseInfo;
    private final RemoteSerializer serializer;
    private final AsyncQueue workerQueue;

    DatabaseInfo getDatabaseInfo() {
        return this.databaseInfo;
    }

    AsyncQueue getWorkerQueue() {
        return this.workerQueue;
    }

    public Datastore(DatabaseInfo databaseInfo, AsyncQueue asyncQueue, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, Context context, GrpcMetadataProvider grpcMetadataProvider) {
        this.databaseInfo = databaseInfo;
        this.workerQueue = asyncQueue;
        this.serializer = new RemoteSerializer(databaseInfo.getDatabaseId());
        this.channel = initializeChannel(databaseInfo, asyncQueue, credentialsProvider, credentialsProvider2, context, grpcMetadataProvider);
    }

    FirestoreChannel initializeChannel(DatabaseInfo databaseInfo, AsyncQueue asyncQueue, CredentialsProvider<User> credentialsProvider, CredentialsProvider<String> credentialsProvider2, Context context, GrpcMetadataProvider grpcMetadataProvider) {
        return new FirestoreChannel(asyncQueue, context, credentialsProvider, credentialsProvider2, databaseInfo, grpcMetadataProvider);
    }

    void shutdown() {
        this.channel.shutdown();
    }

    WatchStream createWatchStream(WatchStream.Callback callback) {
        return new WatchStream(this.channel, this.workerQueue, this.serializer, callback);
    }

    WriteStream createWriteStream(WriteStream.Callback callback) {
        return new WriteStream(this.channel, this.workerQueue, this.serializer, callback);
    }

    public Task<List<MutationResult>> commit(List<Mutation> list) {
        CommitRequest.Builder builderNewBuilder = CommitRequest.newBuilder();
        builderNewBuilder.setDatabase(this.serializer.databaseName());
        Iterator<Mutation> it = list.iterator();
        while (it.hasNext()) {
            builderNewBuilder.addWrites(this.serializer.encodeMutation(it.next()));
        }
        return this.channel.runRpc(FirestoreGrpc.getCommitMethod(), builderNewBuilder.build()).continueWith(this.workerQueue.getExecutor(), new Continuation() { // from class: com.google.firebase.firestore.remote.Datastore$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f$0.m5338lambda$commit$0$comgooglefirebasefirestoreremoteDatastore(task);
            }
        });
    }

    /* renamed from: lambda$commit$0$com-google-firebase-firestore-remote-Datastore, reason: not valid java name */
    /* synthetic */ List m5338lambda$commit$0$comgooglefirebasefirestoreremoteDatastore(Task task) throws Exception {
        if (!task.isSuccessful()) {
            if ((task.getException() instanceof FirebaseFirestoreException) && ((FirebaseFirestoreException) task.getException()).getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
                this.channel.invalidateToken();
            }
            throw task.getException();
        }
        CommitResponse commitResponse = (CommitResponse) task.getResult();
        SnapshotVersion snapshotVersionDecodeVersion = this.serializer.decodeVersion(commitResponse.getCommitTime());
        int writeResultsCount = commitResponse.getWriteResultsCount();
        ArrayList arrayList = new ArrayList(writeResultsCount);
        for (int i = 0; i < writeResultsCount; i++) {
            arrayList.add(this.serializer.decodeMutationResult(commitResponse.getWriteResults(i), snapshotVersionDecodeVersion));
        }
        return arrayList;
    }

    public Task<List<MutableDocument>> lookup(final List<DocumentKey> list) {
        BatchGetDocumentsRequest.Builder builderNewBuilder = BatchGetDocumentsRequest.newBuilder();
        builderNewBuilder.setDatabase(this.serializer.databaseName());
        Iterator<DocumentKey> it = list.iterator();
        while (it.hasNext()) {
            builderNewBuilder.addDocuments(this.serializer.encodeKey(it.next()));
        }
        final ArrayList arrayList = new ArrayList();
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.channel.runStreamingResponseRpc(FirestoreGrpc.getBatchGetDocumentsMethod(), builderNewBuilder.build(), new FirestoreChannel.StreamingListener<BatchGetDocumentsResponse>() { // from class: com.google.firebase.firestore.remote.Datastore.1
            @Override // com.google.firebase.firestore.remote.FirestoreChannel.StreamingListener
            public void onMessage(BatchGetDocumentsResponse batchGetDocumentsResponse) {
                arrayList.add(batchGetDocumentsResponse);
                if (arrayList.size() == list.size()) {
                    HashMap map = new HashMap();
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        MutableDocument mutableDocumentDecodeMaybeDocument = Datastore.this.serializer.decodeMaybeDocument((BatchGetDocumentsResponse) it2.next());
                        map.put(mutableDocumentDecodeMaybeDocument.getKey(), mutableDocumentDecodeMaybeDocument);
                    }
                    ArrayList arrayList2 = new ArrayList();
                    Iterator it3 = list.iterator();
                    while (it3.hasNext()) {
                        arrayList2.add((MutableDocument) map.get((DocumentKey) it3.next()));
                    }
                    taskCompletionSource.trySetResult(arrayList2);
                }
            }

            @Override // com.google.firebase.firestore.remote.FirestoreChannel.StreamingListener
            public void onClose(Status status) {
                if (status.isOk()) {
                    taskCompletionSource.trySetResult(Collections.emptyList());
                    return;
                }
                FirebaseFirestoreException firebaseFirestoreExceptionExceptionFromStatus = Util.exceptionFromStatus(status);
                if (firebaseFirestoreExceptionExceptionFromStatus.getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
                    Datastore.this.channel.invalidateToken();
                }
                taskCompletionSource.trySetException(firebaseFirestoreExceptionExceptionFromStatus);
            }
        });
        return taskCompletionSource.getTask();
    }

    public Task<Map<String, Value>> runAggregateQuery(Query query, List<AggregateField> list) {
        Target.QueryTarget queryTargetEncodeQueryTarget = this.serializer.encodeQueryTarget(query.toTarget());
        final HashMap<String, String> map = new HashMap<>();
        StructuredAggregationQuery structuredAggregationQueryEncodeStructuredAggregationQuery = this.serializer.encodeStructuredAggregationQuery(queryTargetEncodeQueryTarget, list, map);
        RunAggregationQueryRequest.Builder builderNewBuilder = RunAggregationQueryRequest.newBuilder();
        builderNewBuilder.setParent(queryTargetEncodeQueryTarget.getParent());
        builderNewBuilder.setStructuredAggregationQuery(structuredAggregationQueryEncodeStructuredAggregationQuery);
        return this.channel.runRpc(FirestoreGrpc.getRunAggregationQueryMethod(), builderNewBuilder.build()).continueWith(this.workerQueue.getExecutor(), new Continuation() { // from class: com.google.firebase.firestore.remote.Datastore$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.f$0.m5339xb2492930(map, task);
            }
        });
    }

    /* renamed from: lambda$runAggregateQuery$1$com-google-firebase-firestore-remote-Datastore, reason: not valid java name */
    /* synthetic */ Map m5339xb2492930(HashMap map, Task task) throws Exception {
        if (!task.isSuccessful()) {
            if ((task.getException() instanceof FirebaseFirestoreException) && ((FirebaseFirestoreException) task.getException()).getCode() == FirebaseFirestoreException.Code.UNAUTHENTICATED) {
                this.channel.invalidateToken();
            }
            throw task.getException();
        }
        HashMap map2 = new HashMap();
        for (Map.Entry<String, Value> entry : ((RunAggregationQueryResponse) task.getResult()).getResult().getAggregateFieldsMap().entrySet()) {
            Assert.hardAssert(map.containsKey(entry.getKey()), "%s not present in aliasMap", entry.getKey());
            map2.put((String) map.get(entry.getKey()), entry.getValue());
        }
        return map2;
    }

    public static boolean isPermanentError(Status status) {
        return isPermanentError(FirebaseFirestoreException.Code.fromValue(status.getCode().value()));
    }

    /* renamed from: com.google.firebase.firestore.remote.Datastore$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code;

        static {
            int[] iArr = new int[FirebaseFirestoreException.Code.values().length];
            $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code = iArr;
            try {
                iArr[FirebaseFirestoreException.Code.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.CANCELLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.DEADLINE_EXCEEDED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.INTERNAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNAVAILABLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNAUTHENTICATED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.INVALID_ARGUMENT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.NOT_FOUND.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.ALREADY_EXISTS.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.PERMISSION_DENIED.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.FAILED_PRECONDITION.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.ABORTED.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.OUT_OF_RANGE.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.UNIMPLEMENTED.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[FirebaseFirestoreException.Code.DATA_LOSS.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    public static boolean isPermanentError(FirebaseFirestoreException.Code code) {
        switch (AnonymousClass2.$SwitchMap$com$google$firebase$firestore$FirebaseFirestoreException$Code[code.ordinal()]) {
            case 1:
                throw new IllegalArgumentException("Treated status OK as error");
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return false;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
                return true;
            default:
                throw new IllegalArgumentException("Unknown gRPC status code: " + code);
        }
    }

    public static boolean isMissingSslCiphers(Status status) {
        status.getCode();
        Throwable cause = status.getCause();
        if (!(cause instanceof SSLHandshakeException)) {
            return false;
        }
        cause.getMessage().contains("no ciphers available");
        return false;
    }

    public static boolean isPermanentWriteError(Status status) {
        return isPermanentError(status) && !status.getCode().equals(Status.Code.ABORTED);
    }
}
