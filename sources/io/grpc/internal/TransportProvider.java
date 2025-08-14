package io.grpc.internal;

import javax.annotation.Nullable;

/* loaded from: classes6.dex */
interface TransportProvider {
    @Nullable
    ClientTransport obtainActiveTransport();
}
