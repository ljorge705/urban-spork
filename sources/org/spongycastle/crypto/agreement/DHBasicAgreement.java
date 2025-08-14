package org.spongycastle.crypto.agreement;

import java.math.BigInteger;
import org.spongycastle.crypto.BasicAgreement;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;

/* loaded from: classes3.dex */
public class DHBasicAgreement implements BasicAgreement {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private DHParameters dhParams;
    private DHPrivateKeyParameters key;

    @Override // org.spongycastle.crypto.BasicAgreement
    public void init(CipherParameters cipherParameters) {
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (cipherParameters instanceof ParametersWithRandom) {
            asymmetricKeyParameter = (AsymmetricKeyParameter) ((ParametersWithRandom) cipherParameters).getParameters();
        } else {
            asymmetricKeyParameter = (AsymmetricKeyParameter) cipherParameters;
        }
        if (!(asymmetricKeyParameter instanceof DHPrivateKeyParameters)) {
            throw new IllegalArgumentException("DHEngine expects DHPrivateKeyParameters");
        }
        DHPrivateKeyParameters dHPrivateKeyParameters = (DHPrivateKeyParameters) asymmetricKeyParameter;
        this.key = dHPrivateKeyParameters;
        this.dhParams = dHPrivateKeyParameters.getParameters();
    }

    @Override // org.spongycastle.crypto.BasicAgreement
    public int getFieldSize() {
        return (this.key.getParameters().getP().bitLength() + 7) / 8;
    }

    @Override // org.spongycastle.crypto.BasicAgreement
    public BigInteger calculateAgreement(CipherParameters cipherParameters) {
        DHPublicKeyParameters dHPublicKeyParameters = (DHPublicKeyParameters) cipherParameters;
        if (!dHPublicKeyParameters.getParameters().equals(this.dhParams)) {
            throw new IllegalArgumentException("Diffie-Hellman public key has wrong parameters.");
        }
        BigInteger p = this.dhParams.getP();
        BigInteger y = dHPublicKeyParameters.getY();
        if (y != null) {
            BigInteger bigInteger = ONE;
            if (y.compareTo(bigInteger) > 0 && y.compareTo(p.subtract(bigInteger)) < 0) {
                BigInteger bigIntegerModPow = y.modPow(this.key.getX(), p);
                if (bigIntegerModPow.equals(bigInteger)) {
                    throw new IllegalStateException("Shared key can't be 1");
                }
                return bigIntegerModPow;
            }
        }
        throw new IllegalArgumentException("Diffie-Hellman public key is weak");
    }
}
