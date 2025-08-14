package com.onfido.android.sdk.capture.ui.camera.rx;

import com.onfido.android.sdk.capture.document.DocumentConfigurationManager;
import com.onfido.android.sdk.capture.internal.usecase.DocumentProcessingUseCase;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DocumentCaptureDelayTransformer_Factory implements Factory<DocumentCaptureDelayTransformer> {
    private final Provider<DocumentConfigurationManager> documentConfigurationManagerProvider;
    private final Provider<DocumentProcessingUseCase> documentProcessingUseCaseProvider;
    private final Provider<NativeDetector> nativeDetectorProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public DocumentCaptureDelayTransformer_Factory(Provider<NativeDetector> provider, Provider<DocumentConfigurationManager> provider2, Provider<SchedulersProvider> provider3, Provider<DocumentProcessingUseCase> provider4) {
        this.nativeDetectorProvider = provider;
        this.documentConfigurationManagerProvider = provider2;
        this.schedulersProvider = provider3;
        this.documentProcessingUseCaseProvider = provider4;
    }

    public static DocumentCaptureDelayTransformer_Factory create(Provider<NativeDetector> provider, Provider<DocumentConfigurationManager> provider2, Provider<SchedulersProvider> provider3, Provider<DocumentProcessingUseCase> provider4) {
        return new DocumentCaptureDelayTransformer_Factory(provider, provider2, provider3, provider4);
    }

    public static DocumentCaptureDelayTransformer newInstance(NativeDetector nativeDetector, DocumentConfigurationManager documentConfigurationManager, SchedulersProvider schedulersProvider, DocumentProcessingUseCase documentProcessingUseCase) {
        return new DocumentCaptureDelayTransformer(nativeDetector, documentConfigurationManager, schedulersProvider, documentProcessingUseCase);
    }

    @Override // com.onfido.javax.inject.Provider
    public DocumentCaptureDelayTransformer get() {
        return newInstance(this.nativeDetectorProvider.get(), this.documentConfigurationManagerProvider.get(), this.schedulersProvider.get(), this.documentProcessingUseCaseProvider.get());
    }
}
