package com.onfido.hosted.web.module.externallink;

import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import okhttp3.OkHttpClient;

/* loaded from: classes6.dex */
public final class HostedWebModuleExternalLinkRepositoryImpl_Factory implements Factory<HostedWebModuleExternalLinkRepositoryImpl> {
    private final Provider<OkHttpClient> okHttpClientProvider;

    public HostedWebModuleExternalLinkRepositoryImpl_Factory(Provider<OkHttpClient> provider) {
        this.okHttpClientProvider = provider;
    }

    public static HostedWebModuleExternalLinkRepositoryImpl_Factory create(Provider<OkHttpClient> provider) {
        return new HostedWebModuleExternalLinkRepositoryImpl_Factory(provider);
    }

    public static HostedWebModuleExternalLinkRepositoryImpl newInstance(OkHttpClient okHttpClient) {
        return new HostedWebModuleExternalLinkRepositoryImpl(okHttpClient);
    }

    @Override // com.onfido.javax.inject.Provider
    public HostedWebModuleExternalLinkRepositoryImpl get() {
        return newInstance(this.okHttpClientProvider.get());
    }
}
