package com.onfido.dagger.internal;

import com.onfido.dagger.Lazy;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class ProviderOfLazy<T> implements Provider<Lazy<T>> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Provider<T> provider;

    private ProviderOfLazy(Provider<T> provider) {
        this.provider = provider;
    }

    @Override // com.onfido.javax.inject.Provider
    public Lazy<T> get() {
        return DoubleCheck.lazy(this.provider);
    }

    public static <T> Provider<Lazy<T>> create(Provider<T> provider) {
        return new ProviderOfLazy((Provider) Preconditions.checkNotNull(provider));
    }
}
