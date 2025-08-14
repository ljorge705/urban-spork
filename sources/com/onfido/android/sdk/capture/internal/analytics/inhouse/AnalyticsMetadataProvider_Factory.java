package com.onfido.android.sdk.capture.internal.analytics.inhouse;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class AnalyticsMetadataProvider_Factory implements Factory<AnalyticsMetadataProvider> {

    private static final class InstanceHolder {
        private static final AnalyticsMetadataProvider_Factory INSTANCE = new AnalyticsMetadataProvider_Factory();

        private InstanceHolder() {
        }
    }

    public static AnalyticsMetadataProvider_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static AnalyticsMetadataProvider newInstance() {
        return new AnalyticsMetadataProvider();
    }

    @Override // com.onfido.javax.inject.Provider
    public AnalyticsMetadataProvider get() {
        return newInstance();
    }
}
