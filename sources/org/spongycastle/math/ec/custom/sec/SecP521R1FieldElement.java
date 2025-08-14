package org.spongycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.spongycastle.math.ec.ECFieldElement;
import org.spongycastle.math.raw.Mod;
import org.spongycastle.math.raw.Nat;
import org.spongycastle.util.Arrays;

/* loaded from: classes7.dex */
public class SecP521R1FieldElement extends ECFieldElement {
    public static final BigInteger Q = SecP521R1Curve.q;
    protected int[] x;

    @Override // org.spongycastle.math.ec.ECFieldElement
    public String getFieldName() {
        return "SecP521R1Field";
    }

    public SecP521R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP521R1FieldElement");
        }
        this.x = SecP521R1Field.fromBigInteger(bigInteger);
    }

    public SecP521R1FieldElement() {
        this.x = Nat.create(17);
    }

    protected SecP521R1FieldElement(int[] iArr) {
        this.x = iArr;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isZero() {
        return Nat.isZero(17, this.x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean isOne() {
        return Nat.isOne(17, this.x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public boolean testBitZero() {
        return Nat.getBit(this.x, 0) == 1;
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat.toBigInteger(17, this.x);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public int getFieldSize() {
        return Q.bitLength();
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(17);
        SecP521R1Field.add(this.x, ((SecP521R1FieldElement) eCFieldElement).x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] iArrCreate = Nat.create(17);
        SecP521R1Field.addOne(this.x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(17);
        SecP521R1Field.subtract(this.x, ((SecP521R1FieldElement) eCFieldElement).x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(17);
        SecP521R1Field.multiply(this.x, ((SecP521R1FieldElement) eCFieldElement).x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] iArrCreate = Nat.create(17);
        Mod.invert(SecP521R1Field.P, ((SecP521R1FieldElement) eCFieldElement).x, iArrCreate);
        SecP521R1Field.multiply(iArrCreate, this.x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement negate() {
        int[] iArrCreate = Nat.create(17);
        SecP521R1Field.negate(this.x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement square() {
        int[] iArrCreate = Nat.create(17);
        SecP521R1Field.square(this.x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement invert() {
        int[] iArrCreate = Nat.create(17);
        Mod.invert(SecP521R1Field.P, this.x, iArrCreate);
        return new SecP521R1FieldElement(iArrCreate);
    }

    @Override // org.spongycastle.math.ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] iArr = this.x;
        if (Nat.isZero(17, iArr) || Nat.isOne(17, iArr)) {
            return this;
        }
        int[] iArrCreate = Nat.create(17);
        int[] iArrCreate2 = Nat.create(17);
        SecP521R1Field.squareN(iArr, 519, iArrCreate);
        SecP521R1Field.square(iArrCreate, iArrCreate2);
        if (Nat.eq(17, iArr, iArrCreate2)) {
            return new SecP521R1FieldElement(iArrCreate);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP521R1FieldElement) {
            return Nat.eq(17, this.x, ((SecP521R1FieldElement) obj).x);
        }
        return false;
    }

    public int hashCode() {
        return Q.hashCode() ^ Arrays.hashCode(this.x, 0, 17);
    }
}
