package com.onfido.android.sdk.capture.common.preferences;

import android.content.Context;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;

/* loaded from: classes2.dex */
public final class SharedPreferencesDataSource_Factory implements Factory<SharedPreferencesDataSource> {
    private final Provider<Context> contextProvider;
    private final Provider<Json> jsonParserProvider;

    public SharedPreferencesDataSource_Factory(Provider<Context> provider, Provider<Json> provider2) {
        this.contextProvider = provider;
        this.jsonParserProvider = provider2;
    }

    public static SharedPreferencesDataSource_Factory create(Provider<Context> provider, Provider<Json> provider2) {
        return new SharedPreferencesDataSource_Factory(provider, provider2);
    }

    public static SharedPreferencesDataSource newInstance(Context context, Json json) {
        return new SharedPreferencesDataSource(context, json);
    }

    @Override // com.onfido.javax.inject.Provider
    public SharedPreferencesDataSource get() {
        return newInstance(this.contextProvider.get(), this.jsonParserProvider.get());
    }
}
