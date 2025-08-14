package androidx.compose.foundation.lazy.grid;

import androidx.compose.ui.unit.LayoutDirection;
import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.protocol.SentryTransaction;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* compiled from: LazyGridMeasuredLine.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001BT\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\rø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u0006\u0010\u001d\u001a\u00020\u000bJ$\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\b2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\rR\u000e\u0010\u0011\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0019\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\u001b\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u000e\u0010\u0010\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006#"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLine;", "", "index", "Landroidx/compose/foundation/lazy/grid/LineIndex;", FirebaseAnalytics.Param.ITEMS, "", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", SentryTransaction.JsonKeys.SPANS, "", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "isVertical", "", "slotsPerLine", "", ViewProps.LAYOUT_DIRECTION, "Landroidx/compose/ui/unit/LayoutDirection;", "mainAxisSpacing", "crossAxisSpacing", "(I[Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;Ljava/util/List;ZILandroidx/compose/ui/unit/LayoutDirection;IILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getIndex-hA7yfN8", "()I", "I", "getItems", "()[Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "[Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "mainAxisSize", "getMainAxisSize", "mainAxisSizeWithSpacings", "getMainAxisSizeWithSpacings", "isEmpty", ViewProps.POSITION, "Landroidx/compose/foundation/lazy/grid/LazyGridPositionedItem;", "offset", "layoutWidth", "layoutHeight", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyGridMeasuredLine {
    private final int crossAxisSpacing;
    private final int index;
    private final boolean isVertical;
    private final LazyGridMeasuredItem[] items;
    private final LayoutDirection layoutDirection;
    private final int mainAxisSize;
    private final int mainAxisSizeWithSpacings;
    private final int mainAxisSpacing;
    private final int slotsPerLine;
    private final List<GridItemSpan> spans;

    public /* synthetic */ LazyGridMeasuredLine(int i, LazyGridMeasuredItem[] lazyGridMeasuredItemArr, List list, boolean z, int i2, LayoutDirection layoutDirection, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, lazyGridMeasuredItemArr, list, z, i2, layoutDirection, i3, i4);
    }

    /* renamed from: getIndex-hA7yfN8, reason: not valid java name and from getter */
    public final int getIndex() {
        return this.index;
    }

    public final LazyGridMeasuredItem[] getItems() {
        return this.items;
    }

    public final int getMainAxisSize() {
        return this.mainAxisSize;
    }

    public final int getMainAxisSizeWithSpacings() {
        return this.mainAxisSizeWithSpacings;
    }

    private LazyGridMeasuredLine(int i, LazyGridMeasuredItem[] lazyGridMeasuredItemArr, List<GridItemSpan> list, boolean z, int i2, LayoutDirection layoutDirection, int i3, int i4) {
        this.index = i;
        this.items = lazyGridMeasuredItemArr;
        this.spans = list;
        this.isVertical = z;
        this.slotsPerLine = i2;
        this.layoutDirection = layoutDirection;
        this.mainAxisSpacing = i3;
        this.crossAxisSpacing = i4;
        int iMax = 0;
        for (LazyGridMeasuredItem lazyGridMeasuredItem : lazyGridMeasuredItemArr) {
            iMax = Math.max(iMax, lazyGridMeasuredItem.getMainAxisSize());
        }
        this.mainAxisSize = iMax;
        this.mainAxisSizeWithSpacings = RangesKt.coerceAtLeast(iMax + this.mainAxisSpacing, 0);
    }

    public final boolean isEmpty() {
        return this.items.length == 0;
    }

    public final List<LazyGridPositionedItem> position(int offset, int layoutWidth, int layoutHeight) {
        LazyGridMeasuredItem[] lazyGridMeasuredItemArr = this.items;
        ArrayList arrayList = new ArrayList(lazyGridMeasuredItemArr.length);
        int length = lazyGridMeasuredItemArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int crossAxisSize = 0;
        while (i < length) {
            LazyGridMeasuredItem lazyGridMeasuredItem = lazyGridMeasuredItemArr[i];
            int i4 = i2 + 1;
            int iM820getCurrentLineSpanimpl = GridItemSpan.m820getCurrentLineSpanimpl(this.spans.get(i2).getPackedValue());
            int i5 = this.layoutDirection == LayoutDirection.Rtl ? (this.slotsPerLine - i3) - iM820getCurrentLineSpanimpl : i3;
            boolean z = this.isVertical;
            int i6 = z ? this.index : i5;
            if (!z) {
                i5 = this.index;
            }
            LazyGridPositionedItem lazyGridPositionedItemPosition = lazyGridMeasuredItem.position(offset, crossAxisSize, layoutWidth, layoutHeight, i6, i5);
            crossAxisSize += lazyGridMeasuredItem.getCrossAxisSize() + this.crossAxisSpacing;
            i3 += iM820getCurrentLineSpanimpl;
            arrayList.add(lazyGridPositionedItemPosition);
            i++;
            i2 = i4;
        }
        return arrayList;
    }
}
