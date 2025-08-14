package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder;

import android.content.Context;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.IsPersistentSurfaceSupportedUseCase;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util.SurfaceSizeProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class RecorderWrapperFactory_Factory implements Factory<RecorderWrapperFactory> {
    private final Provider<Context> contextProvider;
    private final Provider<IsPersistentSurfaceSupportedUseCase> isPersistentSurfaceSupportedUseCaseProvider;
    private final Provider<SurfaceSizeProvider> surfaceSizeProvider;

    public RecorderWrapperFactory_Factory(Provider<Context> provider, Provider<IsPersistentSurfaceSupportedUseCase> provider2, Provider<SurfaceSizeProvider> provider3) {
        this.contextProvider = provider;
        this.isPersistentSurfaceSupportedUseCaseProvider = provider2;
        this.surfaceSizeProvider = provider3;
    }

    public static RecorderWrapperFactory_Factory create(Provider<Context> provider, Provider<IsPersistentSurfaceSupportedUseCase> provider2, Provider<SurfaceSizeProvider> provider3) {
        return new RecorderWrapperFactory_Factory(provider, provider2, provider3);
    }

    public static RecorderWrapperFactory newInstance(Context context, IsPersistentSurfaceSupportedUseCase isPersistentSurfaceSupportedUseCase, SurfaceSizeProvider surfaceSizeProvider) {
        return new RecorderWrapperFactory(context, isPersistentSurfaceSupportedUseCase, surfaceSizeProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public RecorderWrapperFactory get() {
        return newInstance(this.contextProvider.get(), this.isPersistentSurfaceSupportedUseCaseProvider.get(), this.surfaceSizeProvider.get());
    }
}
