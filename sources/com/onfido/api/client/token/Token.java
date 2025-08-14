package com.onfido.api.client.token;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Token.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007¨\u0006\u000b"}, d2 = {"Lcom/onfido/api/client/token/Token;", "Ljava/io/Serializable;", "tokenValue", "", "applicationId", "(Ljava/lang/String;Ljava/lang/String;)V", "getApplicationId", "()Ljava/lang/String;", "setApplicationId", "(Ljava/lang/String;)V", "getTokenValue", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes6.dex */
public abstract class Token implements Serializable {
    private String applicationId;
    private final String tokenValue;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public Token(String tokenValue) {
        this(tokenValue, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(tokenValue, "tokenValue");
    }

    public final String getApplicationId() {
        return this.applicationId;
    }

    public final String getTokenValue() {
        return this.tokenValue;
    }

    public final void setApplicationId(String str) {
        this.applicationId = str;
    }

    public Token(String tokenValue, String str) {
        Intrinsics.checkNotNullParameter(tokenValue, "tokenValue");
        this.tokenValue = tokenValue;
        this.applicationId = str;
    }

    public /* synthetic */ Token(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2);
    }
}
