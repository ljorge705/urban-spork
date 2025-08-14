package org.spongycastle.math.ec;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public class ZSignedDigitR2LMultiplier extends AbstractECMultiplier {
    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECPoint infinity = eCPoint.getCurve().getInfinity();
        int iBitLength = bigInteger.bitLength();
        int lowestSetBit = bigInteger.getLowestSetBit();
        ECPoint eCPointTimesPow2 = eCPoint.timesPow2(lowestSetBit);
        while (true) {
            lowestSetBit++;
            if (lowestSetBit < iBitLength) {
                infinity = infinity.add(bigInteger.testBit(lowestSetBit) ? eCPointTimesPow2 : eCPointTimesPow2.negate());
                eCPointTimesPow2 = eCPointTimesPow2.twice();
            } else {
                return infinity.add(eCPointTimesPow2);
            }
        }
    }
}
