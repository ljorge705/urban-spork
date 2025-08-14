package org.spongycastle.cms;

import java.math.BigInteger;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Selector;

/* loaded from: classes4.dex */
class OriginatorId implements Selector {
    private X500Name issuer;
    private BigInteger serialNumber;
    private byte[] subjectKeyId;

    private void setIssuerAndSerial(X500Name x500Name, BigInteger bigInteger) {
        this.issuer = x500Name;
        this.serialNumber = bigInteger;
    }

    private void setSubjectKeyID(byte[] bArr) {
        this.subjectKeyId = bArr;
    }

    public X500Name getIssuer() {
        return this.issuer;
    }

    @Override // org.spongycastle.util.Selector
    public boolean match(Object obj) {
        return false;
    }

    public OriginatorId(byte[] bArr) {
        setSubjectKeyID(bArr);
    }

    public OriginatorId(X500Name x500Name, BigInteger bigInteger) {
        setIssuerAndSerial(x500Name, bigInteger);
    }

    public OriginatorId(X500Name x500Name, BigInteger bigInteger, byte[] bArr) {
        setIssuerAndSerial(x500Name, bigInteger);
        setSubjectKeyID(bArr);
    }

    @Override // org.spongycastle.util.Selector
    public Object clone() {
        return new OriginatorId(this.issuer, this.serialNumber, this.subjectKeyId);
    }

    public int hashCode() {
        int iHashCode = Arrays.hashCode(this.subjectKeyId);
        BigInteger bigInteger = this.serialNumber;
        if (bigInteger != null) {
            iHashCode ^= bigInteger.hashCode();
        }
        X500Name x500Name = this.issuer;
        return x500Name != null ? iHashCode ^ x500Name.hashCode() : iHashCode;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof OriginatorId)) {
            return false;
        }
        OriginatorId originatorId = (OriginatorId) obj;
        return Arrays.areEqual(this.subjectKeyId, originatorId.subjectKeyId) && equalsObj(this.serialNumber, originatorId.serialNumber) && equalsObj(this.issuer, originatorId.issuer);
    }

    private boolean equalsObj(Object obj, Object obj2) {
        if (obj != null) {
            return obj.equals(obj2);
        }
        return obj2 == null;
    }
}
