package com.onfido.android.sdk.capture.common.di.network;

import com.onfido.android.sdk.capture.internal.network.InternalOnfidoNetworkInterceptorsProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class NetworkModule_ProvideInternalOnfidoNetworkInterceptorsProvider$onfido_capture_sdk_core_releaseFactory implements Factory<InternalOnfidoNetworkInterceptorsProvider> {

    private static final class InstanceHolder {
        private static final NetworkModule_ProvideInternalOnfidoNetworkInterceptorsProvider$onfido_capture_sdk_core_releaseFactory INSTANCE = new NetworkModule_ProvideInternalOnfidoNetworkInterceptorsProvider$onfido_capture_sdk_core_releaseFactory();

        private InstanceHolder() {
        }
    }

    public static NetworkModule_ProvideInternalOnfidoNetworkInterceptorsProvider$onfido_capture_sdk_core_releaseFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static InternalOnfidoNetworkInterceptorsProvider provideInternalOnfidoNetworkInterceptorsProvider$onfido_capture_sdk_core_release() {
        return (InternalOnfidoNetworkInterceptorsProvider) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideInternalOnfidoNetworkInterceptorsProvider$onfido_capture_sdk_core_release());
    }

    @Override // com.onfido.javax.inject.Provider
    public InternalOnfidoNetworkInterceptorsProvider get() {
        return provideInternalOnfidoNetworkInterceptorsProvider$onfido_capture_sdk_core_release();
    }
}
