package org.spongycastle.math.field;

import java.math.BigInteger;
import org.spongycastle.util.Integers;

/* loaded from: classes7.dex */
class GenericPolynomialExtensionField implements PolynomialExtensionField {
    protected final Polynomial minimalPolynomial;
    protected final FiniteField subfield;

    @Override // org.spongycastle.math.field.PolynomialExtensionField
    public Polynomial getMinimalPolynomial() {
        return this.minimalPolynomial;
    }

    @Override // org.spongycastle.math.field.ExtensionField
    public FiniteField getSubfield() {
        return this.subfield;
    }

    GenericPolynomialExtensionField(FiniteField finiteField, Polynomial polynomial) {
        this.subfield = finiteField;
        this.minimalPolynomial = polynomial;
    }

    @Override // org.spongycastle.math.field.FiniteField
    public BigInteger getCharacteristic() {
        return this.subfield.getCharacteristic();
    }

    @Override // org.spongycastle.math.field.FiniteField
    public int getDimension() {
        return this.subfield.getDimension() * this.minimalPolynomial.getDegree();
    }

    @Override // org.spongycastle.math.field.ExtensionField
    public int getDegree() {
        return this.minimalPolynomial.getDegree();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GenericPolynomialExtensionField)) {
            return false;
        }
        GenericPolynomialExtensionField genericPolynomialExtensionField = (GenericPolynomialExtensionField) obj;
        return this.subfield.equals(genericPolynomialExtensionField.subfield) && this.minimalPolynomial.equals(genericPolynomialExtensionField.minimalPolynomial);
    }

    public int hashCode() {
        return this.subfield.hashCode() ^ Integers.rotateLeft(this.minimalPolynomial.hashCode(), 16);
    }
}
