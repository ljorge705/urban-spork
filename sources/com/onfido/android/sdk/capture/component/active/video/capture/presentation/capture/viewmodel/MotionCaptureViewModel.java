package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorResult;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.model.FaceAlignmentFeedback;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b`\u0018\u00002\u00020\u0001:\u0001\u001fJ\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0010H&J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0016H&J\b\u0010\u0017\u001a\u00020\u0010H&J\b\u0010\u0018\u001a\u00020\u0010H&J\b\u0010\u0019\u001a\u00020\u0010H&J\b\u0010\u001a\u001a\u00020\u0010H&J\b\u0010\u001b\u001a\u00020\u0010H&J\b\u0010\u001c\u001a\u00020\u0010H&J\b\u0010\u001d\u001a\u00020\u0010H&J\b\u0010\u001e\u001a\u00020\u0010H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0006R\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0006R\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0006¨\u0006 "}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel;", "", "detectedFace", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorResult$FaceDetected;", "getDetectedFace", "()Lio/reactivex/rxjava3/core/Observable;", "faceAlignmentFeedback", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/model/FaceAlignmentFeedback;", "getFaceAlignmentFeedback", "faceAlignmentFeedbackAccessibility", "getFaceAlignmentFeedbackAccessibility", "viewState", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState;", "getViewState", "completeFlow", "", "finishRecording", "initialize", "ovalRect", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "isCompletedState", "", "onAudioConflictAlertDialogDismissed", "onDestroy", "onMicIsNotAvailableAlertDialogDismissed", "onRecordingIsTooShortAlertDialogDismissed", "onRecordingTimeoutAlertDialogDismissed", "onStop", "startRecording", "trackScreenAnalyticsEvent", "ViewState", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface MotionCaptureViewModel {

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\b\u0002\u0003\u0004\u0005\u0006\u0007\b\t\u0082\u0001\b\n\u000b\f\r\u000e\u000f\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState;", "", "Completed", "DelayFinishRecording", "DelayStartRecording", "Error", "FaceAlignment", "FaceNotPresent", "RecordingFinished", "RecordingStarted", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Completed;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$DelayFinishRecording;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$DelayStartRecording;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$FaceAlignment;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$FaceNotPresent;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$RecordingFinished;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$RecordingStarted;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface ViewState {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Completed;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class Completed implements ViewState {
            public static final Completed INSTANCE = new Completed();

            private Completed() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$DelayFinishRecording;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class DelayFinishRecording implements ViewState {
            public static final DelayFinishRecording INSTANCE = new DelayFinishRecording();

            private DelayFinishRecording() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$DelayStartRecording;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class DelayStartRecording implements ViewState {
            public static final DelayStartRecording INSTANCE = new DelayStartRecording();

            private DelayStartRecording() {
            }
        }

        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0006\u0002\u0003\u0004\u0005\u0006\u0007\u0082\u0001\u0006\b\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState;", "AudioConflict", "FaceAlignmentTimeout", "MicIsNotAvailable", "RecordingFailed", "RecordingIsTooShort", "RecordingTimeout", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error$AudioConflict;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error$FaceAlignmentTimeout;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error$MicIsNotAvailable;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error$RecordingFailed;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error$RecordingIsTooShort;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error$RecordingTimeout;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public interface Error extends ViewState {

            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error$AudioConflict;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class AudioConflict implements Error {
                public static final AudioConflict INSTANCE = new AudioConflict();

                private AudioConflict() {
                }
            }

            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error$FaceAlignmentTimeout;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class FaceAlignmentTimeout implements Error {
                public static final FaceAlignmentTimeout INSTANCE = new FaceAlignmentTimeout();

                private FaceAlignmentTimeout() {
                }
            }

            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error$MicIsNotAvailable;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class MicIsNotAvailable implements Error {
                public static final MicIsNotAvailable INSTANCE = new MicIsNotAvailable();

                private MicIsNotAvailable() {
                }
            }

            @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error$RecordingFailed;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error;", "error", "", "(Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final /* data */ class RecordingFailed implements Error {
                private final Throwable error;

                public RecordingFailed(Throwable error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    this.error = error;
                }

                public static /* synthetic */ RecordingFailed copy$default(RecordingFailed recordingFailed, Throwable th, int i, Object obj) {
                    if ((i & 1) != 0) {
                        th = recordingFailed.error;
                    }
                    return recordingFailed.copy(th);
                }

                /* renamed from: component1, reason: from getter */
                public final Throwable getError() {
                    return this.error;
                }

                public final RecordingFailed copy(Throwable error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    return new RecordingFailed(error);
                }

                public boolean equals(Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof RecordingFailed) && Intrinsics.areEqual(this.error, ((RecordingFailed) other).error);
                }

                public final Throwable getError() {
                    return this.error;
                }

                public int hashCode() {
                    return this.error.hashCode();
                }

                public String toString() {
                    return "RecordingFailed(error=" + this.error + ')';
                }
            }

            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error$RecordingIsTooShort;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class RecordingIsTooShort implements Error {
                public static final RecordingIsTooShort INSTANCE = new RecordingIsTooShort();

                private RecordingIsTooShort() {
                }
            }

            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error$RecordingTimeout;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$Error;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
            public static final class RecordingTimeout implements Error {
                public static final RecordingTimeout INSTANCE = new RecordingTimeout();

                private RecordingTimeout() {
                }
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$FaceAlignment;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class FaceAlignment implements ViewState {
            public static final FaceAlignment INSTANCE = new FaceAlignment();

            private FaceAlignment() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$FaceNotPresent;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class FaceNotPresent implements ViewState {
            public static final FaceNotPresent INSTANCE = new FaceNotPresent();

            private FaceNotPresent() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$RecordingFinished;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class RecordingFinished implements ViewState {
            public static final RecordingFinished INSTANCE = new RecordingFinished();

            private RecordingFinished() {
            }
        }

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState$RecordingStarted;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/viewmodel/MotionCaptureViewModel$ViewState;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public static final class RecordingStarted implements ViewState {
            public static final RecordingStarted INSTANCE = new RecordingStarted();

            private RecordingStarted() {
            }
        }
    }

    void completeFlow();

    void finishRecording();

    Observable<FaceDetectorResult.FaceDetected> getDetectedFace();

    Observable<FaceAlignmentFeedback> getFaceAlignmentFeedback();

    Observable<FaceAlignmentFeedback> getFaceAlignmentFeedbackAccessibility();

    Observable<ViewState> getViewState();

    void initialize(OnfidoRectF ovalRect);

    boolean isCompletedState();

    void onAudioConflictAlertDialogDismissed();

    void onDestroy();

    void onMicIsNotAvailableAlertDialogDismissed();

    void onRecordingIsTooShortAlertDialogDismissed();

    void onRecordingTimeoutAlertDialogDismissed();

    void onStop();

    void startRecording();

    void trackScreenAnalyticsEvent();
}
