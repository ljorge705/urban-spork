package androidx.compose.material;

import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: Badge.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aU\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152 \b\u0002\u0010\u0017\u001a\u001a\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0018¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001d\u001as\u0010\u001e\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152 \b\u0002\u0010\u001f\u001a\u001a\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0018¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001b2\u001c\u0010\u0017\u001a\u0018\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00110\u0018¢\u0006\u0002\b\u001a¢\u0006\u0002\b\u001bH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b!\u0010\"\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0019\u0010\u0003\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006\"\u0019\u0010\b\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006\"\u0019\u0010\n\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006\"\u0019\u0010\f\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\r\u0010\u0006\"\u0019\u0010\u000e\u001a\u00020\u0004X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000f\u0010\u0006\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006#"}, d2 = {"BadgeContentFontSize", "Landroidx/compose/ui/unit/TextUnit;", "J", "BadgeHorizontalOffset", "Landroidx/compose/ui/unit/Dp;", "getBadgeHorizontalOffset", "()F", "F", "BadgeRadius", "getBadgeRadius", "BadgeWithContentHorizontalOffset", "getBadgeWithContentHorizontalOffset", "BadgeWithContentHorizontalPadding", "getBadgeWithContentHorizontalPadding", "BadgeWithContentRadius", "getBadgeWithContentRadius", "Badge", "", "modifier", "Landroidx/compose/ui/Modifier;", ViewProps.BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "contentColor", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "Badge-eopBjH0", "(Landroidx/compose/ui/Modifier;JJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BadgeBox", "badgeContent", "Landroidx/compose/foundation/layout/BoxScope;", "BadgeBox-DTcfvLk", "(Landroidx/compose/ui/Modifier;JJLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class BadgeKt {
    private static final float BadgeHorizontalOffset;
    private static final float BadgeRadius;
    private static final float BadgeWithContentHorizontalPadding;
    private static final float BadgeWithContentRadius = Dp.m4390constructorimpl(8);
    private static final long BadgeContentFontSize = TextUnitKt.getSp(10);
    private static final float BadgeWithContentHorizontalOffset = Dp.m4390constructorimpl(-Dp.m4390constructorimpl(6));

    public static final float getBadgeHorizontalOffset() {
        return BadgeHorizontalOffset;
    }

    public static final float getBadgeRadius() {
        return BadgeRadius;
    }

    public static final float getBadgeWithContentHorizontalOffset() {
        return BadgeWithContentHorizontalOffset;
    }

    public static final float getBadgeWithContentHorizontalPadding() {
        return BadgeWithContentHorizontalPadding;
    }

    public static final float getBadgeWithContentRadius() {
        return BadgeWithContentRadius;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:124:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x017a  */
    @androidx.compose.material.ExperimentalMaterialApi
    /* renamed from: BadgeBox-DTcfvLk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1184BadgeBoxDTcfvLk(androidx.compose.ui.Modifier r20, long r21, long r23, kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r25, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.BoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r26, androidx.compose.runtime.Composer r27, final int r28, final int r29) {
        /*
            Method dump skipped, instructions count: 805
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.BadgeKt.m1184BadgeBoxDTcfvLk(androidx.compose.ui.Modifier, long, long, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:97:? A[RETURN, SYNTHETIC] */
    @androidx.compose.material.ExperimentalMaterialApi
    /* renamed from: Badge-eopBjH0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1183BadgeeopBjH0(androidx.compose.ui.Modifier r16, long r17, long r19, kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r21, androidx.compose.runtime.Composer r22, final int r23, final int r24) {
        /*
            Method dump skipped, instructions count: 579
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.BadgeKt.m1183BadgeeopBjH0(androidx.compose.ui.Modifier, long, long, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    static {
        float f = 4;
        BadgeRadius = Dp.m4390constructorimpl(f);
        BadgeWithContentHorizontalPadding = Dp.m4390constructorimpl(f);
        BadgeHorizontalOffset = Dp.m4390constructorimpl(-Dp.m4390constructorimpl(f));
    }
}
