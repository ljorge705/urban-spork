package org.spongycastle.crypto.agreement;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.generators.DHKeyPairGenerator;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHKeyGenerationParameters;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;

/* loaded from: classes3.dex */
public class DHAgreement {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private DHParameters dhParams;
    private DHPrivateKeyParameters key;
    private BigInteger privateValue;
    private SecureRandom random;

    public void init(CipherParameters cipherParameters) {
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.random = parametersWithRandom.getRandom();
            asymmetricKeyParameter = (AsymmetricKeyParameter) parametersWithRandom.getParameters();
        } else {
            this.random = new SecureRandom();
            asymmetricKeyParameter = (AsymmetricKeyParameter) cipherParameters;
        }
        if (!(asymmetricKeyParameter instanceof DHPrivateKeyParameters)) {
            throw new IllegalArgumentException("DHEngine expects DHPrivateKeyParameters");
        }
        DHPrivateKeyParameters dHPrivateKeyParameters = (DHPrivateKeyParameters) asymmetricKeyParameter;
        this.key = dHPrivateKeyParameters;
        this.dhParams = dHPrivateKeyParameters.getParameters();
    }

    public BigInteger calculateMessage() {
        DHKeyPairGenerator dHKeyPairGenerator = new DHKeyPairGenerator();
        dHKeyPairGenerator.init(new DHKeyGenerationParameters(this.random, this.dhParams));
        AsymmetricCipherKeyPair asymmetricCipherKeyPairGenerateKeyPair = dHKeyPairGenerator.generateKeyPair();
        this.privateValue = ((DHPrivateKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPrivate()).getX();
        return ((DHPublicKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPublic()).getY();
    }

    public BigInteger calculateAgreement(DHPublicKeyParameters dHPublicKeyParameters, BigInteger bigInteger) {
        if (!dHPublicKeyParameters.getParameters().equals(this.dhParams)) {
            throw new IllegalArgumentException("Diffie-Hellman public key has wrong parameters.");
        }
        BigInteger p = this.dhParams.getP();
        BigInteger y = dHPublicKeyParameters.getY();
        if (y != null) {
            BigInteger bigInteger2 = ONE;
            if (y.compareTo(bigInteger2) > 0 && y.compareTo(p.subtract(bigInteger2)) < 0) {
                BigInteger bigIntegerModPow = y.modPow(this.privateValue, p);
                if (bigIntegerModPow.equals(bigInteger2)) {
                    throw new IllegalStateException("Shared key can't be 1");
                }
                return bigInteger.modPow(this.key.getX(), p).multiply(bigIntegerModPow).mod(p);
            }
        }
        throw new IllegalArgumentException("Diffie-Hellman public key is weak");
    }
}
