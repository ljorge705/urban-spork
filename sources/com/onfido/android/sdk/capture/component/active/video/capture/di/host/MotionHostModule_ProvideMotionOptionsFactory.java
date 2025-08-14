package com.onfido.android.sdk.capture.component.active.video.capture.di.host;

import com.onfido.android.sdk.capture.ui.options.MotionCaptureVariantOptions;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class MotionHostModule_ProvideMotionOptionsFactory implements Factory<MotionCaptureVariantOptions> {
    private final MotionHostModule module;

    public MotionHostModule_ProvideMotionOptionsFactory(MotionHostModule motionHostModule) {
        this.module = motionHostModule;
    }

    public static MotionHostModule_ProvideMotionOptionsFactory create(MotionHostModule motionHostModule) {
        return new MotionHostModule_ProvideMotionOptionsFactory(motionHostModule);
    }

    public static MotionCaptureVariantOptions provideMotionOptions(MotionHostModule motionHostModule) {
        return (MotionCaptureVariantOptions) Preconditions.checkNotNullFromProvides(motionHostModule.getMotionOptions());
    }

    @Override // com.onfido.javax.inject.Provider
    public MotionCaptureVariantOptions get() {
        return provideMotionOptions(this.module);
    }
}
