package org.jmrtd.io;

import java.io.IOException;
import java.io.InputStream;
import org.jmrtd.io.FragmentBuffer;

/* loaded from: classes4.dex */
public class InputStreamBuffer {
    private FragmentBuffer buffer;
    private PositionInputStream carrier;

    public InputStreamBuffer(InputStream inputStream, int i) {
        PositionInputStream positionInputStream = new PositionInputStream(inputStream);
        this.carrier = positionInputStream;
        positionInputStream.mark(i);
        this.buffer = new FragmentBuffer(i);
    }

    public void updateFrom(InputStreamBuffer inputStreamBuffer) {
        this.buffer.updateFrom(inputStreamBuffer.buffer);
    }

    public SubInputStream getInputStream() {
        SubInputStream subInputStream;
        synchronized (this.carrier) {
            subInputStream = new SubInputStream(this.carrier);
        }
        return subInputStream;
    }

    public synchronized int getPosition() {
        return this.buffer.getPosition();
    }

    public synchronized int getBytesBuffered() {
        return this.buffer.getBytesBuffered();
    }

    public int getLength() {
        return this.buffer.getLength();
    }

    public String toString() {
        return "InputStreamBuffer [" + this.buffer + "]";
    }

    public class SubInputStream extends InputStream {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Object syncObject;
        private int position = 0;
        private int markedPosition = -1;

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        public int getPosition() {
            return this.position;
        }

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        public SubInputStream(Object obj) {
            this.syncObject = obj;
        }

        public FragmentBuffer getBuffer() {
            return InputStreamBuffer.this.buffer;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            synchronized (this.syncObject) {
                if (this.position >= InputStreamBuffer.this.buffer.getLength()) {
                    return -1;
                }
                if (InputStreamBuffer.this.buffer.isCoveredByFragment(this.position)) {
                    byte[] buffer = InputStreamBuffer.this.buffer.getBuffer();
                    int i = this.position;
                    this.position = i + 1;
                    return buffer[i] & 255;
                }
                if (InputStreamBuffer.this.carrier.markSupported()) {
                    syncCarrierPosition(this.position);
                }
                try {
                    int i2 = InputStreamBuffer.this.carrier.read();
                    if (i2 < 0) {
                        return -1;
                    }
                    FragmentBuffer fragmentBuffer = InputStreamBuffer.this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    fragmentBuffer.addFragment(i3, (byte) i2);
                    return i2;
                } catch (IOException e) {
                    throw e;
                }
            }
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            int i;
            synchronized (this.syncObject) {
                i = read(bArr, 0, bArr.length);
            }
            return i;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            synchronized (this.syncObject) {
                if (bArr == null) {
                    throw new NullPointerException();
                }
                if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
                    throw new IndexOutOfBoundsException();
                }
                if (i2 == 0) {
                    return 0;
                }
                if (i2 > InputStreamBuffer.this.buffer.getLength() - this.position) {
                    i2 = InputStreamBuffer.this.buffer.getLength() - this.position;
                }
                if (this.position >= InputStreamBuffer.this.buffer.getLength()) {
                    return -1;
                }
                if (InputStreamBuffer.this.carrier.markSupported()) {
                    syncCarrierPosition(this.position);
                }
                FragmentBuffer.Fragment smallestUnbufferedFragment = InputStreamBuffer.this.buffer.getSmallestUnbufferedFragment(this.position, i2);
                if (smallestUnbufferedFragment.getLength() <= 0) {
                    int iMin = Math.min(i2, InputStreamBuffer.this.buffer.getLength() - this.position);
                    System.arraycopy(InputStreamBuffer.this.buffer.getBuffer(), this.position, bArr, i, iMin);
                    this.position += iMin;
                    return iMin;
                }
                int offset = smallestUnbufferedFragment.getOffset() - this.position;
                int length = smallestUnbufferedFragment.getLength();
                System.arraycopy(InputStreamBuffer.this.buffer.getBuffer(), this.position, bArr, i, offset);
                this.position += offset;
                if (InputStreamBuffer.this.carrier.markSupported()) {
                    syncCarrierPosition(this.position);
                }
                int i3 = i + offset;
                int i4 = InputStreamBuffer.this.carrier.read(bArr, i3, length);
                InputStreamBuffer.this.buffer.addFragment(smallestUnbufferedFragment.getOffset(), bArr, i3, i4);
                this.position += i4;
                return offset + i4;
            }
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            long jSkip;
            synchronized (this.syncObject) {
                int bufferedLength = InputStreamBuffer.this.buffer.getBufferedLength(this.position);
                long j2 = bufferedLength;
                if (j <= j2) {
                    this.position = (int) (this.position + j);
                    return j;
                }
                this.position += bufferedLength;
                if (InputStreamBuffer.this.carrier.markSupported()) {
                    syncCarrierPosition(this.position);
                    jSkip = InputStreamBuffer.this.carrier.skip(j - j2);
                    this.position += (int) jSkip;
                } else {
                    jSkip = super.skip(j - j2);
                }
                return j2 + jSkip;
            }
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return InputStreamBuffer.this.buffer.getBufferedLength(this.position);
        }

        @Override // java.io.InputStream
        public synchronized void mark(int i) {
            this.markedPosition = this.position;
        }

        @Override // java.io.InputStream
        public synchronized void reset() throws IOException {
            int i = this.markedPosition;
            if (i < 0) {
                throw new IOException("Invalid reset, was mark() called?");
            }
            this.position = i;
        }

        private void syncCarrierPosition(int i) throws IOException {
            long j = i;
            if (j == InputStreamBuffer.this.carrier.getPosition()) {
                return;
            }
            InputStreamBuffer.this.carrier.reset();
            int iSkip = 0;
            while (iSkip < i) {
                long j2 = iSkip;
                iSkip = (int) (j2 + InputStreamBuffer.this.carrier.skip(j - j2));
            }
        }
    }
}
