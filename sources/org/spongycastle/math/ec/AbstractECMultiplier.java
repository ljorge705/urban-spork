package org.spongycastle.math.ec;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public abstract class AbstractECMultiplier implements ECMultiplier {
    protected abstract ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger);

    @Override // org.spongycastle.math.ec.ECMultiplier
    public ECPoint multiply(ECPoint eCPoint, BigInteger bigInteger) {
        int iSignum = bigInteger.signum();
        if (iSignum == 0 || eCPoint.isInfinity()) {
            return eCPoint.getCurve().getInfinity();
        }
        ECPoint eCPointMultiplyPositive = multiplyPositive(eCPoint, bigInteger.abs());
        if (iSignum <= 0) {
            eCPointMultiplyPositive = eCPointMultiplyPositive.negate();
        }
        return ECAlgorithms.validatePoint(eCPointMultiplyPositive);
    }
}
