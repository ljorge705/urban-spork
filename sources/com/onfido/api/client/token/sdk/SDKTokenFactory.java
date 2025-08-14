package com.onfido.api.client.token.sdk;

import com.onfido.api.client.token.sdk.url.ApiUrlCreator;
import com.onfido.api.client.token.sdk.url.ApiUrlCreatorImpl;
import com.onfido.api.client.token.sdk.url.AuthUrlCreator;

/* loaded from: classes6.dex */
public final class SDKTokenFactory {
    private static final ApiUrlCreator idvUrlCreator = new ApiUrlCreatorImpl();
    private static final ApiUrlCreator authUrlCreator = new AuthUrlCreator();

    public static InternalSDKToken forIDV(String str, String str2) {
        return new InternalSDKToken(str, str2, idvUrlCreator);
    }

    public static InternalSDKToken forAuth(String str, String str2) {
        return new InternalSDKToken(str, str2, authUrlCreator);
    }
}
