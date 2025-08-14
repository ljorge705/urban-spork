package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import com.onfido.android.sdk.capture.detector.face.FaceDetector;
import com.onfido.android.sdk.capture.detector.face.FaceDetectorEmpty;
import com.onfido.android.sdk.capture.detector.face.FaceDetectorGoogle;
import com.onfido.dagger.Lazy;
import com.onfido.dagger.internal.DoubleCheck;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideFaceDetector$onfido_capture_sdk_core_releaseFactory implements Factory<FaceDetector> {
    private final Provider<Context> contextProvider;
    private final Provider<FaceDetectorEmpty> faceDetectorEmptyProvider;
    private final Provider<FaceDetectorGoogle> faceDetectorGoogleProvider;
    private final SdkModule module;

    public SdkModule_ProvideFaceDetector$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule, Provider<Context> provider, Provider<FaceDetectorGoogle> provider2, Provider<FaceDetectorEmpty> provider3) {
        this.module = sdkModule;
        this.contextProvider = provider;
        this.faceDetectorGoogleProvider = provider2;
        this.faceDetectorEmptyProvider = provider3;
    }

    public static SdkModule_ProvideFaceDetector$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule, Provider<Context> provider, Provider<FaceDetectorGoogle> provider2, Provider<FaceDetectorEmpty> provider3) {
        return new SdkModule_ProvideFaceDetector$onfido_capture_sdk_core_releaseFactory(sdkModule, provider, provider2, provider3);
    }

    public static FaceDetector provideFaceDetector$onfido_capture_sdk_core_release(SdkModule sdkModule, Context context, Lazy<FaceDetectorGoogle> lazy, Lazy<FaceDetectorEmpty> lazy2) {
        return (FaceDetector) Preconditions.checkNotNullFromProvides(sdkModule.provideFaceDetector$onfido_capture_sdk_core_release(context, lazy, lazy2));
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceDetector get() {
        return provideFaceDetector$onfido_capture_sdk_core_release(this.module, this.contextProvider.get(), DoubleCheck.lazy(this.faceDetectorGoogleProvider), DoubleCheck.lazy(this.faceDetectorEmptyProvider));
    }
}
