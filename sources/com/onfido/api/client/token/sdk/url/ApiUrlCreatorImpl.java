package com.onfido.api.client.token.sdk.url;

import com.onfido.api.client.token.TokenConstants;
import com.onfido.api.client.token.sdk.model.InternalSDKTokenPayload;
import com.onfido.api.client.util.StringUtil;

/* loaded from: classes6.dex */
public class ApiUrlCreatorImpl implements ApiUrlCreator {
    @Override // com.onfido.api.client.token.sdk.url.ApiUrlCreator
    public String createApiUrl(String str) {
        InternalSDKTokenPayload sDKTokenPayload = InternalSDKTokenPayload.parseSDKTokenPayload(str);
        return (sDKTokenPayload == null || !StringUtil.hasCharacter(sDKTokenPayload.getBaseUrl())) ? TokenConstants.BASE_API_URL : sDKTokenPayload.getBaseUrl();
    }
}
