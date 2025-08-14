package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;

/* compiled from: LookaheadLayoutCoordinates.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bw\u0018\u00002\u00020\u0001J'\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u0003H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\u0007\u0082\u0001\u0001\bø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/layout/LookaheadLayoutCoordinates;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "localLookaheadPositionOf", "Landroidx/compose/ui/geometry/Offset;", "sourceCoordinates", "relativeToSource", "localLookaheadPositionOf-R5De75A", "(Landroidx/compose/ui/layout/LookaheadLayoutCoordinates;J)J", "Landroidx/compose/ui/layout/LookaheadLayoutCoordinatesImpl;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface LookaheadLayoutCoordinates extends LayoutCoordinates {
    /* renamed from: localLookaheadPositionOf-R5De75A, reason: not valid java name */
    long mo3411localLookaheadPositionOfR5De75A(LookaheadLayoutCoordinates sourceCoordinates, long relativeToSource);

    /* renamed from: localLookaheadPositionOf-R5De75A$default, reason: not valid java name */
    static /* synthetic */ long m3410localLookaheadPositionOfR5De75A$default(LookaheadLayoutCoordinates lookaheadLayoutCoordinates, LookaheadLayoutCoordinates lookaheadLayoutCoordinates2, long j, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: localLookaheadPositionOf-R5De75A");
        }
        if ((i & 2) != 0) {
            j = Offset.INSTANCE.m1655getZeroF1C5BW0();
        }
        return lookaheadLayoutCoordinates.mo3411localLookaheadPositionOfR5De75A(lookaheadLayoutCoordinates2, j);
    }
}
