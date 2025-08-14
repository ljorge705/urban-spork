package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutItemProviderKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.leanplum.Constants;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: LazyStaggeredGridMeasure.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\u0017\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082\b\u001aJ\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\t0\f2!\u0010\u000e\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00010\fH\u0083\b\u001a3\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u00020\n2\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0002¢\u0006\u0002\u0010\u0019\u001a\u001c\u0010\u001a\u001a\u00020\u0003*\u00020\n2\u0006\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u000fH\u0002\u001a\u001c\u0010\u001d\u001a\u00020\u000f*\u00020\n2\u0006\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u000fH\u0002\u001a.\u0010 \u001a\u00020\u0003*\u00020!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00030\fH\u0082\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010$\u001a\f\u0010%\u001a\u00020\u000f*\u00020\u0018H\u0002\u001a2\u0010&\u001a\u00020\u000f\"\u0004\b\u0000\u0010'*\b\u0012\u0004\u0012\u0002H'0\u00152\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u0002H'\u0012\u0004\u0012\u00020\u000f0\fH\u0082\b¢\u0006\u0002\u0010(\u001a\u0016\u0010)\u001a\u00020\u000f*\u00020\u00182\b\b\u0002\u0010*\u001a\u00020\u000fH\u0000\u001a!\u0010+\u001a\u00020\u000f*\u00020\u00182\u0006\u0010,\u001a\u00020!H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u0010.\u001a,\u0010/\u001a\u000200*\u00020\n2\u0006\u00101\u001a\u00020\u000f2\u0006\u00102\u001a\u00020\u00182\u0006\u00103\u001a\u00020\u00182\u0006\u00104\u001a\u00020\u0001H\u0003\u001aq\u00105\u001a\u000200*\u0002062\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\u00182\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u00012\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\u000f2\u0006\u0010B\u001a\u00020\u000f2\u0006\u0010C\u001a\u00020\u000f2\u0006\u0010D\u001a\u00020\u000f2\u0006\u0010E\u001a\u00020\u000fH\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bF\u0010G\u001a\u0014\u0010H\u001a\u00020\u0003*\u00020\u00182\u0006\u0010I\u001a\u00020\u000fH\u0002\u001a!\u0010J\u001a\u00020\u0018*\u00020\u00182\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\fH\u0082\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006K"}, d2 = {"DebugLoggingEnabled", "", "debugLog", "", "message", "Lkotlin/Function0;", "", "calculateExtraItems", "", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridPositionedItem;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;", ViewProps.POSITION, "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "filter", "", "Lkotlin/ParameterName;", "name", "itemIndex", "calculatePositionedItems", "measuredItems", "", "Lkotlin/collections/ArrayDeque;", "itemScrollOffsets", "", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;[Lkotlin/collections/ArrayDeque;[I)Ljava/util/List;", "ensureIndicesInRange", "indices", "itemCount", "findPreviousItemIndex", Constants.IAP_ITEM_PARAM, "lane", "forEach", "Landroidx/compose/foundation/lazy/staggeredgrid/SpanRange;", "block", "forEach-nIS5qE8", "(JLkotlin/jvm/functions/Function1;)V", "indexOfMaxValue", "indexOfMinBy", ExifInterface.GPS_DIRECTION_TRUE, "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)I", "indexOfMinValue", "minBound", "maxInRange", "indexRange", "maxInRange-jy6DScQ", "([IJ)I", "measure", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "initialScrollDelta", "initialItemIndices", "initialItemOffsets", "canRestartMeasure", "measureStaggeredGrid", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "itemProvider", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "resolvedSlotSums", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "isVertical", "contentOffset", "Landroidx/compose/ui/unit/IntOffset;", "mainAxisAvailableSize", "mainAxisSpacing", "crossAxisSpacing", "beforeContentPadding", "afterContentPadding", "measureStaggeredGrid-yR9pz_M", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;[IJZJIIIII)Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "offsetBy", "delta", ViewProps.TRANSFORM, "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyStaggeredGridMeasureKt {
    private static final boolean DebugLoggingEnabled = false;

    private static final void debugLog(Function0<String> function0) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: measureStaggeredGrid-yR9pz_M, reason: not valid java name */
    public static final LazyStaggeredGridMeasureResult m909measureStaggeredGridyR9pz_M(LazyLayoutMeasureScope measureStaggeredGrid, LazyStaggeredGridState state, LazyStaggeredGridItemProvider itemProvider, int[] resolvedSlotSums, long j, boolean z, long j2, int i, int i2, int i3, int i4, int i5) {
        T t;
        int iM908maxInRangejy6DScQ;
        T t2;
        int i6;
        Intrinsics.checkNotNullParameter(measureStaggeredGrid, "$this$measureStaggeredGrid");
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(itemProvider, "itemProvider");
        Intrinsics.checkNotNullParameter(resolvedSlotSums, "resolvedSlotSums");
        LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext = new LazyStaggeredGridMeasureContext(state, itemProvider, resolvedSlotSums, j, z, measureStaggeredGrid, i, j2, i4, i5, i2, i3, null);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
        try {
            Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
            try {
                int[] indices = state.getScrollPosition().getIndices();
                int[] offsets = state.getScrollPosition().getOffsets();
                if (indices.length == resolvedSlotSums.length) {
                    t = indices;
                } else {
                    lazyStaggeredGridMeasureContext.getLaneInfo().reset();
                    int length = resolvedSlotSums.length;
                    int[] iArr = new int[length];
                    int i7 = 0;
                    while (i7 < length) {
                        if (i7 >= indices.length || (iM908maxInRangejy6DScQ = indices[i7]) == -1) {
                            iM908maxInRangejy6DScQ = i7 == 0 ? 0 : m908maxInRangejy6DScQ(iArr, SpanRange.m915constructorimpl(0, i7)) + 1;
                        }
                        iArr[i7] = iM908maxInRangejy6DScQ;
                        lazyStaggeredGridMeasureContext.getLaneInfo().setLane(iArr[i7], i7);
                        i7++;
                    }
                    t = iArr;
                }
                objectRef.element = t;
                if (offsets.length == resolvedSlotSums.length) {
                    t2 = offsets;
                } else {
                    int length2 = resolvedSlotSums.length;
                    int[] iArr2 = new int[length2];
                    int i8 = 0;
                    while (i8 < length2) {
                        if (i8 < offsets.length) {
                            i6 = offsets[i8];
                        } else {
                            i6 = i8 == 0 ? 0 : iArr2[i8 - 1];
                        }
                        iArr2[i8] = i6;
                        i8++;
                    }
                    t2 = iArr2;
                }
                objectRef2.element = t2;
                Unit unit = Unit.INSTANCE;
                snapshotCreateNonObservableSnapshot.dispose();
                return measure(lazyStaggeredGridMeasureContext, MathKt.roundToInt(state.getScrollToBeConsumed()), (int[]) objectRef.element, (int[]) objectRef2.element, true);
            } finally {
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
            }
        } catch (Throwable th) {
            snapshotCreateNonObservableSnapshot.dispose();
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:139:0x02d5, code lost:
    
        r1 = indexOfMinValue$default(r4, 0, 1, null);
        r5 = r27[r1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x02df, code lost:
    
        if (r5 != (-1)) goto L142;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x02e1, code lost:
    
        r5 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x02e2, code lost:
    
        r5 = findPreviousItemIndex(r37, r5, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x02e6, code lost:
    
        if (r5 >= 0) goto L156;
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x02e8, code lost:
    
        r7 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x02ee, code lost:
    
        if (measure$lambda$47$misalignedStart(r7, r37, r4, r1) == false) goto L155;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x02f0, code lost:
    
        if (r41 == false) goto L155;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x02f2, code lost:
    
        r37.getLaneInfo().reset();
        r2 = r7.length;
        r3 = new int[r2];
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x02fd, code lost:
    
        if (r5 >= r2) goto L370;
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x02ff, code lost:
    
        r3[r5] = -1;
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0305, code lost:
    
        r2 = r4.length;
        r5 = new int[r2];
        r6 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x0309, code lost:
    
        if (r6 >= r2) goto L371;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x030b, code lost:
    
        r5[r6] = r4[r1];
        r6 = r6 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0319, code lost:
    
        return measure(r37, r26, r3, r5, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x031a, code lost:
    
        r8 = r26;
        r26 = r2;
        r40 = r3;
        r38 = r10;
        r24 = r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0326, code lost:
    
        r8 = r26;
        r7 = r27;
        r13 = r37.m905getSpanRangelOCCd4c(r37.getItemProvider(), r5, r1);
        r1 = r37.getLaneInfo();
        r38 = r10;
        r24 = r11;
        r10 = (int) (r13 & 4294967295L);
        r26 = r2;
        r40 = r3;
        r2 = (int) (r13 >> 32);
        r3 = r10 - r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x034e, code lost:
    
        if (r3 == 1) goto L159;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0350, code lost:
    
        r11 = -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0352, code lost:
    
        r11 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0353, code lost:
    
        r1.setLane(r5, r11);
        r1 = r37.getMeasuredItemProvider().m912getAndMeasurejy6DScQ(r5, r13);
        r11 = m908maxInRangejy6DScQ(r4, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0363, code lost:
    
        if (r3 == 1) goto L163;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0365, code lost:
    
        r14 = r37.getLaneInfo().getGaps(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x036e, code lost:
    
        r14 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x036f, code lost:
    
        if (r2 >= r10) goto L366;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0371, code lost:
    
        r9[r2].addFirst(r1);
        r7[r2] = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0378, code lost:
    
        if (r14 != null) goto L168;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x037a, code lost:
    
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x037c, code lost:
    
        r3 = r14[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x037e, code lost:
    
        r4[r2] = (r1.getSizeWithSpacings() + r11) + r3;
        r2 = r2 + 1;
     */
    /* JADX WARN: Removed duplicated region for block: B:220:0x04d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureResult measure(androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureContext r37, int r38, int[] r39, int[] r40, boolean r41) {
        /*
            Method dump skipped, instructions count: 1846
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt.measure(androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureContext, int, int[], int[], boolean):androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureResult");
    }

    private static final boolean measure$lambda$47$hasSpaceBeforeFirst(int[] iArr, int[] iArr2, LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            int i2 = iArr[i];
            if (iArr2[i] < Math.max(-lazyStaggeredGridMeasureContext.getMainAxisSpacing(), 0) && i2 > 0) {
                return true;
            }
        }
        return false;
    }

    private static final boolean measure$lambda$47$misalignedStart(int[] iArr, LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int[] iArr2, int i) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (findPreviousItemIndex(lazyStaggeredGridMeasureContext, iArr[i2], i2) == -1 && iArr2[i2] != iArr2[i]) {
                return true;
            }
        }
        int length2 = iArr.length;
        for (int i3 = 0; i3 < length2; i3++) {
            if (findPreviousItemIndex(lazyStaggeredGridMeasureContext, iArr[i3], i3) != -1 && iArr2[i3] >= iArr2[i]) {
                return true;
            }
        }
        int lane = lazyStaggeredGridMeasureContext.getLaneInfo().getLane(0);
        return (lane == 0 || lane == -1 || lane == -2) ? false : true;
    }

    private static final List<LazyStaggeredGridPositionedItem> calculatePositionedItems(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, ArrayDeque<LazyStaggeredGridMeasuredItem>[] arrayDequeArr, int[] iArr) {
        int size = 0;
        for (ArrayDeque<LazyStaggeredGridMeasuredItem> arrayDeque : arrayDequeArr) {
            size += arrayDeque.size();
        }
        ArrayList arrayList = new ArrayList(size);
        while (true) {
            for (ArrayDeque<LazyStaggeredGridMeasuredItem> arrayDeque2 : arrayDequeArr) {
                if (!arrayDeque2.isEmpty()) {
                    int length = arrayDequeArr.length;
                    int i = -1;
                    int i2 = Integer.MAX_VALUE;
                    for (int i3 = 0; i3 < length; i3++) {
                        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemFirstOrNull = arrayDequeArr[i3].firstOrNull();
                        int index = lazyStaggeredGridMeasuredItemFirstOrNull != null ? lazyStaggeredGridMeasuredItemFirstOrNull.getIndex() : Integer.MAX_VALUE;
                        if (i2 > index) {
                            i = i3;
                            i2 = index;
                        }
                    }
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemRemoveFirst = arrayDequeArr[i].removeFirst();
                    if (lazyStaggeredGridMeasuredItemRemoveFirst.getLane() == i) {
                        long jM915constructorimpl = SpanRange.m915constructorimpl(lazyStaggeredGridMeasuredItemRemoveFirst.getLane(), lazyStaggeredGridMeasuredItemRemoveFirst.getSpan());
                        int iM908maxInRangejy6DScQ = m908maxInRangejy6DScQ(iArr, jM915constructorimpl);
                        int crossAxisSpacing = i == 0 ? 0 : lazyStaggeredGridMeasureContext.getResolvedSlotSums()[i - 1] + (lazyStaggeredGridMeasureContext.getCrossAxisSpacing() * i);
                        if (!lazyStaggeredGridMeasuredItemRemoveFirst.getPlaceables().isEmpty()) {
                            arrayList.add(lazyStaggeredGridMeasuredItemRemoveFirst.position(i, iM908maxInRangejy6DScQ, crossAxisSpacing));
                            int i4 = (int) (jM915constructorimpl & 4294967295L);
                            for (int i5 = (int) (jM915constructorimpl >> 32); i5 < i4; i5++) {
                                iArr[i5] = lazyStaggeredGridMeasuredItemRemoveFirst.getSizeWithSpacings() + iM908maxInRangejy6DScQ;
                            }
                        }
                    }
                }
            }
            return arrayList;
        }
    }

    private static final List<LazyStaggeredGridPositionedItem> calculateExtraItems(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, Function1<? super LazyStaggeredGridMeasuredItem, LazyStaggeredGridPositionedItem> function1, Function1<? super Integer, Boolean> function12) {
        LazyLayoutPinnedItemList pinnedItems = lazyStaggeredGridMeasureContext.getState().getPinnedItems();
        int size = pinnedItems.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            LazyLayoutPinnedItemList.PinnedItem pinnedItem = pinnedItems.get(i);
            int iFindIndexByKey = LazyLayoutItemProviderKt.findIndexByKey(lazyStaggeredGridMeasureContext.getItemProvider(), pinnedItem.getKey(), pinnedItem.getIndex());
            if (function12.invoke(Integer.valueOf(iFindIndexByKey)).booleanValue()) {
                long jM905getSpanRangelOCCd4c = lazyStaggeredGridMeasureContext.m905getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext.getItemProvider(), iFindIndexByKey, 0);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(function1.invoke(lazyStaggeredGridMeasureContext.getMeasuredItemProvider().m912getAndMeasurejy6DScQ(iFindIndexByKey, jM905getSpanRangelOCCd4c)));
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    /* renamed from: forEach-nIS5qE8, reason: not valid java name */
    private static final void m907forEachnIS5qE8(long j, Function1<? super Integer, Unit> function1) {
        int i = (int) (j & 4294967295L);
        for (int i2 = (int) (j >> 32); i2 < i; i2++) {
            function1.invoke(Integer.valueOf(i2));
        }
    }

    private static final void offsetBy(int[] iArr, int i) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = iArr[i2] + i;
        }
    }

    /* renamed from: maxInRange-jy6DScQ, reason: not valid java name */
    private static final int m908maxInRangejy6DScQ(int[] iArr, long j) {
        int i = (int) (j & 4294967295L);
        int iMax = Integer.MIN_VALUE;
        for (int i2 = (int) (j >> 32); i2 < i; i2++) {
            iMax = Math.max(iMax, iArr[i2]);
        }
        return iMax;
    }

    public static /* synthetic */ int indexOfMinValue$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Integer.MIN_VALUE;
        }
        return indexOfMinValue(iArr, i);
    }

    public static final int indexOfMinValue(int[] iArr, int i) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        int length = iArr.length;
        int i2 = -1;
        int i3 = Integer.MAX_VALUE;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = i + 1;
            int i6 = iArr[i4];
            if (i5 <= i6 && i6 < i3) {
                i2 = i4;
                i3 = i6;
            }
        }
        return i2;
    }

    private static final <T> int indexOfMinBy(T[] tArr, Function1<? super T, Integer> function1) {
        int length = tArr.length;
        int i = -1;
        int i2 = Integer.MAX_VALUE;
        for (int i3 = 0; i3 < length; i3++) {
            int iIntValue = function1.invoke(tArr[i3]).intValue();
            if (i2 > iIntValue) {
                i = i3;
                i2 = iIntValue;
            }
        }
        return i;
    }

    private static final int indexOfMaxValue(int[] iArr) {
        int length = iArr.length;
        int i = -1;
        int i2 = Integer.MIN_VALUE;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = iArr[i3];
            if (i2 < i4) {
                i = i3;
                i2 = i4;
            }
        }
        return i;
    }

    private static final int[] transform(int[] iArr, Function1<? super Integer, Integer> function1) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            iArr[i] = function1.invoke(Integer.valueOf(iArr[i])).intValue();
        }
        return iArr;
    }

    private static final void ensureIndicesInRange(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int[] iArr, int i) {
        int length = iArr.length - 1;
        if (length < 0) {
            return;
        }
        while (true) {
            int i2 = length - 1;
            while (true) {
                if (iArr[length] < i && lazyStaggeredGridMeasureContext.getLaneInfo().assignedToLane(iArr[length], length)) {
                    break;
                } else {
                    iArr[length] = findPreviousItemIndex(lazyStaggeredGridMeasureContext, iArr[length], length);
                }
            }
            if (iArr[length] >= 0 && !lazyStaggeredGridMeasureContext.isFullSpan(lazyStaggeredGridMeasureContext.getItemProvider(), iArr[length])) {
                lazyStaggeredGridMeasureContext.getLaneInfo().setLane(iArr[length], length);
            }
            if (i2 < 0) {
                return;
            } else {
                length = i2;
            }
        }
    }

    private static final int findPreviousItemIndex(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int i, int i2) {
        return lazyStaggeredGridMeasureContext.getLaneInfo().findPreviousItemIndex(i, i2);
    }
}
