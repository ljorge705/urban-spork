package com.onfido.android.sdk.capture.detector.mrz;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class MRZDetectorEmpty_Factory implements Factory<MRZDetectorEmpty> {

    private static final class InstanceHolder {
        private static final MRZDetectorEmpty_Factory INSTANCE = new MRZDetectorEmpty_Factory();

        private InstanceHolder() {
        }
    }

    public static MRZDetectorEmpty_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static MRZDetectorEmpty newInstance() {
        return new MRZDetectorEmpty();
    }

    @Override // com.onfido.javax.inject.Provider
    public MRZDetectorEmpty get() {
        return newInstance();
    }
}
