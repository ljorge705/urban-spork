package com.onfido.android.sdk.capture.internal.validation;

import com.onfido.javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResultsFailureAnalyzerImpl;", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResultsFailureAnalyzer;", "()V", "documentProcessingFailureCounts", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingFailureCounts;", "getDocumentProcessingFailureCounts", "()Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingFailureCounts;", "processingFailureCounts", "analyzeDocumentProcessingResults", "", "results", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;", "updateFailureCounts", "failureCounts", "updateProcessingFailureCounts", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentProcessingResultsFailureAnalyzerImpl implements DocumentProcessingResultsFailureAnalyzer {
    private DocumentProcessingFailureCounts processingFailureCounts = new DocumentProcessingFailureCounts(0, 0, 0, 0, 0, 0, 63, null);

    @Inject
    public DocumentProcessingResultsFailureAnalyzerImpl() {
    }

    private final void updateProcessingFailureCounts(DocumentProcessingResults results) {
        int glareFailureCount = this.processingFailureCounts.getGlareFailureCount();
        int blurFailureCount = this.processingFailureCounts.getBlurFailureCount();
        int edgeDetectionFailureCount = this.processingFailureCounts.getEdgeDetectionFailureCount();
        int barcodeFailureCount = this.processingFailureCounts.getBarcodeFailureCount();
        int mrzValidationFailureCount = this.processingFailureCounts.getMrzValidationFailureCount();
        int faceOnDocumentDetectionFailureCount = this.processingFailureCounts.getFaceOnDocumentDetectionFailureCount();
        if (!results.getGlareResults().isValid()) {
            glareFailureCount++;
        }
        int i = glareFailureCount;
        if (!results.getBlurResults().isValid()) {
            blurFailureCount++;
        }
        int i2 = blurFailureCount;
        if (!results.getEdgeDetectionResults().isValid()) {
            edgeDetectionFailureCount++;
        }
        int i3 = edgeDetectionFailureCount;
        if (!results.getBarcodeResults().isValid()) {
            barcodeFailureCount++;
        }
        int i4 = barcodeFailureCount;
        if (!results.getMrzValidationResult().isValid()) {
            mrzValidationFailureCount++;
        }
        int i5 = mrzValidationFailureCount;
        if (!results.getFaceOnDocumentDetectionResult().isValid()) {
            faceOnDocumentDetectionFailureCount++;
        }
        this.processingFailureCounts = new DocumentProcessingFailureCounts(i, i2, i3, i4, i5, faceOnDocumentDetectionFailureCount);
    }

    @Override // com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResultsAnalyzer
    public void analyzeDocumentProcessingResults(DocumentProcessingResults results) {
        Intrinsics.checkNotNullParameter(results, "results");
        if (results.isValidDocumentImage()) {
            return;
        }
        updateProcessingFailureCounts(results);
    }

    @Override // com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResultsFailureAnalyzer
    /* renamed from: getDocumentProcessingFailureCounts, reason: from getter */
    public DocumentProcessingFailureCounts getProcessingFailureCounts() {
        return this.processingFailureCounts;
    }

    @Override // com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResultsFailureAnalyzer
    public void updateFailureCounts(DocumentProcessingFailureCounts failureCounts) {
        Intrinsics.checkNotNullParameter(failureCounts, "failureCounts");
        this.processingFailureCounts = failureCounts;
    }
}
