package com.onfido.android.sdk.capture.internal.usecase;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DocumentPositionUseCase_Factory implements Factory<DocumentPositionUseCase> {

    private static final class InstanceHolder {
        private static final DocumentPositionUseCase_Factory INSTANCE = new DocumentPositionUseCase_Factory();

        private InstanceHolder() {
        }
    }

    public static DocumentPositionUseCase_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DocumentPositionUseCase newInstance() {
        return new DocumentPositionUseCase();
    }

    @Override // com.onfido.javax.inject.Provider
    public DocumentPositionUseCase get() {
        return newInstance();
    }
}
