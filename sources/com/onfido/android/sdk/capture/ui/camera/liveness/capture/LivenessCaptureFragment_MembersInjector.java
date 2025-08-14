package com.onfido.android.sdk.capture.ui.camera.liveness.capture;

import com.onfido.android.sdk.capture.internal.camera.factory.CameraFactory;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.service.VibratorService;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LivenessCaptureFragment_MembersInjector implements MembersInjector<LivenessCaptureFragment> {
    private final Provider<AnnouncementService> announcementServiceProvider;
    private final Provider<CameraFactory> cameraFactoryProvider;
    private final Provider<ImageUtils> imageUtilsProvider;
    private final Provider<VibratorService> vibratorServiceProvider;
    private final Provider<LivenessCaptureViewModel> viewModelProvider;

    public LivenessCaptureFragment_MembersInjector(Provider<LivenessCaptureViewModel> provider, Provider<CameraFactory> provider2, Provider<AnnouncementService> provider3, Provider<VibratorService> provider4, Provider<ImageUtils> provider5) {
        this.viewModelProvider = provider;
        this.cameraFactoryProvider = provider2;
        this.announcementServiceProvider = provider3;
        this.vibratorServiceProvider = provider4;
        this.imageUtilsProvider = provider5;
    }

    public static MembersInjector<LivenessCaptureFragment> create(Provider<LivenessCaptureViewModel> provider, Provider<CameraFactory> provider2, Provider<AnnouncementService> provider3, Provider<VibratorService> provider4, Provider<ImageUtils> provider5) {
        return new LivenessCaptureFragment_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectAnnouncementService(LivenessCaptureFragment livenessCaptureFragment, AnnouncementService announcementService) {
        livenessCaptureFragment.announcementService = announcementService;
    }

    public static void injectCameraFactory(LivenessCaptureFragment livenessCaptureFragment, CameraFactory cameraFactory) {
        livenessCaptureFragment.cameraFactory = cameraFactory;
    }

    public static void injectImageUtils(LivenessCaptureFragment livenessCaptureFragment, ImageUtils imageUtils) {
        livenessCaptureFragment.imageUtils = imageUtils;
    }

    public static void injectVibratorService(LivenessCaptureFragment livenessCaptureFragment, VibratorService vibratorService) {
        livenessCaptureFragment.vibratorService = vibratorService;
    }

    public static void injectViewModelProvider(LivenessCaptureFragment livenessCaptureFragment, Provider<LivenessCaptureViewModel> provider) {
        livenessCaptureFragment.viewModelProvider = provider;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(LivenessCaptureFragment livenessCaptureFragment) {
        injectViewModelProvider(livenessCaptureFragment, this.viewModelProvider);
        injectCameraFactory(livenessCaptureFragment, this.cameraFactoryProvider.get());
        injectAnnouncementService(livenessCaptureFragment, this.announcementServiceProvider.get());
        injectVibratorService(livenessCaptureFragment, this.vibratorServiceProvider.get());
        injectImageUtils(livenessCaptureFragment, this.imageUtilsProvider.get());
    }
}
