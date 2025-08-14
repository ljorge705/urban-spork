package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.document.supported.data.repository.RemoteSupportedDocumentsRepository;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.ui.countryselection.OnfidoSupportedDocumentsRepository;
import com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository;
import com.onfido.android.sdk.workflow.internal.workflow.tasks.documentupload.WorkflowSupportedDocumentsRepository;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideSupportedDocumentsRepository$onfido_capture_sdk_core_releaseFactory implements Factory<SupportedDocumentsRepository> {
    private final SdkModule module;
    private final Provider<OnfidoConfig> onfidoConfigProvider;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;
    private final Provider<RemoteSupportedDocumentsRepository> remoteSupportedDocumentsRepositoryProvider;
    private final Provider<OnfidoSupportedDocumentsRepository> supportedDocumentsRepositoryProvider;
    private final Provider<WorkflowSupportedDocumentsRepository> workflowSupportedDocumentsRepositoryProvider;

    public SdkModule_ProvideSupportedDocumentsRepository$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule, Provider<OnfidoSupportedDocumentsRepository> provider, Provider<WorkflowSupportedDocumentsRepository> provider2, Provider<RemoteSupportedDocumentsRepository> provider3, Provider<OnfidoRemoteConfig> provider4, Provider<OnfidoConfig> provider5) {
        this.module = sdkModule;
        this.supportedDocumentsRepositoryProvider = provider;
        this.workflowSupportedDocumentsRepositoryProvider = provider2;
        this.remoteSupportedDocumentsRepositoryProvider = provider3;
        this.remoteConfigProvider = provider4;
        this.onfidoConfigProvider = provider5;
    }

    public static SdkModule_ProvideSupportedDocumentsRepository$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule, Provider<OnfidoSupportedDocumentsRepository> provider, Provider<WorkflowSupportedDocumentsRepository> provider2, Provider<RemoteSupportedDocumentsRepository> provider3, Provider<OnfidoRemoteConfig> provider4, Provider<OnfidoConfig> provider5) {
        return new SdkModule_ProvideSupportedDocumentsRepository$onfido_capture_sdk_core_releaseFactory(sdkModule, provider, provider2, provider3, provider4, provider5);
    }

    public static SupportedDocumentsRepository provideSupportedDocumentsRepository$onfido_capture_sdk_core_release(SdkModule sdkModule, Provider<OnfidoSupportedDocumentsRepository> provider, Provider<WorkflowSupportedDocumentsRepository> provider2, Provider<RemoteSupportedDocumentsRepository> provider3, OnfidoRemoteConfig onfidoRemoteConfig, OnfidoConfig onfidoConfig) {
        return (SupportedDocumentsRepository) Preconditions.checkNotNullFromProvides(sdkModule.provideSupportedDocumentsRepository$onfido_capture_sdk_core_release(provider, provider2, provider3, onfidoRemoteConfig, onfidoConfig));
    }

    @Override // com.onfido.javax.inject.Provider
    public SupportedDocumentsRepository get() {
        return provideSupportedDocumentsRepository$onfido_capture_sdk_core_release(this.module, this.supportedDocumentsRepositoryProvider, this.workflowSupportedDocumentsRepositoryProvider, this.remoteSupportedDocumentsRepositoryProvider, this.remoteConfigProvider.get(), this.onfidoConfigProvider.get());
    }
}
