package androidx.compose.material;

import androidx.compose.foundation.BorderKt;
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
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import com.BV.LinearGradient.LinearGradientManager;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.bouncycastle.crypto.CryptoServicesPermission;

/* compiled from: OutlinedTextField.kt */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aÖ\u0001\u0010\u0004\u001a\u00020\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0002\b\b2\u0019\u0010\t\u001a\u0015\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0005\u0018\u00010\n¢\u0006\u0002\b\b2\u0013\u0010\f\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u0017H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001d\u0010\u001e\u001a\u0087\u0002\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00050\n2\b\b\u0002\u0010#\u001a\u00020\u000b2\b\b\u0002\u0010$\u001a\u00020\u00102\b\b\u0002\u0010%\u001a\u00020\u00102\b\b\u0002\u0010&\u001a\u00020'2\u0015\b\u0002\u0010\f\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0015\b\u0002\u0010(\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0015\b\u0002\u0010)\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\b\b2\b\b\u0002\u0010*\u001a\u00020\u00102\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u00101\u001a\u0002022\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u00105\u001a\u000206H\u0007¢\u0006\u0002\u00107\u001a\u0087\u0002\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u0002082\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020\u00050\n2\b\b\u0002\u0010#\u001a\u00020\u000b2\b\b\u0002\u0010$\u001a\u00020\u00102\b\b\u0002\u0010%\u001a\u00020\u00102\b\b\u0002\u0010&\u001a\u00020'2\u0015\b\u0002\u0010\f\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0015\b\u0002\u0010(\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0015\b\u0002\u0010)\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\b\b2\b\b\u0002\u0010*\u001a\u00020\u00102\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u00101\u001a\u0002022\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u00105\u001a\u000206H\u0007¢\u0006\u0002\u00109\u001a\u0095\u0002\u0010:\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u00102\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u0010&\u001a\u00020'2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u00101\u001a\u0002022\u0006\u0010+\u001a\u00020,2\u0006\u00103\u001a\u0002042\u0019\u0010;\u001a\u0015\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0005\u0018\u00010\n¢\u0006\u0002\b\b2\u0013\u0010<\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010=\u001a\u00020\u00152\u0006\u0010>\u001a\u00020\u00012\u0006\u0010?\u001a\u00020\u00122\u0006\u0010@\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019H\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bA\u0010B\u001aU\u0010C\u001a\u0002022\b\u0010D\u001a\u0004\u0018\u00010E2\b\u0010F\u001a\u0004\u0018\u00010E2\u0006\u0010G\u001a\u00020E2\b\u0010H\u001a\u0004\u0018\u00010E2\b\u0010I\u001a\u0004\u0018\u00010E2\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020\u0015H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bM\u0010N\u001aM\u0010O\u001a\u0002022\b\u0010D\u001a\u0004\u0018\u00010E2\b\u0010F\u001a\u0004\u0018\u00010E2\u0006\u0010G\u001a\u00020E2\b\u0010H\u001a\u0004\u0018\u00010E2\b\u0010I\u001a\u0004\u0018\u00010E2\u0006\u0010J\u001a\u00020KH\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bP\u0010Q\u001a!\u0010R\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u0017H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bS\u0010T\u001a9\u0010U\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u0017H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\bV\u0010W\u001al\u0010X\u001a\u00020\u0005*\u00020Y2\u0006\u0010Z\u001a\u0002022\u0006\u0010[\u001a\u0002022\b\u0010D\u001a\u0004\u0018\u00010E2\b\u0010F\u001a\u0004\u0018\u00010E2\u0006\u0010G\u001a\u00020E2\b\u0010H\u001a\u0004\u0018\u00010E2\b\u0010I\u001a\u0004\u0018\u00010E2\u0006\u0010\\\u001a\u00020E2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010L\u001a\u00020\u0015H\u0002\"\u0013\u0010\u0000\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\"\u0013\u0010\u0003\u001a\u00020\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006]"}, d2 = {"OutlinedTextFieldInnerPadding", "Landroidx/compose/ui/unit/Dp;", "F", "OutlinedTextFieldTopPadding", "IconsWithTextFieldLayout", "", "textField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", ReactTextInputShadowNode.PROP_PLACEHOLDER, "Lkotlin/Function1;", "Landroidx/compose/ui/Modifier;", "label", "leading", "trailing", "singleLine", "", "leadingColor", "Landroidx/compose/ui/graphics/Color;", "trailingColor", "animationProgress", "", "onLabelMeasured", "Landroidx/compose/ui/geometry/Size;", "shape", "Landroidx/compose/ui/graphics/Shape;", ViewProps.BORDER_WIDTH, ViewProps.BORDER_COLOR, "labelSize", "IconsWithTextFieldLayout-T2E5_Oc", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZJJFLkotlin/jvm/functions/Function1;Landroidx/compose/ui/graphics/Shape;FJJLandroidx/compose/runtime/Composer;II)V", "OutlinedTextField", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "modifier", ViewProps.ENABLED, "readOnly", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "leadingIcon", "trailingIcon", "isError", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "maxLines", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", LinearGradientManager.PROP_COLORS, "Landroidx/compose/material/TextFieldColors;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/runtime/Composer;III)V", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material/TextFieldColors;Landroidx/compose/runtime/Composer;III)V", "OutlinedTextFieldLayout", "decoratedPlaceholder", "decoratedLabel", "labelProgress", "indicatorWidth", "indicatorColor", "cursorColor", "OutlinedTextFieldLayout-Sac-xI0", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;ZZLandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;Landroidx/compose/ui/text/TextStyle;ZILandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;JJFFJJLandroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;IIII)V", "calculateHeight", "leadingPlaceable", "Landroidx/compose/ui/layout/Placeable;", "trailingPlaceable", "textFieldPlaceable", "labelPlaceable", "placeholderPlaceable", CryptoServicesPermission.CONSTRAINTS, "Landroidx/compose/ui/unit/Constraints;", "density", "calculateHeight-MK6IjOU", "(Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JF)I", "calculateWidth", "calculateWidth-VsPV1Ek", "(Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;J)I", "outlineCutout", "outlineCutout-d16Qtg0", "(Landroidx/compose/ui/Modifier;J)Landroidx/compose/ui/Modifier;", "outlinedBorder", "outlinedBorder-gLEpSso", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;FJJ)Landroidx/compose/ui/Modifier;", "place", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "height", "width", "borderPlaceable", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class OutlinedTextFieldKt {
    private static final float OutlinedTextFieldInnerPadding = Dp.m4390constructorimpl(4);
    private static final float OutlinedTextFieldTopPadding = Dp.m4390constructorimpl(8);

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
    /* JADX WARN: Removed duplicated region for block: B:269:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x038d  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x039d  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x03e8  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0429  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x045a  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x047f  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x055e  */
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
    public static final void OutlinedTextField(final java.lang.String r74, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r75, androidx.compose.ui.Modifier r76, boolean r77, boolean r78, androidx.compose.ui.text.TextStyle r79, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r80, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r81, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r82, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r83, boolean r84, androidx.compose.ui.text.input.VisualTransformation r85, androidx.compose.foundation.text.KeyboardOptions r86, androidx.compose.foundation.text.KeyboardActions r87, boolean r88, int r89, androidx.compose.foundation.interaction.MutableInteractionSource r90, androidx.compose.ui.graphics.Shape r91, androidx.compose.material.TextFieldColors r92, androidx.compose.runtime.Composer r93, final int r94, final int r95, final int r96) {
        /*
            Method dump skipped, instructions count: 1403
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.OutlinedTextFieldKt.OutlinedTextField(java.lang.String, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.material.TextFieldColors, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* renamed from: OutlinedTextField$lambda-2, reason: not valid java name */
    private static final TextFieldValue m1344OutlinedTextField$lambda2(MutableState<TextFieldValue> mutableState) {
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
    /* JADX WARN: Removed duplicated region for block: B:289:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0437  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x04fc  */
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
    public static final void OutlinedTextField(final androidx.compose.ui.text.input.TextFieldValue r72, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r73, androidx.compose.ui.Modifier r74, boolean r75, boolean r76, androidx.compose.ui.text.TextStyle r77, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r78, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r79, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r80, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r81, boolean r82, androidx.compose.ui.text.input.VisualTransformation r83, androidx.compose.foundation.text.KeyboardOptions r84, androidx.compose.foundation.text.KeyboardActions r85, boolean r86, int r87, androidx.compose.foundation.interaction.MutableInteractionSource r88, androidx.compose.ui.graphics.Shape r89, androidx.compose.material.TextFieldColors r90, androidx.compose.runtime.Composer r91, final int r92, final int r93, final int r94) {
        /*
            Method dump skipped, instructions count: 1305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.OutlinedTextFieldKt.OutlinedTextField(androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.material.TextFieldColors, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:266:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x016d  */
    /* renamed from: OutlinedTextFieldLayout-Sac-xI0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1346OutlinedTextFieldLayoutSacxI0(final androidx.compose.ui.Modifier r39, final androidx.compose.ui.text.input.TextFieldValue r40, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r41, final boolean r42, final boolean r43, final androidx.compose.foundation.text.KeyboardOptions r44, final androidx.compose.foundation.text.KeyboardActions r45, final androidx.compose.ui.text.TextStyle r46, final boolean r47, int r48, final androidx.compose.ui.text.input.VisualTransformation r49, final androidx.compose.foundation.interaction.MutableInteractionSource r50, final kotlin.jvm.functions.Function3<? super androidx.compose.ui.Modifier, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r51, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r52, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r53, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r54, final long r55, final long r57, final float r59, final float r60, final long r61, final long r63, final androidx.compose.ui.graphics.Shape r65, androidx.compose.runtime.Composer r66, final int r67, final int r68, final int r69, final int r70) {
        /*
            Method dump skipped, instructions count: 1138
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.OutlinedTextFieldKt.m1346OutlinedTextFieldLayoutSacxI0(androidx.compose.ui.Modifier, androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, boolean, boolean, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, androidx.compose.ui.text.TextStyle, boolean, int, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, long, long, float, float, long, long, androidx.compose.ui.graphics.Shape, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: IconsWithTextFieldLayout-T2E5_Oc, reason: not valid java name */
    public static final void m1343IconsWithTextFieldLayoutT2E5_Oc(final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super Modifier, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final boolean z, final long j, final long j2, final float f, final Function1<? super Size, Unit> function1, final Shape shape, final float f2, final long j3, final long j4, Composer composer, final int i, final int i2) {
        int i3;
        int i4;
        int i5;
        String str;
        String str2;
        String str3;
        String str4;
        Composer composer2;
        String str5;
        String str6;
        Composer composer3;
        Composer composerStartRestartGroup = composer.startRestartGroup(178502320);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(IconsWithTextFieldLayout)P(11,8,3,5,12,10,6:c#ui.graphics.Color,13:c#ui.graphics.Color!1,7,9,2:c#ui.unit.Dp,1:c#ui.graphics.Color,4:c#ui.geometry.Size)391@18695L5641:OutlinedTextField.kt#jmzs0o");
        if ((i & 14) == 0) {
            i3 = (composerStartRestartGroup.changed(function2) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 112) == 0) {
            i3 |= composerStartRestartGroup.changed(function3) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i3 |= composerStartRestartGroup.changed(function22) ? 256 : 128;
        }
        if ((i & 7168) == 0) {
            i3 |= composerStartRestartGroup.changed(function23) ? 2048 : 1024;
        }
        if ((57344 & i) == 0) {
            i3 |= composerStartRestartGroup.changed(function24) ? 16384 : 8192;
        }
        if ((458752 & i) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 131072 : 65536;
        }
        if ((3670016 & i) == 0) {
            i3 |= composerStartRestartGroup.changed(j) ? 1048576 : 524288;
        }
        if ((i & 29360128) == 0) {
            i3 |= composerStartRestartGroup.changed(j2) ? 8388608 : 4194304;
        }
        if ((i & 234881024) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? 67108864 : 33554432;
        }
        if ((i & 1879048192) == 0) {
            i3 |= composerStartRestartGroup.changed(function1) ? 536870912 : 268435456;
        }
        if ((i2 & 14) == 0) {
            i4 = (composerStartRestartGroup.changed(shape) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i2 & 112) == 0) {
            i4 |= composerStartRestartGroup.changed(f2) ? 32 : 16;
        }
        if ((i2 & 896) == 0) {
            i4 |= composerStartRestartGroup.changed(j3) ? 256 : 128;
        }
        if ((i2 & 7168) == 0) {
            i4 |= composerStartRestartGroup.changed(j4) ? 2048 : 1024;
        }
        if (((1533916891 & i3) ^ 306783378) != 0 || ((i4 & 5851) ^ 1170) != 0 || !composerStartRestartGroup.getSkipping()) {
            MeasurePolicy measurePolicy = new MeasurePolicy() { // from class: androidx.compose.material.OutlinedTextFieldKt$IconsWithTextFieldLayout$2
                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i6) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicHeight(this, intrinsicMeasureScope, list, i6);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i6) {
                    return MeasurePolicy.DefaultImpls.maxIntrinsicWidth(this, intrinsicMeasureScope, list, i6);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i6) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicHeight(this, intrinsicMeasureScope, list, i6);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i6) {
                    return MeasurePolicy.DefaultImpls.minIntrinsicWidth(this, intrinsicMeasureScope, list, i6);
                }

                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* renamed from: measure-3p2s80s */
                public final MeasureResult mo287measure3p2s80s(final MeasureScope Layout, List<? extends Measurable> measurables, long j5) {
                    Object next;
                    Object next2;
                    Object next3;
                    Object next4;
                    Intrinsics.checkNotNullParameter(Layout, "$this$Layout");
                    Intrinsics.checkNotNullParameter(measurables, "measurables");
                    int i6 = Layout.mo571roundToPx0680j_4(TextFieldImplKt.getTextFieldPadding());
                    long jM4337copyZbe2FdA$default = Constraints.m4337copyZbe2FdA$default(j5, 0, 0, 0, 0, 10, null);
                    List<? extends Measurable> list = measurables;
                    Iterator<T> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                        if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) next), TextFieldImplKt.LeadingId)) {
                            break;
                        }
                    }
                    Measurable measurable = (Measurable) next;
                    Placeable placeableMo3396measureBRTryo0 = measurable == null ? null : measurable.mo3396measureBRTryo0(jM4337copyZbe2FdA$default);
                    int iWidthOrZero = TextFieldImplKt.widthOrZero(placeableMo3396measureBRTryo0);
                    Iterator<T> it2 = list.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it2.next();
                        if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) next2), TextFieldImplKt.TrailingId)) {
                            break;
                        }
                    }
                    Measurable measurable2 = (Measurable) next2;
                    Placeable placeableMo3396measureBRTryo02 = measurable2 == null ? null : measurable2.mo3396measureBRTryo0(ConstraintsKt.m4363offsetNN6EwU$default(jM4337copyZbe2FdA$default, -iWidthOrZero, 0, 2, null));
                    int i7 = -(iWidthOrZero + TextFieldImplKt.widthOrZero(placeableMo3396measureBRTryo02));
                    int i8 = -i6;
                    long jM4362offsetNN6EwU = ConstraintsKt.m4362offsetNN6EwU(jM4337copyZbe2FdA$default, i7, i8);
                    Iterator<T> it3 = list.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            next3 = null;
                            break;
                        }
                        next3 = it3.next();
                        if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) next3), TextFieldImplKt.LabelId)) {
                            break;
                        }
                    }
                    Measurable measurable3 = (Measurable) next3;
                    Placeable placeableMo3396measureBRTryo03 = measurable3 == null ? null : measurable3.mo3396measureBRTryo0(jM4362offsetNN6EwU);
                    if (placeableMo3396measureBRTryo03 != null) {
                        function1.invoke(Size.m1696boximpl(SizeKt.Size(placeableMo3396measureBRTryo03.getWidth(), placeableMo3396measureBRTryo03.getHeight())));
                    }
                    long jM4337copyZbe2FdA$default2 = Constraints.m4337copyZbe2FdA$default(ConstraintsKt.m4362offsetNN6EwU(j5, i7, i8 - Math.max(TextFieldImplKt.heightOrZero(placeableMo3396measureBRTryo03) / 2, i6)), 0, 0, 0, 0, 11, null);
                    for (Measurable measurable4 : list) {
                        if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable4), TextFieldImplKt.TextFieldId)) {
                            final Placeable placeableMo3396measureBRTryo04 = measurable4.mo3396measureBRTryo0(jM4337copyZbe2FdA$default2);
                            long jM4337copyZbe2FdA$default3 = Constraints.m4337copyZbe2FdA$default(jM4337copyZbe2FdA$default2, 0, 0, 0, 0, 14, null);
                            Iterator<T> it4 = list.iterator();
                            while (true) {
                                if (!it4.hasNext()) {
                                    next4 = null;
                                    break;
                                }
                                next4 = it4.next();
                                if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) next4), TextFieldImplKt.PlaceholderId)) {
                                    break;
                                }
                            }
                            Measurable measurable5 = (Measurable) next4;
                            Placeable placeableMo3396measureBRTryo05 = measurable5 == null ? null : measurable5.mo3396measureBRTryo0(jM4337copyZbe2FdA$default3);
                            Placeable placeable = placeableMo3396measureBRTryo0;
                            Placeable placeable2 = placeableMo3396measureBRTryo02;
                            Placeable placeable3 = placeableMo3396measureBRTryo03;
                            Placeable placeable4 = placeableMo3396measureBRTryo05;
                            final int iM1352calculateWidthVsPV1Ek = OutlinedTextFieldKt.m1352calculateWidthVsPV1Ek(placeable, placeable2, placeableMo3396measureBRTryo04, placeable3, placeable4, j5);
                            final int iM1351calculateHeightMK6IjOU = OutlinedTextFieldKt.m1351calculateHeightMK6IjOU(placeable, placeable2, placeableMo3396measureBRTryo04, placeable3, placeable4, j5, Layout.getDensity());
                            for (Measurable measurable6 : list) {
                                if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable6), Constants.KEY_BORDER)) {
                                    final Placeable placeableMo3396measureBRTryo06 = measurable6.mo3396measureBRTryo0(ConstraintsKt.Constraints(iM1352calculateWidthVsPV1Ek != Integer.MAX_VALUE ? iM1352calculateWidthVsPV1Ek : 0, iM1352calculateWidthVsPV1Ek, iM1351calculateHeightMK6IjOU != Integer.MAX_VALUE ? iM1351calculateHeightMK6IjOU : 0, iM1351calculateHeightMK6IjOU));
                                    final float f3 = f;
                                    final boolean z2 = z;
                                    final Placeable placeable5 = placeableMo3396measureBRTryo0;
                                    final Placeable placeable6 = placeableMo3396measureBRTryo02;
                                    final Placeable placeable7 = placeableMo3396measureBRTryo03;
                                    final Placeable placeable8 = placeableMo3396measureBRTryo05;
                                    return MeasureScope.layout$default(Layout, iM1352calculateWidthVsPV1Ek, iM1351calculateHeightMK6IjOU, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.OutlinedTextFieldKt$IconsWithTextFieldLayout$2$measure$2
                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                        {
                                            super(1);
                                        }

                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                            invoke2(placementScope);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(Placeable.PlacementScope layout) {
                                            Intrinsics.checkNotNullParameter(layout, "$this$layout");
                                            OutlinedTextFieldKt.place(layout, iM1351calculateHeightMK6IjOU, iM1352calculateWidthVsPV1Ek, placeable5, placeable6, placeableMo3396measureBRTryo04, placeable7, placeable8, placeableMo3396measureBRTryo06, f3, z2, Layout.getDensity());
                                        }
                                    }, 4, null);
                                }
                            }
                            throw new NoSuchElementException("Collection contains no element matching the predicate.");
                        }
                    }
                    throw new NoSuchElementException("Collection contains no element matching the predicate.");
                }
            };
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
            Updater.m1525setimpl(composerM1518constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m1525setimpl(composerM1518constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m1525setimpl(composerM1518constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            composerStartRestartGroup.enableReusing();
            function3MaterializerOf.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            composerStartRestartGroup.startReplaceableGroup(-804088826);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C398@19170L163,435@20582L182:OutlinedTextField.kt#jmzs0o");
            BoxKt.Box(m1354outlinedBordergLEpSso(LayoutIdKt.layoutId(Modifier.INSTANCE, Constants.KEY_BORDER), shape, f2, j3, j4), composerStartRestartGroup, 0);
            if (function23 != null) {
                composerStartRestartGroup.startReplaceableGroup(-804088628);
                ComposerKt.sourceInformation(composerStartRestartGroup, "405@19386L338");
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
                composerStartRestartGroup.startReplaceableGroup(-447675969);
                ComposerKt.sourceInformation(composerStartRestartGroup, "C409@19578L128:OutlinedTextField.kt#jmzs0o");
                i5 = i3;
                str = "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo";
                str2 = "C:CompositionLocal.kt#9igjgp";
                str3 = "C72@3384L9:Box.kt#2w3rfo";
                str4 = "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh";
                TextFieldImplKt.m1458DecorationeuL9pac(j, null, null, function23, composerStartRestartGroup, ((i3 >> 18) & 14) | (i3 & 7168), 6);
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
                composer2 = composerStartRestartGroup;
            } else {
                i5 = i3;
                str = "C(Box)P(2,1,3)70@3267L67,71@3339L130:Box.kt#2w3rfo";
                str2 = "C:CompositionLocal.kt#9igjgp";
                str3 = "C72@3384L9:Box.kt#2w3rfo";
                str4 = "C(Layout)P(!1,2)71@2788L7,72@2843L7,73@2855L389:Layout.kt#80mrfh";
                composer2 = composerStartRestartGroup;
                composer2.startReplaceableGroup(-804088258);
                composer2.endReplaceableGroup();
            }
            if (function24 != null) {
                composer2.startReplaceableGroup(-804088223);
                ComposerKt.sourceInformation(composer2, "416@19791L341");
                Modifier modifierThen2 = LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.TrailingId).then(TextFieldImplKt.getIconDefaultSizeModifier());
                Alignment center2 = Alignment.INSTANCE.getCenter();
                composer2.startReplaceableGroup(-1990474327);
                ComposerKt.sourceInformation(composer2, str);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center2, false, composer2, 0);
                composer2.startReplaceableGroup(1376089335);
                ComposerKt.sourceInformation(composer2, str4);
                ProvidableCompositionLocal<Density> localDensity3 = CompositionLocalsKt.getLocalDensity();
                String str7 = str2;
                ComposerKt.sourceInformationMarkerStart(composer2, 103361330, str7);
                Object objConsume5 = composer2.consume(localDensity3);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                Density density3 = (Density) objConsume5;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection3 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composer2, 103361330, str7);
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
                String str8 = str3;
                ComposerKt.sourceInformation(composer2, str8);
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                composer2.startReplaceableGroup(-447675563);
                ComposerKt.sourceInformation(composer2, "C420@19984L130:OutlinedTextField.kt#jmzs0o");
                str5 = str7;
                str6 = str8;
                Composer composer4 = composer2;
                TextFieldImplKt.m1458DecorationeuL9pac(j2, null, null, function24, composer2, ((i5 >> 21) & 14) | ((i5 >> 3) & 7168), 6);
                composer4.endReplaceableGroup();
                composer4.endReplaceableGroup();
                composer4.endReplaceableGroup();
                composer4.endNode();
                composer4.endReplaceableGroup();
                composer4.endReplaceableGroup();
                composer4.endReplaceableGroup();
                composer3 = composer4;
            } else {
                str5 = str2;
                str6 = str3;
                composer3 = composer2;
                composer3.startReplaceableGroup(-804087850);
                composer3.endReplaceableGroup();
            }
            float fM4390constructorimpl = Dp.m4390constructorimpl(TextFieldImplKt.getTextFieldPadding() - TextFieldImplKt.getHorizontalIconPadding());
            Modifier.Companion companion2 = Modifier.INSTANCE;
            float textFieldPadding = function23 != null ? fM4390constructorimpl : TextFieldImplKt.getTextFieldPadding();
            if (function24 == null) {
                fM4390constructorimpl = TextFieldImplKt.getTextFieldPadding();
            }
            Modifier modifierM694paddingqDBjuR0$default = PaddingKt.m694paddingqDBjuR0$default(companion2, textFieldPadding, 0.0f, fM4390constructorimpl, 0.0f, 10, null);
            if (function3 != null) {
                composer3.startReplaceableGroup(-804087519);
                ComposerKt.sourceInformation(composer3, "432@20495L59");
                function3.invoke(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.PlaceholderId).then(modifierM694paddingqDBjuR0$default), composer3, Integer.valueOf(i5 & 112));
                composer3.endReplaceableGroup();
            } else {
                composer3.startReplaceableGroup(-804087428);
                composer3.endReplaceableGroup();
            }
            Modifier modifierThen3 = LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.TextFieldId).then(modifierM694paddingqDBjuR0$default);
            composer3.startReplaceableGroup(-1990474327);
            ComposerKt.sourceInformation(composer3, str);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy3 = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true, composer3, 48);
            composer3.startReplaceableGroup(1376089335);
            ComposerKt.sourceInformation(composer3, str4);
            ProvidableCompositionLocal<Density> localDensity4 = CompositionLocalsKt.getLocalDensity();
            String str9 = str5;
            ComposerKt.sourceInformationMarkerStart(composer3, 103361330, str9);
            Object objConsume7 = composer3.consume(localDensity4);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            Density density4 = (Density) objConsume7;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection4 = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composer3, 103361330, str9);
            Object objConsume8 = composer3.consume(localLayoutDirection4);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            LayoutDirection layoutDirection4 = (LayoutDirection) objConsume8;
            Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf4 = LayoutKt.materializerOf(modifierThen3);
            if (!(composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer3.startReusableNode();
            if (composer3.getInserting()) {
                composer3.createNode(constructor4);
            } else {
                composer3.useNode();
            }
            composer3.disableReusing();
            Composer composerM1518constructorimpl4 = Updater.m1518constructorimpl(composer3);
            Updater.m1525setimpl(composerM1518constructorimpl4, measurePolicyRememberBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m1525setimpl(composerM1518constructorimpl4, density4, ComposeUiNode.INSTANCE.getSetDensity());
            Updater.m1525setimpl(composerM1518constructorimpl4, layoutDirection4, ComposeUiNode.INSTANCE.getSetLayoutDirection());
            composer3.enableReusing();
            function3MaterializerOf4.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composer3)), composer3, 0);
            composer3.startReplaceableGroup(2058660585);
            composer3.startReplaceableGroup(-1253629305);
            String str10 = str6;
            ComposerKt.sourceInformation(composer3, str10);
            BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
            composer3.startReplaceableGroup(-447674808);
            ComposerKt.sourceInformation(composer3, "C439@20739L11:OutlinedTextField.kt#jmzs0o");
            function2.invoke(composer3, Integer.valueOf(i5 & 14));
            composer3.endReplaceableGroup();
            composer3.endReplaceableGroup();
            composer3.endReplaceableGroup();
            composer3.endNode();
            composer3.endReplaceableGroup();
            composer3.endReplaceableGroup();
            if (function22 != null) {
                composer3.startReplaceableGroup(-804087199);
                ComposerKt.sourceInformation(composer3, "443@20815L54");
                Modifier modifierLayoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.LabelId);
                composer3.startReplaceableGroup(-1990474327);
                ComposerKt.sourceInformation(composer3, str);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy4 = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composer3, 0);
                composer3.startReplaceableGroup(1376089335);
                ComposerKt.sourceInformation(composer3, str4);
                ProvidableCompositionLocal<Density> localDensity5 = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart(composer3, 103361330, str9);
                Object objConsume9 = composer3.consume(localDensity5);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                Density density5 = (Density) objConsume9;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection5 = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart(composer3, 103361330, str9);
                Object objConsume10 = composer3.consume(localLayoutDirection5);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                LayoutDirection layoutDirection5 = (LayoutDirection) objConsume10;
                Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3MaterializerOf5 = LayoutKt.materializerOf(modifierLayoutId);
                if (!(composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer3.startReusableNode();
                if (composer3.getInserting()) {
                    composer3.createNode(constructor5);
                } else {
                    composer3.useNode();
                }
                composer3.disableReusing();
                Composer composerM1518constructorimpl5 = Updater.m1518constructorimpl(composer3);
                Updater.m1525setimpl(composerM1518constructorimpl5, measurePolicyRememberBoxMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m1525setimpl(composerM1518constructorimpl5, density5, ComposeUiNode.INSTANCE.getSetDensity());
                Updater.m1525setimpl(composerM1518constructorimpl5, layoutDirection5, ComposeUiNode.INSTANCE.getSetLayoutDirection());
                composer3.enableReusing();
                function3MaterializerOf5.invoke(SkippableUpdater.m1509boximpl(SkippableUpdater.m1510constructorimpl(composer3)), composer3, 0);
                composer3.startReplaceableGroup(2058660585);
                composer3.startReplaceableGroup(-1253629305);
                ComposerKt.sourceInformation(composer3, str10);
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                composer3.startReplaceableGroup(-447674687);
                ComposerKt.sourceInformation(composer3, "C443@20860L7:OutlinedTextField.kt#jmzs0o");
                function22.invoke(composer3, Integer.valueOf((i5 >> 6) & 14));
                composer3.endReplaceableGroup();
                composer3.endReplaceableGroup();
                composer3.endReplaceableGroup();
                composer3.endNode();
                composer3.endReplaceableGroup();
                composer3.endReplaceableGroup();
                composer3.endReplaceableGroup();
            } else {
                composer3.startReplaceableGroup(-804087113);
                composer3.endReplaceableGroup();
            }
            composer3.endReplaceableGroup();
            composer3.endReplaceableGroup();
            composer3.endNode();
            composer3.endReplaceableGroup();
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer3 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.OutlinedTextFieldKt$IconsWithTextFieldLayout$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                invoke(composer5, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer5, int i6) {
                OutlinedTextFieldKt.m1343IconsWithTextFieldLayoutT2E5_Oc(function2, function3, function22, function23, function24, z, j, j2, f, function1, shape, f2, j3, j4, composer5, i | 1, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculateWidth-VsPV1Ek, reason: not valid java name */
    public static final int m1352calculateWidthVsPV1Ek(Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, long j) {
        return Math.max(TextFieldImplKt.widthOrZero(placeable) + Math.max(placeable3.getWidth(), Math.max(TextFieldImplKt.widthOrZero(placeable4), TextFieldImplKt.widthOrZero(placeable5))) + TextFieldImplKt.widthOrZero(placeable2), Constraints.m4348getMinWidthimpl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculateHeight-MK6IjOU, reason: not valid java name */
    public static final int m1351calculateHeightMK6IjOU(Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, long j, float f) {
        int iMax = Math.max(placeable3.getHeight(), TextFieldImplKt.heightOrZero(placeable5));
        float textFieldPadding = TextFieldImplKt.getTextFieldPadding() * f;
        return Math.max(Constraints.m4347getMinHeightimpl(j), Math.max(TextFieldImplKt.heightOrZero(placeable), Math.max(TextFieldImplKt.heightOrZero(placeable2), MathKt.roundToInt(iMax + textFieldPadding + Math.max(textFieldPadding, TextFieldImplKt.heightOrZero(placeable4) / 2.0f)))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void place(Placeable.PlacementScope placementScope, int i, int i2, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, Placeable placeable6, float f, boolean z, float f2) {
        int iRoundToInt = MathKt.roundToInt(TextFieldImplKt.getTextFieldPadding() * f2);
        float horizontalIconPadding = TextFieldImplKt.getHorizontalIconPadding() * f2;
        if (placeable != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, Alignment.INSTANCE.getCenterVertically().align(placeable.getHeight(), i), 0.0f, 4, null);
        }
        if (placeable2 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i2 - placeable2.getWidth(), Alignment.INSTANCE.getCenterVertically().align(placeable2.getHeight(), i), 0.0f, 4, null);
        }
        if (placeable4 != null) {
            float f3 = 1 - f;
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, MathKt.roundToInt(placeable == null ? 0.0f : f3 * (TextFieldImplKt.widthOrZero(placeable) - horizontalIconPadding)) + iRoundToInt, MathKt.roundToInt(((z ? Alignment.INSTANCE.getCenterVertically().align(placeable4.getHeight(), i) : iRoundToInt) * f3) - ((placeable4.getHeight() / 2) * f)), 0.0f, 4, null);
        }
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, TextFieldImplKt.widthOrZero(placeable), z ? Alignment.INSTANCE.getCenterVertically().align(placeable3.getHeight(), i) : iRoundToInt, 0.0f, 4, null);
        if (placeable5 != null) {
            if (z) {
                iRoundToInt = Alignment.INSTANCE.getCenterVertically().align(placeable5.getHeight(), i);
            }
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable5, TextFieldImplKt.widthOrZero(placeable), iRoundToInt, 0.0f, 4, null);
        }
        Placeable.PlacementScope.m3444place70tqf50$default(placementScope, placeable6, IntOffset.INSTANCE.m4518getZeronOccac(), 0.0f, 2, null);
    }

    /* renamed from: outlinedBorder-gLEpSso, reason: not valid java name */
    private static final Modifier m1354outlinedBordergLEpSso(Modifier modifier, Shape shape, float f, long j, long j2) {
        return BorderKt.m437borderxT4_qwU(m1353outlineCutoutd16Qtg0(modifier, j2), f, j, shape);
    }

    /* renamed from: outlineCutout-d16Qtg0, reason: not valid java name */
    private static final Modifier m1353outlineCutoutd16Qtg0(Modifier modifier, final long j) {
        return DrawModifierKt.drawWithContent(modifier, new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.material.OutlinedTextFieldKt$outlineCutout$1

            /* compiled from: OutlinedTextField.kt */
            @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[LayoutDirection.values().length];
                    iArr[LayoutDirection.Ltr.ordinal()] = 1;
                    iArr[LayoutDirection.Rtl.ordinal()] = 2;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope drawWithContent) {
                float fM1708getWidthimpl;
                Intrinsics.checkNotNullParameter(drawWithContent, "$this$drawWithContent");
                float fM1708getWidthimpl2 = Size.m1708getWidthimpl(j);
                if (fM1708getWidthimpl2 > 0.0f) {
                    float f = drawWithContent.mo577toPx0680j_4(OutlinedTextFieldKt.OutlinedTextFieldInnerPadding);
                    float f2 = drawWithContent.mo577toPx0680j_4(TextFieldImplKt.getTextFieldPadding()) - f;
                    float f3 = 2;
                    float fM1708getWidthimpl3 = fM1708getWidthimpl2 + f2 + (f * f3);
                    int i = WhenMappings.$EnumSwitchMapping$0[drawWithContent.getLayoutDirection().ordinal()];
                    if (i == 1) {
                        fM1708getWidthimpl = f2;
                    } else {
                        if (i != 2) {
                            throw new NoWhenBranchMatchedException();
                        }
                        fM1708getWidthimpl = Size.m1708getWidthimpl(drawWithContent.mo2417getSizeNHjbRc()) - fM1708getWidthimpl3;
                    }
                    int i2 = WhenMappings.$EnumSwitchMapping$0[drawWithContent.getLayoutDirection().ordinal()];
                    if (i2 != 1) {
                        if (i2 != 2) {
                            throw new NoWhenBranchMatchedException();
                        }
                        fM1708getWidthimpl3 = Size.m1708getWidthimpl(drawWithContent.mo2417getSizeNHjbRc()) - f2;
                    }
                    float f4 = fM1708getWidthimpl3;
                    float fM1705getHeightimpl = Size.m1705getHeightimpl(j);
                    int iM1865getDifferencertfAjoo = ClipOp.INSTANCE.m1865getDifferencertfAjoo();
                    DrawContext drawContext = drawWithContent.getDrawContext();
                    long jMo2342getSizeNHjbRc = drawContext.mo2342getSizeNHjbRc();
                    drawContext.getCanvas().save();
                    drawContext.getTransform().mo2345clipRectN_I0leg(fM1708getWidthimpl, (-fM1705getHeightimpl) / f3, f4, fM1705getHeightimpl / f3, iM1865getDifferencertfAjoo);
                    drawWithContent.drawContent();
                    drawContext.getCanvas().restore();
                    drawContext.mo2343setSizeuvyYCjk(jMo2342getSizeNHjbRc);
                    return;
                }
                drawWithContent.drawContent();
            }
        });
    }
}
