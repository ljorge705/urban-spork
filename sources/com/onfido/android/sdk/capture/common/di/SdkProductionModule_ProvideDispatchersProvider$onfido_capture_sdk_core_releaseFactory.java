package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class SdkProductionModule_ProvideDispatchersProvider$onfido_capture_sdk_core_releaseFactory implements Factory<DispatchersProvider> {

    private static final class InstanceHolder {
        private static final SdkProductionModule_ProvideDispatchersProvider$onfido_capture_sdk_core_releaseFactory INSTANCE = new SdkProductionModule_ProvideDispatchersProvider$onfido_capture_sdk_core_releaseFactory();

        private InstanceHolder() {
        }
    }

    public static SdkProductionModule_ProvideDispatchersProvider$onfido_capture_sdk_core_releaseFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DispatchersProvider provideDispatchersProvider$onfido_capture_sdk_core_release() {
        return (DispatchersProvider) Preconditions.checkNotNullFromProvides(SdkProductionModule.INSTANCE.provideDispatchersProvider$onfido_capture_sdk_core_release());
    }

    @Override // com.onfido.javax.inject.Provider
    public DispatchersProvider get() {
        return provideDispatchersProvider$onfido_capture_sdk_core_release();
    }
}
