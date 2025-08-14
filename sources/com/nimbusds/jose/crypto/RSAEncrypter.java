package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWECryptoParts;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.impl.AAD;
import com.nimbusds.jose.crypto.impl.AlgorithmSupportMessage;
import com.nimbusds.jose.crypto.impl.ContentCryptoProvider;
import com.nimbusds.jose.crypto.impl.JWEHeaderValidation;
import com.nimbusds.jose.crypto.impl.RSA1_5;
import com.nimbusds.jose.crypto.impl.RSACryptoProvider;
import com.nimbusds.jose.crypto.impl.RSA_OAEP;
import com.nimbusds.jose.crypto.impl.RSA_OAEP_SHA2;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.Base64URL;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class RSAEncrypter extends RSACryptoProvider implements JWEEncrypter {
    private final RSAPublicKey publicKey;

    public RSAPublicKey getPublicKey() {
        return this.publicKey;
    }

    public RSAEncrypter(RSAPublicKey rSAPublicKey) {
        this(rSAPublicKey, null);
    }

    public RSAEncrypter(RSAKey rSAKey) throws JOSEException {
        this(rSAKey.toRSAPublicKey());
    }

    public RSAEncrypter(RSAPublicKey rSAPublicKey, SecretKey secretKey) {
        super(secretKey);
        if (rSAPublicKey == null) {
            throw new IllegalArgumentException("The public RSA key must not be null");
        }
        this.publicKey = rSAPublicKey;
    }

    @Deprecated
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr) throws JOSEException {
        return encrypt(jWEHeader, bArr, AAD.compute(jWEHeader));
    }

    @Override // com.nimbusds.jose.JWEEncrypter
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr, byte[] bArr2) throws JOSEException {
        Base64URL base64URLEncode;
        JWEAlgorithm algorithmAndEnsureNotNull = JWEHeaderValidation.getAlgorithmAndEnsureNotNull(jWEHeader);
        SecretKey cek = getCEK(jWEHeader.getEncryptionMethod());
        if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.RSA1_5)) {
            base64URLEncode = Base64URL.encode(RSA1_5.encryptCEK(this.publicKey, cek, getJCAContext().getKeyEncryptionProvider()));
        } else if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.RSA_OAEP)) {
            base64URLEncode = Base64URL.encode(RSA_OAEP.encryptCEK(this.publicKey, cek, getJCAContext().getKeyEncryptionProvider()));
        } else if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.RSA_OAEP_256)) {
            base64URLEncode = Base64URL.encode(RSA_OAEP_SHA2.encryptCEK(this.publicKey, cek, 256, getJCAContext().getKeyEncryptionProvider()));
        } else if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.RSA_OAEP_384)) {
            base64URLEncode = Base64URL.encode(RSA_OAEP_SHA2.encryptCEK(this.publicKey, cek, 384, getJCAContext().getKeyEncryptionProvider()));
        } else if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.RSA_OAEP_512)) {
            base64URLEncode = Base64URL.encode(RSA_OAEP_SHA2.encryptCEK(this.publicKey, cek, 512, getJCAContext().getKeyEncryptionProvider()));
        } else {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(algorithmAndEnsureNotNull, SUPPORTED_ALGORITHMS));
        }
        return ContentCryptoProvider.encrypt(jWEHeader, bArr, bArr2, cek, base64URLEncode, getJCAContext());
    }
}
