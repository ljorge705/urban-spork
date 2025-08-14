package com.onfido.android.sdk.capture.ui.camera.view;

import android.content.Context;
import android.hardware.Camera;
import android.view.Surface;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.ui.camera.CameraResolutionProvider;
import com.onfido.android.sdk.capture.ui.camera.view.CameraSource;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes2.dex */
public class CameraSourceFactory {
    CameraSourceFactory() {
    }

    public static boolean isFrontCameraSupported() {
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            try {
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(i, cameraInfo);
                if (1 == cameraInfo.facing) {
                    return true;
                }
            } catch (RuntimeException unused) {
            }
        }
        return false;
    }

    public static CameraSourceFactory newInstance() {
        return new CameraSourceFactory();
    }

    public CameraSource createCameraSource(Context context, int i, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, int i4, int i5, Surface surface, CameraResolutionProvider cameraResolutionProvider) {
        int i6;
        if (i != 1 || isFrontCameraSupported()) {
            i6 = i;
        } else {
            i6 = 0;
            Timber.w("CAMERA_FACING_FRONT not supported by this camera, reverting to back camera.", new Object[0]);
        }
        CameraSource.Builder builder = new CameraSource.Builder(context);
        builder.setFacing(i6);
        builder.setFocusMode("continuous-picture");
        builder.setCameraResolutionProvider(cameraResolutionProvider);
        builder.setIsPortrait(z2);
        builder.setFlashMode((i6 == 0 && z) ? "auto" : DebugKt.DEBUG_PROPERTY_VALUE_OFF);
        if (z3) {
            builder.setupRecording(i2, i3, i5, i4, surface, z4);
        }
        return builder.build();
    }
}
