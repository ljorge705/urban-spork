package com.onfido.android.sdk.capture.detector.face;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class FaceDetectorEmpty_Factory implements Factory<FaceDetectorEmpty> {

    private static final class InstanceHolder {
        private static final FaceDetectorEmpty_Factory INSTANCE = new FaceDetectorEmpty_Factory();

        private InstanceHolder() {
        }
    }

    public static FaceDetectorEmpty_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static FaceDetectorEmpty newInstance() {
        return new FaceDetectorEmpty();
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceDetectorEmpty get() {
        return newInstance();
    }
}
