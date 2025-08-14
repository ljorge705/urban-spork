package org.spongycastle.cms;

import java.io.IOException;

/* loaded from: classes4.dex */
public class CMSStreamException extends IOException {
    private final Throwable underlying;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.underlying;
    }

    CMSStreamException(String str) {
        super(str);
        this.underlying = null;
    }

    CMSStreamException(String str, Throwable th) {
        super(str);
        this.underlying = th;
    }
}
