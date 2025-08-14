package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import android.nfc.NfcAdapter;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkCaptureProductionModule_Companion_ProvideNfcAdapterFactory implements Factory<NfcAdapter> {
    private final Provider<Context> contextProvider;

    public SdkCaptureProductionModule_Companion_ProvideNfcAdapterFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static SdkCaptureProductionModule_Companion_ProvideNfcAdapterFactory create(Provider<Context> provider) {
        return new SdkCaptureProductionModule_Companion_ProvideNfcAdapterFactory(provider);
    }

    public static NfcAdapter provideNfcAdapter(Context context) {
        return SdkCaptureProductionModule.INSTANCE.provideNfcAdapter(context);
    }

    @Override // com.onfido.javax.inject.Provider
    public NfcAdapter get() {
        return provideNfcAdapter(this.contextProvider.get());
    }
}
