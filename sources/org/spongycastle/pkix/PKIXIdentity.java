package org.spongycastle.pkix;

import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.SubjectKeyIdentifier;
import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.cms.KeyTransRecipientId;
import org.spongycastle.cms.RecipientId;

/* loaded from: classes7.dex */
public class PKIXIdentity {
    private final X509CertificateHolder[] certificateHolders;
    private final PrivateKeyInfo privateKeyInfo;

    public PrivateKeyInfo getPrivateKeyInfo() {
        return this.privateKeyInfo;
    }

    public PKIXIdentity(PrivateKeyInfo privateKeyInfo, X509CertificateHolder[] x509CertificateHolderArr) {
        this.privateKeyInfo = privateKeyInfo;
        X509CertificateHolder[] x509CertificateHolderArr2 = new X509CertificateHolder[x509CertificateHolderArr.length];
        this.certificateHolders = x509CertificateHolderArr2;
        System.arraycopy(x509CertificateHolderArr, 0, x509CertificateHolderArr2, 0, x509CertificateHolderArr.length);
    }

    public X509CertificateHolder getCertificate() {
        return this.certificateHolders[0];
    }

    public RecipientId getRecipientId() {
        return new KeyTransRecipientId(this.certificateHolders[0].getIssuer(), this.certificateHolders[0].getSerialNumber(), getSubjectKeyIdentifier());
    }

    private byte[] getSubjectKeyIdentifier() {
        SubjectKeyIdentifier subjectKeyIdentifierFromExtensions = SubjectKeyIdentifier.fromExtensions(this.certificateHolders[0].getExtensions());
        if (subjectKeyIdentifierFromExtensions == null) {
            return null;
        }
        return subjectKeyIdentifierFromExtensions.getKeyIdentifier();
    }
}
