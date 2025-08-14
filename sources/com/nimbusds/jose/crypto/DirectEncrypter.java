package com.nimbusds.jose.crypto;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWECryptoParts;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.impl.AAD;
import com.nimbusds.jose.crypto.impl.AlgorithmSupportMessage;
import com.nimbusds.jose.crypto.impl.ContentCryptoProvider;
import com.nimbusds.jose.crypto.impl.DirectCryptoProvider;
import com.nimbusds.jose.crypto.impl.JWEHeaderValidation;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class DirectEncrypter extends DirectCryptoProvider implements JWEEncrypter {
    public DirectEncrypter(SecretKey secretKey) throws KeyLengthException {
        super(secretKey);
    }

    public DirectEncrypter(byte[] bArr) throws KeyLengthException {
        this(new SecretKeySpec(bArr, CipherStorageKeystoreAesCbc.ALGORITHM_AES));
    }

    public DirectEncrypter(OctetSequenceKey octetSequenceKey) throws KeyLengthException {
        this(octetSequenceKey.toSecretKey(CipherStorageKeystoreAesCbc.ALGORITHM_AES));
    }

    @Deprecated
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr) throws JOSEException {
        return encrypt(jWEHeader, bArr, AAD.compute(jWEHeader));
    }

    @Override // com.nimbusds.jose.JWEEncrypter
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr, byte[] bArr2) throws JOSEException {
        JWEAlgorithm algorithmAndEnsureNotNull = JWEHeaderValidation.getAlgorithmAndEnsureNotNull(jWEHeader);
        if (!algorithmAndEnsureNotNull.equals(JWEAlgorithm.DIR)) {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(algorithmAndEnsureNotNull, SUPPORTED_ALGORITHMS));
        }
        return ContentCryptoProvider.encrypt(jWEHeader, bArr, bArr2, getKey(), null, getJCAContext());
    }
}
