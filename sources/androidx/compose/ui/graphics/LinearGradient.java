package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import com.BV.LinearGradient.LinearGradientManager;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Brush.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001BD\b\u0000\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000bø\u0001\u0000¢\u0006\u0002\u0010\fJ!\u0010\u0013\u001a\u00060\u0014j\u0002`\u00152\u0006\u0010\u0016\u001a\u00020\u000fH\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0096\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020 H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\u00020\bX\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\rR\u001d\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0007\u001a\u00020\bX\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\rR\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\n\u001a\u00020\u000bX\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0012\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006!"}, d2 = {"Landroidx/compose/ui/graphics/LinearGradient;", "Landroidx/compose/ui/graphics/ShaderBrush;", LinearGradientManager.PROP_COLORS, "", "Landroidx/compose/ui/graphics/Color;", "stops", "", ViewProps.START, "Landroidx/compose/ui/geometry/Offset;", ViewProps.END, "tileMode", "Landroidx/compose/ui/graphics/TileMode;", "(Ljava/util/List;Ljava/util/List;JJILkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "intrinsicSize", "Landroidx/compose/ui/geometry/Size;", "getIntrinsicSize-NH-jbRc", "()J", "I", "createShader", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", RRWebVideoEvent.JsonKeys.SIZE, "createShader-uvyYCjk", "(J)Landroid/graphics/Shader;", "equals", "", "other", "", "hashCode", "", "toString", "", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LinearGradient extends ShaderBrush {
    private final List<Color> colors;
    private final long end;
    private final long start;
    private final List<Float> stops;
    private final int tileMode;

    public /* synthetic */ LinearGradient(List list, List list2, long j, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2, j, j2, i);
    }

    public /* synthetic */ LinearGradient(List list, List list2, long j, long j2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i2 & 2) != 0 ? null : list2, j, j2, (i2 & 16) != 0 ? TileMode.INSTANCE.m2240getClamp3opZhB0() : i, null);
    }

    private LinearGradient(List<Color> list, List<Float> list2, long j, long j2, int i) {
        this.colors = list;
        this.stops = list2;
        this.start = j;
        this.end = j2;
        this.tileMode = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0038  */
    @Override // androidx.compose.ui.graphics.Brush
    /* renamed from: getIntrinsicSize-NH-jbRc */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long getIntrinsicSize() {
        /*
            r5 = this;
            long r0 = r5.start
            float r0 = androidx.compose.ui.geometry.Offset.m1639getXimpl(r0)
            boolean r1 = java.lang.Float.isInfinite(r0)
            r2 = 2143289344(0x7fc00000, float:NaN)
            if (r1 != 0) goto L38
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L38
            long r0 = r5.end
            float r0 = androidx.compose.ui.geometry.Offset.m1639getXimpl(r0)
            boolean r1 = java.lang.Float.isInfinite(r0)
            if (r1 != 0) goto L38
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L38
            long r0 = r5.start
            float r0 = androidx.compose.ui.geometry.Offset.m1639getXimpl(r0)
            long r3 = r5.end
            float r1 = androidx.compose.ui.geometry.Offset.m1639getXimpl(r3)
            float r0 = r0 - r1
            float r0 = java.lang.Math.abs(r0)
            goto L39
        L38:
            r0 = r2
        L39:
            long r3 = r5.start
            float r1 = androidx.compose.ui.geometry.Offset.m1640getYimpl(r3)
            boolean r3 = java.lang.Float.isInfinite(r1)
            if (r3 != 0) goto L6e
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L6e
            long r3 = r5.end
            float r1 = androidx.compose.ui.geometry.Offset.m1640getYimpl(r3)
            boolean r3 = java.lang.Float.isInfinite(r1)
            if (r3 != 0) goto L6e
            boolean r1 = java.lang.Float.isNaN(r1)
            if (r1 != 0) goto L6e
            long r1 = r5.start
            float r1 = androidx.compose.ui.geometry.Offset.m1640getYimpl(r1)
            long r2 = r5.end
            float r2 = androidx.compose.ui.geometry.Offset.m1640getYimpl(r2)
            float r1 = r1 - r2
            float r2 = java.lang.Math.abs(r1)
        L6e:
            long r0 = androidx.compose.ui.geometry.SizeKt.Size(r0, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.LinearGradient.getIntrinsicSize():long");
    }

    @Override // androidx.compose.ui.graphics.ShaderBrush
    /* renamed from: createShader-uvyYCjk */
    public Shader mo1846createShaderuvyYCjk(long size) {
        return ShaderKt.m2182LinearGradientShaderVjE6UOU(OffsetKt.Offset(Offset.m1639getXimpl(this.start) == Float.POSITIVE_INFINITY ? Size.m1708getWidthimpl(size) : Offset.m1639getXimpl(this.start), Offset.m1640getYimpl(this.start) == Float.POSITIVE_INFINITY ? Size.m1705getHeightimpl(size) : Offset.m1640getYimpl(this.start)), OffsetKt.Offset(Offset.m1639getXimpl(this.end) == Float.POSITIVE_INFINITY ? Size.m1708getWidthimpl(size) : Offset.m1639getXimpl(this.end), Offset.m1640getYimpl(this.end) == Float.POSITIVE_INFINITY ? Size.m1705getHeightimpl(size) : Offset.m1640getYimpl(this.end)), this.colors, this.stops, this.tileMode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LinearGradient)) {
            return false;
        }
        LinearGradient linearGradient = (LinearGradient) other;
        return Intrinsics.areEqual(this.colors, linearGradient.colors) && Intrinsics.areEqual(this.stops, linearGradient.stops) && Offset.m1636equalsimpl0(this.start, linearGradient.start) && Offset.m1636equalsimpl0(this.end, linearGradient.end) && TileMode.m2236equalsimpl0(this.tileMode, linearGradient.tileMode);
    }

    public int hashCode() {
        int iHashCode = this.colors.hashCode() * 31;
        List<Float> list = this.stops;
        return ((((((iHashCode + (list != null ? list.hashCode() : 0)) * 31) + Offset.m1641hashCodeimpl(this.start)) * 31) + Offset.m1641hashCodeimpl(this.end)) * 31) + TileMode.m2237hashCodeimpl(this.tileMode);
    }

    public String toString() {
        return "LinearGradient(colors=" + this.colors + ", stops=" + this.stops + ", " + (OffsetKt.m1656isFinitek4lQ0M(this.start) ? "start=" + ((Object) Offset.m1647toStringimpl(this.start)) + ", " : "") + (OffsetKt.m1656isFinitek4lQ0M(this.end) ? "end=" + ((Object) Offset.m1647toStringimpl(this.end)) + ", " : "") + "tileMode=" + ((Object) TileMode.m2238toStringimpl(this.tileMode)) + ')';
    }
}
