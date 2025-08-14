package com.onfido.android.sdk.capture.component.active.video.capture.data.repository;

import android.content.Context;
import com.onfido.android.sdk.capture.common.result.FailureReason;
import com.onfido.android.sdk.capture.utils.mapper.Mapper;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class ActiveVideoCaptureRepositoryImpl_Factory implements Factory<ActiveVideoCaptureRepositoryImpl> {
    private final Provider<Context> contextProvider;
    private final Provider<Mapper<Throwable, FailureReason>> errorMapperProvider;
    private final Provider<MotionDataSource> motionDataSourceProvider;

    public ActiveVideoCaptureRepositoryImpl_Factory(Provider<MotionDataSource> provider, Provider<Mapper<Throwable, FailureReason>> provider2, Provider<Context> provider3) {
        this.motionDataSourceProvider = provider;
        this.errorMapperProvider = provider2;
        this.contextProvider = provider3;
    }

    public static ActiveVideoCaptureRepositoryImpl_Factory create(Provider<MotionDataSource> provider, Provider<Mapper<Throwable, FailureReason>> provider2, Provider<Context> provider3) {
        return new ActiveVideoCaptureRepositoryImpl_Factory(provider, provider2, provider3);
    }

    public static ActiveVideoCaptureRepositoryImpl newInstance(MotionDataSource motionDataSource, Mapper<Throwable, FailureReason> mapper, Context context) {
        return new ActiveVideoCaptureRepositoryImpl(motionDataSource, mapper, context);
    }

    @Override // com.onfido.javax.inject.Provider
    public ActiveVideoCaptureRepositoryImpl get() {
        return newInstance(this.motionDataSourceProvider.get(), this.errorMapperProvider.get(), this.contextProvider.get());
    }
}
