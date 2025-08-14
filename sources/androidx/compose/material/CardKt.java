package androidx.compose.material;

import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: Card.kt */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a¬\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001b\u0010\u001c\u001af\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u001e\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001f"}, d2 = {"Card", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "shape", "Landroidx/compose/ui/graphics/Shape;", ViewProps.BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "contentColor", Constants.KEY_BORDER, "Landroidx/compose/foundation/BorderStroke;", ViewProps.ELEVATION, "Landroidx/compose/ui/unit/Dp;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "indication", "Landroidx/compose/foundation/Indication;", ViewProps.ENABLED, "", "onClickLabel", "", ViewProps.ROLE, "Landroidx/compose/ui/semantics/Role;", "content", "Landroidx/compose/runtime/Composable;", "Card-9VG74zQ", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/Indication;ZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "Card-F-jzlyU", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJLandroidx/compose/foundation/BorderStroke;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class CardKt {
    /* JADX WARN: Removed duplicated region for block: B:100:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:123:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0129  */
    /* renamed from: Card-F-jzlyU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1217CardFjzlyU(androidx.compose.ui.Modifier r25, androidx.compose.ui.graphics.Shape r26, long r27, long r29, androidx.compose.foundation.BorderStroke r31, float r32, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r33, androidx.compose.runtime.Composer r34, final int r35, final int r36) {
        /*
            Method dump skipped, instructions count: 465
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.CardKt.m1217CardFjzlyU(androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, long, long, androidx.compose.foundation.BorderStroke, float, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:211:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0131  */
    @androidx.compose.material.ExperimentalMaterialApi
    /* renamed from: Card-9VG74zQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1216Card9VG74zQ(final kotlin.jvm.functions.Function0<kotlin.Unit> r37, androidx.compose.ui.Modifier r38, androidx.compose.ui.graphics.Shape r39, long r40, long r42, androidx.compose.foundation.BorderStroke r44, float r45, androidx.compose.foundation.interaction.MutableInteractionSource r46, androidx.compose.foundation.Indication r47, boolean r48, java.lang.String r49, androidx.compose.ui.semantics.Role r50, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r51, androidx.compose.runtime.Composer r52, final int r53, final int r54, final int r55) {
        /*
            Method dump skipped, instructions count: 878
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.CardKt.m1216Card9VG74zQ(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, androidx.compose.ui.graphics.Shape, long, long, androidx.compose.foundation.BorderStroke, float, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.foundation.Indication, boolean, java.lang.String, androidx.compose.ui.semantics.Role, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
