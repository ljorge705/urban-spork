package androidx.compose.ui.modifier;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import io.sentry.protocol.SentryThread;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ModifierLocalNode.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u00012\u00020\u0002J)\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\b0\t2\u0006\u0010\u000f\u001a\u0002H\bH\u0016¢\u0006\u0002\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R$\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Landroidx/compose/ui/modifier/ModifierLocalNode;", "Landroidx/compose/ui/modifier/ModifierLocalReadScope;", "Landroidx/compose/ui/node/DelegatableNode;", "providedValues", "Landroidx/compose/ui/modifier/ModifierLocalMap;", "getProvidedValues", "()Landroidx/compose/ui/modifier/ModifierLocalMap;", SentryThread.JsonKeys.CURRENT, ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/ui/modifier/ModifierLocal;", "getCurrent", "(Landroidx/compose/ui/modifier/ModifierLocal;)Ljava/lang/Object;", "provide", "", Constants.KEY_KEY, "value", "(Landroidx/compose/ui/modifier/ModifierLocal;Ljava/lang/Object;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ModifierLocalNode extends ModifierLocalReadScope, DelegatableNode {
    default ModifierLocalMap getProvidedValues() {
        return EmptyMap.INSTANCE;
    }

    default <T> void provide(ModifierLocal<T> key, T value) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (getProvidedValues() == EmptyMap.INSTANCE) {
            throw new IllegalArgumentException("In order to provide locals you must override providedValues: ModifierLocalMap".toString());
        }
        if (!getProvidedValues().contains$ui_release(key)) {
            throw new IllegalArgumentException(("Any provided key must be initially provided in the overridden providedValues: ModifierLocalMap property. Key " + key + " was not found.").toString());
        }
        getProvidedValues().mo3480set$ui_release(key, value);
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalReadScope
    default <T> T getCurrent(ModifierLocal<T> modifierLocal) {
        NodeChain nodes;
        Intrinsics.checkNotNullParameter(modifierLocal, "<this>");
        if (!getNode().getIsAttached()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        ModifierLocalNode modifierLocalNode = this;
        int iM3598constructorimpl = NodeKind.m3598constructorimpl(32);
        if (!modifierLocalNode.getNode().getIsAttached()) {
            throw new IllegalStateException("Check failed.".toString());
        }
        Modifier.Node parent = modifierLocalNode.getNode().getParent();
        LayoutNode layoutNodeRequireLayoutNode = DelegatableNodeKt.requireLayoutNode(modifierLocalNode);
        while (layoutNodeRequireLayoutNode != null) {
            if ((layoutNodeRequireLayoutNode.getNodes().getHead().getAggregateChildKindSet() & iM3598constructorimpl) != 0) {
                while (parent != null) {
                    if ((parent.getKindSet() & iM3598constructorimpl) != 0 && (parent instanceof ModifierLocalNode)) {
                        ModifierLocalNode modifierLocalNode2 = (ModifierLocalNode) parent;
                        if (modifierLocalNode2.getProvidedValues().contains$ui_release(modifierLocal)) {
                            return (T) modifierLocalNode2.getProvidedValues().get$ui_release(modifierLocal);
                        }
                    }
                    parent = parent.getParent();
                }
            }
            layoutNodeRequireLayoutNode = layoutNodeRequireLayoutNode.getParent$ui_release();
            parent = (layoutNodeRequireLayoutNode == null || (nodes = layoutNodeRequireLayoutNode.getNodes()) == null) ? null : nodes.getTail();
        }
        return modifierLocal.getDefaultFactory$ui_release().invoke();
    }
}
