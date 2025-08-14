package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera;

import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc;
import com.onfido.android.sdk.capture.internal.camera.OnfidoCamera;
import com.onfido.android.sdk.capture.internal.camera.camerax.CameraX;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.RxExtensionsKt;
import com.onfido.dagger.assisted.Assisted;
import com.onfido.dagger.assisted.AssistedFactory;
import com.onfido.dagger.assisted.AssistedInject;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000  2\u00020\u0001:\u0002 !B\u001b\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0016J\b\u0010\u0012\u001a\u00020\u000eH\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0016J9\u0010\u0014\u001a\u00020\u000e2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00162!\u0010\u0017\u001a\u001d\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u000e0\u0018H\u0016JG\u0010\u001d\u001a\u00020\u000e2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00162\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00162!\u0010\u0017\u001a\u001d\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u000e0\u0018H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraXController;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraController;", "cameraX", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraX;", "faceDetector", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvc;", "(Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraX;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvc;)V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "filePath", "", "recorder", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoRecorder;", "cancelRecording", "", "finishRecording", "getVideoFilePath", "onDestroy", "onStop", "reset", ViewProps.START, "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "error", "startRecording", "onStarted", "onFinished", "Companion", "Factory", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionCameraXController implements MotionCameraController {
    public static final String ERROR_MESSAGE_CAMERA_NOT_AVAILABLE = "CameraX: Camera Not Available";
    private final CameraX cameraX;
    private final CompositeDisposable compositeDisposable;
    private final FaceDetectorAvc faceDetector;
    private String filePath;
    private OnfidoCamera.VideoRecorder recorder;

    @AssistedFactory
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraXController$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraXController;", "cameraX", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraX;", "faceDetector", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvc;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        MotionCameraXController create(CameraX cameraX, FaceDetectorAvc faceDetector);
    }

    @AssistedInject
    public MotionCameraXController(@Assisted CameraX cameraX, @Assisted FaceDetectorAvc faceDetector) {
        Intrinsics.checkNotNullParameter(cameraX, "cameraX");
        Intrinsics.checkNotNullParameter(faceDetector, "faceDetector");
        this.cameraX = cameraX;
        this.faceDetector = faceDetector;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public void cancelRecording() {
        OnfidoCamera.VideoRecorder videoRecorder = this.recorder;
        if (videoRecorder != null) {
            videoRecorder.cancel();
        }
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public void finishRecording() {
        OnfidoCamera.VideoRecorder videoRecorder = this.recorder;
        if (videoRecorder != null) {
            videoRecorder.finish();
        }
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public String getVideoFilePath() {
        String str = this.filePath;
        Intrinsics.checkNotNull(str);
        return str;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public void onDestroy() {
        this.recorder = null;
        this.faceDetector.close();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public void onStop() {
        this.compositeDisposable.clear();
        this.cameraX.stop();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public void reset() {
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public void start(final Function0<Unit> onSuccess, final Function1<? super Throwable, Unit> onError) {
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onError, "onError");
        this.cameraX.start(OnfidoCamera.CameraFacing.FRONT, new Function1<OnfidoCamera.CameraStatus, Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraXController.start.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OnfidoCamera.CameraStatus cameraStatus) {
                invoke2(cameraStatus);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(OnfidoCamera.CameraStatus cameraStatus) {
                Function1<Throwable, Unit> function1;
                Throwable illegalStateException;
                Intrinsics.checkNotNullParameter(cameraStatus, "cameraStatus");
                if (Intrinsics.areEqual(cameraStatus, OnfidoCamera.CameraStatus.Started.INSTANCE)) {
                    onSuccess.invoke();
                    this.faceDetector.initialize();
                    CompositeDisposable compositeDisposable = this.compositeDisposable;
                    Observable<? extends Object> observableObserveFrame = this.cameraX.observeFrame();
                    final MotionCameraXController motionCameraXController = this;
                    Disposable disposableSubscribe = observableObserveFrame.subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraXController.start.1.1
                        @Override // io.reactivex.rxjava3.functions.Consumer
                        public final void accept(Object motionImage) {
                            Intrinsics.checkNotNullParameter(motionImage, "motionImage");
                            motionCameraXController.faceDetector.processFrame((MotionImage) motionImage);
                        }
                    });
                    Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
                    RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
                } else {
                    if (cameraStatus instanceof OnfidoCamera.CameraStatus.Failed) {
                        function1 = onError;
                        illegalStateException = ((OnfidoCamera.CameraStatus.Failed) cameraStatus).getError();
                    } else if (!Intrinsics.areEqual(cameraStatus, OnfidoCamera.CameraStatus.NotFound.INSTANCE) && Intrinsics.areEqual(cameraStatus, OnfidoCamera.CameraStatus.NotAvailable.INSTANCE)) {
                        function1 = onError;
                        illegalStateException = new IllegalStateException(MotionCameraXController.ERROR_MESSAGE_CAMERA_NOT_AVAILABLE);
                    }
                    function1.invoke(illegalStateException);
                }
                Timber.INSTANCE.d(cameraStatus.toString(), new Object[0]);
            }
        });
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public void startRecording(final Function0<Unit> onStarted, final Function0<Unit> onFinished, final Function1<? super Throwable, Unit> onError) {
        Intrinsics.checkNotNullParameter(onStarted, "onStarted");
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        Intrinsics.checkNotNullParameter(onError, "onError");
        this.recorder = this.cameraX.takeVideo(new Function1<OnfidoCamera.VideoCaptureEvent, Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraXController.startRecording.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OnfidoCamera.VideoCaptureEvent videoCaptureEvent) {
                invoke2(videoCaptureEvent);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(OnfidoCamera.VideoCaptureEvent event) {
                Function0<Unit> function0;
                Intrinsics.checkNotNullParameter(event, "event");
                if (Intrinsics.areEqual(event, OnfidoCamera.VideoCaptureEvent.Started.INSTANCE)) {
                    function0 = onStarted;
                } else {
                    if (!(event instanceof OnfidoCamera.VideoCaptureEvent.Recorded)) {
                        if (!Intrinsics.areEqual(event, OnfidoCamera.VideoCaptureEvent.Canceled.INSTANCE) && !Intrinsics.areEqual(event, OnfidoCamera.VideoCaptureEvent.Timeout.INSTANCE) && (event instanceof OnfidoCamera.VideoCaptureEvent.Error)) {
                            OnfidoCamera.VideoCaptureEvent.Error error = (OnfidoCamera.VideoCaptureEvent.Error) event;
                            Timber.INSTANCE.e(error.getError());
                            onError.invoke(error.getError());
                        }
                        Timber.INSTANCE.d(event.toString(), new Object[0]);
                    }
                    Timber.INSTANCE.d("Recording stopped. Output file: " + this.filePath, new Object[0]);
                    this.filePath = ((OnfidoCamera.VideoCaptureEvent.Recorded) event).getFilePath();
                    function0 = onFinished;
                }
                function0.invoke();
                Timber.INSTANCE.d(event.toString(), new Object[0]);
            }
        });
    }
}
