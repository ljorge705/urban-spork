package org.spongycastle.jce.provider;

import java.security.cert.CRLException;

/* loaded from: classes7.dex */
class ExtCRLException extends CRLException {
    Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    ExtCRLException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
