package com.onfido.android.sdk.capture.detector.rectangle;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class RectangleDetectorEmpty_Factory implements Factory<RectangleDetectorEmpty> {

    private static final class InstanceHolder {
        private static final RectangleDetectorEmpty_Factory INSTANCE = new RectangleDetectorEmpty_Factory();

        private InstanceHolder() {
        }
    }

    public static RectangleDetectorEmpty_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static RectangleDetectorEmpty newInstance() {
        return new RectangleDetectorEmpty();
    }

    @Override // com.onfido.javax.inject.Provider
    public RectangleDetectorEmpty get() {
        return newInstance();
    }
}
