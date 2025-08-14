package org.bouncycastle.pqc.crypto.crystals.kyber;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes4.dex */
public class KyberKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private KyberParameters kyberParams;
    private SecureRandom random;

    private AsymmetricCipherKeyPair genKeyPair() {
        KyberEngine engine = this.kyberParams.getEngine();
        engine.init(this.random);
        byte[][] bArrGenerateKemKeyPair = engine.generateKemKeyPair();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new KyberPublicKeyParameters(this.kyberParams, bArrGenerateKemKeyPair[0], bArrGenerateKemKeyPair[1]), (AsymmetricKeyParameter) new KyberPrivateKeyParameters(this.kyberParams, bArrGenerateKemKeyPair[2], bArrGenerateKemKeyPair[3], bArrGenerateKemKeyPair[4], bArrGenerateKemKeyPair[0], bArrGenerateKemKeyPair[1]));
    }

    private void initialize(KeyGenerationParameters keyGenerationParameters) {
        this.kyberParams = ((KyberKeyGenerationParameters) keyGenerationParameters).getParameters();
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
