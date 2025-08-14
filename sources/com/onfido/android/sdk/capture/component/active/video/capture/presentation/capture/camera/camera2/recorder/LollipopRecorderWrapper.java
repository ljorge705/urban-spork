package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.media.MediaRecorder;
import android.view.Surface;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util.SurfaceSizeProvider;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/LollipopRecorderWrapper;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/RecorderWrapperCommon;", "context", "Landroid/content/Context;", "surfaceSizeProvider", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/SurfaceSizeProvider;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/SurfaceSizeProvider;)V", "getMediaRecorderSurface", "Landroid/view/Surface;", "initialize", "", "videoCaptureConfig", "Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;", "cameraCharacteristics", "Landroid/hardware/camera2/CameraCharacteristics;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LollipopRecorderWrapper extends RecorderWrapperCommon {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LollipopRecorderWrapper(Context context, SurfaceSizeProvider surfaceSizeProvider) {
        super(context, surfaceSizeProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(surfaceSizeProvider, "surfaceSizeProvider");
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapper
    public Surface getMediaRecorderSurface() {
        MediaRecorder mediaRecorder = getMediaRecorder();
        if (mediaRecorder == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        Surface surface = mediaRecorder.getSurface();
        Intrinsics.checkNotNullExpressionValue(surface, "run(...)");
        return surface;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapper
    public void initialize(VideoCaptureConfig videoCaptureConfig, CameraCharacteristics cameraCharacteristics) {
        Intrinsics.checkNotNullParameter(videoCaptureConfig, "videoCaptureConfig");
        Intrinsics.checkNotNullParameter(cameraCharacteristics, "cameraCharacteristics");
        MediaRecorder mediaRecorder = new MediaRecorder();
        configureMediaRecorder(mediaRecorder, videoCaptureConfig, cameraCharacteristics);
        setMediaRecorder(mediaRecorder);
    }
}
