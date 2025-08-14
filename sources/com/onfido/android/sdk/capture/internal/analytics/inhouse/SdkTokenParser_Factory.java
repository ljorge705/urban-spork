package com.onfido.android.sdk.capture.internal.analytics.inhouse;

import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;

/* loaded from: classes2.dex */
public final class SdkTokenParser_Factory implements Factory<SdkTokenParser> {
    private final Provider<Json> jsonParserProvider;

    public SdkTokenParser_Factory(Provider<Json> provider) {
        this.jsonParserProvider = provider;
    }

    public static SdkTokenParser_Factory create(Provider<Json> provider) {
        return new SdkTokenParser_Factory(provider);
    }

    public static SdkTokenParser newInstance(Json json) {
        return new SdkTokenParser(json);
    }

    @Override // com.onfido.javax.inject.Provider
    public SdkTokenParser get() {
        return newInstance(this.jsonParserProvider.get());
    }
}
