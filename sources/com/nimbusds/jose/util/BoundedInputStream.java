package com.nimbusds.jose.util;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class BoundedInputStream extends InputStream {
    private final InputStream in;
    private long mark;
    private final long max;
    private long pos;
    private boolean propagateClose;

    public long getLimitBytes() {
        return this.max;
    }

    public boolean isPropagateClose() {
        return this.propagateClose;
    }

    public void setPropagateClose(boolean z) {
        this.propagateClose = z;
    }

    public BoundedInputStream(InputStream inputStream, long j) {
        this.pos = 0L;
        this.mark = -1L;
        this.propagateClose = true;
        this.max = j;
        this.in = inputStream;
    }

    public BoundedInputStream(InputStream inputStream) {
        this(inputStream, -1L);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        long j = this.max;
        if (j >= 0 && this.pos >= j) {
            throw new IOException("Exceeded configured input limit of " + this.max + " bytes");
        }
        int i = this.in.read();
        this.pos++;
        return i;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.max;
        if (j >= 0 && this.pos >= j) {
            throw new IOException("Exceeded configured input limit of " + this.max + " bytes");
        }
        int i3 = this.in.read(bArr, i, i2);
        if (i3 == -1) {
            return -1;
        }
        long j2 = this.pos + i3;
        this.pos = j2;
        long j3 = this.max;
        if (j3 < 0 || j2 < j3) {
            return i3;
        }
        throw new IOException("Exceeded configured input limit of " + this.max + " bytes");
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long j2 = this.max;
        if (j2 >= 0) {
            j = Math.min(j, j2 - this.pos);
        }
        long jSkip = this.in.skip(j);
        this.pos += jSkip;
        return jSkip;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        long j = this.max;
        if (j < 0 || this.pos < j) {
            return this.in.available();
        }
        return 0;
    }

    public String toString() {
        return this.in.toString();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.propagateClose) {
            this.in.close();
        }
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.in.reset();
        this.pos = this.mark;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        this.in.mark(i);
        this.mark = this.pos;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.in.markSupported();
    }
}
