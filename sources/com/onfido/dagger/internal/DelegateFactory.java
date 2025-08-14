package com.onfido.dagger.internal;

import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class DelegateFactory<T> implements Factory<T> {
    private Provider<T> delegate;

    @Override // com.onfido.javax.inject.Provider
    public T get() {
        Provider<T> provider = this.delegate;
        if (provider == null) {
            throw new IllegalStateException();
        }
        return provider.get();
    }

    @Deprecated
    public void setDelegatedProvider(Provider<T> provider) {
        setDelegate(this, provider);
    }

    public static <T> void setDelegate(Provider<T> provider, Provider<T> provider2) {
        Preconditions.checkNotNull(provider2);
        DelegateFactory delegateFactory = (DelegateFactory) provider;
        if (delegateFactory.delegate != null) {
            throw new IllegalStateException();
        }
        delegateFactory.delegate = provider2;
    }

    Provider<T> getDelegate() {
        return (Provider) Preconditions.checkNotNull(this.delegate);
    }
}
