package com.onfido.android.sdk.capture.internal.validation.backend;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class IQSValidations_Factory implements Factory<IQSValidations> {
    private final Provider<NativeDetector> nativeDetectorProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;

    public IQSValidations_Factory(Provider<NativeDetector> provider, Provider<OnfidoRemoteConfig> provider2) {
        this.nativeDetectorProvider = provider;
        this.onfidoRemoteConfigProvider = provider2;
    }

    public static IQSValidations_Factory create(Provider<NativeDetector> provider, Provider<OnfidoRemoteConfig> provider2) {
        return new IQSValidations_Factory(provider, provider2);
    }

    public static IQSValidations newInstance(NativeDetector nativeDetector, OnfidoRemoteConfig onfidoRemoteConfig) {
        return new IQSValidations(nativeDetector, onfidoRemoteConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public IQSValidations get() {
        return newInstance(this.nativeDetectorProvider.get(), this.onfidoRemoteConfigProvider.get());
    }
}
