package com.clevertap.android.sdk.gif;

import androidx.core.view.ViewCompat;
import com.clevertap.android.sdk.Logger;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/* loaded from: classes5.dex */
class GifHeaderParser {
    private static final int DEFAULT_FRAME_DELAY = 10;
    private static final int MAX_BLOCK_SIZE = 256;
    private static final int MIN_FRAME_DELAY = 2;
    private static final String TAG = "GifHeaderParser";
    private final byte[] block = new byte[256];
    private int blockSize = 0;
    private GifHeader header;
    private ByteBuffer rawData;

    public void clear() {
        this.rawData = null;
        this.header = null;
    }

    GifHeaderParser() {
    }

    public boolean isAnimated() {
        readHeader();
        if (!err()) {
            readContents(2);
        }
        return this.header.frameCount > 1;
    }

    public GifHeaderParser setData(ByteBuffer byteBuffer) {
        reset();
        ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        this.rawData = byteBufferAsReadOnlyBuffer;
        byteBufferAsReadOnlyBuffer.position(0);
        this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public GifHeaderParser setData(byte[] bArr) {
        if (bArr != null) {
            setData(ByteBuffer.wrap(bArr));
        } else {
            this.rawData = null;
            this.header.status = 2;
        }
        return this;
    }

    GifHeader parseHeader() {
        if (this.rawData == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        }
        if (err()) {
            return this.header;
        }
        readHeader();
        if (!err()) {
            readContents();
            if (this.header.frameCount < 0) {
                this.header.status = 1;
            }
        }
        return this.header;
    }

    private boolean err() {
        return this.header.status != 0;
    }

    private int read() {
        try {
            return this.rawData.get() & 255;
        } catch (Exception unused) {
            this.header.status = 1;
            return 0;
        }
    }

    private void readBitmap() {
        this.header.currentFrame.ix = readShort();
        this.header.currentFrame.iy = readShort();
        this.header.currentFrame.iw = readShort();
        this.header.currentFrame.ih = readShort();
        int i = read();
        boolean z = (i & 128) != 0;
        int iPow = (int) Math.pow(2.0d, (i & 7) + 1);
        this.header.currentFrame.interlace = (i & 64) != 0;
        if (z) {
            this.header.currentFrame.lct = readColorTable(iPow);
        } else {
            this.header.currentFrame.lct = null;
        }
        this.header.currentFrame.bufferFrameStart = this.rawData.position();
        skipImageData();
        if (err()) {
            return;
        }
        this.header.frameCount++;
        this.header.frames.add(this.header.currentFrame);
    }

    private int readBlock() {
        int i = read();
        this.blockSize = i;
        int i2 = 0;
        if (i > 0) {
            while (true) {
                try {
                    int i3 = this.blockSize;
                    if (i2 >= i3) {
                        break;
                    }
                    int i4 = i3 - i2;
                    this.rawData.get(this.block, i2, i4);
                    i2 += i4;
                } catch (Exception unused) {
                    this.header.status = 1;
                }
            }
        }
        return i2;
    }

    private int[] readColorTable(int i) {
        byte[] bArr = new byte[i * 3];
        int[] iArr = null;
        try {
            this.rawData.get(bArr);
            iArr = new int[256];
            int i2 = 0;
            int i3 = 0;
            while (i2 < i) {
                int i4 = bArr[i3] & 255;
                int i5 = i3 + 2;
                int i6 = bArr[i3 + 1] & 255;
                i3 += 3;
                int i7 = i2 + 1;
                iArr[i2] = (i6 << 8) | (i4 << 16) | ViewCompat.MEASURED_STATE_MASK | (bArr[i5] & 255);
                i2 = i7;
            }
        } catch (BufferUnderflowException e) {
            Logger.d(TAG, "Format Error Reading Color Table", e);
            this.header.status = 1;
        }
        return iArr;
    }

    private void readContents() {
        readContents(Integer.MAX_VALUE);
    }

    private void readContents(int i) {
        boolean z = false;
        while (!z && !err() && this.header.frameCount <= i) {
            int i2 = read();
            if (i2 == 33) {
                int i3 = read();
                if (i3 == 1) {
                    skip();
                } else if (i3 == 249) {
                    this.header.currentFrame = new GifFrame();
                    readGraphicControlExt();
                } else if (i3 == 254) {
                    skip();
                } else if (i3 == 255) {
                    readBlock();
                    String str = "";
                    for (int i4 = 0; i4 < 11; i4++) {
                        str = str + ((char) this.block[i4]);
                    }
                    if (str.equals("NETSCAPE2.0")) {
                        readNetscapeExt();
                    } else {
                        skip();
                    }
                } else {
                    skip();
                }
            } else if (i2 == 44) {
                if (this.header.currentFrame == null) {
                    this.header.currentFrame = new GifFrame();
                }
                readBitmap();
            } else if (i2 != 59) {
                this.header.status = 1;
            } else {
                z = true;
            }
        }
    }

    private void readGraphicControlExt() {
        read();
        int i = read();
        this.header.currentFrame.dispose = (i & 28) >> 2;
        if (this.header.currentFrame.dispose == 0) {
            this.header.currentFrame.dispose = 1;
        }
        this.header.currentFrame.transparency = (i & 1) != 0;
        int i2 = readShort();
        if (i2 < 2) {
            i2 = 10;
        }
        this.header.currentFrame.delay = i2 * 10;
        this.header.currentFrame.transIndex = read();
        read();
    }

    private void readHeader() {
        String str = "";
        for (int i = 0; i < 6; i++) {
            str = str + ((char) read());
        }
        if (!str.startsWith("GIF")) {
            this.header.status = 1;
            return;
        }
        readLSD();
        if (!this.header.gctFlag || err()) {
            return;
        }
        GifHeader gifHeader = this.header;
        gifHeader.gct = readColorTable(gifHeader.gctSize);
        GifHeader gifHeader2 = this.header;
        gifHeader2.bgColor = gifHeader2.gct[this.header.bgIndex];
    }

    private void readLSD() {
        this.header.width = readShort();
        this.header.height = readShort();
        int i = read();
        this.header.gctFlag = (i & 128) != 0;
        this.header.gctSize = 2 << (i & 7);
        this.header.bgIndex = read();
        this.header.pixelAspect = read();
    }

    private void readNetscapeExt() {
        do {
            readBlock();
            byte[] bArr = this.block;
            if (bArr[0] == 1) {
                this.header.loopCount = ((bArr[2] & 255) << 8) | (bArr[1] & 255);
                if (this.header.loopCount == 0) {
                    this.header.loopCount = -1;
                }
            }
            if (this.blockSize <= 0) {
                return;
            }
        } while (!err());
    }

    private int readShort() {
        return this.rawData.getShort();
    }

    private void reset() {
        this.rawData = null;
        Arrays.fill(this.block, (byte) 0);
        this.header = new GifHeader();
        this.blockSize = 0;
    }

    private void skip() {
        int i;
        do {
            try {
                i = read();
                ByteBuffer byteBuffer = this.rawData;
                byteBuffer.position(byteBuffer.position() + i);
            } catch (IllegalArgumentException unused) {
                return;
            }
        } while (i > 0);
    }

    private void skipImageData() {
        read();
        skip();
    }
}
