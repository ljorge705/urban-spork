package com.nimbusds.jose.jwk.gen;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;

/* loaded from: classes2.dex */
public class RSAKeyGenerator extends JWKGenerator<RSAKey> {
    public static final int MIN_KEY_SIZE_BITS = 2048;
    private final int size;

    public RSAKeyGenerator(int i) {
        this(i, false);
    }

    public RSAKeyGenerator(int i, boolean z) {
        if (!z && i < 2048) {
            throw new IllegalArgumentException("The key size must be at least 2048 bits");
        }
        this.size = i;
    }

    @Override // com.nimbusds.jose.jwk.gen.JWKGenerator
    public RSAKey generate() throws NoSuchAlgorithmException, JOSEException {
        KeyPairGenerator keyPairGenerator;
        try {
            if (this.keyStore != null) {
                keyPairGenerator = KeyPairGenerator.getInstance("RSA", this.keyStore.getProvider());
            } else if (this.provider != null) {
                keyPairGenerator = KeyPairGenerator.getInstance("RSA", this.provider);
            } else {
                keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            }
            if (this.secureRandom != null) {
                keyPairGenerator.initialize(this.size, this.secureRandom);
            } else {
                keyPairGenerator.initialize(this.size);
            }
            KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
            RSAKey.Builder builderKeyStore = new RSAKey.Builder((RSAPublicKey) keyPairGenerateKeyPair.getPublic()).privateKey(keyPairGenerateKeyPair.getPrivate()).keyUse(this.use).keyOperations(this.ops).algorithm(this.alg).expirationTime(this.exp).notBeforeTime(this.nbf).issueTime(this.iat).keyStore(this.keyStore);
            if (this.x5tKid) {
                builderKeyStore.keyIDFromThumbprint();
            } else {
                builderKeyStore.keyID(this.kid);
            }
            return builderKeyStore.build();
        } catch (NoSuchAlgorithmException e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }
}
