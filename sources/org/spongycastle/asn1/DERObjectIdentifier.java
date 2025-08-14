package org.spongycastle.asn1;

/* loaded from: classes4.dex */
public class DERObjectIdentifier extends ASN1ObjectIdentifier {
    public DERObjectIdentifier(String str) {
        super(str);
    }

    DERObjectIdentifier(byte[] bArr) {
        super(bArr);
    }

    DERObjectIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        super(aSN1ObjectIdentifier, str);
    }
}
