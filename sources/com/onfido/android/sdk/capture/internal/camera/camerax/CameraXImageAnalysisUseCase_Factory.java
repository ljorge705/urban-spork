package com.onfido.android.sdk.capture.internal.camera.camerax;

import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class CameraXImageAnalysisUseCase_Factory implements Factory<CameraXImageAnalysisUseCase> {
    private final Provider<TimeProvider> timeProvider;

    public CameraXImageAnalysisUseCase_Factory(Provider<TimeProvider> provider) {
        this.timeProvider = provider;
    }

    public static CameraXImageAnalysisUseCase_Factory create(Provider<TimeProvider> provider) {
        return new CameraXImageAnalysisUseCase_Factory(provider);
    }

    public static CameraXImageAnalysisUseCase newInstance(TimeProvider timeProvider) {
        return new CameraXImageAnalysisUseCase(timeProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public CameraXImageAnalysisUseCase get() {
        return newInstance(this.timeProvider.get());
    }
}
