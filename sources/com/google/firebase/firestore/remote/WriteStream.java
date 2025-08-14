package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.remote.Stream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.v1.FirestoreGrpc;
import com.google.firestore.v1.WriteRequest;
import com.google.firestore.v1.WriteResponse;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class WriteStream extends AbstractStream<WriteRequest, WriteResponse, Callback> {
    public static final ByteString EMPTY_STREAM_TOKEN = ByteString.EMPTY;
    protected boolean handshakeComplete;
    private ByteString lastStreamToken;
    private final RemoteSerializer serializer;

    public interface Callback extends Stream.StreamCallback {
        void onHandshakeComplete();

        void onWriteResponse(SnapshotVersion snapshotVersion, List<MutationResult> list);
    }

    ByteString getLastStreamToken() {
        return this.lastStreamToken;
    }

    boolean isHandshakeComplete() {
        return this.handshakeComplete;
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
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    WriteStream(FirestoreChannel firestoreChannel, AsyncQueue asyncQueue, RemoteSerializer remoteSerializer, Callback callback) {
        super(firestoreChannel, FirestoreGrpc.getWriteMethod(), asyncQueue, AsyncQueue.TimerId.WRITE_STREAM_CONNECTION_BACKOFF, AsyncQueue.TimerId.WRITE_STREAM_IDLE, AsyncQueue.TimerId.HEALTH_CHECK_TIMEOUT, callback);
        this.handshakeComplete = false;
        this.lastStreamToken = EMPTY_STREAM_TOKEN;
        this.serializer = remoteSerializer;
    }

    @Override // com.google.firebase.firestore.remote.AbstractStream, com.google.firebase.firestore.remote.Stream
    public void start() {
        this.handshakeComplete = false;
        super.start();
    }

    @Override // com.google.firebase.firestore.remote.AbstractStream
    protected void tearDown() {
        if (this.handshakeComplete) {
            writeMutations(Collections.emptyList());
        }
    }

    void setLastStreamToken(ByteString byteString) {
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(byteString);
    }

    void writeHandshake() {
        Assert.hardAssert(isOpen(), "Writing handshake requires an opened stream", new Object[0]);
        Assert.hardAssert(!this.handshakeComplete, "Handshake already completed", new Object[0]);
        writeRequest(WriteRequest.newBuilder().setDatabase(this.serializer.databaseName()).build());
    }

    void writeMutations(List<Mutation> list) {
        Assert.hardAssert(isOpen(), "Writing mutations requires an opened stream", new Object[0]);
        Assert.hardAssert(this.handshakeComplete, "Handshake must be complete before writing mutations", new Object[0]);
        WriteRequest.Builder builderNewBuilder = WriteRequest.newBuilder();
        Iterator<Mutation> it = list.iterator();
        while (it.hasNext()) {
            builderNewBuilder.addWrites(this.serializer.encodeMutation(it.next()));
        }
        builderNewBuilder.setStreamToken(this.lastStreamToken);
        writeRequest(builderNewBuilder.build());
    }

    @Override // com.google.firebase.firestore.remote.AbstractStream
    public void onNext(WriteResponse writeResponse) {
        this.lastStreamToken = writeResponse.getStreamToken();
        if (!this.handshakeComplete) {
            this.handshakeComplete = true;
            ((Callback) this.listener).onHandshakeComplete();
            return;
        }
        this.backoff.reset();
        SnapshotVersion snapshotVersionDecodeVersion = this.serializer.decodeVersion(writeResponse.getCommitTime());
        int writeResultsCount = writeResponse.getWriteResultsCount();
        ArrayList arrayList = new ArrayList(writeResultsCount);
        for (int i = 0; i < writeResultsCount; i++) {
            arrayList.add(this.serializer.decodeMutationResult(writeResponse.getWriteResults(i), snapshotVersionDecodeVersion));
        }
        ((Callback) this.listener).onWriteResponse(snapshotVersionDecodeVersion, arrayList);
    }
}
