package androidx.compose.material;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Dp;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: IconButton.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aL\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\b\b\u0002\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00060\b¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\u0010\u001aZ\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u000b2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00060\u00142\b\b\u0002\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00060\b¢\u0006\u0002\b\u000fH\u0007¢\u0006\u0002\u0010\u0015\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0013\u0010\u0002\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"IconButtonSizeModifier", "Landroidx/compose/ui/Modifier;", "RippleRadius", "Landroidx/compose/ui/unit/Dp;", "F", "IconButton", "", "onClick", "Lkotlin/Function0;", "modifier", ViewProps.ENABLED, "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "content", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "IconToggleButton", "checked", "onCheckedChange", "Lkotlin/Function1;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class IconButtonKt {
    private static final float RippleRadius = Dp.m4390constructorimpl(24);
    private static final Modifier IconButtonSizeModifier = SizeKt.m733size3ABfNKs(Modifier.INSTANCE, Dp.m4390constructorimpl(48));

    /* JADX WARN: Removed duplicated region for block: B:26:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:92:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void IconButton(final kotlin.jvm.functions.Function0<kotlin.Unit> r18, androidx.compose.ui.Modifier r19, boolean r20, androidx.compose.foundation.interaction.MutableInteractionSource r21, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r22, androidx.compose.runtime.Composer r23, final int r24, final int r25) {
        /*
            Method dump skipped, instructions count: 614
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.IconButtonKt.IconButton(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:102:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x022e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void IconToggleButton(final boolean r21, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r22, androidx.compose.ui.Modifier r23, boolean r24, androidx.compose.foundation.interaction.MutableInteractionSource r25, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r26, androidx.compose.runtime.Composer r27, final int r28, final int r29) {
        /*
            Method dump skipped, instructions count: 656
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.IconButtonKt.IconToggleButton(boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }
}
