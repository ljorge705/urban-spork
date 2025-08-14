package org.spongycastle.math.ec;

import java.math.BigInteger;
import java.util.Hashtable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.spongycastle.math.ec.ECFieldElement;

/* loaded from: classes7.dex */
public abstract class ECPoint {
    protected static ECFieldElement[] EMPTY_ZS = new ECFieldElement[0];
    protected ECCurve curve;
    protected Hashtable preCompTable;
    protected boolean withCompression;
    protected ECFieldElement x;
    protected ECFieldElement y;
    protected ECFieldElement[] zs;

    public abstract ECPoint add(ECPoint eCPoint);

    protected abstract ECPoint detach();

    protected abstract boolean getCompressionYTilde();

    public ECCurve getCurve() {
        return this.curve;
    }

    public final ECFieldElement getRawXCoord() {
        return this.x;
    }

    public final ECFieldElement getRawYCoord() {
        return this.y;
    }

    protected final ECFieldElement[] getRawZCoords() {
        return this.zs;
    }

    public ECFieldElement getXCoord() {
        return this.x;
    }

    public ECFieldElement getYCoord() {
        return this.y;
    }

    public boolean isCompressed() {
        return this.withCompression;
    }

    public abstract ECPoint negate();

    protected abstract boolean satisfiesCurveEquation();

    public abstract ECPoint subtract(ECPoint eCPoint);

    public abstract ECPoint twice();

    protected static ECFieldElement[] getInitialZCoords(ECCurve eCCurve) {
        int coordinateSystem = eCCurve == null ? 0 : eCCurve.getCoordinateSystem();
        if (coordinateSystem == 0 || coordinateSystem == 5) {
            return EMPTY_ZS;
        }
        ECFieldElement eCFieldElementFromBigInteger = eCCurve.fromBigInteger(ECConstants.ONE);
        if (coordinateSystem != 1 && coordinateSystem != 2) {
            if (coordinateSystem == 3) {
                return new ECFieldElement[]{eCFieldElementFromBigInteger, eCFieldElementFromBigInteger, eCFieldElementFromBigInteger};
            }
            if (coordinateSystem == 4) {
                return new ECFieldElement[]{eCFieldElementFromBigInteger, eCCurve.getA()};
            }
            if (coordinateSystem != 6) {
                throw new IllegalArgumentException("unknown coordinate system");
            }
        }
        return new ECFieldElement[]{eCFieldElementFromBigInteger};
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, getInitialZCoords(eCCurve));
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        this.preCompTable = null;
        this.curve = eCCurve;
        this.x = eCFieldElement;
        this.y = eCFieldElement2;
        this.zs = eCFieldElementArr;
    }

    protected boolean satisfiesCofactor() {
        BigInteger cofactor = this.curve.getCofactor();
        return cofactor == null || cofactor.equals(ECConstants.ONE) || !ECAlgorithms.referenceMultiply(this, cofactor).isInfinity();
    }

    public final ECPoint getDetachedPoint() {
        return normalize().detach();
    }

    protected int getCurveCoordinateSystem() {
        ECCurve eCCurve = this.curve;
        if (eCCurve == null) {
            return 0;
        }
        return eCCurve.getCoordinateSystem();
    }

    public ECFieldElement getX() {
        return normalize().getXCoord();
    }

    public ECFieldElement getY() {
        return normalize().getYCoord();
    }

    public ECFieldElement getAffineXCoord() {
        checkNormalized();
        return getXCoord();
    }

    public ECFieldElement getAffineYCoord() {
        checkNormalized();
        return getYCoord();
    }

    public ECFieldElement getZCoord(int i) {
        if (i >= 0) {
            ECFieldElement[] eCFieldElementArr = this.zs;
            if (i < eCFieldElementArr.length) {
                return eCFieldElementArr[i];
            }
        }
        return null;
    }

    public ECFieldElement[] getZCoords() {
        ECFieldElement[] eCFieldElementArr = this.zs;
        int length = eCFieldElementArr.length;
        if (length == 0) {
            return EMPTY_ZS;
        }
        ECFieldElement[] eCFieldElementArr2 = new ECFieldElement[length];
        System.arraycopy(eCFieldElementArr, 0, eCFieldElementArr2, 0, length);
        return eCFieldElementArr2;
    }

    protected void checkNormalized() {
        if (!isNormalized()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    public boolean isNormalized() {
        int curveCoordinateSystem = getCurveCoordinateSystem();
        return curveCoordinateSystem == 0 || curveCoordinateSystem == 5 || isInfinity() || this.zs[0].isOne();
    }

    public ECPoint normalize() {
        int curveCoordinateSystem;
        if (isInfinity() || (curveCoordinateSystem = getCurveCoordinateSystem()) == 0 || curveCoordinateSystem == 5) {
            return this;
        }
        ECFieldElement zCoord = getZCoord(0);
        return zCoord.isOne() ? this : normalize(zCoord.invert());
    }

    ECPoint normalize(ECFieldElement eCFieldElement) {
        int curveCoordinateSystem = getCurveCoordinateSystem();
        if (curveCoordinateSystem != 1) {
            if (curveCoordinateSystem == 2 || curveCoordinateSystem == 3 || curveCoordinateSystem == 4) {
                ECFieldElement eCFieldElementSquare = eCFieldElement.square();
                return createScaledPoint(eCFieldElementSquare, eCFieldElementSquare.multiply(eCFieldElement));
            }
            if (curveCoordinateSystem != 6) {
                throw new IllegalStateException("not a projective coordinate system");
            }
        }
        return createScaledPoint(eCFieldElement, eCFieldElement);
    }

    protected ECPoint createScaledPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord().multiply(eCFieldElement2), this.withCompression);
    }

    public boolean isInfinity() {
        if (this.x != null && this.y != null) {
            ECFieldElement[] eCFieldElementArr = this.zs;
            if (eCFieldElementArr.length <= 0 || !eCFieldElementArr[0].isZero()) {
                return false;
            }
        }
        return true;
    }

    public boolean isValid() {
        return isInfinity() || getCurve() == null || (satisfiesCurveEquation() && satisfiesCofactor());
    }

    public ECPoint scaleX(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord(), getRawZCoords(), this.withCompression);
    }

    public ECPoint scaleY(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord(), getRawYCoord().multiply(eCFieldElement), getRawZCoords(), this.withCompression);
    }

    public boolean equals(ECPoint eCPoint) {
        ECPoint eCPointNormalize;
        if (eCPoint == null) {
            return false;
        }
        ECCurve curve = getCurve();
        ECCurve curve2 = eCPoint.getCurve();
        boolean z = curve == null;
        boolean z2 = curve2 == null;
        boolean zIsInfinity = isInfinity();
        boolean zIsInfinity2 = eCPoint.isInfinity();
        if (zIsInfinity || zIsInfinity2) {
            if (zIsInfinity && zIsInfinity2) {
                return z || z2 || curve.equals(curve2);
            }
            return false;
        }
        if (z && z2) {
            eCPointNormalize = this;
        } else if (z) {
            eCPoint = eCPoint.normalize();
            eCPointNormalize = this;
        } else if (z2) {
            eCPointNormalize = normalize();
        } else {
            if (!curve.equals(curve2)) {
                return false;
            }
            ECPoint[] eCPointArr = {this, curve.importPoint(eCPoint)};
            curve.normalizeAll(eCPointArr);
            eCPointNormalize = eCPointArr[0];
            eCPoint = eCPointArr[1];
        }
        return eCPointNormalize.getXCoord().equals(eCPoint.getXCoord()) && eCPointNormalize.getYCoord().equals(eCPoint.getYCoord());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ECPoint) {
            return equals((ECPoint) obj);
        }
        return false;
    }

    public int hashCode() {
        ECCurve curve = getCurve();
        int i = curve == null ? 0 : ~curve.hashCode();
        if (isInfinity()) {
            return i;
        }
        ECPoint eCPointNormalize = normalize();
        return (i ^ (eCPointNormalize.getXCoord().hashCode() * 17)) ^ (eCPointNormalize.getYCoord().hashCode() * 257);
    }

    public String toString() {
        if (isInfinity()) {
            return "INF";
        }
        StringBuffer stringBuffer = new StringBuffer("(");
        stringBuffer.append(getRawXCoord());
        stringBuffer.append(AbstractJsonLexerKt.COMMA);
        stringBuffer.append(getRawYCoord());
        for (int i = 0; i < this.zs.length; i++) {
            stringBuffer.append(AbstractJsonLexerKt.COMMA);
            stringBuffer.append(this.zs[i]);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public byte[] getEncoded() {
        return getEncoded(this.withCompression);
    }

    public byte[] getEncoded(boolean z) {
        if (isInfinity()) {
            return new byte[1];
        }
        ECPoint eCPointNormalize = normalize();
        byte[] encoded = eCPointNormalize.getXCoord().getEncoded();
        if (z) {
            byte[] bArr = new byte[encoded.length + 1];
            bArr[0] = (byte) (eCPointNormalize.getCompressionYTilde() ? 3 : 2);
            System.arraycopy(encoded, 0, bArr, 1, encoded.length);
            return bArr;
        }
        byte[] encoded2 = eCPointNormalize.getYCoord().getEncoded();
        byte[] bArr2 = new byte[encoded.length + encoded2.length + 1];
        bArr2[0] = 4;
        System.arraycopy(encoded, 0, bArr2, 1, encoded.length);
        System.arraycopy(encoded2, 0, bArr2, encoded.length + 1, encoded2.length);
        return bArr2;
    }

    public ECPoint timesPow2(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
        ECPoint eCPointTwice = this;
        while (true) {
            i--;
            if (i < 0) {
                return eCPointTwice;
            }
            eCPointTwice = eCPointTwice.twice();
        }
    }

    public ECPoint twicePlus(ECPoint eCPoint) {
        return twice().add(eCPoint);
    }

    public ECPoint threeTimes() {
        return twicePlus(this);
    }

    public ECPoint multiply(BigInteger bigInteger) {
        return getCurve().getMultiplier().multiply(this, bigInteger);
    }

    public static abstract class AbstractFp extends ECPoint {
        protected AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        protected AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        protected boolean getCompressionYTilde() {
            return getAffineYCoord().testBitZero();
        }

        @Override // org.spongycastle.math.ec.ECPoint
        protected boolean satisfiesCurveEquation() {
            ECFieldElement eCFieldElement = this.x;
            ECFieldElement eCFieldElement2 = this.y;
            ECFieldElement a2 = this.curve.getA();
            ECFieldElement b = this.curve.getB();
            ECFieldElement eCFieldElementSquare = eCFieldElement2.square();
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem != 0) {
                if (curveCoordinateSystem == 1) {
                    ECFieldElement eCFieldElement3 = this.zs[0];
                    if (!eCFieldElement3.isOne()) {
                        ECFieldElement eCFieldElementSquare2 = eCFieldElement3.square();
                        ECFieldElement eCFieldElementMultiply = eCFieldElement3.multiply(eCFieldElementSquare2);
                        eCFieldElementSquare = eCFieldElementSquare.multiply(eCFieldElement3);
                        a2 = a2.multiply(eCFieldElementSquare2);
                        b = b.multiply(eCFieldElementMultiply);
                    }
                } else if (curveCoordinateSystem == 2 || curveCoordinateSystem == 3 || curveCoordinateSystem == 4) {
                    ECFieldElement eCFieldElement4 = this.zs[0];
                    if (!eCFieldElement4.isOne()) {
                        ECFieldElement eCFieldElementSquare3 = eCFieldElement4.square();
                        ECFieldElement eCFieldElementSquare4 = eCFieldElementSquare3.square();
                        ECFieldElement eCFieldElementMultiply2 = eCFieldElementSquare3.multiply(eCFieldElementSquare4);
                        a2 = a2.multiply(eCFieldElementSquare4);
                        b = b.multiply(eCFieldElementMultiply2);
                    }
                } else {
                    throw new IllegalStateException("unsupported coordinate system");
                }
            }
            return eCFieldElementSquare.equals(eCFieldElement.square().add(a2).multiply(eCFieldElement).add(b));
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint subtract(ECPoint eCPoint) {
            return eCPoint.isInfinity() ? this : add(eCPoint.negate());
        }
    }

    public static class Fp extends AbstractFp {
        public Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this(eCCurve, eCFieldElement, eCFieldElement2, false);
        }

        public Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
            if ((eCFieldElement == null) != (eCFieldElement2 == null)) {
                throw new IllegalArgumentException("Exactly one of the field elements is null");
            }
            this.withCompression = z;
        }

        Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
            this.withCompression = z;
        }

        @Override // org.spongycastle.math.ec.ECPoint
        protected ECPoint detach() {
            return new Fp(null, getAffineXCoord(), getAffineYCoord());
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECFieldElement getZCoord(int i) {
            if (i == 1 && 4 == getCurveCoordinateSystem()) {
                return getJacobianModifiedW();
            }
            return super.getZCoord(i);
        }

        /* JADX WARN: Removed duplicated region for block: B:60:0x0127  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x0135  */
        @Override // org.spongycastle.math.ec.ECPoint
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public org.spongycastle.math.ec.ECPoint add(org.spongycastle.math.ec.ECPoint r17) {
            /*
                Method dump skipped, instructions count: 543
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.math.ec.ECPoint.Fp.add(org.spongycastle.math.ec.ECPoint):org.spongycastle.math.ec.ECPoint");
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint twice() {
            ECFieldElement eCFieldElementAdd;
            ECFieldElement eCFieldElementFour;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.y;
            if (eCFieldElement.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement2 = this.x;
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElementDivide = three(eCFieldElement2.square()).add(getCurve().getA()).divide(two(eCFieldElement));
                ECFieldElement eCFieldElementSubtract = eCFieldElementDivide.square().subtract(two(eCFieldElement2));
                return new Fp(curve, eCFieldElementSubtract, eCFieldElementDivide.multiply(eCFieldElement2.subtract(eCFieldElementSubtract)).subtract(eCFieldElement), this.withCompression);
            }
            if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement3 = this.zs[0];
                boolean zIsOne = eCFieldElement3.isOne();
                ECFieldElement a2 = curve.getA();
                if (!a2.isZero() && !zIsOne) {
                    a2 = a2.multiply(eCFieldElement3.square());
                }
                ECFieldElement eCFieldElementAdd2 = a2.add(three(eCFieldElement2.square()));
                ECFieldElement eCFieldElementMultiply = zIsOne ? eCFieldElement : eCFieldElement.multiply(eCFieldElement3);
                ECFieldElement eCFieldElementSquare = zIsOne ? eCFieldElement.square() : eCFieldElementMultiply.multiply(eCFieldElement);
                ECFieldElement eCFieldElementFour2 = four(eCFieldElement2.multiply(eCFieldElementSquare));
                ECFieldElement eCFieldElementSubtract2 = eCFieldElementAdd2.square().subtract(two(eCFieldElementFour2));
                ECFieldElement eCFieldElementTwo = two(eCFieldElementMultiply);
                ECFieldElement eCFieldElementMultiply2 = eCFieldElementSubtract2.multiply(eCFieldElementTwo);
                ECFieldElement eCFieldElementTwo2 = two(eCFieldElementSquare);
                return new Fp(curve, eCFieldElementMultiply2, eCFieldElementFour2.subtract(eCFieldElementSubtract2).multiply(eCFieldElementAdd2).subtract(two(eCFieldElementTwo2.square())), new ECFieldElement[]{two(zIsOne ? two(eCFieldElementTwo2) : eCFieldElementTwo.square()).multiply(eCFieldElementMultiply)}, this.withCompression);
            }
            if (coordinateSystem != 2) {
                if (coordinateSystem == 4) {
                    return twiceJacobianModified(true);
                }
                throw new IllegalStateException("unsupported coordinate system");
            }
            ECFieldElement eCFieldElement4 = this.zs[0];
            boolean zIsOne2 = eCFieldElement4.isOne();
            ECFieldElement eCFieldElementSquare2 = eCFieldElement.square();
            ECFieldElement eCFieldElementSquare3 = eCFieldElementSquare2.square();
            ECFieldElement a3 = curve.getA();
            ECFieldElement eCFieldElementNegate = a3.negate();
            if (eCFieldElementNegate.toBigInteger().equals(BigInteger.valueOf(3L))) {
                ECFieldElement eCFieldElementSquare4 = zIsOne2 ? eCFieldElement4 : eCFieldElement4.square();
                eCFieldElementAdd = three(eCFieldElement2.add(eCFieldElementSquare4).multiply(eCFieldElement2.subtract(eCFieldElementSquare4)));
                eCFieldElementFour = four(eCFieldElementSquare2.multiply(eCFieldElement2));
            } else {
                ECFieldElement eCFieldElementThree = three(eCFieldElement2.square());
                if (zIsOne2) {
                    eCFieldElementAdd = eCFieldElementThree.add(a3);
                } else if (a3.isZero()) {
                    eCFieldElementAdd = eCFieldElementThree;
                } else {
                    ECFieldElement eCFieldElementSquare5 = eCFieldElement4.square().square();
                    if (eCFieldElementNegate.bitLength() < a3.bitLength()) {
                        eCFieldElementAdd = eCFieldElementThree.subtract(eCFieldElementSquare5.multiply(eCFieldElementNegate));
                    } else {
                        eCFieldElementAdd = eCFieldElementThree.add(eCFieldElementSquare5.multiply(a3));
                    }
                }
                eCFieldElementFour = four(eCFieldElement2.multiply(eCFieldElementSquare2));
            }
            ECFieldElement eCFieldElementSubtract3 = eCFieldElementAdd.square().subtract(two(eCFieldElementFour));
            ECFieldElement eCFieldElementSubtract4 = eCFieldElementFour.subtract(eCFieldElementSubtract3).multiply(eCFieldElementAdd).subtract(eight(eCFieldElementSquare3));
            ECFieldElement eCFieldElementTwo3 = two(eCFieldElement);
            if (!zIsOne2) {
                eCFieldElementTwo3 = eCFieldElementTwo3.multiply(eCFieldElement4);
            }
            return new Fp(curve, eCFieldElementSubtract3, eCFieldElementSubtract4, new ECFieldElement[]{eCFieldElementTwo3}, this.withCompression);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint twicePlus(ECPoint eCPoint) {
            if (this == eCPoint) {
                return threeTimes();
            }
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return twice();
            }
            ECFieldElement eCFieldElement = this.y;
            if (eCFieldElement.isZero()) {
                return eCPoint;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 0) {
                if (coordinateSystem == 4) {
                    return twiceJacobianModified(false).add(eCPoint);
                }
                return twice().add(eCPoint);
            }
            ECFieldElement eCFieldElement2 = this.x;
            ECFieldElement eCFieldElement3 = eCPoint.x;
            ECFieldElement eCFieldElement4 = eCPoint.y;
            ECFieldElement eCFieldElementSubtract = eCFieldElement3.subtract(eCFieldElement2);
            ECFieldElement eCFieldElementSubtract2 = eCFieldElement4.subtract(eCFieldElement);
            if (eCFieldElementSubtract.isZero()) {
                return eCFieldElementSubtract2.isZero() ? threeTimes() : this;
            }
            ECFieldElement eCFieldElementSquare = eCFieldElementSubtract.square();
            ECFieldElement eCFieldElementSubtract3 = eCFieldElementSquare.multiply(two(eCFieldElement2).add(eCFieldElement3)).subtract(eCFieldElementSubtract2.square());
            if (eCFieldElementSubtract3.isZero()) {
                return curve.getInfinity();
            }
            ECFieldElement eCFieldElementInvert = eCFieldElementSubtract3.multiply(eCFieldElementSubtract).invert();
            ECFieldElement eCFieldElementMultiply = eCFieldElementSubtract3.multiply(eCFieldElementInvert).multiply(eCFieldElementSubtract2);
            ECFieldElement eCFieldElementSubtract4 = two(eCFieldElement).multiply(eCFieldElementSquare).multiply(eCFieldElementSubtract).multiply(eCFieldElementInvert).subtract(eCFieldElementMultiply);
            ECFieldElement eCFieldElementAdd = eCFieldElementSubtract4.subtract(eCFieldElementMultiply).multiply(eCFieldElementMultiply.add(eCFieldElementSubtract4)).add(eCFieldElement3);
            return new Fp(curve, eCFieldElementAdd, eCFieldElement2.subtract(eCFieldElementAdd).multiply(eCFieldElementSubtract4).subtract(eCFieldElement), this.withCompression);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint threeTimes() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.y;
            if (eCFieldElement.isZero()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 0) {
                if (coordinateSystem == 4) {
                    return twiceJacobianModified(false).add(this);
                }
                return twice().add(this);
            }
            ECFieldElement eCFieldElement2 = this.x;
            ECFieldElement eCFieldElementTwo = two(eCFieldElement);
            ECFieldElement eCFieldElementSquare = eCFieldElementTwo.square();
            ECFieldElement eCFieldElementAdd = three(eCFieldElement2.square()).add(getCurve().getA());
            ECFieldElement eCFieldElementSubtract = three(eCFieldElement2).multiply(eCFieldElementSquare).subtract(eCFieldElementAdd.square());
            if (eCFieldElementSubtract.isZero()) {
                return getCurve().getInfinity();
            }
            ECFieldElement eCFieldElementInvert = eCFieldElementSubtract.multiply(eCFieldElementTwo).invert();
            ECFieldElement eCFieldElementMultiply = eCFieldElementSubtract.multiply(eCFieldElementInvert).multiply(eCFieldElementAdd);
            ECFieldElement eCFieldElementSubtract2 = eCFieldElementSquare.square().multiply(eCFieldElementInvert).subtract(eCFieldElementMultiply);
            ECFieldElement eCFieldElementAdd2 = eCFieldElementSubtract2.subtract(eCFieldElementMultiply).multiply(eCFieldElementMultiply.add(eCFieldElementSubtract2)).add(eCFieldElement2);
            return new Fp(curve, eCFieldElementAdd2, eCFieldElement2.subtract(eCFieldElementAdd2).multiply(eCFieldElementSubtract2).subtract(eCFieldElement), this.withCompression);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint timesPow2(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("'e' cannot be negative");
            }
            if (i == 0 || isInfinity()) {
                return this;
            }
            if (i == 1) {
                return twice();
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElementMultiply = this.y;
            if (eCFieldElementMultiply.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement a2 = curve.getA();
            ECFieldElement eCFieldElementMultiply2 = this.x;
            ECFieldElement eCFieldElementFromBigInteger = this.zs.length < 1 ? curve.fromBigInteger(ECConstants.ONE) : this.zs[0];
            if (!eCFieldElementFromBigInteger.isOne() && coordinateSystem != 0) {
                if (coordinateSystem == 1) {
                    ECFieldElement eCFieldElementSquare = eCFieldElementFromBigInteger.square();
                    eCFieldElementMultiply2 = eCFieldElementMultiply2.multiply(eCFieldElementFromBigInteger);
                    eCFieldElementMultiply = eCFieldElementMultiply.multiply(eCFieldElementSquare);
                    a2 = calculateJacobianModifiedW(eCFieldElementFromBigInteger, eCFieldElementSquare);
                } else if (coordinateSystem == 2) {
                    a2 = calculateJacobianModifiedW(eCFieldElementFromBigInteger, null);
                } else if (coordinateSystem == 4) {
                    a2 = getJacobianModifiedW();
                } else {
                    throw new IllegalStateException("unsupported coordinate system");
                }
            }
            int i2 = 0;
            ECFieldElement eCFieldElement = a2;
            ECFieldElement eCFieldElementSubtract = eCFieldElementMultiply;
            ECFieldElement eCFieldElement2 = eCFieldElementMultiply2;
            ECFieldElement eCFieldElementTwo = eCFieldElement;
            while (i2 < i) {
                if (eCFieldElementSubtract.isZero()) {
                    return curve.getInfinity();
                }
                ECFieldElement eCFieldElementThree = three(eCFieldElement2.square());
                ECFieldElement eCFieldElementTwo2 = two(eCFieldElementSubtract);
                ECFieldElement eCFieldElementMultiply3 = eCFieldElementTwo2.multiply(eCFieldElementSubtract);
                ECFieldElement eCFieldElementTwo3 = two(eCFieldElement2.multiply(eCFieldElementMultiply3));
                ECFieldElement eCFieldElementTwo4 = two(eCFieldElementMultiply3.square());
                if (!eCFieldElementTwo.isZero()) {
                    eCFieldElementThree = eCFieldElementThree.add(eCFieldElementTwo);
                    eCFieldElementTwo = two(eCFieldElementTwo4.multiply(eCFieldElementTwo));
                }
                ECFieldElement eCFieldElementSubtract2 = eCFieldElementThree.square().subtract(two(eCFieldElementTwo3));
                eCFieldElementSubtract = eCFieldElementThree.multiply(eCFieldElementTwo3.subtract(eCFieldElementSubtract2)).subtract(eCFieldElementTwo4);
                eCFieldElementFromBigInteger = eCFieldElementFromBigInteger.isOne() ? eCFieldElementTwo2 : eCFieldElementTwo2.multiply(eCFieldElementFromBigInteger);
                i2++;
                eCFieldElement2 = eCFieldElementSubtract2;
            }
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElementInvert = eCFieldElementFromBigInteger.invert();
                ECFieldElement eCFieldElementSquare2 = eCFieldElementInvert.square();
                return new Fp(curve, eCFieldElement2.multiply(eCFieldElementSquare2), eCFieldElementSubtract.multiply(eCFieldElementSquare2.multiply(eCFieldElementInvert)), this.withCompression);
            }
            if (coordinateSystem == 1) {
                return new Fp(curve, eCFieldElement2.multiply(eCFieldElementFromBigInteger), eCFieldElementSubtract, new ECFieldElement[]{eCFieldElementFromBigInteger.multiply(eCFieldElementFromBigInteger.square())}, this.withCompression);
            }
            if (coordinateSystem == 2) {
                return new Fp(curve, eCFieldElement2, eCFieldElementSubtract, new ECFieldElement[]{eCFieldElementFromBigInteger}, this.withCompression);
            }
            if (coordinateSystem == 4) {
                return new Fp(curve, eCFieldElement2, eCFieldElementSubtract, new ECFieldElement[]{eCFieldElementFromBigInteger, eCFieldElementTwo}, this.withCompression);
            }
            throw new IllegalStateException("unsupported coordinate system");
        }

        protected ECFieldElement two(ECFieldElement eCFieldElement) {
            return eCFieldElement.add(eCFieldElement);
        }

        protected ECFieldElement three(ECFieldElement eCFieldElement) {
            return two(eCFieldElement).add(eCFieldElement);
        }

        protected ECFieldElement four(ECFieldElement eCFieldElement) {
            return two(two(eCFieldElement));
        }

        protected ECFieldElement eight(ECFieldElement eCFieldElement) {
            return four(two(eCFieldElement));
        }

        protected ECFieldElement doubleProductFromSquares(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3, ECFieldElement eCFieldElement4) {
            return eCFieldElement.add(eCFieldElement2).square().subtract(eCFieldElement3).subtract(eCFieldElement4);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            if (curve.getCoordinateSystem() != 0) {
                return new Fp(curve, this.x, this.y.negate(), this.zs, this.withCompression);
            }
            return new Fp(curve, this.x, this.y.negate(), this.withCompression);
        }

        protected ECFieldElement calculateJacobianModifiedW(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            ECFieldElement a2 = getCurve().getA();
            if (a2.isZero() || eCFieldElement.isOne()) {
                return a2;
            }
            if (eCFieldElement2 == null) {
                eCFieldElement2 = eCFieldElement.square();
            }
            ECFieldElement eCFieldElementSquare = eCFieldElement2.square();
            ECFieldElement eCFieldElementNegate = a2.negate();
            if (eCFieldElementNegate.bitLength() < a2.bitLength()) {
                return eCFieldElementSquare.multiply(eCFieldElementNegate).negate();
            }
            return eCFieldElementSquare.multiply(a2);
        }

        protected ECFieldElement getJacobianModifiedW() {
            ECFieldElement eCFieldElement = this.zs[1];
            if (eCFieldElement != null) {
                return eCFieldElement;
            }
            ECFieldElement[] eCFieldElementArr = this.zs;
            ECFieldElement eCFieldElementCalculateJacobianModifiedW = calculateJacobianModifiedW(this.zs[0], null);
            eCFieldElementArr[1] = eCFieldElementCalculateJacobianModifiedW;
            return eCFieldElementCalculateJacobianModifiedW;
        }

        protected Fp twiceJacobianModified(boolean z) {
            ECFieldElement eCFieldElement = this.x;
            ECFieldElement eCFieldElement2 = this.y;
            ECFieldElement eCFieldElement3 = this.zs[0];
            ECFieldElement jacobianModifiedW = getJacobianModifiedW();
            ECFieldElement eCFieldElementAdd = three(eCFieldElement.square()).add(jacobianModifiedW);
            ECFieldElement eCFieldElementTwo = two(eCFieldElement2);
            ECFieldElement eCFieldElementMultiply = eCFieldElementTwo.multiply(eCFieldElement2);
            ECFieldElement eCFieldElementTwo2 = two(eCFieldElement.multiply(eCFieldElementMultiply));
            ECFieldElement eCFieldElementSubtract = eCFieldElementAdd.square().subtract(two(eCFieldElementTwo2));
            ECFieldElement eCFieldElementTwo3 = two(eCFieldElementMultiply.square());
            ECFieldElement eCFieldElementSubtract2 = eCFieldElementAdd.multiply(eCFieldElementTwo2.subtract(eCFieldElementSubtract)).subtract(eCFieldElementTwo3);
            ECFieldElement eCFieldElementTwo4 = z ? two(eCFieldElementTwo3.multiply(jacobianModifiedW)) : null;
            if (!eCFieldElement3.isOne()) {
                eCFieldElementTwo = eCFieldElementTwo.multiply(eCFieldElement3);
            }
            return new Fp(getCurve(), eCFieldElementSubtract, eCFieldElementSubtract2, new ECFieldElement[]{eCFieldElementTwo, eCFieldElementTwo4}, this.withCompression);
        }
    }

    public static abstract class AbstractF2m extends ECPoint {
        protected AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        protected AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        protected boolean satisfiesCurveEquation() {
            ECFieldElement eCFieldElementMultiplyPlusProduct;
            ECFieldElement eCFieldElementSquarePlusProduct;
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.x;
            ECFieldElement a2 = curve.getA();
            ECFieldElement b = curve.getB();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem == 6) {
                ECFieldElement eCFieldElement2 = this.zs[0];
                boolean zIsOne = eCFieldElement2.isOne();
                if (eCFieldElement.isZero()) {
                    ECFieldElement eCFieldElementSquare = this.y.square();
                    if (!zIsOne) {
                        b = b.multiply(eCFieldElement2.square());
                    }
                    return eCFieldElementSquare.equals(b);
                }
                ECFieldElement eCFieldElement3 = this.y;
                ECFieldElement eCFieldElementSquare2 = eCFieldElement.square();
                if (zIsOne) {
                    eCFieldElementMultiplyPlusProduct = eCFieldElement3.square().add(eCFieldElement3).add(a2);
                    eCFieldElementSquarePlusProduct = eCFieldElementSquare2.square().add(b);
                } else {
                    ECFieldElement eCFieldElementSquare3 = eCFieldElement2.square();
                    ECFieldElement eCFieldElementSquare4 = eCFieldElementSquare3.square();
                    eCFieldElementMultiplyPlusProduct = eCFieldElement3.add(eCFieldElement2).multiplyPlusProduct(eCFieldElement3, a2, eCFieldElementSquare3);
                    eCFieldElementSquarePlusProduct = eCFieldElementSquare2.squarePlusProduct(b, eCFieldElementSquare4);
                }
                return eCFieldElementMultiplyPlusProduct.multiply(eCFieldElementSquare2).equals(eCFieldElementSquarePlusProduct);
            }
            ECFieldElement eCFieldElement4 = this.y;
            ECFieldElement eCFieldElementMultiply = eCFieldElement4.add(eCFieldElement).multiply(eCFieldElement4);
            if (coordinateSystem != 0) {
                if (coordinateSystem == 1) {
                    ECFieldElement eCFieldElement5 = this.zs[0];
                    if (!eCFieldElement5.isOne()) {
                        ECFieldElement eCFieldElementMultiply2 = eCFieldElement5.multiply(eCFieldElement5.square());
                        eCFieldElementMultiply = eCFieldElementMultiply.multiply(eCFieldElement5);
                        a2 = a2.multiply(eCFieldElement5);
                        b = b.multiply(eCFieldElementMultiply2);
                    }
                } else {
                    throw new IllegalStateException("unsupported coordinate system");
                }
            }
            return eCFieldElementMultiply.equals(eCFieldElement.add(a2).multiply(eCFieldElement.square()).add(b));
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint scaleX(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 5) {
                ECFieldElement rawXCoord = getRawXCoord();
                return getCurve().createRawPoint(rawXCoord, getRawYCoord().add(rawXCoord).divide(eCFieldElement).add(rawXCoord.multiply(eCFieldElement)), getRawZCoords(), this.withCompression);
            }
            if (curveCoordinateSystem == 6) {
                ECFieldElement rawXCoord2 = getRawXCoord();
                ECFieldElement rawYCoord = getRawYCoord();
                ECFieldElement eCFieldElement2 = getRawZCoords()[0];
                ECFieldElement eCFieldElementMultiply = rawXCoord2.multiply(eCFieldElement.square());
                return getCurve().createRawPoint(eCFieldElementMultiply, rawYCoord.add(rawXCoord2).add(eCFieldElementMultiply), new ECFieldElement[]{eCFieldElement2.multiply(eCFieldElement)}, this.withCompression);
            }
            return super.scaleX(eCFieldElement);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint scaleY(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 5 || curveCoordinateSystem == 6) {
                ECFieldElement rawXCoord = getRawXCoord();
                return getCurve().createRawPoint(rawXCoord, getRawYCoord().add(rawXCoord).multiply(eCFieldElement).add(rawXCoord), getRawZCoords(), this.withCompression);
            }
            return super.scaleY(eCFieldElement);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint subtract(ECPoint eCPoint) {
            return eCPoint.isInfinity() ? this : add(eCPoint.negate());
        }

        public AbstractF2m tau() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.x;
            if (coordinateSystem != 0) {
                if (coordinateSystem != 1) {
                    if (coordinateSystem != 5) {
                        if (coordinateSystem != 6) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                    }
                }
                return (AbstractF2m) curve.createRawPoint(eCFieldElement.square(), this.y.square(), new ECFieldElement[]{this.zs[0].square()}, this.withCompression);
            }
            return (AbstractF2m) curve.createRawPoint(eCFieldElement.square(), this.y.square(), this.withCompression);
        }

        public AbstractF2m tauPow(int i) {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.x;
            if (coordinateSystem != 0) {
                if (coordinateSystem != 1) {
                    if (coordinateSystem != 5) {
                        if (coordinateSystem != 6) {
                            throw new IllegalStateException("unsupported coordinate system");
                        }
                    }
                }
                return (AbstractF2m) curve.createRawPoint(eCFieldElement.squarePow(i), this.y.squarePow(i), new ECFieldElement[]{this.zs[0].squarePow(i)}, this.withCompression);
            }
            return (AbstractF2m) curve.createRawPoint(eCFieldElement.squarePow(i), this.y.squarePow(i), this.withCompression);
        }
    }

    public static class F2m extends AbstractF2m {
        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            this(eCCurve, eCFieldElement, eCFieldElement2, false);
        }

        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
            if ((eCFieldElement == null) != (eCFieldElement2 == null)) {
                throw new IllegalArgumentException("Exactly one of the field elements is null");
            }
            if (eCFieldElement != null) {
                ECFieldElement.F2m.checkFieldElements(this.x, this.y);
                if (eCCurve != null) {
                    ECFieldElement.F2m.checkFieldElements(this.x, this.curve.getA());
                }
            }
            this.withCompression = z;
        }

        F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
            this.withCompression = z;
        }

        @Override // org.spongycastle.math.ec.ECPoint
        protected ECPoint detach() {
            return new F2m(null, getAffineXCoord(), getAffineYCoord());
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECFieldElement getYCoord() {
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 5 || curveCoordinateSystem == 6) {
                ECFieldElement eCFieldElement = this.x;
                ECFieldElement eCFieldElement2 = this.y;
                if (isInfinity() || eCFieldElement.isZero()) {
                    return eCFieldElement2;
                }
                ECFieldElement eCFieldElementMultiply = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement);
                if (6 != curveCoordinateSystem) {
                    return eCFieldElementMultiply;
                }
                ECFieldElement eCFieldElement3 = this.zs[0];
                return !eCFieldElement3.isOne() ? eCFieldElementMultiply.divide(eCFieldElement3) : eCFieldElementMultiply;
            }
            return this.y;
        }

        @Override // org.spongycastle.math.ec.ECPoint
        protected boolean getCompressionYTilde() {
            ECFieldElement rawXCoord = getRawXCoord();
            if (rawXCoord.isZero()) {
                return false;
            }
            ECFieldElement rawYCoord = getRawYCoord();
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 5 || curveCoordinateSystem == 6) {
                return rawYCoord.testBitZero() != rawXCoord.testBitZero();
            }
            return rawYCoord.divide(rawXCoord).testBitZero();
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint add(ECPoint eCPoint) {
            ECFieldElement eCFieldElementMultiply;
            ECFieldElement eCFieldElementMultiply2;
            ECFieldElement eCFieldElementMultiply3;
            ECFieldElement eCFieldElementAdd;
            ECFieldElement eCFieldElementFromBigInteger;
            ECFieldElement eCFieldElementAdd2;
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElementMultiply4 = this.x;
            ECFieldElement eCFieldElement = eCPoint.x;
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElement2 = this.y;
                ECFieldElement eCFieldElement3 = eCPoint.y;
                ECFieldElement eCFieldElementAdd3 = eCFieldElementMultiply4.add(eCFieldElement);
                ECFieldElement eCFieldElementAdd4 = eCFieldElement2.add(eCFieldElement3);
                if (eCFieldElementAdd3.isZero()) {
                    if (eCFieldElementAdd4.isZero()) {
                        return twice();
                    }
                    return curve.getInfinity();
                }
                ECFieldElement eCFieldElementDivide = eCFieldElementAdd4.divide(eCFieldElementAdd3);
                ECFieldElement eCFieldElementAdd5 = eCFieldElementDivide.square().add(eCFieldElementDivide).add(eCFieldElementAdd3).add(curve.getA());
                return new F2m(curve, eCFieldElementAdd5, eCFieldElementDivide.multiply(eCFieldElementMultiply4.add(eCFieldElementAdd5)).add(eCFieldElementAdd5).add(eCFieldElement2), this.withCompression);
            }
            if (coordinateSystem == 1) {
                ECFieldElement eCFieldElement4 = this.y;
                ECFieldElement eCFieldElementMultiply5 = this.zs[0];
                ECFieldElement eCFieldElement5 = eCPoint.y;
                ECFieldElement eCFieldElement6 = eCPoint.zs[0];
                boolean zIsOne = eCFieldElement6.isOne();
                ECFieldElement eCFieldElementAdd6 = eCFieldElementMultiply5.multiply(eCFieldElement5).add(zIsOne ? eCFieldElement4 : eCFieldElement4.multiply(eCFieldElement6));
                ECFieldElement eCFieldElementAdd7 = eCFieldElementMultiply5.multiply(eCFieldElement).add(zIsOne ? eCFieldElementMultiply4 : eCFieldElementMultiply4.multiply(eCFieldElement6));
                if (eCFieldElementAdd7.isZero()) {
                    if (eCFieldElementAdd6.isZero()) {
                        return twice();
                    }
                    return curve.getInfinity();
                }
                ECFieldElement eCFieldElementSquare = eCFieldElementAdd7.square();
                ECFieldElement eCFieldElementMultiply6 = eCFieldElementSquare.multiply(eCFieldElementAdd7);
                if (!zIsOne) {
                    eCFieldElementMultiply5 = eCFieldElementMultiply5.multiply(eCFieldElement6);
                }
                ECFieldElement eCFieldElementAdd8 = eCFieldElementAdd6.add(eCFieldElementAdd7);
                ECFieldElement eCFieldElementAdd9 = eCFieldElementAdd8.multiplyPlusProduct(eCFieldElementAdd6, eCFieldElementSquare, curve.getA()).multiply(eCFieldElementMultiply5).add(eCFieldElementMultiply6);
                ECFieldElement eCFieldElementMultiply7 = eCFieldElementAdd7.multiply(eCFieldElementAdd9);
                if (!zIsOne) {
                    eCFieldElementSquare = eCFieldElementSquare.multiply(eCFieldElement6);
                }
                return new F2m(curve, eCFieldElementMultiply7, eCFieldElementAdd6.multiplyPlusProduct(eCFieldElementMultiply4, eCFieldElementAdd7, eCFieldElement4).multiplyPlusProduct(eCFieldElementSquare, eCFieldElementAdd8, eCFieldElementAdd9), new ECFieldElement[]{eCFieldElementMultiply6.multiply(eCFieldElementMultiply5)}, this.withCompression);
            }
            if (coordinateSystem == 6) {
                if (eCFieldElementMultiply4.isZero()) {
                    if (eCFieldElement.isZero()) {
                        return curve.getInfinity();
                    }
                    return eCPoint.add(this);
                }
                ECFieldElement eCFieldElement7 = this.y;
                ECFieldElement eCFieldElement8 = this.zs[0];
                ECFieldElement eCFieldElement9 = eCPoint.y;
                ECFieldElement eCFieldElement10 = eCPoint.zs[0];
                boolean zIsOne2 = eCFieldElement8.isOne();
                if (zIsOne2) {
                    eCFieldElementMultiply = eCFieldElement;
                    eCFieldElementMultiply2 = eCFieldElement9;
                } else {
                    eCFieldElementMultiply = eCFieldElement.multiply(eCFieldElement8);
                    eCFieldElementMultiply2 = eCFieldElement9.multiply(eCFieldElement8);
                }
                boolean zIsOne3 = eCFieldElement10.isOne();
                if (zIsOne3) {
                    eCFieldElementMultiply3 = eCFieldElement7;
                } else {
                    eCFieldElementMultiply4 = eCFieldElementMultiply4.multiply(eCFieldElement10);
                    eCFieldElementMultiply3 = eCFieldElement7.multiply(eCFieldElement10);
                }
                ECFieldElement eCFieldElementAdd10 = eCFieldElementMultiply3.add(eCFieldElementMultiply2);
                ECFieldElement eCFieldElementAdd11 = eCFieldElementMultiply4.add(eCFieldElementMultiply);
                if (eCFieldElementAdd11.isZero()) {
                    if (eCFieldElementAdd10.isZero()) {
                        return twice();
                    }
                    return curve.getInfinity();
                }
                if (eCFieldElement.isZero()) {
                    ECPoint eCPointNormalize = normalize();
                    ECFieldElement xCoord = eCPointNormalize.getXCoord();
                    ECFieldElement yCoord = eCPointNormalize.getYCoord();
                    ECFieldElement eCFieldElementDivide2 = yCoord.add(eCFieldElement9).divide(xCoord);
                    eCFieldElementAdd = eCFieldElementDivide2.square().add(eCFieldElementDivide2).add(xCoord).add(curve.getA());
                    if (eCFieldElementAdd.isZero()) {
                        return new F2m(curve, eCFieldElementAdd, curve.getB().sqrt(), this.withCompression);
                    }
                    eCFieldElementAdd2 = eCFieldElementDivide2.multiply(xCoord.add(eCFieldElementAdd)).add(eCFieldElementAdd).add(yCoord).divide(eCFieldElementAdd).add(eCFieldElementAdd);
                    eCFieldElementFromBigInteger = curve.fromBigInteger(ECConstants.ONE);
                } else {
                    ECFieldElement eCFieldElementSquare2 = eCFieldElementAdd11.square();
                    ECFieldElement eCFieldElementMultiply8 = eCFieldElementAdd10.multiply(eCFieldElementMultiply4);
                    ECFieldElement eCFieldElementMultiply9 = eCFieldElementAdd10.multiply(eCFieldElementMultiply);
                    ECFieldElement eCFieldElementMultiply10 = eCFieldElementMultiply8.multiply(eCFieldElementMultiply9);
                    if (eCFieldElementMultiply10.isZero()) {
                        return new F2m(curve, eCFieldElementMultiply10, curve.getB().sqrt(), this.withCompression);
                    }
                    ECFieldElement eCFieldElementMultiply11 = eCFieldElementAdd10.multiply(eCFieldElementSquare2);
                    ECFieldElement eCFieldElementMultiply12 = !zIsOne3 ? eCFieldElementMultiply11.multiply(eCFieldElement10) : eCFieldElementMultiply11;
                    ECFieldElement eCFieldElementSquarePlusProduct = eCFieldElementMultiply9.add(eCFieldElementSquare2).squarePlusProduct(eCFieldElementMultiply12, eCFieldElement7.add(eCFieldElement8));
                    if (!zIsOne2) {
                        eCFieldElementMultiply12 = eCFieldElementMultiply12.multiply(eCFieldElement8);
                    }
                    eCFieldElementAdd = eCFieldElementMultiply10;
                    eCFieldElementFromBigInteger = eCFieldElementMultiply12;
                    eCFieldElementAdd2 = eCFieldElementSquarePlusProduct;
                }
                return new F2m(curve, eCFieldElementAdd, eCFieldElementAdd2, new ECFieldElement[]{eCFieldElementFromBigInteger}, this.withCompression);
            }
            throw new IllegalStateException("unsupported coordinate system");
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint twice() {
            ECFieldElement eCFieldElementAdd;
            ECFieldElement eCFieldElementSquarePlusProduct;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElementMultiply = this.x;
            if (eCFieldElementMultiply.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem == 0) {
                ECFieldElement eCFieldElementAdd2 = this.y.divide(eCFieldElementMultiply).add(eCFieldElementMultiply);
                ECFieldElement eCFieldElementAdd3 = eCFieldElementAdd2.square().add(eCFieldElementAdd2).add(curve.getA());
                return new F2m(curve, eCFieldElementAdd3, eCFieldElementMultiply.squarePlusProduct(eCFieldElementAdd3, eCFieldElementAdd2.addOne()), this.withCompression);
            }
            if (coordinateSystem == 1) {
                ECFieldElement eCFieldElementMultiply2 = this.y;
                ECFieldElement eCFieldElement = this.zs[0];
                boolean zIsOne = eCFieldElement.isOne();
                ECFieldElement eCFieldElementMultiply3 = zIsOne ? eCFieldElementMultiply : eCFieldElementMultiply.multiply(eCFieldElement);
                if (!zIsOne) {
                    eCFieldElementMultiply2 = eCFieldElementMultiply2.multiply(eCFieldElement);
                }
                ECFieldElement eCFieldElementSquare = eCFieldElementMultiply.square();
                ECFieldElement eCFieldElementAdd4 = eCFieldElementSquare.add(eCFieldElementMultiply2);
                ECFieldElement eCFieldElementSquare2 = eCFieldElementMultiply3.square();
                ECFieldElement eCFieldElementAdd5 = eCFieldElementAdd4.add(eCFieldElementMultiply3);
                ECFieldElement eCFieldElementMultiplyPlusProduct = eCFieldElementAdd5.multiplyPlusProduct(eCFieldElementAdd4, eCFieldElementSquare2, curve.getA());
                return new F2m(curve, eCFieldElementMultiply3.multiply(eCFieldElementMultiplyPlusProduct), eCFieldElementSquare.square().multiplyPlusProduct(eCFieldElementMultiply3, eCFieldElementMultiplyPlusProduct, eCFieldElementAdd5), new ECFieldElement[]{eCFieldElementMultiply3.multiply(eCFieldElementSquare2)}, this.withCompression);
            }
            if (coordinateSystem == 6) {
                ECFieldElement eCFieldElement2 = this.y;
                ECFieldElement eCFieldElement3 = this.zs[0];
                boolean zIsOne2 = eCFieldElement3.isOne();
                ECFieldElement eCFieldElementMultiply4 = zIsOne2 ? eCFieldElement2 : eCFieldElement2.multiply(eCFieldElement3);
                ECFieldElement eCFieldElementSquare3 = zIsOne2 ? eCFieldElement3 : eCFieldElement3.square();
                ECFieldElement a2 = curve.getA();
                ECFieldElement eCFieldElementMultiply5 = zIsOne2 ? a2 : a2.multiply(eCFieldElementSquare3);
                ECFieldElement eCFieldElementAdd6 = eCFieldElement2.square().add(eCFieldElementMultiply4).add(eCFieldElementMultiply5);
                if (eCFieldElementAdd6.isZero()) {
                    return new F2m(curve, eCFieldElementAdd6, curve.getB().sqrt(), this.withCompression);
                }
                ECFieldElement eCFieldElementSquare4 = eCFieldElementAdd6.square();
                ECFieldElement eCFieldElementMultiply6 = zIsOne2 ? eCFieldElementAdd6 : eCFieldElementAdd6.multiply(eCFieldElementSquare3);
                ECFieldElement b = curve.getB();
                if (b.bitLength() < (curve.getFieldSize() >> 1)) {
                    ECFieldElement eCFieldElementSquare5 = eCFieldElement2.add(eCFieldElementMultiply).square();
                    if (b.isOne()) {
                        eCFieldElementSquarePlusProduct = eCFieldElementMultiply5.add(eCFieldElementSquare3).square();
                    } else {
                        eCFieldElementSquarePlusProduct = eCFieldElementMultiply5.squarePlusProduct(b, eCFieldElementSquare3.square());
                    }
                    eCFieldElementAdd = eCFieldElementSquare5.add(eCFieldElementAdd6).add(eCFieldElementSquare3).multiply(eCFieldElementSquare5).add(eCFieldElementSquarePlusProduct).add(eCFieldElementSquare4);
                    if (a2.isZero()) {
                        eCFieldElementAdd = eCFieldElementAdd.add(eCFieldElementMultiply6);
                    } else if (!a2.isOne()) {
                        eCFieldElementAdd = eCFieldElementAdd.add(a2.addOne().multiply(eCFieldElementMultiply6));
                    }
                } else {
                    if (!zIsOne2) {
                        eCFieldElementMultiply = eCFieldElementMultiply.multiply(eCFieldElement3);
                    }
                    eCFieldElementAdd = eCFieldElementMultiply.squarePlusProduct(eCFieldElementAdd6, eCFieldElementMultiply4).add(eCFieldElementSquare4).add(eCFieldElementMultiply6);
                }
                return new F2m(curve, eCFieldElementSquare4, eCFieldElementAdd, new ECFieldElement[]{eCFieldElementMultiply6}, this.withCompression);
            }
            throw new IllegalStateException("unsupported coordinate system");
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint twicePlus(ECPoint eCPoint) {
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return twice();
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.x;
            if (eCFieldElement.isZero()) {
                return eCPoint;
            }
            if (curve.getCoordinateSystem() == 6) {
                ECFieldElement eCFieldElement2 = eCPoint.x;
                ECFieldElement eCFieldElement3 = eCPoint.zs[0];
                if (eCFieldElement2.isZero() || !eCFieldElement3.isOne()) {
                    return twice().add(eCPoint);
                }
                ECFieldElement eCFieldElement4 = this.y;
                ECFieldElement eCFieldElement5 = this.zs[0];
                ECFieldElement eCFieldElement6 = eCPoint.y;
                ECFieldElement eCFieldElementSquare = eCFieldElement.square();
                ECFieldElement eCFieldElementSquare2 = eCFieldElement4.square();
                ECFieldElement eCFieldElementSquare3 = eCFieldElement5.square();
                ECFieldElement eCFieldElementAdd = curve.getA().multiply(eCFieldElementSquare3).add(eCFieldElementSquare2).add(eCFieldElement4.multiply(eCFieldElement5));
                ECFieldElement eCFieldElementAddOne = eCFieldElement6.addOne();
                ECFieldElement eCFieldElementMultiplyPlusProduct = curve.getA().add(eCFieldElementAddOne).multiply(eCFieldElementSquare3).add(eCFieldElementSquare2).multiplyPlusProduct(eCFieldElementAdd, eCFieldElementSquare, eCFieldElementSquare3);
                ECFieldElement eCFieldElementMultiply = eCFieldElement2.multiply(eCFieldElementSquare3);
                ECFieldElement eCFieldElementSquare4 = eCFieldElementMultiply.add(eCFieldElementAdd).square();
                if (eCFieldElementSquare4.isZero()) {
                    if (eCFieldElementMultiplyPlusProduct.isZero()) {
                        return eCPoint.twice();
                    }
                    return curve.getInfinity();
                }
                if (eCFieldElementMultiplyPlusProduct.isZero()) {
                    return new F2m(curve, eCFieldElementMultiplyPlusProduct, curve.getB().sqrt(), this.withCompression);
                }
                ECFieldElement eCFieldElementMultiply2 = eCFieldElementMultiplyPlusProduct.square().multiply(eCFieldElementMultiply);
                ECFieldElement eCFieldElementMultiply3 = eCFieldElementMultiplyPlusProduct.multiply(eCFieldElementSquare4).multiply(eCFieldElementSquare3);
                return new F2m(curve, eCFieldElementMultiply2, eCFieldElementMultiplyPlusProduct.add(eCFieldElementSquare4).square().multiplyPlusProduct(eCFieldElementAdd, eCFieldElementAddOne, eCFieldElementMultiply3), new ECFieldElement[]{eCFieldElementMultiply3}, this.withCompression);
            }
            return twice().add(eCPoint);
        }

        @Override // org.spongycastle.math.ec.ECPoint
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.x;
            if (eCFieldElement.isZero()) {
                return this;
            }
            int curveCoordinateSystem = getCurveCoordinateSystem();
            if (curveCoordinateSystem == 0) {
                return new F2m(this.curve, eCFieldElement, this.y.add(eCFieldElement), this.withCompression);
            }
            if (curveCoordinateSystem == 1) {
                return new F2m(this.curve, eCFieldElement, this.y.add(eCFieldElement), new ECFieldElement[]{this.zs[0]}, this.withCompression);
            }
            if (curveCoordinateSystem == 5) {
                return new F2m(this.curve, eCFieldElement, this.y.addOne(), this.withCompression);
            }
            if (curveCoordinateSystem == 6) {
                ECFieldElement eCFieldElement2 = this.y;
                ECFieldElement eCFieldElement3 = this.zs[0];
                return new F2m(this.curve, eCFieldElement, eCFieldElement2.add(eCFieldElement3), new ECFieldElement[]{eCFieldElement3}, this.withCompression);
            }
            throw new IllegalStateException("unsupported coordinate system");
        }
    }
}
