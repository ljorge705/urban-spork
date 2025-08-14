package com.clevertap.android.sdk.gif;

import android.graphics.Bitmap;
import com.clevertap.android.sdk.Logger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes5.dex */
class GifDecoder {
    private static final int BYTES_PER_INTEGER = 4;
    private static final int DISPOSAL_BACKGROUND = 2;
    private static final int DISPOSAL_NONE = 1;
    private static final int DISPOSAL_PREVIOUS = 3;
    private static final int DISPOSAL_UNSPECIFIED = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    static final int LOOP_FOREVER = -1;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    static final int STATUS_FORMAT_ERROR = 1;
    static final int STATUS_OK = 0;
    static final int STATUS_OPEN_ERROR = 2;
    static final int STATUS_PARTIAL_DECODE = 3;
    private static final String TAG = "GifDecoder";
    private static final int WORK_BUFFER_SIZE = 16384;
    private int[] act;
    private final BitmapProvider bitmapProvider;
    private byte[] block;
    private int downsampledHeight;
    private int downsampledWidth;
    private int framePointer;
    private GifHeader header;
    private boolean isFirstFrameTransparent;
    private int loopIndex;
    private byte[] mainPixels;
    private int[] mainScratch;
    private GifHeaderParser parser;
    private final int[] pct;
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private int sampleSize;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;
    private byte[] workBuffer;
    private int workBufferPosition;
    private int workBufferSize;

    interface BitmapProvider {
        Bitmap obtain(int i, int i2, Bitmap.Config config);

        byte[] obtainByteArray(int i);

        int[] obtainIntArray(int i);

        void release(Bitmap bitmap);

        void release(byte[] bArr);

        void release(int[] iArr);
    }

    int getCurrentFrameIndex() {
        return this.framePointer;
    }

    ByteBuffer getData() {
        return this.rawData;
    }

    int getLoopIndex() {
        return this.loopIndex;
    }

    int getStatus() {
        return this.status;
    }

    void resetFrameIndex() {
        this.framePointer = -1;
    }

    void resetLoopIndex() {
        this.loopIndex = 0;
    }

    GifDecoder(BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer) {
        this(bitmapProvider, gifHeader, byteBuffer, 1);
    }

    GifDecoder(BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
        this(bitmapProvider);
        setData(gifHeader, byteBuffer, i);
    }

    GifDecoder(BitmapProvider bitmapProvider) {
        this.pct = new int[256];
        this.workBufferPosition = 0;
        this.workBufferSize = 0;
        this.bitmapProvider = bitmapProvider;
        this.header = new GifHeader();
    }

    GifDecoder() {
        this(new SimpleBitmapProvider());
    }

    boolean advance() {
        if (this.header.frameCount <= 0) {
            return false;
        }
        if (this.framePointer == getFrameCount() - 1) {
            this.loopIndex++;
        }
        if (this.header.loopCount != -1 && this.loopIndex > this.header.loopCount) {
            return false;
        }
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
        return true;
    }

    void clear() {
        this.header = null;
        byte[] bArr = this.mainPixels;
        if (bArr != null) {
            this.bitmapProvider.release(bArr);
        }
        int[] iArr = this.mainScratch;
        if (iArr != null) {
            this.bitmapProvider.release(iArr);
        }
        Bitmap bitmap = this.previousImage;
        if (bitmap != null) {
            this.bitmapProvider.release(bitmap);
        }
        this.previousImage = null;
        this.rawData = null;
        this.isFirstFrameTransparent = false;
        byte[] bArr2 = this.block;
        if (bArr2 != null) {
            this.bitmapProvider.release(bArr2);
        }
        byte[] bArr3 = this.workBuffer;
        if (bArr3 != null) {
            this.bitmapProvider.release(bArr3);
        }
    }

    int getByteSize() {
        return this.rawData.limit() + this.mainPixels.length + (this.mainScratch.length * 4);
    }

    int getDelay(int i) {
        if (i < 0 || i >= this.header.frameCount) {
            return -1;
        }
        return this.header.frames.get(i).delay;
    }

    int getFrameCount() {
        return this.header.frameCount;
    }

    int getHeight() {
        return this.header.height;
    }

    int getLoopCount() {
        return this.header.loopCount;
    }

    int getNextDelay() {
        int i;
        if (this.header.frameCount <= 0 || (i = this.framePointer) < 0) {
            return 0;
        }
        return getDelay(i);
    }

    synchronized Bitmap getNextFrame() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            Logger.d(TAG, "unable to decode frame, frameCount=" + this.header.frameCount + " framePointer=" + this.framePointer);
            this.status = 1;
        }
        int i = this.status;
        if (i != 1 && i != 2) {
            this.status = 0;
            GifFrame gifFrame = this.header.frames.get(this.framePointer);
            int i2 = this.framePointer - 1;
            GifFrame gifFrame2 = i2 >= 0 ? this.header.frames.get(i2) : null;
            int[] iArr = gifFrame.lct != null ? gifFrame.lct : this.header.gct;
            this.act = iArr;
            if (iArr == null) {
                Logger.d(TAG, "No Valid Color Table for frame #" + this.framePointer);
                this.status = 1;
                return null;
            }
            if (gifFrame.transparency) {
                int[] iArr2 = this.act;
                System.arraycopy(iArr2, 0, this.pct, 0, iArr2.length);
                int[] iArr3 = this.pct;
                this.act = iArr3;
                iArr3[gifFrame.transIndex] = 0;
            }
            return setPixels(gifFrame, gifFrame2);
        }
        Logger.d(TAG, "Unable to decode frame, status=" + this.status);
        return null;
    }

    int getWidth() {
        return this.header.width;
    }

    int read(InputStream inputStream, int i) throws IOException {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i > 0 ? i + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int i2 = inputStream.read(bArr, 0, 16384);
                    if (i2 == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i2);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                Logger.d(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                Logger.d(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    synchronized int read(byte[] bArr) {
        GifHeader header = getHeaderParser().setData(bArr).parseHeader();
        this.header = header;
        if (bArr != null) {
            setData(header, bArr);
        }
        return this.status;
    }

    synchronized void setData(GifHeader gifHeader, byte[] bArr) {
        setData(gifHeader, ByteBuffer.wrap(bArr));
    }

    synchronized void setData(GifHeader gifHeader, ByteBuffer byteBuffer) {
        setData(gifHeader, byteBuffer, 1);
    }

    synchronized void setData(GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Sample size must be >=0, not: " + i);
        }
        int iHighestOneBit = Integer.highestOneBit(i);
        this.status = 0;
        this.header = gifHeader;
        this.isFirstFrameTransparent = false;
        this.framePointer = -1;
        resetLoopIndex();
        ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        this.rawData = byteBufferAsReadOnlyBuffer;
        byteBufferAsReadOnlyBuffer.position(0);
        this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        this.savePrevious = false;
        Iterator<GifFrame> it = gifHeader.frames.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().dispose == 3) {
                this.savePrevious = true;
                break;
            }
        }
        this.sampleSize = iHighestOneBit;
        this.downsampledWidth = gifHeader.width / iHighestOneBit;
        this.downsampledHeight = gifHeader.height / iHighestOneBit;
        this.mainPixels = this.bitmapProvider.obtainByteArray(gifHeader.width * gifHeader.height);
        this.mainScratch = this.bitmapProvider.obtainIntArray(this.downsampledWidth * this.downsampledHeight);
    }

    boolean setFrameIndex(int i) {
        if (i < -1 || i >= getFrameCount()) {
            return false;
        }
        this.framePointer = i;
        return true;
    }

    private int averageColorsNear(int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = i; i9 < this.sampleSize + i; i9++) {
            byte[] bArr = this.mainPixels;
            if (i9 >= bArr.length || i9 >= i2) {
                break;
            }
            int i10 = this.act[bArr[i9] & 255];
            if (i10 != 0) {
                i4 += (i10 >> 24) & 255;
                i5 += (i10 >> 16) & 255;
                i6 += (i10 >> 8) & 255;
                i7 += i10 & 255;
                i8++;
            }
        }
        int i11 = i + i3;
        for (int i12 = i11; i12 < this.sampleSize + i11; i12++) {
            byte[] bArr2 = this.mainPixels;
            if (i12 >= bArr2.length || i12 >= i2) {
                break;
            }
            int i13 = this.act[bArr2[i12] & 255];
            if (i13 != 0) {
                i4 += (i13 >> 24) & 255;
                i5 += (i13 >> 16) & 255;
                i6 += (i13 >> 8) & 255;
                i7 += i13 & 255;
                i8++;
            }
        }
        if (i8 == 0) {
            return 0;
        }
        return ((i4 / i8) << 24) | ((i5 / i8) << 16) | ((i6 / i8) << 8) | (i7 / i8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v23, types: [short] */
    /* JADX WARN: Type inference failed for: r3v25 */
    private void decodeBitmapData(GifFrame gifFrame) {
        int i;
        short s;
        this.workBufferSize = 0;
        this.workBufferPosition = 0;
        if (gifFrame != null) {
            this.rawData.position(gifFrame.bufferFrameStart);
        }
        int i2 = gifFrame == null ? this.header.width * this.header.height : gifFrame.ih * gifFrame.iw;
        byte[] bArr = this.mainPixels;
        if (bArr == null || bArr.length < i2) {
            this.mainPixels = this.bitmapProvider.obtainByteArray(i2);
        }
        if (this.prefix == null) {
            this.prefix = new short[4096];
        }
        if (this.suffix == null) {
            this.suffix = new byte[4096];
        }
        if (this.pixelStack == null) {
            this.pixelStack = new byte[4097];
        }
        int i3 = readByte();
        int i4 = 1;
        int i5 = 1 << i3;
        int i6 = i5 + 1;
        int i7 = i5 + 2;
        int i8 = i3 + 1;
        int i9 = (1 << i8) - 1;
        for (int i10 = 0; i10 < i5; i10++) {
            this.prefix[i10] = 0;
            this.suffix[i10] = (byte) i10;
        }
        int i11 = -1;
        int i12 = 0;
        int block = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = i8;
        int i20 = i7;
        int i21 = i9;
        int i22 = -1;
        while (true) {
            if (i12 >= i2) {
                break;
            }
            int i23 = 3;
            if (block == 0) {
                block = readBlock();
                if (block <= 0) {
                    this.status = 3;
                    break;
                }
                i13 = 0;
            }
            i15 += (this.block[i13] & 255) << i14;
            i14 += 8;
            i13 += i4;
            block += i11;
            int i24 = i20;
            int i25 = i19;
            int i26 = i22;
            int i27 = i17;
            while (i14 >= i25) {
                int i28 = i15 & i21;
                i15 >>= i25;
                i14 -= i25;
                if (i28 != i5) {
                    if (i28 > i24) {
                        this.status = i23;
                    } else if (i28 != i6) {
                        int i29 = i8;
                        int i30 = i26;
                        if (i30 == -1) {
                            this.pixelStack[i18] = this.suffix[i28];
                            i26 = i28;
                            i27 = i26;
                            i8 = i29;
                            i18++;
                            i23 = 3;
                            i11 = -1;
                        } else {
                            if (i28 >= i24) {
                                i = i6;
                                this.pixelStack[i18] = (byte) i27;
                                s = i30;
                                i18++;
                            } else {
                                i = i6;
                                s = i28;
                            }
                            while (s >= i5) {
                                this.pixelStack[i18] = this.suffix[s];
                                s = this.prefix[s];
                                i18++;
                                i5 = i5;
                            }
                            int i31 = i5;
                            byte[] bArr2 = this.suffix;
                            int i32 = bArr2[s] & 255;
                            int i33 = i18 + 1;
                            int i34 = i7;
                            byte b = (byte) i32;
                            this.pixelStack[i18] = b;
                            if (i24 < 4096) {
                                this.prefix[i24] = (short) i30;
                                bArr2[i24] = b;
                                i24++;
                                if ((i24 & i21) == 0 && i24 < 4096) {
                                    i25++;
                                    i21 += i24;
                                }
                            }
                            i18 = i33;
                            while (i18 > 0) {
                                i18--;
                                this.mainPixels[i16] = this.pixelStack[i18];
                                i12++;
                                i16++;
                            }
                            i26 = i28;
                            i5 = i31;
                            i6 = i;
                            i7 = i34;
                            i23 = 3;
                            i11 = -1;
                            i27 = i32;
                            i8 = i29;
                        }
                    }
                    i20 = i24;
                    i19 = i25;
                    i22 = i26;
                    i17 = i27;
                    i4 = 1;
                    i11 = -1;
                    break;
                }
                i25 = i8;
                i24 = i7;
                i21 = i9;
                i11 = -1;
                i26 = -1;
            }
            i22 = i26;
            i20 = i24;
            i19 = i25;
            i17 = i27;
            i6 = i6;
            i4 = 1;
        }
        for (int i35 = i16; i35 < i2; i35++) {
            this.mainPixels[i35] = 0;
        }
    }

    private void fillRect(int[] iArr, GifFrame gifFrame, int i) {
        int i2 = gifFrame.ih / this.sampleSize;
        int i3 = gifFrame.iy / this.sampleSize;
        int i4 = gifFrame.iw / this.sampleSize;
        int i5 = gifFrame.ix / this.sampleSize;
        int i6 = this.downsampledWidth;
        int i7 = (i3 * i6) + i5;
        int i8 = (i2 * i6) + i7;
        while (i7 < i8) {
            int i9 = i7 + i4;
            for (int i10 = i7; i10 < i9; i10++) {
                iArr[i10] = i;
            }
            i7 += this.downsampledWidth;
        }
    }

    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    private Bitmap getNextBitmap() {
        Bitmap bitmapObtain = this.bitmapProvider.obtain(this.downsampledWidth, this.downsampledHeight, this.isFirstFrameTransparent ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        setAlpha(bitmapObtain);
        return bitmapObtain;
    }

    private int readBlock() {
        int i = readByte();
        if (i > 0) {
            try {
                if (this.block == null) {
                    this.block = this.bitmapProvider.obtainByteArray(255);
                }
                int i2 = this.workBufferSize;
                int i3 = this.workBufferPosition;
                int i4 = i2 - i3;
                if (i4 >= i) {
                    System.arraycopy(this.workBuffer, i3, this.block, 0, i);
                    this.workBufferPosition += i;
                } else if (this.rawData.remaining() + i4 >= i) {
                    System.arraycopy(this.workBuffer, this.workBufferPosition, this.block, 0, i4);
                    this.workBufferPosition = this.workBufferSize;
                    readChunkIfNeeded();
                    int i5 = i - i4;
                    System.arraycopy(this.workBuffer, 0, this.block, i4, i5);
                    this.workBufferPosition += i5;
                } else {
                    this.status = 1;
                }
            } catch (Exception e) {
                Logger.d(TAG, "Error Reading Block", e);
                this.status = 1;
            }
        }
        return i;
    }

    private int readByte() {
        try {
            readChunkIfNeeded();
            byte[] bArr = this.workBuffer;
            int i = this.workBufferPosition;
            this.workBufferPosition = i + 1;
            return bArr[i] & 255;
        } catch (Exception unused) {
            this.status = 1;
            return 0;
        }
    }

    private void readChunkIfNeeded() {
        if (this.workBufferSize > this.workBufferPosition) {
            return;
        }
        if (this.workBuffer == null) {
            this.workBuffer = this.bitmapProvider.obtainByteArray(16384);
        }
        this.workBufferPosition = 0;
        int iMin = Math.min(this.rawData.remaining(), 16384);
        this.workBufferSize = iMin;
        this.rawData.get(this.workBuffer, 0, iMin);
    }

    private Bitmap setPixels(GifFrame gifFrame, GifFrame gifFrame2) {
        int i;
        int iAverageColorsNear;
        int i2;
        int[] iArr = this.mainScratch;
        int i3 = 0;
        if (gifFrame2 == null) {
            Arrays.fill(iArr, 0);
        }
        int i4 = 3;
        int i5 = 2;
        int i6 = 1;
        if (gifFrame2 != null && gifFrame2.dispose > 0) {
            if (gifFrame2.dispose == 2) {
                if (!gifFrame.transparency) {
                    i2 = this.header.bgColor;
                    if (gifFrame.lct != null && this.header.bgIndex == gifFrame.transIndex) {
                    }
                    fillRect(iArr, gifFrame2, i2);
                } else if (this.framePointer == 0) {
                    this.isFirstFrameTransparent = true;
                }
                i2 = 0;
                fillRect(iArr, gifFrame2, i2);
            } else if (gifFrame2.dispose == 3) {
                if (this.previousImage == null) {
                    fillRect(iArr, gifFrame2, 0);
                } else {
                    int i7 = gifFrame2.ih / this.sampleSize;
                    int i8 = gifFrame2.iy / this.sampleSize;
                    int i9 = gifFrame2.iw / this.sampleSize;
                    int i10 = gifFrame2.ix / this.sampleSize;
                    int i11 = this.downsampledWidth;
                    this.previousImage.getPixels(iArr, (i8 * i11) + i10, i11, i10, i8, i9, i7);
                }
            }
        }
        decodeBitmapData(gifFrame);
        int i12 = gifFrame.ih / this.sampleSize;
        int i13 = gifFrame.iy / this.sampleSize;
        int i14 = gifFrame.iw / this.sampleSize;
        int i15 = gifFrame.ix / this.sampleSize;
        boolean z = this.framePointer == 0;
        int i16 = 8;
        int i17 = 0;
        int i18 = 1;
        while (i3 < i12) {
            if (gifFrame.interlace) {
                if (i17 >= i12) {
                    i18++;
                    if (i18 == i5) {
                        i17 = 4;
                    } else if (i18 == i4) {
                        i17 = i5;
                        i16 = 4;
                    } else if (i18 == 4) {
                        i16 = i5;
                        i17 = i6;
                    }
                }
                i = i17 + i16;
            } else {
                i = i17;
                i17 = i3;
            }
            int i19 = i17 + i13;
            if (i19 < this.downsampledHeight) {
                int i20 = this.downsampledWidth;
                int i21 = i19 * i20;
                int i22 = i21 + i15;
                int i23 = i22 + i14;
                if (i21 + i20 < i23) {
                    i23 = i21 + i20;
                }
                int i24 = this.sampleSize * i3 * gifFrame.iw;
                int i25 = ((i23 - i22) * this.sampleSize) + i24;
                int i26 = i22;
                while (i26 < i23) {
                    int i27 = i12;
                    int i28 = i13;
                    if (this.sampleSize == 1) {
                        iAverageColorsNear = this.act[this.mainPixels[i24] & 255];
                    } else {
                        iAverageColorsNear = averageColorsNear(i24, i25, gifFrame.iw);
                    }
                    if (iAverageColorsNear != 0) {
                        iArr[i26] = iAverageColorsNear;
                    } else if (!this.isFirstFrameTransparent && z) {
                        this.isFirstFrameTransparent = true;
                    }
                    i24 += this.sampleSize;
                    i26++;
                    i12 = i27;
                    i13 = i28;
                }
            }
            i3++;
            i12 = i12;
            i17 = i;
            i13 = i13;
            i4 = 3;
            i5 = 2;
            i6 = 1;
        }
        if (this.savePrevious && (gifFrame.dispose == 0 || gifFrame.dispose == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            Bitmap bitmap = this.previousImage;
            int i29 = this.downsampledWidth;
            bitmap.setPixels(iArr, 0, i29, 0, 0, i29, this.downsampledHeight);
        }
        Bitmap nextBitmap = getNextBitmap();
        int i30 = this.downsampledWidth;
        nextBitmap.setPixels(iArr, 0, i30, 0, 0, i30, this.downsampledHeight);
        return nextBitmap;
    }

    private static void setAlpha(Bitmap bitmap) {
        bitmap.setHasAlpha(true);
    }
}
