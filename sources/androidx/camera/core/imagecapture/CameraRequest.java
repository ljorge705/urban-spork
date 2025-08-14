package androidx.camera.core.imagecapture;

import androidx.camera.core.impl.CaptureConfig;
import java.util.List;

/* loaded from: classes.dex */
public final class CameraRequest {
    private final TakePictureCallback mCallback;
    private final List<CaptureConfig> mCaptureConfigs;

    List<CaptureConfig> getCaptureConfigs() {
        return this.mCaptureConfigs;
    }

    public CameraRequest(List<CaptureConfig> list, TakePictureCallback takePictureCallback) {
        this.mCaptureConfigs = list;
        this.mCallback = takePictureCallback;
    }

    boolean isAborted() {
        return this.mCallback.isAborted();
    }
}
