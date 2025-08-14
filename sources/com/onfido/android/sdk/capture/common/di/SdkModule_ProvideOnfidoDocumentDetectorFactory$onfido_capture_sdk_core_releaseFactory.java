package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoDocumentDetectorFactory;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideOnfidoDocumentDetectorFactory$onfido_capture_sdk_core_releaseFactory implements Factory<OnfidoDocumentDetectorFactory> {
    private final SdkModule module;

    public SdkModule_ProvideOnfidoDocumentDetectorFactory$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule) {
        this.module = sdkModule;
    }

    public static SdkModule_ProvideOnfidoDocumentDetectorFactory$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule) {
        return new SdkModule_ProvideOnfidoDocumentDetectorFactory$onfido_capture_sdk_core_releaseFactory(sdkModule);
    }

    public static OnfidoDocumentDetectorFactory provideOnfidoDocumentDetectorFactory$onfido_capture_sdk_core_release(SdkModule sdkModule) {
        return (OnfidoDocumentDetectorFactory) Preconditions.checkNotNullFromProvides(sdkModule.provideOnfidoDocumentDetectorFactory$onfido_capture_sdk_core_release());
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoDocumentDetectorFactory get() {
        return provideOnfidoDocumentDetectorFactory$onfido_capture_sdk_core_release(this.module);
    }
}
