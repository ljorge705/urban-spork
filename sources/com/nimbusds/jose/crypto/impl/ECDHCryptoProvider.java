package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWECryptoParts;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.impl.ECDH;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.util.Base64URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/* loaded from: classes2.dex */
public abstract class ECDHCryptoProvider extends BaseJWEProvider {
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
        linkedHashSet.add(JWEAlgorithm.ECDH_ES);
        linkedHashSet.add(JWEAlgorithm.ECDH_ES_A128KW);
        linkedHashSet.add(JWEAlgorithm.ECDH_ES_A192KW);
        linkedHashSet.add(JWEAlgorithm.ECDH_ES_A256KW);
        SUPPORTED_ALGORITHMS = Collections.unmodifiableSet(linkedHashSet);
    }

    protected ECDHCryptoProvider(Curve curve, SecretKey secretKey) throws JOSEException {
        super(SUPPORTED_ALGORITHMS, ContentCryptoProvider.SUPPORTED_ENCRYPTION_METHODS, secretKey);
        Curve curve2 = curve != null ? curve : new Curve("unknown");
        if (!supportedEllipticCurves().contains(curve)) {
            throw new JOSEException(AlgorithmSupportMessage.unsupportedEllipticCurve(curve2, supportedEllipticCurves()));
        }
        this.curve = curve;
        this.concatKDF = new ConcatKDF("SHA-256");
    }

    protected JWECryptoParts encryptWithZ(JWEHeader jWEHeader, SecretKey secretKey, byte[] bArr, byte[] bArr2) throws JOSEException {
        Base64URL base64URLEncode;
        SecretKey secretKey2;
        ECDH.AlgorithmMode algorithmModeResolveAlgorithmMode = ECDH.resolveAlgorithmMode(JWEHeaderValidation.getAlgorithmAndEnsureNotNull(jWEHeader));
        EncryptionMethod encryptionMethod = jWEHeader.getEncryptionMethod();
        getConcatKDF().getJCAContext().setProvider(getJCAContext().getMACProvider());
        SecretKey secretKeyDeriveSharedKey = ECDH.deriveSharedKey(jWEHeader, secretKey, getConcatKDF());
        if (algorithmModeResolveAlgorithmMode.equals(ECDH.AlgorithmMode.DIRECT)) {
            if (isCEKProvided()) {
                throw new JOSEException("The provided CEK is not supported");
            }
            secretKey2 = secretKeyDeriveSharedKey;
            base64URLEncode = null;
        } else if (algorithmModeResolveAlgorithmMode.equals(ECDH.AlgorithmMode.KW)) {
            SecretKey cek = getCEK(encryptionMethod);
            base64URLEncode = Base64URL.encode(AESKW.wrapCEK(cek, secretKeyDeriveSharedKey, getJCAContext().getKeyEncryptionProvider()));
            secretKey2 = cek;
        } else {
            throw new JOSEException("Unexpected JWE ECDH algorithm mode: " + algorithmModeResolveAlgorithmMode);
        }
        return ContentCryptoProvider.encrypt(jWEHeader, bArr, bArr2, secretKey2, base64URLEncode, getJCAContext());
    }

    protected byte[] decryptWithZ(JWEHeader jWEHeader, byte[] bArr, SecretKey secretKey, Base64URL base64URL, Base64URL base64URL2, Base64URL base64URL3, Base64URL base64URL4) throws NoSuchPaddingException, NoSuchAlgorithmException, JOSEException, InvalidKeyException {
        ECDH.AlgorithmMode algorithmModeResolveAlgorithmMode = ECDH.resolveAlgorithmMode(JWEHeaderValidation.getAlgorithmAndEnsureNotNull(jWEHeader));
        getConcatKDF().getJCAContext().setProvider(getJCAContext().getMACProvider());
        SecretKey secretKeyDeriveSharedKey = ECDH.deriveSharedKey(jWEHeader, secretKey, getConcatKDF());
        if (!algorithmModeResolveAlgorithmMode.equals(ECDH.AlgorithmMode.DIRECT)) {
            if (!algorithmModeResolveAlgorithmMode.equals(ECDH.AlgorithmMode.KW)) {
                throw new JOSEException("Unexpected JWE ECDH algorithm mode: " + algorithmModeResolveAlgorithmMode);
            }
            if (base64URL == null) {
                throw new JOSEException("Missing JWE encrypted key");
            }
            secretKeyDeriveSharedKey = AESKW.unwrapCEK(secretKeyDeriveSharedKey, base64URL.decode(), getJCAContext().getKeyEncryptionProvider());
        }
        return ContentCryptoProvider.decrypt(jWEHeader, bArr, base64URL, base64URL2, base64URL3, base64URL4, secretKeyDeriveSharedKey, getJCAContext());
    }
}
