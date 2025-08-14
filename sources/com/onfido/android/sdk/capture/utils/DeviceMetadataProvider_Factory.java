package com.onfido.android.sdk.capture.utils;

import com.onfido.android.sdk.capture.antifraud.SignalExtractor;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class DeviceMetadataProvider_Factory implements Factory<DeviceMetadataProvider> {
    private final Provider<SignalExtractor> signalExtractorProvider;

    public DeviceMetadataProvider_Factory(Provider<SignalExtractor> provider) {
        this.signalExtractorProvider = provider;
    }

    public static DeviceMetadataProvider_Factory create(Provider<SignalExtractor> provider) {
        return new DeviceMetadataProvider_Factory(provider);
    }

    public static DeviceMetadataProvider newInstance(SignalExtractor signalExtractor) {
        return new DeviceMetadataProvider(signalExtractor);
    }

    @Override // com.onfido.javax.inject.Provider
    public DeviceMetadataProvider get() {
        return newInstance(this.signalExtractorProvider.get());
    }
}
