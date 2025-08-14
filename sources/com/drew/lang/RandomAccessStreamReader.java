package com.drew.lang;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class RandomAccessStreamReader extends RandomAccessReader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_CHUNK_LENGTH = 2048;
    private final int _chunkLength;
    private final ArrayList<byte[]> _chunks;
    private boolean _isStreamFinished;
    private final InputStream _stream;
    private long _streamLength;

    @Override // com.drew.lang.RandomAccessReader
    public int toUnshiftedOffset(int i) {
        return i;
    }

    public RandomAccessStreamReader(InputStream inputStream) {
        this(inputStream, 2048, -1L);
    }

    public RandomAccessStreamReader(InputStream inputStream, int i) {
        this(inputStream, i, -1L);
    }

    public RandomAccessStreamReader(InputStream inputStream, int i, long j) {
        this._chunks = new ArrayList<>();
        inputStream.getClass();
        if (i <= 0) {
            throw new IllegalArgumentException("chunkLength must be greater than zero");
        }
        this._chunkLength = i;
        this._stream = inputStream;
        this._streamLength = j;
    }

    @Override // com.drew.lang.RandomAccessReader
    public long getLength() throws IOException {
        long j = this._streamLength;
        if (j != -1) {
            return j;
        }
        isValidIndex(Integer.MAX_VALUE, 1);
        return this._streamLength;
    }

    @Override // com.drew.lang.RandomAccessReader
    protected void validateIndex(int i, int i2) throws IOException {
        if (i < 0) {
            throw new BufferBoundsException(String.format("Attempt to read from buffer using a negative index (%d)", Integer.valueOf(i)));
        }
        if (i2 < 0) {
            throw new BufferBoundsException("Number of requested bytes must be zero or greater");
        }
        if ((i + i2) - 1 > 2147483647L) {
            throw new BufferBoundsException(String.format("Number of requested bytes summed with starting index exceed maximum range of signed 32 bit integers (requested index: %d, requested count: %d)", Integer.valueOf(i), Integer.valueOf(i2)));
        }
        if (!isValidIndex(i, i2)) {
            throw new BufferBoundsException(i, i2, this._streamLength);
        }
    }

    @Override // com.drew.lang.RandomAccessReader
    protected boolean isValidIndex(int i, int i2) throws IOException {
        int i3;
        if (i < 0 || i2 < 0) {
            return false;
        }
        long j = (i + i2) - 1;
        if (j > 2147483647L) {
            return false;
        }
        int i4 = (int) j;
        if (this._isStreamFinished) {
            return ((long) i4) < this._streamLength;
        }
        int i5 = i4 / this._chunkLength;
        while (i5 >= this._chunks.size()) {
            byte[] bArr = new byte[this._chunkLength];
            int i6 = 0;
            while (!this._isStreamFinished && i6 != (i3 = this._chunkLength)) {
                int i7 = this._stream.read(bArr, i6, i3 - i6);
                if (i7 == -1) {
                    this._isStreamFinished = true;
                    int size = (this._chunks.size() * this._chunkLength) + i6;
                    if (this._streamLength == -1) {
                        this._streamLength = size;
                    }
                    if (i4 >= this._streamLength) {
                        this._chunks.add(bArr);
                        return false;
                    }
                } else {
                    i6 += i7;
                }
            }
            this._chunks.add(bArr);
        }
        return true;
    }

    @Override // com.drew.lang.RandomAccessReader
    public byte getByte(int i) throws IOException {
        int i2 = this._chunkLength;
        int i3 = i / i2;
        return this._chunks.get(i3)[i % i2];
    }

    @Override // com.drew.lang.RandomAccessReader
    public byte[] getBytes(int i, int i2) throws IOException {
        validateIndex(i, i2);
        byte[] bArr = new byte[i2];
        int i3 = 0;
        while (i2 != 0) {
            int i4 = this._chunkLength;
            int i5 = i / i4;
            int i6 = i % i4;
            int iMin = Math.min(i2, i4 - i6);
            System.arraycopy(this._chunks.get(i5), i6, bArr, i3, iMin);
            i2 -= iMin;
            i += iMin;
            i3 += iMin;
        }
        return bArr;
    }
}
