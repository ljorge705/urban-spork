package androidx.compose.material;

import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.CornerSizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* compiled from: BackdropScaffold.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\b\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\t\u0010\u0006R\u001c\u0010\n\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000b\u0010\u0006R\u001a\u0010\f\u001a\u00020\r8Gø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00118G¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0014"}, d2 = {"Landroidx/compose/material/BackdropScaffoldDefaults;", "", "()V", "FrontLayerElevation", "Landroidx/compose/ui/unit/Dp;", "getFrontLayerElevation-D9Ej5fM", "()F", "F", "HeaderHeight", "getHeaderHeight-D9Ej5fM", "PeekHeight", "getPeekHeight-D9Ej5fM", "frontLayerScrimColor", "Landroidx/compose/ui/graphics/Color;", "getFrontLayerScrimColor", "(Landroidx/compose/runtime/Composer;I)J", "frontLayerShape", "Landroidx/compose/ui/graphics/Shape;", "getFrontLayerShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "material_release"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class BackdropScaffoldDefaults {
    public static final int $stable = 0;
    public static final BackdropScaffoldDefaults INSTANCE = new BackdropScaffoldDefaults();
    private static final float PeekHeight = Dp.m4390constructorimpl(56);
    private static final float HeaderHeight = Dp.m4390constructorimpl(48);
    private static final float FrontLayerElevation = Dp.m4390constructorimpl(1);

    /* renamed from: getFrontLayerElevation-D9Ej5fM, reason: not valid java name */
    public final float m1169getFrontLayerElevationD9Ej5fM() {
        return FrontLayerElevation;
    }

    /* renamed from: getHeaderHeight-D9Ej5fM, reason: not valid java name */
    public final float m1170getHeaderHeightD9Ej5fM() {
        return HeaderHeight;
    }

    /* renamed from: getPeekHeight-D9Ej5fM, reason: not valid java name */
    public final float m1171getPeekHeightD9Ej5fM() {
        return PeekHeight;
    }

    private BackdropScaffoldDefaults() {
    }

    public final Shape getFrontLayerShape(Composer composer, int i) {
        composer.startReplaceableGroup(1422915872);
        ComposerKt.sourceInformation(composer, "C503@20260L6:BackdropScaffold.kt#jmzs0o");
        float f = 16;
        CornerBasedShape cornerBasedShapeCopy$default = CornerBasedShape.copy$default(MaterialTheme.INSTANCE.getShapes(composer, 0).getLarge(), CornerSizeKt.m952CornerSize0680j_4(Dp.m4390constructorimpl(f)), CornerSizeKt.m952CornerSize0680j_4(Dp.m4390constructorimpl(f)), null, null, 12, null);
        composer.endReplaceableGroup();
        return cornerBasedShapeCopy$default;
    }

    public final long getFrontLayerScrimColor(Composer composer, int i) {
        composer.startReplaceableGroup(-658199267);
        ComposerKt.sourceInformation(composer, "C515@20611L6:BackdropScaffold.kt#jmzs0o");
        long jM1876copywmQWz5c$default = Color.m1876copywmQWz5c$default(MaterialTheme.INSTANCE.getColors(composer, 0).m1246getSurface0d7_KjU(), 0.6f, 0.0f, 0.0f, 0.0f, 14, null);
        composer.endReplaceableGroup();
        return jM1876copywmQWz5c$default;
    }
}
