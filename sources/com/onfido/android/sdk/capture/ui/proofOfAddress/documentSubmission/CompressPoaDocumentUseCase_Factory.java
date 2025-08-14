package com.onfido.android.sdk.capture.ui.proofOfAddress.documentSubmission;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class CompressPoaDocumentUseCase_Factory implements Factory<CompressPoaDocumentUseCase> {

    private static final class InstanceHolder {
        private static final CompressPoaDocumentUseCase_Factory INSTANCE = new CompressPoaDocumentUseCase_Factory();

        private InstanceHolder() {
        }
    }

    public static CompressPoaDocumentUseCase_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static CompressPoaDocumentUseCase newInstance() {
        return new CompressPoaDocumentUseCase();
    }

    @Override // com.onfido.javax.inject.Provider
    public CompressPoaDocumentUseCase get() {
        return newInstance();
    }
}
