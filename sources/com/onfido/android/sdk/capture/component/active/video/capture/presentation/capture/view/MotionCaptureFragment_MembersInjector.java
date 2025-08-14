package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view;

import com.onfido.android.sdk.capture.common.result.HapticFeedback;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraControllerFactory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvcResultMapperTestImpl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.MotionFaceDetectorFactory;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.AvcTimer;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionCaptureFragment_MembersInjector implements MembersInjector<MotionCaptureFragment> {
    private final Provider<OnfidoAnalytics> analyticsProvider;
    private final Provider<AnnouncementService> announcementServiceProvider;
    private final Provider<MotionCameraControllerFactory> cameraControllerFactoryProvider;
    private final Provider<AvcTimer> delayStartRecordingTimerProvider;
    private final Provider<AvcTimer> delayTimerProvider;
    private final Provider<FaceDetectorAvcResultMapperTestImpl<Object>> faceDetectorAvcResultMapperTestImplProvider;
    private final Provider<HapticFeedback> hapticFeedbackProvider;
    private final Provider<HeadTurnGuidanceViewModel> headTurnGuidanceViewModelProvider;
    private final Provider<MotionFaceDetectorFactory> motionFaceDetectorFactoryProvider;
    private final Provider<MotionCaptureVariantOptions> motionOptionsProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<MotionCaptureViewModelImpl.Factory> viewModelFactoryProvider;

    public MotionCaptureFragment_MembersInjector(Provider<MotionCaptureVariantOptions> provider, Provider<MotionCameraControllerFactory> provider2, Provider<MotionFaceDetectorFactory> provider3, Provider<HeadTurnGuidanceViewModel> provider4, Provider<SchedulersProvider> provider5, Provider<OnfidoAnalytics> provider6, Provider<HapticFeedback> provider7, Provider<AvcTimer> provider8, Provider<AvcTimer> provider9, Provider<AnnouncementService> provider10, Provider<MotionCaptureViewModelImpl.Factory> provider11, Provider<FaceDetectorAvcResultMapperTestImpl<Object>> provider12) {
        this.motionOptionsProvider = provider;
        this.cameraControllerFactoryProvider = provider2;
        this.motionFaceDetectorFactoryProvider = provider3;
        this.headTurnGuidanceViewModelProvider = provider4;
        this.schedulersProvider = provider5;
        this.analyticsProvider = provider6;
        this.hapticFeedbackProvider = provider7;
        this.delayStartRecordingTimerProvider = provider8;
        this.delayTimerProvider = provider9;
        this.announcementServiceProvider = provider10;
        this.viewModelFactoryProvider = provider11;
        this.faceDetectorAvcResultMapperTestImplProvider = provider12;
    }

    public static MembersInjector<MotionCaptureFragment> create(Provider<MotionCaptureVariantOptions> provider, Provider<MotionCameraControllerFactory> provider2, Provider<MotionFaceDetectorFactory> provider3, Provider<HeadTurnGuidanceViewModel> provider4, Provider<SchedulersProvider> provider5, Provider<OnfidoAnalytics> provider6, Provider<HapticFeedback> provider7, Provider<AvcTimer> provider8, Provider<AvcTimer> provider9, Provider<AnnouncementService> provider10, Provider<MotionCaptureViewModelImpl.Factory> provider11, Provider<FaceDetectorAvcResultMapperTestImpl<Object>> provider12) {
        return new MotionCaptureFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12);
    }

    public static void injectAnalytics(MotionCaptureFragment motionCaptureFragment, OnfidoAnalytics onfidoAnalytics) {
        motionCaptureFragment.analytics = onfidoAnalytics;
    }

    public static void injectAnnouncementService(MotionCaptureFragment motionCaptureFragment, AnnouncementService announcementService) {
        motionCaptureFragment.announcementService = announcementService;
    }

    public static void injectCameraControllerFactory(MotionCaptureFragment motionCaptureFragment, MotionCameraControllerFactory motionCameraControllerFactory) {
        motionCaptureFragment.cameraControllerFactory = motionCameraControllerFactory;
    }

    public static void injectDelayStartRecordingTimer(MotionCaptureFragment motionCaptureFragment, AvcTimer avcTimer) {
        motionCaptureFragment.delayStartRecordingTimer = avcTimer;
    }

    public static void injectDelayTimer(MotionCaptureFragment motionCaptureFragment, AvcTimer avcTimer) {
        motionCaptureFragment.delayTimer = avcTimer;
    }

    public static void injectFaceDetectorAvcResultMapperTestImpl(MotionCaptureFragment motionCaptureFragment, FaceDetectorAvcResultMapperTestImpl<Object> faceDetectorAvcResultMapperTestImpl) {
        motionCaptureFragment.faceDetectorAvcResultMapperTestImpl = faceDetectorAvcResultMapperTestImpl;
    }

    public static void injectHapticFeedback(MotionCaptureFragment motionCaptureFragment, HapticFeedback hapticFeedback) {
        motionCaptureFragment.hapticFeedback = hapticFeedback;
    }

    public static void injectHeadTurnGuidanceViewModel(MotionCaptureFragment motionCaptureFragment, HeadTurnGuidanceViewModel headTurnGuidanceViewModel) {
        motionCaptureFragment.headTurnGuidanceViewModel = headTurnGuidanceViewModel;
    }

    public static void injectMotionFaceDetectorFactory(MotionCaptureFragment motionCaptureFragment, MotionFaceDetectorFactory motionFaceDetectorFactory) {
        motionCaptureFragment.motionFaceDetectorFactory = motionFaceDetectorFactory;
    }

    public static void injectMotionOptions(MotionCaptureFragment motionCaptureFragment, MotionCaptureVariantOptions motionCaptureVariantOptions) {
        motionCaptureFragment.motionOptions = motionCaptureVariantOptions;
    }

    public static void injectSchedulersProvider(MotionCaptureFragment motionCaptureFragment, SchedulersProvider schedulersProvider) {
        motionCaptureFragment.schedulersProvider = schedulersProvider;
    }

    public static void injectViewModelFactory(MotionCaptureFragment motionCaptureFragment, MotionCaptureViewModelImpl.Factory factory) {
        motionCaptureFragment.viewModelFactory = factory;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(MotionCaptureFragment motionCaptureFragment) {
        injectMotionOptions(motionCaptureFragment, this.motionOptionsProvider.get());
        injectCameraControllerFactory(motionCaptureFragment, this.cameraControllerFactoryProvider.get());
        injectMotionFaceDetectorFactory(motionCaptureFragment, this.motionFaceDetectorFactoryProvider.get());
        injectHeadTurnGuidanceViewModel(motionCaptureFragment, this.headTurnGuidanceViewModelProvider.get());
        injectSchedulersProvider(motionCaptureFragment, this.schedulersProvider.get());
        injectAnalytics(motionCaptureFragment, this.analyticsProvider.get());
        injectHapticFeedback(motionCaptureFragment, this.hapticFeedbackProvider.get());
        injectDelayStartRecordingTimer(motionCaptureFragment, this.delayStartRecordingTimerProvider.get());
        injectDelayTimer(motionCaptureFragment, this.delayTimerProvider.get());
        injectAnnouncementService(motionCaptureFragment, this.announcementServiceProvider.get());
        injectViewModelFactory(motionCaptureFragment, this.viewModelFactoryProvider.get());
        injectFaceDetectorAvcResultMapperTestImpl(motionCaptureFragment, this.faceDetectorAvcResultMapperTestImplProvider.get());
    }
}
