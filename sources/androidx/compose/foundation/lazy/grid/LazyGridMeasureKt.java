package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProviderKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: LazyGridMeasure.kt */
@Metadata(d1 = {"\u0000 \u0001\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aR\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\nH\u0083\bø\u0001\u0000\u001a\u008c\u0001\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020#H\u0002\u001aê\u0001\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010'\u001a\u00020(2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u000e2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\u000e2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u000f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020#2\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\u0006\u0010\u0003\u001a\u00020\u00042/\u00107\u001a+\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020:0\n¢\u0006\u0002\b;\u0012\u0004\u0012\u00020<08H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b=\u0010>\u001a$\u0010?\u001a\u00020\u0012*\u00020\u00022\u0006\u0010@\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u000eH\u0002\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006A"}, d2 = {"calculateExtraItems", "", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "pinnedItems", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;", "measuredItemProvider", "Landroidx/compose/foundation/lazy/grid/LazyMeasuredItemProvider;", "itemProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;", "itemConstraints", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/grid/ItemIndex;", "Landroidx/compose/ui/unit/Constraints;", "filter", "", "", "calculateItemsOffsets", "", "Landroidx/compose/foundation/lazy/grid/LazyGridPositionedItem;", "lines", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLine;", "itemsBefore", "itemsAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "firstLineScrollOffset", "isVertical", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "measureLazyGrid", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "itemsCount", "measuredLineProvider", "Landroidx/compose/foundation/lazy/grid/LazyMeasuredLineProvider;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenLines", "firstVisibleLineIndex", "Landroidx/compose/foundation/lazy/grid/LineIndex;", "firstVisibleLineScrollOffset", "scrollToBeConsumed", "", CryptoServicesPermission.CONSTRAINTS, "placementAnimator", "Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;", "spanLayoutProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider;", "layout", "Lkotlin/Function3;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyGrid-t5wl_D8", "(ILandroidx/compose/foundation/lazy/grid/LazyGridItemProvider;Landroidx/compose/foundation/lazy/grid/LazyMeasuredLineProvider;Landroidx/compose/foundation/lazy/grid/LazyMeasuredItemProvider;IIIIIIFJZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider;Landroidx/compose/foundation/lazy/layout/LazyLayoutPinnedItemList;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "positionExtraItem", "mainAxisOffset", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyGridMeasureKt {
    private static final int calculateItemsOffsets$reverseAware(int i, boolean z, int i2) {
        return !z ? i : (i2 - i) - 1;
    }

    /* renamed from: measureLazyGrid-t5wl_D8, reason: not valid java name */
    public static final LazyGridMeasureResult m855measureLazyGridt5wl_D8(int i, LazyGridItemProvider itemProvider, LazyMeasuredLineProvider measuredLineProvider, LazyMeasuredItemProvider measuredItemProvider, int i2, int i3, int i4, int i5, int i6, int i7, float f, long j, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density, LazyGridItemPlacementAnimator placementAnimator, LazyGridSpanLayoutProvider spanLayoutProvider, LazyLayoutPinnedItemList pinnedItems, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> layout) {
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        LazyGridMeasuredLine lazyGridMeasuredLine;
        int i13;
        int iM4360constrainWidthK40F9xA;
        int iM4345getMaxHeightimpl;
        List<LazyGridPositionedItem> list;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        ArrayList arrayList;
        float f2;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        LazyGridMeasuredItem[] items;
        LazyGridMeasuredItem lazyGridMeasuredItem;
        int i24;
        int i25;
        int iM874constructorimpl;
        Intrinsics.checkNotNullParameter(itemProvider, "itemProvider");
        Intrinsics.checkNotNullParameter(measuredLineProvider, "measuredLineProvider");
        Intrinsics.checkNotNullParameter(measuredItemProvider, "measuredItemProvider");
        Intrinsics.checkNotNullParameter(density, "density");
        Intrinsics.checkNotNullParameter(placementAnimator, "placementAnimator");
        Intrinsics.checkNotNullParameter(spanLayoutProvider, "spanLayoutProvider");
        Intrinsics.checkNotNullParameter(pinnedItems, "pinnedItems");
        Intrinsics.checkNotNullParameter(layout, "layout");
        if (i3 < 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (i4 < 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (i <= 0) {
            return new LazyGridMeasureResult(null, 0, false, 0.0f, layout.invoke(Integer.valueOf(Constraints.m4348getMinWidthimpl(j)), Integer.valueOf(Constraints.m4347getMinHeightimpl(j)), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$measureLazyGrid$1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope invoke) {
                    Intrinsics.checkNotNullParameter(invoke, "$this$invoke");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }
            }), CollectionsKt.emptyList(), -i3, i2 + i4, 0, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
        }
        int iRoundToInt = MathKt.roundToInt(f);
        int i26 = i7 - iRoundToInt;
        if (LineIndex.m877equalsimpl0(i6, LineIndex.m874constructorimpl(0)) && i26 < 0) {
            iRoundToInt += i26;
            i26 = 0;
        }
        ArrayList arrayList2 = new ArrayList();
        int i27 = -i3;
        int i28 = i27 + (i5 < 0 ? i5 : 0);
        int mainAxisSizeWithSpacings = i26 + i28;
        int i29 = i6;
        while (mainAxisSizeWithSpacings < 0 && i29 - LineIndex.m874constructorimpl(0) > 0) {
            int iM874constructorimpl2 = LineIndex.m874constructorimpl(i29 - 1);
            int i30 = i27;
            LazyGridMeasuredLine lazyGridMeasuredLineM870getAndMeasurebKFJvoY = measuredLineProvider.m870getAndMeasurebKFJvoY(iM874constructorimpl2);
            arrayList2.add(0, lazyGridMeasuredLineM870getAndMeasurebKFJvoY);
            mainAxisSizeWithSpacings += lazyGridMeasuredLineM870getAndMeasurebKFJvoY.getMainAxisSizeWithSpacings();
            i27 = i30;
            i29 = iM874constructorimpl2;
        }
        int i31 = i27;
        if (mainAxisSizeWithSpacings < i28) {
            iRoundToInt += mainAxisSizeWithSpacings;
            mainAxisSizeWithSpacings = i28;
        }
        int i32 = mainAxisSizeWithSpacings - i28;
        int i33 = i2 + i4;
        int i34 = i29;
        int iCoerceAtLeast = RangesKt.coerceAtLeast(i33, 0);
        int mainAxisSizeWithSpacings2 = -i32;
        int size = arrayList2.size();
        int iM874constructorimpl3 = i34;
        int mainAxisSizeWithSpacings3 = i32;
        for (int i35 = 0; i35 < size; i35++) {
            LazyGridMeasuredLine lazyGridMeasuredLine2 = (LazyGridMeasuredLine) arrayList2.get(i35);
            iM874constructorimpl3 = LineIndex.m874constructorimpl(iM874constructorimpl3 + 1);
            mainAxisSizeWithSpacings2 += lazyGridMeasuredLine2.getMainAxisSizeWithSpacings();
        }
        int i36 = mainAxisSizeWithSpacings2;
        int iM874constructorimpl4 = iM874constructorimpl3;
        while (iM874constructorimpl4 < i && (i36 < iCoerceAtLeast || i36 <= 0 || arrayList2.isEmpty())) {
            int i37 = iCoerceAtLeast;
            LazyGridMeasuredLine lazyGridMeasuredLineM870getAndMeasurebKFJvoY2 = measuredLineProvider.m870getAndMeasurebKFJvoY(iM874constructorimpl4);
            if (lazyGridMeasuredLineM870getAndMeasurebKFJvoY2.isEmpty()) {
                break;
            }
            int mainAxisSizeWithSpacings4 = i36 + lazyGridMeasuredLineM870getAndMeasurebKFJvoY2.getMainAxisSizeWithSpacings();
            if (mainAxisSizeWithSpacings4 <= i28) {
                i24 = i28;
                i25 = mainAxisSizeWithSpacings4;
                if (((LazyGridMeasuredItem) ArraysKt.last(lazyGridMeasuredLineM870getAndMeasurebKFJvoY2.getItems())).getIndex() != i - 1) {
                    iM874constructorimpl = LineIndex.m874constructorimpl(iM874constructorimpl4 + 1);
                    mainAxisSizeWithSpacings3 -= lazyGridMeasuredLineM870getAndMeasurebKFJvoY2.getMainAxisSizeWithSpacings();
                }
                iM874constructorimpl4 = LineIndex.m874constructorimpl(iM874constructorimpl4 + 1);
                i34 = iM874constructorimpl;
                iCoerceAtLeast = i37;
                i36 = i25;
                i28 = i24;
            } else {
                i24 = i28;
                i25 = mainAxisSizeWithSpacings4;
            }
            arrayList2.add(lazyGridMeasuredLineM870getAndMeasurebKFJvoY2);
            iM874constructorimpl = i34;
            iM874constructorimpl4 = LineIndex.m874constructorimpl(iM874constructorimpl4 + 1);
            i34 = iM874constructorimpl;
            iCoerceAtLeast = i37;
            i36 = i25;
            i28 = i24;
        }
        if (i36 < i2) {
            int i38 = i2 - i36;
            int i39 = i36 + i38;
            int iM874constructorimpl5 = i34;
            int mainAxisSizeWithSpacings5 = mainAxisSizeWithSpacings3 - i38;
            while (true) {
                if (mainAxisSizeWithSpacings5 >= i3) {
                    i8 = i33;
                    i9 = 0;
                    break;
                }
                if (iM874constructorimpl5 - LineIndex.m874constructorimpl(0) <= 0) {
                    i9 = 0;
                    i8 = i33;
                    break;
                }
                iM874constructorimpl5 = LineIndex.m874constructorimpl(iM874constructorimpl5 - 1);
                int i40 = i33;
                LazyGridMeasuredLine lazyGridMeasuredLineM870getAndMeasurebKFJvoY3 = measuredLineProvider.m870getAndMeasurebKFJvoY(iM874constructorimpl5);
                arrayList2.add(0, lazyGridMeasuredLineM870getAndMeasurebKFJvoY3);
                mainAxisSizeWithSpacings5 += lazyGridMeasuredLineM870getAndMeasurebKFJvoY3.getMainAxisSizeWithSpacings();
                i33 = i40;
            }
            iRoundToInt += i38;
            if (mainAxisSizeWithSpacings5 < 0) {
                iRoundToInt += mainAxisSizeWithSpacings5;
                i39 += mainAxisSizeWithSpacings5;
                i11 = i9;
            } else {
                i11 = mainAxisSizeWithSpacings5;
            }
            i10 = i39;
        } else {
            i8 = i33;
            i9 = 0;
            i10 = i36;
            i11 = mainAxisSizeWithSpacings3;
        }
        float f3 = (MathKt.getSign(MathKt.roundToInt(f)) != MathKt.getSign(iRoundToInt) || Math.abs(MathKt.roundToInt(f)) < Math.abs(iRoundToInt)) ? f : iRoundToInt;
        if (i11 < 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        int i41 = -i11;
        LazyGridMeasuredLine lazyGridMeasuredLine3 = (LazyGridMeasuredLine) CollectionsKt.first((List) arrayList2);
        LazyGridMeasuredItem lazyGridMeasuredItem2 = (LazyGridMeasuredItem) ArraysKt.firstOrNull(lazyGridMeasuredLine3.getItems());
        int index = lazyGridMeasuredItem2 != null ? lazyGridMeasuredItem2.getIndex() : i9;
        LazyGridMeasuredLine lazyGridMeasuredLine4 = (LazyGridMeasuredLine) CollectionsKt.lastOrNull((List) arrayList2);
        int index2 = (lazyGridMeasuredLine4 == null || (items = lazyGridMeasuredLine4.getItems()) == null || (lazyGridMeasuredItem = (LazyGridMeasuredItem) ArraysKt.lastOrNull(items)) == null) ? i9 : lazyGridMeasuredItem.getIndex();
        LazyLayoutPinnedItemList lazyLayoutPinnedItemList = pinnedItems;
        int size2 = lazyLayoutPinnedItemList.size();
        ArrayList arrayListEmptyList = null;
        int i42 = i10;
        List listEmptyList = null;
        int i43 = 0;
        while (i43 < size2) {
            LazyLayoutPinnedItemList.PinnedItem pinnedItem = lazyLayoutPinnedItemList.get(i43);
            int i44 = size2;
            int i45 = i11;
            int i46 = index2;
            int iFindIndexByKey = LazyLayoutItemProviderKt.findIndexByKey(itemProvider, pinnedItem.getKey(), pinnedItem.getIndex());
            if (iFindIndexByKey < 0 || iFindIndexByKey >= index) {
                i17 = i8;
                i18 = index;
                arrayList = arrayList2;
                f2 = f3;
                i19 = i31;
                i20 = i42;
                i21 = i46;
                i22 = i41;
                i23 = i43;
            } else {
                int iM826constructorimpl = ItemIndex.m826constructorimpl(iFindIndexByKey);
                long jM871itemConstraintsHZ0wssc = measuredLineProvider.m871itemConstraintsHZ0wssc(iM826constructorimpl);
                i21 = i46;
                i18 = index;
                arrayList = arrayList2;
                f2 = f3;
                i22 = i41;
                i17 = i8;
                i19 = i31;
                i20 = i42;
                i23 = i43;
                LazyGridMeasuredItem lazyGridMeasuredItemM867getAndMeasureednRnyU$default = LazyMeasuredItemProvider.m867getAndMeasureednRnyU$default(measuredItemProvider, iM826constructorimpl, 0, jM871itemConstraintsHZ0wssc, 2, null);
                ArrayList arrayList3 = listEmptyList == null ? new ArrayList() : listEmptyList;
                arrayList3.add(lazyGridMeasuredItemM867getAndMeasureednRnyU$default);
                listEmptyList = arrayList3;
            }
            i43 = i23 + 1;
            arrayList2 = arrayList;
            f3 = f2;
            i11 = i45;
            i41 = i22;
            i31 = i19;
            index2 = i21;
            index = i18;
            i8 = i17;
            i42 = i20;
            size2 = i44;
        }
        int i47 = i8;
        int i48 = i11;
        int i49 = index2;
        int i50 = index;
        ArrayList arrayList4 = arrayList2;
        float f4 = f3;
        int i51 = i41;
        int i52 = i31;
        int i53 = i42;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List list2 = listEmptyList;
        int size3 = lazyLayoutPinnedItemList.size();
        int i54 = 0;
        while (i54 < size3) {
            LazyLayoutPinnedItemList.PinnedItem pinnedItem2 = lazyLayoutPinnedItemList.get(i54);
            int iFindIndexByKey2 = LazyLayoutItemProviderKt.findIndexByKey(itemProvider, pinnedItem2.getKey(), pinnedItem2.getIndex());
            int i55 = i49;
            if (i55 + 1 > iFindIndexByKey2 || iFindIndexByKey2 >= i) {
                i14 = i54;
                i15 = i55;
                i16 = size3;
            } else {
                int iM826constructorimpl2 = ItemIndex.m826constructorimpl(iFindIndexByKey2);
                i14 = i54;
                i15 = i55;
                i16 = size3;
                LazyGridMeasuredItem lazyGridMeasuredItemM867getAndMeasureednRnyU$default2 = LazyMeasuredItemProvider.m867getAndMeasureednRnyU$default(measuredItemProvider, iM826constructorimpl2, 0, measuredLineProvider.m871itemConstraintsHZ0wssc(iM826constructorimpl2), 2, null);
                if (arrayListEmptyList == null) {
                    arrayListEmptyList = new ArrayList();
                }
                List list3 = arrayListEmptyList;
                list3.add(lazyGridMeasuredItemM867getAndMeasureednRnyU$default2);
                arrayListEmptyList = list3;
            }
            i54 = i14 + 1;
            size3 = i16;
            i49 = i15;
        }
        int i56 = i49;
        if (arrayListEmptyList == null) {
            arrayListEmptyList = CollectionsKt.emptyList();
        }
        List list4 = arrayListEmptyList;
        if (i3 > 0 || i5 < 0) {
            int size4 = arrayList4.size();
            i12 = i48;
            int i57 = 0;
            while (i57 < size4) {
                int mainAxisSizeWithSpacings6 = ((LazyGridMeasuredLine) arrayList4.get(i57)).getMainAxisSizeWithSpacings();
                if (i12 == 0 || mainAxisSizeWithSpacings6 > i12 || i57 == CollectionsKt.getLastIndex(arrayList4)) {
                    break;
                }
                i12 -= mainAxisSizeWithSpacings6;
                i57++;
                lazyGridMeasuredLine3 = (LazyGridMeasuredLine) arrayList4.get(i57);
            }
            lazyGridMeasuredLine = lazyGridMeasuredLine3;
        } else {
            lazyGridMeasuredLine = lazyGridMeasuredLine3;
            i12 = i48;
        }
        if (z) {
            iM4360constrainWidthK40F9xA = Constraints.m4346getMaxWidthimpl(j);
            i13 = i53;
        } else {
            i13 = i53;
            iM4360constrainWidthK40F9xA = ConstraintsKt.m4360constrainWidthK40F9xA(j, i13);
        }
        if (z) {
            iM4345getMaxHeightimpl = ConstraintsKt.m4359constrainHeightK40F9xA(j, i13);
        } else {
            iM4345getMaxHeightimpl = Constraints.m4345getMaxHeightimpl(j);
        }
        final List<LazyGridPositionedItem> listCalculateItemsOffsets = calculateItemsOffsets(arrayList4, list2, list4, iM4360constrainWidthK40F9xA, iM4345getMaxHeightimpl, i13, i2, i51, z, vertical, horizontal, z2, density);
        placementAnimator.onMeasured((int) f4, iM4360constrainWidthK40F9xA, iM4345getMaxHeightimpl, listCalculateItemsOffsets, measuredItemProvider, spanLayoutProvider);
        boolean z3 = i56 != i + (-1) || i13 > i2;
        MeasureResult measureResultInvoke = layout.invoke(Integer.valueOf(iM4360constrainWidthK40F9xA), Integer.valueOf(iM4345getMaxHeightimpl), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridMeasureKt$measureLazyGrid$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope invoke) {
                Intrinsics.checkNotNullParameter(invoke, "$this$invoke");
                List<LazyGridPositionedItem> list5 = listCalculateItemsOffsets;
                int size5 = list5.size();
                for (int i58 = 0; i58 < size5; i58++) {
                    list5.get(i58).place(invoke);
                }
            }
        });
        if (list2.isEmpty() && list4.isEmpty()) {
            list = listCalculateItemsOffsets;
        } else {
            ArrayList arrayList5 = new ArrayList(listCalculateItemsOffsets.size());
            int size5 = listCalculateItemsOffsets.size();
            int i58 = 0;
            while (i58 < size5) {
                LazyGridPositionedItem lazyGridPositionedItem = listCalculateItemsOffsets.get(i58);
                int index3 = lazyGridPositionedItem.getIndex();
                int i59 = i50;
                if (i59 <= index3 && index3 <= i56) {
                    arrayList5.add(lazyGridPositionedItem);
                }
                i58++;
                i50 = i59;
            }
            list = arrayList5;
        }
        return new LazyGridMeasureResult(lazyGridMeasuredLine, i12, z3, f4, measureResultInvoke, list, i52, i47, i, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
    }

    private static final List<LazyGridMeasuredItem> calculateExtraItems(LazyLayoutPinnedItemList lazyLayoutPinnedItemList, LazyMeasuredItemProvider lazyMeasuredItemProvider, LazyGridItemProvider lazyGridItemProvider, Function1<? super ItemIndex, Constraints> function1, Function1<? super Integer, Boolean> function12) {
        LazyLayoutPinnedItemList lazyLayoutPinnedItemList2 = lazyLayoutPinnedItemList;
        int size = lazyLayoutPinnedItemList2.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            LazyLayoutPinnedItemList.PinnedItem pinnedItem = lazyLayoutPinnedItemList2.get(i);
            int iFindIndexByKey = LazyLayoutItemProviderKt.findIndexByKey(lazyGridItemProvider, pinnedItem.getKey(), pinnedItem.getIndex());
            if (function12.invoke(Integer.valueOf(iFindIndexByKey)).booleanValue()) {
                int iM826constructorimpl = ItemIndex.m826constructorimpl(iFindIndexByKey);
                LazyGridMeasuredItem lazyGridMeasuredItemM867getAndMeasureednRnyU$default = LazyMeasuredItemProvider.m867getAndMeasureednRnyU$default(lazyMeasuredItemProvider, iM826constructorimpl, 0, function1.invoke(ItemIndex.m824boximpl(iM826constructorimpl)).getValue(), 2, null);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lazyGridMeasuredItemM867getAndMeasureednRnyU$default);
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyGridPositionedItem> calculateItemsOffsets(List<LazyGridMeasuredLine> list, List<LazyGridMeasuredItem> list2, List<LazyGridMeasuredItem> list3, int i, int i2, int i3, int i4, int i5, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density) {
        int i6 = z ? i2 : i;
        boolean z3 = i3 < Math.min(i6, i4);
        if (z3 && i5 != 0) {
            throw new IllegalStateException("Check failed.".toString());
        }
        int size = list.size();
        int length = 0;
        for (int i7 = 0; i7 < size; i7++) {
            length += list.get(i7).getItems().length;
        }
        ArrayList arrayList = new ArrayList(length);
        if (z3) {
            if (!list2.isEmpty() || !list3.isEmpty()) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            int size2 = list.size();
            int[] iArr = new int[size2];
            for (int i8 = 0; i8 < size2; i8++) {
                iArr[i8] = list.get(calculateItemsOffsets$reverseAware(i8, z2, size2)).getMainAxisSize();
            }
            int[] iArr2 = new int[size2];
            for (int i9 = 0; i9 < size2; i9++) {
                iArr2[i9] = 0;
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
                    int mainAxisSize = iArr2[first];
                    LazyGridMeasuredLine lazyGridMeasuredLine = list.get(calculateItemsOffsets$reverseAware(first, z2, size2));
                    if (z2) {
                        mainAxisSize = (i6 - mainAxisSize) - lazyGridMeasuredLine.getMainAxisSize();
                    }
                    arrayList.addAll(lazyGridMeasuredLine.position(mainAxisSize, i, i2));
                    if (first == last) {
                        break;
                    }
                    first += step;
                }
            }
        } else {
            int size3 = list2.size();
            int mainAxisSizeWithSpacings = i5;
            for (int i10 = 0; i10 < size3; i10++) {
                LazyGridMeasuredItem lazyGridMeasuredItem = list2.get(i10);
                mainAxisSizeWithSpacings -= lazyGridMeasuredItem.getMainAxisSizeWithSpacings();
                arrayList.add(positionExtraItem(lazyGridMeasuredItem, mainAxisSizeWithSpacings, i, i2));
            }
            int size4 = list.size();
            int mainAxisSizeWithSpacings2 = i5;
            for (int i11 = 0; i11 < size4; i11++) {
                LazyGridMeasuredLine lazyGridMeasuredLine2 = list.get(i11);
                arrayList.addAll(lazyGridMeasuredLine2.position(mainAxisSizeWithSpacings2, i, i2));
                mainAxisSizeWithSpacings2 += lazyGridMeasuredLine2.getMainAxisSizeWithSpacings();
            }
            int size5 = list3.size();
            for (int i12 = 0; i12 < size5; i12++) {
                LazyGridMeasuredItem lazyGridMeasuredItem2 = list3.get(i12);
                arrayList.add(positionExtraItem(lazyGridMeasuredItem2, mainAxisSizeWithSpacings2, i, i2));
                mainAxisSizeWithSpacings2 += lazyGridMeasuredItem2.getMainAxisSizeWithSpacings();
            }
        }
        return arrayList;
    }

    private static final LazyGridPositionedItem positionExtraItem(LazyGridMeasuredItem lazyGridMeasuredItem, int i, int i2, int i3) {
        return lazyGridMeasuredItem.position(i, 0, i2, i3, 0, 0);
    }
}
