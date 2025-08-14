package org.spongycastle.pkcs.jcajce;

import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Provider;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.cryptopro.GOST28147Parameters;
import org.spongycastle.asn1.pkcs.PBES2Parameters;
import org.spongycastle.asn1.pkcs.PBKDF2Params;
import org.spongycastle.asn1.pkcs.PKCS12PBEParams;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.jcajce.PKCS12KeyWithParameters;
import org.spongycastle.jcajce.spec.GOST28147ParameterSpec;
import org.spongycastle.jcajce.spec.PBKDF2KeySpec;
import org.spongycastle.jcajce.util.DefaultJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jcajce.util.NamedJcaJceHelper;
import org.spongycastle.jcajce.util.ProviderJcaJceHelper;
import org.spongycastle.operator.DefaultSecretKeySizeProvider;
import org.spongycastle.operator.InputDecryptor;
import org.spongycastle.operator.InputDecryptorProvider;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.operator.SecretKeySizeProvider;

/* loaded from: classes7.dex */
public class JcePKCSPBEInputDecryptorProviderBuilder {
    private JcaJceHelper helper = new DefaultJcaJceHelper();
    private boolean wrongPKCS12Zero = false;
    private SecretKeySizeProvider keySizeProvider = DefaultSecretKeySizeProvider.INSTANCE;

    public JcePKCSPBEInputDecryptorProviderBuilder setKeySizeProvider(SecretKeySizeProvider secretKeySizeProvider) {
        this.keySizeProvider = secretKeySizeProvider;
        return this;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setTryWrongPKCS12Zero(boolean z) {
        this.wrongPKCS12Zero = z;
        return this;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setProvider(String str) {
        this.helper = new NamedJcaJceHelper(str);
        return this;
    }

    public InputDecryptorProvider build(final char[] cArr) {
        return new InputDecryptorProvider() { // from class: org.spongycastle.pkcs.jcajce.JcePKCSPBEInputDecryptorProviderBuilder.1
            private Cipher cipher;
            private AlgorithmIdentifier encryptionAlg;

            @Override // org.spongycastle.operator.InputDecryptorProvider
            public InputDecryptor get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException, InvalidKeySpecException, InvalidKeyException, InvalidAlgorithmParameterException {
                SecretKey secretKeyGenerateSecret;
                ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
                try {
                    if (algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
                        PKCS12PBEParams pKCS12PBEParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                        Cipher cipherCreateCipher = JcePKCSPBEInputDecryptorProviderBuilder.this.helper.createCipher(algorithm.getId());
                        this.cipher = cipherCreateCipher;
                        cipherCreateCipher.init(2, new PKCS12KeyWithParameters(cArr, JcePKCSPBEInputDecryptorProviderBuilder.this.wrongPKCS12Zero, pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue()));
                        this.encryptionAlg = algorithmIdentifier;
                    } else if (algorithm.equals(PKCSObjectIdentifiers.id_PBES2)) {
                        PBES2Parameters pBES2Parameters = PBES2Parameters.getInstance(algorithmIdentifier.getParameters());
                        PBKDF2Params pBKDF2Params = PBKDF2Params.getInstance(pBES2Parameters.getKeyDerivationFunc().getParameters());
                        AlgorithmIdentifier algorithmIdentifier2 = AlgorithmIdentifier.getInstance(pBES2Parameters.getEncryptionScheme());
                        SecretKeyFactory secretKeyFactoryCreateSecretKeyFactory = JcePKCSPBEInputDecryptorProviderBuilder.this.helper.createSecretKeyFactory(pBES2Parameters.getKeyDerivationFunc().getAlgorithm().getId());
                        if (pBKDF2Params.isDefaultPrf()) {
                            secretKeyGenerateSecret = secretKeyFactoryCreateSecretKeyFactory.generateSecret(new PBEKeySpec(cArr, pBKDF2Params.getSalt(), pBKDF2Params.getIterationCount().intValue(), JcePKCSPBEInputDecryptorProviderBuilder.this.keySizeProvider.getKeySize(algorithmIdentifier2)));
                        } else {
                            secretKeyGenerateSecret = secretKeyFactoryCreateSecretKeyFactory.generateSecret(new PBKDF2KeySpec(cArr, pBKDF2Params.getSalt(), pBKDF2Params.getIterationCount().intValue(), JcePKCSPBEInputDecryptorProviderBuilder.this.keySizeProvider.getKeySize(algorithmIdentifier2), pBKDF2Params.getPrf()));
                        }
                        this.cipher = JcePKCSPBEInputDecryptorProviderBuilder.this.helper.createCipher(pBES2Parameters.getEncryptionScheme().getAlgorithm().getId());
                        this.encryptionAlg = AlgorithmIdentifier.getInstance(pBES2Parameters.getEncryptionScheme());
                        ASN1Encodable parameters = pBES2Parameters.getEncryptionScheme().getParameters();
                        if (parameters instanceof ASN1OctetString) {
                            this.cipher.init(2, secretKeyGenerateSecret, new IvParameterSpec(ASN1OctetString.getInstance(parameters).getOctets()));
                        } else {
                            GOST28147Parameters gOST28147Parameters = GOST28147Parameters.getInstance(parameters);
                            this.cipher.init(2, secretKeyGenerateSecret, new GOST28147ParameterSpec(gOST28147Parameters.getEncryptionParamSet(), gOST28147Parameters.getIV()));
                        }
                    }
                    return new InputDecryptor() { // from class: org.spongycastle.pkcs.jcajce.JcePKCSPBEInputDecryptorProviderBuilder.1.1
                        @Override // org.spongycastle.operator.InputDecryptor
                        public AlgorithmIdentifier getAlgorithmIdentifier() {
                            return AnonymousClass1.this.encryptionAlg;
                        }

                        @Override // org.spongycastle.operator.InputDecryptor
                        public InputStream getInputStream(InputStream inputStream) {
                            return new CipherInputStream(inputStream, AnonymousClass1.this.cipher);
                        }
                    };
                } catch (Exception e) {
                    throw new OperatorCreationException("unable to create InputDecryptor: " + e.getMessage(), e);
                }
            }
        };
    }
}
