package androidx.camera.viewfinder.internal.quirk;

import android.os.Build;

/* loaded from: classes.dex */
public class SurfaceViewNotCroppedByParentQuirk implements Quirk {
    private static final String RED_MI_NOTE_10_MODEL = "M2101K7AG";
    private static final String XIAOMI = "XIAOMI";

    static boolean load() {
        return XIAOMI.equalsIgnoreCase(Build.MANUFACTURER) && RED_MI_NOTE_10_MODEL.equalsIgnoreCase(Build.MODEL);
    }
}
