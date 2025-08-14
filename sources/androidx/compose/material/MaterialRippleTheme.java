package androidx.compose.material;

import androidx.compose.material.ripple.RippleAlpha;
import androidx.compose.material.ripple.RippleTheme;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import kotlin.Metadata;

/* compiled from: MaterialTheme.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u0004H\u0017ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\r\u0010\u0007\u001a\u00020\bH\u0017¢\u0006\u0002\u0010\t\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\n"}, d2 = {"Landroidx/compose/material/MaterialRippleTheme;", "Landroidx/compose/material/ripple/RippleTheme;", "()V", "defaultColor", "Landroidx/compose/ui/graphics/Color;", "defaultColor-WaAFU9c", "(Landroidx/compose/runtime/Composer;I)J", "rippleAlpha", "Landroidx/compose/material/ripple/RippleAlpha;", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material/ripple/RippleAlpha;", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
final class MaterialRippleTheme implements RippleTheme {
    public static final MaterialRippleTheme INSTANCE = new MaterialRippleTheme();

    private MaterialRippleTheme() {
    }

    @Override // androidx.compose.material.ripple.RippleTheme
    /* renamed from: defaultColor-WaAFU9c, reason: not valid java name */
    public long mo1326defaultColorWaAFU9c(Composer composer, int i) {
        composer.startReplaceableGroup(-1141625935);
        ComposerKt.sourceInformation(composer, "C(defaultColor)122@5043L7,123@5087L6:MaterialTheme.kt#jmzs0o");
        RippleTheme.Companion companion = RippleTheme.INSTANCE;
        ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
        ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localContentColor);
        ComposerKt.sourceInformationMarkerEnd(composer);
        long jM1488defaultRippleColor5vOe2sY = companion.m1488defaultRippleColor5vOe2sY(((Color) objConsume).m1887unboximpl(), MaterialTheme.INSTANCE.getColors(composer, 0).isLight());
        composer.endReplaceableGroup();
        return jM1488defaultRippleColor5vOe2sY;
    }

    @Override // androidx.compose.material.ripple.RippleTheme
    public RippleAlpha rippleAlpha(Composer composer, int i) {
        composer.startReplaceableGroup(929632070);
        ComposerKt.sourceInformation(composer, "C(rippleAlpha)128@5231L7,129@5275L6:MaterialTheme.kt#jmzs0o");
        RippleTheme.Companion companion = RippleTheme.INSTANCE;
        ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
        ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localContentColor);
        ComposerKt.sourceInformationMarkerEnd(composer);
        RippleAlpha rippleAlphaM1487defaultRippleAlphaDxMtmZc = companion.m1487defaultRippleAlphaDxMtmZc(((Color) objConsume).m1887unboximpl(), MaterialTheme.INSTANCE.getColors(composer, 0).isLight());
        composer.endReplaceableGroup();
        return rippleAlphaM1487defaultRippleAlphaDxMtmZc;
    }
}
