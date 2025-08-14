package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;

/* compiled from: NodeCoordinator.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a;\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\u0007\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\b"}, d2 = {"nextUncheckedUntil", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/ui/node/DelegatableNode;", "type", "Landroidx/compose/ui/node/NodeKind;", "stopType", "nextUncheckedUntil-hw7D004", "(Landroidx/compose/ui/node/DelegatableNode;II)Ljava/lang/Object;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NodeCoordinatorKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: nextUncheckedUntil-hw7D004, reason: not valid java name */
    public static final <T> T m3596nextUncheckedUntilhw7D004(DelegatableNode delegatableNode, int i, int i2) {
        Modifier.Node node = (T) delegatableNode.getNode().getChild();
        if (node == null) {
            return null;
        }
        int aggregateChildKindSet = node.getAggregateChildKindSet() & i;
        if (aggregateChildKindSet == 0) {
            return null;
        }
        for (Object obj = node; obj != null; obj = (T) ((Modifier.Node) obj).getChild()) {
            int kindSet = ((Modifier.Node) obj).getKindSet();
            if ((kindSet & i2) != 0) {
                return null;
            }
            if ((kindSet & i) != 0) {
                return (T) obj;
            }
        }
        return null;
    }
}
