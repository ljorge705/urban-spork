package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import com.onfido.android.sdk.capture.detector.barcode.BarcodeDetector;
import com.onfido.android.sdk.capture.detector.barcode.BarcodeDetectorEmpty;
import com.onfido.android.sdk.capture.detector.barcode.BarcodeDetectorGoogle;
import com.onfido.dagger.Lazy;
import com.onfido.dagger.internal.DoubleCheck;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideBarcodeDetector$onfido_capture_sdk_core_releaseFactory implements Factory<BarcodeDetector> {
    private final Provider<BarcodeDetectorEmpty> barcodeDetectorEmptyProvider;
    private final Provider<BarcodeDetectorGoogle> barcodeDetectorGoogleProvider;
    private final Provider<Context> contextProvider;
    private final SdkModule module;

    public SdkModule_ProvideBarcodeDetector$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule, Provider<Context> provider, Provider<BarcodeDetectorGoogle> provider2, Provider<BarcodeDetectorEmpty> provider3) {
        this.module = sdkModule;
        this.contextProvider = provider;
        this.barcodeDetectorGoogleProvider = provider2;
        this.barcodeDetectorEmptyProvider = provider3;
    }

    public static SdkModule_ProvideBarcodeDetector$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule, Provider<Context> provider, Provider<BarcodeDetectorGoogle> provider2, Provider<BarcodeDetectorEmpty> provider3) {
        return new SdkModule_ProvideBarcodeDetector$onfido_capture_sdk_core_releaseFactory(sdkModule, provider, provider2, provider3);
    }

    public static BarcodeDetector provideBarcodeDetector$onfido_capture_sdk_core_release(SdkModule sdkModule, Context context, Lazy<BarcodeDetectorGoogle> lazy, Lazy<BarcodeDetectorEmpty> lazy2) {
        return (BarcodeDetector) Preconditions.checkNotNullFromProvides(sdkModule.provideBarcodeDetector$onfido_capture_sdk_core_release(context, lazy, lazy2));
    }

    @Override // com.onfido.javax.inject.Provider
    public BarcodeDetector get() {
        return provideBarcodeDetector$onfido_capture_sdk_core_release(this.module, this.contextProvider.get(), DoubleCheck.lazy(this.barcodeDetectorGoogleProvider), DoubleCheck.lazy(this.barcodeDetectorEmptyProvider));
    }
}
