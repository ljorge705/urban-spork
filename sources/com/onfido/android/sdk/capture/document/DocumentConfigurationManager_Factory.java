package com.onfido.android.sdk.capture.document;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DocumentConfigurationManager_Factory implements Factory<DocumentConfigurationManager> {

    private static final class InstanceHolder {
        private static final DocumentConfigurationManager_Factory INSTANCE = new DocumentConfigurationManager_Factory();

        private InstanceHolder() {
        }
    }

    public static DocumentConfigurationManager_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DocumentConfigurationManager newInstance() {
        return new DocumentConfigurationManager();
    }

    @Override // com.onfido.javax.inject.Provider
    public DocumentConfigurationManager get() {
        return newInstance();
    }
}
