package org.ejbca.cvc;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERTaggedObject;

/* loaded from: classes4.dex */
public class OIDField extends AbstractDataField {
    private static final long serialVersionUID = 5212215839749666908L;
    private String id;

    public String getValue() {
        return this.id;
    }

    @Override // org.ejbca.cvc.AbstractDataField
    protected String valueAsText() {
        return this.id;
    }

    OIDField() {
        super(CVCTagEnum.OID);
    }

    OIDField(String str) {
        this();
        this.id = str;
    }

    OIDField(byte[] bArr) {
        this();
        this.id = ASN1ObjectIdentifier.getInstance(new DERTaggedObject(true, 0, (ASN1Encodable) new DEROctetString(bArr)), false).getId();
    }

    @Override // org.ejbca.cvc.AbstractDataField
    protected byte[] getEncoded() {
        try {
            byte[] encoded = new ASN1ObjectIdentifier(this.id).getEncoded();
            int length = encoded.length - 2;
            byte[] bArr = new byte[length];
            System.arraycopy(encoded, 2, bArr, 0, length);
            return bArr;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public String toString() {
        return getValue();
    }

    public boolean equals(Object obj) {
        if (obj instanceof OIDField) {
            return this.id.equals(((OIDField) obj).getValue());
        }
        return false;
    }
}
