package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.util.Strings;

/* loaded from: classes4.dex */
public class DistributionPointName extends ASN1Object implements ASN1Choice {
    public static final int FULL_NAME = 0;
    public static final int NAME_RELATIVE_TO_CRL_ISSUER = 1;
    ASN1Encodable name;
    int type;

    public ASN1Encodable getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }

    public static DistributionPointName getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1TaggedObject.getInstance(aSN1TaggedObject, true));
    }

    public static DistributionPointName getInstance(Object obj) {
        if (obj == null || (obj instanceof DistributionPointName)) {
            return (DistributionPointName) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new DistributionPointName((ASN1TaggedObject) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public DistributionPointName(int i, ASN1Encodable aSN1Encodable) {
        this.type = i;
        this.name = aSN1Encodable;
    }

    public DistributionPointName(GeneralNames generalNames) {
        this(0, generalNames);
    }

    public DistributionPointName(ASN1TaggedObject aSN1TaggedObject) {
        int tagNo = aSN1TaggedObject.getTagNo();
        this.type = tagNo;
        if (tagNo == 0) {
            this.name = GeneralNames.getInstance(aSN1TaggedObject, false);
        } else {
            this.name = ASN1Set.getInstance(aSN1TaggedObject, false);
        }
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(false, this.type, this.name);
    }

    public String toString() {
        String strLineSeparator = Strings.lineSeparator();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DistributionPointName: [");
        stringBuffer.append(strLineSeparator);
        if (this.type == 0) {
            appendObject(stringBuffer, strLineSeparator, "fullName", this.name.toString());
        } else {
            appendObject(stringBuffer, strLineSeparator, "nameRelativeToCRLIssuer", this.name.toString());
        }
        stringBuffer.append("]");
        stringBuffer.append(strLineSeparator);
        return stringBuffer.toString();
    }

    private void appendObject(StringBuffer stringBuffer, String str, String str2, String str3) {
        stringBuffer.append("    ");
        stringBuffer.append(str2);
        stringBuffer.append(":");
        stringBuffer.append(str);
        stringBuffer.append("    ");
        stringBuffer.append("    ");
        stringBuffer.append(str3);
        stringBuffer.append(str);
    }
}
