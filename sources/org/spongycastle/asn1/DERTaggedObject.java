package org.spongycastle.asn1;

import java.io.IOException;

/* loaded from: classes4.dex */
public class DERTaggedObject extends ASN1TaggedObject {
    private static final byte[] ZERO_BYTES = new byte[0];

    public DERTaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        super(z, i, aSN1Encodable);
    }

    public DERTaggedObject(int i, ASN1Encodable aSN1Encodable) {
        super(true, i, aSN1Encodable);
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    boolean isConstructed() {
        if (this.empty || this.explicit) {
            return true;
        }
        return this.obj.toASN1Primitive().toDERObject().isConstructed();
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    int encodedLength() throws IOException {
        int iCalculateTagLength;
        if (!this.empty) {
            int iEncodedLength = this.obj.toASN1Primitive().toDERObject().encodedLength();
            if (this.explicit) {
                iCalculateTagLength = StreamUtil.calculateTagLength(this.tagNo) + StreamUtil.calculateBodyLength(iEncodedLength);
            } else {
                iEncodedLength--;
                iCalculateTagLength = StreamUtil.calculateTagLength(this.tagNo);
            }
            return iCalculateTagLength + iEncodedLength;
        }
        return StreamUtil.calculateTagLength(this.tagNo) + 1;
    }

    @Override // org.spongycastle.asn1.ASN1TaggedObject, org.spongycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        if (!this.empty) {
            ASN1Primitive dERObject = this.obj.toASN1Primitive().toDERObject();
            if (this.explicit) {
                aSN1OutputStream.writeTag(160, this.tagNo);
                aSN1OutputStream.writeLength(dERObject.encodedLength());
                aSN1OutputStream.writeObject(dERObject);
                return;
            } else {
                aSN1OutputStream.writeTag(dERObject.isConstructed() ? 160 : 128, this.tagNo);
                aSN1OutputStream.writeImplicitObject(dERObject);
                return;
            }
        }
        aSN1OutputStream.writeEncoded(160, this.tagNo, ZERO_BYTES);
    }
}
