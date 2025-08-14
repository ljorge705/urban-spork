package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalyzer;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideImageAnalyzer$onfido_capture_sdk_core_releaseFactory implements Factory<ImageAnalyzer<OnfidoImage>> {
    private final SdkModule module;

    public SdkModule_ProvideImageAnalyzer$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule) {
        this.module = sdkModule;
    }

    public static SdkModule_ProvideImageAnalyzer$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule) {
        return new SdkModule_ProvideImageAnalyzer$onfido_capture_sdk_core_releaseFactory(sdkModule);
    }

    public static ImageAnalyzer<OnfidoImage> provideImageAnalyzer$onfido_capture_sdk_core_release(SdkModule sdkModule) {
        return (ImageAnalyzer) Preconditions.checkNotNullFromProvides(sdkModule.provideImageAnalyzer$onfido_capture_sdk_core_release());
    }

    @Override // com.onfido.javax.inject.Provider
    public ImageAnalyzer<OnfidoImage> get() {
        return provideImageAnalyzer$onfido_capture_sdk_core_release(this.module);
    }
}
