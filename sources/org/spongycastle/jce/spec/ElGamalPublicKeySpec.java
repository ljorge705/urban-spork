package org.spongycastle.jce.spec;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public class ElGamalPublicKeySpec extends ElGamalKeySpec {
    private BigInteger y;

    public BigInteger getY() {
        return this.y;
    }

    public ElGamalPublicKeySpec(BigInteger bigInteger, ElGamalParameterSpec elGamalParameterSpec) {
        super(elGamalParameterSpec);
        this.y = bigInteger;
    }
}
