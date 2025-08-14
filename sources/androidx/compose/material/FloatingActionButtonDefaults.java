package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.unit.Dp;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: FloatingActionButton.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J)\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\t\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\n"}, d2 = {"Landroidx/compose/material/FloatingActionButtonDefaults;", "", "()V", ViewProps.ELEVATION, "Landroidx/compose/material/FloatingActionButtonElevation;", "defaultElevation", "Landroidx/compose/ui/unit/Dp;", "pressedElevation", "elevation-ixp7dh8", "(FFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/FloatingActionButtonElevation;", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class FloatingActionButtonDefaults {
    public static final int $stable = 0;
    public static final FloatingActionButtonDefaults INSTANCE = new FloatingActionButtonDefaults();

    private FloatingActionButtonDefaults() {
    }

    /* renamed from: elevation-ixp7dh8, reason: not valid java name */
    public final FloatingActionButtonElevation m1317elevationixp7dh8(float f, float f2, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(795786825);
        ComposerKt.sourceInformation(composer, "C(elevation)P(0:c#ui.unit.Dp,1:c#ui.unit.Dp)229@9835L225:FloatingActionButton.kt#jmzs0o");
        if ((i2 & 1) != 0) {
            f = Dp.m4390constructorimpl(6);
        }
        if ((i2 & 2) != 0) {
            f2 = Dp.m4390constructorimpl(12);
        }
        Dp dpM4388boximpl = Dp.m4388boximpl(f);
        Dp dpM4388boximpl2 = Dp.m4388boximpl(f2);
        composer.startReplaceableGroup(-3686552);
        ComposerKt.sourceInformation(composer, "C(remember)P(1,2):Composables.kt#9igjgp");
        boolean zChanged = composer.changed(dpM4388boximpl) | composer.changed(dpM4388boximpl2);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new DefaultFloatingActionButtonElevation(f, f2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        return (DefaultFloatingActionButtonElevation) objRememberedValue;
    }
}
