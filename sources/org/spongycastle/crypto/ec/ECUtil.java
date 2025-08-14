package org.spongycastle.crypto.ec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.math.ec.ECConstants;

/* loaded from: classes4.dex */
class ECUtil {
    ECUtil() {
    }

    static BigInteger generateK(BigInteger bigInteger, SecureRandom secureRandom) {
        int iBitLength = bigInteger.bitLength();
        while (true) {
            BigInteger bigInteger2 = new BigInteger(iBitLength, secureRandom);
            if (!bigInteger2.equals(ECConstants.ZERO) && bigInteger2.compareTo(bigInteger) < 0) {
                return bigInteger2;
            }
        }
    }
}
