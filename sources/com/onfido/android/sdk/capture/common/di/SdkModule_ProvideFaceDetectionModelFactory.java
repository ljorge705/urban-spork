package com.onfido.android.sdk.capture.common.di;

import com.google.mlkit.vision.face.FaceDetector;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideFaceDetectionModelFactory implements Factory<FaceDetector> {
    private final SdkModule module;

    public SdkModule_ProvideFaceDetectionModelFactory(SdkModule sdkModule) {
        this.module = sdkModule;
    }

    public static SdkModule_ProvideFaceDetectionModelFactory create(SdkModule sdkModule) {
        return new SdkModule_ProvideFaceDetectionModelFactory(sdkModule);
    }

    public static FaceDetector provideFaceDetectionModel(SdkModule sdkModule) {
        return (FaceDetector) Preconditions.checkNotNullFromProvides(sdkModule.provideFaceDetectionModel());
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceDetector get() {
        return provideFaceDetectionModel(this.module);
    }
}
