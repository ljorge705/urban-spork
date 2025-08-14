package androidx.compose.foundation.pager;

import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.lazy.LazyListItemInfo;
import androidx.compose.foundation.lazy.LazyListLayoutInfo;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: PagerState.kt */
@Metadata(d1 = {"\u0000_\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0003\b\u000b\u001d\u001a\u0017\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0082\b\u001a!\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020\u00102\b\b\u0002\u0010'\u001a\u00020\u000eH\u0007¢\u0006\u0002\u0010(\u001a\u0015\u0010)\u001a\u00020 *\u00020%H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010*\u001a\u0015\u0010+\u001a\u00020 *\u00020%H\u0080@ø\u0001\u0000¢\u0006\u0002\u0010*\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0019\u0010\u0002\u001a\u00020\u0003X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t\"\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f\"\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0010X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000\"O\u0010\u0012\u001a=\u0012\u0004\u0012\u00020\u0014\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u000e0\u0013¢\u0006\u0002\b\u0019X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0010\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"DEBUG", "", "DefaultPositionThreshold", "Landroidx/compose/ui/unit/Dp;", "getDefaultPositionThreshold", "()F", "F", "EmptyInteractionSources", "androidx/compose/foundation/pager/PagerStateKt$EmptyInteractionSources$1", "Landroidx/compose/foundation/pager/PagerStateKt$EmptyInteractionSources$1;", "EmptyLayoutInfo", "androidx/compose/foundation/pager/PagerStateKt$EmptyLayoutInfo$1", "Landroidx/compose/foundation/pager/PagerStateKt$EmptyLayoutInfo$1;", "MaxPageOffset", "", "MaxPagesForAnimateScroll", "", "MinPageOffset", "SnapAlignmentStartToStart", "Lkotlin/Function3;", "Landroidx/compose/ui/unit/Density;", "Lkotlin/ParameterName;", "name", "layoutSize", "itemSize", "Lkotlin/ExtensionFunctionType;", "getSnapAlignmentStartToStart", "()Lkotlin/jvm/functions/Function3;", "UnitDensity", "androidx/compose/foundation/pager/PagerStateKt$UnitDensity$1", "Landroidx/compose/foundation/pager/PagerStateKt$UnitDensity$1;", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "rememberPagerState", "Landroidx/compose/foundation/pager/PagerState;", "initialPage", "initialPageOffsetFraction", "(IFLandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/pager/PagerState;", "animateToNextPage", "(Landroidx/compose/foundation/pager/PagerState;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateToPreviousPage", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PagerStateKt {
    private static final boolean DEBUG = false;
    private static final float MaxPageOffset = 0.5f;
    private static final int MaxPagesForAnimateScroll = 3;
    private static final float MinPageOffset = -0.5f;
    private static final Function3<Density, Float, Float, Float> SnapAlignmentStartToStart = new Function3<Density, Float, Float, Float>() { // from class: androidx.compose.foundation.pager.PagerStateKt$SnapAlignmentStartToStart$1
        public final Float invoke(Density density, float f, float f2) {
            Intrinsics.checkNotNullParameter(density, "$this$null");
            return Float.valueOf(0.0f);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Float invoke(Density density, Float f, Float f2) {
            return invoke(density, f.floatValue(), f2.floatValue());
        }
    };
    private static final float DefaultPositionThreshold = Dp.m4390constructorimpl(56);
    private static final PagerStateKt$EmptyLayoutInfo$1 EmptyLayoutInfo = new LazyListLayoutInfo() { // from class: androidx.compose.foundation.pager.PagerStateKt$EmptyLayoutInfo$1
        private final int mainAxisItemSpacing;
        private final int totalItemsCount;
        private final int viewportEndOffset;
        private final int viewportStartOffset;
        private final List<LazyListItemInfo> visibleItemsInfo = CollectionsKt.emptyList();

        @Override // androidx.compose.foundation.lazy.LazyListLayoutInfo
        public int getMainAxisItemSpacing() {
            return this.mainAxisItemSpacing;
        }

        @Override // androidx.compose.foundation.lazy.LazyListLayoutInfo
        public int getTotalItemsCount() {
            return this.totalItemsCount;
        }

        @Override // androidx.compose.foundation.lazy.LazyListLayoutInfo
        public int getViewportEndOffset() {
            return this.viewportEndOffset;
        }

        @Override // androidx.compose.foundation.lazy.LazyListLayoutInfo
        public int getViewportStartOffset() {
            return this.viewportStartOffset;
        }

        @Override // androidx.compose.foundation.lazy.LazyListLayoutInfo
        public List<LazyListItemInfo> getVisibleItemsInfo() {
            return this.visibleItemsInfo;
        }
    };
    private static final PagerStateKt$UnitDensity$1 UnitDensity = new Density() { // from class: androidx.compose.foundation.pager.PagerStateKt$UnitDensity$1
        private final float density = 1.0f;
        private final float fontScale = 1.0f;

        @Override // androidx.compose.ui.unit.Density
        public float getDensity() {
            return this.density;
        }

        @Override // androidx.compose.ui.unit.Density
        public float getFontScale() {
            return this.fontScale;
        }
    };
    private static final PagerStateKt$EmptyInteractionSources$1 EmptyInteractionSources = new InteractionSource() { // from class: androidx.compose.foundation.pager.PagerStateKt$EmptyInteractionSources$1
        @Override // androidx.compose.foundation.interaction.InteractionSource
        public Flow<Interaction> getInteractions() {
            return FlowKt.emptyFlow();
        }
    };

    private static final void debugLog(Function0<String> function0) {
    }

    public static final float getDefaultPositionThreshold() {
        return DefaultPositionThreshold;
    }

    public static final Function3<Density, Float, Float, Float> getSnapAlignmentStartToStart() {
        return SnapAlignmentStartToStart;
    }

    public static final PagerState rememberPagerState(final int i, final float f, Composer composer, int i2, int i3) {
        composer.startReplaceableGroup(144687223);
        ComposerKt.sourceInformation(composer, "C(rememberPagerState)70@2945L108,70@2902L151:PagerState.kt#g6yjnt");
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            f = 0.0f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(144687223, i2, -1, "androidx.compose.foundation.pager.rememberPagerState (PagerState.kt:66)");
        }
        Object[] objArr = new Object[0];
        Saver<PagerState, ?> saver = PagerState.INSTANCE.getSaver();
        Integer numValueOf = Integer.valueOf(i);
        Float fValueOf = Float.valueOf(f);
        composer.startReplaceableGroup(511388516);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1,2):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(numValueOf) | composer.changed(fValueOf);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (Function0) new Function0<PagerState>() { // from class: androidx.compose.foundation.pager.PagerStateKt$rememberPagerState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final PagerState invoke() {
                    return new PagerState(i, f);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        PagerState pagerState = (PagerState) RememberSaveableKt.m1531rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) objRememberedValue, composer, 72, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return pagerState;
    }

    public static final Object animateToNextPage(PagerState pagerState, Continuation<? super Unit> continuation) throws Throwable {
        if (pagerState.getCurrentPage() + 1 >= pagerState.getPageCount$foundation_release()) {
            return Unit.INSTANCE;
        }
        Object objAnimateScrollToPage$default = PagerState.animateScrollToPage$default(pagerState, pagerState.getCurrentPage() + 1, 0.0f, null, continuation, 6, null);
        return objAnimateScrollToPage$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateScrollToPage$default : Unit.INSTANCE;
    }

    public static final Object animateToPreviousPage(PagerState pagerState, Continuation<? super Unit> continuation) throws Throwable {
        if (pagerState.getCurrentPage() - 1 < 0) {
            return Unit.INSTANCE;
        }
        Object objAnimateScrollToPage$default = PagerState.animateScrollToPage$default(pagerState, pagerState.getCurrentPage() - 1, 0.0f, null, continuation, 6, null);
        return objAnimateScrollToPage$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAnimateScrollToPage$default : Unit.INSTANCE;
    }
}
