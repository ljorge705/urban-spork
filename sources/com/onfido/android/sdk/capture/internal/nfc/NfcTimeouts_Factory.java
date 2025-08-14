package com.onfido.android.sdk.capture.internal.nfc;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class NfcTimeouts_Factory implements Factory<NfcTimeouts> {

    private static final class InstanceHolder {
        private static final NfcTimeouts_Factory INSTANCE = new NfcTimeouts_Factory();

        private InstanceHolder() {
        }
    }

    public static NfcTimeouts_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static NfcTimeouts newInstance() {
        return new NfcTimeouts();
    }

    @Override // com.onfido.javax.inject.Provider
    public NfcTimeouts get() {
        return newInstance();
    }
}
