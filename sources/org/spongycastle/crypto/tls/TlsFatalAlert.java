package org.spongycastle.crypto.tls;

/* loaded from: classes7.dex */
public class TlsFatalAlert extends TlsException {
    protected short alertDescription;

    public short getAlertDescription() {
        return this.alertDescription;
    }

    public TlsFatalAlert(short s) {
        this(s, null);
    }

    public TlsFatalAlert(short s, Throwable th) {
        super(AlertDescription.getText(s), th);
        this.alertDescription = s;
    }
}
