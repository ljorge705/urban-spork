package com.google.maps.android;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StreetViewUtil.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/google/maps/android/ResponseStreetView;", "", "status", "Lcom/google/maps/android/Status;", "(Lcom/google/maps/android/Status;)V", "getStatus", "()Lcom/google/maps/android/Status;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "library_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ResponseStreetView {
    private final Status status;

    public static /* synthetic */ ResponseStreetView copy$default(ResponseStreetView responseStreetView, Status status, int i, Object obj) {
        if ((i & 1) != 0) {
            status = responseStreetView.status;
        }
        return responseStreetView.copy(status);
    }

    /* renamed from: component1, reason: from getter */
    public final Status getStatus() {
        return this.status;
    }

    public final ResponseStreetView copy(Status status) {
        Intrinsics.checkNotNullParameter(status, "status");
        return new ResponseStreetView(status);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ResponseStreetView) && this.status == ((ResponseStreetView) other).status;
    }

    public final Status getStatus() {
        return this.status;
    }

    public int hashCode() {
        return this.status.hashCode();
    }

    public String toString() {
        return "ResponseStreetView(status=" + this.status + ')';
    }

    public ResponseStreetView(Status status) {
        Intrinsics.checkNotNullParameter(status, "status");
        this.status = status;
    }
}
