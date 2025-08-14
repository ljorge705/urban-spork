package com.onfido.android.sdk.capture.internal.camera;

import android.graphics.Bitmap;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001bB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001a\u001a\u00020\u0005H\u0016R\u0014\u0010\n\u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\b\u001a\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0006\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0007\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014¨\u0006\u001c"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage;", "", "data", "", "width", "", "height", ViewProps.ROTATION, "cropRect", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage$CropRect;", "bitmap", "Landroid/graphics/Bitmap;", "([BIIILcom/onfido/android/sdk/capture/internal/camera/OnfidoImage$CropRect;Landroid/graphics/Bitmap;)V", "getBitmap$onfido_capture_sdk_core_release", "()Landroid/graphics/Bitmap;", "getCropRect$onfido_capture_sdk_core_release", "()Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage$CropRect;", "getData$onfido_capture_sdk_core_release", "()[B", "getHeight$onfido_capture_sdk_core_release", "()I", "getRotation$onfido_capture_sdk_core_release", "getWidth$onfido_capture_sdk_core_release", "equals", "", "other", "hashCode", "CropRect", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class OnfidoImage {
    private final Bitmap bitmap;
    private final CropRect cropRect;
    private final byte[] data;
    private final int height;
    private final int rotation;
    private final int width;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B3\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage$CropRect;", "", "zoomFactor", "", "verticalOffset", "", "horizontalOffset", "previewWidth", "previewHeight", "(FIIII)V", "getHorizontalOffset", "()I", "getPreviewHeight", "getPreviewWidth", "getVerticalOffset", "getZoomFactor", "()F", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class CropRect {
        private final int horizontalOffset;
        private final int previewHeight;
        private final int previewWidth;
        private final int verticalOffset;
        private final float zoomFactor;

        public CropRect(float f, int i, int i2, int i3, int i4) {
            this.zoomFactor = f;
            this.verticalOffset = i;
            this.horizontalOffset = i2;
            this.previewWidth = i3;
            this.previewHeight = i4;
        }

        public static /* synthetic */ CropRect copy$default(CropRect cropRect, float f, int i, int i2, int i3, int i4, int i5, Object obj) {
            if ((i5 & 1) != 0) {
                f = cropRect.zoomFactor;
            }
            if ((i5 & 2) != 0) {
                i = cropRect.verticalOffset;
            }
            int i6 = i;
            if ((i5 & 4) != 0) {
                i2 = cropRect.horizontalOffset;
            }
            int i7 = i2;
            if ((i5 & 8) != 0) {
                i3 = cropRect.previewWidth;
            }
            int i8 = i3;
            if ((i5 & 16) != 0) {
                i4 = cropRect.previewHeight;
            }
            return cropRect.copy(f, i6, i7, i8, i4);
        }

        /* renamed from: component1, reason: from getter */
        public final float getZoomFactor() {
            return this.zoomFactor;
        }

        /* renamed from: component2, reason: from getter */
        public final int getVerticalOffset() {
            return this.verticalOffset;
        }

        /* renamed from: component3, reason: from getter */
        public final int getHorizontalOffset() {
            return this.horizontalOffset;
        }

        /* renamed from: component4, reason: from getter */
        public final int getPreviewWidth() {
            return this.previewWidth;
        }

        /* renamed from: component5, reason: from getter */
        public final int getPreviewHeight() {
            return this.previewHeight;
        }

        public final CropRect copy(float zoomFactor, int verticalOffset, int horizontalOffset, int previewWidth, int previewHeight) {
            return new CropRect(zoomFactor, verticalOffset, horizontalOffset, previewWidth, previewHeight);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CropRect)) {
                return false;
            }
            CropRect cropRect = (CropRect) other;
            return Float.compare(this.zoomFactor, cropRect.zoomFactor) == 0 && this.verticalOffset == cropRect.verticalOffset && this.horizontalOffset == cropRect.horizontalOffset && this.previewWidth == cropRect.previewWidth && this.previewHeight == cropRect.previewHeight;
        }

        public final int getHorizontalOffset() {
            return this.horizontalOffset;
        }

        public final int getPreviewHeight() {
            return this.previewHeight;
        }

        public final int getPreviewWidth() {
            return this.previewWidth;
        }

        public final int getVerticalOffset() {
            return this.verticalOffset;
        }

        public final float getZoomFactor() {
            return this.zoomFactor;
        }

        public int hashCode() {
            return (((((((Float.hashCode(this.zoomFactor) * 31) + Integer.hashCode(this.verticalOffset)) * 31) + Integer.hashCode(this.horizontalOffset)) * 31) + Integer.hashCode(this.previewWidth)) * 31) + Integer.hashCode(this.previewHeight);
        }

        public String toString() {
            return "CropRect(zoomFactor=" + this.zoomFactor + ", verticalOffset=" + this.verticalOffset + ", horizontalOffset=" + this.horizontalOffset + ", previewWidth=" + this.previewWidth + ", previewHeight=" + this.previewHeight + ')';
        }

        public /* synthetic */ CropRect(float f, int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
            this((i5 & 1) != 0 ? 1.0f : f, (i5 & 2) != 0 ? 0 : i, (i5 & 4) != 0 ? 0 : i2, i3, i4);
        }
    }

    public OnfidoImage(byte[] data, int i, int i2, int i3, CropRect cropRect, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(cropRect, "cropRect");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        this.data = data;
        this.width = i;
        this.height = i2;
        this.rotation = i3;
        this.cropRect = cropRect;
        this.bitmap = bitmap;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OnfidoImage)) {
            return false;
        }
        OnfidoImage onfidoImage = (OnfidoImage) other;
        return Arrays.equals(this.data, onfidoImage.data) && this.width == onfidoImage.width && this.height == onfidoImage.height && this.rotation == onfidoImage.rotation && Intrinsics.areEqual(this.cropRect, onfidoImage.cropRect);
    }

    /* renamed from: getBitmap$onfido_capture_sdk_core_release, reason: from getter */
    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    /* renamed from: getCropRect$onfido_capture_sdk_core_release, reason: from getter */
    public final CropRect getCropRect() {
        return this.cropRect;
    }

    /* renamed from: getData$onfido_capture_sdk_core_release, reason: from getter */
    public final byte[] getData() {
        return this.data;
    }

    /* renamed from: getHeight$onfido_capture_sdk_core_release, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    /* renamed from: getRotation$onfido_capture_sdk_core_release, reason: from getter */
    public final int getRotation() {
        return this.rotation;
    }

    /* renamed from: getWidth$onfido_capture_sdk_core_release, reason: from getter */
    public final int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return (((((((Arrays.hashCode(this.data) * 31) + this.width) * 31) + this.height) * 31) + this.rotation) * 31) + this.cropRect.hashCode();
    }
}
