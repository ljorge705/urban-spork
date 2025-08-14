package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public class ElGamalPrivateKeyParameters extends ElGamalKeyParameters {
    private BigInteger x;

    public BigInteger getX() {
        return this.x;
    }

    public ElGamalPrivateKeyParameters(BigInteger bigInteger, ElGamalParameters elGamalParameters) {
        super(true, elGamalParameters);
        this.x = bigInteger;
    }

    @Override // org.spongycastle.crypto.params.ElGamalKeyParameters
    public boolean equals(Object obj) {
        if ((obj instanceof ElGamalPrivateKeyParameters) && ((ElGamalPrivateKeyParameters) obj).getX().equals(this.x)) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // org.spongycastle.crypto.params.ElGamalKeyParameters
    public int hashCode() {
        return getX().hashCode();
    }
}
