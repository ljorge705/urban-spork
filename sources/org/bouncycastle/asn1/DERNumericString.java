package org.bouncycastle.asn1;

/* loaded from: classes4.dex */
public class DERNumericString extends ASN1NumericString {
    public DERNumericString(String str) {
        this(str, false);
    }

    public DERNumericString(String str, boolean z) {
        super(str, z);
    }

    DERNumericString(byte[] bArr, boolean z) {
        super(bArr, z);
    }
}
