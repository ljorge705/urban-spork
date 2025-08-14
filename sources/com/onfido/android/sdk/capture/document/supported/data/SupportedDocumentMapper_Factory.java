package com.onfido.android.sdk.capture.document.supported.data;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class SupportedDocumentMapper_Factory implements Factory<SupportedDocumentMapper> {

    private static final class InstanceHolder {
        private static final SupportedDocumentMapper_Factory INSTANCE = new SupportedDocumentMapper_Factory();

        private InstanceHolder() {
        }
    }

    public static SupportedDocumentMapper_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static SupportedDocumentMapper newInstance() {
        return new SupportedDocumentMapper();
    }

    @Override // com.onfido.javax.inject.Provider
    public SupportedDocumentMapper get() {
        return newInstance();
    }
}
