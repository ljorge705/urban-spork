package org.spongycastle.cert.ocsp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.spongycastle.asn1.ASN1Exception;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OutputStream;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ocsp.OCSPRequest;
import org.spongycastle.asn1.ocsp.Request;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.cert.CertIOException;
import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.operator.ContentVerifier;
import org.spongycastle.operator.ContentVerifierProvider;

/* loaded from: classes4.dex */
public class OCSPReq {
    private static final X509CertificateHolder[] EMPTY_CERTS = new X509CertificateHolder[0];
    private Extensions extensions;
    private OCSPRequest req;

    public boolean hasExtensions() {
        return this.extensions != null;
    }

    public OCSPReq(OCSPRequest oCSPRequest) {
        this.req = oCSPRequest;
        this.extensions = oCSPRequest.getTbsRequest().getRequestExtensions();
    }

    public OCSPReq(byte[] bArr) throws IOException {
        this(new ASN1InputStream(bArr));
    }

    private OCSPReq(ASN1InputStream aSN1InputStream) throws IOException {
        try {
            OCSPRequest oCSPRequest = OCSPRequest.getInstance(aSN1InputStream.readObject());
            this.req = oCSPRequest;
            if (oCSPRequest == null) {
                throw new CertIOException("malformed request: no request data found");
            }
            this.extensions = oCSPRequest.getTbsRequest().getRequestExtensions();
        } catch (ClassCastException e) {
            throw new CertIOException("malformed request: " + e.getMessage(), e);
        } catch (IllegalArgumentException e2) {
            throw new CertIOException("malformed request: " + e2.getMessage(), e2);
        } catch (ASN1Exception e3) {
            throw new CertIOException("malformed request: " + e3.getMessage(), e3);
        }
    }

    public int getVersionNumber() {
        return this.req.getTbsRequest().getVersion().getValue().intValue() + 1;
    }

    public GeneralName getRequestorName() {
        return GeneralName.getInstance(this.req.getTbsRequest().getRequestorName());
    }

    public Req[] getRequestList() {
        ASN1Sequence requestList = this.req.getTbsRequest().getRequestList();
        int size = requestList.size();
        Req[] reqArr = new Req[size];
        for (int i = 0; i != size; i++) {
            reqArr[i] = new Req(Request.getInstance(requestList.getObjectAt(i)));
        }
        return reqArr;
    }

    public Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extensions extensions = this.extensions;
        if (extensions != null) {
            return extensions.getExtension(aSN1ObjectIdentifier);
        }
        return null;
    }

    public List getExtensionOIDs() {
        return OCSPUtils.getExtensionOIDs(this.extensions);
    }

    public Set getCriticalExtensionOIDs() {
        return OCSPUtils.getCriticalExtensionOIDs(this.extensions);
    }

    public Set getNonCriticalExtensionOIDs() {
        return OCSPUtils.getNonCriticalExtensionOIDs(this.extensions);
    }

    public ASN1ObjectIdentifier getSignatureAlgOID() {
        if (isSigned()) {
            return this.req.getOptionalSignature().getSignatureAlgorithm().getAlgorithm();
        }
        return null;
    }

    public byte[] getSignature() {
        if (isSigned()) {
            return this.req.getOptionalSignature().getSignature().getOctets();
        }
        return null;
    }

    public X509CertificateHolder[] getCerts() {
        ASN1Sequence certs;
        if (this.req.getOptionalSignature() != null && (certs = this.req.getOptionalSignature().getCerts()) != null) {
            int size = certs.size();
            X509CertificateHolder[] x509CertificateHolderArr = new X509CertificateHolder[size];
            for (int i = 0; i != size; i++) {
                x509CertificateHolderArr[i] = new X509CertificateHolder(Certificate.getInstance(certs.getObjectAt(i)));
            }
            return x509CertificateHolderArr;
        }
        return EMPTY_CERTS;
    }

    public boolean isSigned() {
        return this.req.getOptionalSignature() != null;
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) throws OCSPException, IOException {
        if (!isSigned()) {
            throw new OCSPException("attempt to verify signature on unsigned object");
        }
        try {
            ContentVerifier contentVerifier = contentVerifierProvider.get(this.req.getOptionalSignature().getSignatureAlgorithm());
            contentVerifier.getOutputStream().write(this.req.getTbsRequest().getEncoded("DER"));
            return contentVerifier.verify(getSignature());
        } catch (Exception e) {
            throw new OCSPException("exception processing signature: " + e, e);
        }
    }

    public byte[] getEncoded() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new ASN1OutputStream(byteArrayOutputStream).writeObject(this.req);
        return byteArrayOutputStream.toByteArray();
    }
}
