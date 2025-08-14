package com.onfido.android.sdk.capture.internal.camera.camerax;

import android.content.Context;
import androidx.camera.video.FileOutputOptions;
import androidx.camera.video.PendingRecording;
import androidx.camera.video.Recorder;
import androidx.camera.video.Recording;
import androidx.camera.video.VideoCapture;
import androidx.camera.video.VideoRecordEvent;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import com.onfido.android.sdk.capture.internal.camera.OnfidoCamera;
import com.onfido.android.sdk.capture.internal.camera.VideoFileBuilder;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.camera.capture.VideoCaptureConfig;
import com.onfido.javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000o\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u000b\b\u0000\u0018\u0000 %2\u00020\u0001:\u0001%B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0002JC\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172!\u0010\u0018\u001a\u001d\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00100\u0019H\u0002JB\u0010\u001e\u001a\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010#\u001a\u00020$2!\u0010\u0018\u001a\u001d\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00100\u0019H\u0087\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraXTakeVideoUseCase;", "", "applicationContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_hasValidRecording", "", "executor", "Ljava/util/concurrent/Executor;", "isRecordingFailed", "recorderController", "com/onfido/android/sdk/capture/internal/camera/camerax/CameraXTakeVideoUseCase$recorderController$1", "Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraXTakeVideoUseCase$recorderController$1;", "recording", "Landroidx/camera/video/Recording;", "closeRecording", "", "handleRecordingEvent", "recordEvent", "Landroidx/camera/video/VideoRecordEvent;", "maxRecordingDuration", "", "outputFile", "Ljava/io/File;", "callback", "Lkotlin/Function1;", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoCaptureEvent;", "Lkotlin/ParameterName;", "name", "event", "invoke", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoCamera$VideoRecorder;", "videoCapture", "Landroidx/camera/video/VideoCapture;", "Landroidx/camera/video/Recorder;", "config", "Lcom/onfido/android/sdk/capture/ui/camera/capture/VideoCaptureConfig;", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CameraXTakeVideoUseCase {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final int MINIMUM_BYTE_FOR_VALID_RECORDING = 1024;
    private boolean _hasValidRecording;
    private final Context applicationContext;
    private final Executor executor;
    private boolean isRecordingFailed;
    private final CameraXTakeVideoUseCase$recorderController$1 recorderController;
    private Recording recording;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/CameraXTakeVideoUseCase$Companion;", "", "()V", "MINIMUM_BYTE_FOR_VALID_RECORDING", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.onfido.android.sdk.capture.internal.camera.camerax.CameraXTakeVideoUseCase$recorderController$1] */
    @Inject
    public CameraXTakeVideoUseCase(Context applicationContext) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        this.applicationContext = applicationContext;
        Executor mainExecutor = ContextCompat.getMainExecutor(applicationContext);
        Intrinsics.checkNotNullExpressionValue(mainExecutor, "getMainExecutor(...)");
        this.executor = mainExecutor;
        this.recorderController = new OnfidoCamera.VideoRecorder() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.CameraXTakeVideoUseCase$recorderController$1
            @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera.VideoRecorder
            public void cancel() {
                try {
                    Recording recording = this.this$0.recording;
                    if (recording != null) {
                        recording.pause();
                    }
                } catch (IllegalStateException e) {
                    Timber.INSTANCE.e(e);
                }
            }

            @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera.VideoRecorder
            public void finish() {
                Recording recording = this.this$0.recording;
                if (recording != null) {
                    recording.stop();
                }
            }

            @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera.VideoRecorder
            public boolean getHasValidRecording() {
                return this.this$0._hasValidRecording;
            }

            @Override // com.onfido.android.sdk.capture.internal.camera.OnfidoCamera.VideoRecorder
            public boolean isRecording() {
                return this.this$0.recording != null;
            }
        };
    }

    private final void closeRecording() {
        Recording recording = this.recording;
        if (recording != null) {
            recording.close();
        }
        this.recording = null;
    }

    private final void handleRecordingEvent(VideoRecordEvent recordEvent, long maxRecordingDuration, File outputFile, Function1<? super OnfidoCamera.VideoCaptureEvent, Unit> callback) {
        OnfidoCamera.VideoCaptureEvent recorded;
        if (recordEvent instanceof VideoRecordEvent.Status) {
            VideoRecordEvent.Status status = (VideoRecordEvent.Status) recordEvent;
            if (status.getRecordingStats().getRecordedDurationNanos() >= maxRecordingDuration && this.recording != null) {
                this.isRecordingFailed = true;
                closeRecording();
                callback.invoke(OnfidoCamera.VideoCaptureEvent.Timeout.INSTANCE);
            }
            this._hasValidRecording = status.getRecordingStats().getNumBytesRecorded() > 1024;
            return;
        }
        if (recordEvent instanceof VideoRecordEvent.Pause) {
            this.isRecordingFailed = true;
            closeRecording();
            recorded = OnfidoCamera.VideoCaptureEvent.Canceled.INSTANCE;
        } else if (recordEvent instanceof VideoRecordEvent.Start) {
            this.isRecordingFailed = false;
            recorded = OnfidoCamera.VideoCaptureEvent.Started.INSTANCE;
        } else {
            if (!(recordEvent instanceof VideoRecordEvent.Finalize)) {
                return;
            }
            this.recording = null;
            VideoRecordEvent.Finalize finalize = (VideoRecordEvent.Finalize) recordEvent;
            if (finalize.hasError()) {
                this.isRecordingFailed = true;
                outputFile.delete();
                Timber.INSTANCE.e("CameraX couldn't record video since " + finalize.getCause() + " with error code " + finalize.getError(), new Object[0]);
                Throwable cause = finalize.getCause();
                if (cause == null) {
                    cause = new IllegalStateException("Recording failed");
                }
                callback.invoke(new OnfidoCamera.VideoCaptureEvent.Error(cause));
                return;
            }
            if (this.isRecordingFailed) {
                outputFile.delete();
                return;
            } else {
                String absolutePath = outputFile.getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
                recorded = new OnfidoCamera.VideoCaptureEvent.Recorded(absolutePath);
            }
        }
        callback.invoke(recorded);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$1(CameraXTakeVideoUseCase this$0, long j, File outputFile, Function1 callback, VideoRecordEvent videoRecordEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(outputFile, "$outputFile");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Intrinsics.checkNotNull(videoRecordEvent);
        this$0.handleRecordingEvent(videoRecordEvent, j, outputFile, callback);
    }

    public final OnfidoCamera.VideoRecorder invoke(VideoCapture<Recorder> videoCapture, VideoCaptureConfig config, final Function1<? super OnfidoCamera.VideoCaptureEvent, Unit> callback) throws IOException {
        Intrinsics.checkNotNullParameter(videoCapture, "videoCapture");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this._hasValidRecording = false;
        final File fileBuild = new VideoFileBuilder().prefix(config.getFileNamePrefix()).build(this.applicationContext);
        FileOutputOptions fileOutputOptionsBuild = new FileOutputOptions.Builder(fileBuild).build();
        Intrinsics.checkNotNullExpressionValue(fileOutputOptionsBuild, "build(...)");
        PendingRecording pendingRecordingPrepareRecording = ((Recorder) videoCapture.getOutput()).prepareRecording(this.applicationContext, fileOutputOptionsBuild);
        if (config.getHasAudio()) {
            pendingRecordingPrepareRecording.withAudioEnabled();
        }
        Intrinsics.checkNotNullExpressionValue(pendingRecordingPrepareRecording, "apply(...)");
        final long nanos = TimeUnit.MILLISECONDS.toNanos(config.getMaxDuration());
        try {
            this.recording = pendingRecordingPrepareRecording.start(this.executor, new Consumer() { // from class: com.onfido.android.sdk.capture.internal.camera.camerax.CameraXTakeVideoUseCase$$ExternalSyntheticLambda0
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    CameraXTakeVideoUseCase.invoke$lambda$1(this.f$0, nanos, fileBuild, callback, (VideoRecordEvent) obj);
                }
            });
        } catch (IllegalStateException e) {
            Timber.INSTANCE.e(e);
            callback.invoke(new OnfidoCamera.VideoCaptureEvent.Error(e));
        }
        return this.recorderController;
    }
}
