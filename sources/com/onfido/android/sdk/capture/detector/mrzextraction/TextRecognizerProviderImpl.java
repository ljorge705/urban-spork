package com.onfido.android.sdk.capture.detector.mrzextraction;

import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/mrzextraction/TextRecognizerProviderImpl;", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/TextRecognizerProvider;", "()V", "provide", "Lcom/google/mlkit/vision/text/TextRecognizer;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TextRecognizerProviderImpl implements TextRecognizerProvider {
    @Inject
    public TextRecognizerProviderImpl() {
    }

    @Override // com.onfido.android.sdk.capture.detector.mrzextraction.TextRecognizerProvider
    public TextRecognizer provide() {
        TextRecognizer client = TextRecognition.getClient(new TextRecognizerOptions.Builder().build());
        Intrinsics.checkNotNullExpressionValue(client, "getClient(...)");
        return client;
    }
}
