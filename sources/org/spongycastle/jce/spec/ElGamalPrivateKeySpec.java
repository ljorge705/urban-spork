package org.spongycastle.jce.spec;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public class ElGamalPrivateKeySpec extends ElGamalKeySpec {
    private BigInteger x;

    public BigInteger getX() {
        return this.x;
    }

    public ElGamalPrivateKeySpec(BigInteger bigInteger, ElGamalParameterSpec elGamalParameterSpec) {
        super(elGamalParameterSpec);
        this.x = bigInteger;
    }
}
