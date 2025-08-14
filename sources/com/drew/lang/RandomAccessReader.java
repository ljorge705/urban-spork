package com.drew.lang;

import androidx.core.view.ViewCompat;
import com.drew.metadata.StringValue;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* loaded from: classes5.dex */
public abstract class RandomAccessReader {
    private boolean _isMotorolaByteOrder = true;

    public abstract byte getByte(int i) throws IOException;

    public abstract byte[] getBytes(int i, int i2) throws IOException;

    public abstract long getLength() throws IOException;

    public boolean isMotorolaByteOrder() {
        return this._isMotorolaByteOrder;
    }

    protected abstract boolean isValidIndex(int i, int i2) throws IOException;

    public void setMotorolaByteOrder(boolean z) {
        this._isMotorolaByteOrder = z;
    }

    public abstract int toUnshiftedOffset(int i);

    protected abstract void validateIndex(int i, int i2) throws IOException;

    public boolean getBit(int i) throws IOException {
        int i2 = i / 8;
        validateIndex(i2, 1);
        return ((getByte(i2) >> (i % 8)) & 1) == 1;
    }

    public short getUInt8(int i) throws IOException {
        validateIndex(i, 1);
        return (short) (getByte(i) & 255);
    }

    public byte getInt8(int i) throws IOException {
        validateIndex(i, 1);
        return getByte(i);
    }

    public int getUInt16(int i) throws IOException {
        int i2;
        byte b;
        validateIndex(i, 2);
        if (this._isMotorolaByteOrder) {
            i2 = (getByte(i) << 8) & 65280;
            b = getByte(i + 1);
        } else {
            i2 = (getByte(i + 1) << 8) & 65280;
            b = getByte(i);
        }
        return (b & 255) | i2;
    }

    public short getInt16(int i) throws IOException {
        int i2;
        byte b;
        validateIndex(i, 2);
        if (this._isMotorolaByteOrder) {
            i2 = (getByte(i) << 8) & (-256);
            b = getByte(i + 1);
        } else {
            i2 = (getByte(i + 1) << 8) & (-256);
            b = getByte(i);
        }
        return (short) ((b & 255) | i2);
    }

    public int getInt24(int i) throws IOException {
        int i2;
        byte b;
        validateIndex(i, 3);
        if (this._isMotorolaByteOrder) {
            i2 = ((getByte(i) << 16) & 16711680) | (65280 & (getByte(i + 1) << 8));
            b = getByte(i + 2);
        } else {
            i2 = ((getByte(i + 2) << 16) & 16711680) | (65280 & (getByte(i + 1) << 8));
            b = getByte(i);
        }
        return (b & 255) | i2;
    }

    public long getUInt32(int i) throws IOException {
        long j;
        byte b;
        validateIndex(i, 4);
        if (this._isMotorolaByteOrder) {
            j = (65280 & (getByte(i + 2) << 8)) | (16711680 & (getByte(i + 1) << 16)) | (4278190080L & (getByte(i) << 24));
            b = getByte(i + 3);
        } else {
            j = (65280 & (getByte(i + 1) << 8)) | (16711680 & (getByte(i + 2) << 16)) | (4278190080L & (getByte(i + 3) << 24));
            b = getByte(i);
        }
        return (b & 255) | j;
    }

    public int getInt32(int i) throws IOException {
        int i2;
        byte b;
        validateIndex(i, 4);
        if (this._isMotorolaByteOrder) {
            i2 = ((getByte(i) << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | (16711680 & (getByte(i + 1) << 16)) | (65280 & (getByte(i + 2) << 8));
            b = getByte(i + 3);
        } else {
            i2 = ((getByte(i + 3) << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | (16711680 & (getByte(i + 2) << 16)) | (65280 & (getByte(i + 1) << 8));
            b = getByte(i);
        }
        return (b & 255) | i2;
    }

    public long getInt64(int i) throws IOException {
        long j;
        byte b;
        validateIndex(i, 8);
        if (this._isMotorolaByteOrder) {
            j = ((getByte(i) << 56) & (-72057594037927936L)) | ((getByte(i + 1) << 48) & 71776119061217280L) | ((getByte(i + 2) << 40) & 280375465082880L) | ((getByte(i + 3) << 32) & 1095216660480L) | ((getByte(i + 4) << 24) & 4278190080L) | ((getByte(i + 5) << 16) & 16711680) | ((getByte(i + 6) << 8) & 65280);
            b = getByte(i + 7);
        } else {
            j = ((getByte(i + 7) << 56) & (-72057594037927936L)) | ((getByte(i + 6) << 48) & 71776119061217280L) | ((getByte(i + 5) << 40) & 280375465082880L) | ((getByte(i + 4) << 32) & 1095216660480L) | ((getByte(i + 3) << 24) & 4278190080L) | ((getByte(i + 2) << 16) & 16711680) | ((getByte(i + 1) << 8) & 65280);
            b = getByte(i);
        }
        return j | (b & 255);
    }

    public float getS15Fixed16(int i) throws IOException {
        float f;
        int i2;
        byte b;
        validateIndex(i, 4);
        if (this._isMotorolaByteOrder) {
            f = ((getByte(i) & 255) << 8) | (getByte(i + 1) & 255);
            i2 = (getByte(i + 2) & 255) << 8;
            b = getByte(i + 3);
        } else {
            f = ((getByte(i + 3) & 255) << 8) | (getByte(i + 2) & 255);
            i2 = (getByte(i + 1) & 255) << 8;
            b = getByte(i);
        }
        return (float) (f + (((b & 255) | i2) / 65536.0d));
    }

    public float getFloat32(int i) throws IOException {
        return Float.intBitsToFloat(getInt32(i));
    }

    public double getDouble64(int i) throws IOException {
        return Double.longBitsToDouble(getInt64(i));
    }

    public StringValue getStringValue(int i, int i2, Charset charset) throws IOException {
        return new StringValue(getBytes(i, i2), charset);
    }

    public String getString(int i, int i2, Charset charset) throws IOException {
        return new String(getBytes(i, i2), charset.name());
    }

    public String getString(int i, int i2, String str) throws IOException {
        byte[] bytes = getBytes(i, i2);
        try {
            return new String(bytes, str);
        } catch (UnsupportedEncodingException unused) {
            return new String(bytes);
        }
    }

    public String getNullTerminatedString(int i, int i2, Charset charset) throws IOException {
        return new String(getNullTerminatedBytes(i, i2), charset.name());
    }

    public StringValue getNullTerminatedStringValue(int i, int i2, Charset charset) throws IOException {
        return new StringValue(getNullTerminatedBytes(i, i2), charset);
    }

    public byte[] getNullTerminatedBytes(int i, int i2) throws IOException {
        byte[] bytes = getBytes(i, i2);
        int i3 = 0;
        while (i3 < bytes.length && bytes[i3] != 0) {
            i3++;
        }
        if (i3 == i2) {
            return bytes;
        }
        byte[] bArr = new byte[i3];
        if (i3 > 0) {
            System.arraycopy(bytes, 0, bArr, 0, i3);
        }
        return bArr;
    }
}
