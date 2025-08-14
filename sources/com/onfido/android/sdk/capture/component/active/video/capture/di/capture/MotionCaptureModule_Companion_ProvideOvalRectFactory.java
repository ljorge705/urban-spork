package com.onfido.android.sdk.capture.component.active.video.capture.di.capture;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.util.OvalRect;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class MotionCaptureModule_Companion_ProvideOvalRectFactory implements Factory<OvalRect> {

    private static final class InstanceHolder {
        private static final MotionCaptureModule_Companion_ProvideOvalRectFactory INSTANCE = new MotionCaptureModule_Companion_ProvideOvalRectFactory();

        private InstanceHolder() {
        }
    }

    public static MotionCaptureModule_Companion_ProvideOvalRectFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static OvalRect provideOvalRect() {
        return (OvalRect) Preconditions.checkNotNullFromProvides(MotionCaptureModule.INSTANCE.provideOvalRect());
    }

    @Override // com.onfido.javax.inject.Provider
    public OvalRect get() {
        return provideOvalRect();
    }
}
