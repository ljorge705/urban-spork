package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideLivenessCaptureSettingsFactory implements Factory<SdkConfiguration.LivenessCapture> {
    private final SdkModule module;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;

    public SdkModule_ProvideLivenessCaptureSettingsFactory(SdkModule sdkModule, Provider<OnfidoRemoteConfig> provider) {
        this.module = sdkModule;
        this.onfidoRemoteConfigProvider = provider;
    }

    public static SdkModule_ProvideLivenessCaptureSettingsFactory create(SdkModule sdkModule, Provider<OnfidoRemoteConfig> provider) {
        return new SdkModule_ProvideLivenessCaptureSettingsFactory(sdkModule, provider);
    }

    public static SdkConfiguration.LivenessCapture provideLivenessCaptureSettings(SdkModule sdkModule, OnfidoRemoteConfig onfidoRemoteConfig) {
        return (SdkConfiguration.LivenessCapture) Preconditions.checkNotNullFromProvides(sdkModule.provideLivenessCaptureSettings(onfidoRemoteConfig));
    }

    @Override // com.onfido.javax.inject.Provider
    public SdkConfiguration.LivenessCapture get() {
        return provideLivenessCaptureSettings(this.module, this.onfidoRemoteConfigProvider.get());
    }
}
