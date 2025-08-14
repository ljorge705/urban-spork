package com.onfido.android.sdk.capture.internal.ui.countryselection;

import android.telephony.TelephonyManager;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class GetCurrentCountryCodeUseCase_Factory implements Factory<GetCurrentCountryCodeUseCase> {
    private final Provider<TelephonyManager> telephonyManagerProvider;

    public GetCurrentCountryCodeUseCase_Factory(Provider<TelephonyManager> provider) {
        this.telephonyManagerProvider = provider;
    }

    public static GetCurrentCountryCodeUseCase_Factory create(Provider<TelephonyManager> provider) {
        return new GetCurrentCountryCodeUseCase_Factory(provider);
    }

    public static GetCurrentCountryCodeUseCase newInstance(TelephonyManager telephonyManager) {
        return new GetCurrentCountryCodeUseCase(telephonyManager);
    }

    @Override // com.onfido.javax.inject.Provider
    public GetCurrentCountryCodeUseCase get() {
        return newInstance(this.telephonyManagerProvider.get());
    }
}
