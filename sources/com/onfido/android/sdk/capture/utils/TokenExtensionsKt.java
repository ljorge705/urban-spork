package com.onfido.android.sdk.capture.utils;

import com.onfido.api.client.token.InternalToken;
import com.onfido.api.client.token.Token;
import com.onfido.api.client.token.sdk.InternalSDKToken;
import com.onfido.api.client.token.sdk.SDKToken;
import com.onfido.api.client.token.sdk.url.ApiUrlCreatorImpl;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"mapToInternalToken", "Lcom/onfido/api/client/token/InternalToken;", "Lcom/onfido/api/client/token/Token;", "onfido-capture-sdk-core_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TokenExtensionsKt {
    public static final InternalToken mapToInternalToken(Token token) {
        Intrinsics.checkNotNullParameter(token, "<this>");
        return token instanceof SDKToken ? new InternalSDKToken(token.getTokenValue(), token.getApplicationId(), null, 4, null) : new InternalToken(token.getTokenValue(), token.getApplicationId()) { // from class: com.onfido.android.sdk.capture.utils.TokenExtensionsKt.mapToInternalToken.1
            @Override // com.onfido.api.client.token.InternalToken
            public String buildUrl() {
                String strCreateApiUrl = new ApiUrlCreatorImpl().createApiUrl(getTokenValue());
                Intrinsics.checkNotNullExpressionValue(strCreateApiUrl, "createApiUrl(...)");
                return strCreateApiUrl;
            }
        };
    }
}
