package org.jmrtd.protocol;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.scuba.smartcards.APDUWrapper;
import net.sf.scuba.smartcards.CardService;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.smartcards.CommandAPDU;
import net.sf.scuba.smartcards.ResponseAPDU;
import net.sf.scuba.util.Hex;
import org.jmrtd.APDULevelAACapable;

/* loaded from: classes4.dex */
public class AAAPDUSender implements APDULevelAACapable {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd.protocol");
    private SecureMessagingAPDUSender secureMessagingSender;

    public AAAPDUSender(CardService cardService) {
        this.secureMessagingSender = new SecureMessagingAPDUSender(cardService);
    }

    @Override // org.jmrtd.APDULevelAACapable
    public synchronized byte[] sendInternalAuthenticate(APDUWrapper aPDUWrapper, int i, byte[] bArr) throws CardServiceException {
        ResponseAPDU responseAPDUTransmit;
        int sw;
        short s;
        if (bArr != null) {
            if (bArr.length == 8) {
                int i2 = i <= 1848 ? 256 : 65536;
                CommandAPDU commandAPDU = new CommandAPDU(0, -120, 0, 0, bArr, i2);
                byte[] data = null;
                try {
                    responseAPDUTransmit = this.secureMessagingSender.transmit(aPDUWrapper, commandAPDU);
                } catch (CardServiceException e) {
                    e = e;
                    responseAPDUTransmit = null;
                }
                try {
                    sw = responseAPDUTransmit.getSW();
                } catch (CardServiceException e2) {
                    e = e2;
                    LOGGER.log(Level.INFO, "Exception during transmission of command APDU = " + Hex.bytesToHexString(commandAPDU.getBytes()), (Throwable) e);
                    sw = e.getSW();
                    s = (short) sw;
                    if (s != -28672) {
                    }
                    if ((65280 & s) != 24832) {
                    }
                    if (responseAPDUTransmit == null) {
                    }
                    throw new CardServiceException("Internal Authenticate failed", s);
                }
                s = (short) sw;
                if (s != -28672 && responseAPDUTransmit != null) {
                    return responseAPDUTransmit.getData();
                }
                if ((65280 & s) != 24832 && i2 == 256) {
                    byte[] data2 = responseAPDUTransmit == null ? null : responseAPDUTransmit.getData();
                    ResponseAPDU responseAPDUTransmit2 = this.secureMessagingSender.transmit(aPDUWrapper, new CommandAPDU(0, -120, 0, 0, bArr, 65536));
                    if (responseAPDUTransmit2 != null) {
                        data = responseAPDUTransmit2.getData();
                    }
                    if (data2 == null && data == null) {
                        throw new CardServiceException("Internal Authenticate failed", s);
                    }
                    return (data2 == null || data != null) ? (data2 != null || data == null) ? data2.length > data.length ? data2 : data : data : data2;
                }
                if (responseAPDUTransmit == null && responseAPDUTransmit.getData() != null) {
                    LOGGER.warning("Internal Authenticate may not have succeeded, got status word " + Integer.toHexString(65535 & s));
                    return responseAPDUTransmit.getData();
                }
                throw new CardServiceException("Internal Authenticate failed", s);
            }
        }
        throw new IllegalArgumentException("rndIFD wrong length");
    }
}
