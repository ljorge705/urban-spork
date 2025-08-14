package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.viewmodel.util;

import android.content.Context;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MicAvailabilityHelper_Factory implements Factory<MicAvailabilityHelper> {
    private final Provider<Context> contextProvider;

    public MicAvailabilityHelper_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static MicAvailabilityHelper_Factory create(Provider<Context> provider) {
        return new MicAvailabilityHelper_Factory(provider);
    }

    public static MicAvailabilityHelper newInstance(Context context) {
        return new MicAvailabilityHelper(context);
    }

    @Override // com.onfido.javax.inject.Provider
    public MicAvailabilityHelper get() {
        return newInstance(this.contextProvider.get());
    }
}
