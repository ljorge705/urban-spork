package org.spongycastle.asn1;

import java.io.IOException;

/* loaded from: classes4.dex */
public class ASN1Exception extends IOException {
    private Throwable cause;

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.cause;
    }

    ASN1Exception(String str) {
        super(str);
    }

    ASN1Exception(String str, Throwable th) {
        super(str);
        this.cause = th;
    }
}
