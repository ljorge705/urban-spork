package net.sf.scuba.smartcards;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import java.io.IOException;

/* loaded from: classes4.dex */
public class IsoDepCardService extends CardService {
    private int apduCount = 0;
    private IsoDep isoDep;

    public IsoDepCardService(IsoDep isoDep) {
        this.isoDep = isoDep;
    }

    @Override // net.sf.scuba.smartcards.CardService
    public void open() throws IOException, CardServiceException {
        if (isOpen()) {
            return;
        }
        try {
            this.isoDep.connect();
            if (!this.isoDep.isConnected()) {
                throw new CardServiceException("Failed to connect");
            }
            this.state = 1;
        } catch (IOException e) {
            throw new CardServiceException("Failed to connect", e);
        }
    }

    @Override // net.sf.scuba.smartcards.CardService
    public boolean isOpen() {
        if (this.isoDep.isConnected()) {
            this.state = 1;
            return true;
        }
        this.state = 0;
        return false;
    }

    @Override // net.sf.scuba.smartcards.CardService
    public ResponseAPDU transmit(CommandAPDU commandAPDU) throws IOException, CardServiceException {
        try {
            if (!this.isoDep.isConnected()) {
                throw new CardServiceException("Not connected");
            }
            byte[] bArrTransceive = this.isoDep.transceive(commandAPDU.getBytes());
            if (bArrTransceive == null || bArrTransceive.length < 2) {
                throw new CardServiceException("Failed response");
            }
            ResponseAPDU responseAPDU = new ResponseAPDU(bArrTransceive);
            int i = this.apduCount + 1;
            this.apduCount = i;
            notifyExchangedAPDU(new APDUEvent(this, "ISODep", i, commandAPDU, responseAPDU));
            return responseAPDU;
        } catch (CardServiceException e) {
            throw e;
        } catch (Exception e2) {
            throw new CardServiceException("Could not tranceive APDU", e2);
        }
    }

    @Override // net.sf.scuba.smartcards.CardService
    public byte[] getATR() {
        Tag tag;
        IsoDep isoDep = this.isoDep;
        if (isoDep == null || (tag = isoDep.getTag()) == null) {
            return null;
        }
        if (NfcA.get(tag) != null) {
            return this.isoDep.getHistoricalBytes();
        }
        if (NfcB.get(tag) != null) {
            return this.isoDep.getHiLayerResponse();
        }
        return this.isoDep.getHistoricalBytes();
    }

    @Override // net.sf.scuba.smartcards.CardService
    public boolean isExtendedAPDULengthSupported() {
        return this.isoDep.isExtendedLengthApduSupported();
    }

    @Override // net.sf.scuba.smartcards.CardService
    public void close() throws IOException {
        try {
            this.isoDep.close();
            this.state = 0;
        } catch (IOException unused) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x001d, code lost:
    
        return false;
     */
    @Override // net.sf.scuba.smartcards.CardService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean isConnectionLost(java.lang.Exception r4) {
        /*
            r3 = this;
            boolean r0 = r3.isDirectConnectionLost(r4)
            r1 = 1
            if (r0 == 0) goto L8
            return r1
        L8:
            r0 = 0
            if (r4 != 0) goto Lc
            return r0
        Lc:
            java.lang.Throwable r2 = r4.getCause()
            if (r2 == 0) goto L1d
            if (r4 == r2) goto L1d
            boolean r4 = r3.isDirectConnectionLost(r2)
            if (r4 == 0) goto L1b
            return r1
        L1b:
            r4 = r2
            goto Lc
        L1d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: net.sf.scuba.smartcards.IsoDepCardService.isConnectionLost(java.lang.Exception):boolean");
    }

    private boolean isDirectConnectionLost(Throwable th) {
        if (!isISODepConnected()) {
            return true;
        }
        if (th == null) {
            return false;
        }
        String name = th.getClass().getName();
        if (name != null && name.contains("TagLostException")) {
            return true;
        }
        String message = th.getMessage();
        if (message == null) {
            message = "";
        }
        if (message.toLowerCase().contains("tag was lost")) {
            return true;
        }
        return (th instanceof CardServiceException) && (message.toLowerCase().contains("not connected") || message.toLowerCase().contains("failed response"));
    }

    private boolean isISODepConnected() {
        try {
            return this.isoDep.isConnected();
        } catch (Exception unused) {
            return false;
        }
    }
}
