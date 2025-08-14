package com.onfido.android.sdk.capture.common.permissions;

import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PermissionsManagementFragment_MembersInjector implements MembersInjector<PermissionsManagementFragment> {
    private final Provider<PermissionsManagementPresenter> presenterProvider;

    public PermissionsManagementFragment_MembersInjector(Provider<PermissionsManagementPresenter> provider) {
        this.presenterProvider = provider;
    }

    public static MembersInjector<PermissionsManagementFragment> create(Provider<PermissionsManagementPresenter> provider) {
        return new PermissionsManagementFragment_MembersInjector(provider);
    }

    public static void injectPresenter(PermissionsManagementFragment permissionsManagementFragment, PermissionsManagementPresenter permissionsManagementPresenter) {
        permissionsManagementFragment.presenter = permissionsManagementPresenter;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(PermissionsManagementFragment permissionsManagementFragment) {
        injectPresenter(permissionsManagementFragment, this.presenterProvider.get());
    }
}
