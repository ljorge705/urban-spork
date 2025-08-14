package com.onfido.android.sdk.capture.detector.barcode;

import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class BarcodeDetectorGoogle_Factory implements Factory<BarcodeDetectorGoogle> {
    private final Provider<BarcodeScanner> barcodeDetectorProvider;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;

    public BarcodeDetectorGoogle_Factory(Provider<OnfidoRemoteConfig> provider, Provider<BarcodeScanner> provider2) {
        this.remoteConfigProvider = provider;
        this.barcodeDetectorProvider = provider2;
    }

    public static BarcodeDetectorGoogle_Factory create(Provider<OnfidoRemoteConfig> provider, Provider<BarcodeScanner> provider2) {
        return new BarcodeDetectorGoogle_Factory(provider, provider2);
    }

    public static BarcodeDetectorGoogle newInstance(OnfidoRemoteConfig onfidoRemoteConfig, Provider<BarcodeScanner> provider) {
        return new BarcodeDetectorGoogle(onfidoRemoteConfig, provider);
    }

    @Override // com.onfido.javax.inject.Provider
    public BarcodeDetectorGoogle get() {
        return newInstance(this.remoteConfigProvider.get(), this.barcodeDetectorProvider);
    }
}
