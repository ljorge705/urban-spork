package com.onfido.android.sdk.capture.component.active.video.capture.di.capture;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionImage;
import com.onfido.android.sdk.capture.internal.camera.camerax.ImageAnalyzer;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class MotionFrameSamplerModule_ProvidesMotionImageAnalyzerFactory implements Factory<ImageAnalyzer<MotionImage>> {
    private final MotionFrameSamplerModule module;

    public MotionFrameSamplerModule_ProvidesMotionImageAnalyzerFactory(MotionFrameSamplerModule motionFrameSamplerModule) {
        this.module = motionFrameSamplerModule;
    }

    public static MotionFrameSamplerModule_ProvidesMotionImageAnalyzerFactory create(MotionFrameSamplerModule motionFrameSamplerModule) {
        return new MotionFrameSamplerModule_ProvidesMotionImageAnalyzerFactory(motionFrameSamplerModule);
    }

    public static ImageAnalyzer<MotionImage> providesMotionImageAnalyzer(MotionFrameSamplerModule motionFrameSamplerModule) {
        return (ImageAnalyzer) Preconditions.checkNotNullFromProvides(motionFrameSamplerModule.providesMotionImageAnalyzer());
    }

    @Override // com.onfido.javax.inject.Provider
    public ImageAnalyzer<MotionImage> get() {
        return providesMotionImageAnalyzer(this.module);
    }
}
