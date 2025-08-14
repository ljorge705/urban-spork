package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideCustomerUserHashFactory implements Factory<String> {
    private final SdkModule module;
    private final Provider<OnfidoTokenProvider> tokenProvider;

    public SdkModule_ProvideCustomerUserHashFactory(SdkModule sdkModule, Provider<OnfidoTokenProvider> provider) {
        this.module = sdkModule;
        this.tokenProvider = provider;
    }

    public static SdkModule_ProvideCustomerUserHashFactory create(SdkModule sdkModule, Provider<OnfidoTokenProvider> provider) {
        return new SdkModule_ProvideCustomerUserHashFactory(sdkModule, provider);
    }

    public static String provideCustomerUserHash(SdkModule sdkModule, OnfidoTokenProvider onfidoTokenProvider) {
        return (String) Preconditions.checkNotNullFromProvides(sdkModule.provideCustomerUserHash(onfidoTokenProvider));
    }

    @Override // com.onfido.javax.inject.Provider
    public String get() {
        return provideCustomerUserHash(this.module, this.tokenProvider.get());
    }
}
