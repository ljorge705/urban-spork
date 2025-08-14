package org.spongycastle.asn1;

import java.io.IOException;

/* loaded from: classes4.dex */
public class DERBitString extends ASN1BitString {
    private static byte[] toByteArray(byte b) {
        return new byte[]{b};
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    boolean isConstructed() {
        return false;
    }

    public static DERBitString getInstance(Object obj) {
        if (obj == null || (obj instanceof DERBitString)) {
            return (DERBitString) obj;
        }
        if (obj instanceof DLBitString) {
            DLBitString dLBitString = (DLBitString) obj;
            return new DERBitString(dLBitString.data, dLBitString.padBits);
        }
        if (obj instanceof byte[]) {
            try {
                return (DERBitString) fromByteArray((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static DERBitString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (z || (object instanceof DERBitString)) {
            return getInstance(object);
        }
        return fromOctetString(((ASN1OctetString) object).getOctets());
    }

    protected DERBitString(byte b, int i) {
        this(toByteArray(b), i);
    }

    public DERBitString(byte[] bArr, int i) {
        super(bArr, i);
    }

    public DERBitString(byte[] bArr) {
        this(bArr, 0);
    }

    public DERBitString(int i) {
        super(getBytes(i), getPadBits(i));
    }

    public DERBitString(ASN1Encodable aSN1Encodable) throws IOException {
        super(aSN1Encodable.toASN1Primitive().getEncoded("DER"), 0);
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    int encodedLength() {
        return StreamUtil.calculateBodyLength(this.data.length + 1) + 1 + this.data.length + 1;
    }

    @Override // org.spongycastle.asn1.ASN1BitString, org.spongycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        byte[] bArrDerForm = derForm(this.data, this.padBits);
        int length = bArrDerForm.length;
        byte[] bArr = new byte[length + 1];
        bArr[0] = (byte) getPadBits();
        System.arraycopy(bArrDerForm, 0, bArr, 1, length);
        aSN1OutputStream.writeEncoded(3, bArr);
    }

    static DERBitString fromOctetString(byte[] bArr) {
        if (bArr.length < 1) {
            throw new IllegalArgumentException("truncated BIT STRING detected");
        }
        byte b = bArr[0];
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        if (length != 0) {
            System.arraycopy(bArr, 1, bArr2, 0, bArr.length - 1);
        }
        return new DERBitString(bArr2, b);
    }
}
