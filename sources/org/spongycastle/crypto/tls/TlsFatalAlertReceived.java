package org.spongycastle.crypto.tls;

/* loaded from: classes7.dex */
public class TlsFatalAlertReceived extends TlsException {
    protected short alertDescription;

    public short getAlertDescription() {
        return this.alertDescription;
    }

    public TlsFatalAlertReceived(short s) {
        super(AlertDescription.getText(s), null);
        this.alertDescription = s;
    }
}
