package io.grpc;

import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes6.dex */
public interface InternalInstrumented<T> extends InternalWithLogId {
    ListenableFuture<T> getStats();
}
