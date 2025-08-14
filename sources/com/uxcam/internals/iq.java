package com.uxcam.internals;

/* loaded from: classes6.dex */
public final class iq implements hb {
    public iq(int i) {
    }

    public static final void a(int[] iArr, int[] iArr2, int i, int i2, int i3, int i4, int i5) {
        int i6 = i * i3;
        int i7 = 0;
        for (int i8 = 0; i8 < i4; i8++) {
            int i9 = 0;
            while (i9 < i2) {
                iArr2[i6] = (iArr[i7] & 255) << 2;
                i6++;
                i9++;
                i7++;
            }
            int i10 = i6 - 1;
            for (int i11 = i2; i11 < i3; i11++) {
                iArr2[i6] = iArr2[i10];
                i6++;
            }
            i6 += i3;
        }
        int i12 = i6 - (2 * i3);
        for (int i13 = i4 * 2; i13 < i5; i13 += 2) {
            for (int i14 = 0; i14 < i3; i14++) {
                iArr2[i6] = iArr2[i12 + i14];
                i6++;
            }
            i6 += i3;
        }
    }

    @Override // com.uxcam.internals.hb
    public final void a(ed edVar, ed edVar2) {
        int[] iArr = edVar.d[0];
        int[] iArr2 = edVar2.d[0];
        int i = edVar.b;
        int i2 = edVar2.b;
        int i3 = edVar2.c;
        int length = iArr.length / i;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < length; i6++) {
            int i7 = 0;
            while (i7 < i) {
                iArr2[i4] = (iArr[i5] & 255) << 2;
                i7++;
                i4++;
                i5++;
            }
            int i8 = i;
            while (i8 < i2) {
                iArr2[i4] = iArr2[i - 1];
                i8++;
                i4++;
            }
        }
        int i9 = (length - 1) * i2;
        while (length < i3) {
            int i10 = 0;
            while (i10 < i2) {
                iArr2[i4] = iArr2[i9 + i10];
                i10++;
                i4++;
            }
            length++;
        }
        a(edVar.d[1], edVar2.d[1], 0, edVar.b >> 1, edVar2.b >> 1, edVar.c >> 1, edVar2.c);
        a(edVar.d[1], edVar2.d[1], 1, edVar.b >> 1, edVar2.b >> 1, edVar.c >> 1, edVar2.c);
        a(edVar.d[2], edVar2.d[2], 0, edVar.b >> 1, edVar2.b >> 1, edVar.c >> 1, edVar2.c);
        a(edVar.d[2], edVar2.d[2], 1, edVar.b >> 1, edVar2.b >> 1, edVar.c >> 1, edVar2.c);
    }
}
