package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import com.BV.LinearGradient.LinearGradientManager;
import kotlin.Metadata;

/* compiled from: RadioButton.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J3\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u000b"}, d2 = {"Landroidx/compose/material/RadioButtonDefaults;", "", "()V", LinearGradientManager.PROP_COLORS, "Landroidx/compose/material/RadioButtonColors;", "selectedColor", "Landroidx/compose/ui/graphics/Color;", "unselectedColor", "disabledColor", "colors-RGew2ao", "(JJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/RadioButtonColors;", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class RadioButtonDefaults {
    public static final int $stable = 0;
    public static final RadioButtonDefaults INSTANCE = new RadioButtonDefaults();

    private RadioButtonDefaults() {
    }

    /* renamed from: colors-RGew2ao, reason: not valid java name */
    public final RadioButtonColors m1386colorsRGew2ao(long j, long j2, long j3, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(-1165740466);
        ComposerKt.sourceInformation(composer, "C(colors)P(1:c#ui.graphics.Color,2:c#ui.graphics.Color,0:c#ui.graphics.Color)149@6156L6,150@6221L6,151@6303L6,151@6346L8,153@6398L197:RadioButton.kt#jmzs0o");
        long jM1244getSecondary0d7_KjU = (i2 & 1) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1244getSecondary0d7_KjU() : j;
        long jM1876copywmQWz5c$default = (i2 & 2) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), 0.6f, 0.0f, 0.0f, 0.0f, 14, null) : j2;
        long jM1876copywmQWz5c$default2 = (i2 & 4) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j3;
        Color colorM1867boximpl = Color.m1867boximpl(jM1244getSecondary0d7_KjU);
        Color colorM1867boximpl2 = Color.m1867boximpl(jM1876copywmQWz5c$default);
        Color colorM1867boximpl3 = Color.m1867boximpl(jM1876copywmQWz5c$default2);
        composer.startReplaceableGroup(-3686095);
        ComposerKt.sourceInformation(composer, "C(remember)P(1,2,3):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(colorM1867boximpl) | composer.changed(colorM1867boximpl2) | composer.changed(colorM1867boximpl3);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new DefaultRadioButtonColors(jM1244getSecondary0d7_KjU, jM1876copywmQWz5c$default, jM1876copywmQWz5c$default2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return (DefaultRadioButtonColors) objRememberedValue;
    }
}
