package com.onfido.android.sdk.capture.detector.mrzextraction;

import com.google.mlkit.vision.text.TextRecognizer;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bà\u0080\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/mrzextraction/TextRecognizerProvider;", "", "provide", "Lcom/google/mlkit/vision/text/TextRecognizer;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface TextRecognizerProvider {
    TextRecognizer provide();
}
