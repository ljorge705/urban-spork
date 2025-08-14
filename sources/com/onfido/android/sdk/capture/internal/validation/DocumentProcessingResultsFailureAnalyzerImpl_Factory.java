package com.onfido.android.sdk.capture.internal.validation;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class DocumentProcessingResultsFailureAnalyzerImpl_Factory implements Factory<DocumentProcessingResultsFailureAnalyzerImpl> {

    private static final class InstanceHolder {
        private static final DocumentProcessingResultsFailureAnalyzerImpl_Factory INSTANCE = new DocumentProcessingResultsFailureAnalyzerImpl_Factory();

        private InstanceHolder() {
        }
    }

    public static DocumentProcessingResultsFailureAnalyzerImpl_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static DocumentProcessingResultsFailureAnalyzerImpl newInstance() {
        return new DocumentProcessingResultsFailureAnalyzerImpl();
    }

    @Override // com.onfido.javax.inject.Provider
    public DocumentProcessingResultsFailureAnalyzerImpl get() {
        return newInstance();
    }
}
