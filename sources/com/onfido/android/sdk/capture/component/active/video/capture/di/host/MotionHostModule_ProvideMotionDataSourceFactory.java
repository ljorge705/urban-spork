package com.onfido.android.sdk.capture.component.active.video.capture.di.host;

import com.onfido.android.sdk.capture.component.active.video.capture.data.demo.MotionDemoDataSource;
import com.onfido.android.sdk.capture.component.active.video.capture.data.remote.MotionRemoteDataSource;
import com.onfido.android.sdk.capture.component.active.video.capture.data.repository.MotionDataSource;
import com.onfido.android.sdk.capture.internal.token.OnfidoTokenProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MotionHostModule_ProvideMotionDataSourceFactory implements Factory<MotionDataSource> {
    private final MotionHostModule module;
    private final Provider<MotionDemoDataSource> motionDemoDataSourceProvider;
    private final Provider<MotionRemoteDataSource> motionRemoteDataSourceProvider;
    private final Provider<OnfidoTokenProvider> onfidoTokenProvider;

    public MotionHostModule_ProvideMotionDataSourceFactory(MotionHostModule motionHostModule, Provider<OnfidoTokenProvider> provider, Provider<MotionDemoDataSource> provider2, Provider<MotionRemoteDataSource> provider3) {
        this.module = motionHostModule;
        this.onfidoTokenProvider = provider;
        this.motionDemoDataSourceProvider = provider2;
        this.motionRemoteDataSourceProvider = provider3;
    }

    public static MotionHostModule_ProvideMotionDataSourceFactory create(MotionHostModule motionHostModule, Provider<OnfidoTokenProvider> provider, Provider<MotionDemoDataSource> provider2, Provider<MotionRemoteDataSource> provider3) {
        return new MotionHostModule_ProvideMotionDataSourceFactory(motionHostModule, provider, provider2, provider3);
    }

    public static MotionDataSource provideMotionDataSource(MotionHostModule motionHostModule, OnfidoTokenProvider onfidoTokenProvider, MotionDemoDataSource motionDemoDataSource, MotionRemoteDataSource motionRemoteDataSource) {
        return (MotionDataSource) Preconditions.checkNotNullFromProvides(motionHostModule.provideMotionDataSource(onfidoTokenProvider, motionDemoDataSource, motionRemoteDataSource));
    }

    @Override // com.onfido.javax.inject.Provider
    public MotionDataSource get() {
        return provideMotionDataSource(this.module, this.onfidoTokenProvider.get(), this.motionDemoDataSourceProvider.get(), this.motionRemoteDataSourceProvider.get());
    }
}
