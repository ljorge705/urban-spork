package com.onfido.android.sdk.capture.ui.camera.util;

import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class CameraPermissionsUtils_MembersInjector implements MembersInjector<CameraPermissionsUtils> {
    private final Provider<RuntimePermissionsManager> permissionsManagerProvider;

    public CameraPermissionsUtils_MembersInjector(Provider<RuntimePermissionsManager> provider) {
        this.permissionsManagerProvider = provider;
    }

    public static MembersInjector<CameraPermissionsUtils> create(Provider<RuntimePermissionsManager> provider) {
        return new CameraPermissionsUtils_MembersInjector(provider);
    }

    public static void injectPermissionsManager(CameraPermissionsUtils cameraPermissionsUtils, RuntimePermissionsManager runtimePermissionsManager) {
        cameraPermissionsUtils.permissionsManager = runtimePermissionsManager;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(CameraPermissionsUtils cameraPermissionsUtils) {
        injectPermissionsManager(cameraPermissionsUtils, this.permissionsManagerProvider.get());
    }
}
