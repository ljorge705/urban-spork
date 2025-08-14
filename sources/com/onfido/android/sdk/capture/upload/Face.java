package com.onfido.android.sdk.capture.upload;

import com.facebook.hermes.intl.Constants;
import com.onfido.android.sdk.capture.ui.camera.face.FaceCaptureVariant;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Captures.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/onfido/android/sdk/capture/upload/Face;", "Ljava/io/Serializable;", "id", "", Constants.SENSITIVITY_VARIANT, "Lcom/onfido/android/sdk/capture/ui/camera/face/FaceCaptureVariant;", "(Ljava/lang/String;Lcom/onfido/android/sdk/capture/ui/camera/face/FaceCaptureVariant;)V", "getId", "()Ljava/lang/String;", "getVariant", "()Lcom/onfido/android/sdk/capture/ui/camera/face/FaceCaptureVariant;", "component1", "component2", com.clevertap.android.sdk.Constants.COPY_TYPE, "equals", "", "other", "", "hashCode", "", "toString", "onfido-public-api_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class Face implements Serializable {
    private final String id;
    private final FaceCaptureVariant variant;

    public static /* synthetic */ Face copy$default(Face face, String str, FaceCaptureVariant faceCaptureVariant, int i, Object obj) {
        if ((i & 1) != 0) {
            str = face.id;
        }
        if ((i & 2) != 0) {
            faceCaptureVariant = face.variant;
        }
        return face.copy(str, faceCaptureVariant);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final FaceCaptureVariant getVariant() {
        return this.variant;
    }

    public final Face copy(String id, FaceCaptureVariant variant) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(variant, "variant");
        return new Face(id, variant);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Face)) {
            return false;
        }
        Face face = (Face) other;
        return Intrinsics.areEqual(this.id, face.id) && this.variant == face.variant;
    }

    public final String getId() {
        return this.id;
    }

    public final FaceCaptureVariant getVariant() {
        return this.variant;
    }

    public int hashCode() {
        return (this.id.hashCode() * 31) + this.variant.hashCode();
    }

    public String toString() {
        return "Face(id=" + this.id + ", variant=" + this.variant + ")";
    }

    public Face(String id, FaceCaptureVariant variant) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(variant, "variant");
        this.id = id;
        this.variant = variant;
    }
}
