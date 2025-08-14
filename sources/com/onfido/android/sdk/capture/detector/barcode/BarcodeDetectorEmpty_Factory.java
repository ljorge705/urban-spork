package com.onfido.android.sdk.capture.detector.barcode;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class BarcodeDetectorEmpty_Factory implements Factory<BarcodeDetectorEmpty> {

    private static final class InstanceHolder {
        private static final BarcodeDetectorEmpty_Factory INSTANCE = new BarcodeDetectorEmpty_Factory();

        private InstanceHolder() {
        }
    }

    public static BarcodeDetectorEmpty_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static BarcodeDetectorEmpty newInstance() {
        return new BarcodeDetectorEmpty();
    }

    @Override // com.onfido.javax.inject.Provider
    public BarcodeDetectorEmpty get() {
        return newInstance();
    }
}
