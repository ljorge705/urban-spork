package androidx.compose.foundation.lazy;

import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import com.clevertap.android.sdk.leanplum.Constants;
import java.util.ArrayList;
import java.util.Comparator;
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

/* compiled from: LazyListItemPlacementAnimator.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u00162\b\b\u0002\u0010\u001e\u001a\u00020\bH\u0002J;\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u00012\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\u0019ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b%\u0010&J4\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\b2\u0006\u0010+\u001a\u00020\b2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00160\u00122\u0006\u0010-\u001a\u00020.J\u0006\u0010/\u001a\u00020(J\u0018\u00100\u001a\u00020(2\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u00101\u001a\u00020\rH\u0002J\u0014\u00102\u001a\u00020\u0005*\u00020\r2\u0006\u00103\u001a\u00020\bH\u0002J\u001c\u00104\u001a\u00020\u0019*\u00020\bH\u0002ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b5\u00106R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00010\u000fj\b\u0012\u0004\u0012\u00020\u0001`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\b*\u00020\u00198BX\u0082\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00067"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListItemPlacementAnimator;", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "isVertical", "", "(Lkotlinx/coroutines/CoroutineScope;Z)V", "firstVisibleIndex", "", "keyToIndexMap", "", "keyToItemInfoMap", "", "Landroidx/compose/foundation/lazy/ItemInfo;", "movingAwayKeys", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "movingAwayToEndBound", "", "Landroidx/compose/foundation/lazy/LazyMeasuredItem;", "movingAwayToStartBound", "movingInFromEndBound", "Landroidx/compose/foundation/lazy/LazyListPositionedItem;", "movingInFromStartBound", "mainAxis", "Landroidx/compose/ui/unit/IntOffset;", "getMainAxis--gyyYBs", "(J)I", "createItemInfo", Constants.IAP_ITEM_PARAM, "mainAxisOffset", "getAnimatedOffset", com.clevertap.android.sdk.Constants.KEY_KEY, "placeableIndex", "minOffset", "maxOffset", "rawOffset", "getAnimatedOffset-YT5a7pE", "(Ljava/lang/Object;IIIJ)J", "onMeasured", "", "consumedScroll", "layoutWidth", "layoutHeight", "positionedItems", "itemProvider", "Landroidx/compose/foundation/lazy/LazyMeasuredItemProvider;", "reset", "startAnimationsIfNeeded", "itemInfo", "isWithinBounds", "mainAxisLayoutSize", "toOffset", "toOffset-Bjo55l4", "(I)J", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyListItemPlacementAnimator {
    private int firstVisibleIndex;
    private final boolean isVertical;
    private Map<Object, Integer> keyToIndexMap;
    private final Map<Object, ItemInfo> keyToItemInfoMap;
    private final LinkedHashSet<Object> movingAwayKeys;
    private final List<LazyMeasuredItem> movingAwayToEndBound;
    private final List<LazyMeasuredItem> movingAwayToStartBound;
    private final List<LazyListPositionedItem> movingInFromEndBound;
    private final List<LazyListPositionedItem> movingInFromStartBound;
    private final CoroutineScope scope;

    public LazyListItemPlacementAnimator(CoroutineScope scope, boolean z) {
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
    public final long m795getAnimatedOffsetYT5a7pE(Object key, int placeableIndex, int minOffset, int maxOffset, long rawOffset) {
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
        if (placeableInfo.getInProgress() && ((m793getMainAxisgyyYBs(jIntOffset2) <= minOffset && m793getMainAxisgyyYBs(jIntOffset) <= minOffset) || (m793getMainAxisgyyYBs(jIntOffset2) >= maxOffset && m793getMainAxisgyyYBs(jIntOffset) >= maxOffset))) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new LazyListItemPlacementAnimator$getAnimatedOffset$1(placeableInfo, null), 3, null);
        }
        return jIntOffset;
    }

    public final void reset() {
        this.keyToItemInfoMap.clear();
        this.keyToIndexMap = MapsKt.emptyMap();
        this.firstVisibleIndex = -1;
    }

    static /* synthetic */ ItemInfo createItemInfo$default(LazyListItemPlacementAnimator lazyListItemPlacementAnimator, LazyListPositionedItem lazyListPositionedItem, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = lazyListItemPlacementAnimator.m793getMainAxisgyyYBs(lazyListPositionedItem.m804getOffsetBjo55l4(0));
        }
        return lazyListItemPlacementAnimator.createItemInfo(lazyListPositionedItem, i);
    }

    private final ItemInfo createItemInfo(LazyListPositionedItem item, int mainAxisOffset) {
        long jM4504copyiSbpLlY$default;
        ItemInfo itemInfo = new ItemInfo();
        long jM804getOffsetBjo55l4 = item.m804getOffsetBjo55l4(0);
        if (this.isVertical) {
            jM4504copyiSbpLlY$default = IntOffset.m4504copyiSbpLlY$default(jM804getOffsetBjo55l4, 0, mainAxisOffset, 1, null);
        } else {
            jM4504copyiSbpLlY$default = IntOffset.m4504copyiSbpLlY$default(jM804getOffsetBjo55l4, mainAxisOffset, 0, 2, null);
        }
        int placeablesCount = item.getPlaceablesCount();
        for (int i = 0; i < placeablesCount; i++) {
            long jM804getOffsetBjo55l42 = item.m804getOffsetBjo55l4(i);
            long jIntOffset = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jM804getOffsetBjo55l42) - IntOffset.m4508getXimpl(jM804getOffsetBjo55l4), IntOffset.m4509getYimpl(jM804getOffsetBjo55l42) - IntOffset.m4509getYimpl(jM804getOffsetBjo55l4));
            itemInfo.getPlaceables().add(new PlaceableInfo(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jM4504copyiSbpLlY$default) + IntOffset.m4508getXimpl(jIntOffset), IntOffset.m4509getYimpl(jM4504copyiSbpLlY$default) + IntOffset.m4509getYimpl(jIntOffset)), item.getMainAxisSize(i), null));
        }
        return itemInfo;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void startAnimationsIfNeeded(androidx.compose.foundation.lazy.LazyListPositionedItem r18, androidx.compose.foundation.lazy.ItemInfo r19) {
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
            long r4 = r0.m804getOffsetBjo55l4(r1)
            java.util.List r2 = r19.getPlaceables()
            androidx.compose.foundation.lazy.PlaceableInfo r6 = new androidx.compose.foundation.lazy.PlaceableInfo
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
            androidx.compose.foundation.lazy.PlaceableInfo r5 = (androidx.compose.foundation.lazy.PlaceableInfo) r5
            long r6 = r5.getTargetOffset()
            long r8 = r19.getNotAnimatableDelta()
            int r10 = androidx.compose.ui.unit.IntOffset.m4508getXimpl(r6)
            int r11 = androidx.compose.ui.unit.IntOffset.m4508getXimpl(r8)
            int r10 = r10 + r11
            int r6 = androidx.compose.ui.unit.IntOffset.m4509getYimpl(r6)
            int r7 = androidx.compose.ui.unit.IntOffset.m4509getYimpl(r8)
            int r6 = r6 + r7
            long r6 = androidx.compose.ui.unit.IntOffsetKt.IntOffset(r10, r6)
            long r8 = r0.m804getOffsetBjo55l4(r4)
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
            r5.m814setTargetOffsetgyyYBs(r6)
            if (r10 == 0) goto Lda
            r6 = 1
            r5.setInProgress(r6)
            r6 = r17
            kotlinx.coroutines.CoroutineScope r11 = r6.scope
            r12 = 0
            r13 = 0
            androidx.compose.foundation.lazy.LazyListItemPlacementAnimator$startAnimationsIfNeeded$1$1 r7 = new androidx.compose.foundation.lazy.LazyListItemPlacementAnimator$startAnimationsIfNeeded$1$1
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.LazyListItemPlacementAnimator.startAnimationsIfNeeded(androidx.compose.foundation.lazy.LazyListPositionedItem, androidx.compose.foundation.lazy.ItemInfo):void");
    }

    private final boolean isWithinBounds(ItemInfo itemInfo, int i) {
        List<PlaceableInfo> placeables = itemInfo.getPlaceables();
        int size = placeables.size();
        for (int i2 = 0; i2 < size; i2++) {
            PlaceableInfo placeableInfo = placeables.get(i2);
            long targetOffset = placeableInfo.getTargetOffset();
            long notAnimatableDelta = itemInfo.getNotAnimatableDelta();
            long jIntOffset = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(targetOffset) + IntOffset.m4508getXimpl(notAnimatableDelta), IntOffset.m4509getYimpl(targetOffset) + IntOffset.m4509getYimpl(notAnimatableDelta));
            if (m793getMainAxisgyyYBs(jIntOffset) + placeableInfo.getMainAxisSize() > 0 && m793getMainAxisgyyYBs(jIntOffset) < i) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: toOffset-Bjo55l4, reason: not valid java name */
    private final long m794toOffsetBjo55l4(int i) {
        boolean z = this.isVertical;
        int i2 = z ? 0 : i;
        if (!z) {
            i = 0;
        }
        return IntOffsetKt.IntOffset(i2, i);
    }

    /* renamed from: getMainAxis--gyyYBs, reason: not valid java name */
    private final int m793getMainAxisgyyYBs(long j) {
        return this.isVertical ? IntOffset.m4509getYimpl(j) : IntOffset.m4508getXimpl(j);
    }

    public final void onMeasured(int consumedScroll, int layoutWidth, int layoutHeight, List<LazyListPositionedItem> positionedItems, LazyMeasuredItemProvider itemProvider) {
        boolean z;
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(positionedItems, "positionedItems");
        Intrinsics.checkNotNullParameter(itemProvider, "itemProvider");
        int size = positionedItems.size();
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
        LazyListPositionedItem lazyListPositionedItem = (LazyListPositionedItem) CollectionsKt.firstOrNull((List) positionedItems);
        this.firstVisibleIndex = lazyListPositionedItem != null ? lazyListPositionedItem.getIndex() : 0;
        final Map<Object, Integer> map = this.keyToIndexMap;
        this.keyToIndexMap = itemProvider.getKeyToIndexMap();
        int i5 = this.isVertical ? layoutHeight : layoutWidth;
        long jM794toOffsetBjo55l4 = m794toOffsetBjo55l4(consumedScroll);
        this.movingAwayKeys.addAll(this.keyToItemInfoMap.keySet());
        int size2 = positionedItems.size();
        int i6 = 0;
        while (i6 < size2) {
            LazyListPositionedItem lazyListPositionedItem2 = positionedItems.get(i6);
            this.movingAwayKeys.remove(lazyListPositionedItem2.getKey());
            if (lazyListPositionedItem2.getHasAnimations()) {
                ItemInfo itemInfo = this.keyToItemInfoMap.get(lazyListPositionedItem2.getKey());
                if (itemInfo == null) {
                    Integer num = map.get(lazyListPositionedItem2.getKey());
                    if (num != null && lazyListPositionedItem2.getIndex() != num.intValue()) {
                        if (num.intValue() < i4) {
                            this.movingInFromStartBound.add(lazyListPositionedItem2);
                        } else {
                            this.movingInFromEndBound.add(lazyListPositionedItem2);
                        }
                        i = i4;
                        i2 = size2;
                    } else {
                        i = i4;
                        i2 = size2;
                        this.keyToItemInfoMap.put(lazyListPositionedItem2.getKey(), createItemInfo$default(this, lazyListPositionedItem2, 0, 2, null));
                    }
                } else {
                    i = i4;
                    i2 = size2;
                    long notAnimatableDelta = itemInfo.getNotAnimatableDelta();
                    itemInfo.m792setNotAnimatableDeltagyyYBs(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(notAnimatableDelta) + IntOffset.m4508getXimpl(jM794toOffsetBjo55l4), IntOffset.m4509getYimpl(notAnimatableDelta) + IntOffset.m4509getYimpl(jM794toOffsetBjo55l4)));
                    startAnimationsIfNeeded(lazyListPositionedItem2, itemInfo);
                }
            } else {
                i = i4;
                i2 = size2;
                this.keyToItemInfoMap.remove(lazyListPositionedItem2.getKey());
            }
            i6++;
            size2 = i2;
            i4 = i;
        }
        List<LazyListPositionedItem> list = this.movingInFromStartBound;
        if (list.size() > 1) {
            CollectionsKt.sortWith(list, new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemPlacementAnimator$onMeasured$$inlined$sortByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues((Integer) map.get(((LazyListPositionedItem) t2).getKey()), (Integer) map.get(((LazyListPositionedItem) t).getKey()));
                }
            });
        }
        List<LazyListPositionedItem> list2 = this.movingInFromStartBound;
        int size3 = list2.size();
        int size4 = 0;
        for (int i7 = 0; i7 < size3; i7++) {
            LazyListPositionedItem lazyListPositionedItem3 = list2.get(i7);
            int size5 = (0 - size4) - lazyListPositionedItem3.getSize();
            size4 += lazyListPositionedItem3.getSize();
            ItemInfo itemInfoCreateItemInfo = createItemInfo(lazyListPositionedItem3, size5);
            this.keyToItemInfoMap.put(lazyListPositionedItem3.getKey(), itemInfoCreateItemInfo);
            startAnimationsIfNeeded(lazyListPositionedItem3, itemInfoCreateItemInfo);
        }
        List<LazyListPositionedItem> list3 = this.movingInFromEndBound;
        if (list3.size() > 1) {
            CollectionsKt.sortWith(list3, new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemPlacementAnimator$onMeasured$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues((Integer) map.get(((LazyListPositionedItem) t).getKey()), (Integer) map.get(((LazyListPositionedItem) t2).getKey()));
                }
            });
        }
        List<LazyListPositionedItem> list4 = this.movingInFromEndBound;
        int size6 = list4.size();
        int size7 = 0;
        for (int i8 = 0; i8 < size6; i8++) {
            LazyListPositionedItem lazyListPositionedItem4 = list4.get(i8);
            int i9 = i5 + size7;
            size7 += lazyListPositionedItem4.getSize();
            ItemInfo itemInfoCreateItemInfo2 = createItemInfo(lazyListPositionedItem4, i9);
            this.keyToItemInfoMap.put(lazyListPositionedItem4.getKey(), itemInfoCreateItemInfo2);
            startAnimationsIfNeeded(lazyListPositionedItem4, itemInfoCreateItemInfo2);
        }
        for (Object obj : this.movingAwayKeys) {
            ItemInfo itemInfo2 = (ItemInfo) MapsKt.getValue(this.keyToItemInfoMap, obj);
            Integer num2 = this.keyToIndexMap.get(obj);
            List<PlaceableInfo> placeables = itemInfo2.getPlaceables();
            int size8 = placeables.size();
            int i10 = 0;
            while (true) {
                if (i10 >= size8) {
                    z = false;
                    break;
                } else {
                    if (placeables.get(i10).getInProgress()) {
                        z = true;
                        break;
                    }
                    i10++;
                }
            }
            if (itemInfo2.getPlaceables().isEmpty() || num2 == null || ((!z && Intrinsics.areEqual(num2, map.get(obj))) || (!z && !isWithinBounds(itemInfo2, i5)))) {
                this.keyToItemInfoMap.remove(obj);
            } else {
                LazyMeasuredItem lazyMeasuredItemM811getAndMeasureZjPyQlc = itemProvider.m811getAndMeasureZjPyQlc(DataIndex.m779constructorimpl(num2.intValue()));
                if (num2.intValue() < this.firstVisibleIndex) {
                    this.movingAwayToStartBound.add(lazyMeasuredItemM811getAndMeasureZjPyQlc);
                } else {
                    this.movingAwayToEndBound.add(lazyMeasuredItemM811getAndMeasureZjPyQlc);
                }
            }
        }
        List<LazyMeasuredItem> list5 = this.movingAwayToStartBound;
        if (list5.size() > 1) {
            CollectionsKt.sortWith(list5, new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemPlacementAnimator$onMeasured$$inlined$sortByDescending$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues((Integer) this.this$0.keyToIndexMap.get(((LazyMeasuredItem) t2).getKey()), (Integer) this.this$0.keyToIndexMap.get(((LazyMeasuredItem) t).getKey()));
                }
            });
        }
        List<LazyMeasuredItem> list6 = this.movingAwayToStartBound;
        int size9 = list6.size();
        int size10 = 0;
        for (int i11 = 0; i11 < size9; i11++) {
            LazyMeasuredItem lazyMeasuredItem = list6.get(i11);
            int size11 = (0 - size10) - lazyMeasuredItem.getSize();
            size10 += lazyMeasuredItem.getSize();
            ItemInfo itemInfo3 = (ItemInfo) MapsKt.getValue(this.keyToItemInfoMap, lazyMeasuredItem.getKey());
            LazyListPositionedItem lazyListPositionedItemPosition = lazyMeasuredItem.position(size11, layoutWidth, layoutHeight);
            positionedItems.add(lazyListPositionedItemPosition);
            startAnimationsIfNeeded(lazyListPositionedItemPosition, itemInfo3);
        }
        List<LazyMeasuredItem> list7 = this.movingAwayToEndBound;
        if (list7.size() > 1) {
            CollectionsKt.sortWith(list7, new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemPlacementAnimator$onMeasured$$inlined$sortBy$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues((Integer) this.this$0.keyToIndexMap.get(((LazyMeasuredItem) t).getKey()), (Integer) this.this$0.keyToIndexMap.get(((LazyMeasuredItem) t2).getKey()));
                }
            });
        }
        List<LazyMeasuredItem> list8 = this.movingAwayToEndBound;
        int size12 = list8.size();
        int size13 = 0;
        for (int i12 = 0; i12 < size12; i12++) {
            LazyMeasuredItem lazyMeasuredItem2 = list8.get(i12);
            int i13 = i5 + size13;
            size13 += lazyMeasuredItem2.getSize();
            ItemInfo itemInfo4 = (ItemInfo) MapsKt.getValue(this.keyToItemInfoMap, lazyMeasuredItem2.getKey());
            LazyListPositionedItem lazyListPositionedItemPosition2 = lazyMeasuredItem2.position(i13, layoutWidth, layoutHeight);
            positionedItems.add(lazyListPositionedItemPosition2);
            startAnimationsIfNeeded(lazyListPositionedItemPosition2, itemInfo4);
        }
        this.movingInFromStartBound.clear();
        this.movingInFromEndBound.clear();
        this.movingAwayToStartBound.clear();
        this.movingAwayToEndBound.clear();
        this.movingAwayKeys.clear();
    }
}
