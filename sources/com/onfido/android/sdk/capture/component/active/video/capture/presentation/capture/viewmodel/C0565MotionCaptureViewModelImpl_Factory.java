package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.mapper.ToFaceAlignmentFeedbackAccessibilityMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.mapper.ToFaceAlignmentFeedbackMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.mapper.ToFaceAlignmentMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.AudioFocusHelper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.AvcTimer;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.MicAvailabilityHelper;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.javax.inject.Provider;

/* renamed from: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0565MotionCaptureViewModelImpl_Factory {
    private final Provider<OnfidoAnalytics> analyticsProvider;
    private final Provider<AnnouncementService> announcementServiceProvider;
    private final Provider<AudioFocusHelper> audioFocusHelperProvider;
    private final Provider<AvcTimer> faceAlignmentTimerProvider;
    private final Provider<HeadTurnGuidanceViewModel> headTurnGuidanceViewModelProvider;
    private final Provider<MicAvailabilityHelper> micAvailabilityHelperProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<ToFaceAlignmentFeedbackAccessibilityMapper> toFaceAlignmentFeedbackAccessibilityMapperProvider;
    private final Provider<ToFaceAlignmentFeedbackMapper> toFaceAlignmentFeedbackMapperProvider;
    private final Provider<ToFaceAlignmentMapper> toFaceAlignmentMapperProvider;

    public C0565MotionCaptureViewModelImpl_Factory(Provider<AvcTimer> provider, Provider<HeadTurnGuidanceViewModel> provider2, Provider<ToFaceAlignmentMapper> provider3, Provider<ToFaceAlignmentFeedbackMapper> provider4, Provider<ToFaceAlignmentFeedbackAccessibilityMapper> provider5, Provider<AnnouncementService> provider6, Provider<OnfidoAnalytics> provider7, Provider<SchedulersProvider> provider8, Provider<MicAvailabilityHelper> provider9, Provider<AudioFocusHelper> provider10) {
        this.faceAlignmentTimerProvider = provider;
        this.headTurnGuidanceViewModelProvider = provider2;
        this.toFaceAlignmentMapperProvider = provider3;
        this.toFaceAlignmentFeedbackMapperProvider = provider4;
        this.toFaceAlignmentFeedbackAccessibilityMapperProvider = provider5;
        this.announcementServiceProvider = provider6;
        this.analyticsProvider = provider7;
        this.schedulersProvider = provider8;
        this.micAvailabilityHelperProvider = provider9;
        this.audioFocusHelperProvider = provider10;
    }

    public static C0565MotionCaptureViewModelImpl_Factory create(Provider<AvcTimer> provider, Provider<HeadTurnGuidanceViewModel> provider2, Provider<ToFaceAlignmentMapper> provider3, Provider<ToFaceAlignmentFeedbackMapper> provider4, Provider<ToFaceAlignmentFeedbackAccessibilityMapper> provider5, Provider<AnnouncementService> provider6, Provider<OnfidoAnalytics> provider7, Provider<SchedulersProvider> provider8, Provider<MicAvailabilityHelper> provider9, Provider<AudioFocusHelper> provider10) {
        return new C0565MotionCaptureViewModelImpl_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10);
    }

    public static MotionCaptureViewModelImpl newInstance(AvcTimer avcTimer, HeadTurnGuidanceViewModel headTurnGuidanceViewModel, ToFaceAlignmentMapper toFaceAlignmentMapper, ToFaceAlignmentFeedbackMapper toFaceAlignmentFeedbackMapper, ToFaceAlignmentFeedbackAccessibilityMapper toFaceAlignmentFeedbackAccessibilityMapper, AnnouncementService announcementService, OnfidoAnalytics onfidoAnalytics, SchedulersProvider schedulersProvider, MicAvailabilityHelper micAvailabilityHelper, AudioFocusHelper audioFocusHelper, boolean z, MotionCameraController motionCameraController, FaceDetectorAvc faceDetectorAvc) {
        return new MotionCaptureViewModelImpl(avcTimer, headTurnGuidanceViewModel, toFaceAlignmentMapper, toFaceAlignmentFeedbackMapper, toFaceAlignmentFeedbackAccessibilityMapper, announcementService, onfidoAnalytics, schedulersProvider, micAvailabilityHelper, audioFocusHelper, z, motionCameraController, faceDetectorAvc);
    }

    public MotionCaptureViewModelImpl get(boolean z, MotionCameraController motionCameraController, FaceDetectorAvc faceDetectorAvc) {
        return newInstance(this.faceAlignmentTimerProvider.get(), this.headTurnGuidanceViewModelProvider.get(), this.toFaceAlignmentMapperProvider.get(), this.toFaceAlignmentFeedbackMapperProvider.get(), this.toFaceAlignmentFeedbackAccessibilityMapperProvider.get(), this.announcementServiceProvider.get(), this.analyticsProvider.get(), this.schedulersProvider.get(), this.micAvailabilityHelperProvider.get(), this.audioFocusHelperProvider.get(), z, motionCameraController, faceDetectorAvc);
    }
}
