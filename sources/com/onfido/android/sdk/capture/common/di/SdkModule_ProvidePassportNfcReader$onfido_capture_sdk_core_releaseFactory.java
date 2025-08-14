package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.internal.analytics.inhouse.trackers.NfcTracker;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.nfc.PassportNfcReader;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvidePassportNfcReader$onfido_capture_sdk_core_releaseFactory implements Factory<PassportNfcReader> {
    private final SdkModule module;
    private final Provider<NfcTracker> nfcTrackerProvider;
    private final Provider<OnfidoRemoteConfig> onfidoRemoteConfigProvider;

    public SdkModule_ProvidePassportNfcReader$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule, Provider<OnfidoRemoteConfig> provider, Provider<NfcTracker> provider2) {
        this.module = sdkModule;
        this.onfidoRemoteConfigProvider = provider;
        this.nfcTrackerProvider = provider2;
    }

    public static SdkModule_ProvidePassportNfcReader$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule, Provider<OnfidoRemoteConfig> provider, Provider<NfcTracker> provider2) {
        return new SdkModule_ProvidePassportNfcReader$onfido_capture_sdk_core_releaseFactory(sdkModule, provider, provider2);
    }

    public static PassportNfcReader providePassportNfcReader$onfido_capture_sdk_core_release(SdkModule sdkModule, OnfidoRemoteConfig onfidoRemoteConfig, NfcTracker nfcTracker) {
        return (PassportNfcReader) Preconditions.checkNotNullFromProvides(sdkModule.providePassportNfcReader$onfido_capture_sdk_core_release(onfidoRemoteConfig, nfcTracker));
    }

    @Override // com.onfido.javax.inject.Provider
    public PassportNfcReader get() {
        return providePassportNfcReader$onfido_capture_sdk_core_release(this.module, this.onfidoRemoteConfigProvider.get(), this.nfcTrackerProvider.get());
    }
}
