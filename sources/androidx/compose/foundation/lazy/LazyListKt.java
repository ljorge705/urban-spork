package androidx.compose.foundation.lazy;

import androidx.compose.foundation.CheckScrollableContainerConstraintsKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffsetKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyList.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0098\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00010\u0019¢\u0006\u0002\b\u001bH\u0001¢\u0006\u0002\u0010\u001c\u001a\u001d\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010 \u001a\u0097\u0001\u0010!\u001a\u0019\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0\"¢\u0006\u0002\b\u001b2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010(\u001a\u00020)H\u0003ø\u0001\u0000¢\u0006\u0002\u0010*\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"LazyList", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/LazyListState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "isVertical", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "userScrollEnabled", "beyondBoundsItemCount", "", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/LazyListScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZZLandroidx/compose/foundation/gestures/FlingBehavior;ZILandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "ScrollPositionUpdater", "itemProvider", "Landroidx/compose/foundation/lazy/LazyListItemProvider;", "(Landroidx/compose/foundation/lazy/LazyListItemProvider;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/runtime/Composer;I)V", "rememberLazyListMeasurePolicy", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "beyondBoundsInfo", "Landroidx/compose/foundation/lazy/LazyListBeyondBoundsInfo;", "placementAnimator", "Landroidx/compose/foundation/lazy/LazyListItemPlacementAnimator;", "(Landroidx/compose/foundation/lazy/LazyListItemProvider;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/lazy/LazyListBeyondBoundsInfo;Landroidx/compose/foundation/layout/PaddingValues;ZZILandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/lazy/LazyListItemPlacementAnimator;Landroidx/compose/runtime/Composer;III)Lkotlin/jvm/functions/Function2;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyListKt {
    /* JADX WARN: Removed duplicated region for block: B:106:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x03c8  */
    /* JADX WARN: Removed duplicated region for block: B:186:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x013f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LazyList(final androidx.compose.ui.Modifier r33, final androidx.compose.foundation.lazy.LazyListState r34, final androidx.compose.foundation.layout.PaddingValues r35, final boolean r36, final boolean r37, final androidx.compose.foundation.gestures.FlingBehavior r38, final boolean r39, int r40, androidx.compose.ui.Alignment.Horizontal r41, androidx.compose.foundation.layout.Arrangement.Vertical r42, androidx.compose.ui.Alignment.Vertical r43, androidx.compose.foundation.layout.Arrangement.Horizontal r44, final kotlin.jvm.functions.Function1<? super androidx.compose.foundation.lazy.LazyListScope, kotlin.Unit> r45, androidx.compose.runtime.Composer r46, final int r47, final int r48, final int r49) {
        /*
            Method dump skipped, instructions count: 1013
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyListKt.LazyList(androidx.compose.ui.Modifier, androidx.compose.foundation.lazy.LazyListState, androidx.compose.foundation.layout.PaddingValues, boolean, boolean, androidx.compose.foundation.gestures.FlingBehavior, boolean, int, androidx.compose.ui.Alignment$Horizontal, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.ui.Alignment$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ScrollPositionUpdater(final LazyListItemProvider lazyListItemProvider, final LazyListState lazyListState, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(3173830);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ScrollPositionUpdater):LazyList.kt#428nma");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(lazyListItemProvider) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(lazyListState) ? 32 : 16;
        }
        if ((i2 & 91) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(3173830, i, -1, "androidx.compose.foundation.lazy.ScrollPositionUpdater (LazyList.kt:141)");
            }
            if (lazyListItemProvider.getItemCount() > 0) {
                lazyListState.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(lazyListItemProvider);
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
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyListKt.ScrollPositionUpdater.1
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
                LazyListKt.ScrollPositionUpdater(lazyListItemProvider, lazyListState, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            }
        });
    }

    private static final Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> rememberLazyListMeasurePolicy(final LazyListItemProvider lazyListItemProvider, final LazyListState lazyListState, final LazyListBeyondBoundsInfo lazyListBeyondBoundsInfo, final PaddingValues paddingValues, final boolean z, final boolean z2, final int i, Alignment.Horizontal horizontal, Alignment.Vertical vertical, Arrangement.Horizontal horizontal2, Arrangement.Vertical vertical2, final LazyListItemPlacementAnimator lazyListItemPlacementAnimator, Composer composer, int i2, int i3, int i4) {
        composer.startReplaceableGroup(-966179815);
        ComposerKt.sourceInformation(composer, "C(rememberLazyListMeasurePolicy)P(6,9!1,2,8,5!2,10!1,11)177@7597L6680:LazyList.kt#428nma");
        final Alignment.Horizontal horizontal3 = (i4 & 128) != 0 ? null : horizontal;
        final Alignment.Vertical vertical3 = (i4 & 256) != 0 ? null : vertical;
        Arrangement.Horizontal horizontal4 = (i4 & 512) != 0 ? null : horizontal2;
        Arrangement.Vertical vertical4 = (i4 & 1024) == 0 ? vertical2 : null;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-966179815, i2, i3, "androidx.compose.foundation.lazy.rememberLazyListMeasurePolicy (LazyList.kt:152)");
        }
        Object[] objArr = {lazyListState, lazyListBeyondBoundsInfo, paddingValues, Boolean.valueOf(z), Boolean.valueOf(z2), horizontal3, vertical3, horizontal4, vertical4, lazyListItemPlacementAnimator};
        composer.startReplaceableGroup(-568225417);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged = false;
        for (int i5 = 0; i5 < 10; i5++) {
            zChanged |= composer.changed(objArr[i5]);
        }
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final Arrangement.Vertical vertical5 = vertical4;
            final Arrangement.Horizontal horizontal5 = horizontal4;
            objRememberedValue = (Function2) new Function2<LazyLayoutMeasureScope, Constraints, LazyListMeasureResult>() { // from class: androidx.compose.foundation.lazy.LazyListKt$rememberLazyListMeasurePolicy$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ LazyListMeasureResult invoke(LazyLayoutMeasureScope lazyLayoutMeasureScope, Constraints constraints) {
                    return m796invoke0kLqBqw(lazyLayoutMeasureScope, constraints.getValue());
                }

                /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                public final LazyListMeasureResult m796invoke0kLqBqw(final LazyLayoutMeasureScope lazyLayoutMeasureScope, final long j) {
                    int i6;
                    int i7;
                    float spacing;
                    int iM4346getMaxWidthimpl;
                    long jIntOffset;
                    Intrinsics.checkNotNullParameter(lazyLayoutMeasureScope, "$this$null");
                    CheckScrollableContainerConstraintsKt.m447checkScrollableContainerConstraintsK40F9xA(j, z2 ? Orientation.Vertical : Orientation.Horizontal);
                    if (z2) {
                        i6 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(paddingValues.mo671calculateLeftPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection()));
                    } else {
                        i6 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(PaddingKt.calculateStartPadding(paddingValues, lazyLayoutMeasureScope.getLayoutDirection()));
                    }
                    if (z2) {
                        i7 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(paddingValues.mo672calculateRightPaddingu2uoSUM(lazyLayoutMeasureScope.getLayoutDirection()));
                    } else {
                        i7 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(PaddingKt.calculateEndPadding(paddingValues, lazyLayoutMeasureScope.getLayoutDirection()));
                    }
                    int i8 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(paddingValues.getTop());
                    int i9 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(paddingValues.getBottom());
                    final int i10 = i8 + i9;
                    final int i11 = i6 + i7;
                    boolean z3 = z2;
                    int i12 = z3 ? i10 : i11;
                    int i13 = (!z3 || z) ? (z3 && z) ? i9 : (z3 || z) ? i7 : i6 : i8;
                    final int i14 = i12 - i13;
                    long jM4362offsetNN6EwU = ConstraintsKt.m4362offsetNN6EwU(j, -i11, -i10);
                    lazyListState.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(lazyListItemProvider);
                    LazyLayoutMeasureScope lazyLayoutMeasureScope2 = lazyLayoutMeasureScope;
                    lazyListState.setDensity$foundation_release(lazyLayoutMeasureScope2);
                    lazyListItemProvider.getItemScope().setMaxSize(Constraints.m4346getMaxWidthimpl(jM4362offsetNN6EwU), Constraints.m4345getMaxHeightimpl(jM4362offsetNN6EwU));
                    if (z2) {
                        Arrangement.Vertical vertical6 = vertical5;
                        if (vertical6 == null) {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                        spacing = vertical6.getSpacing();
                    } else {
                        Arrangement.Horizontal horizontal6 = horizontal5;
                        if (horizontal6 != null) {
                            spacing = horizontal6.getSpacing();
                        } else {
                            throw new IllegalArgumentException("Required value was null.".toString());
                        }
                    }
                    final int i15 = lazyLayoutMeasureScope.mo571roundToPx0680j_4(spacing);
                    final int itemCount = lazyListItemProvider.getItemCount();
                    if (z2) {
                        iM4346getMaxWidthimpl = Constraints.m4345getMaxHeightimpl(j) - i10;
                    } else {
                        iM4346getMaxWidthimpl = Constraints.m4346getMaxWidthimpl(j) - i11;
                    }
                    int i16 = iM4346getMaxWidthimpl;
                    if (!z || i16 > 0) {
                        jIntOffset = IntOffsetKt.IntOffset(i6, i8);
                    } else {
                        boolean z4 = z2;
                        if (!z4) {
                            i6 += i16;
                        }
                        if (z4) {
                            i8 += i16;
                        }
                        jIntOffset = IntOffsetKt.IntOffset(i6, i8);
                    }
                    final long j2 = jIntOffset;
                    boolean z5 = z2;
                    LazyListItemProvider lazyListItemProvider2 = lazyListItemProvider;
                    final boolean z6 = z2;
                    final Alignment.Horizontal horizontal7 = horizontal3;
                    final Alignment.Vertical vertical7 = vertical3;
                    final boolean z7 = z;
                    final LazyListItemPlacementAnimator lazyListItemPlacementAnimator2 = lazyListItemPlacementAnimator;
                    final int i17 = i13;
                    LazyMeasuredItemProvider lazyMeasuredItemProvider = new LazyMeasuredItemProvider(jM4362offsetNN6EwU, z5, lazyListItemProvider2, lazyLayoutMeasureScope, new MeasuredItemFactory() { // from class: androidx.compose.foundation.lazy.LazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1
                        @Override // androidx.compose.foundation.lazy.MeasuredItemFactory
                        /* renamed from: createItem-HK0c1C0, reason: not valid java name */
                        public final LazyMeasuredItem mo797createItemHK0c1C0(int i18, Object key, List<? extends Placeable> placeables) {
                            Intrinsics.checkNotNullParameter(key, "key");
                            Intrinsics.checkNotNullParameter(placeables, "placeables");
                            return new LazyMeasuredItem(i18, placeables, z6, horizontal7, vertical7, lazyLayoutMeasureScope.getLayoutDirection(), z7, i17, i14, lazyListItemPlacementAnimator2, i18 == itemCount + (-1) ? 0 : i15, j2, key, null);
                        }
                    }, null);
                    lazyListState.m810setPremeasureConstraintsBRTryo0$foundation_release(lazyMeasuredItemProvider.getChildConstraints());
                    Snapshot.Companion companion = Snapshot.INSTANCE;
                    LazyListState lazyListState2 = lazyListState;
                    Snapshot snapshotCreateNonObservableSnapshot = companion.createNonObservableSnapshot();
                    try {
                        Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
                        try {
                            int iM779constructorimpl = DataIndex.m779constructorimpl(lazyListState2.getFirstVisibleItemIndex());
                            int firstVisibleItemScrollOffset = lazyListState2.getFirstVisibleItemScrollOffset();
                            Unit unit = Unit.INSTANCE;
                            snapshotCreateNonObservableSnapshot.dispose();
                            LazyListMeasureResult lazyListMeasureResultM801measureLazyListHh3qtAg = LazyListMeasureKt.m801measureLazyListHh3qtAg(itemCount, lazyListItemProvider, lazyMeasuredItemProvider, i16, i13, i14, i15, iM779constructorimpl, firstVisibleItemScrollOffset, lazyListState.getScrollToBeConsumed(), jM4362offsetNN6EwU, z2, lazyListItemProvider.getHeaderIndexes(), vertical5, horizontal5, z, lazyLayoutMeasureScope2, lazyListItemPlacementAnimator, lazyListBeyondBoundsInfo, i, lazyListState.getPinnedItems(), new Function3<Integer, Integer, Function1<? super Placeable.PlacementScope, ? extends Unit>, MeasureResult>() { // from class: androidx.compose.foundation.lazy.LazyListKt$rememberLazyListMeasurePolicy$1$1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(3);
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ MeasureResult invoke(Integer num, Integer num2, Function1<? super Placeable.PlacementScope, ? extends Unit> function1) {
                                    return invoke(num.intValue(), num2.intValue(), (Function1<? super Placeable.PlacementScope, Unit>) function1);
                                }

                                public final MeasureResult invoke(int i18, int i19, Function1<? super Placeable.PlacementScope, Unit> placement) {
                                    Intrinsics.checkNotNullParameter(placement, "placement");
                                    return lazyLayoutMeasureScope.layout(ConstraintsKt.m4360constrainWidthK40F9xA(j, i18 + i11), ConstraintsKt.m4359constrainHeightK40F9xA(j, i19 + i10), MapsKt.emptyMap(), placement);
                                }
                            });
                            lazyListState.applyMeasureResult$foundation_release(lazyListMeasureResultM801measureLazyListHh3qtAg);
                            return lazyListMeasureResultM801measureLazyListHh3qtAg;
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
        Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2 = (Function2) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return function2;
    }
}
