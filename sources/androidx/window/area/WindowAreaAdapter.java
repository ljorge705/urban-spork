package androidx.window.area;

import kotlin.Metadata;

/* compiled from: WindowAreaAdapter.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/window/area/WindowAreaAdapter;", "", "()V", "translate", "Landroidx/window/area/WindowAreaStatus;", "status", "", "translate$window_release", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class WindowAreaAdapter {
    public static final WindowAreaAdapter INSTANCE = new WindowAreaAdapter();

    private WindowAreaAdapter() {
    }

    public final WindowAreaStatus translate$window_release(int status) {
        if (status == 1) {
            return WindowAreaStatus.UNAVAILABLE;
        }
        if (status == 2) {
            return WindowAreaStatus.AVAILABLE;
        }
        return WindowAreaStatus.UNSUPPORTED;
    }
}
