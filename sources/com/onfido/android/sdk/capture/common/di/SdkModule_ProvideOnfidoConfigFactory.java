package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideOnfidoConfigFactory implements Factory<OnfidoConfig> {
    private final SdkModule module;

    public SdkModule_ProvideOnfidoConfigFactory(SdkModule sdkModule) {
        this.module = sdkModule;
    }

    public static SdkModule_ProvideOnfidoConfigFactory create(SdkModule sdkModule) {
        return new SdkModule_ProvideOnfidoConfigFactory(sdkModule);
    }

    public static OnfidoConfig provideOnfidoConfig(SdkModule sdkModule) {
        return (OnfidoConfig) Preconditions.checkNotNullFromProvides(sdkModule.getOnfidoConfig());
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoConfig get() {
        return provideOnfidoConfig(this.module);
    }
}
