package com.onfido.android.sdk.capture.ui.proofOfAddress.host;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.ui.proofOfAddress.PoaUtils;
import com.onfido.android.sdk.capture.ui.proofOfAddress.host.PoaHostViewModel;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PoaHostFragment_MembersInjector implements MembersInjector<PoaHostFragment> {
    private final Provider<ImageUtils> imageUtilsProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<PoaUtils> poaUtilsProvider;
    private final Provider<PoaHostViewModel.Factory> poaViewModelFactoryProvider;
    private final Provider<RuntimePermissionsManager> runtimePermissionsManagerProvider;

    public PoaHostFragment_MembersInjector(Provider<PoaUtils> provider, Provider<RuntimePermissionsManager> provider2, Provider<PoaHostViewModel.Factory> provider3, Provider<ImageUtils> provider4, Provider<OnfidoConfig> provider5, Provider<OnfidoRemoteConfig> provider6) {
        this.poaUtilsProvider = provider;
        this.runtimePermissionsManagerProvider = provider2;
        this.poaViewModelFactoryProvider = provider3;
        this.imageUtilsProvider = provider4;
        this.onfidoConfigProvider = provider5;
        this.onfidoRemoteConfigProvider = provider6;
    }

    public static MembersInjector<PoaHostFragment> create(Provider<PoaUtils> provider, Provider<RuntimePermissionsManager> provider2, Provider<PoaHostViewModel.Factory> provider3, Provider<ImageUtils> provider4, Provider<OnfidoConfig> provider5, Provider<OnfidoRemoteConfig> provider6) {
        return new PoaHostFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectImageUtils(PoaHostFragment poaHostFragment, ImageUtils imageUtils) {
        poaHostFragment.imageUtils = imageUtils;
    }

    public static void injectOnfidoConfig(PoaHostFragment poaHostFragment, OnfidoConfig onfidoConfig) {
        poaHostFragment.onfidoConfig = onfidoConfig;
    }

    public static void injectOnfidoRemoteConfig(PoaHostFragment poaHostFragment, OnfidoRemoteConfig onfidoRemoteConfig) {
        poaHostFragment.onfidoRemoteConfig = onfidoRemoteConfig;
    }

    public static void injectPoaUtils(PoaHostFragment poaHostFragment, PoaUtils poaUtils) {
        poaHostFragment.poaUtils = poaUtils;
    }

    public static void injectPoaViewModelFactory(PoaHostFragment poaHostFragment, PoaHostViewModel.Factory factory) {
        poaHostFragment.poaViewModelFactory = factory;
    }

    public static void injectRuntimePermissionsManager(PoaHostFragment poaHostFragment, RuntimePermissionsManager runtimePermissionsManager) {
        poaHostFragment.runtimePermissionsManager = runtimePermissionsManager;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(PoaHostFragment poaHostFragment) {
        injectPoaUtils(poaHostFragment, this.poaUtilsProvider.get());
        injectRuntimePermissionsManager(poaHostFragment, this.runtimePermissionsManagerProvider.get());
        injectPoaViewModelFactory(poaHostFragment, this.poaViewModelFactoryProvider.get());
        injectImageUtils(poaHostFragment, this.imageUtilsProvider.get());
        injectOnfidoConfig(poaHostFragment, this.onfidoConfigProvider.get());
        injectOnfidoRemoteConfig(poaHostFragment, this.onfidoRemoteConfigProvider.get());
    }
}
