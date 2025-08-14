package com.onfido.workflow.internal.ui.processor;

import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes6.dex */
public final class PermissionsFlowHelper_Factory implements Factory<PermissionsFlowHelper> {
    private final Provider<Navigator> navigatorProvider;
    private final Provider<RuntimePermissionsManager> permissionsManagerProvider;

    public PermissionsFlowHelper_Factory(Provider<Navigator> provider, Provider<RuntimePermissionsManager> provider2) {
        this.navigatorProvider = provider;
        this.permissionsManagerProvider = provider2;
    }

    @Override // com.onfido.javax.inject.Provider
    public PermissionsFlowHelper get() {
        return newInstance(this.navigatorProvider.get(), this.permissionsManagerProvider.get());
    }

    public static PermissionsFlowHelper_Factory create(Provider<Navigator> provider, Provider<RuntimePermissionsManager> provider2) {
        return new PermissionsFlowHelper_Factory(provider, provider2);
    }

    public static PermissionsFlowHelper newInstance(Navigator navigator, RuntimePermissionsManager runtimePermissionsManager) {
        return new PermissionsFlowHelper(navigator, runtimePermissionsManager);
    }
}
