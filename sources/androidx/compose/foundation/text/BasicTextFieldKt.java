package androidx.compose.foundation.text;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.text.input.TextFieldValue;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: BasicTextField.kt */
@Metadata(d1 = {"\u0000l\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u001aâ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b23\b\u0002\u0010\u001c\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u001d¢\u0006\u0002\b\u001e¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u001eH\u0007¢\u0006\u0002\u0010\"\u001aì\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010#\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b23\b\u0002\u0010\u001c\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u001d¢\u0006\u0002\b\u001e¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u001eH\u0007¢\u0006\u0002\u0010$\u001aâ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020%2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b23\b\u0002\u0010\u001c\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u001d¢\u0006\u0002\b\u001e¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u001eH\u0007¢\u0006\u0002\u0010&\u001aì\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020%2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010#\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b23\b\u0002\u0010\u001c\u001a-\u0012\u001e\u0012\u001c\u0012\u0004\u0012\u00020\u00010\u001d¢\u0006\u0002\b\u001e¢\u0006\f\b\u001f\u0012\b\b \u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u001eH\u0007¢\u0006\u0002\u0010'¨\u0006("}, d2 = {"BasicTextField", "", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", ViewProps.ENABLED, "", "readOnly", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "singleLine", "maxLines", "", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "onTextLayout", "Landroidx/compose/ui/text/TextLayoutResult;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "decorationBox", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ParameterName;", "name", "innerTextField", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "minLines", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Brush;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BasicTextFieldKt {
    /* JADX WARN: Removed duplicated region for block: B:105:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x03e4  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x042a  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0431  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0434  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x04c2  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x04dc  */
    /* JADX WARN: Removed duplicated region for block: B:274:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0138  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicTextField(final java.lang.String r40, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r41, androidx.compose.ui.Modifier r42, boolean r43, boolean r44, androidx.compose.ui.text.TextStyle r45, androidx.compose.foundation.text.KeyboardOptions r46, androidx.compose.foundation.text.KeyboardActions r47, boolean r48, int r49, int r50, androidx.compose.ui.text.input.VisualTransformation r51, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r52, androidx.compose.foundation.interaction.MutableInteractionSource r53, androidx.compose.ui.graphics.Brush r54, kotlin.jvm.functions.Function3<? super kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r55, androidx.compose.runtime.Composer r56, final int r57, final int r58, final int r59) {
        /*
            Method dump skipped, instructions count: 1297
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(java.lang.String, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue BasicTextField$lambda$2(MutableState<TextFieldValue> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String BasicTextField$lambda$6(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0375  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x039d  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:260:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0138  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicTextField(final androidx.compose.ui.text.input.TextFieldValue r36, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r37, androidx.compose.ui.Modifier r38, boolean r39, boolean r40, androidx.compose.ui.text.TextStyle r41, androidx.compose.foundation.text.KeyboardOptions r42, androidx.compose.foundation.text.KeyboardActions r43, boolean r44, int r45, int r46, androidx.compose.ui.text.input.VisualTransformation r47, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.TextLayoutResult, kotlin.Unit> r48, androidx.compose.foundation.interaction.MutableInteractionSource r49, androidx.compose.ui.graphics.Brush r50, kotlin.jvm.functions.Function3<? super kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r51, androidx.compose.runtime.Composer r52, final int r53, final int r54, final int r55) {
        /*
            Method dump skipped, instructions count: 1093
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:220:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x013d  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void BasicTextField(final java.lang.String r37, final kotlin.jvm.functions.Function1 r38, androidx.compose.ui.Modifier r39, boolean r40, boolean r41, androidx.compose.ui.text.TextStyle r42, androidx.compose.foundation.text.KeyboardOptions r43, androidx.compose.foundation.text.KeyboardActions r44, boolean r45, int r46, androidx.compose.ui.text.input.VisualTransformation r47, kotlin.jvm.functions.Function1 r48, androidx.compose.foundation.interaction.MutableInteractionSource r49, androidx.compose.ui.graphics.Brush r50, kotlin.jvm.functions.Function3 r51, androidx.compose.runtime.Composer r52, final int r53, final int r54, final int r55) {
        /*
            Method dump skipped, instructions count: 899
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(java.lang.String, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:220:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x013d  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void BasicTextField(final androidx.compose.ui.text.input.TextFieldValue r37, final kotlin.jvm.functions.Function1 r38, androidx.compose.ui.Modifier r39, boolean r40, boolean r41, androidx.compose.ui.text.TextStyle r42, androidx.compose.foundation.text.KeyboardOptions r43, androidx.compose.foundation.text.KeyboardActions r44, boolean r45, int r46, androidx.compose.ui.text.input.VisualTransformation r47, kotlin.jvm.functions.Function1 r48, androidx.compose.foundation.interaction.MutableInteractionSource r49, androidx.compose.ui.graphics.Brush r50, kotlin.jvm.functions.Function3 r51, androidx.compose.runtime.Composer r52, final int r53, final int r54, final int r55) {
        /*
            Method dump skipped, instructions count: 899
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.BasicTextFieldKt.BasicTextField(androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function1, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Brush, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
