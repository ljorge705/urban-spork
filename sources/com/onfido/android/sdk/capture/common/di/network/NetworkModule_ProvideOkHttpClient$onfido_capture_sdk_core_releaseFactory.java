package com.onfido.android.sdk.capture.common.di.network;

import com.onfido.android.sdk.capture.internal.network.InternalOnfidoNetworkInterceptorsProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;
import okhttp3.OkHttpClient;

/* loaded from: classes2.dex */
public final class NetworkModule_ProvideOkHttpClient$onfido_capture_sdk_core_releaseFactory implements Factory<OkHttpClient> {
    private final Provider<InternalOnfidoNetworkInterceptorsProvider> providerProvider;

    public NetworkModule_ProvideOkHttpClient$onfido_capture_sdk_core_releaseFactory(Provider<InternalOnfidoNetworkInterceptorsProvider> provider) {
        this.providerProvider = provider;
    }

    public static NetworkModule_ProvideOkHttpClient$onfido_capture_sdk_core_releaseFactory create(Provider<InternalOnfidoNetworkInterceptorsProvider> provider) {
        return new NetworkModule_ProvideOkHttpClient$onfido_capture_sdk_core_releaseFactory(provider);
    }

    public static OkHttpClient provideOkHttpClient$onfido_capture_sdk_core_release(InternalOnfidoNetworkInterceptorsProvider internalOnfidoNetworkInterceptorsProvider) {
        return (OkHttpClient) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOkHttpClient$onfido_capture_sdk_core_release(internalOnfidoNetworkInterceptorsProvider));
    }

    @Override // com.onfido.javax.inject.Provider
    public OkHttpClient get() {
        return provideOkHttpClient$onfido_capture_sdk_core_release(this.providerProvider.get());
    }
}
