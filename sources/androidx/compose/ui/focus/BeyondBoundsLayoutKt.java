package androidx.compose.ui.focus;

import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BeyondBoundsLayout.kt */
@Metadata(d1 = {"\u0000 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aD\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\u0010\u0005\u001a\u0015\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0006¢\u0006\u0002\b\bH\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"searchBeyondBounds", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/ui/focus/FocusTargetModifierNode;", "direction", "Landroidx/compose/ui/focus/FocusDirection;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/BeyondBoundsLayout$BeyondBoundsScope;", "Lkotlin/ExtensionFunctionType;", "searchBeyondBounds--OM-vw8", "(Landroidx/compose/ui/focus/FocusTargetModifierNode;ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BeyondBoundsLayoutKt {
    /* renamed from: searchBeyondBounds--OM-vw8, reason: not valid java name */
    public static final <T> T m1561searchBeyondBoundsOMvw8(FocusTargetModifierNode searchBeyondBounds, int i, Function1<? super BeyondBoundsLayout.BeyondBoundsScope, ? extends T> block) {
        int iM3382getBeforehoxUOeE;
        Intrinsics.checkNotNullParameter(searchBeyondBounds, "$this$searchBeyondBounds");
        Intrinsics.checkNotNullParameter(block, "block");
        BeyondBoundsLayout beyondBoundsLayoutParent$ui_release = searchBeyondBounds.getBeyondBoundsLayoutParent$ui_release();
        if (beyondBoundsLayoutParent$ui_release == null) {
            return null;
        }
        if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1582getUpdhqQ8s())) {
            iM3382getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m3380getAbovehoxUOeE();
        } else if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1573getDowndhqQ8s())) {
            iM3382getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m3383getBelowhoxUOeE();
        } else if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1577getLeftdhqQ8s())) {
            iM3382getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m3384getLefthoxUOeE();
        } else if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1581getRightdhqQ8s())) {
            iM3382getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m3385getRighthoxUOeE();
        } else if (FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1578getNextdhqQ8s())) {
            iM3382getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m3381getAfterhoxUOeE();
        } else {
            if (!FocusDirection.m1565equalsimpl0(i, FocusDirection.INSTANCE.m1580getPreviousdhqQ8s())) {
                throw new IllegalStateException("Unsupported direction for beyond bounds layout".toString());
            }
            iM3382getBeforehoxUOeE = BeyondBoundsLayout.LayoutDirection.INSTANCE.m3382getBeforehoxUOeE();
        }
        return (T) beyondBoundsLayoutParent$ui_release.mo891layouto7g1Pn8(iM3382getBeforehoxUOeE, block);
    }
}
