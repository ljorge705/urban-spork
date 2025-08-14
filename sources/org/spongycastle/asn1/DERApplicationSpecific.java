package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.spongycastle.util.encoders.Hex;

/* loaded from: classes4.dex */
public class DERApplicationSpecific extends ASN1ApplicationSpecific {
    DERApplicationSpecific(boolean z, int i, byte[] bArr) {
        super(z, i, bArr);
    }

    public DERApplicationSpecific(int i, byte[] bArr) {
        this(false, i, bArr);
    }

    public DERApplicationSpecific(int i, ASN1Encodable aSN1Encodable) throws IOException {
        this(true, i, aSN1Encodable);
    }

    public DERApplicationSpecific(boolean z, int i, ASN1Encodable aSN1Encodable) throws IOException {
        super(z || aSN1Encodable.toASN1Primitive().isConstructed(), i, getEncoding(z, aSN1Encodable));
    }

    private static byte[] getEncoding(boolean z, ASN1Encodable aSN1Encodable) throws IOException {
        byte[] encoded = aSN1Encodable.toASN1Primitive().getEncoded("DER");
        if (z) {
            return encoded;
        }
        int lengthOfHeader = getLengthOfHeader(encoded);
        int length = encoded.length - lengthOfHeader;
        byte[] bArr = new byte[length];
        System.arraycopy(encoded, lengthOfHeader, bArr, 0, length);
        return bArr;
    }

    public DERApplicationSpecific(int i, ASN1EncodableVector aSN1EncodableVector) {
        super(true, i, getEncodedVector(aSN1EncodableVector));
    }

    private static byte[] getEncodedVector(ASN1EncodableVector aSN1EncodableVector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != aSN1EncodableVector.size(); i++) {
            try {
                byteArrayOutputStream.write(((ASN1Object) aSN1EncodableVector.get(i)).getEncoded("DER"));
            } catch (IOException e) {
                throw new ASN1ParsingException("malformed object: " + e, e);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override // org.spongycastle.asn1.ASN1ApplicationSpecific, org.spongycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(this.isConstructed ? 96 : 64, this.tag, this.octets);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("[");
        if (isConstructed()) {
            stringBuffer.append("CONSTRUCTED ");
        }
        stringBuffer.append("APPLICATION ");
        stringBuffer.append(Integer.toString(getApplicationTag()));
        stringBuffer.append("]");
        if (this.octets != null) {
            stringBuffer.append(" #");
            stringBuffer.append(Hex.toHexString(this.octets));
        } else {
            stringBuffer.append(" #null");
        }
        stringBuffer.append(StringUtils.SPACE);
        return stringBuffer.toString();
    }
}
