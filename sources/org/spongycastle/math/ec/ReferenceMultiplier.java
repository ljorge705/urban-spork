package org.spongycastle.math.ec;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public class ReferenceMultiplier extends AbstractECMultiplier {
    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        return ECAlgorithms.referenceMultiply(eCPoint, bigInteger);
    }
}
