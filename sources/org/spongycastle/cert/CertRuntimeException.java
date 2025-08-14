package org.spongycastle.cert;

/* loaded from: classes4.dex */
public class CertRuntimeException extends RuntimeException {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public CertRuntimeException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
