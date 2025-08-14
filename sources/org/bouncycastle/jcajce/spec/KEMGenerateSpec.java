package org.bouncycastle.jcajce.spec;

import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;

/* loaded from: classes4.dex */
public class KEMGenerateSpec implements AlgorithmParameterSpec {
    private final String keyAlgorithmName;
    private final int keySizeInBits;
    private final PublicKey publicKey;

    public KEMGenerateSpec(PublicKey publicKey, String str) {
        this(publicKey, str, 256);
    }

    public KEMGenerateSpec(PublicKey publicKey, String str, int i) {
        this.publicKey = publicKey;
        this.keyAlgorithmName = str;
        this.keySizeInBits = i;
    }

    public String getKeyAlgorithmName() {
        return this.keyAlgorithmName;
    }

    public int getKeySize() {
        return this.keySizeInBits;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }
}
