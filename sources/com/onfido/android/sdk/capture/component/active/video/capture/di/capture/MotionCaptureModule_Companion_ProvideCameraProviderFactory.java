package com.onfido.android.sdk.capture.component.active.video.capture.di.capture;

import android.content.Context;
import androidx.camera.lifecycle.ProcessCameraProvider;
import com.google.common.util.concurrent.ListenableFuture;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionCaptureModule_Companion_ProvideCameraProviderFactory implements Factory<ListenableFuture<ProcessCameraProvider>> {
    private final Provider<Context> applicationContextProvider;

    public MotionCaptureModule_Companion_ProvideCameraProviderFactory(Provider<Context> provider) {
        this.applicationContextProvider = provider;
    }

    public static MotionCaptureModule_Companion_ProvideCameraProviderFactory create(Provider<Context> provider) {
        return new MotionCaptureModule_Companion_ProvideCameraProviderFactory(provider);
    }

    public static ListenableFuture<ProcessCameraProvider> provideCameraProvider(Context context) {
        return (ListenableFuture) Preconditions.checkNotNullFromProvides(MotionCaptureModule.INSTANCE.provideCameraProvider(context));
    }

    @Override // com.onfido.javax.inject.Provider
    public ListenableFuture<ProcessCameraProvider> get() {
        return provideCameraProvider(this.applicationContextProvider.get());
    }
}
