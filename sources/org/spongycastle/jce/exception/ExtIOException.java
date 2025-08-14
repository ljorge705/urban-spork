package org.spongycastle.jce.exception;

import java.io.IOException;

/* loaded from: classes7.dex */
public class ExtIOException extends IOException implements ExtException {
    private Throwable cause;

    @Override // java.lang.Throwable, org.spongycastle.jce.exception.ExtException
    public Throwable getCause() {
        return this.cause;
    }

    public ExtIOException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
