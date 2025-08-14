package org.spongycastle.eac;

/* loaded from: classes7.dex */
public class EACException extends Exception {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public EACException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    public EACException(String str) {
        super(str);
    }
}
