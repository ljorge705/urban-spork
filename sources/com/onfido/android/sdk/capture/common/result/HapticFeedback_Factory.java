package com.onfido.android.sdk.capture.common.result;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class HapticFeedback_Factory implements Factory<HapticFeedback> {

    private static final class InstanceHolder {
        private static final HapticFeedback_Factory INSTANCE = new HapticFeedback_Factory();

        private InstanceHolder() {
        }
    }

    public static HapticFeedback_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static HapticFeedback newInstance() {
        return new HapticFeedback();
    }

    @Override // com.onfido.javax.inject.Provider
    public HapticFeedback get() {
        return newInstance();
    }
}
