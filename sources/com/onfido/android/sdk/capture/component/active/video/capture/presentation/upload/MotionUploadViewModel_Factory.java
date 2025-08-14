package com.onfido.android.sdk.capture.component.active.video.capture.presentation.upload;

import com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase.UploadActiveVideoCaptureUseCase;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionUploadViewModel_Factory implements Factory<MotionUploadViewModel> {
    private final Provider<OnfidoAnalytics> analyticsProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<UploadActiveVideoCaptureUseCase> uploadActiveVideoCaptureProvider;
    private final Provider<WaitingScreenTracker> waitingScreenTrackerProvider;

    public MotionUploadViewModel_Factory(Provider<UploadActiveVideoCaptureUseCase> provider, Provider<SchedulersProvider> provider2, Provider<OnfidoAnalytics> provider3, Provider<WaitingScreenTracker> provider4) {
        this.uploadActiveVideoCaptureProvider = provider;
        this.schedulersProvider = provider2;
        this.analyticsProvider = provider3;
        this.waitingScreenTrackerProvider = provider4;
    }

    public static MotionUploadViewModel_Factory create(Provider<UploadActiveVideoCaptureUseCase> provider, Provider<SchedulersProvider> provider2, Provider<OnfidoAnalytics> provider3, Provider<WaitingScreenTracker> provider4) {
        return new MotionUploadViewModel_Factory(provider, provider2, provider3, provider4);
    }

    public static MotionUploadViewModel newInstance(UploadActiveVideoCaptureUseCase uploadActiveVideoCaptureUseCase, SchedulersProvider schedulersProvider, OnfidoAnalytics onfidoAnalytics, WaitingScreenTracker waitingScreenTracker) {
        return new MotionUploadViewModel(uploadActiveVideoCaptureUseCase, schedulersProvider, onfidoAnalytics, waitingScreenTracker);
    }

    @Override // com.onfido.javax.inject.Provider
    public MotionUploadViewModel get() {
        return newInstance(this.uploadActiveVideoCaptureProvider.get(), this.schedulersProvider.get(), this.analyticsProvider.get(), this.waitingScreenTrackerProvider.get());
    }
}
