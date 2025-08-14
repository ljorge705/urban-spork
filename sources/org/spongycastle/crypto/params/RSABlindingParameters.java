package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

/* loaded from: classes7.dex */
public class RSABlindingParameters implements CipherParameters {
    private BigInteger blindingFactor;
    private RSAKeyParameters publicKey;

    public BigInteger getBlindingFactor() {
        return this.blindingFactor;
    }

    public RSAKeyParameters getPublicKey() {
        return this.publicKey;
    }

    public RSABlindingParameters(RSAKeyParameters rSAKeyParameters, BigInteger bigInteger) {
        if (rSAKeyParameters instanceof RSAPrivateCrtKeyParameters) {
            throw new IllegalArgumentException("RSA parameters should be for a public key");
        }
        this.publicKey = rSAKeyParameters;
        this.blindingFactor = bigInteger;
    }
}
