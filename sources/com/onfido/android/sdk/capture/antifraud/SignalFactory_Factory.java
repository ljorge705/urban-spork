package com.onfido.android.sdk.capture.antifraud;

import com.onfido.android.sdk.capture.utils.UuidProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SignalFactory_Factory implements Factory<SignalFactory> {
    private final Provider<UuidProvider> uuidProvider;

    public SignalFactory_Factory(Provider<UuidProvider> provider) {
        this.uuidProvider = provider;
    }

    public static SignalFactory_Factory create(Provider<UuidProvider> provider) {
        return new SignalFactory_Factory(provider);
    }

    public static SignalFactory newInstance(UuidProvider uuidProvider) {
        return new SignalFactory(uuidProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public SignalFactory get() {
        return newInstance(this.uuidProvider.get());
    }
}
