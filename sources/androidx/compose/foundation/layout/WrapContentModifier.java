package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: Size.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002BS\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\u0012ø\u0001\u0000¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\rH\u0096\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J)\u0010\u0018\u001a\u00020\u0019*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"Landroidx/compose/foundation/layout/WrapContentModifier;", "Landroidx/compose/ui/layout/LayoutModifier;", "Landroidx/compose/ui/platform/InspectorValueInfo;", "direction", "Landroidx/compose/foundation/layout/Direction;", "unbounded", "", "alignmentCallback", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/ui/unit/LayoutDirection;", "Landroidx/compose/ui/unit/IntOffset;", "align", "", "inspectorInfo", "Lkotlin/Function1;", "Landroidx/compose/ui/platform/InspectorInfo;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/layout/Direction;ZLkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "equals", "other", "hashCode", "", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class WrapContentModifier extends InspectorValueInfo implements LayoutModifier {
    private final Object align;
    private final Function2<IntSize, LayoutDirection, IntOffset> alignmentCallback;
    private final Direction direction;
    private final boolean unbounded;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public WrapContentModifier(Direction direction, boolean z, Function2<? super IntSize, ? super LayoutDirection, IntOffset> alignmentCallback, Object align, Function1<? super InspectorInfo, Unit> inspectorInfo) {
        super(inspectorInfo);
        Intrinsics.checkNotNullParameter(direction, "direction");
        Intrinsics.checkNotNullParameter(alignmentCallback, "alignmentCallback");
        Intrinsics.checkNotNullParameter(align, "align");
        Intrinsics.checkNotNullParameter(inspectorInfo, "inspectorInfo");
        this.direction = direction;
        this.unbounded = z;
        this.alignmentCallback = alignmentCallback;
        this.align = align;
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo300measure3p2s80s(final MeasureScope measure, Measurable measurable, long j) {
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        final Placeable placeableMo3396measureBRTryo0 = measurable.mo3396measureBRTryo0(ConstraintsKt.Constraints(this.direction != Direction.Vertical ? 0 : Constraints.m4348getMinWidthimpl(j), (this.direction == Direction.Vertical || !this.unbounded) ? Constraints.m4346getMaxWidthimpl(j) : Integer.MAX_VALUE, this.direction == Direction.Horizontal ? Constraints.m4347getMinHeightimpl(j) : 0, (this.direction == Direction.Horizontal || !this.unbounded) ? Constraints.m4345getMaxHeightimpl(j) : Integer.MAX_VALUE));
        final int iCoerceIn = RangesKt.coerceIn(placeableMo3396measureBRTryo0.getWidth(), Constraints.m4348getMinWidthimpl(j), Constraints.m4346getMaxWidthimpl(j));
        final int iCoerceIn2 = RangesKt.coerceIn(placeableMo3396measureBRTryo0.getHeight(), Constraints.m4347getMinHeightimpl(j), Constraints.m4345getMaxHeightimpl(j));
        return MeasureScope.layout$default(measure, iCoerceIn, iCoerceIn2, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.layout.WrapContentModifier$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                Placeable.PlacementScope.m3444place70tqf50$default(layout, placeableMo3396measureBRTryo0, ((IntOffset) this.this$0.alignmentCallback.invoke(IntSize.m4542boximpl(IntSizeKt.IntSize(iCoerceIn - placeableMo3396measureBRTryo0.getWidth(), iCoerceIn2 - placeableMo3396measureBRTryo0.getHeight())), measure.getLayoutDirection())).getPackedValue(), 0.0f, 2, null);
            }
        }, 4, null);
    }

    public boolean equals(Object other) {
        if (!(other instanceof WrapContentModifier)) {
            return false;
        }
        WrapContentModifier wrapContentModifier = (WrapContentModifier) other;
        return this.direction == wrapContentModifier.direction && this.unbounded == wrapContentModifier.unbounded && Intrinsics.areEqual(this.align, wrapContentModifier.align);
    }

    public int hashCode() {
        return (((this.direction.hashCode() * 31) + Boolean.hashCode(this.unbounded)) * 31) + this.align.hashCode();
    }
}
