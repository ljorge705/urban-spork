package androidx.camera.viewfinder.internal.transform;

import android.graphics.Rect;

/* loaded from: classes.dex */
final class AutoValue_TransformationInfo extends TransformationInfo {
    private final Rect cropRect;
    private final int rotationDegrees;
    private final int targetRotation;

    @Override // androidx.camera.viewfinder.internal.transform.TransformationInfo
    public Rect getCropRect() {
        return this.cropRect;
    }

    @Override // androidx.camera.viewfinder.internal.transform.TransformationInfo
    public int getRotationDegrees() {
        return this.rotationDegrees;
    }

    @Override // androidx.camera.viewfinder.internal.transform.TransformationInfo
    public int getTargetRotation() {
        return this.targetRotation;
    }

    AutoValue_TransformationInfo(Rect rect, int i, int i2) {
        if (rect == null) {
            throw new NullPointerException("Null cropRect");
        }
        this.cropRect = rect;
        this.rotationDegrees = i;
        this.targetRotation = i2;
    }

    public String toString() {
        return "TransformationInfo{cropRect=" + this.cropRect + ", rotationDegrees=" + this.rotationDegrees + ", targetRotation=" + this.targetRotation + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TransformationInfo)) {
            return false;
        }
        TransformationInfo transformationInfo = (TransformationInfo) obj;
        return this.cropRect.equals(transformationInfo.getCropRect()) && this.rotationDegrees == transformationInfo.getRotationDegrees() && this.targetRotation == transformationInfo.getTargetRotation();
    }

    public int hashCode() {
        return ((((this.cropRect.hashCode() ^ 1000003) * 1000003) ^ this.rotationDegrees) * 1000003) ^ this.targetRotation;
    }
}
