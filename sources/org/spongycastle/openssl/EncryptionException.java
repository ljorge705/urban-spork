package org.spongycastle.openssl;

/* loaded from: classes7.dex */
public class EncryptionException extends PEMException {
    private Throwable cause;

    @Override // org.spongycastle.openssl.PEMException, java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public EncryptionException(String str) {
        super(str);
    }

    public EncryptionException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
