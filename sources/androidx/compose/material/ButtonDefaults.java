package androidx.compose.material;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: Button.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J=\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'J3\u0010(\u001a\u00020)2\b\b\u0002\u0010*\u001a\u00020\u00042\b\b\u0002\u0010+\u001a\u00020\u00042\b\b\u0002\u0010,\u001a\u00020\u0004H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b-\u0010.J3\u0010/\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101J3\u00102\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\"H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b3\u00101R\u0019\u0010\u0003\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0019\u0010\u0006\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u000f\u0010\rR\u001c\u0010\u0010\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0011\u0010\rR\u001c\u0010\u0012\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0013\u0010\rR\u000e\u0010\u0014\u001a\u00020\u0015X\u0086T¢\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0017\u0010\rR\u0011\u0010\u0018\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\nR\u0019\u0010\u001a\u001a\u00020\u0004X\u0082\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0005R\u0011\u0010\u001b\u001a\u00020\u001c8G¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00064"}, d2 = {"Landroidx/compose/material/ButtonDefaults;", "", "()V", "ButtonHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "ButtonVerticalPadding", "ContentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getContentPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "IconSize", "getIconSize-D9Ej5fM", "()F", "IconSpacing", "getIconSpacing-D9Ej5fM", "MinHeight", "getMinHeight-D9Ej5fM", "MinWidth", "getMinWidth-D9Ej5fM", "OutlinedBorderOpacity", "", "OutlinedBorderSize", "getOutlinedBorderSize-D9Ej5fM", "TextButtonContentPadding", "getTextButtonContentPadding", "TextButtonHorizontalPadding", "outlinedBorder", "Landroidx/compose/foundation/BorderStroke;", "getOutlinedBorder", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/BorderStroke;", "buttonColors", "Landroidx/compose/material/ButtonColors;", ViewProps.BACKGROUND_COLOR, "Landroidx/compose/ui/graphics/Color;", "contentColor", "disabledBackgroundColor", "disabledContentColor", "buttonColors-ro_MJ88", "(JJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ButtonColors;", ViewProps.ELEVATION, "Landroidx/compose/material/ButtonElevation;", "defaultElevation", "pressedElevation", "disabledElevation", "elevation-yajeYGU", "(FFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ButtonElevation;", "outlinedButtonColors", "outlinedButtonColors-RGew2ao", "(JJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ButtonColors;", "textButtonColors", "textButtonColors-RGew2ao", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ButtonDefaults {
    public static final int $stable = 0;
    private static final float ButtonHorizontalPadding;
    private static final float ButtonVerticalPadding;
    private static final PaddingValues ContentPadding;
    public static final ButtonDefaults INSTANCE = new ButtonDefaults();
    private static final float IconSize;
    private static final float IconSpacing;
    private static final float MinHeight;
    private static final float MinWidth;
    public static final float OutlinedBorderOpacity = 0.12f;
    private static final float OutlinedBorderSize;
    private static final PaddingValues TextButtonContentPadding;
    private static final float TextButtonHorizontalPadding;

    public final PaddingValues getContentPadding() {
        return ContentPadding;
    }

    /* renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1207getIconSizeD9Ej5fM() {
        return IconSize;
    }

    /* renamed from: getIconSpacing-D9Ej5fM, reason: not valid java name */
    public final float m1208getIconSpacingD9Ej5fM() {
        return IconSpacing;
    }

    /* renamed from: getMinHeight-D9Ej5fM, reason: not valid java name */
    public final float m1209getMinHeightD9Ej5fM() {
        return MinHeight;
    }

    /* renamed from: getMinWidth-D9Ej5fM, reason: not valid java name */
    public final float m1210getMinWidthD9Ej5fM() {
        return MinWidth;
    }

    /* renamed from: getOutlinedBorderSize-D9Ej5fM, reason: not valid java name */
    public final float m1211getOutlinedBorderSizeD9Ej5fM() {
        return OutlinedBorderSize;
    }

    public final PaddingValues getTextButtonContentPadding() {
        return TextButtonContentPadding;
    }

    private ButtonDefaults() {
    }

    /* renamed from: buttonColors-ro_MJ88, reason: not valid java name */
    public final ButtonColors m1205buttonColorsro_MJ88(long j, long j2, long j3, long j4, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(2063544006);
        ComposerKt.sourceInformation(composer, "C(buttonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)368@15341L6,369@15387L32,370@15476L6,371@15554L6,372@15623L6,373@15679L8:Button.kt#jmzs0o");
        long jM1242getPrimary0d7_KjU = (i2 & 1) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1242getPrimary0d7_KjU() : j;
        DefaultButtonColors defaultButtonColors = new DefaultButtonColors(jM1242getPrimary0d7_KjU, (i2 & 2) != 0 ? ColorsKt.m1260contentColorForek8zF_U(jM1242getPrimary0d7_KjU, composer, i & 14) : j2, (i2 & 4) != 0 ? ColorKt.m1922compositeOverOWjLjI(Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), MaterialTheme.INSTANCE.getColors(composer, 0).m1246getSurface0d7_KjU()) : j3, (i2 & 8) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j4, null);
        composer.endReplaceableGroup();
        return defaultButtonColors;
    }

    /* renamed from: outlinedButtonColors-RGew2ao, reason: not valid java name */
    public final ButtonColors m1212outlinedButtonColorsRGew2ao(long j, long j2, long j3, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(706917817);
        ComposerKt.sourceInformation(composer, "C(outlinedButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color)391@16426L6,392@16486L6,393@16554L6,394@16610L8:Button.kt#jmzs0o");
        long jM1246getSurface0d7_KjU = (i2 & 1) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1246getSurface0d7_KjU() : j;
        DefaultButtonColors defaultButtonColors = new DefaultButtonColors(jM1246getSurface0d7_KjU, (i2 & 2) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1242getPrimary0d7_KjU() : j2, jM1246getSurface0d7_KjU, (i2 & 4) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j3, null);
        composer.endReplaceableGroup();
        return defaultButtonColors;
    }

    /* renamed from: textButtonColors-RGew2ao, reason: not valid java name */
    public final ButtonColors m1213textButtonColorsRGew2ao(long j, long j2, long j3, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(1409303640);
        ComposerKt.sourceInformation(composer, "C(textButtonColors)P(0:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color)413@17377L6,414@17445L6,415@17501L8:Button.kt#jmzs0o");
        long jM1912getTransparent0d7_KjU = (i2 & 1) != 0 ? Color.INSTANCE.m1912getTransparent0d7_KjU() : j;
        DefaultButtonColors defaultButtonColors = new DefaultButtonColors(jM1912getTransparent0d7_KjU, (i2 & 2) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1242getPrimary0d7_KjU() : j2, jM1912getTransparent0d7_KjU, (i2 & 4) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j3, null);
        composer.endReplaceableGroup();
        return defaultButtonColors;
    }

    public final BorderStroke getOutlinedBorder(Composer composer, int i) {
        composer.startReplaceableGroup(-1546587144);
        ComposerKt.sourceInformation(composer, "C439@18205L6:Button.kt#jmzs0o");
        BorderStroke borderStrokeM446BorderStrokecXLIe8U = BorderStrokeKt.m446BorderStrokecXLIe8U(m1211getOutlinedBorderSizeD9Ej5fM(), Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), 0.12f, 0.0f, 0.0f, 0.0f, 14, null));
        composer.endReplaceableGroup();
        return borderStrokeM446BorderStrokecXLIe8U;
    }

    /* renamed from: elevation-yajeYGU, reason: not valid java name */
    public final ButtonElevation m1206elevationyajeYGU(float f, float f2, float f3, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(399129690);
        ComposerKt.sourceInformation(composer, "C(elevation)P(0:c#ui.unit.Dp,2:c#ui.unit.Dp,1:c#ui.unit.Dp)348@14487L285:Button.kt#jmzs0o");
        if ((i2 & 1) != 0) {
            f = Dp.m4390constructorimpl(2);
        }
        if ((i2 & 2) != 0) {
            f2 = Dp.m4390constructorimpl(8);
        }
        if ((i2 & 4) != 0) {
            f3 = Dp.m4390constructorimpl(0);
        }
        Dp dpM4388boximpl = Dp.m4388boximpl(f);
        Dp dpM4388boximpl2 = Dp.m4388boximpl(f2);
        Dp dpM4388boximpl3 = Dp.m4388boximpl(f3);
        composer.startReplaceableGroup(-3686095);
        ComposerKt.sourceInformation(composer, "C(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(dpM4388boximpl) | composer.changed(dpM4388boximpl2) | composer.changed(dpM4388boximpl3);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new DefaultButtonElevation(f, f2, f3, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return (DefaultButtonElevation) objRememberedValue;
    }

    static {
        float fM4390constructorimpl = Dp.m4390constructorimpl(16);
        ButtonHorizontalPadding = fM4390constructorimpl;
        float f = 8;
        float fM4390constructorimpl2 = Dp.m4390constructorimpl(f);
        ButtonVerticalPadding = fM4390constructorimpl2;
        PaddingValues paddingValuesM686PaddingValuesa9UjIt4 = PaddingKt.m686PaddingValuesa9UjIt4(fM4390constructorimpl, fM4390constructorimpl2, fM4390constructorimpl, fM4390constructorimpl2);
        ContentPadding = paddingValuesM686PaddingValuesa9UjIt4;
        MinWidth = Dp.m4390constructorimpl(64);
        MinHeight = Dp.m4390constructorimpl(36);
        IconSize = Dp.m4390constructorimpl(18);
        IconSpacing = Dp.m4390constructorimpl(f);
        OutlinedBorderSize = Dp.m4390constructorimpl(1);
        float fM4390constructorimpl3 = Dp.m4390constructorimpl(f);
        TextButtonHorizontalPadding = fM4390constructorimpl3;
        TextButtonContentPadding = PaddingKt.m686PaddingValuesa9UjIt4(fM4390constructorimpl3, paddingValuesM686PaddingValuesa9UjIt4.getTop(), fM4390constructorimpl3, paddingValuesM686PaddingValuesa9UjIt4.getBottom());
    }
}
