package org.spongycastle.cert.ocsp;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.ocsp.CertID;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.operator.DigestCalculator;
import org.spongycastle.operator.DigestCalculatorProvider;
import org.spongycastle.operator.OperatorCreationException;

/* loaded from: classes4.dex */
public class CertificateID {
    public static final AlgorithmIdentifier HASH_SHA1 = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);
    private final CertID id;

    public CertID toASN1Primitive() {
        return this.id;
    }

    public CertificateID(CertID certID) {
        if (certID == null) {
            throw new IllegalArgumentException("'id' cannot be null");
        }
        this.id = certID;
    }

    public CertificateID(DigestCalculator digestCalculator, X509CertificateHolder x509CertificateHolder, BigInteger bigInteger) throws OCSPException {
        this.id = createCertID(digestCalculator, x509CertificateHolder, new ASN1Integer(bigInteger));
    }

    public ASN1ObjectIdentifier getHashAlgOID() {
        return this.id.getHashAlgorithm().getAlgorithm();
    }

    public byte[] getIssuerNameHash() {
        return this.id.getIssuerNameHash().getOctets();
    }

    public byte[] getIssuerKeyHash() {
        return this.id.getIssuerKeyHash().getOctets();
    }

    public BigInteger getSerialNumber() {
        return this.id.getSerialNumber().getValue();
    }

    public boolean matchesIssuer(X509CertificateHolder x509CertificateHolder, DigestCalculatorProvider digestCalculatorProvider) throws OCSPException {
        try {
            return createCertID(digestCalculatorProvider.get(this.id.getHashAlgorithm()), x509CertificateHolder, this.id.getSerialNumber()).equals(this.id);
        } catch (OperatorCreationException e) {
            throw new OCSPException("unable to create digest calculator: " + e.getMessage(), e);
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof CertificateID) {
            return this.id.toASN1Primitive().equals(((CertificateID) obj).id.toASN1Primitive());
        }
        return false;
    }

    public int hashCode() {
        return this.id.toASN1Primitive().hashCode();
    }

    public static CertificateID deriveCertificateID(CertificateID certificateID, BigInteger bigInteger) {
        return new CertificateID(new CertID(certificateID.id.getHashAlgorithm(), certificateID.id.getIssuerNameHash(), certificateID.id.getIssuerKeyHash(), new ASN1Integer(bigInteger)));
    }

    private static CertID createCertID(DigestCalculator digestCalculator, X509CertificateHolder x509CertificateHolder, ASN1Integer aSN1Integer) throws OCSPException, IOException {
        try {
            OutputStream outputStream = digestCalculator.getOutputStream();
            outputStream.write(x509CertificateHolder.toASN1Structure().getSubject().getEncoded("DER"));
            outputStream.close();
            DEROctetString dEROctetString = new DEROctetString(digestCalculator.getDigest());
            SubjectPublicKeyInfo subjectPublicKeyInfo = x509CertificateHolder.getSubjectPublicKeyInfo();
            OutputStream outputStream2 = digestCalculator.getOutputStream();
            outputStream2.write(subjectPublicKeyInfo.getPublicKeyData().getBytes());
            outputStream2.close();
            return new CertID(digestCalculator.getAlgorithmIdentifier(), dEROctetString, new DEROctetString(digestCalculator.getDigest()), aSN1Integer);
        } catch (Exception e) {
            throw new OCSPException("problem creating ID: " + e, e);
        }
    }
}
