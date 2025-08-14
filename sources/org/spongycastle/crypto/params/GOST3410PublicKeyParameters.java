package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public class GOST3410PublicKeyParameters extends GOST3410KeyParameters {
    private BigInteger y;

    public BigInteger getY() {
        return this.y;
    }

    public GOST3410PublicKeyParameters(BigInteger bigInteger, GOST3410Parameters gOST3410Parameters) {
        super(false, gOST3410Parameters);
        this.y = bigInteger;
    }
}
