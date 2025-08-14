package org.bouncycastle.asn1;

import java.io.IOException;

/* loaded from: classes4.dex */
public class BERSet extends ASN1Set {
    public BERSet() {
    }

    public BERSet(ASN1Encodable aSN1Encodable) {
        super(aSN1Encodable);
    }

    public BERSet(ASN1EncodableVector aSN1EncodableVector) {
        super(aSN1EncodableVector, false);
    }

    BERSet(boolean z, ASN1Encodable[] aSN1EncodableArr) {
        super(z, aSN1EncodableArr);
    }

    public BERSet(ASN1Encodable[] aSN1EncodableArr) {
        super(aSN1EncodableArr, false);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        aSN1OutputStream.writeEncodingIL(z, 49, this.elements);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive
    int encodedLength(boolean z) throws IOException {
        int iEncodedLength = z ? 4 : 3;
        int length = this.elements.length;
        for (int i = 0; i < length; i++) {
            iEncodedLength += this.elements[i].toASN1Primitive().encodedLength(true);
        }
        return iEncodedLength;
    }
}
