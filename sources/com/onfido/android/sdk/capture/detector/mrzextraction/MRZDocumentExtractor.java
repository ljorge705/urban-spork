package com.onfido.android.sdk.capture.detector.mrzextraction;

import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bà\u0080\u0001\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocumentExtractor;", "", "detect", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZResult;", "documentDetectionFrame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "(Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface MRZDocumentExtractor {
    Object detect(DocumentDetectionFrame documentDetectionFrame, Continuation<? super MRZResult> continuation);
}
