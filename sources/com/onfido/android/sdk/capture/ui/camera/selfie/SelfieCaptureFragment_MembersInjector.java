package com.onfido.android.sdk.capture.ui.camera.selfie;

import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.internal.camera.factory.CameraFactory;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.performance.AggregatedPerformanceAnalytics;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.ui.camera.selfie.SelfieCaptureViewModel;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SelfieCaptureFragment_MembersInjector implements MembersInjector<SelfieCaptureFragment> {
    private final Provider<AnnouncementService> announcementServiceProvider;
    private final Provider<CameraFactory> cameraFactoryProvider;
    private final Provider<ImageUtils> imageUtilsProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<AggregatedPerformanceAnalytics> performanceAnalyticsProvider;
    private final Provider<RuntimePermissionsManager> permissionsManagerProvider;
    private final Provider<SelfieCaptureViewModel.Factory> viewModelFactoryProvider;

    public SelfieCaptureFragment_MembersInjector(Provider<AggregatedPerformanceAnalytics> provider, Provider<ImageUtils> provider2, Provider<OnfidoRemoteConfig> provider3, Provider<SelfieCaptureViewModel.Factory> provider4, Provider<RuntimePermissionsManager> provider5, Provider<AnnouncementService> provider6, Provider<CameraFactory> provider7) {
        this.performanceAnalyticsProvider = provider;
        this.imageUtilsProvider = provider2;
        this.onfidoRemoteConfigProvider = provider3;
        this.viewModelFactoryProvider = provider4;
        this.permissionsManagerProvider = provider5;
        this.announcementServiceProvider = provider6;
        this.cameraFactoryProvider = provider7;
    }

    public static MembersInjector<SelfieCaptureFragment> create(Provider<AggregatedPerformanceAnalytics> provider, Provider<ImageUtils> provider2, Provider<OnfidoRemoteConfig> provider3, Provider<SelfieCaptureViewModel.Factory> provider4, Provider<RuntimePermissionsManager> provider5, Provider<AnnouncementService> provider6, Provider<CameraFactory> provider7) {
        return new SelfieCaptureFragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static void injectAnnouncementService(SelfieCaptureFragment selfieCaptureFragment, AnnouncementService announcementService) {
        selfieCaptureFragment.announcementService = announcementService;
    }

    public static void injectCameraFactory(SelfieCaptureFragment selfieCaptureFragment, CameraFactory cameraFactory) {
        selfieCaptureFragment.cameraFactory = cameraFactory;
    }

    public static void injectImageUtils(SelfieCaptureFragment selfieCaptureFragment, ImageUtils imageUtils) {
        selfieCaptureFragment.imageUtils = imageUtils;
    }

    public static void injectOnfidoRemoteConfig(SelfieCaptureFragment selfieCaptureFragment, OnfidoRemoteConfig onfidoRemoteConfig) {
        selfieCaptureFragment.onfidoRemoteConfig = onfidoRemoteConfig;
    }

    public static void injectPerformanceAnalytics(SelfieCaptureFragment selfieCaptureFragment, AggregatedPerformanceAnalytics aggregatedPerformanceAnalytics) {
        selfieCaptureFragment.performanceAnalytics = aggregatedPerformanceAnalytics;
    }

    public static void injectPermissionsManager(SelfieCaptureFragment selfieCaptureFragment, RuntimePermissionsManager runtimePermissionsManager) {
        selfieCaptureFragment.permissionsManager = runtimePermissionsManager;
    }

    public static void injectViewModelFactory(SelfieCaptureFragment selfieCaptureFragment, SelfieCaptureViewModel.Factory factory) {
        selfieCaptureFragment.viewModelFactory = factory;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(SelfieCaptureFragment selfieCaptureFragment) {
        injectPerformanceAnalytics(selfieCaptureFragment, this.performanceAnalyticsProvider.get());
        injectImageUtils(selfieCaptureFragment, this.imageUtilsProvider.get());
        injectOnfidoRemoteConfig(selfieCaptureFragment, this.onfidoRemoteConfigProvider.get());
        injectViewModelFactory(selfieCaptureFragment, this.viewModelFactoryProvider.get());
        injectPermissionsManager(selfieCaptureFragment, this.permissionsManagerProvider.get());
        injectAnnouncementService(selfieCaptureFragment, this.announcementServiceProvider.get());
        injectCameraFactory(selfieCaptureFragment, this.cameraFactoryProvider.get());
    }
}
