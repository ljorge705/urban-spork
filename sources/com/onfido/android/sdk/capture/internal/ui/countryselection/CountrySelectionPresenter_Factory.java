package com.onfido.android.sdk.capture.internal.ui.countryselection;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class CountrySelectionPresenter_Factory implements Factory<CountrySelectionPresenter> {
    private final Provider<GetCurrentCountryCodeUseCase> getCurrentCountryCodeUseCaseProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<ScreenTracker> screenTrackerProvider;
    private final Provider<SupportedDocumentsRepository> supportedDocumentsRepositoryProvider;

    public CountrySelectionPresenter_Factory(Provider<SupportedDocumentsRepository> provider, Provider<ScreenTracker> provider2, Provider<GetCurrentCountryCodeUseCase> provider3, Provider<SchedulersProvider> provider4) {
        this.supportedDocumentsRepositoryProvider = provider;
        this.screenTrackerProvider = provider2;
        this.getCurrentCountryCodeUseCaseProvider = provider3;
        this.schedulersProvider = provider4;
    }

    public static CountrySelectionPresenter_Factory create(Provider<SupportedDocumentsRepository> provider, Provider<ScreenTracker> provider2, Provider<GetCurrentCountryCodeUseCase> provider3, Provider<SchedulersProvider> provider4) {
        return new CountrySelectionPresenter_Factory(provider, provider2, provider3, provider4);
    }

    public static CountrySelectionPresenter newInstance(SupportedDocumentsRepository supportedDocumentsRepository, ScreenTracker screenTracker, GetCurrentCountryCodeUseCase getCurrentCountryCodeUseCase, SchedulersProvider schedulersProvider) {
        return new CountrySelectionPresenter(supportedDocumentsRepository, screenTracker, getCurrentCountryCodeUseCase, schedulersProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public CountrySelectionPresenter get() {
        return newInstance(this.supportedDocumentsRepositoryProvider.get(), this.screenTrackerProvider.get(), this.getCurrentCountryCodeUseCaseProvider.get(), this.schedulersProvider.get());
    }
}
