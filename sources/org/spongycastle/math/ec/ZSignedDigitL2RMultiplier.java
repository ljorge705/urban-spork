package org.spongycastle.math.ec;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public class ZSignedDigitL2RMultiplier extends AbstractECMultiplier {
    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint eCPointNormalize = eCPoint.normalize();
        ECPoint eCPointNegate = eCPointNormalize.negate();
        int iBitLength = bigInteger.bitLength();
        int lowestSetBit = bigInteger.getLowestSetBit();
        ECPoint eCPointTwicePlus = eCPointNormalize;
        while (true) {
            iBitLength--;
            if (iBitLength > lowestSetBit) {
                eCPointTwicePlus = eCPointTwicePlus.twicePlus(bigInteger.testBit(iBitLength) ? eCPointNormalize : eCPointNegate);
            } else {
                return eCPointTwicePlus.timesPow2(lowestSetBit);
            }
        }
    }
}
