package org.spongycastle.cert.ocsp;

/* loaded from: classes4.dex */
public class OCSPException extends Exception {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public OCSPException(String str) {
        super(str);
    }

    public OCSPException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
