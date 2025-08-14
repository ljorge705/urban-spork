package org.spongycastle.asn1.eac;

import java.io.IOException;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1ApplicationSpecific;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ParsingException;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.util.Arrays;

/* loaded from: classes4.dex */
public class CVCertificateRequest extends ASN1Object {
    private static final int bodyValid = 1;
    private static final int signValid = 2;
    private CertificateBody certificateBody;
    private byte[] innerSignature = null;
    private final ASN1ApplicationSpecific original;
    private byte[] outerSignature;

    public CertificateBody getCertificateBody() {
        return this.certificateBody;
    }

    public boolean hasOuterSignature() {
        return this.outerSignature != null;
    }

    private CVCertificateRequest(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        this.outerSignature = null;
        this.original = aSN1ApplicationSpecific;
        if (aSN1ApplicationSpecific.isConstructed() && aSN1ApplicationSpecific.getApplicationTag() == 7) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(aSN1ApplicationSpecific.getObject(16));
            initCertBody(ASN1ApplicationSpecific.getInstance(aSN1Sequence.getObjectAt(0)));
            this.outerSignature = ASN1ApplicationSpecific.getInstance(aSN1Sequence.getObjectAt(aSN1Sequence.size() - 1)).getContents();
            return;
        }
        initCertBody(aSN1ApplicationSpecific);
    }

    private void initCertBody(ASN1ApplicationSpecific aSN1ApplicationSpecific) throws IOException {
        if (aSN1ApplicationSpecific.getApplicationTag() == 33) {
            Enumeration objects = ASN1Sequence.getInstance(aSN1ApplicationSpecific.getObject(16)).getObjects();
            int i = 0;
            while (objects.hasMoreElements()) {
                ASN1ApplicationSpecific aSN1ApplicationSpecific2 = ASN1ApplicationSpecific.getInstance(objects.nextElement());
                int applicationTag = aSN1ApplicationSpecific2.getApplicationTag();
                if (applicationTag == 55) {
                    this.innerSignature = aSN1ApplicationSpecific2.getContents();
                    i |= 2;
                } else if (applicationTag == 78) {
                    this.certificateBody = CertificateBody.getInstance(aSN1ApplicationSpecific2);
                    i |= 1;
                } else {
                    throw new IOException("Invalid tag, not an CV Certificate Request element:" + aSN1ApplicationSpecific2.getApplicationTag());
                }
            }
            if ((i & 3) == 0) {
                throw new IOException("Invalid CARDHOLDER_CERTIFICATE in request:" + aSN1ApplicationSpecific.getApplicationTag());
            }
            return;
        }
        throw new IOException("not a CARDHOLDER_CERTIFICATE in request:" + aSN1ApplicationSpecific.getApplicationTag());
    }

    public static CVCertificateRequest getInstance(Object obj) {
        if (obj instanceof CVCertificateRequest) {
            return (CVCertificateRequest) obj;
        }
        if (obj == null) {
            return null;
        }
        try {
            return new CVCertificateRequest(ASN1ApplicationSpecific.getInstance(obj));
        } catch (IOException e) {
            throw new ASN1ParsingException("unable to parse data: " + e.getMessage(), e);
        }
    }

    public PublicKeyDataObject getPublicKey() {
        return this.certificateBody.getPublicKey();
    }

    public byte[] getInnerSignature() {
        return Arrays.clone(this.innerSignature);
    }

    public byte[] getOuterSignature() {
        return Arrays.clone(this.outerSignature);
    }

    @Override // org.spongycastle.asn1.ASN1Object, org.spongycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1ApplicationSpecific aSN1ApplicationSpecific = this.original;
        if (aSN1ApplicationSpecific != null) {
            return aSN1ApplicationSpecific;
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certificateBody);
        try {
            aSN1EncodableVector.add(new DERApplicationSpecific(false, 55, (ASN1Encodable) new DEROctetString(this.innerSignature)));
            return new DERApplicationSpecific(33, aSN1EncodableVector);
        } catch (IOException unused) {
            throw new IllegalStateException("unable to convert signature!");
        }
    }
}
