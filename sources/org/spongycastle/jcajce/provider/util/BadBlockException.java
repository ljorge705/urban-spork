package org.spongycastle.jcajce.provider.util;

import javax.crypto.BadPaddingException;

/* loaded from: classes7.dex */
public class BadBlockException extends BadPaddingException {
    private final Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public BadBlockException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
