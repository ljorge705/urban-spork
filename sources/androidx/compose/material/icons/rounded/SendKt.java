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

/* compiled from: Send.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_send", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Send", "Landroidx/compose/material/icons/Icons$Rounded;", "getSend", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-core_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SendKt {
    private static ImageVector _send;

    public static final ImageVector getSend(Icons.Rounded rounded) {
        Intrinsics.checkNotNullParameter(rounded, "<this>");
        ImageVector imageVector = _send;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Send", Dp.m4390constructorimpl(24.0f), Dp.m4390constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m1903getBlack0d7_KjU(), null);
        int iM2220getButtKaPHkGw = StrokeCap.INSTANCE.m2220getButtKaPHkGw();
        int iM2230getBevelLxFBmk8 = StrokeJoin.INSTANCE.m2230getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(3.0f, 5.51f);
        pathBuilder.verticalLineToRelative(3.71f);
        pathBuilder.curveToRelative(0.0f, 0.46f, 0.31f, 0.86f, 0.76f, 0.97f);
        pathBuilder.lineTo(11.0f, 12.0f);
        pathBuilder.lineToRelative(-7.24f, 1.81f);
        pathBuilder.curveTo(3.31f, 13.92f, 3.0f, 14.32f, 3.0f, 14.78f);
        pathBuilder.verticalLineToRelative(3.71f);
        pathBuilder.curveToRelative(0.0f, 0.72f, 0.73f, 1.2f, 1.39f, 0.92f);
        pathBuilder.lineToRelative(15.42f, -6.49f);
        pathBuilder.curveToRelative(0.82f, -0.34f, 0.82f, -1.5f, 0.0f, -1.84f);
        pathBuilder.lineTo(4.39f, 4.58f);
        pathBuilder.curveTo(3.73f, 4.31f, 3.0f, 4.79f, 3.0f, 5.51f);
        pathBuilder.close();
        Unit unit = Unit.INSTANCE;
        ImageVector imageVectorBuild = ImageVector.Builder.m2498addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM2220getButtKaPHkGw, iM2230getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _send = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
