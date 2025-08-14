package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class ix implements hb {
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
                in.a(iArr2[i], iArr3[i], iArr4[i], iArr5, i2);
                i4++;
                i++;
                i2 += 3;
            }
        }
    }
}
