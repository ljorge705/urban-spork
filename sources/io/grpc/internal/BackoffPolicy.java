package io.grpc.internal;

/* loaded from: classes6.dex */
public interface BackoffPolicy {

    public interface Provider {
        BackoffPolicy get();
    }

    long nextBackoffNanos();
}
