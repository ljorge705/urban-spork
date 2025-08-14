package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.internal.config.MutableOnfidoRemoteConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class SdkProductionModule_ProvideMutableOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory implements Factory<MutableOnfidoRemoteConfig> {

    private static final class InstanceHolder {
        private static final SdkProductionModule_ProvideMutableOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory INSTANCE = new SdkProductionModule_ProvideMutableOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory();

        private InstanceHolder() {
        }
    }

    public static SdkProductionModule_ProvideMutableOnfidoRemoteConfig$onfido_capture_sdk_core_releaseFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static MutableOnfidoRemoteConfig provideMutableOnfidoRemoteConfig$onfido_capture_sdk_core_release() {
        return (MutableOnfidoRemoteConfig) Preconditions.checkNotNullFromProvides(SdkProductionModule.INSTANCE.provideMutableOnfidoRemoteConfig$onfido_capture_sdk_core_release());
    }

    @Override // com.onfido.javax.inject.Provider
    public MutableOnfidoRemoteConfig get() {
        return provideMutableOnfidoRemoteConfig$onfido_capture_sdk_core_release();
    }
}
