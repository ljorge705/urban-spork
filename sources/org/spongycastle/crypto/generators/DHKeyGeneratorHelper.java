package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.math.ec.WNafUtil;
import org.spongycastle.util.BigIntegers;

/* loaded from: classes2.dex */
class DHKeyGeneratorHelper {
    static final DHKeyGeneratorHelper INSTANCE = new DHKeyGeneratorHelper();
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    private DHKeyGeneratorHelper() {
    }

    BigInteger calculatePrivate(DHParameters dHParameters, SecureRandom secureRandom) {
        BigInteger bigIntegerCreateRandomInRange;
        BigInteger bit;
        int l = dHParameters.getL();
        if (l != 0) {
            int i = l >>> 2;
            do {
                bit = new BigInteger(l, secureRandom).setBit(l - 1);
            } while (WNafUtil.getNafWeight(bit) < i);
            return bit;
        }
        BigInteger bigInteger = TWO;
        int m = dHParameters.getM();
        BigInteger bigIntegerShiftLeft = m != 0 ? ONE.shiftLeft(m - 1) : bigInteger;
        BigInteger q = dHParameters.getQ();
        if (q == null) {
            q = dHParameters.getP();
        }
        BigInteger bigIntegerSubtract = q.subtract(bigInteger);
        int iBitLength = bigIntegerSubtract.bitLength() >>> 2;
        do {
            bigIntegerCreateRandomInRange = BigIntegers.createRandomInRange(bigIntegerShiftLeft, bigIntegerSubtract, secureRandom);
        } while (WNafUtil.getNafWeight(bigIntegerCreateRandomInRange) < iBitLength);
        return bigIntegerCreateRandomInRange;
    }

    BigInteger calculatePublic(DHParameters dHParameters, BigInteger bigInteger) {
        return dHParameters.getG().modPow(bigInteger, dHParameters.getP());
    }
}
