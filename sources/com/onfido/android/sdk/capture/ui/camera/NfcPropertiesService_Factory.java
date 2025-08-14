package com.onfido.android.sdk.capture.ui.camera;

import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcPropertiesService_Factory implements Factory<NfcPropertiesService> {
    private final Provider<OnfidoApiService> apiServiceProvider;

    public NfcPropertiesService_Factory(Provider<OnfidoApiService> provider) {
        this.apiServiceProvider = provider;
    }

    public static NfcPropertiesService_Factory create(Provider<OnfidoApiService> provider) {
        return new NfcPropertiesService_Factory(provider);
    }

    public static NfcPropertiesService newInstance(OnfidoApiService onfidoApiService) {
        return new NfcPropertiesService(onfidoApiService);
    }

    @Override // com.onfido.javax.inject.Provider
    public NfcPropertiesService get() {
        return newInstance(this.apiServiceProvider.get());
    }
}
