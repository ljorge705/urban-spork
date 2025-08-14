package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.recorder.persistentsurface;

import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera.camera2.util.ApiLevelUtil;
import com.onfido.android.sdk.capture.utils.DeviceUtils;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class IsPersistentSurfaceSupportedUseCaseImpl_Factory implements Factory<IsPersistentSurfaceSupportedUseCaseImpl> {
    private final Provider<ApiLevelUtil> apiLevelUtilProvider;
    private final Provider<DeviceUtils> deviceUtilsProvider;
    private final Provider<PersistentRecorderSurfaceRepository> repositoryProvider;

    public IsPersistentSurfaceSupportedUseCaseImpl_Factory(Provider<ApiLevelUtil> provider, Provider<DeviceUtils> provider2, Provider<PersistentRecorderSurfaceRepository> provider3) {
        this.apiLevelUtilProvider = provider;
        this.deviceUtilsProvider = provider2;
        this.repositoryProvider = provider3;
    }

    public static IsPersistentSurfaceSupportedUseCaseImpl_Factory create(Provider<ApiLevelUtil> provider, Provider<DeviceUtils> provider2, Provider<PersistentRecorderSurfaceRepository> provider3) {
        return new IsPersistentSurfaceSupportedUseCaseImpl_Factory(provider, provider2, provider3);
    }

    public static IsPersistentSurfaceSupportedUseCaseImpl newInstance(ApiLevelUtil apiLevelUtil, DeviceUtils deviceUtils, PersistentRecorderSurfaceRepository persistentRecorderSurfaceRepository) {
        return new IsPersistentSurfaceSupportedUseCaseImpl(apiLevelUtil, deviceUtils, persistentRecorderSurfaceRepository);
    }

    @Override // com.onfido.javax.inject.Provider
    public IsPersistentSurfaceSupportedUseCaseImpl get() {
        return newInstance(this.apiLevelUtilProvider.get(), this.deviceUtilsProvider.get(), this.repositoryProvider.get());
    }
}
