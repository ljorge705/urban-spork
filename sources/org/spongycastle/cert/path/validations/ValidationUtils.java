package org.spongycastle.cert.path.validations;

import org.spongycastle.cert.X509CertificateHolder;

/* loaded from: classes4.dex */
class ValidationUtils {
    ValidationUtils() {
    }

    static boolean isSelfIssued(X509CertificateHolder x509CertificateHolder) {
        return x509CertificateHolder.getSubject().equals(x509CertificateHolder.getIssuer());
    }
}
