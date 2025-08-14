package com.onfido.android.sdk.capture.component.active.video.capture.di.host;

import com.google.android.gms.common.moduleinstall.ModuleInstallClient;
import com.google.mlkit.vision.face.FaceDetector;
import com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase.GetFaceDetectionModuleAvailabilityUseCase;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionHostModule_ProvideFaceDetectionModuleAvailabilityUseCaseFactory implements Factory<GetFaceDetectionModuleAvailabilityUseCase> {
    private final Provider<ModuleInstallClient> installClientProvider;
    private final Provider<FaceDetector> mlKitFaceDetectorProvider;
    private final MotionHostModule module;

    public MotionHostModule_ProvideFaceDetectionModuleAvailabilityUseCaseFactory(MotionHostModule motionHostModule, Provider<ModuleInstallClient> provider, Provider<FaceDetector> provider2) {
        this.module = motionHostModule;
        this.installClientProvider = provider;
        this.mlKitFaceDetectorProvider = provider2;
    }

    public static MotionHostModule_ProvideFaceDetectionModuleAvailabilityUseCaseFactory create(MotionHostModule motionHostModule, Provider<ModuleInstallClient> provider, Provider<FaceDetector> provider2) {
        return new MotionHostModule_ProvideFaceDetectionModuleAvailabilityUseCaseFactory(motionHostModule, provider, provider2);
    }

    public static GetFaceDetectionModuleAvailabilityUseCase provideFaceDetectionModuleAvailabilityUseCase(MotionHostModule motionHostModule, ModuleInstallClient moduleInstallClient, FaceDetector faceDetector) {
        return (GetFaceDetectionModuleAvailabilityUseCase) Preconditions.checkNotNullFromProvides(motionHostModule.provideFaceDetectionModuleAvailabilityUseCase(moduleInstallClient, faceDetector));
    }

    @Override // com.onfido.javax.inject.Provider
    public GetFaceDetectionModuleAvailabilityUseCase get() {
        return provideFaceDetectionModuleAvailabilityUseCase(this.module, this.installClientProvider.get(), this.mlKitFaceDetectorProvider.get());
    }
}
