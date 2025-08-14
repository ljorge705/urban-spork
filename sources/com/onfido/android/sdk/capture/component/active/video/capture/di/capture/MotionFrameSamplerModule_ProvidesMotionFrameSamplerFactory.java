package com.onfido.android.sdk.capture.component.active.video.capture.di.capture;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionImage;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.preview.PreviewBitmapHelper;
import com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionFrameSamplerModule_ProvidesMotionFrameSamplerFactory implements Factory<FrameSampler<MotionImage>> {
    private final MotionFrameSamplerModule module;
    private final Provider<PreviewBitmapHelper> previewBitmapHelperProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public MotionFrameSamplerModule_ProvidesMotionFrameSamplerFactory(MotionFrameSamplerModule motionFrameSamplerModule, Provider<PreviewBitmapHelper> provider, Provider<SchedulersProvider> provider2) {
        this.module = motionFrameSamplerModule;
        this.previewBitmapHelperProvider = provider;
        this.schedulersProvider = provider2;
    }

    public static MotionFrameSamplerModule_ProvidesMotionFrameSamplerFactory create(MotionFrameSamplerModule motionFrameSamplerModule, Provider<PreviewBitmapHelper> provider, Provider<SchedulersProvider> provider2) {
        return new MotionFrameSamplerModule_ProvidesMotionFrameSamplerFactory(motionFrameSamplerModule, provider, provider2);
    }

    public static FrameSampler<MotionImage> providesMotionFrameSampler(MotionFrameSamplerModule motionFrameSamplerModule, PreviewBitmapHelper previewBitmapHelper, SchedulersProvider schedulersProvider) {
        return (FrameSampler) Preconditions.checkNotNullFromProvides(motionFrameSamplerModule.providesMotionFrameSampler(previewBitmapHelper, schedulersProvider));
    }

    @Override // com.onfido.javax.inject.Provider
    public FrameSampler<MotionImage> get() {
        return providesMotionFrameSampler(this.module, this.previewBitmapHelperProvider.get(), this.schedulersProvider.get());
    }
}
