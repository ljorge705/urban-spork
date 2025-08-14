package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSizeKt;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* compiled from: LazyStaggeredGridMeasure.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001BN\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u001e\u0010&\u001a\u00020'2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u00032\u0006\u0010)\u001a\u00020\u0003R\u001c\u0010\n\u001a\u00020\u000bø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0017\"\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\u001e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\"\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0015\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006*"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "", "index", "", Constants.KEY_KEY, "placeables", "", "Landroidx/compose/ui/layout/Placeable;", "isVertical", "", "contentOffset", "Landroidx/compose/ui/unit/IntOffset;", "spacing", "lane", "span", "(ILjava/lang/Object;Ljava/util/List;ZJIIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getContentOffset-nOcc-ac", "()J", "J", "crossAxisSize", "getCrossAxisSize", "()I", "getIndex", "()Z", "isVisible", "setVisible", "(Z)V", "getKey", "()Ljava/lang/Object;", "getLane", "mainAxisSize", "getMainAxisSize", "getPlaceables", "()Ljava/util/List;", "sizeWithSpacings", "getSizeWithSpacings", "getSpacing", "getSpan", ViewProps.POSITION, "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridPositionedItem;", "mainAxis", "crossAxis", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class LazyStaggeredGridMeasuredItem {
    private final long contentOffset;
    private final int crossAxisSize;
    private final int index;
    private final boolean isVertical;
    private boolean isVisible;
    private final Object key;
    private final int lane;
    private final int mainAxisSize;
    private final List<Placeable> placeables;
    private final int sizeWithSpacings;
    private final int spacing;
    private final int span;

    public /* synthetic */ LazyStaggeredGridMeasuredItem(int i, Object obj, List list, boolean z, long j, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, obj, list, z, j, i2, i3, i4);
    }

    /* renamed from: getContentOffset-nOcc-ac, reason: not valid java name and from getter */
    public final long getContentOffset() {
        return this.contentOffset;
    }

    public final int getCrossAxisSize() {
        return this.crossAxisSize;
    }

    public final int getIndex() {
        return this.index;
    }

    public final Object getKey() {
        return this.key;
    }

    public final int getLane() {
        return this.lane;
    }

    public final int getMainAxisSize() {
        return this.mainAxisSize;
    }

    public final List<Placeable> getPlaceables() {
        return this.placeables;
    }

    public final int getSizeWithSpacings() {
        return this.sizeWithSpacings;
    }

    public final int getSpacing() {
        return this.spacing;
    }

    public final int getSpan() {
        return this.span;
    }

    /* renamed from: isVertical, reason: from getter */
    public final boolean getIsVertical() {
        return this.isVertical;
    }

    /* renamed from: isVisible, reason: from getter */
    public final boolean getIsVisible() {
        return this.isVisible;
    }

    public final void setVisible(boolean z) {
        this.isVisible = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private LazyStaggeredGridMeasuredItem(int i, Object obj, List<? extends Placeable> list, boolean z, long j, int i2, int i3, int i4) {
        Integer num;
        this.index = i;
        this.key = obj;
        this.placeables = list;
        this.isVertical = z;
        this.contentOffset = j;
        this.spacing = i2;
        this.lane = i3;
        this.span = i4;
        int i5 = 1;
        this.isVisible = true;
        int iValueOf = 0;
        int size = list.size();
        for (int i6 = 0; i6 < size; i6++) {
            Placeable placeable = (Placeable) list.get(i6);
            iValueOf = Integer.valueOf(iValueOf.intValue() + (this.isVertical ? placeable.getHeight() : placeable.getWidth()));
        }
        int iIntValue = iValueOf.intValue();
        this.mainAxisSize = iIntValue;
        this.sizeWithSpacings = RangesKt.coerceAtLeast(iIntValue + this.spacing, 0);
        List<Placeable> list2 = this.placeables;
        if (list2.isEmpty()) {
            num = null;
        } else {
            Placeable placeable2 = list2.get(0);
            Integer numValueOf = Integer.valueOf(this.isVertical ? placeable2.getWidth() : placeable2.getHeight());
            int lastIndex = CollectionsKt.getLastIndex(list2);
            if (1 <= lastIndex) {
                while (true) {
                    Placeable placeable3 = list2.get(i5);
                    Integer numValueOf2 = Integer.valueOf(this.isVertical ? placeable3.getWidth() : placeable3.getHeight());
                    numValueOf = numValueOf2.compareTo(numValueOf) > 0 ? numValueOf2 : numValueOf;
                    if (i5 == lastIndex) {
                        break;
                    } else {
                        i5++;
                    }
                }
            }
            num = numValueOf;
        }
        Integer num2 = num;
        this.crossAxisSize = num2 != null ? num2.intValue() : 0;
    }

    public final LazyStaggeredGridPositionedItem position(int lane, int mainAxis, int crossAxis) {
        long jIntOffset;
        long jIntSize;
        if (this.isVertical) {
            jIntOffset = IntOffsetKt.IntOffset(crossAxis, mainAxis);
        } else {
            jIntOffset = IntOffsetKt.IntOffset(mainAxis, crossAxis);
        }
        long j = jIntOffset;
        int i = this.index;
        Object obj = this.key;
        if (this.isVertical) {
            jIntSize = IntSizeKt.IntSize(this.crossAxisSize, this.sizeWithSpacings);
        } else {
            jIntSize = IntSizeKt.IntSize(this.sizeWithSpacings, this.crossAxisSize);
        }
        return new LazyStaggeredGridPositionedItem(j, i, lane, obj, jIntSize, this.placeables, this.contentOffset, this.isVertical, null);
    }
}
