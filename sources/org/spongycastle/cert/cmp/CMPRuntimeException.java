package org.spongycastle.cert.cmp;

/* loaded from: classes4.dex */
public class CMPRuntimeException extends RuntimeException {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public CMPRuntimeException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
