package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.impl.AAD;
import com.nimbusds.jose.crypto.impl.AlgorithmSupportMessage;
import com.nimbusds.jose.crypto.impl.ContentCryptoProvider;
import com.nimbusds.jose.crypto.impl.CriticalHeaderParamsDeferral;
import com.nimbusds.jose.crypto.impl.JWEHeaderValidation;
import com.nimbusds.jose.crypto.impl.RSA1_5;
import com.nimbusds.jose.crypto.impl.RSACryptoProvider;
import com.nimbusds.jose.crypto.impl.RSAKeyUtils;
import com.nimbusds.jose.crypto.impl.RSA_OAEP;
import com.nimbusds.jose.crypto.impl.RSA_OAEP_SHA2;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.Base64URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.spec.InvalidParameterSpecException;
import java.util.Set;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class RSADecrypter extends RSACryptoProvider implements JWEDecrypter, CriticalHeaderParamsAware {
    private Exception cekDecryptionException;
    private final CriticalHeaderParamsDeferral critPolicy;
    private final PrivateKey privateKey;

    public Exception getCEKDecryptionException() {
        return this.cekDecryptionException;
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public RSADecrypter(PrivateKey privateKey) {
        this(privateKey, null, false);
    }

    public RSADecrypter(RSAKey rSAKey) throws JOSEException {
        this(RSAKeyUtils.toRSAPrivateKey(rSAKey));
    }

    public RSADecrypter(PrivateKey privateKey, Set<String> set) {
        this(privateKey, set, false);
    }

    public RSADecrypter(PrivateKey privateKey, Set<String> set, boolean z) {
        int iKeyBitLength;
        super(null);
        CriticalHeaderParamsDeferral criticalHeaderParamsDeferral = new CriticalHeaderParamsDeferral();
        this.critPolicy = criticalHeaderParamsDeferral;
        if (!privateKey.getAlgorithm().equalsIgnoreCase("RSA")) {
            throw new IllegalArgumentException("The private key algorithm must be RSA");
        }
        if (!z && (iKeyBitLength = RSAKeyUtils.keyBitLength(privateKey)) > 0 && iKeyBitLength < 2048) {
            throw new IllegalArgumentException("The RSA key size must be at least 2048 bits");
        }
        this.privateKey = privateKey;
        criticalHeaderParamsDeferral.setDeferredCriticalHeaderParams(set);
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getProcessedCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Override // com.nimbusds.jose.CriticalHeaderParamsAware
    public Set<String> getDeferredCriticalHeaderParams() {
        return this.critPolicy.getProcessedCriticalHeaderParams();
    }

    @Deprecated
    public byte[] decrypt(JWEHeader jWEHeader, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4) throws JOSEException {
        return decrypt(jWEHeader, base64URL, base64URL2, base64URL3, base64URL4, AAD.compute(jWEHeader));
    }

    @Override // com.nimbusds.jose.JWEDecrypter
    public byte[] decrypt(JWEHeader jWEHeader, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, byte[] bArr) throws JOSEException, InvalidParameterSpecException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKey secretKeyDecryptCEK;
        SecretKey secretKey;
        if (base64URL == null) {
            throw new JOSEException("Missing JWE encrypted key");
        }
        if (base64URL2 == null) {
            throw new JOSEException("Missing JWE initialization vector (IV)");
        }
        if (base64URL4 == null) {
            throw new JOSEException("Missing JWE authentication tag");
        }
        this.critPolicy.ensureHeaderPasses(jWEHeader);
        JWEAlgorithm algorithmAndEnsureNotNull = JWEHeaderValidation.getAlgorithmAndEnsureNotNull(jWEHeader);
        if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.RSA1_5)) {
            int iCekBitLength = jWEHeader.getEncryptionMethod().cekBitLength();
            SecretKey secretKeyGenerateCEK = ContentCryptoProvider.generateCEK(jWEHeader.getEncryptionMethod(), getJCAContext().getSecureRandom());
            try {
                SecretKey secretKeyDecryptCEK2 = RSA1_5.decryptCEK(this.privateKey, base64URL.decode(), iCekBitLength, getJCAContext().getKeyEncryptionProvider());
                if (secretKeyDecryptCEK2 != null) {
                    secretKeyGenerateCEK = secretKeyDecryptCEK2;
                }
            } catch (Exception e) {
                this.cekDecryptionException = e;
            }
            this.cekDecryptionException = null;
            secretKey = secretKeyGenerateCEK;
        } else {
            if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.RSA_OAEP)) {
                secretKeyDecryptCEK = RSA_OAEP.decryptCEK(this.privateKey, base64URL.decode(), getJCAContext().getKeyEncryptionProvider());
            } else if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.RSA_OAEP_256)) {
                secretKeyDecryptCEK = RSA_OAEP_SHA2.decryptCEK(this.privateKey, base64URL.decode(), 256, getJCAContext().getKeyEncryptionProvider());
            } else if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.RSA_OAEP_384)) {
                secretKeyDecryptCEK = RSA_OAEP_SHA2.decryptCEK(this.privateKey, base64URL.decode(), 384, getJCAContext().getKeyEncryptionProvider());
            } else if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.RSA_OAEP_512)) {
                secretKeyDecryptCEK = RSA_OAEP_SHA2.decryptCEK(this.privateKey, base64URL.decode(), 512, getJCAContext().getKeyEncryptionProvider());
            } else {
                throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(algorithmAndEnsureNotNull, SUPPORTED_ALGORITHMS));
            }
            secretKey = secretKeyDecryptCEK;
        }
        return ContentCryptoProvider.decrypt(jWEHeader, bArr, base64URL, base64URL2, base64URL3, base64URL4, secretKey, getJCAContext());
    }
}
