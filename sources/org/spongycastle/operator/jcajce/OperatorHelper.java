package org.spongycastle.operator.jcajce;

import com.oblador.keychain.cipherStorage.CipherStorageKeystoreAesCbc;
import com.oblador.keychain.cipherStorage.CipherStorageKeystoreRsaEcb;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PSSParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.jmrtd.lds.SignedDataUtil;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.bsi.BSIObjectIdentifiers;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.eac.EACObjectIdentifiers;
import org.spongycastle.asn1.kisa.KISAObjectIdentifiers;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.ntt.NTTObjectIdentifiers;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.RSASSAPSSparams;
import org.spongycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.jcajce.util.AlgorithmParametersUtils;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jcajce.util.MessageDigestUtils;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.util.Integers;

/* loaded from: classes7.dex */
class OperatorHelper {
    private static final Map asymmetricWrapperAlgNames;
    private static final Map oids;
    private static final Map symmetricKeyAlgNames;
    private static final Map symmetricWrapperAlgNames;
    private static final Map symmetricWrapperKeySizes;
    private JcaJceHelper helper;

    static {
        HashMap map = new HashMap();
        oids = map;
        HashMap map2 = new HashMap();
        asymmetricWrapperAlgNames = map2;
        HashMap map3 = new HashMap();
        symmetricWrapperAlgNames = map3;
        HashMap map4 = new HashMap();
        symmetricKeyAlgNames = map4;
        HashMap map5 = new HashMap();
        symmetricWrapperKeySizes = map5;
        map.put(new ASN1ObjectIdentifier(SignedDataUtil.PKCS1_SHA1_WITH_RSA_OID), "SHA1WITHRSA");
        map.put(PKCSObjectIdentifiers.sha224WithRSAEncryption, "SHA224WITHRSA");
        map.put(PKCSObjectIdentifiers.sha256WithRSAEncryption, "SHA256WITHRSA");
        map.put(PKCSObjectIdentifiers.sha384WithRSAEncryption, "SHA384WITHRSA");
        map.put(PKCSObjectIdentifiers.sha512WithRSAEncryption, "SHA512WITHRSA");
        map.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_94, "GOST3411WITHGOST3410");
        map.put(CryptoProObjectIdentifiers.gostR3411_94_with_gostR3410_2001, "GOST3411WITHECGOST3410");
        map.put(BSIObjectIdentifiers.ecdsa_plain_SHA1, "SHA1WITHPLAIN-ECDSA");
        map.put(BSIObjectIdentifiers.ecdsa_plain_SHA224, "SHA224WITHPLAIN-ECDSA");
        map.put(BSIObjectIdentifiers.ecdsa_plain_SHA256, "SHA256WITHPLAIN-ECDSA");
        map.put(BSIObjectIdentifiers.ecdsa_plain_SHA384, "SHA384WITHPLAIN-ECDSA");
        map.put(BSIObjectIdentifiers.ecdsa_plain_SHA512, "SHA512WITHPLAIN-ECDSA");
        map.put(BSIObjectIdentifiers.ecdsa_plain_RIPEMD160, "RIPEMD160WITHPLAIN-ECDSA");
        map.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_1, "SHA1WITHCVC-ECDSA");
        map.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_224, "SHA224WITHCVC-ECDSA");
        map.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_256, "SHA256WITHCVC-ECDSA");
        map.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_384, "SHA384WITHCVC-ECDSA");
        map.put(EACObjectIdentifiers.id_TA_ECDSA_SHA_512, "SHA512WITHCVC-ECDSA");
        map.put(new ASN1ObjectIdentifier(SignedDataUtil.PKCS1_MD5_WITH_RSA_OID), "MD5WITHRSA");
        map.put(new ASN1ObjectIdentifier(SignedDataUtil.PKCS1_MD2_WITH_RSA_OID), "MD2WITHRSA");
        map.put(new ASN1ObjectIdentifier("1.2.840.10040.4.3"), "SHA1WITHDSA");
        map.put(X9ObjectIdentifiers.ecdsa_with_SHA1, "SHA1WITHECDSA");
        map.put(X9ObjectIdentifiers.ecdsa_with_SHA224, "SHA224WITHECDSA");
        map.put(X9ObjectIdentifiers.ecdsa_with_SHA256, "SHA256WITHECDSA");
        map.put(X9ObjectIdentifiers.ecdsa_with_SHA384, "SHA384WITHECDSA");
        map.put(X9ObjectIdentifiers.ecdsa_with_SHA512, "SHA512WITHECDSA");
        map.put(OIWObjectIdentifiers.sha1WithRSA, "SHA1WITHRSA");
        map.put(OIWObjectIdentifiers.dsaWithSHA1, "SHA1WITHDSA");
        map.put(NISTObjectIdentifiers.dsa_with_sha224, "SHA224WITHDSA");
        map.put(NISTObjectIdentifiers.dsa_with_sha256, "SHA256WITHDSA");
        map.put(OIWObjectIdentifiers.idSHA1, "SHA-1");
        map.put(NISTObjectIdentifiers.id_sha224, "SHA-224");
        map.put(NISTObjectIdentifiers.id_sha256, "SHA-256");
        map.put(NISTObjectIdentifiers.id_sha384, "SHA-384");
        map.put(NISTObjectIdentifiers.id_sha512, "SHA-512");
        map.put(TeleTrusTObjectIdentifiers.ripemd128, "RIPEMD128");
        map.put(TeleTrusTObjectIdentifiers.ripemd160, "RIPEMD160");
        map.put(TeleTrusTObjectIdentifiers.ripemd256, "RIPEMD256");
        map2.put(PKCSObjectIdentifiers.rsaEncryption, CipherStorageKeystoreRsaEcb.TRANSFORMATION_RSA_ECB_PKCS1);
        map3.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap, "DESEDEWrap");
        map3.put(PKCSObjectIdentifiers.id_alg_CMSRC2wrap, "RC2Wrap");
        map3.put(NISTObjectIdentifiers.id_aes128_wrap, "AESWrap");
        map3.put(NISTObjectIdentifiers.id_aes192_wrap, "AESWrap");
        map3.put(NISTObjectIdentifiers.id_aes256_wrap, "AESWrap");
        map3.put(NTTObjectIdentifiers.id_camellia128_wrap, "CamelliaWrap");
        map3.put(NTTObjectIdentifiers.id_camellia192_wrap, "CamelliaWrap");
        map3.put(NTTObjectIdentifiers.id_camellia256_wrap, "CamelliaWrap");
        map3.put(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap, "SEEDWrap");
        map3.put(PKCSObjectIdentifiers.des_EDE3_CBC, "DESede");
        map5.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap, Integers.valueOf(192));
        map5.put(NISTObjectIdentifiers.id_aes128_wrap, Integers.valueOf(128));
        map5.put(NISTObjectIdentifiers.id_aes192_wrap, Integers.valueOf(192));
        map5.put(NISTObjectIdentifiers.id_aes256_wrap, Integers.valueOf(256));
        map5.put(NTTObjectIdentifiers.id_camellia128_wrap, Integers.valueOf(128));
        map5.put(NTTObjectIdentifiers.id_camellia192_wrap, Integers.valueOf(192));
        map5.put(NTTObjectIdentifiers.id_camellia256_wrap, Integers.valueOf(256));
        map5.put(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap, Integers.valueOf(128));
        map5.put(PKCSObjectIdentifiers.des_EDE3_CBC, Integers.valueOf(192));
        map4.put(NISTObjectIdentifiers.aes, CipherStorageKeystoreAesCbc.ALGORITHM_AES);
        map4.put(NISTObjectIdentifiers.id_aes128_CBC, CipherStorageKeystoreAesCbc.ALGORITHM_AES);
        map4.put(NISTObjectIdentifiers.id_aes192_CBC, CipherStorageKeystoreAesCbc.ALGORITHM_AES);
        map4.put(NISTObjectIdentifiers.id_aes256_CBC, CipherStorageKeystoreAesCbc.ALGORITHM_AES);
        map4.put(PKCSObjectIdentifiers.des_EDE3_CBC, "DESede");
        map4.put(PKCSObjectIdentifiers.RC2_CBC, "RC2");
    }

    OperatorHelper(JcaJceHelper jcaJceHelper) {
        this.helper = jcaJceHelper;
    }

    String getWrappingAlgorithmName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (String) symmetricWrapperAlgNames.get(aSN1ObjectIdentifier);
    }

    int getKeySizeInBits(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return ((Integer) symmetricWrapperKeySizes.get(aSN1ObjectIdentifier)).intValue();
    }

    Cipher createAsymmetricWrapper(ASN1ObjectIdentifier aSN1ObjectIdentifier, Map map) throws OperatorCreationException {
        try {
            String str = !map.isEmpty() ? (String) map.get(aSN1ObjectIdentifier) : null;
            if (str == null) {
                str = (String) asymmetricWrapperAlgNames.get(aSN1ObjectIdentifier);
            }
            if (str != null) {
                try {
                    return this.helper.createCipher(str);
                } catch (NoSuchAlgorithmException unused) {
                    if (str.equals(CipherStorageKeystoreRsaEcb.TRANSFORMATION_RSA_ECB_PKCS1)) {
                        try {
                            return this.helper.createCipher("RSA/NONE/PKCS1Padding");
                        } catch (NoSuchAlgorithmException unused2) {
                        }
                    }
                }
            }
            return this.helper.createCipher(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e) {
            throw new OperatorCreationException("cannot create cipher: " + e.getMessage(), e);
        }
    }

    Cipher createSymmetricWrapper(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws OperatorCreationException {
        try {
            String str = (String) symmetricWrapperAlgNames.get(aSN1ObjectIdentifier);
            if (str != null) {
                try {
                    return this.helper.createCipher(str);
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return this.helper.createCipher(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e) {
            throw new OperatorCreationException("cannot create cipher: " + e.getMessage(), e);
        }
    }

    AlgorithmParameters createAlgorithmParameters(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException, IOException {
        if (algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.rsaEncryption)) {
            return null;
        }
        try {
            AlgorithmParameters algorithmParametersCreateAlgorithmParameters = this.helper.createAlgorithmParameters(algorithmIdentifier.getAlgorithm().getId());
            try {
                algorithmParametersCreateAlgorithmParameters.init(algorithmIdentifier.getParameters().toASN1Primitive().getEncoded());
                return algorithmParametersCreateAlgorithmParameters;
            } catch (IOException e) {
                throw new OperatorCreationException("cannot initialise algorithm parameters: " + e.getMessage(), e);
            }
        } catch (NoSuchAlgorithmException unused) {
            return null;
        } catch (NoSuchProviderException e2) {
            throw new OperatorCreationException("cannot create algorithm parameters: " + e2.getMessage(), e2);
        }
    }

    MessageDigest createDigest(AlgorithmIdentifier algorithmIdentifier) throws GeneralSecurityException {
        try {
            return this.helper.createDigest(MessageDigestUtils.getDigestName(algorithmIdentifier.getAlgorithm()));
        } catch (NoSuchAlgorithmException e) {
            Map map = oids;
            if (map.get(algorithmIdentifier.getAlgorithm()) != null) {
                return this.helper.createDigest((String) map.get(algorithmIdentifier.getAlgorithm()));
            }
            throw e;
        }
    }

    Signature createSignature(AlgorithmIdentifier algorithmIdentifier) throws GeneralSecurityException {
        try {
            return this.helper.createSignature(getSignatureName(algorithmIdentifier));
        } catch (NoSuchAlgorithmException e) {
            Map map = oids;
            if (map.get(algorithmIdentifier.getAlgorithm()) != null) {
                return this.helper.createSignature((String) map.get(algorithmIdentifier.getAlgorithm()));
            }
            throw e;
        }
    }

    public Signature createRawSignature(AlgorithmIdentifier algorithmIdentifier) throws InvalidAlgorithmParameterException {
        try {
            String signatureName = getSignatureName(algorithmIdentifier);
            String str = "NONE" + signatureName.substring(signatureName.indexOf("WITH"));
            Signature signatureCreateSignature = this.helper.createSignature(str);
            if (algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.id_RSASSA_PSS)) {
                AlgorithmParameters algorithmParametersCreateAlgorithmParameters = this.helper.createAlgorithmParameters(str);
                AlgorithmParametersUtils.loadParameters(algorithmParametersCreateAlgorithmParameters, algorithmIdentifier.getParameters());
                signatureCreateSignature.setParameter((PSSParameterSpec) algorithmParametersCreateAlgorithmParameters.getParameterSpec(PSSParameterSpec.class));
            }
            return signatureCreateSignature;
        } catch (Exception unused) {
            return null;
        }
    }

    private static String getSignatureName(AlgorithmIdentifier algorithmIdentifier) {
        ASN1Encodable parameters = algorithmIdentifier.getParameters();
        if (parameters != null && !DERNull.INSTANCE.equals(parameters) && algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.id_RSASSA_PSS)) {
            return getDigestName(RSASSAPSSparams.getInstance(parameters).getHashAlgorithm().getAlgorithm()) + "WITHRSAANDMGF1";
        }
        Map map = oids;
        if (map.containsKey(algorithmIdentifier.getAlgorithm())) {
            return (String) map.get(algorithmIdentifier.getAlgorithm());
        }
        return algorithmIdentifier.getAlgorithm().getId();
    }

    private static String getDigestName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String digestName = MessageDigestUtils.getDigestName(aSN1ObjectIdentifier);
        int iIndexOf = digestName.indexOf(45);
        if (iIndexOf > 0 && !digestName.startsWith("SHA3")) {
            return digestName.substring(0, iIndexOf) + digestName.substring(iIndexOf + 1);
        }
        return MessageDigestUtils.getDigestName(aSN1ObjectIdentifier);
    }

    public X509Certificate convertCertificate(X509CertificateHolder x509CertificateHolder) throws CertificateException {
        try {
            return (X509Certificate) this.helper.createCertificateFactory("X.509").generateCertificate(new ByteArrayInputStream(x509CertificateHolder.getEncoded()));
        } catch (IOException e) {
            throw new OpCertificateException("cannot get encoded form of certificate: " + e.getMessage(), e);
        } catch (NoSuchProviderException e2) {
            throw new OpCertificateException("cannot find factory provider: " + e2.getMessage(), e2);
        }
    }

    public PublicKey convertPublicKey(SubjectPublicKeyInfo subjectPublicKeyInfo) throws OperatorCreationException {
        try {
            return this.helper.createKeyFactory(subjectPublicKeyInfo.getAlgorithm().getAlgorithm().getId()).generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded()));
        } catch (IOException e) {
            throw new OperatorCreationException("cannot get encoded form of key: " + e.getMessage(), e);
        } catch (NoSuchAlgorithmException e2) {
            throw new OperatorCreationException("cannot create key factory: " + e2.getMessage(), e2);
        } catch (NoSuchProviderException e3) {
            throw new OperatorCreationException("cannot find factory provider: " + e3.getMessage(), e3);
        } catch (InvalidKeySpecException e4) {
            throw new OperatorCreationException("cannot create key factory: " + e4.getMessage(), e4);
        }
    }

    private static class OpCertificateException extends CertificateException {
        private Throwable cause;

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }

        public OpCertificateException(String str, Throwable th) {
            super(str);
            this.cause = th;
        }
    }

    String getKeyAlgorithmName(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = (String) symmetricKeyAlgNames.get(aSN1ObjectIdentifier);
        return str != null ? str : aSN1ObjectIdentifier.getId();
    }
}
