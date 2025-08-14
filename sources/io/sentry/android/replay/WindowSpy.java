package io.sentry.android.replay;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import java.lang.reflect.Field;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Windows.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011R!\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001d\u0010\t\u001a\u0004\u0018\u00010\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\f¨\u0006\u0012"}, d2 = {"Lio/sentry/android/replay/WindowSpy;", "", "()V", "decorViewClass", "Ljava/lang/Class;", "getDecorViewClass", "()Ljava/lang/Class;", "decorViewClass$delegate", "Lkotlin/Lazy;", "windowField", "Ljava/lang/reflect/Field;", "getWindowField", "()Ljava/lang/reflect/Field;", "windowField$delegate", "pullWindow", "Landroid/view/Window;", "maybeDecorView", "Landroid/view/View;", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WindowSpy {
    public static final WindowSpy INSTANCE = new WindowSpy();

    /* renamed from: decorViewClass$delegate, reason: from kotlin metadata */
    private static final Lazy decorViewClass = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Class<?>>() { // from class: io.sentry.android.replay.WindowSpy$decorViewClass$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Class<?> invoke() {
            try {
                return Class.forName("com.android.internal.policy.DecorView");
            } catch (Throwable th) {
                Log.d("WindowSpy", "Unexpected exception loading DecorView on API " + Build.VERSION.SDK_INT, th);
                return null;
            }
        }
    });

    /* renamed from: windowField$delegate, reason: from kotlin metadata */
    private static final Lazy windowField = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Field>() { // from class: io.sentry.android.replay.WindowSpy$windowField$2
        @Override // kotlin.jvm.functions.Function0
        public final Field invoke() throws NoSuchFieldException {
            Class decorViewClass2 = WindowSpy.INSTANCE.getDecorViewClass();
            if (decorViewClass2 == null) {
                return null;
            }
            try {
                Field declaredField = decorViewClass2.getDeclaredField("mWindow");
                declaredField.setAccessible(true);
                return declaredField;
            } catch (NoSuchFieldException e) {
                Log.d("WindowSpy", "Unexpected exception retrieving " + decorViewClass2 + "#mWindow on API " + Build.VERSION.SDK_INT, e);
                return null;
            }
        }
    });

    private WindowSpy() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> getDecorViewClass() {
        return (Class) decorViewClass.getValue();
    }

    private final Field getWindowField() {
        return (Field) windowField.getValue();
    }

    public final Window pullWindow(View maybeDecorView) throws IllegalAccessException, IllegalArgumentException {
        Field windowField2;
        Intrinsics.checkNotNullParameter(maybeDecorView, "maybeDecorView");
        Class<?> decorViewClass2 = getDecorViewClass();
        if (decorViewClass2 == null || !decorViewClass2.isInstance(maybeDecorView) || (windowField2 = INSTANCE.getWindowField()) == null) {
            return null;
        }
        Object obj = windowField2.get(maybeDecorView);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type android.view.Window");
        return (Window) obj;
    }
}
