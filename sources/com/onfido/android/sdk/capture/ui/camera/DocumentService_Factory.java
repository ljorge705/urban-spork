package com.onfido.android.sdk.capture.ui.camera;

import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DocumentService_Factory implements Factory<DocumentService> {
    private final Provider<OnfidoApiService> apiServiceProvider;

    public DocumentService_Factory(Provider<OnfidoApiService> provider) {
        this.apiServiceProvider = provider;
    }

    public static DocumentService_Factory create(Provider<OnfidoApiService> provider) {
        return new DocumentService_Factory(provider);
    }

    public static DocumentService newInstance(OnfidoApiService onfidoApiService) {
        return new DocumentService(onfidoApiService);
    }

    @Override // com.onfido.javax.inject.Provider
    public DocumentService get() {
        return newInstance(this.apiServiceProvider.get());
    }
}
