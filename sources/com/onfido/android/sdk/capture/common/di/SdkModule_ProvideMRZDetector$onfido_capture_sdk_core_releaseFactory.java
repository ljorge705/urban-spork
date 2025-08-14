package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import com.onfido.android.sdk.capture.detector.mrz.MRZDetector;
import com.onfido.android.sdk.capture.detector.mrz.MRZDetectorEmpty;
import com.onfido.android.sdk.capture.detector.mrz.MRZDetectorGoogle;
import com.onfido.dagger.Lazy;
import com.onfido.dagger.internal.DoubleCheck;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideMRZDetector$onfido_capture_sdk_core_releaseFactory implements Factory<MRZDetector> {
    private final Provider<Context> contextProvider;
    private final SdkModule module;
    private final Provider<MRZDetectorEmpty> mrzDetectorEmptyProvider;
    private final Provider<MRZDetectorGoogle> mrzDetectorGoogleProvider;

    public SdkModule_ProvideMRZDetector$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule, Provider<Context> provider, Provider<MRZDetectorGoogle> provider2, Provider<MRZDetectorEmpty> provider3) {
        this.module = sdkModule;
        this.contextProvider = provider;
        this.mrzDetectorGoogleProvider = provider2;
        this.mrzDetectorEmptyProvider = provider3;
    }

    public static SdkModule_ProvideMRZDetector$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule, Provider<Context> provider, Provider<MRZDetectorGoogle> provider2, Provider<MRZDetectorEmpty> provider3) {
        return new SdkModule_ProvideMRZDetector$onfido_capture_sdk_core_releaseFactory(sdkModule, provider, provider2, provider3);
    }

    public static MRZDetector provideMRZDetector$onfido_capture_sdk_core_release(SdkModule sdkModule, Context context, Lazy<MRZDetectorGoogle> lazy, Lazy<MRZDetectorEmpty> lazy2) {
        return (MRZDetector) Preconditions.checkNotNullFromProvides(sdkModule.provideMRZDetector$onfido_capture_sdk_core_release(context, lazy, lazy2));
    }

    @Override // com.onfido.javax.inject.Provider
    public MRZDetector get() {
        return provideMRZDetector$onfido_capture_sdk_core_release(this.module, this.contextProvider.get(), DoubleCheck.lazy(this.mrzDetectorGoogleProvider), DoubleCheck.lazy(this.mrzDetectorEmptyProvider));
    }
}
