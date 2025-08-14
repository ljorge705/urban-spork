package io.grpc.stub;

/* loaded from: classes6.dex */
public interface StreamObserver<V> {
    void onCompleted();

    void onError(Throwable th);

    void onNext(V v);
}
