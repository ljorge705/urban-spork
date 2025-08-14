package com.onfido.android.sdk.capture.internal.usecase;

import android.os.ResultReceiver;
import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MediaCallbacksUseCase_Factory implements Factory<MediaCallbacksUseCase> {
    private final Provider<ResultReceiver> mediaCallbackProvider;
    private final Provider<SchedulersProvider> schedulersProvider;
    private final Provider<TimeProvider> timeProvider;

    public MediaCallbacksUseCase_Factory(Provider<ResultReceiver> provider, Provider<SchedulersProvider> provider2, Provider<TimeProvider> provider3) {
        this.mediaCallbackProvider = provider;
        this.schedulersProvider = provider2;
        this.timeProvider = provider3;
    }

    public static MediaCallbacksUseCase_Factory create(Provider<ResultReceiver> provider, Provider<SchedulersProvider> provider2, Provider<TimeProvider> provider3) {
        return new MediaCallbacksUseCase_Factory(provider, provider2, provider3);
    }

    public static MediaCallbacksUseCase newInstance(ResultReceiver resultReceiver, SchedulersProvider schedulersProvider, TimeProvider timeProvider) {
        return new MediaCallbacksUseCase(resultReceiver, schedulersProvider, timeProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public MediaCallbacksUseCase get() {
        return newInstance(this.mediaCallbackProvider.get(), this.schedulersProvider.get(), this.timeProvider.get());
    }
}
