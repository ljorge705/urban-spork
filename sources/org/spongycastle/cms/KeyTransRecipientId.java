package org.spongycastle.cms;

import java.math.BigInteger;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.cert.selector.X509CertificateHolderSelector;

/* loaded from: classes4.dex */
public class KeyTransRecipientId extends RecipientId {
    private X509CertificateHolderSelector baseSelector;

    private KeyTransRecipientId(X509CertificateHolderSelector x509CertificateHolderSelector) {
        super(0);
        this.baseSelector = x509CertificateHolderSelector;
    }

    public KeyTransRecipientId(byte[] bArr) {
        this(null, null, bArr);
    }

    public KeyTransRecipientId(X500Name x500Name, BigInteger bigInteger) {
        this(x500Name, bigInteger, null);
    }

    public KeyTransRecipientId(X500Name x500Name, BigInteger bigInteger, byte[] bArr) {
        this(new X509CertificateHolderSelector(x500Name, bigInteger, bArr));
    }

    public X500Name getIssuer() {
        return this.baseSelector.getIssuer();
    }

    public BigInteger getSerialNumber() {
        return this.baseSelector.getSerialNumber();
    }

    public byte[] getSubjectKeyIdentifier() {
        return this.baseSelector.getSubjectKeyIdentifier();
    }

    public int hashCode() {
        return this.baseSelector.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof KeyTransRecipientId) {
            return this.baseSelector.equals(((KeyTransRecipientId) obj).baseSelector);
        }
        return false;
    }

    @Override // org.spongycastle.cms.RecipientId, org.spongycastle.util.Selector
    public Object clone() {
        return new KeyTransRecipientId(this.baseSelector);
    }

    @Override // org.spongycastle.util.Selector
    public boolean match(Object obj) {
        if (obj instanceof KeyTransRecipientInformation) {
            return ((KeyTransRecipientInformation) obj).getRID().equals(this);
        }
        return this.baseSelector.match(obj);
    }
}
