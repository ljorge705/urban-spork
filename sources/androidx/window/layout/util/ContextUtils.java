package androidx.window.layout.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.inputmethodservice.InputMethodService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ContextUtils.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/window/layout/util/ContextUtils;", "", "()V", "unwrapUiContext", "Landroid/content/Context;", "context", "unwrapUiContext$window_release", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class ContextUtils {
    public static final ContextUtils INSTANCE = new ContextUtils();

    private ContextUtils() {
    }

    public final Context unwrapUiContext$window_release(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Context baseContext = context;
        while (baseContext instanceof ContextWrapper) {
            if ((baseContext instanceof Activity) || (baseContext instanceof InputMethodService)) {
                return baseContext;
            }
            ContextWrapper contextWrapper = (ContextWrapper) baseContext;
            if (contextWrapper.getBaseContext() == null) {
                return baseContext;
            }
            baseContext = contextWrapper.getBaseContext();
            Intrinsics.checkNotNullExpressionValue(baseContext, "iterator.baseContext");
        }
        throw new IllegalArgumentException("Context " + context + " is not a UiContext");
    }
}
