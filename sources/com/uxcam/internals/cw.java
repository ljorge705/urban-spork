package com.uxcam.internals;

import androidx.core.view.ViewCompat;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: classes6.dex */
public final class cw {
    public static ByteBuffer a(ByteBuffer byteBuffer) {
        int i = -1;
        if (byteBuffer.hasRemaining()) {
            int i2 = -1;
            while (true) {
                if (!byteBuffer.hasRemaining()) {
                    break;
                }
                i2 = (i2 << 8) | (byteBuffer.get() & 255);
                if ((i2 & ViewCompat.MEASURED_SIZE_MASK) == 1) {
                    byteBuffer.position(byteBuffer.position());
                    break;
                }
            }
        }
        if (!byteBuffer.hasRemaining()) {
            return null;
        }
        int iPosition = byteBuffer.position();
        ByteBuffer byteBufferSlice = byteBuffer.slice();
        byteBufferSlice.order(ByteOrder.BIG_ENDIAN);
        while (true) {
            if (!byteBuffer.hasRemaining()) {
                break;
            }
            i = (i << 8) | (byteBuffer.get() & 255);
            if ((i & ViewCompat.MEASURED_SIZE_MASK) == 1) {
                byteBuffer.position(byteBuffer.position() - (i == 1 ? 4 : 3));
                byteBufferSlice.limit(byteBuffer.position() - iPosition);
            }
        }
        return byteBufferSlice;
    }

    public static final void a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        byte b = byteBuffer.get();
        byte b2 = byteBuffer.get();
        byteBuffer2.put(b);
        byteBuffer2.put(b2);
        while (byteBuffer.hasRemaining()) {
            byte b3 = byteBuffer.get();
            if (b == 0 && b2 == 0 && (b3 & 255) <= 3) {
                byteBuffer2.put((byte) 3);
                b = 3;
            } else {
                b = b2;
            }
            byteBuffer2.put(b3);
            b2 = b3;
        }
    }
}
