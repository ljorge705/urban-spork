package androidx.compose.ui.layout;

import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: Placeable.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b'\u0018\u00002\u00020\u0001:\u0001)B\u0005¢\u0006\u0002\u0010\u0002J@\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020!2\u0019\u0010\"\u001a\u0015\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u001e\u0018\u00010#¢\u0006\u0002\b%H$ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b&\u0010'J\b\u0010(\u001a\u00020\u001eH\u0002R\u001d\u0010\u0003\u001a\u00020\u00048DX\u0084\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR/\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@DX\u0084\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0011\u0010\u0006\"\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0015\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u000bR/\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u0017@DX\u0084\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0010\n\u0002\u0010\u0014\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\u0013R\u001e\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u000b\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006*"}, d2 = {"Landroidx/compose/ui/layout/Placeable;", "Landroidx/compose/ui/layout/Measured;", "()V", "apparentToRealOffset", "Landroidx/compose/ui/unit/IntOffset;", "getApparentToRealOffset-nOcc-ac", "()J", "<set-?>", "", "height", "getHeight", "()I", "measuredHeight", "getMeasuredHeight", "value", "Landroidx/compose/ui/unit/IntSize;", "measuredSize", "getMeasuredSize-YbymL2g", "setMeasuredSize-ozmzZPI", "(J)V", "J", "measuredWidth", "getMeasuredWidth", "Landroidx/compose/ui/unit/Constraints;", "measurementConstraints", "getMeasurementConstraints-msEJaDk", "setMeasurementConstraints-BRTryo0", "width", "getWidth", "placeAt", "", ViewProps.POSITION, ViewProps.Z_INDEX, "", "layerBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lkotlin/ExtensionFunctionType;", "placeAt-f8xVGno", "(JFLkotlin/jvm/functions/Function1;)V", "recalculateWidthAndHeight", "PlacementScope", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class Placeable implements Measured {
    public static final int $stable = 8;
    private int height;
    private long measuredSize = IntSizeKt.IntSize(0, 0);
    private long measurementConstraints = PlaceableKt.DefaultConstraints;
    private int width;

    public final int getHeight() {
        return this.height;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: getMeasuredSize-YbymL2g, reason: not valid java name and from getter */
    public final long getMeasuredSize() {
        return this.measuredSize;
    }

    /* renamed from: getMeasurementConstraints-msEJaDk, reason: not valid java name and from getter */
    protected final long getMeasurementConstraints() {
        return this.measurementConstraints;
    }

    public final int getWidth() {
        return this.width;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: placeAt-f8xVGno */
    public abstract void mo3397placeAtf8xVGno(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock);

    @Override // androidx.compose.ui.layout.Measured
    public int getMeasuredWidth() {
        return IntSize.m4550getWidthimpl(this.measuredSize);
    }

    @Override // androidx.compose.ui.layout.Measured
    public int getMeasuredHeight() {
        return IntSize.m4549getHeightimpl(this.measuredSize);
    }

    /* renamed from: setMeasuredSize-ozmzZPI, reason: not valid java name */
    protected final void m3442setMeasuredSizeozmzZPI(long j) {
        if (IntSize.m4548equalsimpl0(this.measuredSize, j)) {
            return;
        }
        this.measuredSize = j;
        recalculateWidthAndHeight();
    }

    private final void recalculateWidthAndHeight() {
        this.width = RangesKt.coerceIn(IntSize.m4550getWidthimpl(this.measuredSize), Constraints.m4348getMinWidthimpl(this.measurementConstraints), Constraints.m4346getMaxWidthimpl(this.measurementConstraints));
        this.height = RangesKt.coerceIn(IntSize.m4549getHeightimpl(this.measuredSize), Constraints.m4347getMinHeightimpl(this.measurementConstraints), Constraints.m4345getMaxHeightimpl(this.measurementConstraints));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: setMeasurementConstraints-BRTryo0, reason: not valid java name */
    public final void m3443setMeasurementConstraintsBRTryo0(long j) {
        if (Constraints.m4339equalsimpl0(this.measurementConstraints, j)) {
            return;
        }
        this.measurementConstraints = j;
        recalculateWidthAndHeight();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: getApparentToRealOffset-nOcc-ac, reason: not valid java name */
    public final long m3439getApparentToRealOffsetnOccac() {
        return IntOffsetKt.IntOffset((this.width - IntSize.m4550getWidthimpl(this.measuredSize)) / 2, (this.height - IntSize.m4549getHeightimpl(this.measuredSize)) / 2);
    }

    /* compiled from: Placeable.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b'\u0018\u0000 *2\u00020\u0001:\u0001*B\u0005¢\u0006\u0002\u0010\u0002J)\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0018J$\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\r2\b\b\u0002\u0010\u0015\u001a\u00020\u0016JJ\u0010\u001b\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u001b\b\b\u0010\u001c\u001a\u0015\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u001d¢\u0006\u0002\b\u001fH\u0080\bø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b \u0010!JJ\u0010\"\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u001b\b\b\u0010\u001c\u001a\u0015\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u001d¢\u0006\u0002\b\u001fH\u0080\bø\u0001\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010!J)\u0010$\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b%\u0010\u0018J$\u0010$\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\r2\b\b\u0002\u0010\u0015\u001a\u00020\u0016JD\u0010&\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u0019\b\u0002\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00110\u001d¢\u0006\u0002\b\u001fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010!J?\u0010&\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\r2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u0019\b\u0002\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00110\u001d¢\u0006\u0002\b\u001fJD\u0010(\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u0019\b\u0002\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00110\u001d¢\u0006\u0002\b\u001fø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b)\u0010!J?\u0010(\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\r2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u0019\b\u0002\u0010\u001c\u001a\u0013\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00110\u001d¢\u0006\u0002\b\u001fR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00048WX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¤\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0012\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019\n\u0005\b\u009920\u0001¨\u0006+"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "()V", "coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getCoordinates$annotations", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "parentLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getParentLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "parentWidth", "", "getParentWidth", "()I", "place", "", "Landroidx/compose/ui/layout/Placeable;", ViewProps.POSITION, "Landroidx/compose/ui/unit/IntOffset;", ViewProps.Z_INDEX, "", "place-70tqf50", "(Landroidx/compose/ui/layout/Placeable;JF)V", "x", "y", "placeApparentToRealOffset", "layerBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lkotlin/ExtensionFunctionType;", "placeApparentToRealOffset-aW-9-wM$ui_release", "(Landroidx/compose/ui/layout/Placeable;JFLkotlin/jvm/functions/Function1;)V", "placeAutoMirrored", "placeAutoMirrored-aW-9-wM$ui_release", "placeRelative", "placeRelative-70tqf50", "placeRelativeWithLayer", "placeRelativeWithLayer-aW-9-wM", "placeWithLayer", "placeWithLayer-aW-9-wM", "Companion", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static abstract class PlacementScope {
        public static final int $stable = 0;
        private static LayoutCoordinates _coordinates;
        private static LayoutNodeLayoutDelegate layoutDelegate;
        private static int parentWidth;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static LayoutDirection parentLayoutDirection = LayoutDirection.Ltr;

        public static /* synthetic */ void getCoordinates$annotations() {
        }

        public LayoutCoordinates getCoordinates() {
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public abstract LayoutDirection getParentLayoutDirection();

        /* JADX INFO: Access modifiers changed from: protected */
        public abstract int getParentWidth();

        /* renamed from: placeRelative-70tqf50$default, reason: not valid java name */
        public static /* synthetic */ void m3445placeRelative70tqf50$default(PlacementScope placementScope, Placeable placeable, long j, float f, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeRelative-70tqf50");
            }
            if ((i & 2) != 0) {
                f = 0.0f;
            }
            placementScope.m3451placeRelative70tqf50(placeable, j, f);
        }

        public static /* synthetic */ void placeRelative$default(PlacementScope placementScope, Placeable placeable, int i, int i2, float f, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeRelative");
            }
            if ((i3 & 4) != 0) {
                f = 0.0f;
            }
            placementScope.placeRelative(placeable, i, i2, f);
        }

        public final void placeRelative(Placeable placeable, int i, int i2, float f) {
            Intrinsics.checkNotNullParameter(placeable, "<this>");
            long jIntOffset = IntOffsetKt.IntOffset(i, i2);
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                long jM3439getApparentToRealOffsetnOccac = placeable.m3439getApparentToRealOffsetnOccac();
                placeable.mo3397placeAtf8xVGno(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset) + IntOffset.m4508getXimpl(jM3439getApparentToRealOffsetnOccac), IntOffset.m4509getYimpl(jIntOffset) + IntOffset.m4509getYimpl(jM3439getApparentToRealOffsetnOccac)), f, null);
            } else {
                long jIntOffset2 = IntOffsetKt.IntOffset((getParentWidth() - placeable.getWidth()) - IntOffset.m4508getXimpl(jIntOffset), IntOffset.m4509getYimpl(jIntOffset));
                long jM3439getApparentToRealOffsetnOccac2 = placeable.m3439getApparentToRealOffsetnOccac();
                placeable.mo3397placeAtf8xVGno(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset2) + IntOffset.m4508getXimpl(jM3439getApparentToRealOffsetnOccac2), IntOffset.m4509getYimpl(jIntOffset2) + IntOffset.m4509getYimpl(jM3439getApparentToRealOffsetnOccac2)), f, null);
            }
        }

        public static /* synthetic */ void place$default(PlacementScope placementScope, Placeable placeable, int i, int i2, float f, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: place");
            }
            if ((i3 & 4) != 0) {
                f = 0.0f;
            }
            placementScope.place(placeable, i, i2, f);
        }

        public final void place(Placeable placeable, int i, int i2, float f) {
            Intrinsics.checkNotNullParameter(placeable, "<this>");
            long jIntOffset = IntOffsetKt.IntOffset(i, i2);
            long jM3439getApparentToRealOffsetnOccac = placeable.m3439getApparentToRealOffsetnOccac();
            placeable.mo3397placeAtf8xVGno(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset) + IntOffset.m4508getXimpl(jM3439getApparentToRealOffsetnOccac), IntOffset.m4509getYimpl(jIntOffset) + IntOffset.m4509getYimpl(jM3439getApparentToRealOffsetnOccac)), f, null);
        }

        /* renamed from: place-70tqf50$default, reason: not valid java name */
        public static /* synthetic */ void m3444place70tqf50$default(PlacementScope placementScope, Placeable placeable, long j, float f, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: place-70tqf50");
            }
            if ((i & 2) != 0) {
                f = 0.0f;
            }
            placementScope.m3448place70tqf50(placeable, j, f);
        }

        /* renamed from: placeRelativeWithLayer-aW-9-wM$default, reason: not valid java name */
        public static /* synthetic */ void m3446placeRelativeWithLayeraW9wM$default(PlacementScope placementScope, Placeable placeable, long j, float f, Function1 function1, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeRelativeWithLayer-aW-9-wM");
            }
            if ((i & 2) != 0) {
                f = 0.0f;
            }
            float f2 = f;
            if ((i & 4) != 0) {
                function1 = PlaceableKt.DefaultLayerBlock;
            }
            placementScope.m3452placeRelativeWithLayeraW9wM(placeable, j, f2, function1);
        }

        public static /* synthetic */ void placeRelativeWithLayer$default(PlacementScope placementScope, Placeable placeable, int i, int i2, float f, Function1 function1, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeRelativeWithLayer");
            }
            if ((i3 & 4) != 0) {
                f = 0.0f;
            }
            float f2 = f;
            if ((i3 & 8) != 0) {
                function1 = PlaceableKt.DefaultLayerBlock;
            }
            placementScope.placeRelativeWithLayer(placeable, i, i2, f2, function1);
        }

        public final void placeRelativeWithLayer(Placeable placeable, int i, int i2, float f, Function1<? super GraphicsLayerScope, Unit> layerBlock) {
            Intrinsics.checkNotNullParameter(placeable, "<this>");
            Intrinsics.checkNotNullParameter(layerBlock, "layerBlock");
            long jIntOffset = IntOffsetKt.IntOffset(i, i2);
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                long jM3439getApparentToRealOffsetnOccac = placeable.m3439getApparentToRealOffsetnOccac();
                placeable.mo3397placeAtf8xVGno(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset) + IntOffset.m4508getXimpl(jM3439getApparentToRealOffsetnOccac), IntOffset.m4509getYimpl(jIntOffset) + IntOffset.m4509getYimpl(jM3439getApparentToRealOffsetnOccac)), f, layerBlock);
            } else {
                long jIntOffset2 = IntOffsetKt.IntOffset((getParentWidth() - placeable.getWidth()) - IntOffset.m4508getXimpl(jIntOffset), IntOffset.m4509getYimpl(jIntOffset));
                long jM3439getApparentToRealOffsetnOccac2 = placeable.m3439getApparentToRealOffsetnOccac();
                placeable.mo3397placeAtf8xVGno(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset2) + IntOffset.m4508getXimpl(jM3439getApparentToRealOffsetnOccac2), IntOffset.m4509getYimpl(jIntOffset2) + IntOffset.m4509getYimpl(jM3439getApparentToRealOffsetnOccac2)), f, layerBlock);
            }
        }

        public static /* synthetic */ void placeWithLayer$default(PlacementScope placementScope, Placeable placeable, int i, int i2, float f, Function1 function1, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeWithLayer");
            }
            if ((i3 & 4) != 0) {
                f = 0.0f;
            }
            float f2 = f;
            if ((i3 & 8) != 0) {
                function1 = PlaceableKt.DefaultLayerBlock;
            }
            placementScope.placeWithLayer(placeable, i, i2, f2, function1);
        }

        public final void placeWithLayer(Placeable placeable, int i, int i2, float f, Function1<? super GraphicsLayerScope, Unit> layerBlock) {
            Intrinsics.checkNotNullParameter(placeable, "<this>");
            Intrinsics.checkNotNullParameter(layerBlock, "layerBlock");
            long jIntOffset = IntOffsetKt.IntOffset(i, i2);
            long jM3439getApparentToRealOffsetnOccac = placeable.m3439getApparentToRealOffsetnOccac();
            placeable.mo3397placeAtf8xVGno(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset) + IntOffset.m4508getXimpl(jM3439getApparentToRealOffsetnOccac), IntOffset.m4509getYimpl(jIntOffset) + IntOffset.m4509getYimpl(jM3439getApparentToRealOffsetnOccac)), f, layerBlock);
        }

        /* renamed from: placeWithLayer-aW-9-wM$default, reason: not valid java name */
        public static /* synthetic */ void m3447placeWithLayeraW9wM$default(PlacementScope placementScope, Placeable placeable, long j, float f, Function1 function1, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: placeWithLayer-aW-9-wM");
            }
            if ((i & 2) != 0) {
                f = 0.0f;
            }
            float f2 = f;
            if ((i & 4) != 0) {
                function1 = PlaceableKt.DefaultLayerBlock;
            }
            placementScope.m3453placeWithLayeraW9wM(placeable, j, f2, function1);
        }

        /* renamed from: placeAutoMirrored-aW-9-wM$ui_release, reason: not valid java name */
        public final void m3450placeAutoMirroredaW9wM$ui_release(Placeable placeAutoMirrored, long j, float f, Function1<? super GraphicsLayerScope, Unit> function1) {
            Intrinsics.checkNotNullParameter(placeAutoMirrored, "$this$placeAutoMirrored");
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                long jM3439getApparentToRealOffsetnOccac = placeAutoMirrored.m3439getApparentToRealOffsetnOccac();
                placeAutoMirrored.mo3397placeAtf8xVGno(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(j) + IntOffset.m4508getXimpl(jM3439getApparentToRealOffsetnOccac), IntOffset.m4509getYimpl(j) + IntOffset.m4509getYimpl(jM3439getApparentToRealOffsetnOccac)), f, function1);
            } else {
                long jIntOffset = IntOffsetKt.IntOffset((getParentWidth() - placeAutoMirrored.getWidth()) - IntOffset.m4508getXimpl(j), IntOffset.m4509getYimpl(j));
                long jM3439getApparentToRealOffsetnOccac2 = placeAutoMirrored.m3439getApparentToRealOffsetnOccac();
                placeAutoMirrored.mo3397placeAtf8xVGno(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset) + IntOffset.m4508getXimpl(jM3439getApparentToRealOffsetnOccac2), IntOffset.m4509getYimpl(jIntOffset) + IntOffset.m4509getYimpl(jM3439getApparentToRealOffsetnOccac2)), f, function1);
            }
        }

        /* renamed from: placeApparentToRealOffset-aW-9-wM$ui_release, reason: not valid java name */
        public final void m3449placeApparentToRealOffsetaW9wM$ui_release(Placeable placeApparentToRealOffset, long j, float f, Function1<? super GraphicsLayerScope, Unit> function1) {
            Intrinsics.checkNotNullParameter(placeApparentToRealOffset, "$this$placeApparentToRealOffset");
            long jM3439getApparentToRealOffsetnOccac = placeApparentToRealOffset.m3439getApparentToRealOffsetnOccac();
            placeApparentToRealOffset.mo3397placeAtf8xVGno(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(j) + IntOffset.m4508getXimpl(jM3439getApparentToRealOffsetnOccac), IntOffset.m4509getYimpl(j) + IntOffset.m4509getYimpl(jM3439getApparentToRealOffsetnOccac)), f, function1);
        }

        /* compiled from: Placeable.kt */
        @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002JA\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u00172\u0019\b\u0004\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00190\u001c¢\u0006\u0002\b\u001dH\u0086\bø\u0001\u0000R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u00048VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@RX\u0094\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u0010@RX\u0094\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001e"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope$Companion;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "()V", "_coordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "coordinates", "getCoordinates$annotations", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "layoutDelegate", "Landroidx/compose/ui/node/LayoutNodeLayoutDelegate;", "<set-?>", "Landroidx/compose/ui/unit/LayoutDirection;", "parentLayoutDirection", "getParentLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "", "parentWidth", "getParentWidth", "()I", "configureForPlacingForAlignment", "", "scope", "Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "executeWithRtlMirroringValues", "", "lookaheadCapablePlaceable", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Companion extends PlacementScope {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public static /* synthetic */ void getCoordinates$annotations() {
            }

            private Companion() {
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.compose.ui.layout.Placeable.PlacementScope
            public LayoutDirection getParentLayoutDirection() {
                return PlacementScope.parentLayoutDirection;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.compose.ui.layout.Placeable.PlacementScope
            public int getParentWidth() {
                return PlacementScope.parentWidth;
            }

            @Override // androidx.compose.ui.layout.Placeable.PlacementScope
            public LayoutCoordinates getCoordinates() {
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = PlacementScope.layoutDelegate;
                if (layoutNodeLayoutDelegate != null) {
                    layoutNodeLayoutDelegate.setCoordinatesAccessedDuringPlacement(true);
                }
                return PlacementScope._coordinates;
            }

            public final void executeWithRtlMirroringValues(int parentWidth, LayoutDirection parentLayoutDirection, LookaheadCapablePlaceable lookaheadCapablePlaceable, Function1<? super PlacementScope, Unit> block) {
                Intrinsics.checkNotNullParameter(parentLayoutDirection, "parentLayoutDirection");
                Intrinsics.checkNotNullParameter(block, "block");
                LayoutCoordinates layoutCoordinates = PlacementScope._coordinates;
                int parentWidth2 = PlacementScope.INSTANCE.getParentWidth();
                LayoutDirection parentLayoutDirection2 = PlacementScope.INSTANCE.getParentLayoutDirection();
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = PlacementScope.layoutDelegate;
                Companion companion = PlacementScope.INSTANCE;
                PlacementScope.parentWidth = parentWidth;
                Companion companion2 = PlacementScope.INSTANCE;
                PlacementScope.parentLayoutDirection = parentLayoutDirection;
                boolean zConfigureForPlacingForAlignment = configureForPlacingForAlignment(lookaheadCapablePlaceable);
                block.invoke(this);
                if (lookaheadCapablePlaceable != null) {
                    lookaheadCapablePlaceable.setPlacingForAlignment$ui_release(zConfigureForPlacingForAlignment);
                }
                Companion companion3 = PlacementScope.INSTANCE;
                PlacementScope.parentWidth = parentWidth2;
                Companion companion4 = PlacementScope.INSTANCE;
                PlacementScope.parentLayoutDirection = parentLayoutDirection2;
                PlacementScope._coordinates = layoutCoordinates;
                PlacementScope.layoutDelegate = layoutNodeLayoutDelegate;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final boolean configureForPlacingForAlignment(LookaheadCapablePlaceable scope) {
                if (scope == null) {
                    PlacementScope._coordinates = null;
                    PlacementScope.layoutDelegate = null;
                    return false;
                }
                boolean zIsPlacingForAlignment$ui_release = scope.getIsPlacingForAlignment();
                LookaheadCapablePlaceable parent = scope.getParent();
                if (parent != null && parent.getIsPlacingForAlignment()) {
                    scope.setPlacingForAlignment$ui_release(true);
                }
                PlacementScope.layoutDelegate = scope.getLayoutNode().getLayoutDelegate();
                if (scope.getIsPlacingForAlignment() || scope.getIsShallowPlacing()) {
                    PlacementScope._coordinates = null;
                } else {
                    PlacementScope._coordinates = scope.getCoordinates();
                }
                return zIsPlacingForAlignment$ui_release;
            }
        }

        /* renamed from: placeRelative-70tqf50, reason: not valid java name */
        public final void m3451placeRelative70tqf50(Placeable placeRelative, long j, float f) {
            Intrinsics.checkNotNullParameter(placeRelative, "$this$placeRelative");
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                long jM3439getApparentToRealOffsetnOccac = placeRelative.m3439getApparentToRealOffsetnOccac();
                placeRelative.mo3397placeAtf8xVGno(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(j) + IntOffset.m4508getXimpl(jM3439getApparentToRealOffsetnOccac), IntOffset.m4509getYimpl(j) + IntOffset.m4509getYimpl(jM3439getApparentToRealOffsetnOccac)), f, null);
            } else {
                long jIntOffset = IntOffsetKt.IntOffset((getParentWidth() - placeRelative.getWidth()) - IntOffset.m4508getXimpl(j), IntOffset.m4509getYimpl(j));
                long jM3439getApparentToRealOffsetnOccac2 = placeRelative.m3439getApparentToRealOffsetnOccac();
                placeRelative.mo3397placeAtf8xVGno(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset) + IntOffset.m4508getXimpl(jM3439getApparentToRealOffsetnOccac2), IntOffset.m4509getYimpl(jIntOffset) + IntOffset.m4509getYimpl(jM3439getApparentToRealOffsetnOccac2)), f, null);
            }
        }

        /* renamed from: place-70tqf50, reason: not valid java name */
        public final void m3448place70tqf50(Placeable place, long j, float f) {
            Intrinsics.checkNotNullParameter(place, "$this$place");
            long jM3439getApparentToRealOffsetnOccac = place.m3439getApparentToRealOffsetnOccac();
            place.mo3397placeAtf8xVGno(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(j) + IntOffset.m4508getXimpl(jM3439getApparentToRealOffsetnOccac), IntOffset.m4509getYimpl(j) + IntOffset.m4509getYimpl(jM3439getApparentToRealOffsetnOccac)), f, null);
        }

        /* renamed from: placeRelativeWithLayer-aW-9-wM, reason: not valid java name */
        public final void m3452placeRelativeWithLayeraW9wM(Placeable placeRelativeWithLayer, long j, float f, Function1<? super GraphicsLayerScope, Unit> layerBlock) {
            Intrinsics.checkNotNullParameter(placeRelativeWithLayer, "$this$placeRelativeWithLayer");
            Intrinsics.checkNotNullParameter(layerBlock, "layerBlock");
            if (getParentLayoutDirection() == LayoutDirection.Ltr || getParentWidth() == 0) {
                long jM3439getApparentToRealOffsetnOccac = placeRelativeWithLayer.m3439getApparentToRealOffsetnOccac();
                placeRelativeWithLayer.mo3397placeAtf8xVGno(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(j) + IntOffset.m4508getXimpl(jM3439getApparentToRealOffsetnOccac), IntOffset.m4509getYimpl(j) + IntOffset.m4509getYimpl(jM3439getApparentToRealOffsetnOccac)), f, layerBlock);
            } else {
                long jIntOffset = IntOffsetKt.IntOffset((getParentWidth() - placeRelativeWithLayer.getWidth()) - IntOffset.m4508getXimpl(j), IntOffset.m4509getYimpl(j));
                long jM3439getApparentToRealOffsetnOccac2 = placeRelativeWithLayer.m3439getApparentToRealOffsetnOccac();
                placeRelativeWithLayer.mo3397placeAtf8xVGno(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(jIntOffset) + IntOffset.m4508getXimpl(jM3439getApparentToRealOffsetnOccac2), IntOffset.m4509getYimpl(jIntOffset) + IntOffset.m4509getYimpl(jM3439getApparentToRealOffsetnOccac2)), f, layerBlock);
            }
        }

        /* renamed from: placeWithLayer-aW-9-wM, reason: not valid java name */
        public final void m3453placeWithLayeraW9wM(Placeable placeWithLayer, long j, float f, Function1<? super GraphicsLayerScope, Unit> layerBlock) {
            Intrinsics.checkNotNullParameter(placeWithLayer, "$this$placeWithLayer");
            Intrinsics.checkNotNullParameter(layerBlock, "layerBlock");
            long jM3439getApparentToRealOffsetnOccac = placeWithLayer.m3439getApparentToRealOffsetnOccac();
            placeWithLayer.mo3397placeAtf8xVGno(IntOffsetKt.IntOffset(IntOffset.m4508getXimpl(j) + IntOffset.m4508getXimpl(jM3439getApparentToRealOffsetnOccac), IntOffset.m4509getYimpl(j) + IntOffset.m4509getYimpl(jM3439getApparentToRealOffsetnOccac)), f, layerBlock);
        }
    }
}
