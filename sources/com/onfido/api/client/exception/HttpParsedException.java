package com.onfido.api.client.exception;

import com.clevertap.android.sdk.Constants;
import com.onfido.api.client.data.ErrorData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HttpParsedException.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\b\u001a\u00020\u0004HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/onfido/api/client/exception/HttpParsedException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "errorData", "Lcom/onfido/api/client/data/ErrorData;", "(Lcom/onfido/api/client/data/ErrorData;)V", "getErrorData", "()Lcom/onfido/api/client/data/ErrorData;", "component1", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class HttpParsedException extends Exception {
    private final ErrorData errorData;

    public static /* synthetic */ HttpParsedException copy$default(HttpParsedException httpParsedException, ErrorData errorData, int i, Object obj) {
        if ((i & 1) != 0) {
            errorData = httpParsedException.errorData;
        }
        return httpParsedException.copy(errorData);
    }

    /* renamed from: component1, reason: from getter */
    public final ErrorData getErrorData() {
        return this.errorData;
    }

    public final HttpParsedException copy(ErrorData errorData) {
        Intrinsics.checkNotNullParameter(errorData, "errorData");
        return new HttpParsedException(errorData);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof HttpParsedException) && Intrinsics.areEqual(this.errorData, ((HttpParsedException) other).errorData);
    }

    public final ErrorData getErrorData() {
        return this.errorData;
    }

    public int hashCode() {
        return this.errorData.hashCode();
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "HttpParsedException(errorData=" + this.errorData + ")";
    }

    public HttpParsedException(ErrorData errorData) {
        Intrinsics.checkNotNullParameter(errorData, "errorData");
        this.errorData = errorData;
    }
}
