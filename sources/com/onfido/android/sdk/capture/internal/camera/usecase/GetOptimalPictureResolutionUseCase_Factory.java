package com.onfido.android.sdk.capture.internal.camera.usecase;

import com.onfido.android.sdk.capture.internal.util.Dimension;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class GetOptimalPictureResolutionUseCase_Factory {
    private final Provider<Dimension> previewDimensionProvider;
    private final Provider<Double> screenHeightToFrameHeightRatioProvider;

    public GetOptimalPictureResolutionUseCase_Factory(Provider<Double> provider, Provider<Dimension> provider2) {
        this.screenHeightToFrameHeightRatioProvider = provider;
        this.previewDimensionProvider = provider2;
    }

    public static GetOptimalPictureResolutionUseCase_Factory create(Provider<Double> provider, Provider<Dimension> provider2) {
        return new GetOptimalPictureResolutionUseCase_Factory(provider, provider2);
    }

    public static GetOptimalPictureResolutionUseCase newInstance(double d, Dimension dimension) {
        return new GetOptimalPictureResolutionUseCase(d, dimension);
    }

    public GetOptimalPictureResolutionUseCase get() {
        return newInstance(this.screenHeightToFrameHeightRatioProvider.get().doubleValue(), this.previewDimensionProvider.get());
    }
}
