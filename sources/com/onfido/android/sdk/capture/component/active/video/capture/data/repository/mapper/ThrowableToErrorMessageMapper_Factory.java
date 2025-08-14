package com.onfido.android.sdk.capture.component.active.video.capture.data.repository.mapper;

import android.content.res.Resources;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;

/* loaded from: classes2.dex */
public final class ThrowableToErrorMessageMapper_Factory implements Factory<ThrowableToErrorMessageMapper> {
    private final Provider<Json> jsonParserProvider;
    private final Provider<Resources> resourcesProvider;

    public ThrowableToErrorMessageMapper_Factory(Provider<Json> provider, Provider<Resources> provider2) {
        this.jsonParserProvider = provider;
        this.resourcesProvider = provider2;
    }

    public static ThrowableToErrorMessageMapper_Factory create(Provider<Json> provider, Provider<Resources> provider2) {
        return new ThrowableToErrorMessageMapper_Factory(provider, provider2);
    }

    public static ThrowableToErrorMessageMapper newInstance(Json json, Resources resources) {
        return new ThrowableToErrorMessageMapper(json, resources);
    }

    @Override // com.onfido.javax.inject.Provider
    public ThrowableToErrorMessageMapper get() {
        return newInstance(this.jsonParserProvider.get(), this.resourcesProvider.get());
    }
}
