package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel;

import com.onfido.android.sdk.capture.component.active.video.capture.analytics.AvcAnalyticsCaptureStatus;
import com.onfido.android.sdk.capture.component.active.video.capture.analytics.AvcAnalyticsEvent;
import com.onfido.android.sdk.capture.component.active.video.capture.analytics.AvcAnalyticsFaceAlignment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorResult;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.headturn.HeadTurnGuidanceViewModel;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.mapper.ToFaceAlignmentFeedbackAccessibilityMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.mapper.ToFaceAlignmentFeedbackMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.mapper.ToFaceAlignmentMapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.model.FaceAlignment;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.model.FaceAlignmentFeedback;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.AudioFocusHelper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.AvcTimer;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util.MicAvailabilityHelper;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.dagger.assisted.Assisted;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000À\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 j2\u00020\u0001:\u0002jkBu\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0001\u0010\u0016\u001a\u00020\u0017\u0012\b\b\u0001\u0010\u0018\u001a\u00020\u0019\u0012\b\b\u0001\u0010\u001a\u001a\u00020\u001b¢\u0006\u0002\u0010\u001cJ\b\u00107\u001a\u000208H\u0002J\b\u00109\u001a\u000208H\u0016J\u0010\u0010:\u001a\u0002082\u0006\u0010;\u001a\u00020)H\u0002J\u0010\u0010<\u001a\u0002082\u0006\u0010;\u001a\u00020)H\u0002J\b\u0010=\u001a\u000208H\u0016J\b\u0010>\u001a\u000201H\u0002J\b\u0010?\u001a\u000201H\u0002J\b\u0010@\u001a\u000201H\u0002J\u0010\u0010A\u001a\u0002082\u0006\u0010B\u001a\u00020CH\u0002J\b\u0010D\u001a\u000208H\u0002J\u0010\u0010E\u001a\u0002082\u0006\u0010F\u001a\u00020GH\u0016J\b\u0010H\u001a\u00020\u0017H\u0016J\b\u0010I\u001a\u00020\u0017H\u0002J\b\u0010J\u001a\u00020\u0017H\u0002J\u0010\u0010K\u001a\u00020\u00172\u0006\u0010L\u001a\u00020CH\u0002J\b\u0010M\u001a\u00020\u0017H\u0002J\b\u0010N\u001a\u00020\u0017H\u0002J\b\u0010O\u001a\u00020\u0017H\u0002J\b\u0010P\u001a\u000208H\u0002J\u0010\u0010Q\u001a\u0002082\u0006\u0010F\u001a\u00020GH\u0002J\b\u0010R\u001a\u000208H\u0002J\b\u0010S\u001a\u000208H\u0002J\b\u0010T\u001a\u000208H\u0002J\b\u0010U\u001a\u000208H\u0016J\b\u0010V\u001a\u000208H\u0016J\b\u0010W\u001a\u000208H\u0016J\b\u0010X\u001a\u000208H\u0016J\b\u0010Y\u001a\u000208H\u0016J\b\u0010Z\u001a\u000208H\u0016J\b\u0010[\u001a\u000208H\u0002J\b\u0010\\\u001a\u000208H\u0002J\u0015\u0010]\u001a\u0002082\u0006\u00103\u001a\u000204H\u0001¢\u0006\u0002\b^J\b\u0010_\u001a\u000208H\u0002J\b\u0010`\u001a\u000208H\u0002J\b\u0010a\u001a\u000208H\u0016J\b\u0010b\u001a\u000208H\u0002J\u0010\u0010c\u001a\u0002082\u0006\u0010d\u001a\u00020eH\u0002J\u0010\u0010f\u001a\u0002082\u0006\u0010g\u001a\u00020hH\u0002J\b\u0010i\u001a\u000208H\u0016R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R!\u0010$\u001a\u0015\u0012\f\u0012\n &*\u0004\u0018\u00010!0!0%¢\u0006\u0002\b'X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020)0 X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010#R\u001a\u0010+\u001a\b\u0012\u0004\u0012\u00020)0 X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010#R\u0019\u0010-\u001a\r\u0012\u0004\u0012\u00020)0.¢\u0006\u0002\b'X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010/\u001a\u0015\u0012\f\u0012\n &*\u0004\u0018\u00010)0)0%¢\u0006\u0002\b'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00103\u001a\b\u0012\u0004\u0012\u0002040 X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010#R\u0019\u00106\u001a\r\u0012\u0004\u0012\u0002040.¢\u0006\u0002\b'X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006l"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModelImpl;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel;", "faceAlignmentTimer", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/AvcTimer;", "headTurnGuidanceViewModel", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel;", "toFaceAlignmentMapper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/mapper/ToFaceAlignmentMapper;", "toFaceAlignmentFeedbackMapper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/mapper/ToFaceAlignmentFeedbackMapper;", "toFaceAlignmentFeedbackAccessibilityMapper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/mapper/ToFaceAlignmentFeedbackAccessibilityMapper;", "announcementService", "Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;", "analytics", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "micAvailabilityHelper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/MicAvailabilityHelper;", "audioFocusHelper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/AudioFocusHelper;", "audioEnabled", "", "cameraController", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraController;", "faceDetector", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvc;", "(Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/AvcTimer;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/headturn/HeadTurnGuidanceViewModel;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/mapper/ToFaceAlignmentMapper;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/mapper/ToFaceAlignmentFeedbackMapper;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/mapper/ToFaceAlignmentFeedbackAccessibilityMapper;Lcom/onfido/android/sdk/capture/internal/service/AnnouncementService;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/OnfidoAnalytics;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/MicAvailabilityHelper;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/util/AudioFocusHelper;ZLcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraController;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvc;)V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "detectedFace", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult$FaceDetected;", "getDetectedFace", "()Lio/reactivex/rxjava3/core/Observable;", "detectedFaceSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "faceAlignmentFeedback", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback;", "getFaceAlignmentFeedback", "faceAlignmentFeedbackAccessibility", "getFaceAlignmentFeedbackAccessibility", "faceAlignmentFeedbackAccessibilitySubject", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "faceAlignmentFeedbackSubject", "recordingTime", "", "recordingTimerDisposable", "viewState", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState;", "getViewState", "viewStateSubject", "cancelRecording", "", "completeFlow", "emitFaceAlignmentAccessibilityFeedback", "feedback", "emitFaceAlignmentFeedback", "finishRecording", "getAdjustedMaxRecordingTime", "getFaceNotDetectedFeedbackTimeout", "getFaceNotDetectedTransitionTimeout", "handleFaceAlignment", "it", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignment;", "handleFaceNotDetected", "initialize", "ovalRect", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "isCompletedState", "isDelayStartRecordingState", "isFaceAlignmentState", "isFaceMisalignedDuringRecording", "faceAlignment", "isFaceNotPresentState", "isRecordingFinishedState", "isRecordingStartedState", "observeAudioFocus", "observeFaceDetected", "observeFaceNotDetected", "observeHeadTurnCompleted", "onAlertDialogDismissed", "onAudioConflictAlertDialogDismissed", "onDestroy", "onMicIsNotAvailableAlertDialogDismissed", "onRecordingIsTooShortAlertDialogDismissed", "onRecordingTimeoutAlertDialogDismissed", "onStop", "resetPreviewIfRecordingStartedState", "resetStates", "setViewState", "setViewState$onfido_capture_sdk_core_release", "startFaceNotDetectedFeedbackTimer", "startFaceNotDetectedTransitionTimeoutTimer", "startRecording", "startRecordingTimer", "trackCaptureStatusAnalyticsEvent", "status", "Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsCaptureStatus;", "trackFaceAlignmentAnalyticsEvent", "alignment", "Lcom/onfido/android/sdk/capture/component/active/video/capture/analytics/AvcAnalyticsFaceAlignment;", "trackScreenAnalyticsEvent", "Companion", "Factory", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionCaptureViewModelImpl implements MotionCaptureViewModel {
    public static final long MAX_RECORDING_TIME_MILLISECONDS = 15000;
    public static final long MIN_RECORDING_TIME_MILLISECONDS = 1500;
    public static final long TIMER_FACE_NOT_DETECTED_FEEDBACK_MILLISECONDS = 5000;
    public static final long TIMER_FACE_NOT_DETECTED_FEEDBACK_MILLISECONDS_ACCESSIBILITY = 10000;
    public static final long TIMER_FACE_NOT_DETECTED_TIMEOUT_MILLISECONDS = 5000;
    public static final long TIMER_FACE_NOT_DETECTED_TIMEOUT_MILLISECONDS_ACCESSIBILITY = 10000;
    public static final long TIMER_PERIOD_MILLISECONDS = 100;
    private final OnfidoAnalytics analytics;
    private final AnnouncementService announcementService;
    private final boolean audioEnabled;
    private final AudioFocusHelper audioFocusHelper;
    private final MotionCameraController cameraController;
    private final CompositeDisposable compositeDisposable;
    private final Observable<FaceDetectorResult.FaceDetected> detectedFace;
    private final PublishSubject<FaceDetectorResult.FaceDetected> detectedFaceSubject;
    private final Observable<FaceAlignmentFeedback> faceAlignmentFeedback;
    private final Observable<FaceAlignmentFeedback> faceAlignmentFeedbackAccessibility;
    private final BehaviorSubject<FaceAlignmentFeedback> faceAlignmentFeedbackAccessibilitySubject;
    private final PublishSubject<FaceAlignmentFeedback> faceAlignmentFeedbackSubject;
    private final AvcTimer faceAlignmentTimer;
    private final FaceDetectorAvc faceDetector;
    private final HeadTurnGuidanceViewModel headTurnGuidanceViewModel;
    private final MicAvailabilityHelper micAvailabilityHelper;
    private long recordingTime;
    private final CompositeDisposable recordingTimerDisposable;
    private final SchedulersProvider schedulersProvider;
    private final ToFaceAlignmentFeedbackAccessibilityMapper toFaceAlignmentFeedbackAccessibilityMapper;
    private final ToFaceAlignmentFeedbackMapper toFaceAlignmentFeedbackMapper;
    private final ToFaceAlignmentMapper toFaceAlignmentMapper;
    private final Observable<MotionCaptureViewModel.ViewState> viewState;
    private final BehaviorSubject<MotionCaptureViewModel.ViewState> viewStateSubject;

    @AssistedFactory
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModelImpl$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModelImpl;", "audioEnabled", "", "cameraController", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraController;", "faceDetector", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvc;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        MotionCaptureViewModelImpl create(boolean audioEnabled, MotionCameraController cameraController, FaceDetectorAvc faceDetector);
    }

    @AssistedInject
    public MotionCaptureViewModelImpl(AvcTimer faceAlignmentTimer, HeadTurnGuidanceViewModel headTurnGuidanceViewModel, ToFaceAlignmentMapper toFaceAlignmentMapper, ToFaceAlignmentFeedbackMapper toFaceAlignmentFeedbackMapper, ToFaceAlignmentFeedbackAccessibilityMapper toFaceAlignmentFeedbackAccessibilityMapper, AnnouncementService announcementService, OnfidoAnalytics analytics, SchedulersProvider schedulersProvider, MicAvailabilityHelper micAvailabilityHelper, AudioFocusHelper audioFocusHelper, @Assisted boolean z, @Assisted MotionCameraController cameraController, @Assisted FaceDetectorAvc faceDetector) {
        Intrinsics.checkNotNullParameter(faceAlignmentTimer, "faceAlignmentTimer");
        Intrinsics.checkNotNullParameter(headTurnGuidanceViewModel, "headTurnGuidanceViewModel");
        Intrinsics.checkNotNullParameter(toFaceAlignmentMapper, "toFaceAlignmentMapper");
        Intrinsics.checkNotNullParameter(toFaceAlignmentFeedbackMapper, "toFaceAlignmentFeedbackMapper");
        Intrinsics.checkNotNullParameter(toFaceAlignmentFeedbackAccessibilityMapper, "toFaceAlignmentFeedbackAccessibilityMapper");
        Intrinsics.checkNotNullParameter(announcementService, "announcementService");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(micAvailabilityHelper, "micAvailabilityHelper");
        Intrinsics.checkNotNullParameter(audioFocusHelper, "audioFocusHelper");
        Intrinsics.checkNotNullParameter(cameraController, "cameraController");
        Intrinsics.checkNotNullParameter(faceDetector, "faceDetector");
        this.faceAlignmentTimer = faceAlignmentTimer;
        this.headTurnGuidanceViewModel = headTurnGuidanceViewModel;
        this.toFaceAlignmentMapper = toFaceAlignmentMapper;
        this.toFaceAlignmentFeedbackMapper = toFaceAlignmentFeedbackMapper;
        this.toFaceAlignmentFeedbackAccessibilityMapper = toFaceAlignmentFeedbackAccessibilityMapper;
        this.announcementService = announcementService;
        this.analytics = analytics;
        this.schedulersProvider = schedulersProvider;
        this.micAvailabilityHelper = micAvailabilityHelper;
        this.audioFocusHelper = audioFocusHelper;
        this.audioEnabled = z;
        this.cameraController = cameraController;
        this.faceDetector = faceDetector;
        this.compositeDisposable = new CompositeDisposable();
        this.recordingTimerDisposable = new CompositeDisposable();
        PublishSubject<FaceDetectorResult.FaceDetected> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create(...)");
        this.detectedFaceSubject = publishSubjectCreate;
        Observable<FaceDetectorResult.FaceDetected> observableHide = publishSubjectCreate.hide();
        Intrinsics.checkNotNullExpressionValue(observableHide, "hide(...)");
        this.detectedFace = observableHide;
        BehaviorSubject<MotionCaptureViewModel.ViewState> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(MotionCaptureViewModel.ViewState.FaceNotPresent.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault, "createDefault(...)");
        this.viewStateSubject = behaviorSubjectCreateDefault;
        Observable<MotionCaptureViewModel.ViewState> observableDistinctUntilChanged = behaviorSubjectCreateDefault.hide().distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        this.viewState = observableDistinctUntilChanged;
        PublishSubject<FaceAlignmentFeedback> publishSubjectCreate2 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate2, "create(...)");
        this.faceAlignmentFeedbackSubject = publishSubjectCreate2;
        Observable<FaceAlignmentFeedback> observableDoOnNext = publishSubjectCreate2.hide().distinctUntilChanged().doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl$faceAlignmentFeedback$1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(FaceAlignmentFeedback faceAlignmentFeedback) {
                MotionCaptureViewModelImpl motionCaptureViewModelImpl;
                AvcAnalyticsFaceAlignment avcAnalyticsFaceAlignment;
                if (Intrinsics.areEqual(faceAlignmentFeedback, FaceAlignmentFeedback.MoveBack.INSTANCE)) {
                    motionCaptureViewModelImpl = this.this$0;
                    avcAnalyticsFaceAlignment = AvcAnalyticsFaceAlignment.FACE_TOO_CLOSE;
                } else if (Intrinsics.areEqual(faceAlignmentFeedback, FaceAlignmentFeedback.MoveCloser.INSTANCE)) {
                    motionCaptureViewModelImpl = this.this$0;
                    avcAnalyticsFaceAlignment = AvcAnalyticsFaceAlignment.FACE_TOO_FAR;
                } else if (Intrinsics.areEqual(faceAlignmentFeedback, FaceAlignmentFeedback.FaceNotCentered.INSTANCE)) {
                    motionCaptureViewModelImpl = this.this$0;
                    avcAnalyticsFaceAlignment = AvcAnalyticsFaceAlignment.FACE_NOT_CENTERED;
                } else if (Intrinsics.areEqual(faceAlignmentFeedback, FaceAlignmentFeedback.FaceNotDetected.INSTANCE)) {
                    motionCaptureViewModelImpl = this.this$0;
                    avcAnalyticsFaceAlignment = AvcAnalyticsFaceAlignment.NO_FACE_DETECTED;
                } else {
                    if (!Intrinsics.areEqual(faceAlignmentFeedback, FaceAlignmentFeedback.FaceAligned.INSTANCE)) {
                        return;
                    }
                    motionCaptureViewModelImpl = this.this$0;
                    avcAnalyticsFaceAlignment = AvcAnalyticsFaceAlignment.FACE_ALIGNED;
                }
                motionCaptureViewModelImpl.trackFaceAlignmentAnalyticsEvent(avcAnalyticsFaceAlignment);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableDoOnNext, "doOnNext(...)");
        this.faceAlignmentFeedback = observableDoOnNext;
        BehaviorSubject<FaceAlignmentFeedback> behaviorSubjectCreateDefault2 = BehaviorSubject.createDefault(FaceAlignmentFeedback.Idle.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(behaviorSubjectCreateDefault2, "createDefault(...)");
        this.faceAlignmentFeedbackAccessibilitySubject = behaviorSubjectCreateDefault2;
        Observable<FaceAlignmentFeedback> observableDistinctUntilChanged2 = behaviorSubjectCreateDefault2.hide().distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged2, "distinctUntilChanged(...)");
        this.faceAlignmentFeedbackAccessibility = observableDistinctUntilChanged2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelRecording() {
        this.recordingTimerDisposable.clear();
        this.audioFocusHelper.abandonAudioFocus();
        this.cameraController.cancelRecording();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitFaceAlignmentAccessibilityFeedback(FaceAlignmentFeedback feedback) {
        this.faceAlignmentFeedbackAccessibilitySubject.onNext(feedback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitFaceAlignmentFeedback(FaceAlignmentFeedback feedback) {
        this.faceAlignmentFeedbackSubject.onNext(feedback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long getAdjustedMaxRecordingTime() {
        return 14000L;
    }

    private final long getFaceNotDetectedFeedbackTimeout() {
        return this.announcementService.isEnabled() ? 10000L : 5000L;
    }

    private final long getFaceNotDetectedTransitionTimeout() {
        return this.announcementService.isEnabled() ? 10000L : 5000L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleFaceAlignment(FaceAlignment it) {
        MotionCaptureViewModel.ViewState viewState;
        if (isFaceNotPresentState()) {
            this.faceAlignmentTimer.cancel();
        }
        if ((it instanceof FaceAlignment.FaceCenterIsNotAligned) || Intrinsics.areEqual(it, FaceAlignment.FaceCenterIsAligned.FaceIsTooBig.INSTANCE) || Intrinsics.areEqual(it, FaceAlignment.FaceCenterIsAligned.FaceIsTooSmall.INSTANCE)) {
            resetPreviewIfRecordingStartedState();
            viewState = MotionCaptureViewModel.ViewState.FaceAlignment.INSTANCE;
        } else {
            if (!Intrinsics.areEqual(it, FaceAlignment.FaceCenterIsAligned.FaceIsAligned.INSTANCE)) {
                return;
            }
            if (!isFaceNotPresentState() && !isFaceAlignmentState()) {
                return;
            } else {
                viewState = MotionCaptureViewModel.ViewState.DelayStartRecording.INSTANCE;
            }
        }
        setViewState$onfido_capture_sdk_core_release(viewState);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleFaceNotDetected() {
        resetPreviewIfRecordingStartedState();
        setViewState$onfido_capture_sdk_core_release(MotionCaptureViewModel.ViewState.FaceNotPresent.INSTANCE);
        FaceAlignmentFeedback.FaceNotDetected faceNotDetected = FaceAlignmentFeedback.FaceNotDetected.INSTANCE;
        emitFaceAlignmentFeedback(faceNotDetected);
        emitFaceAlignmentAccessibilityFeedback(faceNotDetected);
        startFaceNotDetectedTransitionTimeoutTimer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isDelayStartRecordingState() {
        return Intrinsics.areEqual(this.viewStateSubject.getValue(), MotionCaptureViewModel.ViewState.DelayStartRecording.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFaceAlignmentState() {
        return Intrinsics.areEqual(this.viewStateSubject.getValue(), MotionCaptureViewModel.ViewState.FaceAlignment.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFaceMisalignedDuringRecording(FaceAlignment faceAlignment) {
        if ((faceAlignment instanceof FaceAlignment.FaceCenterIsNotAligned) || Intrinsics.areEqual(faceAlignment, FaceAlignment.FaceCenterIsAligned.FaceIsTooBig.INSTANCE) || Intrinsics.areEqual(faceAlignment, FaceAlignment.FaceCenterIsAligned.FaceIsTooSmall.INSTANCE)) {
            return isRecordingStartedState();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFaceNotPresentState() {
        return Intrinsics.areEqual(this.viewStateSubject.getValue(), MotionCaptureViewModel.ViewState.FaceNotPresent.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isRecordingFinishedState() {
        return Intrinsics.areEqual(this.viewStateSubject.getValue(), MotionCaptureViewModel.ViewState.RecordingFinished.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isRecordingStartedState() {
        return Intrinsics.areEqual(this.viewStateSubject.getValue(), MotionCaptureViewModel.ViewState.RecordingStarted.INSTANCE);
    }

    private final void observeAudioFocus() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = this.audioFocusHelper.getFocusLostObservable().observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.observeAudioFocus.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept(((Boolean) obj).booleanValue());
            }

            public final void accept(boolean z) {
                if (MotionCaptureViewModelImpl.this.isRecordingStartedState()) {
                    MotionCaptureViewModelImpl.this.cancelRecording();
                }
                MotionCaptureViewModelImpl.this.setViewState$onfido_capture_sdk_core_release(MotionCaptureViewModel.ViewState.Error.AudioConflict.INSTANCE);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeFaceDetected(final OnfidoRectF ovalRect) {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Observable<U> observableCast = this.faceDetector.getResultObservable().filter(new Predicate() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl$observeFaceDetected$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof FaceDetectorResult.FaceDetected;
            }
        }).cast(FaceDetectorResult.FaceDetected.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Disposable disposableSubscribe = observableCast.observeOn(this.schedulersProvider.getUi()).doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.observeFaceDetected.1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(FaceDetectorResult.FaceDetected it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (MotionCaptureViewModelImpl.this.isRecordingStartedState()) {
                    MotionCaptureViewModelImpl.this.headTurnGuidanceViewModel.handleFaceRotation(it.getFaceAngle());
                } else {
                    MotionCaptureViewModelImpl.this.detectedFaceSubject.onNext(it);
                }
            }
        }).map(new Function() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.observeFaceDetected.2
            @Override // io.reactivex.rxjava3.functions.Function
            public final FaceAlignment apply(FaceDetectorResult.FaceDetected it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MotionCaptureViewModelImpl.this.toFaceAlignmentMapper.map(ovalRect, it.getFaceRect(), it.getFaceAngle(), MotionCaptureViewModelImpl.this.isDelayStartRecordingState() || MotionCaptureViewModelImpl.this.isRecordingStartedState());
            }
        }).filter(new Predicate() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.observeFaceDetected.3
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(FaceAlignment it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MotionCaptureViewModelImpl.this.isFaceNotPresentState() || MotionCaptureViewModelImpl.this.isFaceAlignmentState() || MotionCaptureViewModelImpl.this.isDelayStartRecordingState() || MotionCaptureViewModelImpl.this.isRecordingStartedState();
            }
        }).doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.observeFaceDetected.4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(FaceAlignment it) {
                Intrinsics.checkNotNullParameter(it, "it");
                MotionCaptureViewModelImpl motionCaptureViewModelImpl = MotionCaptureViewModelImpl.this;
                motionCaptureViewModelImpl.emitFaceAlignmentFeedback(motionCaptureViewModelImpl.toFaceAlignmentFeedbackMapper.map(it));
            }
        }).doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.observeFaceDetected.5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(FaceAlignment it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (MotionCaptureViewModelImpl.this.isFaceMisalignedDuringRecording(it)) {
                    MotionCaptureViewModelImpl.this.emitFaceAlignmentAccessibilityFeedback(FaceAlignmentFeedback.FaceNotCentered.INSTANCE);
                }
                MotionCaptureViewModelImpl motionCaptureViewModelImpl = MotionCaptureViewModelImpl.this;
                motionCaptureViewModelImpl.emitFaceAlignmentAccessibilityFeedback(motionCaptureViewModelImpl.toFaceAlignmentFeedbackAccessibilityMapper.map(it));
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.observeFaceDetected.6
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(FaceAlignment p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                MotionCaptureViewModelImpl.this.handleFaceAlignment(p0);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeFaceNotDetected() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Observable<FaceDetectorResult> observableDistinctUntilChanged = this.faceDetector.getResultObservable().distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        Observable<U> observableCast = observableDistinctUntilChanged.filter(new Predicate() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl$observeFaceNotDetected$$inlined$filterIsInstance$1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it instanceof FaceDetectorResult.FaceNotDetected;
            }
        }).cast(FaceDetectorResult.FaceNotDetected.class);
        Intrinsics.checkNotNullExpressionValue(observableCast, "cast(...)");
        Disposable disposableSubscribe = observableCast.filter(new Predicate() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.observeFaceNotDetected.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(FaceDetectorResult.FaceNotDetected it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MotionCaptureViewModelImpl.this.isFaceAlignmentState() || MotionCaptureViewModelImpl.this.isDelayStartRecordingState() || MotionCaptureViewModelImpl.this.isRecordingStartedState();
            }
        }).observeOn(this.schedulersProvider.getUi()).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.observeFaceNotDetected.2
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(FaceDetectorResult.FaceNotDetected it) {
                Intrinsics.checkNotNullParameter(it, "it");
                MotionCaptureViewModelImpl.this.handleFaceNotDetected();
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void observeHeadTurnCompleted() {
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        Disposable disposableSubscribe = this.headTurnGuidanceViewModel.mo5553getViewState().filter(new Predicate() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.observeHeadTurnCompleted.1
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(HeadTurnGuidanceViewModel.ViewState it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return it.isCompleted();
            }
        }).filter(new Predicate() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.observeHeadTurnCompleted.2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(HeadTurnGuidanceViewModel.ViewState it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return MotionCaptureViewModelImpl.this.isRecordingStartedState();
            }
        }).subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.observeHeadTurnCompleted.3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(HeadTurnGuidanceViewModel.ViewState it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (MotionCaptureViewModelImpl.this.recordingTime >= 1500) {
                    MotionCaptureViewModelImpl.this.recordingTimerDisposable.clear();
                    MotionCaptureViewModelImpl.this.setViewState$onfido_capture_sdk_core_release(MotionCaptureViewModel.ViewState.DelayFinishRecording.INSTANCE);
                } else {
                    MotionCaptureViewModelImpl.this.cancelRecording();
                    MotionCaptureViewModelImpl.this.setViewState$onfido_capture_sdk_core_release(MotionCaptureViewModel.ViewState.Error.RecordingIsTooShort.INSTANCE);
                    MotionCaptureViewModelImpl.this.analytics.track(AvcAnalyticsEvent.FaceCaptureErrorTooFast.INSTANCE);
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    private final void onAlertDialogDismissed() {
        this.cameraController.reset();
        setViewState$onfido_capture_sdk_core_release(MotionCaptureViewModel.ViewState.FaceAlignment.INSTANCE);
    }

    private final void resetPreviewIfRecordingStartedState() {
        if (isRecordingStartedState()) {
            cancelRecording();
            this.cameraController.reset();
            trackCaptureStatusAnalyticsEvent(AvcAnalyticsCaptureStatus.FACE_LOST);
        }
    }

    private final void resetStates() {
        MotionCaptureViewModel.ViewState viewState;
        MotionCaptureViewModel.ViewState value = this.viewStateSubject.getValue();
        if (Intrinsics.areEqual(value, MotionCaptureViewModel.ViewState.FaceAlignment.INSTANCE) || Intrinsics.areEqual(value, MotionCaptureViewModel.ViewState.DelayStartRecording.INSTANCE) || (value instanceof MotionCaptureViewModel.ViewState.Error)) {
            viewState = MotionCaptureViewModel.ViewState.FaceNotPresent.INSTANCE;
        } else if (Intrinsics.areEqual(value, MotionCaptureViewModel.ViewState.RecordingStarted.INSTANCE) || Intrinsics.areEqual(value, MotionCaptureViewModel.ViewState.DelayFinishRecording.INSTANCE)) {
            cancelRecording();
            viewState = MotionCaptureViewModel.ViewState.FaceNotPresent.INSTANCE;
        } else if (!Intrinsics.areEqual(value, MotionCaptureViewModel.ViewState.RecordingFinished.INSTANCE)) {
            return;
        } else {
            viewState = MotionCaptureViewModel.ViewState.Completed.INSTANCE;
        }
        setViewState$onfido_capture_sdk_core_release(viewState);
    }

    private final void startFaceNotDetectedFeedbackTimer() {
        if (isFaceNotPresentState()) {
            this.faceAlignmentTimer.start(getFaceNotDetectedFeedbackTimeout(), new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.startFaceNotDetectedFeedbackTimer.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    MotionCaptureViewModelImpl motionCaptureViewModelImpl = MotionCaptureViewModelImpl.this;
                    FaceAlignmentFeedback.FaceNotDetected faceNotDetected = FaceAlignmentFeedback.FaceNotDetected.INSTANCE;
                    motionCaptureViewModelImpl.emitFaceAlignmentFeedback(faceNotDetected);
                    MotionCaptureViewModelImpl.this.emitFaceAlignmentAccessibilityFeedback(faceNotDetected);
                    MotionCaptureViewModelImpl.this.startFaceNotDetectedTransitionTimeoutTimer();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startFaceNotDetectedTransitionTimeoutTimer() {
        this.faceAlignmentTimer.start(getFaceNotDetectedTransitionTimeout(), new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.startFaceNotDetectedTransitionTimeoutTimer.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MotionCaptureViewModelImpl.this.setViewState$onfido_capture_sdk_core_release(MotionCaptureViewModel.ViewState.Error.FaceAlignmentTimeout.INSTANCE);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startRecordingTimer() {
        CompositeDisposable compositeDisposable = this.recordingTimerDisposable;
        Disposable disposableSubscribe = Observable.interval(100L, TimeUnit.MILLISECONDS, this.schedulersProvider.getTimer()).takeUntil(new Predicate() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.startRecordingTimer.1
            public final boolean test(long j) {
                return j * 100 == MotionCaptureViewModelImpl.this.getAdjustedMaxRecordingTime();
            }

            @Override // io.reactivex.rxjava3.functions.Predicate
            public /* bridge */ /* synthetic */ boolean test(Object obj) {
                return test(((Number) obj).longValue());
            }
        }).observeOn(this.schedulersProvider.getUi()).doOnNext(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.startRecordingTimer.2
            public final void accept(long j) {
                MotionCaptureViewModelImpl.this.recordingTime = j * 100;
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Object obj) {
                accept(((Number) obj).longValue());
            }
        }).doOnComplete(new Action() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                MotionCaptureViewModelImpl.startRecordingTimer$lambda$0(this.f$0);
            }
        }).subscribe();
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startRecordingTimer$lambda$0(MotionCaptureViewModelImpl this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.cancelRecording();
        this$0.setViewState$onfido_capture_sdk_core_release(MotionCaptureViewModel.ViewState.Error.RecordingTimeout.INSTANCE);
        this$0.analytics.track(AvcAnalyticsEvent.FaceCaptureErrorTimeout.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void trackCaptureStatusAnalyticsEvent(AvcAnalyticsCaptureStatus status) {
        this.analytics.track(new AvcAnalyticsEvent.FaceCaptureStatus(status.getValue()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void trackFaceAlignmentAnalyticsEvent(AvcAnalyticsFaceAlignment alignment) {
        this.analytics.track(new AvcAnalyticsEvent.FaceAlignmentStatus(alignment.getValue()));
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public void completeFlow() {
        setViewState$onfido_capture_sdk_core_release(MotionCaptureViewModel.ViewState.Completed.INSTANCE);
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public void finishRecording() {
        this.audioFocusHelper.abandonAudioFocus();
        this.cameraController.finishRecording();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public Observable<FaceDetectorResult.FaceDetected> getDetectedFace() {
        return this.detectedFace;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public Observable<FaceAlignmentFeedback> getFaceAlignmentFeedback() {
        return this.faceAlignmentFeedback;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public Observable<FaceAlignmentFeedback> getFaceAlignmentFeedbackAccessibility() {
        return this.faceAlignmentFeedbackAccessibility;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public Observable<MotionCaptureViewModel.ViewState> getViewState() {
        return this.viewState;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public void initialize(OnfidoRectF ovalRect) {
        Intrinsics.checkNotNullParameter(ovalRect, "ovalRect");
        startFaceNotDetectedFeedbackTimer();
        observeFaceNotDetected();
        observeFaceDetected(ovalRect);
        observeHeadTurnCompleted();
        observeAudioFocus();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public boolean isCompletedState() {
        return Intrinsics.areEqual(this.viewStateSubject.getValue(), MotionCaptureViewModel.ViewState.Completed.INSTANCE);
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public void onAudioConflictAlertDialogDismissed() {
        onAlertDialogDismissed();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public void onDestroy() {
        this.cameraController.onDestroy();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public void onMicIsNotAvailableAlertDialogDismissed() {
        onAlertDialogDismissed();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public void onRecordingIsTooShortAlertDialogDismissed() {
        onAlertDialogDismissed();
        this.analytics.track(AvcAnalyticsEvent.FaceCaptureErrorTooFastRestartCapture.INSTANCE);
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public void onRecordingTimeoutAlertDialogDismissed() {
        onAlertDialogDismissed();
        this.analytics.track(AvcAnalyticsEvent.FaceCaptureErrorTimeoutRestartCapture.INSTANCE);
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public void onStop() {
        this.compositeDisposable.clear();
        this.faceAlignmentTimer.cancel();
        resetStates();
        this.cameraController.onStop();
    }

    public final void setViewState$onfido_capture_sdk_core_release(MotionCaptureViewModel.ViewState viewState) {
        Intrinsics.checkNotNullParameter(viewState, "viewState");
        this.viewStateSubject.onNext(viewState);
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public void startRecording() {
        MotionCaptureViewModel.ViewState viewState;
        if (this.audioEnabled && !this.micAvailabilityHelper.isMicAvailable()) {
            viewState = MotionCaptureViewModel.ViewState.Error.MicIsNotAvailable.INSTANCE;
        } else {
            if (!this.audioEnabled || this.announcementService.isEnabled() || this.audioFocusHelper.requestAudioFocusIfPossible()) {
                this.cameraController.startRecording(new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.startRecording.1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        MotionCaptureViewModelImpl.this.setViewState$onfido_capture_sdk_core_release(MotionCaptureViewModel.ViewState.RecordingStarted.INSTANCE);
                        MotionCaptureViewModelImpl.this.startRecordingTimer();
                        MotionCaptureViewModelImpl.this.trackCaptureStatusAnalyticsEvent(AvcAnalyticsCaptureStatus.CAPTURE_STARTED);
                    }
                }, new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.startRecording.2
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        MotionCaptureViewModelImpl.this.setViewState$onfido_capture_sdk_core_release(MotionCaptureViewModel.ViewState.RecordingFinished.INSTANCE);
                        MotionCaptureViewModelImpl.this.trackCaptureStatusAnalyticsEvent(AvcAnalyticsCaptureStatus.CAPTURE_COMPLETED);
                    }
                }, new Function1<Throwable, Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModelImpl.startRecording.3
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                        invoke2(th);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Throwable it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        if (MotionCaptureViewModelImpl.this.isRecordingFinishedState()) {
                            MotionCaptureViewModelImpl.this.setViewState$onfido_capture_sdk_core_release(new MotionCaptureViewModel.ViewState.Error.RecordingFailed(it));
                        }
                    }
                });
                return;
            }
            viewState = MotionCaptureViewModel.ViewState.Error.AudioConflict.INSTANCE;
        }
        setViewState$onfido_capture_sdk_core_release(viewState);
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.MotionCaptureViewModel
    public void trackScreenAnalyticsEvent() {
        this.analytics.track(AvcAnalyticsEvent.FaceAlignment.INSTANCE);
    }
}
