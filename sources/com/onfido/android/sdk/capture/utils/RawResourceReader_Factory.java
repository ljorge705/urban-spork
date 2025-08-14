package com.onfido.android.sdk.capture.utils;

import android.content.Context;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class RawResourceReader_Factory implements Factory<RawResourceReader> {
    private final Provider<Context> contextProvider;

    public RawResourceReader_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static RawResourceReader_Factory create(Provider<Context> provider) {
        return new RawResourceReader_Factory(provider);
    }

    public static RawResourceReader newInstance(Context context) {
        return new RawResourceReader(context);
    }

    @Override // com.onfido.javax.inject.Provider
    public RawResourceReader get() {
        return newInstance(this.contextProvider.get());
    }
}
