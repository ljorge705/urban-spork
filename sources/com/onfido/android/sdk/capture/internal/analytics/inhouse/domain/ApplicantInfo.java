package com.onfido.android.sdk.capture.internal.analytics.inhouse.domain;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/analytics/inhouse/domain/ApplicantInfo;", "", "applicantUuid", "", "clientUuid", "(Ljava/lang/String;Ljava/lang/String;)V", "getApplicantUuid", "()Ljava/lang/String;", "getClientUuid", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ApplicantInfo {
    private final String applicantUuid;
    private final String clientUuid;

    public ApplicantInfo(String applicantUuid, String clientUuid) {
        Intrinsics.checkNotNullParameter(applicantUuid, "applicantUuid");
        Intrinsics.checkNotNullParameter(clientUuid, "clientUuid");
        this.applicantUuid = applicantUuid;
        this.clientUuid = clientUuid;
    }

    public static /* synthetic */ ApplicantInfo copy$default(ApplicantInfo applicantInfo, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = applicantInfo.applicantUuid;
        }
        if ((i & 2) != 0) {
            str2 = applicantInfo.clientUuid;
        }
        return applicantInfo.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getApplicantUuid() {
        return this.applicantUuid;
    }

    /* renamed from: component2, reason: from getter */
    public final String getClientUuid() {
        return this.clientUuid;
    }

    public final ApplicantInfo copy(String applicantUuid, String clientUuid) {
        Intrinsics.checkNotNullParameter(applicantUuid, "applicantUuid");
        Intrinsics.checkNotNullParameter(clientUuid, "clientUuid");
        return new ApplicantInfo(applicantUuid, clientUuid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ApplicantInfo)) {
            return false;
        }
        ApplicantInfo applicantInfo = (ApplicantInfo) other;
        return Intrinsics.areEqual(this.applicantUuid, applicantInfo.applicantUuid) && Intrinsics.areEqual(this.clientUuid, applicantInfo.clientUuid);
    }

    public final String getApplicantUuid() {
        return this.applicantUuid;
    }

    public final String getClientUuid() {
        return this.clientUuid;
    }

    public int hashCode() {
        return (this.applicantUuid.hashCode() * 31) + this.clientUuid.hashCode();
    }

    public String toString() {
        return "ApplicantInfo(applicantUuid=" + this.applicantUuid + ", clientUuid=" + this.clientUuid + ')';
    }
}
