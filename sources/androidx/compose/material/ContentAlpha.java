package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;

/* compiled from: ContentAlpha.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0003¢\u0006\u0002\u0010\u000eR\u0011\u0010\u0003\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u00048G¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006¨\u0006\u000f"}, d2 = {"Landroidx/compose/material/ContentAlpha;", "", "()V", "disabled", "", "getDisabled", "(Landroidx/compose/runtime/Composer;I)F", "high", "getHigh", "medium", "getMedium", "contentAlpha", "highContrastAlpha", "lowContrastAlpha", "(FFLandroidx/compose/runtime/Composer;I)F", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ContentAlpha {
    public static final int $stable = 0;
    public static final ContentAlpha INSTANCE = new ContentAlpha();

    private ContentAlpha() {
    }

    public final float getHigh(Composer composer, int i) {
        composer.startReplaceableGroup(-1305244065);
        ComposerKt.sourceInformation(composer, "C34@1107L146:ContentAlpha.kt#jmzs0o");
        float fContentAlpha = contentAlpha(1.0f, 0.87f, composer, ((i << 6) & 896) | 54);
        composer.endReplaceableGroup();
        return fContentAlpha;
    }

    public final float getMedium(Composer composer, int i) {
        composer.startReplaceableGroup(575700177);
        ComposerKt.sourceInformation(composer, "C45@1458L150:ContentAlpha.kt#jmzs0o");
        float fContentAlpha = contentAlpha(0.74f, 0.6f, composer, ((i << 6) & 896) | 54);
        composer.endReplaceableGroup();
        return fContentAlpha;
    }

    public final float getDisabled(Composer composer, int i) {
        composer.startReplaceableGroup(-651892877);
        ComposerKt.sourceInformation(composer, "C56@1805L154:ContentAlpha.kt#jmzs0o");
        float fContentAlpha = contentAlpha(0.38f, 0.38f, composer, ((i << 6) & 896) | 54);
        composer.endReplaceableGroup();
        return fContentAlpha;
    }

    private final float contentAlpha(float f, float f2, Composer composer, int i) {
        composer.startReplaceableGroup(-1499253717);
        ComposerKt.sourceInformation(composer, "C(contentAlpha)76@2623L7,77@2670L6:ContentAlpha.kt#jmzs0o");
        ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
        ComposerKt.sourceInformationMarkerStart(composer, 103361330, "C:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localContentColor);
        ComposerKt.sourceInformationMarkerEnd(composer);
        long jM1887unboximpl = ((Color) objConsume).m1887unboximpl();
        if (!MaterialTheme.INSTANCE.getColors(composer, 0).isLight() ? ColorKt.m1929luminance8_81llA(jM1887unboximpl) >= 0.5d : ColorKt.m1929luminance8_81llA(jM1887unboximpl) <= 0.5d) {
            f = f2;
        }
        composer.endReplaceableGroup();
        return f;
    }
}
