package com.onfido.android.sdk.capture.ui.camera;

import android.graphics.Bitmap;
import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.internal.camera.OnfidoImage;
import com.onfido.android.sdk.capture.utils.OnfidoExtensionsKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0096\u0002J\b\u0010 \u001a\u00020\u0005H\u0016R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001a¨\u0006!"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "Lcom/onfido/android/sdk/capture/ui/camera/CameraFrame;", "data", "", "cameraWidth", "", "cameraHeight", "frameWidth", "frameHeight", "outerFrame", "Lcom/onfido/android/sdk/capture/ui/camera/Frame;", "innerFrame", ViewProps.ROTATION, "cropRect", "Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage$CropRect;", "bitmap", "Landroid/graphics/Bitmap;", "([BIIIILcom/onfido/android/sdk/capture/ui/camera/Frame;Lcom/onfido/android/sdk/capture/ui/camera/Frame;ILcom/onfido/android/sdk/capture/internal/camera/OnfidoImage$CropRect;Landroid/graphics/Bitmap;)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getCropRect", "()Lcom/onfido/android/sdk/capture/internal/camera/OnfidoImage$CropRect;", "getFrameHeight", "()I", "getFrameWidth", "getInnerFrame", "()Lcom/onfido/android/sdk/capture/ui/camera/Frame;", "getOuterFrame", "equals", "", "other", "", "hashCode", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DocumentDetectionFrame extends CameraFrame {
    private final Bitmap bitmap;
    private final OnfidoImage.CropRect cropRect;
    private final int frameHeight;
    private final int frameWidth;
    private final Frame innerFrame;
    private final Frame outerFrame;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DocumentDetectionFrame(byte[] data, int i, int i2, int i3, int i4, Frame outerFrame, Frame innerFrame, int i5, OnfidoImage.CropRect cropRect, Bitmap bitmap) {
        super(data, i, i2, i5, null);
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(outerFrame, "outerFrame");
        Intrinsics.checkNotNullParameter(innerFrame, "innerFrame");
        Intrinsics.checkNotNullParameter(cropRect, "cropRect");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        this.frameWidth = i3;
        this.frameHeight = i4;
        this.outerFrame = outerFrame;
        this.innerFrame = innerFrame;
        this.cropRect = cropRect;
        this.bitmap = bitmap;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CameraFrame
    public boolean equals(Object other) {
        if (!(other instanceof DocumentDetectionFrame)) {
            return super.equals(other);
        }
        DocumentDetectionFrame documentDetectionFrame = (DocumentDetectionFrame) other;
        return Arrays.equals(documentDetectionFrame.getYuv(), getYuv()) && documentDetectionFrame.getPictureWidth() == getPictureWidth() && documentDetectionFrame.getPictureHeight() == getPictureHeight() && documentDetectionFrame.frameWidth == this.frameWidth && documentDetectionFrame.frameHeight == this.frameHeight && Intrinsics.areEqual(documentDetectionFrame.outerFrame, this.outerFrame) && Intrinsics.areEqual(documentDetectionFrame.innerFrame, this.innerFrame) && documentDetectionFrame.getRotation() == getRotation();
    }

    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    public final OnfidoImage.CropRect getCropRect() {
        return this.cropRect;
    }

    public final int getFrameHeight() {
        return this.frameHeight;
    }

    public final int getFrameWidth() {
        return this.frameWidth;
    }

    public final Frame getInnerFrame() {
        return this.innerFrame;
    }

    public final Frame getOuterFrame() {
        return this.outerFrame;
    }

    @Override // com.onfido.android.sdk.capture.ui.camera.CameraFrame
    public int hashCode() {
        return (Arrays.hashCode(getYuv()) * 31) + OnfidoExtensionsKt.hashCode(Integer.valueOf(this.frameWidth), Integer.valueOf(this.frameHeight), this.outerFrame, this.innerFrame, Integer.valueOf(getPictureWidth()), Integer.valueOf(getPictureHeight()), Integer.valueOf(getRotation()));
    }

    public /* synthetic */ DocumentDetectionFrame(byte[] bArr, int i, int i2, int i3, int i4, Frame frame, Frame frame2, int i5, OnfidoImage.CropRect cropRect, Bitmap bitmap, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, i, i2, i3, i4, frame, (i6 & 64) != 0 ? frame : frame2, (i6 & 128) != 0 ? 0 : i5, (i6 & 256) != 0 ? new OnfidoImage.CropRect(1.0f, 0, 0, 0, 0) : cropRect, bitmap);
    }
}
