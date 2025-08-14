package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2;

import android.hardware.camera2.CameraCharacteristics;
import android.view.Surface;
import androidx.camera.viewfinder.CameraViewfinder;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionImage;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapper;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface.IsPersistentSurfaceSupportedUseCase;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.facedetector.FaceDetectorAvc;
import com.onfido.android.sdk.capture.internal.camera.camerax.FrameSampler;
import com.onfido.android.sdk.capture.internal.camera.camerax.PreviewConfig;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
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

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001:\u0001,BU\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0002\u0010\u0013J\b\u0010\u0019\u001a\u00020\u0018H\u0016J\b\u0010\u001a\u001a\u00020\u0018H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0018H\u0016J\b\u0010\u001e\u001a\u00020\u0018H\u0016J\b\u0010\u001f\u001a\u00020\u0018H\u0016J9\u0010 \u001a\u00020\u00182\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172!\u0010\"\u001a\u001d\u0012\u0013\u0012\u00110$¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\u00180#H\u0016JG\u0010(\u001a\u00020\u00182\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172!\u0010\"\u001a\u001d\u0012\u0013\u0012\u00110$¢\u0006\f\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0004\u0012\u00020\u00180#H\u0016J\b\u0010+\u001a\u00020\u0018H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/MotionCamera2Controller;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionCameraController;", "cameraViewfinder", "Landroidx/camera/viewfinder/CameraViewfinder;", "videoCaptureConfig", "Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;", "previewConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/PreviewConfig;", "faceDetector", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvc;", "camera2Wrapper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/Camera2Wrapper;", "recorderWrapper", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/RecorderWrapper;", "isPersistentSurfaceSupportedUseCase", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/persistentsurface/IsPersistentSurfaceSupportedUseCase;", "frameSampler", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/FrameSampler;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionImage;", "(Landroidx/camera/viewfinder/CameraViewfinder;Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;Lcom/onfido/android/sdk/capture/internal/camera/camerax/PreviewConfig;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvc;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/Camera2Wrapper;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/RecorderWrapper;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/persistentsurface/IsPersistentSurfaceSupportedUseCase;Lcom/onfido/android/sdk/capture/internal/camera/camerax/FrameSampler;)V", "compositeDisposable", "Lio/reactivex/rxjava3/disposables/CompositeDisposable;", "onFinishedCallback", "Lkotlin/Function0;", "", "cancelRecording", "finishRecording", "getVideoFilePath", "", "onDestroy", "onStop", "reset", ViewProps.START, "onSuccess", "onError", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "error", "startRecording", "onStarted", "onFinished", "stopRecording", "Factory", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MotionCamera2Controller implements MotionCameraController {
    private final Camera2Wrapper camera2Wrapper;
    private final CameraViewfinder cameraViewfinder;
    private final CompositeDisposable compositeDisposable;
    private final FaceDetectorAvc faceDetector;
    private final FrameSampler<MotionImage> frameSampler;
    private final IsPersistentSurfaceSupportedUseCase isPersistentSurfaceSupportedUseCase;
    private Function0<Unit> onFinishedCallback;
    private final PreviewConfig previewConfig;
    private final RecorderWrapper recorderWrapper;
    private final VideoCaptureConfig videoCaptureConfig;

    @AssistedFactory
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/MotionCamera2Controller$Factory;", "", "create", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/MotionCamera2Controller;", "cameraViewfinder", "Landroidx/camera/viewfinder/CameraViewfinder;", "videoCaptureConfig", "Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;", "previewConfig", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/PreviewConfig;", "faceDetector", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/facedetector/FaceDetectorAvc;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface Factory {
        MotionCamera2Controller create(CameraViewfinder cameraViewfinder, VideoCaptureConfig videoCaptureConfig, PreviewConfig previewConfig, FaceDetectorAvc faceDetector);
    }

    @AssistedInject
    public MotionCamera2Controller(@Assisted CameraViewfinder cameraViewfinder, @Assisted VideoCaptureConfig videoCaptureConfig, @Assisted PreviewConfig previewConfig, @Assisted FaceDetectorAvc faceDetector, Camera2Wrapper camera2Wrapper, RecorderWrapper recorderWrapper, IsPersistentSurfaceSupportedUseCase isPersistentSurfaceSupportedUseCase, FrameSampler<MotionImage> frameSampler) {
        Intrinsics.checkNotNullParameter(cameraViewfinder, "cameraViewfinder");
        Intrinsics.checkNotNullParameter(videoCaptureConfig, "videoCaptureConfig");
        Intrinsics.checkNotNullParameter(previewConfig, "previewConfig");
        Intrinsics.checkNotNullParameter(faceDetector, "faceDetector");
        Intrinsics.checkNotNullParameter(camera2Wrapper, "camera2Wrapper");
        Intrinsics.checkNotNullParameter(recorderWrapper, "recorderWrapper");
        Intrinsics.checkNotNullParameter(isPersistentSurfaceSupportedUseCase, "isPersistentSurfaceSupportedUseCase");
        Intrinsics.checkNotNullParameter(frameSampler, "frameSampler");
        this.cameraViewfinder = cameraViewfinder;
        this.videoCaptureConfig = videoCaptureConfig;
        this.previewConfig = previewConfig;
        this.faceDetector = faceDetector;
        this.camera2Wrapper = camera2Wrapper;
        this.recorderWrapper = recorderWrapper;
        this.isPersistentSurfaceSupportedUseCase = isPersistentSurfaceSupportedUseCase;
        this.frameSampler = frameSampler;
        this.compositeDisposable = new CompositeDisposable();
    }

    private final void stopRecording() {
        this.camera2Wrapper.stopPreview();
        this.recorderWrapper.stopRecording();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public void cancelRecording() {
        try {
            stopRecording();
        } catch (RuntimeException e) {
            Timber.INSTANCE.e(e);
        }
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public void finishRecording() {
        stopRecording();
        Function0<Unit> function0 = this.onFinishedCallback;
        if (function0 != null) {
            function0.invoke();
        }
        this.onFinishedCallback = null;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public String getVideoFilePath() {
        return this.recorderWrapper.getVideoFilePath();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public void onDestroy() {
        this.camera2Wrapper.cleanup();
        this.faceDetector.close();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public void onStop() {
        this.compositeDisposable.clear();
        this.frameSampler.close();
        this.camera2Wrapper.closeCamera();
        this.recorderWrapper.releaseRecorder();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public void reset() {
        this.recorderWrapper.reset(this.videoCaptureConfig, this.camera2Wrapper.getCharacteristics());
        if (this.isPersistentSurfaceSupportedUseCase.invoke()) {
            this.camera2Wrapper.setPreviewMode();
        } else {
            this.camera2Wrapper.resetCaptureSession(this.recorderWrapper.getMediaRecorderSurface());
        }
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public void start(final Function0<Unit> onSuccess, final Function1<? super Throwable, Unit> onError) {
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onError, "onError");
        this.camera2Wrapper.findSelfieCamera(new Function1<CameraCharacteristics, Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.MotionCamera2Controller.start.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CameraCharacteristics cameraCharacteristics) {
                invoke2(cameraCharacteristics);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CameraCharacteristics cameraCharacteristics) {
                Intrinsics.checkNotNullParameter(cameraCharacteristics, "cameraCharacteristics");
                MotionCamera2Controller.this.recorderWrapper.initialize(MotionCamera2Controller.this.videoCaptureConfig, cameraCharacteristics);
                Camera2Wrapper camera2Wrapper = MotionCamera2Controller.this.camera2Wrapper;
                CameraViewfinder cameraViewfinder = MotionCamera2Controller.this.cameraViewfinder;
                Surface mediaRecorderSurface = MotionCamera2Controller.this.recorderWrapper.getMediaRecorderSurface();
                final Function0<Unit> function0 = onSuccess;
                final MotionCamera2Controller motionCamera2Controller = MotionCamera2Controller.this;
                camera2Wrapper.start(cameraViewfinder, mediaRecorderSurface, new Function0<Unit>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.MotionCamera2Controller.start.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        function0.invoke();
                        motionCamera2Controller.faceDetector.initialize();
                        motionCamera2Controller.frameSampler.mo5541onPreviewAvailableHG0u8IE(motionCamera2Controller.cameraViewfinder, motionCamera2Controller.previewConfig.m5592getFrameSamplingPeriodUwyO8pc());
                        CompositeDisposable compositeDisposable = motionCamera2Controller.compositeDisposable;
                        Observable observableObserveFrame = motionCamera2Controller.frameSampler.observeFrame();
                        final FaceDetectorAvc faceDetectorAvc = motionCamera2Controller.faceDetector;
                        Disposable disposableSubscribe = observableObserveFrame.subscribe(new Consumer() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.MotionCamera2Controller.start.1.1.1
                            @Override // io.reactivex.rxjava3.functions.Consumer
                            public final void accept(MotionImage p0) {
                                Intrinsics.checkNotNullParameter(p0, "p0");
                                faceDetectorAvc.processFrame(p0);
                            }
                        });
                        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
                        RxExtensionsKt.plusAssign(compositeDisposable, disposableSubscribe);
                    }
                }, onError);
            }
        }, onError);
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.MotionCameraController
    public void startRecording(Function0<Unit> onStarted, Function0<Unit> onFinished, Function1<? super Throwable, Unit> onError) {
        Intrinsics.checkNotNullParameter(onStarted, "onStarted");
        Intrinsics.checkNotNullParameter(onFinished, "onFinished");
        Intrinsics.checkNotNullParameter(onError, "onError");
        this.camera2Wrapper.setRecordingMode(this.recorderWrapper.getMediaRecorderSurface());
        this.recorderWrapper.startRecording(onStarted, onError);
        this.onFinishedCallback = onFinished;
    }
}
