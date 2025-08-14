package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public class GOST3410PrivateKeyParameters extends GOST3410KeyParameters {
    private BigInteger x;

    public BigInteger getX() {
        return this.x;
    }

    public GOST3410PrivateKeyParameters(BigInteger bigInteger, GOST3410Parameters gOST3410Parameters) {
        super(true, gOST3410Parameters);
        this.x = bigInteger;
    }
}
