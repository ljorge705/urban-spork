package org.spongycastle.jcajce.provider.asymmetric.util;

import java.security.spec.InvalidKeySpecException;

/* loaded from: classes7.dex */
public class ExtendedInvalidKeySpecException extends InvalidKeySpecException {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public ExtendedInvalidKeySpecException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
