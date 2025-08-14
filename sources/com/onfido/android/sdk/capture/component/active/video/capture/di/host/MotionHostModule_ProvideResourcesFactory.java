package com.onfido.android.sdk.capture.component.active.video.capture.di.host;

import android.content.Context;
import android.content.res.Resources;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionHostModule_ProvideResourcesFactory implements Factory<Resources> {
    private final Provider<Context> contextProvider;
    private final MotionHostModule module;

    public MotionHostModule_ProvideResourcesFactory(MotionHostModule motionHostModule, Provider<Context> provider) {
        this.module = motionHostModule;
        this.contextProvider = provider;
    }

    public static MotionHostModule_ProvideResourcesFactory create(MotionHostModule motionHostModule, Provider<Context> provider) {
        return new MotionHostModule_ProvideResourcesFactory(motionHostModule, provider);
    }

    public static Resources provideResources(MotionHostModule motionHostModule, Context context) {
        return (Resources) Preconditions.checkNotNullFromProvides(motionHostModule.provideResources(context));
    }

    @Override // com.onfido.javax.inject.Provider
    public Resources get() {
        return provideResources(this.module, this.contextProvider.get());
    }
}
