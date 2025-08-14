package io.grpc.internal;

/* loaded from: classes6.dex */
public interface ServerListener {
    void serverShutdown();

    ServerTransportListener transportCreated(ServerTransport serverTransport);
}
