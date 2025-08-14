package com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase;

import com.onfido.android.sdk.capture.component.active.video.capture.domain.repository.ActiveVideoCaptureRepository;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class UploadActiveVideoCaptureUseCase_Factory implements Factory<UploadActiveVideoCaptureUseCase> {
    private final Provider<ActiveVideoCaptureRepository> repositoryProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public UploadActiveVideoCaptureUseCase_Factory(Provider<ActiveVideoCaptureRepository> provider, Provider<SchedulersProvider> provider2) {
        this.repositoryProvider = provider;
        this.schedulersProvider = provider2;
    }

    public static UploadActiveVideoCaptureUseCase_Factory create(Provider<ActiveVideoCaptureRepository> provider, Provider<SchedulersProvider> provider2) {
        return new UploadActiveVideoCaptureUseCase_Factory(provider, provider2);
    }

    public static UploadActiveVideoCaptureUseCase newInstance(ActiveVideoCaptureRepository activeVideoCaptureRepository, SchedulersProvider schedulersProvider) {
        return new UploadActiveVideoCaptureUseCase(activeVideoCaptureRepository, schedulersProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public UploadActiveVideoCaptureUseCase get() {
        return newInstance(this.repositoryProvider.get(), this.schedulersProvider.get());
    }
}
