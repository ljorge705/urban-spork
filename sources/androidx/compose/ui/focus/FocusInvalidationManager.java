package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.NodeKind;
import androidx.exifinterface.media.ExifInterface;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FocusInvalidationManager.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0018\u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\tJ\u000e\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000bJ\u000e\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\rJ%\u0010\u000f\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110\b2\u0006\u0010\u0010\u001a\u0002H\u0011H\u0002¢\u0006\u0002\u0010\u0012R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/compose/ui/focus/FocusInvalidationManager;", "", "onRequestApplyChangesListener", "Lkotlin/Function1;", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function1;)V", "focusEventNodes", "", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "focusPropertiesNodes", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "focusTargetNodes", "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "invalidateNodes", "scheduleInvalidation", "node", ExifInterface.GPS_DIRECTION_TRUE, "(Ljava/util/Set;Ljava/lang/Object;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class FocusInvalidationManager {
    private Set<FocusEventModifierNode> focusEventNodes;
    private Set<FocusPropertiesModifierNode> focusPropertiesNodes;
    private Set<FocusTargetModifierNode> focusTargetNodes;
    private final Function0<Unit> invalidateNodes;
    private final Function1<Function0<Unit>, Unit> onRequestApplyChangesListener;

    /* JADX WARN: Multi-variable type inference failed */
    public FocusInvalidationManager(Function1<? super Function0<Unit>, Unit> onRequestApplyChangesListener) {
        Intrinsics.checkNotNullParameter(onRequestApplyChangesListener, "onRequestApplyChangesListener");
        this.onRequestApplyChangesListener = onRequestApplyChangesListener;
        this.focusTargetNodes = new LinkedHashSet();
        this.focusEventNodes = new LinkedHashSet();
        this.focusPropertiesNodes = new LinkedHashSet();
        this.invalidateNodes = new Function0<Unit>() { // from class: androidx.compose.ui.focus.FocusInvalidationManager$invalidateNodes$1
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
                FocusStateImpl focusState;
                Set set = this.this$0.focusPropertiesNodes;
                FocusInvalidationManager focusInvalidationManager = this.this$0;
                Iterator it = set.iterator();
                while (true) {
                    int i = 16;
                    if (!it.hasNext()) {
                        this.this$0.focusPropertiesNodes.clear();
                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                        Set<FocusEventModifierNode> set2 = this.this$0.focusEventNodes;
                        FocusInvalidationManager focusInvalidationManager2 = this.this$0;
                        for (FocusEventModifierNode focusEventModifierNode : set2) {
                            if (focusEventModifierNode.getNode().getIsAttached()) {
                                FocusEventModifierNode focusEventModifierNode2 = focusEventModifierNode;
                                int iM3598constructorimpl = NodeKind.m3598constructorimpl(1024);
                                if (!focusEventModifierNode2.getNode().getIsAttached()) {
                                    throw new IllegalStateException("Check failed.".toString());
                                }
                                MutableVector mutableVector = new MutableVector(new Modifier.Node[i], 0);
                                Modifier.Node child = focusEventModifierNode2.getNode().getChild();
                                if (child == null) {
                                    DelegatableNodeKt.addLayoutNodeChildren(mutableVector, focusEventModifierNode2.getNode());
                                } else {
                                    mutableVector.add(child);
                                }
                                FocusTargetModifierNode focusTargetModifierNode = null;
                                boolean z = true;
                                boolean z2 = false;
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
                                                    FocusTargetModifierNode focusTargetModifierNode2 = (FocusTargetModifierNode) child2;
                                                    if (focusTargetModifierNode != null) {
                                                        z2 = true;
                                                    }
                                                    if (focusInvalidationManager2.focusTargetNodes.contains(focusTargetModifierNode2)) {
                                                        linkedHashSet.add(focusTargetModifierNode2);
                                                        z = false;
                                                    }
                                                    focusTargetModifierNode = focusTargetModifierNode2;
                                                }
                                            } else {
                                                child2 = child2.getChild();
                                            }
                                        }
                                    }
                                }
                                if (z) {
                                    if (z2) {
                                        focusState = FocusEventModifierNodeKt.getFocusState(focusEventModifierNode);
                                    } else if (focusTargetModifierNode == null || (focusState = focusTargetModifierNode.getFocusState()) == null) {
                                        focusState = FocusStateImpl.Inactive;
                                    }
                                    focusEventModifierNode.onFocusEvent(focusState);
                                }
                            }
                            i = 16;
                        }
                        this.this$0.focusEventNodes.clear();
                        for (FocusTargetModifierNode focusTargetModifierNode3 : this.this$0.focusTargetNodes) {
                            if (focusTargetModifierNode3.getIsAttached()) {
                                FocusState focusState2 = focusTargetModifierNode3.getFocusState();
                                focusTargetModifierNode3.invalidateFocus$ui_release();
                                if (!Intrinsics.areEqual(focusState2, focusTargetModifierNode3.getFocusState()) || linkedHashSet.contains(focusTargetModifierNode3)) {
                                    FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetModifierNode3);
                                }
                            }
                        }
                        this.this$0.focusTargetNodes.clear();
                        linkedHashSet.clear();
                        if (this.this$0.focusPropertiesNodes.isEmpty()) {
                            if (this.this$0.focusEventNodes.isEmpty()) {
                                if (!this.this$0.focusTargetNodes.isEmpty()) {
                                    throw new IllegalStateException("Check failed.".toString());
                                }
                                return;
                            }
                            throw new IllegalStateException("Check failed.".toString());
                        }
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    FocusPropertiesModifierNode focusPropertiesModifierNode = (FocusPropertiesModifierNode) it.next();
                    int iM3598constructorimpl2 = NodeKind.m3598constructorimpl(1024);
                    if (!focusPropertiesModifierNode.getNode().getIsAttached()) {
                        throw new IllegalStateException("Check failed.".toString());
                    }
                    MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                    Modifier.Node child3 = focusPropertiesModifierNode.getNode().getChild();
                    if (child3 == null) {
                        DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, focusPropertiesModifierNode.getNode());
                    } else {
                        mutableVector2.add(child3);
                    }
                    while (mutableVector2.isNotEmpty()) {
                        Modifier.Node child4 = (Modifier.Node) mutableVector2.removeAt(mutableVector2.getSize() - 1);
                        if ((child4.getAggregateChildKindSet() & iM3598constructorimpl2) == 0) {
                            DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, child4);
                        } else {
                            while (true) {
                                if (child4 == null) {
                                    break;
                                }
                                if ((child4.getKindSet() & iM3598constructorimpl2) != 0) {
                                    if (child4 instanceof FocusTargetModifierNode) {
                                        focusInvalidationManager.focusTargetNodes.add((FocusTargetModifierNode) child4);
                                    }
                                } else {
                                    child4 = child4.getChild();
                                }
                            }
                        }
                    }
                }
            }
        };
    }

    public final void scheduleInvalidation(FocusTargetModifierNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        scheduleInvalidation(this.focusTargetNodes, node);
    }

    public final void scheduleInvalidation(FocusEventModifierNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        scheduleInvalidation(this.focusEventNodes, node);
    }

    public final void scheduleInvalidation(FocusPropertiesModifierNode node) {
        Intrinsics.checkNotNullParameter(node, "node");
        scheduleInvalidation(this.focusPropertiesNodes, node);
    }

    private final <T> void scheduleInvalidation(Set<T> set, T t) {
        if (set.contains(t)) {
            return;
        }
        set.add(t);
        if (this.focusTargetNodes.size() + this.focusEventNodes.size() + this.focusPropertiesNodes.size() == 1) {
            this.onRequestApplyChangesListener.invoke(this.invalidateNodes);
        }
    }
}
