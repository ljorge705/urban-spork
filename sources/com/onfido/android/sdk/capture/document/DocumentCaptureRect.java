package com.onfido.android.sdk.capture.document;

import android.graphics.RectF;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\b\u0018\u0000  2\u00020\u0001:\u0001 B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\r\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001dJ\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\tR\u0011\u0010\u000f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\t¨\u0006!"}, d2 = {"Lcom/onfido/android/sdk/capture/document/DocumentCaptureRect;", "", "left", "", "top", ViewProps.RIGHT, ViewProps.BOTTOM, "(FFFF)V", "getBottom", "()F", "height", "getHeight", "getLeft", "getRight", "getTop", "width", "getWidth", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toRectF", "Landroid/graphics/RectF;", "toRectF$onfido_capture_sdk_core_release", "toString", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class DocumentCaptureRect {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final float bottom;
    private final float left;
    private final float right;
    private final float top;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/document/DocumentCaptureRect$Companion;", "", "()V", "fromRectF", "Lcom/onfido/android/sdk/capture/document/DocumentCaptureRect;", "rect", "Landroid/graphics/RectF;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final DocumentCaptureRect fromRectF(RectF rect) {
            Intrinsics.checkNotNullParameter(rect, "rect");
            return new DocumentCaptureRect(rect.left, rect.top, rect.right, rect.bottom);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public DocumentCaptureRect(float f, float f2, float f3, float f4) {
        this.left = f;
        this.top = f2;
        this.right = f3;
        this.bottom = f4;
    }

    public static /* synthetic */ DocumentCaptureRect copy$default(DocumentCaptureRect documentCaptureRect, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            f = documentCaptureRect.left;
        }
        if ((i & 2) != 0) {
            f2 = documentCaptureRect.top;
        }
        if ((i & 4) != 0) {
            f3 = documentCaptureRect.right;
        }
        if ((i & 8) != 0) {
            f4 = documentCaptureRect.bottom;
        }
        return documentCaptureRect.copy(f, f2, f3, f4);
    }

    /* renamed from: component1, reason: from getter */
    public final float getLeft() {
        return this.left;
    }

    /* renamed from: component2, reason: from getter */
    public final float getTop() {
        return this.top;
    }

    /* renamed from: component3, reason: from getter */
    public final float getRight() {
        return this.right;
    }

    /* renamed from: component4, reason: from getter */
    public final float getBottom() {
        return this.bottom;
    }

    public final DocumentCaptureRect copy(float left, float top, float right, float bottom) {
        return new DocumentCaptureRect(left, top, right, bottom);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentCaptureRect)) {
            return false;
        }
        DocumentCaptureRect documentCaptureRect = (DocumentCaptureRect) other;
        return Float.compare(this.left, documentCaptureRect.left) == 0 && Float.compare(this.top, documentCaptureRect.top) == 0 && Float.compare(this.right, documentCaptureRect.right) == 0 && Float.compare(this.bottom, documentCaptureRect.bottom) == 0;
    }

    public final float getBottom() {
        return this.bottom;
    }

    public final float getHeight() {
        return this.bottom - this.top;
    }

    public final float getLeft() {
        return this.left;
    }

    public final float getRight() {
        return this.right;
    }

    public final float getTop() {
        return this.top;
    }

    public final float getWidth() {
        return this.right - this.left;
    }

    public int hashCode() {
        return (((((Float.hashCode(this.left) * 31) + Float.hashCode(this.top)) * 31) + Float.hashCode(this.right)) * 31) + Float.hashCode(this.bottom);
    }

    public final RectF toRectF$onfido_capture_sdk_core_release() {
        return new RectF(this.left, this.top, this.right, this.bottom);
    }

    public String toString() {
        return "DocumentCaptureRect(left=" + this.left + ", top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + ')';
    }
}
