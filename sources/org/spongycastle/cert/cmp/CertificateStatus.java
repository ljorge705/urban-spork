package org.spongycastle.cert.cmp;

import java.math.BigInteger;
import org.spongycastle.asn1.cmp.CertStatus;
import org.spongycastle.asn1.cmp.PKIStatusInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.operator.DigestAlgorithmIdentifierFinder;
import org.spongycastle.operator.DigestCalculator;
import org.spongycastle.operator.DigestCalculatorProvider;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.util.Arrays;

/* loaded from: classes4.dex */
public class CertificateStatus {
    private CertStatus certStatus;
    private DigestAlgorithmIdentifierFinder digestAlgFinder;

    CertificateStatus(DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder, CertStatus certStatus) {
        this.digestAlgFinder = digestAlgorithmIdentifierFinder;
        this.certStatus = certStatus;
    }

    public PKIStatusInfo getStatusInfo() {
        return this.certStatus.getStatusInfo();
    }

    public BigInteger getCertRequestID() {
        return this.certStatus.getCertReqId().getValue();
    }

    public boolean isVerified(X509CertificateHolder x509CertificateHolder, DigestCalculatorProvider digestCalculatorProvider) throws CMPException {
        AlgorithmIdentifier algorithmIdentifierFind = this.digestAlgFinder.find(x509CertificateHolder.toASN1Structure().getSignatureAlgorithm());
        if (algorithmIdentifierFind == null) {
            throw new CMPException("cannot find algorithm for digest from signature");
        }
        try {
            DigestCalculator digestCalculator = digestCalculatorProvider.get(algorithmIdentifierFind);
            CMPUtil.derEncodeToStream(x509CertificateHolder.toASN1Structure(), digestCalculator.getOutputStream());
            return Arrays.areEqual(this.certStatus.getCertHash().getOctets(), digestCalculator.getDigest());
        } catch (OperatorCreationException e) {
            throw new CMPException("unable to create digester: " + e.getMessage(), e);
        }
    }
}
