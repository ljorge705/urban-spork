package androidx.compose.ui.graphics;

import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.os.Build;
import androidx.compose.ui.geometry.Offset;
import androidx.media3.common.MimeTypes;
import com.BV.LinearGradient.LinearGradientManager;
import com.google.firebase.messaging.Constants;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidShader.android.kt */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\t\u001aO\u0010\n\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000f2\u0006\u0010\u0013\u001a\u00020\u0006H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0014\u0010\u0015\u001aO\u0010\u0016\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00122\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000f2\u0006\u0010\u0013\u001a\u00020\u0006H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0019\u0010\u001a\u001a?\u0010\u001b\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0017\u001a\u00020\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000fH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u0019\u0010\u001e\u001a\u00020\u001f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0001ø\u0001\u0001\u001a!\u0010 \u001a\u00020!2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\"\u001a\u00020\u001fH\u0001ø\u0001\u0001\u001a3\u0010#\u001a\u0004\u0018\u00010$2\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\"\u001a\u00020\u001fH\u0001ø\u0001\u0001\u001a)\u0010&\u001a\u00020'2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u000fH\u0002ø\u0001\u0001*\n\u0010(\"\u00020\u00012\u00020\u0001\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006)"}, d2 = {"ActualImageShader", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", MimeTypes.BASE_TYPE_IMAGE, "Landroidx/compose/ui/graphics/ImageBitmap;", "tileModeX", "Landroidx/compose/ui/graphics/TileMode;", "tileModeY", "ActualImageShader-F49vj9s", "(Landroidx/compose/ui/graphics/ImageBitmap;II)Landroid/graphics/Shader;", "ActualLinearGradientShader", Constants.MessagePayloadKeys.FROM, "Landroidx/compose/ui/geometry/Offset;", "to", LinearGradientManager.PROP_COLORS, "", "Landroidx/compose/ui/graphics/Color;", "colorStops", "", "tileMode", "ActualLinearGradientShader-VjE6UOU", "(JJLjava/util/List;Ljava/util/List;I)Landroid/graphics/Shader;", "ActualRadialGradientShader", "center", com.clevertap.android.sdk.Constants.KEY_RADIUS, "ActualRadialGradientShader-8uybcMk", "(JFLjava/util/List;Ljava/util/List;I)Landroid/graphics/Shader;", "ActualSweepGradientShader", "ActualSweepGradientShader-9KIMszo", "(JLjava/util/List;Ljava/util/List;)Landroid/graphics/Shader;", "countTransparentColors", "", "makeTransparentColors", "", "numTransparentColors", "makeTransparentStops", "", "stops", "validateColorStops", "", "Shader", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidShader_androidKt {
    /* renamed from: ActualLinearGradientShader-VjE6UOU, reason: not valid java name */
    public static final Shader m1780ActualLinearGradientShaderVjE6UOU(long j, long j2, List<Color> colors, List<Float> list, int i) {
        Intrinsics.checkNotNullParameter(colors, "colors");
        validateColorStops(colors, list);
        int iCountTransparentColors = countTransparentColors(colors);
        return new android.graphics.LinearGradient(Offset.m1639getXimpl(j), Offset.m1640getYimpl(j), Offset.m1639getXimpl(j2), Offset.m1640getYimpl(j2), makeTransparentColors(colors, iCountTransparentColors), makeTransparentStops(list, colors, iCountTransparentColors), AndroidTileMode_androidKt.m1784toAndroidTileMode0vamqd0(i));
    }

    /* renamed from: ActualRadialGradientShader-8uybcMk, reason: not valid java name */
    public static final Shader m1781ActualRadialGradientShader8uybcMk(long j, float f, List<Color> colors, List<Float> list, int i) {
        Intrinsics.checkNotNullParameter(colors, "colors");
        validateColorStops(colors, list);
        int iCountTransparentColors = countTransparentColors(colors);
        return new android.graphics.RadialGradient(Offset.m1639getXimpl(j), Offset.m1640getYimpl(j), f, makeTransparentColors(colors, iCountTransparentColors), makeTransparentStops(list, colors, iCountTransparentColors), AndroidTileMode_androidKt.m1784toAndroidTileMode0vamqd0(i));
    }

    /* renamed from: ActualSweepGradientShader-9KIMszo, reason: not valid java name */
    public static final Shader m1782ActualSweepGradientShader9KIMszo(long j, List<Color> colors, List<Float> list) {
        Intrinsics.checkNotNullParameter(colors, "colors");
        validateColorStops(colors, list);
        int iCountTransparentColors = countTransparentColors(colors);
        return new android.graphics.SweepGradient(Offset.m1639getXimpl(j), Offset.m1640getYimpl(j), makeTransparentColors(colors, iCountTransparentColors), makeTransparentStops(list, colors, iCountTransparentColors));
    }

    /* renamed from: ActualImageShader-F49vj9s, reason: not valid java name */
    public static final Shader m1779ActualImageShaderF49vj9s(ImageBitmap image, int i, int i2) {
        Intrinsics.checkNotNullParameter(image, "image");
        return new BitmapShader(AndroidImageBitmap_androidKt.asAndroidBitmap(image), AndroidTileMode_androidKt.m1784toAndroidTileMode0vamqd0(i), AndroidTileMode_androidKt.m1784toAndroidTileMode0vamqd0(i2));
    }

    public static final int countTransparentColors(List<Color> colors) {
        Intrinsics.checkNotNullParameter(colors, "colors");
        int i = 0;
        if (Build.VERSION.SDK_INT >= 26) {
            return 0;
        }
        int lastIndex = CollectionsKt.getLastIndex(colors);
        for (int i2 = 1; i2 < lastIndex; i2++) {
            if (Color.m1879getAlphaimpl(colors.get(i2).m1887unboximpl()) == 0.0f) {
                i++;
            }
        }
        return i;
    }

    public static final int[] makeTransparentColors(List<Color> colors, int i) {
        int i2;
        Intrinsics.checkNotNullParameter(colors, "colors");
        int i3 = 0;
        if (Build.VERSION.SDK_INT >= 26) {
            int size = colors.size();
            int[] iArr = new int[size];
            while (i3 < size) {
                iArr[i3] = ColorKt.m1931toArgb8_81llA(colors.get(i3).m1887unboximpl());
                i3++;
            }
            return iArr;
        }
        int[] iArr2 = new int[colors.size() + i];
        int lastIndex = CollectionsKt.getLastIndex(colors);
        int size2 = colors.size();
        int i4 = 0;
        while (i3 < size2) {
            long jM1887unboximpl = colors.get(i3).m1887unboximpl();
            if (Color.m1879getAlphaimpl(jM1887unboximpl) == 0.0f) {
                if (i3 == 0) {
                    i2 = i4 + 1;
                    iArr2[i4] = ColorKt.m1931toArgb8_81llA(Color.m1876copywmQWz5c$default(colors.get(1).m1887unboximpl(), 0.0f, 0.0f, 0.0f, 0.0f, 14, null));
                } else if (i3 == lastIndex) {
                    i2 = i4 + 1;
                    iArr2[i4] = ColorKt.m1931toArgb8_81llA(Color.m1876copywmQWz5c$default(colors.get(i3 - 1).m1887unboximpl(), 0.0f, 0.0f, 0.0f, 0.0f, 14, null));
                } else {
                    int i5 = i4 + 1;
                    iArr2[i4] = ColorKt.m1931toArgb8_81llA(Color.m1876copywmQWz5c$default(colors.get(i3 - 1).m1887unboximpl(), 0.0f, 0.0f, 0.0f, 0.0f, 14, null));
                    i4 += 2;
                    iArr2[i5] = ColorKt.m1931toArgb8_81llA(Color.m1876copywmQWz5c$default(colors.get(i3 + 1).m1887unboximpl(), 0.0f, 0.0f, 0.0f, 0.0f, 14, null));
                }
                i4 = i2;
            } else {
                iArr2[i4] = ColorKt.m1931toArgb8_81llA(jM1887unboximpl);
                i4++;
            }
            i3++;
        }
        return iArr2;
    }

    public static final float[] makeTransparentStops(List<Float> list, List<Color> colors, int i) {
        Intrinsics.checkNotNullParameter(colors, "colors");
        if (i == 0) {
            if (list != null) {
                return CollectionsKt.toFloatArray(list);
            }
            return null;
        }
        float[] fArr = new float[colors.size() + i];
        fArr[0] = list != null ? list.get(0).floatValue() : 0.0f;
        int lastIndex = CollectionsKt.getLastIndex(colors);
        int i2 = 1;
        for (int i3 = 1; i3 < lastIndex; i3++) {
            long jM1887unboximpl = colors.get(i3).m1887unboximpl();
            float fFloatValue = list != null ? list.get(i3).floatValue() : i3 / CollectionsKt.getLastIndex(colors);
            int i4 = i2 + 1;
            fArr[i2] = fFloatValue;
            if (Color.m1879getAlphaimpl(jM1887unboximpl) == 0.0f) {
                i2 += 2;
                fArr[i4] = fFloatValue;
            } else {
                i2 = i4;
            }
        }
        fArr[i2] = list != null ? list.get(CollectionsKt.getLastIndex(colors)).floatValue() : 1.0f;
        return fArr;
    }

    private static final void validateColorStops(List<Color> list, List<Float> list2) {
        if (list2 == null) {
            if (list.size() < 2) {
                throw new IllegalArgumentException("colors must have length of at least 2 if colorStops is omitted.");
            }
        } else if (list.size() != list2.size()) {
            throw new IllegalArgumentException("colors and colorStops arguments must have equal length.");
        }
    }
}
