package androidx.compose.ui.draw;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.ScaleFactorKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSizeKt;
import io.sentry.protocol.ViewHierarchyNode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: PainterModifier.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B?\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010J\u001d\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u0010/J\u001d\u00100\u001a\u0002012\u0006\u00102\u001a\u000201H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b3\u0010/J\b\u00104\u001a\u000205H\u0016J\f\u00106\u001a\u000207*\u000208H\u0016J\u0019\u00109\u001a\u00020\u0007*\u00020,H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b:\u0010;J\u0019\u0010<\u001a\u00020\u0007*\u00020,H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b=\u0010;J\u001c\u0010>\u001a\u00020?*\u00020@2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020?H\u0016J\u001c\u0010D\u001a\u00020?*\u00020@2\u0006\u0010A\u001a\u00020B2\u0006\u0010E\u001a\u00020?H\u0016J)\u0010F\u001a\u00020G*\u00020H2\u0006\u0010A\u001a\u00020I2\u0006\u00102\u001a\u000201H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bJ\u0010KJ\u001c\u0010L\u001a\u00020?*\u00020@2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020?H\u0016J\u001c\u0010M\u001a\u00020?*\u00020@2\u0006\u0010A\u001a\u00020B2\u0006\u0010E\u001a\u00020?H\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010&\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006N"}, d2 = {"Landroidx/compose/ui/draw/PainterModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DrawModifierNode;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "sizeToIntrinsics", "", "alignment", "Landroidx/compose/ui/Alignment;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", ViewHierarchyNode.JsonKeys.ALPHA, "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "(Landroidx/compose/ui/graphics/painter/Painter;ZLandroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;)V", "getAlignment", "()Landroidx/compose/ui/Alignment;", "setAlignment", "(Landroidx/compose/ui/Alignment;)V", "getAlpha", "()F", "setAlpha", "(F)V", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "getContentScale", "()Landroidx/compose/ui/layout/ContentScale;", "setContentScale", "(Landroidx/compose/ui/layout/ContentScale;)V", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "setPainter", "(Landroidx/compose/ui/graphics/painter/Painter;)V", "getSizeToIntrinsics", "()Z", "setSizeToIntrinsics", "(Z)V", "useIntrinsicSize", "getUseIntrinsicSize", "calculateScaledSize", "Landroidx/compose/ui/geometry/Size;", "dstSize", "calculateScaledSize-E7KxVPU", "(J)J", "modifyConstraints", "Landroidx/compose/ui/unit/Constraints;", CryptoServicesPermission.CONSTRAINTS, "modifyConstraints-ZezNO4M", "toString", "", "draw", "", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "hasSpecifiedAndFiniteHeight", "hasSpecifiedAndFiniteHeight-uvyYCjk", "(J)Z", "hasSpecifiedAndFiniteWidth", "hasSpecifiedAndFiniteWidth-uvyYCjk", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class PainterModifierNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode {
    private Alignment alignment;
    private float alpha;
    private ColorFilter colorFilter;
    private ContentScale contentScale;
    private Painter painter;
    private boolean sizeToIntrinsics;

    public final Alignment getAlignment() {
        return this.alignment;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    public final ContentScale getContentScale() {
        return this.contentScale;
    }

    public final Painter getPainter() {
        return this.painter;
    }

    public final boolean getSizeToIntrinsics() {
        return this.sizeToIntrinsics;
    }

    public final void setAlignment(Alignment alignment) {
        Intrinsics.checkNotNullParameter(alignment, "<set-?>");
        this.alignment = alignment;
    }

    public final void setAlpha(float f) {
        this.alpha = f;
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
    }

    public final void setContentScale(ContentScale contentScale) {
        Intrinsics.checkNotNullParameter(contentScale, "<set-?>");
        this.contentScale = contentScale;
    }

    public final void setPainter(Painter painter) {
        Intrinsics.checkNotNullParameter(painter, "<set-?>");
        this.painter = painter;
    }

    public final void setSizeToIntrinsics(boolean z) {
        this.sizeToIntrinsics = z;
    }

    public /* synthetic */ PainterModifierNode(Painter painter, boolean z, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(painter, z, (i & 4) != 0 ? Alignment.INSTANCE.getCenter() : alignment, (i & 8) != 0 ? ContentScale.INSTANCE.getInside() : contentScale, (i & 16) != 0 ? 1.0f : f, (i & 32) != 0 ? null : colorFilter);
    }

    public PainterModifierNode(Painter painter, boolean z, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter) {
        Intrinsics.checkNotNullParameter(painter, "painter");
        Intrinsics.checkNotNullParameter(alignment, "alignment");
        Intrinsics.checkNotNullParameter(contentScale, "contentScale");
        this.painter = painter;
        this.sizeToIntrinsics = z;
        this.alignment = alignment;
        this.contentScale = contentScale;
        this.alpha = f;
        this.colorFilter = colorFilter;
    }

    private final boolean getUseIntrinsicSize() {
        return this.sizeToIntrinsics && this.painter.getIntrinsicSize() != Size.INSTANCE.m1716getUnspecifiedNHjbRc();
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult mo1556measure3p2s80s(MeasureScope measure, Measurable measurable, long j) {
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        final Placeable placeableMo3396measureBRTryo0 = measurable.mo3396measureBRTryo0(m1555modifyConstraintsZezNO4M(j));
        return MeasureScope.layout$default(measure, placeableMo3396measureBRTryo0.getWidth(), placeableMo3396measureBRTryo0.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.ui.draw.PainterModifierNode$measure$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope layout) {
                Intrinsics.checkNotNullParameter(layout, "$this$layout");
                Placeable.PlacementScope.placeRelative$default(layout, placeableMo3396measureBRTryo0, 0, 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        if (getUseIntrinsicSize()) {
            long jM1555modifyConstraintsZezNO4M = m1555modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null));
            return Math.max(Constraints.m4348getMinWidthimpl(jM1555modifyConstraintsZezNO4M), measurable.minIntrinsicWidth(i));
        }
        return measurable.minIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        if (getUseIntrinsicSize()) {
            long jM1555modifyConstraintsZezNO4M = m1555modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null));
            return Math.max(Constraints.m4348getMinWidthimpl(jM1555modifyConstraintsZezNO4M), measurable.maxIntrinsicWidth(i));
        }
        return measurable.maxIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        if (getUseIntrinsicSize()) {
            long jM1555modifyConstraintsZezNO4M = m1555modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null));
            return Math.max(Constraints.m4347getMinHeightimpl(jM1555modifyConstraintsZezNO4M), measurable.minIntrinsicHeight(i));
        }
        return measurable.minIntrinsicHeight(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        if (getUseIntrinsicSize()) {
            long jM1555modifyConstraintsZezNO4M = m1555modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null));
            return Math.max(Constraints.m4347getMinHeightimpl(jM1555modifyConstraintsZezNO4M), measurable.maxIntrinsicHeight(i));
        }
        return measurable.maxIntrinsicHeight(i);
    }

    /* renamed from: calculateScaledSize-E7KxVPU, reason: not valid java name */
    private final long m1552calculateScaledSizeE7KxVPU(long dstSize) {
        float fM1708getWidthimpl;
        float fM1705getHeightimpl;
        if (!getUseIntrinsicSize()) {
            return dstSize;
        }
        if (!m1554hasSpecifiedAndFiniteWidthuvyYCjk(this.painter.getIntrinsicSize())) {
            fM1708getWidthimpl = Size.m1708getWidthimpl(dstSize);
        } else {
            fM1708getWidthimpl = Size.m1708getWidthimpl(this.painter.getIntrinsicSize());
        }
        if (!m1553hasSpecifiedAndFiniteHeightuvyYCjk(this.painter.getIntrinsicSize())) {
            fM1705getHeightimpl = Size.m1705getHeightimpl(dstSize);
        } else {
            fM1705getHeightimpl = Size.m1705getHeightimpl(this.painter.getIntrinsicSize());
        }
        long jSize = SizeKt.Size(fM1708getWidthimpl, fM1705getHeightimpl);
        if (Size.m1708getWidthimpl(dstSize) != 0.0f && Size.m1705getHeightimpl(dstSize) != 0.0f) {
            return ScaleFactorKt.m3478timesUQTWf7w(jSize, this.contentScale.mo3387computeScaleFactorH7hwNQA(jSize, dstSize));
        }
        return Size.INSTANCE.m1717getZeroNHjbRc();
    }

    /* renamed from: modifyConstraints-ZezNO4M, reason: not valid java name */
    private final long m1555modifyConstraintsZezNO4M(long constraints) {
        int iM4348getMinWidthimpl;
        int iM4347getMinHeightimpl;
        boolean z = Constraints.m4342getHasBoundedWidthimpl(constraints) && Constraints.m4341getHasBoundedHeightimpl(constraints);
        boolean z2 = Constraints.m4344getHasFixedWidthimpl(constraints) && Constraints.m4343getHasFixedHeightimpl(constraints);
        if ((!getUseIntrinsicSize() && z) || z2) {
            return Constraints.m4337copyZbe2FdA$default(constraints, Constraints.m4346getMaxWidthimpl(constraints), 0, Constraints.m4345getMaxHeightimpl(constraints), 0, 10, null);
        }
        long jMo2486getIntrinsicSizeNHjbRc = this.painter.getIntrinsicSize();
        if (m1554hasSpecifiedAndFiniteWidthuvyYCjk(jMo2486getIntrinsicSizeNHjbRc)) {
            iM4348getMinWidthimpl = MathKt.roundToInt(Size.m1708getWidthimpl(jMo2486getIntrinsicSizeNHjbRc));
        } else {
            iM4348getMinWidthimpl = Constraints.m4348getMinWidthimpl(constraints);
        }
        if (m1553hasSpecifiedAndFiniteHeightuvyYCjk(jMo2486getIntrinsicSizeNHjbRc)) {
            iM4347getMinHeightimpl = MathKt.roundToInt(Size.m1705getHeightimpl(jMo2486getIntrinsicSizeNHjbRc));
        } else {
            iM4347getMinHeightimpl = Constraints.m4347getMinHeightimpl(constraints);
        }
        long jM1552calculateScaledSizeE7KxVPU = m1552calculateScaledSizeE7KxVPU(SizeKt.Size(ConstraintsKt.m4360constrainWidthK40F9xA(constraints, iM4348getMinWidthimpl), ConstraintsKt.m4359constrainHeightK40F9xA(constraints, iM4347getMinHeightimpl)));
        return Constraints.m4337copyZbe2FdA$default(constraints, ConstraintsKt.m4360constrainWidthK40F9xA(constraints, MathKt.roundToInt(Size.m1708getWidthimpl(jM1552calculateScaledSizeE7KxVPU))), 0, ConstraintsKt.m4359constrainHeightK40F9xA(constraints, MathKt.roundToInt(Size.m1705getHeightimpl(jM1552calculateScaledSizeE7KxVPU))), 0, 10, null);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        float fM1708getWidthimpl;
        float fM1705getHeightimpl;
        long jM1717getZeroNHjbRc;
        Intrinsics.checkNotNullParameter(contentDrawScope, "<this>");
        long jMo2486getIntrinsicSizeNHjbRc = this.painter.getIntrinsicSize();
        if (m1554hasSpecifiedAndFiniteWidthuvyYCjk(jMo2486getIntrinsicSizeNHjbRc)) {
            fM1708getWidthimpl = Size.m1708getWidthimpl(jMo2486getIntrinsicSizeNHjbRc);
        } else {
            fM1708getWidthimpl = Size.m1708getWidthimpl(contentDrawScope.mo2417getSizeNHjbRc());
        }
        if (m1553hasSpecifiedAndFiniteHeightuvyYCjk(jMo2486getIntrinsicSizeNHjbRc)) {
            fM1705getHeightimpl = Size.m1705getHeightimpl(jMo2486getIntrinsicSizeNHjbRc);
        } else {
            fM1705getHeightimpl = Size.m1705getHeightimpl(contentDrawScope.mo2417getSizeNHjbRc());
        }
        long jSize = SizeKt.Size(fM1708getWidthimpl, fM1705getHeightimpl);
        if (Size.m1708getWidthimpl(contentDrawScope.mo2417getSizeNHjbRc()) != 0.0f && Size.m1705getHeightimpl(contentDrawScope.mo2417getSizeNHjbRc()) != 0.0f) {
            jM1717getZeroNHjbRc = ScaleFactorKt.m3478timesUQTWf7w(jSize, this.contentScale.mo3387computeScaleFactorH7hwNQA(jSize, contentDrawScope.mo2417getSizeNHjbRc()));
        } else {
            jM1717getZeroNHjbRc = Size.INSTANCE.m1717getZeroNHjbRc();
        }
        long j = jM1717getZeroNHjbRc;
        long jMo1535alignKFBX0sM = this.alignment.mo1535alignKFBX0sM(IntSizeKt.IntSize(MathKt.roundToInt(Size.m1708getWidthimpl(j)), MathKt.roundToInt(Size.m1705getHeightimpl(j))), IntSizeKt.IntSize(MathKt.roundToInt(Size.m1708getWidthimpl(contentDrawScope.mo2417getSizeNHjbRc())), MathKt.roundToInt(Size.m1705getHeightimpl(contentDrawScope.mo2417getSizeNHjbRc()))), contentDrawScope.getLayoutDirection());
        float fM4508getXimpl = IntOffset.m4508getXimpl(jMo1535alignKFBX0sM);
        float fM4509getYimpl = IntOffset.m4509getYimpl(jMo1535alignKFBX0sM);
        ContentDrawScope contentDrawScope2 = contentDrawScope;
        contentDrawScope2.getDrawContext().getTransform().translate(fM4508getXimpl, fM4509getYimpl);
        this.painter.m2492drawx_KDEd0(contentDrawScope2, j, this.alpha, this.colorFilter);
        contentDrawScope2.getDrawContext().getTransform().translate(-fM4508getXimpl, -fM4509getYimpl);
        contentDrawScope.drawContent();
    }

    /* renamed from: hasSpecifiedAndFiniteWidth-uvyYCjk, reason: not valid java name */
    private final boolean m1554hasSpecifiedAndFiniteWidthuvyYCjk(long j) {
        if (!Size.m1704equalsimpl0(j, Size.INSTANCE.m1716getUnspecifiedNHjbRc())) {
            float fM1708getWidthimpl = Size.m1708getWidthimpl(j);
            if (!Float.isInfinite(fM1708getWidthimpl) && !Float.isNaN(fM1708getWidthimpl)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: hasSpecifiedAndFiniteHeight-uvyYCjk, reason: not valid java name */
    private final boolean m1553hasSpecifiedAndFiniteHeightuvyYCjk(long j) {
        if (!Size.m1704equalsimpl0(j, Size.INSTANCE.m1716getUnspecifiedNHjbRc())) {
            float fM1705getHeightimpl = Size.m1705getHeightimpl(j);
            if (!Float.isInfinite(fM1705getHeightimpl) && !Float.isNaN(fM1705getHeightimpl)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "PainterModifier(painter=" + this.painter + ", sizeToIntrinsics=" + this.sizeToIntrinsics + ", alignment=" + this.alignment + ", alpha=" + this.alpha + ", colorFilter=" + this.colorFilter + ')';
    }
}
