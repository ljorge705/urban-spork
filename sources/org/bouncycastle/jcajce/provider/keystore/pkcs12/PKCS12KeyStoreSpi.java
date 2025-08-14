package org.bouncycastle.jcajce.provider.keystore.pkcs12;

import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.ASN1BMPString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.GOST28147Parameters;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.EncryptionScheme;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.pkcs.SafeBag;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.AuthorityKeyIdentifier;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.SubjectKeyIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.internal.asn1.cms.GCMParameters;
import org.bouncycastle.internal.asn1.ntt.NTTObjectIdentifiers;
import org.bouncycastle.internal.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.jcajce.BCLoadStoreParameter;
import org.bouncycastle.jcajce.PKCS12Key;
import org.bouncycastle.jcajce.PKCS12StoreParameter;
import org.bouncycastle.jcajce.provider.keystore.util.AdaptingKeyStoreSpi;
import org.bouncycastle.jcajce.provider.keystore.util.ParameterUtil;
import org.bouncycastle.jcajce.spec.GOST28147ParameterSpec;
import org.bouncycastle.jcajce.spec.PBKDF2KeySpec;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jce.interfaces.BCKeyStore;
import org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.JDKPKCS12StoreParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Properties;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
import org.spongycastle.cms.CMSEnvelopedGenerator;

/* loaded from: classes4.dex */
public class PKCS12KeyStoreSpi extends KeyStoreSpi implements PKCSObjectIdentifiers, X509ObjectIdentifiers, BCKeyStore {
    static final int CERTIFICATE = 1;
    static final int KEY = 2;
    static final int KEY_PRIVATE = 0;
    static final int KEY_PUBLIC = 1;
    static final int KEY_SECRET = 2;
    private static final int MIN_ITERATIONS = 51200;
    static final int NULL = 0;
    static final String PKCS12_MAX_IT_COUNT_PROPERTY = "org.bouncycastle.pkcs12.max_it_count";
    private static final int SALT_SIZE = 20;
    static final int SEALED = 4;
    static final int SECRET = 3;
    private static final DefaultSecretKeyProvider keySizeProvider = new DefaultSecretKeyProvider();
    private ASN1ObjectIdentifier certAlgorithm;
    private CertificateFactory certFact;
    private IgnoresCaseHashtable certs;
    private ASN1ObjectIdentifier keyAlgorithm;
    private IgnoresCaseHashtable keys;
    private IgnoresCaseHashtable localIds;
    private final JcaJceHelper helper = new BCJcaJceHelper();
    private Hashtable chainCerts = new Hashtable();
    private Hashtable keyCerts = new Hashtable();
    protected SecureRandom random = CryptoServicesRegistrar.getSecureRandom();
    private AlgorithmIdentifier macAlgorithm = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
    private int itCount = 102400;
    private int saltLength = 20;

    public static class BCPKCS12KeyStore extends AdaptingKeyStoreSpi {
        public BCPKCS12KeyStore() {
            super(new BCJcaJceHelper(), new PKCS12KeyStoreSpi(new BCJcaJceHelper(), PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC));
        }
    }

    public static class BCPKCS12KeyStore3DES extends AdaptingKeyStoreSpi {
        public BCPKCS12KeyStore3DES() {
            super(new BCJcaJceHelper(), new PKCS12KeyStoreSpi(new BCJcaJceHelper(), PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC));
        }
    }

    public static class BCPKCS12KeyStoreAES256 extends AdaptingKeyStoreSpi {
        public BCPKCS12KeyStoreAES256() {
            super(new BCJcaJceHelper(), new PKCS12KeyStoreSpi(new BCJcaJceHelper(), NISTObjectIdentifiers.id_aes256_CBC, NISTObjectIdentifiers.id_aes128_CBC));
        }
    }

    public static class BCPKCS12KeyStoreAES256GCM extends AdaptingKeyStoreSpi {
        public BCPKCS12KeyStoreAES256GCM() {
            super(new BCJcaJceHelper(), new PKCS12KeyStoreSpi(new BCJcaJceHelper(), NISTObjectIdentifiers.id_aes256_GCM, NISTObjectIdentifiers.id_aes128_GCM));
        }
    }

    private class CertId {
        byte[] id;

        CertId(PublicKey publicKey) {
            this.id = PKCS12KeyStoreSpi.this.createSubjectKeyId(publicKey).getKeyIdentifier();
        }

        CertId(byte[] bArr) {
            this.id = bArr;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof CertId) {
                return Arrays.areEqual(this.id, ((CertId) obj).id);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.id);
        }
    }

    public static class DefPKCS12KeyStore extends AdaptingKeyStoreSpi {
        public DefPKCS12KeyStore() {
            super(new DefaultJcaJceHelper(), new PKCS12KeyStoreSpi(new DefaultJcaJceHelper(), PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC));
        }
    }

    public static class DefPKCS12KeyStore3DES extends AdaptingKeyStoreSpi {
        public DefPKCS12KeyStore3DES() {
            super(new DefaultJcaJceHelper(), new PKCS12KeyStoreSpi(new DefaultJcaJceHelper(), PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC));
        }
    }

    public static class DefPKCS12KeyStoreAES256 extends AdaptingKeyStoreSpi {
        public DefPKCS12KeyStoreAES256() {
            super(new BCJcaJceHelper(), new PKCS12KeyStoreSpi(new BCJcaJceHelper(), NISTObjectIdentifiers.id_aes256_CBC, NISTObjectIdentifiers.id_aes128_CBC));
        }
    }

    public static class DefPKCS12KeyStoreAES256GCM extends AdaptingKeyStoreSpi {
        public DefPKCS12KeyStoreAES256GCM() {
            super(new BCJcaJceHelper(), new PKCS12KeyStoreSpi(new BCJcaJceHelper(), NISTObjectIdentifiers.id_aes256_GCM, NISTObjectIdentifiers.id_aes128_GCM));
        }
    }

    private static class DefaultSecretKeyProvider {
        private final Map KEY_SIZES;

        DefaultSecretKeyProvider() {
            HashMap map = new HashMap();
            map.put(new ASN1ObjectIdentifier(CMSEnvelopedGenerator.CAST5_CBC), Integers.valueOf(128));
            map.put(PKCSObjectIdentifiers.des_EDE3_CBC, Integers.valueOf(192));
            map.put(NISTObjectIdentifiers.id_aes128_CBC, Integers.valueOf(128));
            map.put(NISTObjectIdentifiers.id_aes192_CBC, Integers.valueOf(192));
            map.put(NISTObjectIdentifiers.id_aes256_CBC, Integers.valueOf(256));
            map.put(NISTObjectIdentifiers.id_aes128_GCM, Integers.valueOf(128));
            map.put(NISTObjectIdentifiers.id_aes256_GCM, Integers.valueOf(256));
            map.put(NTTObjectIdentifiers.id_camellia128_cbc, Integers.valueOf(128));
            map.put(NTTObjectIdentifiers.id_camellia192_cbc, Integers.valueOf(192));
            map.put(NTTObjectIdentifiers.id_camellia256_cbc, Integers.valueOf(256));
            map.put(CryptoProObjectIdentifiers.gostR28147_gcfb, Integers.valueOf(256));
            this.KEY_SIZES = Collections.unmodifiableMap(map);
        }

        public int getKeySize(AlgorithmIdentifier algorithmIdentifier) {
            Integer num = (Integer) this.KEY_SIZES.get(algorithmIdentifier.getAlgorithm());
            if (num != null) {
                return num.intValue();
            }
            return -1;
        }
    }

    private static class IgnoresCaseHashtable {
        private Hashtable keys;
        private Hashtable orig;

        private IgnoresCaseHashtable() {
            this.orig = new Hashtable();
            this.keys = new Hashtable();
        }

        public Enumeration elements() {
            return this.orig.elements();
        }

        public Object get(String str) {
            String str2 = (String) this.keys.get(str == null ? null : Strings.toLowerCase(str));
            if (str2 == null) {
                return null;
            }
            return this.orig.get(str2);
        }

        public Enumeration keys() {
            return this.orig.keys();
        }

        public void put(String str, Object obj) {
            String lowerCase = str == null ? null : Strings.toLowerCase(str);
            String str2 = (String) this.keys.get(lowerCase);
            if (str2 != null) {
                this.orig.remove(str2);
            }
            this.keys.put(lowerCase, str);
            this.orig.put(str, obj);
        }

        public Object remove(String str) {
            String str2 = (String) this.keys.remove(str == null ? null : Strings.toLowerCase(str));
            if (str2 == null) {
                return null;
            }
            return this.orig.remove(str2);
        }

        public int size() {
            return this.orig.size();
        }
    }

    public PKCS12KeyStoreSpi(JcaJceHelper jcaJceHelper, ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1ObjectIdentifier aSN1ObjectIdentifier2) {
        this.keys = new IgnoresCaseHashtable();
        this.localIds = new IgnoresCaseHashtable();
        this.certs = new IgnoresCaseHashtable();
        this.keyAlgorithm = aSN1ObjectIdentifier;
        this.certAlgorithm = aSN1ObjectIdentifier2;
        try {
            this.certFact = jcaJceHelper.createCertificateFactory("X.509");
        } catch (Exception e) {
            throw new IllegalArgumentException("can't create cert factory - " + e.toString());
        }
    }

    private byte[] calculatePbeMac(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr, int i, char[] cArr, boolean z, byte[] bArr2) throws Exception {
        PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(bArr, i);
        Mac macCreateMac = this.helper.createMac(aSN1ObjectIdentifier.getId());
        macCreateMac.init(new PKCS12Key(cArr, z), pBEParameterSpec);
        macCreateMac.update(bArr2);
        return macCreateMac.doFinal();
    }

    private Cipher createCipher(int i, char[] cArr, AlgorithmIdentifier algorithmIdentifier) throws NoSuchPaddingException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException, IOException, NoSuchProviderException, InvalidAlgorithmParameterException {
        AlgorithmParameterSpec gOST28147ParameterSpec;
        PBES2Parameters pBES2Parameters = PBES2Parameters.getInstance(algorithmIdentifier.getParameters());
        PBKDF2Params pBKDF2Params = PBKDF2Params.getInstance(pBES2Parameters.getKeyDerivationFunc().getParameters());
        AlgorithmIdentifier algorithmIdentifier2 = AlgorithmIdentifier.getInstance(pBES2Parameters.getEncryptionScheme());
        SecretKeyFactory secretKeyFactoryCreateSecretKeyFactory = this.helper.createSecretKeyFactory(pBES2Parameters.getKeyDerivationFunc().getAlgorithm().getId());
        SecretKey secretKeyGenerateSecret = pBKDF2Params.isDefaultPrf() ? secretKeyFactoryCreateSecretKeyFactory.generateSecret(new PBEKeySpec(cArr, pBKDF2Params.getSalt(), validateIterationCount(pBKDF2Params.getIterationCount()), keySizeProvider.getKeySize(algorithmIdentifier2))) : secretKeyFactoryCreateSecretKeyFactory.generateSecret(new PBKDF2KeySpec(cArr, pBKDF2Params.getSalt(), validateIterationCount(pBKDF2Params.getIterationCount()), keySizeProvider.getKeySize(algorithmIdentifier2), pBKDF2Params.getPrf()));
        Cipher cipherCreateCipher = this.helper.createCipher(pBES2Parameters.getEncryptionScheme().getAlgorithm().getId());
        ASN1Encodable parameters = pBES2Parameters.getEncryptionScheme().getParameters();
        if (parameters instanceof ASN1OctetString) {
            gOST28147ParameterSpec = new IvParameterSpec(ASN1OctetString.getInstance(parameters).getOctets());
        } else {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(parameters);
            if (!(aSN1Sequence.getObjectAt(1) instanceof ASN1ObjectIdentifier)) {
                AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance(algorithmIdentifier2.getAlgorithm().getId(), BouncyCastleProvider.PROVIDER_NAME);
                try {
                    algorithmParameters.init(aSN1Sequence.getEncoded());
                    cipherCreateCipher.init(i, secretKeyGenerateSecret, algorithmParameters);
                    return cipherCreateCipher;
                } catch (IOException e) {
                    throw new InvalidKeySpecException(e.getMessage());
                }
            }
            GOST28147Parameters gOST28147Parameters = GOST28147Parameters.getInstance(parameters);
            gOST28147ParameterSpec = new GOST28147ParameterSpec(gOST28147Parameters.getEncryptionParamSet(), gOST28147Parameters.getIV());
        }
        cipherCreateCipher.init(i, secretKeyGenerateSecret, gOST28147ParameterSpec);
        return cipherCreateCipher;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.bouncycastle.asn1.pkcs.SafeBag createSafeBag(java.lang.String r8, java.security.cert.Certificate r9) throws java.security.cert.CertificateEncodingException {
        /*
            Method dump skipped, instructions count: 287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.createSafeBag(java.lang.String, java.security.cert.Certificate):org.bouncycastle.asn1.pkcs.SafeBag");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SubjectKeyIdentifier createSubjectKeyId(PublicKey publicKey) {
        try {
            return new SubjectKeyIdentifier(getDigest(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded())));
        } catch (Exception unused) {
            throw new RuntimeException("error creating key");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01be  */
    /* JADX WARN: Type inference failed for: r1v32 */
    /* JADX WARN: Type inference failed for: r1v33 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v40 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.util.Hashtable] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.util.Hashtable] */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r6v13, types: [java.lang.Object, java.security.cert.Certificate] */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.lang.Object, java.security.cert.Certificate] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void doStore(java.io.OutputStream r20, char[] r21, boolean r22) throws java.security.NoSuchAlgorithmException, java.io.IOException, java.security.InvalidKeyException, java.security.InvalidAlgorithmParameterException {
        /*
            Method dump skipped, instructions count: 1379
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.doStore(java.io.OutputStream, char[], boolean):void");
    }

    private ASN1Primitive getAlgParams(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_aes128_CBC) || aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_aes256_CBC)) {
            byte[] bArr = new byte[16];
            this.random.nextBytes(bArr);
            return new DEROctetString(bArr);
        }
        if (!aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_aes128_GCM) && !aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_aes256_GCM)) {
            throw new IllegalStateException("unknown encryption OID in getAlgParams()");
        }
        byte[] bArr2 = new byte[12];
        this.random.nextBytes(bArr2);
        return new GCMParameters(bArr2, 16).toASN1Primitive();
    }

    private static byte[] getDigest(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        Digest digestCreateSHA1 = DigestFactory.createSHA1();
        byte[] bArr = new byte[digestCreateSHA1.getDigestSize()];
        byte[] bytes = subjectPublicKeyInfo.getPublicKeyData().getBytes();
        digestCreateSHA1.update(bytes, 0, bytes.length);
        digestCreateSHA1.doFinal(bArr, 0);
        return bArr;
    }

    private static int getKeyLength(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return (aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_aes256_CBC) || aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_aes256_GCM)) ? 32 : 16;
    }

    private Set getUsedCertificateSet() {
        HashSet hashSet = new HashSet();
        Enumeration enumerationKeys = this.keys.keys();
        while (enumerationKeys.hasMoreElements()) {
            Certificate[] certificateArrEngineGetCertificateChain = engineGetCertificateChain((String) enumerationKeys.nextElement());
            for (int i = 0; i != certificateArrEngineGetCertificateChain.length; i++) {
                hashSet.add(certificateArrEngineGetCertificateChain[i]);
            }
        }
        Enumeration enumerationKeys2 = this.certs.keys();
        while (enumerationKeys2.hasMoreElements()) {
            hashSet.add(engineGetCertificate((String) enumerationKeys2.nextElement()));
        }
        return hashSet;
    }

    private static boolean isPBKDF2(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_aes256_CBC) || aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_aes256_GCM) || aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_aes128_CBC) || aSN1ObjectIdentifier.equals((ASN1Primitive) NISTObjectIdentifiers.id_aes128_GCM);
    }

    private void processKeyBag(SafeBag safeBag) throws IOException {
        PrivateKey privateKey = BouncyCastleProvider.getPrivateKey(PrivateKeyInfo.getInstance(safeBag.getBagValue()));
        PKCS12BagAttributeCarrier pKCS12BagAttributeCarrier = (PKCS12BagAttributeCarrier) privateKey;
        Enumeration objects = safeBag.getBagAttributes().getObjects();
        ASN1OctetString aSN1OctetString = null;
        String string = null;
        while (objects.hasMoreElements()) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(objects.nextElement());
            ASN1ObjectIdentifier aSN1ObjectIdentifier = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
            ASN1Set aSN1Set = ASN1Set.getInstance(aSN1Sequence.getObjectAt(1));
            if (aSN1Set.size() > 0) {
                ASN1Primitive aSN1Primitive = (ASN1Primitive) aSN1Set.getObjectAt(0);
                ASN1Encodable bagAttribute = pKCS12BagAttributeCarrier.getBagAttribute(aSN1ObjectIdentifier);
                if (bagAttribute == null) {
                    pKCS12BagAttributeCarrier.setBagAttribute(aSN1ObjectIdentifier, aSN1Primitive);
                } else if (!bagAttribute.toASN1Primitive().equals(aSN1Primitive)) {
                    throw new IOException("attempt to add existing attribute with different value");
                }
                if (aSN1ObjectIdentifier.equals((ASN1Primitive) pkcs_9_at_friendlyName)) {
                    string = ((ASN1BMPString) aSN1Primitive).getString();
                    this.keys.put(string, privateKey);
                } else if (aSN1ObjectIdentifier.equals((ASN1Primitive) pkcs_9_at_localKeyId)) {
                    aSN1OctetString = (ASN1OctetString) aSN1Primitive;
                }
            }
        }
        String str = new String(Hex.encode(aSN1OctetString.getOctets()));
        if (string == null) {
            this.keys.put(str, privateKey);
        } else {
            this.localIds.put(string, str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    private boolean processShroudedKeyBag(SafeBag safeBag, char[] cArr, boolean z) throws InvalidKeyException, IOException, InvalidAlgorithmParameterException {
        String string;
        ASN1OctetString aSN1OctetString;
        EncryptedPrivateKeyInfo encryptedPrivateKeyInfo = EncryptedPrivateKeyInfo.getInstance(safeBag.getBagValue());
        PrivateKey privateKeyUnwrapKey = unwrapKey(encryptedPrivateKeyInfo.getEncryptionAlgorithm(), encryptedPrivateKeyInfo.getEncryptedData(), cArr, z);
        ASN1OctetString aSN1OctetString2 = null;
        if (safeBag.getBagAttributes() != null) {
            Enumeration objects = safeBag.getBagAttributes().getObjects();
            string = null;
            ASN1OctetString aSN1OctetString3 = null;
            while (objects.hasMoreElements()) {
                ASN1Sequence aSN1Sequence = (ASN1Sequence) objects.nextElement();
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) aSN1Sequence.getObjectAt(0);
                ASN1Set aSN1Set = (ASN1Set) aSN1Sequence.getObjectAt(1);
                if (aSN1Set.size() > 0) {
                    ASN1Primitive aSN1Primitive = (ASN1Primitive) aSN1Set.getObjectAt(0);
                    aSN1OctetString = aSN1Primitive;
                    if (privateKeyUnwrapKey instanceof PKCS12BagAttributeCarrier) {
                        PKCS12BagAttributeCarrier pKCS12BagAttributeCarrier = (PKCS12BagAttributeCarrier) privateKeyUnwrapKey;
                        ASN1Encodable bagAttribute = pKCS12BagAttributeCarrier.getBagAttribute(aSN1ObjectIdentifier);
                        if (bagAttribute != null) {
                            boolean zEquals = bagAttribute.toASN1Primitive().equals(aSN1Primitive);
                            aSN1OctetString = aSN1Primitive;
                            if (!zEquals) {
                                throw new IOException("attempt to add existing attribute with different value");
                            }
                        } else {
                            pKCS12BagAttributeCarrier.setBagAttribute(aSN1ObjectIdentifier, aSN1Primitive);
                            aSN1OctetString = aSN1Primitive;
                        }
                    }
                } else {
                    aSN1OctetString = 0;
                }
                if (aSN1ObjectIdentifier.equals((ASN1Primitive) pkcs_9_at_friendlyName)) {
                    string = ((ASN1BMPString) aSN1OctetString).getString();
                    this.keys.put(string, privateKeyUnwrapKey);
                } else if (aSN1ObjectIdentifier.equals((ASN1Primitive) pkcs_9_at_localKeyId)) {
                    aSN1OctetString3 = aSN1OctetString;
                }
            }
            aSN1OctetString2 = aSN1OctetString3;
        } else {
            string = null;
        }
        if (aSN1OctetString2 == null) {
            this.keys.put("unmarked", privateKeyUnwrapKey);
            return true;
        }
        String str = new String(Hex.encode(aSN1OctetString2.getOctets()));
        if (string == null) {
            this.keys.put(str, privateKeyUnwrapKey);
        } else {
            this.localIds.put(string, str);
        }
        return false;
    }

    private int validateIterationCount(BigInteger bigInteger) {
        int iIntValueExact = BigIntegers.intValueExact(bigInteger);
        if (iIntValueExact < 0) {
            throw new IllegalStateException("negative iteration count found");
        }
        BigInteger bigIntegerAsBigInteger = Properties.asBigInteger(PKCS12_MAX_IT_COUNT_PROPERTY);
        if (bigIntegerAsBigInteger == null || BigIntegers.intValueExact(bigIntegerAsBigInteger) >= iIntValueExact) {
            return iIntValueExact;
        }
        throw new IllegalStateException("iteration count " + iIntValueExact + " greater than " + BigIntegers.intValueExact(bigIntegerAsBigInteger));
    }

    protected byte[] cryptData(boolean z, AlgorithmIdentifier algorithmIdentifier, char[] cArr, boolean z2, byte[] bArr) throws InvalidKeyException, IOException, InvalidAlgorithmParameterException {
        ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
        int i = z ? 1 : 2;
        if (!algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
            if (!algorithm.equals((ASN1Primitive) PKCSObjectIdentifiers.id_PBES2)) {
                throw new IOException("unknown PBE algorithm: " + algorithm);
            }
            try {
                return createCipher(i, cArr, algorithmIdentifier).doFinal(bArr);
            } catch (Exception e) {
                throw new IOException("exception decrypting data - " + e.toString());
            }
        }
        PKCS12PBEParams pKCS12PBEParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
        try {
            PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(pKCS12PBEParams.getIV(), BigIntegers.intValueExact(pKCS12PBEParams.getIterations()));
            PKCS12Key pKCS12Key = new PKCS12Key(cArr, z2);
            Cipher cipherCreateCipher = this.helper.createCipher(algorithm.getId());
            cipherCreateCipher.init(i, pKCS12Key, pBEParameterSpec);
            return cipherCreateCipher.doFinal(bArr);
        } catch (Exception e2) {
            throw new IOException("exception decrypting data - " + e2.toString());
        }
    }

    @Override // java.security.KeyStoreSpi
    public Enumeration engineAliases() {
        Hashtable hashtable = new Hashtable();
        Enumeration enumerationKeys = this.certs.keys();
        while (enumerationKeys.hasMoreElements()) {
            hashtable.put(enumerationKeys.nextElement(), "cert");
        }
        Enumeration enumerationKeys2 = this.keys.keys();
        while (enumerationKeys2.hasMoreElements()) {
            String str = (String) enumerationKeys2.nextElement();
            if (hashtable.get(str) == null) {
                hashtable.put(str, Constants.KEY_KEY);
            }
        }
        return hashtable.keys();
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineContainsAlias(String str) {
        return (this.certs.get(str) == null && this.keys.get(str) == null) ? false : true;
    }

    @Override // java.security.KeyStoreSpi
    public void engineDeleteEntry(String str) throws KeyStoreException {
        String str2;
        Certificate certificate;
        Certificate certificate2 = (Certificate) this.certs.remove(str);
        if (certificate2 != null) {
            this.chainCerts.remove(new CertId(certificate2.getPublicKey()));
        }
        if (((Key) this.keys.remove(str)) == null || (str2 = (String) this.localIds.remove(str)) == null || (certificate = (Certificate) this.keyCerts.remove(str2)) == null) {
            return;
        }
        this.chainCerts.remove(new CertId(certificate.getPublicKey()));
    }

    @Override // java.security.KeyStoreSpi
    public Certificate engineGetCertificate(String str) {
        if (str == null) {
            throw new IllegalArgumentException("null alias passed to getCertificate.");
        }
        Certificate certificate = (Certificate) this.certs.get(str);
        if (certificate != null) {
            return certificate;
        }
        String str2 = (String) this.localIds.get(str);
        return (Certificate) (str2 != null ? this.keyCerts.get(str2) : this.keyCerts.get(str));
    }

    @Override // java.security.KeyStoreSpi
    public String engineGetCertificateAlias(Certificate certificate) {
        Enumeration enumerationElements = this.certs.elements();
        Enumeration enumerationKeys = this.certs.keys();
        while (enumerationElements.hasMoreElements()) {
            Certificate certificate2 = (Certificate) enumerationElements.nextElement();
            String str = (String) enumerationKeys.nextElement();
            if (certificate2.equals(certificate)) {
                return str;
            }
        }
        Enumeration enumerationElements2 = this.keyCerts.elements();
        Enumeration enumerationKeys2 = this.keyCerts.keys();
        while (enumerationElements2.hasMoreElements()) {
            Certificate certificate3 = (Certificate) enumerationElements2.nextElement();
            String str2 = (String) enumerationKeys2.nextElement();
            if (certificate3.equals(certificate)) {
                return str2;
            }
        }
        return null;
    }

    @Override // java.security.KeyStoreSpi
    public Certificate[] engineGetCertificateChain(String str) {
        byte[] keyIdentifier;
        if (str == null) {
            throw new IllegalArgumentException("null alias passed to getCertificateChain.");
        }
        Certificate[] certificateArr = null;
        if (!engineIsKeyEntry(str)) {
            return null;
        }
        Certificate certificateEngineGetCertificate = engineGetCertificate(str);
        if (certificateEngineGetCertificate != null) {
            Vector vector = new Vector();
            while (certificateEngineGetCertificate != null) {
                X509Certificate x509Certificate = (X509Certificate) certificateEngineGetCertificate;
                byte[] extensionValue = x509Certificate.getExtensionValue(Extension.authorityKeyIdentifier.getId());
                Certificate certificate = (extensionValue == null || (keyIdentifier = AuthorityKeyIdentifier.getInstance(ASN1OctetString.getInstance(extensionValue).getOctets()).getKeyIdentifier()) == null) ? null : (Certificate) this.chainCerts.get(new CertId(keyIdentifier));
                if (certificate == null) {
                    Principal issuerDN = x509Certificate.getIssuerDN();
                    if (!issuerDN.equals(x509Certificate.getSubjectDN())) {
                        Enumeration enumerationKeys = this.chainCerts.keys();
                        while (true) {
                            if (!enumerationKeys.hasMoreElements()) {
                                break;
                            }
                            X509Certificate x509Certificate2 = (X509Certificate) this.chainCerts.get(enumerationKeys.nextElement());
                            if (x509Certificate2.getSubjectDN().equals(issuerDN)) {
                                try {
                                    x509Certificate.verify(x509Certificate2.getPublicKey());
                                    certificate = x509Certificate2;
                                    break;
                                } catch (Exception unused) {
                                    continue;
                                }
                            }
                        }
                    }
                }
                if (!vector.contains(certificateEngineGetCertificate)) {
                    vector.addElement(certificateEngineGetCertificate);
                    if (certificate != certificateEngineGetCertificate) {
                        certificateEngineGetCertificate = certificate;
                    }
                }
                certificateEngineGetCertificate = null;
            }
            int size = vector.size();
            certificateArr = new Certificate[size];
            for (int i = 0; i != size; i++) {
                certificateArr[i] = (Certificate) vector.elementAt(i);
            }
        }
        return certificateArr;
    }

    @Override // java.security.KeyStoreSpi
    public Date engineGetCreationDate(String str) {
        if (str == null) {
            throw new NullPointerException("alias == null");
        }
        if (this.keys.get(str) == null && this.certs.get(str) == null) {
            return null;
        }
        return new Date();
    }

    @Override // java.security.KeyStoreSpi
    public Key engineGetKey(String str, char[] cArr) throws NoSuchAlgorithmException, UnrecoverableKeyException {
        if (str != null) {
            return (Key) this.keys.get(str);
        }
        throw new IllegalArgumentException("null alias passed to getKey.");
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineIsCertificateEntry(String str) {
        return this.certs.get(str) != null && this.keys.get(str) == null;
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineIsKeyEntry(String str) {
        return this.keys.get(str) != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:114:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0365  */
    /* JADX WARN: Type inference failed for: r2v8, types: [org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.Object, java.security.cert.Certificate] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v45 */
    /* JADX WARN: Type inference failed for: r4v5, types: [org.bouncycastle.asn1.ASN1OctetString] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.String] */
    @Override // java.security.KeyStoreSpi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void engineLoad(java.io.InputStream r19, char[] r20) throws java.io.IOException, java.security.InvalidKeyException, java.security.cert.CertificateException, java.security.InvalidAlgorithmParameterException {
        /*
            Method dump skipped, instructions count: 1076
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.engineLoad(java.io.InputStream, char[]):void");
    }

    @Override // java.security.KeyStoreSpi
    public void engineLoad(KeyStore.LoadStoreParameter loadStoreParameter) throws NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException, InvalidAlgorithmParameterException {
        if (loadStoreParameter == null) {
            engineLoad(null, null);
        } else {
            if (!(loadStoreParameter instanceof BCLoadStoreParameter)) {
                throw new IllegalArgumentException("no support for 'param' of type " + loadStoreParameter.getClass().getName());
            }
            engineLoad(((BCLoadStoreParameter) loadStoreParameter).getInputStream(), ParameterUtil.extractPassword(loadStoreParameter));
        }
    }

    @Override // java.security.KeyStoreSpi
    public boolean engineProbe(InputStream inputStream) throws IOException {
        return false;
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetCertificateEntry(String str, Certificate certificate) throws KeyStoreException {
        if (this.keys.get(str) != null) {
            throw new KeyStoreException("There is a key entry with the name " + str + ".");
        }
        this.certs.put(str, certificate);
        this.chainCerts.put(new CertId(certificate.getPublicKey()), certificate);
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) throws KeyStoreException {
        boolean z = key instanceof PrivateKey;
        if (!z) {
            throw new KeyStoreException("PKCS12 does not support non-PrivateKeys");
        }
        if (z && certificateArr == null) {
            throw new KeyStoreException("no certificate chain for private key");
        }
        if (this.keys.get(str) != null) {
            engineDeleteEntry(str);
        }
        this.keys.put(str, key);
        if (certificateArr != null) {
            this.certs.put(str, certificateArr[0]);
            for (int i = 0; i != certificateArr.length; i++) {
                this.chainCerts.put(new CertId(certificateArr[i].getPublicKey()), certificateArr[i]);
            }
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) throws KeyStoreException {
        throw new RuntimeException("operation not supported");
    }

    @Override // java.security.KeyStoreSpi
    public int engineSize() {
        Hashtable hashtable = new Hashtable();
        Enumeration enumerationKeys = this.certs.keys();
        while (enumerationKeys.hasMoreElements()) {
            hashtable.put(enumerationKeys.nextElement(), "cert");
        }
        Enumeration enumerationKeys2 = this.keys.keys();
        while (enumerationKeys2.hasMoreElements()) {
            String str = (String) enumerationKeys2.nextElement();
            if (hashtable.get(str) == null) {
                hashtable.put(str, Constants.KEY_KEY);
            }
        }
        return hashtable.size();
    }

    @Override // java.security.KeyStoreSpi
    public void engineStore(OutputStream outputStream, char[] cArr) throws NoSuchAlgorithmException, IOException, InvalidKeyException, InvalidAlgorithmParameterException {
        doStore(outputStream, cArr, false);
    }

    @Override // java.security.KeyStoreSpi
    public void engineStore(KeyStore.LoadStoreParameter loadStoreParameter) throws NoSuchAlgorithmException, IOException, InvalidKeyException, CertificateException, InvalidAlgorithmParameterException {
        PKCS12StoreParameter pKCS12StoreParameter;
        char[] password;
        if (loadStoreParameter == null) {
            throw new IllegalArgumentException("'param' arg cannot be null");
        }
        boolean z = loadStoreParameter instanceof PKCS12StoreParameter;
        if (!z && !(loadStoreParameter instanceof JDKPKCS12StoreParameter)) {
            throw new IllegalArgumentException("No support for 'param' of type " + loadStoreParameter.getClass().getName());
        }
        if (z) {
            pKCS12StoreParameter = (PKCS12StoreParameter) loadStoreParameter;
        } else {
            JDKPKCS12StoreParameter jDKPKCS12StoreParameter = (JDKPKCS12StoreParameter) loadStoreParameter;
            pKCS12StoreParameter = new PKCS12StoreParameter(jDKPKCS12StoreParameter.getOutputStream(), loadStoreParameter.getProtectionParameter(), jDKPKCS12StoreParameter.isUseDEREncoding());
        }
        KeyStore.ProtectionParameter protectionParameter = loadStoreParameter.getProtectionParameter();
        if (protectionParameter == null) {
            password = null;
        } else {
            if (!(protectionParameter instanceof KeyStore.PasswordProtection)) {
                throw new IllegalArgumentException("No support for protection parameter of type " + protectionParameter.getClass().getName());
            }
            password = ((KeyStore.PasswordProtection) protectionParameter).getPassword();
        }
        doStore(pKCS12StoreParameter.getOutputStream(), password, pKCS12StoreParameter.isForDEREncoding());
    }

    @Override // org.bouncycastle.jce.interfaces.BCKeyStore
    public void setRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    protected PrivateKey unwrapKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr, char[] cArr, boolean z) throws InvalidKeyException, IOException, InvalidAlgorithmParameterException {
        ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
        try {
            if (!algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
                if (algorithm.equals((ASN1Primitive) PKCSObjectIdentifiers.id_PBES2)) {
                    return (PrivateKey) createCipher(4, cArr, algorithmIdentifier).unwrap(bArr, "", 2);
                }
                throw new IOException("exception unwrapping private key - cannot recognise: " + algorithm);
            }
            PKCS12PBEParams pKCS12PBEParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
            PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(pKCS12PBEParams.getIV(), validateIterationCount(pKCS12PBEParams.getIterations()));
            Cipher cipherCreateCipher = this.helper.createCipher(algorithm.getId());
            cipherCreateCipher.init(4, new PKCS12Key(cArr, z), pBEParameterSpec);
            return (PrivateKey) cipherCreateCipher.unwrap(bArr, "", 2);
        } catch (Exception e) {
            throw new IOException("exception unwrapping private key - " + e.toString());
        }
    }

    protected byte[] wrapKey(String str, Key key, PKCS12PBEParams pKCS12PBEParams, char[] cArr) throws InvalidKeyException, IOException, InvalidAlgorithmParameterException {
        PBEKeySpec pBEKeySpec = new PBEKeySpec(cArr);
        try {
            SecretKeyFactory secretKeyFactoryCreateSecretKeyFactory = this.helper.createSecretKeyFactory(str);
            PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(pKCS12PBEParams.getIV(), BigIntegers.intValueExact(pKCS12PBEParams.getIterations()));
            Cipher cipherCreateCipher = this.helper.createCipher(str);
            cipherCreateCipher.init(3, secretKeyFactoryCreateSecretKeyFactory.generateSecret(pBEKeySpec), pBEParameterSpec);
            return cipherCreateCipher.wrap(key);
        } catch (Exception e) {
            throw new IOException("exception encrypting data - " + e.toString());
        }
    }

    protected byte[] wrapKey(EncryptionScheme encryptionScheme, Key key, PBKDF2Params pBKDF2Params, char[] cArr) throws NoSuchAlgorithmException, IOException, InvalidKeyException, InvalidAlgorithmParameterException {
        PBEKeySpec pBEKeySpec = new PBEKeySpec(cArr, pBKDF2Params.getSalt(), BigIntegers.intValueExact(pBKDF2Params.getIterationCount()), BigIntegers.intValueExact(pBKDF2Params.getKeyLength()) * 8);
        try {
            SecretKeyFactory secretKeyFactoryCreateSecretKeyFactory = this.helper.createSecretKeyFactory("PBKDF2withHMacSHA256");
            Cipher cipherCreateCipher = this.helper.createCipher(encryptionScheme.getAlgorithm().getId());
            AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance(encryptionScheme.getAlgorithm().getId());
            algorithmParameters.init(encryptionScheme.getParameters().toASN1Primitive().getEncoded());
            cipherCreateCipher.init(3, secretKeyFactoryCreateSecretKeyFactory.generateSecret(pBEKeySpec), algorithmParameters);
            return cipherCreateCipher.wrap(key);
        } catch (Exception e) {
            throw new IOException("exception encrypting data - " + e.toString());
        }
    }
}
