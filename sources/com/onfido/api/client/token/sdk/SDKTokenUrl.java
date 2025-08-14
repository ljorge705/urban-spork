package com.onfido.api.client.token.sdk;

import com.clevertap.android.sdk.Constants;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: SDKTokenUrl.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0080\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u001d\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/onfido/api/client/token/sdk/SDKTokenUrl;", "Ljava/io/Serializable;", "baseUrl", "", "authUrl", "(Ljava/lang/String;Ljava/lang/String;)V", "getAuthUrl", "()Ljava/lang/String;", "getBaseUrl", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class SDKTokenUrl implements Serializable {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String KEY_AUTH_URL = "auth_url";
    private static final String KEY_ONFIDO_API_URL = "onfido_api_url";
    private final String authUrl;
    private final String baseUrl;

    /* JADX WARN: Multi-variable type inference failed */
    public SDKTokenUrl() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ SDKTokenUrl copy$default(SDKTokenUrl sDKTokenUrl, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sDKTokenUrl.baseUrl;
        }
        if ((i & 2) != 0) {
            str2 = sDKTokenUrl.authUrl;
        }
        return sDKTokenUrl.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getBaseUrl() {
        return this.baseUrl;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAuthUrl() {
        return this.authUrl;
    }

    public final SDKTokenUrl copy(String baseUrl, String authUrl) {
        return new SDKTokenUrl(baseUrl, authUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SDKTokenUrl)) {
            return false;
        }
        SDKTokenUrl sDKTokenUrl = (SDKTokenUrl) other;
        return Intrinsics.areEqual(this.baseUrl, sDKTokenUrl.baseUrl) && Intrinsics.areEqual(this.authUrl, sDKTokenUrl.authUrl);
    }

    public final String getAuthUrl() {
        return this.authUrl;
    }

    public final String getBaseUrl() {
        return this.baseUrl;
    }

    public int hashCode() {
        String str = this.baseUrl;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.authUrl;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "SDKTokenUrl(baseUrl=" + this.baseUrl + ", authUrl=" + this.authUrl + ")";
    }

    public SDKTokenUrl(String str, String str2) {
        this.baseUrl = str;
        this.authUrl = str2;
    }

    public /* synthetic */ SDKTokenUrl(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2);
    }

    /* compiled from: SDKTokenUrl.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/onfido/api/client/token/sdk/SDKTokenUrl$Companion;", "", "()V", "KEY_AUTH_URL", "", "KEY_ONFIDO_API_URL", "parseJson", "Lcom/onfido/api/client/token/sdk/SDKTokenUrl;", "json", "parseJson$onfido_public_api_release", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SDKTokenUrl parseJson$onfido_public_api_release(String json) {
            Object objM6095constructorimpl;
            Intrinsics.checkNotNullParameter(json, "json");
            try {
                Result.Companion companion = Result.INSTANCE;
                Companion companion2 = this;
                JSONObject jSONObject = new JSONObject(json);
                objM6095constructorimpl = Result.m6095constructorimpl(new SDKTokenUrl(jSONObject.getString(SDKTokenUrl.KEY_ONFIDO_API_URL), jSONObject.getString(SDKTokenUrl.KEY_AUTH_URL)));
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                objM6095constructorimpl = Result.m6095constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m6101isFailureimpl(objM6095constructorimpl)) {
                objM6095constructorimpl = null;
            }
            return (SDKTokenUrl) objM6095constructorimpl;
        }
    }
}
