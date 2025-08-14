package androidx.compose.material;

import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import com.BV.LinearGradient.LinearGradientManager;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: Button.kt */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u008d\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u008d\u0001\u0010\u001a\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u008d\u0001\u0010\u001b\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007¢\u0006\u0002\u0010\u0019¨\u0006\u001c"}, d2 = {"Button", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", ViewProps.ENABLED, "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", ViewProps.ELEVATION, "Landroidx/compose/material/ButtonElevation;", "shape", "Landroidx/compose/ui/graphics/Shape;", Constants.KEY_BORDER, "Landroidx/compose/foundation/BorderStroke;", LinearGradientManager.PROP_COLORS, "Landroidx/compose/material/ButtonColors;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/ButtonElevation;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/material/ButtonColors;Landroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "OutlinedButton", "TextButton", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ButtonKt {
    /* JADX WARN: Removed duplicated region for block: B:100:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:184:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0108  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Button(final kotlin.jvm.functions.Function0<kotlin.Unit> r38, androidx.compose.ui.Modifier r39, boolean r40, androidx.compose.foundation.interaction.MutableInteractionSource r41, androidx.compose.material.ButtonElevation r42, androidx.compose.ui.graphics.Shape r43, androidx.compose.foundation.BorderStroke r44, androidx.compose.material.ButtonColors r45, androidx.compose.foundation.layout.PaddingValues r46, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r47, androidx.compose.runtime.Composer r48, final int r49, final int r50) {
        /*
            Method dump skipped, instructions count: 893
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ButtonKt.Button(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.material.ButtonElevation, androidx.compose.ui.graphics.Shape, androidx.compose.foundation.BorderStroke, androidx.compose.material.ButtonColors, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:174:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0109  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void OutlinedButton(final kotlin.jvm.functions.Function0<kotlin.Unit> r31, androidx.compose.ui.Modifier r32, boolean r33, androidx.compose.foundation.interaction.MutableInteractionSource r34, androidx.compose.material.ButtonElevation r35, androidx.compose.ui.graphics.Shape r36, androidx.compose.foundation.BorderStroke r37, androidx.compose.material.ButtonColors r38, androidx.compose.foundation.layout.PaddingValues r39, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r40, androidx.compose.runtime.Composer r41, final int r42, final int r43) {
        /*
            Method dump skipped, instructions count: 701
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ButtonKt.OutlinedButton(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.material.ButtonElevation, androidx.compose.ui.graphics.Shape, androidx.compose.foundation.BorderStroke, androidx.compose.material.ButtonColors, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:170:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0121  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TextButton(final kotlin.jvm.functions.Function0<kotlin.Unit> r30, androidx.compose.ui.Modifier r31, boolean r32, androidx.compose.foundation.interaction.MutableInteractionSource r33, androidx.compose.material.ButtonElevation r34, androidx.compose.ui.graphics.Shape r35, androidx.compose.foundation.BorderStroke r36, androidx.compose.material.ButtonColors r37, androidx.compose.foundation.layout.PaddingValues r38, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r39, androidx.compose.runtime.Composer r40, final int r41, final int r42) {
        /*
            Method dump skipped, instructions count: 678
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ButtonKt.TextButton(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.material.ButtonElevation, androidx.compose.ui.graphics.Shape, androidx.compose.foundation.BorderStroke, androidx.compose.material.ButtonColors, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Button$lambda-1, reason: not valid java name */
    public static final long m1214Button$lambda1(State<Color> state) {
        return state.getValue().m1887unboximpl();
    }
}
