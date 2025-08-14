package org.spongycastle.cert.selector.jcajce;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.X509CertSelector;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.cert.selector.X509CertificateHolderSelector;

/* loaded from: classes4.dex */
public class JcaX509CertSelectorConverter {
    protected X509CertSelector doConversion(X500Name x500Name, BigInteger bigInteger, byte[] bArr) throws IOException {
        X509CertSelector x509CertSelector = new X509CertSelector();
        if (x500Name != null) {
            try {
                x509CertSelector.setIssuer(x500Name.getEncoded());
            } catch (IOException e) {
                throw new IllegalArgumentException("unable to convert issuer: " + e.getMessage());
            }
        }
        if (bigInteger != null) {
            x509CertSelector.setSerialNumber(bigInteger);
        }
        if (bArr != null) {
            try {
                x509CertSelector.setSubjectKeyIdentifier(new DEROctetString(bArr).getEncoded());
            } catch (IOException e2) {
                throw new IllegalArgumentException("unable to convert issuer: " + e2.getMessage());
            }
        }
        return x509CertSelector;
    }

    public X509CertSelector getCertSelector(X509CertificateHolderSelector x509CertificateHolderSelector) {
        return doConversion(x509CertificateHolderSelector.getIssuer(), x509CertificateHolderSelector.getSerialNumber(), x509CertificateHolderSelector.getSubjectKeyIdentifier());
    }
}
