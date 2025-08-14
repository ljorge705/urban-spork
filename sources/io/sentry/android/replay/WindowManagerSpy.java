package io.sentry.android.replay;

import android.util.Log;
import android.view.View;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Windows.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J<\u0010\u0012\u001a\u00020\u001322\u0010\u0014\u001a.\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00170\u0016j\b\u0012\u0004\u0012\u00020\u0017`\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00170\u0016j\b\u0012\u0004\u0012\u00020\u0017`\u00180\u0015H\u0007R\u001d\u0010\u0003\u001a\u0004\u0018\u00010\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R!\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\b\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u000e\u001a\u0004\u0018\u00010\u00018BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\b\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, d2 = {"Lio/sentry/android/replay/WindowManagerSpy;", "", "()V", "mViewsField", "Ljava/lang/reflect/Field;", "getMViewsField", "()Ljava/lang/reflect/Field;", "mViewsField$delegate", "Lkotlin/Lazy;", "windowManagerClass", "Ljava/lang/Class;", "getWindowManagerClass", "()Ljava/lang/Class;", "windowManagerClass$delegate", "windowManagerInstance", "getWindowManagerInstance", "()Ljava/lang/Object;", "windowManagerInstance$delegate", "swapWindowManagerGlobalMViews", "", "swap", "Lkotlin/Function1;", "Ljava/util/ArrayList;", "Landroid/view/View;", "Lkotlin/collections/ArrayList;", "sentry-android-replay_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WindowManagerSpy {
    public static final WindowManagerSpy INSTANCE = new WindowManagerSpy();

    /* renamed from: windowManagerClass$delegate, reason: from kotlin metadata */
    private static final Lazy windowManagerClass = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Class<?>>() { // from class: io.sentry.android.replay.WindowManagerSpy$windowManagerClass$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Class<?> invoke() {
            try {
                return Class.forName("android.view.WindowManagerGlobal");
            } catch (Throwable th) {
                Log.w("WindowManagerSpy", th);
                return null;
            }
        }
    });

    /* renamed from: windowManagerInstance$delegate, reason: from kotlin metadata */
    private static final Lazy windowManagerInstance = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Object>() { // from class: io.sentry.android.replay.WindowManagerSpy$windowManagerInstance$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            Method method;
            Class windowManagerClass2 = WindowManagerSpy.INSTANCE.getWindowManagerClass();
            if (windowManagerClass2 == null || (method = windowManagerClass2.getMethod("getInstance", new Class[0])) == null) {
                return null;
            }
            return method.invoke(null, new Object[0]);
        }
    });

    /* renamed from: mViewsField$delegate, reason: from kotlin metadata */
    private static final Lazy mViewsField = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<Field>() { // from class: io.sentry.android.replay.WindowManagerSpy$mViewsField$2
        @Override // kotlin.jvm.functions.Function0
        public final Field invoke() throws NoSuchFieldException {
            Class windowManagerClass2 = WindowManagerSpy.INSTANCE.getWindowManagerClass();
            if (windowManagerClass2 == null) {
                return null;
            }
            Field declaredField = windowManagerClass2.getDeclaredField("mViews");
            declaredField.setAccessible(true);
            return declaredField;
        }
    });

    private WindowManagerSpy() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Class<?> getWindowManagerClass() {
        return (Class) windowManagerClass.getValue();
    }

    private final Object getWindowManagerInstance() {
        return windowManagerInstance.getValue();
    }

    private final Field getMViewsField() {
        return (Field) mViewsField.getValue();
    }

    public final void swapWindowManagerGlobalMViews(Function1<? super ArrayList<View>, ? extends ArrayList<View>> swap) {
        Field mViewsField2;
        Intrinsics.checkNotNullParameter(swap, "swap");
        try {
            Object windowManagerInstance2 = getWindowManagerInstance();
            if (windowManagerInstance2 == null || (mViewsField2 = INSTANCE.getMViewsField()) == null) {
                return;
            }
            Object obj = mViewsField2.get(windowManagerInstance2);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type java.util.ArrayList<android.view.View>{ kotlin.collections.TypeAliasesKt.ArrayList<android.view.View> }");
            mViewsField2.set(windowManagerInstance2, swap.invoke((ArrayList) obj));
        } catch (Throwable th) {
            Log.w("WindowManagerSpy", th);
        }
    }
}
