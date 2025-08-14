package org.jmrtd.protocol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import net.sf.scuba.smartcards.APDUWrapper;
import net.sf.scuba.smartcards.CommandAPDU;
import net.sf.scuba.smartcards.ResponseAPDU;
import net.sf.scuba.tlv.TLVUtil;
import org.jmrtd.Util;

/* loaded from: classes4.dex */
public abstract class SecureMessagingWrapper implements Serializable, APDUWrapper {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd.protocol");
    private static final long serialVersionUID = 4709645514566992414L;
    private transient Cipher cipher;
    private SecretKey ksEnc;
    private SecretKey ksMac;
    private transient Mac mac;
    private int maxTranceiveLength;
    private boolean shouldCheckMAC;
    private long ssc;

    private byte[] encodeLe(int i) {
        return (i < 0 || i > 256) ? new byte[]{(byte) ((65280 & i) >> 8), (byte) (i & 255)} : new byte[]{(byte) i};
    }

    protected abstract byte[] getEncodedSendSequenceCounter();

    public SecretKey getEncryptionKey() {
        return this.ksEnc;
    }

    protected abstract IvParameterSpec getIV() throws GeneralSecurityException;

    public SecretKey getMACKey() {
        return this.ksMac;
    }

    public int getMaxTranceiveLength() {
        return this.maxTranceiveLength;
    }

    protected abstract int getPadLength();

    public long getSendSequenceCounter() {
        return this.ssc;
    }

    public boolean shouldCheckMAC() {
        return this.shouldCheckMAC;
    }

    protected SecureMessagingWrapper(SecretKey secretKey, SecretKey secretKey2, String str, String str2, int i, boolean z, long j) throws GeneralSecurityException {
        this.maxTranceiveLength = i;
        this.shouldCheckMAC = z;
        this.ksEnc = secretKey;
        this.ksMac = secretKey2;
        this.ssc = j;
        this.cipher = Util.getCipher(str);
        this.mac = Util.getMac(str2);
    }

    public static SecureMessagingWrapper getInstance(SecureMessagingWrapper secureMessagingWrapper) {
        try {
        } catch (GeneralSecurityException e) {
            LOGGER.log(Level.WARNING, "Could not copy wrapper", (Throwable) e);
        }
        if (secureMessagingWrapper instanceof DESedeSecureMessagingWrapper) {
            return new DESedeSecureMessagingWrapper((DESedeSecureMessagingWrapper) secureMessagingWrapper);
        }
        if (secureMessagingWrapper instanceof AESSecureMessagingWrapper) {
            return new AESSecureMessagingWrapper((AESSecureMessagingWrapper) secureMessagingWrapper);
        }
        LOGGER.warning("Not copying wrapper");
        return secureMessagingWrapper;
    }

    @Override // net.sf.scuba.smartcards.APDUWrapper
    public CommandAPDU wrap(CommandAPDU commandAPDU) {
        this.ssc++;
        try {
            return wrapCommandAPDU(commandAPDU);
        } catch (IOException e) {
            throw new IllegalStateException("Unexpected exception", e);
        } catch (GeneralSecurityException e2) {
            throw new IllegalStateException("Unexpected exception", e2);
        }
    }

    @Override // net.sf.scuba.smartcards.APDUWrapper
    public ResponseAPDU unwrap(ResponseAPDU responseAPDU) {
        this.ssc++;
        try {
            byte[] data = responseAPDU.getData();
            if (data == null || data.length <= 0) {
                throw new IllegalStateException("Card indicates SM error, SW = " + Integer.toHexString(responseAPDU.getSW() & 65535));
            }
            return unwrapResponseAPDU(responseAPDU);
        } catch (IOException e) {
            throw new IllegalStateException("Unexpected exception", e);
        } catch (GeneralSecurityException e2) {
            throw new IllegalStateException("Unexpected exception", e2);
        }
    }

    protected boolean checkMac(byte[] bArr, byte[] bArr2) throws IllegalStateException, GeneralSecurityException, IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.write(getEncodedSendSequenceCounter());
            byte[] bArrPad = Util.pad(bArr, 0, bArr.length - 12, getPadLength());
            dataOutputStream.write(bArrPad, 0, bArrPad.length);
            dataOutputStream.flush();
            dataOutputStream.close();
            this.mac.init(this.ksMac);
            byte[] bArrDoFinal = this.mac.doFinal(byteArrayOutputStream.toByteArray());
            if (bArrDoFinal.length > 8 && bArr2.length == 8) {
                byte[] bArr3 = new byte[8];
                System.arraycopy(bArrDoFinal, 0, bArr3, 0, 8);
                bArrDoFinal = bArr3;
            }
            return Arrays.equals(bArr2, bArrDoFinal);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Exception checking MAC", (Throwable) e);
            return false;
        }
    }

    private CommandAPDU wrapCommandAPDU(CommandAPDU commandAPDU) throws GeneralSecurityException, IOException {
        int cla = commandAPDU.getCLA();
        int ins = commandAPDU.getINS();
        int p1 = commandAPDU.getP1();
        int p2 = commandAPDU.getP2();
        int nc = commandAPDU.getNc();
        int ne = commandAPDU.getNe();
        byte[] bArr = {(byte) (cla | 12), (byte) ins, (byte) p1, (byte) p2};
        byte[] bArrPad = Util.pad(bArr, getPadLength());
        int i = ((byte) commandAPDU.getINS()) == -79 ? 1 : 0;
        byte[] byteArray = new byte[0];
        byte[] bArrWrapDO = new byte[0];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (ne > 0) {
            try {
                bArrWrapDO = TLVUtil.wrapDO(151, encodeLe(ne));
            } finally {
            }
        }
        if (nc > 0) {
            byte[] bArrPad2 = Util.pad(commandAPDU.getData(), getPadLength());
            this.cipher.init(1, this.ksEnc, getIV());
            byte[] bArrDoFinal = this.cipher.doFinal(bArrPad2);
            byteArrayOutputStream.reset();
            byteArrayOutputStream.write(i != 0 ? -123 : -121);
            byteArrayOutputStream.write(TLVUtil.getLengthAsBytes(bArrDoFinal.length + (i ^ 1)));
            if (i == 0) {
                byteArrayOutputStream.write(1);
            }
            byteArrayOutputStream.write(bArrDoFinal, 0, bArrDoFinal.length);
            byteArray = byteArrayOutputStream.toByteArray();
        }
        byteArrayOutputStream.reset();
        byteArrayOutputStream.write(getEncodedSendSequenceCounter());
        byteArrayOutputStream.write(bArrPad);
        byteArrayOutputStream.write(byteArray);
        byteArrayOutputStream.write(bArrWrapDO);
        byte[] bArrPad3 = Util.pad(byteArrayOutputStream.toByteArray(), getPadLength());
        this.mac.init(this.ksMac);
        byte[] bArrDoFinal2 = this.mac.doFinal(bArrPad3);
        int length = bArrDoFinal2.length;
        if (length != 8) {
            length = 8;
        }
        byteArrayOutputStream.reset();
        byteArrayOutputStream.write(-114);
        byteArrayOutputStream.write(length);
        byteArrayOutputStream.write(bArrDoFinal2, 0, length);
        byte[] byteArray2 = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.reset();
        byteArrayOutputStream.write(byteArray);
        byteArrayOutputStream.write(bArrWrapDO);
        byteArrayOutputStream.write(byteArray2);
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            LOGGER.log(Level.FINE, "Error closing stream", (Throwable) e);
        }
        byte[] byteArray3 = byteArrayOutputStream.toByteArray();
        if (ne <= 256 && byteArray3.length <= 255) {
            return new CommandAPDU(bArr[0], bArr[1], bArr[2], bArr[3], byteArray3, 256);
        }
        if (ne > 256 || byteArray3.length > 255) {
            return new CommandAPDU(bArr[0], bArr[1], bArr[2], bArr[3], byteArray3, 65536);
        }
        return new CommandAPDU(bArr[0], bArr[1], bArr[2], bArr[3], byteArray3, getMaxTranceiveLength());
    }

    private ResponseAPDU unwrapResponseAPDU(ResponseAPDU responseAPDU) throws GeneralSecurityException, IOException {
        byte[] bytes = responseAPDU.getBytes();
        if (bytes == null || bytes.length < 2) {
            throw new IllegalArgumentException("Invalid response APDU");
        }
        this.cipher.init(2, this.ksEnc, getIV());
        byte[] do87 = new byte[0];
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bytes));
        byte[] do8e = null;
        boolean z = false;
        short do99 = 0;
        while (!z) {
            try {
                byte b = dataInputStream.readByte();
                if (b == -123) {
                    do87 = readDO87(dataInputStream, true);
                } else if (b == -121) {
                    do87 = readDO87(dataInputStream, false);
                } else if (b == -114) {
                    do8e = readDO8E(dataInputStream);
                    z = true;
                } else if (b == -103) {
                    do99 = readDO99(dataInputStream);
                } else {
                    LOGGER.warning("Unexpected tag " + Integer.toHexString(b));
                }
            } catch (Throwable th) {
                dataInputStream.close();
                throw th;
            }
        }
        dataInputStream.close();
        if (shouldCheckMAC() && !checkMac(bytes, do8e)) {
            throw new IllegalStateException("Invalid MAC");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(do87, 0, do87.length);
        byteArrayOutputStream.write((65280 & do99) >> 8);
        byteArrayOutputStream.write(do99 & 255);
        return new ResponseAPDU(byteArrayOutputStream.toByteArray());
    }

    private byte[] readDO87(DataInputStream dataInputStream, boolean z) throws GeneralSecurityException, IOException {
        int unsignedByte;
        int unsignedByte2 = dataInputStream.readUnsignedByte();
        if ((unsignedByte2 & 128) == 128) {
            int i = unsignedByte2 & 127;
            int unsignedByte3 = 0;
            for (int i2 = 0; i2 < i; i2++) {
                unsignedByte3 = (unsignedByte3 << 8) | dataInputStream.readUnsignedByte();
            }
            if (!z && dataInputStream.readUnsignedByte() != 1) {
                throw new IllegalStateException("DO'87 expected 0x01 marker");
            }
            unsignedByte2 = unsignedByte3;
        } else if (!z && (unsignedByte = dataInputStream.readUnsignedByte()) != 1) {
            throw new IllegalStateException("DO'87 expected 0x01 marker, found " + Integer.toHexString(unsignedByte & 255));
        }
        if (!z) {
            unsignedByte2--;
        }
        byte[] bArr = new byte[unsignedByte2];
        dataInputStream.readFully(bArr);
        return Util.unpad(this.cipher.doFinal(bArr));
    }

    private short readDO99(DataInputStream dataInputStream) throws IOException {
        if (dataInputStream.readUnsignedByte() != 2) {
            throw new IllegalStateException("DO'99 wrong length");
        }
        byte b = dataInputStream.readByte();
        return (short) ((dataInputStream.readByte() & 255) | ((b & 255) << 8));
    }

    private byte[] readDO8E(DataInputStream dataInputStream) throws IOException {
        int unsignedByte = dataInputStream.readUnsignedByte();
        if (unsignedByte != 8 && unsignedByte != 16) {
            throw new IllegalStateException("DO'8E wrong length for MAC: " + unsignedByte);
        }
        byte[] bArr = new byte[unsignedByte];
        dataInputStream.readFully(bArr);
        return bArr;
    }

    public String toString() {
        return "SecureMessagingWrapper [ssc: " + this.ssc + ", ksEnc: " + this.ksEnc + ", ksMac: " + this.ksMac + ", maxTranceiveLength: " + this.maxTranceiveLength + ", shouldCheckMAC: " + this.shouldCheckMAC + "]";
    }

    public int hashCode() {
        SecretKey secretKey = this.ksEnc;
        int iHashCode = ((secretKey == null ? 0 : secretKey.hashCode()) + 31) * 31;
        SecretKey secretKey2 = this.ksMac;
        int iHashCode2 = (((((iHashCode + (secretKey2 != null ? secretKey2.hashCode() : 0)) * 31) + this.maxTranceiveLength) * 31) + (this.shouldCheckMAC ? 1231 : 1237)) * 31;
        long j = this.ssc;
        return iHashCode2 + ((int) (j ^ (j >>> 32)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SecureMessagingWrapper secureMessagingWrapper = (SecureMessagingWrapper) obj;
        SecretKey secretKey = this.ksEnc;
        if (secretKey == null) {
            if (secureMessagingWrapper.ksEnc != null) {
                return false;
            }
        } else if (!secretKey.equals(secureMessagingWrapper.ksEnc)) {
            return false;
        }
        SecretKey secretKey2 = this.ksMac;
        if (secretKey2 == null) {
            if (secureMessagingWrapper.ksMac != null) {
                return false;
            }
        } else if (!secretKey2.equals(secureMessagingWrapper.ksMac)) {
            return false;
        }
        return this.maxTranceiveLength == secureMessagingWrapper.maxTranceiveLength && this.shouldCheckMAC == secureMessagingWrapper.shouldCheckMAC && this.ssc == secureMessagingWrapper.ssc;
    }
}
