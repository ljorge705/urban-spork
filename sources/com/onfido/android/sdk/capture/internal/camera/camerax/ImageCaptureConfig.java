package com.onfido.android.sdk.capture.internal.camera.camerax;

import android.util.Size;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageCaptureConfig;", "", "targetResolution", "Landroid/util/Size;", "captureMode", "", "flashMode", "(Landroid/util/Size;II)V", "getCaptureMode", "()I", "getFlashMode", "getTargetResolution", "()Landroid/util/Size;", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "Companion", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class ImageCaptureConfig {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Size TARGET_RESOLUTION_FULL_HD = new Size(1920, 1440);
    private final int captureMode;
    private final int flashMode;
    private final Size targetResolution;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/camera/camerax/ImageCaptureConfig$Companion;", "", "()V", "TARGET_RESOLUTION_FULL_HD", "Landroid/util/Size;", "getTARGET_RESOLUTION_FULL_HD", "()Landroid/util/Size;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public final Size getTARGET_RESOLUTION_FULL_HD() {
            return ImageCaptureConfig.TARGET_RESOLUTION_FULL_HD;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public ImageCaptureConfig() {
        this(null, 0, 0, 7, null);
    }

    public static /* synthetic */ ImageCaptureConfig copy$default(ImageCaptureConfig imageCaptureConfig, Size size, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            size = imageCaptureConfig.targetResolution;
        }
        if ((i3 & 2) != 0) {
            i = imageCaptureConfig.captureMode;
        }
        if ((i3 & 4) != 0) {
            i2 = imageCaptureConfig.flashMode;
        }
        return imageCaptureConfig.copy(size, i, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final Size getTargetResolution() {
        return this.targetResolution;
    }

    /* renamed from: component2, reason: from getter */
    public final int getCaptureMode() {
        return this.captureMode;
    }

    /* renamed from: component3, reason: from getter */
    public final int getFlashMode() {
        return this.flashMode;
    }

    public final ImageCaptureConfig copy(Size targetResolution, int captureMode, int flashMode) {
        Intrinsics.checkNotNullParameter(targetResolution, "targetResolution");
        return new ImageCaptureConfig(targetResolution, captureMode, flashMode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImageCaptureConfig)) {
            return false;
        }
        ImageCaptureConfig imageCaptureConfig = (ImageCaptureConfig) other;
        return Intrinsics.areEqual(this.targetResolution, imageCaptureConfig.targetResolution) && this.captureMode == imageCaptureConfig.captureMode && this.flashMode == imageCaptureConfig.flashMode;
    }

    public final int getCaptureMode() {
        return this.captureMode;
    }

    public final int getFlashMode() {
        return this.flashMode;
    }

    public final Size getTargetResolution() {
        return this.targetResolution;
    }

    public int hashCode() {
        return (((this.targetResolution.hashCode() * 31) + Integer.hashCode(this.captureMode)) * 31) + Integer.hashCode(this.flashMode);
    }

    public String toString() {
        return "ImageCaptureConfig(targetResolution=" + this.targetResolution + ", captureMode=" + this.captureMode + ", flashMode=" + this.flashMode + ')';
    }

    public ImageCaptureConfig(Size targetResolution, int i, int i2) {
        Intrinsics.checkNotNullParameter(targetResolution, "targetResolution");
        this.targetResolution = targetResolution;
        this.captureMode = i;
        this.flashMode = i2;
    }

    public /* synthetic */ ImageCaptureConfig(Size size, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? TARGET_RESOLUTION_FULL_HD : size, (i3 & 2) != 0 ? 1 : i, (i3 & 4) != 0 ? 2 : i2);
    }
}
