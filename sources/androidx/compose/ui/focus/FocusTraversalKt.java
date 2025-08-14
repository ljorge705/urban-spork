package androidx.compose.ui.focus;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.unit.LayoutDirection;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FocusTraversal.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a)\u0010\n\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u000e\u0010\u0012\u001a\u0004\u0018\u00010\u0001*\u00020\u0001H\u0000\u001a\u000e\u0010\u0013\u001a\u0004\u0018\u00010\u0001*\u00020\u0001H\u0002\u001a\f\u0010\u0014\u001a\u00020\u0015*\u00020\u0001H\u0001\u001a=\u0010\u0016\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00070\u0018H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001a\" \u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00018@X\u0081\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001e\u0010\u0006\u001a\u00020\u0007*\u00020\u00018@X\u0081\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0003\u001a\u0004\b\u0006\u0010\t\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"activeChild", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "getActiveChild$annotations", "(Landroidx/compose/ui/focus/FocusTargetModifierNode;)V", "getActiveChild", "(Landroidx/compose/ui/focus/FocusTargetModifierNode;)Landroidx/compose/ui/focus/FocusTargetModifierNode;", "isEligibleForFocusSearch", "", "isEligibleForFocusSearch$annotations", "(Landroidx/compose/ui/focus/FocusTargetModifierNode;)Z", "customFocusSearch", "Landroidx/compose/ui/focus/FocusRequester;", "focusDirection", "Landroidx/compose/ui/focus/FocusDirection;", ViewProps.LAYOUT_DIRECTION, "Landroidx/compose/ui/unit/LayoutDirection;", "customFocusSearch--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetModifierNode;ILandroidx/compose/ui/unit/LayoutDirection;)Landroidx/compose/ui/focus/FocusRequester;", "findActiveFocusNode", "findNonDeactivatedParent", "focusRect", "Landroidx/compose/ui/geometry/Rect;", "focusSearch", "onFound", "Lkotlin/Function1;", "focusSearch-sMXa3k8", "(Landroidx/compose/ui/focus/FocusTargetModifierNode;ILandroidx/compose/ui/unit/LayoutDirection;Lkotlin/jvm/functions/Function1;)Z", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FocusTraversalKt {

    /* compiled from: FocusTraversal.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FocusStateImpl.values().length];
            try {
                iArr2[FocusStateImpl.Active.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[FocusStateImpl.ActiveParent.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[FocusStateImpl.Captured.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[FocusStateImpl.Inactive.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static /* synthetic */ void getActiveChild$annotations(FocusTargetModifierNode focusTargetModifierNode) {
    }

    public static /* synthetic */ void isEligibleForFocusSearch$annotations(FocusTargetModifierNode focusTargetModifierNode) {
    }

    /* renamed from: customFocusSearch--OM-vw8, reason: not valid java name */
    public static final FocusRequester m1591customFocusSearchOMvw8(FocusTargetModifierNode customFocusSearch, int i, LayoutDirection layoutDirection) {
        FocusRequester end;
        Intrinsics.checkNotNullParameter(customFocusSearch, "$this$customFocusSearch");
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        FocusProperties focusPropertiesFetchFocusProperties$ui_release = customFocusSearch.fetchFocusProperties$ui_release();
        if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1578getNextdhqQ8s())) {
            return focusPropertiesFetchFocusProperties$ui_release.getNext();
        }
        if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1580getPreviousdhqQ8s())) {
            return focusPropertiesFetchFocusProperties$ui_release.getPrevious();
        }
        if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1582getUpdhqQ8s())) {
            return focusPropertiesFetchFocusProperties$ui_release.getUp();
        }
        if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1573getDowndhqQ8s())) {
            return focusPropertiesFetchFocusProperties$ui_release.getDown();
        }
        if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1577getLeftdhqQ8s())) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()];
            if (i2 == 1) {
                end = focusPropertiesFetchFocusProperties$ui_release.getStart();
            } else {
                if (i2 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                end = focusPropertiesFetchFocusProperties$ui_release.getEnd();
            }
            if (Intrinsics.areEqual(end, FocusRequester.INSTANCE.getDefault())) {
                end = null;
            }
            if (end == null) {
                return focusPropertiesFetchFocusProperties$ui_release.getLeft();
            }
        } else if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1581getRightdhqQ8s())) {
            int i3 = WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()];
            if (i3 == 1) {
                end = focusPropertiesFetchFocusProperties$ui_release.getEnd();
            } else {
                if (i3 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                end = focusPropertiesFetchFocusProperties$ui_release.getStart();
            }
            if (Intrinsics.areEqual(end, FocusRequester.INSTANCE.getDefault())) {
                end = null;
            }
            if (end == null) {
                return focusPropertiesFetchFocusProperties$ui_release.getRight();
            }
        } else {
            if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1574getEnterdhqQ8s())) {
                return focusPropertiesFetchFocusProperties$ui_release.getEnter().invoke(FocusDirection.m1562boximpl(i));
            }
            if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1575getExitdhqQ8s())) {
                return focusPropertiesFetchFocusProperties$ui_release.getExit().invoke(FocusDirection.m1562boximpl(i));
            }
            throw new IllegalStateException("invalid FocusDirection".toString());
        }
        return end;
    }

    /* renamed from: focusSearch-sMXa3k8, reason: not valid java name */
    public static final boolean m1592focusSearchsMXa3k8(FocusTargetModifierNode focusSearch, int i, LayoutDirection layoutDirection, Function1<? super FocusTargetModifierNode, Boolean> onFound) {
        int iM1581getRightdhqQ8s;
        Boolean boolM1604twoDimensionalFocusSearchOMvw8;
        Intrinsics.checkNotNullParameter(focusSearch, "$this$focusSearch");
        Intrinsics.checkNotNullParameter(layoutDirection, "layoutDirection");
        Intrinsics.checkNotNullParameter(onFound, "onFound");
        if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1578getNextdhqQ8s()) || FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1580getPreviousdhqQ8s())) {
            return OneDimensionalFocusSearchKt.m1595oneDimensionalFocusSearchOMvw8(focusSearch, i, onFound);
        }
        if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1577getLeftdhqQ8s()) || FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1581getRightdhqQ8s()) || FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1582getUpdhqQ8s()) || FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1573getDowndhqQ8s())) {
            Boolean boolM1604twoDimensionalFocusSearchOMvw82 = TwoDimensionalFocusSearchKt.m1604twoDimensionalFocusSearchOMvw8(focusSearch, i, onFound);
            if (boolM1604twoDimensionalFocusSearchOMvw82 != null) {
                return boolM1604twoDimensionalFocusSearchOMvw82.booleanValue();
            }
        } else if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1574getEnterdhqQ8s())) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()];
            if (i2 == 1) {
                iM1581getRightdhqQ8s = FocusDirection.INSTANCE.m1581getRightdhqQ8s();
            } else {
                if (i2 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                iM1581getRightdhqQ8s = FocusDirection.INSTANCE.m1577getLeftdhqQ8s();
            }
            FocusTargetModifierNode focusTargetModifierNodeFindActiveFocusNode = findActiveFocusNode(focusSearch);
            if (focusTargetModifierNodeFindActiveFocusNode != null && (boolM1604twoDimensionalFocusSearchOMvw8 = TwoDimensionalFocusSearchKt.m1604twoDimensionalFocusSearchOMvw8(focusTargetModifierNodeFindActiveFocusNode, iM1581getRightdhqQ8s, onFound)) != null) {
                return boolM1604twoDimensionalFocusSearchOMvw8.booleanValue();
            }
        } else if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1575getExitdhqQ8s())) {
            FocusTargetModifierNode focusTargetModifierNodeFindActiveFocusNode2 = findActiveFocusNode(focusSearch);
            FocusTargetModifierNode focusTargetModifierNodeFindNonDeactivatedParent = focusTargetModifierNodeFindActiveFocusNode2 != null ? findNonDeactivatedParent(focusTargetModifierNodeFindActiveFocusNode2) : null;
            if (focusTargetModifierNodeFindNonDeactivatedParent != null && !Intrinsics.areEqual(focusTargetModifierNodeFindNonDeactivatedParent, focusSearch)) {
                return onFound.invoke(focusTargetModifierNodeFindNonDeactivatedParent).booleanValue();
            }
        } else {
            throw new IllegalStateException(("Focus search invoked with invalid FocusDirection " + ((Object) FocusDirection.m1567toStringimpl(i))).toString());
        }
        return false;
    }

    public static final Rect focusRect(FocusTargetModifierNode focusTargetModifierNode) {
        Intrinsics.checkNotNullParameter(focusTargetModifierNode, "<this>");
        NodeCoordinator coordinator$ui_release = focusTargetModifierNode.getCoordinator();
        if (coordinator$ui_release != null) {
            NodeCoordinator nodeCoordinator = coordinator$ui_release;
            Rect rectLocalBoundingBoxOf = LayoutCoordinatesKt.findRootCoordinates(nodeCoordinator).localBoundingBoxOf(nodeCoordinator, false);
            if (rectLocalBoundingBoxOf != null) {
                return rectLocalBoundingBoxOf;
            }
        }
        return Rect.INSTANCE.getZero();
    }

    public static final boolean isEligibleForFocusSearch(FocusTargetModifierNode focusTargetModifierNode) {
        LayoutNode layoutNode;
        NodeCoordinator coordinator$ui_release;
        LayoutNode layoutNode2;
        Intrinsics.checkNotNullParameter(focusTargetModifierNode, "<this>");
        NodeCoordinator coordinator$ui_release2 = focusTargetModifierNode.getCoordinator();
        return (coordinator$ui_release2 == null || (layoutNode = coordinator$ui_release2.getLayoutNode()) == null || !layoutNode.getIsPlaced() || (coordinator$ui_release = focusTargetModifierNode.getCoordinator()) == null || (layoutNode2 = coordinator$ui_release.getLayoutNode()) == null || !layoutNode2.isAttached()) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0042, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.focus.FocusTargetModifierNode getActiveChild(androidx.compose.ui.focus.FocusTargetModifierNode r6) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            androidx.compose.ui.Modifier$Node r0 = r6.getNode()
            boolean r0 = r0.getIsAttached()
            r1 = 0
            if (r0 != 0) goto L11
            return r1
        L11:
            androidx.compose.ui.node.DelegatableNode r6 = (androidx.compose.ui.node.DelegatableNode) r6
            r0 = 1024(0x400, float:1.435E-42)
            int r0 = androidx.compose.ui.node.NodeKind.m3598constructorimpl(r0)
            androidx.compose.ui.Modifier$Node r2 = r6.getNode()
            boolean r2 = r2.getIsAttached()
            if (r2 == 0) goto L8a
            androidx.compose.runtime.collection.MutableVector r2 = new androidx.compose.runtime.collection.MutableVector
            r3 = 16
            androidx.compose.ui.Modifier$Node[] r3 = new androidx.compose.ui.Modifier.Node[r3]
            r4 = 0
            r2.<init>(r3, r4)
            androidx.compose.ui.Modifier$Node r3 = r6.getNode()
            androidx.compose.ui.Modifier$Node r3 = r3.getChild()
            if (r3 != 0) goto L3f
            androidx.compose.ui.Modifier$Node r6 = r6.getNode()
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r2, r6)
            goto L42
        L3f:
            r2.add(r3)
        L42:
            boolean r6 = r2.isNotEmpty()
            if (r6 == 0) goto L89
            int r6 = r2.getSize()
            r3 = 1
            int r6 = r6 - r3
            java.lang.Object r6 = r2.removeAt(r6)
            androidx.compose.ui.Modifier$Node r6 = (androidx.compose.ui.Modifier.Node) r6
            int r4 = r6.getAggregateChildKindSet()
            r4 = r4 & r0
            if (r4 != 0) goto L5f
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r2, r6)
            goto L42
        L5f:
            if (r6 == 0) goto L42
            int r4 = r6.getKindSet()
            r4 = r4 & r0
            if (r4 == 0) goto L84
            boolean r4 = r6 instanceof androidx.compose.ui.focus.FocusTargetModifierNode
            if (r4 == 0) goto L42
            androidx.compose.ui.focus.FocusTargetModifierNode r6 = (androidx.compose.ui.focus.FocusTargetModifierNode) r6
            androidx.compose.ui.focus.FocusStateImpl r4 = r6.getFocusStateImpl()
            int[] r5 = androidx.compose.ui.focus.FocusTraversalKt.WhenMappings.$EnumSwitchMapping$1
            int r4 = r4.ordinal()
            r4 = r5[r4]
            if (r4 == r3) goto L83
            r3 = 2
            if (r4 == r3) goto L83
            r3 = 3
            if (r4 == r3) goto L83
            goto L42
        L83:
            return r6
        L84:
            androidx.compose.ui.Modifier$Node r6 = r6.getChild()
            goto L5f
        L89:
            return r1
        L8a:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "Check failed."
            java.lang.String r0 = r0.toString()
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusTraversalKt.getActiveChild(androidx.compose.ui.focus.FocusTargetModifierNode):androidx.compose.ui.focus.FocusTargetModifierNode");
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0056, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.focus.FocusTargetModifierNode findActiveFocusNode(androidx.compose.ui.focus.FocusTargetModifierNode r6) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            androidx.compose.ui.focus.FocusStateImpl r0 = r6.getFocusStateImpl()
            int[] r1 = androidx.compose.ui.focus.FocusTraversalKt.WhenMappings.$EnumSwitchMapping$1
            int r0 = r0.ordinal()
            r0 = r1[r0]
            r1 = 1
            if (r0 == r1) goto L9a
            r2 = 2
            r3 = 0
            if (r0 == r2) goto L25
            r1 = 3
            if (r0 == r1) goto L9a
            r6 = 4
            if (r0 != r6) goto L1f
            return r3
        L1f:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        L25:
            androidx.compose.ui.node.DelegatableNode r6 = (androidx.compose.ui.node.DelegatableNode) r6
            r0 = 1024(0x400, float:1.435E-42)
            int r0 = androidx.compose.ui.node.NodeKind.m3598constructorimpl(r0)
            androidx.compose.ui.Modifier$Node r2 = r6.getNode()
            boolean r2 = r2.getIsAttached()
            if (r2 == 0) goto L8e
            androidx.compose.runtime.collection.MutableVector r2 = new androidx.compose.runtime.collection.MutableVector
            r4 = 16
            androidx.compose.ui.Modifier$Node[] r4 = new androidx.compose.ui.Modifier.Node[r4]
            r5 = 0
            r2.<init>(r4, r5)
            androidx.compose.ui.Modifier$Node r4 = r6.getNode()
            androidx.compose.ui.Modifier$Node r4 = r4.getChild()
            if (r4 != 0) goto L53
            androidx.compose.ui.Modifier$Node r6 = r6.getNode()
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r2, r6)
            goto L56
        L53:
            r2.add(r4)
        L56:
            boolean r6 = r2.isNotEmpty()
            if (r6 == 0) goto L8d
            int r6 = r2.getSize()
            int r6 = r6 - r1
            java.lang.Object r6 = r2.removeAt(r6)
            androidx.compose.ui.Modifier$Node r6 = (androidx.compose.ui.Modifier.Node) r6
            int r4 = r6.getAggregateChildKindSet()
            r4 = r4 & r0
            if (r4 != 0) goto L72
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r2, r6)
            goto L56
        L72:
            if (r6 == 0) goto L56
            int r4 = r6.getKindSet()
            r4 = r4 & r0
            if (r4 == 0) goto L88
            boolean r4 = r6 instanceof androidx.compose.ui.focus.FocusTargetModifierNode
            if (r4 == 0) goto L56
            androidx.compose.ui.focus.FocusTargetModifierNode r6 = (androidx.compose.ui.focus.FocusTargetModifierNode) r6
            androidx.compose.ui.focus.FocusTargetModifierNode r6 = findActiveFocusNode(r6)
            if (r6 == 0) goto L56
            return r6
        L88:
            androidx.compose.ui.Modifier$Node r6 = r6.getChild()
            goto L72
        L8d:
            return r3
        L8e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "Check failed."
            java.lang.String r0 = r0.toString()
            r6.<init>(r0)
            throw r6
        L9a:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusTraversalKt.findActiveFocusNode(androidx.compose.ui.focus.FocusTargetModifierNode):androidx.compose.ui.focus.FocusTargetModifierNode");
    }

    private static final FocusTargetModifierNode findNonDeactivatedParent(FocusTargetModifierNode focusTargetModifierNode) {
        NodeChain nodes;
        FocusTargetModifierNode focusTargetModifierNode2 = focusTargetModifierNode;
        int iM3598constructorimpl = NodeKind.m3598constructorimpl(1024);
        if (!focusTargetModifierNode2.getNode().getIsAttached()) {
            throw new IllegalStateException("Check failed.".toString());
        }
        Modifier.Node parent = focusTargetModifierNode2.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetModifierNode2);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM3598constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM3598constructorimpl) != 0 && (parent instanceof FocusTargetModifierNode)) {
                        FocusTargetModifierNode focusTargetModifierNode3 = (FocusTargetModifierNode) parent;
                        if (focusTargetModifierNode3.fetchFocusProperties$ui_release().getCanFocus()) {
                            return focusTargetModifierNode3;
                        }
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return null;
    }
}
