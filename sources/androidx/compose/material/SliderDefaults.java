package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import com.BV.LinearGradient.LinearGradientManager;
import kotlin.Metadata;

/* compiled from: Slider.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jy\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\f2\b\b\u0002\u0010\u0010\u001a\u00020\f2\b\b\u0002\u0010\u0011\u001a\u00020\f2\b\b\u0002\u0010\u0012\u001a\u00020\f2\b\b\u0002\u0010\u0013\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\f2\b\b\u0002\u0010\u0015\u001a\u00020\fH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0018"}, d2 = {"Landroidx/compose/material/SliderDefaults;", "", "()V", "DisabledActiveTrackAlpha", "", "DisabledInactiveTrackAlpha", "DisabledTickAlpha", "InactiveTrackAlpha", "TickAlpha", LinearGradientManager.PROP_COLORS, "Landroidx/compose/material/SliderColors;", "thumbColor", "Landroidx/compose/ui/graphics/Color;", "disabledThumbColor", "activeTrackColor", "inactiveTrackColor", "disabledActiveTrackColor", "disabledInactiveTrackColor", "activeTickColor", "inactiveTickColor", "disabledActiveTickColor", "disabledInactiveTickColor", "colors-q0g_0yA", "(JJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material/SliderColors;", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SliderDefaults {
    public static final int $stable = 0;
    public static final float DisabledActiveTrackAlpha = 0.32f;
    public static final float DisabledInactiveTrackAlpha = 0.12f;
    public static final float DisabledTickAlpha = 0.12f;
    public static final SliderDefaults INSTANCE = new SliderDefaults();
    public static final float InactiveTrackAlpha = 0.24f;
    public static final float TickAlpha = 0.54f;

    private SliderDefaults() {
    }

    /* renamed from: colors-q0g_0yA, reason: not valid java name */
    public final SliderColors m1397colorsq0g_0yA(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, Composer composer, int i, int i2, int i3) {
        composer.startReplaceableGroup(-341393942);
        ComposerKt.sourceInformation(composer, "C(colors)P(9:c#ui.graphics.Color,6:c#ui.graphics.Color,1:c#ui.graphics.Color,8:c#ui.graphics.Color,3:c#ui.graphics.Color,5:c#ui.graphics.Color,0:c#ui.graphics.Color,7:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.graphics.Color)398@18117L6,399@18183L6,400@18239L8,401@18290L6,402@18355L6,405@18526L6,408@18739L33:Slider.kt#jmzs0o");
        long jM1242getPrimary0d7_KjU = (i3 & 1) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1242getPrimary0d7_KjU() : j;
        long jM1922compositeOverOWjLjI = (i3 & 2) != 0 ? ColorKt.m1922compositeOverOWjLjI(Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), ContentAlpha.INSTANCE.getDisabled(composer, 0), 0.0f, 0.0f, 0.0f, 14, null), MaterialTheme.INSTANCE.getColors(composer, 0).m1246getSurface0d7_KjU()) : j2;
        long jM1242getPrimary0d7_KjU2 = (i3 & 4) != 0 ? MaterialTheme.INSTANCE.getColors(composer, 0).m1242getPrimary0d7_KjU() : j3;
        long jM1876copywmQWz5c$default = (i3 & 8) != 0 ? Color.m1876copywmQWz5c$default(jM1242getPrimary0d7_KjU2, 0.24f, 0.0f, 0.0f, 0.0f, 14, null) : j4;
        long jM1876copywmQWz5c$default2 = (i3 & 16) != 0 ? Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1241getOnSurface0d7_KjU(), 0.32f, 0.0f, 0.0f, 0.0f, 14, null) : j5;
        long jM1876copywmQWz5c$default3 = (i3 & 32) != 0 ? Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default2, 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : j6;
        long jM1876copywmQWz5c$default4 = (i3 & 64) != 0 ? Color.m1876copywmQWz5c$default(ColorsKt.m1260contentColorForek8zF_U(jM1242getPrimary0d7_KjU2, composer, (i >> 6) & 14), 0.54f, 0.0f, 0.0f, 0.0f, 14, null) : j7;
        DefaultSliderColors defaultSliderColors = new DefaultSliderColors(jM1242getPrimary0d7_KjU, jM1922compositeOverOWjLjI, jM1242getPrimary0d7_KjU2, jM1876copywmQWz5c$default, jM1876copywmQWz5c$default2, jM1876copywmQWz5c$default3, jM1876copywmQWz5c$default4, (i3 & 128) != 0 ? Color.m1876copywmQWz5c$default(jM1242getPrimary0d7_KjU2, 0.54f, 0.0f, 0.0f, 0.0f, 14, null) : j8, (i3 & 256) != 0 ? Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default4, 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : j9, (i3 & 512) != 0 ? Color.m1876copywmQWz5c$default(jM1876copywmQWz5c$default3, 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : j10, null);
        composer.endReplaceableGroup();
        return defaultSliderColors;
    }
}
