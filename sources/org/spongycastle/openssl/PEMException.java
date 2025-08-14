package org.spongycastle.openssl;

import java.io.IOException;

/* loaded from: classes7.dex */
public class PEMException extends IOException {
    Exception underlying;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.underlying;
    }

    public Exception getUnderlyingException() {
        return this.underlying;
    }

    public PEMException(String str) {
        super(str);
    }

    public PEMException(String str, Exception exc) {
        super(str);
        this.underlying = exc;
    }
}
