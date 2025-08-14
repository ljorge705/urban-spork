package org.spongycastle.asn1;

import java.io.IOException;

/* loaded from: classes4.dex */
public class DERNull extends ASN1Null {
    public static final DERNull INSTANCE = new DERNull();
    private static final byte[] zeroBytes = new byte[0];

    @Override // org.spongycastle.asn1.ASN1Primitive
    int encodedLength() {
        return 2;
    }

    @Override // org.spongycastle.asn1.ASN1Primitive
    boolean isConstructed() {
        return false;
    }

    @Override // org.spongycastle.asn1.ASN1Null, org.spongycastle.asn1.ASN1Primitive
    void encode(ASN1OutputStream aSN1OutputStream) throws IOException {
        aSN1OutputStream.writeEncoded(5, zeroBytes);
    }
}
