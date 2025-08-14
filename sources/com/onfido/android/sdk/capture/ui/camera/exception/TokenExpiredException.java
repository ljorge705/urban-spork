package com.onfido.android.sdk.capture.ui.camera.exception;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/exception/TokenExpiredException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TokenExpiredException extends Exception {
    public static final TokenExpiredException INSTANCE = new TokenExpiredException();

    private TokenExpiredException() {
        super("token expired");
    }
}
