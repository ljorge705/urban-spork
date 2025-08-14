package org.spongycastle.pkcs;

import java.io.IOException;

/* loaded from: classes7.dex */
public class PKCSIOException extends IOException {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public PKCSIOException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    public PKCSIOException(String str) {
        super(str);
    }
}
