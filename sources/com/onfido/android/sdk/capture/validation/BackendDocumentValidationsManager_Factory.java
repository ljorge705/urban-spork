package com.onfido.android.sdk.capture.validation;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.validation.backend.DefaultBackendValidations;
import com.onfido.android.sdk.capture.internal.validation.backend.IQSValidations;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class BackendDocumentValidationsManager_Factory implements Factory<BackendDocumentValidationsManager> {
    private final Provider<DefaultBackendValidations> defaultValidationsProvider;
    private final Provider<IQSValidations> iQSValidationsProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;

    public BackendDocumentValidationsManager_Factory(Provider<DefaultBackendValidations> provider, Provider<IQSValidations> provider2, Provider<OnfidoRemoteConfig> provider3) {
        this.defaultValidationsProvider = provider;
        this.iQSValidationsProvider = provider2;
        this.onfidoRemoteConfigProvider = provider3;
    }

    public static BackendDocumentValidationsManager_Factory create(Provider<DefaultBackendValidations> provider, Provider<IQSValidations> provider2, Provider<OnfidoRemoteConfig> provider3) {
        return new BackendDocumentValidationsManager_Factory(provider, provider2, provider3);
    }

    public static BackendDocumentValidationsManager newInstance(DefaultBackendValidations defaultBackendValidations, IQSValidations iQSValidations, OnfidoRemoteConfig onfidoRemoteConfig) {
        return new BackendDocumentValidationsManager(defaultBackendValidations, iQSValidations, onfidoRemoteConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public BackendDocumentValidationsManager get() {
        return newInstance(this.defaultValidationsProvider.get(), this.iQSValidationsProvider.get(), this.onfidoRemoteConfigProvider.get());
    }
}
