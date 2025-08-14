package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: Padding.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002BQ\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0018\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0096\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0016J)\u0010\u001d\u001a\u00020\u001e*\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b$\u0010%R\u001c\u0010\u0007\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0006\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0016\u0010\u0011R\u001c\u0010\u0005\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0017\u0010\u0011\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006&"}, d2 = {"Landroidx/compose/foundation/layout/PaddingModifier;", "Landroidx/compose/ui/layout/LayoutModifier;", "Landroidx/compose/ui/platform/InspectorValueInfo;", ViewProps.START, "Landroidx/compose/ui/unit/Dp;", "top", ViewProps.END, ViewProps.BOTTOM, "rtlAware", "", "inspectorInfo", "Lkotlin/Function1;", "Landroidx/compose/ui/platform/InspectorInfo;", "", "Lkotlin/ExtensionFunctionType;", "(FFFFZLkotlin/jvm/functions/Function1;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getBottom-D9Ej5fM", "()F", "F", "getEnd-D9Ej5fM", "getRtlAware", "()Z", "getStart-D9Ej5fM", "getTop-D9Ej5fM", "equals", "other", "", "hashCode", "", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class PaddingModifier extends InspectorValueInfo implements LayoutModifier {
    private final float bottom;
    private final float end;
    private final boolean rtlAware;
    private final float start;
    private final float top;

    public /* synthetic */ PaddingModifier(float f, float f2, float f3, float f4, boolean z, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, z, function1);
    }

    /* renamed from: getBottom-D9Ej5fM, reason: not valid java name and from getter */
    public final float getBottom() {
        return this.bottom;
    }

    /* renamed from: getEnd-D9Ej5fM, reason: not valid java name and from getter */
    public final float getEnd() {
        return this.end;
    }

    public final boolean getRtlAware() {
        return this.rtlAware;
    }

    /* renamed from: getStart-D9Ej5fM, reason: not valid java name and from getter */
    public final float getStart() {
        return this.start;
    }

    /* renamed from: getTop-D9Ej5fM, reason: not valid java name and from getter */
    public final float getTop() {
        return this.top;
    }

    private PaddingModifier(float f, float f2, float f3, float f4, boolean z, Function1<? super InspectorInfo, Unit> function1) {
        super(function1);
        this.start = f;
        this.top = f2;
        this.end = f3;
        this.bottom = f4;
        this.rtlAware = z;
        if ((f < 0.0f && !Dp.m4395equalsimpl0(f, Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM())) || ((f2 < 0.0f && !Dp.m4395equalsimpl0(f2, Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM())) || ((f3 < 0.0f && !Dp.m4395equalsimpl0(f3, Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM())) || (f4 < 0.0f && !Dp.m4395equalsimpl0(f4, Dp.INSTANCE.m4410getUnspecifiedD9Ej5fM()))))) {
            throw new IllegalArgumentException("Padding must be non-negative".toString());
        }
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo300measure3p2s80s(final MeasureScope measure, Measurable measurable, long j) {
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        int i = measure.mo571roundToPx0680j_4(this.start) + measure.mo571roundToPx0680j_4(this.end);
        int i2 = measure.mo571roundToPx0680j_4(this.top) + measure.mo571roundToPx0680j_4(this.bottom);
        final Placeable placeableMo3396measureBRTryo0 = measurable.mo3396measureBRTryo0(ConstraintsKt.m4362offsetNN6EwU(j, -i, -i2));
        return MeasureScope.layout$default(measure, ConstraintsKt.m4360constrainWidthK40F9xA(j, placeableMo3396measureBRTryo0.getWidth() + i), ConstraintsKt.m4359constrainHeightK40F9xA(j, placeableMo3396measureBRTryo0.getHeight() + i2), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.layout.PaddingModifier$measure$1
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
                if (this.this$0.getRtlAware()) {
                    Placeable.PlacementScope.placeRelative$default(layout, placeableMo3396measureBRTryo0, measure.mo571roundToPx0680j_4(this.this$0.getStart()), measure.mo571roundToPx0680j_4(this.this$0.getTop()), 0.0f, 4, null);
                } else {
                    Placeable.PlacementScope.place$default(layout, placeableMo3396measureBRTryo0, measure.mo571roundToPx0680j_4(this.this$0.getStart()), measure.mo571roundToPx0680j_4(this.this$0.getTop()), 0.0f, 4, null);
                }
            }
        }, 4, null);
    }

    public int hashCode() {
        return (((((((Dp.m4396hashCodeimpl(this.start) * 31) + Dp.m4396hashCodeimpl(this.top)) * 31) + Dp.m4396hashCodeimpl(this.end)) * 31) + Dp.m4396hashCodeimpl(this.bottom)) * 31) + Boolean.hashCode(this.rtlAware);
    }

    public boolean equals(Object other) {
        PaddingModifier paddingModifier = other instanceof PaddingModifier ? (PaddingModifier) other : null;
        return paddingModifier != null && Dp.m4395equalsimpl0(this.start, paddingModifier.start) && Dp.m4395equalsimpl0(this.top, paddingModifier.top) && Dp.m4395equalsimpl0(this.end, paddingModifier.end) && Dp.m4395equalsimpl0(this.bottom, paddingModifier.bottom) && this.rtlAware == paddingModifier.rtlAware;
    }

    public /* synthetic */ PaddingModifier(float f, float f2, float f3, float f4, boolean z, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Dp.m4390constructorimpl(0) : f, (i & 2) != 0 ? Dp.m4390constructorimpl(0) : f2, (i & 4) != 0 ? Dp.m4390constructorimpl(0) : f3, (i & 8) != 0 ? Dp.m4390constructorimpl(0) : f4, z, function1, null);
    }
}
