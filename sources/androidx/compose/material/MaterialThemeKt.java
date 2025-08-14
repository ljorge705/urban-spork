package androidx.compose.material;

import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material.ripple.RippleKt;
import androidx.compose.material.ripple.RippleThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import com.BV.LinearGradient.LinearGradientManager;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreRsaEcb;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MaterialTheme.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a>\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\nH\u0007¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"MaterialTheme", "", LinearGradientManager.PROP_COLORS, "Landroidx/compose/material/Colors;", "typography", "Landroidx/compose/material/Typography;", "shapes", "Landroidx/compose/material/Shapes;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/material/Colors;Landroidx/compose/material/Typography;Landroidx/compose/material/Shapes;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MaterialThemeKt {
    public static final void MaterialTheme(Colors colors, Typography typography, Shapes shapes, final Function2<? super Composer, ? super Integer, Unit> content, Composer composer, final int i, final int i2) {
        final Colors colors2;
        int i3;
        Typography typography2;
        Shapes shapes2;
        final Shapes shapes3;
        final Typography typography3;
        int i4;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1505114095);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(MaterialTheme)P(!1,3,2)59@2947L6,60@2998L10,61@3045L6,*64@3120L184,69@3367L16,70@3410L45,73@3581L4,71@3460L456:MaterialTheme.kt#jmzs0o");
        if ((i & 14) == 0) {
            if ((i2 & 1) == 0) {
                colors2 = colors;
                if (composerStartRestartGroup.changed(colors2)) {
                    i4 = 4;
                }
                i3 = i4 | i;
            } else {
                colors2 = colors;
            }
            i4 = 2;
            i3 = i4 | i;
        } else {
            colors2 = colors;
            i3 = i;
        }
        if ((i & 112) == 0) {
            if ((i2 & 2) == 0) {
                typography2 = typography;
                int i5 = composerStartRestartGroup.changed(typography2) ? 32 : 16;
                i3 |= i5;
            } else {
                typography2 = typography;
            }
            i3 |= i5;
        } else {
            typography2 = typography;
        }
        if ((i & 896) == 0) {
            if ((i2 & 4) == 0) {
                shapes2 = shapes;
                int i6 = composerStartRestartGroup.changed(shapes2) ? 256 : 128;
                i3 |= i6;
            } else {
                shapes2 = shapes;
            }
            i3 |= i6;
        } else {
            shapes2 = shapes;
        }
        if ((i2 & 8) != 0) {
            i3 |= CipherStorageKeystoreRsaEcb.ENCRYPTION_KEY_SIZE;
        } else if ((i & 7168) == 0) {
            i3 |= composerStartRestartGroup.changed(content) ? 2048 : 1024;
        }
        if (((i3 & 5851) ^ 1170) != 0 || !composerStartRestartGroup.getSkipping()) {
            if ((i & 1) != 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                composerStartRestartGroup.skipCurrentGroup();
                if ((i2 & 1) != 0) {
                    i3 &= -15;
                }
                if ((i2 & 2) != 0) {
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                }
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    colors2 = MaterialTheme.INSTANCE.getColors(composerStartRestartGroup, 0);
                    i3 &= -15;
                }
                if ((i2 & 2) != 0) {
                    typography2 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 0);
                    i3 &= -113;
                }
                if ((i2 & 4) != 0) {
                    shapes2 = MaterialTheme.INSTANCE.getShapes(composerStartRestartGroup, 0);
                    i3 &= -897;
                }
                composerStartRestartGroup.endDefaults();
            }
            final int i7 = i3;
            final Typography typography4 = typography2;
            Shapes shapes4 = shapes2;
            composerStartRestartGroup.startReplaceableGroup(-3687241);
            ComposerKt.sourceInformation(composerStartRestartGroup, "C(remember):Composables.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Colors colors3 = colors2;
                objRememberedValue = colors3.m1234copypvPzIIM((8191 & 1) != 0 ? colors3.m1242getPrimary0d7_KjU() : 0L, (8191 & 2) != 0 ? colors3.m1243getPrimaryVariant0d7_KjU() : 0L, (8191 & 4) != 0 ? colors3.m1244getSecondary0d7_KjU() : 0L, (8191 & 8) != 0 ? colors3.m1245getSecondaryVariant0d7_KjU() : 0L, (8191 & 16) != 0 ? colors3.m1235getBackground0d7_KjU() : 0L, (8191 & 32) != 0 ? colors3.m1246getSurface0d7_KjU() : 0L, (8191 & 64) != 0 ? colors3.m1236getError0d7_KjU() : 0L, (8191 & 128) != 0 ? colors3.m1239getOnPrimary0d7_KjU() : 0L, (8191 & 256) != 0 ? colors3.m1240getOnSecondary0d7_KjU() : 0L, (8191 & 512) != 0 ? colors3.m1237getOnBackground0d7_KjU() : 0L, (8191 & 1024) != 0 ? colors3.m1241getOnSurface0d7_KjU() : 0L, (8191 & 2048) != 0 ? colors3.m1238getOnError0d7_KjU() : 0L, (8191 & 4096) != 0 ? colors3.isLight() : false);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            Colors colors4 = (Colors) objRememberedValue;
            ColorsKt.updateColorsFrom(colors4, colors2);
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ColorsKt.getLocalColors().provides(colors4), ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(ContentAlpha.INSTANCE.getHigh(composerStartRestartGroup, 0))), IndicationKt.getLocalIndication().provides(RippleKt.m1486rememberRipple9IZ8Weo(false, 0.0f, 0L, composerStartRestartGroup, 0, 7)), RippleThemeKt.getLocalRippleTheme().provides(MaterialRippleTheme.INSTANCE), ShapesKt.getLocalShapes().provides(shapes4), TextSelectionColorsKt.getLocalTextSelectionColors().provides(MaterialTextSelectionColorsKt.rememberTextSelectionColors(colors4, composerStartRestartGroup, 0)), TypographyKt.getLocalTypography().provides(typography4)}, ComposableLambdaKt.composableLambda(composerStartRestartGroup, -819894159, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.MaterialThemeKt.MaterialTheme.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i8) {
                    ComposerKt.sourceInformation(composer2, "C80@3849L61:MaterialTheme.kt#jmzs0o");
                    if (((i8 & 11) ^ 2) == 0 && composer2.getSkipping()) {
                        composer2.skipToGroupEnd();
                    } else {
                        TextKt.ProvideTextStyle(typography4.getBody1(), content, composer2, (i7 >> 6) & 112);
                    }
                }
            }), composerStartRestartGroup, 56);
            shapes3 = shapes4;
            typography3 = typography4;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            typography3 = typography2;
            shapes3 = shapes2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.MaterialThemeKt.MaterialTheme.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i8) {
                MaterialThemeKt.MaterialTheme(colors2, typography3, shapes3, content, composer2, i | 1, i2);
            }
        });
    }
}
