package org.spongycastle.cert.crmf.jcajce;

import java.io.IOException;
import java.security.Provider;
import java.security.PublicKey;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.crmf.CertReqMsg;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.cert.crmf.CRMFException;
import org.spongycastle.cert.crmf.CertificateRequestMessage;
import org.spongycastle.jcajce.util.DefaultJcaJceHelper;
import org.spongycastle.jcajce.util.NamedJcaJceHelper;
import org.spongycastle.jcajce.util.ProviderJcaJceHelper;

/* loaded from: classes4.dex */
public class JcaCertificateRequestMessage extends CertificateRequestMessage {
    private CRMFHelper helper;

    public JcaCertificateRequestMessage(byte[] bArr) {
        this(CertReqMsg.getInstance(bArr));
    }

    public JcaCertificateRequestMessage(CertificateRequestMessage certificateRequestMessage) {
        this(certificateRequestMessage.toASN1Structure());
    }

    public JcaCertificateRequestMessage(CertReqMsg certReqMsg) {
        super(certReqMsg);
        this.helper = new CRMFHelper(new DefaultJcaJceHelper());
    }

    public JcaCertificateRequestMessage setProvider(String str) {
        this.helper = new CRMFHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JcaCertificateRequestMessage setProvider(Provider provider) {
        this.helper = new CRMFHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public X500Principal getSubjectX500Principal() {
        X500Name subject = getCertTemplate().getSubject();
        if (subject == null) {
            return null;
        }
        try {
            return new X500Principal(subject.getEncoded("DER"));
        } catch (IOException e) {
            throw new IllegalStateException("unable to construct DER encoding of name: " + e.getMessage());
        }
    }

    public PublicKey getPublicKey() throws CRMFException {
        SubjectPublicKeyInfo publicKey = getCertTemplate().getPublicKey();
        if (publicKey != null) {
            return this.helper.toPublicKey(publicKey);
        }
        return null;
    }
}
