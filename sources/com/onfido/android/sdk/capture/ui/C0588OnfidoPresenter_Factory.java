package com.onfido.android.sdk.capture.ui;

import com.onfido.android.sdk.capture.OnfidoConfig;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.document.DocumentConfigurationManager;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.FlowTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.LivenessTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.WaitingScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.nfc.ShouldLaunchNfcFlowUseCase;
import com.onfido.android.sdk.capture.internal.performance.trackers.ScreenLoadTracker;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.internal.util.logging.RemoteLoggerTree;
import com.onfido.android.sdk.capture.ui.nfc.NfcDataRepository;
import com.onfido.android.sdk.capture.utils.DeviceUtils;
import com.onfido.javax.inject.Provider;

/* renamed from: com.onfido.android.sdk.capture.ui.OnfidoPresenter_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0588OnfidoPresenter_Factory {
    private final Provider<DeviceUtils> deviceUtilsProvider;
    private final Provider<DocumentConfigurationManager> documentConfigurationManagerProvider;
    private final Provider<FlowTracker> flowTrackerProvider;
    private final Provider<LivenessTracker> livenessTrackerProvider;
    private final Provider<NfcDataRepository> nfcDataRepositoryProvider;
    private final Provider<NfcInteractor> nfcInteractorProvider;
    private final Provider<NfcTracker> nfcTrackerProvider;
    private final Provider<OnfidoPresenterInitializer> onfidoPresenterInitializerProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<RemoteLoggerTree> remoteLoggerTreeProvider;
    private final Provider<RuntimePermissionsManager> runtimePermissionsManagerProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<ScreenLoadTracker> screenLoadTrackerProvider;
    private final Provider<ShouldLaunchNfcFlowUseCase> shouldLaunchNfcFlowUseCaseProvider;
    private final Provider<WaitingScreenTracker> waitingScreenTrackerProvider;

    public C0588OnfidoPresenter_Factory(Provider<FlowTracker> provider, Provider<LivenessTracker> provider2, Provider<ScreenLoadTracker> provider3, Provider<NfcTracker> provider4, Provider<RuntimePermissionsManager> provider5, Provider<DocumentConfigurationManager> provider6, Provider<OnfidoPresenterInitializer> provider7, Provider<NfcDataRepository> provider8, Provider<SchedulersProvider> provider9, Provider<OnfidoRemoteConfig> provider10, Provider<RemoteLoggerTree> provider11, Provider<DeviceUtils> provider12, Provider<WaitingScreenTracker> provider13, Provider<NfcInteractor> provider14, Provider<ShouldLaunchNfcFlowUseCase> provider15) {
        this.flowTrackerProvider = provider;
        this.livenessTrackerProvider = provider2;
        this.screenLoadTrackerProvider = provider3;
        this.nfcTrackerProvider = provider4;
        this.runtimePermissionsManagerProvider = provider5;
        this.documentConfigurationManagerProvider = provider6;
        this.onfidoPresenterInitializerProvider = provider7;
        this.nfcDataRepositoryProvider = provider8;
        this.schedulersProvider = provider9;
        this.onfidoRemoteConfigProvider = provider10;
        this.remoteLoggerTreeProvider = provider11;
        this.deviceUtilsProvider = provider12;
        this.waitingScreenTrackerProvider = provider13;
        this.nfcInteractorProvider = provider14;
        this.shouldLaunchNfcFlowUseCaseProvider = provider15;
    }

    public static C0588OnfidoPresenter_Factory create(Provider<FlowTracker> provider, Provider<LivenessTracker> provider2, Provider<ScreenLoadTracker> provider3, Provider<NfcTracker> provider4, Provider<RuntimePermissionsManager> provider5, Provider<DocumentConfigurationManager> provider6, Provider<OnfidoPresenterInitializer> provider7, Provider<NfcDataRepository> provider8, Provider<SchedulersProvider> provider9, Provider<OnfidoRemoteConfig> provider10, Provider<RemoteLoggerTree> provider11, Provider<DeviceUtils> provider12, Provider<WaitingScreenTracker> provider13, Provider<NfcInteractor> provider14, Provider<ShouldLaunchNfcFlowUseCase> provider15) {
        return new C0588OnfidoPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15);
    }

    public static OnfidoPresenter newInstance(FlowTracker flowTracker, LivenessTracker livenessTracker, ScreenLoadTracker screenLoadTracker, NfcTracker nfcTracker, RuntimePermissionsManager runtimePermissionsManager, DocumentConfigurationManager documentConfigurationManager, OnfidoPresenterInitializer onfidoPresenterInitializer, NfcDataRepository nfcDataRepository, SchedulersProvider schedulersProvider, OnfidoRemoteConfig onfidoRemoteConfig, RemoteLoggerTree remoteLoggerTree, DeviceUtils deviceUtils, WaitingScreenTracker waitingScreenTracker, NfcInteractor nfcInteractor, ShouldLaunchNfcFlowUseCase shouldLaunchNfcFlowUseCase, OnfidoConfig onfidoConfig) {
        return new OnfidoPresenter(flowTracker, livenessTracker, screenLoadTracker, nfcTracker, runtimePermissionsManager, documentConfigurationManager, onfidoPresenterInitializer, nfcDataRepository, schedulersProvider, onfidoRemoteConfig, remoteLoggerTree, deviceUtils, waitingScreenTracker, nfcInteractor, shouldLaunchNfcFlowUseCase, onfidoConfig);
    }

    public OnfidoPresenter get(OnfidoConfig onfidoConfig) {
        return newInstance(this.flowTrackerProvider.get(), this.livenessTrackerProvider.get(), this.screenLoadTrackerProvider.get(), this.nfcTrackerProvider.get(), this.runtimePermissionsManagerProvider.get(), this.documentConfigurationManagerProvider.get(), this.onfidoPresenterInitializerProvider.get(), this.nfcDataRepositoryProvider.get(), this.schedulersProvider.get(), this.onfidoRemoteConfigProvider.get(), this.remoteLoggerTreeProvider.get(), this.deviceUtilsProvider.get(), this.waitingScreenTrackerProvider.get(), this.nfcInteractorProvider.get(), this.shouldLaunchNfcFlowUseCaseProvider.get(), onfidoConfig);
    }
}
