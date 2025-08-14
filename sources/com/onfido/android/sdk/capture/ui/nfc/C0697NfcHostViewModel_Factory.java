package com.onfido.android.sdk.capture.ui.nfc;

import androidx.lifecycle.SavedStateHandle;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.ScreenTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.javax.inject.Provider;

/* renamed from: com.onfido.android.sdk.capture.ui.nfc.NfcHostViewModel_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0697NfcHostViewModel_Factory {
    private final Provider<NfcInteractor> nfcInteractorProvider;
    private final Provider<NfcTracker> nfcTrackerProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<ScreenTracker> screenTrackerProvider;
    private final Provider<SharedPreferencesDataSource> storageProvider;

    public C0697NfcHostViewModel_Factory(Provider<SchedulersProvider> provider, Provider<NfcInteractor> provider2, Provider<NfcTracker> provider3, Provider<ScreenTracker> provider4, Provider<OnfidoRemoteConfig> provider5, Provider<SharedPreferencesDataSource> provider6) {
        this.schedulersProvider = provider;
        this.nfcInteractorProvider = provider2;
        this.nfcTrackerProvider = provider3;
        this.screenTrackerProvider = provider4;
        this.onfidoRemoteConfigProvider = provider5;
        this.storageProvider = provider6;
    }

    public static C0697NfcHostViewModel_Factory create(Provider<SchedulersProvider> provider, Provider<NfcInteractor> provider2, Provider<NfcTracker> provider3, Provider<ScreenTracker> provider4, Provider<OnfidoRemoteConfig> provider5, Provider<SharedPreferencesDataSource> provider6) {
        return new C0697NfcHostViewModel_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static NfcHostViewModel newInstance(SchedulersProvider schedulersProvider, NfcInteractor nfcInteractor, NfcTracker nfcTracker, ScreenTracker screenTracker, OnfidoRemoteConfig onfidoRemoteConfig, SharedPreferencesDataSource sharedPreferencesDataSource, SavedStateHandle savedStateHandle) {
        return new NfcHostViewModel(schedulersProvider, nfcInteractor, nfcTracker, screenTracker, onfidoRemoteConfig, sharedPreferencesDataSource, savedStateHandle);
    }

    public NfcHostViewModel get(SavedStateHandle savedStateHandle) {
        return newInstance(this.schedulersProvider.get(), this.nfcInteractorProvider.get(), this.nfcTrackerProvider.get(), this.screenTrackerProvider.get(), this.onfidoRemoteConfigProvider.get(), this.storageProvider.get(), savedStateHandle);
    }
}
