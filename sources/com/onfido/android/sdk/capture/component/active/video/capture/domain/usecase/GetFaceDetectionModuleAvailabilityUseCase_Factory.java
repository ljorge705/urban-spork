package com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase;

import com.google.android.gms.common.moduleinstall.ModuleInstallClient;
import com.google.mlkit.vision.face.FaceDetector;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class GetFaceDetectionModuleAvailabilityUseCase_Factory implements Factory<GetFaceDetectionModuleAvailabilityUseCase> {
    private final Provider<FaceDetector> mlKitFaceDetectorProvider;
    private final Provider<ModuleInstallClient> moduleInstallClientProvider;

    public GetFaceDetectionModuleAvailabilityUseCase_Factory(Provider<ModuleInstallClient> provider, Provider<FaceDetector> provider2) {
        this.moduleInstallClientProvider = provider;
        this.mlKitFaceDetectorProvider = provider2;
    }

    public static GetFaceDetectionModuleAvailabilityUseCase_Factory create(Provider<ModuleInstallClient> provider, Provider<FaceDetector> provider2) {
        return new GetFaceDetectionModuleAvailabilityUseCase_Factory(provider, provider2);
    }

    public static GetFaceDetectionModuleAvailabilityUseCase newInstance(ModuleInstallClient moduleInstallClient, FaceDetector faceDetector) {
        return new GetFaceDetectionModuleAvailabilityUseCase(moduleInstallClient, faceDetector);
    }

    @Override // com.onfido.javax.inject.Provider
    public GetFaceDetectionModuleAvailabilityUseCase get() {
        return newInstance(this.moduleInstallClientProvider.get(), this.mlKitFaceDetectorProvider.get());
    }
}
