package com.onfido.android.sdk.capture.ui.camera.liveness.intro;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class LivenessIntroVideoUrlProvider_Factory implements Factory<LivenessIntroVideoUrlProvider> {

    private static final class InstanceHolder {
        private static final LivenessIntroVideoUrlProvider_Factory INSTANCE = new LivenessIntroVideoUrlProvider_Factory();

        private InstanceHolder() {
        }
    }

    public static LivenessIntroVideoUrlProvider_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static LivenessIntroVideoUrlProvider newInstance() {
        return new LivenessIntroVideoUrlProvider();
    }

    @Override // com.onfido.javax.inject.Provider
    public LivenessIntroVideoUrlProvider get() {
        return newInstance();
    }
}
