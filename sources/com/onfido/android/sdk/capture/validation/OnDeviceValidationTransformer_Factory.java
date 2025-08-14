package com.onfido.android.sdk.capture.validation;

import com.onfido.android.sdk.capture.detector.barcode.BarcodeDetector;
import com.onfido.android.sdk.capture.detector.face.FaceOnDocumentDetector;
import com.onfido.android.sdk.capture.detector.mrz.MRZDetector;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.dagger.Lazy;
import com.onfido.dagger.internal.DoubleCheck;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class OnDeviceValidationTransformer_Factory implements Factory<OnDeviceValidationTransformer> {
    private final Provider<BarcodeDetector> barcodeDetectorProvider;
    private final Provider<FaceOnDocumentDetector> faceOnDocumentDetectorProvider;
    private final Provider<MRZDetector> mRZDetectorProvider;
    private final Provider<NativeDetector> nativeDetectorProvider;

    public OnDeviceValidationTransformer_Factory(Provider<NativeDetector> provider, Provider<MRZDetector> provider2, Provider<BarcodeDetector> provider3, Provider<FaceOnDocumentDetector> provider4) {
        this.nativeDetectorProvider = provider;
        this.mRZDetectorProvider = provider2;
        this.barcodeDetectorProvider = provider3;
        this.faceOnDocumentDetectorProvider = provider4;
    }

    public static OnDeviceValidationTransformer_Factory create(Provider<NativeDetector> provider, Provider<MRZDetector> provider2, Provider<BarcodeDetector> provider3, Provider<FaceOnDocumentDetector> provider4) {
        return new OnDeviceValidationTransformer_Factory(provider, provider2, provider3, provider4);
    }

    public static OnDeviceValidationTransformer newInstance(NativeDetector nativeDetector, Lazy<MRZDetector> lazy, Lazy<BarcodeDetector> lazy2, Lazy<FaceOnDocumentDetector> lazy3) {
        return new OnDeviceValidationTransformer(nativeDetector, lazy, lazy2, lazy3);
    }

    @Override // com.onfido.javax.inject.Provider
    public OnDeviceValidationTransformer get() {
        return newInstance(this.nativeDetectorProvider.get(), DoubleCheck.lazy(this.mRZDetectorProvider), DoubleCheck.lazy(this.barcodeDetectorProvider), DoubleCheck.lazy(this.faceOnDocumentDetectorProvider));
    }
}
