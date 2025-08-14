package androidx.compose.ui.layout;

import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LayoutId.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B&\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n¢\u0006\u0002\u0010\u000bJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0005H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u0005*\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0005H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Landroidx/compose/ui/layout/LayoutId;", "Landroidx/compose/ui/layout/ParentDataModifier;", "Landroidx/compose/ui/layout/LayoutIdParentData;", "Landroidx/compose/ui/platform/InspectorValueInfo;", "layoutId", "", "inspectorInfo", "Lkotlin/Function1;", "Landroidx/compose/ui/platform/InspectorInfo;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "getLayoutId", "()Ljava/lang/Object;", "equals", "", "other", "hashCode", "", "toString", "", "modifyParentData", "Landroidx/compose/ui/unit/Density;", "parentData", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class LayoutId extends InspectorValueInfo implements ParentDataModifier, LayoutIdParentData {
    private final Object layoutId;

    @Override // androidx.compose.ui.layout.LayoutIdParentData
    public Object getLayoutId() {
        return this.layoutId;
    }

    @Override // androidx.compose.ui.layout.ParentDataModifier
    public Object modifyParentData(Density density, Object obj) {
        Intrinsics.checkNotNullParameter(density, "<this>");
        return this;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LayoutId(Object layoutId, Function1<? super InspectorInfo, Unit> inspectorInfo) {
        super(inspectorInfo);
        Intrinsics.checkNotNullParameter(layoutId, "layoutId");
        Intrinsics.checkNotNullParameter(inspectorInfo, "inspectorInfo");
        this.layoutId = layoutId;
    }

    public int hashCode() {
        return getLayoutId().hashCode();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        LayoutId layoutId = other instanceof LayoutId ? (LayoutId) other : null;
        if (layoutId == null) {
            return false;
        }
        return Intrinsics.areEqual(getLayoutId(), layoutId.getLayoutId());
    }

    public String toString() {
        return "LayoutId(id=" + getLayoutId() + ')';
    }
}
