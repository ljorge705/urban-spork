package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class iu implements hb {

    /* renamed from: a, reason: collision with root package name */
    public final int f214a;

    public iu(int i) {
        this.f214a = i;
    }

    public static final void a(int i, int i2, int i3, int[] iArr, int i4) {
        int i5 = i2 - 128;
        int i6 = i3 - 128;
        int i7 = (i - 16) * 298;
        int i8 = (((i6 * 409) + i7) + 128) >> 8;
        int i9 = (((i7 - (i5 * 100)) - (i6 * 208)) + 128) >> 8;
        int i10 = (((i5 * 516) + i7) + 128) >> 8;
        if (i10 < 0) {
            i10 = 0;
        } else if (i10 > 255) {
            i10 = 255;
        }
        iArr[i4] = i10;
        int i11 = i4 + 1;
        if (i9 < 0) {
            i9 = 0;
        } else if (i9 > 255) {
            i9 = 255;
        }
        iArr[i11] = i9;
        int i12 = i4 + 2;
        if (i8 < 0) {
            i8 = 0;
        } else if (i8 > 255) {
            i8 = 255;
        }
        iArr[i12] = i8;
    }

    @Override // com.uxcam.internals.hb
    public final void a(ed edVar, ed edVar2) {
        int[][] iArr = edVar.d;
        int[] iArr2 = iArr[0];
        int[] iArr3 = iArr[1];
        int[] iArr4 = iArr[2];
        int[] iArr5 = edVar2.d[0];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < edVar2.c; i3++) {
            for (int i4 = 0; i4 < edVar2.b; i4 += 2) {
                int i5 = iArr2[i];
                int i6 = this.f214a;
                a(i5 >> i6, iArr3[i2] >> i6, iArr4[i2] >> i6, iArr5, i * 3);
                int i7 = i + 1;
                int i8 = iArr2[i7];
                int i9 = this.f214a;
                a(i8 >> i9, iArr3[i2] >> i9, iArr4[i2] >> i9, iArr5, i7 * 3);
                i += 2;
                i2++;
            }
        }
    }
}
