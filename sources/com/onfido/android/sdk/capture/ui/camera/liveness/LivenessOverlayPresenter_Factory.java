package com.onfido.android.sdk.capture.ui.camera.liveness;

import com.onfido.android.sdk.capture.detector.face.FaceDetector;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.service.VibratorService;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.ui.camera.liveness.turn.LivenessProgressManager;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LivenessOverlayPresenter_Factory implements Factory<LivenessOverlayPresenter> {
    private final Provider<AnnouncementService> announcementServiceProvider;
    private final Provider<FaceDetector> faceDetectorProvider;
    private final Provider<LivenessProgressManager> livenessProgressManagerProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<VibratorService> vibratorServiceProvider;

    public LivenessOverlayPresenter_Factory(Provider<FaceDetector> provider, Provider<LivenessProgressManager> provider2, Provider<SchedulersProvider> provider3, Provider<VibratorService> provider4, Provider<AnnouncementService> provider5) {
        this.faceDetectorProvider = provider;
        this.livenessProgressManagerProvider = provider2;
        this.schedulersProvider = provider3;
        this.vibratorServiceProvider = provider4;
        this.announcementServiceProvider = provider5;
    }

    public static LivenessOverlayPresenter_Factory create(Provider<FaceDetector> provider, Provider<LivenessProgressManager> provider2, Provider<SchedulersProvider> provider3, Provider<VibratorService> provider4, Provider<AnnouncementService> provider5) {
        return new LivenessOverlayPresenter_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static LivenessOverlayPresenter newInstance(FaceDetector faceDetector, LivenessProgressManager livenessProgressManager, SchedulersProvider schedulersProvider, VibratorService vibratorService, AnnouncementService announcementService) {
        return new LivenessOverlayPresenter(faceDetector, livenessProgressManager, schedulersProvider, vibratorService, announcementService);
    }

    @Override // com.onfido.javax.inject.Provider
    public LivenessOverlayPresenter get() {
        return newInstance(this.faceDetectorProvider.get(), this.livenessProgressManagerProvider.get(), this.schedulersProvider.get(), this.vibratorServiceProvider.get(), this.announcementServiceProvider.get());
    }
}
