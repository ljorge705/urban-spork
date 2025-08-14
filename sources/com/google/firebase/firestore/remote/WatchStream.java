package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.remote.Stream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firestore.v1.FirestoreGrpc;
import com.google.firestore.v1.ListenRequest;
import com.google.firestore.v1.ListenResponse;
import com.google.protobuf.ByteString;
import java.util.Map;

/* loaded from: classes2.dex */
public class WatchStream extends AbstractStream<ListenRequest, ListenResponse, Callback> {
    public static final ByteString EMPTY_RESUME_TOKEN = ByteString.EMPTY;
    private final RemoteSerializer serializer;

    interface Callback extends Stream.StreamCallback {
        void onWatchChange(SnapshotVersion snapshotVersion, WatchChange watchChange);
    }

    @Override // com.google.firebase.firestore.remote.AbstractStream, com.google.firebase.firestore.remote.Stream
    public /* bridge */ /* synthetic */ void inhibitBackoff() {
        super.inhibitBackoff();
    }

    @Override // com.google.firebase.firestore.remote.AbstractStream, com.google.firebase.firestore.remote.Stream
    public /* bridge */ /* synthetic */ boolean isOpen() {
        return super.isOpen();
    }

    @Override // com.google.firebase.firestore.remote.AbstractStream, com.google.firebase.firestore.remote.Stream
    public /* bridge */ /* synthetic */ boolean isStarted() {
        return super.isStarted();
    }

    @Override // com.google.firebase.firestore.remote.AbstractStream, com.google.firebase.firestore.remote.Stream
    public /* bridge */ /* synthetic */ void start() {
        super.start();
    }

    @Override // com.google.firebase.firestore.remote.AbstractStream, com.google.firebase.firestore.remote.Stream
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    WatchStream(FirestoreChannel firestoreChannel, AsyncQueue asyncQueue, RemoteSerializer remoteSerializer, Callback callback) {
        super(firestoreChannel, FirestoreGrpc.getListenMethod(), asyncQueue, AsyncQueue.TimerId.LISTEN_STREAM_CONNECTION_BACKOFF, AsyncQueue.TimerId.LISTEN_STREAM_IDLE, AsyncQueue.TimerId.HEALTH_CHECK_TIMEOUT, callback);
        this.serializer = remoteSerializer;
    }

    public void watchQuery(TargetData targetData) {
        Assert.hardAssert(isOpen(), "Watching queries requires an open stream", new Object[0]);
        ListenRequest.Builder addTarget = ListenRequest.newBuilder().setDatabase(this.serializer.databaseName()).setAddTarget(this.serializer.encodeTarget(targetData));
        Map<String, String> mapEncodeListenRequestLabels = this.serializer.encodeListenRequestLabels(targetData);
        if (mapEncodeListenRequestLabels != null) {
            addTarget.putAllLabels(mapEncodeListenRequestLabels);
        }
        writeRequest(addTarget.build());
    }

    public void unwatchTarget(int i) {
        Assert.hardAssert(isOpen(), "Unwatching targets requires an open stream", new Object[0]);
        writeRequest(ListenRequest.newBuilder().setDatabase(this.serializer.databaseName()).setRemoveTarget(i).build());
    }

    @Override // com.google.firebase.firestore.remote.AbstractStream
    public void onNext(ListenResponse listenResponse) {
        this.backoff.reset();
        WatchChange watchChangeDecodeWatchChange = this.serializer.decodeWatchChange(listenResponse);
        ((Callback) this.listener).onWatchChange(this.serializer.decodeVersionFromListenResponse(listenResponse), watchChangeDecodeWatchChange);
    }
}
