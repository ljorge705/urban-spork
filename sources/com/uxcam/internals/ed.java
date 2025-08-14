package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class ed {

    /* renamed from: a, reason: collision with root package name */
    public final bd f133a;
    public final int b;
    public final int c;
    public final int[][] d;

    public ed(int i, int i2, int[][] iArr, bd bdVar) {
        this.b = i;
        this.c = i2;
        this.d = iArr;
        this.f133a = bdVar;
    }

    public final int b(int i) {
        return this.b >> this.f133a.c[i];
    }

    public static ed a(int i, int i2, bd bdVar) {
        int[] iArr = new int[4];
        for (int i3 = 0; i3 < bdVar.f94a; i3++) {
            int i4 = bdVar.b[i3];
            iArr[i4] = ((i >> bdVar.c[i3]) * (i2 >> bdVar.d[i3])) + iArr[i4];
        }
        int i5 = 0;
        for (int i6 = 0; i6 < 4; i6++) {
            i5 += iArr[i6] != 0 ? 1 : 0;
        }
        int[][] iArr2 = new int[i5][];
        int i7 = 0;
        for (int i8 = 0; i8 < 4; i8++) {
            int i9 = iArr[i8];
            if (i9 != 0) {
                iArr2[i7] = new int[i9];
                i7++;
            }
        }
        return new ed(i, i2, iArr2, bdVar);
    }

    public final int a(int i) {
        return this.c >> this.f133a.d[i];
    }
}
