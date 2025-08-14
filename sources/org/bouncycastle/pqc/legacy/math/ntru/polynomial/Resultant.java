package org.bouncycastle.pqc.legacy.math.ntru.polynomial;

import java.math.BigInteger;

/* loaded from: classes4.dex */
public class Resultant {
    public BigInteger res;
    public BigIntPolynomial rho;

    Resultant(BigIntPolynomial bigIntPolynomial, BigInteger bigInteger) {
        this.rho = bigIntPolynomial;
        this.res = bigInteger;
    }
}
