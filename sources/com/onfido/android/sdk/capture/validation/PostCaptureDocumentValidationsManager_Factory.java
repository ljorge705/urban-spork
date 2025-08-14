package com.onfido.android.sdk.capture.validation;

import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.validation.RetainableValidationsResult;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PostCaptureDocumentValidationsManager_Factory implements Factory<PostCaptureDocumentValidationsManager> {
    private final Provider<BarcodeValidationSuspender> barcodeValidationSuspenderProvider;
    private final Provider<IdentityInteractor> identityInteractorProvider;
    private final Provider<OnDeviceValidationTransformer> onDeviceValidationTransformerProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<RetainableValidationsResult> retainableValidationsResultProvider;

    public PostCaptureDocumentValidationsManager_Factory(Provider<IdentityInteractor> provider, Provider<OnDeviceValidationTransformer> provider2, Provider<RetainableValidationsResult> provider3, Provider<BarcodeValidationSuspender> provider4, Provider<OnfidoRemoteConfig> provider5) {
        this.identityInteractorProvider = provider;
        this.onDeviceValidationTransformerProvider = provider2;
        this.retainableValidationsResultProvider = provider3;
        this.barcodeValidationSuspenderProvider = provider4;
        this.onfidoRemoteConfigProvider = provider5;
    }

    public static PostCaptureDocumentValidationsManager_Factory create(Provider<IdentityInteractor> provider, Provider<OnDeviceValidationTransformer> provider2, Provider<RetainableValidationsResult> provider3, Provider<BarcodeValidationSuspender> provider4, Provider<OnfidoRemoteConfig> provider5) {
        return new PostCaptureDocumentValidationsManager_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static PostCaptureDocumentValidationsManager newInstance(IdentityInteractor identityInteractor, OnDeviceValidationTransformer onDeviceValidationTransformer, RetainableValidationsResult retainableValidationsResult, BarcodeValidationSuspender barcodeValidationSuspender, OnfidoRemoteConfig onfidoRemoteConfig) {
        return new PostCaptureDocumentValidationsManager(identityInteractor, onDeviceValidationTransformer, retainableValidationsResult, barcodeValidationSuspender, onfidoRemoteConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public PostCaptureDocumentValidationsManager get() {
        return newInstance(this.identityInteractorProvider.get(), this.onDeviceValidationTransformerProvider.get(), this.retainableValidationsResultProvider.get(), this.barcodeValidationSuspenderProvider.get(), this.onfidoRemoteConfigProvider.get());
    }
}
