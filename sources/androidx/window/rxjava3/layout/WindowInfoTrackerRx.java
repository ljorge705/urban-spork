package androidx.window.rxjava3.layout;

import android.app.Activity;
import android.content.Context;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.WindowLayoutInfo;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.rx3.RxConvertKt;

/* compiled from: WindowInfoTrackerRx.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0018\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\t*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\t*\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\n"}, d2 = {"windowLayoutInfoFlowable", "Lio/reactivex/rxjava3/core/Flowable;", "Landroidx/window/layout/WindowLayoutInfo;", "Landroidx/window/layout/WindowInfoTracker;", "activity", "Landroid/app/Activity;", "context", "Landroid/content/Context;", "windowLayoutInfoObservable", "Lio/reactivex/rxjava3/core/Observable;", "window-rxjava3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class WindowInfoTrackerRx {
    public static final Observable<WindowLayoutInfo> windowLayoutInfoObservable(WindowInfoTracker windowInfoTracker, Activity activity) {
        Intrinsics.checkNotNullParameter(windowInfoTracker, "<this>");
        Intrinsics.checkNotNullParameter(activity, "activity");
        return RxConvertKt.asObservable$default(windowInfoTracker.windowLayoutInfo(activity), null, 1, null);
    }

    public static final Flowable<WindowLayoutInfo> windowLayoutInfoFlowable(WindowInfoTracker windowInfoTracker, Activity activity) {
        Intrinsics.checkNotNullParameter(windowInfoTracker, "<this>");
        Intrinsics.checkNotNullParameter(activity, "activity");
        return RxConvertKt.asFlowable$default(windowInfoTracker.windowLayoutInfo(activity), null, 1, null);
    }

    public static final Observable<WindowLayoutInfo> windowLayoutInfoObservable(WindowInfoTracker windowInfoTracker, Context context) {
        Intrinsics.checkNotNullParameter(windowInfoTracker, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        return RxConvertKt.asObservable$default(windowInfoTracker.windowLayoutInfo(context), null, 1, null);
    }

    public static final Flowable<WindowLayoutInfo> windowLayoutInfoFlowable(WindowInfoTracker windowInfoTracker, Context context) {
        Intrinsics.checkNotNullParameter(windowInfoTracker, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        return RxConvertKt.asFlowable$default(windowInfoTracker.windowLayoutInfo(context), null, 1, null);
    }
}
