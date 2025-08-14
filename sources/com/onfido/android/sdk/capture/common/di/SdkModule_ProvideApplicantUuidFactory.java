package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideApplicantUuidFactory implements Factory<ApplicantId> {
    private final SdkModule module;
    private final Provider<OnfidoTokenProvider> tokenProvider;

    public SdkModule_ProvideApplicantUuidFactory(SdkModule sdkModule, Provider<OnfidoTokenProvider> provider) {
        this.module = sdkModule;
        this.tokenProvider = provider;
    }

    public static SdkModule_ProvideApplicantUuidFactory create(SdkModule sdkModule, Provider<OnfidoTokenProvider> provider) {
        return new SdkModule_ProvideApplicantUuidFactory(sdkModule, provider);
    }

    public static ApplicantId provideApplicantUuid(SdkModule sdkModule, OnfidoTokenProvider onfidoTokenProvider) {
        return (ApplicantId) Preconditions.checkNotNullFromProvides(sdkModule.provideApplicantUuid(onfidoTokenProvider));
    }

    @Override // com.onfido.javax.inject.Provider
    public ApplicantId get() {
        return provideApplicantUuid(this.module, this.tokenProvider.get());
    }
}
