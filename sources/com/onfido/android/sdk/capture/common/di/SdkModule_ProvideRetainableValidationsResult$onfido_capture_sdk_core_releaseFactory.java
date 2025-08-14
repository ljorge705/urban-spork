package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.internal.validation.RetainableValidationsResult;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideRetainableValidationsResult$onfido_capture_sdk_core_releaseFactory implements Factory<RetainableValidationsResult> {
    private final SdkModule module;

    public SdkModule_ProvideRetainableValidationsResult$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule) {
        this.module = sdkModule;
    }

    public static SdkModule_ProvideRetainableValidationsResult$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule) {
        return new SdkModule_ProvideRetainableValidationsResult$onfido_capture_sdk_core_releaseFactory(sdkModule);
    }

    public static RetainableValidationsResult provideRetainableValidationsResult$onfido_capture_sdk_core_release(SdkModule sdkModule) {
        return (RetainableValidationsResult) Preconditions.checkNotNullFromProvides(sdkModule.provideRetainableValidationsResult$onfido_capture_sdk_core_release());
    }

    @Override // com.onfido.javax.inject.Provider
    public RetainableValidationsResult get() {
        return provideRetainableValidationsResult$onfido_capture_sdk_core_release(this.module);
    }
}
