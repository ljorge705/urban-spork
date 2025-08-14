package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: ElevationOverlay.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0017ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\t\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\n"}, d2 = {"Landroidx/compose/material/DefaultElevationOverlay;", "Landroidx/compose/material/ElevationOverlay;", "()V", "apply", "Landroidx/compose/ui/graphics/Color;", "color", ViewProps.ELEVATION, "Landroidx/compose/ui/unit/Dp;", "apply-7g2Lkgo", "(JFLandroidx/compose/runtime/Composer;I)J", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class DefaultElevationOverlay implements ElevationOverlay {
    public static final DefaultElevationOverlay INSTANCE = new DefaultElevationOverlay();

    private DefaultElevationOverlay() {
    }

    @Override // androidx.compose.material.ElevationOverlay
    /* renamed from: apply-7g2Lkgo, reason: not valid java name */
    public long mo1276apply7g2Lkgo(long j, float f, Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -1272525387, "C(apply)P(0:c#ui.graphics.Color,1:c#ui.unit.Dp)69@2742L6:ElevationOverlay.kt#jmzs0o");
        Colors colors = MaterialTheme.INSTANCE.getColors(composer, 0);
        if (Dp.m4389compareTo0680j_4(f, Dp.m4390constructorimpl(0)) > 0 && !colors.isLight()) {
            composer.startReplaceableGroup(-1272525241);
            ComposerKt.sourceInformation(composer, "71@2841L42");
            j = ColorKt.m1922compositeOverOWjLjI(ElevationOverlayKt.m1303calculateForegroundColorCLU3JFs(j, f, composer, (i & 112) | (i & 14)), j);
            composer.endReplaceableGroup();
        } else {
            composer.startReplaceableGroup(-1272525098);
            composer.endReplaceableGroup();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return j;
    }
}
