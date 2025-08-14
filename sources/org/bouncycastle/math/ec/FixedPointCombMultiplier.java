package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Nat;

/* loaded from: classes4.dex */
public class FixedPointCombMultiplier extends AbstractECMultiplier {
    @Override // org.bouncycastle.math.ec.AbstractECMultiplier
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECCurve curve = eCPoint.getCurve();
        if (bigInteger.bitLength() > FixedPointUtil.getCombSize(curve)) {
            throw new IllegalStateException("fixed-point comb doesn't support scalars larger than the curve order");
        }
        FixedPointPreCompInfo fixedPointPreCompInfoPrecompute = FixedPointUtil.precompute(eCPoint);
        ECLookupTable lookupTable = fixedPointPreCompInfoPrecompute.getLookupTable();
        int width = fixedPointPreCompInfoPrecompute.getWidth();
        int i = ((r1 + width) - 1) / width;
        ECPoint infinity = curve.getInfinity();
        int i2 = width * i;
        int[] iArrFromBigInteger = Nat.fromBigInteger(i2, bigInteger);
        int i3 = i2 - 1;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = 0;
            for (int i6 = i3 - i4; i6 >= 0; i6 -= i) {
                int i7 = iArrFromBigInteger[i6 >>> 5] >>> (i6 & 31);
                i5 = ((i5 ^ (i7 >>> 1)) << 1) ^ i7;
            }
            infinity = infinity.twicePlus(lookupTable.lookup(i5));
        }
        return infinity.add(fixedPointPreCompInfoPrecompute.getOffset());
    }
}
