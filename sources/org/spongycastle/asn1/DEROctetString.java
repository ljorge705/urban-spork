package org.spongycastle.asn1;

import java.io.IOException;

/* loaded from: classes4.dex */
public class DEROctetString extends ASN1OctetString {
    @Override // org.spongycastle.asn1.ASN1Primitive
    boolean isConstructed() {
        return false;
    }

    public DEROctetString(byte[] bArr) {
        super(bArr);
    }

    public DEROctetString(ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1Encodable.toASN1Primitive().getEncoded("DER"));
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    int encodedLength() {
        return StreamUtil.calculateBodyLength(this.string.length) + 1 + this.string.length;
    }

    @Override // org.spongycastle.asn1.ASN1OctetString, org.spongycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(4, this.string);
    }

    static void encode(DEROutputStream dEROutputStream, byte[] bArr) throws IOException {
        dEROutputStream.writeEncoded(4, bArr);
    }
}
