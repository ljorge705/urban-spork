package com.onfido.android.sdk.capture.detector.face;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class FaceDetectorGoogle_Factory implements Factory<FaceDetectorGoogle> {

    private static final class InstanceHolder {
        private static final FaceDetectorGoogle_Factory INSTANCE = new FaceDetectorGoogle_Factory();

        private InstanceHolder() {
        }
    }

    public static FaceDetectorGoogle_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static FaceDetectorGoogle newInstance() {
        return new FaceDetectorGoogle();
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceDetectorGoogle get() {
        return newInstance();
    }
}
