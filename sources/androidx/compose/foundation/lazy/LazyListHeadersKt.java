package androidx.compose.foundation.lazy;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyListHeaders.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0004\u001aF\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0000¨\u0006\f"}, d2 = {"findOrComposeLazyListHeader", "Landroidx/compose/foundation/lazy/LazyListPositionedItem;", "composedVisibleItems", "", "itemProvider", "Landroidx/compose/foundation/lazy/LazyMeasuredItemProvider;", "headerIndexes", "", "", "beforeContentPadding", "layoutWidth", "layoutHeight", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyListHeadersKt {
    public static final LazyListPositionedItem findOrComposeLazyListHeader(List<LazyListPositionedItem> composedVisibleItems, LazyMeasuredItemProvider itemProvider, List<Integer> headerIndexes, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(composedVisibleItems, "composedVisibleItems");
        Intrinsics.checkNotNullParameter(itemProvider, "itemProvider");
        Intrinsics.checkNotNullParameter(headerIndexes, "headerIndexes");
        int index = ((LazyListPositionedItem) CollectionsKt.first((List) composedVisibleItems)).getIndex();
        int size = headerIndexes.size();
        int iIntValue = -1;
        int iIntValue2 = -1;
        int i4 = 0;
        while (i4 < size && headerIndexes.get(i4).intValue() <= index) {
            iIntValue = headerIndexes.get(i4).intValue();
            i4++;
            iIntValue2 = ((i4 < 0 || i4 > CollectionsKt.getLastIndex(headerIndexes)) ? -1 : headerIndexes.get(i4)).intValue();
        }
        int size2 = composedVisibleItems.size();
        int offset = Integer.MIN_VALUE;
        int offset2 = Integer.MIN_VALUE;
        int i5 = -1;
        for (int i6 = 0; i6 < size2; i6++) {
            LazyListPositionedItem lazyListPositionedItem = composedVisibleItems.get(i6);
            if (lazyListPositionedItem.getIndex() == iIntValue) {
                offset = lazyListPositionedItem.getOffset();
                i5 = i6;
            } else if (lazyListPositionedItem.getIndex() == iIntValue2) {
                offset2 = lazyListPositionedItem.getOffset();
            }
        }
        if (iIntValue == -1) {
            return null;
        }
        LazyMeasuredItem lazyMeasuredItemM811getAndMeasureZjPyQlc = itemProvider.m811getAndMeasureZjPyQlc(DataIndex.m779constructorimpl(iIntValue));
        int iMax = offset != Integer.MIN_VALUE ? Math.max(-i, offset) : -i;
        if (offset2 != Integer.MIN_VALUE) {
            iMax = Math.min(iMax, offset2 - lazyMeasuredItemM811getAndMeasureZjPyQlc.getSize());
        }
        LazyListPositionedItem lazyListPositionedItemPosition = lazyMeasuredItemM811getAndMeasureZjPyQlc.position(iMax, i2, i3);
        if (i5 != -1) {
            composedVisibleItems.set(i5, lazyListPositionedItemPosition);
        } else {
            composedVisibleItems.add(0, lazyListPositionedItemPosition);
        }
        return lazyListPositionedItemPosition;
    }
}
