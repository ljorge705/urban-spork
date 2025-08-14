package com.onfido.android.sdk.capture.errors;

import kotlin.Metadata;

/* compiled from: MultipleApplicantsException.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/onfido/android/sdk/capture/errors/MultipleApplicantsException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "()V", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MultipleApplicantsException extends RuntimeException {
    public MultipleApplicantsException() {
        super("You are not allowed to define multiple applicants.");
    }
}
