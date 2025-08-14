package com.nimbusds.jose.crypto;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWECryptoParts;
import com.nimbusds.jose.JWEEncrypter;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.impl.AAD;
import com.nimbusds.jose.crypto.impl.AESCryptoProvider;
import com.nimbusds.jose.crypto.impl.AESGCM;
import com.nimbusds.jose.crypto.impl.AESGCMKW;
import com.nimbusds.jose.crypto.impl.AESKW;
import com.nimbusds.jose.crypto.impl.AlgorithmSupportMessage;
import com.nimbusds.jose.crypto.impl.AuthenticatedCipherText;
import com.nimbusds.jose.crypto.impl.ContentCryptoProvider;
import com.nimbusds.jose.crypto.impl.JWEHeaderValidation;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.ByteUtils;
import com.nimbusds.jose.util.Container;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.util.Arrays;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class AESEncrypter extends AESCryptoProvider implements JWEEncrypter {

    private enum AlgFamily {
        AESKW,
        AESGCMKW
    }

    public AESEncrypter(SecretKey secretKey, SecretKey secretKey2) throws KeyLengthException {
        super(secretKey, secretKey2);
    }

    public AESEncrypter(SecretKey secretKey) throws KeyLengthException {
        this(secretKey, null);
    }

    public AESEncrypter(byte[] bArr) throws KeyLengthException {
        this(new SecretKeySpec(bArr, CipherStorageKeystoreAesCbc.ALGORITHM_AES));
    }

    public AESEncrypter(OctetSequenceKey octetSequenceKey) throws KeyLengthException {
        this(octetSequenceKey.toSecretKey(CipherStorageKeystoreAesCbc.ALGORITHM_AES));
    }

    @Deprecated
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr) throws JOSEException {
        return encrypt(jWEHeader, bArr, AAD.compute(jWEHeader));
    }

    @Override // com.nimbusds.jose.JWEEncrypter
    public JWECryptoParts encrypt(JWEHeader jWEHeader, byte[] bArr, byte[] bArr2) throws JOSEException {
        AlgFamily algFamily;
        JWEHeader jWEHeaderBuild;
        Base64URL base64URLEncode;
        JWEAlgorithm algorithmAndEnsureNotNull = JWEHeaderValidation.getAlgorithmAndEnsureNotNull(jWEHeader);
        EncryptionMethod encryptionMethod = jWEHeader.getEncryptionMethod();
        if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.A128KW)) {
            if (ByteUtils.safeBitLength(getKey().getEncoded()) != 128) {
                throw new KeyLengthException("The Key Encryption Key (KEK) length must be 128 bits for A128KW encryption");
            }
            algFamily = AlgFamily.AESKW;
        } else if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.A192KW)) {
            if (ByteUtils.safeBitLength(getKey().getEncoded()) != 192) {
                throw new KeyLengthException("The Key Encryption Key (KEK) length must be 192 bits for A192KW encryption");
            }
            algFamily = AlgFamily.AESKW;
        } else if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.A256KW)) {
            if (ByteUtils.safeBitLength(getKey().getEncoded()) != 256) {
                throw new KeyLengthException("The Key Encryption Key (KEK) length must be 256 bits for A256KW encryption");
            }
            algFamily = AlgFamily.AESKW;
        } else if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.A128GCMKW)) {
            if (ByteUtils.safeBitLength(getKey().getEncoded()) != 128) {
                throw new KeyLengthException("The Key Encryption Key (KEK) length must be 128 bits for A128GCMKW encryption");
            }
            algFamily = AlgFamily.AESGCMKW;
        } else if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.A192GCMKW)) {
            if (ByteUtils.safeBitLength(getKey().getEncoded()) != 192) {
                throw new KeyLengthException("The Key Encryption Key (KEK) length must be 192 bits for A192GCMKW encryption");
            }
            algFamily = AlgFamily.AESGCMKW;
        } else if (algorithmAndEnsureNotNull.equals(JWEAlgorithm.A256GCMKW)) {
            if (ByteUtils.safeBitLength(getKey().getEncoded()) != 256) {
                throw new KeyLengthException("The Key Encryption Key (KEK) length must be 256 bits for A256GCMKW encryption");
            }
            algFamily = AlgFamily.AESGCMKW;
        } else {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(algorithmAndEnsureNotNull, SUPPORTED_ALGORITHMS));
        }
        SecretKey cek = getCEK(encryptionMethod);
        if (AlgFamily.AESKW.equals(algFamily)) {
            jWEHeaderBuild = jWEHeader;
            base64URLEncode = Base64URL.encode(AESKW.wrapCEK(cek, getKey(), getJCAContext().getKeyEncryptionProvider()));
        } else if (AlgFamily.AESGCMKW.equals(algFamily)) {
            Container container = new Container(AESGCM.generateIV(getJCAContext().getSecureRandom()));
            AuthenticatedCipherText authenticatedCipherTextEncryptCEK = AESGCMKW.encryptCEK(cek, container, getKey(), getJCAContext().getKeyEncryptionProvider());
            Base64URL base64URLEncode2 = Base64URL.encode(authenticatedCipherTextEncryptCEK.getCipherText());
            jWEHeaderBuild = new JWEHeader.Builder(jWEHeader).iv(Base64URL.encode((byte[]) container.get())).authTag(Base64URL.encode(authenticatedCipherTextEncryptCEK.getAuthenticationTag())).build();
            base64URLEncode = base64URLEncode2;
        } else {
            throw new JOSEException("Unexpected JWE algorithm: " + algorithmAndEnsureNotNull);
        }
        if (Arrays.equals(AAD.compute(jWEHeader), bArr2)) {
            bArr2 = AAD.compute(jWEHeaderBuild);
        }
        return ContentCryptoProvider.encrypt(jWEHeaderBuild, bArr, bArr2, cek, base64URLEncode, getJCAContext());
    }
}
