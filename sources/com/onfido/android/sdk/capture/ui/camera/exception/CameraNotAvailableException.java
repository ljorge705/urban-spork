package com.onfido.android.sdk.capture.ui.camera.exception;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00060\u0001j\u0002`\u00022\u00020\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/exception/CameraNotAvailableException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "Lcom/onfido/android/sdk/capture/ui/camera/exception/CameraException;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CameraNotAvailableException extends RuntimeException implements CameraException {
    public static final CameraNotAvailableException INSTANCE = new CameraNotAvailableException();

    private CameraNotAvailableException() {
        super("Camera not available");
    }
}
