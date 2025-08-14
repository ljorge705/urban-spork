package com.onfido.android.sdk.capture.common.di;

import android.os.ResultReceiver;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideMediaCallbackFactory implements Factory<ResultReceiver> {
    private final SdkModule module;
    private final Provider<OnfidoConfig> onfidoConfigProvider;

    public SdkModule_ProvideMediaCallbackFactory(SdkModule sdkModule, Provider<OnfidoConfig> provider) {
        this.module = sdkModule;
        this.onfidoConfigProvider = provider;
    }

    public static SdkModule_ProvideMediaCallbackFactory create(SdkModule sdkModule, Provider<OnfidoConfig> provider) {
        return new SdkModule_ProvideMediaCallbackFactory(sdkModule, provider);
    }

    public static ResultReceiver provideMediaCallback(SdkModule sdkModule, OnfidoConfig onfidoConfig) {
        return sdkModule.provideMediaCallback(onfidoConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public ResultReceiver get() {
        return provideMediaCallback(this.module, this.onfidoConfigProvider.get());
    }
}
