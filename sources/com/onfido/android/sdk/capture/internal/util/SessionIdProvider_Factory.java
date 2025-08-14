package com.onfido.android.sdk.capture.internal.util;

import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.android.sdk.capture.utils.UuidProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SessionIdProvider_Factory implements Factory<SessionIdProvider> {
    private final Provider<SharedPreferencesDataSource> sharedPreferencesDataSourceProvider;
    private final Provider<TimeProvider> timeProvider;
    private final Provider<UuidProvider> uuidProvider;

    public SessionIdProvider_Factory(Provider<UuidProvider> provider, Provider<TimeProvider> provider2, Provider<SharedPreferencesDataSource> provider3) {
        this.uuidProvider = provider;
        this.timeProvider = provider2;
        this.sharedPreferencesDataSourceProvider = provider3;
    }

    public static SessionIdProvider_Factory create(Provider<UuidProvider> provider, Provider<TimeProvider> provider2, Provider<SharedPreferencesDataSource> provider3) {
        return new SessionIdProvider_Factory(provider, provider2, provider3);
    }

    public static SessionIdProvider newInstance(UuidProvider uuidProvider, TimeProvider timeProvider, SharedPreferencesDataSource sharedPreferencesDataSource) {
        return new SessionIdProvider(uuidProvider, timeProvider, sharedPreferencesDataSource);
    }

    @Override // com.onfido.javax.inject.Provider
    public SessionIdProvider get() {
        return newInstance(this.uuidProvider.get(), this.timeProvider.get(), this.sharedPreferencesDataSourceProvider.get());
    }
}
