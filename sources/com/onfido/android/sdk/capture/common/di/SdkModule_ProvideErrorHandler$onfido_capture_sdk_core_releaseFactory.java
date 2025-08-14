package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.network.error.ErrorHandler;
import com.onfido.api.client.ErrorParser;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideErrorHandler$onfido_capture_sdk_core_releaseFactory implements Factory<ErrorHandler> {
    private final Provider<ErrorParser> errorParserProvider;
    private final SdkModule module;

    public SdkModule_ProvideErrorHandler$onfido_capture_sdk_core_releaseFactory(SdkModule sdkModule, Provider<ErrorParser> provider) {
        this.module = sdkModule;
        this.errorParserProvider = provider;
    }

    public static SdkModule_ProvideErrorHandler$onfido_capture_sdk_core_releaseFactory create(SdkModule sdkModule, Provider<ErrorParser> provider) {
        return new SdkModule_ProvideErrorHandler$onfido_capture_sdk_core_releaseFactory(sdkModule, provider);
    }

    public static ErrorHandler provideErrorHandler$onfido_capture_sdk_core_release(SdkModule sdkModule, ErrorParser errorParser) {
        return (ErrorHandler) Preconditions.checkNotNullFromProvides(sdkModule.provideErrorHandler$onfido_capture_sdk_core_release(errorParser));
    }

    @Override // com.onfido.javax.inject.Provider
    public ErrorHandler get() {
        return provideErrorHandler$onfido_capture_sdk_core_release(this.module, this.errorParserProvider.get());
    }
}
