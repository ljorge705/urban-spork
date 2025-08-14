package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.layout.Measurable;
import com.facebook.react.uimanager.ViewProps;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* compiled from: LayoutNodeLayoutDelegate.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0007H\u0002¨\u0006\b"}, d2 = {"updateChildMeasurables", "", "Landroidx/compose/ui/node/LayoutNode;", FirebaseAnalytics.Param.DESTINATION, "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/ui/layout/Measurable;", ViewProps.TRANSFORM, "Lkotlin/Function1;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LayoutNodeLayoutDelegateKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateChildMeasurables(LayoutNode layoutNode, MutableVector<Measurable> mutableVector, Function1<? super LayoutNode, ? extends Measurable> function1) {
        MutableVector<LayoutNode> mutableVector2 = layoutNode.get_children$ui_release();
        int size = mutableVector2.getSize();
        if (size > 0) {
            LayoutNode[] content = mutableVector2.getContent();
            int i = 0;
            do {
                LayoutNode layoutNode2 = content[i];
                if (mutableVector.getSize() <= i) {
                    mutableVector.add(function1.invoke(layoutNode2));
                } else {
                    mutableVector.set(i, function1.invoke(layoutNode2));
                }
                i++;
            } while (i < size);
        }
        mutableVector.removeRange(layoutNode.getChildren$ui_release().size(), mutableVector.getSize());
    }
}
