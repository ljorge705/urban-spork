package androidx.compose.material;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Dp;
import com.BV.LinearGradient.LinearGradientManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import kotlin.Metadata;

/* compiled from: TextFieldImpl.kt */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aH\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\u0011\u0010$\u001a\r\u0012\u0004\u0012\u00020\u001d0%¢\u0006\u0002\b&H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010(\u001añ\u0001\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u00182\u0006\u0010-\u001a\u00020\u00182\u0006\u0010.\u001a\u00020/2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020/\u0012\u0004\u0012\u00020\u001d012\u0006\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00020\u00182\u0006\u00104\u001a\u00020!2\u0013\u00105\u001a\u000f\u0012\u0004\u0012\u00020\u001d\u0018\u00010%¢\u0006\u0002\b&2\u0013\u00106\u001a\u000f\u0012\u0004\u0012\u00020\u001d\u0018\u00010%¢\u0006\u0002\b&2\u0013\u00107\u001a\u000f\u0012\u0004\u0012\u00020\u001d\u0018\u00010%¢\u0006\u0002\b&2\u0013\u00108\u001a\u000f\u0012\u0004\u0012\u00020\u001d\u0018\u00010%¢\u0006\u0002\b&2\u0006\u00109\u001a\u00020\u00182\u0006\u0010:\u001a\u00020;2\b\b\u0002\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u00020\u00012\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0001¢\u0006\u0002\u0010G\u001a\u0012\u0010H\u001a\u00020\u00012\b\u0010I\u001a\u0004\u0018\u00010\u0019H\u0000\u001a\u0012\u0010J\u001a\u00020\u00012\b\u0010I\u001a\u0004\u0018\u00010\u0019H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0019\u0010\u0002\u001a\u00020\u0003X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\"\u0013\u0010\u000b\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0006\"\u0013\u0010\f\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0006\"\u000e\u0010\r\u001a\u00020\u000eX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u000eX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u000eX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u000eX\u0080T¢\u0006\u0002\n\u0000\"\u0019\u0010\u0014\u001a\u00020\u0003X\u0080\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0015\u0010\u0005\"\u000e\u0010\u0016\u001a\u00020\u000eX\u0080T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0017\u001a\u00020\u0018*\u00020\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006K"}, d2 = {"AnimationDuration", "", "HorizontalIconPadding", "Landroidx/compose/ui/unit/Dp;", "getHorizontalIconPadding", "()F", "F", "IconDefaultSizeModifier", "Landroidx/compose/ui/Modifier;", "getIconDefaultSizeModifier", "()Landroidx/compose/ui/Modifier;", "IndicatorFocusedWidth", "IndicatorUnfocusedWidth", "LabelId", "", "LeadingId", "PlaceholderAnimationDelayOrDuration", "PlaceholderAnimationDuration", "PlaceholderId", "TextFieldId", "TextFieldPadding", "getTextFieldPadding", "TrailingId", "nonZero", "", "Landroidx/compose/ui/layout/Placeable;", "getNonZero", "(Landroidx/compose/ui/layout/Placeable;)Z", "Decoration", "", "contentColor", "Landroidx/compose/ui/graphics/Color;", "typography", "Landroidx/compose/ui/text/TextStyle;", "contentAlpha", "", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Decoration-euL9pac", "(JLandroidx/compose/ui/text/TextStyle;Ljava/lang/Float;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "TextFieldImpl", "type", "Landroidx/compose/material/TextFieldType;", ViewProps.ENABLED, "readOnly", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "modifier", "singleLine", "textStyle", "label", ReactTextInputShadowNode.PROP_PLACEHOLDER, "leading", "trailing", "isError", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "maxLines", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "shape", "Landroidx/compose/ui/graphics/Shape;", LinearGradientManager.PROP_COLORS, "Landroidx/compose/material/TextFieldColors;", "(Landroidx/compose/material/TextFieldType;ZZLandroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/runtime/Composer;III)V", "heightOrZero", "placeable", "widthOrZero", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldImplKt {
    public static final int AnimationDuration = 150;
    private static final Modifier IconDefaultSizeModifier;
    public static final String LabelId = "Label";
    public static final String LeadingId = "Leading";
    private static final int PlaceholderAnimationDelayOrDuration = 67;
    private static final int PlaceholderAnimationDuration = 83;
    public static final String PlaceholderId = "Hint";
    public static final String TextFieldId = "TextField";
    public static final String TrailingId = "Trailing";
    private static final float IndicatorUnfocusedWidth = Dp.m4390constructorimpl(1);
    private static final float IndicatorFocusedWidth = Dp.m4390constructorimpl(2);
    private static final float TextFieldPadding = Dp.m4390constructorimpl(16);
    private static final float HorizontalIconPadding = Dp.m4390constructorimpl(12);

    public static final float getHorizontalIconPadding() {
        return HorizontalIconPadding;
    }

    public static final Modifier getIconDefaultSizeModifier() {
        return IconDefaultSizeModifier;
    }

    public static final float getTextFieldPadding() {
        return TextFieldPadding;
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x03ca  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0442  */
    /* JADX WARN: Removed duplicated region for block: B:260:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x016d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TextFieldImpl(final androidx.compose.material.TextFieldType r58, final boolean r59, final boolean r60, final androidx.compose.ui.text.input.TextFieldValue r61, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r62, final androidx.compose.ui.Modifier r63, final boolean r64, final androidx.compose.ui.text.TextStyle r65, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r66, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r67, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r68, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r69, final boolean r70, final androidx.compose.ui.text.input.VisualTransformation r71, androidx.compose.foundation.text.KeyboardOptions r72, final androidx.compose.foundation.text.KeyboardActions r73, int r74, final androidx.compose.foundation.interaction.MutableInteractionSource r75, final androidx.compose.ui.graphics.Shape r76, final androidx.compose.material.TextFieldColors r77, androidx.compose.runtime.Composer r78, final int r79, final int r80, final int r81) {
        /*
            Method dump skipped, instructions count: 1151
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextFieldImplKt.TextFieldImpl(androidx.compose.material.TextFieldType, boolean, boolean, androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.material.TextFieldColors, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /* renamed from: Decoration-euL9pac, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1458DecorationeuL9pac(final long r15, androidx.compose.ui.text.TextStyle r17, java.lang.Float r18, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r19, androidx.compose.runtime.Composer r20, final int r21, final int r22) {
        /*
            Method dump skipped, instructions count: 251
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextFieldImplKt.m1458DecorationeuL9pac(long, androidx.compose.ui.text.TextStyle, java.lang.Float, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean getNonZero(Placeable placeable) {
        return (placeable.getWidth() == 0 && placeable.getHeight() == 0) ? false : true;
    }

    public static final int widthOrZero(Placeable placeable) {
        if (placeable == null) {
            return 0;
        }
        return placeable.getWidth();
    }

    public static final int heightOrZero(Placeable placeable) {
        if (placeable == null) {
            return 0;
        }
        return placeable.getHeight();
    }

    static {
        float f = 48;
        IconDefaultSizeModifier = SizeKt.m717defaultMinSizeVpY3zN4(Modifier.INSTANCE, Dp.m4390constructorimpl(f), Dp.m4390constructorimpl(f));
    }
}
