package androidx.camera.viewfinder.internal.utils;

import android.os.Looper;

/* loaded from: classes.dex */
public final class Threads {
    private Threads() {
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
