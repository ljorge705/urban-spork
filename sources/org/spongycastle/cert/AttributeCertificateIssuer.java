package org.spongycastle.cert;

import java.util.ArrayList;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.AttCertIssuer;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.V2Form;
import org.spongycastle.util.Selector;

/* loaded from: classes4.dex */
public class AttributeCertificateIssuer implements Selector {
    final ASN1Encodable form;

    public AttributeCertificateIssuer(AttCertIssuer attCertIssuer) {
        this.form = attCertIssuer.getIssuer();
    }

    public AttributeCertificateIssuer(X500Name x500Name) {
        this.form = new V2Form(new GeneralNames(new GeneralName(x500Name)));
    }

    public X500Name[] getNames() {
        GeneralNames issuerName;
        ASN1Encodable aSN1Encodable = this.form;
        if (aSN1Encodable instanceof V2Form) {
            issuerName = ((V2Form) aSN1Encodable).getIssuerName();
        } else {
            issuerName = (GeneralNames) aSN1Encodable;
        }
        GeneralName[] names = issuerName.getNames();
        ArrayList arrayList = new ArrayList(names.length);
        for (int i = 0; i != names.length; i++) {
            if (names[i].getTagNo() == 4) {
                arrayList.add(X500Name.getInstance(names[i].getName()));
            }
        }
        return (X500Name[]) arrayList.toArray(new X500Name[arrayList.size()]);
    }

    private boolean matchesDN(X500Name x500Name, GeneralNames generalNames) {
        GeneralName[] names = generalNames.getNames();
        for (int i = 0; i != names.length; i++) {
            GeneralName generalName = names[i];
            if (generalName.getTagNo() == 4 && X500Name.getInstance(generalName.getName()).equals(x500Name)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.spongycastle.util.Selector
    public Object clone() {
        return new AttributeCertificateIssuer(AttCertIssuer.getInstance(this.form));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttributeCertificateIssuer) {
            return this.form.equals(((AttributeCertificateIssuer) obj).form);
        }
        return false;
    }

    public int hashCode() {
        return this.form.hashCode();
    }

    @Override // org.spongycastle.util.Selector
    public boolean match(Object obj) {
        if (!(obj instanceof X509CertificateHolder)) {
            return false;
        }
        X509CertificateHolder x509CertificateHolder = (X509CertificateHolder) obj;
        ASN1Encodable aSN1Encodable = this.form;
        if (aSN1Encodable instanceof V2Form) {
            V2Form v2Form = (V2Form) aSN1Encodable;
            if (v2Form.getBaseCertificateID() != null) {
                return v2Form.getBaseCertificateID().getSerial().getValue().equals(x509CertificateHolder.getSerialNumber()) && matchesDN(x509CertificateHolder.getIssuer(), v2Form.getBaseCertificateID().getIssuer());
            }
            if (matchesDN(x509CertificateHolder.getSubject(), v2Form.getIssuerName())) {
                return true;
            }
        } else {
            if (matchesDN(x509CertificateHolder.getSubject(), (GeneralNames) aSN1Encodable)) {
                return true;
            }
        }
        return false;
    }
}
