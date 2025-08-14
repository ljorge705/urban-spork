package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.lazy.layout.IntervalList;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.State;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* compiled from: LazyLayoutItemProvider.kt */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0007\u001ap\u0010\u0004\u001a\u00020\u0001\"\b\b\u0000\u0010\u0005*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\b2\u0006\u0010\t\u001a\u00020\n2A\u0010\u000b\u001a=\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\u00050\r¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00130\f¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0002\u0010\u0015\u001a\u001e\u0010\u0016\u001a\u00020\u0011*\u00020\u00012\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u0011H\u0001¨\u0006\u001a"}, d2 = {"DelegatingLazyLayoutItemProvider", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "delegate", "Landroidx/compose/runtime/State;", "LazyLayoutItemProvider", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent;", "intervals", "Landroidx/compose/foundation/lazy/layout/IntervalList;", "nearestItemsRange", "Lkotlin/ranges/IntRange;", "itemContent", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/IntervalList$Interval;", "Lkotlin/ParameterName;", "name", "interval", "", "index", "", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/lazy/layout/IntervalList;Lkotlin/ranges/IntRange;Lkotlin/jvm/functions/Function4;)Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "findIndexByKey", Constants.KEY_KEY, "", "lastKnownIndex", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyLayoutItemProviderKt {
    public static final <T extends LazyLayoutIntervalContent> LazyLayoutItemProvider LazyLayoutItemProvider(IntervalList<? extends T> intervals, IntRange nearestItemsRange, Function4<? super IntervalList.Interval<? extends T>, ? super Integer, ? super Composer, ? super Integer, Unit> itemContent) {
        Intrinsics.checkNotNullParameter(intervals, "intervals");
        Intrinsics.checkNotNullParameter(nearestItemsRange, "nearestItemsRange");
        Intrinsics.checkNotNullParameter(itemContent, "itemContent");
        return new DefaultLazyLayoutItemsProvider(itemContent, intervals, nearestItemsRange);
    }

    public static final LazyLayoutItemProvider DelegatingLazyLayoutItemProvider(State<? extends LazyLayoutItemProvider> delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        return new DefaultDelegatingLazyLayoutItemProvider(delegate);
    }

    public static final int findIndexByKey(LazyLayoutItemProvider lazyLayoutItemProvider, Object obj, int i) {
        Integer num;
        Intrinsics.checkNotNullParameter(lazyLayoutItemProvider, "<this>");
        return obj == null ? i : ((i >= lazyLayoutItemProvider.getItemCount() || !Intrinsics.areEqual(obj, lazyLayoutItemProvider.getKey(i))) && (num = lazyLayoutItemProvider.getKeyToIndexMap().get(obj)) != null) ? num.intValue() : i;
    }
}
