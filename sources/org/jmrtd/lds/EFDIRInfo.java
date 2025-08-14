package org.jmrtd.lds;

import java.util.Arrays;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DLSequence;

/* loaded from: classes4.dex */
public class EFDIRInfo extends SecurityInfo {
    private static final String EF_DIR_PROTOCOL_OID = "2.23.136.1.1.13";
    private static final long serialVersionUID = 6778691696414558842L;
    private byte[] efDIR;

    @Override // org.jmrtd.lds.SecurityInfo
    public String getObjectIdentifier() {
        return EF_DIR_PROTOCOL_OID;
    }

    @Override // org.jmrtd.lds.SecurityInfo
    public String getProtocolOIDString() {
        return "id-EFDIR";
    }

    public EFDIRInfo(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("Cannot create EFDIRInfo for null");
        }
        this.efDIR = Arrays.copyOf(bArr, bArr.length);
    }

    public byte[] getEFDIR() {
        byte[] bArr = this.efDIR;
        return Arrays.copyOf(bArr, bArr.length);
    }

    @Override // org.jmrtd.lds.SecurityInfo
    public ASN1Primitive getDERObject() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1ObjectIdentifier(EF_DIR_PROTOCOL_OID));
        aSN1EncodableVector.add(ASN1OctetString.getInstance(this.efDIR));
        return DLSequence.getInstance(aSN1EncodableVector);
    }
}
