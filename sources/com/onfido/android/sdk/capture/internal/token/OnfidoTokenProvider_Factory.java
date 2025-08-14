package com.onfido.android.sdk.capture.internal.token;

import com.onfido.api.client.token.Token;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class OnfidoTokenProvider_Factory implements Factory<OnfidoTokenProvider> {
    private final Provider<Token> tokenProvider;

    public OnfidoTokenProvider_Factory(Provider<Token> provider) {
        this.tokenProvider = provider;
    }

    public static OnfidoTokenProvider_Factory create(Provider<Token> provider) {
        return new OnfidoTokenProvider_Factory(provider);
    }

    public static OnfidoTokenProvider newInstance(Token token) {
        return new OnfidoTokenProvider(token);
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoTokenProvider get() {
        return newInstance(this.tokenProvider.get());
    }
}
