package com.onfido.android.sdk.capture.utils;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DefaultTimeProvider_Factory implements Factory<DefaultTimeProvider> {

    private static final class InstanceHolder {
        private static final DefaultTimeProvider_Factory INSTANCE = new DefaultTimeProvider_Factory();

        private InstanceHolder() {
        }
    }

    public static DefaultTimeProvider_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DefaultTimeProvider newInstance() {
        return new DefaultTimeProvider();
    }

    @Override // com.onfido.javax.inject.Provider
    public DefaultTimeProvider get() {
        return newInstance();
    }
}
