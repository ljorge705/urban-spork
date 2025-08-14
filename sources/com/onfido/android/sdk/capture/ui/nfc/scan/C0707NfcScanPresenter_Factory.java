package com.onfido.android.sdk.capture.ui.nfc.scan;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.common.preferences.SharedPreferencesDataSource;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.nfc.NfcInteractor;
import com.onfido.android.sdk.capture.internal.nfc.NfcTimeouts;
import com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents;
import com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.nfc.MRTDDataGroup;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.javax.inject.Provider;

/* renamed from: com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter_Factory, reason: case insensitive filesystem */
/* loaded from: classes2.dex */
public final class C0707NfcScanPresenter_Factory {
    private final Provider<NfcInteractor> nfcInteractorProvider;
    private final Provider<NfcTimeouts> nfcTimeoutsProvider;
    private final Provider<NfcTracker> nfcTrackerProvider;
    private final Provider<OnfidoRemoteConfig> remoteConfigProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<SharedPreferencesDataSource> storageProvider;

    public C0707NfcScanPresenter_Factory(Provider<NfcInteractor> provider, Provider<SchedulersProvider> provider2, Provider<NfcTracker> provider3, Provider<NfcTimeouts> provider4, Provider<SharedPreferencesDataSource> provider5, Provider<OnfidoRemoteConfig> provider6) {
        this.nfcInteractorProvider = provider;
        this.schedulersProvider = provider2;
        this.nfcTrackerProvider = provider3;
        this.nfcTimeoutsProvider = provider4;
        this.storageProvider = provider5;
        this.remoteConfigProvider = provider6;
    }

    public static C0707NfcScanPresenter_Factory create(Provider<NfcInteractor> provider, Provider<SchedulersProvider> provider2, Provider<NfcTracker> provider3, Provider<NfcTimeouts> provider4, Provider<SharedPreferencesDataSource> provider5, Provider<OnfidoRemoteConfig> provider6) {
        return new C0707NfcScanPresenter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static NfcScanPresenter newInstance(DocumentType documentType, CountryCode countryCode, NfcFlowType nfcFlowType, PassportBACKey passportBACKey, byte[] bArr, boolean z, MRTDDataGroup[] mRTDDataGroupArr, RealtimeNfcEvents realtimeNfcEvents, NfcInteractor nfcInteractor, SchedulersProvider schedulersProvider, NfcTracker nfcTracker, NfcTimeouts nfcTimeouts, SharedPreferencesDataSource sharedPreferencesDataSource, OnfidoRemoteConfig onfidoRemoteConfig) {
        return new NfcScanPresenter(documentType, countryCode, nfcFlowType, passportBACKey, bArr, z, mRTDDataGroupArr, realtimeNfcEvents, nfcInteractor, schedulersProvider, nfcTracker, nfcTimeouts, sharedPreferencesDataSource, onfidoRemoteConfig);
    }

    public NfcScanPresenter get(DocumentType documentType, CountryCode countryCode, NfcFlowType nfcFlowType, PassportBACKey passportBACKey, byte[] bArr, boolean z, MRTDDataGroup[] mRTDDataGroupArr, RealtimeNfcEvents realtimeNfcEvents) {
        return newInstance(documentType, countryCode, nfcFlowType, passportBACKey, bArr, z, mRTDDataGroupArr, realtimeNfcEvents, this.nfcInteractorProvider.get(), this.schedulersProvider.get(), this.nfcTrackerProvider.get(), this.nfcTimeoutsProvider.get(), this.storageProvider.get(), this.remoteConfigProvider.get());
    }
}
