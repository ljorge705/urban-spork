package androidx.compose.material.icons.rounded;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Star.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_star", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Star", "Landroidx/compose/material/icons/Icons$Rounded;", "getStar", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-core_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class StarKt {
    private static ImageVector _star;

    public static final ImageVector getStar(Icons.Rounded rounded) {
        Intrinsics.checkNotNullParameter(rounded, "<this>");
        ImageVector imageVector = _star;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Star", Dp.m4390constructorimpl(24.0f), Dp.m4390constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m1903getBlack0d7_KjU(), null);
        int iM2220getButtKaPHkGw = StrokeCap.INSTANCE.m2220getButtKaPHkGw();
        int iM2230getBevelLxFBmk8 = StrokeJoin.INSTANCE.m2230getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(14.43f, 10.0f);
        pathBuilder.lineToRelative(-1.47f, -4.84f);
        pathBuilder.curveToRelative(-0.29f, -0.95f, -1.63f, -0.95f, -1.91f, 0.0f);
        pathBuilder.lineTo(9.57f, 10.0f);
        pathBuilder.horizontalLineTo(5.12f);
        pathBuilder.curveToRelative(-0.97f, 0.0f, -1.37f, 1.25f, -0.58f, 1.81f);
        pathBuilder.lineToRelative(3.64f, 2.6f);
        pathBuilder.lineToRelative(-1.43f, 4.61f);
        pathBuilder.curveToRelative(-0.29f, 0.93f, 0.79f, 1.68f, 1.56f, 1.09f);
        pathBuilder.lineTo(12.0f, 17.31f);
        pathBuilder.lineToRelative(3.69f, 2.81f);
        pathBuilder.curveToRelative(0.77f, 0.59f, 1.85f, -0.16f, 1.56f, -1.09f);
        pathBuilder.lineToRelative(-1.43f, -4.61f);
        pathBuilder.lineToRelative(3.64f, -2.6f);
        pathBuilder.curveToRelative(0.79f, -0.57f, 0.39f, -1.81f, -0.58f, -1.81f);
        pathBuilder.horizontalLineTo(14.43f);
        pathBuilder.close();
        Unit unit = Unit.INSTANCE;
        ImageVector imageVectorBuild = ImageVector.Builder.m2498addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM2220getButtKaPHkGw, iM2230getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _star = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
