package org.spongycastle.eac;

import java.io.IOException;

/* loaded from: classes7.dex */
public class EACIOException extends IOException {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public EACIOException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    public EACIOException(String str) {
        super(str);
    }
}
