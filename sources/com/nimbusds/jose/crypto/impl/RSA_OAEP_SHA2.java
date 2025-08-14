package com.nimbusds.jose.crypto.impl;

import com.nimbusds.jose.JOSEException;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.MGF1ParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
/* loaded from: classes2.dex */
public class RSA_OAEP_SHA2 {
    private static final String RSA_OEAP_256_JCA_ALG = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
    private static final String RSA_OEAP_384_JCA_ALG = "RSA/ECB/OAEPWithSHA-384AndMGF1Padding";
    private static final String RSA_OEAP_512_JCA_ALG = "RSA/ECB/OAEPWithSHA-512AndMGF1Padding";
    private static final String SHA_256_JCA_ALG = "SHA-256";
    private static final String SHA_384_JCA_ALG = "SHA-384";
    private static final String SHA_512_JCA_ALG = "SHA-512";

    public static byte[] encryptCEK(RSAPublicKey rSAPublicKey, SecretKey secretKey, int i, Provider provider) throws JOSEException, InvalidParameterSpecException, InvalidKeyException, InvalidAlgorithmParameterException {
        MGF1ParameterSpec mGF1ParameterSpec;
        String str;
        String str2;
        if (256 == i) {
            mGF1ParameterSpec = MGF1ParameterSpec.SHA256;
            str = RSA_OEAP_256_JCA_ALG;
            str2 = "SHA-256";
        } else if (384 == i) {
            mGF1ParameterSpec = MGF1ParameterSpec.SHA384;
            str = RSA_OEAP_384_JCA_ALG;
            str2 = "SHA-384";
        } else if (512 == i) {
            mGF1ParameterSpec = MGF1ParameterSpec.SHA512;
            str = RSA_OEAP_512_JCA_ALG;
            str2 = "SHA-512";
        } else {
            throw new JOSEException("Unsupported SHA-2 bit size: " + i);
        }
        try {
            AlgorithmParameters algorithmParametersHelper = AlgorithmParametersHelper.getInstance("OAEP", provider);
            algorithmParametersHelper.init(new OAEPParameterSpec(str2, "MGF1", mGF1ParameterSpec, PSource.PSpecified.DEFAULT));
            Cipher cipherHelper = CipherHelper.getInstance(str, provider);
            cipherHelper.init(1, rSAPublicKey, algorithmParametersHelper);
            return cipherHelper.doFinal(secretKey.getEncoded());
        } catch (IllegalBlockSizeException e) {
            throw new JOSEException("RSA block size exception: The RSA key is too short, use a longer one", e);
        } catch (Exception e2) {
            throw new JOSEException(e2.getMessage(), e2);
        }
    }

    public static SecretKey decryptCEK(PrivateKey privateKey, byte[] bArr, int i, Provider provider) throws JOSEException, InvalidParameterSpecException, InvalidKeyException, InvalidAlgorithmParameterException {
        MGF1ParameterSpec mGF1ParameterSpec;
        String str;
        String str2;
        if (256 == i) {
            mGF1ParameterSpec = MGF1ParameterSpec.SHA256;
            str = RSA_OEAP_256_JCA_ALG;
            str2 = "SHA-256";
        } else if (384 == i) {
            mGF1ParameterSpec = MGF1ParameterSpec.SHA384;
            str = RSA_OEAP_384_JCA_ALG;
            str2 = "SHA-384";
        } else if (512 == i) {
            mGF1ParameterSpec = MGF1ParameterSpec.SHA512;
            str = RSA_OEAP_512_JCA_ALG;
            str2 = "SHA-512";
        } else {
            throw new JOSEException("Unsupported SHA-2 bit size: " + i);
        }
        try {
            AlgorithmParameters algorithmParametersHelper = AlgorithmParametersHelper.getInstance("OAEP", provider);
            algorithmParametersHelper.init(new OAEPParameterSpec(str2, "MGF1", mGF1ParameterSpec, PSource.PSpecified.DEFAULT));
            Cipher cipherHelper = CipherHelper.getInstance(str, provider);
            cipherHelper.init(2, privateKey, algorithmParametersHelper);
            return new SecretKeySpec(cipherHelper.doFinal(bArr), CipherStorageKeystoreAesCbc.ALGORITHM_AES);
        } catch (Exception e) {
            throw new JOSEException(e.getMessage(), e);
        }
    }

    private RSA_OAEP_SHA2() {
    }
}
