package org.spongycastle.math.ec;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public class FixedPointCombMultiplier extends AbstractECMultiplier {
    protected int getWidthForCombSize(int i) {
        return i > 257 ? 6 : 5;
    }

    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    protected ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        ECCurve curve = eCPoint.getCurve();
        int combSize = FixedPointUtil.getCombSize(curve);
        if (bigInteger.bitLength() > combSize) {
            throw new IllegalStateException("fixed-point comb doesn't support scalars larger than the curve order");
        }
        FixedPointPreCompInfo fixedPointPreCompInfoPrecompute = FixedPointUtil.precompute(eCPoint, getWidthForCombSize(combSize));
        ECPoint[] preComp = fixedPointPreCompInfoPrecompute.getPreComp();
        int width = fixedPointPreCompInfoPrecompute.getWidth();
        int i = ((combSize + width) - 1) / width;
        ECPoint infinity = curve.getInfinity();
        int i2 = (width * i) - 1;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = 0;
            for (int i5 = i2 - i3; i5 >= 0; i5 -= i) {
                i4 <<= 1;
                if (bigInteger.testBit(i5)) {
                    i4 |= 1;
                }
            }
            infinity = infinity.twicePlus(preComp[i4]);
        }
        return infinity.add(fixedPointPreCompInfoPrecompute.getOffset());
    }
}
