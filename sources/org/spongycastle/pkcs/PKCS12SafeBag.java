package org.spongycastle.pkcs;

import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.pkcs.Attribute;
import org.spongycastle.asn1.pkcs.CRLBag;
import org.spongycastle.asn1.pkcs.CertBag;
import org.spongycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.pkcs.SafeBag;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.asn1.x509.CertificateList;
import org.spongycastle.cert.X509CRLHolder;
import org.spongycastle.cert.X509CertificateHolder;

/* loaded from: classes7.dex */
public class PKCS12SafeBag {
    public static final ASN1ObjectIdentifier friendlyNameAttribute = PKCSObjectIdentifiers.pkcs_9_at_friendlyName;
    public static final ASN1ObjectIdentifier localKeyIdAttribute = PKCSObjectIdentifiers.pkcs_9_at_localKeyId;
    private SafeBag safeBag;

    public SafeBag toASN1Structure() {
        return this.safeBag;
    }

    public PKCS12SafeBag(SafeBag safeBag) {
        this.safeBag = safeBag;
    }

    public ASN1ObjectIdentifier getType() {
        return this.safeBag.getBagId();
    }

    public Attribute[] getAttributes() {
        ASN1Set bagAttributes = this.safeBag.getBagAttributes();
        if (bagAttributes == null) {
            return null;
        }
        Attribute[] attributeArr = new Attribute[bagAttributes.size()];
        for (int i = 0; i != bagAttributes.size(); i++) {
            attributeArr[i] = Attribute.getInstance(bagAttributes.getObjectAt(i));
        }
        return attributeArr;
    }

    public Object getBagValue() {
        if (getType().equals(PKCSObjectIdentifiers.pkcs8ShroudedKeyBag)) {
            return new PKCS8EncryptedPrivateKeyInfo(EncryptedPrivateKeyInfo.getInstance(this.safeBag.getBagValue()));
        }
        if (getType().equals(PKCSObjectIdentifiers.certBag)) {
            return new X509CertificateHolder(Certificate.getInstance(ASN1OctetString.getInstance(CertBag.getInstance(this.safeBag.getBagValue()).getCertValue()).getOctets()));
        }
        if (getType().equals(PKCSObjectIdentifiers.keyBag)) {
            return PrivateKeyInfo.getInstance(this.safeBag.getBagValue());
        }
        if (getType().equals(PKCSObjectIdentifiers.crlBag)) {
            return new X509CRLHolder(CertificateList.getInstance(ASN1OctetString.getInstance(CRLBag.getInstance(this.safeBag.getBagValue()).getCrlValue()).getOctets()));
        }
        return this.safeBag.getBagValue();
    }
}
