package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCaptureSession;
import androidx.camera.core.impl.CameraCaptureCallback;

/* loaded from: classes.dex */
final class CaptureCallbackContainer extends CameraCaptureCallback {
    private final CameraCaptureSession.CaptureCallback mCaptureCallback;

    CameraCaptureSession.CaptureCallback getCaptureCallback() {
        return this.mCaptureCallback;
    }

    private CaptureCallbackContainer(CameraCaptureSession.CaptureCallback captureCallback) {
        if (captureCallback == null) {
            throw new NullPointerException("captureCallback is null");
        }
        this.mCaptureCallback = captureCallback;
    }

    static CaptureCallbackContainer create(CameraCaptureSession.CaptureCallback captureCallback) {
        return new CaptureCallbackContainer(captureCallback);
    }
}
