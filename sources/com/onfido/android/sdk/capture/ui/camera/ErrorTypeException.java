package com.onfido.android.sdk.capture.ui.camera;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.upload.ErrorType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0004HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/ErrorTypeException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "errorType", "Lcom/onfido/android/sdk/capture/upload/ErrorType;", "(Lcom/onfido/android/sdk/capture/upload/ErrorType;)V", "getErrorType", "()Lcom/onfido/android/sdk/capture/upload/ErrorType;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ErrorTypeException extends Exception {
    private final ErrorType errorType;

    public ErrorTypeException(ErrorType errorType) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        this.errorType = errorType;
    }

    public static /* synthetic */ ErrorTypeException copy$default(ErrorTypeException errorTypeException, ErrorType errorType, int i, Object obj) {
        if ((i & 1) != 0) {
            errorType = errorTypeException.errorType;
        }
        return errorTypeException.copy(errorType);
    }

    /* renamed from: component1, reason: from getter */
    public final ErrorType getErrorType() {
        return this.errorType;
    }

    public final ErrorTypeException copy(ErrorType errorType) {
        Intrinsics.checkNotNullParameter(errorType, "errorType");
        return new ErrorTypeException(errorType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ErrorTypeException) && Intrinsics.areEqual(this.errorType, ((ErrorTypeException) other).errorType);
    }

    public final ErrorType getErrorType() {
        return this.errorType;
    }

    public int hashCode() {
        return this.errorType.hashCode();
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "ErrorTypeException(errorType=" + this.errorType + ')';
    }
}
