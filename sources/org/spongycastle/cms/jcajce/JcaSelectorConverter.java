package org.spongycastle.cms.jcajce;

import java.io.IOException;
import java.security.cert.X509CertSelector;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.cms.KeyTransRecipientId;
import org.spongycastle.cms.SignerId;

/* loaded from: classes4.dex */
public class JcaSelectorConverter {
    public SignerId getSignerId(X509CertSelector x509CertSelector) {
        try {
            if (x509CertSelector.getSubjectKeyIdentifier() != null) {
                return new SignerId(X500Name.getInstance(x509CertSelector.getIssuerAsBytes()), x509CertSelector.getSerialNumber(), ASN1OctetString.getInstance(x509CertSelector.getSubjectKeyIdentifier()).getOctets());
            }
            return new SignerId(X500Name.getInstance(x509CertSelector.getIssuerAsBytes()), x509CertSelector.getSerialNumber());
        } catch (IOException e) {
            throw new IllegalArgumentException("unable to convert issuer: " + e.getMessage());
        }
    }

    public KeyTransRecipientId getKeyTransRecipientId(X509CertSelector x509CertSelector) {
        try {
            if (x509CertSelector.getSubjectKeyIdentifier() != null) {
                return new KeyTransRecipientId(X500Name.getInstance(x509CertSelector.getIssuerAsBytes()), x509CertSelector.getSerialNumber(), ASN1OctetString.getInstance(x509CertSelector.getSubjectKeyIdentifier()).getOctets());
            }
            return new KeyTransRecipientId(X500Name.getInstance(x509CertSelector.getIssuerAsBytes()), x509CertSelector.getSerialNumber());
        } catch (IOException e) {
            throw new IllegalArgumentException("unable to convert issuer: " + e.getMessage());
        }
    }
}
