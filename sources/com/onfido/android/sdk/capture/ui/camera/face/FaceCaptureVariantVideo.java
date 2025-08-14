package com.onfido.android.sdk.capture.ui.camera.face;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: FaceCaptureVariantVideo.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/face/FaceCaptureVariantVideo;", "", "showIntroVideo", "", "showConfirmationVideo", "(ZZ)V", "getShowConfirmationVideo", "()Z", "getShowIntroVideo", "component1", "component2", Constants.COPY_TYPE, "equals", "other", "hashCode", "", "toString", "", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class FaceCaptureVariantVideo {
    private final boolean showConfirmationVideo;
    private final boolean showIntroVideo;

    /* JADX WARN: Illegal instructions before constructor call */
    public FaceCaptureVariantVideo() {
        boolean z = false;
        this(z, z, 3, null);
    }

    public static /* synthetic */ FaceCaptureVariantVideo copy$default(FaceCaptureVariantVideo faceCaptureVariantVideo, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = faceCaptureVariantVideo.showIntroVideo;
        }
        if ((i & 2) != 0) {
            z2 = faceCaptureVariantVideo.showConfirmationVideo;
        }
        return faceCaptureVariantVideo.copy(z, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getShowIntroVideo() {
        return this.showIntroVideo;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getShowConfirmationVideo() {
        return this.showConfirmationVideo;
    }

    public final FaceCaptureVariantVideo copy(boolean showIntroVideo, boolean showConfirmationVideo) {
        return new FaceCaptureVariantVideo(showIntroVideo, showConfirmationVideo);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FaceCaptureVariantVideo)) {
            return false;
        }
        FaceCaptureVariantVideo faceCaptureVariantVideo = (FaceCaptureVariantVideo) other;
        return this.showIntroVideo == faceCaptureVariantVideo.showIntroVideo && this.showConfirmationVideo == faceCaptureVariantVideo.showConfirmationVideo;
    }

    public final boolean getShowConfirmationVideo() {
        return this.showConfirmationVideo;
    }

    public final boolean getShowIntroVideo() {
        return this.showIntroVideo;
    }

    public int hashCode() {
        return (Boolean.hashCode(this.showIntroVideo) * 31) + Boolean.hashCode(this.showConfirmationVideo);
    }

    public String toString() {
        return "FaceCaptureVariantVideo(showIntroVideo=" + this.showIntroVideo + ", showConfirmationVideo=" + this.showConfirmationVideo + ")";
    }

    public FaceCaptureVariantVideo(boolean z, boolean z2) {
        this.showIntroVideo = z;
        this.showConfirmationVideo = z2;
    }

    public /* synthetic */ FaceCaptureVariantVideo(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? true : z2);
    }
}
