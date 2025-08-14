package net.sf.scuba.tlv;

import com.google.common.primitives.SignedBytes;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.scuba.smartcards.ISO7816;

/* loaded from: classes4.dex */
public class TLVUtil implements ASN1Constants {
    private static final Logger LOGGER = Logger.getLogger("net.sf.scuba.tlv");

    static int getTagClass(int i) {
        int i2 = 3;
        while (i2 >= 0 && ((255 << (i2 * 8)) & i) == 0) {
            i2--;
        }
        int i3 = i2 * 8;
        int i4 = ((i & (255 << i3)) >> i3) & 192;
        if (i4 == 0) {
            return 0;
        }
        if (i4 != 64) {
            return i4 != 128 ? 3 : 2;
        }
        return 1;
    }

    public static boolean isPrimitive(int i) {
        int i2 = 3;
        while (i2 >= 0 && ((255 << (i2 * 8)) & i) == 0) {
            i2--;
        }
        int i3 = i2 * 8;
        return (((i & (255 << i3)) >> i3) & 32) == 0;
    }

    private TLVUtil() {
    }

    public static int getTagLength(int i) {
        return getTagAsBytes(i).length;
    }

    public static int getLengthLength(int i) {
        return getLengthAsBytes(i).length;
    }

    public static byte[] getTagAsBytes(int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int iLog = ((int) (Math.log(i) / Math.log(256.0d))) + 1;
        for (int i2 = 0; i2 < iLog; i2++) {
            int i3 = ((iLog - i2) - 1) * 8;
            byteArrayOutputStream.write(((255 << i3) & i) >> i3);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int tagClass = getTagClass(i);
        if (tagClass == 1) {
            byteArray[0] = (byte) (byteArray[0] | SignedBytes.MAX_POWER_OF_TWO);
        } else if (tagClass == 2) {
            byteArray[0] = (byte) (byteArray[0] | 128);
        } else if (tagClass == 3) {
            byteArray[0] = (byte) (byteArray[0] | ISO7816.INS_GET_RESPONSE);
        }
        if (!isPrimitive(i)) {
            byteArray[0] = (byte) (byteArray[0] | 32);
        }
        return byteArray;
    }

    public static byte[] getLengthAsBytes(int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (i < 128) {
            byteArrayOutputStream.write(i);
        } else {
            int iLog = log(i, 256);
            byteArrayOutputStream.write(iLog | 128);
            for (int i2 = 0; i2 < iLog; i2++) {
                int i3 = ((iLog - i2) - 1) * 8;
                byteArrayOutputStream.write(((255 << i3) & i) >> i3);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] wrapDO(int i, byte[] bArr) throws IOException {
        if (bArr == null) {
            throw new IllegalArgumentException("Data to wrap is null");
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                TLVOutputStream tLVOutputStream = new TLVOutputStream(byteArrayOutputStream);
                tLVOutputStream.writeTag(i);
                tLVOutputStream.writeValue(bArr);
                tLVOutputStream.flush();
                tLVOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                throw new IllegalStateException("Error writing stream", e);
            }
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                LOGGER.log(Level.FINE, "Error closing stream", (Throwable) e2);
            }
        }
    }

    public static byte[] unwrapDO(int i, byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            throw new IllegalArgumentException("Wrapped data is null or length < 2");
        }
        TLVInputStream tLVInputStream = new TLVInputStream(new ByteArrayInputStream(bArr));
        try {
            try {
                int tag = tLVInputStream.readTag();
                if (tag != i) {
                    throw new IllegalArgumentException("Expected tag " + Integer.toHexString(i) + ", found tag " + Integer.toHexString(tag));
                }
                int length = tLVInputStream.readLength();
                byte[] bArr2 = new byte[length];
                System.arraycopy(tLVInputStream.readValue(), 0, bArr2, 0, length);
                return bArr2;
            } finally {
                try {
                    tLVInputStream.close();
                } catch (IOException e) {
                    LOGGER.log(Level.FINE, "Error closing stream", (Throwable) e);
                }
            }
        } catch (IOException e2) {
            throw new IllegalStateException("Error reading from stream", e2);
        }
    }

    private static int log(int i, int i2) {
        int i3 = 0;
        while (i > 0) {
            i /= i2;
            i3++;
        }
        return i3;
    }
}
