package com.onfido.android.sdk.capture.native_detector;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class NativeDetector_Factory implements Factory<NativeDetector> {

    private static final class InstanceHolder {
        private static final NativeDetector_Factory INSTANCE = new NativeDetector_Factory();

        private InstanceHolder() {
        }
    }

    public static NativeDetector_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static NativeDetector newInstance() {
        return new NativeDetector();
    }

    @Override // com.onfido.javax.inject.Provider
    public NativeDetector get() {
        return newInstance();
    }
}
