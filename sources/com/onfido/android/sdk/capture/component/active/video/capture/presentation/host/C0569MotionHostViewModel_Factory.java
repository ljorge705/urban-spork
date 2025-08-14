package com.onfido.android.sdk.capture.component.active.video.capture.presentation.host;

import androidx.lifecycle.SavedStateHandle;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.component.active.video.capture.domain.usecase.GetFaceDetectionModuleAvailabilityUseCase;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.OnfidoAnalytics;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.javax.inject.Provider;

/* renamed from: com.onfido.android.sdk.capture.component.active.video.capture.presentation.host.MotionHostViewModel_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0569MotionHostViewModel_Factory {
    private final Provider<OnfidoAnalytics> analyticsProvider;
    private final Provider<GetFaceDetectionModuleAvailabilityUseCase> getFaceDetectionModuleAvailabilityUseCaseProvider;
    private final Provider<NavigationManagerHolder> navigationManagerHolderProvider;
    private final Provider<Navigator> navigatorProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<RuntimePermissionsManager> runtimePermissionsManagerProvider;
    private final Provider<SchedulersProvider> schedulersProvider;

    public C0569MotionHostViewModel_Factory(Provider<Navigator> provider, Provider<NavigationManagerHolder> provider2, Provider<RuntimePermissionsManager> provider3, Provider<OnfidoAnalytics> provider4, Provider<OnfidoRemoteConfig> provider5, Provider<GetFaceDetectionModuleAvailabilityUseCase> provider6, Provider<SchedulersProvider> provider7) {
        this.navigatorProvider = provider;
        this.navigationManagerHolderProvider = provider2;
        this.runtimePermissionsManagerProvider = provider3;
        this.analyticsProvider = provider4;
        this.onfidoRemoteConfigProvider = provider5;
        this.getFaceDetectionModuleAvailabilityUseCaseProvider = provider6;
        this.schedulersProvider = provider7;
    }

    public static C0569MotionHostViewModel_Factory create(Provider<Navigator> provider, Provider<NavigationManagerHolder> provider2, Provider<RuntimePermissionsManager> provider3, Provider<OnfidoAnalytics> provider4, Provider<OnfidoRemoteConfig> provider5, Provider<GetFaceDetectionModuleAvailabilityUseCase> provider6, Provider<SchedulersProvider> provider7) {
        return new C0569MotionHostViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static MotionHostViewModel newInstance(Navigator navigator, NavigationManagerHolder navigationManagerHolder, RuntimePermissionsManager runtimePermissionsManager, OnfidoAnalytics onfidoAnalytics, OnfidoRemoteConfig onfidoRemoteConfig, GetFaceDetectionModuleAvailabilityUseCase getFaceDetectionModuleAvailabilityUseCase, SchedulersProvider schedulersProvider, SavedStateHandle savedStateHandle, boolean z, boolean z2) {
        return new MotionHostViewModel(navigator, navigationManagerHolder, runtimePermissionsManager, onfidoAnalytics, onfidoRemoteConfig, getFaceDetectionModuleAvailabilityUseCase, schedulersProvider, savedStateHandle, z, z2);
    }

    public MotionHostViewModel get(SavedStateHandle savedStateHandle, boolean z, boolean z2) {
        return newInstance(this.navigatorProvider.get(), this.navigationManagerHolderProvider.get(), this.runtimePermissionsManagerProvider.get(), this.analyticsProvider.get(), this.onfidoRemoteConfigProvider.get(), this.getFaceDetectionModuleAvailabilityUseCaseProvider.get(), this.schedulersProvider.get(), savedStateHandle, z, z2);
    }
}
