package org.spongycastle.jce.exception;

import java.security.cert.CertificateEncodingException;

/* loaded from: classes7.dex */
public class ExtCertificateEncodingException extends CertificateEncodingException implements ExtException {
    private Throwable cause;

    @Override // java.lang.Throwable, org.spongycastle.jce.exception.ExtException
    public Throwable getCause() {
        return this.cause;
    }

    public ExtCertificateEncodingException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
