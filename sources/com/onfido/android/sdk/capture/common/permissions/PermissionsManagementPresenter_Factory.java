package com.onfido.android.sdk.capture.common.permissions;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.PermissionsTracker;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PermissionsManagementPresenter_Factory implements Factory<PermissionsManagementPresenter> {
    private final Provider<PermissionsTracker> permissionsTrackerProvider;
    private final Provider<RuntimePermissionsManager> runtimePermissionsManagerProvider;

    public PermissionsManagementPresenter_Factory(Provider<RuntimePermissionsManager> provider, Provider<PermissionsTracker> provider2) {
        this.runtimePermissionsManagerProvider = provider;
        this.permissionsTrackerProvider = provider2;
    }

    public static PermissionsManagementPresenter_Factory create(Provider<RuntimePermissionsManager> provider, Provider<PermissionsTracker> provider2) {
        return new PermissionsManagementPresenter_Factory(provider, provider2);
    }

    public static PermissionsManagementPresenter newInstance(RuntimePermissionsManager runtimePermissionsManager, PermissionsTracker permissionsTracker) {
        return new PermissionsManagementPresenter(runtimePermissionsManager, permissionsTracker);
    }

    @Override // com.onfido.javax.inject.Provider
    public PermissionsManagementPresenter get() {
        return newInstance(this.runtimePermissionsManagerProvider.get(), this.permissionsTrackerProvider.get());
    }
}
