package com.onfido.android.sdk.capture.document.supported.data.remote.datasource;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class SupportedDocumentsLocalDataSource_Factory implements Factory<SupportedDocumentsLocalDataSource> {

    private static final class InstanceHolder {
        private static final SupportedDocumentsLocalDataSource_Factory INSTANCE = new SupportedDocumentsLocalDataSource_Factory();

        private InstanceHolder() {
        }
    }

    public static SupportedDocumentsLocalDataSource_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static SupportedDocumentsLocalDataSource newInstance() {
        return new SupportedDocumentsLocalDataSource();
    }

    @Override // com.onfido.javax.inject.Provider
    public SupportedDocumentsLocalDataSource get() {
        return newInstance();
    }
}
