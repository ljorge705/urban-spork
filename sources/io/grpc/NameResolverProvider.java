package io.grpc;

import io.grpc.NameResolver;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Collections;

/* loaded from: classes6.dex */
public abstract class NameResolverProvider extends NameResolver.Factory {
    protected abstract boolean isAvailable();

    protected abstract int priority();

    protected String getScheme() {
        return getDefaultScheme();
    }

    protected Collection<Class<? extends SocketAddress>> getProducedSocketAddressTypes() {
        return Collections.singleton(InetSocketAddress.class);
    }
}
