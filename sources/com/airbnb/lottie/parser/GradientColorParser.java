package com.airbnb.lottie.parser;

import android.graphics.Color;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.utils.MiscUtils;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes5.dex */
public class GradientColorParser implements ValueParser<GradientColor> {
    private int colorPoints;

    public GradientColorParser(int i) {
        this.colorPoints = i;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00cf  */
    @Override // com.airbnb.lottie.parser.ValueParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.airbnb.lottie.model.content.GradientColor parse(com.airbnb.lottie.parser.moshi.JsonReader r18, float r19) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.GradientColorParser.parse(com.airbnb.lottie.parser.moshi.JsonReader, float):com.airbnb.lottie.model.content.GradientColor");
    }

    private GradientColor addOpacityStopsToGradientIfNeeded(GradientColor gradientColor, List<Float> list) {
        int i = this.colorPoints * 4;
        if (list.size() <= i) {
            return gradientColor;
        }
        float[] positions = gradientColor.getPositions();
        int[] colors = gradientColor.getColors();
        int size = (list.size() - i) / 2;
        float[] fArr = new float[size];
        float[] fArr2 = new float[size];
        int i2 = 0;
        while (i < list.size()) {
            if (i % 2 == 0) {
                fArr[i2] = list.get(i).floatValue();
            } else {
                fArr2[i2] = list.get(i).floatValue();
                i2++;
            }
            i++;
        }
        int i3 = this.colorPoints + size;
        float[] fArr3 = new float[i3];
        int[] iArr = new int[i3];
        System.arraycopy(gradientColor.getPositions(), 0, fArr3, 0, this.colorPoints);
        System.arraycopy(fArr, 0, fArr3, this.colorPoints, size);
        Arrays.sort(fArr3);
        for (int i4 = 0; i4 < i3; i4++) {
            float f = fArr3[i4];
            int iBinarySearch = Arrays.binarySearch(positions, f);
            int iBinarySearch2 = Arrays.binarySearch(fArr, f);
            if (iBinarySearch < 0 || iBinarySearch2 > 0) {
                if (iBinarySearch2 < 0) {
                    iBinarySearch2 = -(iBinarySearch2 + 1);
                }
                iArr[i4] = getColorInBetweenColorStops(f, fArr2[iBinarySearch2], positions, colors);
            } else {
                iArr[i4] = getColorInBetweenOpacityStops(f, colors[iBinarySearch], fArr, fArr2);
            }
        }
        return new GradientColor(fArr3, iArr);
    }

    private int getColorInBetweenColorStops(float f, float f2, float[] fArr, int[] iArr) {
        if (iArr.length < 2 || f == fArr[0]) {
            return iArr[0];
        }
        for (int i = 1; i < fArr.length; i++) {
            float f3 = fArr[i];
            if (f3 >= f || i == fArr.length - 1) {
                int i2 = i - 1;
                float f4 = fArr[i2];
                float f5 = (f - f4) / (f3 - f4);
                int i3 = iArr[i];
                int i4 = iArr[i2];
                return Color.argb((int) (f2 * 255.0f), MiscUtils.lerp(Color.red(i4), Color.red(i3), f5), MiscUtils.lerp(Color.green(i4), Color.green(i3), f5), MiscUtils.lerp(Color.blue(i4), Color.blue(i3), f5));
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }

    private int getColorInBetweenOpacityStops(float f, int i, float[] fArr, float[] fArr2) {
        float fLerp;
        if (fArr2.length < 2 || f <= fArr[0]) {
            return Color.argb((int) (fArr2[0] * 255.0f), Color.red(i), Color.green(i), Color.blue(i));
        }
        for (int i2 = 1; i2 < fArr.length; i2++) {
            float f2 = fArr[i2];
            if (f2 >= f || i2 == fArr.length - 1) {
                if (f2 <= f) {
                    fLerp = fArr2[i2];
                } else {
                    int i3 = i2 - 1;
                    float f3 = fArr[i3];
                    fLerp = MiscUtils.lerp(fArr2[i3], fArr2[i2], (f - f3) / (f2 - f3));
                }
                return Color.argb((int) (fLerp * 255.0f), Color.red(i), Color.green(i), Color.blue(i));
            }
        }
        throw new IllegalArgumentException("Unreachable code.");
    }
}
