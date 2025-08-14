package org.spongycastle.cert.ocsp.jcajce;

import java.math.BigInteger;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.spongycastle.cert.jcajce.JcaX509CertificateHolder;
import org.spongycastle.cert.ocsp.CertificateID;
import org.spongycastle.cert.ocsp.OCSPException;
import org.spongycastle.operator.DigestCalculator;

/* loaded from: classes4.dex */
public class JcaCertificateID extends CertificateID {
    public JcaCertificateID(DigestCalculator digestCalculator, X509Certificate x509Certificate, BigInteger bigInteger) throws OCSPException, CertificateEncodingException {
        super(digestCalculator, new JcaX509CertificateHolder(x509Certificate), bigInteger);
    }
}
