package com.onfido.android.sdk.capture.antifraud;

import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SignalExtractor_Factory implements Factory<SignalExtractor> {
    private final Provider<SignalFactory> signalFactoryProvider;

    public SignalExtractor_Factory(Provider<SignalFactory> provider) {
        this.signalFactoryProvider = provider;
    }

    public static SignalExtractor_Factory create(Provider<SignalFactory> provider) {
        return new SignalExtractor_Factory(provider);
    }

    public static SignalExtractor newInstance(SignalFactory signalFactory) {
        return new SignalExtractor(signalFactory);
    }

    @Override // com.onfido.javax.inject.Provider
    public SignalExtractor get() {
        return newInstance(this.signalFactoryProvider.get());
    }
}
