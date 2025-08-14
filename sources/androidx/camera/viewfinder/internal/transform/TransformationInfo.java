package androidx.camera.viewfinder.internal.transform;

import android.graphics.Rect;

/* loaded from: classes.dex */
public abstract class TransformationInfo {
    public abstract Rect getCropRect();

    public abstract int getRotationDegrees();

    public abstract int getTargetRotation();

    public static TransformationInfo of(Rect rect, int i, int i2) {
        return new AutoValue_TransformationInfo(rect, i, i2);
    }

    TransformationInfo() {
    }
}
