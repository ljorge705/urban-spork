package com.nimbusds.jose.crypto.impl;

import com.google.crypto.tink.subtle.X25519;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWEHeader;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.OctetKeyPair;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.interfaces.ECPublicKey;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes2.dex */
public class ECDH {

    public enum AlgorithmMode {
        DIRECT,
        KW
    }

    public static AlgorithmMode resolveAlgorithmMode(JWEAlgorithm jWEAlgorithm) throws JOSEException {
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES)) {
            return AlgorithmMode.DIRECT;
        }
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES_A128KW) || jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES_A192KW) || jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES_A256KW)) {
            return AlgorithmMode.KW;
        }
        throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(jWEAlgorithm, ECDHCryptoProvider.SUPPORTED_ALGORITHMS));
    }

    public static int sharedKeyLength(JWEAlgorithm jWEAlgorithm, EncryptionMethod encryptionMethod) throws JOSEException {
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES)) {
            int iCekBitLength = encryptionMethod.cekBitLength();
            if (iCekBitLength != 0) {
                return iCekBitLength;
            }
            throw new JOSEException("Unsupported JWE encryption method " + encryptionMethod);
        }
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES_A128KW)) {
            return 128;
        }
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES_A192KW)) {
            return 192;
        }
        if (jWEAlgorithm.equals(JWEAlgorithm.ECDH_ES_A256KW)) {
            return 256;
        }
        throw new JOSEException(AlgorithmSupportMessage.unsupportedJWEAlgorithm(jWEAlgorithm, ECDHCryptoProvider.SUPPORTED_ALGORITHMS));
    }

    public static SecretKey deriveSharedSecret(ECPublicKey eCPublicKey, PrivateKey privateKey, Provider provider) throws IllegalStateException, NoSuchAlgorithmException, JOSEException, InvalidKeyException {
        KeyAgreement keyAgreement;
        try {
            if (provider != null) {
                keyAgreement = KeyAgreement.getInstance("ECDH", provider);
            } else {
                keyAgreement = KeyAgreement.getInstance("ECDH");
            }
            try {
                keyAgreement.init(privateKey);
                keyAgreement.doPhase(eCPublicKey, true);
                return new SecretKeySpec(keyAgreement.generateSecret(), CipherStorageKeystoreAesCbc.ALGORITHM_AES);
            } catch (InvalidKeyException e) {
                throw new JOSEException("Invalid key for ECDH key agreement: " + e.getMessage(), e);
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new JOSEException("Couldn't get an ECDH key agreement instance: " + e2.getMessage(), e2);
        }
    }

    public static SecretKey deriveSharedSecret(OctetKeyPair octetKeyPair, OctetKeyPair octetKeyPair2) throws JOSEException {
        if (octetKeyPair.isPrivate()) {
            throw new JOSEException("Expected public key but received OKP with 'd' value");
        }
        if (!Curve.X25519.equals(octetKeyPair.getCurve())) {
            throw new JOSEException("Expected public key OKP with crv=X25519");
        }
        if (!octetKeyPair2.isPrivate()) {
            throw new JOSEException("Expected private key but received OKP without 'd' value");
        }
        if (!Curve.X25519.equals(octetKeyPair2.getCurve())) {
            throw new JOSEException("Expected private key OKP with crv=X25519");
        }
        try {
            return new SecretKeySpec(X25519.computeSharedSecret(octetKeyPair2.getDecodedD(), octetKeyPair.getDecodedX()), CipherStorageKeystoreAesCbc.ALGORITHM_AES);
        } catch (InvalidKeyException e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }

    public static SecretKey deriveSharedKey(JWEHeader jWEHeader, SecretKey secretKey, ConcatKDF concatKDF) throws JOSEException {
        String name;
        int iSharedKeyLength = sharedKeyLength(jWEHeader.getAlgorithm(), jWEHeader.getEncryptionMethod());
        AlgorithmMode algorithmModeResolveAlgorithmMode = resolveAlgorithmMode(jWEHeader.getAlgorithm());
        if (algorithmModeResolveAlgorithmMode == AlgorithmMode.DIRECT) {
            name = jWEHeader.getEncryptionMethod().getName();
        } else if (algorithmModeResolveAlgorithmMode == AlgorithmMode.KW) {
            name = jWEHeader.getAlgorithm().getName();
        } else {
            throw new JOSEException("Unsupported JWE ECDH algorithm mode: " + algorithmModeResolveAlgorithmMode);
        }
        return concatKDF.deriveKey(secretKey, iSharedKeyLength, ConcatKDF.encodeDataWithLength(name.getBytes(StandardCharsets.US_ASCII)), ConcatKDF.encodeDataWithLength(jWEHeader.getAgreementPartyUInfo()), ConcatKDF.encodeDataWithLength(jWEHeader.getAgreementPartyVInfo()), ConcatKDF.encodeIntData(iSharedKeyLength), ConcatKDF.encodeNoData());
    }

    private ECDH() {
    }
}
