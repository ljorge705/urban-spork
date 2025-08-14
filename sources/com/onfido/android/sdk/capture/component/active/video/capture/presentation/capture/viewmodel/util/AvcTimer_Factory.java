package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util;

import com.onfido.android.sdk.capture.internal.util.SchedulersProvider;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class AvcTimer_Factory implements Factory<AvcTimer> {
    private final Provider<SchedulersProvider> schedulersProvider;

    public AvcTimer_Factory(Provider<SchedulersProvider> provider) {
        this.schedulersProvider = provider;
    }

    public static AvcTimer_Factory create(Provider<SchedulersProvider> provider) {
        return new AvcTimer_Factory(provider);
    }

    public static AvcTimer newInstance(SchedulersProvider schedulersProvider) {
        return new AvcTimer(schedulersProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public AvcTimer get() {
        return newInstance(this.schedulersProvider.get());
    }
}
