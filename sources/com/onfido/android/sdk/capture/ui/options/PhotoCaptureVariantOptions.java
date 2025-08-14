package com.onfido.android.sdk.capture.ui.options;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;

/* compiled from: PhotoCaptureVariantOptions.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/options/PhotoCaptureVariantOptions;", "Lcom/onfido/android/sdk/capture/ui/options/BaseOptions;", "withIntroScreen", "", "(Z)V", "getWithIntroScreen", "()Z", "component1", Constants.COPY_TYPE, "equals", "other", "", "hashCode", "", "toString", "", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class PhotoCaptureVariantOptions extends BaseOptions {
    private final boolean withIntroScreen;

    public static /* synthetic */ PhotoCaptureVariantOptions copy$default(PhotoCaptureVariantOptions photoCaptureVariantOptions, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = photoCaptureVariantOptions.withIntroScreen;
        }
        return photoCaptureVariantOptions.copy(z);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getWithIntroScreen() {
        return this.withIntroScreen;
    }

    public final PhotoCaptureVariantOptions copy(boolean withIntroScreen) {
        return new PhotoCaptureVariantOptions(withIntroScreen);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PhotoCaptureVariantOptions) && this.withIntroScreen == ((PhotoCaptureVariantOptions) other).withIntroScreen;
    }

    public final boolean getWithIntroScreen() {
        return this.withIntroScreen;
    }

    public int hashCode() {
        return Boolean.hashCode(this.withIntroScreen);
    }

    public String toString() {
        return "PhotoCaptureVariantOptions(withIntroScreen=" + this.withIntroScreen + ")";
    }

    public PhotoCaptureVariantOptions(boolean z) {
        this.withIntroScreen = z;
    }
}
