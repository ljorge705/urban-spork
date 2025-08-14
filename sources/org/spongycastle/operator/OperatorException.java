package org.spongycastle.operator;

/* loaded from: classes7.dex */
public class OperatorException extends Exception {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public OperatorException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    public OperatorException(String str) {
        super(str);
    }
}
