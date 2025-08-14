package com.onfido.android.sdk.capture.component.active.video.capture.di.capture;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapperFactory;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionCaptureModule_Companion_ProvideRecorderWrapperFactory implements Factory<RecorderWrapper> {
    private final Provider<RecorderWrapperFactory> recorderWrapperFactoryProvider;

    public MotionCaptureModule_Companion_ProvideRecorderWrapperFactory(Provider<RecorderWrapperFactory> provider) {
        this.recorderWrapperFactoryProvider = provider;
    }

    public static MotionCaptureModule_Companion_ProvideRecorderWrapperFactory create(Provider<RecorderWrapperFactory> provider) {
        return new MotionCaptureModule_Companion_ProvideRecorderWrapperFactory(provider);
    }

    public static RecorderWrapper provideRecorderWrapper(RecorderWrapperFactory recorderWrapperFactory) {
        return (RecorderWrapper) Preconditions.checkNotNullFromProvides(MotionCaptureModule.INSTANCE.provideRecorderWrapper(recorderWrapperFactory));
    }

    @Override // com.onfido.javax.inject.Provider
    public RecorderWrapper get() {
        return provideRecorderWrapper(this.recorderWrapperFactoryProvider.get());
    }
}
