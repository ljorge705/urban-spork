package org.spongycastle.cert.cmp;

/* loaded from: classes4.dex */
public class CMPException extends Exception {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public CMPException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    public CMPException(String str) {
        super(str);
    }
}
