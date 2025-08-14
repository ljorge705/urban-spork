package com.onfido.android.sdk.capture.internal.service;

import android.content.Context;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class VibratorService_Factory implements Factory<VibratorService> {
    private final Provider<Context> contextProvider;

    public VibratorService_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static VibratorService_Factory create(Provider<Context> provider) {
        return new VibratorService_Factory(provider);
    }

    public static VibratorService newInstance(Context context) {
        return new VibratorService(context);
    }

    @Override // com.onfido.javax.inject.Provider
    public VibratorService get() {
        return newInstance(this.contextProvider.get());
    }
}
