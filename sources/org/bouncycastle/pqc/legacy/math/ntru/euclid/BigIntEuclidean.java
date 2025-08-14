package org.bouncycastle.pqc.legacy.math.ntru.euclid;

import java.math.BigInteger;

/* loaded from: classes4.dex */
public class BigIntEuclidean {
    public BigInteger gcd;
    public BigInteger x;
    public BigInteger y;

    private BigIntEuclidean() {
    }

    public static BigIntEuclidean calculate(BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigInteger3 = BigInteger.ZERO;
        BigInteger bigInteger4 = BigInteger.ONE;
        BigInteger bigInteger5 = BigInteger.ONE;
        BigInteger bigInteger6 = BigInteger.ZERO;
        BigInteger bigInteger7 = bigInteger;
        BigInteger bigInteger8 = bigInteger2;
        while (!bigInteger8.equals(BigInteger.ZERO)) {
            BigInteger[] bigIntegerArrDivideAndRemainder = bigInteger7.divideAndRemainder(bigInteger8);
            BigInteger bigInteger9 = bigIntegerArrDivideAndRemainder[0];
            BigInteger bigInteger10 = bigIntegerArrDivideAndRemainder[1];
            BigInteger bigIntegerSubtract = bigInteger4.subtract(bigInteger9.multiply(bigInteger3));
            BigInteger bigIntegerSubtract2 = bigInteger6.subtract(bigInteger9.multiply(bigInteger5));
            bigInteger7 = bigInteger8;
            bigInteger8 = bigInteger10;
            bigInteger4 = bigInteger3;
            bigInteger3 = bigIntegerSubtract;
            bigInteger6 = bigInteger5;
            bigInteger5 = bigIntegerSubtract2;
        }
        BigIntEuclidean bigIntEuclidean = new BigIntEuclidean();
        bigIntEuclidean.x = bigInteger4;
        bigIntEuclidean.y = bigInteger6;
        bigIntEuclidean.gcd = bigInteger7;
        return bigIntEuclidean;
    }
}
