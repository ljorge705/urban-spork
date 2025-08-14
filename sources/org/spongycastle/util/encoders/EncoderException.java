package org.spongycastle.util.encoders;

/* loaded from: classes7.dex */
public class EncoderException extends IllegalStateException {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    EncoderException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
