package com.nimbusds.jose.jwk.gen;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;

/* loaded from: classes2.dex */
public class ECKeyGenerator extends JWKGenerator<ECKey> {
    private final Curve crv;

    public ECKeyGenerator(Curve curve) {
        if (curve == null) {
            throw new IllegalArgumentException("The curve must not be null");
        }
        this.crv = curve;
    }

    @Override // com.nimbusds.jose.jwk.gen.JWKGenerator
    public ECKey generate() throws NoSuchAlgorithmException, JOSEException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator;
        ECParameterSpec eCParameterSpec = this.crv.toECParameterSpec();
        try {
            if (this.keyStore != null) {
                keyPairGenerator = KeyPairGenerator.getInstance("EC", this.keyStore.getProvider());
            } else if (this.provider != null) {
                keyPairGenerator = KeyPairGenerator.getInstance("EC", this.provider);
            } else {
                keyPairGenerator = KeyPairGenerator.getInstance("EC");
            }
            if (this.secureRandom != null) {
                keyPairGenerator.initialize(eCParameterSpec, this.secureRandom);
            } else {
                keyPairGenerator.initialize(eCParameterSpec);
            }
            KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
            ECKey.Builder builderKeyStore = new ECKey.Builder(this.crv, (ECPublicKey) keyPairGenerateKeyPair.getPublic()).privateKey(keyPairGenerateKeyPair.getPrivate()).keyUse(this.use).keyOperations(this.ops).algorithm(this.alg).expirationTime(this.exp).notBeforeTime(this.nbf).issueTime(this.iat).keyStore(this.keyStore);
            if (this.x5tKid) {
                builderKeyStore.keyIDFromThumbprint();
            } else {
                builderKeyStore.keyID(this.kid);
            }
            return builderKeyStore.build();
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }
}
