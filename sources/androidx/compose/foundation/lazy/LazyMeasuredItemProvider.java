package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: LazyMeasuredItemProvider.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B2\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000bø\u0001\u0000¢\u0006\u0002\u0010\fJ\u001b\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bR\u001c\u0010\r\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00130\u00128F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/lazy/LazyMeasuredItemProvider;", "", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "isVertical", "", "itemProvider", "Landroidx/compose/foundation/lazy/LazyListItemProvider;", "measureScope", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "measuredItemFactory", "Landroidx/compose/foundation/lazy/MeasuredItemFactory;", "(JZLandroidx/compose/foundation/lazy/LazyListItemProvider;Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Landroidx/compose/foundation/lazy/MeasuredItemFactory;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "childConstraints", "getChildConstraints-msEJaDk", "()J", "J", "keyToIndexMap", "", "", "getKeyToIndexMap", "()Ljava/util/Map;", "getAndMeasure", "Landroidx/compose/foundation/lazy/LazyMeasuredItem;", "index", "Landroidx/compose/foundation/lazy/DataIndex;", "getAndMeasure-ZjPyQlc", "(I)Landroidx/compose/foundation/lazy/LazyMeasuredItem;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyMeasuredItemProvider {
    private final long childConstraints;
    private final LazyListItemProvider itemProvider;
    private final LazyLayoutMeasureScope measureScope;
    private final MeasuredItemFactory measuredItemFactory;

    public /* synthetic */ LazyMeasuredItemProvider(long j, boolean z, LazyListItemProvider lazyListItemProvider, LazyLayoutMeasureScope lazyLayoutMeasureScope, MeasuredItemFactory measuredItemFactory, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, z, lazyListItemProvider, lazyLayoutMeasureScope, measuredItemFactory);
    }

    /* renamed from: getChildConstraints-msEJaDk, reason: not valid java name and from getter */
    public final long getChildConstraints() {
        return this.childConstraints;
    }

    private LazyMeasuredItemProvider(long j, boolean z, LazyListItemProvider lazyListItemProvider, LazyLayoutMeasureScope lazyLayoutMeasureScope, MeasuredItemFactory measuredItemFactory) {
        this.itemProvider = lazyListItemProvider;
        this.measureScope = lazyLayoutMeasureScope;
        this.measuredItemFactory = measuredItemFactory;
        this.childConstraints = ConstraintsKt.Constraints$default(0, z ? Constraints.m4346getMaxWidthimpl(j) : Integer.MAX_VALUE, 0, z ? Integer.MAX_VALUE : Constraints.m4345getMaxHeightimpl(j), 5, null);
    }

    /* renamed from: getAndMeasure-ZjPyQlc, reason: not valid java name */
    public final LazyMeasuredItem m811getAndMeasureZjPyQlc(int index) {
        return this.measuredItemFactory.mo797createItemHK0c1C0(index, this.itemProvider.getKey(index), this.measureScope.mo893measure0kLqBqw(index, this.childConstraints));
    }

    public final Map<Object, Integer> getKeyToIndexMap() {
        return this.itemProvider.getKeyToIndexMap();
    }
}
