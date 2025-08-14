package com.onfido.android.sdk.capture.common.result;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/onfido/android/sdk/capture/common/result/FailureReason;", "", "throwable", "", "message", "", "(Ljava/lang/Throwable;Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "getThrowable", "()Ljava/lang/Throwable;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FailureReason {
    private final String message;
    private final Throwable throwable;

    public FailureReason(Throwable throwable, String message) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        Intrinsics.checkNotNullParameter(message, "message");
        this.throwable = throwable;
        this.message = message;
    }

    public final String getMessage() {
        return this.message;
    }

    public final Throwable getThrowable() {
        return this.throwable;
    }
}
