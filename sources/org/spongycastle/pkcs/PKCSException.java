package org.spongycastle.pkcs;

/* loaded from: classes7.dex */
public class PKCSException extends Exception {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public PKCSException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    public PKCSException(String str) {
        super(str);
    }
}
