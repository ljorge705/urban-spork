package com.onfido.android.sdk.capture.detector.rectangle;

import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class RectangleDetectorGoogle_Factory implements Factory<RectangleDetectorGoogle> {
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<RectangleTransformer> transformerProvider;

    public RectangleDetectorGoogle_Factory(Provider<RectangleTransformer> provider, Provider<SchedulersProvider> provider2) {
        this.transformerProvider = provider;
        this.schedulersProvider = provider2;
    }

    public static RectangleDetectorGoogle_Factory create(Provider<RectangleTransformer> provider, Provider<SchedulersProvider> provider2) {
        return new RectangleDetectorGoogle_Factory(provider, provider2);
    }

    public static RectangleDetectorGoogle newInstance(RectangleTransformer rectangleTransformer, SchedulersProvider schedulersProvider) {
        return new RectangleDetectorGoogle(rectangleTransformer, schedulersProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public RectangleDetectorGoogle get() {
        return newInstance(this.transformerProvider.get(), this.schedulersProvider.get());
    }
}
