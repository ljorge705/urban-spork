package com.onfido.android.sdk.capture.ui.camera.liveness.capture;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.detector.face.FaceDetector;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.CaptureTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessTracker;
import com.onfido.android.sdk.capture.internal.usecase.FaceProcessingUseCase;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LivenessCaptureViewModel_Factory implements Factory<LivenessCaptureViewModel> {
    private final Provider<CaptureTracker> captureTrackerProvider;
    private final Provider<DispatchersProvider> dispatchersProvider;
    private final Provider<FaceDetector> faceDetectorProvider;
    private final Provider<FaceProcessingUseCase> faceProcessingUseCaseProvider;
    private final Provider<IdentityInteractor> identityInteractorProvider;
    private final Provider<LivenessInteractor> livenessInteractorProvider;
    private final Provider<LivenessTracker> livenessTrackerProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<TimeProvider> timeProvider;

    public LivenessCaptureViewModel_Factory(Provider<FaceProcessingUseCase> provider, Provider<FaceDetector> provider2, Provider<IdentityInteractor> provider3, Provider<LivenessInteractor> provider4, Provider<LivenessTracker> provider5, Provider<CaptureTracker> provider6, Provider<TimeProvider> provider7, Provider<SchedulersProvider> provider8, Provider<OnfidoConfig> provider9, Provider<DispatchersProvider> provider10) {
        this.faceProcessingUseCaseProvider = provider;
        this.faceDetectorProvider = provider2;
        this.identityInteractorProvider = provider3;
        this.livenessInteractorProvider = provider4;
        this.livenessTrackerProvider = provider5;
        this.captureTrackerProvider = provider6;
        this.timeProvider = provider7;
        this.schedulersProvider = provider8;
        this.onfidoConfigProvider = provider9;
        this.dispatchersProvider = provider10;
    }

    public static LivenessCaptureViewModel_Factory create(Provider<FaceProcessingUseCase> provider, Provider<FaceDetector> provider2, Provider<IdentityInteractor> provider3, Provider<LivenessInteractor> provider4, Provider<LivenessTracker> provider5, Provider<CaptureTracker> provider6, Provider<TimeProvider> provider7, Provider<SchedulersProvider> provider8, Provider<OnfidoConfig> provider9, Provider<DispatchersProvider> provider10) {
        return new LivenessCaptureViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static LivenessCaptureViewModel newInstance(FaceProcessingUseCase faceProcessingUseCase, FaceDetector faceDetector, IdentityInteractor identityInteractor, LivenessInteractor livenessInteractor, LivenessTracker livenessTracker, CaptureTracker captureTracker, TimeProvider timeProvider, SchedulersProvider schedulersProvider, OnfidoConfig onfidoConfig, DispatchersProvider dispatchersProvider) {
        return new LivenessCaptureViewModel(faceProcessingUseCase, faceDetector, identityInteractor, livenessInteractor, livenessTracker, captureTracker, timeProvider, schedulersProvider, onfidoConfig, dispatchersProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public LivenessCaptureViewModel get() {
        return newInstance(this.faceProcessingUseCaseProvider.get(), this.faceDetectorProvider.get(), this.identityInteractorProvider.get(), this.livenessInteractorProvider.get(), this.livenessTrackerProvider.get(), this.captureTrackerProvider.get(), this.timeProvider.get(), this.schedulersProvider.get(), this.onfidoConfigProvider.get(), this.dispatchersProvider.get());
    }
}
