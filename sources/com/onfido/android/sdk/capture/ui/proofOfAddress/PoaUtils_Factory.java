package com.onfido.android.sdk.capture.ui.proofOfAddress;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class PoaUtils_Factory implements Factory<PoaUtils> {

    private static final class InstanceHolder {
        private static final PoaUtils_Factory INSTANCE = new PoaUtils_Factory();

        private InstanceHolder() {
        }
    }

    public static PoaUtils_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static PoaUtils newInstance() {
        return new PoaUtils();
    }

    @Override // com.onfido.javax.inject.Provider
    public PoaUtils get() {
        return newInstance();
    }
}
