package com.onfido.android.sdk.capture.component.active.video.capture.di.capture;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.api.client.data.SdkConfiguration;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionCaptureModule_Companion_ProvideMotionVideoSettingsFactory implements Factory<SdkConfiguration.MotionCapture.MotionVideoSettings> {
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;

    public MotionCaptureModule_Companion_ProvideMotionVideoSettingsFactory(Provider<OnfidoRemoteConfig> provider) {
        this.onfidoRemoteConfigProvider = provider;
    }

    public static MotionCaptureModule_Companion_ProvideMotionVideoSettingsFactory create(Provider<OnfidoRemoteConfig> provider) {
        return new MotionCaptureModule_Companion_ProvideMotionVideoSettingsFactory(provider);
    }

    public static SdkConfiguration.MotionCapture.MotionVideoSettings provideMotionVideoSettings(OnfidoRemoteConfig onfidoRemoteConfig) {
        return (SdkConfiguration.MotionCapture.MotionVideoSettings) Preconditions.checkNotNullFromProvides(MotionCaptureModule.INSTANCE.provideMotionVideoSettings(onfidoRemoteConfig));
    }

    @Override // com.onfido.javax.inject.Provider
    public SdkConfiguration.MotionCapture.MotionVideoSettings get() {
        return provideMotionVideoSettings(this.onfidoRemoteConfigProvider.get());
    }
}
