package com.onfido.android.sdk.capture.detector.mrzextraction;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class MRZDocumentExtractorEmpty_Factory implements Factory<MRZDocumentExtractorEmpty> {

    private static final class InstanceHolder {
        private static final MRZDocumentExtractorEmpty_Factory INSTANCE = new MRZDocumentExtractorEmpty_Factory();

        private InstanceHolder() {
        }
    }

    public static MRZDocumentExtractorEmpty_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static MRZDocumentExtractorEmpty newInstance() {
        return new MRZDocumentExtractorEmpty();
    }

    @Override // com.onfido.javax.inject.Provider
    public MRZDocumentExtractorEmpty get() {
        return newInstance();
    }
}
