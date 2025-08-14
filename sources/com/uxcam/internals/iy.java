package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class iy implements hb {
    public final void a(int i, int i2, int[] iArr, int[] iArr2) {
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < (i2 >> 1); i5++) {
            int i6 = 0;
            while (i6 < i) {
                int i7 = i3 + i;
                iArr2[i4] = ((((((((iArr[i3] - 128) * 7168) >> 13) + 128) + ((((iArr[i3 + 1] - 128) * 7168) >> 13) + 128)) + ((((iArr[i7] - 128) * 7168) >> 13) + 128)) + ((((iArr[i7 + 1] - 128) * 7168) >> 13) + 128)) + 2) >> 2;
                i6 += 2;
                i4++;
                i3 += 2;
            }
            i3 += i;
        }
    }

    @Override // com.uxcam.internals.hb
    public final void a(ed edVar, ed edVar2) {
        int[] iArr = edVar.d[0];
        int[] iArr2 = edVar2.d[0];
        for (int i = 0; i < edVar.a(0) * edVar.b(0); i++) {
            iArr2[i] = ((iArr[i] * 7168) >> 13) + 16;
        }
        a(edVar.b(1), edVar.a(1), edVar.d[1], edVar2.d[1]);
        a(edVar.b(2), edVar.a(2), edVar.d[2], edVar2.d[2]);
    }
}
