package androidx.camera.camera2.internal.compat.workaround;

import android.hardware.camera2.CameraCharacteristics;

/* loaded from: classes.dex */
public interface CameraCharacteristicsProvider {
    <T> T get(CameraCharacteristics.Key<T> key);
}
