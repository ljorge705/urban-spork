package androidx.compose.material;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import com.facebook.hermes.intl.Constants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* compiled from: Typography.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\t"}, d2 = {"LocalTypography", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material/Typography;", "getLocalTypography", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "withDefaultFontFamily", "Landroidx/compose/ui/text/TextStyle;", Constants.COLLATION_DEFAULT, "Landroidx/compose/ui/text/font/FontFamily;", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class TypographyKt {
    private static final ProvidableCompositionLocal<Typography> LocalTypography = CompositionLocalKt.staticCompositionLocalOf(new Function0<Typography>() { // from class: androidx.compose.material.TypographyKt$LocalTypography$1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Typography invoke() {
            return new Typography(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
        }
    });

    public static final ProvidableCompositionLocal<Typography> getLocalTypography() {
        return LocalTypography;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextStyle withDefaultFontFamily(TextStyle textStyle, FontFamily fontFamily) {
        if (textStyle.getFontFamily() != null) {
            return textStyle;
        }
        return textStyle.m3919copyHL5avdY((262111 & 1) != 0 ? textStyle.spanStyle.m3865getColor0d7_KjU() : 0L, (262111 & 2) != 0 ? textStyle.spanStyle.getFontSize() : 0L, (262111 & 4) != 0 ? textStyle.spanStyle.getFontWeight() : null, (262111 & 8) != 0 ? textStyle.spanStyle.getFontStyle() : null, (262111 & 16) != 0 ? textStyle.spanStyle.getFontSynthesis() : null, (262111 & 32) != 0 ? textStyle.spanStyle.getFontFamily() : fontFamily, (262111 & 64) != 0 ? textStyle.spanStyle.getFontFeatureSettings() : null, (262111 & 128) != 0 ? textStyle.spanStyle.getLetterSpacing() : 0L, (262111 & 256) != 0 ? textStyle.spanStyle.getBaselineShift() : null, (262111 & 512) != 0 ? textStyle.spanStyle.getTextGeometricTransform() : null, (262111 & 1024) != 0 ? textStyle.spanStyle.getLocaleList() : null, (262111 & 2048) != 0 ? textStyle.spanStyle.getBackground() : 0L, (262111 & 4096) != 0 ? textStyle.spanStyle.getTextDecoration() : null, (262111 & 8192) != 0 ? textStyle.spanStyle.getShadow() : null, (262111 & 16384) != 0 ? textStyle.paragraphStyle.getTextAlign() : null, (262111 & 32768) != 0 ? textStyle.paragraphStyle.getTextDirection() : null, (262111 & 65536) != 0 ? textStyle.paragraphStyle.getLineHeight() : 0L, (262111 & 131072) != 0 ? textStyle.paragraphStyle.getTextIndent() : null);
    }
}
