package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class FaceDetectorAvcResultMapperTestImpl_Factory<T> implements Factory<FaceDetectorAvcResultMapperTestImpl<T>> {

    private static final class InstanceHolder {
        private static final FaceDetectorAvcResultMapperTestImpl_Factory INSTANCE = new FaceDetectorAvcResultMapperTestImpl_Factory();

        private InstanceHolder() {
        }
    }

    public static <T> FaceDetectorAvcResultMapperTestImpl_Factory<T> create() {
        return InstanceHolder.INSTANCE;
    }

    public static <T> FaceDetectorAvcResultMapperTestImpl<T> newInstance() {
        return new FaceDetectorAvcResultMapperTestImpl<>();
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceDetectorAvcResultMapperTestImpl<T> get() {
        return newInstance();
    }
}
