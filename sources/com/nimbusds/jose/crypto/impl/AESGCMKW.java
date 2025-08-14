package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.util.ByteUtils;
import com.nimbusds.jose.util.Container;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class AESGCMKW {
    public static AuthenticatedCipherText encryptCEK(SecretKey secretKey, Container<byte[]> container, SecretKey secretKey2, Provider provider) throws JOSEException {
        return AESGCM.encrypt(secretKey2, container, secretKey.getEncoded(), new byte[0], provider);
    }

    public static SecretKey decryptCEK(SecretKey secretKey, byte[] bArr, AuthenticatedCipherText authenticatedCipherText, int i, Provider provider) throws NoSuchPaddingException, NoSuchAlgorithmException, JOSEException, InvalidKeyException, InvalidAlgorithmParameterException {
        byte[] bArrDecrypt = AESGCM.decrypt(secretKey, bArr, authenticatedCipherText.getCipherText(), new byte[0], authenticatedCipherText.getAuthenticationTag(), provider);
        if (ByteUtils.safeBitLength(bArrDecrypt) != i) {
            throw new KeyLengthException("CEK key length mismatch: " + ByteUtils.safeBitLength(bArrDecrypt) + " != " + i);
        }
        return new SecretKeySpec(bArrDecrypt, CipherStorageKeystoreAesCbc.ALGORITHM_AES);
    }

    private AESGCMKW() {
    }
}
