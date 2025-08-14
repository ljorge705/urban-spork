package com.onfido.android.sdk.capture.detector.rectangle;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class RectangleTransformer_Factory implements Factory<RectangleTransformer> {

    private static final class InstanceHolder {
        private static final RectangleTransformer_Factory INSTANCE = new RectangleTransformer_Factory();

        private InstanceHolder() {
        }
    }

    public static RectangleTransformer_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static RectangleTransformer newInstance() {
        return new RectangleTransformer();
    }

    @Override // com.onfido.javax.inject.Provider
    public RectangleTransformer get() {
        return newInstance();
    }
}
