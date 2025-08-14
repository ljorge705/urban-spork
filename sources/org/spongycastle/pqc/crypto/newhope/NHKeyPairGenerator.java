package org.spongycastle.pqc.crypto.newhope;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;

/* loaded from: classes7.dex */
public class NHKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private SecureRandom random;

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.random = keyGenerationParameters.getRandom();
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        byte[] bArr = new byte[1824];
        short[] sArr = new short[1024];
        NewHope.keygen(this.random, bArr, sArr);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new NHPublicKeyParameters(bArr), (AsymmetricKeyParameter) new NHPrivateKeyParameters(sArr));
    }
}
