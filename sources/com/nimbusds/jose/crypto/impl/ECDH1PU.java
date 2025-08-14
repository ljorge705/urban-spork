package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.crypto.impl.ECDH;
import com.nimbusds.jose.crypto.utils.ECChecks;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.OctetKeyPair;
import com.nimbusds.jose.util.Base64URL;
import com.nimbusds.jose.util.ByteUtils;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.nio.charset.StandardCharsets;
import java.security.Provider;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.util.Objects;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes2.dex */
public class ECDH1PU {
    public static ECDH.AlgorithmMode resolveAlgorithmMode(JWEAlgorithm jWEAlgorithm) throws JOSEException {
        Objects.requireNonNull(jWEAlgorithm, "The parameter \"alg\" must not be null");
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_1PU)) {
            return ECDH.AlgorithmMode.DIRECT;
        }
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_1PU_A128KW) || jWEAlgorithm.equals(JWEAlgorithm.ECDH_1PU_A192KW) || jWEAlgorithm.equals(JWEAlgorithm.ECDH_1PU_A256KW)) {
            return ECDH.AlgorithmMode.KW;
        }
        throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(jWEAlgorithm, ECDHCryptoProvider.SUPPORTED_ALGORITHMS));
    }

    public static int sharedKeyLength(JWEAlgorithm jWEAlgorithm, EncryptionMethod encryptionMethod) throws JOSEException {
        Objects.requireNonNull(jWEAlgorithm, "The parameter \"alg\" must not be null");
        Objects.requireNonNull(encryptionMethod, "The parameter \"enc\" must not be null");
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_1PU)) {
            int iCekBitLength = encryptionMethod.cekBitLength();
            if (iCekBitLength != 0) {
                return iCekBitLength;
            }
            throw new JOSEException("Unsupported JWE encryption method " + encryptionMethod);
        }
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_1PU_A128KW)) {
            return 128;
        }
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_1PU_A192KW)) {
            return 192;
        }
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_1PU_A256KW)) {
            return 256;
        }
        throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(jWEAlgorithm, ECDHCryptoProvider.SUPPORTED_ALGORITHMS));
    }

    public static SecretKey deriveSharedKey(JWEHeader jWEHeader, SecretKey secretKey, ConcatKDF concatKDF) throws JOSEException {
        String name;
        Objects.requireNonNull(jWEHeader, "The parameter \"header\" must not be null");
        Objects.requireNonNull(secretKey, "The parameter \"Z\" must not be null");
        Objects.requireNonNull(concatKDF, "The parameter \"concatKDF\" must not be null");
        int iSharedKeyLength = sharedKeyLength(jWEHeader.getAlgorithm(), jWEHeader.getEncryptionMethod());
        ECDH.AlgorithmMode algorithmModeResolveAlgorithmMode = resolveAlgorithmMode(jWEHeader.getAlgorithm());
        if (algorithmModeResolveAlgorithmMode == ECDH.AlgorithmMode.DIRECT) {
            name = jWEHeader.getEncryptionMethod().getName();
        } else if (algorithmModeResolveAlgorithmMode == ECDH.AlgorithmMode.KW) {
            name = jWEHeader.getAlgorithm().getName();
        } else {
            throw new JOSEException("Unsupported JWE ECDH algorithm mode: " + algorithmModeResolveAlgorithmMode);
        }
        return concatKDF.deriveKey(secretKey, iSharedKeyLength, ConcatKDF.encodeDataWithLength(name.getBytes(StandardCharsets.US_ASCII)), ConcatKDF.encodeDataWithLength(jWEHeader.getAgreementPartyUInfo()), ConcatKDF.encodeDataWithLength(jWEHeader.getAgreementPartyVInfo()), ConcatKDF.encodeIntData(iSharedKeyLength), ConcatKDF.encodeNoData());
    }

    public static SecretKey deriveSharedKey(JWEHeader jWEHeader, SecretKey secretKey, Base64URL base64URL, ConcatKDF concatKDF) throws JOSEException {
        String name;
        Objects.requireNonNull(jWEHeader, "The parameter \"header\" must not be null");
        Objects.requireNonNull(secretKey, "The parameter \"Z\" must not be null");
        Objects.requireNonNull(base64URL, "The parameter \"tag\" must not be null");
        Objects.requireNonNull(concatKDF, "The parameter \"concatKDF\" must not be null");
        int iSharedKeyLength = sharedKeyLength(jWEHeader.getAlgorithm(), jWEHeader.getEncryptionMethod());
        ECDH.AlgorithmMode algorithmModeResolveAlgorithmMode = resolveAlgorithmMode(jWEHeader.getAlgorithm());
        if (algorithmModeResolveAlgorithmMode == ECDH.AlgorithmMode.DIRECT) {
            name = jWEHeader.getEncryptionMethod().getName();
        } else if (algorithmModeResolveAlgorithmMode == ECDH.AlgorithmMode.KW) {
            name = jWEHeader.getAlgorithm().getName();
        } else {
            throw new JOSEException("Unsupported JWE ECDH algorithm mode: " + algorithmModeResolveAlgorithmMode);
        }
        return concatKDF.deriveKey(secretKey, iSharedKeyLength, ConcatKDF.encodeDataWithLength(name.getBytes(StandardCharsets.US_ASCII)), ConcatKDF.encodeDataWithLength(jWEHeader.getAgreementPartyUInfo()), ConcatKDF.encodeDataWithLength(jWEHeader.getAgreementPartyVInfo()), ConcatKDF.encodeIntData(iSharedKeyLength), ConcatKDF.encodeNoData(), ConcatKDF.encodeDataWithLength(base64URL));
    }

    public static SecretKey deriveZ(SecretKey secretKey, SecretKey secretKey2) {
        Objects.requireNonNull(secretKey, "The parameter \"Ze\" must not be null");
        Objects.requireNonNull(secretKey2, "The parameter \"Zs\" must not be null");
        byte[] bArrConcat = ByteUtils.concat(secretKey.getEncoded(), secretKey2.getEncoded());
        return new SecretKeySpec(bArrConcat, 0, bArrConcat.length, CipherStorageKeystoreAesCbc.ALGORITHM_AES);
    }

    public static SecretKey deriveSenderZ(ECPrivateKey eCPrivateKey, ECPublicKey eCPublicKey, ECPrivateKey eCPrivateKey2, Provider provider) throws JOSEException {
        validateSameCurve(eCPrivateKey, eCPublicKey);
        validateSameCurve(eCPrivateKey2, eCPublicKey);
        return deriveZ(ECDH.deriveSharedSecret(eCPublicKey, eCPrivateKey2, provider), ECDH.deriveSharedSecret(eCPublicKey, eCPrivateKey, provider));
    }

    public static SecretKey deriveSenderZ(OctetKeyPair octetKeyPair, OctetKeyPair octetKeyPair2, OctetKeyPair octetKeyPair3) throws JOSEException {
        validateSameCurve(octetKeyPair, octetKeyPair2);
        validateSameCurve(octetKeyPair3, octetKeyPair2);
        return deriveZ(ECDH.deriveSharedSecret(octetKeyPair2, octetKeyPair3), ECDH.deriveSharedSecret(octetKeyPair2, octetKeyPair));
    }

    public static SecretKey deriveRecipientZ(ECPrivateKey eCPrivateKey, ECPublicKey eCPublicKey, ECPublicKey eCPublicKey2, Provider provider) throws JOSEException {
        validateSameCurve(eCPrivateKey, eCPublicKey);
        validateSameCurve(eCPrivateKey, eCPublicKey2);
        return deriveZ(ECDH.deriveSharedSecret(eCPublicKey2, eCPrivateKey, provider), ECDH.deriveSharedSecret(eCPublicKey, eCPrivateKey, provider));
    }

    public static SecretKey deriveRecipientZ(OctetKeyPair octetKeyPair, OctetKeyPair octetKeyPair2, OctetKeyPair octetKeyPair3) throws JOSEException {
        validateSameCurve(octetKeyPair, octetKeyPair2);
        validateSameCurve(octetKeyPair, octetKeyPair3);
        return deriveZ(ECDH.deriveSharedSecret(octetKeyPair3, octetKeyPair), ECDH.deriveSharedSecret(octetKeyPair2, octetKeyPair));
    }

    public static void validateSameCurve(ECPrivateKey eCPrivateKey, ECPublicKey eCPublicKey) throws JOSEException {
        Objects.requireNonNull(eCPrivateKey, "The parameter \"privateKey\" must not be null");
        Objects.requireNonNull(eCPublicKey, "The parameter \"publicKey\" must not be null");
        if (!eCPrivateKey.getParams().getCurve().equals(eCPublicKey.getParams().getCurve())) {
            throw new JOSEException("Curve of public key does not match curve of private key");
        }
        if (!ECChecks.isPointOnCurve(eCPublicKey, eCPrivateKey)) {
            throw new JOSEException("Invalid public EC key: Point(s) not on the expected curve");
        }
    }

    public static void validateSameCurve(OctetKeyPair octetKeyPair, OctetKeyPair octetKeyPair2) throws JOSEException {
        Objects.requireNonNull(octetKeyPair, "The parameter \"privateKey\" must not be null");
        Objects.requireNonNull(octetKeyPair2, "The parameter \"publicKey\" must not be null");
        if (!octetKeyPair.isPrivate()) {
            throw new JOSEException("OKP private key should be a private key");
        }
        if (octetKeyPair2.isPrivate()) {
            throw new JOSEException("OKP public key should not be a private key");
        }
        if (!octetKeyPair2.getCurve().equals(Curve.X25519)) {
            throw new JOSEException("Only supports OctetKeyPairs with crv=X25519");
        }
        if (!octetKeyPair.getCurve().equals(octetKeyPair2.getCurve())) {
            throw new JOSEException("Curve of public key does not match curve of private key");
        }
    }

    private ECDH1PU() {
    }
}
