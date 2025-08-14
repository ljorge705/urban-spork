package org.bouncycastle.pqc.legacy.math.ntru.polynomial;

/* loaded from: classes4.dex */
public interface Polynomial {
    BigIntPolynomial mult(BigIntPolynomial bigIntPolynomial);

    IntegerPolynomial mult(IntegerPolynomial integerPolynomial);

    IntegerPolynomial mult(IntegerPolynomial integerPolynomial, int i);

    IntegerPolynomial toIntegerPolynomial();
}
