package com.drew.lang;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes5.dex */
public class StreamReader extends SequentialReader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private long _pos;
    private final InputStream _stream;

    @Override // com.drew.lang.SequentialReader
    public long getPosition() {
        return this._pos;
    }

    public StreamReader(InputStream inputStream) {
        inputStream.getClass();
        this._stream = inputStream;
        this._pos = 0L;
    }

    @Override // com.drew.lang.SequentialReader
    public byte getByte() throws IOException {
        int i = this._stream.read();
        if (i == -1) {
            throw new EOFException("End of data reached.");
        }
        this._pos++;
        return (byte) i;
    }

    @Override // com.drew.lang.SequentialReader
    public byte[] getBytes(int i) throws IOException {
        byte[] bArr = new byte[i];
        getBytes(bArr, 0, i);
        return bArr;
    }

    @Override // com.drew.lang.SequentialReader
    public void getBytes(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 != i2) {
            int i4 = this._stream.read(bArr, i + i3, i2 - i3);
            if (i4 == -1) {
                throw new EOFException("End of data reached.");
            }
            i3 += i4;
        }
        this._pos += i3;
    }

    @Override // com.drew.lang.SequentialReader
    public void skip(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("n must be zero or greater.");
        }
        long jSkipInternal = skipInternal(j);
        if (jSkipInternal != j) {
            throw new EOFException(String.format("Unable to skip. Requested %d bytes but skipped %d.", Long.valueOf(j), Long.valueOf(jSkipInternal)));
        }
    }

    @Override // com.drew.lang.SequentialReader
    public boolean trySkip(long j) throws IOException {
        if (j >= 0) {
            return skipInternal(j) == j;
        }
        throw new IllegalArgumentException("n must be zero or greater.");
    }

    @Override // com.drew.lang.SequentialReader
    public int available() {
        try {
            return this._stream.available();
        } catch (IOException unused) {
            return 0;
        }
    }

    private long skipInternal(long j) throws IOException {
        long j2 = 0;
        while (j2 != j) {
            long jSkip = this._stream.skip(j - j2);
            j2 += jSkip;
            if (jSkip == 0) {
                break;
            }
        }
        this._pos += j2;
        return j2;
    }
}
