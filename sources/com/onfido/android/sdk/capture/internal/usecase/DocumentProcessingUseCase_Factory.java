package com.onfido.android.sdk.capture.internal.usecase;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.validation.RetainableValidationsResult;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.validation.BarcodeValidationSuspender;
import com.onfido.android.sdk.capture.validation.OnDeviceValidationTransformer;
import com.onfido.android.sdk.capture.validation.RealTimeDocumentValidationsManager;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DocumentProcessingUseCase_Factory implements Factory<DocumentProcessingUseCase> {
    private final Provider<BarcodeValidationSuspender> barcodeValidationSuspenderProvider;
    private final Provider<NativeDetector> nativeDetectorProvider;
    private final Provider<OnDeviceValidationTransformer> onDeviceValidationTransformerProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<RealTimeDocumentValidationsManager> realTimeDocumentValidationsManagerProvider;
    private final Provider<RetainableValidationsResult> retainableValidationsResultProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public DocumentProcessingUseCase_Factory(Provider<NativeDetector> provider, Provider<RealTimeDocumentValidationsManager> provider2, Provider<OnDeviceValidationTransformer> provider3, Provider<RetainableValidationsResult> provider4, Provider<BarcodeValidationSuspender> provider5, Provider<SchedulersProvider> provider6, Provider<OnfidoRemoteConfig> provider7) {
        this.nativeDetectorProvider = provider;
        this.realTimeDocumentValidationsManagerProvider = provider2;
        this.onDeviceValidationTransformerProvider = provider3;
        this.retainableValidationsResultProvider = provider4;
        this.barcodeValidationSuspenderProvider = provider5;
        this.schedulersProvider = provider6;
        this.onfidoRemoteConfigProvider = provider7;
    }

    public static DocumentProcessingUseCase_Factory create(Provider<NativeDetector> provider, Provider<RealTimeDocumentValidationsManager> provider2, Provider<OnDeviceValidationTransformer> provider3, Provider<RetainableValidationsResult> provider4, Provider<BarcodeValidationSuspender> provider5, Provider<SchedulersProvider> provider6, Provider<OnfidoRemoteConfig> provider7) {
        return new DocumentProcessingUseCase_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static DocumentProcessingUseCase newInstance(NativeDetector nativeDetector, RealTimeDocumentValidationsManager realTimeDocumentValidationsManager, OnDeviceValidationTransformer onDeviceValidationTransformer, RetainableValidationsResult retainableValidationsResult, BarcodeValidationSuspender barcodeValidationSuspender, SchedulersProvider schedulersProvider, OnfidoRemoteConfig onfidoRemoteConfig) {
        return new DocumentProcessingUseCase(nativeDetector, realTimeDocumentValidationsManager, onDeviceValidationTransformer, retainableValidationsResult, barcodeValidationSuspender, schedulersProvider, onfidoRemoteConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public DocumentProcessingUseCase get() {
        return newInstance(this.nativeDetectorProvider.get(), this.realTimeDocumentValidationsManagerProvider.get(), this.onDeviceValidationTransformerProvider.get(), this.retainableValidationsResultProvider.get(), this.barcodeValidationSuspenderProvider.get(), this.schedulersProvider.get(), this.onfidoRemoteConfigProvider.get());
    }
}
