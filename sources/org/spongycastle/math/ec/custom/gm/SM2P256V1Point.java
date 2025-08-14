package org.spongycastle.math.ec.custom.gm;

import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.ec.ECPoint;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.math.raw.Nat256;

/* loaded from: classes7.dex */
public class SM2P256V1Point extends ECPoint.AbstractFp {
    public SM2P256V1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, false);
    }

    public SM2P256V1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
        if ((eCFieldElement == null) != (eCFieldElement2 == null)) {
            throw new IllegalArgumentException("Exactly one of the field elements is null");
        }
        this.withCompression = z;
    }

    SM2P256V1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        this.withCompression = z;
    }

    @Override // org.spongycastle.math.ec.ECPoint
    protected ECPoint detach() {
        return new SM2P256V1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint add(ECPoint eCPoint) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        if (this == eCPoint) {
            return twice();
        }
        ECCurve curve = getCurve();
        SM2P256V1FieldElement sM2P256V1FieldElement = (SM2P256V1FieldElement) this.x;
        SM2P256V1FieldElement sM2P256V1FieldElement2 = (SM2P256V1FieldElement) this.y;
        SM2P256V1FieldElement sM2P256V1FieldElement3 = (SM2P256V1FieldElement) eCPoint.getXCoord();
        SM2P256V1FieldElement sM2P256V1FieldElement4 = (SM2P256V1FieldElement) eCPoint.getYCoord();
        SM2P256V1FieldElement sM2P256V1FieldElement5 = (SM2P256V1FieldElement) this.zs[0];
        SM2P256V1FieldElement sM2P256V1FieldElement6 = (SM2P256V1FieldElement) eCPoint.getZCoord(0);
        int[] iArrCreateExt = Nat256.createExt();
        int[] iArrCreate = Nat256.create();
        int[] iArrCreate2 = Nat256.create();
        int[] iArrCreate3 = Nat256.create();
        boolean zIsOne = sM2P256V1FieldElement5.isOne();
        if (zIsOne) {
            iArr = sM2P256V1FieldElement3.x;
            iArr2 = sM2P256V1FieldElement4.x;
        } else {
            SM2P256V1Field.square(sM2P256V1FieldElement5.x, iArrCreate2);
            SM2P256V1Field.multiply(iArrCreate2, sM2P256V1FieldElement3.x, iArrCreate);
            SM2P256V1Field.multiply(iArrCreate2, sM2P256V1FieldElement5.x, iArrCreate2);
            SM2P256V1Field.multiply(iArrCreate2, sM2P256V1FieldElement4.x, iArrCreate2);
            iArr = iArrCreate;
            iArr2 = iArrCreate2;
        }
        boolean zIsOne2 = sM2P256V1FieldElement6.isOne();
        if (zIsOne2) {
            iArr3 = sM2P256V1FieldElement.x;
            iArr4 = sM2P256V1FieldElement2.x;
        } else {
            SM2P256V1Field.square(sM2P256V1FieldElement6.x, iArrCreate3);
            SM2P256V1Field.multiply(iArrCreate3, sM2P256V1FieldElement.x, iArrCreateExt);
            SM2P256V1Field.multiply(iArrCreate3, sM2P256V1FieldElement6.x, iArrCreate3);
            SM2P256V1Field.multiply(iArrCreate3, sM2P256V1FieldElement2.x, iArrCreate3);
            iArr3 = iArrCreateExt;
            iArr4 = iArrCreate3;
        }
        int[] iArrCreate4 = Nat256.create();
        SM2P256V1Field.subtract(iArr3, iArr, iArrCreate4);
        SM2P256V1Field.subtract(iArr4, iArr2, iArrCreate);
        if (Nat256.isZero(iArrCreate4)) {
            if (Nat256.isZero(iArrCreate)) {
                return twice();
            }
            return curve.getInfinity();
        }
        SM2P256V1Field.square(iArrCreate4, iArrCreate2);
        int[] iArrCreate5 = Nat256.create();
        SM2P256V1Field.multiply(iArrCreate2, iArrCreate4, iArrCreate5);
        SM2P256V1Field.multiply(iArrCreate2, iArr3, iArrCreate2);
        SM2P256V1Field.negate(iArrCreate5, iArrCreate5);
        Nat256.mul(iArr4, iArrCreate5, iArrCreateExt);
        SM2P256V1Field.reduce32(Nat256.addBothTo(iArrCreate2, iArrCreate2, iArrCreate5), iArrCreate5);
        SM2P256V1FieldElement sM2P256V1FieldElement7 = new SM2P256V1FieldElement(iArrCreate3);
        SM2P256V1Field.square(iArrCreate, sM2P256V1FieldElement7.x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement7.x, iArrCreate5, sM2P256V1FieldElement7.x);
        SM2P256V1FieldElement sM2P256V1FieldElement8 = new SM2P256V1FieldElement(iArrCreate5);
        SM2P256V1Field.subtract(iArrCreate2, sM2P256V1FieldElement7.x, sM2P256V1FieldElement8.x);
        SM2P256V1Field.multiplyAddToExt(sM2P256V1FieldElement8.x, iArrCreate, iArrCreateExt);
        SM2P256V1Field.reduce(iArrCreateExt, sM2P256V1FieldElement8.x);
        SM2P256V1FieldElement sM2P256V1FieldElement9 = new SM2P256V1FieldElement(iArrCreate4);
        if (!zIsOne) {
            SM2P256V1Field.multiply(sM2P256V1FieldElement9.x, sM2P256V1FieldElement5.x, sM2P256V1FieldElement9.x);
        }
        if (!zIsOne2) {
            SM2P256V1Field.multiply(sM2P256V1FieldElement9.x, sM2P256V1FieldElement6.x, sM2P256V1FieldElement9.x);
        }
        return new SM2P256V1Point(curve, sM2P256V1FieldElement7, sM2P256V1FieldElement8, new ECFieldElement[]{sM2P256V1FieldElement9}, this.withCompression);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SM2P256V1FieldElement sM2P256V1FieldElement = (SM2P256V1FieldElement) this.y;
        if (sM2P256V1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SM2P256V1FieldElement sM2P256V1FieldElement2 = (SM2P256V1FieldElement) this.x;
        SM2P256V1FieldElement sM2P256V1FieldElement3 = (SM2P256V1FieldElement) this.zs[0];
        int[] iArrCreate = Nat256.create();
        int[] iArrCreate2 = Nat256.create();
        int[] iArrCreate3 = Nat256.create();
        SM2P256V1Field.square(sM2P256V1FieldElement.x, iArrCreate3);
        int[] iArrCreate4 = Nat256.create();
        SM2P256V1Field.square(iArrCreate3, iArrCreate4);
        boolean zIsOne = sM2P256V1FieldElement3.isOne();
        int[] iArr = sM2P256V1FieldElement3.x;
        if (!zIsOne) {
            SM2P256V1Field.square(sM2P256V1FieldElement3.x, iArrCreate2);
            iArr = iArrCreate2;
        }
        SM2P256V1Field.subtract(sM2P256V1FieldElement2.x, iArr, iArrCreate);
        SM2P256V1Field.add(sM2P256V1FieldElement2.x, iArr, iArrCreate2);
        SM2P256V1Field.multiply(iArrCreate2, iArrCreate, iArrCreate2);
        SM2P256V1Field.reduce32(Nat256.addBothTo(iArrCreate2, iArrCreate2, iArrCreate2), iArrCreate2);
        SM2P256V1Field.multiply(iArrCreate3, sM2P256V1FieldElement2.x, iArrCreate3);
        SM2P256V1Field.reduce32(Nat.shiftUpBits(8, iArrCreate3, 2, 0), iArrCreate3);
        SM2P256V1Field.reduce32(Nat.shiftUpBits(8, iArrCreate4, 3, 0, iArrCreate), iArrCreate);
        SM2P256V1FieldElement sM2P256V1FieldElement4 = new SM2P256V1FieldElement(iArrCreate4);
        SM2P256V1Field.square(iArrCreate2, sM2P256V1FieldElement4.x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement4.x, iArrCreate3, sM2P256V1FieldElement4.x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement4.x, iArrCreate3, sM2P256V1FieldElement4.x);
        SM2P256V1FieldElement sM2P256V1FieldElement5 = new SM2P256V1FieldElement(iArrCreate3);
        SM2P256V1Field.subtract(iArrCreate3, sM2P256V1FieldElement4.x, sM2P256V1FieldElement5.x);
        SM2P256V1Field.multiply(sM2P256V1FieldElement5.x, iArrCreate2, sM2P256V1FieldElement5.x);
        SM2P256V1Field.subtract(sM2P256V1FieldElement5.x, iArrCreate, sM2P256V1FieldElement5.x);
        SM2P256V1FieldElement sM2P256V1FieldElement6 = new SM2P256V1FieldElement(iArrCreate2);
        SM2P256V1Field.twice(sM2P256V1FieldElement.x, sM2P256V1FieldElement6.x);
        if (!zIsOne) {
            SM2P256V1Field.multiply(sM2P256V1FieldElement6.x, sM2P256V1FieldElement3.x, sM2P256V1FieldElement6.x);
        }
        return new SM2P256V1Point(curve, sM2P256V1FieldElement4, sM2P256V1FieldElement5, new ECFieldElement[]{sM2P256V1FieldElement6}, this.withCompression);
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
        return this.y.isZero() ? eCPoint : twice().add(eCPoint);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint threeTimes() {
        return (isInfinity() || this.y.isZero()) ? this : twice().add(this);
    }

    @Override // org.spongycastle.math.ec.ECPoint
    public ECPoint negate() {
        return isInfinity() ? this : new SM2P256V1Point(this.curve, this.x, this.y.negate(), this.zs, this.withCompression);
    }
}
