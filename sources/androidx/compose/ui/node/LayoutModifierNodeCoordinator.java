package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LookaheadScope;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import com.facebook.react.uimanager.ViewProps;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: LayoutModifierNodeCoordinator.kt */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 :2\u00020\u0001:\u0003:;<B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u0017H\u0016J\u0010\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u0017H\u0016J\u001d\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'J\u0010\u0010(\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u0017H\u0016J\u0010\u0010)\u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u0017H\u0016J\b\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020.H\u0016J@\u0010/\u001a\u00020+2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0019\u00104\u001a\u0015\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020+\u0018\u000105¢\u0006\u0002\b7H\u0014ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b8\u00109R$\u0010\b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006="}, d2 = {"Landroidx/compose/ui/node/LayoutModifierNodeCoordinator;", "Landroidx/compose/ui/node/NodeCoordinator;", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "measureNode", "Landroidx/compose/ui/node/LayoutModifierNode;", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/node/LayoutModifierNode;)V", "<set-?>", "layoutModifierNode", "getLayoutModifierNode", "()Landroidx/compose/ui/node/LayoutModifierNode;", "setLayoutModifierNode$ui_release", "(Landroidx/compose/ui/node/LayoutModifierNode;)V", "lookAheadTransientMeasureNode", "Landroidx/compose/ui/node/IntermediateLayoutModifierNode;", "tail", "Landroidx/compose/ui/Modifier$Node;", "getTail", "()Landroidx/compose/ui/Modifier$Node;", "wrappedNonNull", "getWrappedNonNull", "()Landroidx/compose/ui/node/NodeCoordinator;", "calculateAlignmentLine", "", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "createLookaheadDelegate", "Landroidx/compose/ui/node/LookaheadDelegate;", "scope", "Landroidx/compose/ui/layout/LookaheadScope;", "maxIntrinsicHeight", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/Placeable;", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "measure-BRTryo0", "(J)Landroidx/compose/ui/layout/Placeable;", "minIntrinsicHeight", "minIntrinsicWidth", "onLayoutModifierNodeChanged", "", "performDraw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "placeAt", ViewProps.POSITION, "Landroidx/compose/ui/unit/IntOffset;", ViewProps.Z_INDEX, "", "layerBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lkotlin/ExtensionFunctionType;", "placeAt-f8xVGno", "(JFLkotlin/jvm/functions/Function1;)V", "Companion", "LookaheadDelegateForIntermediateLayoutModifier", "LookaheadDelegateForLayoutModifierNode", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LayoutModifierNodeCoordinator extends NodeCoordinator {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Paint modifierBoundsPaint;
    private LayoutModifierNode layoutModifierNode;
    private IntermediateLayoutModifierNode lookAheadTransientMeasureNode;

    public final LayoutModifierNode getLayoutModifierNode() {
        return this.layoutModifierNode;
    }

    public final void setLayoutModifierNode$ui_release(LayoutModifierNode layoutModifierNode) {
        Intrinsics.checkNotNullParameter(layoutModifierNode, "<set-?>");
        this.layoutModifierNode = layoutModifierNode;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LayoutModifierNodeCoordinator(LayoutNode layoutNode, LayoutModifierNode measureNode) {
        super(layoutNode);
        Intrinsics.checkNotNullParameter(layoutNode, "layoutNode");
        Intrinsics.checkNotNullParameter(measureNode, "measureNode");
        this.layoutModifierNode = measureNode;
        this.lookAheadTransientMeasureNode = ((measureNode.getNode().getKindSet() & NodeKind.m3598constructorimpl(512)) == 0 || !(measureNode instanceof IntermediateLayoutModifierNode)) ? null : (IntermediateLayoutModifierNode) measureNode;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public Modifier.Node getTail() {
        return this.layoutModifierNode.getNode();
    }

    public final NodeCoordinator getWrappedNonNull() {
        NodeCoordinator wrapped$ui_release = getWrapped();
        Intrinsics.checkNotNull(wrapped$ui_release);
        return wrapped$ui_release;
    }

    /* compiled from: LayoutModifierNodeCoordinator.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u001d\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006H\u0016J\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0016\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/node/LayoutModifierNodeCoordinator$LookaheadDelegateForLayoutModifierNode;", "Landroidx/compose/ui/node/LookaheadDelegate;", "scope", "Landroidx/compose/ui/layout/LookaheadScope;", "(Landroidx/compose/ui/node/LayoutModifierNodeCoordinator;Landroidx/compose/ui/layout/LookaheadScope;)V", "calculateAlignmentLine", "", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "maxIntrinsicHeight", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/Placeable;", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "measure-BRTryo0", "(J)Landroidx/compose/ui/layout/Placeable;", "minIntrinsicHeight", "minIntrinsicWidth", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class LookaheadDelegateForLayoutModifierNode extends LookaheadDelegate {
        final /* synthetic */ LayoutModifierNodeCoordinator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LookaheadDelegateForLayoutModifierNode(LayoutModifierNodeCoordinator layoutModifierNodeCoordinator, LookaheadScope scope) {
            super(layoutModifierNodeCoordinator, scope);
            Intrinsics.checkNotNullParameter(scope, "scope");
            this.this$0 = layoutModifierNodeCoordinator;
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* renamed from: measure-BRTryo0 */
        public Placeable mo3396measureBRTryo0(long constraints) {
            LookaheadDelegateForLayoutModifierNode lookaheadDelegateForLayoutModifierNode = this;
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = this.this$0;
            lookaheadDelegateForLayoutModifierNode.m3443setMeasurementConstraintsBRTryo0(constraints);
            LookaheadDelegate lookaheadDelegate = layoutModifierNodeCoordinator.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            lookaheadDelegateForLayoutModifierNode.set_measureResult(layoutModifierNodeCoordinator.getLayoutModifierNode().mo1556measure3p2s80s(this, lookaheadDelegate, constraints));
            return lookaheadDelegateForLayoutModifierNode;
        }

        @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
        public int calculateAlignmentLine(AlignmentLine alignmentLine) {
            Intrinsics.checkNotNullParameter(alignmentLine, "alignmentLine");
            int iCalculateAlignmentAndPlaceChildAsNeeded = LayoutModifierNodeCoordinatorKt.calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
            getCachedAlignmentLinesMap().put(alignmentLine, Integer.valueOf(iCalculateAlignmentAndPlaceChildAsNeeded));
            return iCalculateAlignmentAndPlaceChildAsNeeded;
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicWidth(int height) {
            LayoutModifierNode layoutModifierNode = this.this$0.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = this.this$0.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return layoutModifierNode.minIntrinsicWidth(this, lookaheadDelegate, height);
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicWidth(int height) {
            LayoutModifierNode layoutModifierNode = this.this$0.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = this.this$0.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return layoutModifierNode.maxIntrinsicWidth(this, lookaheadDelegate, height);
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int minIntrinsicHeight(int width) {
            LayoutModifierNode layoutModifierNode = this.this$0.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = this.this$0.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return layoutModifierNode.minIntrinsicHeight(this, lookaheadDelegate, width);
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate, androidx.compose.ui.layout.IntrinsicMeasurable
        public int maxIntrinsicHeight(int width) {
            LayoutModifierNode layoutModifierNode = this.this$0.getLayoutModifierNode();
            LookaheadDelegate lookaheadDelegate = this.this$0.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return layoutModifierNode.maxIntrinsicHeight(this, lookaheadDelegate, width);
        }
    }

    /* compiled from: LayoutModifierNodeCoordinator.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001:\u0001\u0016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u001d\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\n0\nR\u00060\u0000R\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Landroidx/compose/ui/node/LayoutModifierNodeCoordinator$LookaheadDelegateForIntermediateLayoutModifier;", "Landroidx/compose/ui/node/LookaheadDelegate;", "scope", "Landroidx/compose/ui/layout/LookaheadScope;", "intermediateMeasureNode", "Landroidx/compose/ui/node/IntermediateLayoutModifierNode;", "(Landroidx/compose/ui/node/LayoutModifierNodeCoordinator;Landroidx/compose/ui/layout/LookaheadScope;Landroidx/compose/ui/node/IntermediateLayoutModifierNode;)V", "getIntermediateMeasureNode", "()Landroidx/compose/ui/node/IntermediateLayoutModifierNode;", "passThroughMeasureResult", "Landroidx/compose/ui/node/LayoutModifierNodeCoordinator$LookaheadDelegateForIntermediateLayoutModifier$PassThroughMeasureResult;", "Landroidx/compose/ui/node/LayoutModifierNodeCoordinator;", "calculateAlignmentLine", "", "alignmentLine", "Landroidx/compose/ui/layout/AlignmentLine;", "measure", "Landroidx/compose/ui/layout/Placeable;", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "measure-BRTryo0", "(J)Landroidx/compose/ui/layout/Placeable;", "PassThroughMeasureResult", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class LookaheadDelegateForIntermediateLayoutModifier extends LookaheadDelegate {
        private final IntermediateLayoutModifierNode intermediateMeasureNode;
        private final PassThroughMeasureResult passThroughMeasureResult;
        final /* synthetic */ LayoutModifierNodeCoordinator this$0;

        public final IntermediateLayoutModifierNode getIntermediateMeasureNode() {
            return this.intermediateMeasureNode;
        }

        /* compiled from: LayoutModifierNodeCoordinator.kt */
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016R \u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/node/LayoutModifierNodeCoordinator$LookaheadDelegateForIntermediateLayoutModifier$PassThroughMeasureResult;", "Landroidx/compose/ui/layout/MeasureResult;", "(Landroidx/compose/ui/node/LayoutModifierNodeCoordinator$LookaheadDelegateForIntermediateLayoutModifier;)V", "alignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "", "getAlignmentLines", "()Ljava/util/Map;", "height", "getHeight", "()I", "width", "getWidth", "placeChildren", "", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        private final class PassThroughMeasureResult implements MeasureResult {
            private final Map<AlignmentLine, Integer> alignmentLines = MapsKt.emptyMap();

            @Override // androidx.compose.ui.layout.MeasureResult
            public Map<AlignmentLine, Integer> getAlignmentLines() {
                return this.alignmentLines;
            }

            public PassThroughMeasureResult() {
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public int getWidth() {
                LookaheadDelegate lookaheadDelegate = LookaheadDelegateForIntermediateLayoutModifier.this.this$0.getWrappedNonNull().getLookaheadDelegate();
                Intrinsics.checkNotNull(lookaheadDelegate);
                return lookaheadDelegate.getMeasureResult$ui_release().getWidth();
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public int getHeight() {
                LookaheadDelegate lookaheadDelegate = LookaheadDelegateForIntermediateLayoutModifier.this.this$0.getWrappedNonNull().getLookaheadDelegate();
                Intrinsics.checkNotNull(lookaheadDelegate);
                return lookaheadDelegate.getMeasureResult$ui_release().getHeight();
            }

            @Override // androidx.compose.ui.layout.MeasureResult
            public void placeChildren() {
                Placeable.PlacementScope.Companion companion = Placeable.PlacementScope.INSTANCE;
                LookaheadDelegate lookaheadDelegate = LookaheadDelegateForIntermediateLayoutModifier.this.this$0.getWrappedNonNull().getLookaheadDelegate();
                Intrinsics.checkNotNull(lookaheadDelegate);
                Placeable.PlacementScope.place$default(companion, lookaheadDelegate, 0, 0, 0.0f, 4, null);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LookaheadDelegateForIntermediateLayoutModifier(LayoutModifierNodeCoordinator layoutModifierNodeCoordinator, LookaheadScope scope, IntermediateLayoutModifierNode intermediateMeasureNode) {
            super(layoutModifierNodeCoordinator, scope);
            Intrinsics.checkNotNullParameter(scope, "scope");
            Intrinsics.checkNotNullParameter(intermediateMeasureNode, "intermediateMeasureNode");
            this.this$0 = layoutModifierNodeCoordinator;
            this.intermediateMeasureNode = intermediateMeasureNode;
            this.passThroughMeasureResult = new PassThroughMeasureResult();
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* renamed from: measure-BRTryo0 */
        public Placeable mo3396measureBRTryo0(long constraints) {
            IntermediateLayoutModifierNode intermediateLayoutModifierNode = this.intermediateMeasureNode;
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = this.this$0;
            LookaheadDelegateForIntermediateLayoutModifier lookaheadDelegateForIntermediateLayoutModifier = this;
            lookaheadDelegateForIntermediateLayoutModifier.m3443setMeasurementConstraintsBRTryo0(constraints);
            LookaheadDelegate lookaheadDelegate = layoutModifierNodeCoordinator.getWrappedNonNull().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            lookaheadDelegate.mo3396measureBRTryo0(constraints);
            intermediateLayoutModifierNode.mo3485setTargetSizeozmzZPI(IntSizeKt.IntSize(lookaheadDelegate.getMeasureResult$ui_release().getWidth(), lookaheadDelegate.getMeasureResult$ui_release().getHeight()));
            lookaheadDelegateForIntermediateLayoutModifier.set_measureResult(this.passThroughMeasureResult);
            return lookaheadDelegateForIntermediateLayoutModifier;
        }

        @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
        public int calculateAlignmentLine(AlignmentLine alignmentLine) {
            Intrinsics.checkNotNullParameter(alignmentLine, "alignmentLine");
            int iCalculateAlignmentAndPlaceChildAsNeeded = LayoutModifierNodeCoordinatorKt.calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
            getCachedAlignmentLinesMap().put(alignmentLine, Integer.valueOf(iCalculateAlignmentAndPlaceChildAsNeeded));
            return iCalculateAlignmentAndPlaceChildAsNeeded;
        }
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public LookaheadDelegate createLookaheadDelegate(LookaheadScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        IntermediateLayoutModifierNode intermediateLayoutModifierNode = this.lookAheadTransientMeasureNode;
        if (intermediateLayoutModifierNode != null) {
            return new LookaheadDelegateForIntermediateLayoutModifier(this, scope, intermediateLayoutModifierNode);
        }
        return new LookaheadDelegateForLayoutModifierNode(this, scope);
    }

    @Override // androidx.compose.ui.layout.Measurable
    /* renamed from: measure-BRTryo0 */
    public Placeable mo3396measureBRTryo0(long constraints) {
        LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = this;
        layoutModifierNodeCoordinator.m3443setMeasurementConstraintsBRTryo0(constraints);
        setMeasureResult$ui_release(this.layoutModifierNode.mo1556measure3p2s80s(this, getWrappedNonNull(), constraints));
        LayoutModifierNodeCoordinator layoutModifierNodeCoordinator2 = this;
        OwnedLayer layer = layoutModifierNodeCoordinator.getLayer();
        if (layer != null) {
            layer.mo3643resizeozmzZPI(layoutModifierNodeCoordinator.getMeasuredSize());
        }
        onMeasured();
        return layoutModifierNodeCoordinator2;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicWidth(int height) {
        return this.layoutModifierNode.minIntrinsicWidth(this, getWrappedNonNull(), height);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicWidth(int height) {
        return this.layoutModifierNode.maxIntrinsicWidth(this, getWrappedNonNull(), height);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int minIntrinsicHeight(int width) {
        return this.layoutModifierNode.minIntrinsicHeight(this, getWrappedNonNull(), width);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public int maxIntrinsicHeight(int width) {
        return this.layoutModifierNode.maxIntrinsicHeight(this, getWrappedNonNull(), width);
    }

    @Override // androidx.compose.ui.node.NodeCoordinator, androidx.compose.ui.layout.Placeable
    /* renamed from: placeAt-f8xVGno */
    protected void mo3397placeAtf8xVGno(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock) {
        super.mo3397placeAtf8xVGno(position, zIndex, layerBlock);
        if (getIsShallowPlacing()) {
            return;
        }
        onPlaced();
        Placeable.PlacementScope.Companion companion = Placeable.PlacementScope.INSTANCE;
        int iM4550getWidthimpl = IntSize.m4550getWidthimpl(getMeasuredSize());
        LayoutDirection layoutDirection = getLayoutDirection();
        LayoutCoordinates layoutCoordinates = Placeable.PlacementScope._coordinates;
        int parentWidth = Placeable.PlacementScope.INSTANCE.getParentWidth();
        LayoutDirection parentLayoutDirection = Placeable.PlacementScope.INSTANCE.getParentLayoutDirection();
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = Placeable.PlacementScope.layoutDelegate;
        Placeable.PlacementScope.Companion companion2 = Placeable.PlacementScope.INSTANCE;
        Placeable.PlacementScope.parentWidth = iM4550getWidthimpl;
        Placeable.PlacementScope.Companion companion3 = Placeable.PlacementScope.INSTANCE;
        Placeable.PlacementScope.parentLayoutDirection = layoutDirection;
        LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = this;
        boolean zConfigureForPlacingForAlignment = companion.configureForPlacingForAlignment(layoutModifierNodeCoordinator);
        getMeasureResult$ui_release().placeChildren();
        layoutModifierNodeCoordinator.setPlacingForAlignment$ui_release(zConfigureForPlacingForAlignment);
        Placeable.PlacementScope.Companion companion4 = Placeable.PlacementScope.INSTANCE;
        Placeable.PlacementScope.parentWidth = parentWidth;
        Placeable.PlacementScope.Companion companion5 = Placeable.PlacementScope.INSTANCE;
        Placeable.PlacementScope.parentLayoutDirection = parentLayoutDirection;
        Placeable.PlacementScope._coordinates = layoutCoordinates;
        Placeable.PlacementScope.layoutDelegate = layoutNodeLayoutDelegate;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public void onLayoutModifierNodeChanged() {
        super.onLayoutModifierNodeChanged();
        LayoutModifierNode layoutModifierNode = this.layoutModifierNode;
        Modifier.Node node = layoutModifierNode.getNode();
        if ((node.getKindSet() & NodeKind.m3598constructorimpl(512)) != 0 && (layoutModifierNode instanceof IntermediateLayoutModifierNode)) {
            IntermediateLayoutModifierNode intermediateLayoutModifierNode = (IntermediateLayoutModifierNode) layoutModifierNode;
            this.lookAheadTransientMeasureNode = intermediateLayoutModifierNode;
            LookaheadDelegate lookaheadDelegate$ui_release = getLookaheadDelegate();
            if (lookaheadDelegate$ui_release != null) {
                updateLookaheadDelegate(new LookaheadDelegateForIntermediateLayoutModifier(this, lookaheadDelegate$ui_release.getLookaheadScope(), intermediateLayoutModifierNode));
                return;
            }
            return;
        }
        this.lookAheadTransientMeasureNode = null;
        LookaheadDelegate lookaheadDelegate$ui_release2 = getLookaheadDelegate();
        if (lookaheadDelegate$ui_release2 != null) {
            updateLookaheadDelegate(new LookaheadDelegateForLayoutModifierNode(this, lookaheadDelegate$ui_release2.getLookaheadScope()));
        }
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public int calculateAlignmentLine(AlignmentLine alignmentLine) {
        Intrinsics.checkNotNullParameter(alignmentLine, "alignmentLine");
        LookaheadDelegate lookaheadDelegate$ui_release = getLookaheadDelegate();
        return lookaheadDelegate$ui_release != null ? lookaheadDelegate$ui_release.getCachedAlignmentLine$ui_release(alignmentLine) : LayoutModifierNodeCoordinatorKt.calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public void performDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        getWrappedNonNull().draw(canvas);
        if (LayoutNodeKt.requireOwner(getLayoutNode()).getShowLayoutBounds()) {
            drawBorder(canvas, modifierBoundsPaint);
        }
    }

    /* compiled from: LayoutModifierNodeCoordinator.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/node/LayoutModifierNodeCoordinator$Companion;", "", "()V", "modifierBoundsPaint", "Landroidx/compose/ui/graphics/Paint;", "getModifierBoundsPaint", "()Landroidx/compose/ui/graphics/Paint;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Paint getModifierBoundsPaint() {
            return LayoutModifierNodeCoordinator.modifierBoundsPaint;
        }
    }

    static {
        Paint Paint = AndroidPaint_androidKt.Paint();
        Paint.mo1759setColor8_81llA(Color.INSTANCE.m1904getBlue0d7_KjU());
        Paint.setStrokeWidth(1.0f);
        Paint.mo1763setStylek9PVt8s(PaintingStyle.INSTANCE.m2138getStrokeTiuSbCo());
        modifierBoundsPaint = Paint;
    }
}
