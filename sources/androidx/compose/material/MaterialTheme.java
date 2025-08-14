package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import com.BV.LinearGradient.LinearGradientManager;
import kotlin.Metadata;

/* compiled from: MaterialTheme.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b8G¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8G¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/compose/material/MaterialTheme;", "", "()V", LinearGradientManager.PROP_COLORS, "Landroidx/compose/material/Colors;", "getColors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material/Colors;", "shapes", "Landroidx/compose/material/Shapes;", "getShapes", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material/Shapes;", "typography", "Landroidx/compose/material/Typography;", "getTypography", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material/Typography;", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MaterialTheme {
    public static final int $stable = 0;
    public static final MaterialTheme INSTANCE = new MaterialTheme();

    private MaterialTheme() {
    }

    public final Colors getColors(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1431171313, "C97@4338L7:MaterialTheme.kt#jmzs0o");
        ProvidableCompositionLocal<Colors> localColors = ColorsKt.getLocalColors();
        ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localColors);
        ComposerKt.sourceInformationMarkerEnd(composer);
        Colors colors = (Colors) objConsume;
        ComposerKt.sourceInformationMarkerEnd(composer);
        return colors;
    }

    public final Typography getTypography(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, -137609831, "C107@4639L7:MaterialTheme.kt#jmzs0o");
        ProvidableCompositionLocal<Typography> localTypography = TypographyKt.getLocalTypography();
        ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localTypography);
        ComposerKt.sourceInformationMarkerEnd(composer);
        Typography typography = (Typography) objConsume;
        ComposerKt.sourceInformationMarkerEnd(composer);
        return typography;
    }

    public final Shapes getShapes(Composer composer, int i) {
        ComposerKt.sourceInformationMarkerStart(composer, 1314390512, "C115@4847L7:MaterialTheme.kt#jmzs0o");
        ProvidableCompositionLocal<Shapes> localShapes = ShapesKt.getLocalShapes();
        ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localShapes);
        ComposerKt.sourceInformationMarkerEnd(composer);
        Shapes shapes = (Shapes) objConsume;
        ComposerKt.sourceInformationMarkerEnd(composer);
        return shapes;
    }
}
