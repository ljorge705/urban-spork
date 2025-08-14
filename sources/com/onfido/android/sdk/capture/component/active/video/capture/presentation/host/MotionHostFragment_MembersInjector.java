package com.onfido.android.sdk.capture.component.active.video.capture.presentation.host;

import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostViewModel;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionHostFragment_MembersInjector implements MembersInjector<MotionHostFragment> {
    private final Provider<MotionHostViewModel.Factory> motionHostViewModelFactoryProvider;
    private final Provider<RuntimePermissionsManager> runtimePermissionsManagerProvider;

    public MotionHostFragment_MembersInjector(Provider<RuntimePermissionsManager> provider, Provider<MotionHostViewModel.Factory> provider2) {
        this.runtimePermissionsManagerProvider = provider;
        this.motionHostViewModelFactoryProvider = provider2;
    }

    public static MembersInjector<MotionHostFragment> create(Provider<RuntimePermissionsManager> provider, Provider<MotionHostViewModel.Factory> provider2) {
        return new MotionHostFragment_MembersInjector(provider, provider2);
    }

    public static void injectMotionHostViewModelFactory(MotionHostFragment motionHostFragment, MotionHostViewModel.Factory factory) {
        motionHostFragment.motionHostViewModelFactory = factory;
    }

    public static void injectRuntimePermissionsManager(MotionHostFragment motionHostFragment, RuntimePermissionsManager runtimePermissionsManager) {
        motionHostFragment.runtimePermissionsManager = runtimePermissionsManager;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(MotionHostFragment motionHostFragment) {
        injectRuntimePermissionsManager(motionHostFragment, this.runtimePermissionsManagerProvider.get());
        injectMotionHostViewModelFactory(motionHostFragment, this.motionHostViewModelFactoryProvider.get());
    }
}
