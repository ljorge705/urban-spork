package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetector;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorEmpty;
import com.onfido.android.sdk.capture.detector.rectangle.RectangleDetectorGoogle;
import com.onfido.dagger.Lazy;
import com.onfido.dagger.internal.DoubleCheck;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideRectangleDetector$onfido_capture_sdk_core_releaseFactory implements Factory<RectangleDetector> {
    private final Provider<Context> contextProvider;
    private final SdkModule module;
    private final Provider<RectangleDetectorEmpty> rectangleDetectorEmptyProvider;
    private final Provider<RectangleDetectorGoogle> rectangleDetectorGoogleProvider;

    public SdkModule_ProvideRectangleDetector$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule, Provider<Context> provider, Provider<RectangleDetectorGoogle> provider2, Provider<RectangleDetectorEmpty> provider3) {
        this.module = sdkModule;
        this.contextProvider = provider;
        this.rectangleDetectorGoogleProvider = provider2;
        this.rectangleDetectorEmptyProvider = provider3;
    }

    public static SdkModule_ProvideRectangleDetector$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule, Provider<Context> provider, Provider<RectangleDetectorGoogle> provider2, Provider<RectangleDetectorEmpty> provider3) {
        return new SdkModule_ProvideRectangleDetector$onfido_capture_sdk_core_releaseFactory(sdkModule, provider, provider2, provider3);
    }

    public static RectangleDetector provideRectangleDetector$onfido_capture_sdk_core_release(SdkModule sdkModule, Context context, Lazy<RectangleDetectorGoogle> lazy, Lazy<RectangleDetectorEmpty> lazy2) {
        return (RectangleDetector) Preconditions.checkNotNullFromProvides(sdkModule.provideRectangleDetector$onfido_capture_sdk_core_release(context, lazy, lazy2));
    }

    @Override // com.onfido.javax.inject.Provider
    public RectangleDetector get() {
        return provideRectangleDetector$onfido_capture_sdk_core_release(this.module, this.contextProvider.get(), DoubleCheck.lazy(this.rectangleDetectorGoogleProvider), DoubleCheck.lazy(this.rectangleDetectorEmptyProvider));
    }
}
