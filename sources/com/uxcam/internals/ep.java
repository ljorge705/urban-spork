package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class ep implements hb {

    /* renamed from: a, reason: collision with root package name */
    public final int f140a;
    public final int b = 1;

    public ep(int i) {
        this.f140a = i;
    }

    @Override // com.uxcam.internals.hb
    public final void a(ed edVar, ed edVar2) {
        int[] iArr = edVar.d[0];
        int[][] iArr2 = edVar2.d;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < edVar.c; i3++) {
            for (int i4 = 0; i4 < (edVar.b >> 1); i4++) {
                int[] iArr3 = iArr2[1];
                iArr3[i] = 0;
                int[] iArr4 = iArr2[2];
                iArr4[i] = 0;
                int i5 = i << 1;
                int i6 = i;
                int i7 = i;
                eo.a(iArr[i2], iArr[i2 + 1], iArr[i2 + 2], iArr2[0], i5, iArr3, i6, iArr4, i7);
                int[] iArr5 = iArr2[0];
                iArr5[i5] = iArr5[i5] << this.f140a;
                int i8 = iArr[i2 + 3];
                int i9 = i2 + 5;
                int i10 = iArr[i2 + 4];
                i2 += 6;
                int i11 = i5 + 1;
                eo.a(i8, i10, iArr[i9], iArr5, i11, iArr2[1], i6, iArr2[2], i7);
                int[] iArr6 = iArr2[0];
                int i12 = iArr6[i11];
                int i13 = this.f140a;
                iArr6[i11] = i12 << i13;
                int[] iArr7 = iArr2[1];
                int i14 = iArr7[i] << i13;
                int i15 = this.b;
                iArr7[i] = i14 >> i15;
                int[] iArr8 = iArr2[2];
                iArr8[i] = (iArr8[i] << i13) >> i15;
                i++;
            }
        }
    }
}
