package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: LayoutCoordinates.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0002*\u00020\u0002\u001a\u0012\u0010\u0006\u001a\u00020\u0007*\u00020\u0002ø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\u0012\u0010\t\u001a\u00020\u0007*\u00020\u0002ø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\u0012\u0010\n\u001a\u00020\u0007*\u00020\u0002ø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"boundsInParent", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "boundsInRoot", "boundsInWindow", "findRootCoordinates", "positionInParent", "Landroidx/compose/ui/geometry/Offset;", "(Landroidx/compose/ui/layout/LayoutCoordinates;)J", "positionInRoot", "positionInWindow", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LayoutCoordinatesKt {
    public static final long positionInRoot(LayoutCoordinates layoutCoordinates) {
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        return layoutCoordinates.mo3403localToRootMKHz9U(Offset.INSTANCE.m1655getZeroF1C5BW0());
    }

    public static final long positionInWindow(LayoutCoordinates layoutCoordinates) {
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        return layoutCoordinates.mo3404localToWindowMKHz9U(Offset.INSTANCE.m1655getZeroF1C5BW0());
    }

    public static final Rect boundsInRoot(LayoutCoordinates layoutCoordinates) {
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        return LayoutCoordinates.localBoundingBoxOf$default(findRootCoordinates(layoutCoordinates), layoutCoordinates, false, 2, null);
    }

    public static final Rect boundsInWindow(LayoutCoordinates layoutCoordinates) {
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = findRootCoordinates(layoutCoordinates);
        Rect rectBoundsInRoot = boundsInRoot(layoutCoordinates);
        float fM4550getWidthimpl = IntSize.m4550getWidthimpl(layoutCoordinatesFindRootCoordinates.mo3401getSizeYbymL2g());
        float fM4549getHeightimpl = IntSize.m4549getHeightimpl(layoutCoordinatesFindRootCoordinates.mo3401getSizeYbymL2g());
        float fCoerceIn = RangesKt.coerceIn(rectBoundsInRoot.getLeft(), 0.0f, fM4550getWidthimpl);
        float fCoerceIn2 = RangesKt.coerceIn(rectBoundsInRoot.getTop(), 0.0f, fM4549getHeightimpl);
        float fCoerceIn3 = RangesKt.coerceIn(rectBoundsInRoot.getRight(), 0.0f, fM4550getWidthimpl);
        float fCoerceIn4 = RangesKt.coerceIn(rectBoundsInRoot.getBottom(), 0.0f, fM4549getHeightimpl);
        if (fCoerceIn == fCoerceIn3 || fCoerceIn2 == fCoerceIn4) {
            return Rect.INSTANCE.getZero();
        }
        long jMo3404localToWindowMKHz9U = layoutCoordinatesFindRootCoordinates.mo3404localToWindowMKHz9U(OffsetKt.Offset(fCoerceIn, fCoerceIn2));
        long jMo3404localToWindowMKHz9U2 = layoutCoordinatesFindRootCoordinates.mo3404localToWindowMKHz9U(OffsetKt.Offset(fCoerceIn3, fCoerceIn2));
        long jMo3404localToWindowMKHz9U3 = layoutCoordinatesFindRootCoordinates.mo3404localToWindowMKHz9U(OffsetKt.Offset(fCoerceIn3, fCoerceIn4));
        long jMo3404localToWindowMKHz9U4 = layoutCoordinatesFindRootCoordinates.mo3404localToWindowMKHz9U(OffsetKt.Offset(fCoerceIn, fCoerceIn4));
        return new Rect(ComparisonsKt.minOf(Offset.m1639getXimpl(jMo3404localToWindowMKHz9U), Offset.m1639getXimpl(jMo3404localToWindowMKHz9U2), Offset.m1639getXimpl(jMo3404localToWindowMKHz9U4), Offset.m1639getXimpl(jMo3404localToWindowMKHz9U3)), ComparisonsKt.minOf(Offset.m1640getYimpl(jMo3404localToWindowMKHz9U), Offset.m1640getYimpl(jMo3404localToWindowMKHz9U2), Offset.m1640getYimpl(jMo3404localToWindowMKHz9U4), Offset.m1640getYimpl(jMo3404localToWindowMKHz9U3)), ComparisonsKt.maxOf(Offset.m1639getXimpl(jMo3404localToWindowMKHz9U), Offset.m1639getXimpl(jMo3404localToWindowMKHz9U2), Offset.m1639getXimpl(jMo3404localToWindowMKHz9U4), Offset.m1639getXimpl(jMo3404localToWindowMKHz9U3)), ComparisonsKt.maxOf(Offset.m1640getYimpl(jMo3404localToWindowMKHz9U), Offset.m1640getYimpl(jMo3404localToWindowMKHz9U2), Offset.m1640getYimpl(jMo3404localToWindowMKHz9U4), Offset.m1640getYimpl(jMo3404localToWindowMKHz9U3)));
    }

    public static final long positionInParent(LayoutCoordinates layoutCoordinates) {
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        return parentLayoutCoordinates != null ? parentLayoutCoordinates.mo3402localPositionOfR5De75A(layoutCoordinates, Offset.INSTANCE.m1655getZeroF1C5BW0()) : Offset.INSTANCE.m1655getZeroF1C5BW0();
    }

    public static final Rect boundsInParent(LayoutCoordinates layoutCoordinates) {
        Rect rectLocalBoundingBoxOf$default;
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        return (parentLayoutCoordinates == null || (rectLocalBoundingBoxOf$default = LayoutCoordinates.localBoundingBoxOf$default(parentLayoutCoordinates, layoutCoordinates, false, 2, null)) == null) ? new Rect(0.0f, 0.0f, IntSize.m4550getWidthimpl(layoutCoordinates.mo3401getSizeYbymL2g()), IntSize.m4549getHeightimpl(layoutCoordinates.mo3401getSizeYbymL2g())) : rectLocalBoundingBoxOf$default;
    }

    public static final LayoutCoordinates findRootCoordinates(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates layoutCoordinates2;
        Intrinsics.checkNotNullParameter(layoutCoordinates, "<this>");
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        while (true) {
            LayoutCoordinates layoutCoordinates3 = parentLayoutCoordinates;
            layoutCoordinates2 = layoutCoordinates;
            layoutCoordinates = layoutCoordinates3;
            if (layoutCoordinates == null) {
                break;
            }
            parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        }
        NodeCoordinator nodeCoordinator = layoutCoordinates2 instanceof NodeCoordinator ? (NodeCoordinator) layoutCoordinates2 : null;
        if (nodeCoordinator == null) {
            return layoutCoordinates2;
        }
        NodeCoordinator wrappedBy = nodeCoordinator.getWrappedBy();
        while (true) {
            NodeCoordinator nodeCoordinator2 = wrappedBy;
            NodeCoordinator nodeCoordinator3 = nodeCoordinator;
            nodeCoordinator = nodeCoordinator2;
            if (nodeCoordinator != null) {
                wrappedBy = nodeCoordinator.getWrappedBy();
            } else {
                return nodeCoordinator3;
            }
        }
    }
}
