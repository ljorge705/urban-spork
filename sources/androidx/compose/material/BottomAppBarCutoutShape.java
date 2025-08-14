package androidx.compose.material;

import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathOperation;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.rrweb.RRWebVideoEvent;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppBar.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\t\u0010\n\u001a\u00020\u0001HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0004HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J-\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\u001c\u0010\u001f\u001a\u00020 *\u00020!2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J4\u0010\"\u001a\u00020 *\u00020!2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u0010'\u001a\u00020$2\u0006\u0010(\u001a\u00020$H\u0002R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006)"}, d2 = {"Landroidx/compose/material/BottomAppBarCutoutShape;", "Landroidx/compose/ui/graphics/Shape;", "cutoutShape", "fabPlacement", "Landroidx/compose/material/FabPlacement;", "(Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/FabPlacement;)V", "getCutoutShape", "()Landroidx/compose/ui/graphics/Shape;", "getFabPlacement", "()Landroidx/compose/material/FabPlacement;", "component1", "component2", Constants.COPY_TYPE, "createOutline", "Landroidx/compose/ui/graphics/Outline;", RRWebVideoEvent.JsonKeys.SIZE, "Landroidx/compose/ui/geometry/Size;", ViewProps.LAYOUT_DIRECTION, "Landroidx/compose/ui/unit/LayoutDirection;", "density", "Landroidx/compose/ui/unit/Density;", "createOutline-Pq9zytI", "(JLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;)Landroidx/compose/ui/graphics/Outline;", "equals", "", "other", "", "hashCode", "", "toString", "", "addCutoutShape", "", "Landroidx/compose/ui/graphics/Path;", "addRoundedEdges", "cutoutStartPosition", "", "cutoutEndPosition", "cutoutRadius", "roundedEdgeRadius", "verticalOffset", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final /* data */ class BottomAppBarCutoutShape implements Shape {
    private final Shape cutoutShape;
    private final FabPlacement fabPlacement;

    public static /* synthetic */ BottomAppBarCutoutShape copy$default(BottomAppBarCutoutShape bottomAppBarCutoutShape, Shape shape, FabPlacement fabPlacement, int i, Object obj) {
        if ((i & 1) != 0) {
            shape = bottomAppBarCutoutShape.cutoutShape;
        }
        if ((i & 2) != 0) {
            fabPlacement = bottomAppBarCutoutShape.fabPlacement;
        }
        return bottomAppBarCutoutShape.copy(shape, fabPlacement);
    }

    /* renamed from: component1, reason: from getter */
    public final Shape getCutoutShape() {
        return this.cutoutShape;
    }

    /* renamed from: component2, reason: from getter */
    public final FabPlacement getFabPlacement() {
        return this.fabPlacement;
    }

    public final BottomAppBarCutoutShape copy(Shape cutoutShape, FabPlacement fabPlacement) {
        Intrinsics.checkNotNullParameter(cutoutShape, "cutoutShape");
        Intrinsics.checkNotNullParameter(fabPlacement, "fabPlacement");
        return new BottomAppBarCutoutShape(cutoutShape, fabPlacement);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BottomAppBarCutoutShape)) {
            return false;
        }
        BottomAppBarCutoutShape bottomAppBarCutoutShape = (BottomAppBarCutoutShape) other;
        return Intrinsics.areEqual(this.cutoutShape, bottomAppBarCutoutShape.cutoutShape) && Intrinsics.areEqual(this.fabPlacement, bottomAppBarCutoutShape.fabPlacement);
    }

    public final Shape getCutoutShape() {
        return this.cutoutShape;
    }

    public final FabPlacement getFabPlacement() {
        return this.fabPlacement;
    }

    public int hashCode() {
        return (this.cutoutShape.hashCode() * 31) + this.fabPlacement.hashCode();
    }

    public String toString() {
        return "BottomAppBarCutoutShape(cutoutShape=" + this.cutoutShape + ", fabPlacement=" + this.fabPlacement + ')';
    }

    public BottomAppBarCutoutShape(Shape cutoutShape, FabPlacement fabPlacement) {
        Intrinsics.checkNotNullParameter(cutoutShape, "cutoutShape");
        Intrinsics.checkNotNullParameter(fabPlacement, "fabPlacement");
        this.cutoutShape = cutoutShape;
        this.fabPlacement = fabPlacement;
    }

    @Override // androidx.compose.ui.graphics.Shape
    /* renamed from: createOutline-Pq9zytI */
    public Outline mo469createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density) {
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        Intrinsics.checkNotNullParameter(density, "density");
        Path Path = AndroidPath_androidKt.Path();
        Path.addRect(new Rect(0.0f, 0.0f, Size.m1708getWidthimpl(size), Size.m1705getHeightimpl(size)));
        Path Path2 = AndroidPath_androidKt.Path();
        addCutoutShape(Path2, layoutDirection, density);
        Path2.mo1772opN5in7k0(Path, Path2, PathOperation.INSTANCE.m2159getDifferenceb3I0S0c());
        return new Outline.Generic(Path2);
    }

    private final void addCutoutShape(Path path, LayoutDirection layoutDirection, Density density) {
        float fMo577toPx0680j_4 = density.mo577toPx0680j_4(AppBarKt.BottomAppBarCutoutOffset);
        float f = 2 * fMo577toPx0680j_4;
        long jSize = SizeKt.Size(this.fabPlacement.getWidth() + f, this.fabPlacement.getHeight() + f);
        float left = this.fabPlacement.getLeft() - fMo577toPx0680j_4;
        float fM1708getWidthimpl = left + Size.m1708getWidthimpl(jSize);
        float fM1705getHeightimpl = Size.m1705getHeightimpl(jSize) / 2.0f;
        OutlineKt.addOutline(path, this.cutoutShape.mo469createOutlinePq9zytI(jSize, layoutDirection, density));
        path.mo1774translatek4lQ0M(OffsetKt.Offset(left, -fM1705getHeightimpl));
        if (Intrinsics.areEqual(this.cutoutShape, RoundedCornerShapeKt.getCircleShape())) {
            addRoundedEdges(path, left, fM1708getWidthimpl, fM1705getHeightimpl, density.mo577toPx0680j_4(AppBarKt.BottomAppBarRoundedEdgeRadius), 0.0f);
        }
    }

    private final void addRoundedEdges(Path path, float f, float f2, float f3, float f4, float f5) {
        float f6 = -((float) Math.sqrt((f3 * f3) - (f5 * f5)));
        float f7 = f3 + f6;
        float f8 = f + f7;
        float f9 = f2 - f7;
        Pair<Float, Float> pairCalculateRoundedEdgeIntercept = AppBarKt.calculateRoundedEdgeIntercept(f6 - 1.0f, f5, f3);
        float fFloatValue = pairCalculateRoundedEdgeIntercept.component1().floatValue() + f3;
        float fFloatValue2 = pairCalculateRoundedEdgeIntercept.component2().floatValue() - f5;
        path.moveTo(f8 - f4, 0.0f);
        path.quadraticBezierTo(f8 - 1.0f, 0.0f, f + fFloatValue, fFloatValue2);
        path.lineTo(f2 - fFloatValue, fFloatValue2);
        path.quadraticBezierTo(f9 + 1.0f, 0.0f, f4 + f9, 0.0f);
        path.close();
    }
}
