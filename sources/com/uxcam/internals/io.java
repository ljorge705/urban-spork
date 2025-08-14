package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class io implements hb {
    @Override // com.uxcam.internals.hb
    public final void a(ed edVar, ed edVar2) {
        int[] iArr = edVar.d[0];
        int[] iArr2 = edVar2.d[0];
        for (int i = 0; i < edVar.a(0) * edVar.b(0); i++) {
            iArr2[i] = ((iArr[i] * 7168) >> 13) + 16;
        }
        int[] iArr3 = edVar.d[1];
        int[] iArr4 = edVar2.d[1];
        for (int i2 = 0; i2 < edVar.a(1) * edVar.b(1); i2++) {
            iArr4[i2] = (((iArr3[i2] - 128) * 7168) >> 13) + 128;
        }
        int[] iArr5 = edVar.d[2];
        int[] iArr6 = edVar2.d[2];
        for (int i3 = 0; i3 < edVar.a(2) * edVar.b(2); i3++) {
            iArr6[i3] = (((iArr5[i3] - 128) * 7168) >> 13) + 128;
        }
    }
}
