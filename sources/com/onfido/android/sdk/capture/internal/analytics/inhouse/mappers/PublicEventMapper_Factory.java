package com.onfido.android.sdk.capture.internal.analytics.inhouse.mappers;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class PublicEventMapper_Factory implements Factory<PublicEventMapper> {

    private static final class InstanceHolder {
        private static final PublicEventMapper_Factory INSTANCE = new PublicEventMapper_Factory();

        private InstanceHolder() {
        }
    }

    public static PublicEventMapper_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static PublicEventMapper newInstance() {
        return new PublicEventMapper();
    }

    @Override // com.onfido.javax.inject.Provider
    public PublicEventMapper get() {
        return newInstance();
    }
}
