package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.math.ec.WNafUtil;
import org.spongycastle.util.BigIntegers;

/* loaded from: classes2.dex */
class DHParametersHelper {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    DHParametersHelper() {
    }

    static BigInteger[] generateSafePrimes(int i, int i2, SecureRandom secureRandom) {
        int i3 = i - 1;
        int i4 = i >>> 2;
        while (true) {
            BigInteger bigInteger = new BigInteger(i3, 2, secureRandom);
            BigInteger bigIntegerAdd = bigInteger.shiftLeft(1).add(ONE);
            if (bigIntegerAdd.isProbablePrime(i2) && (i2 <= 2 || bigInteger.isProbablePrime(i2 - 2))) {
                if (WNafUtil.getNafWeight(bigIntegerAdd) >= i4) {
                    return new BigInteger[]{bigIntegerAdd, bigInteger};
                }
            }
        }
    }

    static BigInteger selectGenerator(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        BigInteger bigIntegerModPow;
        BigInteger bigIntegerSubtract = bigInteger.subtract(TWO);
        do {
            BigInteger bigInteger3 = TWO;
            bigIntegerModPow = BigIntegers.createRandomInRange(bigInteger3, bigIntegerSubtract, secureRandom).modPow(bigInteger3, bigInteger);
        } while (bigIntegerModPow.equals(ONE));
        return bigIntegerModPow;
    }
}
