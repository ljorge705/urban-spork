package com.onfido.android.sdk.capture.ui.camera.liveness.capture;

import androidx.core.app.FrameMetricsAggregator;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.google.android.gms.fido.u2f.api.common.ClientData;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.detector.face.FaceDetectionResult;
import com.onfido.android.sdk.capture.detector.face.FaceDetector;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.CaptureTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessTracker;
import com.onfido.android.sdk.capture.internal.usecase.FaceProcessingUseCase;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.CaptureType;
import com.onfido.android.sdk.capture.ui.camera.FaceDetectionFrame;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengeType;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengeViewModel;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessChallengesViewModel;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.LivenessPerformedChallenges;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.MovementLivenessChallenge;
import com.onfido.android.sdk.capture.ui.camera.liveness.challenges.ReciteLivenessChallenge;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.javax.inject.Inject;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.reactive.ReactiveFlowKt;
import kotlinx.coroutines.rx3.RxAwaitKt;

@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000 u2\u00020\u0001:\u0001uBW\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015¢\u0006\u0002\u0010\u0016J\r\u00103\u001a\u000204H\u0000¢\u0006\u0002\b5J\r\u00106\u001a\u00020/H\u0000¢\u0006\u0002\b7J\r\u00108\u001a\u00020/H\u0000¢\u0006\u0002\b9J\r\u0010:\u001a\u00020;H\u0000¢\u0006\u0002\b<J\r\u0010=\u001a\u00020 H\u0000¢\u0006\u0002\b>J\r\u0010?\u001a\u000204H\u0000¢\u0006\u0002\b@J\b\u0010A\u001a\u000204H\u0002J\r\u0010B\u001a\u000204H\u0000¢\u0006\u0002\bCJ\b\u0010D\u001a\u000204H\u0014J\r\u0010E\u001a\u000204H\u0000¢\u0006\u0002\bFJ\r\u0010G\u001a\u000204H\u0000¢\u0006\u0002\bHJ\u0015\u0010I\u001a\u0002042\u0006\u0010J\u001a\u00020KH\u0000¢\u0006\u0002\bLJ\u0015\u0010M\u001a\u0002042\u0006\u0010N\u001a\u00020OH\u0000¢\u0006\u0002\bPJ\u0015\u0010Q\u001a\u0002042\u0006\u0010R\u001a\u00020 H\u0000¢\u0006\u0002\bSJ\u0018\u0010T\u001a\u0002042\u0006\u0010U\u001a\u00020\u001c2\u0006\u0010V\u001a\u00020WH\u0002J\u0015\u0010X\u001a\u0002042\u0006\u0010Y\u001a\u00020KH\u0000¢\u0006\u0002\bZJ\r\u0010[\u001a\u000204H\u0000¢\u0006\u0002\b\\J\b\u0010]\u001a\u000204H\u0002J\u0015\u0010^\u001a\u0002042\u0006\u0010_\u001a\u00020WH\u0000¢\u0006\u0002\b`J\b\u0010a\u001a\u000204H\u0002J\r\u0010b\u001a\u000204H\u0000¢\u0006\u0002\bcJ\b\u0010d\u001a\u000204H\u0002J\u0015\u0010e\u001a\u0002042\u0006\u0010f\u001a\u00020 H\u0000¢\u0006\u0002\bgJ\r\u0010h\u001a\u000204H\u0000¢\u0006\u0002\biJ\u001d\u0010j\u001a\u0002042\u0006\u0010k\u001a\u00020l2\u0006\u0010m\u001a\u00020nH\u0000¢\u0006\u0002\boJ\b\u0010p\u001a\u000204H\u0002J\r\u0010q\u001a\u000204H\u0000¢\u0006\u0002\brJ\r\u0010s\u001a\u000204H\u0000¢\u0006\u0002\btR\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u001bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u0010\u0010&\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020 0\u001bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020 0\u001bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001eR\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00020 0\u001bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001eR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00101\u001a\b\u0012\u0004\u0012\u00020 0\u001bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001eR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006v"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessCaptureViewModel;", "Landroidx/lifecycle/ViewModel;", "faceProcessingUseCase", "Lcom/onfido/android/sdk/capture/internal/usecase/FaceProcessingUseCase;", "faceDetector", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetector;", "identityInteractor", "Lcom/onfido/android/sdk/capture/analytics/IdentityInteractor;", "livenessInteractor", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessInteractor;", "livenessTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/LivenessTracker;", "captureTracker", "Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureTracker;", "timeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "schedulersProvider", "Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;", "onfidoConfig", "Lcom/onfido/android/sdk/capture/OnfidoConfig;", "dispatchersProvider", "Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;", "(Lcom/onfido/android/sdk/capture/internal/usecase/FaceProcessingUseCase;Lcom/onfido/android/sdk/capture/detector/face/FaceDetector;Lcom/onfido/android/sdk/capture/analytics/IdentityInteractor;Lcom/onfido/android/sdk/capture/ui/camera/liveness/capture/LivenessInteractor;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/LivenessTracker;Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/trackers/CaptureTracker;Lcom/onfido/android/sdk/capture/utils/TimeProvider;Lcom/onfido/android/sdk/capture/internal/util/SchedulersProvider;Lcom/onfido/android/sdk/capture/OnfidoConfig;Lcom/onfido/android/sdk/capture/internal/util/DispatchersProvider;)V", "availableStorageCalculationJob", "Lkotlinx/coroutines/Job;", "challengeProcessorJob", "challengeProviderLiveData", "Landroidx/lifecycle/MutableLiveData;", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeViewModel;", "getChallengeProviderLiveData$onfido_capture_sdk_core_release", "()Landroidx/lifecycle/MutableLiveData;", "challengesCompletedLiveData", "", "getChallengesCompletedLiveData$onfido_capture_sdk_core_release", "faceDetectionJob", "faceDetectionResultLiveData", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult;", "getFaceDetectionResultLiveData$onfido_capture_sdk_core_release", "faceDetectionTimeoutJob", "faceOutTimeoutLiveData", "getFaceOutTimeoutLiveData$onfido_capture_sdk_core_release", "faceTrackingJob", "livenessControlButtonLiveData", "getLivenessControlButtonLiveData$onfido_capture_sdk_core_release", "livenessControlButtonWithDelayLiveData", "getLivenessControlButtonWithDelayLiveData$onfido_capture_sdk_core_release", "livenessPreviousChallengeCompleted", "", "movementChallengeTimeoutJob", "storageThresholdReachedLiveData", "getStorageThresholdReachedLiveData$onfido_capture_sdk_core_release", "cancelFaceDetectionJobs", "", "cancelFaceDetectionJobs$onfido_capture_sdk_core_release", "getLivenessButtonDisplayDelay", "getLivenessButtonDisplayDelay$onfido_capture_sdk_core_release", "getMovementTimeoutValueMs", "getMovementTimeoutValueMs$onfido_capture_sdk_core_release", "getUploadChallengesList", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessPerformedChallenges;", "getUploadChallengesList$onfido_capture_sdk_core_release", "isManualCapture", "isManualCapture$onfido_capture_sdk_core_release", "issueNextChallenge", "issueNextChallenge$onfido_capture_sdk_core_release", "observeFaceOut", "onAutoLivenessRecordingStart", "onAutoLivenessRecordingStart$onfido_capture_sdk_core_release", "onCleared", "onManualLivenessNextClick", "onManualLivenessNextClick$onfido_capture_sdk_core_release", "onManualLivenessRecordingStart", "onManualLivenessRecordingStart$onfido_capture_sdk_core_release", "onNextChallenge", ClientData.KEY_CHALLENGE, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallenge;", "onNextChallenge$onfido_capture_sdk_core_release", "onNextFaceDetectionFrame", "frameData", "Lcom/onfido/android/sdk/capture/ui/camera/FaceDetectionFrame;", "onNextFaceDetectionFrame$onfido_capture_sdk_core_release", "onRecordingStarted", "isStartedManually", "onRecordingStarted$onfido_capture_sdk_core_release", "processCurrentChallenge", "currentChallengeViewModel", "livenessChallengesViewModel", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesViewModel;", "pushPerformedChallenge", "livenessChallenge", "pushPerformedChallenge$onfido_capture_sdk_core_release", "reset", "reset$onfido_capture_sdk_core_release", "startFaceDetection", "startLivenessProcessing", "challengesViewModel", "startLivenessProcessing$onfido_capture_sdk_core_release", "startMovementChallengeTimeout", "stopFaceTracking", "stopFaceTracking$onfido_capture_sdk_core_release", "stopMovementChallengeTimeout", "trackCapture", "isPortrait", "trackCapture$onfido_capture_sdk_core_release", "trackCaptureError", "trackCaptureError$onfido_capture_sdk_core_release", "trackChallenge", "challengeIndex", "", "challengeType", "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengeType;", "trackChallenge$onfido_capture_sdk_core_release", "trackVideoButtonClicked", "trackVideoCaptureTimeout", "trackVideoCaptureTimeout$onfido_capture_sdk_core_release", "trackVideoTimeoutRetryButtonClicked", "trackVideoTimeoutRetryButtonClicked$onfido_capture_sdk_core_release", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LivenessCaptureViewModel extends ViewModel {
    public static final long FACE_DETECTION_SAMPLING_PERIOD_MS = 200;
    public static final long FACE_DETECTION_TIMEOUT_MS = 5000;
    public static final long FACE_TRACKING_TIMEOUT_MS = 5000;
    private static final long RECITE_LIVENESS_BUTTON_DELAY_MS = 5000;
    public static final long VIDEO_RECORDING_SIZE_CHECK_INTERVAL_MS = 1000;
    private Job availableStorageCalculationJob;
    private final CaptureTracker captureTracker;
    private Job challengeProcessorJob;
    private final MutableLiveData<LivenessChallengeViewModel> challengeProviderLiveData;
    private final MutableLiveData<Boolean> challengesCompletedLiveData;
    private final DispatchersProvider dispatchersProvider;
    private Job faceDetectionJob;
    private final MutableLiveData<FaceDetectionResult> faceDetectionResultLiveData;
    private Job faceDetectionTimeoutJob;
    private final FaceDetector faceDetector;
    private final MutableLiveData<Boolean> faceOutTimeoutLiveData;
    private final FaceProcessingUseCase faceProcessingUseCase;
    private Job faceTrackingJob;
    private final IdentityInteractor identityInteractor;
    private final MutableLiveData<Boolean> livenessControlButtonLiveData;
    private final MutableLiveData<Boolean> livenessControlButtonWithDelayLiveData;
    private final LivenessInteractor livenessInteractor;
    private long livenessPreviousChallengeCompleted;
    private final LivenessTracker livenessTracker;
    private Job movementChallengeTimeoutJob;
    private final OnfidoConfig onfidoConfig;
    private final SchedulersProvider schedulersProvider;
    private final MutableLiveData<Boolean> storageThresholdReachedLiveData;
    private final TimeProvider timeProvider;

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$observeFaceOut$1", f = "LivenessCaptureViewModel.kt", i = {}, l = {200}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$observeFaceOut$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LivenessCaptureViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Completable completableObserveFaceOut$onfido_capture_sdk_core_release = LivenessCaptureViewModel.this.faceProcessingUseCase.observeFaceOut$onfido_capture_sdk_core_release();
                this.label = 1;
                if (RxAwaitKt.await(completableObserveFaceOut$onfido_capture_sdk_core_release, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            LivenessCaptureViewModel.this.trackVideoCaptureTimeout$onfido_capture_sdk_core_release();
            LivenessCaptureViewModel.this.getFaceOutTimeoutLiveData$onfido_capture_sdk_core_release().postValue(Boxing.boxBoolean(true));
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startFaceDetection$1", f = "LivenessCaptureViewModel.kt", i = {}, l = {152}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startFaceDetection$1, reason: invalid class name and case insensitive filesystem */
    static final class C06731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult;"}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startFaceDetection$1$1", f = "LivenessCaptureViewModel.kt", i = {}, l = {146}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startFaceDetection$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01551 extends SuspendLambda implements Function2<FlowCollector<? super FaceDetectionResult>, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ LivenessCaptureViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01551(LivenessCaptureViewModel livenessCaptureViewModel, Continuation<? super C01551> continuation) {
                super(2, continuation);
                this.this$0 = livenessCaptureViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01551(this.this$0, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    long preRecordingInstructionsReadingTimeMilisseconds = this.this$0.livenessInteractor.getPreRecordingInstructionsReadingTimeMilisseconds();
                    this.label = 1;
                    if (DelayKt.delay(preRecordingInstructionsReadingTimeMilisseconds, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(FlowCollector<? super FaceDetectionResult> flowCollector, Continuation<? super Unit> continuation) {
                return ((C01551) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/onfido/android/sdk/capture/detector/face/FaceDetectionResult;", "it", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
        @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startFaceDetection$1$2", f = "LivenessCaptureViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startFaceDetection$1$2, reason: invalid class name */
        static final class AnonymousClass2 extends SuspendLambda implements Function3<FlowCollector<? super FaceDetectionResult>, Throwable, Continuation<? super Unit>, Object> {
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ LivenessCaptureViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(LivenessCaptureViewModel livenessCaptureViewModel, Continuation<? super AnonymousClass2> continuation) {
                super(3, continuation);
                this.this$0 = livenessCaptureViewModel;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                Throwable th = (Throwable) this.L$0;
                Timber.INSTANCE.i(th, "Error on getting the face detection results: " + th.getMessage(), new Object[0]);
                this.this$0.getLivenessControlButtonLiveData$onfido_capture_sdk_core_release().postValue(Boxing.boxBoolean(true));
                return Unit.INSTANCE;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(FlowCollector<? super FaceDetectionResult> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, continuation);
                anonymousClass2.L$0 = th;
                return anonymousClass2.invokeSuspend(Unit.INSTANCE);
            }
        }

        C06731(Continuation<? super C06731> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LivenessCaptureViewModel.this.new C06731(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flowable<FaceDetectionResult> flowableSample = LivenessCaptureViewModel.this.faceDetector.observeFaceTracking().sample(LivenessCaptureViewModel.this.isManualCapture$onfido_capture_sdk_core_release() ? 0L : 200L, TimeUnit.MILLISECONDS, LivenessCaptureViewModel.this.schedulersProvider.getTimer());
                Intrinsics.checkNotNullExpressionValue(flowableSample, "sample(...)");
                Flow flowM7628catch = FlowKt.m7628catch(FlowKt.onStart(ReactiveFlowKt.asFlow(flowableSample), new C01551(LivenessCaptureViewModel.this, null)), new AnonymousClass2(LivenessCaptureViewModel.this, null));
                final LivenessCaptureViewModel livenessCaptureViewModel = LivenessCaptureViewModel.this;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel.startFaceDetection.1.3
                    public final Object emit(FaceDetectionResult faceDetectionResult, Continuation<? super Unit> continuation) {
                        livenessCaptureViewModel.getFaceDetectionResultLiveData$onfido_capture_sdk_core_release().postValue(faceDetectionResult);
                        return Unit.INSTANCE;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((FaceDetectionResult) obj2, (Continuation<? super Unit>) continuation);
                    }
                };
                this.label = 1;
                if (flowM7628catch.collect(flowCollector, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06731) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startFaceDetection$2", f = "LivenessCaptureViewModel.kt", i = {}, l = {156}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startFaceDetection$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LivenessCaptureViewModel.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                long j = LivenessCaptureViewModel.this.isManualCapture$onfido_capture_sdk_core_release() ? 0L : 5000L;
                this.label = 1;
                if (DelayKt.delay(j, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            LivenessCaptureViewModel.this.getLivenessControlButtonLiveData$onfido_capture_sdk_core_release().postValue(Boxing.boxBoolean(true));
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startMovementChallengeTimeout$1", f = "LivenessCaptureViewModel.kt", i = {}, l = {234}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.onfido.android.sdk.capture.ui.camera.liveness.capture.LivenessCaptureViewModel$startMovementChallengeTimeout$1, reason: invalid class name and case insensitive filesystem */
    static final class C06741 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06741(Continuation<? super C06741> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LivenessCaptureViewModel.this.new C06741(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    long movementTimeoutValueMs$onfido_capture_sdk_core_release = LivenessCaptureViewModel.this.getMovementTimeoutValueMs$onfido_capture_sdk_core_release();
                    this.label = 1;
                    if (DelayKt.delay(movementTimeoutValueMs$onfido_capture_sdk_core_release, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                LivenessCaptureViewModel.this.getLivenessControlButtonLiveData$onfido_capture_sdk_core_release().postValue(Boxing.boxBoolean(true));
            } catch (Throwable th) {
                Timber.INSTANCE.e(th, "Error on face tracking timeout subscription: " + th.getMessage(), new Object[0]);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06741) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Inject
    public LivenessCaptureViewModel(FaceProcessingUseCase faceProcessingUseCase, FaceDetector faceDetector, IdentityInteractor identityInteractor, LivenessInteractor livenessInteractor, LivenessTracker livenessTracker, CaptureTracker captureTracker, TimeProvider timeProvider, SchedulersProvider schedulersProvider, OnfidoConfig onfidoConfig, DispatchersProvider dispatchersProvider) {
        Intrinsics.checkNotNullParameter(faceProcessingUseCase, "faceProcessingUseCase");
        Intrinsics.checkNotNullParameter(faceDetector, "faceDetector");
        Intrinsics.checkNotNullParameter(identityInteractor, "identityInteractor");
        Intrinsics.checkNotNullParameter(livenessInteractor, "livenessInteractor");
        Intrinsics.checkNotNullParameter(livenessTracker, "livenessTracker");
        Intrinsics.checkNotNullParameter(captureTracker, "captureTracker");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        Intrinsics.checkNotNullParameter(schedulersProvider, "schedulersProvider");
        Intrinsics.checkNotNullParameter(onfidoConfig, "onfidoConfig");
        Intrinsics.checkNotNullParameter(dispatchersProvider, "dispatchersProvider");
        this.faceProcessingUseCase = faceProcessingUseCase;
        this.faceDetector = faceDetector;
        this.identityInteractor = identityInteractor;
        this.livenessInteractor = livenessInteractor;
        this.livenessTracker = livenessTracker;
        this.captureTracker = captureTracker;
        this.timeProvider = timeProvider;
        this.schedulersProvider = schedulersProvider;
        this.onfidoConfig = onfidoConfig;
        this.dispatchersProvider = dispatchersProvider;
        this.livenessControlButtonLiveData = new MutableLiveData<>();
        this.livenessControlButtonWithDelayLiveData = new MutableLiveData<>();
        this.faceOutTimeoutLiveData = new MutableLiveData<>();
        this.storageThresholdReachedLiveData = new MutableLiveData<>();
        this.challengeProviderLiveData = new MutableLiveData<>();
        this.challengesCompletedLiveData = new MutableLiveData<>();
        this.faceDetectionResultLiveData = new MutableLiveData<>();
    }

    private final void observeFaceOut() {
        this.faceTrackingJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.mo5607getDefault(), null, new AnonymousClass1(null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processCurrentChallenge(LivenessChallengeViewModel currentChallengeViewModel, LivenessChallengesViewModel livenessChallengesViewModel) {
        int index = currentChallengeViewModel.getIndex();
        if (index == 0) {
            this.livenessInteractor.initializeLivenessVideoTimestamp();
        } else {
            pushPerformedChallenge$onfido_capture_sdk_core_release(livenessChallengesViewModel.getChallenges().get(index - 1));
        }
        this.challengeProviderLiveData.postValue(currentChallengeViewModel);
        trackChallenge$onfido_capture_sdk_core_release(currentChallengeViewModel.getIndex(), currentChallengeViewModel.getChallenge().getType());
    }

    private final void startFaceDetection() {
        this.faceDetectionJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.mo5607getDefault(), null, new C06731(null), 2, null);
        this.faceDetectionTimeoutJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.mo5607getDefault(), null, new AnonymousClass2(null), 2, null);
    }

    private final void startMovementChallengeTimeout() {
        Job job = this.movementChallengeTimeoutJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.movementChallengeTimeoutJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.getMain(), null, new C06741(null), 2, null);
    }

    private final void stopMovementChallengeTimeout() {
        Job job = this.movementChallengeTimeoutJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    private final void trackVideoButtonClicked() {
        long currentTimestamp = this.timeProvider.getCurrentTimestamp() - this.livenessInteractor.getLivenessStartTimestamp();
        long j = this.livenessPreviousChallengeCompleted;
        long j2 = currentTimestamp - j;
        this.livenessPreviousChallengeCompleted = j + j2;
        if (this.livenessInteractor.getLivenessPerformedChallenges().getChallenges().size() >= 1) {
            this.captureTracker.trackVideoFinishButtonClicked$onfido_capture_sdk_core_release(j2);
        } else {
            this.captureTracker.trackVideoNextButtonClicked$onfido_capture_sdk_core_release(j2);
        }
    }

    public final void cancelFaceDetectionJobs$onfido_capture_sdk_core_release() {
        Job job;
        Job job2;
        Job job3 = this.faceDetectionJob;
        if (job3 != null && job3.isActive() && (job2 = this.faceDetectionJob) != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        Job job4 = this.faceDetectionTimeoutJob;
        if (job4 == null || !job4.isActive() || (job = this.faceDetectionTimeoutJob) == null) {
            return;
        }
        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
    }

    public final MutableLiveData<LivenessChallengeViewModel> getChallengeProviderLiveData$onfido_capture_sdk_core_release() {
        return this.challengeProviderLiveData;
    }

    public final MutableLiveData<Boolean> getChallengesCompletedLiveData$onfido_capture_sdk_core_release() {
        return this.challengesCompletedLiveData;
    }

    public final MutableLiveData<FaceDetectionResult> getFaceDetectionResultLiveData$onfido_capture_sdk_core_release() {
        return this.faceDetectionResultLiveData;
    }

    public final MutableLiveData<Boolean> getFaceOutTimeoutLiveData$onfido_capture_sdk_core_release() {
        return this.faceOutTimeoutLiveData;
    }

    public final long getLivenessButtonDisplayDelay$onfido_capture_sdk_core_release() {
        return isManualCapture$onfido_capture_sdk_core_release() ? 0L : 5000L;
    }

    public final MutableLiveData<Boolean> getLivenessControlButtonLiveData$onfido_capture_sdk_core_release() {
        return this.livenessControlButtonLiveData;
    }

    public final MutableLiveData<Boolean> getLivenessControlButtonWithDelayLiveData$onfido_capture_sdk_core_release() {
        return this.livenessControlButtonWithDelayLiveData;
    }

    public final long getMovementTimeoutValueMs$onfido_capture_sdk_core_release() {
        return (!this.identityInteractor.isDeviceHighEnd() || isManualCapture$onfido_capture_sdk_core_release()) ? 0L : 5000L;
    }

    public final MutableLiveData<Boolean> getStorageThresholdReachedLiveData$onfido_capture_sdk_core_release() {
        return this.storageThresholdReachedLiveData;
    }

    public final LivenessPerformedChallenges getUploadChallengesList$onfido_capture_sdk_core_release() {
        return this.livenessInteractor.getLivenessPerformedChallenges();
    }

    public final boolean isManualCapture$onfido_capture_sdk_core_release() {
        return this.onfidoConfig.getManualLivenessCapture();
    }

    public final void issueNextChallenge$onfido_capture_sdk_core_release() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.getMain(), null, new LivenessCaptureViewModel$issueNextChallenge$1(this, null), 2, null);
    }

    public final void onAutoLivenessRecordingStart$onfido_capture_sdk_core_release() {
        this.captureTracker.trackVideoAutoRecordingStarted$onfido_capture_sdk_core_release();
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        stopFaceTracking$onfido_capture_sdk_core_release();
    }

    public final void onManualLivenessNextClick$onfido_capture_sdk_core_release() {
        trackVideoButtonClicked();
    }

    public final void onManualLivenessRecordingStart$onfido_capture_sdk_core_release() {
        this.captureTracker.trackVideoManualRecordingStarted$onfido_capture_sdk_core_release();
    }

    public final void onNextChallenge$onfido_capture_sdk_core_release(LivenessChallenge challenge) {
        Intrinsics.checkNotNullParameter(challenge, "challenge");
        if (challenge instanceof MovementLivenessChallenge) {
            this.livenessControlButtonLiveData.postValue(Boolean.FALSE);
            startMovementChallengeTimeout();
        } else if (challenge instanceof ReciteLivenessChallenge) {
            stopMovementChallengeTimeout();
            this.livenessControlButtonLiveData.postValue(Boolean.FALSE);
            this.livenessControlButtonWithDelayLiveData.postValue(Boolean.TRUE);
        }
    }

    public final void onNextFaceDetectionFrame$onfido_capture_sdk_core_release(FaceDetectionFrame frameData) {
        Intrinsics.checkNotNullParameter(frameData, "frameData");
        this.faceDetector.getFaceDetectionSubject().onNext(frameData);
    }

    public final void onRecordingStarted$onfido_capture_sdk_core_release(boolean isStartedManually) {
        VideoCaptureConfig videoCaptureConfig = new VideoCaptureConfig(null, false, 0, 0, 0, 0, 0, 0L, 0L, FrameMetricsAggregator.EVERY_DURATION, null);
        if (this.livenessInteractor.hasEnoughAvailableStorageSpace()) {
            this.availableStorageCalculationJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.getMain(), null, new LivenessCaptureViewModel$onRecordingStarted$1(this, videoCaptureConfig, null), 2, null);
        }
        if (isStartedManually) {
            return;
        }
        observeFaceOut();
    }

    public final void pushPerformedChallenge$onfido_capture_sdk_core_release(LivenessChallenge livenessChallenge) {
        Intrinsics.checkNotNullParameter(livenessChallenge, "livenessChallenge");
        this.livenessInteractor.pushPerformedChallenge(livenessChallenge);
    }

    public final void reset$onfido_capture_sdk_core_release() {
        Job job = this.challengeProcessorJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        stopFaceTracking$onfido_capture_sdk_core_release();
        stopMovementChallengeTimeout();
        cancelFaceDetectionJobs$onfido_capture_sdk_core_release();
    }

    public final void startLivenessProcessing$onfido_capture_sdk_core_release(LivenessChallengesViewModel challengesViewModel) {
        Intrinsics.checkNotNullParameter(challengesViewModel, "challengesViewModel");
        this.challengeProcessorJob = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), this.dispatchersProvider.mo5607getDefault(), null, new LivenessCaptureViewModel$startLivenessProcessing$1(this, FlowKt.asFlow(RangesKt.until(0, challengesViewModel.getChallenges().size() + 1)), challengesViewModel, null), 2, null);
        startFaceDetection();
    }

    public final void stopFaceTracking$onfido_capture_sdk_core_release() {
        Job job = this.faceTrackingJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        Job job2 = this.availableStorageCalculationJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        this.faceDetector.close();
    }

    public final void trackCapture$onfido_capture_sdk_core_release(boolean isPortrait) {
        this.livenessTracker.trackFaceCapture$onfido_capture_sdk_core_release(false, isPortrait, CaptureType.VIDEO, null, 0, 0);
    }

    public final void trackCaptureError$onfido_capture_sdk_core_release() {
    }

    public final void trackChallenge$onfido_capture_sdk_core_release(int challengeIndex, LivenessChallengeType challengeType) {
        Intrinsics.checkNotNullParameter(challengeType, "challengeType");
        if (challengeIndex == 0) {
            this.captureTracker.trackVideoCaptureFirstChallenge$onfido_capture_sdk_core_release(challengeType);
        } else if (challengeIndex == 1) {
            this.captureTracker.trackVideoCaptureSecondChallenge$onfido_capture_sdk_core_release(challengeType);
        }
        String strName = challengeType.name();
        Locale US = Locale.US;
        Intrinsics.checkNotNullExpressionValue(US, "US");
        String lowerCase = strName.toLowerCase(US);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        this.livenessTracker.trackLivenessChallenge$onfido_capture_sdk_core_release(challengeIndex + 1, lowerCase);
    }

    public final void trackVideoCaptureTimeout$onfido_capture_sdk_core_release() {
        this.captureTracker.trackVideoCaptureTimeout$onfido_capture_sdk_core_release();
    }

    public final void trackVideoTimeoutRetryButtonClicked$onfido_capture_sdk_core_release() {
        this.captureTracker.trackVideoTimeoutRetryButtonClicked$onfido_capture_sdk_core_release();
    }
}
