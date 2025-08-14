package org.spongycastle.cms;

/* loaded from: classes4.dex */
public class CMSAttributeTableGenerationException extends CMSRuntimeException {
    Exception e;

    @Override // org.spongycastle.cms.CMSRuntimeException, java.lang.Throwable
    public Throwable getCause() {
        return this.e;
    }

    @Override // org.spongycastle.cms.CMSRuntimeException
    public Exception getUnderlyingException() {
        return this.e;
    }

    public CMSAttributeTableGenerationException(String str) {
        super(str);
    }

    public CMSAttributeTableGenerationException(String str, Exception exc) {
        super(str);
        this.e = exc;
    }
}
