package org.spongycastle.util.encoders;

/* loaded from: classes7.dex */
public class DecoderException extends IllegalStateException {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    DecoderException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
