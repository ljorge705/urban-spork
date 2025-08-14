package com.onfido.android.sdk.capture.internal.camera.camerax;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DefaultImageAnalyzer_Factory implements Factory<DefaultImageAnalyzer> {

    private static final class InstanceHolder {
        private static final DefaultImageAnalyzer_Factory INSTANCE = new DefaultImageAnalyzer_Factory();

        private InstanceHolder() {
        }
    }

    public static DefaultImageAnalyzer_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DefaultImageAnalyzer newInstance() {
        return new DefaultImageAnalyzer();
    }

    @Override // com.onfido.javax.inject.Provider
    public DefaultImageAnalyzer get() {
        return newInstance();
    }
}
