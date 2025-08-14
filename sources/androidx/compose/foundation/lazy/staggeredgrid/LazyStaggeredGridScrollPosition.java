package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProviderKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.snapshots.Snapshot;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* compiled from: LazyStaggeredGridScrollPosition.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00126\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\u0002\u0010\fJ\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u0007J\u0018\u0010 \u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0003H\u0002J\u000e\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#J\u0010\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020&H\u0007R>\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00038F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00038F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u0016\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014¨\u0006'"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridScrollPosition;", "", "initialIndices", "", "initialOffsets", "fillIndices", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "targetIndex", "laneCount", "([I[ILkotlin/jvm/functions/Function2;)V", "hadFirstNotEmptyLayout", "", "<set-?>", "indices", "getIndices", "()[I", "setIndices", "([I)V", "indices$delegate", "Landroidx/compose/runtime/MutableState;", "lastKnownFirstItemKey", "offsets", "getOffsets", "setOffsets", "offsets$delegate", "requestPosition", "", "index", "scrollOffset", "update", "updateFromMeasureResult", "measureResult", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "updateScrollPositionIfTheFirstItemWasMoved", "itemProvider", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyStaggeredGridScrollPosition {
    private final Function2<Integer, Integer, int[]> fillIndices;
    private boolean hadFirstNotEmptyLayout;

    /* renamed from: indices$delegate, reason: from kotlin metadata */
    private final MutableState indices;
    private Object lastKnownFirstItemKey;

    /* renamed from: offsets$delegate, reason: from kotlin metadata */
    private final MutableState offsets;

    /* JADX WARN: Multi-variable type inference failed */
    public LazyStaggeredGridScrollPosition(int[] initialIndices, int[] initialOffsets, Function2<? super Integer, ? super Integer, int[]> fillIndices) {
        Intrinsics.checkNotNullParameter(initialIndices, "initialIndices");
        Intrinsics.checkNotNullParameter(initialOffsets, "initialOffsets");
        Intrinsics.checkNotNullParameter(fillIndices, "fillIndices");
        this.fillIndices = fillIndices;
        this.indices = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(initialIndices, null, 2, null);
        this.offsets = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(initialOffsets, null, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int[] getIndices() {
        return (int[]) this.indices.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int[] getOffsets() {
        return (int[]) this.offsets.getValue();
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [kotlin.collections.IntIterator] */
    public final void updateFromMeasureResult(LazyStaggeredGridMeasureResult measureResult) {
        LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo;
        Intrinsics.checkNotNullParameter(measureResult, "measureResult");
        int[] firstVisibleItemIndices = measureResult.getFirstVisibleItemIndices();
        if (firstVisibleItemIndices.length == 0) {
            throw new NoSuchElementException();
        }
        int i = 0;
        int i2 = firstVisibleItemIndices[0];
        int lastIndex = ArraysKt.getLastIndex(firstVisibleItemIndices);
        if (lastIndex != 0) {
            int i3 = i2 == -1 ? Integer.MAX_VALUE : i2;
            ?? it = new IntRange(1, lastIndex).iterator();
            while (it.hasNext()) {
                int i4 = firstVisibleItemIndices[it.nextInt()];
                int i5 = i4 == -1 ? Integer.MAX_VALUE : i4;
                if (i3 > i5) {
                    i2 = i4;
                    i3 = i5;
                }
            }
        }
        if (i2 == Integer.MAX_VALUE) {
            i2 = 0;
        }
        List<LazyStaggeredGridItemInfo> visibleItemsInfo = measureResult.getVisibleItemsInfo();
        int size = visibleItemsInfo.size();
        while (true) {
            if (i >= size) {
                lazyStaggeredGridItemInfo = null;
                break;
            }
            lazyStaggeredGridItemInfo = visibleItemsInfo.get(i);
            if (lazyStaggeredGridItemInfo.getIndex() == i2) {
                break;
            } else {
                i++;
            }
        }
        LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo2 = lazyStaggeredGridItemInfo;
        this.lastKnownFirstItemKey = lazyStaggeredGridItemInfo2 != null ? lazyStaggeredGridItemInfo2.getKey() : null;
        if (this.hadFirstNotEmptyLayout || measureResult.getTotalItemsCount() > 0) {
            this.hadFirstNotEmptyLayout = true;
            Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
            try {
                Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
                try {
                    update(measureResult.getFirstVisibleItemIndices(), measureResult.getFirstVisibleItemScrollOffsets());
                    Unit unit = Unit.INSTANCE;
                } finally {
                    snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                }
            } finally {
                snapshotCreateNonObservableSnapshot.dispose();
            }
        }
    }

    public final void requestPosition(int index, int scrollOffset) {
        int[] iArrInvoke = this.fillIndices.invoke(Integer.valueOf(index), Integer.valueOf(getIndices().length));
        int length = iArrInvoke.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = scrollOffset;
        }
        update(iArrInvoke, iArr);
        this.lastKnownFirstItemKey = null;
    }

    public final void updateScrollPositionIfTheFirstItemWasMoved(LazyLayoutItemProvider itemProvider) {
        Intrinsics.checkNotNullParameter(itemProvider, "itemProvider");
        Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
        try {
            Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
            try {
                Object obj = this.lastKnownFirstItemKey;
                Integer orNull = ArraysKt.getOrNull(getIndices(), 0);
                int iFindIndexByKey = LazyLayoutItemProviderKt.findIndexByKey(itemProvider, obj, orNull != null ? orNull.intValue() : 0);
                if (!ArraysKt.contains(getIndices(), iFindIndexByKey)) {
                    update(this.fillIndices.invoke(Integer.valueOf(iFindIndexByKey), Integer.valueOf(getIndices().length)), getOffsets());
                }
                Unit unit = Unit.INSTANCE;
            } finally {
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
            }
        } finally {
            snapshotCreateNonObservableSnapshot.dispose();
        }
    }

    private final void update(int[] indices, int[] offsets) {
        if (!Arrays.equals(indices, getIndices())) {
            setIndices(indices);
        }
        if (Arrays.equals(offsets, getOffsets())) {
            return;
        }
        setOffsets(offsets);
    }

    public final void setIndices(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.indices.setValue(iArr);
    }

    public final void setOffsets(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.offsets.setValue(iArr);
    }
}
