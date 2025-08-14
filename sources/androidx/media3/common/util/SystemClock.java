package androidx.media3.common.util;

import android.os.Handler;
import android.os.Looper;

/* loaded from: classes5.dex */
public class SystemClock implements Clock {
    @Override // androidx.media3.common.util.Clock
    public void onThreadBlocked() {
    }

    protected SystemClock() {
    }

    @Override // androidx.media3.common.util.Clock
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override // androidx.media3.common.util.Clock
    public long elapsedRealtime() {
        return android.os.SystemClock.elapsedRealtime();
    }

    @Override // androidx.media3.common.util.Clock
    public long uptimeMillis() {
        return android.os.SystemClock.uptimeMillis();
    }

    @Override // androidx.media3.common.util.Clock
    public HandlerWrapper createHandler(Looper looper, Handler.Callback callback) {
        return new SystemHandlerWrapper(new Handler(looper, callback));
    }
}
