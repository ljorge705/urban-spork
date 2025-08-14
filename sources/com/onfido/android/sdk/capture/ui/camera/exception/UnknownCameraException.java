package com.onfido.android.sdk.capture.ui.camera.exception;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/exception/UnknownCameraException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "Lcom/onfido/android/sdk/capture/ui/camera/exception/CameraException;", "message", "", "(Ljava/lang/String;)V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class UnknownCameraException extends RuntimeException implements CameraException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnknownCameraException(String message) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
    }
}
