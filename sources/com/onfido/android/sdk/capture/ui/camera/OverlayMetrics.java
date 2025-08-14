package com.onfido.android.sdk.capture.ui.camera;

import android.graphics.RectF;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\tHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f¨\u0006\""}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/OverlayMetrics;", "", "visibleCaptureRect", "Landroid/graphics/RectF;", "realCaptureRect", "bigHorizontalWeight", "", "verticalWeight", "height", "", "width", "(Landroid/graphics/RectF;Landroid/graphics/RectF;FFII)V", "getBigHorizontalWeight", "()F", "getHeight", "()I", "getRealCaptureRect", "()Landroid/graphics/RectF;", "getVerticalWeight", "getVisibleCaptureRect", "getWidth", "component1", "component2", "component3", "component4", "component5", "component6", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class OverlayMetrics {
    private final float bigHorizontalWeight;
    private final int height;
    private final RectF realCaptureRect;
    private final float verticalWeight;
    private final RectF visibleCaptureRect;
    private final int width;

    public OverlayMetrics(RectF visibleCaptureRect, RectF realCaptureRect, float f, float f2, int i, int i2) {
        Intrinsics.checkNotNullParameter(visibleCaptureRect, "visibleCaptureRect");
        Intrinsics.checkNotNullParameter(realCaptureRect, "realCaptureRect");
        this.visibleCaptureRect = visibleCaptureRect;
        this.realCaptureRect = realCaptureRect;
        this.bigHorizontalWeight = f;
        this.verticalWeight = f2;
        this.height = i;
        this.width = i2;
    }

    public static /* synthetic */ OverlayMetrics copy$default(OverlayMetrics overlayMetrics, RectF rectF, RectF rectF2, float f, float f2, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            rectF = overlayMetrics.visibleCaptureRect;
        }
        if ((i3 & 2) != 0) {
            rectF2 = overlayMetrics.realCaptureRect;
        }
        RectF rectF3 = rectF2;
        if ((i3 & 4) != 0) {
            f = overlayMetrics.bigHorizontalWeight;
        }
        float f3 = f;
        if ((i3 & 8) != 0) {
            f2 = overlayMetrics.verticalWeight;
        }
        float f4 = f2;
        if ((i3 & 16) != 0) {
            i = overlayMetrics.height;
        }
        int i4 = i;
        if ((i3 & 32) != 0) {
            i2 = overlayMetrics.width;
        }
        return overlayMetrics.copy(rectF, rectF3, f3, f4, i4, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final RectF getVisibleCaptureRect() {
        return this.visibleCaptureRect;
    }

    /* renamed from: component2, reason: from getter */
    public final RectF getRealCaptureRect() {
        return this.realCaptureRect;
    }

    /* renamed from: component3, reason: from getter */
    public final float getBigHorizontalWeight() {
        return this.bigHorizontalWeight;
    }

    /* renamed from: component4, reason: from getter */
    public final float getVerticalWeight() {
        return this.verticalWeight;
    }

    /* renamed from: component5, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    /* renamed from: component6, reason: from getter */
    public final int getWidth() {
        return this.width;
    }

    public final OverlayMetrics copy(RectF visibleCaptureRect, RectF realCaptureRect, float bigHorizontalWeight, float verticalWeight, int height, int width) {
        Intrinsics.checkNotNullParameter(visibleCaptureRect, "visibleCaptureRect");
        Intrinsics.checkNotNullParameter(realCaptureRect, "realCaptureRect");
        return new OverlayMetrics(visibleCaptureRect, realCaptureRect, bigHorizontalWeight, verticalWeight, height, width);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OverlayMetrics)) {
            return false;
        }
        OverlayMetrics overlayMetrics = (OverlayMetrics) other;
        return Intrinsics.areEqual(this.visibleCaptureRect, overlayMetrics.visibleCaptureRect) && Intrinsics.areEqual(this.realCaptureRect, overlayMetrics.realCaptureRect) && Float.compare(this.bigHorizontalWeight, overlayMetrics.bigHorizontalWeight) == 0 && Float.compare(this.verticalWeight, overlayMetrics.verticalWeight) == 0 && this.height == overlayMetrics.height && this.width == overlayMetrics.width;
    }

    public final float getBigHorizontalWeight() {
        return this.bigHorizontalWeight;
    }

    public final int getHeight() {
        return this.height;
    }

    public final RectF getRealCaptureRect() {
        return this.realCaptureRect;
    }

    public final float getVerticalWeight() {
        return this.verticalWeight;
    }

    public final RectF getVisibleCaptureRect() {
        return this.visibleCaptureRect;
    }

    public final int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return (((((((((this.visibleCaptureRect.hashCode() * 31) + this.realCaptureRect.hashCode()) * 31) + Float.hashCode(this.bigHorizontalWeight)) * 31) + Float.hashCode(this.verticalWeight)) * 31) + Integer.hashCode(this.height)) * 31) + Integer.hashCode(this.width);
    }

    public String toString() {
        return "OverlayMetrics(visibleCaptureRect=" + this.visibleCaptureRect + ", realCaptureRect=" + this.realCaptureRect + ", bigHorizontalWeight=" + this.bigHorizontalWeight + ", verticalWeight=" + this.verticalWeight + ", height=" + this.height + ", width=" + this.width + ')';
    }
}
