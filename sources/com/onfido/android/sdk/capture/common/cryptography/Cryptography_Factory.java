package com.onfido.android.sdk.capture.common.cryptography;

import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class Cryptography_Factory implements Factory<Cryptography> {
    private final Provider<CryptographyHelper> helperProvider;

    public Cryptography_Factory(Provider<CryptographyHelper> provider) {
        this.helperProvider = provider;
    }

    public static Cryptography_Factory create(Provider<CryptographyHelper> provider) {
        return new Cryptography_Factory(provider);
    }

    public static Cryptography newInstance(CryptographyHelper cryptographyHelper) {
        return new Cryptography(cryptographyHelper);
    }

    @Override // com.onfido.javax.inject.Provider
    public Cryptography get() {
        return newInstance(this.helperProvider.get());
    }
}
