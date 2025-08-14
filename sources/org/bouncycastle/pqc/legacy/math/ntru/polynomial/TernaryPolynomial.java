package org.bouncycastle.pqc.legacy.math.ntru.polynomial;

/* loaded from: classes4.dex */
public interface TernaryPolynomial extends Polynomial {
    void clear();

    int[] getNegOnes();

    int[] getOnes();

    @Override // org.bouncycastle.pqc.legacy.math.ntru.polynomial.Polynomial
    IntegerPolynomial mult(IntegerPolynomial integerPolynomial);

    int size();
}
