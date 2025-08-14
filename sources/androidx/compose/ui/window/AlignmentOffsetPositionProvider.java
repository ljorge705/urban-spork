package androidx.compose.ui.window;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Popup.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0018\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u0006J5\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0010H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/window/AlignmentOffsetPositionProvider;", "Landroidx/compose/ui/window/PopupPositionProvider;", "alignment", "Landroidx/compose/ui/Alignment;", "offset", "Landroidx/compose/ui/unit/IntOffset;", "(Landroidx/compose/ui/Alignment;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAlignment", "()Landroidx/compose/ui/Alignment;", "getOffset-nOcc-ac", "()J", "J", "calculatePosition", "anchorBounds", "Landroidx/compose/ui/unit/IntRect;", "windowSize", "Landroidx/compose/ui/unit/IntSize;", ViewProps.LAYOUT_DIRECTION, "Landroidx/compose/ui/unit/LayoutDirection;", "popupContentSize", "calculatePosition-llwVHH4", "(Landroidx/compose/ui/unit/IntRect;JLandroidx/compose/ui/unit/LayoutDirection;J)J", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AlignmentOffsetPositionProvider implements PopupPositionProvider {
    private final Alignment alignment;
    private final long offset;

    public /* synthetic */ AlignmentOffsetPositionProvider(Alignment alignment, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(alignment, j);
    }

    public final Alignment getAlignment() {
        return this.alignment;
    }

    /* renamed from: getOffset-nOcc-ac, reason: not valid java name and from getter */
    public final long getOffset() {
        return this.offset;
    }

    private AlignmentOffsetPositionProvider(Alignment alignment, long j) {
        this.alignment = alignment;
        this.offset = j;
    }

    @Override // androidx.compose.ui.window.PopupPositionProvider
    /* renamed from: calculatePosition-llwVHH4 */
    public long mo1077calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
        Intrinsics.checkNotNullParameter(anchorBounds, "anchorBounds");
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        long jIntOffset = IntOffsetKt.IntOffset(0, 0);
        long jMo1535alignKFBX0sM = this.alignment.mo1535alignKFBX0sM(IntSize.INSTANCE.m4555getZeroYbymL2g(), IntSizeKt.IntSize(anchorBounds.getWidth(), anchorBounds.getHeight()), layoutDirection);
        long jMo1535alignKFBX0sM2 = this.alignment.mo1535alignKFBX0sM(IntSize.INSTANCE.m4555getZeroYbymL2g(), IntSizeKt.IntSize(IntSize.m4550getWidthimpl(popupContentSize), IntSize.m4549getHeightimpl(popupContentSize)), layoutDirection);
        long jIntOffset2 = IntOffsetKt.IntOffset(anchorBounds.getLeft(), anchorBounds.getTop());
        long jIntOffset3 = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset) + IntOffset.m4508getXimpl(jIntOffset2), IntOffset.m4509getYimpl(jIntOffset) + IntOffset.m4509getYimpl(jIntOffset2));
        long jIntOffset4 = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset3) + IntOffset.m4508getXimpl(jMo1535alignKFBX0sM), IntOffset.m4509getYimpl(jIntOffset3) + IntOffset.m4509getYimpl(jMo1535alignKFBX0sM));
        long jIntOffset5 = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jMo1535alignKFBX0sM2), IntOffset.m4509getYimpl(jMo1535alignKFBX0sM2));
        long jIntOffset6 = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset4) - IntOffset.m4508getXimpl(jIntOffset5), IntOffset.m4509getYimpl(jIntOffset4) - IntOffset.m4509getYimpl(jIntOffset5));
        long jIntOffset7 = IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(this.offset) * (layoutDirection == LayoutDirection.Ltr ? 1 : -1), IntOffset.m4509getYimpl(this.offset));
        return IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset6) + IntOffset.m4508getXimpl(jIntOffset7), IntOffset.m4509getYimpl(jIntOffset6) + IntOffset.m4509getYimpl(jIntOffset7));
    }
}
