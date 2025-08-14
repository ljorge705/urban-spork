package org.spongycastle.x509;

import java.security.cert.CertificateEncodingException;

/* loaded from: classes7.dex */
class ExtCertificateEncodingException extends CertificateEncodingException {
    Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    ExtCertificateEncodingException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
