package org.spongycastle.util;

/* loaded from: classes7.dex */
public class StoreException extends RuntimeException {
    private Throwable _e;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this._e;
    }

    public StoreException(String str, Throwable th) {
        super(str);
        this._e = th;
    }
}
