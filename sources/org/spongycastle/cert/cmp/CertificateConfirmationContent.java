package org.spongycastle.cert.cmp;

import org.spongycastle.asn1.cmp.CertConfirmContent;
import org.spongycastle.asn1.cmp.CertStatus;
import org.spongycastle.operator.DefaultDigestAlgorithmIdentifierFinder;
import org.spongycastle.operator.DigestAlgorithmIdentifierFinder;

/* loaded from: classes4.dex */
public class CertificateConfirmationContent {
    private CertConfirmContent content;
    private DigestAlgorithmIdentifierFinder digestAlgFinder;

    public CertConfirmContent toASN1Structure() {
        return this.content;
    }

    public CertificateConfirmationContent(CertConfirmContent certConfirmContent) {
        this(certConfirmContent, new DefaultDigestAlgorithmIdentifierFinder());
    }

    public CertificateConfirmationContent(CertConfirmContent certConfirmContent, DigestAlgorithmIdentifierFinder digestAlgorithmIdentifierFinder) {
        this.digestAlgFinder = digestAlgorithmIdentifierFinder;
        this.content = certConfirmContent;
    }

    public CertificateStatus[] getStatusMessages() {
        CertStatus[] certStatusArray = this.content.toCertStatusArray();
        int length = certStatusArray.length;
        CertificateStatus[] certificateStatusArr = new CertificateStatus[length];
        for (int i = 0; i != length; i++) {
            certificateStatusArr[i] = new CertificateStatus(this.digestAlgFinder, certStatusArray[i]);
        }
        return certificateStatusArr;
    }
}
