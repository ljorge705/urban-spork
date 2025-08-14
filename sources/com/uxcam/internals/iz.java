package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class iz implements hb {

    /* renamed from: a, reason: collision with root package name */
    public final int f217a;

    public iz(int i) {
        this.f217a = i;
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
            int i4 = 0;
            while (i4 < edVar2.b) {
                int i5 = iArr2[i];
                int i6 = this.f217a;
                int i7 = i5 >> i6;
                int i8 = (iArr3[i] >> i6) - 128;
                int i9 = (iArr4[i] >> i6) - 128;
                int i10 = (i7 - 16) * 298;
                int i11 = (((i9 * 409) + i10) + 128) >> 8;
                int i12 = (((i10 - (i8 * 100)) - (i9 * 208)) + 128) >> 8;
                int i13 = (((i8 * 516) + i10) + 128) >> 8;
                if (i13 < 0) {
                    i13 = 0;
                } else if (i13 > 255) {
                    i13 = 255;
                }
                iArr5[i2] = i13;
                int i14 = i2 + 1;
                if (i12 < 0) {
                    i12 = 0;
                } else if (i12 > 255) {
                    i12 = 255;
                }
                iArr5[i14] = i12;
                int i15 = i2 + 2;
                if (i11 < 0) {
                    i11 = 0;
                } else if (i11 > 255) {
                    i11 = 255;
                }
                iArr5[i15] = i11;
                i4++;
                i++;
                i2 += 3;
            }
        }
    }
}
