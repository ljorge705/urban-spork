package androidx.window.core.layout;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WindowHeightSizeClass.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/window/core/layout/WindowHeightSizeClass;", "", "rawValue", "", "(I)V", "equals", "", "other", "hashCode", "toString", "", "Companion", "window-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class WindowHeightSizeClass {
    private final int rawValue;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final WindowHeightSizeClass COMPACT = new WindowHeightSizeClass(0);
    public static final WindowHeightSizeClass MEDIUM = new WindowHeightSizeClass(1);
    public static final WindowHeightSizeClass EXPANDED = new WindowHeightSizeClass(2);

    /* renamed from: hashCode, reason: from getter */
    public int getRawValue() {
        return this.rawValue;
    }

    private WindowHeightSizeClass(int i) {
        this.rawValue = i;
    }

    public String toString() {
        String str;
        if (Intrinsics.areEqual(this, COMPACT)) {
            str = "COMPACT";
        } else if (Intrinsics.areEqual(this, MEDIUM)) {
            str = "MEDIUM";
        } else {
            str = Intrinsics.areEqual(this, EXPANDED) ? "EXPANDED" : "UNKNOWN";
        }
        return "WindowHeightSizeClass: ".concat(str);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type androidx.window.core.layout.WindowHeightSizeClass");
        return this.rawValue == ((WindowHeightSizeClass) other).rawValue;
    }

    /* compiled from: WindowHeightSizeClass.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0001¢\u0006\u0002\b\nR\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/window/core/layout/WindowHeightSizeClass$Companion;", "", "()V", "COMPACT", "Landroidx/window/core/layout/WindowHeightSizeClass;", "EXPANDED", "MEDIUM", "compute", "dpHeight", "", "compute$window_core_release", "window-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final WindowHeightSizeClass compute$window_core_release(float dpHeight) {
            if (dpHeight <= 0.0f) {
                throw new IllegalArgumentException(("Height must be positive, received " + dpHeight).toString());
            }
            if (dpHeight < 480.0f) {
                return WindowHeightSizeClass.COMPACT;
            }
            if (dpHeight < 900.0f) {
                return WindowHeightSizeClass.MEDIUM;
            }
            return WindowHeightSizeClass.EXPANDED;
        }
    }
}
