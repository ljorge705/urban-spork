package com.onfido.android.sdk.capture.common.di;

import com.onfido.api.client.token.Token;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideOnfidoTokenFactory implements Factory<Token> {
    private final SdkModule module;

    public SdkModule_ProvideOnfidoTokenFactory(SdkModule sdkModule) {
        this.module = sdkModule;
    }

    public static SdkModule_ProvideOnfidoTokenFactory create(SdkModule sdkModule) {
        return new SdkModule_ProvideOnfidoTokenFactory(sdkModule);
    }

    public static Token provideOnfidoToken(SdkModule sdkModule) {
        return (Token) Preconditions.checkNotNullFromProvides(sdkModule.provideOnfidoToken());
    }

    @Override // com.onfido.javax.inject.Provider
    public Token get() {
        return provideOnfidoToken(this.module);
    }
}
