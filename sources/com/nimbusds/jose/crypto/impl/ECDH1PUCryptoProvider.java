package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWECryptoParts;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.impl.ECDH;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.util.Base64URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/* loaded from: classes2.dex */
public abstract class ECDH1PUCryptoProvider extends BaseJWEProvider {
    public static final Set<JWEAlgorithm> SUPPORTED_ALGORITHMS;
    public static final Set<EncryptionMethod> SUPPORTED_ENCRYPTION_METHODS = ContentCryptoProvider.SUPPORTED_ENCRYPTION_METHODS;
    private final ConcatKDF concatKDF;
    private final Curve curve;

    protected ConcatKDF getConcatKDF() {
        return this.concatKDF;
    }

    public Curve getCurve() {
        return this.curve;
    }

    public abstract Set<Curve> supportedEllipticCurves();

    static {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(JWEAlgorithm.ECDH_1PU);
        linkedHashSet.add(JWEAlgorithm.ECDH_1PU_A128KW);
        linkedHashSet.add(JWEAlgorithm.ECDH_1PU_A192KW);
        linkedHashSet.add(JWEAlgorithm.ECDH_1PU_A256KW);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
    }

    protected ECDH1PUCryptoProvider(Curve curve, SecretKey secretKey) throws JOSEException {
        super(SUPPORTED_ALGORITHMS, ContentCryptoProvider.SUPPORTED_ENCRYPTION_METHODS, secretKey);
        Curve curve2 = curve != null ? curve : new Curve("unknown");
        if (!supportedEllipticCurves().contains(curve)) {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedEllipticCurve(curve2, supportedEllipticCurves()));
        }
        this.curve = curve;
        this.concatKDF = new ConcatKDF("SHA-256");
    }

    protected JWECryptoParts encryptWithZ(JWEHeader jWEHeader, SecretKey secretKey, byte[] bArr, byte[] bArr2) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, JOSEException, InvalidKeyException, InvalidAlgorithmParameterException {
        ECDH.AlgorithmMode algorithmModeResolveAlgorithmMode = ECDH1PU.resolveAlgorithmMode(JWEHeaderValidation.getAlgorithmAndEnsureNotNull(jWEHeader));
        EncryptionMethod encryptionMethod = jWEHeader.getEncryptionMethod();
        if (algorithmModeResolveAlgorithmMode.equals(ECDH.AlgorithmMode.DIRECT)) {
            if (isCEKProvided()) {
                throw new JOSEException("The provided CEK is not supported");
            }
            getConcatKDF().getJCAContext().setProvider(getJCAContext().getMACProvider());
            return ContentCryptoProvider.encrypt(jWEHeader, bArr, bArr2, ECDH1PU.deriveSharedKey(jWEHeader, secretKey, getConcatKDF()), null, getJCAContext());
        }
        if (algorithmModeResolveAlgorithmMode.equals(ECDH.AlgorithmMode.KW)) {
            if (!EncryptionMethod.Family.AES_CBC_HMAC_SHA.contains(encryptionMethod)) {
                throw new JOSEException(AlgorithmSupportMessage.unsupportedEncryptionMethod(jWEHeader.getEncryptionMethod(), EncryptionMethod.Family.AES_CBC_HMAC_SHA));
            }
            SecretKey cek = getCEK(encryptionMethod);
            JWECryptoParts jWECryptoPartsEncrypt = ContentCryptoProvider.encrypt(jWEHeader, bArr, bArr2, cek, null, getJCAContext());
            return new JWECryptoParts(jWEHeader, Base64URL.encode(AESKW.wrapCEK(cek, ECDH1PU.deriveSharedKey(jWEHeader, secretKey, jWECryptoPartsEncrypt.getAuthenticationTag(), getConcatKDF()), getJCAContext().getKeyEncryptionProvider())), jWECryptoPartsEncrypt.getInitializationVector(), jWECryptoPartsEncrypt.getCipherText(), jWECryptoPartsEncrypt.getAuthenticationTag());
        }
        throw new JOSEException("Unexpected JWE ECDH algorithm mode: " + algorithmModeResolveAlgorithmMode);
    }

    protected byte[] decryptWithZ(JWEHeader jWEHeader, byte[] bArr, SecretKey secretKey, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4) throws NoSuchPaddingException, NoSuchAlgorithmException, JOSEException, InvalidKeyException {
        SecretKey secretKeyUnwrapCEK;
        ECDH.AlgorithmMode algorithmModeResolveAlgorithmMode = ECDH1PU.resolveAlgorithmMode(JWEHeaderValidation.getAlgorithmAndEnsureNotNull(jWEHeader));
        getConcatKDF().getJCAContext().setProvider(getJCAContext().getMACProvider());
        if (algorithmModeResolveAlgorithmMode.equals(ECDH.AlgorithmMode.DIRECT)) {
            secretKeyUnwrapCEK = ECDH1PU.deriveSharedKey(jWEHeader, secretKey, getConcatKDF());
        } else {
            if (!algorithmModeResolveAlgorithmMode.equals(ECDH.AlgorithmMode.KW)) {
                throw new JOSEException("Unexpected JWE ECDH algorithm mode: " + algorithmModeResolveAlgorithmMode);
            }
            if (base64URL == null) {
                throw new JOSEException("Missing JWE encrypted key");
            }
            secretKeyUnwrapCEK = AESKW.unwrapCEK(ECDH1PU.deriveSharedKey(jWEHeader, secretKey, base64URL4, getConcatKDF()), base64URL.decode(), getJCAContext().getKeyEncryptionProvider());
        }
        return ContentCryptoProvider.decrypt(jWEHeader, bArr, null, base64URL2, base64URL3, base64URL4, secretKeyUnwrapCEK, getJCAContext());
    }
}
