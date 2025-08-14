package com.onfido.android.sdk.capture.utils;

import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class UuidProvider_Factory implements Factory<UuidProvider> {
    private final Provider<SharedPreferencesDataSource> sharedPreferencesDataSourceProvider;

    public UuidProvider_Factory(Provider<SharedPreferencesDataSource> provider) {
        this.sharedPreferencesDataSourceProvider = provider;
    }

    public static UuidProvider_Factory create(Provider<SharedPreferencesDataSource> provider) {
        return new UuidProvider_Factory(provider);
    }

    public static UuidProvider newInstance(SharedPreferencesDataSource sharedPreferencesDataSource) {
        return new UuidProvider(sharedPreferencesDataSource);
    }

    @Override // com.onfido.javax.inject.Provider
    public UuidProvider get() {
        return newInstance(this.sharedPreferencesDataSourceProvider.get());
    }
}
