package com.onfido.android.sdk.capture.ui.userconsent;

import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.android.sdk.capture.ui.userconsent.network.UserConsentApi;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import okhttp3.OkHttpClient;

/* loaded from: classes2.dex */
public final class UserConsentRepository_Factory implements Factory<UserConsentRepository> {
    private final Provider<OkHttpClient> okHttpClientProvider;
    private final Provider<OnfidoTokenProvider> tokenProvider;
    private final Provider<UserConsentApi> userConsentApiProvider;

    public UserConsentRepository_Factory(Provider<OkHttpClient> provider, Provider<UserConsentApi> provider2, Provider<OnfidoTokenProvider> provider3) {
        this.okHttpClientProvider = provider;
        this.userConsentApiProvider = provider2;
        this.tokenProvider = provider3;
    }

    public static UserConsentRepository_Factory create(Provider<OkHttpClient> provider, Provider<UserConsentApi> provider2, Provider<OnfidoTokenProvider> provider3) {
        return new UserConsentRepository_Factory(provider, provider2, provider3);
    }

    public static UserConsentRepository newInstance(OkHttpClient okHttpClient, UserConsentApi userConsentApi, OnfidoTokenProvider onfidoTokenProvider) {
        return new UserConsentRepository(okHttpClient, userConsentApi, onfidoTokenProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public UserConsentRepository get() {
        return newInstance(this.okHttpClientProvider.get(), this.userConsentApiProvider.get(), this.tokenProvider.get());
    }
}
