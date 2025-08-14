package com.onfido.android.sdk.capture.internal.usecase;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.validation.DocumentProcessingResults;
import com.onfido.android.sdk.capture.ui.camera.DocumentDetectionFrame;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/DocumentProcessingUseCaseResult;", "", "documentProcessingResults", "Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;", "cameraFrame", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "(Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;)V", "getCameraFrame", "()Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "getDocumentProcessingResults", "()Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingResults;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DocumentProcessingUseCaseResult {
    private final DocumentDetectionFrame cameraFrame;
    private final DocumentProcessingResults documentProcessingResults;

    public DocumentProcessingUseCaseResult(DocumentProcessingResults documentProcessingResults, DocumentDetectionFrame cameraFrame) {
        Intrinsics.checkNotNullParameter(documentProcessingResults, "documentProcessingResults");
        Intrinsics.checkNotNullParameter(cameraFrame, "cameraFrame");
        this.documentProcessingResults = documentProcessingResults;
        this.cameraFrame = cameraFrame;
    }

    public static /* synthetic */ DocumentProcessingUseCaseResult copy$default(DocumentProcessingUseCaseResult documentProcessingUseCaseResult, DocumentProcessingResults documentProcessingResults, DocumentDetectionFrame documentDetectionFrame, int i, Object obj) {
        if ((i & 1) != 0) {
            documentProcessingResults = documentProcessingUseCaseResult.documentProcessingResults;
        }
        if ((i & 2) != 0) {
            documentDetectionFrame = documentProcessingUseCaseResult.cameraFrame;
        }
        return documentProcessingUseCaseResult.copy(documentProcessingResults, documentDetectionFrame);
    }

    /* renamed from: component1, reason: from getter */
    public final DocumentProcessingResults getDocumentProcessingResults() {
        return this.documentProcessingResults;
    }

    /* renamed from: component2, reason: from getter */
    public final DocumentDetectionFrame getCameraFrame() {
        return this.cameraFrame;
    }

    public final DocumentProcessingUseCaseResult copy(DocumentProcessingResults documentProcessingResults, DocumentDetectionFrame cameraFrame) {
        Intrinsics.checkNotNullParameter(documentProcessingResults, "documentProcessingResults");
        Intrinsics.checkNotNullParameter(cameraFrame, "cameraFrame");
        return new DocumentProcessingUseCaseResult(documentProcessingResults, cameraFrame);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentProcessingUseCaseResult)) {
            return false;
        }
        DocumentProcessingUseCaseResult documentProcessingUseCaseResult = (DocumentProcessingUseCaseResult) other;
        return Intrinsics.areEqual(this.documentProcessingResults, documentProcessingUseCaseResult.documentProcessingResults) && Intrinsics.areEqual(this.cameraFrame, documentProcessingUseCaseResult.cameraFrame);
    }

    public final DocumentDetectionFrame getCameraFrame() {
        return this.cameraFrame;
    }

    public final DocumentProcessingResults getDocumentProcessingResults() {
        return this.documentProcessingResults;
    }

    public int hashCode() {
        return (this.documentProcessingResults.hashCode() * 31) + this.cameraFrame.hashCode();
    }

    public String toString() {
        return "DocumentProcessingUseCaseResult(documentProcessingResults=" + this.documentProcessingResults + ", cameraFrame=" + this.cameraFrame + ')';
    }
}
