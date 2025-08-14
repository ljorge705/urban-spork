package com.onfido.android.sdk.capture.ui.camera;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.common.cryptography.Cryptography;
import com.onfido.android.sdk.capture.common.cryptography.PayloadHelper;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.camera.factory.CameraFactory;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.performance.AggregatedPerformanceAnalytics;
import com.onfido.android.sdk.capture.internal.performance.trackers.ScreenLoadTracker;
import com.onfido.android.sdk.capture.internal.service.AnnouncementService;
import com.onfido.android.sdk.capture.internal.service.VibratorService;
import com.onfido.android.sdk.capture.internal.util.DispatchersProvider;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.environment.EnvironmentIntegrityChecker;
import com.onfido.android.sdk.capture.network.OnfidoApiService;
import com.onfido.android.sdk.capture.ui.BaseActivity_MembersInjector;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.api.client.token.TokenProvider;
import com.onfido.dagger.MembersInjector;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class CaptureActivity_MembersInjector implements MembersInjector<CaptureActivity> {
    private final Provider<AnnouncementService> announcementServiceProvider;
    private final Provider<CameraFactory> cameraFactoryProvider;
    private final Provider<Cryptography> cryptographyProvider;
    private final Provider<DispatchersProvider> dispatchersProvider;
    private final Provider<EnvironmentIntegrityChecker> environmentIntegrityCheckerProvider;
    private final Provider<FlowTracker> flowTrackerProvider;
    private final Provider<ImageUtils> imageUtilsProvider;
    private final Provider<OnfidoApiService> onfidoApiServiceProvider;
    private final Provider<OnfidoConfig> onfidoConfigProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<PayloadHelper> payloadHelperProvider;
    private final Provider<AggregatedPerformanceAnalytics> performanceAnalyticsProvider;
    private final Provider<CapturePresenter> presenterProvider;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<ScreenLoadTracker> screenLoadTrackerProvider;
    private final Provider<TokenProvider> tokenProvider;
    private final Provider<VibratorService> vibratorServiceProvider;
    private final Provider<WaitingScreenTracker> waitingScreenTrackerProvider;

    public CaptureActivity_MembersInjector(Provider<AggregatedPerformanceAnalytics> provider, Provider<OnfidoConfig> provider2, Provider<WaitingScreenTracker> provider3, Provider<OnfidoRemoteConfig> provider4, Provider<FlowTracker> provider5, Provider<CapturePresenter> provider6, Provider<ImageUtils> provider7, Provider<OnfidoApiService> provider8, Provider<AnnouncementService> provider9, Provider<VibratorService> provider10, Provider<SchedulersProvider> provider11, Provider<ScreenLoadTracker> provider12, Provider<OnfidoRemoteConfig> provider13, Provider<Cryptography> provider14, Provider<PayloadHelper> provider15, Provider<CameraFactory> provider16, Provider<TokenProvider> provider17, Provider<EnvironmentIntegrityChecker> provider18, Provider<DispatchersProvider> provider19) {
        this.performanceAnalyticsProvider = provider;
        this.onfidoConfigProvider = provider2;
        this.waitingScreenTrackerProvider = provider3;
        this.remoteConfigProvider = provider4;
        this.flowTrackerProvider = provider5;
        this.presenterProvider = provider6;
        this.imageUtilsProvider = provider7;
        this.onfidoApiServiceProvider = provider8;
        this.announcementServiceProvider = provider9;
        this.vibratorServiceProvider = provider10;
        this.schedulersProvider = provider11;
        this.screenLoadTrackerProvider = provider12;
        this.onfidoRemoteConfigProvider = provider13;
        this.cryptographyProvider = provider14;
        this.payloadHelperProvider = provider15;
        this.cameraFactoryProvider = provider16;
        this.tokenProvider = provider17;
        this.environmentIntegrityCheckerProvider = provider18;
        this.dispatchersProvider = provider19;
    }

    public static MembersInjector<CaptureActivity> create(Provider<AggregatedPerformanceAnalytics> provider, Provider<OnfidoConfig> provider2, Provider<WaitingScreenTracker> provider3, Provider<OnfidoRemoteConfig> provider4, Provider<FlowTracker> provider5, Provider<CapturePresenter> provider6, Provider<ImageUtils> provider7, Provider<OnfidoApiService> provider8, Provider<AnnouncementService> provider9, Provider<VibratorService> provider10, Provider<SchedulersProvider> provider11, Provider<ScreenLoadTracker> provider12, Provider<OnfidoRemoteConfig> provider13, Provider<Cryptography> provider14, Provider<PayloadHelper> provider15, Provider<CameraFactory> provider16, Provider<TokenProvider> provider17, Provider<EnvironmentIntegrityChecker> provider18, Provider<DispatchersProvider> provider19) {
        return new CaptureActivity_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17, provider18, provider19);
    }

    public static void injectAnnouncementService(CaptureActivity captureActivity, AnnouncementService announcementService) {
        captureActivity.announcementService = announcementService;
    }

    public static void injectCameraFactory(CaptureActivity captureActivity, CameraFactory cameraFactory) {
        captureActivity.cameraFactory = cameraFactory;
    }

    public static void injectCryptography(CaptureActivity captureActivity, Cryptography cryptography) {
        captureActivity.cryptography = cryptography;
    }

    public static void injectDispatchersProvider(CaptureActivity captureActivity, DispatchersProvider dispatchersProvider) {
        captureActivity.dispatchersProvider = dispatchersProvider;
    }

    public static void injectEnvironmentIntegrityChecker(CaptureActivity captureActivity, EnvironmentIntegrityChecker environmentIntegrityChecker) {
        captureActivity.environmentIntegrityChecker = environmentIntegrityChecker;
    }

    public static void injectImageUtils(CaptureActivity captureActivity, ImageUtils imageUtils) {
        captureActivity.imageUtils = imageUtils;
    }

    public static void injectOnfidoApiService(CaptureActivity captureActivity, OnfidoApiService onfidoApiService) {
        captureActivity.onfidoApiService = onfidoApiService;
    }

    public static void injectOnfidoRemoteConfig(CaptureActivity captureActivity, OnfidoRemoteConfig onfidoRemoteConfig) {
        captureActivity.onfidoRemoteConfig = onfidoRemoteConfig;
    }

    public static void injectPayloadHelper(CaptureActivity captureActivity, PayloadHelper payloadHelper) {
        captureActivity.payloadHelper = payloadHelper;
    }

    public static void injectPresenter(CaptureActivity captureActivity, CapturePresenter capturePresenter) {
        captureActivity.presenter = capturePresenter;
    }

    public static void injectSchedulersProvider(CaptureActivity captureActivity, SchedulersProvider schedulersProvider) {
        captureActivity.schedulersProvider = schedulersProvider;
    }

    public static void injectScreenLoadTracker(CaptureActivity captureActivity, ScreenLoadTracker screenLoadTracker) {
        captureActivity.screenLoadTracker = screenLoadTracker;
    }

    public static void injectTokenProvider(CaptureActivity captureActivity, TokenProvider tokenProvider) {
        captureActivity.tokenProvider = tokenProvider;
    }

    public static void injectVibratorService(CaptureActivity captureActivity, VibratorService vibratorService) {
        captureActivity.vibratorService = vibratorService;
    }

    @Override // com.onfido.dagger.MembersInjector
    public void injectMembers(CaptureActivity captureActivity) {
        BaseActivity_MembersInjector.injectPerformanceAnalytics(captureActivity, this.performanceAnalyticsProvider.get());
        BaseActivity_MembersInjector.injectOnfidoConfig(captureActivity, this.onfidoConfigProvider.get());
        BaseActivity_MembersInjector.injectWaitingScreenTracker(captureActivity, this.waitingScreenTrackerProvider.get());
        BaseActivity_MembersInjector.injectRemoteConfig(captureActivity, this.remoteConfigProvider.get());
        BaseActivity_MembersInjector.injectFlowTracker(captureActivity, this.flowTrackerProvider.get());
        injectPresenter(captureActivity, this.presenterProvider.get());
        injectImageUtils(captureActivity, this.imageUtilsProvider.get());
        injectOnfidoApiService(captureActivity, this.onfidoApiServiceProvider.get());
        injectAnnouncementService(captureActivity, this.announcementServiceProvider.get());
        injectVibratorService(captureActivity, this.vibratorServiceProvider.get());
        injectSchedulersProvider(captureActivity, this.schedulersProvider.get());
        injectScreenLoadTracker(captureActivity, this.screenLoadTrackerProvider.get());
        injectOnfidoRemoteConfig(captureActivity, this.onfidoRemoteConfigProvider.get());
        injectCryptography(captureActivity, this.cryptographyProvider.get());
        injectPayloadHelper(captureActivity, this.payloadHelperProvider.get());
        injectCameraFactory(captureActivity, this.cameraFactoryProvider.get());
        injectTokenProvider(captureActivity, this.tokenProvider.get());
        injectEnvironmentIntegrityChecker(captureActivity, this.environmentIntegrityCheckerProvider.get());
        injectDispatchersProvider(captureActivity, this.dispatchersProvider.get());
    }
}
