package androidx.compose.ui.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.layout.SubcomposeSlotReusePolicy;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeKt;
import androidx.compose.ui.platform.Wrapper_androidKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.messaging.Constants;
import io.sentry.rrweb.RRWebVideoEvent;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SubcomposeLayout.kt */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001:\u0002MNB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J(\u0010!\u001a\u00020\"2\u001d\u0010#\u001a\u0019\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0$¢\u0006\u0002\b(ø\u0001\u0000J\u0010\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u0010H\u0002J\u0006\u0010+\u001a\u00020,J\u000e\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\u0010J\u0006\u0010/\u001a\u00020,J\u0012\u00100\u001a\u0004\u0018\u00010\u00012\u0006\u0010*\u001a\u00020\u0010H\u0002J\u0017\u00101\u001a\u00020,2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020,02H\u0082\bJ\u0006\u00103\u001a\u00020,J\"\u00104\u001a\u00020,2\u0006\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u00020\u00102\b\b\u0002\u00107\u001a\u00020\u0010H\u0002J(\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010\u00012\u0011\u0010;\u001a\r\u0012\u0004\u0012\u00020,02¢\u0006\u0002\b<¢\u0006\u0002\u0010=J\u0018\u0010>\u001a\u00020,2\u0006\u0010?\u001a\u00020\u00032\u0006\u0010@\u001a\u00020\u0013H\u0002J2\u0010>\u001a\u00020,2\u0006\u0010?\u001a\u00020\u00032\b\u0010:\u001a\u0004\u0018\u00010\u00012\u0011\u0010;\u001a\r\u0012\u0004\u0012\u00020,02¢\u0006\u0002\b<H\u0002¢\u0006\u0002\u0010AJ.\u0010>\u001a\b\u0012\u0004\u0012\u00020C0B2\b\u0010:\u001a\u0004\u0018\u00010\u00012\u0011\u0010;\u001a\r\u0012\u0004\u0012\u00020,02¢\u0006\u0002\b<¢\u0006\u0002\u0010DJ:\u0010E\u001a\u00020F2\b\u0010G\u001a\u0004\u0018\u00010F2\u0006\u0010H\u001a\u00020\u00032\u0006\u0010I\u001a\u00020\n2\u0011\u0010J\u001a\r\u0012\u0004\u0012\u00020,02¢\u0006\u0002\b<H\u0002¢\u0006\u0002\u0010KJ\u0014\u0010L\u001a\u0004\u0018\u00010\u00032\b\u0010:\u001a\u0004\u0018\u00010\u0001H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u00030\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0019\u001a\u00060\u001aR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u00030\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 \u0082\u0002\u0004\n\u0002\b\u0019¨\u0006O"}, d2 = {"Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;", "", "root", "Landroidx/compose/ui/node/LayoutNode;", "slotReusePolicy", "Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;", "(Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;)V", "NoIntrinsicsMessage", "", "compositionContext", "Landroidx/compose/runtime/CompositionContext;", "getCompositionContext", "()Landroidx/compose/runtime/CompositionContext;", "setCompositionContext", "(Landroidx/compose/runtime/CompositionContext;)V", "currentIndex", "", "nodeToNodeState", "", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$NodeState;", "precomposeMap", "precomposedCount", "reusableCount", "reusableSlotIdsSet", "Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy$SlotIdsSet;", "scope", "Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$Scope;", "slotIdToNode", "value", "getSlotReusePolicy", "()Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;", "setSlotReusePolicy", "(Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;)V", "createMeasurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "block", "Lkotlin/Function2;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "Lkotlin/ExtensionFunctionType;", "createNodeAt", "index", "disposeCurrentNodes", "", "disposeOrReuseStartingFromIndex", "startIndex", "forceRecomposeChildren", "getSlotIdAtIndex", "ignoreRemeasureRequests", "Lkotlin/Function0;", "makeSureStateIsConsistent", "move", Constants.MessagePayloadKeys.FROM, "to", "count", "precompose", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "slotId", "content", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "subcompose", "node", "nodeState", "(Landroidx/compose/ui/node/LayoutNode;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "", "Landroidx/compose/ui/layout/Measurable;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/List;", "subcomposeInto", "Landroidx/compose/runtime/Composition;", "existing", RRWebVideoEvent.JsonKeys.CONTAINER, "parent", "composable", "(Landroidx/compose/runtime/Composition;Landroidx/compose/ui/node/LayoutNode;Landroidx/compose/runtime/CompositionContext;Lkotlin/jvm/functions/Function2;)Landroidx/compose/runtime/Composition;", "takeNodeFromReusables", "NodeState", "Scope", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LayoutNodeSubcompositionsState {
    private final String NoIntrinsicsMessage;
    private CompositionContext compositionContext;
    private int currentIndex;
    private final Map<LayoutNode, NodeState> nodeToNodeState;
    private final Map<Object, LayoutNode> precomposeMap;
    private int precomposedCount;
    private int reusableCount;
    private final SubcomposeSlotReusePolicy.SlotIdsSet reusableSlotIdsSet;
    private final LayoutNode root;
    private final Scope scope;
    private final Map<Object, LayoutNode> slotIdToNode;
    private SubcomposeSlotReusePolicy slotReusePolicy;

    public final CompositionContext getCompositionContext() {
        return this.compositionContext;
    }

    public final SubcomposeSlotReusePolicy getSlotReusePolicy() {
        return this.slotReusePolicy;
    }

    public final void setCompositionContext(CompositionContext compositionContext) {
        this.compositionContext = compositionContext;
    }

    public LayoutNodeSubcompositionsState(LayoutNode root, SubcomposeSlotReusePolicy slotReusePolicy) {
        Intrinsics.checkNotNullParameter(root, "root");
        Intrinsics.checkNotNullParameter(slotReusePolicy, "slotReusePolicy");
        this.root = root;
        this.slotReusePolicy = slotReusePolicy;
        this.nodeToNodeState = new LinkedHashMap();
        this.slotIdToNode = new LinkedHashMap();
        this.scope = new Scope();
        this.precomposeMap = new LinkedHashMap();
        this.reusableSlotIdsSet = new SubcomposeSlotReusePolicy.SlotIdsSet(null, 1, null);
        this.NoIntrinsicsMessage = "Asking for intrinsic measurements of SubcomposeLayout layouts is not supported. This includes components that are built on top of SubcomposeLayout, such as lazy lists, BoxWithConstraints, TabRow, etc. To mitigate this:\n- if intrinsic measurements are used to achieve 'match parent' sizing,, consider replacing the parent of the component with a custom layout which controls the order in which children are measured, making intrinsic measurement not needed\n- adding a size modifier to the component, in order to fast return the queried intrinsic measurement.";
    }

    public final void setSlotReusePolicy(SubcomposeSlotReusePolicy value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (this.slotReusePolicy != value) {
            this.slotReusePolicy = value;
            disposeOrReuseStartingFromIndex(0);
        }
    }

    public final List<Measurable> subcompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        makeSureStateIsConsistent();
        LayoutNode.LayoutState layoutState$ui_release = this.root.getLayoutState$ui_release();
        if (layoutState$ui_release != LayoutNode.LayoutState.Measuring && layoutState$ui_release != LayoutNode.LayoutState.LayingOut) {
            throw new IllegalStateException("subcompose can only be used inside the measure or layout blocks".toString());
        }
        Map<Object, LayoutNode> map = this.slotIdToNode;
        LayoutNode layoutNodeRemove = map.get(slotId);
        if (layoutNodeRemove == null) {
            layoutNodeRemove = this.precomposeMap.remove(slotId);
            if (layoutNodeRemove != null) {
                int i = this.precomposedCount;
                if (i <= 0) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                this.precomposedCount = i - 1;
            } else {
                layoutNodeRemove = takeNodeFromReusables(slotId);
                if (layoutNodeRemove == null) {
                    layoutNodeRemove = createNodeAt(this.currentIndex);
                }
            }
            map.put(slotId, layoutNodeRemove);
        }
        LayoutNode layoutNode = layoutNodeRemove;
        int iIndexOf = this.root.getFoldedChildren$ui_release().indexOf(layoutNode);
        int i2 = this.currentIndex;
        if (iIndexOf < i2) {
            throw new IllegalArgumentException(("Key \"" + slotId + "\" was already used. If you are using LazyColumn/Row please make sure you provide a unique key for each item.").toString());
        }
        if (i2 != iIndexOf) {
            move$default(this, iIndexOf, i2, 0, 4, null);
        }
        this.currentIndex++;
        subcompose(layoutNode, slotId, content);
        return layoutNode.getChildMeasurables$ui_release();
    }

    private final void subcompose(LayoutNode node, final NodeState nodeState) {
        Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
        try {
            Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
            try {
                LayoutNode layoutNode = this.root;
                layoutNode.ignoreRemeasureRequests = true;
                final Function2<Composer, Integer, Unit> content = nodeState.getContent();
                Composition composition = nodeState.getComposition();
                CompositionContext compositionContext = this.compositionContext;
                if (compositionContext != null) {
                    nodeState.setComposition(subcomposeInto(composition, node, compositionContext, ComposableLambdaKt.composableLambdaInstance(-34810602, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$subcompose$3$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer, int i) {
                            ComposerKt.sourceInformation(composer, "C446@19062L46:SubcomposeLayout.kt#80mrfh");
                            if ((i & 11) == 2 && composer.getSkipping()) {
                                composer.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-34810602, i, -1, "androidx.compose.ui.layout.LayoutNodeSubcompositionsState.subcompose.<anonymous>.<anonymous>.<anonymous> (SubcomposeLayout.kt:445)");
                            }
                            boolean active = nodeState.getActive();
                            Function2<Composer, Integer, Unit> function2 = content;
                            composer.startReusableGroup(207, Boolean.valueOf(active));
                            boolean zChanged = composer.changed(active);
                            if (active) {
                                function2.invoke(composer, 0);
                            } else {
                                composer.deactivateToEndGroup(zChanged);
                            }
                            composer.endReusableGroup();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    })));
                    layoutNode.ignoreRemeasureRequests = false;
                    Unit unit = Unit.INSTANCE;
                    return;
                }
                throw new IllegalStateException("parent composition reference not set".toString());
            } finally {
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
            }
        } finally {
            snapshotCreateNonObservableSnapshot.dispose();
        }
    }

    private final Composition subcomposeInto(Composition existing, LayoutNode container, CompositionContext parent, Function2<? super Composer, ? super Integer, Unit> composable) {
        if (existing == null || existing.getDisposed()) {
            existing = Wrapper_androidKt.createSubcomposition(container, parent);
        }
        existing.setContent(composable);
        return existing;
    }

    private final Object getSlotIdAtIndex(int index) {
        NodeState nodeState = this.nodeToNodeState.get(this.root.getFoldedChildren$ui_release().get(index));
        Intrinsics.checkNotNull(nodeState);
        return nodeState.getSlotId();
    }

    public final void disposeOrReuseStartingFromIndex(int startIndex) {
        this.reusableCount = 0;
        int size = (this.root.getFoldedChildren$ui_release().size() - this.precomposedCount) - 1;
        if (startIndex <= size) {
            this.reusableSlotIdsSet.clear();
            if (startIndex <= size) {
                int i = startIndex;
                while (true) {
                    this.reusableSlotIdsSet.add(getSlotIdAtIndex(i));
                    if (i == size) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
            this.slotReusePolicy.getSlotsToRetain(this.reusableSlotIdsSet);
            Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
            try {
                Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
                boolean z = false;
                while (size >= startIndex) {
                    try {
                        LayoutNode layoutNode = this.root.getFoldedChildren$ui_release().get(size);
                        NodeState nodeState = this.nodeToNodeState.get(layoutNode);
                        Intrinsics.checkNotNull(nodeState);
                        NodeState nodeState2 = nodeState;
                        Object slotId = nodeState2.getSlotId();
                        if (!this.reusableSlotIdsSet.contains(slotId)) {
                            LayoutNode layoutNode2 = this.root;
                            layoutNode2.ignoreRemeasureRequests = true;
                            this.nodeToNodeState.remove(layoutNode);
                            Composition composition = nodeState2.getComposition();
                            if (composition != null) {
                                composition.dispose();
                            }
                            this.root.removeAt$ui_release(size, 1);
                            layoutNode2.ignoreRemeasureRequests = false;
                        } else {
                            layoutNode.setMeasuredByParent$ui_release(LayoutNode.UsageByParent.NotUsed);
                            this.reusableCount++;
                            if (nodeState2.getActive()) {
                                nodeState2.setActive(false);
                                z = true;
                            }
                        }
                        this.slotIdToNode.remove(slotId);
                        size--;
                    } finally {
                        snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
                    }
                }
                Unit unit = Unit.INSTANCE;
                if (z) {
                    Snapshot.INSTANCE.sendApplyNotifications();
                }
            } finally {
                snapshotCreateNonObservableSnapshot.dispose();
            }
        }
        makeSureStateIsConsistent();
    }

    public final void makeSureStateIsConsistent() {
        if (this.nodeToNodeState.size() != this.root.getFoldedChildren$ui_release().size()) {
            throw new IllegalArgumentException(("Inconsistency between the count of nodes tracked by the state (" + this.nodeToNodeState.size() + ") and the children count on the SubcomposeLayout (" + this.root.getFoldedChildren$ui_release().size() + "). Are you trying to use the state of the disposed SubcomposeLayout?").toString());
        }
        if ((this.root.getFoldedChildren$ui_release().size() - this.reusableCount) - this.precomposedCount < 0) {
            throw new IllegalArgumentException(("Incorrect state. Total children " + this.root.getFoldedChildren$ui_release().size() + ". Reusable children " + this.reusableCount + ". Precomposed children " + this.precomposedCount).toString());
        }
        if (this.precomposeMap.size() != this.precomposedCount) {
            throw new IllegalArgumentException(("Incorrect state. Precomposed children " + this.precomposedCount + ". Map size " + this.precomposeMap.size()).toString());
        }
    }

    private final LayoutNode takeNodeFromReusables(Object slotId) {
        int i;
        if (this.reusableCount == 0) {
            return null;
        }
        int size = this.root.getFoldedChildren$ui_release().size() - this.precomposedCount;
        int i2 = size - this.reusableCount;
        int i3 = size - 1;
        int i4 = i3;
        while (true) {
            if (i4 < i2) {
                i = -1;
                break;
            }
            if (Intrinsics.areEqual(getSlotIdAtIndex(i4), slotId)) {
                i = i4;
                break;
            }
            i4--;
        }
        if (i == -1) {
            while (true) {
                if (i3 < i2) {
                    i4 = i3;
                    break;
                }
                NodeState nodeState = this.nodeToNodeState.get(this.root.getFoldedChildren$ui_release().get(i3));
                Intrinsics.checkNotNull(nodeState);
                NodeState nodeState2 = nodeState;
                if (this.slotReusePolicy.areCompatible(slotId, nodeState2.getSlotId())) {
                    nodeState2.setSlotId(slotId);
                    i4 = i3;
                    i = i4;
                    break;
                }
                i3--;
            }
        }
        if (i == -1) {
            return null;
        }
        if (i4 != i2) {
            move(i4, i2, 1);
        }
        this.reusableCount--;
        LayoutNode layoutNode = this.root.getFoldedChildren$ui_release().get(i2);
        NodeState nodeState3 = this.nodeToNodeState.get(layoutNode);
        Intrinsics.checkNotNull(nodeState3);
        NodeState nodeState4 = nodeState3;
        nodeState4.setActive(true);
        nodeState4.setForceRecompose(true);
        Snapshot.INSTANCE.sendApplyNotifications();
        return layoutNode;
    }

    public final MeasurePolicy createMeasurePolicy(final Function2<? super SubcomposeMeasureScope, ? super Constraints, ? extends MeasureResult> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return new LayoutNode.NoIntrinsicsMeasurePolicy(this.NoIntrinsicsMessage) { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.createMeasurePolicy.1
            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* renamed from: measure-3p2s80s */
            public MeasureResult mo287measure3p2s80s(MeasureScope measure, List<? extends Measurable> measurables, long j) {
                Intrinsics.checkNotNullParameter(measure, "$this$measure");
                Intrinsics.checkNotNullParameter(measurables, "measurables");
                LayoutNodeSubcompositionsState.this.scope.setLayoutDirection(measure.getLayoutDirection());
                LayoutNodeSubcompositionsState.this.scope.setDensity(measure.getDensity());
                LayoutNodeSubcompositionsState.this.scope.setFontScale(measure.getFontScale());
                LayoutNodeSubcompositionsState.this.currentIndex = 0;
                final MeasureResult measureResultInvoke = block.invoke(LayoutNodeSubcompositionsState.this.scope, Constraints.m4334boximpl(j));
                final int i = LayoutNodeSubcompositionsState.this.currentIndex;
                final LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
                return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$createMeasurePolicy$1$measure$1
                    @Override // androidx.compose.ui.layout.MeasureResult
                    public int getWidth() {
                        return measureResultInvoke.getWidth();
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    public int getHeight() {
                        return measureResultInvoke.getHeight();
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    public Map<AlignmentLine, Integer> getAlignmentLines() {
                        return measureResultInvoke.getAlignmentLines();
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    public void placeChildren() {
                        layoutNodeSubcompositionsState.currentIndex = i;
                        measureResultInvoke.placeChildren();
                        LayoutNodeSubcompositionsState layoutNodeSubcompositionsState2 = layoutNodeSubcompositionsState;
                        layoutNodeSubcompositionsState2.disposeOrReuseStartingFromIndex(layoutNodeSubcompositionsState2.currentIndex);
                    }
                };
            }
        };
    }

    public final SubcomposeLayoutState.PrecomposedSlotHandle precompose(final Object slotId, Function2<? super Composer, ? super Integer, Unit> content) {
        Intrinsics.checkNotNullParameter(content, "content");
        makeSureStateIsConsistent();
        if (!this.slotIdToNode.containsKey(slotId)) {
            Map<Object, LayoutNode> map = this.precomposeMap;
            LayoutNode layoutNodeTakeNodeFromReusables = map.get(slotId);
            if (layoutNodeTakeNodeFromReusables == null) {
                layoutNodeTakeNodeFromReusables = takeNodeFromReusables(slotId);
                if (layoutNodeTakeNodeFromReusables != null) {
                    move(this.root.getFoldedChildren$ui_release().indexOf(layoutNodeTakeNodeFromReusables), this.root.getFoldedChildren$ui_release().size(), 1);
                    this.precomposedCount++;
                } else {
                    layoutNodeTakeNodeFromReusables = createNodeAt(this.root.getFoldedChildren$ui_release().size());
                    this.precomposedCount++;
                }
                map.put(slotId, layoutNodeTakeNodeFromReusables);
            }
            subcompose(layoutNodeTakeNodeFromReusables, slotId, content);
        }
        return new SubcomposeLayoutState.PrecomposedSlotHandle() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState.precompose.1
            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
            public void dispose() {
                LayoutNodeSubcompositionsState.this.makeSureStateIsConsistent();
                LayoutNode layoutNode = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.remove(slotId);
                if (layoutNode != null) {
                    if (LayoutNodeSubcompositionsState.this.precomposedCount > 0) {
                        int iIndexOf = LayoutNodeSubcompositionsState.this.root.getFoldedChildren$ui_release().indexOf(layoutNode);
                        if (iIndexOf >= LayoutNodeSubcompositionsState.this.root.getFoldedChildren$ui_release().size() - LayoutNodeSubcompositionsState.this.precomposedCount) {
                            LayoutNodeSubcompositionsState.this.reusableCount++;
                            LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
                            layoutNodeSubcompositionsState.precomposedCount--;
                            int size = (LayoutNodeSubcompositionsState.this.root.getFoldedChildren$ui_release().size() - LayoutNodeSubcompositionsState.this.precomposedCount) - LayoutNodeSubcompositionsState.this.reusableCount;
                            LayoutNodeSubcompositionsState.this.move(iIndexOf, size, 1);
                            LayoutNodeSubcompositionsState.this.disposeOrReuseStartingFromIndex(size);
                            return;
                        }
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    throw new IllegalStateException("Check failed.".toString());
                }
            }

            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
            public int getPlaceablesCount() {
                List<LayoutNode> children$ui_release;
                LayoutNode layoutNode = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(slotId);
                if (layoutNode == null || (children$ui_release = layoutNode.getChildren$ui_release()) == null) {
                    return 0;
                }
                return children$ui_release.size();
            }

            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
            /* renamed from: premeasure-0kLqBqw, reason: not valid java name */
            public void mo3409premeasure0kLqBqw(int index, long constraints) {
                LayoutNode layoutNode = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(slotId);
                if (layoutNode == null || !layoutNode.isAttached()) {
                    return;
                }
                int size = layoutNode.getChildren$ui_release().size();
                if (index < 0 || index >= size) {
                    throw new IndexOutOfBoundsException("Index (" + index + ") is out of bound of [0, " + size + ')');
                }
                if (!layoutNode.getIsPlaced()) {
                    LayoutNode layoutNode2 = LayoutNodeSubcompositionsState.this.root;
                    layoutNode2.ignoreRemeasureRequests = true;
                    LayoutNodeKt.requireOwner(layoutNode).mo3649measureAndLayout0kLqBqw(layoutNode.getChildren$ui_release().get(index), constraints);
                    layoutNode2.ignoreRemeasureRequests = false;
                    return;
                }
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        };
    }

    private final LayoutNode createNodeAt(int index) {
        LayoutNode layoutNode = new LayoutNode(true, 0, 2, null);
        LayoutNode layoutNode2 = this.root;
        layoutNode2.ignoreRemeasureRequests = true;
        this.root.insertAt$ui_release(index, layoutNode);
        layoutNode2.ignoreRemeasureRequests = false;
        return layoutNode;
    }

    static /* synthetic */ void move$default(LayoutNodeSubcompositionsState layoutNodeSubcompositionsState, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i3 = 1;
        }
        layoutNodeSubcompositionsState.move(i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SubcomposeLayout.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0018\b\u0002\u0018\u00002\u00020\u0001B.\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tR+\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R'\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001c\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000e\"\u0004\b\u001e\u0010\u0010R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$NodeState;", "", "slotId", "content", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "composition", "Landroidx/compose/runtime/Composition;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composition;)V", "<set-?>", "", "active", "getActive", "()Z", "setActive", "(Z)V", "active$delegate", "Landroidx/compose/runtime/MutableState;", "getComposition", "()Landroidx/compose/runtime/Composition;", "setComposition", "(Landroidx/compose/runtime/Composition;)V", "getContent", "()Lkotlin/jvm/functions/Function2;", "setContent", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "forceRecompose", "getForceRecompose", "setForceRecompose", "getSlotId", "()Ljava/lang/Object;", "setSlotId", "(Ljava/lang/Object;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final class NodeState {

        /* renamed from: active$delegate, reason: from kotlin metadata */
        private final MutableState active;
        private Composition composition;
        private Function2<? super Composer, ? super Integer, Unit> content;
        private boolean forceRecompose;
        private Object slotId;

        public final Composition getComposition() {
            return this.composition;
        }

        public final Function2<Composer, Integer, Unit> getContent() {
            return this.content;
        }

        public final boolean getForceRecompose() {
            return this.forceRecompose;
        }

        public final Object getSlotId() {
            return this.slotId;
        }

        public final void setComposition(Composition composition) {
            this.composition = composition;
        }

        public final void setContent(Function2<? super Composer, ? super Integer, Unit> function2) {
            Intrinsics.checkNotNullParameter(function2, "<set-?>");
            this.content = function2;
        }

        public final void setForceRecompose(boolean z) {
            this.forceRecompose = z;
        }

        public final void setSlotId(Object obj) {
            this.slotId = obj;
        }

        public NodeState(Object obj, Function2<? super Composer, ? super Integer, Unit> content, Composition composition) {
            Intrinsics.checkNotNullParameter(content, "content");
            this.slotId = obj;
            this.content = content;
            this.composition = composition;
            this.active = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
        }

        public /* synthetic */ NodeState(Object obj, Function2 function2, Composition composition, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(obj, function2, (i & 4) != 0 ? null : composition);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final boolean getActive() {
            return ((Boolean) this.active.getValue()).booleanValue();
        }

        public final void setActive(boolean z) {
            this.active.setValue(Boolean.valueOf(z));
        }
    }

    /* compiled from: SubcomposeLayout.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\u0002\b\u001aH\u0016¢\u0006\u0002\u0010\u001bR\u001a\u0010\u0003\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState$Scope;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "(Landroidx/compose/ui/layout/LayoutNodeSubcompositionsState;)V", "density", "", "getDensity", "()F", "setDensity", "(F)V", "fontScale", "getFontScale", "setFontScale", ViewProps.LAYOUT_DIRECTION, "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "subcompose", "", "Landroidx/compose/ui/layout/Measurable;", "slotId", "", "content", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/List;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class Scope implements SubcomposeMeasureScope {
        private float density;
        private float fontScale;
        private LayoutDirection layoutDirection = LayoutDirection.Rtl;

        @Override // androidx.compose.ui.unit.Density
        public float getDensity() {
            return this.density;
        }

        @Override // androidx.compose.ui.unit.Density
        public float getFontScale() {
            return this.fontScale;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public LayoutDirection getLayoutDirection() {
            return this.layoutDirection;
        }

        public void setDensity(float f) {
            this.density = f;
        }

        public void setFontScale(float f) {
            this.fontScale = f;
        }

        public void setLayoutDirection(LayoutDirection layoutDirection) {
            Intrinsics.checkNotNullParameter(layoutDirection, "<set-?>");
            this.layoutDirection = layoutDirection;
        }

        public Scope() {
        }

        @Override // androidx.compose.ui.layout.SubcomposeMeasureScope
        public List<Measurable> subcompose(Object slotId, Function2<? super Composer, ? super Integer, Unit> content) {
            Intrinsics.checkNotNullParameter(content, "content");
            return LayoutNodeSubcompositionsState.this.subcompose(slotId, content);
        }
    }

    private final void subcompose(LayoutNode node, Object slotId, Function2<? super Composer, ? super Integer, Unit> content) {
        Map<LayoutNode, NodeState> map = this.nodeToNodeState;
        NodeState nodeState = map.get(node);
        if (nodeState == null) {
            nodeState = new NodeState(slotId, ComposableSingletons$SubcomposeLayoutKt.INSTANCE.m3386getLambda1$ui_release(), null, 4, null);
            map.put(node, nodeState);
        }
        NodeState nodeState2 = nodeState;
        Composition composition = nodeState2.getComposition();
        boolean hasInvalidations = composition != null ? composition.getHasInvalidations() : true;
        if (nodeState2.getContent() != content || hasInvalidations || nodeState2.getForceRecompose()) {
            nodeState2.setContent(content);
            subcompose(node, nodeState2);
            nodeState2.setForceRecompose(false);
        }
    }

    public final void forceRecomposeChildren() {
        Iterator<Map.Entry<LayoutNode, NodeState>> it = this.nodeToNodeState.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().setForceRecompose(true);
        }
        if (this.root.getMeasurePending$ui_release()) {
            return;
        }
        LayoutNode.requestRemeasure$ui_release$default(this.root, false, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void move(int from, int to, int count) {
        LayoutNode layoutNode = this.root;
        layoutNode.ignoreRemeasureRequests = true;
        this.root.move$ui_release(from, to, count);
        layoutNode.ignoreRemeasureRequests = false;
    }

    private final void ignoreRemeasureRequests(Function0<Unit> block) {
        LayoutNode layoutNode = this.root;
        layoutNode.ignoreRemeasureRequests = true;
        block.invoke();
        layoutNode.ignoreRemeasureRequests = false;
    }

    public final void disposeCurrentNodes() {
        LayoutNode layoutNode = this.root;
        layoutNode.ignoreRemeasureRequests = true;
        Iterator<T> it = this.nodeToNodeState.values().iterator();
        while (it.hasNext()) {
            Composition composition = ((NodeState) it.next()).getComposition();
            if (composition != null) {
                composition.dispose();
            }
        }
        this.root.removeAll$ui_release();
        layoutNode.ignoreRemeasureRequests = false;
        this.nodeToNodeState.clear();
        this.slotIdToNode.clear();
        this.precomposedCount = 0;
        this.reusableCount = 0;
        this.precomposeMap.clear();
        makeSureStateIsConsistent();
    }
}
