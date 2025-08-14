package androidx.compose.foundation.lazy;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* compiled from: LazyMeasuredItem.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B|\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\b\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0003\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u001e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u0003R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0019\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001bR\u0011\u0010!\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u000e\u0010\u0014\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0015\u001a\u00020\u0016X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010#\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006)"}, d2 = {"Landroidx/compose/foundation/lazy/LazyMeasuredItem;", "", "index", "", "placeables", "", "Landroidx/compose/ui/layout/Placeable;", "isVertical", "", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", ViewProps.LAYOUT_DIRECTION, "Landroidx/compose/ui/unit/LayoutDirection;", "reverseLayout", "beforeContentPadding", "afterContentPadding", "placementAnimator", "Landroidx/compose/foundation/lazy/LazyListItemPlacementAnimator;", "spacing", "visualOffset", "Landroidx/compose/ui/unit/IntOffset;", Constants.KEY_KEY, "(ILjava/util/List;ZLandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/unit/LayoutDirection;ZIILandroidx/compose/foundation/lazy/LazyListItemPlacementAnimator;IJLjava/lang/Object;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "crossAxisSize", "getCrossAxisSize", "()I", "getIndex", "getKey", "()Ljava/lang/Object;", RRWebVideoEvent.JsonKeys.SIZE, "getSize", "sizeWithSpacings", "getSizeWithSpacings", "J", ViewProps.POSITION, "Landroidx/compose/foundation/lazy/LazyListPositionedItem;", "offset", "layoutWidth", "layoutHeight", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyMeasuredItem {
    private final int afterContentPadding;
    private final int beforeContentPadding;
    private final int crossAxisSize;
    private final Alignment.Horizontal horizontalAlignment;
    private final int index;
    private final boolean isVertical;
    private final Object key;
    private final LayoutDirection layoutDirection;
    private final List<Placeable> placeables;
    private final LazyListItemPlacementAnimator placementAnimator;
    private final boolean reverseLayout;
    private final int size;
    private final int sizeWithSpacings;
    private final int spacing;
    private final Alignment.Vertical verticalAlignment;
    private final long visualOffset;

    public /* synthetic */ LazyMeasuredItem(int i, List list, boolean z, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z2, int i2, int i3, LazyListItemPlacementAnimator lazyListItemPlacementAnimator, int i4, long j, Object obj, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, list, z, horizontal, vertical, layoutDirection, z2, i2, i3, lazyListItemPlacementAnimator, i4, j, obj);
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

    public final int getSize() {
        return this.size;
    }

    public final int getSizeWithSpacings() {
        return this.sizeWithSpacings;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private LazyMeasuredItem(int i, List<? extends Placeable> list, boolean z, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z2, int i2, int i3, LazyListItemPlacementAnimator lazyListItemPlacementAnimator, int i4, long j, Object obj) {
        this.index = i;
        this.placeables = list;
        this.isVertical = z;
        this.horizontalAlignment = horizontal;
        this.verticalAlignment = vertical;
        this.layoutDirection = layoutDirection;
        this.reverseLayout = z2;
        this.beforeContentPadding = i2;
        this.afterContentPadding = i3;
        this.placementAnimator = lazyListItemPlacementAnimator;
        this.spacing = i4;
        this.visualOffset = j;
        this.key = obj;
        int size = list.size();
        int height = 0;
        int iMax = 0;
        for (int i5 = 0; i5 < size; i5++) {
            Placeable placeable = (Placeable) list.get(i5);
            height += this.isVertical ? placeable.getHeight() : placeable.getWidth();
            iMax = Math.max(iMax, !this.isVertical ? placeable.getHeight() : placeable.getWidth());
        }
        this.size = height;
        this.sizeWithSpacings = RangesKt.coerceAtLeast(height + this.spacing, 0);
        this.crossAxisSize = iMax;
    }

    public final LazyListPositionedItem position(int offset, int layoutWidth, int layoutHeight) {
        long jIntOffset;
        ArrayList arrayList = new ArrayList();
        int i = this.isVertical ? layoutHeight : layoutWidth;
        List<Placeable> list = this.placeables;
        int size = list.size();
        int height = offset;
        for (int i2 = 0; i2 < size; i2++) {
            Placeable placeable = list.get(i2);
            if (this.isVertical) {
                Alignment.Horizontal horizontal = this.horizontalAlignment;
                if (horizontal == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                jIntOffset = IntOffsetKt.IntOffset(horizontal.align(placeable.getWidth(), layoutWidth, this.layoutDirection), height);
            } else {
                Alignment.Vertical vertical = this.verticalAlignment;
                if (vertical == null) {
                    throw new IllegalArgumentException("Required value was null.".toString());
                }
                jIntOffset = IntOffsetKt.IntOffset(height, vertical.align(placeable.getHeight(), layoutHeight));
            }
            height += this.isVertical ? placeable.getHeight() : placeable.getWidth();
            arrayList.add(new LazyListPlaceableWrapper(jIntOffset, placeable, null));
        }
        return new LazyListPositionedItem(offset, this.index, this.key, this.size, -this.beforeContentPadding, i + this.afterContentPadding, this.isVertical, arrayList, this.placementAnimator, this.visualOffset, this.reverseLayout, i, null);
    }
}
