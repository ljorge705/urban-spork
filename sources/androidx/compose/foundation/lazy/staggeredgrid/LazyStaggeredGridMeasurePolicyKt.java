package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyStaggeredGridMeasurePolicy.kt */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a~\u0010\u0000\u001a\u0019\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0001¢\u0006\u0002\b\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u001d\u0010\u0014\u001a\u0019\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00160\u0001¢\u0006\u0002\b\u0005H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a,\u0010\u0018\u001a\u00020\u0019*\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a,\u0010\u001d\u001a\u00020\u0019*\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a$\u0010\u001e\u001a\u00020\u0019*\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u001f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"rememberStaggeredGridMeasurePolicy", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "Lkotlin/ExtensionFunctionType;", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "itemProvider", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "slotSizesSums", "Landroidx/compose/ui/unit/Density;", "", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "afterPadding", "Landroidx/compose/ui/unit/Dp;", ViewProps.LAYOUT_DIRECTION, "Landroidx/compose/ui/unit/LayoutDirection;", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/Orientation;ZLandroidx/compose/ui/unit/LayoutDirection;)F", "beforePadding", "startPadding", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/unit/LayoutDirection;)F", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyStaggeredGridMeasurePolicyKt {

    /* compiled from: LazyStaggeredGridMeasurePolicy.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Orientation.values().length];
            try {
                iArr[Orientation.Vertical.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Orientation.Horizontal.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> rememberStaggeredGridMeasurePolicy(final LazyStaggeredGridState state, final LazyStaggeredGridItemProvider itemProvider, final PaddingValues contentPadding, final boolean z, final Orientation orientation, final Arrangement.Vertical verticalArrangement, final Arrangement.Horizontal horizontalArrangement, final Function2<? super Density, ? super Constraints, int[]> slotSizesSums, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(itemProvider, "itemProvider");
        Intrinsics.checkNotNullParameter(contentPadding, "contentPadding");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        Intrinsics.checkNotNullParameter(verticalArrangement, "verticalArrangement");
        Intrinsics.checkNotNullParameter(horizontalArrangement, "horizontalArrangement");
        Intrinsics.checkNotNullParameter(slotSizesSums, "slotSizesSums");
        composer.startReplaceableGroup(-1929457971);
        ComposerKt.sourceInformation(composer, "C(rememberStaggeredGridMeasurePolicy)P(6,2!1,4,3,7)47@2048L2831:LazyStaggeredGridMeasurePolicy.kt#fzvcnm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1929457971, i, -1, "androidx.compose.foundation.lazy.staggeredgrid.rememberStaggeredGridMeasurePolicy (LazyStaggeredGridMeasurePolicy.kt:38)");
        }
        Object[] objArr = {state, itemProvider, contentPadding, Boolean.valueOf(z), orientation, verticalArrangement, horizontalArrangement, slotSizesSums};
        composer.startReplaceableGroup(-568225417);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged = false;
        for (int i2 = 0; i2 < 8; i2++) {
            zChanged |= composer.changed(objArr[i2]);
        }
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (Function2) new Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasurePolicyKt$rememberStaggeredGridMeasurePolicy$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyStaggeredGridMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                    return m910invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                }

                /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyStaggeredGridMeasureResult m910invoke0kLqBqw(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j) {
                    long jIntOffset;
                    float spacing;
                    float spacing2;
                    Intrinsics.checkNotNullParameter(lazyLayoutMeasureScope, "$this$null");
                    CheckScrollableContainerConstraintsKt.m447checkScrollableContainerConstraintsK40F9xA(j, orientation);
                    int[] iArrInvoke = slotSizesSums.invoke(lazyLayoutMeasureScope, Constraints.m4334boximpl(j));
                    boolean z2 = orientation == Orientation.Vertical;
                    state.setLaneWidthsPrefixSum$foundation_release(iArrInvoke);
                    state.setVertical$foundation_release(z2);
                    state.setSpanProvider$foundation_release(itemProvider.getSpanProvider());
                    int i3 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.beforePadding(contentPadding, orientation, z, lazyLayoutMeasureScope.getLayoutDirection()));
                    int i4 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.afterPadding(contentPadding, orientation, z, lazyLayoutMeasureScope.getLayoutDirection()));
                    int i5 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(LazyStaggeredGridMeasurePolicyKt.startPadding(contentPadding, orientation, lazyLayoutMeasureScope.getLayoutDirection()));
                    int iM4345getMaxHeightimpl = ((z2 ? Constraints.m4345getMaxHeightimpl(j) : Constraints.m4346getMaxWidthimpl(j)) - i3) - i4;
                    if (z2) {
                        jIntOffset = IntOffsetKt.IntOffset(i5, i3);
                    } else {
                        jIntOffset = IntOffsetKt.IntOffset(i3, i5);
                    }
                    long j2 = jIntOffset;
                    if (z2) {
                        spacing = verticalArrangement.getSpacing();
                    } else {
                        spacing = horizontalArrangement.getSpacing();
                    }
                    int i6 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(spacing);
                    if (z2) {
                        spacing2 = horizontalArrangement.getSpacing();
                    } else {
                        spacing2 = verticalArrangement.getSpacing();
                    }
                    int i7 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(spacing2);
                    PaddingValues paddingValues = contentPadding;
                    int i8 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(Dp.m4390constructorimpl(PaddingKt.calculateStartPadding(paddingValues, lazyLayoutMeasureScope.getLayoutDirection()) + PaddingKt.calculateEndPadding(paddingValues, lazyLayoutMeasureScope.getLayoutDirection())));
                    PaddingValues paddingValues2 = contentPadding;
                    LazyStaggeredGridMeasureResult lazyStaggeredGridMeasureResultM909measureStaggeredGridyR9pz_M = LazyStaggeredGridMeasureKt.m909measureStaggeredGridyR9pz_M(lazyLayoutMeasureScope, state, itemProvider, iArrInvoke, Constraints.m4337copyZbe2FdA$default(j, ConstraintsKt.m4360constrainWidthK40F9xA(j, i8), 0, ConstraintsKt.m4359constrainHeightK40F9xA(j, lazyLayoutMeasureScope.mo571roundToPx0680j_4(Dp.m4390constructorimpl(paddingValues2.getTop() + paddingValues2.getBottom()))), 0, 10, null), z2, j2, iM4345getMaxHeightimpl, i6, i7, i3, i4);
                    state.applyMeasureResult$foundation_release(lazyStaggeredGridMeasureResultM909measureStaggeredGridyR9pz_M);
                    return lazyStaggeredGridMeasureResultM909measureStaggeredGridyR9pz_M;
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2 = (Function2) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return function2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float startPadding(PaddingValues paddingValues, Orientation orientation, LayoutDirection layoutDirection) {
        int i = WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()];
        if (i == 1) {
            return PaddingKt.calculateStartPadding(paddingValues, layoutDirection);
        }
        if (i == 2) {
            return paddingValues.getTop();
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float beforePadding(PaddingValues paddingValues, Orientation orientation, boolean z, LayoutDirection layoutDirection) {
        int i = WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()];
        if (i == 1) {
            return z ? paddingValues.getBottom() : paddingValues.getTop();
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        if (z) {
            return PaddingKt.calculateEndPadding(paddingValues, layoutDirection);
        }
        return PaddingKt.calculateStartPadding(paddingValues, layoutDirection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float afterPadding(PaddingValues paddingValues, Orientation orientation, boolean z, LayoutDirection layoutDirection) {
        int i = WhenMappings.$EnumSwitchMapping$0[orientation.ordinal()];
        if (i == 1) {
            return z ? paddingValues.getTop() : paddingValues.getBottom();
        }
        if (i != 2) {
            throw new NoWhenBranchMatchedException();
        }
        if (z) {
            return PaddingKt.calculateStartPadding(paddingValues, layoutDirection);
        }
        return PaddingKt.calculateEndPadding(paddingValues, layoutDirection);
    }
}
