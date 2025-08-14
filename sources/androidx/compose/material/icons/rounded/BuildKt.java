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

/* compiled from: Build.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_build", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Build", "Landroidx/compose/material/icons/Icons$Rounded;", "getBuild", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-core_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class BuildKt {
    private static ImageVector _build;

    public static final ImageVector getBuild(Icons.Rounded rounded) {
        Intrinsics.checkNotNullParameter(rounded, "<this>");
        ImageVector imageVector = _build;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Build", Dp.m4390constructorimpl(24.0f), Dp.m4390constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m1903getBlack0d7_KjU(), null);
        int iM2220getButtKaPHkGw = StrokeCap.INSTANCE.m2220getButtKaPHkGw();
        int iM2230getBevelLxFBmk8 = StrokeJoin.INSTANCE.m2230getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(19.65f, 16.11f);
        pathBuilder.lineToRelative(-5.03f, -5.03f);
        pathBuilder.curveToRelative(0.42f, -1.14f, 0.53f, -2.43f, 0.13f, -3.78f);
        pathBuilder.curveToRelative(-0.61f, -2.08f, -2.34f, -3.72f, -4.45f, -4.17f);
        pathBuilder.curveTo(8.61f, 2.78f, 7.01f, 3.14f, 5.74f, 3.97f);
        pathBuilder.lineToRelative(3.64f, 3.64f);
        pathBuilder.lineTo(7.61f, 9.38f);
        pathBuilder.lineTo(3.97f, 5.74f);
        pathBuilder.curveTo(3.14f, 7.01f, 2.78f, 8.61f, 3.14f, 10.3f);
        pathBuilder.curveToRelative(0.45f, 2.12f, 2.09f, 3.85f, 4.16f, 4.45f);
        pathBuilder.curveToRelative(1.36f, 0.4f, 2.65f, 0.29f, 3.78f, -0.13f);
        pathBuilder.lineToRelative(5.03f, 5.03f);
        pathBuilder.curveToRelative(0.98f, 0.98f, 2.56f, 0.98f, 3.54f, 0.0f);
        pathBuilder.lineToRelative(0.0f, 0.0f);
        pathBuilder.curveTo(20.62f, 18.67f, 20.62f, 17.09f, 19.65f, 16.11f);
        pathBuilder.close();
        Unit unit = Unit.INSTANCE;
        ImageVector imageVectorBuild = ImageVector.Builder.m2498addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM2220getButtKaPHkGw, iM2230getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _build = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
