package androidx.compose.foundation.lazy;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProviderKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: LazyListMeasure.kt */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u008c\u0001\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001bH\u0002\u001aL\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\b2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020'H\u0002\u001aS\u0010(\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010)\u001a\u00020*2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020'H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b+\u0010,\u001aø\u0001\u0010-\u001a\u00020.2\u0006\u0010$\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010 \u001a\u00020!2\u0006\u0010/\u001a\u00020\u00022\u0006\u00100\u001a\u00020\u00022\u0006\u00101\u001a\u00020\u00022\u0006\u00102\u001a\u00020\u00022\u0006\u00103\u001a\u00020*2\u0006\u00104\u001a\u00020\u00022\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u0010\u0014\u001a\u00020\u00042\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020;2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020'2/\u0010<\u001a+\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020?\u0012\u0004\u0012\u00020@0>¢\u0006\u0002\bA\u0012\u0004\u0012\u00020B0=H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bC\u0010D\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006E"}, d2 = {"EmptyRange", "Lkotlin/Pair;", "", "notInEmptyRange", "", "getNotInEmptyRange", "(I)Z", "calculateItemsOffsets", "", "Landroidx/compose/foundation/lazy/LazyListPositionedItem;", FirebaseAnalytics.Param.ITEMS, "", "Landroidx/compose/foundation/lazy/LazyMeasuredItem;", "extraItemsBefore", "extraItemsAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "itemsScrollOffset", "isVertical", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "createItemsAfterList", "beyondBoundsInfo", "Landroidx/compose/foundation/lazy/LazyListBeyondBoundsInfo;", "visibleItems", "measuredItemProvider", "Landroidx/compose/foundation/lazy/LazyMeasuredItemProvider;", "itemProvider", "Landroidx/compose/foundation/lazy/LazyListItemProvider;", "itemsCount", "beyondBoundsItemCount", "pinnedItems", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;", "createItemsBeforeList", "currentFirstItemIndex", "Landroidx/compose/foundation/lazy/DataIndex;", "createItemsBeforeList-tv8sHfA", "(Landroidx/compose/foundation/lazy/LazyListBeyondBoundsInfo;ILandroidx/compose/foundation/lazy/LazyMeasuredItemProvider;Landroidx/compose/foundation/lazy/LazyListItemProvider;IILandroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;)Ljava/util/List;", "measureLazyList", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenItems", "firstVisibleItemIndex", "firstVisibleItemScrollOffset", "scrollToBeConsumed", "", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "headerIndexes", "placementAnimator", "Landroidx/compose/foundation/lazy/LazyListItemPlacementAnimator;", "layout", "Lkotlin/Function3;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyList-Hh3qtAg", "(ILandroidx/compose/foundation/lazy/LazyListItemProvider;Landroidx/compose/foundation/lazy/LazyMeasuredItemProvider;IIIIIIFJZLjava/util/List;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/LazyListItemPlacementAnimator;Landroidx/compose/foundation/lazy/LazyListBeyondBoundsInfo;ILandroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyListMeasureKt {
    private static final Pair<Integer, Integer> EmptyRange = TuplesKt.to(Integer.MIN_VALUE, Integer.MIN_VALUE);

    private static final int calculateItemsOffsets$reverseAware(int i, boolean z, int i2) {
        return !z ? i : (i2 - i) - 1;
    }

    private static final boolean getNotInEmptyRange(int i) {
        return i != Integer.MIN_VALUE;
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01bb A[PHI: r2 r5 r6 r17 r20
      0x01bb: PHI (r2v9 int) = (r2v8 int), (r2v28 int) binds: [B:48:0x017a, B:55:0x01ae] A[DONT_GENERATE, DONT_INLINE]
      0x01bb: PHI (r5v7 int) = (r5v6 int), (r5v27 int) binds: [B:48:0x017a, B:55:0x01ae] A[DONT_GENERATE, DONT_INLINE]
      0x01bb: PHI (r6v4 int) = (r6v3 int), (r6v20 int) binds: [B:48:0x017a, B:55:0x01ae] A[DONT_GENERATE, DONT_INLINE]
      0x01bb: PHI (r17v3 int) = (r17v2 int), (r17v11 int) binds: [B:48:0x017a, B:55:0x01ae] A[DONT_GENERATE, DONT_INLINE]
      0x01bb: PHI (r20v4 int) = (r20v3 int), (r20v6 int) binds: [B:48:0x017a, B:55:0x01ae] A[DONT_GENERATE, DONT_INLINE]] */
    /* renamed from: measureLazyList-Hh3qtAg, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.foundation.lazy.LazyListMeasureResult m801measureLazyListHh3qtAg(int r32, androidx.compose.foundation.lazy.LazyListItemProvider r33, androidx.compose.foundation.lazy.LazyMeasuredItemProvider r34, int r35, int r36, int r37, int r38, int r39, int r40, float r41, long r42, boolean r44, java.util.List<java.lang.Integer> r45, androidx.compose.foundation.layout.Arrangement.Vertical r46, androidx.compose.foundation.layout.Arrangement.Horizontal r47, boolean r48, androidx.compose.ui.unit.Density r49, androidx.compose.foundation.lazy.LazyListItemPlacementAnimator r50, androidx.compose.foundation.lazy.LazyListBeyondBoundsInfo r51, int r52, androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList r53, kotlin.jvm.functions.Function3<? super java.lang.Integer, ? super java.lang.Integer, ? super kotlin.jvm.functions.Function1<? super androidx.compose.ui.layout.Placeable.PlacementScope, kotlin.Unit>, ? extends androidx.compose.ui.layout.MeasureResult> r54) {
        /*
            Method dump skipped, instructions count: 938
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyListMeasureKt.m801measureLazyListHh3qtAg(int, androidx.compose.foundation.lazy.LazyListItemProvider, androidx.compose.foundation.lazy.LazyMeasuredItemProvider, int, int, int, int, int, int, float, long, boolean, java.util.List, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, boolean, androidx.compose.ui.unit.Density, androidx.compose.foundation.lazy.LazyListItemPlacementAnimator, androidx.compose.foundation.lazy.LazyListBeyondBoundsInfo, int, androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList, kotlin.jvm.functions.Function3):androidx.compose.foundation.lazy.LazyListMeasureResult");
    }

    private static final int createItemsAfterList$endIndex(LazyListBeyondBoundsInfo lazyListBeyondBoundsInfo, int i) {
        return Math.min(lazyListBeyondBoundsInfo.getEnd(), i - 1);
    }

    private static final List<LazyMeasuredItem> createItemsAfterList(LazyListBeyondBoundsInfo lazyListBeyondBoundsInfo, List<LazyMeasuredItem> list, LazyMeasuredItemProvider lazyMeasuredItemProvider, LazyListItemProvider lazyListItemProvider, int i, int i2, LazyLayoutPinnedItemList lazyLayoutPinnedItemList) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        int index = ((LazyMeasuredItem) CollectionsKt.last((List) list)).getIndex();
        if (lazyListBeyondBoundsInfo.hasIntervals()) {
            index = Math.max(createItemsAfterList$endIndex(lazyListBeyondBoundsInfo, i), index);
        }
        int iMin = Math.min(index + i2, i - 1);
        int index2 = ((LazyMeasuredItem) CollectionsKt.last((List) list)).getIndex() + 1;
        if (index2 <= iMin) {
            while (true) {
                createItemsAfterList$addItem(objectRef, lazyMeasuredItemProvider, index2);
                if (index2 == iMin) {
                    break;
                }
                index2++;
            }
        }
        LazyLayoutPinnedItemList lazyLayoutPinnedItemList2 = lazyLayoutPinnedItemList;
        int size = lazyLayoutPinnedItemList2.size();
        for (int i3 = 0; i3 < size; i3++) {
            LazyLayoutPinnedItemList.PinnedItem pinnedItem = lazyLayoutPinnedItemList2.get(i3);
            int iFindIndexByKey = LazyLayoutItemProviderKt.findIndexByKey(lazyListItemProvider, pinnedItem.getKey(), pinnedItem.getIndex());
            if (iFindIndexByKey > iMin && iFindIndexByKey < i) {
                createItemsAfterList$addItem(objectRef, lazyMeasuredItemProvider, iFindIndexByKey);
            }
        }
        List<LazyMeasuredItem> list2 = (List) objectRef.element;
        return list2 == null ? CollectionsKt.emptyList() : list2;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.util.List] */
    private static final void createItemsAfterList$addItem(Ref.ObjectRef<List<LazyMeasuredItem>> objectRef, LazyMeasuredItemProvider lazyMeasuredItemProvider, int i) {
        if (objectRef.element == null) {
            objectRef.element = new ArrayList();
        }
        List<LazyMeasuredItem> list = objectRef.element;
        if (list == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        list.add(lazyMeasuredItemProvider.m811getAndMeasureZjPyQlc(DataIndex.m779constructorimpl(i)));
    }

    private static final int createItemsBeforeList_tv8sHfA$startIndex(LazyListBeyondBoundsInfo lazyListBeyondBoundsInfo, int i) {
        return Math.min(lazyListBeyondBoundsInfo.getStart(), i - 1);
    }

    /* renamed from: createItemsBeforeList-tv8sHfA, reason: not valid java name */
    private static final List<LazyMeasuredItem> m800createItemsBeforeListtv8sHfA(LazyListBeyondBoundsInfo lazyListBeyondBoundsInfo, int i, LazyMeasuredItemProvider lazyMeasuredItemProvider, LazyListItemProvider lazyListItemProvider, int i2, int i3, LazyLayoutPinnedItemList lazyLayoutPinnedItemList) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        int iMin = lazyListBeyondBoundsInfo.hasIntervals() ? Math.min(createItemsBeforeList_tv8sHfA$startIndex(lazyListBeyondBoundsInfo, i2), i) : i;
        int iMax = Math.max(0, iMin - i3);
        int i4 = i - 1;
        if (iMax <= i4) {
            while (true) {
                createItemsBeforeList_tv8sHfA$addItem$5(objectRef, lazyMeasuredItemProvider, i4);
                if (i4 == iMax) {
                    break;
                }
                i4--;
            }
        }
        LazyLayoutPinnedItemList lazyLayoutPinnedItemList2 = lazyLayoutPinnedItemList;
        int size = lazyLayoutPinnedItemList2.size();
        for (int i5 = 0; i5 < size; i5++) {
            LazyLayoutPinnedItemList.PinnedItem pinnedItem = lazyLayoutPinnedItemList2.get(i5);
            int iFindIndexByKey = LazyLayoutItemProviderKt.findIndexByKey(lazyListItemProvider, pinnedItem.getKey(), pinnedItem.getIndex());
            if (iFindIndexByKey < iMax) {
                createItemsBeforeList_tv8sHfA$addItem$5(objectRef, lazyMeasuredItemProvider, iFindIndexByKey);
            }
        }
        List<LazyMeasuredItem> list = (List) objectRef.element;
        return list == null ? CollectionsKt.emptyList() : list;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.util.List] */
    private static final void createItemsBeforeList_tv8sHfA$addItem$5(Ref.ObjectRef<List<LazyMeasuredItem>> objectRef, LazyMeasuredItemProvider lazyMeasuredItemProvider, int i) {
        if (objectRef.element == null) {
            objectRef.element = new ArrayList();
        }
        List<LazyMeasuredItem> list = objectRef.element;
        if (list == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        list.add(lazyMeasuredItemProvider.m811getAndMeasureZjPyQlc(DataIndex.m779constructorimpl(i)));
    }

    private static final List<LazyListPositionedItem> calculateItemsOffsets(List<LazyMeasuredItem> list, List<LazyMeasuredItem> list2, List<LazyMeasuredItem> list3, int i, int i2, int i3, int i4, int i5, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density) {
        int i6 = z ? i2 : i;
        boolean z3 = i3 < Math.min(i6, i4);
        if (z3 && i5 != 0) {
            throw new IllegalStateException("Check failed.".toString());
        }
        ArrayList arrayList = new ArrayList(list.size() + list2.size() + list3.size());
        if (z3) {
            if (!list2.isEmpty() || !list3.isEmpty()) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            int size = list.size();
            int[] iArr = new int[size];
            for (int i7 = 0; i7 < size; i7++) {
                iArr[i7] = list.get(calculateItemsOffsets$reverseAware(i7, z2, size)).getSize();
            }
            int[] iArr2 = new int[size];
            for (int i8 = 0; i8 < size; i8++) {
                iArr2[i8] = 0;
            }
            if (z) {
                if (vertical == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                vertical.arrange(density, i6, iArr, iArr2);
            } else {
                if (horizontal == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                horizontal.arrange(density, i6, iArr, LayoutDirection.Ltr, iArr2);
            }
            IntRange indices = ArraysKt.getIndices(iArr2);
            if (z2) {
                indices = RangesKt.reversed(indices);
            }
            int first = indices.getFirst();
            int last = indices.getLast();
            int step = indices.getStep();
            if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
                while (true) {
                    int size2 = iArr2[first];
                    LazyMeasuredItem lazyMeasuredItem = list.get(calculateItemsOffsets$reverseAware(first, z2, size));
                    if (z2) {
                        size2 = (i6 - size2) - lazyMeasuredItem.getSize();
                    }
                    arrayList.add(lazyMeasuredItem.position(size2, i, i2));
                    if (first == last) {
                        break;
                    }
                    first += step;
                }
            }
        } else {
            int size3 = list2.size();
            int sizeWithSpacings = i5;
            for (int i9 = 0; i9 < size3; i9++) {
                LazyMeasuredItem lazyMeasuredItem2 = list2.get(i9);
                sizeWithSpacings -= lazyMeasuredItem2.getSizeWithSpacings();
                arrayList.add(lazyMeasuredItem2.position(sizeWithSpacings, i, i2));
            }
            int size4 = list.size();
            int sizeWithSpacings2 = i5;
            for (int i10 = 0; i10 < size4; i10++) {
                LazyMeasuredItem lazyMeasuredItem3 = list.get(i10);
                arrayList.add(lazyMeasuredItem3.position(sizeWithSpacings2, i, i2));
                sizeWithSpacings2 += lazyMeasuredItem3.getSizeWithSpacings();
            }
            int size5 = list3.size();
            for (int i11 = 0; i11 < size5; i11++) {
                LazyMeasuredItem lazyMeasuredItem4 = list3.get(i11);
                arrayList.add(lazyMeasuredItem4.position(sizeWithSpacings2, i, i2));
                sizeWithSpacings2 += lazyMeasuredItem4.getSizeWithSpacings();
            }
        }
        return arrayList;
    }
}
