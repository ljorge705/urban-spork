package org.spongycastle.math.ec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;

/* loaded from: classes7.dex */
class Tnaf {
    private static final BigInteger MINUS_ONE;
    private static final BigInteger MINUS_THREE;
    private static final BigInteger MINUS_TWO;
    public static final byte POW_2_WIDTH = 16;
    public static final byte WIDTH = 4;
    public static final ZTauElement[] alpha0;
    public static final byte[][] alpha0Tnaf;
    public static final ZTauElement[] alpha1;
    public static final byte[][] alpha1Tnaf;

    public static byte getMu(int i) {
        return (byte) (i == 0 ? -1 : 1);
    }

    Tnaf() {
    }

    static {
        BigInteger bigIntegerNegate = ECConstants.ONE.negate();
        MINUS_ONE = bigIntegerNegate;
        MINUS_TWO = ECConstants.TWO.negate();
        BigInteger bigIntegerNegate2 = ECConstants.THREE.negate();
        MINUS_THREE = bigIntegerNegate2;
        alpha0 = new ZTauElement[]{null, new ZTauElement(ECConstants.ONE, ECConstants.ZERO), null, new ZTauElement(bigIntegerNegate2, bigIntegerNegate), null, new ZTauElement(bigIntegerNegate, bigIntegerNegate), null, new ZTauElement(ECConstants.ONE, bigIntegerNegate), null};
        alpha0Tnaf = new byte[][]{null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, 1}};
        alpha1 = new ZTauElement[]{null, new ZTauElement(ECConstants.ONE, ECConstants.ZERO), null, new ZTauElement(bigIntegerNegate2, ECConstants.ONE), null, new ZTauElement(bigIntegerNegate, ECConstants.ONE), null, new ZTauElement(ECConstants.ONE, ECConstants.ONE), null};
        alpha1Tnaf = new byte[][]{null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, -1}};
    }

    public static BigInteger norm(byte b, ZTauElement zTauElement) {
        BigInteger bigIntegerMultiply = zTauElement.u.multiply(zTauElement.u);
        BigInteger bigIntegerMultiply2 = zTauElement.u.multiply(zTauElement.v);
        BigInteger bigIntegerShiftLeft = zTauElement.v.multiply(zTauElement.v).shiftLeft(1);
        if (b == 1) {
            return bigIntegerMultiply.add(bigIntegerMultiply2).add(bigIntegerShiftLeft);
        }
        if (b == -1) {
            return bigIntegerMultiply.subtract(bigIntegerMultiply2).add(bigIntegerShiftLeft);
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    public static SimpleBigDecimal norm(byte b, SimpleBigDecimal simpleBigDecimal, SimpleBigDecimal simpleBigDecimal2) {
        SimpleBigDecimal simpleBigDecimalMultiply = simpleBigDecimal.multiply(simpleBigDecimal);
        SimpleBigDecimal simpleBigDecimalMultiply2 = simpleBigDecimal.multiply(simpleBigDecimal2);
        SimpleBigDecimal simpleBigDecimalShiftLeft = simpleBigDecimal2.multiply(simpleBigDecimal2).shiftLeft(1);
        if (b == 1) {
            return simpleBigDecimalMultiply.add(simpleBigDecimalMultiply2).add(simpleBigDecimalShiftLeft);
        }
        if (b == -1) {
            return simpleBigDecimalMultiply.subtract(simpleBigDecimalMultiply2).add(simpleBigDecimalShiftLeft);
        }
        throw new IllegalArgumentException("mu must be 1 or -1");
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0083, code lost:
    
        if (r5.compareTo(org.spongycastle.math.ec.ECConstants.ONE) >= 0) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.spongycastle.math.ec.ZTauElement round(org.spongycastle.math.ec.SimpleBigDecimal r7, org.spongycastle.math.ec.SimpleBigDecimal r8, byte r9) {
        /*
            int r0 = r7.getScale()
            int r1 = r8.getScale()
            if (r1 != r0) goto La9
            r0 = -1
            r1 = 1
            if (r9 == r1) goto L19
            if (r9 != r0) goto L11
            goto L19
        L11:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "mu must be 1 or -1"
            r7.<init>(r8)
            throw r7
        L19:
            java.math.BigInteger r2 = r7.round()
            java.math.BigInteger r3 = r8.round()
            org.spongycastle.math.ec.SimpleBigDecimal r7 = r7.subtract(r2)
            org.spongycastle.math.ec.SimpleBigDecimal r8 = r8.subtract(r3)
            org.spongycastle.math.ec.SimpleBigDecimal r4 = r7.add(r7)
            if (r9 != r1) goto L34
            org.spongycastle.math.ec.SimpleBigDecimal r4 = r4.add(r8)
            goto L38
        L34:
            org.spongycastle.math.ec.SimpleBigDecimal r4 = r4.subtract(r8)
        L38:
            org.spongycastle.math.ec.SimpleBigDecimal r5 = r8.add(r8)
            org.spongycastle.math.ec.SimpleBigDecimal r5 = r5.add(r8)
            org.spongycastle.math.ec.SimpleBigDecimal r8 = r5.add(r8)
            if (r9 != r1) goto L4f
            org.spongycastle.math.ec.SimpleBigDecimal r5 = r7.subtract(r5)
            org.spongycastle.math.ec.SimpleBigDecimal r7 = r7.add(r8)
            goto L57
        L4f:
            org.spongycastle.math.ec.SimpleBigDecimal r5 = r7.add(r5)
            org.spongycastle.math.ec.SimpleBigDecimal r7 = r7.subtract(r8)
        L57:
            java.math.BigInteger r8 = org.spongycastle.math.ec.ECConstants.ONE
            int r8 = r4.compareTo(r8)
            r6 = 0
            if (r8 < 0) goto L69
            java.math.BigInteger r8 = org.spongycastle.math.ec.Tnaf.MINUS_ONE
            int r8 = r5.compareTo(r8)
            if (r8 >= 0) goto L75
            goto L71
        L69:
            java.math.BigInteger r8 = org.spongycastle.math.ec.ECConstants.TWO
            int r8 = r7.compareTo(r8)
            if (r8 < 0) goto L74
        L71:
            r1 = r6
            r6 = r9
            goto L75
        L74:
            r1 = r6
        L75:
            java.math.BigInteger r8 = org.spongycastle.math.ec.Tnaf.MINUS_ONE
            int r8 = r4.compareTo(r8)
            if (r8 >= 0) goto L86
            java.math.BigInteger r7 = org.spongycastle.math.ec.ECConstants.ONE
            int r7 = r5.compareTo(r7)
            if (r7 < 0) goto L91
            goto L8e
        L86:
            java.math.BigInteger r8 = org.spongycastle.math.ec.Tnaf.MINUS_TWO
            int r7 = r7.compareTo(r8)
            if (r7 >= 0) goto L90
        L8e:
            int r7 = -r9
            byte r6 = (byte) r7
        L90:
            r0 = r1
        L91:
            long r7 = (long) r0
            java.math.BigInteger r7 = java.math.BigInteger.valueOf(r7)
            java.math.BigInteger r7 = r2.add(r7)
            long r8 = (long) r6
            java.math.BigInteger r8 = java.math.BigInteger.valueOf(r8)
            java.math.BigInteger r8 = r3.add(r8)
            org.spongycastle.math.ec.ZTauElement r9 = new org.spongycastle.math.ec.ZTauElement
            r9.<init>(r7, r8)
            return r9
        La9:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "lambda0 and lambda1 do not have same scale"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.math.ec.Tnaf.round(org.spongycastle.math.ec.SimpleBigDecimal, org.spongycastle.math.ec.SimpleBigDecimal, byte):org.spongycastle.math.ec.ZTauElement");
    }

    public static SimpleBigDecimal approximateDivisionByN(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, byte b, int i, int i2) {
        BigInteger bigIntegerMultiply = bigInteger2.multiply(bigInteger.shiftRight(((i - r0) - 2) + b));
        BigInteger bigIntegerAdd = bigIntegerMultiply.add(bigInteger3.multiply(bigIntegerMultiply.shiftRight(i)));
        int i3 = (((i + 5) / 2) + i2) - i2;
        BigInteger bigIntegerShiftRight = bigIntegerAdd.shiftRight(i3);
        if (bigIntegerAdd.testBit(i3 - 1)) {
            bigIntegerShiftRight = bigIntegerShiftRight.add(ECConstants.ONE);
        }
        return new SimpleBigDecimal(bigIntegerShiftRight, i2);
    }

    public static byte[] tauAdicNaf(byte b, ZTauElement zTauElement) {
        BigInteger bigIntegerSubtract;
        if (b != 1 && b != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        int iBitLength = norm(b, zTauElement).bitLength();
        byte[] bArr = new byte[iBitLength > 30 ? iBitLength + 4 : 34];
        BigInteger bigIntegerAdd = zTauElement.u;
        BigInteger bigInteger = zTauElement.v;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (!bigIntegerAdd.equals(ECConstants.ZERO) || !bigInteger.equals(ECConstants.ZERO)) {
                if (bigIntegerAdd.testBit(0)) {
                    byte bIntValue = (byte) ECConstants.TWO.subtract(bigIntegerAdd.subtract(bigInteger.shiftLeft(1)).mod(ECConstants.FOUR)).intValue();
                    bArr[i2] = bIntValue;
                    if (bIntValue == 1) {
                        bigIntegerAdd = bigIntegerAdd.clearBit(0);
                    } else {
                        bigIntegerAdd = bigIntegerAdd.add(ECConstants.ONE);
                    }
                    i = i2;
                } else {
                    bArr[i2] = 0;
                }
                BigInteger bigIntegerShiftRight = bigIntegerAdd.shiftRight(1);
                if (b == 1) {
                    bigIntegerSubtract = bigInteger.add(bigIntegerShiftRight);
                } else {
                    bigIntegerSubtract = bigInteger.subtract(bigIntegerShiftRight);
                }
                BigInteger bigIntegerNegate = bigIntegerAdd.shiftRight(1).negate();
                i2++;
                bigIntegerAdd = bigIntegerSubtract;
                bigInteger = bigIntegerNegate;
            } else {
                int i3 = i + 1;
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, 0, bArr2, 0, i3);
                return bArr2;
            }
        }
    }

    public static ECPoint.AbstractF2m tau(ECPoint.AbstractF2m abstractF2m) {
        return abstractF2m.tau();
    }

    public static byte getMu(ECCurve.AbstractF2m abstractF2m) {
        if (abstractF2m.isKoblitz()) {
            return abstractF2m.getA().isZero() ? (byte) -1 : (byte) 1;
        }
        throw new IllegalArgumentException("No Koblitz curve (ABC), TNAF multiplication not possible");
    }

    public static byte getMu(ECFieldElement eCFieldElement) {
        return (byte) (eCFieldElement.isZero() ? -1 : 1);
    }

    public static BigInteger[] getLucas(byte b, int i, boolean z) {
        BigInteger bigInteger;
        BigInteger bigIntegerSubtract;
        if (b != 1 && b != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        if (z) {
            bigInteger = ECConstants.TWO;
            bigIntegerSubtract = BigInteger.valueOf(b);
        } else {
            bigInteger = ECConstants.ZERO;
            bigIntegerSubtract = ECConstants.ONE;
        }
        int i2 = 1;
        while (i2 < i) {
            i2++;
            BigInteger bigInteger2 = bigIntegerSubtract;
            bigIntegerSubtract = (b == 1 ? bigIntegerSubtract : bigIntegerSubtract.negate()).subtract(bigInteger.shiftLeft(1));
            bigInteger = bigInteger2;
        }
        return new BigInteger[]{bigInteger, bigIntegerSubtract};
    }

    public static BigInteger getTw(byte b, int i) {
        if (i == 4) {
            if (b == 1) {
                return BigInteger.valueOf(6L);
            }
            return BigInteger.valueOf(10L);
        }
        BigInteger[] lucas = getLucas(b, i, false);
        BigInteger bit = ECConstants.ZERO.setBit(i);
        return ECConstants.TWO.multiply(lucas[0]).multiply(lucas[1].modInverse(bit)).mod(bit);
    }

    public static BigInteger[] getSi(ECCurve.AbstractF2m abstractF2m) {
        if (!abstractF2m.isKoblitz()) {
            throw new IllegalArgumentException("si is defined for Koblitz curves only");
        }
        int fieldSize = abstractF2m.getFieldSize();
        int iIntValue = abstractF2m.getA().toBigInteger().intValue();
        byte mu = getMu(iIntValue);
        int shiftsForCofactor = getShiftsForCofactor(abstractF2m.getCofactor());
        BigInteger[] lucas = getLucas(mu, (fieldSize + 3) - iIntValue, false);
        if (mu == 1) {
            lucas[0] = lucas[0].negate();
            lucas[1] = lucas[1].negate();
        }
        return new BigInteger[]{ECConstants.ONE.add(lucas[1]).shiftRight(shiftsForCofactor), ECConstants.ONE.add(lucas[0]).shiftRight(shiftsForCofactor).negate()};
    }

    public static BigInteger[] getSi(int i, int i2, BigInteger bigInteger) {
        byte mu = getMu(i2);
        int shiftsForCofactor = getShiftsForCofactor(bigInteger);
        BigInteger[] lucas = getLucas(mu, (i + 3) - i2, false);
        if (mu == 1) {
            lucas[0] = lucas[0].negate();
            lucas[1] = lucas[1].negate();
        }
        return new BigInteger[]{ECConstants.ONE.add(lucas[1]).shiftRight(shiftsForCofactor), ECConstants.ONE.add(lucas[0]).shiftRight(shiftsForCofactor).negate()};
    }

    protected static int getShiftsForCofactor(BigInteger bigInteger) {
        if (bigInteger != null) {
            if (bigInteger.equals(ECConstants.TWO)) {
                return 1;
            }
            if (bigInteger.equals(ECConstants.FOUR)) {
                return 2;
            }
        }
        throw new IllegalArgumentException("h (Cofactor) must be 2 or 4");
    }

    public static ZTauElement partModReduction(BigInteger bigInteger, int i, byte b, BigInteger[] bigIntegerArr, byte b2, byte b3) {
        BigInteger bigIntegerSubtract;
        if (b2 == 1) {
            bigIntegerSubtract = bigIntegerArr[0].add(bigIntegerArr[1]);
        } else {
            bigIntegerSubtract = bigIntegerArr[0].subtract(bigIntegerArr[1]);
        }
        BigInteger bigInteger2 = getLucas(b2, i, true)[1];
        ZTauElement zTauElementRound = round(approximateDivisionByN(bigInteger, bigIntegerArr[0], bigInteger2, b, i, b3), approximateDivisionByN(bigInteger, bigIntegerArr[1], bigInteger2, b, i, b3), b2);
        return new ZTauElement(bigInteger.subtract(bigIntegerSubtract.multiply(zTauElementRound.u)).subtract(BigInteger.valueOf(2L).multiply(bigIntegerArr[1]).multiply(zTauElementRound.v)), bigIntegerArr[1].multiply(zTauElementRound.u).subtract(bigIntegerArr[0].multiply(zTauElementRound.v)));
    }

    public static ECPoint.AbstractF2m multiplyRTnaf(ECPoint.AbstractF2m abstractF2m, BigInteger bigInteger) {
        ECCurve.AbstractF2m abstractF2m2 = (ECCurve.AbstractF2m) abstractF2m.getCurve();
        int fieldSize = abstractF2m2.getFieldSize();
        int iIntValue = abstractF2m2.getA().toBigInteger().intValue();
        return multiplyTnaf(abstractF2m, partModReduction(bigInteger, fieldSize, (byte) iIntValue, abstractF2m2.getSi(), getMu(iIntValue), (byte) 10));
    }

    public static ECPoint.AbstractF2m multiplyTnaf(ECPoint.AbstractF2m abstractF2m, ZTauElement zTauElement) {
        return multiplyFromTnaf(abstractF2m, tauAdicNaf(getMu(((ECCurve.AbstractF2m) abstractF2m.getCurve()).getA()), zTauElement));
    }

    public static ECPoint.AbstractF2m multiplyFromTnaf(ECPoint.AbstractF2m abstractF2m, byte[] bArr) {
        ECPoint.AbstractF2m abstractF2m2 = (ECPoint.AbstractF2m) abstractF2m.getCurve().getInfinity();
        ECPoint.AbstractF2m abstractF2m3 = (ECPoint.AbstractF2m) abstractF2m.negate();
        int i = 0;
        for (int length = bArr.length - 1; length >= 0; length--) {
            i++;
            byte b = bArr[length];
            if (b != 0) {
                abstractF2m2 = (ECPoint.AbstractF2m) abstractF2m2.tauPow(i).add(b > 0 ? abstractF2m : abstractF2m3);
                i = 0;
            }
        }
        return i > 0 ? abstractF2m2.tauPow(i) : abstractF2m2;
    }

    public static byte[] tauAdicWNaf(byte b, ZTauElement zTauElement, byte b2, BigInteger bigInteger, BigInteger bigInteger2, ZTauElement[] zTauElementArr) {
        BigInteger bigIntegerSubtract;
        int iIntValue;
        boolean z;
        if (b != 1 && b != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        int iBitLength = norm(b, zTauElement).bitLength();
        byte[] bArr = new byte[iBitLength > 30 ? iBitLength + 4 + b2 : b2 + 34];
        BigInteger bigIntegerShiftRight = bigInteger.shiftRight(1);
        BigInteger bigIntegerAdd = zTauElement.u;
        BigInteger bigIntegerAdd2 = zTauElement.v;
        int i = 0;
        while (true) {
            if (bigIntegerAdd.equals(ECConstants.ZERO) && bigIntegerAdd2.equals(ECConstants.ZERO)) {
                return bArr;
            }
            if (bigIntegerAdd.testBit(0)) {
                BigInteger bigIntegerMod = bigIntegerAdd.add(bigIntegerAdd2.multiply(bigInteger2)).mod(bigInteger);
                if (bigIntegerMod.compareTo(bigIntegerShiftRight) >= 0) {
                    iIntValue = bigIntegerMod.subtract(bigInteger).intValue();
                } else {
                    iIntValue = bigIntegerMod.intValue();
                }
                byte b3 = (byte) iIntValue;
                bArr[i] = b3;
                if (b3 < 0) {
                    b3 = (byte) (-b3);
                    z = false;
                } else {
                    z = true;
                }
                if (z) {
                    bigIntegerAdd = bigIntegerAdd.subtract(zTauElementArr[b3].u);
                    bigIntegerAdd2 = bigIntegerAdd2.subtract(zTauElementArr[b3].v);
                } else {
                    bigIntegerAdd = bigIntegerAdd.add(zTauElementArr[b3].u);
                    bigIntegerAdd2 = bigIntegerAdd2.add(zTauElementArr[b3].v);
                }
            } else {
                bArr[i] = 0;
            }
            if (b == 1) {
                bigIntegerSubtract = bigIntegerAdd2.add(bigIntegerAdd.shiftRight(1));
            } else {
                bigIntegerSubtract = bigIntegerAdd2.subtract(bigIntegerAdd.shiftRight(1));
            }
            BigInteger bigIntegerNegate = bigIntegerAdd.shiftRight(1).negate();
            i++;
            bigIntegerAdd = bigIntegerSubtract;
            bigIntegerAdd2 = bigIntegerNegate;
        }
    }

    public static ECPoint.AbstractF2m[] getPreComp(ECPoint.AbstractF2m abstractF2m, byte b) {
        byte[][] bArr = b == 0 ? alpha0Tnaf : alpha1Tnaf;
        ECPoint.AbstractF2m[] abstractF2mArr = new ECPoint.AbstractF2m[(bArr.length + 1) >>> 1];
        abstractF2mArr[0] = abstractF2m;
        int length = bArr.length;
        for (int i = 3; i < length; i += 2) {
            abstractF2mArr[i >>> 1] = multiplyFromTnaf(abstractF2m, bArr[i]);
        }
        abstractF2m.getCurve().normalizeAll(abstractF2mArr);
        return abstractF2mArr;
    }
}
