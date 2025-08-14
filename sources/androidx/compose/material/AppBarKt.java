package androidx.compose.material;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Dp;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;

/* compiled from: AppBar.kt */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001ae\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00072\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\n0\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001a\u001aq\u0010\u001b\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\n0\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u0087\u0001\u0010\u001f\u001a\u00020\n2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\n0!¢\u0006\u0002\b\u00172\b\b\u0002\u0010\u0013\u001a\u00020\u00072\u0015\b\u0002\u0010\"\u001a\u000f\u0012\u0004\u0012\u00020\n\u0018\u00010!¢\u0006\u0002\b\u00172\u001e\b\u0002\u0010#\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\n0\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u0001H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%\u001ae\u0010\u001f\u001a\u00020\n2\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00012\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u001c\u0010\u0014\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\n0\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'\u001a\u0019\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)H\u0080\b\u001a,\u0010,\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020)0-2\u0006\u0010.\u001a\u00020)2\u0006\u0010+\u001a\u00020)2\u0006\u0010/\u001a\u00020)H\u0000\u001a\u0011\u00100\u001a\u00020)2\u0006\u00101\u001a\u00020)H\u0082\b\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0003\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0004\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0005\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u00062"}, d2 = {"AppBarHeight", "Landroidx/compose/ui/unit/Dp;", "F", "AppBarHorizontalPadding", "BottomAppBarCutoutOffset", "BottomAppBarRoundedEdgeRadius", "TitleIconModifier", "Landroidx/compose/ui/Modifier;", "TitleInsetWithoutIcon", "AppBar", "", ViewProps.BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "contentColor", ViewProps.ELEVATION, "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "shape", "Landroidx/compose/ui/graphics/Shape;", "modifier", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "AppBar-celAv9A", "(JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomAppBar", "cutoutShape", "BottomAppBar-Y1yfwus", "(Landroidx/compose/ui/Modifier;JJLandroidx/compose/ui/graphics/Shape;FLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "TopAppBar", "title", "Lkotlin/Function0;", "navigationIcon", Constants.KEY_ACTIONS, "TopAppBar-xWeB9-s", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;JJFLandroidx/compose/runtime/Composer;II)V", "TopAppBar-HsRjFd4", "(Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "calculateCutoutCircleYIntercept", "", "cutoutRadius", "verticalOffset", "calculateRoundedEdgeIntercept", "Lkotlin/Pair;", "controlPointX", Constants.KEY_RADIUS, "square", "x", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class AppBarKt {
    private static final float AppBarHeight = Dp.m4390constructorimpl(56);
    private static final float AppBarHorizontalPadding;
    private static final float BottomAppBarCutoutOffset;
    private static final float BottomAppBarRoundedEdgeRadius;
    private static final Modifier TitleIconModifier;
    private static final Modifier TitleInsetWithoutIcon;

    private static final float square(float f) {
        return f * f;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:127:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f5  */
    /* renamed from: TopAppBar-xWeB9-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1167TopAppBarxWeB9s(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r27, androidx.compose.ui.Modifier r28, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r29, kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r30, long r31, long r33, float r35, androidx.compose.runtime.Composer r36, final int r37, final int r38) {
        /*
            Method dump skipped, instructions count: 469
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.AppBarKt.m1167TopAppBarxWeB9s(kotlin.jvm.functions.Function2, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, long, long, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:115:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0118  */
    /* renamed from: TopAppBar-HsRjFd4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1166TopAppBarHsRjFd4(androidx.compose.ui.Modifier r22, long r23, long r25, float r27, androidx.compose.foundation.layout.PaddingValues r28, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r29, androidx.compose.runtime.Composer r30, final int r31, final int r32) {
        /*
            Method dump skipped, instructions count: 417
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.AppBarKt.m1166TopAppBarHsRjFd4(androidx.compose.ui.Modifier, long, long, float, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:137:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f7  */
    /* renamed from: BottomAppBar-Y1yfwus, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1165BottomAppBarY1yfwus(androidx.compose.ui.Modifier r24, long r25, long r27, androidx.compose.ui.graphics.Shape r29, float r30, androidx.compose.foundation.layout.PaddingValues r31, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, androidx.compose.runtime.Composer r33, final int r34, final int r35) {
        /*
            Method dump skipped, instructions count: 524
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.AppBarKt.m1165BottomAppBarY1yfwus(androidx.compose.ui.Modifier, long, long, androidx.compose.ui.graphics.Shape, float, androidx.compose.foundation.layout.PaddingValues, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final float calculateCutoutCircleYIntercept(float f, float f2) {
        return -((float) Math.sqrt((f * f) - (f2 * f2)));
    }

    public static final Pair<Float, Float> calculateRoundedEdgeIntercept(float f, float f2, float f3) {
        Float fValueOf;
        Float fValueOf2;
        Pair pair;
        Float fValueOf3;
        Float fValueOf4;
        float f4 = f2 * f2;
        float f5 = f3 * f3;
        float f6 = (f * f) + f4;
        float f7 = f4 * f5 * (f6 - f5);
        float f8 = f * f5;
        double d = f7;
        float fSqrt = (f8 - ((float) Math.sqrt(d))) / f6;
        float fSqrt2 = (f8 + ((float) Math.sqrt(d))) / f6;
        float fSqrt3 = (float) Math.sqrt(f5 - (fSqrt * fSqrt));
        float fSqrt4 = (float) Math.sqrt(f5 - (fSqrt2 * fSqrt2));
        if (f2 > 0.0f) {
            if (fSqrt3 > fSqrt4) {
                fValueOf3 = Float.valueOf(fSqrt);
                fValueOf4 = Float.valueOf(fSqrt3);
            } else {
                fValueOf3 = Float.valueOf(fSqrt2);
                fValueOf4 = Float.valueOf(fSqrt4);
            }
            pair = TuplesKt.to(fValueOf3, fValueOf4);
        } else {
            if (fSqrt3 < fSqrt4) {
                fValueOf = Float.valueOf(fSqrt);
                fValueOf2 = Float.valueOf(fSqrt3);
            } else {
                fValueOf = Float.valueOf(fSqrt2);
                fValueOf2 = Float.valueOf(fSqrt4);
            }
            pair = TuplesKt.to(fValueOf, fValueOf2);
        }
        float fFloatValue = ((Number) pair.component1()).floatValue();
        float fFloatValue2 = ((Number) pair.component2()).floatValue();
        if (fFloatValue < f) {
            fFloatValue2 = -fFloatValue2;
        }
        return TuplesKt.to(Float.valueOf(fFloatValue), Float.valueOf(fFloatValue2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:92:? A[RETURN, SYNTHETIC] */
    /* renamed from: AppBar-celAv9A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1164AppBarcelAv9A(final long r24, final long r26, final float r28, final androidx.compose.foundation.layout.PaddingValues r29, final androidx.compose.ui.graphics.Shape r30, androidx.compose.ui.Modifier r31, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, androidx.compose.runtime.Composer r33, final int r34, final int r35) {
        /*
            Method dump skipped, instructions count: 346
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.AppBarKt.m1164AppBarcelAv9A(long, long, float, androidx.compose.foundation.layout.PaddingValues, androidx.compose.ui.graphics.Shape, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    static {
        float f = 4;
        float fM4390constructorimpl = Dp.m4390constructorimpl(f);
        AppBarHorizontalPadding = fM4390constructorimpl;
        TitleInsetWithoutIcon = SizeKt.m738width3ABfNKs(Modifier.INSTANCE, Dp.m4390constructorimpl(Dp.m4390constructorimpl(16) - fM4390constructorimpl));
        TitleIconModifier = SizeKt.m738width3ABfNKs(SizeKt.fillMaxHeight$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m4390constructorimpl(Dp.m4390constructorimpl(72) - fM4390constructorimpl));
        BottomAppBarCutoutOffset = Dp.m4390constructorimpl(8);
        BottomAppBarRoundedEdgeRadius = Dp.m4390constructorimpl(f);
    }
}
