package com.onfido.android.sdk.capture.detector.mrzextraction;

import com.onfido.dagger.internal.Factory;
import com.onfido.javax.inject.Provider;

/* loaded from: classes2.dex */
public final class MRZDocumentExtractorGoogle_Factory implements Factory<MRZDocumentExtractorGoogle> {
    private final Provider<TextRecognizerProvider> textRecognizerProvider;

    public MRZDocumentExtractorGoogle_Factory(Provider<TextRecognizerProvider> provider) {
        this.textRecognizerProvider = provider;
    }

    public static MRZDocumentExtractorGoogle_Factory create(Provider<TextRecognizerProvider> provider) {
        return new MRZDocumentExtractorGoogle_Factory(provider);
    }

    public static MRZDocumentExtractorGoogle newInstance(TextRecognizerProvider textRecognizerProvider) {
        return new MRZDocumentExtractorGoogle(textRecognizerProvider);
    }

    @Override // com.onfido.javax.inject.Provider
    public MRZDocumentExtractorGoogle get() {
        return newInstance(this.textRecognizerProvider.get());
    }
}
