package com.google.firebase.firestore.remote;

import io.grpc.Metadata;
import io.grpc.Status;

/* loaded from: classes2.dex */
interface IncomingStreamObserver<RespT> {
    void onClose(Status status);

    void onHeaders(Metadata metadata);

    void onNext(RespT respt);

    void onOpen();
}
