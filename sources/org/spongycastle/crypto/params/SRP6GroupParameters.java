package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public class SRP6GroupParameters {
    private BigInteger N;
    private BigInteger g;

    public BigInteger getG() {
        return this.g;
    }

    public BigInteger getN() {
        return this.N;
    }

    public SRP6GroupParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this.N = bigInteger;
        this.g = bigInteger2;
    }
}
