package org.spongycastle.asn1;

/* loaded from: classes4.dex */
public class ASN1ParsingException extends IllegalStateException {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    public ASN1ParsingException(String str) {
        super(str);
    }

    public ASN1ParsingException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
