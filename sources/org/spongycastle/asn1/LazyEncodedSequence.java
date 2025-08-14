package org.spongycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

/* loaded from: classes4.dex */
class LazyEncodedSequence extends ASN1Sequence {
    private byte[] encoded;

    LazyEncodedSequence(byte[] bArr) throws IOException {
        this.encoded = bArr;
    }

    private void parse() {
        LazyConstructionEnumeration lazyConstructionEnumeration = new LazyConstructionEnumeration(this.encoded);
        while (lazyConstructionEnumeration.hasMoreElements()) {
            this.seq.addElement(lazyConstructionEnumeration.nextElement());
        }
        this.encoded = null;
    }

    @Override // org.spongycastle.asn1.ASN1Sequence
    public synchronized ASN1Encodable getObjectAt(int i) {
        if (this.encoded != null) {
            parse();
        }
        return super.getObjectAt(i);
    }

    @Override // org.spongycastle.asn1.ASN1Sequence
    public synchronized Enumeration getObjects() {
        if (this.encoded == null) {
            return super.getObjects();
        }
        return new LazyConstructionEnumeration(this.encoded);
    }

    @Override // org.spongycastle.asn1.ASN1Sequence
    public synchronized int size() {
        if (this.encoded != null) {
            parse();
        }
        return super.size();
    }

    @Override // org.spongycastle.asn1.ASN1Sequence, org.spongycastle.asn1.ASN1Primitive
    ASN1Primitive toDERObject() {
        if (this.encoded != null) {
            parse();
        }
        return super.toDERObject();
    }

    @Override // org.spongycastle.asn1.ASN1Sequence, org.spongycastle.asn1.ASN1Primitive
    ASN1Primitive toDLObject() {
        if (this.encoded != null) {
            parse();
        }
        return super.toDLObject();
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    int encodedLength() throws IOException {
        byte[] bArr = this.encoded;
        if (bArr != null) {
            return StreamUtil.calculateBodyLength(bArr.length) + 1 + this.encoded.length;
        }
        return super.toDLObject().encodedLength();
    }

    @Override // org.spongycastle.asn1.ASN1Sequence, org.spongycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        byte[] bArr = this.encoded;
        if (bArr != null) {
            aSN1OutputStream.writeEncoded(48, bArr);
        } else {
            super.toDLObject().encode(aSN1OutputStream);
        }
    }
}
