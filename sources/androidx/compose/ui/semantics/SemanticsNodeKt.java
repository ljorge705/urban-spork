package androidx.compose.ui.semantics;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.SemanticsModifierNode;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SemanticsNode.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0002\b\u0002\u001a\f\u0010\u000f\u001a\u00020\u0010*\u00020\fH\u0002\u001a\"\u0010\u0011\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00140\u0013H\u0002\u001a\"\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u0016*\u00020\u00022\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018H\u0002\u001a\f\u0010\u0019\u001a\u00020\u0010*\u00020\fH\u0002\" \u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\" \u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006\"\u001d\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\f8BX\u0082\u0004ø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"outerMergingSemantics", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/node/LayoutNode;", "getOuterMergingSemantics$annotations", "(Landroidx/compose/ui/node/LayoutNode;)V", "getOuterMergingSemantics", "(Landroidx/compose/ui/node/LayoutNode;)Landroidx/compose/ui/node/SemanticsModifierNode;", "outerSemantics", "getOuterSemantics$annotations", "getOuterSemantics", ViewProps.ROLE, "Landroidx/compose/ui/semantics/Role;", "Landroidx/compose/ui/semantics/SemanticsNode;", "getRole", "(Landroidx/compose/ui/semantics/SemanticsNode;)Landroidx/compose/ui/semantics/Role;", "contentDescriptionFakeNodeId", "", "findClosestParentNode", "selector", "Lkotlin/Function1;", "", "findOneLayerOfSemanticsWrappers", "", "list", "", "roleFakeNodeId", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SemanticsNodeKt {
    public static /* synthetic */ void getOuterMergingSemantics$annotations(LayoutNode layoutNode) {
    }

    public static /* synthetic */ void getOuterSemantics$annotations(LayoutNode layoutNode) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v6, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    public static final SemanticsModifierNode getOuterSemantics(LayoutNode layoutNode) {
        ?? head;
        Intrinsics.checkNotNullParameter(layoutNode, "<this>");
        NodeChain nodes = layoutNode.getNodes();
        int iM3598constructorimpl = NodeKind.m3598constructorimpl(8);
        if ((nodes.getAggregateChildKindSet() & iM3598constructorimpl) != 0) {
            head = nodes.getHead();
            while (head != 0) {
                if ((head.getKindSet() & iM3598constructorimpl) != 0 && (head instanceof SemanticsModifierNode)) {
                    break;
                }
                if ((head.getAggregateChildKindSet() & iM3598constructorimpl) == 0) {
                    break;
                }
                head = head.getChild();
            }
            head = 0;
        } else {
            head = 0;
        }
        return (SemanticsModifierNode) head;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v6, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    public static final SemanticsModifierNode getOuterMergingSemantics(LayoutNode layoutNode) {
        SemanticsModifierNode head;
        Intrinsics.checkNotNullParameter(layoutNode, "<this>");
        NodeChain nodes = layoutNode.getNodes();
        int iM3598constructorimpl = NodeKind.m3598constructorimpl(8);
        if ((nodes.getAggregateChildKindSet() & iM3598constructorimpl) != 0) {
            head = nodes.getHead();
            while (head != 0) {
                if ((head.getKindSet() & iM3598constructorimpl) == 0 || !(head instanceof SemanticsModifierNode) || !head.getSemanticsConfiguration().getIsMergingSemanticsOfDescendants()) {
                    if ((head.getAggregateChildKindSet() & iM3598constructorimpl) == 0) {
                        break;
                    }
                    head = head.getChild();
                } else {
                    break;
                }
            }
            head = 0;
        } else {
            head = 0;
        }
        return (SemanticsModifierNode) head;
    }

    static /* synthetic */ List findOneLayerOfSemanticsWrappers$default(LayoutNode layoutNode, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = new ArrayList();
        }
        return findOneLayerOfSemanticsWrappers(layoutNode, list);
    }

    private static final List<SemanticsModifierNode> findOneLayerOfSemanticsWrappers(LayoutNode layoutNode, List<SemanticsModifierNode> list) {
        MutableVector<LayoutNode> zSortedChildren = layoutNode.getZSortedChildren();
        int size = zSortedChildren.getSize();
        if (size > 0) {
            LayoutNode[] content = zSortedChildren.getContent();
            int i = 0;
            do {
                LayoutNode layoutNode2 = content[i];
                SemanticsModifierNode outerSemantics = getOuterSemantics(layoutNode2);
                if (outerSemantics != null) {
                    list.add(outerSemantics);
                } else {
                    findOneLayerOfSemanticsWrappers(layoutNode2, list);
                }
                i++;
            } while (i < size);
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LayoutNode findClosestParentNode(LayoutNode layoutNode, Function1<? super LayoutNode, Boolean> function1) {
        for (LayoutNode parent$ui_release = layoutNode.getParent$ui_release(); parent$ui_release != null; parent$ui_release = parent$ui_release.getParent$ui_release()) {
            if (function1.invoke(parent$ui_release).booleanValue()) {
                return parent$ui_release;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Role getRole(SemanticsNode semanticsNode) {
        return (Role) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getRole());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int contentDescriptionFakeNodeId(SemanticsNode semanticsNode) {
        return semanticsNode.getId() + 2000000000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int roleFakeNodeId(SemanticsNode semanticsNode) {
        return semanticsNode.getId() + 1000000000;
    }
}
