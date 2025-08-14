package com.onfido.android.sdk.capture.common.di;

import android.os.ResultReceiver;
import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideOnfidoAnalyticsEventListenerFactory implements Factory<ResultReceiver> {
    private final SdkModule module;
    private final Provider<OnfidoConfig> onfidoConfigProvider;

    public SdkModule_ProvideOnfidoAnalyticsEventListenerFactory(SdkModule sdkModule, Provider<OnfidoConfig> provider) {
        this.module = sdkModule;
        this.onfidoConfigProvider = provider;
    }

    public static SdkModule_ProvideOnfidoAnalyticsEventListenerFactory create(SdkModule sdkModule, Provider<OnfidoConfig> provider) {
        return new SdkModule_ProvideOnfidoAnalyticsEventListenerFactory(sdkModule, provider);
    }

    public static ResultReceiver provideOnfidoAnalyticsEventListener(SdkModule sdkModule, OnfidoConfig onfidoConfig) {
        return sdkModule.provideOnfidoAnalyticsEventListener(onfidoConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public ResultReceiver get() {
        return provideOnfidoAnalyticsEventListener(this.module, this.onfidoConfigProvider.get());
    }
}
