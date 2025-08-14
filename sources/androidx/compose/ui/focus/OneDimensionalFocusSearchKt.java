package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.NodeKind;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.leanplum.Constants;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* compiled from: OneDimensionalFocusSearch.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a \u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00040\u0007H\u0003\u001aE\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u000b2\u0006\u0010\f\u001a\u0002H\n2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\t0\u0007H\u0082\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0002\u0010\u000e\u001aE\u0010\u000f\u001a\u00020\t\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u000b2\u0006\u0010\f\u001a\u0002H\n2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020\t0\u0007H\u0082\b\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0002¢\u0006\u0002\u0010\u000e\u001a \u0010\u0010\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00040\u0007H\u0003\u001a=\u0010\u0011\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00040\u0007H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0015\u0010\u0016\u001a\f\u0010\u0017\u001a\u00020\u0004*\u00020\u0005H\u0002\u001a5\u0010\u0018\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00040\u0007H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001a\u001a \u0010\u001b\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00040\u0007H\u0003\u001a \u0010\u001c\u001a\u00020\u0004*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00040\u0007H\u0003\u001a=\u0010\u001d\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00040\u0007H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001e\u0010\u0016\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"InvalidFocusDirection", "", "NoActiveChild", "backwardFocusSearch", "", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "onFound", "Lkotlin/Function1;", "forEachItemAfter", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/runtime/collection/MutableVector;", Constants.IAP_ITEM_PARAM, com.clevertap.android.sdk.Constants.KEY_ACTION, "(Landroidx/compose/runtime/collection/MutableVector;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "forEachItemBefore", "forwardFocusSearch", "generateAndSearchChildren", "focusedItem", "direction", "Landroidx/compose/ui/focus/FocusDirection;", "generateAndSearchChildren-4C6V_qg", "(Landroidx/compose/ui/focus/FocusTargetModifierNode;Landroidx/compose/ui/focus/FocusTargetModifierNode;ILkotlin/jvm/functions/Function1;)Z", "isRoot", "oneDimensionalFocusSearch", "oneDimensionalFocusSearch--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetModifierNode;ILkotlin/jvm/functions/Function1;)Z", "pickChildForBackwardSearch", "pickChildForForwardSearch", "searchChildren", "searchChildren-4C6V_qg", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class OneDimensionalFocusSearchKt {
    private static final String InvalidFocusDirection = "This function should only be used for 1-D focus search";
    private static final String NoActiveChild = "ActiveParent must have a focusedChild";

    /* compiled from: OneDimensionalFocusSearch.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FocusStateImpl.values().length];
            try {
                iArr[FocusStateImpl.ActiveParent.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FocusStateImpl.Active.ordinal()] = 2;
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

    /* renamed from: oneDimensionalFocusSearch--OM-vw8, reason: not valid java name */
    public static final boolean m1595oneDimensionalFocusSearchOMvw8(FocusTargetModifierNode oneDimensionalFocusSearch, int i, Function1<? super FocusTargetModifierNode, Boolean> onFound) {
        Intrinsics.checkNotNullParameter(oneDimensionalFocusSearch, "$this$oneDimensionalFocusSearch");
        Intrinsics.checkNotNullParameter(onFound, "onFound");
        if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1578getNextdhqQ8s())) {
            return forwardFocusSearch(oneDimensionalFocusSearch, onFound);
        }
        if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1580getPreviousdhqQ8s())) {
            return backwardFocusSearch(oneDimensionalFocusSearch, onFound);
        }
        throw new IllegalStateException(InvalidFocusDirection.toString());
    }

    private static final boolean forwardFocusSearch(FocusTargetModifierNode focusTargetModifierNode, Function1<? super FocusTargetModifierNode, Boolean> function1) {
        int i = WhenMappings.$EnumSwitchMapping$0[focusTargetModifierNode.getFocusStateImpl().ordinal()];
        if (i == 1) {
            FocusTargetModifierNode activeChild = FocusTraversalKt.getActiveChild(focusTargetModifierNode);
            if (activeChild != null) {
                return forwardFocusSearch(activeChild, function1) || m1594generateAndSearchChildren4C6V_qg(focusTargetModifierNode, activeChild, FocusDirection.INSTANCE.m1578getNextdhqQ8s(), function1);
            }
            throw new IllegalStateException(NoActiveChild.toString());
        }
        if (i == 2 || i == 3) {
            return pickChildForForwardSearch(focusTargetModifierNode, function1);
        }
        if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
        if (focusTargetModifierNode.fetchFocusProperties$ui_release().getCanFocus()) {
            return function1.invoke(focusTargetModifierNode).booleanValue();
        }
        return pickChildForForwardSearch(focusTargetModifierNode, function1);
    }

    private static final boolean backwardFocusSearch(FocusTargetModifierNode focusTargetModifierNode, Function1<? super FocusTargetModifierNode, Boolean> function1) {
        int i = WhenMappings.$EnumSwitchMapping$0[focusTargetModifierNode.getFocusStateImpl().ordinal()];
        if (i == 1) {
            FocusTargetModifierNode activeChild = FocusTraversalKt.getActiveChild(focusTargetModifierNode);
            if (activeChild == null) {
                throw new IllegalStateException(NoActiveChild.toString());
            }
            int i2 = WhenMappings.$EnumSwitchMapping$0[activeChild.getFocusStateImpl().ordinal()];
            if (i2 != 1) {
                if (i2 == 2 || i2 == 3) {
                    return m1594generateAndSearchChildren4C6V_qg(focusTargetModifierNode, activeChild, FocusDirection.INSTANCE.m1580getPreviousdhqQ8s(), function1);
                }
                if (i2 == 4) {
                    throw new IllegalStateException(NoActiveChild.toString());
                }
                throw new NoWhenBranchMatchedException();
            }
            if (!backwardFocusSearch(activeChild, function1) && !m1594generateAndSearchChildren4C6V_qg(focusTargetModifierNode, activeChild, FocusDirection.INSTANCE.m1580getPreviousdhqQ8s(), function1) && (!focusTargetModifierNode.fetchFocusProperties$ui_release().getCanFocus() || !function1.invoke(activeChild).booleanValue())) {
                return false;
            }
        } else {
            if (i == 2 || i == 3) {
                return pickChildForBackwardSearch(focusTargetModifierNode, function1);
            }
            if (i != 4) {
                throw new NoWhenBranchMatchedException();
            }
            if (!pickChildForBackwardSearch(focusTargetModifierNode, function1) && (!focusTargetModifierNode.fetchFocusProperties$ui_release().getCanFocus() || !function1.invoke(focusTargetModifierNode).booleanValue())) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: generateAndSearchChildren-4C6V_qg, reason: not valid java name */
    private static final boolean m1594generateAndSearchChildren4C6V_qg(final FocusTargetModifierNode focusTargetModifierNode, final FocusTargetModifierNode focusTargetModifierNode2, final int i, final Function1<? super FocusTargetModifierNode, Boolean> function1) {
        if (m1596searchChildren4C6V_qg(focusTargetModifierNode, focusTargetModifierNode2, i, function1)) {
            return true;
        }
        Boolean bool = (Boolean) BeyondBoundsLayoutKt.m1561searchBeyondBoundsOMvw8(focusTargetModifierNode, i, new Function1<BeyondBoundsLayout.BeyondBoundsScope, Boolean>() { // from class: androidx.compose.ui.focus.OneDimensionalFocusSearchKt$generateAndSearchChildren$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(BeyondBoundsLayout.BeyondBoundsScope searchBeyondBounds) {
                Intrinsics.checkNotNullParameter(searchBeyondBounds, "$this$searchBeyondBounds");
                Boolean boolValueOf = Boolean.valueOf(OneDimensionalFocusSearchKt.m1596searchChildren4C6V_qg(focusTargetModifierNode, focusTargetModifierNode2, i, function1));
                if (boolValueOf.booleanValue() || !searchBeyondBounds.getHasMoreContent()) {
                    return boolValueOf;
                }
                return null;
            }
        });
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: searchChildren-4C6V_qg, reason: not valid java name */
    public static final boolean m1596searchChildren4C6V_qg(FocusTargetModifierNode focusTargetModifierNode, FocusTargetModifierNode focusTargetModifierNode2, int i, Function1<? super FocusTargetModifierNode, Boolean> function1) {
        if (focusTargetModifierNode.getFocusStateImpl() != FocusStateImpl.ActiveParent) {
            throw new IllegalStateException("This function should only be used within a parent that has focus.".toString());
        }
        MutableVector mutableVector = new MutableVector(new FocusTargetModifierNode[16], 0);
        FocusTargetModifierNode focusTargetModifierNode3 = focusTargetModifierNode;
        int iM3598constructorimpl = NodeKind.m3598constructorimpl(1024);
        if (!focusTargetModifierNode3.getNode().getIsAttached()) {
            throw new IllegalStateException("Check failed.".toString());
        }
        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = focusTargetModifierNode3.getNode().getChild();
        if (child == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, focusTargetModifierNode3.getNode());
        } else {
            mutableVector2.add(child);
        }
        while (mutableVector2.isNotEmpty()) {
            Modifier.Node child2 = (Modifier.Node) mutableVector2.removeAt(mutableVector2.getSize() - 1);
            if ((child2.getAggregateChildKindSet() & iM3598constructorimpl) == 0) {
                DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, child2);
            } else {
                while (true) {
                    if (child2 == null) {
                        break;
                    }
                    if ((child2.getKindSet() & iM3598constructorimpl) != 0) {
                        if (child2 instanceof FocusTargetModifierNode) {
                            mutableVector.add((FocusTargetModifierNode) child2);
                        }
                    } else {
                        child2 = child2.getChild();
                    }
                }
            }
        }
        mutableVector.sortWith(FocusableChildrenComparator.INSTANCE);
        if (!FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1578getNextdhqQ8s())) {
            if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1580getPreviousdhqQ8s())) {
                IntRange intRange = new IntRange(0, mutableVector.getSize() - 1);
                int first = intRange.getFirst();
                int last = intRange.getLast();
                if (first <= last) {
                    boolean z = false;
                    while (true) {
                        if (z) {
                            FocusTargetModifierNode focusTargetModifierNode4 = (FocusTargetModifierNode) mutableVector.getContent()[last];
                            if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetModifierNode4) && backwardFocusSearch(focusTargetModifierNode4, function1)) {
                                return true;
                            }
                        }
                        if (Intrinsics.areEqual(mutableVector.getContent()[last], focusTargetModifierNode2)) {
                            z = true;
                        }
                        if (last == first) {
                            break;
                        }
                        last--;
                    }
                }
            } else {
                throw new IllegalStateException(InvalidFocusDirection.toString());
            }
        } else {
            IntRange intRange2 = new IntRange(0, mutableVector.getSize() - 1);
            int first2 = intRange2.getFirst();
            int last2 = intRange2.getLast();
            if (first2 <= last2) {
                boolean z2 = false;
                while (true) {
                    if (z2) {
                        FocusTargetModifierNode focusTargetModifierNode5 = (FocusTargetModifierNode) mutableVector.getContent()[first2];
                        if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetModifierNode5) && forwardFocusSearch(focusTargetModifierNode5, function1)) {
                            return true;
                        }
                    }
                    if (Intrinsics.areEqual(mutableVector.getContent()[first2], focusTargetModifierNode2)) {
                        z2 = true;
                    }
                    if (first2 == last2) {
                        break;
                    }
                    first2++;
                }
            }
        }
        if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1578getNextdhqQ8s()) || !focusTargetModifierNode.fetchFocusProperties$ui_release().getCanFocus() || isRoot(focusTargetModifierNode)) {
            return false;
        }
        return function1.invoke(focusTargetModifierNode).booleanValue();
    }

    private static final boolean isRoot(FocusTargetModifierNode focusTargetModifierNode) {
        Modifier.Node nodeNearestAncestor = DelegatableNodeKt.nearestAncestor(focusTargetModifierNode, NodeKind.m3598constructorimpl(1024));
        if (!(nodeNearestAncestor instanceof FocusTargetModifierNode)) {
            nodeNearestAncestor = null;
        }
        return ((FocusTargetModifierNode) nodeNearestAncestor) == null;
    }

    private static final boolean pickChildForForwardSearch(FocusTargetModifierNode focusTargetModifierNode, Function1<? super FocusTargetModifierNode, Boolean> function1) {
        MutableVector mutableVector = new MutableVector(new FocusTargetModifierNode[16], 0);
        FocusTargetModifierNode focusTargetModifierNode2 = focusTargetModifierNode;
        int iM3598constructorimpl = NodeKind.m3598constructorimpl(1024);
        if (!focusTargetModifierNode2.getNode().getIsAttached()) {
            throw new IllegalStateException("Check failed.".toString());
        }
        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = focusTargetModifierNode2.getNode().getChild();
        if (child == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, focusTargetModifierNode2.getNode());
        } else {
            mutableVector2.add(child);
        }
        while (mutableVector2.isNotEmpty()) {
            Modifier.Node child2 = (Modifier.Node) mutableVector2.removeAt(mutableVector2.getSize() - 1);
            if ((child2.getAggregateChildKindSet() & iM3598constructorimpl) == 0) {
                DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, child2);
            } else {
                while (true) {
                    if (child2 == null) {
                        break;
                    }
                    if ((child2.getKindSet() & iM3598constructorimpl) != 0) {
                        if (child2 instanceof FocusTargetModifierNode) {
                            mutableVector.add((FocusTargetModifierNode) child2);
                        }
                    } else {
                        child2 = child2.getChild();
                    }
                }
            }
        }
        mutableVector.sortWith(FocusableChildrenComparator.INSTANCE);
        int size = mutableVector.getSize();
        if (size <= 0) {
            return false;
        }
        Object[] content = mutableVector.getContent();
        int i = 0;
        do {
            FocusTargetModifierNode focusTargetModifierNode3 = (FocusTargetModifierNode) content[i];
            if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetModifierNode3) && forwardFocusSearch(focusTargetModifierNode3, function1)) {
                return true;
            }
            i++;
        } while (i < size);
        return false;
    }

    private static final boolean pickChildForBackwardSearch(FocusTargetModifierNode focusTargetModifierNode, Function1<? super FocusTargetModifierNode, Boolean> function1) {
        MutableVector mutableVector = new MutableVector(new FocusTargetModifierNode[16], 0);
        FocusTargetModifierNode focusTargetModifierNode2 = focusTargetModifierNode;
        int iM3598constructorimpl = NodeKind.m3598constructorimpl(1024);
        if (!focusTargetModifierNode2.getNode().getIsAttached()) {
            throw new IllegalStateException("Check failed.".toString());
        }
        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
        Modifier.Node child = focusTargetModifierNode2.getNode().getChild();
        if (child == null) {
            DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, focusTargetModifierNode2.getNode());
        } else {
            mutableVector2.add(child);
        }
        while (mutableVector2.isNotEmpty()) {
            Modifier.Node child2 = (Modifier.Node) mutableVector2.removeAt(mutableVector2.getSize() - 1);
            if ((child2.getAggregateChildKindSet() & iM3598constructorimpl) == 0) {
                DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, child2);
            } else {
                while (true) {
                    if (child2 == null) {
                        break;
                    }
                    if ((child2.getKindSet() & iM3598constructorimpl) != 0) {
                        if (child2 instanceof FocusTargetModifierNode) {
                            mutableVector.add((FocusTargetModifierNode) child2);
                        }
                    } else {
                        child2 = child2.getChild();
                    }
                }
            }
        }
        mutableVector.sortWith(FocusableChildrenComparator.INSTANCE);
        int size = mutableVector.getSize();
        if (size > 0) {
            int i = size - 1;
            Object[] content = mutableVector.getContent();
            do {
                FocusTargetModifierNode focusTargetModifierNode3 = (FocusTargetModifierNode) content[i];
                if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetModifierNode3) && backwardFocusSearch(focusTargetModifierNode3, function1)) {
                    return true;
                }
                i--;
            } while (i >= 0);
        }
        return false;
    }

    private static final <T> void forEachItemAfter(MutableVector<T> mutableVector, T t, Function1<? super T, Unit> function1) {
        boolean z = false;
        IntRange intRange = new IntRange(0, mutableVector.getSize() - 1);
        int first = intRange.getFirst();
        int last = intRange.getLast();
        if (first > last) {
            return;
        }
        while (true) {
            if (z) {
                function1.invoke(mutableVector.getContent()[first]);
            }
            if (Intrinsics.areEqual(mutableVector.getContent()[first], t)) {
                z = true;
            }
            if (first == last) {
                return;
            } else {
                first++;
            }
        }
    }

    private static final <T> void forEachItemBefore(MutableVector<T> mutableVector, T t, Function1<? super T, Unit> function1) {
        boolean z = false;
        IntRange intRange = new IntRange(0, mutableVector.getSize() - 1);
        int first = intRange.getFirst();
        int last = intRange.getLast();
        if (first > last) {
            return;
        }
        while (true) {
            if (z) {
                function1.invoke(mutableVector.getContent()[last]);
            }
            if (Intrinsics.areEqual(mutableVector.getContent()[last], t)) {
                z = true;
            }
            if (last == first) {
                return;
            } else {
                last--;
            }
        }
    }
}
