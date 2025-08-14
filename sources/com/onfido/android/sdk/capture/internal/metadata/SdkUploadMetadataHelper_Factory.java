package com.onfido.android.sdk.capture.internal.metadata;

import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.environment.EnvironmentIntegrityChecker;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkUploadMetadataHelper_Factory implements Factory<SdkUploadMetadataHelper> {
    private final Provider<EnvironmentIntegrityChecker> environmentIntegrityCheckerProvider;
    private final Provider<IdentityInteractor> identityInteractorProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;

    public SdkUploadMetadataHelper_Factory(Provider<IdentityInteractor> provider, Provider<OnfidoRemoteConfig> provider2, Provider<EnvironmentIntegrityChecker> provider3) {
        this.identityInteractorProvider = provider;
        this.onfidoRemoteConfigProvider = provider2;
        this.environmentIntegrityCheckerProvider = provider3;
    }

    public static SdkUploadMetadataHelper_Factory create(Provider<IdentityInteractor> provider, Provider<OnfidoRemoteConfig> provider2, Provider<EnvironmentIntegrityChecker> provider3) {
        return new SdkUploadMetadataHelper_Factory(provider, provider2, provider3);
    }

    public static SdkUploadMetadataHelper newInstance(IdentityInteractor identityInteractor, OnfidoRemoteConfig onfidoRemoteConfig, EnvironmentIntegrityChecker environmentIntegrityChecker) {
        return new SdkUploadMetadataHelper(identityInteractor, onfidoRemoteConfig, environmentIntegrityChecker);
    }

    @Override // com.onfido.javax.inject.Provider
    public SdkUploadMetadataHelper get() {
        return newInstance(this.identityInteractorProvider.get(), this.onfidoRemoteConfigProvider.get(), this.environmentIntegrityCheckerProvider.get());
    }
}
