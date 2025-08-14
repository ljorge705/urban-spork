package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class in implements hb {

    /* renamed from: a, reason: collision with root package name */
    public static final int f213a = (int) 731.77936d;
    public static final int b = (int) 1815.028d;
    public static final int c = -((int) 352.89936d);
    public static final int d = (int) 1436.148d;

    public static final void a(int i, int i2, int i3, int[] iArr, int i4) {
        int i5 = i << 10;
        int i6 = i2 - 128;
        int i7 = i3 - 128;
        int i8 = (d * i7) + 512;
        int i9 = ((c * i6) - (f213a * i7)) + 512;
        int i10 = (b * i6) + 512;
        iArr[i4] = dp.a((i5 + i10) >> 10);
        iArr[i4 + 1] = dp.a((i9 + i5) >> 10);
        iArr[i4 + 2] = dp.a((i8 + i5) >> 10);
    }

    @Override // com.uxcam.internals.hb
    public final void a(ed edVar, ed edVar2) {
        int i;
        int i2;
        int i3;
        int[][] iArr = edVar.d;
        int i4 = 0;
        int[] iArr2 = iArr[0];
        int[] iArr3 = iArr[1];
        int[] iArr4 = iArr[2];
        int[] iArr5 = edVar2.d[0];
        int i5 = edVar2.b;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (true) {
            i = edVar2.c;
            if (i6 >= (i >> 1)) {
                break;
            }
            int i9 = i4;
            while (true) {
                i3 = edVar2.b;
                if (i9 >= (i3 >> 1)) {
                    break;
                }
                int i10 = (i9 << 1) + i7;
                a(iArr2[i10], iArr3[i8], iArr4[i8], iArr5, i10 * 3);
                int i11 = i10 + 1;
                a(iArr2[i11], iArr3[i8], iArr4[i8], iArr5, i11 * 3);
                int i12 = i10 + i5;
                a(iArr2[i12], iArr3[i8], iArr4[i8], iArr5, i12 * 3);
                int i13 = i12 + 1;
                a(iArr2[i13], iArr3[i8], iArr4[i8], iArr5, i13 * 3);
                i8++;
                i9++;
            }
            if ((i3 & 1) != 0) {
                int i14 = (i3 - 1) + i7;
                a(iArr2[i14], iArr3[i8], iArr4[i8], iArr5, i14 * 3);
                int i15 = i14 + i5;
                a(iArr2[i15], iArr3[i8], iArr4[i8], iArr5, i15 * 3);
                i8++;
            }
            i7 += i5 * 2;
            i6++;
            i4 = 0;
        }
        if ((i & 1) != 0) {
            int i16 = 0;
            while (true) {
                i2 = edVar2.b;
                if (i16 >= (i2 >> 1)) {
                    break;
                }
                int i17 = (i16 << 1) + i7;
                a(iArr2[i17], iArr3[i8], iArr4[i8], iArr5, i17 * 3);
                int i18 = i17 + 1;
                a(iArr2[i18], iArr3[i8], iArr4[i8], iArr5, i18 * 3);
                i8++;
                i16++;
            }
            if ((i2 & 1) != 0) {
                int i19 = (i2 - 1) + i7;
                a(iArr2[i19], iArr3[i8], iArr4[i8], iArr5, i19 * 3);
            }
        }
    }
}
