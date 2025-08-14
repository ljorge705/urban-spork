package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class iw implements hb {

    /* renamed from: a, reason: collision with root package name */
    public final int f216a;

    public iw(int i) {
        this.f216a = i;
    }

    @Override // com.uxcam.internals.hb
    public final void a(ed edVar, ed edVar2) {
        int i = 0;
        System.arraycopy(edVar.d[0], 0, edVar2.d[0], 0, edVar.b * edVar.c);
        int[] iArr = edVar.d[1];
        int[] iArr2 = edVar2.d[1];
        int iB = edVar.b(1);
        int iA = edVar.a(1);
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < iA / 2; i4++) {
            int i5 = 0;
            while (i5 < iB) {
                iArr2[i3] = ((iArr[i2] + iArr[i2 + iB]) + 1) >> 1;
                i5++;
                i3++;
                i2++;
            }
            i2 += iB;
        }
        int[] iArr3 = edVar.d[2];
        int[] iArr4 = edVar2.d[2];
        int iB2 = edVar.b(2);
        int iA2 = edVar.a(2);
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < iA2 / 2; i8++) {
            int i9 = 0;
            while (i9 < iB2) {
                iArr4[i7] = ((iArr3[i6] + iArr3[i6 + iB2]) + 1) >> 1;
                i9++;
                i7++;
                i6++;
            }
            i6 += iB2;
        }
        int i10 = this.f216a;
        if (i10 < 0) {
            int[] iArr5 = edVar2.d[0];
            int i11 = 0 - i10;
            for (int i12 = 0; i12 < iArr5.length; i12++) {
                iArr5[i12] = iArr5[i12] << i11;
            }
            int[] iArr6 = edVar2.d[1];
            int i13 = 0 - this.f216a;
            for (int i14 = 0; i14 < iArr6.length; i14++) {
                iArr6[i14] = iArr6[i14] << i13;
            }
            int[] iArr7 = edVar2.d[2];
            int i15 = 0 - this.f216a;
            while (i < iArr7.length) {
                iArr7[i] = iArr7[i] << i15;
                i++;
            }
            return;
        }
        if (i10 > 0) {
            int[] iArr8 = edVar2.d[0];
            for (int i16 = 0; i16 < iArr8.length; i16++) {
                iArr8[i16] = iArr8[i16] >> i10;
            }
            int[] iArr9 = edVar2.d[1];
            int i17 = this.f216a;
            for (int i18 = 0; i18 < iArr9.length; i18++) {
                iArr9[i18] = iArr9[i18] >> i17;
            }
            int[] iArr10 = edVar2.d[2];
            int i19 = this.f216a;
            while (i < iArr10.length) {
                iArr10[i] = iArr10[i] >> i19;
                i++;
            }
        }
    }
}
