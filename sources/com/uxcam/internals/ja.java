package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class ja implements hb {

    /* renamed from: a, reason: collision with root package name */
    public final int f218a;

    public ja(int i) {
        this.f218a = i;
    }

    public final void a(int i, int i2, int[] iArr, int[] iArr2) {
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < (i2 >> 1); i5++) {
            int i6 = 0;
            while (i6 < i) {
                int i7 = i3 + i;
                iArr2[i4] = ((((iArr[i3] + iArr[i3 + 1]) + iArr[i7]) + iArr[i7 + 1]) + 2) >> 2;
                i6 += 2;
                i4++;
                i3 += 2;
            }
            i3 += i;
        }
    }

    @Override // com.uxcam.internals.hb
    public final void a(ed edVar, ed edVar2) {
        int i = 0;
        System.arraycopy(edVar.d[0], 0, edVar2.d[0], 0, edVar.b * edVar.c);
        a(edVar.b(1), edVar.a(1), edVar.d[1], edVar2.d[1]);
        a(edVar.b(2), edVar.a(2), edVar.d[2], edVar2.d[2]);
        int i2 = this.f218a;
        if (i2 < 0) {
            int[] iArr = edVar2.d[0];
            int i3 = 0 - i2;
            for (int i4 = 0; i4 < iArr.length; i4++) {
                iArr[i4] = iArr[i4] << i3;
            }
            int[] iArr2 = edVar2.d[1];
            int i5 = 0 - this.f218a;
            for (int i6 = 0; i6 < iArr2.length; i6++) {
                iArr2[i6] = iArr2[i6] << i5;
            }
            int[] iArr3 = edVar2.d[2];
            int i7 = 0 - this.f218a;
            while (i < iArr3.length) {
                iArr3[i] = iArr3[i] << i7;
                i++;
            }
            return;
        }
        if (i2 > 0) {
            int[] iArr4 = edVar2.d[0];
            for (int i8 = 0; i8 < iArr4.length; i8++) {
                iArr4[i8] = iArr4[i8] >> i2;
            }
            int[] iArr5 = edVar2.d[1];
            int i9 = this.f218a;
            for (int i10 = 0; i10 < iArr5.length; i10++) {
                iArr5[i10] = iArr5[i10] >> i9;
            }
            int[] iArr6 = edVar2.d[2];
            int i11 = this.f218a;
            while (i < iArr6.length) {
                iArr6[i] = iArr6[i] >> i11;
                i++;
            }
        }
    }
}
