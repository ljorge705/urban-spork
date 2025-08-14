package org.spongycastle.jcajce.provider.asymmetric.rsa;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.RSAKeyGenParameterSpec;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.generators.RSAKeyPairGenerator;
import org.spongycastle.crypto.params.RSAKeyGenerationParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.spongycastle.jcajce.provider.asymmetric.util.PrimeCertaintyCalculator;

/* loaded from: classes7.dex */
public class KeyPairGeneratorSpi extends KeyPairGenerator {
    static final BigInteger defaultPublicExponent = BigInteger.valueOf(65537);
    RSAKeyPairGenerator engine;
    RSAKeyGenerationParameters param;

    public KeyPairGeneratorSpi(String str) {
        super(str);
    }

    public KeyPairGeneratorSpi() {
        super("RSA");
        this.engine = new RSAKeyPairGenerator();
        RSAKeyGenerationParameters rSAKeyGenerationParameters = new RSAKeyGenerationParameters(defaultPublicExponent, new SecureRandom(), 2048, PrimeCertaintyCalculator.getDefaultCertainty(2048));
        this.param = rSAKeyGenerationParameters;
        this.engine.init(rSAKeyGenerationParameters);
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i, SecureRandom secureRandom) {
        RSAKeyGenerationParameters rSAKeyGenerationParameters = new RSAKeyGenerationParameters(defaultPublicExponent, secureRandom, i, PrimeCertaintyCalculator.getDefaultCertainty(i));
        this.param = rSAKeyGenerationParameters;
        this.engine.init(rSAKeyGenerationParameters);
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof RSAKeyGenParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a RSAKeyGenParameterSpec");
        }
        RSAKeyGenParameterSpec rSAKeyGenParameterSpec = (RSAKeyGenParameterSpec) algorithmParameterSpec;
        RSAKeyGenerationParameters rSAKeyGenerationParameters = new RSAKeyGenerationParameters(rSAKeyGenParameterSpec.getPublicExponent(), secureRandom, rSAKeyGenParameterSpec.getKeysize(), PrimeCertaintyCalculator.getDefaultCertainty(2048));
        this.param = rSAKeyGenerationParameters;
        this.engine.init(rSAKeyGenerationParameters);
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        AsymmetricCipherKeyPair asymmetricCipherKeyPairGenerateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCRSAPublicKey((RSAKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPublic()), new BCRSAPrivateCrtKey((RSAPrivateCrtKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPrivate()));
    }
}
