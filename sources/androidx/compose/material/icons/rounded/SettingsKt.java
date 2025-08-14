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

/* compiled from: Settings.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settings", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Settings", "Landroidx/compose/material/icons/Icons$Rounded;", "getSettings", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-core_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SettingsKt {
    private static ImageVector _settings;

    public static final ImageVector getSettings(Icons.Rounded rounded) {
        Intrinsics.checkNotNullParameter(rounded, "<this>");
        ImageVector imageVector = _settings;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Settings", Dp.m4390constructorimpl(24.0f), Dp.m4390constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m1903getBlack0d7_KjU(), null);
        int iM2220getButtKaPHkGw = StrokeCap.INSTANCE.m2220getButtKaPHkGw();
        int iM2230getBevelLxFBmk8 = StrokeJoin.INSTANCE.m2230getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(19.5f, 12.0f);
        pathBuilder.curveToRelative(0.0f, -0.23f, -0.01f, -0.45f, -0.03f, -0.68f);
        pathBuilder.lineToRelative(1.86f, -1.41f);
        pathBuilder.curveToRelative(0.4f, -0.3f, 0.51f, -0.86f, 0.26f, -1.3f);
        pathBuilder.lineToRelative(-1.87f, -3.23f);
        pathBuilder.curveToRelative(-0.25f, -0.44f, -0.79f, -0.62f, -1.25f, -0.42f);
        pathBuilder.lineToRelative(-2.15f, 0.91f);
        pathBuilder.curveToRelative(-0.37f, -0.26f, -0.76f, -0.49f, -1.17f, -0.68f);
        pathBuilder.lineToRelative(-0.29f, -2.31f);
        pathBuilder.curveTo(14.8f, 2.38f, 14.37f, 2.0f, 13.87f, 2.0f);
        pathBuilder.horizontalLineToRelative(-3.73f);
        pathBuilder.curveTo(9.63f, 2.0f, 9.2f, 2.38f, 9.14f, 2.88f);
        pathBuilder.lineTo(8.85f, 5.19f);
        pathBuilder.curveToRelative(-0.41f, 0.19f, -0.8f, 0.42f, -1.17f, 0.68f);
        pathBuilder.lineTo(5.53f, 4.96f);
        pathBuilder.curveToRelative(-0.46f, -0.2f, -1.0f, -0.02f, -1.25f, 0.42f);
        pathBuilder.lineTo(2.41f, 8.62f);
        pathBuilder.curveToRelative(-0.25f, 0.44f, -0.14f, 0.99f, 0.26f, 1.3f);
        pathBuilder.lineToRelative(1.86f, 1.41f);
        pathBuilder.curveTo(4.51f, 11.55f, 4.5f, 11.77f, 4.5f, 12.0f);
        pathBuilder.reflectiveCurveToRelative(0.01f, 0.45f, 0.03f, 0.68f);
        pathBuilder.lineToRelative(-1.86f, 1.41f);
        pathBuilder.curveToRelative(-0.4f, 0.3f, -0.51f, 0.86f, -0.26f, 1.3f);
        pathBuilder.lineToRelative(1.87f, 3.23f);
        pathBuilder.curveToRelative(0.25f, 0.44f, 0.79f, 0.62f, 1.25f, 0.42f);
        pathBuilder.lineToRelative(2.15f, -0.91f);
        pathBuilder.curveToRelative(0.37f, 0.26f, 0.76f, 0.49f, 1.17f, 0.68f);
        pathBuilder.lineToRelative(0.29f, 2.31f);
        pathBuilder.curveTo(9.2f, 21.62f, 9.63f, 22.0f, 10.13f, 22.0f);
        pathBuilder.horizontalLineToRelative(3.73f);
        pathBuilder.curveToRelative(0.5f, 0.0f, 0.93f, -0.38f, 0.99f, -0.88f);
        pathBuilder.lineToRelative(0.29f, -2.31f);
        pathBuilder.curveToRelative(0.41f, -0.19f, 0.8f, -0.42f, 1.17f, -0.68f);
        pathBuilder.lineToRelative(2.15f, 0.91f);
        pathBuilder.curveToRelative(0.46f, 0.2f, 1.0f, 0.02f, 1.25f, -0.42f);
        pathBuilder.lineToRelative(1.87f, -3.23f);
        pathBuilder.curveToRelative(0.25f, -0.44f, 0.14f, -0.99f, -0.26f, -1.3f);
        pathBuilder.lineToRelative(-1.86f, -1.41f);
        pathBuilder.curveTo(19.49f, 12.45f, 19.5f, 12.23f, 19.5f, 12.0f);
        pathBuilder.close();
        pathBuilder.moveTo(12.04f, 15.5f);
        pathBuilder.curveToRelative(-1.93f, 0.0f, -3.5f, -1.57f, -3.5f, -3.5f);
        pathBuilder.reflectiveCurveToRelative(1.57f, -3.5f, 3.5f, -3.5f);
        pathBuilder.reflectiveCurveToRelative(3.5f, 1.57f, 3.5f, 3.5f);
        pathBuilder.reflectiveCurveTo(13.97f, 15.5f, 12.04f, 15.5f);
        pathBuilder.close();
        Unit unit = Unit.INSTANCE;
        ImageVector imageVectorBuild = ImageVector.Builder.m2498addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM2220getButtKaPHkGw, iM2230getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settings = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
