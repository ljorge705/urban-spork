package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.ClipScrollableContainerKt;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.OverscrollKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyStaggeredGrid.kt */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u009e\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u001d\u0010\u0006\u001a\u0019\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\u0002\b\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u001a¢\u0006\u0002\b\u000bH\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a\u001d\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0003¢\u0006\u0002\u0010 \u0082\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"LazyStaggeredGrid", "", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "slotSizesSums", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/Constraints;", "", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScope;", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Landroidx/compose/foundation/gestures/Orientation;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/FlingBehavior;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "ScrollPositionUpdater", "itemProvider", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Landroidx/compose/runtime/Composer;I)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyStaggeredGridKt {
    public static final void LazyStaggeredGrid(final LazyStaggeredGridState state, final Orientation orientation, final Function2<? super Density, ? super Constraints, int[]> slotSizesSums, Modifier modifier, PaddingValues paddingValues, boolean z, FlingBehavior flingBehavior, boolean z2, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, final Function1<? super LazyStaggeredGridScope, Unit> content, Composer composer, final int i, final int i2, final int i3) {
        FlingBehavior flingBehavior2;
        int i4;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        Intrinsics.checkNotNullParameter(slotSizesSums, "slotSizesSums");
        Intrinsics.checkNotNullParameter(content, "content");
        Composer composerStartRestartGroup = composer.startRestartGroup(845690866);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(LazyStaggeredGrid)P(8,5,7,4,1,6,2,9,10,3)53@2453L15,63@2948L18,65@2991L49,66@3065L228,76@3318L60,78@3384L42,88@3792L7,98@4157L265,80@3432L1117:LazyStaggeredGrid.kt#fzvcnm");
        Modifier modifier2 = (i3 & 8) != 0 ? Modifier.INSTANCE : modifier;
        PaddingValues paddingValuesM683PaddingValues0680j_4 = (i3 & 16) != 0 ? PaddingKt.m683PaddingValues0680j_4(Dp.m4390constructorimpl(0)) : paddingValues;
        boolean z3 = (i3 & 32) != 0 ? false : z;
        if ((i3 & 64) != 0) {
            flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior(composerStartRestartGroup, 6);
            i4 = i & (-3670017);
        } else {
            flingBehavior2 = flingBehavior;
            i4 = i;
        }
        boolean z4 = (i3 & 128) != 0 ? true : z2;
        Arrangement.Vertical top = (i3 & 256) != 0 ? Arrangement.INSTANCE.getTop() : vertical;
        Arrangement.Horizontal start = (i3 & 512) != 0 ? Arrangement.INSTANCE.getStart() : horizontal;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(845690866, i4, i2, "androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGrid (LazyStaggeredGrid.kt:39)");
        }
        OverscrollEffect overscrollEffect = ScrollableDefaults.INSTANCE.overscrollEffect(composerStartRestartGroup, 6);
        LazyStaggeredGridItemProvider lazyStaggeredGridItemProviderRememberStaggeredGridItemProvider = LazyStaggeredGridItemProviderKt.rememberStaggeredGridItemProvider(state, content, composerStartRestartGroup, ((i2 << 3) & 112) | 8);
        int i5 = i4 >> 6;
        int i6 = i4 >> 9;
        int i7 = i4;
        final boolean z5 = z3;
        final Modifier modifier3 = modifier2;
        Function2<LazyLayoutMeasureScope, Constraints, LazyStaggeredGridMeasureResult> function2RememberStaggeredGridMeasurePolicy = LazyStaggeredGridMeasurePolicyKt.rememberStaggeredGridMeasurePolicy(state, lazyStaggeredGridItemProviderRememberStaggeredGridItemProvider, paddingValuesM683PaddingValues0680j_4, z3, orientation, top, start, slotSizesSums, composerStartRestartGroup, (i5 & 7168) | (i5 & 896) | 8 | ((i4 << 9) & 57344) | (i6 & 458752) | (i6 & 3670016) | ((i4 << 15) & 29360128));
        LazyLayoutSemanticState lazyLayoutSemanticStateRememberLazyStaggeredGridSemanticState = LazyStaggeredGridSemanticsKt.rememberLazyStaggeredGridSemanticState(state, z5, composerStartRestartGroup, ((i7 >> 12) & 112) | 8);
        LazyStaggeredGridItemProvider lazyStaggeredGridItemProvider = lazyStaggeredGridItemProviderRememberStaggeredGridItemProvider;
        ScrollPositionUpdater(lazyStaggeredGridItemProvider, state, composerStartRestartGroup, 64);
        Modifier modifierOverscroll = OverscrollKt.overscroll(ClipScrollableContainerKt.clipScrollableContainer(modifier3.then(state.getRemeasurementModifier()), orientation), overscrollEffect);
        ScrollableDefaults scrollableDefaults = ScrollableDefaults.INSTANCE;
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composerStartRestartGroup.consume(localLayoutDirection);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        boolean z6 = z4;
        LazyLayoutKt.LazyLayout(lazyStaggeredGridItemProvider, LazyLayoutSemanticsKt.lazyLayoutSemantics(ScrollableKt.scrollable(modifierOverscroll, state, orientation, overscrollEffect, z6, scrollableDefaults.reverseDirection((LayoutDirection) objConsume, orientation, z5), flingBehavior2, state.getMutableInteractionSource()), lazyStaggeredGridItemProvider, lazyLayoutSemanticStateRememberLazyStaggeredGridSemanticState, orientation, z6, z5, composerStartRestartGroup, ((i7 << 6) & 7168) | (i6 & 57344) | (i7 & 458752)), state.getPrefetchState(), function2RememberStaggeredGridMeasurePolicy, composerStartRestartGroup, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final PaddingValues paddingValues2 = paddingValuesM683PaddingValues0680j_4;
        final FlingBehavior flingBehavior3 = flingBehavior2;
        final boolean z7 = z4;
        final Arrangement.Vertical vertical2 = top;
        final Arrangement.Horizontal horizontal2 = start;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt.LazyStaggeredGrid.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i8) {
                LazyStaggeredGridKt.LazyStaggeredGrid(state, orientation, slotSizesSums, modifier3, paddingValues2, z5, flingBehavior3, z7, vertical2, horizontal2, content, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ScrollPositionUpdater(final LazyLayoutItemProvider lazyLayoutItemProvider, final LazyStaggeredGridState lazyStaggeredGridState, Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(231106410);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ScrollPositionUpdater):LazyStaggeredGrid.kt#fzvcnm");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(231106410, i, -1, "androidx.compose.foundation.lazy.staggeredgrid.ScrollPositionUpdater (LazyStaggeredGrid.kt:114)");
        }
        if (lazyLayoutItemProvider.getItemCount() > 0) {
            lazyStaggeredGridState.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(lazyLayoutItemProvider);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridKt.ScrollPositionUpdater.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i2) {
                LazyStaggeredGridKt.ScrollPositionUpdater(lazyLayoutItemProvider, lazyStaggeredGridState, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            }
        });
    }
}
