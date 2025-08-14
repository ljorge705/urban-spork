package org.spongycastle.cert;

/* loaded from: classes4.dex */
public class CertException extends Exception {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public CertException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    public CertException(String str) {
        super(str);
    }
}
