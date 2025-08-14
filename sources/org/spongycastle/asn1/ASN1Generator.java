package org.spongycastle.asn1;

import java.io.OutputStream;

/* loaded from: classes4.dex */
public abstract class ASN1Generator {
    protected OutputStream _out;

    public abstract OutputStream getRawOutputStream();

    public ASN1Generator(OutputStream outputStream) {
        this._out = outputStream;
    }
}
