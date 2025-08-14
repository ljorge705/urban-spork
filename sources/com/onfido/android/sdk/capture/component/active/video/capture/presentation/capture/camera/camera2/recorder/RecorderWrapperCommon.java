package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder;

import android.content.Context;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.display.DisplayManager;
import android.media.MediaRecorder;
import android.util.Size;
import com.onfido.android.sdk.capture.component.active.video.capture.AVCConstants;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util.SurfaceSizeProvider;
import com.onfido.android.sdk.capture.internal.camera.VideoFileBuilder;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b \u0018\u0000 /2\u00020\u0001:\u0001/B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0018\u001a\u00020\u0014H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\u0018\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J9\u0010$\u001a\u00020 2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020 0&2!\u0010'\u001a\u001d\u0012\u0013\u0012\u00110)¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020 0(H\u0016J\b\u0010-\u001a\u00020 H\u0016J\u001c\u0010.\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001b\u001a\u00020\u001cH\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\f\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/RecorderWrapperCommon;", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/recorder/RecorderWrapper;", "context", "Landroid/content/Context;", "surfaceSizeProvider", "Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/SurfaceSizeProvider;", "(Landroid/content/Context;Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/camera2/util/SurfaceSizeProvider;)V", "displayManager", "Landroid/hardware/display/DisplayManager;", "getDisplayManager", "()Landroid/hardware/display/DisplayManager;", "displayManager$delegate", "Lkotlin/Lazy;", "mediaRecorder", "Landroid/media/MediaRecorder;", "getMediaRecorder", "()Landroid/media/MediaRecorder;", "setMediaRecorder", "(Landroid/media/MediaRecorder;)V", "outputFile", "Ljava/io/File;", "getOutputFile", "()Ljava/io/File;", "outputFile$delegate", "createFile", "getOrientationHint", "", "cameraCharacteristics", "Landroid/hardware/camera2/CameraCharacteristics;", "getVideoFilePath", "", "releaseRecorder", "", "reset", "videoCaptureConfig", "Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;", "startRecording", "onSuccess", "Lkotlin/Function0;", "onError", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "error", "stopRecording", "configureMediaRecorder", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class RecorderWrapperCommon implements RecorderWrapper {
    private static final int AUDIO_BITRATE = 96000;
    private static final int AUDIO_CHANNELS = 1;
    private static final int AUDIO_SAMPLE_RATE = 44100;
    private final Context context;

    /* renamed from: displayManager$delegate, reason: from kotlin metadata */
    private final Lazy displayManager;
    private MediaRecorder mediaRecorder;

    /* renamed from: outputFile$delegate, reason: from kotlin metadata */
    private final Lazy outputFile;
    private final SurfaceSizeProvider surfaceSizeProvider;

    public RecorderWrapperCommon(Context context, SurfaceSizeProvider surfaceSizeProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(surfaceSizeProvider, "surfaceSizeProvider");
        this.context = context;
        this.surfaceSizeProvider = surfaceSizeProvider;
        this.outputFile = LazyKt.lazy(new Function0<File>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapperCommon$outputFile$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                return this.this$0.createFile();
            }
        });
        this.displayManager = LazyKt.lazy(new Function0<DisplayManager>() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapperCommon$displayManager$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DisplayManager invoke() {
                Object systemService = this.this$0.context.getSystemService("display");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.display.DisplayManager");
                return (DisplayManager) systemService;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File createFile() {
        return new VideoFileBuilder().prefix(AVCConstants.MOTION_RECORDING_FILE_PREFIX).build(this.context);
    }

    private final DisplayManager getDisplayManager() {
        return (DisplayManager) this.displayManager.getValue();
    }

    private final int getOrientationHint(CameraCharacteristics cameraCharacteristics) {
        Object obj = cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
        Intrinsics.checkNotNull(obj);
        int iIntValue = ((Number) obj).intValue();
        int i = 0;
        int rotation = getDisplayManager().getDisplay(0).getRotation();
        if (rotation == 1) {
            i = 90;
        } else if (rotation == 2) {
            i = 180;
        } else if (rotation == 3) {
            i = 270;
        }
        return ((iIntValue + i) + 360) % 360;
    }

    private final File getOutputFile() {
        return (File) this.outputFile.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startRecording$lambda$0(Function1 onError, MediaRecorder mediaRecorder, int i, int i2) {
        Intrinsics.checkNotNullParameter(onError, "$onError");
        onError.invoke(new IllegalStateException("Camera2: Error while recording the video. Error code: " + i));
    }

    protected final MediaRecorder configureMediaRecorder(MediaRecorder mediaRecorder, VideoCaptureConfig videoCaptureConfig, CameraCharacteristics cameraCharacteristics) {
        Intrinsics.checkNotNullParameter(mediaRecorder, "<this>");
        Intrinsics.checkNotNullParameter(videoCaptureConfig, "videoCaptureConfig");
        Intrinsics.checkNotNullParameter(cameraCharacteristics, "cameraCharacteristics");
        mediaRecorder.setVideoSource(2);
        if (videoCaptureConfig.getHasAudio()) {
            mediaRecorder.setAudioSource(5);
        }
        mediaRecorder.setOutputFormat(2);
        mediaRecorder.setOutputFile(getOutputFile().getAbsolutePath());
        mediaRecorder.setVideoEncodingBitRate(videoCaptureConfig.getBitrate());
        mediaRecorder.setVideoFrameRate(videoCaptureConfig.getMaxFps());
        Size landscape = this.surfaceSizeProvider.getVideoSize(cameraCharacteristics).getLandscape();
        mediaRecorder.setVideoSize(landscape.getWidth(), landscape.getHeight());
        mediaRecorder.setVideoEncoder(2);
        if (videoCaptureConfig.getHasAudio()) {
            mediaRecorder.setAudioEncoder(3);
            mediaRecorder.setAudioSamplingRate(44100);
            mediaRecorder.setAudioChannels(1);
            mediaRecorder.setAudioEncodingBitRate(AUDIO_BITRATE);
        }
        mediaRecorder.setOrientationHint(getOrientationHint(cameraCharacteristics));
        mediaRecorder.prepare();
        return mediaRecorder;
    }

    protected final MediaRecorder getMediaRecorder() {
        return this.mediaRecorder;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapper
    public String getVideoFilePath() {
        String absolutePath = getOutputFile().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
        return absolutePath;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapper
    public void releaseRecorder() {
        MediaRecorder mediaRecorder = this.mediaRecorder;
        if (mediaRecorder != null) {
            mediaRecorder.release();
        }
        this.mediaRecorder = null;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapper
    public void reset(VideoCaptureConfig videoCaptureConfig, CameraCharacteristics cameraCharacteristics) {
        Intrinsics.checkNotNullParameter(videoCaptureConfig, "videoCaptureConfig");
        Intrinsics.checkNotNullParameter(cameraCharacteristics, "cameraCharacteristics");
        MediaRecorder mediaRecorder = this.mediaRecorder;
        if (mediaRecorder != null) {
            mediaRecorder.release();
        }
        initialize(videoCaptureConfig, cameraCharacteristics);
    }

    protected final void setMediaRecorder(MediaRecorder mediaRecorder) {
        this.mediaRecorder = mediaRecorder;
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapper
    public void startRecording(Function0<Unit> onSuccess, final Function1<? super Throwable, Unit> onError) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onError, "onError");
        MediaRecorder mediaRecorder = this.mediaRecorder;
        if (mediaRecorder != null) {
            mediaRecorder.start();
        }
        onSuccess.invoke();
        MediaRecorder mediaRecorder2 = this.mediaRecorder;
        if (mediaRecorder2 != null) {
            mediaRecorder2.setOnErrorListener(new MediaRecorder.OnErrorListener() { // from class: com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapperCommon$$ExternalSyntheticLambda0
                @Override // android.media.MediaRecorder.OnErrorListener
                public final void onError(MediaRecorder mediaRecorder3, int i, int i2) {
                    RecorderWrapperCommon.startRecording$lambda$0(onError, mediaRecorder3, i, i2);
                }
            });
        }
    }

    @Override // com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.RecorderWrapper
    public void stopRecording() throws IllegalStateException {
        MediaRecorder mediaRecorder = this.mediaRecorder;
        if (mediaRecorder != null) {
            mediaRecorder.stop();
        }
        MediaRecorder mediaRecorder2 = this.mediaRecorder;
        if (mediaRecorder2 != null) {
            mediaRecorder2.setOnErrorListener(null);
        }
        Timber.INSTANCE.d("Recording stopped. Output file: " + getOutputFile(), new Object[0]);
    }
}
