package com.onfido.android.sdk.capture.component.active.video.capture.data.demo;

import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;

/* loaded from: classes2.dex */
public final class MotionDemoDataSource_Factory implements Factory<MotionDemoDataSource> {
    private final Provider<Json> jsonParserProvider;

    public MotionDemoDataSource_Factory(Provider<Json> provider) {
        this.jsonParserProvider = provider;
    }

    public static MotionDemoDataSource_Factory create(Provider<Json> provider) {
        return new MotionDemoDataSource_Factory(provider);
    }

    public static MotionDemoDataSource newInstance(Json json) {
        return new MotionDemoDataSource(json);
    }

    @Override // com.onfido.javax.inject.Provider
    public MotionDemoDataSource get() {
        return newInstance(this.jsonParserProvider.get());
    }
}
