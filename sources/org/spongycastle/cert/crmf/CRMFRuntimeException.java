package org.spongycastle.cert.crmf;

/* loaded from: classes4.dex */
public class CRMFRuntimeException extends RuntimeException {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public CRMFRuntimeException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
