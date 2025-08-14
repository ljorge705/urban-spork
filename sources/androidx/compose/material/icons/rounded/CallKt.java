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

/* compiled from: Call.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_call", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Call", "Landroidx/compose/material/icons/Icons$Rounded;", "getCall", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-core_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class CallKt {
    private static ImageVector _call;

    public static final ImageVector getCall(Icons.Rounded rounded) {
        Intrinsics.checkNotNullParameter(rounded, "<this>");
        ImageVector imageVector = _call;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Call", Dp.m4390constructorimpl(24.0f), Dp.m4390constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m1903getBlack0d7_KjU(), null);
        int iM2220getButtKaPHkGw = StrokeCap.INSTANCE.m2220getButtKaPHkGw();
        int iM2230getBevelLxFBmk8 = StrokeJoin.INSTANCE.m2230getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(15.63f, 14.4f);
        pathBuilder.lineToRelative(-2.52f, 2.5f);
        pathBuilder.curveToRelative(-2.5f, -1.43f, -4.57f, -3.5f, -6.0f, -6.0f);
        pathBuilder.lineToRelative(2.5f, -2.52f);
        pathBuilder.curveToRelative(0.23f, -0.24f, 0.33f, -0.57f, 0.27f, -0.9f);
        pathBuilder.lineTo(9.13f, 3.8f);
        pathBuilder.curveTo(9.04f, 3.34f, 8.63f, 3.0f, 8.15f, 3.0f);
        pathBuilder.lineTo(4.0f, 3.0f);
        pathBuilder.curveTo(3.44f, 3.0f, 2.97f, 3.47f, 3.0f, 4.03f);
        pathBuilder.curveTo(3.17f, 6.92f, 4.05f, 9.63f, 5.43f, 12.0f);
        pathBuilder.curveToRelative(1.58f, 2.73f, 3.85f, 4.99f, 6.57f, 6.57f);
        pathBuilder.curveToRelative(2.37f, 1.37f, 5.08f, 2.26f, 7.97f, 2.43f);
        pathBuilder.curveToRelative(0.56f, 0.03f, 1.03f, -0.44f, 1.03f, -1.0f);
        pathBuilder.lineToRelative(0.0f, -4.15f);
        pathBuilder.curveToRelative(0.0f, -0.48f, -0.34f, -0.89f, -0.8f, -0.98f);
        pathBuilder.lineToRelative(-3.67f, -0.73f);
        pathBuilder.curveTo(16.2f, 14.07f, 15.86f, 14.17f, 15.63f, 14.4f);
        pathBuilder.close();
        Unit unit = Unit.INSTANCE;
        ImageVector imageVectorBuild = ImageVector.Builder.m2498addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM2220getButtKaPHkGw, iM2230getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _call = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
