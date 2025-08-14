package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public class ElGamalPublicKeyParameters extends ElGamalKeyParameters {
    private BigInteger y;

    public BigInteger getY() {
        return this.y;
    }

    public ElGamalPublicKeyParameters(BigInteger bigInteger, ElGamalParameters elGamalParameters) {
        super(false, elGamalParameters);
        this.y = bigInteger;
    }

    @Override // org.spongycastle.crypto.params.ElGamalKeyParameters
    public int hashCode() {
        return this.y.hashCode() ^ super.hashCode();
    }

    @Override // org.spongycastle.crypto.params.ElGamalKeyParameters
    public boolean equals(Object obj) {
        return (obj instanceof ElGamalPublicKeyParameters) && ((ElGamalPublicKeyParameters) obj).getY().equals(this.y) && super.equals(obj);
    }
}
