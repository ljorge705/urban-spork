package androidx.compose.ui.focus;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FocusEventModifierNode.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0001¨\u0006\u0006"}, d2 = {"getFocusState", "Landroidx/compose/ui/focus/FocusState;", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "refreshFocusEventNodes", "", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FocusEventModifierNodeKt {

    /* compiled from: FocusEventModifierNode.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            try {
                iArr[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FocusStateImpl.ActiveParent.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FocusStateImpl.Captured.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0036, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.focus.FocusState getFocusState(androidx.compose.ui.focus.FocusEventModifierNode r5) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            androidx.compose.ui.node.DelegatableNode r5 = (androidx.compose.ui.node.DelegatableNode) r5
            r0 = 1024(0x400, float:1.435E-42)
            int r0 = androidx.compose.ui.node.NodeKind.m3598constructorimpl(r0)
            androidx.compose.ui.Modifier$Node r1 = r5.getNode()
            boolean r1 = r1.getIsAttached()
            if (r1 == 0) goto L84
            androidx.compose.runtime.collection.MutableVector r1 = new androidx.compose.runtime.collection.MutableVector
            r2 = 16
            androidx.compose.ui.Modifier$Node[] r2 = new androidx.compose.ui.Modifier.Node[r2]
            r3 = 0
            r1.<init>(r2, r3)
            androidx.compose.ui.Modifier$Node r2 = r5.getNode()
            androidx.compose.ui.Modifier$Node r2 = r2.getChild()
            if (r2 != 0) goto L33
            androidx.compose.ui.Modifier$Node r5 = r5.getNode()
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r1, r5)
            goto L36
        L33:
            r1.add(r2)
        L36:
            boolean r5 = r1.isNotEmpty()
            if (r5 == 0) goto L7f
            int r5 = r1.getSize()
            r2 = 1
            int r5 = r5 - r2
            java.lang.Object r5 = r1.removeAt(r5)
            androidx.compose.ui.Modifier$Node r5 = (androidx.compose.ui.Modifier.Node) r5
            int r3 = r5.getAggregateChildKindSet()
            r3 = r3 & r0
            if (r3 != 0) goto L53
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r1, r5)
            goto L36
        L53:
            if (r5 == 0) goto L36
            int r3 = r5.getKindSet()
            r3 = r3 & r0
            if (r3 == 0) goto L7a
            boolean r3 = r5 instanceof androidx.compose.ui.focus.FocusTargetModifierNode
            if (r3 == 0) goto L36
            androidx.compose.ui.focus.FocusTargetModifierNode r5 = (androidx.compose.ui.focus.FocusTargetModifierNode) r5
            androidx.compose.ui.focus.FocusStateImpl r5 = r5.getFocusStateImpl()
            int[] r3 = androidx.compose.ui.focus.FocusEventModifierNodeKt.WhenMappings.$EnumSwitchMapping$0
            int r4 = r5.ordinal()
            r3 = r3[r4]
            if (r3 == r2) goto L77
            r2 = 2
            if (r3 == r2) goto L77
            r2 = 3
            if (r3 == r2) goto L77
            goto L36
        L77:
            androidx.compose.ui.focus.FocusState r5 = (androidx.compose.ui.focus.FocusState) r5
            return r5
        L7a:
            androidx.compose.ui.Modifier$Node r5 = r5.getChild()
            goto L53
        L7f:
            androidx.compose.ui.focus.FocusStateImpl r5 = androidx.compose.ui.focus.FocusStateImpl.Inactive
            androidx.compose.ui.focus.FocusState r5 = (androidx.compose.ui.focus.FocusState) r5
            return r5
        L84:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "Check failed."
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusEventModifierNodeKt.getFocusState(androidx.compose.ui.focus.FocusEventModifierNode):androidx.compose.ui.focus.FocusState");
    }

    public static final void refreshFocusEventNodes(FocusTargetModifierNode focusTargetModifierNode) {
        NodeChain nodes;
        Intrinsics.checkNotNullParameter(focusTargetModifierNode, "<this>");
        FocusTargetModifierNode focusTargetModifierNode2 = focusTargetModifierNode;
        int iM3598constructorimpl = NodeKind.m3598constructorimpl(4096) | NodeKind.m3598constructorimpl(1024);
        if (!focusTargetModifierNode2.getNode().getIsAttached()) {
            throw new IllegalStateException("Check failed.".toString());
        }
        Modifier.Node parent = focusTargetModifierNode2.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetModifierNode2);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM3598constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM3598constructorimpl) != 0) {
                        if ((NodeKind.m3598constructorimpl(1024) & parent.getKindSet()) != 0) {
                            return;
                        }
                        if (!(parent instanceof FocusEventModifierNode)) {
                            throw new IllegalStateException("Check failed.".toString());
                        }
                        FocusEventModifierNode focusEventModifierNode = (FocusEventModifierNode) parent;
                        focusEventModifierNode.onFocusEvent(getFocusState(focusEventModifierNode));
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
    }
}
