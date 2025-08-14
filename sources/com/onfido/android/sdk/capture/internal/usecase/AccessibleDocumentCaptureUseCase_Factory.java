package com.onfido.android.sdk.capture.internal.usecase;

import android.content.Context;
import com.onfido.android.sdk.capture.internal.service.VibratorService;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class AccessibleDocumentCaptureUseCase_Factory implements Factory<AccessibleDocumentCaptureUseCase> {
    private final Provider<Context> contextProvider;
    private final Provider<DocumentPositionUseCase> documentPositionUseCaseProvider;
    private final Provider<NativeDetector> nativeDetectorProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<VibratorService> vibratorServiceProvider;

    public AccessibleDocumentCaptureUseCase_Factory(Provider<DocumentPositionUseCase> provider, Provider<SchedulersProvider> provider2, Provider<NativeDetector> provider3, Provider<VibratorService> provider4, Provider<Context> provider5) {
        this.documentPositionUseCaseProvider = provider;
        this.schedulersProvider = provider2;
        this.nativeDetectorProvider = provider3;
        this.vibratorServiceProvider = provider4;
        this.contextProvider = provider5;
    }

    public static AccessibleDocumentCaptureUseCase_Factory create(Provider<DocumentPositionUseCase> provider, Provider<SchedulersProvider> provider2, Provider<NativeDetector> provider3, Provider<VibratorService> provider4, Provider<Context> provider5) {
        return new AccessibleDocumentCaptureUseCase_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static AccessibleDocumentCaptureUseCase newInstance(DocumentPositionUseCase documentPositionUseCase, SchedulersProvider schedulersProvider, NativeDetector nativeDetector, VibratorService vibratorService, Context context) {
        return new AccessibleDocumentCaptureUseCase(documentPositionUseCase, schedulersProvider, nativeDetector, vibratorService, context);
    }

    @Override // com.onfido.javax.inject.Provider
    public AccessibleDocumentCaptureUseCase get() {
        return newInstance(this.documentPositionUseCaseProvider.get(), this.schedulersProvider.get(), this.nativeDetectorProvider.get(), this.vibratorServiceProvider.get(), this.contextProvider.get());
    }
}
