package org.spongycastle.cert.path;

import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.util.Memoable;

/* loaded from: classes4.dex */
public interface CertPathValidation extends Memoable {
    void validate(CertPathValidationContext certPathValidationContext, X509CertificateHolder x509CertificateHolder) throws CertPathValidationException;
}
