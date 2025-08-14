package io.grpc.internal;

import io.grpc.Status;

/* loaded from: classes6.dex */
public interface ServerStreamListener extends StreamListener {
    void closed(Status status);

    void halfClosed();
}
