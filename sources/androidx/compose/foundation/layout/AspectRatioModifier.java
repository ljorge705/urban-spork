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
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: AspectRatio.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B.\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000b¢\u0006\u0002\u0010\fJ\u0013\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0019\u0010\u0018\u001a\u00020\u0019*\u00020\u001aH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u001c\u0010\u001d\u001a\u00020\u0015*\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0015H\u0016J\u001c\u0010\"\u001a\u00020\u0015*\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010#\u001a\u00020\u0015H\u0016J)\u0010$\u001a\u00020%*\u00020&2\u0006\u0010\u001f\u001a\u00020'2\u0006\u0010(\u001a\u00020\u001aH\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010*J\u001c\u0010+\u001a\u00020\u0015*\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0015H\u0016J\u001c\u0010,\u001a\u00020\u0015*\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010#\u001a\u00020\u0015H\u0016J#\u0010-\u001a\u00020\u0019*\u00020\u001a2\b\b\u0002\u0010.\u001a\u00020\u0006H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b/\u00100J#\u00101\u001a\u00020\u0019*\u00020\u001a2\b\b\u0002\u0010.\u001a\u00020\u0006H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b2\u00100J#\u00103\u001a\u00020\u0019*\u00020\u001a2\b\b\u0002\u0010.\u001a\u00020\u0006H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b4\u00100J#\u00105\u001a\u00020\u0019*\u00020\u001a2\b\b\u0002\u0010.\u001a\u00020\u0006H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b6\u00100R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00067"}, d2 = {"Landroidx/compose/foundation/layout/AspectRatioModifier;", "Landroidx/compose/ui/layout/LayoutModifier;", "Landroidx/compose/ui/platform/InspectorValueInfo;", ViewProps.ASPECT_RATIO, "", "matchHeightConstraintsFirst", "", "inspectorInfo", "Lkotlin/Function1;", "Landroidx/compose/ui/platform/InspectorInfo;", "", "Lkotlin/ExtensionFunctionType;", "(FZLkotlin/jvm/functions/Function1;)V", "getAspectRatio", "()F", "getMatchHeightConstraintsFirst", "()Z", "equals", "other", "", "hashCode", "", "toString", "", "findSize", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/ui/unit/Constraints;", "findSize-ToXhtMw", "(J)J", "maxIntrinsicHeight", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", CryptoServicesPermission.CONSTRAINTS, "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "tryMaxHeight", "enforceConstraints", "tryMaxHeight-JN-0ABg", "(JZ)J", "tryMaxWidth", "tryMaxWidth-JN-0ABg", "tryMinHeight", "tryMinHeight-JN-0ABg", "tryMinWidth", "tryMinWidth-JN-0ABg", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class AspectRatioModifier extends InspectorValueInfo implements LayoutModifier {
    private final float aspectRatio;
    private final boolean matchHeightConstraintsFirst;

    public final float getAspectRatio() {
        return this.aspectRatio;
    }

    public final boolean getMatchHeightConstraintsFirst() {
        return this.matchHeightConstraintsFirst;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AspectRatioModifier(float f, boolean z, Function1<? super InspectorInfo, Unit> inspectorInfo) {
        super(inspectorInfo);
        Intrinsics.checkNotNullParameter(inspectorInfo, "inspectorInfo");
        this.aspectRatio = f;
        this.matchHeightConstraintsFirst = z;
        if (f <= 0.0f) {
            throw new IllegalArgumentException(("aspectRatio " + f + " must be > 0").toString());
        }
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo300measure3p2s80s(MeasureScope measure, Measurable measurable, long j) {
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        long jM651findSizeToXhtMw = m651findSizeToXhtMw(j);
        if (!IntSize.m4548equalsimpl0(jM651findSizeToXhtMw, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
            j = Constraints.INSTANCE.m4354fixedJhjzzOo(IntSize.m4550getWidthimpl(jM651findSizeToXhtMw), IntSize.m4549getHeightimpl(jM651findSizeToXhtMw));
        }
        final Placeable placeableMo3396measureBRTryo0 = measurable.mo3396measureBRTryo0(j);
        return MeasureScope.layout$default(measure, placeableMo3396measureBRTryo0.getWidth(), placeableMo3396measureBRTryo0.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.layout.AspectRatioModifier$measure$1
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
        if (i != Integer.MAX_VALUE) {
            return MathKt.roundToInt(i * this.aspectRatio);
        }
        return measurable.minIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        if (i != Integer.MAX_VALUE) {
            return MathKt.roundToInt(i * this.aspectRatio);
        }
        return measurable.maxIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        if (i != Integer.MAX_VALUE) {
            return MathKt.roundToInt(i / this.aspectRatio);
        }
        return measurable.minIntrinsicHeight(i);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        if (i != Integer.MAX_VALUE) {
            return MathKt.roundToInt(i / this.aspectRatio);
        }
        return measurable.maxIntrinsicHeight(i);
    }

    /* renamed from: findSize-ToXhtMw, reason: not valid java name */
    private final long m651findSizeToXhtMw(long j) {
        if (!this.matchHeightConstraintsFirst) {
            long jM655tryMaxWidthJN0ABg$default = m655tryMaxWidthJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m4548equalsimpl0(jM655tryMaxWidthJN0ABg$default, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM655tryMaxWidthJN0ABg$default;
            }
            long jM653tryMaxHeightJN0ABg$default = m653tryMaxHeightJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m4548equalsimpl0(jM653tryMaxHeightJN0ABg$default, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM653tryMaxHeightJN0ABg$default;
            }
            long jM659tryMinWidthJN0ABg$default = m659tryMinWidthJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m4548equalsimpl0(jM659tryMinWidthJN0ABg$default, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM659tryMinWidthJN0ABg$default;
            }
            long jM657tryMinHeightJN0ABg$default = m657tryMinHeightJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m4548equalsimpl0(jM657tryMinHeightJN0ABg$default, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM657tryMinHeightJN0ABg$default;
            }
            long jM654tryMaxWidthJN0ABg = m654tryMaxWidthJN0ABg(j, false);
            if (!IntSize.m4548equalsimpl0(jM654tryMaxWidthJN0ABg, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM654tryMaxWidthJN0ABg;
            }
            long jM652tryMaxHeightJN0ABg = m652tryMaxHeightJN0ABg(j, false);
            if (!IntSize.m4548equalsimpl0(jM652tryMaxHeightJN0ABg, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM652tryMaxHeightJN0ABg;
            }
            long jM658tryMinWidthJN0ABg = m658tryMinWidthJN0ABg(j, false);
            if (!IntSize.m4548equalsimpl0(jM658tryMinWidthJN0ABg, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM658tryMinWidthJN0ABg;
            }
            long jM656tryMinHeightJN0ABg = m656tryMinHeightJN0ABg(j, false);
            if (!IntSize.m4548equalsimpl0(jM656tryMinHeightJN0ABg, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM656tryMinHeightJN0ABg;
            }
        } else {
            long jM653tryMaxHeightJN0ABg$default2 = m653tryMaxHeightJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m4548equalsimpl0(jM653tryMaxHeightJN0ABg$default2, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM653tryMaxHeightJN0ABg$default2;
            }
            long jM655tryMaxWidthJN0ABg$default2 = m655tryMaxWidthJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m4548equalsimpl0(jM655tryMaxWidthJN0ABg$default2, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM655tryMaxWidthJN0ABg$default2;
            }
            long jM657tryMinHeightJN0ABg$default2 = m657tryMinHeightJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m4548equalsimpl0(jM657tryMinHeightJN0ABg$default2, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM657tryMinHeightJN0ABg$default2;
            }
            long jM659tryMinWidthJN0ABg$default2 = m659tryMinWidthJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m4548equalsimpl0(jM659tryMinWidthJN0ABg$default2, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM659tryMinWidthJN0ABg$default2;
            }
            long jM652tryMaxHeightJN0ABg2 = m652tryMaxHeightJN0ABg(j, false);
            if (!IntSize.m4548equalsimpl0(jM652tryMaxHeightJN0ABg2, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM652tryMaxHeightJN0ABg2;
            }
            long jM654tryMaxWidthJN0ABg2 = m654tryMaxWidthJN0ABg(j, false);
            if (!IntSize.m4548equalsimpl0(jM654tryMaxWidthJN0ABg2, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM654tryMaxWidthJN0ABg2;
            }
            long jM656tryMinHeightJN0ABg2 = m656tryMinHeightJN0ABg(j, false);
            if (!IntSize.m4548equalsimpl0(jM656tryMinHeightJN0ABg2, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM656tryMinHeightJN0ABg2;
            }
            long jM658tryMinWidthJN0ABg2 = m658tryMinWidthJN0ABg(j, false);
            if (!IntSize.m4548equalsimpl0(jM658tryMinWidthJN0ABg2, IntSize.INSTANCE.m4555getZeroYbymL2g())) {
                return jM658tryMinWidthJN0ABg2;
            }
        }
        return IntSize.INSTANCE.m4555getZeroYbymL2g();
    }

    /* renamed from: tryMaxWidth-JN-0ABg$default, reason: not valid java name */
    static /* synthetic */ long m655tryMaxWidthJN0ABg$default(AspectRatioModifier aspectRatioModifier, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return aspectRatioModifier.m654tryMaxWidthJN0ABg(j, z);
    }

    /* renamed from: tryMaxWidth-JN-0ABg, reason: not valid java name */
    private final long m654tryMaxWidthJN0ABg(long j, boolean z) {
        int iRoundToInt;
        int iM4346getMaxWidthimpl = Constraints.m4346getMaxWidthimpl(j);
        if (iM4346getMaxWidthimpl != Integer.MAX_VALUE && (iRoundToInt = MathKt.roundToInt(iM4346getMaxWidthimpl / this.aspectRatio)) > 0) {
            long jIntSize = IntSizeKt.IntSize(iM4346getMaxWidthimpl, iRoundToInt);
            if (!z || ConstraintsKt.m4361isSatisfiedBy4WqzIAM(j, jIntSize)) {
                return jIntSize;
            }
        }
        return IntSize.INSTANCE.m4555getZeroYbymL2g();
    }

    /* renamed from: tryMaxHeight-JN-0ABg$default, reason: not valid java name */
    static /* synthetic */ long m653tryMaxHeightJN0ABg$default(AspectRatioModifier aspectRatioModifier, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return aspectRatioModifier.m652tryMaxHeightJN0ABg(j, z);
    }

    /* renamed from: tryMaxHeight-JN-0ABg, reason: not valid java name */
    private final long m652tryMaxHeightJN0ABg(long j, boolean z) {
        int iRoundToInt;
        int iM4345getMaxHeightimpl = Constraints.m4345getMaxHeightimpl(j);
        if (iM4345getMaxHeightimpl != Integer.MAX_VALUE && (iRoundToInt = MathKt.roundToInt(iM4345getMaxHeightimpl * this.aspectRatio)) > 0) {
            long jIntSize = IntSizeKt.IntSize(iRoundToInt, iM4345getMaxHeightimpl);
            if (!z || ConstraintsKt.m4361isSatisfiedBy4WqzIAM(j, jIntSize)) {
                return jIntSize;
            }
        }
        return IntSize.INSTANCE.m4555getZeroYbymL2g();
    }

    /* renamed from: tryMinWidth-JN-0ABg$default, reason: not valid java name */
    static /* synthetic */ long m659tryMinWidthJN0ABg$default(AspectRatioModifier aspectRatioModifier, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return aspectRatioModifier.m658tryMinWidthJN0ABg(j, z);
    }

    /* renamed from: tryMinWidth-JN-0ABg, reason: not valid java name */
    private final long m658tryMinWidthJN0ABg(long j, boolean z) {
        int iM4348getMinWidthimpl = Constraints.m4348getMinWidthimpl(j);
        int iRoundToInt = MathKt.roundToInt(iM4348getMinWidthimpl / this.aspectRatio);
        if (iRoundToInt > 0) {
            long jIntSize = IntSizeKt.IntSize(iM4348getMinWidthimpl, iRoundToInt);
            if (!z || ConstraintsKt.m4361isSatisfiedBy4WqzIAM(j, jIntSize)) {
                return jIntSize;
            }
        }
        return IntSize.INSTANCE.m4555getZeroYbymL2g();
    }

    /* renamed from: tryMinHeight-JN-0ABg$default, reason: not valid java name */
    static /* synthetic */ long m657tryMinHeightJN0ABg$default(AspectRatioModifier aspectRatioModifier, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return aspectRatioModifier.m656tryMinHeightJN0ABg(j, z);
    }

    /* renamed from: tryMinHeight-JN-0ABg, reason: not valid java name */
    private final long m656tryMinHeightJN0ABg(long j, boolean z) {
        int iM4347getMinHeightimpl = Constraints.m4347getMinHeightimpl(j);
        int iRoundToInt = MathKt.roundToInt(iM4347getMinHeightimpl * this.aspectRatio);
        if (iRoundToInt > 0) {
            long jIntSize = IntSizeKt.IntSize(iRoundToInt, iM4347getMinHeightimpl);
            if (!z || ConstraintsKt.m4361isSatisfiedBy4WqzIAM(j, jIntSize)) {
                return jIntSize;
            }
        }
        return IntSize.INSTANCE.m4555getZeroYbymL2g();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        AspectRatioModifier aspectRatioModifier = other instanceof AspectRatioModifier ? (AspectRatioModifier) other : null;
        if (aspectRatioModifier == null) {
            return false;
        }
        return this.aspectRatio == aspectRatioModifier.aspectRatio && this.matchHeightConstraintsFirst == ((AspectRatioModifier) other).matchHeightConstraintsFirst;
    }

    public int hashCode() {
        return (Float.hashCode(this.aspectRatio) * 31) + Boolean.hashCode(this.matchHeightConstraintsFirst);
    }

    public String toString() {
        return "AspectRatioModifier(aspectRatio=" + this.aspectRatio + ')';
    }
}
