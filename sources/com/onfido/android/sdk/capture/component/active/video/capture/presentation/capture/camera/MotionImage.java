package com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.camera;

import android.graphics.Bitmap;
import androidx.media3.common.MimeTypes;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.internal.util.OnfidoRectF;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/component/active/video/capture/presentation/capture/camera/MotionImage;", "", MimeTypes.BASE_TYPE_IMAGE, "Landroid/graphics/Bitmap;", "previewFrame", "Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "(Landroid/graphics/Bitmap;Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;)V", "getImage", "()Landroid/graphics/Bitmap;", "getPreviewFrame", "()Lcom/onfido/android/sdk/capture/internal/util/OnfidoRectF;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class MotionImage {
    private final Bitmap image;
    private final OnfidoRectF previewFrame;

    public MotionImage(Bitmap image, OnfidoRectF previewFrame) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(previewFrame, "previewFrame");
        this.image = image;
        this.previewFrame = previewFrame;
    }

    public static /* synthetic */ MotionImage copy$default(MotionImage motionImage, Bitmap bitmap, OnfidoRectF onfidoRectF, int i, Object obj) {
        if ((i & 1) != 0) {
            bitmap = motionImage.image;
        }
        if ((i & 2) != 0) {
            onfidoRectF = motionImage.previewFrame;
        }
        return motionImage.copy(bitmap, onfidoRectF);
    }

    /* renamed from: component1, reason: from getter */
    public final Bitmap getImage() {
        return this.image;
    }

    /* renamed from: component2, reason: from getter */
    public final OnfidoRectF getPreviewFrame() {
        return this.previewFrame;
    }

    public final MotionImage copy(Bitmap image, OnfidoRectF previewFrame) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(previewFrame, "previewFrame");
        return new MotionImage(image, previewFrame);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MotionImage)) {
            return false;
        }
        MotionImage motionImage = (MotionImage) other;
        return Intrinsics.areEqual(this.image, motionImage.image) && Intrinsics.areEqual(this.previewFrame, motionImage.previewFrame);
    }

    public final Bitmap getImage() {
        return this.image;
    }

    public final OnfidoRectF getPreviewFrame() {
        return this.previewFrame;
    }

    public int hashCode() {
        return (this.image.hashCode() * 31) + this.previewFrame.hashCode();
    }

    public String toString() {
        return "MotionImage(image=" + this.image + ", previewFrame=" + this.previewFrame + ')';
    }
}
