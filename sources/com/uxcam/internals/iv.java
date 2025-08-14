package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class iv implements hb {

    /* renamed from: a, reason: collision with root package name */
    public final int f215a;
    public final int b;
    public final int c;

    public iv(int i) {
        int i2 = i + 13;
        this.f215a = i2;
        if (i2 < 0) {
            throw new IllegalArgumentException("Maximum upshift allowed: " + i2);
        }
        this.b = 128 << Math.max(i, 0);
        this.c = 128 << Math.max(0 - i, 0);
    }

    public final void a(int i, int i2, int[] iArr, int[] iArr2) {
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i2 / 2; i5++) {
            int i6 = 0;
            while (i6 < i) {
                int i7 = iArr[i3];
                int i8 = this.b;
                int i9 = this.f215a;
                int i10 = this.c;
                iArr2[i4] = ((((((iArr[i3 + i] - i8) * 9362) >> i9) + i10) + ((((i7 - i8) * 9362) >> i9) + i10)) + 1) >> 1;
                i6++;
                i4++;
                i3++;
            }
            i3 += i;
        }
    }

    @Override // com.uxcam.internals.hb
    public final void a(ed edVar, ed edVar2) {
        int[] iArr = edVar.d[0];
        int[] iArr2 = edVar2.d[0];
        for (int i = 0; i < edVar.a(0) * edVar.b(0); i++) {
            iArr2[i] = ((iArr[i] - 16) * 9362) >> this.f215a;
        }
        a(edVar.b(1), edVar.a(1), edVar.d[1], edVar2.d[1]);
        a(edVar.b(2), edVar.a(2), edVar.d[2], edVar2.d[2]);
    }
}
