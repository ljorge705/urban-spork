package org.spongycastle.pkcs.jcajce;

import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.bc.BCObjectIdentifiers;
import org.spongycastle.asn1.pkcs.EncryptionScheme;
import org.spongycastle.asn1.pkcs.KeyDerivationFunc;
import org.spongycastle.asn1.pkcs.PBES2Parameters;
import org.spongycastle.asn1.pkcs.PBKDF2Params;
import org.spongycastle.asn1.pkcs.PKCS12PBEParams;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.jcajce.PKCS12KeyWithParameters;
import org.spongycastle.jcajce.util.DefaultJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jcajce.util.NamedJcaJceHelper;
import org.spongycastle.jcajce.util.ProviderJcaJceHelper;
import org.spongycastle.operator.DefaultSecretKeySizeProvider;
import org.spongycastle.operator.GenericKey;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.operator.OutputEncryptor;
import org.spongycastle.operator.SecretKeySizeProvider;

/* loaded from: classes7.dex */
public class JcePKCSPBEOutputEncryptorBuilder {
    private ASN1ObjectIdentifier algorithm;
    private ASN1ObjectIdentifier keyEncAlgorithm;
    private SecureRandom random;
    private JcaJceHelper helper = new DefaultJcaJceHelper();
    private SecretKeySizeProvider keySizeProvider = DefaultSecretKeySizeProvider.INSTANCE;
    private int iterationCount = 1024;

    public JcePKCSPBEOutputEncryptorBuilder setIterationCount(int i) {
        this.iterationCount = i;
        return this;
    }

    public JcePKCSPBEOutputEncryptorBuilder setKeySizeProvider(SecretKeySizeProvider secretKeySizeProvider) {
        this.keySizeProvider = secretKeySizeProvider;
        return this;
    }

    public JcePKCSPBEOutputEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (isPKCS12(aSN1ObjectIdentifier)) {
            this.algorithm = aSN1ObjectIdentifier;
            this.keyEncAlgorithm = aSN1ObjectIdentifier;
        } else {
            this.algorithm = PKCSObjectIdentifiers.id_PBES2;
            this.keyEncAlgorithm = aSN1ObjectIdentifier;
        }
    }

    public JcePKCSPBEOutputEncryptorBuilder setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePKCSPBEOutputEncryptorBuilder setProvider(String str) {
        this.helper = new NamedJcaJceHelper(str);
        return this;
    }

    public OutputEncryptor build(final char[] cArr) throws OperatorCreationException, InvalidKeySpecException, InvalidKeyException {
        final AlgorithmIdentifier algorithmIdentifier;
        final Cipher cipherCreateCipher;
        if (this.random == null) {
            this.random = new SecureRandom();
        }
        byte[] bArr = new byte[20];
        this.random.nextBytes(bArr);
        try {
            if (isPKCS12(this.algorithm)) {
                cipherCreateCipher = this.helper.createCipher(this.algorithm.getId());
                cipherCreateCipher.init(1, new PKCS12KeyWithParameters(cArr, bArr, this.iterationCount));
                algorithmIdentifier = new AlgorithmIdentifier(this.algorithm, new PKCS12PBEParams(bArr, this.iterationCount));
            } else if (this.algorithm.equals(PKCSObjectIdentifiers.id_PBES2)) {
                SecretKey secretKeyGenerateSecret = this.helper.createSecretKeyFactory(PKCSObjectIdentifiers.id_PBKDF2.getId()).generateSecret(new PBEKeySpec(cArr, bArr, this.iterationCount, this.keySizeProvider.getKeySize(new AlgorithmIdentifier(this.keyEncAlgorithm))));
                Cipher cipherCreateCipher2 = this.helper.createCipher(this.keyEncAlgorithm.getId());
                cipherCreateCipher2.init(1, secretKeyGenerateSecret, this.random);
                algorithmIdentifier = new AlgorithmIdentifier(this.algorithm, new PBES2Parameters(new KeyDerivationFunc(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(bArr, this.iterationCount)), new EncryptionScheme(this.keyEncAlgorithm, ASN1Primitive.fromByteArray(cipherCreateCipher2.getParameters().getEncoded()))));
                cipherCreateCipher = cipherCreateCipher2;
            } else {
                throw new OperatorCreationException("unrecognised algorithm");
            }
            return new OutputEncryptor() { // from class: org.spongycastle.pkcs.jcajce.JcePKCSPBEOutputEncryptorBuilder.1
                @Override // org.spongycastle.operator.OutputEncryptor
                public AlgorithmIdentifier getAlgorithmIdentifier() {
                    return algorithmIdentifier;
                }

                @Override // org.spongycastle.operator.OutputEncryptor
                public OutputStream getOutputStream(OutputStream outputStream) {
                    return new CipherOutputStream(outputStream, cipherCreateCipher);
                }

                @Override // org.spongycastle.operator.OutputEncryptor
                public GenericKey getKey() {
                    if (JcePKCSPBEOutputEncryptorBuilder.this.isPKCS12(algorithmIdentifier.getAlgorithm())) {
                        return new GenericKey(algorithmIdentifier, JcePKCSPBEOutputEncryptorBuilder.PKCS12PasswordToBytes(cArr));
                    }
                    return new GenericKey(algorithmIdentifier, JcePKCSPBEOutputEncryptorBuilder.PKCS5PasswordToBytes(cArr));
                }
            };
        } catch (Exception e) {
            throw new OperatorCreationException("unable to create OutputEncryptor: " + e.getMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPKCS12(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return aSN1ObjectIdentifier.on(PKCSObjectIdentifiers.pkcs_12PbeIds) || aSN1ObjectIdentifier.on(BCObjectIdentifiers.bc_pbe_sha1_pkcs12) || aSN1ObjectIdentifier.on(BCObjectIdentifiers.bc_pbe_sha256_pkcs12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] PKCS5PasswordToBytes(char[] cArr) {
        if (cArr == null) {
            return new byte[0];
        }
        int length = cArr.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i != length; i++) {
            bArr[i] = (byte) cArr[i];
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] PKCS12PasswordToBytes(char[] cArr) {
        if (cArr == null || cArr.length <= 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[(cArr.length + 1) * 2];
        for (int i = 0; i != cArr.length; i++) {
            int i2 = i * 2;
            char c = cArr[i];
            bArr[i2] = (byte) (c >>> '\b');
            bArr[i2 + 1] = (byte) c;
        }
        return bArr;
    }
}
