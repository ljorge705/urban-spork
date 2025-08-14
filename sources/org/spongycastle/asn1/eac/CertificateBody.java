package org.spongycastle.asn1.eac;

import java.io.IOException;
import org.spongycastle.asn1.ASN1ApplicationSpecific;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.asn1.DEROctetString;

/* loaded from: classes4.dex */
public class CertificateBody extends ASN1Object {
    private static final int CAR = 2;
    private static final int CEfD = 32;
    private static final int CExD = 64;
    private static final int CHA = 16;
    private static final int CHR = 8;
    private static final int CPI = 1;
    private static final int PK = 4;
    public static final int profileType = 127;
    public static final int requestType = 13;
    private DERApplicationSpecific certificateEffectiveDate;
    private DERApplicationSpecific certificateExpirationDate;
    private CertificateHolderAuthorization certificateHolderAuthorization;
    private DERApplicationSpecific certificateHolderReference;
    private DERApplicationSpecific certificateProfileIdentifier;
    private int certificateType = 0;
    private DERApplicationSpecific certificationAuthorityReference;
    private PublicKeyDataObject publicKey;
    ASN1InputStream seq;

    private void setCertificateHolderAuthorization(CertificateHolderAuthorization certificateHolderAuthorization) {
        this.certificateHolderAuthorization = certificateHolderAuthorization;
        this.certificateType |= 16;
    }

    public DERApplicationSpecific getCertificateProfileIdentifier() {
        return this.certificateProfileIdentifier;
    }

    public int getCertificateType() {
        return this.certificateType;
    }

    public PublicKeyDataObject getPublicKey() {
        return this.publicKey;
    }

    private void setIso7816CertificateBody(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException, IllegalArgumentException {
        if (aSN1ApplicationSpecific.getApplicationTag() != 78) {
            throw new IOException("Bad tag : not an iso7816 CERTIFICATE_CONTENT_TEMPLATE");
        }
        ASN1InputStream aSN1InputStream = new ASN1InputStream(aSN1ApplicationSpecific.getContents());
        while (true) {
            ASN1Primitive object = aSN1InputStream.readObject();
            if (object != null) {
                if (object instanceof DERApplicationSpecific) {
                    DERApplicationSpecific dERApplicationSpecific = (DERApplicationSpecific) object;
                    int applicationTag = dERApplicationSpecific.getApplicationTag();
                    if (applicationTag == 2) {
                        setCertificationAuthorityReference(dERApplicationSpecific);
                    } else if (applicationTag == 32) {
                        setCertificateHolderReference(dERApplicationSpecific);
                    } else if (applicationTag == 41) {
                        setCertificateProfileIdentifier(dERApplicationSpecific);
                    } else if (applicationTag == 73) {
                        setPublicKey(PublicKeyDataObject.getInstance(dERApplicationSpecific.getObject(16)));
                    } else if (applicationTag == 76) {
                        setCertificateHolderAuthorization(new CertificateHolderAuthorization(dERApplicationSpecific));
                    } else if (applicationTag == 36) {
                        setCertificateExpirationDate(dERApplicationSpecific);
                    } else if (applicationTag == 37) {
                        setCertificateEffectiveDate(dERApplicationSpecific);
                    } else {
                        this.certificateType = 0;
                        throw new IOException("Not a valid iso7816 DERApplicationSpecific tag " + dERApplicationSpecific.getApplicationTag());
                    }
                } else {
                    throw new IOException("Not a valid iso7816 content : not a DERApplicationSpecific Object :" + EACTags.encodeTag(aSN1ApplicationSpecific) + object.getClass());
                }
            } else {
                aSN1InputStream.close();
                return;
            }
        }
    }

    public CertificateBody(DERApplicationSpecific dERApplicationSpecific, CertificationAuthorityReference certificationAuthorityReference, PublicKeyDataObject publicKeyDataObject, CertificateHolderReference certificateHolderReference, CertificateHolderAuthorization certificateHolderAuthorization, PackedDate packedDate, PackedDate packedDate2) throws IllegalArgumentException {
        setCertificateProfileIdentifier(dERApplicationSpecific);
        setCertificationAuthorityReference(new DERApplicationSpecific(2, certificationAuthorityReference.getEncoded()));
        setPublicKey(publicKeyDataObject);
        setCertificateHolderReference(new DERApplicationSpecific(32, certificateHolderReference.getEncoded()));
        setCertificateHolderAuthorization(certificateHolderAuthorization);
        try {
            setCertificateEffectiveDate(new DERApplicationSpecific(false, 37, (ASN1Encodable) new DEROctetString(packedDate.getEncoding())));
            setCertificateExpirationDate(new DERApplicationSpecific(false, 36, (ASN1Encodable) new DEROctetString(packedDate2.getEncoding())));
        } catch (IOException e) {
            throw new IllegalArgumentException("unable to encode dates: " + e.getMessage());
        }
    }

    private CertificateBody(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException, IllegalArgumentException {
        setIso7816CertificateBody(aSN1ApplicationSpecific);
    }

    private ASN1Primitive profileToASN1Object() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certificateProfileIdentifier);
        aSN1EncodableVector.add(this.certificationAuthorityReference);
        aSN1EncodableVector.add(new DERApplicationSpecific(false, 73, (ASN1Encodable) this.publicKey));
        aSN1EncodableVector.add(this.certificateHolderReference);
        aSN1EncodableVector.add(this.certificateHolderAuthorization);
        aSN1EncodableVector.add(this.certificateEffectiveDate);
        aSN1EncodableVector.add(this.certificateExpirationDate);
        return new DERApplicationSpecific(78, aSN1EncodableVector);
    }

    private void setCertificateProfileIdentifier(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() != 41) {
            throw new IllegalArgumentException("Not an Iso7816Tags.INTERCHANGE_PROFILE tag :" + EACTags.encodeTag(dERApplicationSpecific));
        }
        this.certificateProfileIdentifier = dERApplicationSpecific;
        this.certificateType |= 1;
    }

    private void setCertificateHolderReference(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() != 32) {
            throw new IllegalArgumentException("Not an Iso7816Tags.CARDHOLDER_NAME tag");
        }
        this.certificateHolderReference = dERApplicationSpecific;
        this.certificateType |= 8;
    }

    private void setCertificationAuthorityReference(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() != 2) {
            throw new IllegalArgumentException("Not an Iso7816Tags.ISSUER_IDENTIFICATION_NUMBER tag");
        }
        this.certificationAuthorityReference = dERApplicationSpecific;
        this.certificateType |= 2;
    }

    private void setPublicKey(PublicKeyDataObject publicKeyDataObject) {
        this.publicKey = PublicKeyDataObject.getInstance(publicKeyDataObject);
        this.certificateType |= 4;
    }

    private ASN1Primitive requestToASN1Object() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certificateProfileIdentifier);
        aSN1EncodableVector.add(new DERApplicationSpecific(false, 73, (ASN1Encodable) this.publicKey));
        aSN1EncodableVector.add(this.certificateHolderReference);
        return new DERApplicationSpecific(78, aSN1EncodableVector);
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        try {
            int i = this.certificateType;
            if (i == 127) {
                return profileToASN1Object();
            }
            if (i == 13) {
                return requestToASN1Object();
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    public static CertificateBody getInstance(Object obj) throws IOException {
        if (obj instanceof CertificateBody) {
            return (CertificateBody) obj;
        }
        if (obj != null) {
            return new CertificateBody(ASN1ApplicationSpecific.getInstance(obj));
        }
        return null;
    }

    public PackedDate getCertificateEffectiveDate() {
        if ((this.certificateType & 32) == 32) {
            return new PackedDate(this.certificateEffectiveDate.getContents());
        }
        return null;
    }

    private void setCertificateEffectiveDate(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() != 37) {
            throw new IllegalArgumentException("Not an Iso7816Tags.APPLICATION_EFFECTIVE_DATE tag :" + EACTags.encodeTag(dERApplicationSpecific));
        }
        this.certificateEffectiveDate = dERApplicationSpecific;
        this.certificateType |= 32;
    }

    public PackedDate getCertificateExpirationDate() throws IOException {
        if ((this.certificateType & 64) == 64) {
            return new PackedDate(this.certificateExpirationDate.getContents());
        }
        throw new IOException("certificate Expiration Date not set");
    }

    private void setCertificateExpirationDate(DERApplicationSpecific dERApplicationSpecific) throws IllegalArgumentException {
        if (dERApplicationSpecific.getApplicationTag() != 36) {
            throw new IllegalArgumentException("Not an Iso7816Tags.APPLICATION_EXPIRATION_DATE tag");
        }
        this.certificateExpirationDate = dERApplicationSpecific;
        this.certificateType |= 64;
    }

    public CertificateHolderAuthorization getCertificateHolderAuthorization() throws IOException {
        if ((this.certificateType & 16) == 16) {
            return this.certificateHolderAuthorization;
        }
        throw new IOException("Certificate Holder Authorisation not set");
    }

    public CertificateHolderReference getCertificateHolderReference() {
        return new CertificateHolderReference(this.certificateHolderReference.getContents());
    }

    public CertificationAuthorityReference getCertificationAuthorityReference() throws IOException {
        if ((this.certificateType & 2) == 2) {
            return new CertificationAuthorityReference(this.certificationAuthorityReference.getContents());
        }
        throw new IOException("Certification authority reference not set");
    }
}
