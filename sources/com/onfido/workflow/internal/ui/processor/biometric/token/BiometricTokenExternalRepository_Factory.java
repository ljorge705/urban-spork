package com.onfido.workflow.internal.ui.processor.biometric.token;

import android.content.Context;
import android.os.ResultReceiver;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class BiometricTokenExternalRepository_Factory implements Factory<BiometricTokenExternalRepository> {
    private final Provider<Context> applicationContextProvider;
    private final Provider<ResultReceiver> biometricCallbackProvider;

    public BiometricTokenExternalRepository_Factory(Provider<Context> provider, Provider<ResultReceiver> provider2) {
        this.applicationContextProvider = provider;
        this.biometricCallbackProvider = provider2;
    }

    @Override // com.onfido.javax.inject.Provider
    public BiometricTokenExternalRepository get() {
        return newInstance(this.applicationContextProvider.get(), this.biometricCallbackProvider.get());
    }

    public static BiometricTokenExternalRepository_Factory create(Provider<Context> provider, Provider<ResultReceiver> provider2) {
        return new BiometricTokenExternalRepository_Factory(provider, provider2);
    }

    public static BiometricTokenExternalRepository newInstance(Context context, ResultReceiver resultReceiver) {
        return new BiometricTokenExternalRepository(context, resultReceiver);
    }
}
