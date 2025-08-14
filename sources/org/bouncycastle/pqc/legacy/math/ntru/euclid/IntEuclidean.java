package org.bouncycastle.pqc.legacy.math.ntru.euclid;

/* loaded from: classes4.dex */
public class IntEuclidean {
    public int gcd;
    public int x;
    public int y;

    private IntEuclidean() {
    }

    public static IntEuclidean calculate(int i, int i2) {
        int i3 = 0;
        int i4 = 1;
        int i5 = 1;
        int i6 = 0;
        int i7 = i;
        int i8 = i2;
        while (i8 != 0) {
            int i9 = i7 / i8;
            int i10 = i7 % i8;
            int i11 = i5 - (i9 * i6);
            i7 = i8;
            i8 = i10;
            int i12 = i4;
            i4 = i3 - (i9 * i4);
            i3 = i12;
            i5 = i6;
            i6 = i11;
        }
        IntEuclidean intEuclidean = new IntEuclidean();
        intEuclidean.x = i5;
        intEuclidean.y = i3;
        intEuclidean.gcd = i7;
        return intEuclidean;
    }
}
