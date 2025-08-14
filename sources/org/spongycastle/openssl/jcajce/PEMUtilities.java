package org.spongycastle.openssl.jcajce;

import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreRsaEcb;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.openssl.EncryptionException;
import org.spongycastle.openssl.PEMException;
import org.spongycastle.util.Integers;

/* loaded from: classes7.dex */
class PEMUtilities {
    private static final Map KEYSIZES;
    private static final Set PKCS5_SCHEME_1;
    private static final Set PKCS5_SCHEME_2;

    PEMUtilities() {
    }

    static {
        HashMap map = new HashMap();
        KEYSIZES = map;
        HashSet hashSet = new HashSet();
        PKCS5_SCHEME_1 = hashSet;
        HashSet hashSet2 = new HashSet();
        PKCS5_SCHEME_2 = hashSet2;
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD2AndDES_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD2AndRC2_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithMD5AndRC2_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC);
        hashSet.add(PKCSObjectIdentifiers.pbeWithSHA1AndRC2_CBC);
        hashSet2.add(PKCSObjectIdentifiers.id_PBES2);
        hashSet2.add(PKCSObjectIdentifiers.des_EDE3_CBC);
        hashSet2.add(NISTObjectIdentifiers.id_aes128_CBC);
        hashSet2.add(NISTObjectIdentifiers.id_aes192_CBC);
        hashSet2.add(NISTObjectIdentifiers.id_aes256_CBC);
        map.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), Integers.valueOf(192));
        map.put(NISTObjectIdentifiers.id_aes128_CBC.getId(), Integers.valueOf(128));
        map.put(NISTObjectIdentifiers.id_aes192_CBC.getId(), Integers.valueOf(192));
        map.put(NISTObjectIdentifiers.id_aes256_CBC.getId(), Integers.valueOf(256));
        map.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4.getId(), Integers.valueOf(128));
        map.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4, Integers.valueOf(40));
        map.put(PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC, Integers.valueOf(128));
        map.put(PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, Integers.valueOf(192));
        map.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC, Integers.valueOf(128));
        map.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC, Integers.valueOf(40));
    }

    static int getKeySize(String str) {
        Map map = KEYSIZES;
        if (!map.containsKey(str)) {
            throw new IllegalStateException("no key size for algorithm: " + str);
        }
        return ((Integer) map.get(str)).intValue();
    }

    static boolean isPKCS5Scheme1(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return PKCS5_SCHEME_1.contains(aSN1ObjectIdentifier);
    }

    static boolean isPKCS5Scheme2(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return PKCS5_SCHEME_2.contains(aSN1ObjectIdentifier);
    }

    public static boolean isPKCS12(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return aSN1ObjectIdentifier.getId().startsWith(PKCSObjectIdentifiers.pkcs_12PbeIds.getId());
    }

    public static SecretKey generateSecretKeyForPKCS5Scheme2(JcaJceHelper jcaJceHelper, String str, char[] cArr, byte[] bArr, int i) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchProviderException {
        return new SecretKeySpec(jcaJceHelper.createSecretKeyFactory("PBKDF2with8BIT").generateSecret(new PBEKeySpec(cArr, bArr, i, getKeySize(str))).getEncoded(), str);
    }

    static byte[] crypt(boolean z, JcaJceHelper jcaJceHelper, byte[] bArr, char[] cArr, String str, byte[] bArr2) throws PEMException, InvalidKeyException, InvalidAlgorithmParameterException {
        String str2;
        String str3;
        String str4;
        String str5;
        byte[] bArr3;
        String str6;
        SecretKey key;
        RC2ParameterSpec rC2ParameterSpec;
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
        if (str.endsWith("-CFB")) {
            str2 = "CFB";
            str3 = "NoPadding";
        } else {
            str2 = CipherStorageKeystoreAesCbc.BLOCK_MODE_CBC;
            str3 = "PKCS5Padding";
        }
        if (str.endsWith("-ECB") || "DES-EDE".equals(str) || "DES-EDE3".equals(str)) {
            str2 = CipherStorageKeystoreRsaEcb.BLOCK_MODE_ECB;
            ivParameterSpec = null;
        }
        AlgorithmParameterSpec algorithmParameterSpec = ivParameterSpec;
        if (str.endsWith("-OFB")) {
            str5 = "OFB";
            str4 = "NoPadding";
        } else {
            str4 = str3;
            str5 = str2;
        }
        int i = 1;
        if (str.startsWith("DES-EDE")) {
            str6 = "DESede";
            key = getKey(jcaJceHelper, cArr, "DESede", 24, bArr2, !str.startsWith("DES-EDE3"));
        } else if (str.startsWith("DES-")) {
            str6 = "DES";
            key = getKey(jcaJceHelper, cArr, "DES", 8, bArr2);
        } else if (str.startsWith("BF-")) {
            str6 = "Blowfish";
            key = getKey(jcaJceHelper, cArr, "Blowfish", 16, bArr2);
        } else {
            int i2 = 128;
            if (str.startsWith("RC2-")) {
                if (str.startsWith("RC2-40-")) {
                    i2 = 40;
                } else if (str.startsWith("RC2-64-")) {
                    i2 = 64;
                }
                str6 = "RC2";
                key = getKey(jcaJceHelper, cArr, "RC2", i2 / 8, bArr2);
                if (algorithmParameterSpec == null) {
                    rC2ParameterSpec = new RC2ParameterSpec(i2);
                } else {
                    rC2ParameterSpec = new RC2ParameterSpec(i2, bArr2);
                }
                algorithmParameterSpec = rC2ParameterSpec;
            } else if (str.startsWith("AES-")) {
                if (bArr2.length > 8) {
                    bArr3 = new byte[8];
                    System.arraycopy(bArr2, 0, bArr3, 0, 8);
                } else {
                    bArr3 = bArr2;
                }
                if (!str.startsWith("AES-128-")) {
                    if (str.startsWith("AES-192-")) {
                        i2 = 192;
                    } else {
                        if (!str.startsWith("AES-256-")) {
                            throw new EncryptionException("unknown AES encryption with private key");
                        }
                        i2 = 256;
                    }
                }
                str6 = CipherStorageKeystoreAesCbc.ALGORITHM_AES;
                key = getKey(jcaJceHelper, cArr, CipherStorageKeystoreAesCbc.ALGORITHM_AES, i2 / 8, bArr3);
            } else {
                throw new EncryptionException("unknown encryption with private key");
            }
        }
        try {
            Cipher cipherCreateCipher = jcaJceHelper.createCipher(str6 + "/" + str5 + "/" + str4);
            if (!z) {
                i = 2;
            }
            if (algorithmParameterSpec == null) {
                cipherCreateCipher.init(i, key);
            } else {
                cipherCreateCipher.init(i, key, algorithmParameterSpec);
            }
            return cipherCreateCipher.doFinal(bArr);
        } catch (Exception e) {
            throw new EncryptionException("exception using cipher - please check password and data.", e);
        }
    }

    private static SecretKey getKey(JcaJceHelper jcaJceHelper, char[] cArr, String str, int i, byte[] bArr) throws PEMException {
        return getKey(jcaJceHelper, cArr, str, i, bArr, false);
    }

    private static SecretKey getKey(JcaJceHelper jcaJceHelper, char[] cArr, String str, int i, byte[] bArr, boolean z) throws PEMException {
        try {
            byte[] encoded = jcaJceHelper.createSecretKeyFactory("PBKDF-OpenSSL").generateSecret(new PBEKeySpec(cArr, bArr, 1, i * 8)).getEncoded();
            if (z && encoded.length >= 24) {
                System.arraycopy(encoded, 0, encoded, 16, 8);
            }
            return new SecretKeySpec(encoded, str);
        } catch (GeneralSecurityException e) {
            throw new PEMException("Unable to create OpenSSL PBDKF: " + e.getMessage(), e);
        }
    }
}
