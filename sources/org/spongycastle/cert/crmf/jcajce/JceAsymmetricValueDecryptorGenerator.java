package org.spongycastle.cert.crmf.jcajce;

import java.io.InputStream;
import java.security.Key;
import java.security.PrivateKey;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.cert.crmf.CRMFException;
import org.spongycastle.cert.crmf.ValueDecryptorGenerator;
import org.spongycastle.jcajce.util.DefaultJcaJceHelper;
import org.spongycastle.jcajce.util.NamedJcaJceHelper;
import org.spongycastle.jcajce.util.ProviderJcaJceHelper;
import org.spongycastle.operator.InputDecryptor;
import org.spongycastle.operator.OperatorException;
import org.spongycastle.operator.jcajce.JceAsymmetricKeyUnwrapper;

/* loaded from: classes4.dex */
public class JceAsymmetricValueDecryptorGenerator implements ValueDecryptorGenerator {
    private CRMFHelper helper = new CRMFHelper(new DefaultJcaJceHelper());
    private Provider provider = null;
    private String providerName = null;
    private PrivateKey recipientKey;

    public JceAsymmetricValueDecryptorGenerator(PrivateKey privateKey) {
        this.recipientKey = privateKey;
    }

    public JceAsymmetricValueDecryptorGenerator setProvider(Provider provider) {
        this.helper = new CRMFHelper(new ProviderJcaJceHelper(provider));
        this.provider = provider;
        this.providerName = null;
        return this;
    }

    public JceAsymmetricValueDecryptorGenerator setProvider(String str) {
        this.helper = new CRMFHelper(new NamedJcaJceHelper(str));
        this.provider = null;
        this.providerName = str;
        return this;
    }

    private Key extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) throws CRMFException {
        try {
            JceAsymmetricKeyUnwrapper jceAsymmetricKeyUnwrapper = new JceAsymmetricKeyUnwrapper(algorithmIdentifier, this.recipientKey);
            Provider provider = this.provider;
            if (provider != null) {
                jceAsymmetricKeyUnwrapper.setProvider(provider);
            }
            String str = this.providerName;
            if (str != null) {
                jceAsymmetricKeyUnwrapper.setProvider(str);
            }
            return new SecretKeySpec((byte[]) jceAsymmetricKeyUnwrapper.generateUnwrappedKey(algorithmIdentifier2, bArr).getRepresentation(), algorithmIdentifier2.getAlgorithm().getId());
        } catch (OperatorException e) {
            throw new CRMFException("key invalid in message: " + e.getMessage(), e);
        }
    }

    @Override // org.spongycastle.cert.crmf.ValueDecryptorGenerator
    public InputDecryptor getValueDecryptor(AlgorithmIdentifier algorithmIdentifier, final AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) throws CRMFException {
        final Cipher cipherCreateContentCipher = this.helper.createContentCipher(extractSecretKey(algorithmIdentifier, algorithmIdentifier2, bArr), algorithmIdentifier2);
        return new InputDecryptor() { // from class: org.spongycastle.cert.crmf.jcajce.JceAsymmetricValueDecryptorGenerator.1
            @Override // org.spongycastle.operator.InputDecryptor
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return algorithmIdentifier2;
            }

            @Override // org.spongycastle.operator.InputDecryptor
            public InputStream getInputStream(InputStream inputStream) {
                return new CipherInputStream(inputStream, cipherCreateContentCipher);
            }
        };
    }
}
