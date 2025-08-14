package com.onfido.workflow.internal.ui.processor.biometric.token;

import android.content.Context;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;

/* loaded from: classes6.dex */
public final class BiometricTokenInternalRepository_Factory implements Factory<BiometricTokenInternalRepository> {
    private final Provider<Context> appContextProvider;
    private final Provider<Json> parserProvider;

    public BiometricTokenInternalRepository_Factory(Provider<Context> provider, Provider<Json> provider2) {
        this.appContextProvider = provider;
        this.parserProvider = provider2;
    }

    @Override // com.onfido.javax.inject.Provider
    public BiometricTokenInternalRepository get() {
        return newInstance(this.appContextProvider.get(), this.parserProvider.get());
    }

    public static BiometricTokenInternalRepository_Factory create(Provider<Context> provider, Provider<Json> provider2) {
        return new BiometricTokenInternalRepository_Factory(provider, provider2);
    }

    public static BiometricTokenInternalRepository newInstance(Context context, Json json) {
        return new BiometricTokenInternalRepository(context, json);
    }
}
