package com.onfido.android.sdk.capture.component.active.video.capture.di.host;

import com.onfido.android.sdk.capture.component.active.video.capture.data.remote.MotionApi;
import com.onfido.api.client.OnfidoFetcher;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionHostModule_ProvideMotionApiFactory implements Factory<MotionApi> {
    private final MotionHostModule module;
    private final Provider<OnfidoFetcher> onfidoFetcherProvider;

    public MotionHostModule_ProvideMotionApiFactory(MotionHostModule motionHostModule, Provider<OnfidoFetcher> provider) {
        this.module = motionHostModule;
        this.onfidoFetcherProvider = provider;
    }

    public static MotionHostModule_ProvideMotionApiFactory create(MotionHostModule motionHostModule, Provider<OnfidoFetcher> provider) {
        return new MotionHostModule_ProvideMotionApiFactory(motionHostModule, provider);
    }

    public static MotionApi provideMotionApi(MotionHostModule motionHostModule, OnfidoFetcher onfidoFetcher) {
        return (MotionApi) Preconditions.checkNotNullFromProvides(motionHostModule.provideMotionApi(onfidoFetcher));
    }

    @Override // com.onfido.javax.inject.Provider
    public MotionApi get() {
        return provideMotionApi(this.module, this.onfidoFetcherProvider.get());
    }
}
