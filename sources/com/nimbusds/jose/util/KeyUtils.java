package com.nimbusds.jose.util;

import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import javax.crypto.SecretKey;

/* loaded from: classes2.dex */
public class KeyUtils {
    public static SecretKey toAESKey(final SecretKey secretKey) {
        return (secretKey == null || secretKey.getAlgorithm().equals(CipherStorageKeystoreAesCbc.ALGORITHM_AES)) ? secretKey : new SecretKey() { // from class: com.nimbusds.jose.util.KeyUtils.1
            @Override // java.security.Key
            public String getAlgorithm() {
                return CipherStorageKeystoreAesCbc.ALGORITHM_AES;
            }

            @Override // java.security.Key
            public String getFormat() {
                return secretKey.getFormat();
            }

            @Override // java.security.Key
            public byte[] getEncoded() {
                return secretKey.getEncoded();
            }
        };
    }

    private KeyUtils() {
    }
}
