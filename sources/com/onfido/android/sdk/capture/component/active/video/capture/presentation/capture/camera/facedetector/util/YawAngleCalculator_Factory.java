package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.util;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class YawAngleCalculator_Factory implements Factory<YawAngleCalculator> {

    private static final class InstanceHolder {
        private static final YawAngleCalculator_Factory INSTANCE = new YawAngleCalculator_Factory();

        private InstanceHolder() {
        }
    }

    public static YawAngleCalculator_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static YawAngleCalculator newInstance() {
        return new YawAngleCalculator();
    }

    @Override // com.onfido.javax.inject.Provider
    public YawAngleCalculator get() {
        return newInstance();
    }
}
