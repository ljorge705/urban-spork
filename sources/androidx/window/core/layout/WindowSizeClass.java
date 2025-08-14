package androidx.window.core.layout;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WindowSizeClass.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Landroidx/window/core/layout/WindowSizeClass;", "", "windowWidthSizeClass", "Landroidx/window/core/layout/WindowWidthSizeClass;", "windowHeightSizeClass", "Landroidx/window/core/layout/WindowHeightSizeClass;", "(Landroidx/window/core/layout/WindowWidthSizeClass;Landroidx/window/core/layout/WindowHeightSizeClass;)V", "getWindowHeightSizeClass", "()Landroidx/window/core/layout/WindowHeightSizeClass;", "getWindowWidthSizeClass", "()Landroidx/window/core/layout/WindowWidthSizeClass;", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "window-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class WindowSizeClass {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final WindowHeightSizeClass windowHeightSizeClass;
    private final WindowWidthSizeClass windowWidthSizeClass;

    public /* synthetic */ WindowSizeClass(WindowWidthSizeClass windowWidthSizeClass, WindowHeightSizeClass windowHeightSizeClass, DefaultConstructorMarker defaultConstructorMarker) {
        this(windowWidthSizeClass, windowHeightSizeClass);
    }

    @JvmStatic
    public static final WindowSizeClass compute(float f, float f2) {
        return INSTANCE.compute(f, f2);
    }

    public final WindowHeightSizeClass getWindowHeightSizeClass() {
        return this.windowHeightSizeClass;
    }

    public final WindowWidthSizeClass getWindowWidthSizeClass() {
        return this.windowWidthSizeClass;
    }

    private WindowSizeClass(WindowWidthSizeClass windowWidthSizeClass, WindowHeightSizeClass windowHeightSizeClass) {
        this.windowWidthSizeClass = windowWidthSizeClass;
        this.windowHeightSizeClass = windowHeightSizeClass;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type androidx.window.core.layout.WindowSizeClass");
        WindowSizeClass windowSizeClass = (WindowSizeClass) other;
        return Intrinsics.areEqual(this.windowWidthSizeClass, windowSizeClass.windowWidthSizeClass) && Intrinsics.areEqual(this.windowHeightSizeClass, windowSizeClass.windowHeightSizeClass);
    }

    public int hashCode() {
        return (this.windowWidthSizeClass.getRawValue() * 31) + this.windowHeightSizeClass.getRawValue();
    }

    public String toString() {
        return "SizeClass { widthSizeClass: " + this.windowWidthSizeClass + ", heightSizeClass: " + this.windowHeightSizeClass + " }";
    }

    /* compiled from: WindowSizeClass.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¨\u0006\b"}, d2 = {"Landroidx/window/core/layout/WindowSizeClass$Companion;", "", "()V", "compute", "Landroidx/window/core/layout/WindowSizeClass;", "dpWidth", "", "dpHeight", "window-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final WindowSizeClass compute(float dpWidth, float dpHeight) {
            return new WindowSizeClass(WindowWidthSizeClass.INSTANCE.compute$window_core_release(dpWidth), WindowHeightSizeClass.INSTANCE.compute$window_core_release(dpHeight), null);
        }
    }
}
