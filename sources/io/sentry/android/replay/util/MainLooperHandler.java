package io.sentry.android.replay.util;

import android.os.Handler;
import android.os.Looper;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MainLooperHandler.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lio/sentry/android/replay/util/MainLooperHandler;", "", "looper", "Landroid/os/Looper;", "(Landroid/os/Looper;)V", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "post", "", "runnable", "Ljava/lang/Runnable;", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class MainLooperHandler {
    private final Handler handler;

    /* JADX WARN: Multi-variable type inference failed */
    public MainLooperHandler() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public final Handler getHandler() {
        return this.handler;
    }

    public MainLooperHandler(Looper looper) {
        Intrinsics.checkNotNullParameter(looper, "looper");
        this.handler = new Handler(looper);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ MainLooperHandler(Looper looper, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            looper = Looper.getMainLooper();
            Intrinsics.checkNotNullExpressionValue(looper, "getMainLooper()");
        }
        this(looper);
    }

    public final void post(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        this.handler.post(runnable);
    }
}
