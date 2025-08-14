package com.onfido.android.sdk.capture.ui.camera;

import com.facebook.react.uimanager.ViewProps;
import com.onfido.android.sdk.capture.utils.OnfidoExtensionsKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B)\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0005H\u0016R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u0082\u0001\u0002\u0013\u0014¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/CameraFrame;", "", "yuv", "", "pictureWidth", "", "pictureHeight", ViewProps.ROTATION, "([BIII)V", "getPictureHeight", "()I", "getPictureWidth", "getRotation", "getYuv", "()[B", "equals", "", "other", "hashCode", "Lcom/onfido/android/sdk/capture/ui/camera/DocumentDetectionFrame;", "Lcom/onfido/android/sdk/capture/ui/camera/FaceDetectionFrame;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class CameraFrame {
    private final int pictureHeight;
    private final int pictureWidth;
    private final int rotation;
    private final byte[] yuv;

    private CameraFrame(byte[] bArr, int i, int i2, int i3) {
        this.yuv = bArr;
        this.pictureWidth = i;
        this.pictureHeight = i2;
        this.rotation = i3;
    }

    public boolean equals(Object other) {
        if (!(other instanceof CameraFrame)) {
            return super.equals(other);
        }
        CameraFrame cameraFrame = (CameraFrame) other;
        return Arrays.equals(cameraFrame.yuv, this.yuv) && cameraFrame.pictureWidth == this.pictureWidth && cameraFrame.pictureHeight == this.pictureHeight && cameraFrame.rotation == this.rotation;
    }

    public final int getPictureHeight() {
        return this.pictureHeight;
    }

    public final int getPictureWidth() {
        return this.pictureWidth;
    }

    public final int getRotation() {
        return this.rotation;
    }

    public final byte[] getYuv() {
        return this.yuv;
    }

    public int hashCode() {
        return (Arrays.hashCode(this.yuv) * 31) + OnfidoExtensionsKt.hashCode(Integer.valueOf(this.pictureWidth), Integer.valueOf(this.pictureHeight), Integer.valueOf(this.rotation));
    }

    public /* synthetic */ CameraFrame(byte[] bArr, int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, i, i2, (i4 & 8) != 0 ? 0 : i3, null);
    }

    public /* synthetic */ CameraFrame(byte[] bArr, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(bArr, i, i2, i3);
    }
}
