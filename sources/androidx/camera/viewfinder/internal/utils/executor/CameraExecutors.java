package androidx.camera.viewfinder.internal.utils.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* loaded from: classes.dex */
public final class CameraExecutors {
    private CameraExecutors() {
    }

    public static ScheduledExecutorService mainThreadExecutor() {
        return MainThreadExecutor.getInstance();
    }

    public static Executor directExecutor() {
        return DirectExecutor.getInstance();
    }
}
