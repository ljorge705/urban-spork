package com.onfido.android.sdk.capture.ui.documentselection.host;

import androidx.lifecycle.SavedStateHandle;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.internal.navigation.Navigator;
import com.onfido.android.sdk.capture.internal.navigation.navigator.NavigationManagerHolder;
import com.onfido.android.sdk.capture.internal.performance.trackers.ScreenLoadTracker;
import com.onfido.android.sdk.capture.internal.ui.countryselection.GetCurrentCountryCodeUseCase;
import com.onfido.android.sdk.capture.internal.ui.countryselection.SupportedDocumentsRepository;
import com.onfido.javax.inject.Provider;

/* renamed from: com.onfido.android.sdk.capture.ui.documentselection.host.DocumentSelectionHostViewModel_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0695DocumentSelectionHostViewModel_Factory {
    private final Provider<GetCurrentCountryCodeUseCase> currentCountryCodeUseCaseProvider;
    private final Provider<NavigationManagerHolder> navigationManagerHolderProvider;
    private final Provider<Navigator> navigatorProvider;
    private final Provider<ScreenLoadTracker> screenLoadTrackerProvider;
    private final Provider<ScreenTracker> screenTrackerProvider;
    private final Provider<SupportedDocumentsRepository> supportedDocumentsRepositoryProvider;

    public C0695DocumentSelectionHostViewModel_Factory(Provider<Navigator> provider, Provider<NavigationManagerHolder> provider2, Provider<GetCurrentCountryCodeUseCase> provider3, Provider<SupportedDocumentsRepository> provider4, Provider<ScreenTracker> provider5, Provider<ScreenLoadTracker> provider6) {
        this.navigatorProvider = provider;
        this.navigationManagerHolderProvider = provider2;
        this.currentCountryCodeUseCaseProvider = provider3;
        this.supportedDocumentsRepositoryProvider = provider4;
        this.screenTrackerProvider = provider5;
        this.screenLoadTrackerProvider = provider6;
    }

    public static C0695DocumentSelectionHostViewModel_Factory create(Provider<Navigator> provider, Provider<NavigationManagerHolder> provider2, Provider<GetCurrentCountryCodeUseCase> provider3, Provider<SupportedDocumentsRepository> provider4, Provider<ScreenTracker> provider5, Provider<ScreenLoadTracker> provider6) {
        return new C0695DocumentSelectionHostViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static DocumentSelectionHostViewModel newInstance(Navigator navigator, NavigationManagerHolder navigationManagerHolder, GetCurrentCountryCodeUseCase getCurrentCountryCodeUseCase, SupportedDocumentsRepository supportedDocumentsRepository, ScreenTracker screenTracker, ScreenLoadTracker screenLoadTracker, SavedStateHandle savedStateHandle) {
        return new DocumentSelectionHostViewModel(navigator, navigationManagerHolder, getCurrentCountryCodeUseCase, supportedDocumentsRepository, screenTracker, screenLoadTracker, savedStateHandle);
    }

    public DocumentSelectionHostViewModel get(SavedStateHandle savedStateHandle) {
        return newInstance(this.navigatorProvider.get(), this.navigationManagerHolderProvider.get(), this.currentCountryCodeUseCaseProvider.get(), this.supportedDocumentsRepositoryProvider.get(), this.screenTrackerProvider.get(), this.screenLoadTrackerProvider.get(), savedStateHandle);
    }
}
