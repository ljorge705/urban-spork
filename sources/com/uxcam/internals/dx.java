package com.uxcam.internals;

import java.nio.ByteBuffer;

/* loaded from: classes6.dex */
public final class dx {
    public static void a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        if (byteBuffer2.hasArray()) {
            byteBuffer.put(byteBuffer2.array(), byteBuffer2.position() + byteBuffer2.arrayOffset(), Math.min(byteBuffer.remaining(), byteBuffer2.remaining()));
        } else {
            byte[] bArr = new byte[Math.min(byteBuffer2.remaining(), byteBuffer.remaining())];
            byteBuffer2.duplicate().get(bArr);
            byteBuffer.put(bArr);
        }
    }
}
