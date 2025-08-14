package com.onfido.android.sdk.capture.token;

import android.content.Context;
import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class TokenExpirationServiceConnector_Factory implements Factory<TokenExpirationServiceConnector> {
    private final Provider<Context> applicationContextProvider;
    private final Provider<OnfidoTokenProvider> tokenProvider;

    public TokenExpirationServiceConnector_Factory(Provider<OnfidoTokenProvider> provider, Provider<Context> provider2) {
        this.tokenProvider = provider;
        this.applicationContextProvider = provider2;
    }

    public static TokenExpirationServiceConnector_Factory create(Provider<OnfidoTokenProvider> provider, Provider<Context> provider2) {
        return new TokenExpirationServiceConnector_Factory(provider, provider2);
    }

    public static TokenExpirationServiceConnector newInstance(OnfidoTokenProvider onfidoTokenProvider, Context context) {
        return new TokenExpirationServiceConnector(onfidoTokenProvider, context);
    }

    @Override // com.onfido.javax.inject.Provider
    public TokenExpirationServiceConnector get() {
        return newInstance(this.tokenProvider.get(), this.applicationContextProvider.get());
    }
}
