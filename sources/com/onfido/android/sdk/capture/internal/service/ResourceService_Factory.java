package com.onfido.android.sdk.capture.internal.service;

import android.content.Context;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class ResourceService_Factory implements Factory<ResourceService> {
    private final Provider<Context> contextProvider;

    public ResourceService_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static ResourceService_Factory create(Provider<Context> provider) {
        return new ResourceService_Factory(provider);
    }

    public static ResourceService newInstance(Context context) {
        return new ResourceService(context);
    }

    @Override // com.onfido.javax.inject.Provider
    public ResourceService get() {
        return newInstance(this.contextProvider.get());
    }
}
