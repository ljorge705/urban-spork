package org.spongycastle.asn1.cmc;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERUTF8String;

/* loaded from: classes4.dex */
public class CMCStatusInfoV2 extends ASN1Object {
    private final ASN1Sequence bodyList;
    private final CMCStatus cMCStatus;
    private final OtherStatusInfo otherStatusInfo;
    private final DERUTF8String statusString;

    public OtherStatusInfo getOtherStatusInfo() {
        return this.otherStatusInfo;
    }

    public DERUTF8String getStatusString() {
        return this.statusString;
    }

    public CMCStatus getcMCStatus() {
        return this.cMCStatus;
    }

    public boolean hasOtherInfo() {
        return this.otherStatusInfo != null;
    }

    CMCStatusInfoV2(CMCStatus cMCStatus, ASN1Sequence aSN1Sequence, DERUTF8String dERUTF8String, OtherStatusInfo otherStatusInfo) {
        this.cMCStatus = cMCStatus;
        this.bodyList = aSN1Sequence;
        this.statusString = dERUTF8String;
        this.otherStatusInfo = otherStatusInfo;
    }

    private CMCStatusInfoV2(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 2 || aSN1Sequence.size() > 4) {
            throw new IllegalArgumentException("incorrect sequence size");
        }
        this.cMCStatus = CMCStatus.getInstance(aSN1Sequence.getObjectAt(0));
        this.bodyList = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() <= 2) {
            this.statusString = null;
            this.otherStatusInfo = null;
        } else if (aSN1Sequence.size() == 4) {
            this.statusString = DERUTF8String.getInstance(aSN1Sequence.getObjectAt(2));
            this.otherStatusInfo = OtherStatusInfo.getInstance(aSN1Sequence.getObjectAt(3));
        } else if (aSN1Sequence.getObjectAt(2) instanceof DERUTF8String) {
            this.statusString = DERUTF8String.getInstance(aSN1Sequence.getObjectAt(2));
            this.otherStatusInfo = null;
        } else {
            this.statusString = null;
            this.otherStatusInfo = OtherStatusInfo.getInstance(aSN1Sequence.getObjectAt(2));
        }
    }

    public BodyPartID[] getBodyList() {
        return Utils.toBodyPartIDArray(this.bodyList);
    }

    public static CMCStatusInfoV2 getInstance(Object obj) {
        if (obj instanceof CMCStatusInfoV2) {
            return (CMCStatusInfoV2) obj;
        }
        if (obj != null) {
            return new CMCStatusInfoV2(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.cMCStatus);
        aSN1EncodableVector.add(this.bodyList);
        DERUTF8String dERUTF8String = this.statusString;
        if (dERUTF8String != null) {
            aSN1EncodableVector.add(dERUTF8String);
        }
        OtherStatusInfo otherStatusInfo = this.otherStatusInfo;
        if (otherStatusInfo != null) {
            aSN1EncodableVector.add(otherStatusInfo);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
