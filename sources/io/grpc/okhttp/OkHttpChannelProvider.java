package io.grpc.okhttp;

import io.grpc.ChannelCredentials;
import io.grpc.InternalServiceProviders;
import io.grpc.ManagedChannelProvider;
import io.grpc.okhttp.OkHttpChannelBuilder;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;

/* loaded from: classes6.dex */
public final class OkHttpChannelProvider extends ManagedChannelProvider {
    @Override // io.grpc.ManagedChannelProvider
    public boolean isAvailable() {
        return true;
    }

    @Override // io.grpc.ManagedChannelProvider
    public int priority() {
        return InternalServiceProviders.isAndroid(getClass().getClassLoader()) ? 8 : 3;
    }

    @Override // io.grpc.ManagedChannelProvider
    public OkHttpChannelBuilder builderForAddress(String str, int i) {
        return OkHttpChannelBuilder.forAddress(str, i);
    }

    @Override // io.grpc.ManagedChannelProvider
    public OkHttpChannelBuilder builderForTarget(String str) {
        return OkHttpChannelBuilder.forTarget(str);
    }

    @Override // io.grpc.ManagedChannelProvider
    public ManagedChannelProvider.NewChannelBuilderResult newChannelBuilder(String str, ChannelCredentials channelCredentials) throws NoSuchAlgorithmException, IOException, KeyManagementException {
        OkHttpChannelBuilder.SslSocketFactoryResult sslSocketFactoryResultSslSocketFactoryFrom = OkHttpChannelBuilder.sslSocketFactoryFrom(channelCredentials);
        if (sslSocketFactoryResultSslSocketFactoryFrom.error != null) {
            return ManagedChannelProvider.NewChannelBuilderResult.error(sslSocketFactoryResultSslSocketFactoryFrom.error);
        }
        return ManagedChannelProvider.NewChannelBuilderResult.channelBuilder(new OkHttpChannelBuilder(str, channelCredentials, sslSocketFactoryResultSslSocketFactoryFrom.callCredentials, sslSocketFactoryResultSslSocketFactoryFrom.factory));
    }

    @Override // io.grpc.ManagedChannelProvider
    protected Collection<Class<? extends SocketAddress>> getSupportedSocketAddressTypes() {
        return Collections.singleton(InetSocketAddress.class);
    }
}
