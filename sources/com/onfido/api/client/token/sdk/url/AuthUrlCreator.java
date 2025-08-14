package com.onfido.api.client.token.sdk.url;

import com.onfido.api.client.token.TokenConstants;
import com.onfido.api.client.token.sdk.model.InternalSDKTokenPayload;
import com.onfido.api.client.util.StringUtil;

/* loaded from: classes6.dex */
public class AuthUrlCreator implements ApiUrlCreator {
    @Override // com.onfido.api.client.token.sdk.url.ApiUrlCreator
    public String createApiUrl(String str) {
        String authUrl;
        InternalSDKTokenPayload sDKTokenPayload = InternalSDKTokenPayload.parseSDKTokenPayload(str);
        if (sDKTokenPayload != null) {
            authUrl = sDKTokenPayload.getAuthUrl();
            if (StringUtil.hasCharacter(authUrl)) {
                authUrl = authUrl + TokenConstants.API_VERSION;
            }
        } else {
            authUrl = null;
        }
        return StringUtil.isNullOrEmpty(authUrl) ? TokenConstants.AUTH_BASE_API_URL : authUrl;
    }
}
