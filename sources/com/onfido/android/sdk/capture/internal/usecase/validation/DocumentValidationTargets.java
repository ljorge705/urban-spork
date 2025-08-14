package com.onfido.android.sdk.capture.internal.usecase.validation;

import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import com.onfido.api.client.data.DocSide;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\u0016\u0010\u001a\u001a\u00020\nHÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u0011JI\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020%HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\t\u001a\u00020\nø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006&"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationTargets;", "", "documentType", "Lcom/onfido/android/sdk/capture/DocumentType;", "documentSide", "Lcom/onfido/api/client/data/DocSide;", "minimumCaptureArea", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "maximumCaptureArea", "holdDuration", "Lkotlin/time/Duration;", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/api/client/data/DocSide;Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getDocumentSide", "()Lcom/onfido/api/client/data/DocSide;", "getDocumentType", "()Lcom/onfido/android/sdk/capture/DocumentType;", "getHoldDuration-UwyO8pc", "()J", "J", "getMaximumCaptureArea", "()Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "getMinimumCaptureArea", "component1", "component2", "component3", "component4", "component5", "component5-UwyO8pc", Constants.COPY_TYPE, "copy-9VgGkz4", "(Lcom/onfido/android/sdk/capture/DocumentType;Lcom/onfido/api/client/data/DocSide;Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;J)Lcom/onfido/android/sdk/capture/internal/usecase/validation/DocumentValidationTargets;", "equals", "", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DocumentValidationTargets {
    private final DocSide documentSide;
    private final DocumentType documentType;
    private final long holdDuration;
    private final OnfidoRectF maximumCaptureArea;
    private final OnfidoRectF minimumCaptureArea;

    private DocumentValidationTargets(DocumentType documentType, DocSide docSide, OnfidoRectF minimumCaptureArea, OnfidoRectF maximumCaptureArea, long j) {
        Intrinsics.checkNotNullParameter(minimumCaptureArea, "minimumCaptureArea");
        Intrinsics.checkNotNullParameter(maximumCaptureArea, "maximumCaptureArea");
        this.documentType = documentType;
        this.documentSide = docSide;
        this.minimumCaptureArea = minimumCaptureArea;
        this.maximumCaptureArea = maximumCaptureArea;
        this.holdDuration = j;
    }

    /* renamed from: copy-9VgGkz4$default, reason: not valid java name */
    public static /* synthetic */ DocumentValidationTargets m5603copy9VgGkz4$default(DocumentValidationTargets documentValidationTargets, DocumentType documentType, DocSide docSide, OnfidoRectF onfidoRectF, OnfidoRectF onfidoRectF2, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            documentType = documentValidationTargets.documentType;
        }
        if ((i & 2) != 0) {
            docSide = documentValidationTargets.documentSide;
        }
        DocSide docSide2 = docSide;
        if ((i & 4) != 0) {
            onfidoRectF = documentValidationTargets.minimumCaptureArea;
        }
        OnfidoRectF onfidoRectF3 = onfidoRectF;
        if ((i & 8) != 0) {
            onfidoRectF2 = documentValidationTargets.maximumCaptureArea;
        }
        OnfidoRectF onfidoRectF4 = onfidoRectF2;
        if ((i & 16) != 0) {
            j = documentValidationTargets.holdDuration;
        }
        return documentValidationTargets.m5605copy9VgGkz4(documentType, docSide2, onfidoRectF3, onfidoRectF4, j);
    }

    /* renamed from: component1, reason: from getter */
    public final DocumentType getDocumentType() {
        return this.documentType;
    }

    /* renamed from: component2, reason: from getter */
    public final DocSide getDocumentSide() {
        return this.documentSide;
    }

    /* renamed from: component3, reason: from getter */
    public final OnfidoRectF getMinimumCaptureArea() {
        return this.minimumCaptureArea;
    }

    /* renamed from: component4, reason: from getter */
    public final OnfidoRectF getMaximumCaptureArea() {
        return this.maximumCaptureArea;
    }

    /* renamed from: component5-UwyO8pc, reason: not valid java name and from getter */
    public final long getHoldDuration() {
        return this.holdDuration;
    }

    /* renamed from: copy-9VgGkz4, reason: not valid java name */
    public final DocumentValidationTargets m5605copy9VgGkz4(DocumentType documentType, DocSide documentSide, OnfidoRectF minimumCaptureArea, OnfidoRectF maximumCaptureArea, long holdDuration) {
        Intrinsics.checkNotNullParameter(minimumCaptureArea, "minimumCaptureArea");
        Intrinsics.checkNotNullParameter(maximumCaptureArea, "maximumCaptureArea");
        return new DocumentValidationTargets(documentType, documentSide, minimumCaptureArea, maximumCaptureArea, holdDuration, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentValidationTargets)) {
            return false;
        }
        DocumentValidationTargets documentValidationTargets = (DocumentValidationTargets) other;
        return this.documentType == documentValidationTargets.documentType && this.documentSide == documentValidationTargets.documentSide && Intrinsics.areEqual(this.minimumCaptureArea, documentValidationTargets.minimumCaptureArea) && Intrinsics.areEqual(this.maximumCaptureArea, documentValidationTargets.maximumCaptureArea) && Duration.m7427equalsimpl0(this.holdDuration, documentValidationTargets.holdDuration);
    }

    public final DocSide getDocumentSide() {
        return this.documentSide;
    }

    public final DocumentType getDocumentType() {
        return this.documentType;
    }

    /* renamed from: getHoldDuration-UwyO8pc, reason: not valid java name */
    public final long m5606getHoldDurationUwyO8pc() {
        return this.holdDuration;
    }

    public final OnfidoRectF getMaximumCaptureArea() {
        return this.maximumCaptureArea;
    }

    public final OnfidoRectF getMinimumCaptureArea() {
        return this.minimumCaptureArea;
    }

    public int hashCode() {
        DocumentType documentType = this.documentType;
        int iHashCode = (documentType == null ? 0 : documentType.hashCode()) * 31;
        DocSide docSide = this.documentSide;
        return ((((((iHashCode + (docSide != null ? docSide.hashCode() : 0)) * 31) + this.minimumCaptureArea.hashCode()) * 31) + this.maximumCaptureArea.hashCode()) * 31) + Duration.m7450hashCodeimpl(this.holdDuration);
    }

    public String toString() {
        return "DocumentValidationTargets(documentType=" + this.documentType + ", documentSide=" + this.documentSide + ", minimumCaptureArea=" + this.minimumCaptureArea + ", maximumCaptureArea=" + this.maximumCaptureArea + ", holdDuration=" + ((Object) Duration.m7471toStringimpl(this.holdDuration)) + ')';
    }

    public /* synthetic */ DocumentValidationTargets(DocumentType documentType, DocSide docSide, OnfidoRectF onfidoRectF, OnfidoRectF onfidoRectF2, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : documentType, (i & 2) != 0 ? null : docSide, onfidoRectF, onfidoRectF2, j, null);
    }

    public /* synthetic */ DocumentValidationTargets(DocumentType documentType, DocSide docSide, OnfidoRectF onfidoRectF, OnfidoRectF onfidoRectF2, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(documentType, docSide, onfidoRectF, onfidoRectF2, j);
    }
}
