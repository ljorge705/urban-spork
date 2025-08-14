package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: Size.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002BQ\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0016\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u001c\u0010\u001b\u001a\u00020\u001a*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001aH\u0016J\u001c\u0010 \u001a\u00020\u001a*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001aH\u0016J)\u0010\"\u001a\u00020#*\u00020$2\u0006\u0010\u001d\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0012H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u001c\u0010)\u001a\u00020\u001a*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001aH\u0016J\u001c\u0010*\u001a\u00020\u001a*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001aH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0007\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0010R\u0019\u0010\u0006\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0010R\u0019\u0010\u0005\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0010R\u0019\u0010\u0003\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0010R!\u0010\u0011\u001a\u00020\u0012*\u00020\u00138BX\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006+"}, d2 = {"Landroidx/compose/foundation/layout/SizeModifier;", "Landroidx/compose/ui/layout/LayoutModifier;", "Landroidx/compose/ui/platform/InspectorValueInfo;", ViewProps.MIN_WIDTH, "Landroidx/compose/ui/unit/Dp;", ViewProps.MIN_HEIGHT, ViewProps.MAX_WIDTH, ViewProps.MAX_HEIGHT, "enforceIncoming", "", "inspectorInfo", "Lkotlin/Function1;", "Landroidx/compose/ui/platform/InspectorInfo;", "", "Lkotlin/ExtensionFunctionType;", "(FFFFZLkotlin/jvm/functions/Function1;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "targetConstraints", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/unit/Density;", "getTargetConstraints-OenEA2s", "(Landroidx/compose/ui/unit/Density;)J", "equals", "other", "", "hashCode", "", "maxIntrinsicHeight", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", CryptoServicesPermission.CONSTRAINTS, "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class SizeModifier extends InspectorValueInfo implements LayoutModifier {
    private final boolean enforceIncoming;
    private final float maxHeight;
    private final float maxWidth;
    private final float minHeight;
    private final float minWidth;

    public /* synthetic */ SizeModifier(float f, float f2, float f3, float f4, boolean z, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, z, function1);
    }

    public /* synthetic */ SizeModifier(float f, float f2, float f3, float f4, boolean z, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM() : f, (i & 2) != 0 ? Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM() : f2, (i & 4) != 0 ? Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM() : f3, (i & 8) != 0 ? Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM() : f4, z, function1, null);
    }

    private SizeModifier(float f, float f2, float f3, float f4, boolean z, Function1<? super InspectorInfo, Unit> function1) {
        super(function1);
        this.minWidth = f;
        this.minHeight = f2;
        this.maxWidth = f3;
        this.maxHeight = f4;
        this.enforceIncoming = z;
    }

    /* renamed from: getTargetConstraints-OenEA2s, reason: not valid java name */
    private final long m744getTargetConstraintsOenEA2s(Density density) {
        int iCoerceAtLeast;
        int iCoerceAtLeast2;
        int i = 0;
        int iMo571roundToPx0680j_4 = !Dp.m4395equalsimpl0(this.maxWidth, Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM()) ? density.mo571roundToPx0680j_4(((Dp) RangesKt.coerceAtLeast(Dp.m4388boximpl(this.maxWidth), Dp.m4388boximpl(Dp.m4390constructorimpl(0)))).m4404unboximpl()) : Integer.MAX_VALUE;
        int iMo571roundToPx0680j_42 = !Dp.m4395equalsimpl0(this.maxHeight, Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM()) ? density.mo571roundToPx0680j_4(((Dp) RangesKt.coerceAtLeast(Dp.m4388boximpl(this.maxHeight), Dp.m4388boximpl(Dp.m4390constructorimpl(0)))).m4404unboximpl()) : Integer.MAX_VALUE;
        if (Dp.m4395equalsimpl0(this.minWidth, Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM()) || (iCoerceAtLeast = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(density.mo571roundToPx0680j_4(this.minWidth), iMo571roundToPx0680j_4), 0)) == Integer.MAX_VALUE) {
            iCoerceAtLeast = 0;
        }
        if (!Dp.m4395equalsimpl0(this.minHeight, Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM()) && (iCoerceAtLeast2 = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(density.mo571roundToPx0680j_4(this.minHeight), iMo571roundToPx0680j_42), 0)) != Integer.MAX_VALUE) {
            i = iCoerceAtLeast2;
        }
        return ConstraintsKt.Constraints(iCoerceAtLeast, iMo571roundToPx0680j_4, i, iMo571roundToPx0680j_42);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo300measure3p2s80s(MeasureScope measure, Measurable measurable, long j) {
        int iCoerceAtMost;
        int iCoerceAtLeast;
        int iCoerceAtMost2;
        int iCoerceAtLeast2;
        long jConstraints;
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        long jM744getTargetConstraintsOenEA2s = m744getTargetConstraintsOenEA2s(measure);
        if (this.enforceIncoming) {
            jConstraints = ConstraintsKt.m4358constrainN9IONVI(j, jM744getTargetConstraintsOenEA2s);
        } else {
            if (!Dp.m4395equalsimpl0(this.minWidth, Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM())) {
                iCoerceAtMost = Constraints.m4348getMinWidthimpl(jM744getTargetConstraintsOenEA2s);
            } else {
                iCoerceAtMost = RangesKt.coerceAtMost(Constraints.m4348getMinWidthimpl(j), Constraints.m4346getMaxWidthimpl(jM744getTargetConstraintsOenEA2s));
            }
            if (!Dp.m4395equalsimpl0(this.maxWidth, Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM())) {
                iCoerceAtLeast = Constraints.m4346getMaxWidthimpl(jM744getTargetConstraintsOenEA2s);
            } else {
                iCoerceAtLeast = RangesKt.coerceAtLeast(Constraints.m4346getMaxWidthimpl(j), Constraints.m4348getMinWidthimpl(jM744getTargetConstraintsOenEA2s));
            }
            if (!Dp.m4395equalsimpl0(this.minHeight, Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM())) {
                iCoerceAtMost2 = Constraints.m4347getMinHeightimpl(jM744getTargetConstraintsOenEA2s);
            } else {
                iCoerceAtMost2 = RangesKt.coerceAtMost(Constraints.m4347getMinHeightimpl(j), Constraints.m4345getMaxHeightimpl(jM744getTargetConstraintsOenEA2s));
            }
            if (!Dp.m4395equalsimpl0(this.maxHeight, Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM())) {
                iCoerceAtLeast2 = Constraints.m4345getMaxHeightimpl(jM744getTargetConstraintsOenEA2s);
            } else {
                iCoerceAtLeast2 = RangesKt.coerceAtLeast(Constraints.m4345getMaxHeightimpl(j), Constraints.m4347getMinHeightimpl(jM744getTargetConstraintsOenEA2s));
            }
            jConstraints = ConstraintsKt.Constraints(iCoerceAtMost, iCoerceAtLeast, iCoerceAtMost2, iCoerceAtLeast2);
        }
        final Placeable placeableMo3396measureBRTryo0 = measurable.mo3396measureBRTryo0(jConstraints);
        return MeasureScope.layout$default(measure, placeableMo3396measureBRTryo0.getWidth(), placeableMo3396measureBRTryo0.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.layout.SizeModifier$measure$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope layout) {
                Intrinsics.checkNotNullParameter(layout, "$this$layout");
                Placeable.PlacementScope.placeRelative$default(layout, placeableMo3396measureBRTryo0, 0, 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        long jM744getTargetConstraintsOenEA2s = m744getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m4344getHasFixedWidthimpl(jM744getTargetConstraintsOenEA2s)) {
            return Constraints.m4346getMaxWidthimpl(jM744getTargetConstraintsOenEA2s);
        }
        return ConstraintsKt.m4360constrainWidthK40F9xA(jM744getTargetConstraintsOenEA2s, measurable.minIntrinsicWidth(i));
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        long jM744getTargetConstraintsOenEA2s = m744getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m4343getHasFixedHeightimpl(jM744getTargetConstraintsOenEA2s)) {
            return Constraints.m4345getMaxHeightimpl(jM744getTargetConstraintsOenEA2s);
        }
        return ConstraintsKt.m4359constrainHeightK40F9xA(jM744getTargetConstraintsOenEA2s, measurable.minIntrinsicHeight(i));
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        long jM744getTargetConstraintsOenEA2s = m744getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m4344getHasFixedWidthimpl(jM744getTargetConstraintsOenEA2s)) {
            return Constraints.m4346getMaxWidthimpl(jM744getTargetConstraintsOenEA2s);
        }
        return ConstraintsKt.m4360constrainWidthK40F9xA(jM744getTargetConstraintsOenEA2s, measurable.maxIntrinsicWidth(i));
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        long jM744getTargetConstraintsOenEA2s = m744getTargetConstraintsOenEA2s(intrinsicMeasureScope);
        if (Constraints.m4343getHasFixedHeightimpl(jM744getTargetConstraintsOenEA2s)) {
            return Constraints.m4345getMaxHeightimpl(jM744getTargetConstraintsOenEA2s);
        }
        return ConstraintsKt.m4359constrainHeightK40F9xA(jM744getTargetConstraintsOenEA2s, measurable.maxIntrinsicHeight(i));
    }

    public boolean equals(Object other) {
        if (!(other instanceof SizeModifier)) {
            return false;
        }
        SizeModifier sizeModifier = (SizeModifier) other;
        return Dp.m4395equalsimpl0(this.minWidth, sizeModifier.minWidth) && Dp.m4395equalsimpl0(this.minHeight, sizeModifier.minHeight) && Dp.m4395equalsimpl0(this.maxWidth, sizeModifier.maxWidth) && Dp.m4395equalsimpl0(this.maxHeight, sizeModifier.maxHeight) && this.enforceIncoming == sizeModifier.enforceIncoming;
    }

    public int hashCode() {
        return ((((((Dp.m4396hashCodeimpl(this.minWidth) * 31) + Dp.m4396hashCodeimpl(this.minHeight)) * 31) + Dp.m4396hashCodeimpl(this.maxWidth)) * 31) + Dp.m4396hashCodeimpl(this.maxHeight)) * 31;
    }
}
