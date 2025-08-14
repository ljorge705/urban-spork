package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideSdkContext$onfido_capture_sdk_core_releaseFactory implements Factory<Context> {
    private final SdkModule module;

    public SdkModule_ProvideSdkContext$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule) {
        this.module = sdkModule;
    }

    public static SdkModule_ProvideSdkContext$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule) {
        return new SdkModule_ProvideSdkContext$onfido_capture_sdk_core_releaseFactory(sdkModule);
    }

    public static Context provideSdkContext$onfido_capture_sdk_core_release(SdkModule sdkModule) {
        return (Context) Preconditions.checkNotNullFromProvides(sdkModule.provideSdkContext$onfido_capture_sdk_core_release());
    }

    @Override // com.onfido.javax.inject.Provider
    public Context get() {
        return provideSdkContext$onfido_capture_sdk_core_release(this.module);
    }
}
