package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DelegatingNode.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0001H\u0002J\r\u0010\u0007\u001a\u00020\u0005H\u0010¢\u0006\u0002\b\bJ#\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\f¢\u0006\u0002\u0010\rJ\r\u0010\u000e\u001a\u00020\u0005H\u0010¢\u0006\u0002\b\u000fJ\u001d\u0010\u0010\u001a\u00020\u00052\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00050\u0012H\u0082\bJ\r\u0010\u0013\u001a\u00020\u0005H\u0010¢\u0006\u0002\b\u0014J\u0017\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0010¢\u0006\u0002\b\u0018R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/Modifier$Node;", "()V", "delegate", "addDelegate", "", "node", "attach", "attach$ui_release", "delegated", ExifInterface.GPS_DIRECTION_TRUE, "fn", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Landroidx/compose/ui/Modifier$Node;", "detach", "detach$ui_release", "forEachDelegate", "block", "Lkotlin/Function1;", "reset", "reset$ui_release", "updateCoordinator", "coordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "updateCoordinator$ui_release", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class DelegatingNode extends Modifier.Node {
    public static final int $stable = 8;
    private Modifier.Node delegate;

    @Override // androidx.compose.ui.Modifier.Node
    public void updateCoordinator$ui_release(NodeCoordinator coordinator) {
        super.updateCoordinator$ui_release(coordinator);
        for (Modifier.Node parent = this.delegate; parent != null; parent = parent.getParent()) {
            parent.updateCoordinator$ui_release(coordinator);
        }
    }

    public final <T extends Modifier.Node> T delegated(Function0<? extends T> fn) {
        Intrinsics.checkNotNullParameter(fn, "fn");
        Modifier.Node node = getNode();
        T tInvoke = fn.invoke();
        tInvoke.setAsDelegateTo$ui_release(node);
        if (getIsAttached()) {
            updateCoordinator$ui_release(node.getCoordinator());
            tInvoke.attach$ui_release();
        }
        addDelegate(tInvoke);
        return tInvoke;
    }

    private final void addDelegate(Modifier.Node node) {
        Modifier.Node node2 = this.delegate;
        if (node2 != null) {
            node.setParent$ui_release(node2);
        }
        this.delegate = node;
    }

    private final void forEachDelegate(Function1<? super Modifier.Node, Unit> block) {
        for (Modifier.Node parent = this.delegate; parent != null; parent = parent.getParent()) {
            block.invoke(parent);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void attach$ui_release() {
        super.attach$ui_release();
        for (Modifier.Node parent = this.delegate; parent != null; parent = parent.getParent()) {
            parent.updateCoordinator$ui_release(getCoordinator());
            parent.attach$ui_release();
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void detach$ui_release() {
        for (Modifier.Node parent = this.delegate; parent != null; parent = parent.getParent()) {
            parent.detach$ui_release();
        }
        super.detach$ui_release();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void reset$ui_release() {
        super.reset$ui_release();
        for (Modifier.Node parent = this.delegate; parent != null; parent = parent.getParent()) {
            parent.reset$ui_release();
        }
    }
}
