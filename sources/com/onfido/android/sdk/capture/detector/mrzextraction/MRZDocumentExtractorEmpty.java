package com.onfido.android.sdk.capture.detector.mrzextraction;

import com.onfido.android.sdk.capture.detector.mrzextraction.MRZResult;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0096@¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocumentExtractorEmpty;", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZDocumentExtractor;", "()V", "detect", "Lcom/onfido/android/sdk/capture/detector/mrzextraction/MRZResult;", "documentDetectionFrame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "(Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MRZDocumentExtractorEmpty implements MRZDocumentExtractor {
    @Inject
    public MRZDocumentExtractorEmpty() {
    }

    @Override // com.onfido.android.sdk.capture.detector.mrzextraction.MRZDocumentExtractor
    public Object detect(DocumentDetectionFrame documentDetectionFrame, Continuation<? super MRZResult> continuation) {
        return MRZResult.Skipped.INSTANCE;
    }
}
