package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.BuildDrawCacheParams;
import androidx.compose.ui.draw.DrawCacheModifier;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.focus.FocusEventModifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusOrderModifier;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusRequesterModifier;
import androidx.compose.ui.focus.FocusRequesterModifierNode;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputModifier;
import androidx.compose.ui.layout.IntermediateLayoutModifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.LookaheadLayoutCoordinates;
import androidx.compose.ui.layout.LookaheadOnPlacedModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifier;
import androidx.compose.ui.layout.OnPlacedModifier;
import androidx.compose.ui.layout.OnRemeasuredModifier;
import androidx.compose.ui.layout.ParentDataModifier;
import androidx.compose.ui.layout.RemeasurementModifier;
import androidx.compose.ui.modifier.BackwardsCompatLocalMap;
import androidx.compose.ui.modifier.ModifierLocal;
import androidx.compose.ui.modifier.ModifierLocalConsumer;
import androidx.compose.ui.modifier.ModifierLocalMap;
import androidx.compose.ui.modifier.ModifierLocalNode;
import androidx.compose.ui.modifier.ModifierLocalNodeKt;
import androidx.compose.ui.modifier.ModifierLocalProvider;
import androidx.compose.ui.modifier.ModifierLocalReadScope;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsModifier;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.uimanager.ViewProps;
import io.sentry.protocol.SentryThread;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: BackwardsCompatNode.kt */
@Metadata(d1 = {"\u0000\u0094\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n2\u00020\u000b2\u00020\f2\u00020\r2\u00020\u000e2\u00020\u000f2\u00020\u0010B\r\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u0010\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\u001fH\u0002J\b\u0010H\u001a\u00020\u001fH\u0016J\u0010\u0010I\u001a\u00020F2\u0006\u0010J\u001a\u00020KH\u0016J\b\u0010L\u001a\u00020FH\u0016J\b\u0010M\u001a\u00020FH\u0016J\b\u0010N\u001a\u00020FH\u0016J\r\u0010O\u001a\u00020FH\u0000¢\u0006\u0002\bPJ\u0010\u0010Q\u001a\u00020F2\u0006\u0010R\u001a\u00020SH\u0016J\u0010\u0010T\u001a\u00020F2\u0006\u0010U\u001a\u00020#H\u0016J\u0010\u0010V\u001a\u00020F2\u0006\u0010U\u001a\u00020WH\u0016J\b\u0010X\u001a\u00020FH\u0016J\u0010\u0010Y\u001a\u00020F2\u0006\u0010U\u001a\u00020#H\u0016J-\u0010Z\u001a\u00020F2\u0006\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020^2\u0006\u0010_\u001a\u00020<H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b`\u0010aJ\u001d\u0010b\u001a\u00020F2\u0006\u00108\u001a\u00020<H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bc\u0010@J\b\u0010d\u001a\u00020\u001fH\u0016J\b\u0010e\u001a\u00020fH\u0016J\b\u0010g\u001a\u00020FH\u0002J\b\u0010h\u001a\u00020FH\u0002J\u0006\u0010i\u001a\u00020FJ\u0014\u0010j\u001a\u00020F2\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030kH\u0002J\f\u0010l\u001a\u00020F*\u00020mH\u0016J\u001c\u0010n\u001a\u00020o*\u00020p2\u0006\u0010q\u001a\u00020r2\u0006\u0010s\u001a\u00020oH\u0016J\u001c\u0010t\u001a\u00020o*\u00020p2\u0006\u0010q\u001a\u00020r2\u0006\u0010u\u001a\u00020oH\u0016J)\u0010v\u001a\u00020w*\u00020x2\u0006\u0010q\u001a\u00020y2\u0006\u0010z\u001a\u00020{H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b|\u0010}J\u001c\u0010~\u001a\u00020o*\u00020p2\u0006\u0010q\u001a\u00020r2\u0006\u0010s\u001a\u00020oH\u0016J\u001c\u0010\u007f\u001a\u00020o*\u00020p2\u0006\u0010q\u001a\u00020r2\u0006\u0010u\u001a\u00020oH\u0016J\u001c\u0010\u0080\u0001\u001a\u0005\u0018\u00010\u0081\u0001*\u00020\u00172\n\u0010\u0082\u0001\u001a\u0005\u0018\u00010\u0081\u0001H\u0016R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R$\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0012@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u0013R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00020\u001f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020)8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R2\u0010,\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030.0-j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030.`/X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0014\u00104\u001a\u0002058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R\u001d\u00108\u001a\u0002098VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b:\u0010;R-\u0010=\u001a\u00020<2\u0006\u0010\u001a\u001a\u00020<8V@VX\u0096\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\f\u001a\u0004\b>\u0010;\"\u0004\b?\u0010@R$\u0010A\u001a\u0002HB\"\u0004\b\u0000\u0010B*\b\u0012\u0004\u0012\u0002HB0.8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bC\u0010D\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0083\u0001"}, d2 = {"Landroidx/compose/ui/node/BackwardsCompatNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/IntermediateLayoutModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalNode;", "Landroidx/compose/ui/modifier/ModifierLocalReadScope;", "Landroidx/compose/ui/node/ParentDataModifierNode;", "Landroidx/compose/ui/node/LayoutAwareModifierNode;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "Landroidx/compose/ui/focus/FocusRequesterModifierNode;", "Landroidx/compose/ui/node/OwnerScope;", "Landroidx/compose/ui/draw/BuildDrawCacheParams;", "Landroidx/compose/ui/Modifier$Node;", "element", "Landroidx/compose/ui/Modifier$Element;", "(Landroidx/compose/ui/Modifier$Element;)V", "_providedValues", "Landroidx/compose/ui/modifier/BackwardsCompatLocalMap;", "density", "Landroidx/compose/ui/unit/Density;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "value", "getElement", "()Landroidx/compose/ui/Modifier$Element;", "setElement", "invalidateCache", "", "isValidOwnerScope", "()Z", "lastOnPlacedCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", ViewProps.LAYOUT_DIRECTION, "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "providedValues", "Landroidx/compose/ui/modifier/ModifierLocalMap;", "getProvidedValues", "()Landroidx/compose/ui/modifier/ModifierLocalMap;", "readValues", "Ljava/util/HashSet;", "Landroidx/compose/ui/modifier/ModifierLocal;", "Lkotlin/collections/HashSet;", "getReadValues", "()Ljava/util/HashSet;", "setReadValues", "(Ljava/util/HashSet;)V", "semanticsConfiguration", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "getSemanticsConfiguration", "()Landroidx/compose/ui/semantics/SemanticsConfiguration;", RRWebVideoEvent.JsonKeys.SIZE, "Landroidx/compose/ui/geometry/Size;", "getSize-NH-jbRc", "()J", "Landroidx/compose/ui/unit/IntSize;", "targetSize", "getTargetSize-YbymL2g", "setTargetSize-ozmzZPI", "(J)V", SentryThread.JsonKeys.CURRENT, ExifInterface.GPS_DIRECTION_TRUE, "getCurrent", "(Landroidx/compose/ui/modifier/ModifierLocal;)Ljava/lang/Object;", "initializeModifier", "", "duringAttach", "interceptOutOfBoundsChildEvents", "modifyFocusProperties", "focusProperties", "Landroidx/compose/ui/focus/FocusProperties;", "onAttach", "onCancelPointerInput", "onDetach", "onDrawCacheReadsChanged", "onDrawCacheReadsChanged$ui_release", "onFocusEvent", "focusState", "Landroidx/compose/ui/focus/FocusState;", "onGloballyPositioned", "coordinates", "onLookaheadPlaced", "Landroidx/compose/ui/layout/LookaheadLayoutCoordinates;", "onMeasureResultChanged", "onPlaced", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onRemeasured", "onRemeasured-ozmzZPI", "sharePointerInputWithSiblings", "toString", "", "unInitializeModifier", "updateDrawCache", "updateModifierLocalConsumer", "updateModifierLocalProvider", "Landroidx/compose/ui/modifier/ModifierLocalProvider;", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "modifyParentData", "", "parentData", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BackwardsCompatNode extends Modifier.Node implements LayoutModifierNode, IntermediateLayoutModifierNode, DrawModifierNode, SemanticsModifierNode, PointerInputModifierNode, ModifierLocalNode, ModifierLocalReadScope, ParentDataModifierNode, LayoutAwareModifierNode, GlobalPositionAwareModifierNode, FocusEventModifierNode, FocusPropertiesModifierNode, FocusRequesterModifierNode, OwnerScope, BuildDrawCacheParams {
    private BackwardsCompatLocalMap _providedValues;
    private Modifier.Element element;
    private boolean invalidateCache;
    private LayoutCoordinates lastOnPlacedCoordinates;
    private HashSet<ModifierLocal<?>> readValues;

    public final Modifier.Element getElement() {
        return this.element;
    }

    public final HashSet<ModifierLocal<?>> getReadValues() {
        return this.readValues;
    }

    public final void setReadValues(HashSet<ModifierLocal<?>> hashSet) {
        Intrinsics.checkNotNullParameter(hashSet, "<set-?>");
        this.readValues = hashSet;
    }

    public BackwardsCompatNode(Modifier.Element element) {
        Intrinsics.checkNotNullParameter(element, "element");
        setKindSet$ui_release(NodeKindKt.calculateNodeKindSetFrom(element));
        this.element = element;
        this.invalidateCache = true;
        this.readValues = new HashSet<>();
    }

    public final void setElement(Modifier.Element value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (getIsAttached()) {
            unInitializeModifier();
        }
        this.element = value;
        setKindSet$ui_release(NodeKindKt.calculateNodeKindSetFrom(value));
        if (getIsAttached()) {
            initializeModifier(false);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        initializeModifier(true);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        unInitializeModifier();
    }

    private final void unInitializeModifier() {
        if (!getIsAttached()) {
            throw new IllegalStateException("Check failed.".toString());
        }
        Modifier.Element element = this.element;
        BackwardsCompatNode backwardsCompatNode = this;
        if ((NodeKind.m3598constructorimpl(32) & backwardsCompatNode.getKindSet()) != 0) {
            if (element instanceof ModifierLocalProvider) {
                DelegatableNodeKt.requireOwner(this).getModifierLocalManager().removedProvider(this, ((ModifierLocalProvider) element).getKey());
            }
            if (element instanceof ModifierLocalConsumer) {
                ((ModifierLocalConsumer) element).onModifierLocalsUpdated(BackwardsCompatNodeKt.DetachedModifierLocalReadScope);
            }
        }
        if ((backwardsCompatNode.getKindSet() & NodeKind.m3598constructorimpl(8)) != 0) {
            DelegatableNodeKt.requireOwner(this).onSemanticsChange();
        }
        if (element instanceof FocusRequesterModifier) {
            ((FocusRequesterModifier) element).getFocusRequester().getFocusRequesterNodes$ui_release().remove(this);
        }
    }

    private final void initializeModifier(boolean duringAttach) {
        if (!getIsAttached()) {
            throw new IllegalStateException("Check failed.".toString());
        }
        Modifier.Element element = this.element;
        BackwardsCompatNode backwardsCompatNode = this;
        if ((NodeKind.m3598constructorimpl(32) & backwardsCompatNode.getKindSet()) != 0) {
            if (element instanceof ModifierLocalProvider) {
                updateModifierLocalProvider((ModifierLocalProvider) element);
            }
            if (element instanceof ModifierLocalConsumer) {
                if (duringAttach) {
                    updateModifierLocalConsumer();
                } else {
                    sideEffect(new Function0<Unit>() { // from class: androidx.compose.ui.node.BackwardsCompatNode.initializeModifier.1
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public /* bridge */ /* synthetic */ Unit invoke() {
                            invoke2();
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2() {
                            BackwardsCompatNode.this.updateModifierLocalConsumer();
                        }
                    });
                }
            }
        }
        if ((NodeKind.m3598constructorimpl(4) & backwardsCompatNode.getKindSet()) != 0) {
            if (element instanceof DrawCacheModifier) {
                this.invalidateCache = true;
            }
            if (!duringAttach) {
                LayoutModifierNodeKt.invalidateLayer(this);
            }
        }
        if ((NodeKind.m3598constructorimpl(2) & backwardsCompatNode.getKindSet()) != 0) {
            BackwardsCompatNode backwardsCompatNode2 = this;
            if (DelegatableNodeKt.requireLayoutNode(backwardsCompatNode2).getNodes().getTail().getIsAttached()) {
                NodeCoordinator coordinator$ui_release = getCoordinator();
                Intrinsics.checkNotNull(coordinator$ui_release);
                ((LayoutModifierNodeCoordinator) coordinator$ui_release).setLayoutModifierNode$ui_release(this);
                coordinator$ui_release.onLayoutModifierNodeChanged();
            }
            if (!duringAttach) {
                LayoutModifierNodeKt.invalidateLayer(this);
                DelegatableNodeKt.requireLayoutNode(backwardsCompatNode2).invalidateMeasurements$ui_release();
            }
        }
        if (element instanceof RemeasurementModifier) {
            ((RemeasurementModifier) element).onRemeasurementAvailable(this);
        }
        if ((NodeKind.m3598constructorimpl(128) & backwardsCompatNode.getKindSet()) != 0) {
            if (element instanceof OnRemeasuredModifier) {
                BackwardsCompatNode backwardsCompatNode3 = this;
                if (DelegatableNodeKt.requireLayoutNode(backwardsCompatNode3).getNodes().getTail().getIsAttached()) {
                    DelegatableNodeKt.requireLayoutNode(backwardsCompatNode3).invalidateMeasurements$ui_release();
                }
            }
            if (element instanceof OnPlacedModifier) {
                this.lastOnPlacedCoordinates = null;
                BackwardsCompatNode backwardsCompatNode4 = this;
                if (DelegatableNodeKt.requireLayoutNode(backwardsCompatNode4).getNodes().getTail().getIsAttached()) {
                    DelegatableNodeKt.requireOwner(backwardsCompatNode4).registerOnLayoutCompletedListener(new Owner.OnLayoutCompletedListener() { // from class: androidx.compose.ui.node.BackwardsCompatNode.initializeModifier.2
                        @Override // androidx.compose.ui.node.Owner.OnLayoutCompletedListener
                        public void onLayoutComplete() {
                            if (BackwardsCompatNode.this.lastOnPlacedCoordinates == null) {
                                BackwardsCompatNode backwardsCompatNode5 = BackwardsCompatNode.this;
                                backwardsCompatNode5.onPlaced(DelegatableNodeKt.m3502requireCoordinator64DMado(backwardsCompatNode5, NodeKind.m3598constructorimpl(128)));
                            }
                        }
                    });
                }
            }
        }
        if ((NodeKind.m3598constructorimpl(256) & backwardsCompatNode.getKindSet()) != 0 && (element instanceof OnGloballyPositionedModifier)) {
            BackwardsCompatNode backwardsCompatNode5 = this;
            if (DelegatableNodeKt.requireLayoutNode(backwardsCompatNode5).getNodes().getTail().getIsAttached()) {
                DelegatableNodeKt.requireLayoutNode(backwardsCompatNode5).invalidateMeasurements$ui_release();
            }
        }
        if (element instanceof FocusRequesterModifier) {
            ((FocusRequesterModifier) element).getFocusRequester().getFocusRequesterNodes$ui_release().add(this);
        }
        if ((NodeKind.m3598constructorimpl(16) & backwardsCompatNode.getKindSet()) != 0 && (element instanceof PointerInputModifier)) {
            ((PointerInputModifier) element).getPointerInputFilter().setLayoutCoordinates$ui_release(getCoordinator());
        }
        if ((NodeKind.m3598constructorimpl(8) & backwardsCompatNode.getKindSet()) != 0) {
            DelegatableNodeKt.requireOwner(this).onSemanticsChange();
        }
    }

    @Override // androidx.compose.ui.draw.BuildDrawCacheParams
    public Density getDensity() {
        return DelegatableNodeKt.requireLayoutNode(this).getDensity();
    }

    @Override // androidx.compose.ui.draw.BuildDrawCacheParams
    public LayoutDirection getLayoutDirection() {
        return DelegatableNodeKt.requireLayoutNode(this).getLayoutDirection();
    }

    @Override // androidx.compose.ui.draw.BuildDrawCacheParams
    /* renamed from: getSize-NH-jbRc */
    public long mo1550getSizeNHjbRc() {
        return IntSizeKt.m4560toSizeozmzZPI(DelegatableNodeKt.m3502requireCoordinator64DMado(this, NodeKind.m3598constructorimpl(128)).mo3401getSizeYbymL2g());
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void onMeasureResultChanged() {
        this.invalidateCache = true;
        DrawModifierNodeKt.invalidateDraw(this);
    }

    private final void updateDrawCache() {
        final Modifier.Element element = this.element;
        if (element instanceof DrawCacheModifier) {
            DelegatableNodeKt.requireOwner(this).getSnapshotObserver().observeReads$ui_release(this, BackwardsCompatNodeKt.onDrawCacheReadsChanged, new Function0<Unit>() { // from class: androidx.compose.ui.node.BackwardsCompatNode.updateDrawCache.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    ((DrawCacheModifier) element).onBuildCache(this);
                }
            });
        }
        this.invalidateCache = false;
    }

    public final void onDrawCacheReadsChanged$ui_release() {
        this.invalidateCache = true;
        DrawModifierNodeKt.invalidateDraw(this);
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalNode
    public ModifierLocalMap getProvidedValues() {
        BackwardsCompatLocalMap backwardsCompatLocalMap = this._providedValues;
        return backwardsCompatLocalMap != null ? backwardsCompatLocalMap : ModifierLocalNodeKt.modifierLocalMapOf();
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalNode, androidx.compose.ui.modifier.ModifierLocalReadScope
    public <T> T getCurrent(ModifierLocal<T> modifierLocal) {
        NodeChain nodes;
        Intrinsics.checkNotNullParameter(modifierLocal, "<this>");
        this.readValues.add(modifierLocal);
        BackwardsCompatNode backwardsCompatNode = this;
        int iM3598constructorimpl = NodeKind.m3598constructorimpl(32);
        if (!backwardsCompatNode.getNode().getIsAttached()) {
            throw new IllegalStateException("Check failed.".toString());
        }
        Modifier.Node parent = backwardsCompatNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(backwardsCompatNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM3598constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM3598constructorimpl) != 0 && (parent instanceof ModifierLocalNode)) {
                        ModifierLocalNode modifierLocalNode = (ModifierLocalNode) parent;
                        if (modifierLocalNode.getProvidedValues().contains$ui_release(modifierLocal)) {
                            return (T) modifierLocalNode.getProvidedValues().get$ui_release(modifierLocal);
                        }
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return modifierLocal.getDefaultFactory$ui_release().invoke();
    }

    public final void updateModifierLocalConsumer() {
        if (getIsAttached()) {
            this.readValues.clear();
            DelegatableNodeKt.requireOwner(this).getSnapshotObserver().observeReads$ui_release(this, BackwardsCompatNodeKt.updateModifierLocalConsumer, new Function0<Unit>() { // from class: androidx.compose.ui.node.BackwardsCompatNode.updateModifierLocalConsumer.1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Modifier.Element element = BackwardsCompatNode.this.getElement();
                    Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.modifier.ModifierLocalConsumer");
                    ((ModifierLocalConsumer) element).onModifierLocalsUpdated(BackwardsCompatNode.this);
                }
            });
        }
    }

    private final void updateModifierLocalProvider(ModifierLocalProvider<?> element) {
        BackwardsCompatLocalMap backwardsCompatLocalMap = this._providedValues;
        if (backwardsCompatLocalMap != null && backwardsCompatLocalMap.contains$ui_release(element.getKey())) {
            backwardsCompatLocalMap.setElement(element);
            DelegatableNodeKt.requireOwner(this).getModifierLocalManager().updatedProvider(this, element.getKey());
            return;
        }
        this._providedValues = new BackwardsCompatLocalMap(element);
        BackwardsCompatNode backwardsCompatNode = this;
        if (DelegatableNodeKt.requireLayoutNode(backwardsCompatNode).getNodes().getTail().getIsAttached()) {
            DelegatableNodeKt.requireOwner(backwardsCompatNode).getModifierLocalManager().insertedProvider(this, element.getKey());
        }
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public boolean isValidOwnerScope() {
        return getIsAttached();
    }

    @Override // androidx.compose.ui.node.IntermediateLayoutModifierNode
    /* renamed from: getTargetSize-YbymL2g, reason: not valid java name */
    public long mo3482getTargetSizeYbymL2g() {
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.IntermediateLayoutModifier");
        return ((IntermediateLayoutModifier) element).getTargetSize();
    }

    @Override // androidx.compose.ui.node.IntermediateLayoutModifierNode
    /* renamed from: setTargetSize-ozmzZPI, reason: not valid java name */
    public void mo3485setTargetSizeozmzZPI(long j) {
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.IntermediateLayoutModifier");
        ((IntermediateLayoutModifier) element).mo3399setTargetSizeozmzZPI(j);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public MeasureResult mo1556measure3p2s80s(MeasureScope measure, Measurable measurable, long j) {
        Intrinsics.checkNotNullParameter(measure, "$this$measure");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier");
        return ((LayoutModifier) element).mo300measure3p2s80s(measure, measurable, j);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier");
        return ((LayoutModifier) element).minIntrinsicWidth(intrinsicMeasureScope, measurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier");
        return ((LayoutModifier) element).minIntrinsicHeight(intrinsicMeasureScope, measurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier");
        return ((LayoutModifier) element).maxIntrinsicWidth(intrinsicMeasureScope, measurable, i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable measurable, int i) {
        Intrinsics.checkNotNullParameter(intrinsicMeasureScope, "<this>");
        Intrinsics.checkNotNullParameter(measurable, "measurable");
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier");
        return ((LayoutModifier) element).maxIntrinsicHeight(intrinsicMeasureScope, measurable, i);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        Intrinsics.checkNotNullParameter(contentDrawScope, "<this>");
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.draw.DrawModifier");
        DrawModifier drawModifier = (DrawModifier) element;
        if (this.invalidateCache && (element instanceof DrawCacheModifier)) {
            updateDrawCache();
        }
        drawModifier.draw(contentDrawScope);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public SemanticsConfiguration getSemanticsConfiguration() {
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.semantics.SemanticsModifier");
        return ((SemanticsModifier) element).getSemanticsConfiguration();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* renamed from: onPointerEvent-H0pRuoY, reason: not valid java name */
    public void mo3483onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        Intrinsics.checkNotNullParameter(pointerEvent, "pointerEvent");
        Intrinsics.checkNotNullParameter(pass, "pass");
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
        ((PointerInputModifier) element).getPointerInputFilter().mo3299onPointerEventH0pRuoY(pointerEvent, pass, bounds);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public void onCancelPointerInput() {
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
        ((PointerInputModifier) element).getPointerInputFilter().onCancel();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public boolean sharePointerInputWithSiblings() {
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
        return ((PointerInputModifier) element).getPointerInputFilter().getShareWithSiblings();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public boolean interceptOutOfBoundsChildEvents() {
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
        return ((PointerInputModifier) element).getPointerInputFilter().getInterceptOutOfBoundsChildEvents();
    }

    @Override // androidx.compose.ui.node.ParentDataModifierNode
    public Object modifyParentData(Density density, Object obj) {
        Intrinsics.checkNotNullParameter(density, "<this>");
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.ParentDataModifier");
        return ((ParentDataModifier) element).modifyParentData(density, obj);
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        Intrinsics.checkNotNullParameter(coordinates, "coordinates");
        Modifier.Element element = this.element;
        Intrinsics.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.OnGloballyPositionedModifier");
        ((OnGloballyPositionedModifier) element).onGloballyPositioned(coordinates);
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    public void onLookaheadPlaced(LookaheadLayoutCoordinates coordinates) {
        Intrinsics.checkNotNullParameter(coordinates, "coordinates");
        Modifier.Element element = this.element;
        if (element instanceof LookaheadOnPlacedModifier) {
            ((LookaheadOnPlacedModifier) element).onPlaced(coordinates);
        }
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    /* renamed from: onRemeasured-ozmzZPI, reason: not valid java name */
    public void mo3484onRemeasuredozmzZPI(long size) {
        Modifier.Element element = this.element;
        if (element instanceof OnRemeasuredModifier) {
            ((OnRemeasuredModifier) element).mo500onRemeasuredozmzZPI(size);
        }
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    public void onPlaced(LayoutCoordinates coordinates) {
        Intrinsics.checkNotNullParameter(coordinates, "coordinates");
        this.lastOnPlacedCoordinates = coordinates;
        Modifier.Element element = this.element;
        if (element instanceof OnPlacedModifier) {
            ((OnPlacedModifier) element).onPlaced(coordinates);
        }
    }

    @Override // androidx.compose.ui.focus.FocusEventModifierNode
    public void onFocusEvent(FocusState focusState) {
        Intrinsics.checkNotNullParameter(focusState, "focusState");
        Modifier.Element element = this.element;
        if (!(element instanceof FocusEventModifier)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        ((FocusEventModifier) element).onFocusEvent(focusState);
    }

    @Override // androidx.compose.ui.focus.FocusPropertiesModifierNode
    public void modifyFocusProperties(FocusProperties focusProperties) {
        Intrinsics.checkNotNullParameter(focusProperties, "focusProperties");
        Modifier.Element element = this.element;
        if (!(element instanceof FocusOrderModifier)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        new FocusOrderModifierToProperties((FocusOrderModifier) element).invoke((FocusOrderModifierToProperties) focusProperties);
    }

    public String toString() {
        return this.element.toString();
    }
}
