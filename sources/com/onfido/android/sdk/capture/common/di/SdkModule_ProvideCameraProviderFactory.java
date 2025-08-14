package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import androidx.camera.lifecycle.ProcessCameraProvider;
import com.google.common.util.concurrent.ListenableFuture;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideCameraProviderFactory implements Factory<ListenableFuture<ProcessCameraProvider>> {
    private final Provider<Context> applicationContextProvider;
    private final SdkModule module;

    public SdkModule_ProvideCameraProviderFactory(SdkModule sdkModule, Provider<Context> provider) {
        this.module = sdkModule;
        this.applicationContextProvider = provider;
    }

    public static SdkModule_ProvideCameraProviderFactory create(SdkModule sdkModule, Provider<Context> provider) {
        return new SdkModule_ProvideCameraProviderFactory(sdkModule, provider);
    }

    public static ListenableFuture<ProcessCameraProvider> provideCameraProvider(SdkModule sdkModule, Context context) {
        return (ListenableFuture) Preconditions.checkNotNullFromProvides(sdkModule.provideCameraProvider(context));
    }

    @Override // com.onfido.javax.inject.Provider
    public ListenableFuture<ProcessCameraProvider> get() {
        return provideCameraProvider(this.module, this.applicationContextProvider.get());
    }
}
