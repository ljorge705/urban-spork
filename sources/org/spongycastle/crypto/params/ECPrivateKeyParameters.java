package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public class ECPrivateKeyParameters extends ECKeyParameters {
    BigInteger d;

    public BigInteger getD() {
        return this.d;
    }

    public ECPrivateKeyParameters(BigInteger bigInteger, ECDomainParameters eCDomainParameters) {
        super(true, eCDomainParameters);
        this.d = bigInteger;
    }
}
