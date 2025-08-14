package org.spongycastle.util.io.pem;

import java.io.IOException;

/* loaded from: classes7.dex */
public class PemGenerationException extends IOException {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public PemGenerationException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    public PemGenerationException(String str) {
        super(str);
    }
}
