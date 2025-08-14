package org.spongycastle.pkix.jcajce;

import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.cert.jcajce.JcaX509CertificateHolder;
import org.spongycastle.pkix.PKIXIdentity;

/* loaded from: classes7.dex */
public class JcaPKIXIdentity extends PKIXIdentity {
    private final X509Certificate[] certs;
    private final PrivateKey privKey;

    public PrivateKey getPrivateKey() {
        return this.privKey;
    }

    private static PrivateKeyInfo getPrivateKeyInfo(PrivateKey privateKey) {
        try {
            return PrivateKeyInfo.getInstance(privateKey.getEncoded());
        } catch (Exception unused) {
            return null;
        }
    }

    private static X509CertificateHolder[] getCertificates(X509Certificate[] x509CertificateArr) {
        int length = x509CertificateArr.length;
        X509CertificateHolder[] x509CertificateHolderArr = new X509CertificateHolder[length];
        for (int i = 0; i != length; i++) {
            try {
                x509CertificateHolderArr[i] = new JcaX509CertificateHolder(x509CertificateArr[i]);
            } catch (CertificateEncodingException e) {
                throw new IllegalArgumentException("Unable to process certificates: " + e.getMessage());
            }
        }
        return x509CertificateHolderArr;
    }

    public JcaPKIXIdentity(PrivateKey privateKey, X509Certificate[] x509CertificateArr) {
        super(getPrivateKeyInfo(privateKey), getCertificates(x509CertificateArr));
        this.privKey = privateKey;
        X509Certificate[] x509CertificateArr2 = new X509Certificate[x509CertificateArr.length];
        this.certs = x509CertificateArr2;
        System.arraycopy(x509CertificateArr, 0, x509CertificateArr2, 0, x509CertificateArr.length);
    }

    public X509Certificate getX509Certificate() {
        return this.certs[0];
    }
}
