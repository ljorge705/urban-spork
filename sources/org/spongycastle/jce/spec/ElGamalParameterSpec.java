package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

/* loaded from: classes7.dex */
public class ElGamalParameterSpec implements AlgorithmParameterSpec {
    private BigInteger g;
    private BigInteger p;

    public BigInteger getG() {
        return this.g;
    }

    public BigInteger getP() {
        return this.p;
    }

    public ElGamalParameterSpec(BigInteger bigInteger, BigInteger bigInteger2) {
        this.p = bigInteger;
        this.g = bigInteger2;
    }
}
