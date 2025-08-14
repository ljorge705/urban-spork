package org.spongycastle.crypto.tls;

import java.io.IOException;

/* loaded from: classes7.dex */
public class TlsException extends IOException {
    protected Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public TlsException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
