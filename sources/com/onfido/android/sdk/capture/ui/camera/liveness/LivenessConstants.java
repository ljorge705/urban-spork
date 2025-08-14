package com.onfido.android.sdk.capture.ui.camera.liveness;

import kotlin.Deprecated;
import kotlin.Metadata;

@Deprecated(message = "Use [VideoCaptureConfig]")
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/LivenessConstants;", "", "()V", "LIVENESS_INTRO_VIDEO_NAME", "", "LIVENESS_MF_VIDEO_PREFIX", "LIVENESS_VIDEO_PREFIX", "LIVE_VIDEO_ENCODING_BIT_RATE", "", "LIVE_VIDEO_MAX_DURATION_MS", "LIVE_VIDEO_QUALITY", "MAX_VIDEO_SIZE_BYTES", "", "OUT_OF_STORAGE_THRESHOLD_BYTES", "VIDEO_RECORDING_FILE_FORMAT", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LivenessConstants {
    public static final LivenessConstants INSTANCE = new LivenessConstants();
    public static final String LIVENESS_INTRO_VIDEO_NAME = "ONFIDO_LIVENESS_INTRO.mp4";
    public static final String LIVENESS_MF_VIDEO_PREFIX = "ONFIDO_MF_VID_";
    public static final String LIVENESS_VIDEO_PREFIX = "ONFIDO_VID_";
    public static final int LIVE_VIDEO_ENCODING_BIT_RATE = 1600000;
    public static final int LIVE_VIDEO_MAX_DURATION_MS = 25000;
    public static final int LIVE_VIDEO_QUALITY = 4;
    public static final long MAX_VIDEO_SIZE_BYTES = 5000000;
    public static final int OUT_OF_STORAGE_THRESHOLD_BYTES = 500000;
    public static final String VIDEO_RECORDING_FILE_FORMAT = ".mp4";

    private LivenessConstants() {
    }
}
