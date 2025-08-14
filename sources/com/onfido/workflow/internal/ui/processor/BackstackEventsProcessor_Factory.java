package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class BackstackEventsProcessor_Factory implements Factory<BackstackEventsProcessor> {
    private final Provider<Navigator> navigatorProvider;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;

    public BackstackEventsProcessor_Factory(Provider<OnfidoRemoteConfig> provider, Provider<Navigator> provider2) {
        this.remoteConfigProvider = provider;
        this.navigatorProvider = provider2;
    }

    @Override // com.onfido.javax.inject.Provider
    public BackstackEventsProcessor get() {
        return newInstance(this.remoteConfigProvider.get(), this.navigatorProvider.get());
    }

    public static BackstackEventsProcessor_Factory create(Provider<OnfidoRemoteConfig> provider, Provider<Navigator> provider2) {
        return new BackstackEventsProcessor_Factory(provider, provider2);
    }

    public static BackstackEventsProcessor newInstance(OnfidoRemoteConfig onfidoRemoteConfig, Navigator navigator) {
        return new BackstackEventsProcessor(onfidoRemoteConfig, navigator);
    }
}
