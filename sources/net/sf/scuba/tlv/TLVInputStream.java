package net.sf.scuba.tlv;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes4.dex */
public class TLVInputStream extends InputStream {
    private static final Logger LOGGER = Logger.getLogger("net.sf.scuba.tlv");
    private static final int MAX_BUFFER_LENGTH = 65535;
    private int bufferSize;
    private DataInputStream inputStream;
    private TLVInputState markedState;
    private final InputStream originalInputStream;
    private TLVInputState state;

    public TLVInputStream(InputStream inputStream) {
        this.bufferSize = 0;
        try {
            if ((inputStream instanceof BufferedInputStream) || (inputStream instanceof ByteArrayInputStream)) {
                this.bufferSize = inputStream.available();
            }
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Exception reading from stream", (Throwable) e);
        }
        this.originalInputStream = inputStream;
        this.inputStream = inputStream instanceof DataInputStream ? (DataInputStream) inputStream : new DataInputStream(inputStream);
        this.state = new TLVInputState();
        this.markedState = null;
    }

    public int readTag() throws IOException {
        if (!this.state.isAtStartOfTag() && !this.state.isProcessingValue()) {
            throw new IllegalStateException("Not at start of tag");
        }
        int unsignedByte = this.inputStream.readUnsignedByte();
        int i = 1;
        while (true) {
            if (unsignedByte != 0 && unsignedByte != 255) {
                break;
            }
            unsignedByte = this.inputStream.readUnsignedByte();
            i++;
        }
        if ((unsignedByte & 31) == 31) {
            int unsignedByte2 = this.inputStream.readUnsignedByte();
            while (true) {
                i++;
                if ((unsignedByte2 & 128) != 128) {
                    break;
                }
                unsignedByte = (unsignedByte << 8) | (unsignedByte2 & 127);
                unsignedByte2 = this.inputStream.readUnsignedByte();
            }
            unsignedByte = (unsignedByte << 8) | (unsignedByte2 & 127);
        }
        this.state.setTagProcessed(unsignedByte, i);
        return unsignedByte;
    }

    public int readLength() throws IOException {
        if (!this.state.isAtStartOfLength()) {
            throw new IllegalStateException("Not at start of length");
        }
        int unsignedByte = this.inputStream.readUnsignedByte();
        int i = 1;
        if ((unsignedByte & 128) != 0) {
            int i2 = unsignedByte & 127;
            int unsignedByte2 = 0;
            int i3 = 1;
            for (int i4 = 0; i4 < i2; i4++) {
                i3++;
                unsignedByte2 = (unsignedByte2 << 8) | this.inputStream.readUnsignedByte();
            }
            unsignedByte = unsignedByte2;
            i = i3;
        }
        this.state.setLengthProcessed(unsignedByte, i);
        return unsignedByte;
    }

    public byte[] readValue() throws IOException {
        if (!this.state.isProcessingValue()) {
            throw new IllegalStateException("Not yet processing value!");
        }
        int length = this.state.getLength();
        byte[] bArr = new byte[length];
        this.inputStream.readFully(bArr);
        this.state.updateValueBytesProcessed(length);
        return bArr;
    }

    private long skipValue() throws IOException {
        if (this.state.isAtStartOfTag() || this.state.isAtStartOfLength()) {
            return 0L;
        }
        return skip(this.state.getValueBytesLeft());
    }

    public void skipToTag(int i) throws IOException {
        while (true) {
            if (!this.state.isAtStartOfTag()) {
                if (this.state.isAtStartOfLength()) {
                    readLength();
                    if (TLVUtil.isPrimitive(this.state.getTag())) {
                        skipValue();
                    }
                } else if (TLVUtil.isPrimitive(this.state.getTag())) {
                    skipValue();
                }
            }
            int tag = readTag();
            if (tag == i) {
                return;
            }
            if (TLVUtil.isPrimitive(tag)) {
                if (((int) skipValue()) < readLength()) {
                    return;
                }
            }
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.inputStream.available();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i = this.inputStream.read();
        if (i < 0) {
            return -1;
        }
        this.state.updateValueBytesProcessed(1);
        return i;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        if (j <= 0) {
            return 0L;
        }
        long jSkip = this.inputStream.skip(j);
        this.state.updateValueBytesProcessed((int) jSkip);
        return jSkip;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        this.inputStream.mark(i);
        this.markedState = new TLVInputState(this.state);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.inputStream.markSupported();
    }

    @Override // java.io.InputStream
    public synchronized void reset() throws IOException {
        if (!markSupported()) {
            throw new IOException("mark/reset not supported");
        }
        this.inputStream.reset();
        this.state = this.markedState;
        this.markedState = null;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.inputStream.close();
    }

    public String toString() {
        return this.state.toString();
    }
}
