package com.onfido.android.sdk.capture.component.active.video.capture.di.host;

import android.content.Context;
import com.google.android.gms.common.moduleinstall.ModuleInstallClient;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionHostModule_ProvideModuleInstallClientFactory implements Factory<ModuleInstallClient> {
    private final Provider<Context> contextProvider;
    private final MotionHostModule module;

    public MotionHostModule_ProvideModuleInstallClientFactory(MotionHostModule motionHostModule, Provider<Context> provider) {
        this.module = motionHostModule;
        this.contextProvider = provider;
    }

    public static MotionHostModule_ProvideModuleInstallClientFactory create(MotionHostModule motionHostModule, Provider<Context> provider) {
        return new MotionHostModule_ProvideModuleInstallClientFactory(motionHostModule, provider);
    }

    public static ModuleInstallClient provideModuleInstallClient(MotionHostModule motionHostModule, Context context) {
        return (ModuleInstallClient) Preconditions.checkNotNullFromProvides(motionHostModule.provideModuleInstallClient(context));
    }

    @Override // com.onfido.javax.inject.Provider
    public ModuleInstallClient get() {
        return provideModuleInstallClient(this.module, this.contextProvider.get());
    }
}
