package com.onfido.android.sdk.capture.ui.camera.liveness.capture;

import android.content.Context;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class LivenessInteractor_Factory implements Factory<LivenessInteractor> {
    private final Provider<Context> contextProvider;
    private final Provider<TimeProvider> timeProvider;

    public LivenessInteractor_Factory(Provider<Context> provider, Provider<TimeProvider> provider2) {
        this.contextProvider = provider;
        this.timeProvider = provider2;
    }

    public static LivenessInteractor_Factory create(Provider<Context> provider, Provider<TimeProvider> provider2) {
        return new LivenessInteractor_Factory(provider, provider2);
    }

    public static LivenessInteractor newInstance(Context context, TimeProvider timeProvider) {
        return new LivenessInteractor(context, timeProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public LivenessInteractor get() {
        return newInstance(this.contextProvider.get(), this.timeProvider.get());
    }
}
