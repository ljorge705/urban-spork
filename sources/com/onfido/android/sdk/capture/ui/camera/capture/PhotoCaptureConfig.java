package com.onfido.android.sdk.capture.ui.camera.capture;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/capture/PhotoCaptureConfig;", "", "shouldUseDefaultJpegQuality", "", "(Z)V", "getShouldUseDefaultJpegQuality", "()Z", "component1", Constants.COPY_TYPE, "equals", "other", "hashCode", "", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class PhotoCaptureConfig {
    private final boolean shouldUseDefaultJpegQuality;

    public PhotoCaptureConfig() {
        this(false, 1, null);
    }

    public static /* synthetic */ PhotoCaptureConfig copy$default(PhotoCaptureConfig photoCaptureConfig, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = photoCaptureConfig.shouldUseDefaultJpegQuality;
        }
        return photoCaptureConfig.copy(z);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getShouldUseDefaultJpegQuality() {
        return this.shouldUseDefaultJpegQuality;
    }

    public final PhotoCaptureConfig copy(boolean shouldUseDefaultJpegQuality) {
        return new PhotoCaptureConfig(shouldUseDefaultJpegQuality);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PhotoCaptureConfig) && this.shouldUseDefaultJpegQuality == ((PhotoCaptureConfig) other).shouldUseDefaultJpegQuality;
    }

    public final boolean getShouldUseDefaultJpegQuality() {
        return this.shouldUseDefaultJpegQuality;
    }

    public int hashCode() {
        return Boolean.hashCode(this.shouldUseDefaultJpegQuality);
    }

    public String toString() {
        return "PhotoCaptureConfig(shouldUseDefaultJpegQuality=" + this.shouldUseDefaultJpegQuality + ')';
    }

    public PhotoCaptureConfig(boolean z) {
        this.shouldUseDefaultJpegQuality = z;
    }

    public /* synthetic */ PhotoCaptureConfig(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }
}
