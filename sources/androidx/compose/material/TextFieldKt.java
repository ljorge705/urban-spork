package androidx.compose.material;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutIdParentData;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import com.BV.LinearGradient.LinearGradientManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: TextField.kt */
@Metadata(d1 = {"\u0000¤\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u001a¢\u0001\u0010\r\u001a\u00020\u000e2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u000e0\u0010¢\u0006\u0002\b\u00112\u0013\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0019\u0010\u0013\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0014¢\u0006\u0002\b\u00112\u0013\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0013\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010 \u001a\u0087\u0002\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u000e0\u00142\b\b\u0002\u0010%\u001a\u00020\u00152\b\b\u0002\u0010&\u001a\u00020\u00192\b\b\u0002\u0010'\u001a\u00020\u00192\b\b\u0002\u0010(\u001a\u00020)2\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010*\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010+\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010¢\u0006\u0002\b\u00112\b\b\u0002\u0010,\u001a\u00020\u00192\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u0002022\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u00020:H\u0007¢\u0006\u0002\u0010;\u001a\u0087\u0002\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020<2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020\u000e0\u00142\b\b\u0002\u0010%\u001a\u00020\u00152\b\b\u0002\u0010&\u001a\u00020\u00192\b\b\u0002\u0010'\u001a\u00020\u00192\b\b\u0002\u0010(\u001a\u00020)2\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010*\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010+\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010¢\u0006\u0002\b\u00112\b\b\u0002\u0010,\u001a\u00020\u00192\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u0002022\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u00020:H\u0007¢\u0006\u0002\u0010=\u001a\u009d\u0002\u0010>\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020#2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u000e0\u00142\u0006\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020\u00192\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u0010(\u001a\u00020)2\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u00103\u001a\u0002042\u0006\u0010-\u001a\u00020.2\u0006\u00105\u001a\u0002062\u0019\u0010?\u001a\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0014¢\u0006\u0002\b\u00112\u0013\u0010@\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0013\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0013\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010A\u001a\u00020\u001e2\u0006\u0010B\u001a\u00020\u00012\u0006\u0010C\u001a\u00020\u001b2\u0006\u0010D\u001a\u00020\u001b2\u0006\u0010E\u001a\u00020\u001b2\u0006\u00107\u001a\u000208H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bF\u0010G\u001aU\u0010H\u001a\u0002042\u0006\u0010I\u001a\u0002042\u0006\u0010J\u001a\u00020\u00192\u0006\u0010K\u001a\u0002042\u0006\u0010L\u001a\u0002042\u0006\u0010M\u001a\u0002042\u0006\u0010N\u001a\u0002042\u0006\u0010O\u001a\u00020\u00062\u0006\u0010P\u001a\u00020\u001eH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bQ\u0010R\u001aE\u0010S\u001a\u0002042\u0006\u0010T\u001a\u0002042\u0006\u0010U\u001a\u0002042\u0006\u0010V\u001a\u0002042\u0006\u0010W\u001a\u0002042\u0006\u0010X\u001a\u0002042\u0006\u0010O\u001a\u00020\u0006H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bY\u0010Z\u001a)\u0010[\u001a\u00020\u0015*\u00020\u00152\u0006\u0010\\\u001a\u00020\u00012\u0006\u0010]\u001a\u00020\u001bH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b^\u0010_\u001at\u0010`\u001a\u00020\u000e*\u00020a2\u0006\u0010b\u001a\u0002042\u0006\u0010c\u001a\u0002042\u0006\u0010d\u001a\u00020e2\b\u0010f\u001a\u0004\u0018\u00010e2\b\u0010g\u001a\u0004\u0018\u00010e2\b\u0010h\u001a\u0004\u0018\u00010e2\b\u0010i\u001a\u0004\u0018\u00010e2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010j\u001a\u0002042\u0006\u0010k\u001a\u0002042\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010P\u001a\u00020\u001eH\u0002\u001aR\u0010l\u001a\u00020\u000e*\u00020a2\u0006\u0010b\u001a\u0002042\u0006\u0010c\u001a\u0002042\u0006\u0010m\u001a\u00020e2\b\u0010g\u001a\u0004\u0018\u00010e2\b\u0010h\u001a\u0004\u0018\u00010e2\b\u0010i\u001a\u0004\u0018\u00010e2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010P\u001a\u00020\u001eH\u0002\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0003\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0004\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0005\u001a\u00020\u0006X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0007\"\u001a\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006n"}, d2 = {"FirstBaselineOffset", "Landroidx/compose/ui/unit/Dp;", "F", "LastBaselineOffset", "TextFieldTopPadding", "ZeroConstraints", "Landroidx/compose/ui/unit/Constraints;", "J", "layoutId", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "getLayoutId", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;)Ljava/lang/Object;", "IconsWithTextFieldLayout", "", "textField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "label", ReactTextInputShadowNode.PROP_PLACEHOLDER, "Lkotlin/Function1;", "Landroidx/compose/ui/Modifier;", "leading", "trailing", "singleLine", "", "leadingColor", "Landroidx/compose/ui/graphics/Color;", "trailingColor", "animationProgress", "", "IconsWithTextFieldLayout-SxpAMN0", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZJJFLandroidx/compose/runtime/Composer;I)V", TextFieldImplKt.TextFieldId, "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "modifier", ViewProps.ENABLED, "readOnly", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "leadingIcon", "trailingIcon", "isError", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "maxLines", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "shape", "Landroidx/compose/ui/graphics/Shape;", LinearGradientManager.PROP_COLORS, "Landroidx/compose/material/TextFieldColors;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/runtime/Composer;III)V", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/runtime/Composer;III)V", "TextFieldLayout", "decoratedPlaceholder", "decoratedLabel", "labelProgress", "indicatorWidth", "indicatorColor", ViewProps.BACKGROUND_COLOR, "cursorColor", "TextFieldLayout-uBqXD2s", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;ZZLandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;Landroidx/compose/ui/text/TextStyle;ZILandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;JJFFJJJLandroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;IIII)V", "calculateHeight", "textFieldHeight", "hasLabel", "labelBaseline", "leadingHeight", "trailingHeight", "placeholderHeight", CryptoServicesPermission.CONSTRAINTS, "density", "calculateHeight-YbqEFUw", "(IZIIIIJF)I", "calculateWidth", "leadingWidth", "trailingWidth", "textFieldWidth", "labelWidth", "placeholderWidth", "calculateWidth-VsPV1Ek", "(IIIIIJ)I", "drawIndicatorLine", "lineWidth", "color", "drawIndicatorLine-H2RKhps", "(Landroidx/compose/ui/Modifier;FJ)Landroidx/compose/ui/Modifier;", "placeWithLabel", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "width", "height", "textfieldPlaceable", "Landroidx/compose/ui/layout/Placeable;", "labelPlaceable", "placeholderPlaceable", "leadingPlaceable", "trailingPlaceable", "labelEndPosition", "textPosition", "placeWithoutLabel", "textPlaceable", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldKt {
    private static final float FirstBaselineOffset = Dp.m4390constructorimpl(20);
    private static final float LastBaselineOffset = Dp.m4390constructorimpl(10);
    private static final float TextFieldTopPadding = Dp.m4390constructorimpl(4);
    private static final long ZeroConstraints = ConstraintsKt.Constraints(0, 0, 0, 0);

    /* JADX WARN: Removed duplicated region for block: B:107:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0309  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x033e  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0356  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0376  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0398  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x03d4  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x041a  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x045b  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x048c  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x04b1  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x04fa  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0590  */
    /* JADX WARN: Removed duplicated region for block: B:307:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0138  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TextField(final java.lang.String r74, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r75, androidx.compose.ui.Modifier r76, boolean r77, boolean r78, androidx.compose.ui.text.TextStyle r79, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r80, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r81, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r82, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r83, boolean r84, androidx.compose.ui.text.input.VisualTransformation r85, androidx.compose.foundation.text.KeyboardOptions r86, androidx.compose.foundation.text.KeyboardActions r87, boolean r88, int r89, androidx.compose.foundation.interaction.MutableInteractionSource r90, androidx.compose.ui.graphics.Shape r91, androidx.compose.material.TextFieldColors r92, androidx.compose.runtime.Composer r93, final int r94, final int r95, final int r96) {
        /*
            Method dump skipped, instructions count: 1453
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextFieldKt.TextField(java.lang.String, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.material.TextFieldColors, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* renamed from: TextField$lambda-2, reason: not valid java name */
    private static final TextFieldValue m1461TextField$lambda2(MutableState<TextFieldValue> mutableState) {
        return mutableState.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x030d  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x037b  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x039b  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0458  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x051d  */
    /* JADX WARN: Removed duplicated region for block: B:301:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0136  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TextField(final androidx.compose.ui.text.input.TextFieldValue r72, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r73, androidx.compose.ui.Modifier r74, boolean r75, boolean r76, androidx.compose.ui.text.TextStyle r77, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r78, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r79, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r80, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r81, boolean r82, androidx.compose.ui.text.input.VisualTransformation r83, androidx.compose.foundation.text.KeyboardOptions r84, androidx.compose.foundation.text.KeyboardActions r85, boolean r86, int r87, androidx.compose.foundation.interaction.MutableInteractionSource r88, androidx.compose.ui.graphics.Shape r89, androidx.compose.material.TextFieldColors r90, androidx.compose.runtime.Composer r91, final int r92, final int r93, final int r94) {
        /*
            Method dump skipped, instructions count: 1338
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextFieldKt.TextField(androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.material.TextFieldColors, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x03f2  */
    /* JADX WARN: Removed duplicated region for block: B:270:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0170  */
    /* renamed from: TextFieldLayout-uBqXD2s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1463TextFieldLayoutuBqXD2s(final androidx.compose.ui.Modifier r44, final androidx.compose.ui.text.input.TextFieldValue r45, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r46, final boolean r47, final boolean r48, final androidx.compose.foundation.text.KeyboardOptions r49, final androidx.compose.foundation.text.KeyboardActions r50, final androidx.compose.ui.text.TextStyle r51, final boolean r52, int r53, final androidx.compose.ui.text.input.VisualTransformation r54, final androidx.compose.foundation.interaction.MutableInteractionSource r55, final kotlin.jvm.functions.Function3<? super androidx.compose.ui.Modifier, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r56, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r57, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r58, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r59, final long r60, final long r62, final float r64, final float r65, final long r66, final long r68, final long r70, final androidx.compose.ui.graphics.Shape r72, androidx.compose.runtime.Composer r73, final int r74, final int r75, final int r76, final int r77) {
        /*
            Method dump skipped, instructions count: 1083
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TextFieldKt.m1463TextFieldLayoutuBqXD2s(androidx.compose.ui.Modifier, androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, boolean, boolean, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, androidx.compose.ui.text.TextStyle, boolean, int, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, long, long, float, float, long, long, long, androidx.compose.ui.graphics.Shape, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: IconsWithTextFieldLayout-SxpAMN0, reason: not valid java name */
    public static final void m1460IconsWithTextFieldLayoutSxpAMN0(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final Function3<? super Modifier, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final boolean z, final long j, final long j2, final float f, Composer composer, final int i) {
        int i2;
        int i3;
        String str;
        int i4;
        String str2;
        String str3;
        String str4;
        Composer composer2;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        Composer composerStartRestartGroup = composer.startRestartGroup(178502586);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(IconsWithTextFieldLayout)P(6,1,4,2,7,5,3:c#ui.graphics.Color,8:c#ui.graphics.Color)400@18860L109,403@18974L1634:TextField.kt#jmzs0o");
        if ((i & 14) == 0) {
            i2 = (composerStartRestartGroup.changed(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerStartRestartGroup.changed(function22) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerStartRestartGroup.changed(function3) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i2 |= composerStartRestartGroup.changed(function23) ? 2048 : 1024;
        }
        if ((57344 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(function24) ? 16384 : 8192;
        }
        if ((458752 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 131072 : 65536;
        }
        if ((3670016 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 1048576 : 524288;
        }
        if ((29360128 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(j2) ? 8388608 : 4194304;
        }
        if ((234881024 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(f) ? 67108864 : 33554432;
        }
        int i5 = i2;
        if (((191739611 & i5) ^ 38347922) != 0 || !composerStartRestartGroup.getSkipping()) {
            Boolean boolValueOf = Boolean.valueOf(z);
            Float fValueOf = Float.valueOf(f);
            int i6 = i5 >> 21;
            composerStartRestartGroup.startReplaceableGroup(-3686552);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember)P(1,2):Composables.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(boolValueOf) | composerStartRestartGroup.changed(fValueOf);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new TextFieldMeasurePolicy(z, f);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            TextFieldMeasurePolicy textFieldMeasurePolicy = (TextFieldMeasurePolicy) objRememberedValue;
            composerStartRestartGroup.startReplaceableGroup(1376089335);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
            Modifier.Companion companion = Modifier.INSTANCE;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Density density = (Density) objConsume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            LayoutDirection layoutDirection = (LayoutDirection) objConsume2;
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf = LayoutKt.materializerOf(companion);
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerStartRestartGroup.disableReusing();
            Composer composerM1518constructorimpl = Updater.m1518constructorimpl(composerStartRestartGroup);
            Updater.m1525setimpl(composerM1518constructorimpl, textFieldMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m1525setimpl(composerM1518constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m1525setimpl(composerM1518constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            composerStartRestartGroup.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            composerStartRestartGroup.startReplaceableGroup(-804088982);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C439@20370L183:TextField.kt#jmzs0o");
            if (function23 != null) {
                composerStartRestartGroup.startReplaceableGroup(-804088961);
                ComposerKt.sourceInformation(composerStartRestartGroup, "406@19053L338");
                Modifier modifierThen = LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.LeadingId).then(TextFieldImplKt.getIconDefaultSizeModifier());
                Alignment center = Alignment.INSTANCE.getCenter();
                composerStartRestartGroup.startReplaceableGroup(-1990474327);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 0);
                composerStartRestartGroup.startReplaceableGroup(1376089335);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh");
                ProvidableCompositionLocal<Density> localDensity2 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object objConsume3 = composerStartRestartGroup.consume(localDensity2);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                Density density2 = (Density) objConsume3;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 103361330, "C:CompositionLocal.kt#9igjgp");
                Object objConsume4 = composerStartRestartGroup.consume(localLayoutDirection2);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                LayoutDirection layoutDirection2 = (LayoutDirection) objConsume4;
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf2 = LayoutKt.materializerOf(modifierThen);
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerStartRestartGroup.disableReusing();
                Composer composerM1518constructorimpl2 = Updater.m1518constructorimpl(composerStartRestartGroup);
                Updater.m1525setimpl(composerM1518constructorimpl2, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m1525setimpl(composerM1518constructorimpl2, density2, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m1525setimpl(composerM1518constructorimpl2, layoutDirection2, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                composerStartRestartGroup.enableReusing();
                function3MaterializerOf2.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
                composerStartRestartGroup.startReplaceableGroup(2058660585);
                composerStartRestartGroup.startReplaceableGroup(-1253629305);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C72@3384L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                composerStartRestartGroup.startReplaceableGroup(-447676302);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C410@19245L128:TextField.kt#jmzs0o");
                str = "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh";
                i3 = -1990474327;
                i4 = i5;
                str2 = "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo";
                str3 = "C:CompositionLocal.kt#9igjgp";
                str4 = "C72@3384L9:Box.kt#2w3rfo";
                composer2 = composerStartRestartGroup;
                TextFieldImplKt.m1458DecorationeuL9pac(j, null, null, function23, composerStartRestartGroup, ((i5 >> 18) & 14) | (i5 & 7168), 6);
                composer2.endReplaceableGroup();
                composer2.endReplaceableGroup();
                composer2.endReplaceableGroup();
                composer2.endNode();
                composer2.endReplaceableGroup();
                composer2.endReplaceableGroup();
                composer2.endReplaceableGroup();
            } else {
                i3 = -1990474327;
                str = "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh";
                i4 = i5;
                str2 = "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo";
                str3 = "C:CompositionLocal.kt#9igjgp";
                str4 = "C72@3384L9:Box.kt#2w3rfo";
                composer2 = composerStartRestartGroup;
                composer2.startReplaceableGroup(-804088591);
                composer2.endReplaceableGroup();
            }
            if (function24 != null) {
                composer2.startReplaceableGroup(-804088556);
                ComposerKt.sourceInformation(composer2, "417@19458L341");
                Modifier modifierThen2 = LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.TrailingId).then(TextFieldImplKt.getIconDefaultSizeModifier());
                Alignment center2 = Alignment.INSTANCE.getCenter();
                composer2.startReplaceableGroup(i3);
                String str11 = str2;
                ComposerKt.sourceInformation(composer2, str11);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center2, false, composer2, 0);
                composer2.startReplaceableGroup(1376089335);
                String str12 = str;
                ComposerKt.sourceInformation(composer2, str12);
                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                String str13 = str3;
                ComposerKt.sourceInformationMarkerStart(composer2, 103361330, str13);
                Object objConsume5 = composer2.consume(localDensity3);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                Density density3 = (Density) objConsume5;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composer2, 103361330, str13);
                Object objConsume6 = composer2.consume(localLayoutDirection3);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                LayoutDirection layoutDirection3 = (LayoutDirection) objConsume6;
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf3 = LayoutKt.materializerOf(modifierThen2);
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor3);
                } else {
                    composer2.useNode();
                }
                composer2.disableReusing();
                Composer composerM1518constructorimpl3 = Updater.m1518constructorimpl(composer2);
                Updater.m1525setimpl(composerM1518constructorimpl3, measurePolicyRememberBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m1525setimpl(composerM1518constructorimpl3, density3, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m1525setimpl(composerM1518constructorimpl3, layoutDirection3, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                composer2.enableReusing();
                function3MaterializerOf3.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composer2)), composer2, 0);
                composer2.startReplaceableGroup(2058660585);
                composer2.startReplaceableGroup(-1253629305);
                String str14 = str4;
                ComposerKt.sourceInformation(composer2, str14);
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                composer2.startReplaceableGroup(-447675896);
                ComposerKt.sourceInformation(composer2, "C421@19651L130:TextField.kt#jmzs0o");
                str8 = str14;
                str7 = str13;
                str5 = str12;
                str6 = str11;
                TextFieldImplKt.m1458DecorationeuL9pac(j2, null, null, function24, composer2, (i6 & 14) | ((i4 >> 3) & 7168), 6);
                composer2.endReplaceableGroup();
                composer2.endReplaceableGroup();
                composer2.endReplaceableGroup();
                composer2.endNode();
                composer2.endReplaceableGroup();
                composer2.endReplaceableGroup();
                composer2.endReplaceableGroup();
            } else {
                str5 = str;
                str6 = str2;
                str7 = str3;
                str8 = str4;
                composer2.startReplaceableGroup(-804088183);
                composer2.endReplaceableGroup();
            }
            float fM4390constructorimpl = Dp.m4390constructorimpl(TextFieldImplKt.getTextFieldPadding() - TextFieldImplKt.getHorizontalIconPadding());
            Modifier.Companion companion2 = Modifier.INSTANCE;
            float textFieldPadding = function23 != null ? fM4390constructorimpl : TextFieldImplKt.getTextFieldPadding();
            if (function24 == null) {
                fM4390constructorimpl = TextFieldImplKt.getTextFieldPadding();
            }
            Modifier modifierM694paddingqDBjuR0$default = PaddingKt.m694paddingqDBjuR0$default(companion2, textFieldPadding, 0.0f, fM4390constructorimpl, 0.0f, 10, null);
            if (function3 != null) {
                composer2.startReplaceableGroup(-804087851);
                ComposerKt.sourceInformation(composer2, "434@20163L59");
                function3.invoke(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.PlaceholderId).then(modifierM694paddingqDBjuR0$default), composer2, Integer.valueOf((i4 >> 3) & 112));
                composer2.endReplaceableGroup();
            } else {
                composer2.startReplaceableGroup(-804087760);
                composer2.endReplaceableGroup();
            }
            if (function22 != null) {
                composer2.startReplaceableGroup(-804087728);
                ComposerKt.sourceInformation(composer2, "437@20286L57");
                Modifier modifierThen3 = LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.LabelId).then(modifierM694paddingqDBjuR0$default);
                composer2.startReplaceableGroup(-1990474327);
                ComposerKt.sourceInformation(composer2, str6);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy3 = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composer2, 0);
                composer2.startReplaceableGroup(1376089335);
                ComposerKt.sourceInformation(composer2, str5);
                ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
                str10 = str7;
                ComposerKt.sourceInformationMarkerStart(composer2, 103361330, str10);
                Object objConsume7 = composer2.consume(localDensity4);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                Density density4 = (Density) objConsume7;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection4 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composer2, 103361330, str10);
                Object objConsume8 = composer2.consume(localLayoutDirection4);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                LayoutDirection layoutDirection4 = (LayoutDirection) objConsume8;
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf4 = LayoutKt.materializerOf(modifierThen3);
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor4);
                } else {
                    composer2.useNode();
                }
                composer2.disableReusing();
                Composer composerM1518constructorimpl4 = Updater.m1518constructorimpl(composer2);
                Updater.m1525setimpl(composerM1518constructorimpl4, measurePolicyRememberBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m1525setimpl(composerM1518constructorimpl4, density4, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m1525setimpl(composerM1518constructorimpl4, layoutDirection4, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                composer2.enableReusing();
                function3MaterializerOf4.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composer2)), composer2, 0);
                composer2.startReplaceableGroup(2058660585);
                composer2.startReplaceableGroup(-1253629305);
                str9 = str8;
                ComposerKt.sourceInformation(composer2, str9);
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                composer2.startReplaceableGroup(-447675213);
                ComposerKt.sourceInformation(composer2, "C437@20334L7:TextField.kt#jmzs0o");
                function22.invoke(composer2, Integer.valueOf((i4 >> 3) & 14));
                composer2.endReplaceableGroup();
                composer2.endReplaceableGroup();
                composer2.endReplaceableGroup();
                composer2.endNode();
                composer2.endReplaceableGroup();
                composer2.endReplaceableGroup();
                composer2.endReplaceableGroup();
            } else {
                str9 = str8;
                str10 = str7;
                composer2.startReplaceableGroup(-804087639);
                composer2.endReplaceableGroup();
            }
            Modifier modifierThen4 = LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.TextFieldId).then(modifierM694paddingqDBjuR0$default);
            composer2.startReplaceableGroup(-1990474327);
            ComposerKt.sourceInformation(composer2, str6);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy4 = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true, composer2, 48);
            composer2.startReplaceableGroup(1376089335);
            ComposerKt.sourceInformation(composer2, str5);
            ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composer2, 103361330, str10);
            Object objConsume9 = composer2.consume(localDensity5);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            Density density5 = (Density) objConsume9;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection5 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composer2, 103361330, str10);
            Object objConsume10 = composer2.consume(localLayoutDirection5);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            LayoutDirection layoutDirection5 = (LayoutDirection) objConsume10;
            Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf5 = LayoutKt.materializerOf(modifierThen4);
            if (!(composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor5);
            } else {
                composer2.useNode();
            }
            composer2.disableReusing();
            Composer composerM1518constructorimpl5 = Updater.m1518constructorimpl(composer2);
            Updater.m1525setimpl(composerM1518constructorimpl5, measurePolicyRememberBoxMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m1525setimpl(composerM1518constructorimpl5, density5, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m1525setimpl(composerM1518constructorimpl5, layoutDirection5, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            composer2.enableReusing();
            function3MaterializerOf5.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composer2)), composer2, 0);
            composer2.startReplaceableGroup(2058660585);
            composer2.startReplaceableGroup(-1253629305);
            ComposerKt.sourceInformation(composer2, str9);
            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
            composer2.startReplaceableGroup(-447675019);
            ComposerKt.sourceInformation(composer2, "C443@20528L11:TextField.kt#jmzs0o");
            function2.invoke(composer2, Integer.valueOf(i4 & 14));
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TextFieldKt$IconsWithTextFieldLayout$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                invoke(composer3, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer3, int i7) {
                TextFieldKt.m1460IconsWithTextFieldLayoutSxpAMN0(function2, function22, function3, function23, function24, z, j, j2, f, composer3, i | 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculateWidth-VsPV1Ek, reason: not valid java name */
    public static final int m1469calculateWidthVsPV1Ek(int i, int i2, int i3, int i4, int i5, long j) {
        return Math.max(i + Math.max(i3, Math.max(i4, i5)) + i2, Constraints.m4348getMinWidthimpl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculateHeight-YbqEFUw, reason: not valid java name */
    public static final int m1468calculateHeightYbqEFUw(int i, boolean z, int i2, int i3, int i4, int i5, long j, float f) {
        float f2 = LastBaselineOffset * f;
        float f3 = TextFieldTopPadding * f;
        float textFieldPadding = TextFieldImplKt.getTextFieldPadding() * f;
        int iMax = Math.max(i, i5);
        return Math.max(MathKt.roundToInt(z ? i2 + f3 + iMax + f2 : (textFieldPadding * 2) + iMax), Math.max(Math.max(i3, i4), Constraints.m4347getMinHeightimpl(j)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeWithLabel(Placeable.PlacementScope placementScope, int i, int i2, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, boolean z, int i3, int i4, float f, float f2) {
        int iRoundToInt = MathKt.roundToInt(TextFieldImplKt.getTextFieldPadding() * f2);
        if (placeable4 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, 0, Alignment.INSTANCE.getCenterVertically().align(placeable4.getHeight(), i2), 0.0f, 4, null);
        }
        if (placeable5 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable5, i - placeable5.getWidth(), Alignment.INSTANCE.getCenterVertically().align(placeable5.getHeight(), i2), 0.0f, 4, null);
        }
        if (placeable2 != null) {
            if (z) {
                iRoundToInt = Alignment.INSTANCE.getCenterVertically().align(placeable2.getHeight(), i2);
            }
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, TextFieldImplKt.widthOrZero(placeable4), iRoundToInt - MathKt.roundToInt((iRoundToInt - i3) * f), 0.0f, 4, null);
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, TextFieldImplKt.widthOrZero(placeable4), i4, 0.0f, 4, null);
        if (placeable3 == null) {
            return;
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, TextFieldImplKt.widthOrZero(placeable4), i4, 0.0f, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeWithoutLabel(Placeable.PlacementScope placementScope, int i, int i2, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, boolean z, float f) {
        int iRoundToInt = MathKt.roundToInt(TextFieldImplKt.getTextFieldPadding() * f);
        if (placeable3 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, 0, Alignment.INSTANCE.getCenterVertically().align(placeable3.getHeight(), i2), 0.0f, 4, null);
        }
        if (placeable4 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, i - placeable4.getWidth(), Alignment.INSTANCE.getCenterVertically().align(placeable4.getHeight(), i2), 0.0f, 4, null);
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, TextFieldImplKt.widthOrZero(placeable3), z ? Alignment.INSTANCE.getCenterVertically().align(placeable.getHeight(), i2) : iRoundToInt, 0.0f, 4, null);
        if (placeable2 == null) {
            return;
        }
        if (z) {
            iRoundToInt = Alignment.INSTANCE.getCenterVertically().align(placeable2.getHeight(), i2);
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, TextFieldImplKt.widthOrZero(placeable3), iRoundToInt, 0.0f, 4, null);
    }

    /* renamed from: drawIndicatorLine-H2RKhps, reason: not valid java name */
    public static final Modifier m1470drawIndicatorLineH2RKhps(Modifier drawIndicatorLine, final float f, final long j) {
        Intrinsics.checkNotNullParameter(drawIndicatorLine, "$this$drawIndicatorLine");
        return DrawModifierKt.drawBehind(drawIndicatorLine, new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.TextFieldKt$drawIndicatorLine$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                invoke2(drawScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(DrawScope drawBehind) {
                Intrinsics.checkNotNullParameter(drawBehind, "$this$drawBehind");
                float density = f * drawBehind.getDensity();
                float fM1705getHeightimpl = Size.m1705getHeightimpl(drawBehind.mo2417getSizeNHjbRc()) - (density / 2);
                DrawScope.m2404drawLineNGM6Ib0$default(drawBehind, j, OffsetKt.Offset(0.0f, fM1705getHeightimpl), OffsetKt.Offset(Size.m1708getWidthimpl(drawBehind.mo2417getSizeNHjbRc()), fM1705getHeightimpl), density, 0, null, 0.0f, null, 0, 496, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getLayoutId(IntrinsicMeasurable intrinsicMeasurable) {
        Object parentData = intrinsicMeasurable.getParentData();
        LayoutIdParentData layoutIdParentData = parentData instanceof LayoutIdParentData ? (LayoutIdParentData) parentData : null;
        if (layoutIdParentData == null) {
            return null;
        }
        return layoutIdParentData.getLayoutId();
    }
}
