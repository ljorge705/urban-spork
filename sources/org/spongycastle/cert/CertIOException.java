package org.spongycastle.cert;

import java.io.IOException;

/* loaded from: classes4.dex */
public class CertIOException extends IOException {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public CertIOException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    public CertIOException(String str) {
        super(str);
    }
}
