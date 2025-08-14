package com.adobe.internal.xmp;

/* loaded from: classes5.dex */
public class XMPException extends Exception {
    private int errorCode;

    public int getErrorCode() {
        return this.errorCode;
    }

    public XMPException(String str, int i) {
        super(str);
        this.errorCode = i;
    }

    public XMPException(String str, int i, Throwable th) {
        super(str, th);
        this.errorCode = i;
    }
}
