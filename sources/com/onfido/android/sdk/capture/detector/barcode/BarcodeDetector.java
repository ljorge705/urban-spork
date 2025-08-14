package com.onfido.android.sdk.capture.detector.barcode;

import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import com.onfido.android.sdk.capture.validation.device.BarcodeValidationResult;
import io.reactivex.rxjava3.core.Single;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b`\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/detector/barcode/BarcodeDetector;", "", "detect", "Lio/reactivex/rxjava3/core/Single;", "Lcom/onfido/android/sdk/capture/validation/device/BarcodeValidationResult;", "documentFrame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "isRealtimeAnalysis", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface BarcodeDetector {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Single detect$default(BarcodeDetector barcodeDetector, DocumentDetectionFrame documentDetectionFrame, boolean z, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: detect");
            }
            if ((i & 2) != 0) {
                z = true;
            }
            return barcodeDetector.detect(documentDetectionFrame, z);
        }
    }

    Single<BarcodeValidationResult> detect(DocumentDetectionFrame documentFrame, boolean isRealtimeAnalysis);
}
