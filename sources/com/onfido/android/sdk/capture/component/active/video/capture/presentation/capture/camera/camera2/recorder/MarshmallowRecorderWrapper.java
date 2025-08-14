package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.media.MediaCodec;
import android.media.MediaRecorder;
import android.view.Surface;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util.SurfaceSizeProvider;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\bH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/MarshmallowRecorderWrapper;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/RecorderWrapperCommon;", "context", "Landroid/content/Context;", "surfaceSizeProvider", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/SurfaceSizeProvider;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/SurfaceSizeProvider;)V", "outputPersistentSurface", "Landroid/view/Surface;", "getOutputPersistentSurface", "()Landroid/view/Surface;", "outputPersistentSurface$delegate", "Lkotlin/Lazy;", "getMediaRecorderSurface", "initialize", "", "videoCaptureConfig", "Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;", "cameraCharacteristics", "Landroid/hardware/camera2/CameraCharacteristics;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MarshmallowRecorderWrapper extends RecorderWrapperCommon {

    /* renamed from: outputPersistentSurface$delegate, reason: from kotlin metadata */
    private final Lazy outputPersistentSurface;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MarshmallowRecorderWrapper(Context context, SurfaceSizeProvider surfaceSizeProvider) {
        super(context, surfaceSizeProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(surfaceSizeProvider, "surfaceSizeProvider");
        this.outputPersistentSurface = LazyKt.lazy(new Function0<Surface>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.MarshmallowRecorderWrapper$outputPersistentSurface$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Surface invoke() {
                Surface surfaceCreatePersistentInputSurface = MediaCodec.createPersistentInputSurface();
                Intrinsics.checkNotNullExpressionValue(surfaceCreatePersistentInputSurface, "createPersistentInputSurface(...)");
                return surfaceCreatePersistentInputSurface;
            }
        });
    }

    private final Surface getOutputPersistentSurface() {
        return (Surface) this.outputPersistentSurface.getValue();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapper
    public Surface getMediaRecorderSurface() {
        return getOutputPersistentSurface();
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapper
    public void initialize(VideoCaptureConfig videoCaptureConfig, CameraCharacteristics cameraCharacteristics) {
        Intrinsics.checkNotNullParameter(videoCaptureConfig, "videoCaptureConfig");
        Intrinsics.checkNotNullParameter(cameraCharacteristics, "cameraCharacteristics");
        MediaRecorder mediaRecorder = new MediaRecorder();
        mediaRecorder.setInputSurface(getOutputPersistentSurface());
        configureMediaRecorder(mediaRecorder, videoCaptureConfig, cameraCharacteristics);
        setMediaRecorder(mediaRecorder);
    }
}
