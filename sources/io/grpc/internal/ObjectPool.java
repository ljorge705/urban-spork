package io.grpc.internal;

/* loaded from: classes6.dex */
public interface ObjectPool<T> {
    T getObject();

    T returnObject(Object obj);
}
