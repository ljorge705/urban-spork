package com.onfido.android.sdk.capture.errors;

import kotlin.Metadata;

/* compiled from: DuplicatedFaceCaptureVariantException.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/errors/DuplicatedFaceCaptureVariantException;", "Ljava/lang/RuntimeException;", "()V", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DuplicatedFaceCaptureVariantException extends RuntimeException {
    public DuplicatedFaceCaptureVariantException() {
        super("You are not allowed to define more than one FaceCaptureVariant in a flow.");
    }
}
