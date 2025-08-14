package com.drew.lang;

import java.io.EOFException;
import java.io.IOException;

/* loaded from: classes5.dex */
public class SequentialByteArrayReader extends SequentialReader {
    private final byte[] _bytes;
    private int _index;

    @Override // com.drew.lang.SequentialReader
    public long getPosition() {
        return this._index;
    }

    public SequentialByteArrayReader(byte[] bArr) {
        this(bArr, 0);
    }

    public SequentialByteArrayReader(byte[] bArr, int i) {
        bArr.getClass();
        this._bytes = bArr;
        this._index = i;
    }

    @Override // com.drew.lang.SequentialReader
    public byte getByte() throws IOException {
        int i = this._index;
        byte[] bArr = this._bytes;
        if (i >= bArr.length) {
            throw new EOFException("End of data reached.");
        }
        this._index = i + 1;
        return bArr[i];
    }

    @Override // com.drew.lang.SequentialReader
    public byte[] getBytes(int i) throws IOException {
        int i2 = this._index;
        int i3 = i2 + i;
        byte[] bArr = this._bytes;
        if (i3 > bArr.length) {
            throw new EOFException("End of data reached.");
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, i2, bArr2, 0, i);
        this._index += i;
        return bArr2;
    }

    @Override // com.drew.lang.SequentialReader
    public void getBytes(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this._index;
        int i4 = i3 + i2;
        byte[] bArr2 = this._bytes;
        if (i4 > bArr2.length) {
            throw new EOFException("End of data reached.");
        }
        System.arraycopy(bArr2, i3, bArr, i, i2);
        this._index += i2;
    }

    @Override // com.drew.lang.SequentialReader
    public void skip(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("n must be zero or greater.");
        }
        int i = this._index;
        if (i + j > this._bytes.length) {
            throw new EOFException("End of data reached.");
        }
        this._index = (int) (i + j);
    }

    @Override // com.drew.lang.SequentialReader
    public boolean trySkip(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("n must be zero or greater.");
        }
        int i = (int) (this._index + j);
        this._index = i;
        byte[] bArr = this._bytes;
        if (i <= bArr.length) {
            return true;
        }
        this._index = bArr.length;
        return false;
    }

    @Override // com.drew.lang.SequentialReader
    public int available() {
        return this._bytes.length - this._index;
    }
}
