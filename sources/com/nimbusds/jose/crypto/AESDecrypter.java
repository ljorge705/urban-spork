package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.impl.AAD;
import com.nimbusds.jose.crypto.impl.AESCryptoProvider;
import com.nimbusds.jose.crypto.impl.AESGCMKW;
import com.nimbusds.jose.crypto.impl.AESKW;
import com.nimbusds.jose.crypto.impl.AlgorithmSupportMessage;
import com.nimbusds.jose.crypto.impl.AuthenticatedCipherText;
import com.nimbusds.jose.crypto.impl.ContentCryptoProvider;
import com.nimbusds.jose.crypto.impl.CriticalHeaderParamsDeferral;
import com.nimbusds.jose.crypto.impl.JWEHeaderValidation;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.util.Base64URL;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class AESDecrypter extends AESCryptoProvider implements JWEDecrypter, CriticalHeaderParamsAware {
    private final CriticalHeaderParamsDeferral critPolicy;

    public AESDecrypter(SecretKey secretKey) throws KeyLengthException {
        this(secretKey, null);
    }

    public AESDecrypter(byte[] bArr) throws KeyLengthException {
        this(new SecretKeySpec(bArr, CipherStorageKeystoreAesCbc.ALGORITHM_AES));
    }

    public AESDecrypter(OctetSequenceKey octetSequenceKey) throws KeyLengthException {
        this(octetSequenceKey.toSecretKey(CipherStorageKeystoreAesCbc.ALGORITHM_AES));
    }

    public AESDecrypter(SecretKey secretKey, Set<String> set) throws KeyLengthException {
        super(secretKey, null);
        CriticalHeaderParamsDeferral criticalHeaderParamsDeferral = new CriticalHeaderParamsDeferral();
        this.critPolicy = criticalHeaderParamsDeferral;
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
    public byte[] decrypt(JWEHeader jWEHeader, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, JOSEException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKey secretKeyUnwrapCEK;
        if (base64URL == null) {
            throw new JOSEException("Missing JWE encrypted key");
        }
        if (base64URL2 == null) {
            throw new JOSEException("Missing JWE initialization vector (IV)");
        }
        if (base64URL4 == null) {
            throw new JOSEException("Missing JWE authentication tag");
        }
        JWEAlgorithm algorithmAndEnsureNotNull = JWEHeaderValidation.getAlgorithmAndEnsureNotNull(jWEHeader);
        this.critPolicy.ensureHeaderPasses(jWEHeader);
        int iCekBitLength = jWEHeader.getEncryptionMethod().cekBitLength();
        if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.A128KW) || algorithmAndEnsureNotNull.equals(JWEAlgorithm.A192KW) || algorithmAndEnsureNotNull.equals(JWEAlgorithm.A256KW)) {
            secretKeyUnwrapCEK = AESKW.unwrapCEK(getKey(), base64URL.decode(), getJCAContext().getKeyEncryptionProvider());
        } else if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.A128GCMKW) || algorithmAndEnsureNotNull.equals(JWEAlgorithm.A192GCMKW) || algorithmAndEnsureNotNull.equals(JWEAlgorithm.A256GCMKW)) {
            if (jWEHeader.getIV() == null) {
                throw new JOSEException("Missing JWE \"iv\" header parameter");
            }
            byte[] bArrDecode = jWEHeader.getIV().decode();
            if (jWEHeader.getAuthTag() == null) {
                throw new JOSEException("Missing JWE \"tag\" header parameter");
            }
            secretKeyUnwrapCEK = AESGCMKW.decryptCEK(getKey(), bArrDecode, new AuthenticatedCipherText(base64URL.decode(), jWEHeader.getAuthTag().decode()), iCekBitLength, getJCAContext().getKeyEncryptionProvider());
        } else {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(algorithmAndEnsureNotNull, SUPPORTED_ALGORITHMS));
        }
        return ContentCryptoProvider.decrypt(jWEHeader, bArr, base64URL, base64URL2, base64URL3, base64URL4, secretKeyUnwrapCEK, getJCAContext());
    }
}
