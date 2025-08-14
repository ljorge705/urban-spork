package com.onfido.android.sdk.capture.internal.util.logging;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class RemoteLoggerTree_Factory implements Factory<RemoteLoggerTree> {
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;
    private final Provider<LoggerRepository> repositoryProvider;
    private final Provider<TimeProvider> timeProvider;

    public RemoteLoggerTree_Factory(Provider<LoggerRepository> provider, Provider<TimeProvider> provider2, Provider<OnfidoRemoteConfig> provider3) {
        this.repositoryProvider = provider;
        this.timeProvider = provider2;
        this.remoteConfigProvider = provider3;
    }

    public static RemoteLoggerTree_Factory create(Provider<LoggerRepository> provider, Provider<TimeProvider> provider2, Provider<OnfidoRemoteConfig> provider3) {
        return new RemoteLoggerTree_Factory(provider, provider2, provider3);
    }

    public static RemoteLoggerTree newInstance(LoggerRepository loggerRepository, TimeProvider timeProvider, OnfidoRemoteConfig onfidoRemoteConfig) {
        return new RemoteLoggerTree(loggerRepository, timeProvider, onfidoRemoteConfig);
    }

    @Override // com.onfido.javax.inject.Provider
    public RemoteLoggerTree get() {
        return newInstance(this.repositoryProvider.get(), this.timeProvider.get(), this.remoteConfigProvider.get());
    }
}
