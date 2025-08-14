package com.onfido.android.sdk.capture.internal.camera.usecase;

import com.onfido.android.sdk.capture.internal.util.Dimension;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class GetOptimalFourByThreePictureResolutionUseCase_Factory {
    private final Provider<Dimension> previewDimensionProvider;
    private final Provider<Double> screenHeightToFrameHeightRatioProvider;

    public GetOptimalFourByThreePictureResolutionUseCase_Factory(Provider<Double> provider, Provider<Dimension> provider2) {
        this.screenHeightToFrameHeightRatioProvider = provider;
        this.previewDimensionProvider = provider2;
    }

    public static GetOptimalFourByThreePictureResolutionUseCase_Factory create(Provider<Double> provider, Provider<Dimension> provider2) {
        return new GetOptimalFourByThreePictureResolutionUseCase_Factory(provider, provider2);
    }

    public static GetOptimalFourByThreePictureResolutionUseCase newInstance(double d, Dimension dimension) {
        return new GetOptimalFourByThreePictureResolutionUseCase(d, dimension);
    }

    public GetOptimalFourByThreePictureResolutionUseCase get() {
        return newInstance(this.screenHeightToFrameHeightRatioProvider.get().doubleValue(), this.previewDimensionProvider.get());
    }
}
