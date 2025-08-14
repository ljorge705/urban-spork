package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class ir implements hb {
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
                in.a(iArr2[i], iArr3[i2], iArr4[i2], iArr5, i * 3);
                int i5 = i + 1;
                in.a(iArr2[i5], iArr3[i2], iArr4[i2], iArr5, i5 * 3);
                i += 2;
                i2++;
            }
        }
    }
}
