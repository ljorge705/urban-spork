package com.onfido.android.sdk.capture.network;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.internal.metadata.SdkUploadMetadataHelper;
import com.onfido.android.sdk.capture.token.TokenExpirationServiceConnector;
import com.onfido.api.client.OnfidoAPI;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class OnfidoApiService_Factory implements Factory<OnfidoApiService> {
    private final Provider<IdentityInteractor> identityInteractorProvider;
    private final Provider<OnfidoAPI> onfidoApiProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;
    private final Provider<SdkUploadMetadataHelper> sdkUploadMetadataHelperProvider;
    private final Provider<TokenExpirationServiceConnector> tokenExpirationServiceConnetorProvider;

    public OnfidoApiService_Factory(Provider<OnfidoAPI> provider, Provider<IdentityInteractor> provider2, Provider<OnfidoConfig> provider3, Provider<SdkUploadMetadataHelper> provider4, Provider<TokenExpirationServiceConnector> provider5) {
        this.onfidoApiProvider = provider;
        this.identityInteractorProvider = provider2;
        this.onfidoConfigProvider = provider3;
        this.sdkUploadMetadataHelperProvider = provider4;
        this.tokenExpirationServiceConnetorProvider = provider5;
    }

    public static OnfidoApiService_Factory create(Provider<OnfidoAPI> provider, Provider<IdentityInteractor> provider2, Provider<OnfidoConfig> provider3, Provider<SdkUploadMetadataHelper> provider4, Provider<TokenExpirationServiceConnector> provider5) {
        return new OnfidoApiService_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static OnfidoApiService newInstance(OnfidoAPI onfidoAPI, IdentityInteractor identityInteractor, OnfidoConfig onfidoConfig, SdkUploadMetadataHelper sdkUploadMetadataHelper, TokenExpirationServiceConnector tokenExpirationServiceConnector) {
        return new OnfidoApiService(onfidoAPI, identityInteractor, onfidoConfig, sdkUploadMetadataHelper, tokenExpirationServiceConnector);
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoApiService get() {
        return newInstance(this.onfidoApiProvider.get(), this.identityInteractorProvider.get(), this.onfidoConfigProvider.get(), this.sdkUploadMetadataHelperProvider.get(), this.tokenExpirationServiceConnetorProvider.get());
    }
}
