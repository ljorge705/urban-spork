package org.spongycastle.crypto.io;

import java.io.IOException;

/* loaded from: classes2.dex */
public class CipherIOException extends IOException {
    private static final long serialVersionUID = 1;
    private final Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public CipherIOException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
