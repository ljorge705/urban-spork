package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LazyNearestItemsRange.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0002\u001a=\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\tH\u0007¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"calculateNearestItemsRange", "Lkotlin/ranges/IntRange;", "firstVisibleItem", "", "slidingWindowSize", "extraItemCount", "rememberLazyNearestItemsRangeState", "Landroidx/compose/runtime/State;", "firstVisibleItemIndex", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyNearestItemsRangeKt {
    public static final State<IntRange> rememberLazyNearestItemsRangeState(Function0<Integer> firstVisibleItemIndex, Function0<Integer> slidingWindowSize, Function0<Integer> extraItemCount, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(firstVisibleItemIndex, "firstVisibleItemIndex");
        Intrinsics.checkNotNullParameter(slidingWindowSize, "slidingWindowSize");
        Intrinsics.checkNotNullParameter(extraItemCount, "extraItemCount");
        composer.startReplaceableGroup(429733345);
        ComposerKt.sourceInformation(composer, "C(rememberLazyNearestItemsRangeState)P(1,2)46@1947L353,58@2328L254,58@2306L276:LazyNearestItemsRange.kt#wow0x6");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(429733345, i, -1, "androidx.compose.foundation.lazy.layout.rememberLazyNearestItemsRangeState (LazyNearestItemsRange.kt:41)");
        }
        composer.startReplaceableGroup(1618982084);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(firstVisibleItemIndex) | composer.changed(slidingWindowSize) | composer.changed(extraItemCount);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
            try {
                Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
                try {
                    MutableState mutableStateMutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(calculateNearestItemsRange(firstVisibleItemIndex.invoke().intValue(), slidingWindowSize.invoke().intValue(), extraItemCount.invoke().intValue()), null, 2, null);
                    snapshotCreateNonObservableSnapshot.dispose();
                    composer.updateRememberedValue(mutableStateMutableStateOf$default);
                    objRememberedValue = mutableStateMutableStateOf$default;
                } finally {
                    snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                }
            } catch (Throwable th) {
                snapshotCreateNonObservableSnapshot.dispose();
                throw th;
            }
        }
        composer.endReplaceableGroup();
        MutableState mutableState = (MutableState) objRememberedValue;
        Object[] objArr = {firstVisibleItemIndex, slidingWindowSize, extraItemCount, mutableState};
        composer.startReplaceableGroup(-568225417);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged2 = false;
        for (int i2 = 0; i2 < 4; i2++) {
            zChanged2 |= composer.changed(objArr[i2]);
        }
        LazyNearestItemsRangeKt$rememberLazyNearestItemsRangeState$1$1 lazyNearestItemsRangeKt$rememberLazyNearestItemsRangeState$1$1RememberedValue = composer.rememberedValue();
        if (zChanged2 || lazyNearestItemsRangeKt$rememberLazyNearestItemsRangeState$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
            lazyNearestItemsRangeKt$rememberLazyNearestItemsRangeState$1$1RememberedValue = new LazyNearestItemsRangeKt$rememberLazyNearestItemsRangeState$1$1(firstVisibleItemIndex, slidingWindowSize, extraItemCount, mutableState, null);
            composer.updateRememberedValue(lazyNearestItemsRangeKt$rememberLazyNearestItemsRangeState$1$1RememberedValue);
        }
        composer.endReplaceableGroup();
        EffectsKt.LaunchedEffect(mutableState, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) lazyNearestItemsRangeKt$rememberLazyNearestItemsRangeState$1$1RememberedValue, composer, 64);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return mutableState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntRange calculateNearestItemsRange(int i, int i2, int i3) {
        int i4 = (i / i2) * i2;
        return RangesKt.until(Math.max(i4 - i3, 0), i4 + i2 + i3);
    }
}
