package com.onfido.android.sdk.capture.common.cryptography;

import com.onfido.api.client.token.sdk.ApplicantId;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;

/* loaded from: classes2.dex */
public final class PayloadHelper_Factory implements Factory<PayloadHelper> {
    private final Provider<ApplicantId> applicantIdProvider;
    private final Provider<Cryptography> cryptographyProvider;
    private final Provider<Json> jsonParserProvider;

    public PayloadHelper_Factory(Provider<Cryptography> provider, Provider<Json> provider2, Provider<ApplicantId> provider3) {
        this.cryptographyProvider = provider;
        this.jsonParserProvider = provider2;
        this.applicantIdProvider = provider3;
    }

    public static PayloadHelper_Factory create(Provider<Cryptography> provider, Provider<Json> provider2, Provider<ApplicantId> provider3) {
        return new PayloadHelper_Factory(provider, provider2, provider3);
    }

    public static PayloadHelper newInstance(Cryptography cryptography, Json json, ApplicantId applicantId) {
        return new PayloadHelper(cryptography, json, applicantId);
    }

    @Override // com.onfido.javax.inject.Provider
    public PayloadHelper get() {
        return newInstance(this.cryptographyProvider.get(), this.jsonParserProvider.get(), this.applicantIdProvider.get());
    }
}
