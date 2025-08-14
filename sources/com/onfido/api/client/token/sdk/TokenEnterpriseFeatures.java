package com.onfido.api.client.token.sdk;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: TokenEnterpriseFeatures.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dBA\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJJ\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\f\u0010\nR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\nR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u000e\u0010\nR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\u000f\u0010\n¨\u0006\u001e"}, d2 = {"Lcom/onfido/api/client/token/sdk/TokenEnterpriseFeatures;", "", "mediaCallbacksEnabled", "", TokenEnterpriseFeatures.HIDE_ONFIDO_LOGO, TokenEnterpriseFeatures.DISABLE_MOBILE_SDK_ANALYTICS, TokenEnterpriseFeatures.COBRAND, TokenEnterpriseFeatures.LOGO_COBRAND, "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getCobrand", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getDisableMobileSdkAnalytics", "getHideOnfidoLogo", "getLogoCobrand", "getMediaCallbacksEnabled", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "(Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lcom/onfido/api/client/token/sdk/TokenEnterpriseFeatures;", "equals", "other", "hashCode", "", "toString", "", "Companion", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class TokenEnterpriseFeatures {
    public static final String COBRAND = "cobrand";

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DISABLE_MOBILE_SDK_ANALYTICS = "disableMobileSdkAnalytics";
    public static final String HIDE_ONFIDO_LOGO = "hideOnfidoLogo";
    public static final String LOGO_COBRAND = "logoCobrand";
    public static final String MEDIA_CALLBACK = "useCustomizedApiRequests";
    private final Boolean cobrand;
    private final Boolean disableMobileSdkAnalytics;
    private final Boolean hideOnfidoLogo;
    private final Boolean logoCobrand;
    private final Boolean mediaCallbacksEnabled;

    public TokenEnterpriseFeatures() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ TokenEnterpriseFeatures copy$default(TokenEnterpriseFeatures tokenEnterpriseFeatures, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, int i, Object obj) {
        if ((i & 1) != 0) {
            bool = tokenEnterpriseFeatures.mediaCallbacksEnabled;
        }
        if ((i & 2) != 0) {
            bool2 = tokenEnterpriseFeatures.hideOnfidoLogo;
        }
        Boolean bool6 = bool2;
        if ((i & 4) != 0) {
            bool3 = tokenEnterpriseFeatures.disableMobileSdkAnalytics;
        }
        Boolean bool7 = bool3;
        if ((i & 8) != 0) {
            bool4 = tokenEnterpriseFeatures.cobrand;
        }
        Boolean bool8 = bool4;
        if ((i & 16) != 0) {
            bool5 = tokenEnterpriseFeatures.logoCobrand;
        }
        return tokenEnterpriseFeatures.copy(bool, bool6, bool7, bool8, bool5);
    }

    /* renamed from: component1, reason: from getter */
    public final Boolean getMediaCallbacksEnabled() {
        return this.mediaCallbacksEnabled;
    }

    /* renamed from: component2, reason: from getter */
    public final Boolean getHideOnfidoLogo() {
        return this.hideOnfidoLogo;
    }

    /* renamed from: component3, reason: from getter */
    public final Boolean getDisableMobileSdkAnalytics() {
        return this.disableMobileSdkAnalytics;
    }

    /* renamed from: component4, reason: from getter */
    public final Boolean getCobrand() {
        return this.cobrand;
    }

    /* renamed from: component5, reason: from getter */
    public final Boolean getLogoCobrand() {
        return this.logoCobrand;
    }

    public final TokenEnterpriseFeatures copy(Boolean mediaCallbacksEnabled, Boolean hideOnfidoLogo, Boolean disableMobileSdkAnalytics, Boolean cobrand, Boolean logoCobrand) {
        return new TokenEnterpriseFeatures(mediaCallbacksEnabled, hideOnfidoLogo, disableMobileSdkAnalytics, cobrand, logoCobrand);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TokenEnterpriseFeatures)) {
            return false;
        }
        TokenEnterpriseFeatures tokenEnterpriseFeatures = (TokenEnterpriseFeatures) other;
        return Intrinsics.areEqual(this.mediaCallbacksEnabled, tokenEnterpriseFeatures.mediaCallbacksEnabled) && Intrinsics.areEqual(this.hideOnfidoLogo, tokenEnterpriseFeatures.hideOnfidoLogo) && Intrinsics.areEqual(this.disableMobileSdkAnalytics, tokenEnterpriseFeatures.disableMobileSdkAnalytics) && Intrinsics.areEqual(this.cobrand, tokenEnterpriseFeatures.cobrand) && Intrinsics.areEqual(this.logoCobrand, tokenEnterpriseFeatures.logoCobrand);
    }

    public final Boolean getCobrand() {
        return this.cobrand;
    }

    public final Boolean getDisableMobileSdkAnalytics() {
        return this.disableMobileSdkAnalytics;
    }

    public final Boolean getHideOnfidoLogo() {
        return this.hideOnfidoLogo;
    }

    public final Boolean getLogoCobrand() {
        return this.logoCobrand;
    }

    public final Boolean getMediaCallbacksEnabled() {
        return this.mediaCallbacksEnabled;
    }

    public int hashCode() {
        Boolean bool = this.mediaCallbacksEnabled;
        int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Boolean bool2 = this.hideOnfidoLogo;
        int iHashCode2 = (iHashCode + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.disableMobileSdkAnalytics;
        int iHashCode3 = (iHashCode2 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Boolean bool4 = this.cobrand;
        int iHashCode4 = (iHashCode3 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
        Boolean bool5 = this.logoCobrand;
        return iHashCode4 + (bool5 != null ? bool5.hashCode() : 0);
    }

    public String toString() {
        return "TokenEnterpriseFeatures(mediaCallbacksEnabled=" + this.mediaCallbacksEnabled + ", hideOnfidoLogo=" + this.hideOnfidoLogo + ", disableMobileSdkAnalytics=" + this.disableMobileSdkAnalytics + ", cobrand=" + this.cobrand + ", logoCobrand=" + this.logoCobrand + ")";
    }

    public TokenEnterpriseFeatures(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5) {
        this.mediaCallbacksEnabled = bool;
        this.hideOnfidoLogo = bool2;
        this.disableMobileSdkAnalytics = bool3;
        this.cobrand = bool4;
        this.logoCobrand = bool5;
    }

    public /* synthetic */ TokenEnterpriseFeatures(Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Boolean bool5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : bool, (i & 2) != 0 ? null : bool2, (i & 4) != 0 ? null : bool3, (i & 8) != 0 ? null : bool4, (i & 16) != 0 ? null : bool5);
    }

    /* compiled from: TokenEnterpriseFeatures.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/onfido/api/client/token/sdk/TokenEnterpriseFeatures$Companion;", "", "()V", "COBRAND", "", "DISABLE_MOBILE_SDK_ANALYTICS", "HIDE_ONFIDO_LOGO", "LOGO_COBRAND", "MEDIA_CALLBACK", "parseJson", "Lcom/onfido/api/client/token/sdk/TokenEnterpriseFeatures;", "json", "parseJson$onfido_public_api_release", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TokenEnterpriseFeatures parseJson$onfido_public_api_release(String json) {
            Intrinsics.checkNotNullParameter(json, "json");
            JSONObject jSONObject = new JSONObject(json);
            return new TokenEnterpriseFeatures(Boolean.valueOf(jSONObject.optBoolean(TokenEnterpriseFeatures.MEDIA_CALLBACK)), Boolean.valueOf(jSONObject.optBoolean(TokenEnterpriseFeatures.HIDE_ONFIDO_LOGO)), Boolean.valueOf(jSONObject.optBoolean(TokenEnterpriseFeatures.DISABLE_MOBILE_SDK_ANALYTICS)), Boolean.valueOf(jSONObject.optBoolean(TokenEnterpriseFeatures.COBRAND)), Boolean.valueOf(jSONObject.optBoolean(TokenEnterpriseFeatures.LOGO_COBRAND)));
        }
    }
}
