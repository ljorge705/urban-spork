package org.spongycastle.cert.dane;

/* loaded from: classes4.dex */
public class DANEException extends Exception {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public DANEException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    public DANEException(String str) {
        super(str);
    }
}
