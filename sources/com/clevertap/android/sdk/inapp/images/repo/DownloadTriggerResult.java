package com.clevertap.android.sdk.inapp.images.repo;

import com.clevertap.android.sdk.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileResourcesRepoImpl.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0007HÆ\u0003J3\u0010\u0011\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/repo/DownloadTriggerResult;", "", "successfulUrls", "", "", "failureUrls", "allSuccessful", "", "(Ljava/util/List;Ljava/util/List;Z)V", "getAllSuccessful", "()Z", "getFailureUrls", "()Ljava/util/List;", "getSuccessfulUrls", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "other", "hashCode", "", "toString", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class DownloadTriggerResult {
    private final boolean allSuccessful;
    private final List<String> failureUrls;
    private final List<String> successfulUrls;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DownloadTriggerResult copy$default(DownloadTriggerResult downloadTriggerResult, List list, List list2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            list = downloadTriggerResult.successfulUrls;
        }
        if ((i & 2) != 0) {
            list2 = downloadTriggerResult.failureUrls;
        }
        if ((i & 4) != 0) {
            z = downloadTriggerResult.allSuccessful;
        }
        return downloadTriggerResult.copy(list, list2, z);
    }

    public final List<String> component1() {
        return this.successfulUrls;
    }

    public final List<String> component2() {
        return this.failureUrls;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getAllSuccessful() {
        return this.allSuccessful;
    }

    public final DownloadTriggerResult copy(List<String> successfulUrls, List<String> failureUrls, boolean allSuccessful) {
        Intrinsics.checkNotNullParameter(successfulUrls, "successfulUrls");
        Intrinsics.checkNotNullParameter(failureUrls, "failureUrls");
        return new DownloadTriggerResult(successfulUrls, failureUrls, allSuccessful);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadTriggerResult)) {
            return false;
        }
        DownloadTriggerResult downloadTriggerResult = (DownloadTriggerResult) other;
        return Intrinsics.areEqual(this.successfulUrls, downloadTriggerResult.successfulUrls) && Intrinsics.areEqual(this.failureUrls, downloadTriggerResult.failureUrls) && this.allSuccessful == downloadTriggerResult.allSuccessful;
    }

    public final boolean getAllSuccessful() {
        return this.allSuccessful;
    }

    public final List<String> getFailureUrls() {
        return this.failureUrls;
    }

    public final List<String> getSuccessfulUrls() {
        return this.successfulUrls;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((this.successfulUrls.hashCode() * 31) + this.failureUrls.hashCode()) * 31;
        boolean z = this.allSuccessful;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode + i;
    }

    public String toString() {
        return "DownloadTriggerResult(successfulUrls=" + this.successfulUrls + ", failureUrls=" + this.failureUrls + ", allSuccessful=" + this.allSuccessful + ')';
    }

    public DownloadTriggerResult(List<String> successfulUrls, List<String> failureUrls, boolean z) {
        Intrinsics.checkNotNullParameter(successfulUrls, "successfulUrls");
        Intrinsics.checkNotNullParameter(failureUrls, "failureUrls");
        this.successfulUrls = successfulUrls;
        this.failureUrls = failureUrls;
        this.allSuccessful = z;
    }
}
