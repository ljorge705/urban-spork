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

/* compiled from: Email.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_email", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Email", "Landroidx/compose/material/icons/Icons$Rounded;", "getEmail", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-core_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class EmailKt {
    private static ImageVector _email;

    public static final ImageVector getEmail(Icons.Rounded rounded) {
        Intrinsics.checkNotNullParameter(rounded, "<this>");
        ImageVector imageVector = _email;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Email", Dp.m4390constructorimpl(24.0f), Dp.m4390constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m1903getBlack0d7_KjU(), null);
        int iM2220getButtKaPHkGw = StrokeCap.INSTANCE.m2220getButtKaPHkGw();
        int iM2230getBevelLxFBmk8 = StrokeJoin.INSTANCE.m2230getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(20.0f, 4.0f);
        pathBuilder.horizontalLineTo(4.0f);
        pathBuilder.curveTo(2.9f, 4.0f, 2.01f, 4.9f, 2.01f, 6.0f);
        pathBuilder.lineTo(2.0f, 18.0f);
        pathBuilder.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder.horizontalLineToRelative(16.0f);
        pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.verticalLineTo(6.0f);
        pathBuilder.curveTo(22.0f, 4.9f, 21.1f, 4.0f, 20.0f, 4.0f);
        pathBuilder.close();
        pathBuilder.moveTo(19.6f, 8.25f);
        pathBuilder.lineToRelative(-7.07f, 4.42f);
        pathBuilder.curveToRelative(-0.32f, 0.2f, -0.74f, 0.2f, -1.06f, 0.0f);
        pathBuilder.lineTo(4.4f, 8.25f);
        pathBuilder.curveTo(4.15f, 8.09f, 4.0f, 7.82f, 4.0f, 7.53f);
        pathBuilder.curveToRelative(0.0f, -0.67f, 0.73f, -1.07f, 1.3f, -0.72f);
        pathBuilder.lineTo(12.0f, 11.0f);
        pathBuilder.lineToRelative(6.7f, -4.19f);
        pathBuilder.curveTo(19.27f, 6.46f, 20.0f, 6.86f, 20.0f, 7.53f);
        pathBuilder.curveTo(20.0f, 7.82f, 19.85f, 8.09f, 19.6f, 8.25f);
        pathBuilder.close();
        Unit unit = Unit.INSTANCE;
        ImageVector imageVectorBuild = ImageVector.Builder.m2498addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM2220getButtKaPHkGw, iM2230getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _email = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
