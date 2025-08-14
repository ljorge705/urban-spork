package org.jmrtd.protocol;

import java.security.GeneralSecurityException;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import net.sf.scuba.smartcards.APDUWrapper;
import net.sf.scuba.smartcards.CardService;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.smartcards.CommandAPDU;
import net.sf.scuba.smartcards.ResponseAPDU;
import org.jmrtd.APDULevelBACCapable;
import org.jmrtd.CardServiceProtocolException;
import org.jmrtd.Util;

/* loaded from: classes4.dex */
public class BACAPDUSender implements APDULevelBACCapable {
    private static final Provider BC_PROVIDER = Util.getBouncyCastleProvider();
    private static final IvParameterSpec ZERO_IV_PARAM_SPEC = new IvParameterSpec(new byte[]{0, 0, 0, 0, 0, 0, 0, 0});
    private Cipher cipher;
    private Mac mac;
    private CardService service;

    public BACAPDUSender(CardService cardService) {
        this.service = cardService;
        try {
            this.mac = Mac.getInstance("ISO9797Alg3Mac", BC_PROVIDER);
            this.cipher = Util.getCipher("DESede/CBC/NoPadding");
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Unexpected security exception during initialization", e);
        }
    }

    @Override // org.jmrtd.APDULevelBACCapable
    public synchronized byte[] sendGetChallenge() throws CardServiceException {
        return sendGetChallenge(null);
    }

    public synchronized byte[] sendGetChallenge(APDUWrapper aPDUWrapper) throws CardServiceException {
        byte[] data;
        ResponseAPDU responseAPDUTransmit = this.service.transmit(new CommandAPDU(0, -124, 0, 0, 8));
        data = responseAPDUTransmit.getData();
        if (data == null || data.length != 8) {
            throw new CardServiceException("Get challenge failed", responseAPDUTransmit.getSW());
        }
        return data;
    }

    @Override // org.jmrtd.APDULevelBACCapable
    public synchronized byte[] sendMutualAuth(byte[] bArr, byte[] bArr2, byte[] bArr3, SecretKey secretKey, SecretKey secretKey2) throws CardServiceException {
        byte[] bArrDoFinal;
        byte[] bArr4 = bArr2;
        synchronized (this) {
            if (bArr != null) {
                try {
                    if (bArr.length == 8) {
                        if (bArr4 == null || bArr4.length != 8) {
                            bArr4 = new byte[8];
                        }
                        if (bArr3 == null || bArr3.length != 16) {
                            throw new IllegalArgumentException("kIFD wrong length");
                        }
                        if (secretKey == null) {
                            throw new IllegalArgumentException("kEnc == null");
                        }
                        if (secretKey2 == null) {
                            throw new IllegalArgumentException("kMac == null");
                        }
                        Cipher cipher = this.cipher;
                        IvParameterSpec ivParameterSpec = ZERO_IV_PARAM_SPEC;
                        cipher.init(1, secretKey, ivParameterSpec);
                        byte[] bArr5 = new byte[32];
                        System.arraycopy(bArr, 0, bArr5, 0, 8);
                        System.arraycopy(bArr4, 0, bArr5, 8, 8);
                        System.arraycopy(bArr3, 0, bArr5, 16, 16);
                        byte[] bArrDoFinal2 = this.cipher.doFinal(bArr5);
                        if (bArrDoFinal2.length != 32) {
                            throw new IllegalStateException("Cryptogram wrong length " + bArrDoFinal2.length);
                        }
                        this.mac.init(secretKey2);
                        byte[] bArrDoFinal3 = this.mac.doFinal(Util.pad(bArrDoFinal2, 8));
                        if (bArrDoFinal3.length != 8) {
                            throw new IllegalStateException("MAC wrong length");
                        }
                        byte[] bArr6 = new byte[40];
                        System.arraycopy(bArrDoFinal2, 0, bArr6, 0, 32);
                        System.arraycopy(bArrDoFinal3, 0, bArr6, 32, 8);
                        ResponseAPDU responseAPDUTransmit = this.service.transmit(new CommandAPDU(0, -126, 0, 0, bArr6, 40));
                        if (responseAPDUTransmit == null) {
                            throw new CardServiceException("Mutual authentication failed, received null response APDU");
                        }
                        byte[] bytes = responseAPDUTransmit.getBytes();
                        short sw = (short) responseAPDUTransmit.getSW();
                        if (bytes == null) {
                            throw new CardServiceException("Mutual authentication failed, received empty data in response APDU", sw);
                        }
                        if (sw != -28672) {
                            ResponseAPDU responseAPDUTransmit2 = this.service.transmit(new CommandAPDU(0, -126, 0, 0, bArr6, 0));
                            bytes = responseAPDUTransmit2.getBytes();
                            sw = (short) responseAPDUTransmit2.getSW();
                        }
                        if (bytes.length != 42) {
                            throw new CardServiceProtocolException("Mutual authentication failed: expected length: 40 + 2, actual length: " + bytes.length, 0, sw);
                        }
                        this.cipher.init(2, secretKey, ivParameterSpec);
                        bArrDoFinal = this.cipher.doFinal(bytes, 0, bytes.length - 10);
                        if (bArrDoFinal.length != 32) {
                            throw new CardServiceException("Cryptogram wrong length, was expecting 32, found " + bArrDoFinal.length, sw);
                        }
                    }
                } catch (GeneralSecurityException e) {
                    throw new CardServiceException("Security exception during mutual auth", e);
                }
            }
            throw new IllegalArgumentException("rndIFD wrong length");
        }
        return bArrDoFinal;
    }
}
