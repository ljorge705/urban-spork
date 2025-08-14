package org.spongycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

/* loaded from: classes4.dex */
public class BERTaggedObject extends ASN1TaggedObject {
    public BERTaggedObject(int i, ASN1Encodable aSN1Encodable) {
        super(true, i, aSN1Encodable);
    }

    public BERTaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        super(z, i, aSN1Encodable);
    }

    public BERTaggedObject(int i) {
        super(false, i, new BERSequence());
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
            int iEncodedLength = this.obj.toASN1Primitive().encodedLength();
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
        Enumeration objects;
        aSN1OutputStream.writeTag(160, this.tagNo);
        aSN1OutputStream.write(128);
        if (!this.empty) {
            if (!this.explicit) {
                if (this.obj instanceof ASN1OctetString) {
                    if (this.obj instanceof BEROctetString) {
                        objects = ((BEROctetString) this.obj).getObjects();
                    } else {
                        objects = new BEROctetString(((ASN1OctetString) this.obj).getOctets()).getObjects();
                    }
                } else if (this.obj instanceof ASN1Sequence) {
                    objects = ((ASN1Sequence) this.obj).getObjects();
                } else if (this.obj instanceof ASN1Set) {
                    objects = ((ASN1Set) this.obj).getObjects();
                } else {
                    throw new ASN1Exception("not implemented: " + this.obj.getClass().getName());
                }
                while (objects.hasMoreElements()) {
                    aSN1OutputStream.writeObject((ASN1Encodable) objects.nextElement());
                }
            } else {
                aSN1OutputStream.writeObject(this.obj);
            }
        }
        aSN1OutputStream.write(0);
        aSN1OutputStream.write(0);
    }
}
