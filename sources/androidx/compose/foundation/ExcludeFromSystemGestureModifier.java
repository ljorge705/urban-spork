package androidx.compose.foundation;

import android.view.View;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.OnGloballyPositionedModifier;
import java.util.List;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: SystemGestureExclusion.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¢\u0006\u0002\u0010\bJ\u0018\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0007H\u0002J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0006H\u0016J\u0006\u0010\u0019\u001a\u00020\u0017J\u0010\u0010\u001a\u001a\u00020\u00172\b\u0010\u001b\u001a\u0004\u0018\u00010\fR\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/ExcludeFromSystemGestureModifier;", "Landroidx/compose/ui/layout/OnGloballyPositionedModifier;", "view", "Landroid/view/View;", "exclusion", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/geometry/Rect;", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "getExclusion", "()Lkotlin/jvm/functions/Function1;", "rect", "Landroid/graphics/Rect;", "getRect", "()Landroid/graphics/Rect;", "setRect", "(Landroid/graphics/Rect;)V", "getView", "()Landroid/view/View;", "calcBounds", "layoutCoordinates", "findRoot", "onGloballyPositioned", "", "coordinates", "removeRect", "replaceRect", "newRect", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class ExcludeFromSystemGestureModifier implements OnGloballyPositionedModifier {
    private final Function1<LayoutCoordinates, Rect> exclusion;
    private android.graphics.Rect rect;
    private final View view;

    public final Function1<LayoutCoordinates, Rect> getExclusion() {
        return this.exclusion;
    }

    public final android.graphics.Rect getRect() {
        return this.rect;
    }

    public final View getView() {
        return this.view;
    }

    public final void setRect(android.graphics.Rect rect) {
        this.rect = rect;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ExcludeFromSystemGestureModifier(View view, Function1<? super LayoutCoordinates, Rect> function1) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.view = view;
        this.exclusion = function1;
    }

    @Override // androidx.compose.ui.layout.OnGloballyPositionedModifier
    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        android.graphics.Rect rectCalcBounds;
        Intrinsics.checkNotNullParameter(coordinates, "coordinates");
        Function1<LayoutCoordinates, Rect> function1 = this.exclusion;
        if (function1 == null) {
            Rect rectBoundsInRoot = LayoutCoordinatesKt.boundsInRoot(coordinates);
            rectCalcBounds = new android.graphics.Rect(MathKt.roundToInt(rectBoundsInRoot.getLeft()), MathKt.roundToInt(rectBoundsInRoot.getTop()), MathKt.roundToInt(rectBoundsInRoot.getRight()), MathKt.roundToInt(rectBoundsInRoot.getBottom()));
        } else {
            rectCalcBounds = calcBounds(coordinates, function1.invoke(coordinates));
        }
        replaceRect(rectCalcBounds);
    }

    public final void removeRect() {
        replaceRect(null);
    }

    private final android.graphics.Rect calcBounds(LayoutCoordinates layoutCoordinates, Rect rect) {
        LayoutCoordinates layoutCoordinatesFindRoot = findRoot(layoutCoordinates);
        long jMo3402localPositionOfR5De75A = layoutCoordinatesFindRoot.mo3402localPositionOfR5De75A(layoutCoordinates, rect.m1674getTopLeftF1C5BW0());
        long jMo3402localPositionOfR5De75A2 = layoutCoordinatesFindRoot.mo3402localPositionOfR5De75A(layoutCoordinates, rect.m1675getTopRightF1C5BW0());
        long jMo3402localPositionOfR5De75A3 = layoutCoordinatesFindRoot.mo3402localPositionOfR5De75A(layoutCoordinates, rect.m1667getBottomLeftF1C5BW0());
        long jMo3402localPositionOfR5De75A4 = layoutCoordinatesFindRoot.mo3402localPositionOfR5De75A(layoutCoordinates, rect.m1668getBottomRightF1C5BW0());
        return new android.graphics.Rect(MathKt.roundToInt(ComparisonsKt.minOf(Offset.m1639getXimpl(jMo3402localPositionOfR5De75A), Offset.m1639getXimpl(jMo3402localPositionOfR5De75A2), Offset.m1639getXimpl(jMo3402localPositionOfR5De75A3), Offset.m1639getXimpl(jMo3402localPositionOfR5De75A4))), MathKt.roundToInt(ComparisonsKt.minOf(Offset.m1640getYimpl(jMo3402localPositionOfR5De75A), Offset.m1640getYimpl(jMo3402localPositionOfR5De75A2), Offset.m1640getYimpl(jMo3402localPositionOfR5De75A3), Offset.m1640getYimpl(jMo3402localPositionOfR5De75A4))), MathKt.roundToInt(ComparisonsKt.maxOf(Offset.m1639getXimpl(jMo3402localPositionOfR5De75A), Offset.m1639getXimpl(jMo3402localPositionOfR5De75A2), Offset.m1639getXimpl(jMo3402localPositionOfR5De75A3), Offset.m1639getXimpl(jMo3402localPositionOfR5De75A4))), MathKt.roundToInt(ComparisonsKt.maxOf(Offset.m1640getYimpl(jMo3402localPositionOfR5De75A), Offset.m1640getYimpl(jMo3402localPositionOfR5De75A2), Offset.m1640getYimpl(jMo3402localPositionOfR5De75A3), Offset.m1640getYimpl(jMo3402localPositionOfR5De75A4))));
    }

    private final LayoutCoordinates findRoot(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        while (true) {
            LayoutCoordinates layoutCoordinates2 = parentLayoutCoordinates;
            LayoutCoordinates layoutCoordinates3 = layoutCoordinates;
            layoutCoordinates = layoutCoordinates2;
            if (layoutCoordinates == null) {
                return layoutCoordinates3;
            }
            parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        }
    }

    public final void replaceRect(android.graphics.Rect newRect) {
        MutableVector mutableVector = new MutableVector(new android.graphics.Rect[16], 0);
        List<android.graphics.Rect> systemGestureExclusionRects = this.view.getSystemGestureExclusionRects();
        Intrinsics.checkNotNullExpressionValue(systemGestureExclusionRects, "view.systemGestureExclusionRects");
        mutableVector.addAll(mutableVector.getSize(), (List) systemGestureExclusionRects);
        android.graphics.Rect rect = this.rect;
        if (rect != null) {
            mutableVector.remove(rect);
        }
        if (newRect != null && !newRect.isEmpty()) {
            mutableVector.add(newRect);
        }
        this.view.setSystemGestureExclusionRects(mutableVector.asMutableList());
        this.rect = newRect;
    }
}
