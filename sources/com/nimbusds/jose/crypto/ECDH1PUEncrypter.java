package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWECryptoParts;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.impl.AAD;
import com.nimbusds.jose.crypto.impl.ECDH1PU;
import com.nimbusds.jose.crypto.impl.ECDH1PUCryptoProvider;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.ECKey;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class ECDH1PUEncrypter extends ECDH1PUCryptoProvider implements JWEEncrypter {
    public static final Set<Curve> SUPPORTED_ELLIPTIC_CURVES;
    private final ECPrivateKey privateKey;
    private final ECPublicKey publicKey;

    public ECPrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public ECPublicKey getPublicKey() {
        return this.publicKey;
    }

    @Override // com.nimbusds.jose.crypto.impl.ECDH1PUCryptoProvider
    public Set<Curve> supportedEllipticCurves() {
        return SUPPORTED_ELLIPTIC_CURVES;
    }

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(Curve.P_256);
        linkedHashSet.add(Curve.P_384);
        linkedHashSet.add(Curve.P_521);
        SUPPORTED_ELLIPTIC_CURVES = Collections.unmodifiableSet(linkedHashSet);
    }

    public ECDH1PUEncrypter(ECPrivateKey eCPrivateKey, ECPublicKey eCPublicKey) throws JOSEException {
        this(eCPrivateKey, eCPublicKey, null);
    }

    public ECDH1PUEncrypter(ECPrivateKey eCPrivateKey, ECPublicKey eCPublicKey, SecretKey secretKey) throws JOSEException {
        super(Curve.forECParameterSpec(eCPublicKey.getParams()), secretKey);
        this.privateKey = eCPrivateKey;
        this.publicKey = eCPublicKey;
    }

    @Deprecated
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr) throws JOSEException {
        return encrypt(jWEHeader, bArr, AAD.compute(jWEHeader));
    }

    @Override // com.nimbusds.jose.JWEEncrypter
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, JOSEException, InvalidAlgorithmParameterException {
        KeyPair keyPairGenerateEphemeralKeyPair = generateEphemeralKeyPair(this.publicKey.getParams());
        ECPublicKey eCPublicKey = (ECPublicKey) keyPairGenerateEphemeralKeyPair.getPublic();
        ECPrivateKey eCPrivateKey = (ECPrivateKey) keyPairGenerateEphemeralKeyPair.getPrivate();
        JWEHeader jWEHeaderBuild = new JWEHeader.Builder(jWEHeader).ephemeralPublicKey(new ECKey.Builder(getCurve(), eCPublicKey).build()).build();
        SecretKey secretKeyDeriveSenderZ = ECDH1PU.deriveSenderZ(this.privateKey, this.publicKey, eCPrivateKey, getJCAContext().getKeyEncryptionProvider());
        if (Arrays.equals(AAD.compute(jWEHeader), bArr2)) {
            bArr2 = AAD.compute(jWEHeaderBuild);
        }
        return encryptWithZ(jWEHeaderBuild, secretKeyDeriveSenderZ, bArr, bArr2);
    }

    private KeyPair generateEphemeralKeyPair(ECParameterSpec eCParameterSpec) throws NoSuchAlgorithmException, JOSEException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator;
        Provider keyEncryptionProvider = getJCAContext().getKeyEncryptionProvider();
        try {
            if (keyEncryptionProvider != null) {
                keyPairGenerator = KeyPairGenerator.getInstance("EC", keyEncryptionProvider);
            } else {
                keyPairGenerator = KeyPairGenerator.getInstance("EC");
            }
            keyPairGenerator.initialize(eCParameterSpec);
            return keyPairGenerator.generateKeyPair();
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException e) {
            throw new JOSEException("Couldn't generate ephemeral EC key pair: " + e.getMessage(), e);
        }
    }
}
