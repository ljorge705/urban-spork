package org.spongycastle.pkcs.jcajce;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Hashtable;
import org.spongycastle.asn1.pkcs.CertificationRequest;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.jcajce.util.DefaultJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jcajce.util.NamedJcaJceHelper;
import org.spongycastle.jcajce.util.ProviderJcaJceHelper;
import org.spongycastle.pkcs.PKCS10CertificationRequest;

/* loaded from: classes7.dex */
public class JcaPKCS10CertificationRequest extends PKCS10CertificationRequest {
    private static Hashtable keyAlgorithms;
    private JcaJceHelper helper;

    static {
        Hashtable hashtable = new Hashtable();
        keyAlgorithms = hashtable;
        hashtable.put(PKCSObjectIdentifiers.rsaEncryption, "RSA");
        keyAlgorithms.put(X9ObjectIdentifiers.id_dsa, "DSA");
    }

    public JcaPKCS10CertificationRequest(CertificationRequest certificationRequest) {
        super(certificationRequest);
        this.helper = new DefaultJcaJceHelper();
    }

    public JcaPKCS10CertificationRequest(byte[] bArr) throws IOException {
        super(bArr);
        this.helper = new DefaultJcaJceHelper();
    }

    public JcaPKCS10CertificationRequest(PKCS10CertificationRequest pKCS10CertificationRequest) {
        super(pKCS10CertificationRequest.toASN1Structure());
        this.helper = new DefaultJcaJceHelper();
    }

    public JcaPKCS10CertificationRequest setProvider(String str) {
        this.helper = new NamedJcaJceHelper(str);
        return this;
    }

    public JcaPKCS10CertificationRequest setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public PublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeyException {
        KeyFactory keyFactoryCreateKeyFactory;
        try {
            SubjectPublicKeyInfo subjectPublicKeyInfo = getSubjectPublicKeyInfo();
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded());
            try {
                keyFactoryCreateKeyFactory = this.helper.createKeyFactory(subjectPublicKeyInfo.getAlgorithm().getAlgorithm().getId());
            } catch (NoSuchAlgorithmException e) {
                if (keyAlgorithms.get(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()) != null) {
                    keyFactoryCreateKeyFactory = this.helper.createKeyFactory((String) keyAlgorithms.get(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()));
                } else {
                    throw e;
                }
            }
            return keyFactoryCreateKeyFactory.generatePublic(x509EncodedKeySpec);
        } catch (IOException unused) {
            throw new InvalidKeyException("error extracting key encoding");
        } catch (NoSuchProviderException e2) {
            throw new NoSuchAlgorithmException("cannot find provider: " + e2.getMessage());
        } catch (InvalidKeySpecException unused2) {
            throw new InvalidKeyException("error decoding public key");
        }
    }
}
