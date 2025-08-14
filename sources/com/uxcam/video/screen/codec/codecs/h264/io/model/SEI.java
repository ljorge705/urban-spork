package com.uxcam.video.screen.codec.codecs.h264.io.model;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class SEI {
    public SEIMessage[] messages;

    public static class SEIMessage {
        public byte[] payload;
        public int payloadSize;
        public int payloadType;

        public SEIMessage(int i, int i2, byte[] bArr) {
            this.payload = bArr;
            this.payloadType = i;
            this.payloadSize = i2;
        }
    }

    public SEI(SEIMessage[] sEIMessageArr) {
        this.messages = sEIMessageArr;
    }

    public static SEI read(ByteBuffer byteBuffer) {
        SEIMessage sEIMessageSei_message;
        ArrayList arrayList = new ArrayList();
        do {
            sEIMessageSei_message = sei_message(byteBuffer);
            if (sEIMessageSei_message != null) {
                arrayList.add(sEIMessageSei_message);
            }
        } while (sEIMessageSei_message != null);
        return new SEI((SEIMessage[]) arrayList.toArray(new SEIMessage[0]));
    }

    private static SEIMessage sei_message(ByteBuffer byteBuffer) {
        int i;
        int i2;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            i = byteBuffer.get() & 255;
            if (i != 255) {
                break;
            }
            i4 += 255;
        }
        if (i == -1) {
            return null;
        }
        int i5 = i4 + i;
        while (true) {
            i2 = byteBuffer.get() & 255;
            if (i2 != 255) {
                break;
            }
            i3 += 255;
        }
        if (i2 == -1) {
            return null;
        }
        int i6 = i3 + i2;
        byte[] bArrSei_payload = sei_payload(i5, i6, byteBuffer);
        if (bArrSei_payload.length != i6) {
            return null;
        }
        return new SEIMessage(i5, i6, bArrSei_payload);
    }

    private static byte[] sei_payload(int i, int i2, ByteBuffer byteBuffer) {
        byte[] bArr = new byte[i2];
        byteBuffer.get(bArr);
        return bArr;
    }

    public void write(ByteBuffer byteBuffer) {
        byteBuffer.position();
        int i = Integer.MIN_VALUE;
        for (int i2 = 0; i2 < 1; i2++) {
            byteBuffer.put((byte) (i >>> 24));
            i <<= 8;
        }
    }
}
