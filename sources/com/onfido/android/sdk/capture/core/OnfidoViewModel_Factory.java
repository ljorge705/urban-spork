package com.onfido.android.sdk.capture.core;

import com.onfido.android.sdk.FlowConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class OnfidoViewModel_Factory implements Factory<OnfidoViewModel> {
    private final Provider<FlowConfig> flowConfigProvider;

    public OnfidoViewModel_Factory(Provider<FlowConfig> provider) {
        this.flowConfigProvider = provider;
    }

    public static OnfidoViewModel_Factory create(Provider<FlowConfig> provider) {
        return new OnfidoViewModel_Factory(provider);
    }

    public static OnfidoViewModel newInstance(FlowConfig flowConfig) {
        return new OnfidoViewModel(flowConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoViewModel get() {
        return newInstance(this.flowConfigProvider.get());
    }
}
