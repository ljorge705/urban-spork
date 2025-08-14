package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideFrameSampler$onfido_capture_sdk_core_releaseFactory implements Factory<FrameSampler<OnfidoImage>> {
    private final SdkModule module;
    private final Provider<SchedulersProvider> schedulersProvider;

    public SdkModule_ProvideFrameSampler$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule, Provider<SchedulersProvider> provider) {
        this.module = sdkModule;
        this.schedulersProvider = provider;
    }

    public static SdkModule_ProvideFrameSampler$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule, Provider<SchedulersProvider> provider) {
        return new SdkModule_ProvideFrameSampler$onfido_capture_sdk_core_releaseFactory(sdkModule, provider);
    }

    public static FrameSampler<OnfidoImage> provideFrameSampler$onfido_capture_sdk_core_release(SdkModule sdkModule, SchedulersProvider schedulersProvider) {
        return (FrameSampler) Preconditions.checkNotNullFromProvides(sdkModule.provideFrameSampler$onfido_capture_sdk_core_release(schedulersProvider));
    }

    @Override // com.onfido.javax.inject.Provider
    public FrameSampler<OnfidoImage> get() {
        return provideFrameSampler$onfido_capture_sdk_core_release(this.module, this.schedulersProvider.get());
    }
}
