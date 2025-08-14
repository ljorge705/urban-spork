package com.onfido.android.sdk.capture.internal.util;

import android.content.Context;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class FileCache_Factory implements Factory<FileCache> {
    private final Provider<Context> contextProvider;

    public FileCache_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static FileCache_Factory create(Provider<Context> provider) {
        return new FileCache_Factory(provider);
    }

    public static FileCache newInstance(Context context) {
        return new FileCache(context);
    }

    @Override // com.onfido.javax.inject.Provider
    public FileCache get() {
        return newInstance(this.contextProvider.get());
    }
}
