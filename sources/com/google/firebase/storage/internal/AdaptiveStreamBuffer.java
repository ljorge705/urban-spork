package com.google.firebase.storage.internal;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class AdaptiveStreamBuffer {
    private static final String TAG = "AdaptiveStreamBuffer";
    private static final Runtime runtime = Runtime.getRuntime();
    private byte[] buffer;
    private final InputStream source;
    private int availableBytes = 0;
    private boolean adaptiveMode = true;
    private boolean reachedEnd = false;

    public int available() {
        return this.availableBytes;
    }

    public byte[] get() {
        return this.buffer;
    }

    public boolean isFinished() {
        return this.reachedEnd;
    }

    public AdaptiveStreamBuffer(InputStream inputStream, int i) {
        this.source = inputStream;
        this.buffer = new byte[i];
    }

    public int advance(int i) throws IOException {
        int i2 = this.availableBytes;
        int i3 = 0;
        if (i <= i2) {
            int i4 = i2 - i;
            this.availableBytes = i4;
            byte[] bArr = this.buffer;
            System.arraycopy(bArr, i, bArr, 0, i4);
            return i;
        }
        this.availableBytes = 0;
        while (i3 < i) {
            int iSkip = (int) this.source.skip(i - i3);
            if (iSkip > 0) {
                i3 += iSkip;
            } else if (iSkip != 0) {
                continue;
            } else {
                if (this.source.read() == -1) {
                    break;
                }
                i3++;
            }
        }
        return i3;
    }

    public int fill(int i) throws IOException {
        if (i > this.buffer.length) {
            i = Math.min(i, resize(i));
        }
        while (true) {
            int i2 = this.availableBytes;
            if (i2 >= i) {
                break;
            }
            int i3 = this.source.read(this.buffer, i2, i - i2);
            if (i3 == -1) {
                this.reachedEnd = true;
                break;
            }
            this.availableBytes += i3;
        }
        return this.availableBytes;
    }

    private int resize(int i) {
        int iMax = Math.max(this.buffer.length * 2, i);
        Runtime runtime2 = runtime;
        long jMaxMemory = runtime2.maxMemory() - (runtime2.totalMemory() - runtime2.freeMemory());
        if (this.adaptiveMode && iMax < jMaxMemory) {
            try {
                byte[] bArr = new byte[iMax];
                System.arraycopy(this.buffer, 0, bArr, 0, this.availableBytes);
                this.buffer = bArr;
            } catch (OutOfMemoryError unused) {
                Log.w(TAG, "Turning off adaptive buffer resizing due to low memory.");
                this.adaptiveMode = false;
            }
        } else {
            Log.w(TAG, "Turning off adaptive buffer resizing to conserve memory.");
        }
        return this.buffer.length;
    }

    public void close() throws IOException {
        this.source.close();
    }
}
