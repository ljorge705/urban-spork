package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetector;
import com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetectorEmpty;
import com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetectorGoogle;
import com.onfido.dagger.Lazy;
import com.onfido.dagger.internal.DoubleCheck;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideFaceOnDocumentDetector$onfido_capture_sdk_core_releaseFactory implements Factory<FaceOnDocumentDetector> {
    private final Provider<Context> contextProvider;
    private final Provider<FaceOnDocumentDetectorEmpty> faceOnDocumentDetectorEmptyProvider;
    private final Provider<FaceOnDocumentDetectorGoogle> faceOnDocumentDetectorGoogleProvider;
    private final SdkModule module;

    public SdkModule_ProvideFaceOnDocumentDetector$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule, Provider<Context> provider, Provider<FaceOnDocumentDetectorGoogle> provider2, Provider<FaceOnDocumentDetectorEmpty> provider3) {
        this.module = sdkModule;
        this.contextProvider = provider;
        this.faceOnDocumentDetectorGoogleProvider = provider2;
        this.faceOnDocumentDetectorEmptyProvider = provider3;
    }

    public static SdkModule_ProvideFaceOnDocumentDetector$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule, Provider<Context> provider, Provider<FaceOnDocumentDetectorGoogle> provider2, Provider<FaceOnDocumentDetectorEmpty> provider3) {
        return new SdkModule_ProvideFaceOnDocumentDetector$onfido_capture_sdk_core_releaseFactory(sdkModule, provider, provider2, provider3);
    }

    public static FaceOnDocumentDetector provideFaceOnDocumentDetector$onfido_capture_sdk_core_release(SdkModule sdkModule, Context context, Lazy<FaceOnDocumentDetectorGoogle> lazy, Lazy<FaceOnDocumentDetectorEmpty> lazy2) {
        return (FaceOnDocumentDetector) Preconditions.checkNotNullFromProvides(sdkModule.provideFaceOnDocumentDetector$onfido_capture_sdk_core_release(context, lazy, lazy2));
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceOnDocumentDetector get() {
        return provideFaceOnDocumentDetector$onfido_capture_sdk_core_release(this.module, this.contextProvider.get(), DoubleCheck.lazy(this.faceOnDocumentDetectorGoogleProvider), DoubleCheck.lazy(this.faceOnDocumentDetectorEmptyProvider));
    }
}
