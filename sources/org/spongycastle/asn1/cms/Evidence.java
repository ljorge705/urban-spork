package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERTaggedObject;

/* loaded from: classes4.dex */
public class Evidence extends ASN1Object implements ASN1Choice {
    private TimeStampTokenEvidence tstEvidence;

    public TimeStampTokenEvidence getTstEvidence() {
        return this.tstEvidence;
    }

    public Evidence(TimeStampTokenEvidence timeStampTokenEvidence) {
        this.tstEvidence = timeStampTokenEvidence;
    }

    private Evidence(ASN1TaggedObject aSN1TaggedObject) {
        if (aSN1TaggedObject.getTagNo() == 0) {
            this.tstEvidence = TimeStampTokenEvidence.getInstance(aSN1TaggedObject, false);
        }
    }

    public static Evidence getInstance(Object obj) {
        if (obj == null || (obj instanceof Evidence)) {
            return (Evidence) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new Evidence(ASN1TaggedObject.getInstance(obj));
        }
        throw new IllegalArgumentException("unknown object in getInstance");
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        if (this.tstEvidence != null) {
            return new DERTaggedObject(false, 0, this.tstEvidence);
        }
        return null;
    }
}
