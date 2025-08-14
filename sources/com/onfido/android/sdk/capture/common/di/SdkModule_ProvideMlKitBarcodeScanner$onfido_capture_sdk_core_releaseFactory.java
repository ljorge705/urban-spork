package com.onfido.android.sdk.capture.common.di;

import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideMlKitBarcodeScanner$onfido_capture_sdk_core_releaseFactory implements Factory<BarcodeScanner> {
    private final SdkModule module;

    public SdkModule_ProvideMlKitBarcodeScanner$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule) {
        this.module = sdkModule;
    }

    public static SdkModule_ProvideMlKitBarcodeScanner$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule) {
        return new SdkModule_ProvideMlKitBarcodeScanner$onfido_capture_sdk_core_releaseFactory(sdkModule);
    }

    public static BarcodeScanner provideMlKitBarcodeScanner$onfido_capture_sdk_core_release(SdkModule sdkModule) {
        return (BarcodeScanner) Preconditions.checkNotNullFromProvides(sdkModule.provideMlKitBarcodeScanner$onfido_capture_sdk_core_release());
    }

    @Override // com.onfido.javax.inject.Provider
    public BarcodeScanner get() {
        return provideMlKitBarcodeScanner$onfido_capture_sdk_core_release(this.module);
    }
}
