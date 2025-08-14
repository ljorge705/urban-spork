package io.grpc.internal;

import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;

/* loaded from: classes6.dex */
class GzipInflatingBuffer implements Closeable {
    private static final int GZIP_HEADER_MIN_SIZE = 10;
    private static final int GZIP_MAGIC = 35615;
    private static final int GZIP_TRAILER_SIZE = 8;
    private static final int HEADER_COMMENT_FLAG = 16;
    private static final int HEADER_CRC_FLAG = 2;
    private static final int HEADER_EXTRA_FLAG = 4;
    private static final int HEADER_NAME_FLAG = 8;
    private static final int INFLATE_BUFFER_SIZE = 512;
    private static final int UNSIGNED_SHORT_SIZE = 2;
    private long expectedGzipTrailerIsize;
    private int gzipHeaderFlag;
    private int headerExtraToRead;
    private Inflater inflater;
    private int inflaterInputEnd;
    private int inflaterInputStart;
    private final CompositeReadableBuffer gzippedData = new CompositeReadableBuffer();
    private final CRC32 crc = new CRC32();
    private final GzipMetadataReader gzipMetadataReader = new GzipMetadataReader(this, null);
    private final byte[] inflaterInput = new byte[512];
    private State state = State.HEADER;
    private boolean closed = false;
    private int bytesConsumed = 0;
    private int deflatedBytesConsumed = 0;
    private boolean isStalled = true;

    private enum State {
        HEADER,
        HEADER_EXTRA_LEN,
        HEADER_EXTRA,
        HEADER_NAME,
        HEADER_COMMENT,
        HEADER_CRC,
        INITIALIZE_INFLATER,
        INFLATING,
        INFLATER_NEEDS_INPUT,
        TRAILER
    }

    int getAndResetBytesConsumed() {
        int i = this.bytesConsumed;
        this.bytesConsumed = 0;
        return i;
    }

    int getAndResetDeflatedBytesConsumed() {
        int i = this.deflatedBytesConsumed;
        this.deflatedBytesConsumed = 0;
        return i;
    }

    GzipInflatingBuffer() {
    }

    static /* synthetic */ int access$112(GzipInflatingBuffer gzipInflatingBuffer, int i) {
        int i2 = gzipInflatingBuffer.inflaterInputStart + i;
        gzipInflatingBuffer.inflaterInputStart = i2;
        return i2;
    }

    static /* synthetic */ int access$512(GzipInflatingBuffer gzipInflatingBuffer, int i) {
        int i2 = gzipInflatingBuffer.bytesConsumed + i;
        gzipInflatingBuffer.bytesConsumed = i2;
        return i2;
    }

    private class GzipMetadataReader {
        private GzipMetadataReader() {
        }

        /* synthetic */ GzipMetadataReader(GzipInflatingBuffer gzipInflatingBuffer, AnonymousClass1 anonymousClass1) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int readUnsignedByte() {
            int unsignedByte;
            if (GzipInflatingBuffer.this.inflaterInputEnd - GzipInflatingBuffer.this.inflaterInputStart > 0) {
                unsignedByte = GzipInflatingBuffer.this.inflaterInput[GzipInflatingBuffer.this.inflaterInputStart] & 255;
                GzipInflatingBuffer.access$112(GzipInflatingBuffer.this, 1);
            } else {
                unsignedByte = GzipInflatingBuffer.this.gzippedData.readUnsignedByte();
            }
            GzipInflatingBuffer.this.crc.update(unsignedByte);
            GzipInflatingBuffer.access$512(GzipInflatingBuffer.this, 1);
            return unsignedByte;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void skipBytes(int i) {
            int i2;
            int i3 = GzipInflatingBuffer.this.inflaterInputEnd - GzipInflatingBuffer.this.inflaterInputStart;
            if (i3 > 0) {
                int iMin = Math.min(i3, i);
                GzipInflatingBuffer.this.crc.update(GzipInflatingBuffer.this.inflaterInput, GzipInflatingBuffer.this.inflaterInputStart, iMin);
                GzipInflatingBuffer.access$112(GzipInflatingBuffer.this, iMin);
                i2 = i - iMin;
            } else {
                i2 = i;
            }
            if (i2 > 0) {
                byte[] bArr = new byte[512];
                int i4 = 0;
                while (i4 < i2) {
                    int iMin2 = Math.min(i2 - i4, 512);
                    GzipInflatingBuffer.this.gzippedData.readBytes(bArr, 0, iMin2);
                    GzipInflatingBuffer.this.crc.update(bArr, 0, iMin2);
                    i4 += iMin2;
                }
            }
            GzipInflatingBuffer.access$512(GzipInflatingBuffer.this, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int readableBytes() {
            return (GzipInflatingBuffer.this.inflaterInputEnd - GzipInflatingBuffer.this.inflaterInputStart) + GzipInflatingBuffer.this.gzippedData.readableBytes();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean readBytesUntilZero() {
            while (readableBytes() > 0) {
                if (readUnsignedByte() == 0) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int readUnsignedShort() {
            return readUnsignedByte() | (readUnsignedByte() << 8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long readUnsignedInt() {
            return readUnsignedShort() | (readUnsignedShort() << 16);
        }
    }

    boolean isStalled() {
        Preconditions.checkState(!this.closed, "GzipInflatingBuffer is closed");
        return this.isStalled;
    }

    boolean hasPartialData() {
        Preconditions.checkState(!this.closed, "GzipInflatingBuffer is closed");
        return (this.gzipMetadataReader.readableBytes() == 0 && this.state == State.HEADER) ? false : true;
    }

    void addGzippedBytes(ReadableBuffer readableBuffer) {
        Preconditions.checkState(!this.closed, "GzipInflatingBuffer is closed");
        this.gzippedData.addBuffer(readableBuffer);
        this.isStalled = false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.gzippedData.close();
        Inflater inflater = this.inflater;
        if (inflater != null) {
            inflater.end();
            this.inflater = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0075, code lost:
    
        if (r2 == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x007b, code lost:
    
        if (r6.state != io.grpc.internal.GzipInflatingBuffer.State.HEADER) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0085, code lost:
    
        if (r6.gzipMetadataReader.readableBytes() >= 10) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0088, code lost:
    
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0089, code lost:
    
        r6.isStalled = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x008b, code lost:
    
        return r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    int inflateBytes(byte[] r7, int r8, int r9) throws java.util.zip.ZipException, java.util.zip.DataFormatException {
        /*
            r6 = this;
            boolean r0 = r6.closed
            r1 = 1
            r0 = r0 ^ r1
            java.lang.String r2 = "GzipInflatingBuffer is closed"
            com.google.common.base.Preconditions.checkState(r0, r2)
            r0 = 0
            r3 = r0
        Lb:
            r2 = r1
        Lc:
            if (r2 == 0) goto L75
            int r4 = r9 - r3
            if (r4 <= 0) goto L75
            int[] r2 = io.grpc.internal.GzipInflatingBuffer.AnonymousClass1.$SwitchMap$io$grpc$internal$GzipInflatingBuffer$State
            io.grpc.internal.GzipInflatingBuffer$State r5 = r6.state
            int r5 = r5.ordinal()
            r2 = r2[r5]
            switch(r2) {
                case 1: goto L70;
                case 2: goto L6b;
                case 3: goto L66;
                case 4: goto L61;
                case 5: goto L5c;
                case 6: goto L57;
                case 7: goto L52;
                case 8: goto L40;
                case 9: goto L3b;
                case 10: goto L36;
                default: goto L1f;
            }
        L1f:
            java.lang.AssertionError r7 = new java.lang.AssertionError
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Invalid state: "
            r8.<init>(r9)
            io.grpc.internal.GzipInflatingBuffer$State r9 = r6.state
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L36:
            boolean r2 = r6.processTrailer()
            goto Lc
        L3b:
            boolean r2 = r6.fill()
            goto Lc
        L40:
            int r2 = r8 + r3
            int r2 = r6.inflate(r7, r2, r4)
            int r3 = r3 + r2
            io.grpc.internal.GzipInflatingBuffer$State r2 = r6.state
            io.grpc.internal.GzipInflatingBuffer$State r4 = io.grpc.internal.GzipInflatingBuffer.State.TRAILER
            if (r2 != r4) goto Lb
            boolean r2 = r6.processTrailer()
            goto Lc
        L52:
            boolean r2 = r6.initializeInflater()
            goto Lc
        L57:
            boolean r2 = r6.processHeaderCrc()
            goto Lc
        L5c:
            boolean r2 = r6.processHeaderComment()
            goto Lc
        L61:
            boolean r2 = r6.processHeaderName()
            goto Lc
        L66:
            boolean r2 = r6.processHeaderExtra()
            goto Lc
        L6b:
            boolean r2 = r6.processHeaderExtraLen()
            goto Lc
        L70:
            boolean r2 = r6.processHeader()
            goto Lc
        L75:
            if (r2 == 0) goto L89
            io.grpc.internal.GzipInflatingBuffer$State r7 = r6.state
            io.grpc.internal.GzipInflatingBuffer$State r8 = io.grpc.internal.GzipInflatingBuffer.State.HEADER
            if (r7 != r8) goto L88
            io.grpc.internal.GzipInflatingBuffer$GzipMetadataReader r7 = r6.gzipMetadataReader
            int r7 = io.grpc.internal.GzipInflatingBuffer.GzipMetadataReader.access$700(r7)
            r8 = 10
            if (r7 >= r8) goto L88
            goto L89
        L88:
            r1 = r0
        L89:
            r6.isStalled = r1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.GzipInflatingBuffer.inflateBytes(byte[], int, int):int");
    }

    /* renamed from: io.grpc.internal.GzipInflatingBuffer$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State = iArr;
            try {
                iArr[State.HEADER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.HEADER_EXTRA_LEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.HEADER_EXTRA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.HEADER_NAME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.HEADER_COMMENT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.HEADER_CRC.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.INITIALIZE_INFLATER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.INFLATING.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.INFLATER_NEEDS_INPUT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[State.TRAILER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    private boolean processHeader() throws ZipException {
        if (this.gzipMetadataReader.readableBytes() < 10) {
            return false;
        }
        if (this.gzipMetadataReader.readUnsignedShort() == GZIP_MAGIC) {
            if (this.gzipMetadataReader.readUnsignedByte() == 8) {
                this.gzipHeaderFlag = this.gzipMetadataReader.readUnsignedByte();
                this.gzipMetadataReader.skipBytes(6);
                this.state = State.HEADER_EXTRA_LEN;
                return true;
            }
            throw new ZipException("Unsupported compression method");
        }
        throw new ZipException("Not in GZIP format");
    }

    private boolean processHeaderExtraLen() {
        if ((this.gzipHeaderFlag & 4) == 4) {
            if (this.gzipMetadataReader.readableBytes() < 2) {
                return false;
            }
            this.headerExtraToRead = this.gzipMetadataReader.readUnsignedShort();
            this.state = State.HEADER_EXTRA;
            return true;
        }
        this.state = State.HEADER_NAME;
        return true;
    }

    private boolean processHeaderExtra() {
        int i = this.gzipMetadataReader.readableBytes();
        int i2 = this.headerExtraToRead;
        if (i < i2) {
            return false;
        }
        this.gzipMetadataReader.skipBytes(i2);
        this.state = State.HEADER_NAME;
        return true;
    }

    private boolean processHeaderName() {
        if ((this.gzipHeaderFlag & 8) == 8) {
            if (!this.gzipMetadataReader.readBytesUntilZero()) {
                return false;
            }
            this.state = State.HEADER_COMMENT;
            return true;
        }
        this.state = State.HEADER_COMMENT;
        return true;
    }

    private boolean processHeaderComment() {
        if ((this.gzipHeaderFlag & 16) == 16) {
            if (!this.gzipMetadataReader.readBytesUntilZero()) {
                return false;
            }
            this.state = State.HEADER_CRC;
            return true;
        }
        this.state = State.HEADER_CRC;
        return true;
    }

    private boolean processHeaderCrc() throws ZipException {
        if ((this.gzipHeaderFlag & 2) == 2) {
            if (this.gzipMetadataReader.readableBytes() < 2) {
                return false;
            }
            if ((((int) this.crc.getValue()) & 65535) != this.gzipMetadataReader.readUnsignedShort()) {
                throw new ZipException("Corrupt GZIP header");
            }
            this.state = State.INITIALIZE_INFLATER;
            return true;
        }
        this.state = State.INITIALIZE_INFLATER;
        return true;
    }

    private boolean initializeInflater() {
        Inflater inflater = this.inflater;
        if (inflater == null) {
            this.inflater = new Inflater(true);
        } else {
            inflater.reset();
        }
        this.crc.reset();
        int i = this.inflaterInputEnd;
        int i2 = this.inflaterInputStart;
        int i3 = i - i2;
        if (i3 > 0) {
            this.inflater.setInput(this.inflaterInput, i2, i3);
            this.state = State.INFLATING;
        } else {
            this.state = State.INFLATER_NEEDS_INPUT;
        }
        return true;
    }

    private int inflate(byte[] bArr, int i, int i2) throws ZipException, DataFormatException {
        Preconditions.checkState(this.inflater != null, "inflater is null");
        try {
            int totalIn = this.inflater.getTotalIn();
            int iInflate = this.inflater.inflate(bArr, i, i2);
            int totalIn2 = this.inflater.getTotalIn() - totalIn;
            this.bytesConsumed += totalIn2;
            this.deflatedBytesConsumed += totalIn2;
            this.inflaterInputStart += totalIn2;
            this.crc.update(bArr, i, iInflate);
            if (this.inflater.finished()) {
                this.expectedGzipTrailerIsize = this.inflater.getBytesWritten() & 4294967295L;
                this.state = State.TRAILER;
            } else if (this.inflater.needsInput()) {
                this.state = State.INFLATER_NEEDS_INPUT;
            }
            return iInflate;
        } catch (DataFormatException e) {
            throw new DataFormatException("Inflater data format exception: " + e.getMessage());
        }
    }

    private boolean fill() {
        Preconditions.checkState(this.inflater != null, "inflater is null");
        Preconditions.checkState(this.inflaterInputStart == this.inflaterInputEnd, "inflaterInput has unconsumed bytes");
        int iMin = Math.min(this.gzippedData.readableBytes(), 512);
        if (iMin == 0) {
            return false;
        }
        this.inflaterInputStart = 0;
        this.inflaterInputEnd = iMin;
        this.gzippedData.readBytes(this.inflaterInput, 0, iMin);
        this.inflater.setInput(this.inflaterInput, this.inflaterInputStart, iMin);
        this.state = State.INFLATING;
        return true;
    }

    private boolean processTrailer() throws ZipException {
        if (this.inflater != null && this.gzipMetadataReader.readableBytes() <= 18) {
            this.inflater.end();
            this.inflater = null;
        }
        if (this.gzipMetadataReader.readableBytes() < 8) {
            return false;
        }
        if (this.crc.getValue() != this.gzipMetadataReader.readUnsignedInt() || this.expectedGzipTrailerIsize != this.gzipMetadataReader.readUnsignedInt()) {
            throw new ZipException("Corrupt GZIP trailer");
        }
        this.crc.reset();
        this.state = State.HEADER;
        return true;
    }
}
