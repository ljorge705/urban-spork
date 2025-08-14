package com.nimbusds.jose.crypto;

import com.nimbusds.jose.CriticalHeaderParamsAware;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.impl.AAD;
import com.nimbusds.jose.crypto.impl.AlgorithmSupportMessage;
import com.nimbusds.jose.crypto.impl.ContentCryptoProvider;
import com.nimbusds.jose.crypto.impl.CriticalHeaderParamsDeferral;
import com.nimbusds.jose.crypto.impl.DirectCryptoProvider;
import com.nimbusds.jose.crypto.impl.JWEHeaderValidation;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.util.Base64URL;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.util.Set;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class DirectDecrypter extends DirectCryptoProvider implements JWEDecrypter, CriticalHeaderParamsAware {
    private final CriticalHeaderParamsDeferral critPolicy;
    private final boolean promiscuousMode;

    public DirectDecrypter(SecretKey secretKey) throws KeyLengthException {
        this(secretKey, false);
    }

    public DirectDecrypter(SecretKey secretKey, boolean z) throws KeyLengthException {
        super(secretKey);
        this.critPolicy = new CriticalHeaderParamsDeferral();
        this.promiscuousMode = z;
    }

    public DirectDecrypter(byte[] bArr) throws KeyLengthException {
        this((SecretKey) new SecretKeySpec(bArr, CipherStorageKeystoreAesCbc.ALGORITHM_AES), false);
    }

    public DirectDecrypter(OctetSequenceKey octetSequenceKey) throws KeyLengthException {
        this(octetSequenceKey.toSecretKey(CipherStorageKeystoreAesCbc.ALGORITHM_AES));
    }

    public DirectDecrypter(SecretKey secretKey, Set<String> set) throws KeyLengthException {
        this(secretKey, set, false);
    }

    public DirectDecrypter(SecretKey secretKey, Set<String> set, boolean z) throws KeyLengthException {
        super(secretKey);
        CriticalHeaderParamsDeferral criticalHeaderParamsDeferral = new CriticalHeaderParamsDeferral();
        this.critPolicy = criticalHeaderParamsDeferral;
        criticalHeaderParamsDeferral.setDeferredCriticalHeaderParams(set);
        this.promiscuousMode = z;
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
    public byte[] decrypt(JWEHeader jWEHeader, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4, byte[] bArr) throws JOSEException {
        if (!this.promiscuousMode) {
            JWEAlgorithm algorithmAndEnsureNotNull = JWEHeaderValidation.getAlgorithmAndEnsureNotNull(jWEHeader);
            if (!algorithmAndEnsureNotNull.equals(JWEAlgorithm.DIR)) {
                throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(algorithmAndEnsureNotNull, SUPPORTED_ALGORITHMS));
            }
            if (base64URL != null) {
                throw new JOSEException("Unexpected present JWE encrypted key");
            }
        }
        if (base64URL2 == null) {
            throw new JOSEException("Unexpected present JWE initialization vector (IV)");
        }
        if (base64URL4 == null) {
            throw new JOSEException("Missing JWE authentication tag");
        }
        this.critPolicy.ensureHeaderPasses(jWEHeader);
        return ContentCryptoProvider.decrypt(jWEHeader, bArr, null, base64URL2, base64URL3, base64URL4, getKey(), getJCAContext());
    }
}
