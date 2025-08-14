package com.onfido.android.sdk.capture.detector.face;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class FaceOnDocumentDetectorGoogle_Factory implements Factory<FaceOnDocumentDetectorGoogle> {
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;

    public FaceOnDocumentDetectorGoogle_Factory(Provider<OnfidoRemoteConfig> provider) {
        this.remoteConfigProvider = provider;
    }

    public static FaceOnDocumentDetectorGoogle_Factory create(Provider<OnfidoRemoteConfig> provider) {
        return new FaceOnDocumentDetectorGoogle_Factory(provider);
    }

    public static FaceOnDocumentDetectorGoogle newInstance(OnfidoRemoteConfig onfidoRemoteConfig) {
        return new FaceOnDocumentDetectorGoogle(onfidoRemoteConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public FaceOnDocumentDetectorGoogle get() {
        return newInstance(this.remoteConfigProvider.get());
    }
}
