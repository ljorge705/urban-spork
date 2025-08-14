package com.onfido.android.sdk.capture.internal.usecase.validation;

import com.onfido.android.sdk.capture.detector.rectangle.RectangleTransformer;
import com.onfido.android.sdk.capture.internal.usecase.DocumentPositionUseCase;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.android.sdk.capture.utils.mlmodel.OnfidoMlModelProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DocumentValidationUseCase_Factory implements Factory<DocumentValidationUseCase> {
    private final Provider<DispatchersProvider> dispatchersProvider;
    private final Provider<DocumentPositionUseCase> documentPositionUseCaseProvider;
    private final Provider<NativeDetector> nativeDetectorProvider;
    private final Provider<OnfidoMlModelProvider> onfidoMlModelProvider;
    private final Provider<TimeProvider> timeProvider;
    private final Provider<RectangleTransformer> transformerProvider;

    public DocumentValidationUseCase_Factory(Provider<OnfidoMlModelProvider> provider, Provider<RectangleTransformer> provider2, Provider<DocumentPositionUseCase> provider3, Provider<NativeDetector> provider4, Provider<DispatchersProvider> provider5, Provider<TimeProvider> provider6) {
        this.onfidoMlModelProvider = provider;
        this.transformerProvider = provider2;
        this.documentPositionUseCaseProvider = provider3;
        this.nativeDetectorProvider = provider4;
        this.dispatchersProvider = provider5;
        this.timeProvider = provider6;
    }

    public static DocumentValidationUseCase_Factory create(Provider<OnfidoMlModelProvider> provider, Provider<RectangleTransformer> provider2, Provider<DocumentPositionUseCase> provider3, Provider<NativeDetector> provider4, Provider<DispatchersProvider> provider5, Provider<TimeProvider> provider6) {
        return new DocumentValidationUseCase_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static DocumentValidationUseCase newInstance(OnfidoMlModelProvider onfidoMlModelProvider, RectangleTransformer rectangleTransformer, DocumentPositionUseCase documentPositionUseCase, NativeDetector nativeDetector, DispatchersProvider dispatchersProvider, TimeProvider timeProvider) {
        return new DocumentValidationUseCase(onfidoMlModelProvider, rectangleTransformer, documentPositionUseCase, nativeDetector, dispatchersProvider, timeProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public DocumentValidationUseCase get() {
        return newInstance(this.onfidoMlModelProvider.get(), this.transformerProvider.get(), this.documentPositionUseCaseProvider.get(), this.nativeDetectorProvider.get(), this.dispatchersProvider.get(), this.timeProvider.get());
    }
}
