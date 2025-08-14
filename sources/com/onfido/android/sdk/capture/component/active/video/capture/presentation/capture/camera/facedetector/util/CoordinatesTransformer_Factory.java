package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.util;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class CoordinatesTransformer_Factory implements Factory<CoordinatesTransformer> {

    private static final class InstanceHolder {
        private static final CoordinatesTransformer_Factory INSTANCE = new CoordinatesTransformer_Factory();

        private InstanceHolder() {
        }
    }

    public static CoordinatesTransformer_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static CoordinatesTransformer newInstance() {
        return new CoordinatesTransformer();
    }

    @Override // com.onfido.javax.inject.Provider
    public CoordinatesTransformer get() {
        return newInstance();
    }
}
