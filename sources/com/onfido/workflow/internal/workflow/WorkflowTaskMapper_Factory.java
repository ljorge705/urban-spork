package com.onfido.workflow.internal.workflow;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;

/* loaded from: classes6.dex */
public final class WorkflowTaskMapper_Factory implements Factory<WorkflowTaskMapper> {
    private final Provider<Json> jsonParserProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<OnfidoSupportedDocumentsRepository> supportedDocumentsRepositoryProvider;

    public WorkflowTaskMapper_Factory(Provider<OnfidoSupportedDocumentsRepository> provider, Provider<OnfidoRemoteConfig> provider2, Provider<Json> provider3) {
        this.supportedDocumentsRepositoryProvider = provider;
        this.onfidoRemoteConfigProvider = provider2;
        this.jsonParserProvider = provider3;
    }

    @Override // com.onfido.javax.inject.Provider
    public WorkflowTaskMapper get() {
        return newInstance(this.supportedDocumentsRepositoryProvider.get(), this.onfidoRemoteConfigProvider.get(), this.jsonParserProvider.get());
    }

    public static WorkflowTaskMapper_Factory create(Provider<OnfidoSupportedDocumentsRepository> provider, Provider<OnfidoRemoteConfig> provider2, Provider<Json> provider3) {
        return new WorkflowTaskMapper_Factory(provider, provider2, provider3);
    }

    public static WorkflowTaskMapper newInstance(OnfidoSupportedDocumentsRepository onfidoSupportedDocumentsRepository, OnfidoRemoteConfig onfidoRemoteConfig, Json json) {
        return new WorkflowTaskMapper(onfidoSupportedDocumentsRepository, onfidoRemoteConfig, json);
    }
}
