package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: LazyMeasuredItemProvider.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ-\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyMeasuredItemProvider;", "", "itemProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;", "measureScope", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "defaultMainAxisSpacing", "", "measuredItemFactory", "Landroidx/compose/foundation/lazy/grid/MeasuredItemFactory;", "(Landroidx/compose/foundation/lazy/grid/LazyGridItemProvider;Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;ILandroidx/compose/foundation/lazy/grid/MeasuredItemFactory;)V", "keyToIndexMap", "", "getKeyToIndexMap", "()Ljava/util/Map;", "getAndMeasure", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "index", "Landroidx/compose/foundation/lazy/grid/ItemIndex;", "mainAxisSpacing", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "getAndMeasure-ednRnyU", "(IIJ)Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyMeasuredItemProvider {
    private final int defaultMainAxisSpacing;
    private final LazyGridItemProvider itemProvider;
    private final LazyLayoutMeasureScope measureScope;
    private final MeasuredItemFactory measuredItemFactory;

    public LazyMeasuredItemProvider(LazyGridItemProvider itemProvider, LazyLayoutMeasureScope measureScope, int i, MeasuredItemFactory measuredItemFactory) {
        Intrinsics.checkNotNullParameter(itemProvider, "itemProvider");
        Intrinsics.checkNotNullParameter(measureScope, "measureScope");
        Intrinsics.checkNotNullParameter(measuredItemFactory, "measuredItemFactory");
        this.itemProvider = itemProvider;
        this.measureScope = measureScope;
        this.defaultMainAxisSpacing = i;
        this.measuredItemFactory = measuredItemFactory;
    }

    /* renamed from: getAndMeasure-ednRnyU$default, reason: not valid java name */
    public static /* synthetic */ LazyGridMeasuredItem m867getAndMeasureednRnyU$default(LazyMeasuredItemProvider lazyMeasuredItemProvider, int i, int i2, long j, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = lazyMeasuredItemProvider.defaultMainAxisSpacing;
        }
        return lazyMeasuredItemProvider.m868getAndMeasureednRnyU(i, i2, j);
    }

    /* renamed from: getAndMeasure-ednRnyU, reason: not valid java name */
    public final LazyGridMeasuredItem m868getAndMeasureednRnyU(int index, int mainAxisSpacing, long constraints) {
        int iM4347getMinHeightimpl;
        Object key = this.itemProvider.getKey(index);
        List<Placeable> listMo893measure0kLqBqw = this.measureScope.mo893measure0kLqBqw(index, constraints);
        if (Constraints.m4344getHasFixedWidthimpl(constraints)) {
            iM4347getMinHeightimpl = Constraints.m4348getMinWidthimpl(constraints);
        } else {
            if (!Constraints.m4343getHasFixedHeightimpl(constraints)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            iM4347getMinHeightimpl = Constraints.m4347getMinHeightimpl(constraints);
        }
        return this.measuredItemFactory.mo853createItemPU_OBEw(index, key, iM4347getMinHeightimpl, mainAxisSpacing, listMo893measure0kLqBqw);
    }

    public final Map<Object, Integer> getKeyToIndexMap() {
        return this.itemProvider.getKeyToIndexMap();
    }
}
