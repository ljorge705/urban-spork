package com.onfido.android.sdk.capture.internal.util.environment;

import android.content.Context;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class EnvironmentIntegrityCheckerImpl_Factory implements Factory<EnvironmentIntegrityCheckerImpl> {
    private final Provider<Context> contextProvider;
    private final Provider<NativeDetector> nativeDetectorProvider;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;

    public EnvironmentIntegrityCheckerImpl_Factory(Provider<Context> provider, Provider<NativeDetector> provider2, Provider<OnfidoRemoteConfig> provider3) {
        this.contextProvider = provider;
        this.nativeDetectorProvider = provider2;
        this.remoteConfigProvider = provider3;
    }

    public static EnvironmentIntegrityCheckerImpl_Factory create(Provider<Context> provider, Provider<NativeDetector> provider2, Provider<OnfidoRemoteConfig> provider3) {
        return new EnvironmentIntegrityCheckerImpl_Factory(provider, provider2, provider3);
    }

    public static EnvironmentIntegrityCheckerImpl newInstance(Context context, NativeDetector nativeDetector, OnfidoRemoteConfig onfidoRemoteConfig) {
        return new EnvironmentIntegrityCheckerImpl(context, nativeDetector, onfidoRemoteConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public EnvironmentIntegrityCheckerImpl get() {
        return newInstance(this.contextProvider.get(), this.nativeDetectorProvider.get(), this.remoteConfigProvider.get());
    }
}
