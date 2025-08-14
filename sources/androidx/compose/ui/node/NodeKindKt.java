package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.focus.FocusEventModifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusOrderModifier;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusTargetModifierNode;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.pointer.PointerInputModifier;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.layout.IntermediateLayoutModifier;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.LookaheadOnPlacedModifier;
import androidx.compose.ui.layout.OnGloballyPositionedModifier;
import androidx.compose.ui.layout.OnPlacedModifier;
import androidx.compose.ui.layout.OnRemeasuredModifier;
import androidx.compose.ui.layout.ParentDataModifier;
import androidx.compose.ui.modifier.ModifierLocalConsumer;
import androidx.compose.ui.modifier.ModifierLocalNode;
import androidx.compose.ui.modifier.ModifierLocalProvider;
import androidx.compose.ui.semantics.SemanticsModifier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NodeKind.kt */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000\u001a\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0001H\u0002\u001a\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000\u001a\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000\u001a\u0010\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0015H\u0000\u001a\u0010\u0010\u0013\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0000\u001a&\u0010\u0016\u001a\u00020\u0001*\u00020\u00012\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0080\fø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a\f\u0010\u001a\u001a\u00020\f*\u00020\u001bH\u0003\u001a\f\u0010\u001c\u001a\u00020\u0005*\u00020\u001bH\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"%\u0010\u0004\u001a\u00020\u0005*\u0006\u0012\u0002\b\u00030\u00068@X\u0080\u0004ø\u0001\u0000¢\u0006\f\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"Inserted", "", "Removed", "Updated", "includeSelfInTraversal", "", "Landroidx/compose/ui/node/NodeKind;", "getIncludeSelfInTraversal-H91voCI$annotations", "(I)V", "getIncludeSelfInTraversal-H91voCI", "(I)Z", "autoInvalidateInsertedNode", "", "node", "Landroidx/compose/ui/Modifier$Node;", "autoInvalidateNode", "phase", "autoInvalidateRemovedNode", "autoInvalidateUpdatedNode", "calculateNodeKindSetFrom", "element", "Landroidx/compose/ui/Modifier$Element;", "or", "other", "or-64DMado", "(II)I", "scheduleInvalidationOfAssociatedFocusTargets", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "specifiesCanFocusProperty", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NodeKindKt {
    private static final int Inserted = 1;
    private static final int Removed = 2;
    private static final int Updated = 0;

    /* renamed from: getIncludeSelfInTraversal-H91voCI$annotations, reason: not valid java name */
    public static /* synthetic */ void m3607getIncludeSelfInTraversalH91voCI$annotations(int i) {
    }

    /* renamed from: or-64DMado, reason: not valid java name */
    public static final int m3608or64DMado(int i, int i2) {
        return i | i2;
    }

    public static final void autoInvalidateRemovedNode(Modifier.Node node) {
        Intrinsics.checkNotNullParameter(node, "node");
        autoInvalidateNode(node, 2);
    }

    public static final void autoInvalidateInsertedNode(Modifier.Node node) {
        Intrinsics.checkNotNullParameter(node, "node");
        autoInvalidateNode(node, 1);
    }

    public static final void autoInvalidateUpdatedNode(Modifier.Node node) {
        Intrinsics.checkNotNullParameter(node, "node");
        autoInvalidateNode(node, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void autoInvalidateNode(Modifier.Node node, int i) {
        if (!node.getIsAttached()) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if ((NodeKind.m3598constructorimpl(2) & node.getKindSet()) != 0 && (node instanceof LayoutModifierNode)) {
            LayoutModifierNodeKt.invalidateMeasurements((LayoutModifierNode) node);
            if (i == 2) {
                DelegatableNodeKt.m3502requireCoordinator64DMado(node, NodeKind.m3598constructorimpl(2)).onRelease();
            }
        }
        if ((NodeKind.m3598constructorimpl(256) & node.getKindSet()) != 0 && (node instanceof GlobalPositionAwareModifierNode)) {
            DelegatableNodeKt.requireLayoutNode(node).invalidateMeasurements$ui_release();
        }
        if ((NodeKind.m3598constructorimpl(4) & node.getKindSet()) != 0 && (node instanceof DrawModifierNode)) {
            DrawModifierNodeKt.invalidateDraw((DrawModifierNode) node);
        }
        if ((NodeKind.m3598constructorimpl(8) & node.getKindSet()) != 0 && (node instanceof SemanticsModifierNode)) {
            SemanticsModifierNodeKt.invalidateSemantics((SemanticsModifierNode) node);
        }
        if ((NodeKind.m3598constructorimpl(64) & node.getKindSet()) != 0 && (node instanceof ParentDataModifierNode)) {
            ParentDataModifierNodeKt.invalidateParentData((ParentDataModifierNode) node);
        }
        if ((NodeKind.m3598constructorimpl(1024) & node.getKindSet()) != 0 && (node instanceof FocusTargetModifierNode)) {
            if (i == 2) {
                node.onReset();
            } else {
                DelegatableNodeKt.requireOwner(node).getFocusOwner().scheduleInvalidation((FocusTargetModifierNode) node);
            }
        }
        if ((NodeKind.m3598constructorimpl(2048) & node.getKindSet()) != 0 && (node instanceof FocusPropertiesModifierNode)) {
            FocusPropertiesModifierNode focusPropertiesModifierNode = (FocusPropertiesModifierNode) node;
            if (specifiesCanFocusProperty(focusPropertiesModifierNode)) {
                if (i == 2) {
                    scheduleInvalidationOfAssociatedFocusTargets(focusPropertiesModifierNode);
                } else {
                    DelegatableNodeKt.requireOwner(node).getFocusOwner().scheduleInvalidation(focusPropertiesModifierNode);
                }
            }
        }
        if ((NodeKind.m3598constructorimpl(4096) & node.getKindSet()) == 0 || !(node instanceof FocusEventModifierNode) || i == 2) {
            return;
        }
        DelegatableNodeKt.requireOwner(node).getFocusOwner().scheduleInvalidation((FocusEventModifierNode) node);
    }

    private static final void scheduleInvalidationOfAssociatedFocusTargets(FocusPropertiesModifierNode focusPropertiesModifierNode) {
        FocusPropertiesModifierNode focusPropertiesModifierNode2 = focusPropertiesModifierNode;
        int iM3598constructorimpl = NodeKind.m3598constructorimpl(1024);
        if (!focusPropertiesModifierNode2.getNode().getIsAttached()) {
            throw new IllegalStateException("Check failed.".toString());
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = focusPropertiesModifierNode2.getNode().getChild();
        if (child == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector, focusPropertiesModifierNode2.getNode());
        } else {
            mutableVector.add(child);
        }
        while (mutableVector.isNotEmpty()) {
            Modifier.Node child2 = (Modifier.Node) mutableVector.removeAt(mutableVector.getSize() - 1);
            if ((child2.getAggregateChildKindSet() & iM3598constructorimpl) == 0) {
                DelegatableNodeKt.addLayoutNodeChildren(mutableVector, child2);
            } else {
                while (true) {
                    if (child2 == null) {
                        break;
                    }
                    if ((child2.getKindSet() & iM3598constructorimpl) != 0) {
                        if (child2 instanceof FocusTargetModifierNode) {
                            DelegatableNodeKt.requireOwner(focusPropertiesModifierNode2).getFocusOwner().scheduleInvalidation((FocusTargetModifierNode) child2);
                        }
                    } else {
                        child2 = child2.getChild();
                    }
                }
            }
        }
    }

    private static final boolean specifiesCanFocusProperty(FocusPropertiesModifierNode focusPropertiesModifierNode) {
        CanFocusChecker.INSTANCE.reset();
        focusPropertiesModifierNode.modifyFocusProperties(CanFocusChecker.INSTANCE);
        return CanFocusChecker.INSTANCE.isCanFocusSet();
    }

    /* renamed from: getIncludeSelfInTraversal-H91voCI, reason: not valid java name */
    public static final boolean m3606getIncludeSelfInTraversalH91voCI(int i) {
        return (i & NodeKind.m3598constructorimpl(128)) != 0;
    }

    public static final int calculateNodeKindSetFrom(Modifier.Element element) {
        Intrinsics.checkNotNullParameter(element, "element");
        int iM3598constructorimpl = NodeKind.m3598constructorimpl(1);
        if (element instanceof LayoutModifier) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(2);
        }
        if (element instanceof IntermediateLayoutModifier) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(512);
        }
        if (element instanceof DrawModifier) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(4);
        }
        if (element instanceof SemanticsModifier) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(8);
        }
        if (element instanceof PointerInputModifier) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(16);
        }
        if ((element instanceof ModifierLocalConsumer) || (element instanceof ModifierLocalProvider)) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(32);
        }
        if (element instanceof FocusEventModifier) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(4096);
        }
        if (element instanceof FocusOrderModifier) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(2048);
        }
        if (element instanceof OnGloballyPositionedModifier) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(256);
        }
        if (element instanceof ParentDataModifier) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(64);
        }
        return ((element instanceof OnPlacedModifier) || (element instanceof OnRemeasuredModifier) || (element instanceof LookaheadOnPlacedModifier)) ? iM3598constructorimpl | NodeKind.m3598constructorimpl(128) : iM3598constructorimpl;
    }

    public static final int calculateNodeKindSetFrom(Modifier.Node node) {
        Intrinsics.checkNotNullParameter(node, "node");
        int iM3598constructorimpl = NodeKind.m3598constructorimpl(1);
        if (node instanceof LayoutModifierNode) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(2);
        }
        if (node instanceof DrawModifierNode) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(4);
        }
        if (node instanceof SemanticsModifierNode) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(8);
        }
        if (node instanceof PointerInputModifierNode) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(16);
        }
        if (node instanceof ModifierLocalNode) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(32);
        }
        if (node instanceof ParentDataModifierNode) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(64);
        }
        if (node instanceof LayoutAwareModifierNode) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(128);
        }
        if (node instanceof GlobalPositionAwareModifierNode) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(256);
        }
        if (node instanceof IntermediateLayoutModifierNode) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(512);
        }
        if (node instanceof FocusTargetModifierNode) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(1024);
        }
        if (node instanceof FocusPropertiesModifierNode) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(2048);
        }
        if (node instanceof FocusEventModifierNode) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(4096);
        }
        if (node instanceof KeyInputModifierNode) {
            iM3598constructorimpl |= NodeKind.m3598constructorimpl(8192);
        }
        return node instanceof RotaryInputModifierNode ? iM3598constructorimpl | NodeKind.m3598constructorimpl(16384) : iM3598constructorimpl;
    }
}
