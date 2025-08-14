package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.grid.LazyGridSpanLayoutProvider;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffsetKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyGrid.kt */
@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u009e\u0001\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052#\u0010\u0006\u001a\u001f\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0007¢\u0006\u0002\b\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0017\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u001a¢\u0006\u0002\b\fH\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a\u001d\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010 \u001a\u0094\u0001\u0010!\u001a\u0019\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020#0\u0007¢\u0006\u0002\b\f2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0004\u001a\u00020\u00052#\u0010\u0006\u001a\u001f\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u0007¢\u0006\u0002\b\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010$\u001a\u00020%H\u0003ø\u0001\u0000¢\u0006\u0002\u0010&\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, d2 = {"LazyGrid", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "slotSizesSums", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/Constraints;", "", "", "Lkotlin/ExtensionFunctionType;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "isVertical", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/grid/LazyGridScope;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/grid/LazyGridState;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/gestures/FlingBehavior;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "ScrollPositionUpdater", "itemProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;Landroidx/compose/foundation/lazy/grid/LazyGridState;Landroidx/compose/runtime/Composer;I)V", "rememberLazyGridMeasurePolicy", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/layout/MeasureResult;", "placementAnimator", "Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;Landroidx/compose/foundation/lazy/grid/LazyGridState;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;Landroidx/compose/runtime/Composer;II)Lkotlin/jvm/functions/Function2;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyGridKt {
    /* JADX WARN: Removed duplicated region for block: B:107:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:172:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x013a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LazyGrid(androidx.compose.ui.Modifier r35, final androidx.compose.foundation.lazy.grid.LazyGridState r36, final kotlin.jvm.functions.Function2<? super androidx.compose.ui.unit.Density, ? super androidx.compose.ui.unit.Constraints, ? extends java.util.List<java.lang.Integer>> r37, androidx.compose.foundation.layout.PaddingValues r38, boolean r39, final boolean r40, androidx.compose.foundation.gestures.FlingBehavior r41, final boolean r42, final androidx.compose.foundation.layout.Arrangement.Vertical r43, final androidx.compose.foundation.layout.Arrangement.Horizontal r44, final kotlin.jvm.functions.Function1<? super androidx.compose.foundation.lazy.grid.LazyGridScope, kotlin.Unit> r45, androidx.compose.runtime.Composer r46, final int r47, final int r48, final int r49) {
        /*
            Method dump skipped, instructions count: 920
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.grid.LazyGridKt.LazyGrid(androidx.compose.ui.Modifier, androidx.compose.foundation.lazy.grid.LazyGridState, kotlin.jvm.functions.Function2, androidx.compose.foundation.layout.PaddingValues, boolean, boolean, androidx.compose.foundation.gestures.FlingBehavior, boolean, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ScrollPositionUpdater(final LazyGridItemProvider lazyGridItemProvider, final LazyGridState lazyGridState, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(950944068);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ScrollPositionUpdater):LazyGrid.kt#7791vq");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(lazyGridItemProvider) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(lazyGridState) ? 32 : 16;
        }
        if ((i2 & 91) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(950944068, i, -1, "androidx.compose.foundation.lazy.grid.ScrollPositionUpdater (LazyGrid.kt:139)");
            }
            if (lazyGridItemProvider.getItemCount() > 0) {
                lazyGridState.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(lazyGridItemProvider);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt.ScrollPositionUpdater.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i3) {
                LazyGridKt.ScrollPositionUpdater(lazyGridItemProvider, lazyGridState, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            }
        });
    }

    private static final Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> rememberLazyGridMeasurePolicy(final LazyGridItemProvider lazyGridItemProvider, final LazyGridState lazyGridState, final Function2<? super Density, ? super Constraints, ? extends List<Integer>> function2, final PaddingValues paddingValues, final boolean z, final boolean z2, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, final LazyGridItemPlacementAnimator lazyGridItemPlacementAnimator, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(237903564);
        ComposerKt.sourceInformation(composer, "C(rememberLazyGridMeasurePolicy)P(3,7,6!1,5,2!1,8)169@6864L8299:LazyGrid.kt#7791vq");
        final Arrangement.Horizontal horizontal2 = (i2 & 64) != 0 ? null : horizontal;
        Arrangement.Vertical vertical2 = (i2 & 128) == 0 ? vertical : null;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(237903564, i, -1, "androidx.compose.foundation.lazy.grid.rememberLazyGridMeasurePolicy (LazyGrid.kt:150)");
        }
        Object[] objArr = {lazyGridState, function2, paddingValues, Boolean.valueOf(z), Boolean.valueOf(z2), horizontal2, vertical2, lazyGridItemPlacementAnimator};
        composer.startReplaceableGroup(-568225417);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged = false;
        for (int i3 = 0; i3 < 8; i3++) {
            zChanged |= composer.changed(objArr[i3]);
        }
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final Arrangement.Vertical vertical3 = vertical2;
            objRememberedValue = (Function2) new Function2<LazyLayoutMeasureScope, Constraints, LazyGridMeasureResult>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyGridMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                    return m851invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                }

                /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyGridMeasureResult m851invoke0kLqBqw(final LazyLayoutMeasureScope lazyLayoutMeasureScope, final long j) {
                    int i4;
                    int i5;
                    float spacing;
                    float fM4390constructorimpl;
                    int iM4346getMaxWidthimpl;
                    long jIntOffset;
                    int firstVisibleItemScrollOffset;
                    int iM865getLineIndexOfItem_Ze7BM;
                    Intrinsics.checkNotNullParameter(lazyLayoutMeasureScope, "$this$null");
                    CheckScrollableContainerConstraintsKt.m447checkScrollableContainerConstraintsK40F9xA(j, z2 ? Orientation.Vertical : Orientation.Horizontal);
                    if (z2) {
                        i4 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(paddingValues.mo671calculateLeftPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection()));
                    } else {
                        i4 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(PaddingKt.calculateStartPadding(paddingValues, lazyLayoutMeasureScope.getLayoutDirection()));
                    }
                    if (z2) {
                        i5 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(paddingValues.mo672calculateRightPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection()));
                    } else {
                        i5 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(PaddingKt.calculateEndPadding(paddingValues, lazyLayoutMeasureScope.getLayoutDirection()));
                    }
                    int i6 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(paddingValues.getTop());
                    int i7 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(paddingValues.getBottom());
                    final int i8 = i6 + i7;
                    final int i9 = i4 + i5;
                    boolean z3 = z2;
                    int i10 = z3 ? i8 : i9;
                    int i11 = (!z3 || z) ? (z3 && z) ? i7 : (z3 || z) ? i5 : i4 : i6;
                    final int i12 = i10 - i11;
                    long jM4362offsetNN6EwU = ConstraintsKt.m4362offsetNN6EwU(j, -i9, -i8);
                    lazyGridState.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(lazyGridItemProvider);
                    final LazyGridSpanLayoutProvider spanLayoutProvider = lazyGridItemProvider.getSpanLayoutProvider();
                    final List<Integer> listInvoke = function2.invoke(lazyLayoutMeasureScope, Constraints.m4334boximpl(j));
                    spanLayoutProvider.setSlotsPerLine(listInvoke.size());
                    LazyLayoutMeasureScope lazyLayoutMeasureScope2 = lazyLayoutMeasureScope;
                    lazyGridState.setDensity$foundation_release(lazyLayoutMeasureScope2);
                    lazyGridState.setSlotsPerLine$foundation_release(listInvoke.size());
                    if (z2) {
                        Arrangement.Vertical vertical4 = vertical3;
                        if (vertical4 == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                        spacing = vertical4.getSpacing();
                    } else {
                        Arrangement.Horizontal horizontal3 = horizontal2;
                        if (horizontal3 != null) {
                            spacing = horizontal3.getSpacing();
                        } else {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                    }
                    int i13 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(spacing);
                    if (z2) {
                        Arrangement.Horizontal horizontal4 = horizontal2;
                        if (horizontal4 != null) {
                            fM4390constructorimpl = horizontal4.getSpacing();
                        } else {
                            fM4390constructorimpl = Dp.m4390constructorimpl(0);
                        }
                    } else {
                        Arrangement.Vertical vertical5 = vertical3;
                        if (vertical5 != null) {
                            fM4390constructorimpl = vertical5.getSpacing();
                        } else {
                            fM4390constructorimpl = Dp.m4390constructorimpl(0);
                        }
                    }
                    final int i14 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(fM4390constructorimpl);
                    int itemCount = lazyGridItemProvider.getItemCount();
                    if (z2) {
                        iM4346getMaxWidthimpl = Constraints.m4345getMaxHeightimpl(j) - i8;
                    } else {
                        iM4346getMaxWidthimpl = Constraints.m4346getMaxWidthimpl(j) - i9;
                    }
                    int i15 = iM4346getMaxWidthimpl;
                    if (!z || i15 > 0) {
                        jIntOffset = IntOffsetKt.IntOffset(i4, i6);
                    } else {
                        boolean z4 = z2;
                        if (!z4) {
                            i4 += i15;
                        }
                        if (z4) {
                            i6 += i15;
                        }
                        jIntOffset = IntOffsetKt.IntOffset(i4, i6);
                    }
                    final long j2 = jIntOffset;
                    LazyGridItemProvider lazyGridItemProvider2 = lazyGridItemProvider;
                    final boolean z5 = z2;
                    final boolean z6 = z;
                    final LazyGridItemPlacementAnimator lazyGridItemPlacementAnimator2 = lazyGridItemPlacementAnimator;
                    final int i16 = i11;
                    LazyMeasuredItemProvider lazyMeasuredItemProvider = new LazyMeasuredItemProvider(lazyGridItemProvider2, lazyLayoutMeasureScope, i13, new MeasuredItemFactory() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1
                        @Override // androidx.compose.foundation.lazy.grid.MeasuredItemFactory
                        /* renamed from: createItem-PU_OBEw, reason: not valid java name */
                        public final LazyGridMeasuredItem mo853createItemPU_OBEw(int i17, Object key, int i18, int i19, List<? extends Placeable> placeables) {
                            Intrinsics.checkNotNullParameter(key, "key");
                            Intrinsics.checkNotNullParameter(placeables, "placeables");
                            return new LazyGridMeasuredItem(i17, key, z5, i18, i19, z6, lazyLayoutMeasureScope.getLayoutDirection(), i16, i12, placeables, lazyGridItemPlacementAnimator2, j2, null);
                        }
                    });
                    boolean z7 = z2;
                    final boolean z8 = z2;
                    final LazyMeasuredLineProvider lazyMeasuredLineProvider = new LazyMeasuredLineProvider(z7, listInvoke, i14, itemCount, i13, lazyMeasuredItemProvider, spanLayoutProvider, new MeasuredLineFactory() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1
                        @Override // androidx.compose.foundation.lazy.grid.MeasuredLineFactory
                        /* renamed from: createLine-H9FfpSk, reason: not valid java name */
                        public final LazyGridMeasuredLine mo854createLineH9FfpSk(int i17, LazyGridMeasuredItem[] items, List<GridItemSpan> spans, int i18) {
                            Intrinsics.checkNotNullParameter(items, "items");
                            Intrinsics.checkNotNullParameter(spans, "spans");
                            return new LazyGridMeasuredLine(i17, items, spans, z8, listInvoke.size(), lazyLayoutMeasureScope.getLayoutDirection(), i18, i14, null);
                        }
                    });
                    lazyGridState.setPrefetchInfoRetriever$foundation_release(new Function1<LineIndex, ArrayList<Pair<? extends Integer, ? extends Constraints>>>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ ArrayList<Pair<? extends Integer, ? extends Constraints>> invoke(LineIndex lineIndex) {
                            return m852invokebKFJvoY(lineIndex.m884unboximpl());
                        }

                        /* renamed from: invoke-bKFJvoY, reason: not valid java name */
                        public final ArrayList<Pair<Integer, Constraints>> m852invokebKFJvoY(int i17) {
                            LazyGridSpanLayoutProvider.LineConfiguration lineConfiguration = spanLayoutProvider.getLineConfiguration(i17);
                            int iM826constructorimpl = ItemIndex.m826constructorimpl(lineConfiguration.getFirstItemIndex());
                            ArrayList<Pair<Integer, Constraints>> arrayList = new ArrayList<>(lineConfiguration.getSpans().size());
                            List<GridItemSpan> spans = lineConfiguration.getSpans();
                            LazyMeasuredLineProvider lazyMeasuredLineProvider2 = lazyMeasuredLineProvider;
                            int size = spans.size();
                            int i18 = 0;
                            for (int i19 = 0; i19 < size; i19++) {
                                int iM820getCurrentLineSpanimpl = GridItemSpan.m820getCurrentLineSpanimpl(spans.get(i19).getPackedValue());
                                arrayList.add(TuplesKt.to(Integer.valueOf(iM826constructorimpl), Constraints.m4334boximpl(lazyMeasuredLineProvider2.m869childConstraintsJhjzzOo$foundation_release(i18, iM820getCurrentLineSpanimpl))));
                                iM826constructorimpl = ItemIndex.m826constructorimpl(iM826constructorimpl + 1);
                                i18 += iM820getCurrentLineSpanimpl;
                            }
                            return arrayList;
                        }
                    });
                    Snapshot.Companion companion = Snapshot.INSTANCE;
                    LazyGridState lazyGridState2 = lazyGridState;
                    Snapshot snapshotCreateNonObservableSnapshot = companion.createNonObservableSnapshot();
                    try {
                        Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
                        try {
                            if (lazyGridState2.getFirstVisibleItemIndex() < itemCount || itemCount <= 0) {
                                int iM865getLineIndexOfItem_Ze7BM2 = spanLayoutProvider.m865getLineIndexOfItem_Ze7BM(lazyGridState2.getFirstVisibleItemIndex());
                                firstVisibleItemScrollOffset = lazyGridState2.getFirstVisibleItemScrollOffset();
                                iM865getLineIndexOfItem_Ze7BM = iM865getLineIndexOfItem_Ze7BM2;
                            } else {
                                iM865getLineIndexOfItem_Ze7BM = spanLayoutProvider.m865getLineIndexOfItem_Ze7BM(itemCount - 1);
                                firstVisibleItemScrollOffset = 0;
                            }
                            Unit unit = Unit.INSTANCE;
                            snapshotCreateNonObservableSnapshot.dispose();
                            LazyGridMeasureResult lazyGridMeasureResultM855measureLazyGridt5wl_D8 = LazyGridMeasureKt.m855measureLazyGridt5wl_D8(itemCount, lazyGridItemProvider, lazyMeasuredLineProvider, lazyMeasuredItemProvider, i15, i11, i12, i13, iM865getLineIndexOfItem_Ze7BM, firstVisibleItemScrollOffset, lazyGridState.getScrollToBeConsumed(), jM4362offsetNN6EwU, z2, vertical3, horizontal2, z, lazyLayoutMeasureScope2, lazyGridItemPlacementAnimator, spanLayoutProvider, lazyGridState.getPinnedItems(), new Function3<Integer, Integer, Function1<? super Placeable.PlacementScope, ? extends Unit>, MeasureResult>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridKt$rememberLazyGridMeasurePolicy$1$1.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ MeasureResult invoke(Integer num, Integer num2, Function1<? super Placeable.PlacementScope, ? extends Unit> function1) {
                                    return invoke(num.intValue(), num2.intValue(), (Function1<? super Placeable.PlacementScope, Unit>) function1);
                                }

                                public final MeasureResult invoke(int i17, int i18, Function1<? super Placeable.PlacementScope, Unit> placement) {
                                    Intrinsics.checkNotNullParameter(placement, "placement");
                                    return lazyLayoutMeasureScope.layout(ConstraintsKt.m4360constrainWidthK40F9xA(j, i17 + i9), ConstraintsKt.m4359constrainHeightK40F9xA(j, i18 + i8), MapsKt.emptyMap(), placement);
                                }
                            });
                            lazyGridState.applyMeasureResult$foundation_release(lazyGridMeasureResultM855measureLazyGridt5wl_D8);
                            return lazyGridMeasureResultM855measureLazyGridt5wl_D8;
                        } finally {
                            snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                        }
                    } catch (Throwable th) {
                        snapshotCreateNonObservableSnapshot.dispose();
                        throw th;
                    }
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function22 = (Function2) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return function22;
    }
}
