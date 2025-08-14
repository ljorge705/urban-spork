package org.spongycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes4.dex */
public class DEROutputStream extends ASN1OutputStream {
    @Override // org.spongycastle.asn1.ASN1OutputStream
    ASN1OutputStream getDERSubStream() {
        return this;
    }

    @Override // org.spongycastle.asn1.ASN1OutputStream
    ASN1OutputStream getDLSubStream() {
        return this;
    }

    public DEROutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // org.spongycastle.asn1.ASN1OutputStream
    public void writeObject(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable != null) {
            aSN1Encodable.toASN1Primitive().toDERObject().encode(this);
            return;
        }
        throw new IOException("null object detected");
    }
}
