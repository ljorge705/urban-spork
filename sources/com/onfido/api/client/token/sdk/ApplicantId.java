package com.onfido.api.client.token.sdk;

import com.clevertap.android.sdk.Constants;
import io.sentry.protocol.DebugImage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApplicantId.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/onfido/api/client/token/sdk/ApplicantId;", "", DebugImage.JsonKeys.UUID, "", "(Ljava/lang/String;)V", "getUuid", "()Ljava/lang/String;", "component1", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class ApplicantId {
    private final String uuid;

    public static /* synthetic */ ApplicantId copy$default(ApplicantId applicantId, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = applicantId.uuid;
        }
        return applicantId.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getUuid() {
        return this.uuid;
    }

    public final ApplicantId copy(String uuid) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        return new ApplicantId(uuid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ApplicantId) && Intrinsics.areEqual(this.uuid, ((ApplicantId) other).uuid);
    }

    public final String getUuid() {
        return this.uuid;
    }

    public int hashCode() {
        return this.uuid.hashCode();
    }

    public String toString() {
        return "ApplicantId(uuid=" + this.uuid + ")";
    }

    public ApplicantId(String uuid) {
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        this.uuid = uuid;
    }
}
