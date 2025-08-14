package org.spongycastle.operator;

import java.io.IOException;

/* loaded from: classes7.dex */
public class OperatorStreamException extends IOException {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public OperatorStreamException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
