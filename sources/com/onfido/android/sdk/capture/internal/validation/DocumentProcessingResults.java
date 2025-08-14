package com.onfido.android.sdk.capture.internal.validation;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.edge_detector.EdgeDetectionResults;
import com.onfido.android.sdk.capture.utils.OnfidoExtensionsKt;
import com.onfido.android.sdk.capture.validation.OnDeviceValidationType;
import com.onfido.android.sdk.capture.validation.device.BarcodeValidationResult;
import com.onfido.android.sdk.capture.validation.device.BlurValidationResult;
import com.onfido.android.sdk.capture.validation.device.EdgeDetectionValidationResult;
import com.onfido.android.sdk.capture.validation.device.FaceOnDocumentValidationResult;
import com.onfido.android.sdk.capture.validation.device.GlareValidationResult;
import com.onfido.android.sdk.capture.validation.device.MRZValidationResult;
import com.onfido.android.sdk.capture.validation.device.OnDeviceValidationResult;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\b\u0018\u0000 *2\u00020\u0001:\u0001*BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003J\t\u0010\u001f\u001a\u00020\u000bHÆ\u0003J\t\u0010 \u001a\u00020\rHÆ\u0003JE\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010%\u001a\u00020&H\u0016J\u0006\u0010'\u001a\u00020#J\t\u0010(\u001a\u00020)HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006+"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;", "", "glareResults", "Lcom/onfido/android/sdk/capture/validation/device/GlareValidationResult;", "blurResults", "Lcom/onfido/android/sdk/capture/validation/device/BlurValidationResult;", "edgeDetectionResults", "Lcom/onfido/android/sdk/capture/validation/device/EdgeDetectionValidationResult;", "barcodeResults", "Lcom/onfido/android/sdk/capture/validation/device/BarcodeValidationResult;", "mrzValidationResult", "Lcom/onfido/android/sdk/capture/validation/device/MRZValidationResult;", "faceOnDocumentDetectionResult", "Lcom/onfido/android/sdk/capture/validation/device/FaceOnDocumentValidationResult;", "(Lcom/onfido/android/sdk/capture/validation/device/GlareValidationResult;Lcom/onfido/android/sdk/capture/validation/device/BlurValidationResult;Lcom/onfido/android/sdk/capture/validation/device/EdgeDetectionValidationResult;Lcom/onfido/android/sdk/capture/validation/device/BarcodeValidationResult;Lcom/onfido/android/sdk/capture/validation/device/MRZValidationResult;Lcom/onfido/android/sdk/capture/validation/device/FaceOnDocumentValidationResult;)V", "getBarcodeResults", "()Lcom/onfido/android/sdk/capture/validation/device/BarcodeValidationResult;", "getBlurResults", "()Lcom/onfido/android/sdk/capture/validation/device/BlurValidationResult;", "getEdgeDetectionResults", "()Lcom/onfido/android/sdk/capture/validation/device/EdgeDetectionValidationResult;", "getFaceOnDocumentDetectionResult", "()Lcom/onfido/android/sdk/capture/validation/device/FaceOnDocumentValidationResult;", "getGlareResults", "()Lcom/onfido/android/sdk/capture/validation/device/GlareValidationResult;", "getMrzValidationResult", "()Lcom/onfido/android/sdk/capture/validation/device/MRZValidationResult;", "component1", "component2", "component3", "component4", "component5", "component6", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "isValidDocumentImage", "toString", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DocumentProcessingResults {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BarcodeValidationResult barcodeResults;
    private final BlurValidationResult blurResults;
    private final EdgeDetectionValidationResult edgeDetectionResults;
    private final FaceOnDocumentValidationResult faceOnDocumentDetectionResult;
    private final GlareValidationResult glareResults;
    private final MRZValidationResult mrzValidationResult;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0007¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults$Companion;", "", "()V", "mapFromValidationTypeToResult", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;", "validationTypeToResultMap", "", "Lcom/onfido/android/sdk/capture/validation/OnDeviceValidationType;", "Lcom/onfido/android/sdk/capture/validation/device/OnDeviceValidationResult;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        public final DocumentProcessingResults mapFromValidationTypeToResult(Map<OnDeviceValidationType, ? extends OnDeviceValidationResult> validationTypeToResultMap) {
            Intrinsics.checkNotNullParameter(validationTypeToResultMap, "validationTypeToResultMap");
            OnDeviceValidationResult onDeviceValidationResult = validationTypeToResultMap.get(OnDeviceValidationType.GLARE_DETECTION);
            EdgeDetectionResults edgeDetectionResults = null;
            Object[] objArr = 0;
            Object[] objArr2 = 0;
            Object[] objArr3 = 0;
            GlareValidationResult glareValidationResult = onDeviceValidationResult instanceof GlareValidationResult ? (GlareValidationResult) onDeviceValidationResult : null;
            int i = 3;
            boolean z = false;
            if (glareValidationResult == null) {
                glareValidationResult = new GlareValidationResult(false, false, 3, null);
            }
            GlareValidationResult glareValidationResult2 = glareValidationResult;
            OnDeviceValidationResult onDeviceValidationResult2 = validationTypeToResultMap.get(OnDeviceValidationType.BLUR_DETECTION);
            BlurValidationResult blurValidationResult = onDeviceValidationResult2 instanceof BlurValidationResult ? (BlurValidationResult) onDeviceValidationResult2 : null;
            if (blurValidationResult == null) {
                blurValidationResult = new BlurValidationResult(false, false, 3, null);
            }
            BlurValidationResult blurValidationResult2 = blurValidationResult;
            OnDeviceValidationResult onDeviceValidationResult3 = validationTypeToResultMap.get(OnDeviceValidationType.EDGES_DETECTION);
            EdgeDetectionValidationResult edgeDetectionValidationResult = onDeviceValidationResult3 instanceof EdgeDetectionValidationResult ? (EdgeDetectionValidationResult) onDeviceValidationResult3 : null;
            if (edgeDetectionValidationResult == null) {
                edgeDetectionValidationResult = new EdgeDetectionValidationResult(edgeDetectionResults, z, i, objArr3 == true ? 1 : 0);
            }
            EdgeDetectionValidationResult edgeDetectionValidationResult2 = edgeDetectionValidationResult;
            OnDeviceValidationResult onDeviceValidationResult4 = validationTypeToResultMap.get(OnDeviceValidationType.PDF_417_BARCODE_DETECTION);
            BarcodeValidationResult barcodeValidationResult = onDeviceValidationResult4 instanceof BarcodeValidationResult ? (BarcodeValidationResult) onDeviceValidationResult4 : null;
            if (barcodeValidationResult == null) {
                barcodeValidationResult = new BarcodeValidationResult(objArr2 == true ? 1 : 0, z, i, objArr == true ? 1 : 0);
            }
            BarcodeValidationResult barcodeValidationResult2 = barcodeValidationResult;
            OnDeviceValidationResult onDeviceValidationResult5 = validationTypeToResultMap.get(OnDeviceValidationType.FACE_ON_DOCUMENT_DETECTION);
            FaceOnDocumentValidationResult faceOnDocumentValidationResult = onDeviceValidationResult5 instanceof FaceOnDocumentValidationResult ? (FaceOnDocumentValidationResult) onDeviceValidationResult5 : null;
            if (faceOnDocumentValidationResult == null) {
                faceOnDocumentValidationResult = new FaceOnDocumentValidationResult(null, false, 3, null);
            }
            FaceOnDocumentValidationResult faceOnDocumentValidationResult2 = faceOnDocumentValidationResult;
            OnDeviceValidationResult onDeviceValidationResult6 = validationTypeToResultMap.get(OnDeviceValidationType.MRZ_DETECTION);
            MRZValidationResult mRZValidationResult = onDeviceValidationResult6 instanceof MRZValidationResult ? (MRZValidationResult) onDeviceValidationResult6 : null;
            if (mRZValidationResult == null) {
                mRZValidationResult = new MRZValidationResult(false, false, 3, null);
            }
            return new DocumentProcessingResults(glareValidationResult2, blurValidationResult2, edgeDetectionValidationResult2, barcodeValidationResult2, mRZValidationResult, faceOnDocumentValidationResult2);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public DocumentProcessingResults() {
        this(null, null, null, null, null, null, 63, null);
    }

    public static /* synthetic */ DocumentProcessingResults copy$default(DocumentProcessingResults documentProcessingResults, GlareValidationResult glareValidationResult, BlurValidationResult blurValidationResult, EdgeDetectionValidationResult edgeDetectionValidationResult, BarcodeValidationResult barcodeValidationResult, MRZValidationResult mRZValidationResult, FaceOnDocumentValidationResult faceOnDocumentValidationResult, int i, Object obj) {
        if ((i & 1) != 0) {
            glareValidationResult = documentProcessingResults.glareResults;
        }
        if ((i & 2) != 0) {
            blurValidationResult = documentProcessingResults.blurResults;
        }
        BlurValidationResult blurValidationResult2 = blurValidationResult;
        if ((i & 4) != 0) {
            edgeDetectionValidationResult = documentProcessingResults.edgeDetectionResults;
        }
        EdgeDetectionValidationResult edgeDetectionValidationResult2 = edgeDetectionValidationResult;
        if ((i & 8) != 0) {
            barcodeValidationResult = documentProcessingResults.barcodeResults;
        }
        BarcodeValidationResult barcodeValidationResult2 = barcodeValidationResult;
        if ((i & 16) != 0) {
            mRZValidationResult = documentProcessingResults.mrzValidationResult;
        }
        MRZValidationResult mRZValidationResult2 = mRZValidationResult;
        if ((i & 32) != 0) {
            faceOnDocumentValidationResult = documentProcessingResults.faceOnDocumentDetectionResult;
        }
        return documentProcessingResults.copy(glareValidationResult, blurValidationResult2, edgeDetectionValidationResult2, barcodeValidationResult2, mRZValidationResult2, faceOnDocumentValidationResult);
    }

    @JvmStatic
    public static final DocumentProcessingResults mapFromValidationTypeToResult(Map<OnDeviceValidationType, ? extends OnDeviceValidationResult> map) {
        return INSTANCE.mapFromValidationTypeToResult(map);
    }

    /* renamed from: component1, reason: from getter */
    public final GlareValidationResult getGlareResults() {
        return this.glareResults;
    }

    /* renamed from: component2, reason: from getter */
    public final BlurValidationResult getBlurResults() {
        return this.blurResults;
    }

    /* renamed from: component3, reason: from getter */
    public final EdgeDetectionValidationResult getEdgeDetectionResults() {
        return this.edgeDetectionResults;
    }

    /* renamed from: component4, reason: from getter */
    public final BarcodeValidationResult getBarcodeResults() {
        return this.barcodeResults;
    }

    /* renamed from: component5, reason: from getter */
    public final MRZValidationResult getMrzValidationResult() {
        return this.mrzValidationResult;
    }

    /* renamed from: component6, reason: from getter */
    public final FaceOnDocumentValidationResult getFaceOnDocumentDetectionResult() {
        return this.faceOnDocumentDetectionResult;
    }

    public final DocumentProcessingResults copy(GlareValidationResult glareResults, BlurValidationResult blurResults, EdgeDetectionValidationResult edgeDetectionResults, BarcodeValidationResult barcodeResults, MRZValidationResult mrzValidationResult, FaceOnDocumentValidationResult faceOnDocumentDetectionResult) {
        Intrinsics.checkNotNullParameter(glareResults, "glareResults");
        Intrinsics.checkNotNullParameter(blurResults, "blurResults");
        Intrinsics.checkNotNullParameter(edgeDetectionResults, "edgeDetectionResults");
        Intrinsics.checkNotNullParameter(barcodeResults, "barcodeResults");
        Intrinsics.checkNotNullParameter(mrzValidationResult, "mrzValidationResult");
        Intrinsics.checkNotNullParameter(faceOnDocumentDetectionResult, "faceOnDocumentDetectionResult");
        return new DocumentProcessingResults(glareResults, blurResults, edgeDetectionResults, barcodeResults, mrzValidationResult, faceOnDocumentDetectionResult);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentProcessingResults)) {
            return false;
        }
        DocumentProcessingResults documentProcessingResults = (DocumentProcessingResults) other;
        return Intrinsics.areEqual(this.glareResults, documentProcessingResults.glareResults) && Intrinsics.areEqual(this.blurResults, documentProcessingResults.blurResults) && Intrinsics.areEqual(this.edgeDetectionResults, documentProcessingResults.edgeDetectionResults) && Intrinsics.areEqual(this.barcodeResults, documentProcessingResults.barcodeResults) && Intrinsics.areEqual(this.mrzValidationResult, documentProcessingResults.mrzValidationResult) && Intrinsics.areEqual(this.faceOnDocumentDetectionResult, documentProcessingResults.faceOnDocumentDetectionResult);
    }

    public final BarcodeValidationResult getBarcodeResults() {
        return this.barcodeResults;
    }

    public final BlurValidationResult getBlurResults() {
        return this.blurResults;
    }

    public final EdgeDetectionValidationResult getEdgeDetectionResults() {
        return this.edgeDetectionResults;
    }

    public final FaceOnDocumentValidationResult getFaceOnDocumentDetectionResult() {
        return this.faceOnDocumentDetectionResult;
    }

    public final GlareValidationResult getGlareResults() {
        return this.glareResults;
    }

    public final MRZValidationResult getMrzValidationResult() {
        return this.mrzValidationResult;
    }

    public int hashCode() {
        return OnfidoExtensionsKt.hashCode(this.glareResults, this.blurResults, this.edgeDetectionResults, this.barcodeResults, this.mrzValidationResult, this.faceOnDocumentDetectionResult);
    }

    public final boolean isValidDocumentImage() {
        return this.glareResults.isValid() && this.blurResults.isValid() && this.edgeDetectionResults.isValid() && this.barcodeResults.isValid() && this.mrzValidationResult.isValid() && this.faceOnDocumentDetectionResult.isValid();
    }

    public String toString() {
        return "DocumentProcessingResults(glareResults=" + this.glareResults + ", blurResults=" + this.blurResults + ", edgeDetectionResults=" + this.edgeDetectionResults + ", barcodeResults=" + this.barcodeResults + ", mrzValidationResult=" + this.mrzValidationResult + ", faceOnDocumentDetectionResult=" + this.faceOnDocumentDetectionResult + ')';
    }

    public DocumentProcessingResults(GlareValidationResult glareResults, BlurValidationResult blurResults, EdgeDetectionValidationResult edgeDetectionResults, BarcodeValidationResult barcodeResults, MRZValidationResult mrzValidationResult, FaceOnDocumentValidationResult faceOnDocumentDetectionResult) {
        Intrinsics.checkNotNullParameter(glareResults, "glareResults");
        Intrinsics.checkNotNullParameter(blurResults, "blurResults");
        Intrinsics.checkNotNullParameter(edgeDetectionResults, "edgeDetectionResults");
        Intrinsics.checkNotNullParameter(barcodeResults, "barcodeResults");
        Intrinsics.checkNotNullParameter(mrzValidationResult, "mrzValidationResult");
        Intrinsics.checkNotNullParameter(faceOnDocumentDetectionResult, "faceOnDocumentDetectionResult");
        this.glareResults = glareResults;
        this.blurResults = blurResults;
        this.edgeDetectionResults = edgeDetectionResults;
        this.barcodeResults = barcodeResults;
        this.mrzValidationResult = mrzValidationResult;
        this.faceOnDocumentDetectionResult = faceOnDocumentDetectionResult;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ DocumentProcessingResults(GlareValidationResult glareValidationResult, BlurValidationResult blurValidationResult, EdgeDetectionValidationResult edgeDetectionValidationResult, BarcodeValidationResult barcodeValidationResult, MRZValidationResult mRZValidationResult, FaceOnDocumentValidationResult faceOnDocumentValidationResult, int i, DefaultConstructorMarker defaultConstructorMarker) {
        int i2 = 3;
        boolean z = false;
        this((i & 1) != 0 ? new GlareValidationResult(false, false, 3, null) : glareValidationResult, (i & 2) != 0 ? new BlurValidationResult(false, false, 3, null) : blurValidationResult, (i & 4) != 0 ? new EdgeDetectionValidationResult(null, z, i2, 0 == true ? 1 : 0) : edgeDetectionValidationResult, (i & 8) != 0 ? new BarcodeValidationResult(0 == true ? 1 : 0, z, i2, 0 == true ? 1 : 0) : barcodeValidationResult, (i & 16) != 0 ? new MRZValidationResult(false, false, 3, null) : mRZValidationResult, (i & 32) != 0 ? new FaceOnDocumentValidationResult(null, false, 3, null) : faceOnDocumentValidationResult);
    }
}
