package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory implements Factory<OnfidoRemoteConfig> {

    private static final class InstanceHolder {
        private static final SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory INSTANCE = new SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory();

        private InstanceHolder() {
        }
    }

    public static SdkProductionModule_ProvideOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static OnfidoRemoteConfig provideOnfidoRemoteConfig$onfido_capture_sdk_core_release() {
        return (OnfidoRemoteConfig) Preconditions.checkNotNullFromProvides(SdkProductionModule.INSTANCE.provideOnfidoRemoteConfig$onfido_capture_sdk_core_release());
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoRemoteConfig get() {
        return provideOnfidoRemoteConfig$onfido_capture_sdk_core_release();
    }
}
