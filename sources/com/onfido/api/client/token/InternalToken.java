package com.onfido.api.client.token;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import io.sentry.protocol.Geo;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InternalToken.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u0012\u001a\u00020\u0003H&R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\fR$\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0007\"\u0004\b\u0010\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/onfido/api/client/token/InternalToken;", "Ljava/io/Serializable;", "tokenValue", "", RemoteConfigConstants.RequestFieldKey.APP_ID, "(Ljava/lang/String;Ljava/lang/String;)V", "getAppId", "()Ljava/lang/String;", "setAppId", "(Ljava/lang/String;)V", "isDemoToken", "", "()Z", "<set-?>", Geo.JsonKeys.REGION, "getRegion", "setRegion", "getTokenValue", "buildUrl", "onfido-api-client"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public abstract class InternalToken implements Serializable {
    private String appId;
    private final boolean isDemoToken;
    private String region;
    private final String tokenValue;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public InternalToken(String tokenValue) {
        this(tokenValue, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(tokenValue, "tokenValue");
    }

    public abstract String buildUrl();

    public final String getAppId() {
        return this.appId;
    }

    public final String getRegion() {
        return this.region;
    }

    public final String getTokenValue() {
        return this.tokenValue;
    }

    /* renamed from: isDemoToken, reason: from getter */
    public final boolean getIsDemoToken() {
        return this.isDemoToken;
    }

    public final void setAppId(String str) {
        this.appId = str;
    }

    protected final void setRegion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.region = str;
    }

    public InternalToken(String tokenValue, String str) {
        Intrinsics.checkNotNullParameter(tokenValue, "tokenValue");
        this.tokenValue = tokenValue;
        this.appId = str;
        this.region = TokenConstants.DEFAULT_REGION;
        this.isDemoToken = Intrinsics.areEqual("demo", tokenValue);
    }

    public /* synthetic */ InternalToken(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2);
    }
}
