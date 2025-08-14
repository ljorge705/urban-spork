package org.spongycastle.pkcs;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.pkcs.Attribute;
import org.spongycastle.asn1.pkcs.CertificationRequest;
import org.spongycastle.asn1.pkcs.CertificationRequestInfo;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.operator.ContentSigner;

/* loaded from: classes7.dex */
public class PKCS10CertificationRequestBuilder {
    private List attributes = new ArrayList();
    private boolean leaveOffEmpty = false;
    private SubjectPublicKeyInfo publicKeyInfo;
    private X500Name subject;

    public PKCS10CertificationRequestBuilder setLeaveOffEmptyAttributes(boolean z) {
        this.leaveOffEmpty = z;
        return this;
    }

    public PKCS10CertificationRequestBuilder(X500Name x500Name, SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.subject = x500Name;
        this.publicKeyInfo = subjectPublicKeyInfo;
    }

    public PKCS10CertificationRequestBuilder addAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.attributes.add(new Attribute(aSN1ObjectIdentifier, new DERSet(aSN1Encodable)));
        return this;
    }

    public PKCS10CertificationRequestBuilder addAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable[] aSN1EncodableArr) {
        this.attributes.add(new Attribute(aSN1ObjectIdentifier, new DERSet(aSN1EncodableArr)));
        return this;
    }

    public PKCS10CertificationRequest build(ContentSigner contentSigner) throws IOException {
        CertificationRequestInfo certificationRequestInfo;
        if (!this.attributes.isEmpty()) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            Iterator it = this.attributes.iterator();
            while (it.hasNext()) {
                aSN1EncodableVector.add(Attribute.getInstance(it.next()));
            }
            certificationRequestInfo = new CertificationRequestInfo(this.subject, this.publicKeyInfo, new DERSet(aSN1EncodableVector));
        } else if (this.leaveOffEmpty) {
            certificationRequestInfo = new CertificationRequestInfo(this.subject, this.publicKeyInfo, (ASN1Set) null);
        } else {
            certificationRequestInfo = new CertificationRequestInfo(this.subject, this.publicKeyInfo, new DERSet());
        }
        try {
            OutputStream outputStream = contentSigner.getOutputStream();
            outputStream.write(certificationRequestInfo.getEncoded("DER"));
            outputStream.close();
            return new PKCS10CertificationRequest(new CertificationRequest(certificationRequestInfo, contentSigner.getAlgorithmIdentifier(), new DERBitString(contentSigner.getSignature())));
        } catch (IOException unused) {
            throw new IllegalStateException("cannot produce certification request signature");
        }
    }
}
