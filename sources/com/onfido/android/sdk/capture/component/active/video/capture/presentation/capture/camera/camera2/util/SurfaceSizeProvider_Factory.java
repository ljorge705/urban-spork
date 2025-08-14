package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class SurfaceSizeProvider_Factory implements Factory<SurfaceSizeProvider> {

    private static final class InstanceHolder {
        private static final SurfaceSizeProvider_Factory INSTANCE = new SurfaceSizeProvider_Factory();

        private InstanceHolder() {
        }
    }

    public static SurfaceSizeProvider_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static SurfaceSizeProvider newInstance() {
        return new SurfaceSizeProvider();
    }

    @Override // com.onfido.javax.inject.Provider
    public SurfaceSizeProvider get() {
        return newInstance();
    }
}
