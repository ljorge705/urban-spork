package org.bouncycastle.pqc.crypto.xwing;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.generators.X25519KeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.X25519KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberKeyPairGenerator;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberParameters;

/* loaded from: classes4.dex */
public class XWingKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private SecureRandom random;

    private AsymmetricCipherKeyPair genKeyPair() {
        KyberKeyPairGenerator kyberKeyPairGenerator = new KyberKeyPairGenerator();
        kyberKeyPairGenerator.init(new KyberKeyGenerationParameters(this.random, KyberParameters.kyber768));
        X25519KeyPairGenerator x25519KeyPairGenerator = new X25519KeyPairGenerator();
        x25519KeyPairGenerator.init(new X25519KeyGenerationParameters(this.random));
        AsymmetricCipherKeyPair asymmetricCipherKeyPairGenerateKeyPair = kyberKeyPairGenerator.generateKeyPair();
        AsymmetricCipherKeyPair asymmetricCipherKeyPairGenerateKeyPair2 = x25519KeyPairGenerator.generateKeyPair();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new XWingPublicKeyParameters(asymmetricCipherKeyPairGenerateKeyPair.getPublic(), asymmetricCipherKeyPairGenerateKeyPair2.getPublic()), (AsymmetricKeyParameter) new XWingPrivateKeyParameters(asymmetricCipherKeyPairGenerateKeyPair.getPrivate(), asymmetricCipherKeyPairGenerateKeyPair2.getPrivate()));
    }

    private void initialize(KeyGenerationParameters keyGenerationParameters) {
        this.random = keyGenerationParameters.getRandom();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }
}
