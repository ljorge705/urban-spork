package com.nimbusds.jose.jwk.gen;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.util.Base64URL;
import java.security.SecureRandom;

/* loaded from: classes2.dex */
public class OctetSequenceKeyGenerator extends JWKGenerator<OctetSequenceKey> {
    public static final int MIN_KEY_SIZE_BITS = 112;
    private final int size;

    public OctetSequenceKeyGenerator(int i) {
        if (i < 112) {
            throw new IllegalArgumentException("The key size must be at least 112 bits");
        }
        if (i % 8 != 0) {
            throw new IllegalArgumentException("The key size in bits must be divisible by 8");
        }
        this.size = i;
    }

    @Override // com.nimbusds.jose.jwk.gen.JWKGenerator
    public OctetSequenceKey generate() throws JOSEException {
        byte[] bArr = new byte[this.size / 8];
        if (this.secureRandom != null) {
            this.secureRandom.nextBytes(bArr);
        } else {
            new SecureRandom().nextBytes(bArr);
        }
        OctetSequenceKey.Builder builderKeyStore = new OctetSequenceKey.Builder(Base64URL.encode(bArr)).keyUse(this.use).keyOperations(this.ops).algorithm(this.alg).keyStore(this.keyStore);
        if (this.x5tKid) {
            builderKeyStore.keyIDFromThumbprint();
        } else {
            builderKeyStore.keyID(this.kid);
        }
        return builderKeyStore.build();
    }
}
