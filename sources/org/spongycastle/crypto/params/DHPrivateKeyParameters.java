package org.spongycastle.crypto.params;

import java.math.BigInteger;

/* loaded from: classes7.dex */
public class DHPrivateKeyParameters extends DHKeyParameters {
    private BigInteger x;

    public BigInteger getX() {
        return this.x;
    }

    public DHPrivateKeyParameters(BigInteger bigInteger, DHParameters dHParameters) {
        super(true, dHParameters);
        this.x = bigInteger;
    }

    @Override // org.spongycastle.crypto.params.DHKeyParameters
    public int hashCode() {
        return this.x.hashCode() ^ super.hashCode();
    }

    @Override // org.spongycastle.crypto.params.DHKeyParameters
    public boolean equals(Object obj) {
        return (obj instanceof DHPrivateKeyParameters) && ((DHPrivateKeyParameters) obj).getX().equals(this.x) && super.equals(obj);
    }
}
