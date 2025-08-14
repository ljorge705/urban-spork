package androidx.compose.animation;

import androidx.compose.animation.core.AnimationVector4D;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: ColorVectorConverter.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u001a0\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\nH\u0002\"8\u0010\u0000\u001a)\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0001X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\"?\u0010\f\u001a)\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0001*\u00020\r8Fø\u0001\u0000¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"ColorToVector", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "Lkotlin/ParameterName;", "name", "colorSpace", "Landroidx/compose/animation/core/TwoWayConverter;", "Landroidx/compose/ui/graphics/Color;", "Landroidx/compose/animation/core/AnimationVector4D;", "InverseM1", "", "M1", "VectorConverter", "Landroidx/compose/ui/graphics/Color$Companion;", "getVectorConverter", "(Landroidx/compose/ui/graphics/Color$Companion;)Lkotlin/jvm/functions/Function1;", "multiplyColumn", "", "column", "", "x", "y", "z", "matrix", "animation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ColorVectorConverterKt {
    private static final Function1<ColorSpace, TwoWayConverter<Color, AnimationVector4D>> ColorToVector = new Function1<ColorSpace, TwoWayConverter<Color, AnimationVector4D>>() { // from class: androidx.compose.animation.ColorVectorConverterKt$ColorToVector$1
        @Override // kotlin.jvm.functions.Function1
        public final TwoWayConverter<Color, AnimationVector4D> invoke(final ColorSpace colorSpace) {
            Intrinsics.checkNotNullParameter(colorSpace, "colorSpace");
            return VectorConvertersKt.TwoWayConverter(new Function1<Color, AnimationVector4D>() { // from class: androidx.compose.animation.ColorVectorConverterKt$ColorToVector$1.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ AnimationVector4D invoke(Color color) {
                    return m316invoke8_81llA(color.m1887unboximpl());
                }

                /* renamed from: invoke-8_81llA, reason: not valid java name */
                public final AnimationVector4D m316invoke8_81llA(long j) {
                    long jM1874convertvNxB06k = Color.m1874convertvNxB06k(j, ColorSpaces.INSTANCE.getCieXyz());
                    float fM1883getRedimpl = Color.m1883getRedimpl(jM1874convertvNxB06k);
                    float fM1882getGreenimpl = Color.m1882getGreenimpl(jM1874convertvNxB06k);
                    float fM1880getBlueimpl = Color.m1880getBlueimpl(jM1874convertvNxB06k);
                    double d = 0.33333334f;
                    return new AnimationVector4D(Color.m1879getAlphaimpl(j), (float) Math.pow(ColorVectorConverterKt.multiplyColumn(0, fM1883getRedimpl, fM1882getGreenimpl, fM1880getBlueimpl, ColorVectorConverterKt.M1), d), (float) Math.pow(ColorVectorConverterKt.multiplyColumn(1, fM1883getRedimpl, fM1882getGreenimpl, fM1880getBlueimpl, ColorVectorConverterKt.M1), d), (float) Math.pow(ColorVectorConverterKt.multiplyColumn(2, fM1883getRedimpl, fM1882getGreenimpl, fM1880getBlueimpl, ColorVectorConverterKt.M1), d));
                }
            }, new Function1<AnimationVector4D, Color>() { // from class: androidx.compose.animation.ColorVectorConverterKt$ColorToVector$1.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Color invoke(AnimationVector4D animationVector4D) {
                    return Color.m1867boximpl(m317invokevNxB06k(animationVector4D));
                }

                /* renamed from: invoke-vNxB06k, reason: not valid java name */
                public final long m317invokevNxB06k(AnimationVector4D it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    double d = 3.0f;
                    float fPow = (float) Math.pow(it.getV2(), d);
                    float fPow2 = (float) Math.pow(it.getV3(), d);
                    float fPow3 = (float) Math.pow(it.getV4(), d);
                    return Color.m1874convertvNxB06k(ColorKt.Color(RangesKt.coerceIn(ColorVectorConverterKt.multiplyColumn(0, fPow, fPow2, fPow3, ColorVectorConverterKt.InverseM1), -2.0f, 2.0f), RangesKt.coerceIn(ColorVectorConverterKt.multiplyColumn(1, fPow, fPow2, fPow3, ColorVectorConverterKt.InverseM1), -2.0f, 2.0f), RangesKt.coerceIn(ColorVectorConverterKt.multiplyColumn(2, fPow, fPow2, fPow3, ColorVectorConverterKt.InverseM1), -2.0f, 2.0f), RangesKt.coerceIn(it.getV1(), 0.0f, 1.0f), ColorSpaces.INSTANCE.getCieXyz()), colorSpace);
                }
            });
        }
    };
    private static final float[] M1 = {0.80405736f, 0.026893456f, 0.04586542f, 0.3188387f, 0.9319606f, 0.26299807f, -0.11419419f, 0.05105356f, 0.83999807f};
    private static final float[] InverseM1 = {1.2485008f, -0.032856926f, -0.057883114f, -0.48331892f, 1.1044513f, -0.3194066f, 0.19910365f, -0.07159331f, 1.202023f};

    public static final Function1<ColorSpace, TwoWayConverter<Color, AnimationVector4D>> getVectorConverter(Color.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return ColorToVector;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float multiplyColumn(int i, float f, float f2, float f3, float[] fArr) {
        return (f * fArr[i]) + (f2 * fArr[i + 3]) + (f3 * fArr[i + 6]);
    }
}
