package org.spongycastle.asn1.eac;

import java.io.IOException;
import org.spongycastle.asn1.ASN1ApplicationSpecific;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1ParsingException;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.util.Arrays;

/* loaded from: classes4.dex */
public class CVCertificate extends ASN1Object {
    private static int bodyValid = 1;
    private static int signValid = 2;
    private CertificateBody certificateBody;
    private byte[] signature;
    private int valid;

    public CertificateBody getBody() {
        return this.certificateBody;
    }

    private void setPrivateData(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        this.valid = 0;
        if (aSN1ApplicationSpecific.getApplicationTag() == 33) {
            ASN1InputStream aSN1InputStream = new ASN1InputStream(aSN1ApplicationSpecific.getContents());
            while (true) {
                ASN1Primitive object = aSN1InputStream.readObject();
                if (object != null) {
                    if (object instanceof DERApplicationSpecific) {
                        DERApplicationSpecific dERApplicationSpecific = (DERApplicationSpecific) object;
                        int applicationTag = dERApplicationSpecific.getApplicationTag();
                        if (applicationTag == 55) {
                            this.signature = dERApplicationSpecific.getContents();
                            this.valid |= signValid;
                        } else if (applicationTag == 78) {
                            this.certificateBody = CertificateBody.getInstance(dERApplicationSpecific);
                            this.valid |= bodyValid;
                        } else {
                            throw new IOException("Invalid tag, not an Iso7816CertificateStructure :" + dERApplicationSpecific.getApplicationTag());
                        }
                    } else {
                        throw new IOException("Invalid Object, not an Iso7816CertificateStructure");
                    }
                } else {
                    aSN1InputStream.close();
                    if (this.valid != (signValid | bodyValid)) {
                        throw new IOException("invalid CARDHOLDER_CERTIFICATE :" + aSN1ApplicationSpecific.getApplicationTag());
                    }
                    return;
                }
            }
        } else {
            throw new IOException("not a CARDHOLDER_CERTIFICATE :" + aSN1ApplicationSpecific.getApplicationTag());
        }
    }

    public CVCertificate(ASN1InputStream aSN1InputStream) throws IOException {
        initFrom(aSN1InputStream);
    }

    private void initFrom(ASN1InputStream aSN1InputStream) throws IOException {
        while (true) {
            ASN1Primitive object = aSN1InputStream.readObject();
            if (object == null) {
                return;
            }
            if (object instanceof DERApplicationSpecific) {
                setPrivateData((DERApplicationSpecific) object);
            } else {
                throw new IOException("Invalid Input Stream for creating an Iso7816CertificateStructure");
            }
        }
    }

    private CVCertificate(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        setPrivateData(aSN1ApplicationSpecific);
    }

    public CVCertificate(CertificateBody certificateBody, byte[] bArr) throws IOException {
        this.certificateBody = certificateBody;
        this.signature = Arrays.clone(bArr);
        this.valid = this.valid | bodyValid | signValid;
    }

    public static CVCertificate getInstance(Object obj) {
        if (obj instanceof CVCertificate) {
            return (CVCertificate) obj;
        }
        if (obj == null) {
            return null;
        }
        try {
            return new CVCertificate(DERApplicationSpecific.getInstance(obj));
        } catch (IOException e) {
            throw new ASN1ParsingException("unable to parse data: " + e.getMessage(), e);
        }
    }

    public byte[] getSignature() {
        return Arrays.clone(this.signature);
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certificateBody);
        try {
            aSN1EncodableVector.add(new DERApplicationSpecific(false, 55, (ASN1Encodable) new DEROctetString(this.signature)));
            return new DERApplicationSpecific(33, aSN1EncodableVector);
        } catch (IOException unused) {
            throw new IllegalStateException("unable to convert signature!");
        }
    }

    public ASN1ObjectIdentifier getHolderAuthorization() throws IOException {
        return this.certificateBody.getCertificateHolderAuthorization().getOid();
    }

    public PackedDate getEffectiveDate() throws IOException {
        return this.certificateBody.getCertificateEffectiveDate();
    }

    public int getCertificateType() {
        return this.certificateBody.getCertificateType();
    }

    public PackedDate getExpirationDate() throws IOException {
        return this.certificateBody.getCertificateExpirationDate();
    }

    public int getRole() throws IOException {
        return this.certificateBody.getCertificateHolderAuthorization().getAccessRights();
    }

    public CertificationAuthorityReference getAuthorityReference() throws IOException {
        return this.certificateBody.getCertificationAuthorityReference();
    }

    public CertificateHolderReference getHolderReference() throws IOException {
        return this.certificateBody.getCertificateHolderReference();
    }

    public int getHolderAuthorizationRole() throws IOException {
        return this.certificateBody.getCertificateHolderAuthorization().getAccessRights() & 192;
    }

    public Flags getHolderAuthorizationRights() throws IOException {
        return new Flags(this.certificateBody.getCertificateHolderAuthorization().getAccessRights() & 31);
    }
}
