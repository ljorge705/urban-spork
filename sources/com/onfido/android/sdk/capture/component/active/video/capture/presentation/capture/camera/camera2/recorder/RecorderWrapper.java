package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder;

import android.hardware.camera2.CameraCharacteristics;
import android.view.Surface;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\u0007H&J\u0018\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J9\u0010\u000e\u001a\u00020\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00102!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0013¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00070\u0012H&J\b\u0010\u0017\u001a\u00020\u0007H&¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/RecorderWrapper;", "", "getMediaRecorderSurface", "Landroid/view/Surface;", "getVideoFilePath", "", "initialize", "", "videoCaptureConfig", "Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;", "cameraCharacteristics", "Landroid/hardware/camera2/CameraCharacteristics;", "releaseRecorder", "reset", "startRecording", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "error", "stopRecording", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface RecorderWrapper {
    Surface getMediaRecorderSurface();

    String getVideoFilePath();

    void initialize(VideoCaptureConfig videoCaptureConfig, CameraCharacteristics cameraCharacteristics);

    void releaseRecorder();

    void reset(VideoCaptureConfig videoCaptureConfig, CameraCharacteristics cameraCharacteristics);

    void startRecording(Function0<Unit> onSuccess, Function1<? super Throwable, Unit> onError);

    void stopRecording();
}
