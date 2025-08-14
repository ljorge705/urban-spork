package com.onfido.android.sdk.capture.component.active.video.capture.data.remote;

import android.content.Context;
import com.onfido.api.client.data.SdkUploadMetaData;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;
import kotlinx.serialization.json.Json;

/* loaded from: classes2.dex */
public final class MotionMetaDataHelper_Factory implements Factory<MotionMetaDataHelper> {
    private final Provider<Context> contextProvider;
    private final Provider<Json> jsonParserProvider;
    private final Provider<SdkUploadMetaData> metadataProvider;

    public MotionMetaDataHelper_Factory(Provider<Context> provider, Provider<SdkUploadMetaData> provider2, Provider<Json> provider3) {
        this.contextProvider = provider;
        this.metadataProvider = provider2;
        this.jsonParserProvider = provider3;
    }

    public static MotionMetaDataHelper_Factory create(Provider<Context> provider, Provider<SdkUploadMetaData> provider2, Provider<Json> provider3) {
        return new MotionMetaDataHelper_Factory(provider, provider2, provider3);
    }

    public static MotionMetaDataHelper newInstance(Context context, SdkUploadMetaData sdkUploadMetaData, Json json) {
        return new MotionMetaDataHelper(context, sdkUploadMetaData, json);
    }

    @Override // com.onfido.javax.inject.Provider
    public MotionMetaDataHelper get() {
        return newInstance(this.contextProvider.get(), this.metadataProvider.get(), this.jsonParserProvider.get());
    }
}
