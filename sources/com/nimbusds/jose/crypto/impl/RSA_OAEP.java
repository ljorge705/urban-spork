package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.JOSEException;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class RSA_OAEP {
    private static final String RSA_OEAP_JCA_ALG = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";

    public static byte[] encryptCEK(RSAPublicKey rSAPublicKey, SecretKey secretKey, Provider provider) throws JOSEException, InvalidKeyException {
        try {
            Cipher cipherHelper = CipherHelper.getInstance(RSA_OEAP_JCA_ALG, provider);
            cipherHelper.init(1, rSAPublicKey, new SecureRandom());
            return cipherHelper.doFinal(secretKey.getEncoded());
        } catch (IllegalBlockSizeException e) {
            throw new JOSEException("RSA block size exception: The RSA key is too short, try a longer one", e);
        } catch (Exception e2) {
            throw new JOSEException(e2.getMessage(), e2);
        }
    }

    public static SecretKey decryptCEK(PrivateKey privateKey, byte[] bArr, Provider provider) throws JOSEException, InvalidKeyException {
        try {
            Cipher cipherHelper = CipherHelper.getInstance(RSA_OEAP_JCA_ALG, provider);
            cipherHelper.init(2, privateKey);
            return new SecretKeySpec(cipherHelper.doFinal(bArr), CipherStorageKeystoreAesCbc.ALGORITHM_AES);
        } catch (Exception e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }

    private RSA_OAEP() {
    }
}
