package com.onfido.android.sdk.capture.common.data;

import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class ThemeDataSource_Factory implements Factory<ThemeDataSource> {
    private final Provider<SharedPreferencesDataSource> storageProvider;

    public ThemeDataSource_Factory(Provider<SharedPreferencesDataSource> provider) {
        this.storageProvider = provider;
    }

    public static ThemeDataSource_Factory create(Provider<SharedPreferencesDataSource> provider) {
        return new ThemeDataSource_Factory(provider);
    }

    public static ThemeDataSource newInstance(SharedPreferencesDataSource sharedPreferencesDataSource) {
        return new ThemeDataSource(sharedPreferencesDataSource);
    }

    @Override // com.onfido.javax.inject.Provider
    public ThemeDataSource get() {
        return newInstance(this.storageProvider.get());
    }
}
