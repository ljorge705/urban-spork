package org.spongycastle.pqc.jcajce.provider.rainbow;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.pqc.crypto.rainbow.RainbowKeyGenerationParameters;
import org.spongycastle.pqc.crypto.rainbow.RainbowKeyPairGenerator;
import org.spongycastle.pqc.crypto.rainbow.RainbowParameters;
import org.spongycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import org.spongycastle.pqc.crypto.rainbow.RainbowPublicKeyParameters;
import org.spongycastle.pqc.jcajce.spec.RainbowParameterSpec;

/* loaded from: classes7.dex */
public class RainbowKeyPairGeneratorSpi extends KeyPairGenerator {
    RainbowKeyPairGenerator engine;
    boolean initialised;
    RainbowKeyGenerationParameters param;
    SecureRandom random;
    int strength;

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(int i, SecureRandom secureRandom) {
        this.strength = i;
        this.random = secureRandom;
    }

    public RainbowKeyPairGeneratorSpi() {
        super("Rainbow");
        this.engine = new RainbowKeyPairGenerator();
        this.strength = 1024;
        this.random = new SecureRandom();
        this.initialised = false;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) throws InvalidAlgorithmParameterException {
        if (!(algorithmParameterSpec instanceof RainbowParameterSpec)) {
            throw new InvalidAlgorithmParameterException("parameter object not a RainbowParameterSpec");
        }
        RainbowKeyGenerationParameters rainbowKeyGenerationParameters = new RainbowKeyGenerationParameters(secureRandom, new RainbowParameters(((RainbowParameterSpec) algorithmParameterSpec).getVi()));
        this.param = rainbowKeyGenerationParameters;
        this.engine.init(rainbowKeyGenerationParameters);
        this.initialised = true;
    }

    @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
    public KeyPair generateKeyPair() {
        if (!this.initialised) {
            RainbowKeyGenerationParameters rainbowKeyGenerationParameters = new RainbowKeyGenerationParameters(this.random, new RainbowParameters(new RainbowParameterSpec().getVi()));
            this.param = rainbowKeyGenerationParameters;
            this.engine.init(rainbowKeyGenerationParameters);
            this.initialised = true;
        }
        AsymmetricCipherKeyPair asymmetricCipherKeyPairGenerateKeyPair = this.engine.generateKeyPair();
        return new KeyPair(new BCRainbowPublicKey((RainbowPublicKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPublic()), new BCRainbowPrivateKey((RainbowPrivateKeyParameters) asymmetricCipherKeyPairGenerateKeyPair.getPrivate()));
    }
}
