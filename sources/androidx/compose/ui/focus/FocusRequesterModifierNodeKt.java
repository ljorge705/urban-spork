package androidx.compose.ui.focus;

import kotlin.Metadata;

/* compiled from: FocusRequesterModifierNode.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\f\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\u0007¨\u0006\u0005"}, d2 = {"captureFocus", "", "Landroidx/compose/ui/focus/FocusRequesterModifierNode;", "freeFocus", "requestFocus", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FocusRequesterModifierNodeKt {
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0036, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean requestFocus(androidx.compose.ui.focus.FocusRequesterModifierNode r5) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            androidx.compose.ui.node.DelegatableNode r5 = (androidx.compose.ui.node.DelegatableNode) r5
            r0 = 1024(0x400, float:1.435E-42)
            int r0 = androidx.compose.ui.node.NodeKind.m3598constructorimpl(r0)
            androidx.compose.ui.Modifier$Node r1 = r5.getNode()
            boolean r1 = r1.getIsAttached()
            if (r1 == 0) goto L6f
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
            if (r5 == 0) goto L6e
            int r5 = r1.getSize()
            r2 = 1
            int r5 = r5 - r2
            java.lang.Object r5 = r1.removeAt(r5)
            androidx.compose.ui.Modifier$Node r5 = (androidx.compose.ui.Modifier.Node) r5
            int r4 = r5.getAggregateChildKindSet()
            r4 = r4 & r0
            if (r4 != 0) goto L53
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r1, r5)
            goto L36
        L53:
            if (r5 == 0) goto L36
            int r4 = r5.getKindSet()
            r4 = r4 & r0
            if (r4 == 0) goto L69
            boolean r4 = r5 instanceof androidx.compose.ui.focus.FocusTargetModifierNode
            if (r4 == 0) goto L36
            androidx.compose.ui.focus.FocusTargetModifierNode r5 = (androidx.compose.ui.focus.FocusTargetModifierNode) r5
            boolean r5 = androidx.compose.ui.focus.FocusTransactionsKt.requestFocus(r5)
            if (r5 == 0) goto L36
            return r2
        L69:
            androidx.compose.ui.Modifier$Node r5 = r5.getChild()
            goto L53
        L6e:
            return r3
        L6f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "Check failed."
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusRequesterModifierNodeKt.requestFocus(androidx.compose.ui.focus.FocusRequesterModifierNode):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0036, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean captureFocus(androidx.compose.ui.focus.FocusRequesterModifierNode r5) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            androidx.compose.ui.node.DelegatableNode r5 = (androidx.compose.ui.node.DelegatableNode) r5
            r0 = 1024(0x400, float:1.435E-42)
            int r0 = androidx.compose.ui.node.NodeKind.m3598constructorimpl(r0)
            androidx.compose.ui.Modifier$Node r1 = r5.getNode()
            boolean r1 = r1.getIsAttached()
            if (r1 == 0) goto L6f
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
            if (r5 == 0) goto L6e
            int r5 = r1.getSize()
            r2 = 1
            int r5 = r5 - r2
            java.lang.Object r5 = r1.removeAt(r5)
            androidx.compose.ui.Modifier$Node r5 = (androidx.compose.ui.Modifier.Node) r5
            int r4 = r5.getAggregateChildKindSet()
            r4 = r4 & r0
            if (r4 != 0) goto L53
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r1, r5)
            goto L36
        L53:
            if (r5 == 0) goto L36
            int r4 = r5.getKindSet()
            r4 = r4 & r0
            if (r4 == 0) goto L69
            boolean r4 = r5 instanceof androidx.compose.ui.focus.FocusTargetModifierNode
            if (r4 == 0) goto L36
            androidx.compose.ui.focus.FocusTargetModifierNode r5 = (androidx.compose.ui.focus.FocusTargetModifierNode) r5
            boolean r5 = androidx.compose.ui.focus.FocusTransactionsKt.captureFocus(r5)
            if (r5 == 0) goto L36
            return r2
        L69:
            androidx.compose.ui.Modifier$Node r5 = r5.getChild()
            goto L53
        L6e:
            return r3
        L6f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "Check failed."
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusRequesterModifierNodeKt.captureFocus(androidx.compose.ui.focus.FocusRequesterModifierNode):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0036, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean freeFocus(androidx.compose.ui.focus.FocusRequesterModifierNode r5) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            androidx.compose.ui.node.DelegatableNode r5 = (androidx.compose.ui.node.DelegatableNode) r5
            r0 = 1024(0x400, float:1.435E-42)
            int r0 = androidx.compose.ui.node.NodeKind.m3598constructorimpl(r0)
            androidx.compose.ui.Modifier$Node r1 = r5.getNode()
            boolean r1 = r1.getIsAttached()
            if (r1 == 0) goto L6f
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
            if (r5 == 0) goto L6e
            int r5 = r1.getSize()
            r2 = 1
            int r5 = r5 - r2
            java.lang.Object r5 = r1.removeAt(r5)
            androidx.compose.ui.Modifier$Node r5 = (androidx.compose.ui.Modifier.Node) r5
            int r4 = r5.getAggregateChildKindSet()
            r4 = r4 & r0
            if (r4 != 0) goto L53
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r1, r5)
            goto L36
        L53:
            if (r5 == 0) goto L36
            int r4 = r5.getKindSet()
            r4 = r4 & r0
            if (r4 == 0) goto L69
            boolean r4 = r5 instanceof androidx.compose.ui.focus.FocusTargetModifierNode
            if (r4 == 0) goto L36
            androidx.compose.ui.focus.FocusTargetModifierNode r5 = (androidx.compose.ui.focus.FocusTargetModifierNode) r5
            boolean r5 = androidx.compose.ui.focus.FocusTransactionsKt.freeFocus(r5)
            if (r5 == 0) goto L36
            return r2
        L69:
            androidx.compose.ui.Modifier$Node r5 = r5.getChild()
            goto L53
        L6e:
            return r3
        L6f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "Check failed."
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusRequesterModifierNodeKt.freeFocus(androidx.compose.ui.focus.FocusRequesterModifierNode):boolean");
    }
}
