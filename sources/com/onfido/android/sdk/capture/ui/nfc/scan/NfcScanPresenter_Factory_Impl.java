package com.onfido.android.sdk.capture.ui.nfc.scan;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.analytics.inhouse.domain.NfcFlowType;
import com.onfido.android.sdk.capture.internal.nfc.RealtimeNfcEvents;
import com.onfido.android.sdk.capture.internal.nfc.data.PassportBACKey;
import com.onfido.android.sdk.capture.nfc.MRTDDataGroup;
import com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter;
import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.dagger.internal.InstanceFactory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class NfcScanPresenter_Factory_Impl implements NfcScanPresenter.Factory {
    private final C0707NfcScanPresenter_Factory delegateFactory;

    NfcScanPresenter_Factory_Impl(C0707NfcScanPresenter_Factory c0707NfcScanPresenter_Factory) {
        this.delegateFactory = c0707NfcScanPresenter_Factory;
    }

    @Override // com.onfido.android.sdk.capture.ui.nfc.scan.NfcScanPresenter.Factory
    public NfcScanPresenter create(DocumentType documentType, CountryCode countryCode, NfcFlowType nfcFlowType, PassportBACKey passportBACKey, byte[] bArr, boolean z, MRTDDataGroup[] mRTDDataGroupArr, RealtimeNfcEvents realtimeNfcEvents) {
        return this.delegateFactory.get(documentType, countryCode, nfcFlowType, passportBACKey, bArr, z, mRTDDataGroupArr, realtimeNfcEvents);
    }

    public static Provider<NfcScanPresenter.Factory> create(C0707NfcScanPresenter_Factory c0707NfcScanPresenter_Factory) {
        return InstanceFactory.create(new NfcScanPresenter_Factory_Impl(c0707NfcScanPresenter_Factory));
    }
}
