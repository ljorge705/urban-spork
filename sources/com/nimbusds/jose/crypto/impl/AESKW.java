package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.util.KeyUtils;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class AESKW {
    public static byte[] wrapCEK(SecretKey secretKey, SecretKey secretKey2, Provider provider) throws NoSuchPaddingException, NoSuchAlgorithmException, JOSEException, InvalidKeyException {
        Cipher cipher;
        try {
            if (provider != null) {
                cipher = Cipher.getInstance("AESWrap", provider);
            } else {
                cipher = Cipher.getInstance("AESWrap");
            }
            cipher.init(3, secretKey2);
            return cipher.wrap(secretKey);
        } catch (InvalidKeyException | NoSuchAlgorithmException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new JOSEException("Couldn't wrap AES key: " + e.getMessage(), e);
        }
    }

    public static SecretKey unwrapCEK(SecretKey secretKey, byte[] bArr, Provider provider) throws NoSuchPaddingException, NoSuchAlgorithmException, JOSEException, InvalidKeyException {
        Cipher cipher;
        try {
            if (provider != null) {
                cipher = Cipher.getInstance("AESWrap", provider);
            } else {
                cipher = Cipher.getInstance("AESWrap");
            }
            cipher.init(4, KeyUtils.toAESKey(secretKey));
            return (SecretKey) cipher.unwrap(bArr, CipherStorageKeystoreAesCbc.ALGORITHM_AES, 3);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new JOSEException("Couldn't unwrap AES key: " + e.getMessage(), e);
        }
    }

    private AESKW() {
    }
}
