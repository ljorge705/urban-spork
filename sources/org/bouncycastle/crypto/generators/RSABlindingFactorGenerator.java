package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes4.dex */
public class RSABlindingFactorGenerator {
    private static BigInteger TWO = BigInteger.valueOf(2);
    private RSAKeyParameters key;
    private SecureRandom random;

    public BigInteger generateBlindingFactor() {
        RSAKeyParameters rSAKeyParameters = this.key;
        if (rSAKeyParameters == null) {
            throw new IllegalStateException("generator not initialised");
        }
        BigInteger modulus = rSAKeyParameters.getModulus();
        int iBitLength = modulus.bitLength() - 1;
        while (true) {
            BigInteger bigIntegerCreateRandomBigInteger = BigIntegers.createRandomBigInteger(iBitLength, this.random);
            if (bigIntegerCreateRandomBigInteger.compareTo(TWO) >= 0 && BigIntegers.modOddIsCoprime(modulus, bigIntegerCreateRandomBigInteger)) {
                return bigIntegerCreateRandomBigInteger;
            }
        }
    }

    public void init(CipherParameters cipherParameters) {
        SecureRandom secureRandom;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.key = (RSAKeyParameters) parametersWithRandom.getParameters();
            secureRandom = parametersWithRandom.getRandom();
        } else {
            this.key = (RSAKeyParameters) cipherParameters;
            secureRandom = CryptoServicesRegistrar.getSecureRandom();
        }
        this.random = secureRandom;
        if (this.key instanceof RSAPrivateCrtKeyParameters) {
            throw new IllegalArgumentException("generator requires RSA public key");
        }
    }
}
