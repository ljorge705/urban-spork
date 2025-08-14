package com.onfido.android.sdk.capture.common.cryptography;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class CryptographyHelper_Factory implements Factory<CryptographyHelper> {

    private static final class InstanceHolder {
        private static final CryptographyHelper_Factory INSTANCE = new CryptographyHelper_Factory();

        private InstanceHolder() {
        }
    }

    public static CryptographyHelper_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static CryptographyHelper newInstance() {
        return new CryptographyHelper();
    }

    @Override // com.onfido.javax.inject.Provider
    public CryptographyHelper get() {
        return newInstance();
    }
}
