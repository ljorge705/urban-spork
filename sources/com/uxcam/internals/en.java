package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class en implements hb {
    public static final void a(int i, int i2, int i3, int[] iArr, int i4, int[] iArr2, int i5, int[] iArr3, int i6) {
        int i7 = (i3 * 25) + (i2 * 129) + (i * 66);
        int i8 = (i3 * 112) + ((i * (-38)) - (i2 * 74));
        int i9 = ((i * 112) - (i2 * 94)) - (i3 * 18);
        int i10 = (i8 + 128) >> 8;
        int i11 = (i9 + 128) >> 8;
        int i12 = ((i7 + 128) >> 8) + 16;
        if (i12 < 0) {
            i12 = 0;
        } else if (i12 > 255) {
            i12 = 255;
        }
        iArr[i4] = i12;
        int i13 = iArr2[i5];
        int i14 = i10 + 128;
        if (i14 < 0) {
            i14 = 0;
        } else if (i14 > 255) {
            i14 = 255;
        }
        iArr2[i5] = i14 + i13;
        int i15 = i11 + 128;
        iArr3[i6] = (i15 >= 0 ? i15 > 255 ? 255 : i15 : 0) + iArr3[i6];
    }

    @Override // com.uxcam.internals.hb
    public final void a(ed edVar, ed edVar2) {
        int[] iArr = edVar.d[0];
        int[][] iArr2 = edVar2.d;
        int i = edVar.b * 3;
        int i2 = edVar2.b;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < (edVar.c >> 1); i6++) {
            for (int i7 = 0; i7 < (edVar.b >> 1); i7++) {
                int[] iArr3 = iArr2[1];
                iArr3[i3] = 0;
                int[] iArr4 = iArr2[2];
                iArr4[i3] = 0;
                int i8 = i3;
                int i9 = i3;
                a(iArr[i5], iArr[i5 + 1], iArr[i5 + 2], iArr2[0], i4, iArr3, i8, iArr4, i9);
                int[] iArr5 = iArr2[0];
                iArr5[i4] = iArr5[i4];
                int i10 = i5 + i;
                int i11 = i4 + i2;
                a(iArr[i10], iArr[i10 + 1], iArr[i10 + 2], iArr5, i11, iArr2[1], i8, iArr2[2], i9);
                int[] iArr6 = iArr2[0];
                iArr6[i11] = iArr6[i11];
                int i12 = i4 + 1;
                a(iArr[i5 + 3], iArr[i5 + 4], iArr[i5 + 5], iArr6, i12, iArr2[1], i8, iArr2[2], i9);
                int[] iArr7 = iArr2[0];
                iArr7[i12] = iArr7[i12];
                int i13 = i12 + i2;
                a(iArr[i10 + 3], iArr[i10 + 4], iArr[i10 + 5], iArr7, i13, iArr2[1], i8, iArr2[2], i9);
                int[] iArr8 = iArr2[0];
                iArr8[i13] = iArr8[i13];
                i4 += 2;
                int[] iArr9 = iArr2[1];
                iArr9[i3] = iArr9[i3] >> 2;
                int[] iArr10 = iArr2[2];
                iArr10[i3] = iArr10[i3] >> 2;
                i3++;
                i5 += 6;
            }
            i4 += i2;
            i5 += i;
        }
    }
}
