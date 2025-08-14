package org.spongycastle.operator;

/* loaded from: classes7.dex */
public class RuntimeOperatorException extends RuntimeException {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public RuntimeOperatorException(String str) {
        super(str);
    }

    public RuntimeOperatorException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
