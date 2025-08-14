package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public class DSAPrivateKeyParameters extends DSAKeyParameters {
    private BigInteger x;

    public BigInteger getX() {
        return this.x;
    }

    public DSAPrivateKeyParameters(BigInteger bigInteger, DSAParameters dSAParameters) {
        super(true, dSAParameters);
        this.x = bigInteger;
    }
}
