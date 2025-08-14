package com.onfido.android.sdk.capture.common.permissions;

import android.content.Context;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class RuntimePermissionsManager_Factory implements Factory<RuntimePermissionsManager> {
    private final Provider<Context> contextProvider;
    private final Provider<SharedPreferencesDataSource> sharedPreferencesDataSourceProvider;

    public RuntimePermissionsManager_Factory(Provider<Context> provider, Provider<SharedPreferencesDataSource> provider2) {
        this.contextProvider = provider;
        this.sharedPreferencesDataSourceProvider = provider2;
    }

    public static RuntimePermissionsManager_Factory create(Provider<Context> provider, Provider<SharedPreferencesDataSource> provider2) {
        return new RuntimePermissionsManager_Factory(provider, provider2);
    }

    public static RuntimePermissionsManager newInstance(Context context, SharedPreferencesDataSource sharedPreferencesDataSource) {
        return new RuntimePermissionsManager(context, sharedPreferencesDataSource);
    }

    @Override // com.onfido.javax.inject.Provider
    public RuntimePermissionsManager get() {
        return newInstance(this.contextProvider.get(), this.sharedPreferencesDataSourceProvider.get());
    }
}
