package com.uxcam.screenshot.fullscreenocclusion;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.core.view.ViewCompat;
import com.uxcam.screenshot.model.UXCamBlur;
import com.uxcam.screenshot.model.UXCamOcclusion;
import com.uxcam.screenshot.model.UXCamOverlay;
import java.lang.reflect.Array;
import java.util.concurrent.Executors;

/* loaded from: classes6.dex */
public class FullScreenOcclusionDrawerImpl implements FullScreenOcclusionDrawer {
    @Override // com.uxcam.screenshot.fullscreenocclusion.FullScreenOcclusionDrawer
    public final void a(FullScreenOcclusionConfig fullScreenOcclusionConfig, UXCamOcclusion uXCamOcclusion, Context context) {
        if (uXCamOcclusion == null) {
            fullScreenOcclusionConfig.c.a();
            return;
        }
        if (uXCamOcclusion.getClass() == UXCamOverlay.class) {
            fullScreenOcclusionConfig.b.drawColor(((UXCamOverlay) uXCamOcclusion).getColor());
            fullScreenOcclusionConfig.c.a();
        } else if (uXCamOcclusion.getClass() == UXCamBlur.class) {
            a(((UXCamBlur) uXCamOcclusion).getBlurRadius(), fullScreenOcclusionConfig.f264a, context, fullScreenOcclusionConfig.c);
        }
    }

    public static void a(float f, Bitmap bitmap, FullScreenOcclusionCallback fullScreenOcclusionCallback) {
        int[] iArr;
        int iRound = Math.round(f);
        if (iRound >= 1) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i = width * height;
            int[] iArr2 = new int[i];
            bitmap.getPixels(iArr2, 0, width, 0, 0, width, height);
            int i2 = width - 1;
            int i3 = height - 1;
            int i4 = iRound + iRound;
            int i5 = i4 + 1;
            int[] iArr3 = new int[i];
            int[] iArr4 = new int[i];
            int[] iArr5 = new int[i];
            int[] iArr6 = new int[Math.max(width, height)];
            int i6 = (i4 + 2) >> 1;
            int i7 = i6 * i6;
            int i8 = i7 * 256;
            int[] iArr7 = new int[i8];
            for (int i9 = 0; i9 < i8; i9++) {
                iArr7[i9] = i9 / i7;
            }
            int[][] iArr8 = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i5, 3);
            int i10 = iRound + 1;
            int i11 = 0;
            int i12 = 0;
            int i13 = 0;
            while (i11 < height) {
                int i14 = -iRound;
                int i15 = 0;
                int i16 = 0;
                int i17 = 0;
                int i18 = 0;
                int i19 = 0;
                int i20 = 0;
                int i21 = 0;
                int i22 = 0;
                int i23 = 0;
                while (i14 <= iRound) {
                    int i24 = i3;
                    int i25 = height;
                    int i26 = iArr2[Math.min(i2, Math.max(i14, 0)) + i12];
                    int[] iArr9 = iArr8[i14 + iRound];
                    iArr9[0] = (i26 & 16711680) >> 16;
                    iArr9[1] = (i26 & 65280) >> 8;
                    iArr9[2] = i26 & 255;
                    int iAbs = i10 - Math.abs(i14);
                    int i27 = iArr9[0];
                    i15 = (i27 * iAbs) + i15;
                    int i28 = iArr9[1];
                    i16 = (i28 * iAbs) + i16;
                    int i29 = iArr9[2];
                    i17 = (iAbs * i29) + i17;
                    if (i14 > 0) {
                        i23 += i27;
                        i22 += i28;
                        i21 += i29;
                    } else {
                        i20 += i27;
                        i19 += i28;
                        i18 += i29;
                    }
                    i14++;
                    height = i25;
                    i3 = i24;
                }
                int i30 = i3;
                int i31 = height;
                int i32 = iRound;
                int i33 = 0;
                while (i33 < width) {
                    iArr3[i12] = iArr7[i15];
                    iArr4[i12] = iArr7[i16];
                    iArr5[i12] = iArr7[i17];
                    int i34 = i15 - i20;
                    int i35 = i16 - i19;
                    int i36 = i17 - i18;
                    int[] iArr10 = iArr8[((i32 - iRound) + i5) % i5];
                    int i37 = i20 - iArr10[0];
                    int i38 = i19 - iArr10[1];
                    int i39 = i18 - iArr10[2];
                    if (i11 == 0) {
                        iArr = iArr7;
                        iArr6[i33] = Math.min(i33 + iRound + 1, i2);
                    } else {
                        iArr = iArr7;
                    }
                    int i40 = iArr2[i13 + iArr6[i33]];
                    int i41 = (i40 & 16711680) >> 16;
                    iArr10[0] = i41;
                    int i42 = (i40 & 65280) >> 8;
                    iArr10[1] = i42;
                    int i43 = i40 & 255;
                    iArr10[2] = i43;
                    int i44 = i23 + i41;
                    int i45 = i22 + i42;
                    int i46 = i21 + i43;
                    i15 = i34 + i44;
                    i16 = i35 + i45;
                    i17 = i36 + i46;
                    i32 = (i32 + 1) % i5;
                    int[] iArr11 = iArr8[i32 % i5];
                    int i47 = iArr11[0];
                    i20 = i37 + i47;
                    int i48 = iArr11[1];
                    i19 = i38 + i48;
                    int i49 = iArr11[2];
                    i18 = i39 + i49;
                    i23 = i44 - i47;
                    i22 = i45 - i48;
                    i21 = i46 - i49;
                    i12++;
                    i33++;
                    iArr7 = iArr;
                }
                i13 += width;
                i11++;
                height = i31;
                i3 = i30;
            }
            int i50 = i3;
            int i51 = height;
            int[] iArr12 = iArr7;
            int i52 = 0;
            while (i52 < width) {
                int i53 = -iRound;
                int i54 = i53 * width;
                int i55 = 0;
                int i56 = 0;
                int i57 = 0;
                int i58 = 0;
                int i59 = 0;
                int i60 = 0;
                int i61 = 0;
                int i62 = 0;
                int i63 = 0;
                while (i53 <= iRound) {
                    int[] iArr13 = iArr6;
                    int iMax = Math.max(0, i54) + i52;
                    int[] iArr14 = iArr8[i53 + iRound];
                    iArr14[0] = iArr3[iMax];
                    iArr14[1] = iArr4[iMax];
                    iArr14[2] = iArr5[iMax];
                    int iAbs2 = i10 - Math.abs(i53);
                    i55 = (iArr3[iMax] * iAbs2) + i55;
                    i56 = (iArr4[iMax] * iAbs2) + i56;
                    i57 = (iArr5[iMax] * iAbs2) + i57;
                    if (i53 > 0) {
                        i61 += iArr14[0];
                        i63 += iArr14[1];
                        i62 += iArr14[2];
                    } else {
                        i60 += iArr14[0];
                        i59 += iArr14[1];
                        i58 += iArr14[2];
                    }
                    int i64 = i50;
                    if (i53 < i64) {
                        i54 += width;
                    }
                    i53++;
                    i50 = i64;
                    iArr6 = iArr13;
                }
                int[] iArr15 = iArr6;
                int i65 = i50;
                int i66 = iRound;
                int i67 = i52;
                int i68 = i51;
                int i69 = 0;
                while (i69 < i68) {
                    iArr2[i67] = (iArr2[i67] & ViewCompat.MEASURED_STATE_MASK) | (iArr12[i55] << 16) | (iArr12[i56] << 8) | iArr12[i57];
                    int i70 = i55 - i60;
                    int i71 = i56 - i59;
                    int i72 = i57 - i58;
                    int[] iArr16 = iArr8[((i66 - iRound) + i5) % i5];
                    int i73 = i60 - iArr16[0];
                    int i74 = i59 - iArr16[1];
                    int i75 = i58 - iArr16[2];
                    int i76 = iRound;
                    if (i52 == 0) {
                        iArr15[i69] = Math.min(i69 + i10, i65) * width;
                    }
                    int i77 = iArr15[i69] + i52;
                    int i78 = iArr3[i77];
                    iArr16[0] = i78;
                    int i79 = iArr4[i77];
                    iArr16[1] = i79;
                    int i80 = iArr5[i77];
                    iArr16[2] = i80;
                    int i81 = i61 + i78;
                    int i82 = i63 + i79;
                    int i83 = i62 + i80;
                    i55 = i70 + i81;
                    i56 = i71 + i82;
                    i57 = i72 + i83;
                    i66 = (i66 + 1) % i5;
                    int[] iArr17 = iArr8[i66];
                    int i84 = iArr17[0];
                    i60 = i73 + i84;
                    int i85 = iArr17[1];
                    i59 = i74 + i85;
                    int i86 = iArr17[2];
                    i58 = i75 + i86;
                    i61 = i81 - i84;
                    i63 = i82 - i85;
                    i62 = i83 - i86;
                    i67 += width;
                    i69++;
                    iRound = i76;
                }
                i52++;
                i51 = i68;
                i50 = i65;
                iArr6 = iArr15;
            }
            bitmap.setPixels(iArr2, 0, width, 0, 0, width, i51);
        }
        fullScreenOcclusionCallback.a();
    }

    public static void a(int i, final Bitmap bitmap, Context context, final FullScreenOcclusionCallback fullScreenOcclusionCallback) {
        final float f = (context.getResources().getDisplayMetrics().densityDpi / 160.0f) * i;
        Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: com.uxcam.screenshot.fullscreenocclusion.FullScreenOcclusionDrawerImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FullScreenOcclusionDrawerImpl.a(f, bitmap, fullScreenOcclusionCallback);
            }
        });
    }
}
