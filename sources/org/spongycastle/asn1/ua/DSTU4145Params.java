package org.spongycastle.asn1.ua;

import com.google.common.base.Ascii;
import net.sf.scuba.smartcards.ISO7816;
import net.sf.scuba.smartcards.ISOFileInfo;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.util.Arrays;

/* loaded from: classes4.dex */
public class DSTU4145Params extends ASN1Object {
    private static final byte[] DEFAULT_DKE = {-87, ISO7816.INS_UPDATE_BINARY, -21, 69, -15, 60, ISO7816.INS_MANAGE_CHANNEL, -126, Byte.MIN_VALUE, -60, -106, 123, 35, Ascii.US, 94, -83, -10, 88, -21, -92, ISO7816.INS_GET_RESPONSE, 55, 41, 29, 56, -39, 107, -16, 37, ISO7816.INS_GET_DATA, 78, Ascii.ETB, -8, -23, 114, 13, -58, Ascii.NAK, ISO7816.INS_READ_BINARY_STAMPED, 58, 40, -105, 95, 11, -63, -34, -93, ISOFileInfo.FMD_BYTE, 56, -75, ISOFileInfo.FMD_BYTE, -22, ISO7816.INS_UNBLOCK_CHV, Ascii.ETB, -97, ISO7816.INS_WRITE_BINARY, Ascii.DC2, 62, 109, -72, -6, -59, 121, 4};
    private byte[] dke;
    private DSTU4145ECBinary ecbinary;
    private ASN1ObjectIdentifier namedCurve;

    public static byte[] getDefaultDKE() {
        return DEFAULT_DKE;
    }

    public byte[] getDKE() {
        return this.dke;
    }

    public DSTU4145ECBinary getECBinary() {
        return this.ecbinary;
    }

    public ASN1ObjectIdentifier getNamedCurve() {
        return this.namedCurve;
    }

    public boolean isNamedCurve() {
        return this.namedCurve != null;
    }

    public DSTU4145Params(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.dke = DEFAULT_DKE;
        this.namedCurve = aSN1ObjectIdentifier;
    }

    public DSTU4145Params(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr) {
        this.dke = DEFAULT_DKE;
        this.namedCurve = aSN1ObjectIdentifier;
        this.dke = Arrays.clone(bArr);
    }

    public DSTU4145Params(DSTU4145ECBinary dSTU4145ECBinary) {
        this.dke = DEFAULT_DKE;
        this.ecbinary = dSTU4145ECBinary;
    }

    public static DSTU4145Params getInstance(Object obj) {
        DSTU4145Params dSTU4145Params;
        if (obj instanceof DSTU4145Params) {
            return (DSTU4145Params) obj;
        }
        if (obj != null) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(obj);
            if (aSN1Sequence.getObjectAt(0) instanceof ASN1ObjectIdentifier) {
                dSTU4145Params = new DSTU4145Params(ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0)));
            } else {
                dSTU4145Params = new DSTU4145Params(DSTU4145ECBinary.getInstance(aSN1Sequence.getObjectAt(0)));
            }
            if (aSN1Sequence.size() == 2) {
                byte[] octets = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets();
                dSTU4145Params.dke = octets;
                if (octets.length != DEFAULT_DKE.length) {
                    throw new IllegalArgumentException("object parse error");
                }
            }
            return dSTU4145Params;
        }
        throw new IllegalArgumentException("object parse error");
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.namedCurve;
        if (aSN1ObjectIdentifier != null) {
            aSN1EncodableVector.add(aSN1ObjectIdentifier);
        } else {
            aSN1EncodableVector.add(this.ecbinary);
        }
        if (!Arrays.areEqual(this.dke, DEFAULT_DKE)) {
            aSN1EncodableVector.add(new DEROctetString(this.dke));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
