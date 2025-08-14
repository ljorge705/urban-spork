package androidx.compose.material;

import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.unit.Dp;
import com.BV.LinearGradient.LinearGradientManager;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: RadioButton.kt */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aM\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001a)\u0010\u0018\u001a\u00020\u000b*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0003H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u001e\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0013\u0010\u0002\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0013\u0010\u0005\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0013\u0010\u0006\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0013\u0010\u0007\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0013\u0010\b\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\"\u0013\u0010\t\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0004\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001f"}, d2 = {"RadioAnimationDuration", "", "RadioButtonDotSize", "Landroidx/compose/ui/unit/Dp;", "F", "RadioButtonPadding", "RadioButtonRippleRadius", "RadioButtonSize", "RadioRadius", "RadioStrokeWidth", "RadioButton", "", "selected", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", ViewProps.ENABLED, "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", LinearGradientManager.PROP_COLORS, "Landroidx/compose/material/RadioButtonColors;", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/material/RadioButtonColors;Landroidx/compose/runtime/Composer;II)V", "drawRadio", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "color", "Landroidx/compose/ui/graphics/Color;", "dotRadius", "drawRadio-Hht5A8o", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JF)V", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class RadioButtonKt {
    private static final int RadioAnimationDuration = 100;
    private static final float RadioButtonDotSize;
    private static final float RadioButtonPadding;
    private static final float RadioButtonRippleRadius = Dp.m4390constructorimpl(24);
    private static final float RadioButtonSize;
    private static final float RadioRadius;
    private static final float RadioStrokeWidth;

    /* JADX WARN: Removed duplicated region for block: B:101:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:118:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0127  */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void RadioButton(final boolean r21, final kotlin.jvm.functions.Function0<kotlin.Unit> r22, androidx.compose.ui.Modifier r23, boolean r24, androidx.compose.foundation.interaction.MutableInteractionSource r25, androidx.compose.material.RadioButtonColors r26, androidx.compose.runtime.Composer r27, final int r28, final int r29) {
        /*
            Method dump skipped, instructions count: 592
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.RadioButtonKt.RadioButton(boolean, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.material.RadioButtonColors, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawRadio-Hht5A8o, reason: not valid java name */
    public static final void m1392drawRadioHht5A8o(DrawScope drawScope, long j, float f) {
        float f2 = drawScope.mo577toPx0680j_4(RadioStrokeWidth);
        float f3 = f2 / 2;
        DrawScope.m2399drawCircleVaOC9Bg$default(drawScope, j, drawScope.mo577toPx0680j_4(RadioRadius) - f3, 0L, 0.0f, new Stroke(f2, 0.0f, 0, 0, null, 30, null), null, 0, 108, null);
        if (Dp.m4389compareTo0680j_4(f, Dp.m4390constructorimpl(0)) > 0) {
            DrawScope.m2399drawCircleVaOC9Bg$default(drawScope, j, drawScope.mo577toPx0680j_4(f) - f3, 0L, 0.0f, Fill.INSTANCE, null, 0, 108, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: RadioButton$lambda-1, reason: not valid java name */
    public static final float m1387RadioButton$lambda1(State<Dp> state) {
        return state.getValue().m4404unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: RadioButton$lambda-2, reason: not valid java name */
    public static final long m1388RadioButton$lambda2(State<Color> state) {
        return state.getValue().m1887unboximpl();
    }

    static {
        float f = 2;
        RadioButtonPadding = Dp.m4390constructorimpl(f);
        float fM4390constructorimpl = Dp.m4390constructorimpl(20);
        RadioButtonSize = fM4390constructorimpl;
        RadioRadius = Dp.m4390constructorimpl(fM4390constructorimpl / f);
        RadioButtonDotSize = Dp.m4390constructorimpl(12);
        RadioStrokeWidth = Dp.m4390constructorimpl(f);
    }
}
