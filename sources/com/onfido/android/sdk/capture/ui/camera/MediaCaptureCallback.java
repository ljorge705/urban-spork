package com.onfido.android.sdk.capture.ui.camera;

import com.onfido.android.sdk.capture.ui.camera.view.CameraSource;

/* loaded from: classes2.dex */
public interface MediaCaptureCallback {
    void onErrorTakingPicture(CameraSource.TakePictureException takePictureException);

    void onPictureCaptured(byte[] bArr, int i, int i2);

    void onVideoCanceled();

    void onVideoCaptured(boolean z, String str);

    void onVideoTimeoutExceeded();
}
