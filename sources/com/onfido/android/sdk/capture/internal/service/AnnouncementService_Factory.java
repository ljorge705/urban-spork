package com.onfido.android.sdk.capture.internal.service;

import android.content.Context;
import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class AnnouncementService_Factory implements Factory<AnnouncementService> {
    private final Provider<Context> contextProvider;

    public AnnouncementService_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static AnnouncementService_Factory create(Provider<Context> provider) {
        return new AnnouncementService_Factory(provider);
    }

    public static AnnouncementService newInstance(Context context) {
        return new AnnouncementService(context);
    }

    @Override // com.onfido.javax.inject.Provider
    public AnnouncementService get() {
        return newInstance(this.contextProvider.get());
    }
}
