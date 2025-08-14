package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2;

import android.hardware.camera2.CameraCharacteristics;
import android.view.Surface;
import androidx.camera.viewfinder.CameraViewfinder;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0007H&JN\u0010\t\u001a\u00020\u00072!\u0010\n\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u00070\u000b2!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00070\u000bH&J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0007H&J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H&JI\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00192!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u00070\u000bH&J\b\u0010\u001a\u001a\u00020\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u001b"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/Camera2Wrapper;", "", "characteristics", "Landroid/hardware/camera2/CameraCharacteristics;", "getCharacteristics", "()Landroid/hardware/camera2/CameraCharacteristics;", "cleanup", "", "closeCamera", "findSelfieCamera", "onSuccess", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "onError", "", "error", "resetCaptureSession", "recorderSurface", "Landroid/view/Surface;", "setPreviewMode", "setRecordingMode", ViewProps.START, "cameraViewfinder", "Landroidx/camera/viewfinder/CameraViewfinder;", "Lkotlin/Function0;", "stopPreview", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface Camera2Wrapper {
    void cleanup();

    void closeCamera();

    void findSelfieCamera(Function1<? super CameraCharacteristics, Unit> onSuccess, Function1<? super Throwable, Unit> onError);

    CameraCharacteristics getCharacteristics();

    void resetCaptureSession(Surface recorderSurface);

    void setPreviewMode();

    void setRecordingMode(Surface recorderSurface);

    void start(CameraViewfinder cameraViewfinder, Surface recorderSurface, Function0<Unit> onSuccess, Function1<? super Throwable, Unit> onError);

    void stopPreview();
}
