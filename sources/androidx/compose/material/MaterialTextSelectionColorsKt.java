package androidx.compose.material;

import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import com.BV.LinearGradient.LinearGradientManager;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MaterialTextSelectionColors.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a-\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\n\u001a%\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000e\u0010\u000f\u001a5\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0012\u001a-\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0015\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0001¢\u0006\u0002\u0010\u001a\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"DefaultSelectionBackgroundAlpha", "", "DesiredContrastRatio", "MinimumSelectionBackgroundAlpha", "binarySearchForAccessibleSelectionColorAlpha", "selectionColor", "Landroidx/compose/ui/graphics/Color;", "textColor", ViewProps.BACKGROUND_COLOR, "binarySearchForAccessibleSelectionColorAlpha-ysEtTa8", "(JJJ)F", "calculateContrastRatio", "foreground", AppStateModule.APP_STATE_BACKGROUND, "calculateContrastRatio--OWjLjI", "(JJ)F", "selectionColorAlpha", "calculateContrastRatio-nb2GgbA", "(JFJJ)F", "calculateSelectionBackgroundColor", "calculateSelectionBackgroundColor-ysEtTa8", "(JJJ)J", "rememberTextSelectionColors", "Landroidx/compose/foundation/text/selection/TextSelectionColors;", LinearGradientManager.PROP_COLORS, "Landroidx/compose/material/Colors;", "(Landroidx/compose/material/Colors;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/text/selection/TextSelectionColors;", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MaterialTextSelectionColorsKt {
    private static final float DefaultSelectionBackgroundAlpha = 0.4f;
    private static final float DesiredContrastRatio = 4.5f;
    private static final float MinimumSelectionBackgroundAlpha = 0.2f;

    public static final TextSelectionColors rememberTextSelectionColors(Colors colors, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(colors, "colors");
        composer.startReplaceableGroup(35572393);
        ComposerKt.sourceInformation(composer, "C(rememberTextSelectionColors)45@1902L6,47@1930L384:MaterialTextSelectionColors.kt#jmzs0o");
        long jM1242getPrimary0d7_KjU = colors.m1242getPrimary0d7_KjU();
        long jM1235getBackground0d7_KjU = colors.m1235getBackground0d7_KjU();
        composer.startReplaceableGroup(35572910);
        ComposerKt.sourceInformation(composer, "*43@1845L7");
        long jM1259contentColorFor4WTKRHQ = ColorsKt.m1259contentColorFor4WTKRHQ(colors, jM1235getBackground0d7_KjU);
        if (jM1259contentColorFor4WTKRHQ == Color.INSTANCE.m1913getUnspecified0d7_KjU()) {
            ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
            ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localContentColor);
            ComposerKt.sourceInformationMarkerEnd(composer);
            jM1259contentColorFor4WTKRHQ = ((Color) objConsume).m1887unboximpl();
        }
        composer.endReplaceableGroup();
        long jM1876copywmQWz5c$default = Color.m1876copywmQWz5c$default(jM1259contentColorFor4WTKRHQ, ContentAlpha.INSTANCE.getMedium(composer, 0), 0.0f, 0.0f, 0.0f, 14, null);
        Color colorM1867boximpl = Color.m1867boximpl(jM1242getPrimary0d7_KjU);
        Color colorM1867boximpl2 = Color.m1867boximpl(jM1235getBackground0d7_KjU);
        Color colorM1867boximpl3 = Color.m1867boximpl(jM1876copywmQWz5c$default);
        composer.startReplaceableGroup(-3686095);
        ComposerKt.sourceInformation(composer, "C(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(colorM1867boximpl) | composer.changed(colorM1867boximpl2) | composer.changed(colorM1867boximpl3);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new TextSelectionColors(colors.m1242getPrimary0d7_KjU(), m1330calculateSelectionBackgroundColorysEtTa8(jM1242getPrimary0d7_KjU, jM1876copywmQWz5c$default, jM1235getBackground0d7_KjU), null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        TextSelectionColors textSelectionColors = (TextSelectionColors) objRememberedValue;
        composer.endReplaceableGroup();
        return textSelectionColors;
    }

    /* renamed from: calculateSelectionBackgroundColor-ysEtTa8, reason: not valid java name */
    public static final long m1330calculateSelectionBackgroundColorysEtTa8(long j, long j2, long j3) {
        float fM1327binarySearchForAccessibleSelectionColorAlphaysEtTa8;
        float fM1329calculateContrastRationb2GgbA = m1329calculateContrastRationb2GgbA(j, DefaultSelectionBackgroundAlpha, j2, j3);
        float fM1329calculateContrastRationb2GgbA2 = m1329calculateContrastRationb2GgbA(j, 0.2f, j2, j3);
        if (fM1329calculateContrastRationb2GgbA >= DesiredContrastRatio) {
            fM1327binarySearchForAccessibleSelectionColorAlphaysEtTa8 = DefaultSelectionBackgroundAlpha;
        } else {
            fM1327binarySearchForAccessibleSelectionColorAlphaysEtTa8 = fM1329calculateContrastRationb2GgbA2 < DesiredContrastRatio ? 0.2f : m1327binarySearchForAccessibleSelectionColorAlphaysEtTa8(j, j2, j3);
        }
        return Color.m1876copywmQWz5c$default(j, fM1327binarySearchForAccessibleSelectionColorAlphaysEtTa8, 0.0f, 0.0f, 0.0f, 14, null);
    }

    /* renamed from: binarySearchForAccessibleSelectionColorAlpha-ysEtTa8, reason: not valid java name */
    private static final float m1327binarySearchForAccessibleSelectionColorAlphaysEtTa8(long j, long j2, long j3) {
        float f = 0.2f;
        float f2 = 0.4f;
        float f3 = 0.4f;
        for (int i = 0; i < 7; i++) {
            float fM1329calculateContrastRationb2GgbA = (m1329calculateContrastRationb2GgbA(j, f2, j2, j3) / DesiredContrastRatio) - 1.0f;
            if (0.0f <= fM1329calculateContrastRationb2GgbA && fM1329calculateContrastRationb2GgbA <= 0.01f) {
                break;
            }
            if (fM1329calculateContrastRationb2GgbA < 0.0f) {
                f3 = f2;
            } else {
                f = f2;
            }
            f2 = (f3 + f) / 2.0f;
        }
        return f2;
    }

    /* renamed from: calculateContrastRatio-nb2GgbA, reason: not valid java name */
    private static final float m1329calculateContrastRationb2GgbA(long j, float f, long j2, long j3) {
        long jM1922compositeOverOWjLjI = ColorKt.m1922compositeOverOWjLjI(Color.m1876copywmQWz5c$default(j, f, 0.0f, 0.0f, 0.0f, 14, null), j3);
        return m1328calculateContrastRatioOWjLjI(ColorKt.m1922compositeOverOWjLjI(j2, jM1922compositeOverOWjLjI), jM1922compositeOverOWjLjI);
    }

    /* renamed from: calculateContrastRatio--OWjLjI, reason: not valid java name */
    public static final float m1328calculateContrastRatioOWjLjI(long j, long j2) {
        float fM1929luminance8_81llA = ColorKt.m1929luminance8_81llA(j) + 0.05f;
        float fM1929luminance8_81llA2 = ColorKt.m1929luminance8_81llA(j2) + 0.05f;
        return Math.max(fM1929luminance8_81llA, fM1929luminance8_81llA2) / Math.min(fM1929luminance8_81llA, fM1929luminance8_81llA2);
    }
}
