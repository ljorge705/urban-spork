package com.onfido.android.sdk.capture.detector.mrz;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class MRZDetectorGoogle_Factory implements Factory<MRZDetectorGoogle> {

    private static final class InstanceHolder {
        private static final MRZDetectorGoogle_Factory INSTANCE = new MRZDetectorGoogle_Factory();

        private InstanceHolder() {
        }
    }

    public static MRZDetectorGoogle_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static MRZDetectorGoogle newInstance() {
        return new MRZDetectorGoogle();
    }

    @Override // com.onfido.javax.inject.Provider
    public MRZDetectorGoogle get() {
        return newInstance();
    }
}
