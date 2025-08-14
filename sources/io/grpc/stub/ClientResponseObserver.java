package io.grpc.stub;

/* loaded from: classes6.dex */
public interface ClientResponseObserver<ReqT, RespT> extends StreamObserver<RespT> {
    void beforeStart(ClientCallStreamObserver<ReqT> clientCallStreamObserver);
}
