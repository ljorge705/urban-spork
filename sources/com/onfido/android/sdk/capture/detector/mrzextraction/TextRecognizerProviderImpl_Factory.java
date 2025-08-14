package com.onfido.android.sdk.capture.detector.mrzextraction;

import com.onfido.dagger.internal.Factory;

/* loaded from: classes2.dex */
public final class TextRecognizerProviderImpl_Factory implements Factory<TextRecognizerProviderImpl> {

    private static final class InstanceHolder {
        private static final TextRecognizerProviderImpl_Factory INSTANCE = new TextRecognizerProviderImpl_Factory();

        private InstanceHolder() {
        }
    }

    public static TextRecognizerProviderImpl_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static TextRecognizerProviderImpl newInstance() {
        return new TextRecognizerProviderImpl();
    }

    @Override // com.onfido.javax.inject.Provider
    public TextRecognizerProviderImpl get() {
        return newInstance();
    }
}
