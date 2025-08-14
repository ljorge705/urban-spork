package org.spongycastle.asn1.cmp;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.CertificateList;

/* loaded from: classes4.dex */
public class CRLAnnContent extends ASN1Object {
    private ASN1Sequence content;

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }

    private CRLAnnContent(ASN1Sequence aSN1Sequence) {
        this.content = aSN1Sequence;
    }

    public static CRLAnnContent getInstance(Object obj) {
        if (obj instanceof CRLAnnContent) {
            return (CRLAnnContent) obj;
        }
        if (obj != null) {
            return new CRLAnnContent(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CRLAnnContent(CertificateList certificateList) {
        this.content = new DERSequence(certificateList);
    }

    public CertificateList[] getCertificateLists() {
        int size = this.content.size();
        CertificateList[] certificateListArr = new CertificateList[size];
        for (int i = 0; i != size; i++) {
            certificateListArr[i] = CertificateList.getInstance(this.content.getObjectAt(i));
        }
        return certificateListArr;
    }
}
