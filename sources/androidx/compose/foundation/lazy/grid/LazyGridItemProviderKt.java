package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.LazyNearestItemsRangeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* compiled from: LazyGridItemProvider.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a.\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\b\u000bH\u0001¢\u0006\u0002\u0010\f\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"NearestItemsExtraItemCount", "", "NearestItemsSlidingWindowSize", "rememberLazyGridItemProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;", "state", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/grid/LazyGridScope;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/grid/LazyGridState;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyGridItemProviderKt {
    private static final int NearestItemsExtraItemCount = 200;
    private static final int NearestItemsSlidingWindowSize = 90;

    public static final LazyGridItemProvider rememberLazyGridItemProvider(final LazyGridState state, Function1<? super LazyGridScope, Unit> content, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(content, "content");
        composer.startReplaceableGroup(1831211759);
        ComposerKt.sourceInformation(composer, "C(rememberLazyGridItemProvider)P(1)44@1756L29,46@1887L71,45@1819L265,53@2097L982:LazyGridItemProvider.kt#7791vq");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1831211759, i, -1, "androidx.compose.foundation.lazy.grid.rememberLazyGridItemProvider (LazyGridItemProvider.kt:40)");
        }
        final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(content, composer, (i >> 3) & 14);
        composer.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(state);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (Function0) new Function0<Integer>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridItemProviderKt$rememberLazyGridItemProvider$nearestItemsRangeState$1$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return Integer.valueOf(state.getFirstVisibleItemIndex());
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        final State<IntRange> stateRememberLazyNearestItemsRangeState = LazyNearestItemsRangeKt.rememberLazyNearestItemsRangeState((Function0) objRememberedValue, new Function0<Integer>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridItemProviderKt$rememberLazyGridItemProvider$nearestItemsRangeState$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return 90;
            }
        }, new Function0<Integer>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridItemProviderKt$rememberLazyGridItemProvider$nearestItemsRangeState$3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return 200;
            }
        }, composer, 432);
        composer.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged2 = composer.changed(stateRememberLazyNearestItemsRangeState);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new LazyGridItemProviderKt$rememberLazyGridItemProvider$1$1(SnapshotStateKt.derivedStateOf(new Function0<LazyGridItemProviderImpl>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridItemProviderKt$rememberLazyGridItemProvider$1$itemProviderState$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final LazyGridItemProviderImpl invoke() {
                    LazyGridScopeImpl lazyGridScopeImpl = new LazyGridScopeImpl();
                    stateRememberUpdatedState.getValue().invoke(lazyGridScopeImpl);
                    return new LazyGridItemProviderImpl(lazyGridScopeImpl.getIntervals$foundation_release(), lazyGridScopeImpl.getHasCustomSpans(), state, stateRememberLazyNearestItemsRangeState.getValue());
                }
            }));
            composer.updateRememberedValue(objRememberedValue2);
        }
        composer.endReplaceableGroup();
        LazyGridItemProviderKt$rememberLazyGridItemProvider$1$1 lazyGridItemProviderKt$rememberLazyGridItemProvider$1$1 = (LazyGridItemProviderKt$rememberLazyGridItemProvider$1$1) objRememberedValue2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return lazyGridItemProviderKt$rememberLazyGridItemProvider$1$1;
    }
}
