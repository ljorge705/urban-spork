package com.onfido.android.sdk.capture.internal.util.logging;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.internal.util.SessionIdProvider;
import com.onfido.android.sdk.capture.utils.DeviceMetadataProvider;
import com.onfido.android.sdk.capture.utils.UuidProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class OnfidoLogMapper_Factory implements Factory<OnfidoLogMapper> {
    private final Provider<DeviceMetadataProvider> deviceMetadataProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;
    private final Provider<SessionIdProvider> sessionIdProvider;
    private final Provider<UuidProvider> uuidProvider;

    public OnfidoLogMapper_Factory(Provider<UuidProvider> provider, Provider<OnfidoConfig> provider2, Provider<SessionIdProvider> provider3, Provider<DeviceMetadataProvider> provider4) {
        this.uuidProvider = provider;
        this.onfidoConfigProvider = provider2;
        this.sessionIdProvider = provider3;
        this.deviceMetadataProvider = provider4;
    }

    public static OnfidoLogMapper_Factory create(Provider<UuidProvider> provider, Provider<OnfidoConfig> provider2, Provider<SessionIdProvider> provider3, Provider<DeviceMetadataProvider> provider4) {
        return new OnfidoLogMapper_Factory(provider, provider2, provider3, provider4);
    }

    public static OnfidoLogMapper newInstance(UuidProvider uuidProvider, OnfidoConfig onfidoConfig, SessionIdProvider sessionIdProvider, DeviceMetadataProvider deviceMetadataProvider) {
        return new OnfidoLogMapper(uuidProvider, onfidoConfig, sessionIdProvider, deviceMetadataProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public OnfidoLogMapper get() {
        return newInstance(this.uuidProvider.get(), this.onfidoConfigProvider.get(), this.sessionIdProvider.get(), this.deviceMetadataProvider.get());
    }
}
