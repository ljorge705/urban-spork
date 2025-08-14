package com.onfido.android.sdk.capture.errors;

import kotlin.Metadata;

/* compiled from: InvalidDocumentTypeException.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/onfido/android/sdk/capture/errors/InvalidDocumentTypeException;", "Ljava/lang/RuntimeException;", "()V", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class InvalidDocumentTypeException extends RuntimeException {
    public InvalidDocumentTypeException() {
        super("DocumentType.UNKNOWN should not be used.");
    }
}
