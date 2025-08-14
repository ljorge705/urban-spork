package com.uxcam.internals;

import java.util.Arrays;

/* loaded from: classes6.dex */
public class ia {

    /* renamed from: a, reason: collision with root package name */
    public final int[] f204a;
    public final int[] b;

    public ia(int[] iArr, int[] iArr2) {
        this.f204a = iArr;
        this.b = iArr2;
        a();
    }

    public final void a() {
        db dbVar = new db(0);
        db dbVar2 = new db(0);
        a(0, 0, 0, dbVar, dbVar2);
        dbVar.a();
        dbVar2.a();
    }

    public void a(am amVar, int i) {
        throw null;
    }

    public final int a(int i, int i2, int i3, db dbVar, db dbVar2) {
        int i4 = i + 256;
        int[] iArr = dbVar.f117a;
        if (i4 > iArr.length) {
            int[] iArr2 = new int[i + 384];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            dbVar.f117a = iArr2;
        }
        Arrays.fill(dbVar.f117a, i, i4, -1);
        dbVar.b = Math.max(dbVar.b, i4);
        int[] iArr3 = dbVar2.f117a;
        if (i4 > iArr3.length) {
            int[] iArr4 = new int[i + 384];
            System.arraycopy(iArr3, 0, iArr4, 0, iArr3.length);
            dbVar2.f117a = iArr4;
        }
        Arrays.fill(dbVar2.f117a, i, i4, 0);
        dbVar2.b = Math.max(dbVar2.b, i4);
        int i5 = i2 << 3;
        int iA = i4;
        int i6 = 0;
        while (true) {
            int[] iArr5 = this.b;
            if (i6 >= iArr5.length) {
                return iA;
            }
            int i7 = iArr5[i6];
            if (i7 > i5 && (i2 <= 0 || (this.f204a[i6] >>> (32 - i5)) == i3)) {
                int i8 = this.f204a[i6] >>> (24 - i5);
                int i9 = i8 & 255;
                int i10 = i7 - i5;
                if (i10 <= 8) {
                    for (int i11 = 0; i11 < (1 << (8 - i10)); i11++) {
                        int i12 = i + i9 + i11;
                        dbVar.f117a[i12] = i6;
                        dbVar2.f117a[i12] = i10;
                    }
                } else {
                    int i13 = i9 + i;
                    int[] iArr6 = dbVar.f117a;
                    if (iArr6[i13] == -1) {
                        iArr6[i13] = iA;
                        iA = a(iA, i2 + 1, i8, dbVar, dbVar2);
                    }
                }
            }
            i6++;
        }
    }
}
