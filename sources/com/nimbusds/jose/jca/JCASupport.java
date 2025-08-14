package com.nimbusds.jose.jca;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.crypto.impl.ECDSA;
import com.nimbusds.jose.crypto.impl.RSASSA;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreRsaEcb;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;

/* loaded from: classes2.dex */
public final class JCASupport {
    public static boolean isUnlimitedStrength() {
        try {
            return Cipher.getMaxAllowedKeyLength(CipherStorageKeystoreAesCbc.ALGORITHM_AES) >= 256;
        } catch (NoSuchAlgorithmException unused) {
            return false;
        }
    }

    public static boolean isSupported(Algorithm algorithm) {
        if (algorithm instanceof JWSAlgorithm) {
            return isSupported((JWSAlgorithm) algorithm);
        }
        if (algorithm instanceof JWEAlgorithm) {
            return isSupported((JWEAlgorithm) algorithm);
        }
        if (algorithm instanceof EncryptionMethod) {
            return isSupported((EncryptionMethod) algorithm);
        }
        throw new IllegalArgumentException("Unexpected algorithm class: " + algorithm.getClass().getCanonicalName());
    }

    public static boolean isSupported(Algorithm algorithm, Provider provider) {
        if (algorithm instanceof JWSAlgorithm) {
            return isSupported((JWSAlgorithm) algorithm, provider);
        }
        if (algorithm instanceof JWEAlgorithm) {
            return isSupported((JWEAlgorithm) algorithm, provider);
        }
        if (algorithm instanceof EncryptionMethod) {
            return isSupported((EncryptionMethod) algorithm, provider);
        }
        throw new IllegalArgumentException("Unexpected algorithm class: " + algorithm.getClass().getCanonicalName());
    }

    public static boolean isSupported(JWSAlgorithm jWSAlgorithm) {
        if (jWSAlgorithm.getName().equals(Algorithm.NONE.getName())) {
            return true;
        }
        for (Provider provider : Security.getProviders()) {
            if (isSupported(jWSAlgorithm, provider)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSupported(JWSAlgorithm jWSAlgorithm, Provider provider) throws NoSuchAlgorithmException {
        String str;
        if (JWSAlgorithm.Family.HMAC_SHA.contains(jWSAlgorithm)) {
            if (jWSAlgorithm.equals(JWSAlgorithm.HS256)) {
                str = "HMACSHA256";
            } else {
                if (!jWSAlgorithm.equals(JWSAlgorithm.HS384)) {
                    if (jWSAlgorithm.equals(JWSAlgorithm.HS512)) {
                        str = "HMACSHA512";
                    }
                    return false;
                }
                str = "HMACSHA384";
            }
            try {
                Mac.getInstance(str, provider);
                return true;
            } catch (NoSuchAlgorithmException unused) {
            }
        } else {
            if (JWSAlgorithm.Family.RSA.contains(jWSAlgorithm)) {
                try {
                    RSASSA.getSignerAndVerifier(jWSAlgorithm, provider);
                    return true;
                } catch (JOSEException unused2) {
                    return false;
                }
            }
            if (JWSAlgorithm.Family.EC.contains(jWSAlgorithm)) {
                try {
                    ECDSA.getSignerAndVerifier(jWSAlgorithm, provider);
                    return true;
                } catch (JOSEException unused3) {
                }
            }
            return false;
        }
    }

    public static boolean isSupported(JWEAlgorithm jWEAlgorithm) {
        for (Provider provider : Security.getProviders()) {
            if (isSupported(jWEAlgorithm, provider)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSupported(JWEAlgorithm jWEAlgorithm, Provider provider) throws NoSuchPaddingException, NoSuchAlgorithmException {
        String str;
        String str2;
        if (JWEAlgorithm.Family.RSA.contains(jWEAlgorithm)) {
            if (jWEAlgorithm.equals(JWEAlgorithm.RSA1_5)) {
                str2 = CipherStorageKeystoreRsaEcb.TRANSFORMATION_RSA_ECB_PKCS1;
            } else if (jWEAlgorithm.equals(JWEAlgorithm.RSA_OAEP)) {
                str2 = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";
            } else {
                if (!jWEAlgorithm.equals(JWEAlgorithm.RSA_OAEP_256)) {
                    if (jWEAlgorithm.equals(JWEAlgorithm.RSA_OAEP_512)) {
                        str2 = "RSA/ECB/OAEPWithSHA-512AndMGF1Padding";
                    }
                    return false;
                }
                str2 = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";
            }
            try {
                Cipher.getInstance(str2, provider);
                return true;
            } catch (NoSuchAlgorithmException | NoSuchPaddingException unused) {
            }
        } else {
            if (JWEAlgorithm.Family.AES_KW.contains(jWEAlgorithm)) {
                return provider.getService("Cipher", "AESWrap") != null;
            }
            if (JWEAlgorithm.Family.ECDH_ES.contains(jWEAlgorithm)) {
                return provider.getService("KeyAgreement", "ECDH") != null;
            }
            if (JWEAlgorithm.Family.AES_GCM_KW.contains(jWEAlgorithm)) {
                try {
                    Cipher.getInstance("AES/GCM/NoPadding", provider);
                    return true;
                } catch (NoSuchAlgorithmException | NoSuchPaddingException unused2) {
                    return false;
                }
            }
            if (JWEAlgorithm.Family.PBES2.contains(jWEAlgorithm)) {
                if (jWEAlgorithm.equals(JWEAlgorithm.PBES2_HS256_A128KW)) {
                    str = "HmacSHA256";
                } else {
                    str = jWEAlgorithm.equals(JWEAlgorithm.PBES2_HS384_A192KW) ? "HmacSHA384" : "HmacSHA512";
                }
                return provider.getService("KeyGenerator", str) != null;
            }
            return JWEAlgorithm.DIR.equals(jWEAlgorithm);
        }
    }

    public static boolean isSupported(EncryptionMethod encryptionMethod) {
        for (Provider provider : Security.getProviders()) {
            if (isSupported(encryptionMethod, provider)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSupported(EncryptionMethod encryptionMethod, Provider provider) throws NoSuchPaddingException, NoSuchAlgorithmException {
        String str;
        if (EncryptionMethod.Family.AES_CBC_HMAC_SHA.contains(encryptionMethod)) {
            try {
                Cipher.getInstance("AES/CBC/PKCS5Padding", provider);
                if (encryptionMethod.equals(EncryptionMethod.A128CBC_HS256)) {
                    str = "HmacSHA256";
                } else {
                    str = encryptionMethod.equals(EncryptionMethod.A192CBC_HS384) ? "HmacSHA384" : "HmacSHA512";
                }
                return provider.getService("KeyGenerator", str) != null;
            } catch (NoSuchAlgorithmException | NoSuchPaddingException unused) {
                return false;
            }
        }
        if (EncryptionMethod.Family.AES_GCM.contains(encryptionMethod)) {
            try {
                Cipher.getInstance("AES/GCM/NoPadding", provider);
                return true;
            } catch (NoSuchAlgorithmException | NoSuchPaddingException unused2) {
            }
        }
        return false;
    }

    private JCASupport() {
    }
}
