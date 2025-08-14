package com.uxcam.internals;

import java.lang.reflect.Array;
import java.util.Arrays;

/* loaded from: classes6.dex */
public final class bb {

    /* renamed from: a, reason: collision with root package name */
    public static final int[][] f92a = {new int[]{13107, 8066, 13107, 8066, 8066, 5243, 8066, 5243, 13107, 8066, 13107, 8066, 8066, 5243, 8066, 5243}, new int[]{11916, 7490, 11916, 7490, 7490, 4660, 7490, 4660, 11916, 7490, 11916, 7490, 7490, 4660, 7490, 4660}, new int[]{10082, 6554, 10082, 6554, 6554, 4194, 6554, 4194, 10082, 6554, 10082, 6554, 6554, 4194, 6554, 4194}, new int[]{9362, 5825, 9362, 5825, 5825, 3647, 5825, 3647, 9362, 5825, 9362, 5825, 5825, 3647, 5825, 3647}, new int[]{8192, 5243, 8192, 5243, 5243, 3355, 5243, 3355, 8192, 5243, 8192, 5243, 5243, 3355, 5243, 3355}, new int[]{7282, 4559, 7282, 4559, 4559, 2893, 4559, 2893, 7282, 4559, 7282, 4559, 4559, 2893, 4559, 2893}};
    public static final int[] b = {0, 1, 4, 8, 5, 2, 3, 6, 9, 12, 13, 10, 7, 11, 14, 15};
    public static final int[][] c = {new int[]{10, 13, 10, 13, 13, 16, 13, 16, 10, 13, 10, 13, 13, 16, 13, 16}, new int[]{11, 14, 11, 14, 14, 18, 14, 18, 11, 14, 11, 14, 14, 18, 14, 18}, new int[]{13, 16, 13, 16, 16, 20, 16, 20, 13, 16, 13, 16, 16, 20, 16, 20}, new int[]{14, 18, 14, 18, 18, 23, 18, 23, 14, 18, 14, 18, 18, 23, 18, 23}, new int[]{16, 20, 16, 20, 20, 25, 20, 25, 16, 20, 16, 20, 20, 25, 20, 25}, new int[]{18, 23, 18, 23, 23, 29, 23, 29, 18, 23, 18, 23, 23, 29, 23, 29}};
    public static final int[][] d = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, 6, 64);
    public static final int[][] e = {new int[]{20, 18, 32, 19, 25, 24}, new int[]{22, 19, 35, 21, 28, 26}, new int[]{26, 23, 42, 24, 33, 31}, new int[]{28, 25, 45, 26, 35, 33}, new int[]{32, 28, 51, 30, 40, 38}, new int[]{36, 32, 58, 34, 46, 43}};

    public static void a(int[] iArr) {
        for (int i = 0; i < 16; i += 4) {
            int i2 = iArr[i];
            int i3 = i + 3;
            int i4 = iArr[i3];
            int i5 = i2 + i4;
            int i6 = i + 1;
            int i7 = iArr[i6];
            int i8 = i + 2;
            int i9 = iArr[i8];
            int i10 = i7 + i9;
            int i11 = i7 - i9;
            int i12 = i2 - i4;
            iArr[i] = i5 + i10;
            iArr[i6] = (i12 << 1) + i11;
            iArr[i8] = i5 - i10;
            iArr[i3] = i12 - (i11 << 1);
        }
        for (int i13 = 0; i13 < 4; i13++) {
            int i14 = iArr[i13];
            int i15 = i13 + 12;
            int i16 = iArr[i15];
            int i17 = i14 + i16;
            int i18 = i13 + 4;
            int i19 = iArr[i18];
            int i20 = i13 + 8;
            int i21 = iArr[i20];
            int i22 = i19 + i21;
            int i23 = i19 - i21;
            int i24 = i14 - i16;
            iArr[i13] = i17 + i22;
            iArr[i18] = (i24 << 1) + i23;
            iArr[i20] = i17 - i22;
            iArr[i15] = i24 - (i23 << 1);
        }
    }

    static {
        for (int i = 0; i < 6; i++) {
            Arrays.fill(d[i], e[i][5]);
            for (int i2 = 0; i2 < 8; i2 += 4) {
                for (int i3 = 0; i3 < 8; i3 += 4) {
                    d[i][(i2 << 3) + i3] = e[i][0];
                }
            }
            for (int i4 = 1; i4 < 8; i4 += 2) {
                for (int i5 = 1; i5 < 8; i5 += 2) {
                    d[i][(i4 << 3) + i5] = e[i][1];
                }
            }
            for (int i6 = 2; i6 < 8; i6 += 4) {
                for (int i7 = 2; i7 < 8; i7 += 4) {
                    d[i][(i6 << 3) + i7] = e[i][2];
                }
            }
            for (int i8 = 0; i8 < 8; i8 += 4) {
                for (int i9 = 1; i9 < 8; i9 += 2) {
                    d[i][(i8 << 3) + i9] = e[i][3];
                }
            }
            for (int i10 = 1; i10 < 8; i10 += 2) {
                for (int i11 = 0; i11 < 8; i11 += 4) {
                    d[i][(i10 << 3) + i11] = e[i][3];
                }
            }
            for (int i12 = 0; i12 < 8; i12 += 4) {
                for (int i13 = 2; i13 < 8; i13 += 4) {
                    d[i][(i12 << 3) + i13] = e[i][4];
                }
            }
            for (int i14 = 2; i14 < 8; i14 += 4) {
                for (int i15 = 0; i15 < 8; i15 += 4) {
                    d[i][(i14 << 3) + i15] = e[i][4];
                }
            }
        }
    }
}
