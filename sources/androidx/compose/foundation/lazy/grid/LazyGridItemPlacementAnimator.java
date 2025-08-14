package androidx.compose.foundation.lazy.grid;

import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import com.clevertap.android.sdk.leanplum.Constants;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LazyGridItemPlacementAnimator.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00162\b\b\u0002\u0010!\u001a\u00020\bH\u0002J;\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020\u001cø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b(\u0010)J<\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020\b2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00160\u00122\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000203J\u0006\u00104\u001a\u00020+J\u0018\u00105\u001a\u00020+2\u0006\u0010 \u001a\u00020\u00162\u0006\u00106\u001a\u00020\rH\u0002J\u0014\u00107\u001a\u00020\u0005*\u00020\r2\u0006\u00108\u001a\u00020\bH\u0002J\u001c\u00109\u001a\u00020\u001c*\u00020\bH\u0002ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b:\u0010;R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u000fj\b\u0012\u0004\u0012\u00020\u0001`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0018\u001a\u00020\b*\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001b\u001a\u00020\b*\u00020\u001c8BX\u0082\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006<"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "isVertical", "", "(Lkotlinx/coroutines/CoroutineScope;Z)V", "firstVisibleIndex", "", "keyToIndexMap", "", "keyToItemInfoMap", "", "Landroidx/compose/foundation/lazy/grid/ItemInfo;", "movingAwayKeys", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "movingAwayToEndBound", "", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "movingAwayToStartBound", "movingInFromEndBound", "Landroidx/compose/foundation/lazy/grid/LazyGridPositionedItem;", "movingInFromStartBound", "line", "getLine", "(Landroidx/compose/foundation/lazy/grid/LazyGridPositionedItem;)I", "mainAxis", "Landroidx/compose/ui/unit/IntOffset;", "getMainAxis--gyyYBs", "(J)I", "createItemInfo", Constants.IAP_ITEM_PARAM, "mainAxisOffset", "getAnimatedOffset", com.clevertap.android.sdk.Constants.KEY_KEY, "placeableIndex", "minOffset", "maxOffset", "rawOffset", "getAnimatedOffset-YT5a7pE", "(Ljava/lang/Object;IIIJ)J", "onMeasured", "", "consumedScroll", "layoutWidth", "layoutHeight", "positionedItems", "itemProvider", "Landroidx/compose/foundation/lazy/grid/LazyMeasuredItemProvider;", "spanLayoutProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider;", "reset", "startAnimationsIfNeeded", "itemInfo", "isWithinBounds", "mainAxisLayoutSize", "toOffset", "toOffset-Bjo55l4", "(I)J", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyGridItemPlacementAnimator {
    private int firstVisibleIndex;
    private final boolean isVertical;
    private Map<Object, Integer> keyToIndexMap;
    private final Map<Object, ItemInfo> keyToItemInfoMap;
    private final LinkedHashSet<Object> movingAwayKeys;
    private final List<LazyGridMeasuredItem> movingAwayToEndBound;
    private final List<LazyGridMeasuredItem> movingAwayToStartBound;
    private final List<LazyGridPositionedItem> movingInFromEndBound;
    private final List<LazyGridPositionedItem> movingInFromStartBound;
    private final CoroutineScope scope;

    public LazyGridItemPlacementAnimator(CoroutineScope scope, boolean z) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.scope = scope;
        this.isVertical = z;
        this.keyToItemInfoMap = new LinkedHashMap();
        this.keyToIndexMap = MapsKt.emptyMap();
        this.movingAwayKeys = new LinkedHashSet<>();
        this.movingInFromStartBound = new ArrayList();
        this.movingInFromEndBound = new ArrayList();
        this.movingAwayToStartBound = new ArrayList();
        this.movingAwayToEndBound = new ArrayList();
    }

    /* renamed from: getAnimatedOffset-YT5a7pE, reason: not valid java name */
    public final long m849getAnimatedOffsetYT5a7pE(Object key, int placeableIndex, int minOffset, int maxOffset, long rawOffset) {
        Intrinsics.checkNotNullParameter(key, "key");
        ItemInfo itemInfo = this.keyToItemInfoMap.get(key);
        if (itemInfo == null) {
            return rawOffset;
        }
        PlaceableInfo placeableInfo = itemInfo.getPlaceables().get(placeableIndex);
        long packedValue = placeableInfo.getAnimatedOffset().getValue().getPackedValue();
        long notAnimatableDelta = itemInfo.getNotAnimatableDelta();
        long jIntOffset = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(packedValue) + IntOffset.m4508getXimpl(notAnimatableDelta), IntOffset.m4509getYimpl(packedValue) + IntOffset.m4509getYimpl(notAnimatableDelta));
        long targetOffset = placeableInfo.getTargetOffset();
        long notAnimatableDelta2 = itemInfo.getNotAnimatableDelta();
        long jIntOffset2 = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(targetOffset) + IntOffset.m4508getXimpl(notAnimatableDelta2), IntOffset.m4509getYimpl(targetOffset) + IntOffset.m4509getYimpl(notAnimatableDelta2));
        if (placeableInfo.getInProgress() && ((m847getMainAxisgyyYBs(jIntOffset2) <= minOffset && m847getMainAxisgyyYBs(jIntOffset) < minOffset) || (m847getMainAxisgyyYBs(jIntOffset2) >= maxOffset && m847getMainAxisgyyYBs(jIntOffset) > maxOffset))) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new LazyGridItemPlacementAnimator$getAnimatedOffset$1(placeableInfo, null), 3, null);
        }
        return jIntOffset;
    }

    public final void reset() {
        this.keyToItemInfoMap.clear();
        this.keyToIndexMap = MapsKt.emptyMap();
        this.firstVisibleIndex = -1;
    }

    static /* synthetic */ ItemInfo createItemInfo$default(LazyGridItemPlacementAnimator lazyGridItemPlacementAnimator, LazyGridPositionedItem lazyGridPositionedItem, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = lazyGridItemPlacementAnimator.m847getMainAxisgyyYBs(lazyGridPositionedItem.getOffset());
        }
        return lazyGridItemPlacementAnimator.createItemInfo(lazyGridPositionedItem, i);
    }

    private final ItemInfo createItemInfo(LazyGridPositionedItem item, int mainAxisOffset) {
        long jM4504copyiSbpLlY$default;
        ItemInfo itemInfo = new ItemInfo(item.getCrossAxisSize(), item.getCrossAxisOffset());
        if (this.isVertical) {
            jM4504copyiSbpLlY$default = IntOffset.m4504copyiSbpLlY$default(item.getOffset(), 0, mainAxisOffset, 1, null);
        } else {
            jM4504copyiSbpLlY$default = IntOffset.m4504copyiSbpLlY$default(item.getOffset(), mainAxisOffset, 0, 2, null);
        }
        int placeablesCount = item.getPlaceablesCount();
        for (int i = 0; i < placeablesCount; i++) {
            itemInfo.getPlaceables().add(new PlaceableInfo(jM4504copyiSbpLlY$default, item.getMainAxisSize(i), null));
        }
        return itemInfo;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void startAnimationsIfNeeded(androidx.compose.foundation.lazy.grid.LazyGridPositionedItem r18, androidx.compose.foundation.lazy.grid.ItemInfo r19) {
        /*
            r17 = this;
            r0 = r18
        L2:
            java.util.List r1 = r19.getPlaceables()
            int r1 = r1.size()
            int r2 = r18.getPlaceablesCount()
            if (r1 <= r2) goto L18
            java.util.List r1 = r19.getPlaceables()
            kotlin.collections.CollectionsKt.removeLast(r1)
            goto L2
        L18:
            java.util.List r1 = r19.getPlaceables()
            int r1 = r1.size()
            int r2 = r18.getPlaceablesCount()
            r3 = 0
            if (r1 >= r2) goto L5e
            java.util.List r1 = r19.getPlaceables()
            int r1 = r1.size()
            long r4 = r18.getOffset()
            java.util.List r2 = r19.getPlaceables()
            androidx.compose.foundation.lazy.grid.PlaceableInfo r6 = new androidx.compose.foundation.lazy.grid.PlaceableInfo
            long r7 = r19.getNotAnimatableDelta()
            int r9 = androidx.compose.ui.unit.IntOffset.m4508getXimpl(r4)
            int r10 = androidx.compose.ui.unit.IntOffset.m4508getXimpl(r7)
            int r9 = r9 - r10
            int r4 = androidx.compose.ui.unit.IntOffset.m4509getYimpl(r4)
            int r5 = androidx.compose.ui.unit.IntOffset.m4509getYimpl(r7)
            int r4 = r4 - r5
            long r4 = androidx.compose.ui.unit.IntOffsetKt.IntOffset(r9, r4)
            int r1 = r0.getMainAxisSize(r1)
            r6.<init>(r4, r1, r3)
            r2.add(r6)
            goto L18
        L5e:
            java.util.List r1 = r19.getPlaceables()
            int r2 = r1.size()
            r4 = 0
        L67:
            if (r4 >= r2) goto Ldf
            java.lang.Object r5 = r1.get(r4)
            androidx.compose.foundation.lazy.grid.PlaceableInfo r5 = (androidx.compose.foundation.lazy.grid.PlaceableInfo) r5
            long r6 = r5.getTargetOffset()
            long r8 = r19.getNotAnimatableDelta()
            int r10 = androidx.compose.ui.unit.IntOffset.m4508getXimpl(r6)
            int r11 = androidx.compose.ui.unit.IntOffset.m4508getXimpl(r8)
            int r10 = r10 + r11
            int r6 = androidx.compose.ui.unit.IntOffset.m4509getYimpl(r6)
            int r7 = androidx.compose.ui.unit.IntOffset.m4509getYimpl(r8)
            int r6 = r6 + r7
            long r6 = androidx.compose.ui.unit.IntOffsetKt.IntOffset(r10, r6)
            long r8 = r18.getOffset()
            int r10 = r0.getMainAxisSize(r4)
            r5.setMainAxisSize(r10)
            androidx.compose.animation.core.FiniteAnimationSpec r10 = r0.getAnimationSpec(r4)
            boolean r6 = androidx.compose.ui.unit.IntOffset.m4507equalsimpl0(r6, r8)
            if (r6 != 0) goto Lda
            long r6 = r19.getNotAnimatableDelta()
            int r11 = androidx.compose.ui.unit.IntOffset.m4508getXimpl(r8)
            int r12 = androidx.compose.ui.unit.IntOffset.m4508getXimpl(r6)
            int r11 = r11 - r12
            int r8 = androidx.compose.ui.unit.IntOffset.m4509getYimpl(r8)
            int r6 = androidx.compose.ui.unit.IntOffset.m4509getYimpl(r6)
            int r8 = r8 - r6
            long r6 = androidx.compose.ui.unit.IntOffsetKt.IntOffset(r11, r8)
            r5.m886setTargetOffsetgyyYBs(r6)
            if (r10 == 0) goto Lda
            r6 = 1
            r5.setInProgress(r6)
            r6 = r17
            kotlinx.coroutines.CoroutineScope r11 = r6.scope
            r12 = 0
            r13 = 0
            androidx.compose.foundation.lazy.grid.LazyGridItemPlacementAnimator$startAnimationsIfNeeded$1$1 r7 = new androidx.compose.foundation.lazy.grid.LazyGridItemPlacementAnimator$startAnimationsIfNeeded$1$1
            r7.<init>(r5, r10, r3)
            r14 = r7
            kotlin.jvm.functions.Function2 r14 = (kotlin.jvm.functions.Function2) r14
            r15 = 3
            r16 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r11, r12, r13, r14, r15, r16)
            goto Ldc
        Lda:
            r6 = r17
        Ldc:
            int r4 = r4 + 1
            goto L67
        Ldf:
            r6 = r17
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.grid.LazyGridItemPlacementAnimator.startAnimationsIfNeeded(androidx.compose.foundation.lazy.grid.LazyGridPositionedItem, androidx.compose.foundation.lazy.grid.ItemInfo):void");
    }

    private final boolean isWithinBounds(ItemInfo itemInfo, int i) {
        List<PlaceableInfo> placeables = itemInfo.getPlaceables();
        int size = placeables.size();
        for (int i2 = 0; i2 < size; i2++) {
            PlaceableInfo placeableInfo = placeables.get(i2);
            long targetOffset = placeableInfo.getTargetOffset();
            long notAnimatableDelta = itemInfo.getNotAnimatableDelta();
            long jIntOffset = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(targetOffset) + IntOffset.m4508getXimpl(notAnimatableDelta), IntOffset.m4509getYimpl(targetOffset) + IntOffset.m4509getYimpl(notAnimatableDelta));
            if (m847getMainAxisgyyYBs(jIntOffset) + placeableInfo.getMainAxisSize() > 0 && m847getMainAxisgyyYBs(jIntOffset) < i) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: toOffset-Bjo55l4, reason: not valid java name */
    private final long m848toOffsetBjo55l4(int i) {
        boolean z = this.isVertical;
        int i2 = z ? 0 : i;
        if (!z) {
            i = 0;
        }
        return IntOffsetKt.IntOffset(i2, i);
    }

    /* renamed from: getMainAxis--gyyYBs, reason: not valid java name */
    private final int m847getMainAxisgyyYBs(long j) {
        return this.isVertical ? IntOffset.m4509getYimpl(j) : IntOffset.m4508getXimpl(j);
    }

    private final int getLine(LazyGridPositionedItem lazyGridPositionedItem) {
        return this.isVertical ? lazyGridPositionedItem.getRow() : lazyGridPositionedItem.getColumn();
    }

    public final void onMeasured(int consumedScroll, int layoutWidth, int layoutHeight, List<LazyGridPositionedItem> positionedItems, LazyMeasuredItemProvider itemProvider, LazyGridSpanLayoutProvider spanLayoutProvider) {
        final Map<Object, Integer> map;
        boolean z;
        Iterator it;
        long jM4355fixedHeightOenEA2s;
        int i;
        Map<Object, Integer> map2;
        Intrinsics.checkNotNullParameter(positionedItems, "positionedItems");
        Intrinsics.checkNotNullParameter(itemProvider, "itemProvider");
        Intrinsics.checkNotNullParameter(spanLayoutProvider, "spanLayoutProvider");
        int size = positionedItems.size();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                if (this.keyToItemInfoMap.isEmpty()) {
                    reset();
                    return;
                }
            } else if (positionedItems.get(i3).getHasAnimations()) {
                break;
            } else {
                i3++;
            }
        }
        int i4 = this.firstVisibleIndex;
        LazyGridPositionedItem lazyGridPositionedItem = (LazyGridPositionedItem) CollectionsKt.firstOrNull((List) positionedItems);
        this.firstVisibleIndex = lazyGridPositionedItem != null ? lazyGridPositionedItem.getIndex() : 0;
        Map<Object, Integer> map3 = this.keyToIndexMap;
        this.keyToIndexMap = itemProvider.getKeyToIndexMap();
        int i5 = this.isVertical ? layoutHeight : layoutWidth;
        long jM848toOffsetBjo55l4 = m848toOffsetBjo55l4(consumedScroll);
        this.movingAwayKeys.addAll(this.keyToItemInfoMap.keySet());
        int size2 = positionedItems.size();
        int i6 = 0;
        while (i6 < size2) {
            LazyGridPositionedItem lazyGridPositionedItem2 = positionedItems.get(i6);
            this.movingAwayKeys.remove(lazyGridPositionedItem2.getKey());
            if (lazyGridPositionedItem2.getHasAnimations()) {
                ItemInfo itemInfo = this.keyToItemInfoMap.get(lazyGridPositionedItem2.getKey());
                if (itemInfo == null) {
                    Integer num = map3.get(lazyGridPositionedItem2.getKey());
                    if (num != null && lazyGridPositionedItem2.getIndex() != num.intValue()) {
                        if (num.intValue() < i4) {
                            this.movingInFromStartBound.add(lazyGridPositionedItem2);
                        } else {
                            this.movingInFromEndBound.add(lazyGridPositionedItem2);
                        }
                        i = i4;
                    } else {
                        i = i4;
                        this.keyToItemInfoMap.put(lazyGridPositionedItem2.getKey(), createItemInfo$default(this, lazyGridPositionedItem2, i2, 2, null));
                    }
                    map2 = map3;
                } else {
                    i = i4;
                    long notAnimatableDelta = itemInfo.getNotAnimatableDelta();
                    int iM4508getXimpl = IntOffset.m4508getXimpl(notAnimatableDelta) + IntOffset.m4508getXimpl(jM848toOffsetBjo55l4);
                    int iM4509getYimpl = IntOffset.m4509getYimpl(notAnimatableDelta) + IntOffset.m4509getYimpl(jM848toOffsetBjo55l4);
                    map2 = map3;
                    itemInfo.m838setNotAnimatableDeltagyyYBs(IntOffsetKt.IntOffset(iM4508getXimpl, iM4509getYimpl));
                    itemInfo.setCrossAxisSize(lazyGridPositionedItem2.getCrossAxisSize());
                    itemInfo.setCrossAxisOffset(lazyGridPositionedItem2.getCrossAxisOffset());
                    startAnimationsIfNeeded(lazyGridPositionedItem2, itemInfo);
                }
            } else {
                i = i4;
                map2 = map3;
                this.keyToItemInfoMap.remove(lazyGridPositionedItem2.getKey());
            }
            i6++;
            i4 = i;
            map3 = map2;
            i2 = 0;
        }
        Map<Object, Integer> map4 = map3;
        List<LazyGridPositionedItem> list = this.movingInFromStartBound;
        if (list.size() > 1) {
            map = map4;
            CollectionsKt.sortWith(list, new Comparator() { // from class: androidx.compose.foundation.lazy.grid.LazyGridItemPlacementAnimator$onMeasured$$inlined$sortByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues((Integer) map.get(((LazyGridPositionedItem) t2).getKey()), (Integer) map.get(((LazyGridPositionedItem) t).getKey()));
                }
            });
        } else {
            map = map4;
        }
        List<LazyGridPositionedItem> list2 = this.movingInFromStartBound;
        int size3 = list2.size();
        int i7 = -1;
        int i8 = -1;
        int i9 = 0;
        int i10 = 0;
        int mainAxisSize = 0;
        while (i9 < size3) {
            LazyGridPositionedItem lazyGridPositionedItem3 = list2.get(i9);
            int line = getLine(lazyGridPositionedItem3);
            if (line != i7 && line == i8) {
                mainAxisSize = Math.max(mainAxisSize, lazyGridPositionedItem3.getMainAxisSize());
            } else {
                i10 += mainAxisSize;
                mainAxisSize = lazyGridPositionedItem3.getMainAxisSize();
                i8 = line;
            }
            ItemInfo itemInfoCreateItemInfo = createItemInfo(lazyGridPositionedItem3, (0 - i10) - lazyGridPositionedItem3.getMainAxisSize());
            this.keyToItemInfoMap.put(lazyGridPositionedItem3.getKey(), itemInfoCreateItemInfo);
            startAnimationsIfNeeded(lazyGridPositionedItem3, itemInfoCreateItemInfo);
            i9++;
            i7 = -1;
        }
        List<LazyGridPositionedItem> list3 = this.movingInFromEndBound;
        if (list3.size() > 1) {
            CollectionsKt.sortWith(list3, new Comparator() { // from class: androidx.compose.foundation.lazy.grid.LazyGridItemPlacementAnimator$onMeasured$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues((Integer) map.get(((LazyGridPositionedItem) t).getKey()), (Integer) map.get(((LazyGridPositionedItem) t2).getKey()));
                }
            });
        }
        List<LazyGridPositionedItem> list4 = this.movingInFromEndBound;
        int size4 = list4.size();
        int i11 = -1;
        int i12 = 0;
        int mainAxisSize2 = 0;
        for (int i13 = 0; i13 < size4; i13++) {
            LazyGridPositionedItem lazyGridPositionedItem4 = list4.get(i13);
            int line2 = getLine(lazyGridPositionedItem4);
            if (line2 != -1 && line2 == i11) {
                mainAxisSize2 = Math.max(mainAxisSize2, lazyGridPositionedItem4.getMainAxisSize());
            } else {
                i12 += mainAxisSize2;
                mainAxisSize2 = lazyGridPositionedItem4.getMainAxisSize();
                i11 = line2;
            }
            ItemInfo itemInfoCreateItemInfo2 = createItemInfo(lazyGridPositionedItem4, i5 + i12);
            this.keyToItemInfoMap.put(lazyGridPositionedItem4.getKey(), itemInfoCreateItemInfo2);
            startAnimationsIfNeeded(lazyGridPositionedItem4, itemInfoCreateItemInfo2);
        }
        Iterator it2 = this.movingAwayKeys.iterator();
        while (it2.hasNext()) {
            Object next = it2.next();
            ItemInfo itemInfo2 = (ItemInfo) MapsKt.getValue(this.keyToItemInfoMap, next);
            Integer num2 = this.keyToIndexMap.get(next);
            List<PlaceableInfo> placeables = itemInfo2.getPlaceables();
            int size5 = placeables.size();
            int i14 = 0;
            while (true) {
                if (i14 >= size5) {
                    z = false;
                    break;
                } else {
                    if (placeables.get(i14).getInProgress()) {
                        z = true;
                        break;
                    }
                    i14++;
                }
            }
            if (itemInfo2.getPlaceables().isEmpty() || num2 == null || ((!z && Intrinsics.areEqual(num2, map.get(next))) || (!z && !isWithinBounds(itemInfo2, i5)))) {
                it = it2;
                this.keyToItemInfoMap.remove(next);
            } else {
                int iM826constructorimpl = ItemIndex.m826constructorimpl(num2.intValue());
                if (this.isVertical) {
                    jM4355fixedHeightOenEA2s = Constraints.INSTANCE.m4356fixedWidthOenEA2s(itemInfo2.getCrossAxisSize());
                } else {
                    jM4355fixedHeightOenEA2s = Constraints.INSTANCE.m4355fixedHeightOenEA2s(itemInfo2.getCrossAxisSize());
                }
                it = it2;
                LazyGridMeasuredItem lazyGridMeasuredItemM867getAndMeasureednRnyU$default = LazyMeasuredItemProvider.m867getAndMeasureednRnyU$default(itemProvider, iM826constructorimpl, 0, jM4355fixedHeightOenEA2s, 2, null);
                if (num2.intValue() < this.firstVisibleIndex) {
                    this.movingAwayToStartBound.add(lazyGridMeasuredItemM867getAndMeasureednRnyU$default);
                } else {
                    this.movingAwayToEndBound.add(lazyGridMeasuredItemM867getAndMeasureednRnyU$default);
                }
            }
            it2 = it;
        }
        List<LazyGridMeasuredItem> list5 = this.movingAwayToStartBound;
        if (list5.size() > 1) {
            CollectionsKt.sortWith(list5, new Comparator() { // from class: androidx.compose.foundation.lazy.grid.LazyGridItemPlacementAnimator$onMeasured$$inlined$sortByDescending$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues((Integer) this.this$0.keyToIndexMap.get(((LazyGridMeasuredItem) t2).getKey()), (Integer) this.this$0.keyToIndexMap.get(((LazyGridMeasuredItem) t).getKey()));
                }
            });
        }
        List<LazyGridMeasuredItem> list6 = this.movingAwayToStartBound;
        int size6 = list6.size();
        int i15 = -1;
        int i16 = 0;
        int mainAxisSize3 = 0;
        for (int i17 = 0; i17 < size6; i17++) {
            LazyGridMeasuredItem lazyGridMeasuredItem = list6.get(i17);
            int iM865getLineIndexOfItem_Ze7BM = spanLayoutProvider.m865getLineIndexOfItem_Ze7BM(lazyGridMeasuredItem.getIndex());
            if (iM865getLineIndexOfItem_Ze7BM != -1 && iM865getLineIndexOfItem_Ze7BM == i15) {
                mainAxisSize3 = Math.max(mainAxisSize3, lazyGridMeasuredItem.getMainAxisSize());
            } else {
                i16 += mainAxisSize3;
                mainAxisSize3 = lazyGridMeasuredItem.getMainAxisSize();
                i15 = iM865getLineIndexOfItem_Ze7BM;
            }
            int mainAxisSize4 = (0 - i16) - lazyGridMeasuredItem.getMainAxisSize();
            ItemInfo itemInfo3 = (ItemInfo) MapsKt.getValue(this.keyToItemInfoMap, lazyGridMeasuredItem.getKey());
            LazyGridPositionedItem lazyGridPositionedItemPosition = lazyGridMeasuredItem.position(mainAxisSize4, itemInfo3.getCrossAxisOffset(), layoutWidth, layoutHeight, -1, -1);
            positionedItems.add(lazyGridPositionedItemPosition);
            startAnimationsIfNeeded(lazyGridPositionedItemPosition, itemInfo3);
        }
        List<LazyGridMeasuredItem> list7 = this.movingAwayToEndBound;
        if (list7.size() > 1) {
            CollectionsKt.sortWith(list7, new Comparator() { // from class: androidx.compose.foundation.lazy.grid.LazyGridItemPlacementAnimator$onMeasured$$inlined$sortBy$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues((Integer) this.this$0.keyToIndexMap.get(((LazyGridMeasuredItem) t).getKey()), (Integer) this.this$0.keyToIndexMap.get(((LazyGridMeasuredItem) t2).getKey()));
                }
            });
        }
        List<LazyGridMeasuredItem> list8 = this.movingAwayToEndBound;
        int size7 = list8.size();
        int i18 = -1;
        int i19 = 0;
        int mainAxisSize5 = 0;
        for (int i20 = 0; i20 < size7; i20++) {
            LazyGridMeasuredItem lazyGridMeasuredItem2 = list8.get(i20);
            int iM865getLineIndexOfItem_Ze7BM2 = spanLayoutProvider.m865getLineIndexOfItem_Ze7BM(lazyGridMeasuredItem2.getIndex());
            if (iM865getLineIndexOfItem_Ze7BM2 != -1 && iM865getLineIndexOfItem_Ze7BM2 == i18) {
                mainAxisSize5 = Math.max(mainAxisSize5, lazyGridMeasuredItem2.getMainAxisSize());
            } else {
                i19 += mainAxisSize5;
                mainAxisSize5 = lazyGridMeasuredItem2.getMainAxisSize();
                i18 = iM865getLineIndexOfItem_Ze7BM2;
            }
            ItemInfo itemInfo4 = (ItemInfo) MapsKt.getValue(this.keyToItemInfoMap, lazyGridMeasuredItem2.getKey());
            LazyGridPositionedItem lazyGridPositionedItemPosition2 = lazyGridMeasuredItem2.position(i5 + i19, itemInfo4.getCrossAxisOffset(), layoutWidth, layoutHeight, -1, -1);
            positionedItems.add(lazyGridPositionedItemPosition2);
            startAnimationsIfNeeded(lazyGridPositionedItemPosition2, itemInfo4);
        }
        this.movingInFromStartBound.clear();
        this.movingInFromEndBound.clear();
        this.movingAwayToStartBound.clear();
        this.movingAwayToEndBound.clear();
        this.movingAwayKeys.clear();
    }
}
