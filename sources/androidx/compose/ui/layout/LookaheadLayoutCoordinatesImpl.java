package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: LookaheadLayoutCoordinates.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0016H\u0096\u0002J\u0018\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010#\u001a\u00020\nH\u0016J%\u0010$\u001a\u00020%2\u0006\u0010\"\u001a\u00020\u00012\u0006\u0010&\u001a\u00020%H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b'\u0010(J%\u0010)\u001a\u00020%2\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020%H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b*\u0010+J\u001d\u0010,\u001a\u00020%2\u0006\u0010-\u001a\u00020%H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b.\u0010/J\u001d\u00100\u001a\u00020%2\u0006\u0010-\u001a\u00020%H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b1\u0010/J%\u00102\u001a\u0002032\u0006\u0010\"\u001a\u00020\u000f2\u0006\u00104\u001a\u000205H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b6\u00107J\u001d\u00108\u001a\u00020%2\u0006\u00109\u001a\u00020%H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b:\u0010/R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006;"}, d2 = {"Landroidx/compose/ui/layout/LookaheadLayoutCoordinatesImpl;", "Landroidx/compose/ui/layout/LookaheadLayoutCoordinates;", "lookaheadDelegate", "Landroidx/compose/ui/node/LookaheadDelegate;", "(Landroidx/compose/ui/node/LookaheadDelegate;)V", "coordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "getCoordinator", "()Landroidx/compose/ui/node/NodeCoordinator;", "isAttached", "", "()Z", "getLookaheadDelegate", "()Landroidx/compose/ui/node/LookaheadDelegate;", "parentCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getParentCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "parentLayoutCoordinates", "getParentLayoutCoordinates", "providedAlignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "getProvidedAlignmentLines", "()Ljava/util/Set;", RRWebVideoEvent.JsonKeys.SIZE, "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "()J", "get", "", "alignmentLine", "localBoundingBoxOf", "Landroidx/compose/ui/geometry/Rect;", "sourceCoordinates", "clipBounds", "localLookaheadPositionOf", "Landroidx/compose/ui/geometry/Offset;", "relativeToSource", "localLookaheadPositionOf-R5De75A", "(Landroidx/compose/ui/layout/LookaheadLayoutCoordinates;J)J", "localPositionOf", "localPositionOf-R5De75A", "(Landroidx/compose/ui/layout/LayoutCoordinates;J)J", "localToRoot", "relativeToLocal", "localToRoot-MK-Hz9U", "(J)J", "localToWindow", "localToWindow-MK-Hz9U", "transformFrom", "", "matrix", "Landroidx/compose/ui/graphics/Matrix;", "transformFrom-EL8BTi8", "(Landroidx/compose/ui/layout/LayoutCoordinates;[F)V", "windowToLocal", "relativeToWindow", "windowToLocal-MK-Hz9U", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LookaheadLayoutCoordinatesImpl implements LookaheadLayoutCoordinates {
    private final LookaheadDelegate lookaheadDelegate;

    public final LookaheadDelegate getLookaheadDelegate() {
        return this.lookaheadDelegate;
    }

    public LookaheadLayoutCoordinatesImpl(LookaheadDelegate lookaheadDelegate) {
        Intrinsics.checkNotNullParameter(lookaheadDelegate, "lookaheadDelegate");
        this.lookaheadDelegate = lookaheadDelegate;
    }

    public final NodeCoordinator getCoordinator() {
        return this.lookaheadDelegate.getCoordinator();
    }

    @Override // androidx.compose.ui.layout.LookaheadLayoutCoordinates
    /* renamed from: localLookaheadPositionOf-R5De75A */
    public long mo3411localLookaheadPositionOfR5De75A(LookaheadLayoutCoordinates sourceCoordinates, long relativeToSource) {
        Intrinsics.checkNotNullParameter(sourceCoordinates, "sourceCoordinates");
        LookaheadDelegate lookaheadDelegate = ((LookaheadLayoutCoordinatesImpl) sourceCoordinates).lookaheadDelegate;
        LookaheadDelegate lookaheadDelegate2 = getCoordinator().findCommonAncestor$ui_release(lookaheadDelegate.getCoordinator()).getLookaheadDelegate();
        if (lookaheadDelegate2 == null) {
            LookaheadDelegate rootLookaheadDelegate = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(lookaheadDelegate);
            long jM3550positionInBjo55l4$ui_release = lookaheadDelegate.m3550positionInBjo55l4$ui_release(rootLookaheadDelegate);
            long position = rootLookaheadDelegate.getPosition();
            long jIntOffset = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jM3550positionInBjo55l4$ui_release) + IntOffset.m4508getXimpl(position), IntOffset.m4509getYimpl(jM3550positionInBjo55l4$ui_release) + IntOffset.m4509getYimpl(position));
            long jIntOffset2 = IntOffsetKt.IntOffset(MathKt.roundToInt(Offset.m1639getXimpl(relativeToSource)), MathKt.roundToInt(Offset.m1640getYimpl(relativeToSource)));
            long jIntOffset3 = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset) + IntOffset.m4508getXimpl(jIntOffset2), IntOffset.m4509getYimpl(jIntOffset) + IntOffset.m4509getYimpl(jIntOffset2));
            LookaheadDelegate lookaheadDelegate3 = this.lookaheadDelegate;
            long jM3550positionInBjo55l4$ui_release2 = lookaheadDelegate3.m3550positionInBjo55l4$ui_release(LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(lookaheadDelegate3));
            long position2 = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(lookaheadDelegate3).getPosition();
            long jIntOffset4 = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jM3550positionInBjo55l4$ui_release2) + IntOffset.m4508getXimpl(position2), IntOffset.m4509getYimpl(jM3550positionInBjo55l4$ui_release2) + IntOffset.m4509getYimpl(position2));
            long jIntOffset5 = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset3) - IntOffset.m4508getXimpl(jIntOffset4), IntOffset.m4509getYimpl(jIntOffset3) - IntOffset.m4509getYimpl(jIntOffset4));
            NodeCoordinator wrappedBy = LookaheadLayoutCoordinatesKt.getRootLookaheadDelegate(this.lookaheadDelegate).getCoordinator().getWrappedBy();
            Intrinsics.checkNotNull(wrappedBy);
            NodeCoordinator wrappedBy2 = rootLookaheadDelegate.getCoordinator().getWrappedBy();
            Intrinsics.checkNotNull(wrappedBy2);
            return wrappedBy.mo3402localPositionOfR5De75A(wrappedBy2, OffsetKt.Offset(IntOffset.m4508getXimpl(jIntOffset5), IntOffset.m4509getYimpl(jIntOffset5)));
        }
        long jM3550positionInBjo55l4$ui_release3 = lookaheadDelegate.m3550positionInBjo55l4$ui_release(lookaheadDelegate2);
        long jIntOffset6 = IntOffsetKt.IntOffset(MathKt.roundToInt(Offset.m1639getXimpl(relativeToSource)), MathKt.roundToInt(Offset.m1640getYimpl(relativeToSource)));
        long jIntOffset7 = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jM3550positionInBjo55l4$ui_release3) + IntOffset.m4508getXimpl(jIntOffset6), IntOffset.m4509getYimpl(jM3550positionInBjo55l4$ui_release3) + IntOffset.m4509getYimpl(jIntOffset6));
        long jM3550positionInBjo55l4$ui_release4 = this.lookaheadDelegate.m3550positionInBjo55l4$ui_release(lookaheadDelegate2);
        long jIntOffset8 = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset7) - IntOffset.m4508getXimpl(jM3550positionInBjo55l4$ui_release4), IntOffset.m4509getYimpl(jIntOffset7) - IntOffset.m4509getYimpl(jM3550positionInBjo55l4$ui_release4));
        return OffsetKt.Offset(IntOffset.m4508getXimpl(jIntOffset8), IntOffset.m4509getYimpl(jIntOffset8));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: getSize-YbymL2g */
    public long mo3401getSizeYbymL2g() {
        return getCoordinator().mo3401getSizeYbymL2g();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Set<AlignmentLine> getProvidedAlignmentLines() {
        return getCoordinator().getProvidedAlignmentLines();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public LayoutCoordinates getParentLayoutCoordinates() {
        return getCoordinator().getParentLayoutCoordinates();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public LayoutCoordinates getParentCoordinates() {
        return getCoordinator().getParentCoordinates();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public boolean isAttached() {
        return getCoordinator().isAttached();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: windowToLocal-MK-Hz9U */
    public long mo3406windowToLocalMKHz9U(long relativeToWindow) {
        return getCoordinator().mo3406windowToLocalMKHz9U(relativeToWindow);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localToWindow-MK-Hz9U */
    public long mo3404localToWindowMKHz9U(long relativeToLocal) {
        return getCoordinator().mo3404localToWindowMKHz9U(relativeToLocal);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localToRoot-MK-Hz9U */
    public long mo3403localToRootMKHz9U(long relativeToLocal) {
        return getCoordinator().mo3403localToRootMKHz9U(relativeToLocal);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localPositionOf-R5De75A */
    public long mo3402localPositionOfR5De75A(LayoutCoordinates sourceCoordinates, long relativeToSource) {
        Intrinsics.checkNotNullParameter(sourceCoordinates, "sourceCoordinates");
        return getCoordinator().mo3402localPositionOfR5De75A(sourceCoordinates, relativeToSource);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Rect localBoundingBoxOf(LayoutCoordinates sourceCoordinates, boolean clipBounds) {
        Intrinsics.checkNotNullParameter(sourceCoordinates, "sourceCoordinates");
        return getCoordinator().localBoundingBoxOf(sourceCoordinates, clipBounds);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: transformFrom-EL8BTi8 */
    public void mo3405transformFromEL8BTi8(LayoutCoordinates sourceCoordinates, float[] matrix) {
        Intrinsics.checkNotNullParameter(sourceCoordinates, "sourceCoordinates");
        Intrinsics.checkNotNullParameter(matrix, "matrix");
        getCoordinator().mo3405transformFromEL8BTi8(sourceCoordinates, matrix);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public int get(AlignmentLine alignmentLine) {
        Intrinsics.checkNotNullParameter(alignmentLine, "alignmentLine");
        return getCoordinator().get(alignmentLine);
    }
}
