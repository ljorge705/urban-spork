package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import com.clevertap.android.sdk.Constants;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LazyStaggeredGridMeasure.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001BN\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0010ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eR\u0019\u0010\u000e\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0012R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0006\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u001f\u0010\u0002\u001a\u00020\u0003X\u0096\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\t\u001a\u00020\nX\u0096\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001a\u0010\u0019\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001f"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridPositionedItem;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemInfo;", "offset", "Landroidx/compose/ui/unit/IntOffset;", "index", "", "lane", Constants.KEY_KEY, "", RRWebVideoEvent.JsonKeys.SIZE, "Landroidx/compose/ui/unit/IntSize;", "placeables", "", "Landroidx/compose/ui/layout/Placeable;", "contentOffset", "isVertical", "", "(JIILjava/lang/Object;JLjava/util/List;JZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "getIndex", "()I", "getKey", "()Ljava/lang/Object;", "getLane", "getOffset-nOcc-ac", "()J", "getSize-YbymL2g", "place", "", "scope", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class LazyStaggeredGridPositionedItem implements LazyStaggeredGridItemInfo {
    private final long contentOffset;
    private final int index;
    private final boolean isVertical;
    private final Object key;
    private final int lane;
    private final long offset;
    private final List<Placeable> placeables;
    private final long size;

    public /* synthetic */ LazyStaggeredGridPositionedItem(long j, int i, int i2, Object obj, long j2, List list, long j3, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, i, i2, obj, j2, list, j3, z);
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
    public int getIndex() {
        return this.index;
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
    public Object getKey() {
        return this.key;
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
    public int getLane() {
        return this.lane;
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
    /* renamed from: getOffset-nOcc-ac, reason: from getter */
    public long getOffset() {
        return this.offset;
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
    /* renamed from: getSize-YbymL2g, reason: from getter */
    public long getSize() {
        return this.size;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private LazyStaggeredGridPositionedItem(long j, int i, int i2, Object obj, long j2, List<? extends Placeable> list, long j3, boolean z) {
        this.offset = j;
        this.index = i;
        this.lane = i2;
        this.key = obj;
        this.size = j2;
        this.placeables = list;
        this.contentOffset = j3;
        this.isVertical = z;
    }

    public final void place(Placeable.PlacementScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        List<Placeable> list = this.placeables;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Placeable placeable = list.get(i);
            if (this.isVertical) {
                long offset = getOffset();
                long j = this.contentOffset;
                Placeable.PlacementScope.m3447placeWithLayeraW9wM$default(scope, placeable, IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(offset) + IntOffset.m4508getXimpl(j), IntOffset.m4509getYimpl(offset) + IntOffset.m4509getYimpl(j)), 0.0f, null, 6, null);
            } else {
                long offset2 = getOffset();
                long j2 = this.contentOffset;
                Placeable.PlacementScope.m3446placeRelativeWithLayeraW9wM$default(scope, placeable, IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(offset2) + IntOffset.m4508getXimpl(j2), IntOffset.m4509getYimpl(offset2) + IntOffset.m4509getYimpl(j2)), 0.0f, null, 6, null);
            }
        }
    }
}
