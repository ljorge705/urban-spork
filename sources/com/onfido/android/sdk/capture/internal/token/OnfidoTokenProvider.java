package com.onfido.android.sdk.capture.internal.token;

import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.android.sdk.capture.utils.TokenExtensionsKt;
import com.onfido.api.client.token.InternalToken;
import com.onfido.api.client.token.Token;
import com.onfido.api.client.token.TokenProvider;
import com.onfido.api.client.token.sdk.InternalSDKToken;
import com.onfido.api.client.token.sdk.url.ApiUrlCreatorImpl;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001B\u000f\b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0010¢\u0006\u0002\b\rJ\f\u0010\u000e\u001a\u00020\b*\u00020\u0006H\u0012R\u000e\u0010\u0005\u001a\u00020\u0006X\u0092\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/token/OnfidoTokenProvider;", "Lcom/onfido/api/client/token/TokenProvider;", "token", "Lcom/onfido/api/client/token/Token;", "(Lcom/onfido/api/client/token/Token;)V", "internalToken", "Lcom/onfido/api/client/token/InternalToken;", "provideToken", "Lcom/onfido/api/client/token/sdk/InternalSDKToken;", "updateToken", "", "tokenValue", "", "updateToken$onfido_capture_sdk_core_release", "ensureSDKToken", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Singleton
/* loaded from: classes2.dex */
public class OnfidoTokenProvider implements TokenProvider {
    private InternalToken internalToken;

    @Inject
    public OnfidoTokenProvider(Token token) {
        Intrinsics.checkNotNullParameter(token, "token");
        this.internalToken = TokenExtensionsKt.mapToInternalToken(token);
    }

    private InternalSDKToken ensureSDKToken(InternalToken internalToken) throws OnfidoException {
        if (internalToken instanceof InternalSDKToken) {
            return (InternalSDKToken) internalToken;
        }
        throw new OnfidoException("Unknown token type");
    }

    public void updateToken$onfido_capture_sdk_core_release(String tokenValue) throws OnfidoException {
        Intrinsics.checkNotNullParameter(tokenValue, "tokenValue");
        ensureSDKToken(this.internalToken);
        this.internalToken = new InternalSDKToken(tokenValue, this.internalToken.getAppId(), new ApiUrlCreatorImpl());
    }

    @Override // com.onfido.api.client.token.TokenProvider
    public InternalSDKToken provideToken() {
        return ensureSDKToken(this.internalToken);
    }
}
