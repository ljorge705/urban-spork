package com.onfido.android.sdk.capture.internal.validation;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003JE\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\u0019\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006%"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/validation/DocumentProcessingFailureCounts;", "Landroid/os/Parcelable;", "glareFailureCount", "", "blurFailureCount", "edgeDetectionFailureCount", "barcodeFailureCount", "mrzValidationFailureCount", "faceOnDocumentDetectionFailureCount", "(IIIIII)V", "getBarcodeFailureCount", "()I", "getBlurFailureCount", "getEdgeDetectionFailureCount", "getFaceOnDocumentDetectionFailureCount", "getGlareFailureCount", "getMrzValidationFailureCount", "component1", "component2", "component3", "component4", "component5", "component6", Constants.COPY_TYPE, "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DocumentProcessingFailureCounts implements Parcelable {
    public static final Parcelable.Creator<DocumentProcessingFailureCounts> CREATOR = new Creator();
    private final int barcodeFailureCount;
    private final int blurFailureCount;
    private final int edgeDetectionFailureCount;
    private final int faceOnDocumentDetectionFailureCount;
    private final int glareFailureCount;
    private final int mrzValidationFailureCount;

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<DocumentProcessingFailureCounts> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DocumentProcessingFailureCounts createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new DocumentProcessingFailureCounts(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final DocumentProcessingFailureCounts[] newArray(int i) {
            return new DocumentProcessingFailureCounts[i];
        }
    }

    public DocumentProcessingFailureCounts() {
        this(0, 0, 0, 0, 0, 0, 63, null);
    }

    public static /* synthetic */ DocumentProcessingFailureCounts copy$default(DocumentProcessingFailureCounts documentProcessingFailureCounts, int i, int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i = documentProcessingFailureCounts.glareFailureCount;
        }
        if ((i7 & 2) != 0) {
            i2 = documentProcessingFailureCounts.blurFailureCount;
        }
        int i8 = i2;
        if ((i7 & 4) != 0) {
            i3 = documentProcessingFailureCounts.edgeDetectionFailureCount;
        }
        int i9 = i3;
        if ((i7 & 8) != 0) {
            i4 = documentProcessingFailureCounts.barcodeFailureCount;
        }
        int i10 = i4;
        if ((i7 & 16) != 0) {
            i5 = documentProcessingFailureCounts.mrzValidationFailureCount;
        }
        int i11 = i5;
        if ((i7 & 32) != 0) {
            i6 = documentProcessingFailureCounts.faceOnDocumentDetectionFailureCount;
        }
        return documentProcessingFailureCounts.copy(i, i8, i9, i10, i11, i6);
    }

    /* renamed from: component1, reason: from getter */
    public final int getGlareFailureCount() {
        return this.glareFailureCount;
    }

    /* renamed from: component2, reason: from getter */
    public final int getBlurFailureCount() {
        return this.blurFailureCount;
    }

    /* renamed from: component3, reason: from getter */
    public final int getEdgeDetectionFailureCount() {
        return this.edgeDetectionFailureCount;
    }

    /* renamed from: component4, reason: from getter */
    public final int getBarcodeFailureCount() {
        return this.barcodeFailureCount;
    }

    /* renamed from: component5, reason: from getter */
    public final int getMrzValidationFailureCount() {
        return this.mrzValidationFailureCount;
    }

    /* renamed from: component6, reason: from getter */
    public final int getFaceOnDocumentDetectionFailureCount() {
        return this.faceOnDocumentDetectionFailureCount;
    }

    public final DocumentProcessingFailureCounts copy(int glareFailureCount, int blurFailureCount, int edgeDetectionFailureCount, int barcodeFailureCount, int mrzValidationFailureCount, int faceOnDocumentDetectionFailureCount) {
        return new DocumentProcessingFailureCounts(glareFailureCount, blurFailureCount, edgeDetectionFailureCount, barcodeFailureCount, mrzValidationFailureCount, faceOnDocumentDetectionFailureCount);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentProcessingFailureCounts)) {
            return false;
        }
        DocumentProcessingFailureCounts documentProcessingFailureCounts = (DocumentProcessingFailureCounts) other;
        return this.glareFailureCount == documentProcessingFailureCounts.glareFailureCount && this.blurFailureCount == documentProcessingFailureCounts.blurFailureCount && this.edgeDetectionFailureCount == documentProcessingFailureCounts.edgeDetectionFailureCount && this.barcodeFailureCount == documentProcessingFailureCounts.barcodeFailureCount && this.mrzValidationFailureCount == documentProcessingFailureCounts.mrzValidationFailureCount && this.faceOnDocumentDetectionFailureCount == documentProcessingFailureCounts.faceOnDocumentDetectionFailureCount;
    }

    public final int getBarcodeFailureCount() {
        return this.barcodeFailureCount;
    }

    public final int getBlurFailureCount() {
        return this.blurFailureCount;
    }

    public final int getEdgeDetectionFailureCount() {
        return this.edgeDetectionFailureCount;
    }

    public final int getFaceOnDocumentDetectionFailureCount() {
        return this.faceOnDocumentDetectionFailureCount;
    }

    public final int getGlareFailureCount() {
        return this.glareFailureCount;
    }

    public final int getMrzValidationFailureCount() {
        return this.mrzValidationFailureCount;
    }

    public int hashCode() {
        return (((((((((Integer.hashCode(this.glareFailureCount) * 31) + Integer.hashCode(this.blurFailureCount)) * 31) + Integer.hashCode(this.edgeDetectionFailureCount)) * 31) + Integer.hashCode(this.barcodeFailureCount)) * 31) + Integer.hashCode(this.mrzValidationFailureCount)) * 31) + Integer.hashCode(this.faceOnDocumentDetectionFailureCount);
    }

    public String toString() {
        return "DocumentProcessingFailureCounts(glareFailureCount=" + this.glareFailureCount + ", blurFailureCount=" + this.blurFailureCount + ", edgeDetectionFailureCount=" + this.edgeDetectionFailureCount + ", barcodeFailureCount=" + this.barcodeFailureCount + ", mrzValidationFailureCount=" + this.mrzValidationFailureCount + ", faceOnDocumentDetectionFailureCount=" + this.faceOnDocumentDetectionFailureCount + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "out");
        parcel.writeInt(this.glareFailureCount);
        parcel.writeInt(this.blurFailureCount);
        parcel.writeInt(this.edgeDetectionFailureCount);
        parcel.writeInt(this.barcodeFailureCount);
        parcel.writeInt(this.mrzValidationFailureCount);
        parcel.writeInt(this.faceOnDocumentDetectionFailureCount);
    }

    public DocumentProcessingFailureCounts(int i, int i2, int i3, int i4, int i5, int i6) {
        this.glareFailureCount = i;
        this.blurFailureCount = i2;
        this.edgeDetectionFailureCount = i3;
        this.barcodeFailureCount = i4;
        this.mrzValidationFailureCount = i5;
        this.faceOnDocumentDetectionFailureCount = i6;
    }

    public /* synthetic */ DocumentProcessingFailureCounts(int i, int i2, int i3, int i4, int i5, int i6, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? 0 : i, (i7 & 2) != 0 ? 0 : i2, (i7 & 4) != 0 ? 0 : i3, (i7 & 8) != 0 ? 0 : i4, (i7 & 16) != 0 ? 0 : i5, (i7 & 32) != 0 ? 0 : i6);
    }
}
