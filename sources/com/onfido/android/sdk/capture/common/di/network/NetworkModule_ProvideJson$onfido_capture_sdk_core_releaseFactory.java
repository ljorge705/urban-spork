package com.onfido.android.sdk.capture.common.di.network;

import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import kotlinx.serialization.json.Json;

/* loaded from: classes2.dex */
public final class NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory implements Factory<Json> {

    private static final class InstanceHolder {
        private static final NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory INSTANCE = new NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory();

        private InstanceHolder() {
        }
    }

    public static NetworkModule_ProvideJson$onfido_capture_sdk_core_releaseFactory create() {
        return InstanceHolder.INSTANCE;
    }

    public static Json provideJson$onfido_capture_sdk_core_release() {
        return (Json) Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideJson$onfido_capture_sdk_core_release());
    }

    @Override // com.onfido.javax.inject.Provider
    public Json get() {
        return provideJson$onfido_capture_sdk_core_release();
    }
}
