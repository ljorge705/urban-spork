package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import com.BV.LinearGradient.LinearGradientManager;
import kotlin.Metadata;

/* compiled from: Checkbox.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JG\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u0006H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\r"}, d2 = {"Landroidx/compose/material/CheckboxDefaults;", "", "()V", LinearGradientManager.PROP_COLORS, "Landroidx/compose/material/CheckboxColors;", "checkedColor", "Landroidx/compose/ui/graphics/Color;", "uncheckedColor", "checkmarkColor", "disabledColor", "disabledIndeterminateColor", "colors-zjMxDiM", "(JJJJJLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/CheckboxColors;", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class CheckboxDefaults {
    public static final int $stable = 0;
    public static final CheckboxDefaults INSTANCE = new CheckboxDefaults();

    private CheckboxDefaults() {
    }

    /* renamed from: colors-zjMxDiM, reason: not valid java name */
    public final CheckboxColors m1218colorszjMxDiM(long j, long j2, long j3, long j4, long j5, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(-533071607);
        ComposerKt.sourceInformation(composer, "C(colors)P(0:c#ui.graphics.Color,4:c#ui.graphics.Color,1:c#ui.graphics.Color,2:c#ui.graphics.Color,3:c#ui.graphics.Color)219@9348L6,220@9412L6,221@9495L6,222@9556L6,222@9599L8,223@9693L8,225@9742L922:Checkbox.kt#jmzs0o");
        int i3 = 0;
        long jM1244getSecondary0d7_KjU = (i2 & 1) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1244getSecondary0d7_KjU() : j;
        long jM1876copywmQWz5c$default = (i2 & 2) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), 0.6f, 0.0f, 0.0f, 0.0f, 14, null) : j2;
        long jM1246getSurface0d7_KjU = (i2 & 4) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1246getSurface0d7_KjU() : j3;
        long jM1876copywmQWz5c$default2 = (i2 & 8) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j4;
        long jM1876copywmQWz5c$default3 = (i2 & 16) != 0 ? Color.m1876copywmQWz5c$default(jM1244getSecondary0d7_KjU, ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null) : j5;
        Object[] objArr = {Color.m1867boximpl(jM1244getSecondary0d7_KjU), Color.m1867boximpl(jM1876copywmQWz5c$default), Color.m1867boximpl(jM1246getSurface0d7_KjU), Color.m1867boximpl(jM1876copywmQWz5c$default2), Color.m1867boximpl(jM1876copywmQWz5c$default3)};
        composer.startReplaceableGroup(-3685570);
        ComposerKt.sourceInformation(composer, "C(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged = false;
        while (i3 < 5) {
            Object obj = objArr[i3];
            i3++;
            zChanged |= composer.changed(obj);
        }
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new DefaultCheckboxColors(jM1246getSurface0d7_KjU, Color.m1876copywmQWz5c$default(jM1246getSurface0d7_KjU, 0.0f, 0.0f, 0.0f, 0.0f, 14, null), jM1244getSecondary0d7_KjU, Color.m1876copywmQWz5c$default(jM1244getSecondary0d7_KjU, 0.0f, 0.0f, 0.0f, 0.0f, 14, null), jM1876copywmQWz5c$default2, Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default2, 0.0f, 0.0f, 0.0f, 0.0f, 14, null), jM1876copywmQWz5c$default3, jM1244getSecondary0d7_KjU, jM1876copywmQWz5c$default, jM1876copywmQWz5c$default2, jM1876copywmQWz5c$default3, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return (DefaultCheckboxColors) objRememberedValue;
    }
}
