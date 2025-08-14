package com.onfido.android.sdk.capture.ui.proofOfAddress.di;

import android.content.Context;
import android.content.res.Resources;
import com.onfido.dagger.internal.Factory;
import com.onfido.dagger.internal.Preconditions;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class PoaModule_Companion_ProvideResourcesFactory implements Factory<Resources> {
    private final Provider<Context> contextProvider;

    public PoaModule_Companion_ProvideResourcesFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static PoaModule_Companion_ProvideResourcesFactory create(Provider<Context> provider) {
        return new PoaModule_Companion_ProvideResourcesFactory(provider);
    }

    public static Resources provideResources(Context context) {
        return (Resources) Preconditions.checkNotNullFromProvides(PoaModule.INSTANCE.provideResources(context));
    }

    @Override // com.onfido.javax.inject.Provider
    public Resources get() {
        return provideResources(this.contextProvider.get());
    }
}
