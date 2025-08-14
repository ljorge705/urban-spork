package com.onfido.api.client.token.sdk;

import com.clevertap.android.sdk.Constants;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TokenPayload.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/onfido/api/client/token/sdk/TokenPayload;", "Ljava/io/Serializable;", "applicantId", "", "clientUuid", "(Ljava/lang/String;Ljava/lang/String;)V", "getApplicantId", "()Ljava/lang/String;", "getClientUuid", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class TokenPayload implements Serializable {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_APPLICANT_ID = "app";
    private static final String KEY_CLIENT_ID = "client_uuid";
    private final String applicantId;
    private final String clientUuid;

    public static /* synthetic */ TokenPayload copy$default(TokenPayload tokenPayload, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = tokenPayload.applicantId;
        }
        if ((i & 2) != 0) {
            str2 = tokenPayload.clientUuid;
        }
        return tokenPayload.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getApplicantId() {
        return this.applicantId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getClientUuid() {
        return this.clientUuid;
    }

    public final TokenPayload copy(String applicantId, String clientUuid) {
        Intrinsics.checkNotNullParameter(applicantId, "applicantId");
        Intrinsics.checkNotNullParameter(clientUuid, "clientUuid");
        return new TokenPayload(applicantId, clientUuid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TokenPayload)) {
            return false;
        }
        TokenPayload tokenPayload = (TokenPayload) other;
        return Intrinsics.areEqual(this.applicantId, tokenPayload.applicantId) && Intrinsics.areEqual(this.clientUuid, tokenPayload.clientUuid);
    }

    public final String getApplicantId() {
        return this.applicantId;
    }

    public final String getClientUuid() {
        return this.clientUuid;
    }

    public int hashCode() {
        return (this.applicantId.hashCode() * 31) + this.clientUuid.hashCode();
    }

    public String toString() {
        return "TokenPayload(applicantId=" + this.applicantId + ", clientUuid=" + this.clientUuid + ")";
    }

    public TokenPayload(String applicantId, String clientUuid) {
        Intrinsics.checkNotNullParameter(applicantId, "applicantId");
        Intrinsics.checkNotNullParameter(clientUuid, "clientUuid");
        this.applicantId = applicantId;
        this.clientUuid = clientUuid;
    }

    /* compiled from: TokenPayload.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/onfido/api/client/token/sdk/TokenPayload$Companion;", "", "()V", "KEY_APPLICANT_ID", "", "KEY_CLIENT_ID", "parseJson", "Lcom/onfido/api/client/token/sdk/TokenPayload;", "json", "parseJson$onfido_public_api_release", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TokenPayload parseJson$onfido_public_api_release(String json) throws JSONException {
            Intrinsics.checkNotNullParameter(json, "json");
            JSONObject jSONObject = new JSONObject(json);
            String string = jSONObject.getString("app");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String string2 = jSONObject.getString(TokenPayload.KEY_CLIENT_ID);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            return new TokenPayload(string, string2);
        }
    }
}
