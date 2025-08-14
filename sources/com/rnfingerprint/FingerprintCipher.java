package com.rnfingerprint;

import android.security.keystore.KeyGenParameterSpec;
import com.oblador.keychain.cipherStorage.CipherStorageBase;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

/* loaded from: classes6.dex */
public class FingerprintCipher {
    private static final String CIPHER_ALGO = "AES/CBC/PKCS7Padding";
    private static final String KEY_NAME = "example_key";
    private Cipher cipher;

    public Cipher getCipher() throws NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException {
        Cipher cipher = this.cipher;
        if (cipher != null) {
            return cipher;
        }
        try {
            KeyStore keyStoreGenerateKey = generateKey();
            this.cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            keyStoreGenerateKey.load(null);
            this.cipher.init(1, keyStoreGenerateKey.getKey(KEY_NAME, null));
        } catch (Exception unused) {
        }
        return this.cipher;
    }

    private KeyStore generateKey() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(CipherStorageBase.KEYSTORE_TYPE);
        KeyGenerator keyGenerator = KeyGenerator.getInstance(CipherStorageKeystoreAesCbc.ALGORITHM_AES, CipherStorageBase.KEYSTORE_TYPE);
        keyStore.load(null);
        keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME, 3).setBlockModes(CipherStorageKeystoreAesCbc.BLOCK_MODE_CBC).setUserAuthenticationRequired(true).setEncryptionPaddings(CipherStorageKeystoreAesCbc.PADDING_PKCS7).build());
        keyGenerator.generateKey();
        return keyStore;
    }
}
