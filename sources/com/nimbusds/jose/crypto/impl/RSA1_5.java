package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.util.ByteUtils;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreRsaEcb;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class RSA1_5 {
    public static byte[] encryptCEK(RSAPublicKey rSAPublicKey, SecretKey secretKey, Provider provider) throws JOSEException, InvalidKeyException {
        try {
            Cipher cipherHelper = CipherHelper.getInstance(CipherStorageKeystoreRsaEcb.TRANSFORMATION_RSA_ECB_PKCS1, provider);
            cipherHelper.init(1, rSAPublicKey);
            return cipherHelper.doFinal(secretKey.getEncoded());
        } catch (IllegalBlockSizeException e) {
            throw new JOSEException("RSA block size exception: The RSA key is too short, use a longer one", e);
        } catch (Exception e2) {
            throw new JOSEException("Couldn't encrypt Content Encryption Key (CEK): " + e2.getMessage(), e2);
        }
    }

    public static SecretKey decryptCEK(PrivateKey privateKey, byte[] bArr, int i, Provider provider) throws BadPaddingException, IllegalBlockSizeException, JOSEException, InvalidKeyException {
        try {
            Cipher cipherHelper = CipherHelper.getInstance(CipherStorageKeystoreRsaEcb.TRANSFORMATION_RSA_ECB_PKCS1, provider);
            cipherHelper.init(2, privateKey);
            byte[] bArrDoFinal = cipherHelper.doFinal(bArr);
            if (ByteUtils.safeBitLength(bArrDoFinal) != i) {
                return null;
            }
            return new SecretKeySpec(bArrDoFinal, CipherStorageKeystoreAesCbc.ALGORITHM_AES);
        } catch (Exception e) {
            throw new JOSEException("Couldn't decrypt Content Encryption Key (CEK): " + e.getMessage(), e);
        }
    }

    private RSA1_5() {
    }
}
