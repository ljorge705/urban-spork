package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class SdkModule_ProvideTelephonyManagerFactory implements Factory<TelephonyManager> {
    private final Provider<Context> contextProvider;
    private final SdkModule module;

    public SdkModule_ProvideTelephonyManagerFactory(SdkModule sdkModule, Provider<Context> provider) {
        this.module = sdkModule;
        this.contextProvider = provider;
    }

    public static SdkModule_ProvideTelephonyManagerFactory create(SdkModule sdkModule, Provider<Context> provider) {
        return new SdkModule_ProvideTelephonyManagerFactory(sdkModule, provider);
    }

    public static TelephonyManager provideTelephonyManager(SdkModule sdkModule, Context context) {
        return (TelephonyManager) Preconditions.checkNotNullFromProvides(sdkModule.provideTelephonyManager(context));
    }

    @Override // com.onfido.javax.inject.Provider
    public TelephonyManager get() {
        return provideTelephonyManager(this.module, this.contextProvider.get());
    }
}
