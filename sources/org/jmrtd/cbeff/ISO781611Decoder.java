package org.jmrtd.cbeff;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessControlException;
import java.util.HashMap;
import java.util.logging.Logger;
import net.sf.scuba.tlv.TLVInputStream;
import net.sf.scuba.tlv.TLVUtil;

/* loaded from: classes4.dex */
public class ISO781611Decoder implements ISO781611 {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private BiometricDataBlockDecoder<?> bdbDecoder;

    public ISO781611Decoder(BiometricDataBlockDecoder<?> biometricDataBlockDecoder) {
        this.bdbDecoder = biometricDataBlockDecoder;
    }

    public ComplexCBEFFInfo decode(InputStream inputStream) throws IOException {
        return readBITGroup(inputStream);
    }

    private ComplexCBEFFInfo readBITGroup(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        int tag = tLVInputStream.readTag();
        if (tag != 32609) {
            throw new IllegalArgumentException("Expected tag " + Integer.toHexString(ISO781611.BIOMETRIC_INFORMATION_GROUP_TEMPLATE_TAG) + ", found " + Integer.toHexString(tag));
        }
        return readBITGroup(tag, tLVInputStream.readLength(), inputStream);
    }

    private ComplexCBEFFInfo readBITGroup(int i, int i2, InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        ComplexCBEFFInfo complexCBEFFInfo = new ComplexCBEFFInfo();
        if (i != 32609) {
            throw new IllegalArgumentException("Expected tag " + Integer.toHexString(ISO781611.BIOMETRIC_INFORMATION_GROUP_TEMPLATE_TAG) + ", found " + Integer.toHexString(i));
        }
        int tag = tLVInputStream.readTag();
        if (tag != 2) {
            throw new IllegalArgumentException("Expected tag BIOMETRIC_INFO_COUNT_TAG (" + Integer.toHexString(2) + ") in CBEFF structure, found " + Integer.toHexString(tag));
        }
        int length = tLVInputStream.readLength();
        if (length != 1) {
            throw new IllegalArgumentException("BIOMETRIC_INFO_COUNT should have length 1, found length " + length);
        }
        byte[] value = tLVInputStream.readValue();
        int i3 = value[0] & 255;
        for (int i4 = 0; i4 < i3; i4++) {
            complexCBEFFInfo.add(readBIT(inputStream, i4));
        }
        return complexCBEFFInfo;
    }

    private CBEFFInfo readBIT(InputStream inputStream, int i) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        return readBIT(tLVInputStream.readTag(), tLVInputStream.readLength(), inputStream, i);
    }

    private CBEFFInfo readBIT(int i, int i2, InputStream inputStream, int i3) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        if (i != 32608) {
            throw new IllegalArgumentException("Expected tag BIOMETRIC_INFORMATION_TEMPLATE_TAG (" + Integer.toHexString(ISO781611.BIOMETRIC_INFORMATION_TEMPLATE_TAG) + "), found " + Integer.toHexString(i) + ", index is " + i3);
        }
        int tag = tLVInputStream.readTag();
        int length = tLVInputStream.readLength();
        if (tag == 125) {
            readStaticallyProtectedBIT(inputStream, tag, length, i3);
            return null;
        }
        if ((tag & 160) == 160) {
            return new SimpleCBEFFInfo(readBiometricDataBlock(inputStream, readBHT(inputStream, tag, length, i3), i3));
        }
        throw new IllegalArgumentException("Unsupported template tag: " + Integer.toHexString(tag));
    }

    private StandardBiometricHeader readBHT(InputStream inputStream, int i, int i2, int i3) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        if (i != 161) {
            LOGGER.warning("Expected tag " + Integer.toHexString(161) + ", found " + Integer.toHexString(i));
        }
        HashMap map = new HashMap();
        int length = 0;
        while (length < i2) {
            int tag = tLVInputStream.readTag();
            int tagLength = length + TLVUtil.getTagLength(tag) + TLVUtil.getLengthLength(tLVInputStream.readLength());
            byte[] value = tLVInputStream.readValue();
            length = tagLength + value.length;
            map.put(Integer.valueOf(tag), value);
        }
        return new StandardBiometricHeader(map);
    }

    private void readStaticallyProtectedBIT(InputStream inputStream, int i, int i2, int i3) throws IOException {
        TLVInputStream tLVInputStream = new TLVInputStream(new ByteArrayInputStream(decodeSMTValue(inputStream)));
        try {
            readBiometricDataBlock(new ByteArrayInputStream(decodeSMTValue(inputStream)), readBHT(tLVInputStream, tLVInputStream.readTag(), tLVInputStream.readLength(), i3), i3);
        } finally {
            tLVInputStream.close();
        }
    }

    private byte[] decodeSMTValue(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        int tag = tLVInputStream.readTag();
        int length = tLVInputStream.readLength();
        if (tag == 129) {
            return tLVInputStream.readValue();
        }
        if (tag == 133) {
            throw new AccessControlException("Access denied. Biometric Information Template is statically protected.");
        }
        long jSkip = 0;
        if (tag == 142) {
            while (true) {
                long j = length;
                if (jSkip >= j) {
                    return null;
                }
                jSkip += tLVInputStream.skip(j);
            }
        } else {
            if (tag != 158) {
                LOGGER.info("Unsupported data object tag " + Integer.toHexString(tag));
                return null;
            }
            while (true) {
                long j2 = length;
                if (jSkip >= j2) {
                    return null;
                }
                jSkip += tLVInputStream.skip(j2);
            }
        }
    }

    private BiometricDataBlock readBiometricDataBlock(InputStream inputStream, StandardBiometricHeader standardBiometricHeader, int i) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        int tag = tLVInputStream.readTag();
        if (tag != 24366 && tag != 32558) {
            throw new IllegalArgumentException("Expected tag BIOMETRIC_DATA_BLOCK_TAG (" + Integer.toHexString(ISO781611.BIOMETRIC_DATA_BLOCK_TAG) + ") or BIOMETRIC_DATA_BLOCK_TAG_ALT (" + Integer.toHexString(ISO781611.BIOMETRIC_DATA_BLOCK_CONSTRUCTED_TAG) + "), found " + Integer.toHexString(tag));
        }
        return this.bdbDecoder.decode(inputStream, standardBiometricHeader, i, tLVInputStream.readLength());
    }
}
