package com.adobe.internal.xmp.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class ByteBuffer {
    private byte[] buffer;
    private String encoding;
    private int length;

    public int length() {
        return this.length;
    }

    public ByteBuffer(int i) {
        this.encoding = null;
        this.buffer = new byte[i];
        this.length = 0;
    }

    public ByteBuffer(byte[] bArr) {
        this.encoding = null;
        this.buffer = bArr;
        this.length = bArr.length;
    }

    public ByteBuffer(byte[] bArr, int i) {
        this.encoding = null;
        if (i > bArr.length) {
            throw new ArrayIndexOutOfBoundsException("Valid length exceeds the buffer length.");
        }
        this.buffer = bArr;
        this.length = i;
    }

    public ByteBuffer(InputStream inputStream) throws IOException {
        this.encoding = null;
        this.length = 0;
        this.buffer = new byte[16384];
        while (true) {
            int i = inputStream.read(this.buffer, this.length, 16384);
            if (i <= 0) {
                return;
            }
            int i2 = this.length + i;
            this.length = i2;
            if (i != 16384) {
                return;
            } else {
                ensureCapacity(i2 + 16384);
            }
        }
    }

    public ByteBuffer(byte[] bArr, int i, int i2) {
        this.encoding = null;
        if (i2 > bArr.length - i) {
            throw new ArrayIndexOutOfBoundsException("Valid length exceeds the buffer length.");
        }
        byte[] bArr2 = new byte[i2];
        this.buffer = bArr2;
        System.arraycopy(bArr, i, bArr2, 0, i2);
        this.length = i2;
    }

    public InputStream getByteStream() {
        return new ByteArrayInputStream(this.buffer, 0, this.length);
    }

    public byte byteAt(int i) {
        if (i < this.length) {
            return this.buffer[i];
        }
        throw new IndexOutOfBoundsException("The index exceeds the valid buffer area");
    }

    public int charAt(int i) {
        if (i < this.length) {
            return this.buffer[i] & 255;
        }
        throw new IndexOutOfBoundsException("The index exceeds the valid buffer area");
    }

    public void append(byte b) {
        ensureCapacity(this.length + 1);
        byte[] bArr = this.buffer;
        int i = this.length;
        this.length = i + 1;
        bArr[i] = b;
    }

    public void append(byte[] bArr, int i, int i2) {
        ensureCapacity(this.length + i2);
        System.arraycopy(bArr, i, this.buffer, this.length, i2);
        this.length += i2;
    }

    public void append(byte[] bArr) {
        append(bArr, 0, bArr.length);
    }

    public void append(ByteBuffer byteBuffer) {
        append(byteBuffer.buffer, 0, byteBuffer.length);
    }

    public String getEncoding() {
        if (this.encoding == null) {
            int i = this.length;
            if (i < 2) {
                this.encoding = "UTF-8";
            } else {
                byte[] bArr = this.buffer;
                byte b = bArr[0];
                if (b == 0) {
                    if (i < 4 || bArr[1] != 0) {
                        this.encoding = "UTF-16BE";
                    } else if ((bArr[2] & 255) == 254 && (bArr[3] & 255) == 255) {
                        this.encoding = "UTF-32BE";
                    } else {
                        this.encoding = "UTF-32";
                    }
                } else if ((b & 255) < 128) {
                    if (bArr[1] != 0) {
                        this.encoding = "UTF-8";
                    } else if (i < 4 || bArr[2] != 0) {
                        this.encoding = "UTF-16LE";
                    } else {
                        this.encoding = "UTF-32LE";
                    }
                } else if ((b & 255) == 239) {
                    this.encoding = "UTF-8";
                } else if ((b & 255) != 254 && i >= 4 && bArr[2] == 0) {
                    this.encoding = "UTF-32";
                } else {
                    this.encoding = "UTF-16";
                }
            }
        }
        return this.encoding;
    }

    private void ensureCapacity(int i) {
        byte[] bArr = this.buffer;
        if (i > bArr.length) {
            byte[] bArr2 = new byte[bArr.length * 2];
            this.buffer = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }
    }
}
