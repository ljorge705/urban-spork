package org.jmrtd.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/* loaded from: classes4.dex */
public class PositionInputStream extends InputStream {
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final long MARK_NOT_SET = -1;
    private InputStream carrier;
    private long position = 0;
    private long markedPosition = -1;

    public long getPosition() {
        return this.position;
    }

    public PositionInputStream(InputStream inputStream) {
        this.carrier = inputStream;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i = this.carrier.read();
        if (i >= 0) {
            this.position++;
        }
        return i;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.carrier.read(bArr, i, i2);
        this.position += i3;
        return i3;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long jSkip = this.carrier.skip(j);
        if (jSkip <= 0) {
            LOGGER.warning("Carrier (" + this.carrier.getClass().getCanonicalName() + ")'s skip(" + j + ") only skipped " + jSkip + ", position = " + this.position);
        }
        this.position += jSkip;
        return jSkip;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        this.carrier.mark(i);
        this.markedPosition = this.position;
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        this.carrier.reset();
        this.position = this.markedPosition;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.carrier.markSupported();
    }
}
